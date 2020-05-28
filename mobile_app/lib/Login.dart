import 'dart:async';

import 'package:flutter/material.dart';
import 'package:mobile_app/models/User.dart';
import './Home.dart';

import 'package:http/http.dart' as http;
import 'dart:convert';

class Login extends StatefulWidget {
  static String tag = 'login-page';
  @override
  _Login createState() => new _Login();
}

class _Login extends State<Login> {

String email;
String password;



@override
  void initState() {
    super.initState();
    email="";
    password="";
    
  }

  Future<List> getData() async {
    var url = "http://1f56444784dc.ngrok.io/user";
        
    http.Response response = await http.get(
      //Uri.encodeFull removes all the dashes or extra characters present in our Uri
      Uri.encodeFull(url),
    );

    //print(response.body);
    final body = json.decode(response.body);
    List<User> users=[];
    for(var cada in body){
       User user = User.fromMap(cada);
       users.add(user);
    }
    return users;
    
  }


  @override
  Widget build(BuildContext context) {
    final logo = Hero(
      tag: 'hero',
      child:Text("")
    );

    final email = TextFormField(
      onChanged: (value) {
                                    this.email = value;
                                  },
      keyboardType: TextInputType.emailAddress,
      autofocus: false,
      decoration: InputDecoration(
        hintText: 'Email',
        contentPadding: EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
        border: OutlineInputBorder(borderRadius: BorderRadius.circular(32.0)),
      ),
    );

    final password = TextFormField(
      autofocus: false,
   onChanged: (value) {
                                    this.password = value;
                                  },
      decoration: InputDecoration(
        hintText: 'Password',
     hoverColor: Colors.white,
        contentPadding: EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
        border: OutlineInputBorder(borderRadius: BorderRadius.circular(32.0)),
      ),
    );

    final loginButton = Padding(
      padding: EdgeInsets.symmetric(vertical: 16.0),
      child: RaisedButton(
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(24),
        ),
        onPressed: () {
          autenticar(this.email,this.password,this.context);
        },
        padding: EdgeInsets.all(12),
        color: Colors.deepOrange,
        child: Text('Entrar', style: TextStyle(color: Colors.white)),
      ),
    );

   

    return Container(
      decoration:
              BoxDecoration(
              image: DecorationImage(
          image: AssetImage("assets/images/login.jpeg"),
          fit: BoxFit.cover,
          alignment: Alignment.topCenter,)),
      child: Scaffold(
        backgroundColor: Colors.transparent,
        body: Center(
          child: ListView(
            shrinkWrap: true,
            padding: EdgeInsets.only(left: 24.0, right: 24.0),
            children: <Widget>[
              // Text('happy birthday login),
              logo,
              SizedBox(height: 48.0),
              email,
              SizedBox(height: 8.0),
              password,
              SizedBox(height: 24.0),
              loginButton,
        
            ],
          ),
        ),
      ),
    );
  }


  autenticar(String email,String password,BuildContext context){
    print("verificando");
    getData().then((value) {
      for(var user in value){
        if(user.email==email && user.password==password) {
          Navigator.push(context, MaterialPageRoute(builder: (context) => Home(user:user)));
          break;
        }
        else {
          showError(context);
        }
        
      }
    });
  }
}


showError(BuildContext context) {
    // configura o button

    // configura o  AlertDialog
    AlertDialog alerta = AlertDialog(
      title: Text(
        "Por favor preencha todos os campos!",
        style: TextStyle(
            color: Colors.black,
   
            fontFamily: 'Monteserrat',
            letterSpacing: 2,
            fontSize: 20),
      ),
    );

    // exibe o dialog
    showDialog(
      barrierDismissible: false,
      context: context,
      builder: (BuildContext context) {
        return alerta;
      },
    );
    Timer(Duration(seconds: 2), () {
      Navigator.of(context, rootNavigator: true).pop();
    });
  }