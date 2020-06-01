import 'dart:io';

import 'package:flutter/material.dart';
import 'package:mobile_app/models/Saloon.dart';
import 'package:mobile_app/models/Service.dart';
import 'package:mobile_app/models/User.dart';
import './Services.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';



List<Service> services=[];

class Details extends StatefulWidget {
  Saloon saloon;
  User user;

  Details({this.saloon,this.user});

  @override
  _DetailsState createState() => _DetailsState(saloon:this.saloon,user:this.user);
}

class _DetailsState extends State<Details> {

  Saloon saloon;
  User user;

  _DetailsState({this.saloon,this.user});




@override
  void initState() {

    

    
    getData().then((value) {
      setState(() {
        services=value;
      });
    });

    super.initState();


   
  }

Future<List> getData() async {
    var url = "http://d75fcf211556.ngrok.io/service";
        
    http.Response response = await http.get(
      //Uri.encodeFull removes all the dashes or extra characters present in our Uri
      Uri.encodeFull(url),
      
    );

    //print(response.body);
    final body = json.decode(utf8.decode(response.bodyBytes));
    List<Service> services=[];
    for(var cada in body){
       Service service = Service.fromMap(cada);
       if(service.saloon.name==this.saloon.name) services.add(service);
    }
    return services;
    
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0.0,
        brightness: Brightness.light,
        iconTheme: IconThemeData(
          color: Colors.black87
        ),
      ),
      body: SingleChildScrollView(
        child: Container(
          padding: EdgeInsets.symmetric(horizontal: 24),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Row(
                children: <Widget>[
                   Container(
        width: 160.0,
        height: 220.0,
        decoration: BoxDecoration(
          image: DecorationImage(
                fit: BoxFit.cover,
                image: NetworkImage(saloon.image)
              ),
          borderRadius: BorderRadius.all(Radius.circular(8.0)),
          
        ),
              ),
                  SizedBox(
                    width: 20,
                  ),
                  Container(
                    width:130,
                    height: 220,
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: <Widget>[
                        Flexible(
                                                  child: Text(
                            saloon.name,
                            style: TextStyle(fontSize: 25),
                          ),
                        ),
                        SizedBox(
                    height: 20,
                  ),
                        Text(
                         saloon.city,
                          style: TextStyle(fontSize: 18, color: Colors.grey),
                        ),
                        SizedBox(
                height: 20,
              ),
     Flexible(
                                child: Text(
                    saloon.address,
                    style: TextStyle(color: Colors.grey, fontSize: 16),
                  ),
                ),
              
                        SizedBox(
                          height: 40,
                        ),
                        
                      ],
                    ),
                  ),
                ],
              ),
              SizedBox(
                height: 30,
              ),
              Text(
                "Descrição",
                style: TextStyle(
                    color: Color(0xff242424),
                    fontSize: 20,
                    fontWeight: FontWeight.w600),
              ),
               
              SizedBox(
                height: 20,
              ),
              Text(
                saloon.description,
                style: TextStyle(color: Colors.grey, fontSize: 16),
              ),
              SizedBox(
                height: 24,
              ),
              Row(
                children: <Widget>[
                  Column(
                    children: <Widget>[
                      Row(
                        children: <Widget>[
                        
                          SizedBox(
                            width: 20,
                          ),
                          
                        ],
                      ),
                      SizedBox(
                        height: 20,
                      ),
                      Row(
                        children: <Widget>[
                          
                          SizedBox(
                            width: 20,
                          ),
                         
                        ],
                      )
                    ],
                  ),
                 
                ],
              ),
              Text(
                "Serviços disponíveis",
                key: Key('services_text'),
                style: TextStyle(
                    color: Color(0xff242424),
                    fontSize: 20,
                    fontWeight: FontWeight.w600),
              ),
              SizedBox(
                height: 22,
              ),
             
                    Container(
                     height: 200,
                      child: 
                      Row(
                                              children:[ Expanded(
                                                child: GridView.count(
                            crossAxisCount: 1,
                            childAspectRatio:
                                MediaQuery.of(context).size.height / 200,
                            children: List.generate(services.length, (index) {
                          
                          return Padding(
                            padding: const EdgeInsets.all(8.0),
                            child: Container(
                              height: 50,
                     
                      decoration: BoxDecoration(
                          color: Color(0xffFBB97C),
                          borderRadius: BorderRadius.circular(20)),
                              child: Padding(
                                padding: const EdgeInsets.all(8.0),
                                child: Row(
                                 
                                  mainAxisAlignment: MainAxisAlignment.center,
                                  children: <Widget>[
                                 
                                   
                                    GestureDetector(
                                      key: Key('service_container'),
                                      onTap: () => Navigator.push(context, MaterialPageRoute(builder: (context) => ServicesBook(service:services[index],user:user))),
                                                                child: Container(
                                        width: MediaQuery.of(context).size.width/1.4,
                                        child: Row(
                                                                                  children:[ Padding(
                                                                                    padding: const EdgeInsets.only(left:30.0,right: 15.0),
                                                                                    child: Icon(Icons.touch_app,color:Colors.deepOrange),
                                                                                  ),
                                                                                    Text(
                                            services[index].name,
                                            style: TextStyle(color: Colors.white,
                                            fontSize: 17),
                                          ),]
                                        ),
                                      ),
                                    )
                                  ],
                                ),
                              ),
                            ),
                          );})),
                        ),]
                      ),
                    ),
                  
            
            ],
          ),
        ),
      ),
    );
  }
}

class IconTile extends StatelessWidget {
  final String imgAssetPath;
  final Color backColor;

  IconTile({this.imgAssetPath, this.backColor});

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(right: 16),
      child: Container(
        height: 45,
        width: 45,
        decoration: BoxDecoration(
            color: backColor, borderRadius: BorderRadius.circular(15)),
        child: Image.asset(
          imgAssetPath,
          width: 20,
        ),
      ),
    );
  }
}