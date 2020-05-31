import 'dart:async';

import 'package:flutter/material.dart';
import 'package:mobile_app/models/User.dart';
import './Home.dart';

import 'package:http/http.dart' as http;
import 'dart:convert';

class Login extends StatefulWidget {
  
  @override
  _Login createState() => new _Login();
}

class _Login extends State<Login> {

String email;
String password;
bool loaded;



@override
  void initState() {
    saveData().then((dynamic)=>setState(()=>loaded=true));
    super.initState();
    email="";
    password="";
    
  }

  Future<List> getData() async {
    var url = "http://10.0.2.2:8080/user";
        
    http.Response response = await http.get(
      
      Uri.encodeFull(url),
    );

    final body = json.decode(response.body);
    List<User> users=[];
    for(var cada in body){
       User user = User.fromMap(cada);
       users.add(user);
    }
    return users;
    
  }

// WE SAVE DATA FOR THE MOBILE APP HERE (USER AND SALOONS)

   saveData() async {

    var url = "http://10.0.2.2:8080/saloon/save";
        
    await http.get(
      
      Uri.encodeFull(url),
    );
    
  }


  @override
  Widget build(BuildContext context) {
    final logo = Hero(
      tag: 'hero',
      child:Text("PÊLO",style:TextStyle(fontSize: 50,fontFamily: 'Cinzel'))
    );

    final email = TextFormField(
      key: Key('email_input'),
      onChanged: (value) {
                                    this.email = value;
                                  },
      keyboardType: TextInputType.emailAddress,
      autofocus: false,
      decoration: InputDecoration(
        focusColor: Colors.deepOrange,
        
        fillColor: Colors.deepOrange,
        hoverColor: Colors.deepOrange,
        hintText: 'Email',
        contentPadding: EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
        border: OutlineInputBorder(borderRadius: BorderRadius.circular(32.0),),
      ),
    );

    final password = TextFormField(
      autofocus: false,
      obscureText: true,
      key: Key('password_input'),
   onChanged: (value) {
                                    this.password = value;
                                  },
      decoration: InputDecoration(
        hintText: 'Password',
        
     focusColor: Colors.deepOrange,
        fillColor: Colors.deepOrange,
        hoverColor: Colors.deepOrange,
        contentPadding: EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
        border: OutlineInputBorder(borderRadius: BorderRadius.circular(32.0)),
      ),
    );

    final loginButton = Padding(
      padding: EdgeInsets.symmetric(vertical: 16.0),
      child: RaisedButton(
        key: Key('entrar_button'),
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

   
if(loaded==true){
    return Container(
      decoration:
              BoxDecoration(
              image: DecorationImage(
          image: AssetImage("assets/images/login.jpeg"),
          fit: BoxFit.cover,
          alignment: Alignment.topCenter,)),
      child: Scaffold(
        backgroundColor: Colors.transparent,
        body: 
          ListView(
            shrinkWrap: true,
            padding: EdgeInsets.only(left: 24.0, right: 24.0,top:150),
            children: <Widget>[
              // Text('happy birthday login),
              Padding(
                padding: const EdgeInsets.only(bottom:30.0),
                child: Center(child: logo),
              ),
              SizedBox(height: 48.0),
              email,
              SizedBox(height: 8.0),
              password,
              SizedBox(height: 24.0),
              loginButton,
        
            ],
          ),
        ),
      
    );
}
else return Container(
      decoration:
              BoxDecoration(
              image: DecorationImage(
          image: AssetImage("assets/images/login.jpeg"),
          fit: BoxFit.cover,
          alignment: Alignment.topCenter,)),
      child: Scaffold(
        backgroundColor: Colors.transparent,
       
        ),
      
    );
  }


  autenticar(String email,String password,BuildContext context){
  
    getData().then((value) {
      for(var user in value){
        if(user.email==email && user.password==password) {
          Navigator.push(context, MaterialPageRoute(builder: (context) => Home(userR:user)));
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
        "E-mail ou password incorretos!",
        key: Key('incorrect_credentials'),
        style: TextStyle(
            color: Colors.black,
   
            fontFamily: 'Monteserrat',
            letterSpacing: 2,
            fontSize: 15),
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