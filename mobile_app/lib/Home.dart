import 'dart:io';

import 'package:flutter/material.dart';
import 'package:mobile_app/MyReservations.dart';
import 'package:mobile_app/models/Saloon.dart';
import 'package:mobile_app/models/User.dart';
import './Details.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';


String selectedCategorie= "Todos";
  List<Saloon> saloons=[];
  List<Saloon> filtered=[];

  User user;

class Home extends StatefulWidget {

  User userR;
  
  Home({this.userR});

  @override
  _Home createState() => _Home(userR:this.userR);
}

class _Home extends State<Home> {

User userR;
String search;
 
  _Home({this.userR});

  List<String> categories = ["Todos","Cabeleireiro","Barbeiro"];



  @override
  void initState() {

    
    setState(() {
      user=this.userR;
    });
    print("USER: "+user.email);

    getData().then((value) {
      setState(() {
        saloons=value;
        filtered=saloons;
      });
    });

    super.initState();


   
  }


  Future<List> getData() async {
    var url = "http://518c2d06fb72.ngrok.io/saloon";
        
    http.Response response = await http.get(
      //Uri.encodeFull removes all the dashes or extra characters present in our Uri
      Uri.encodeFull(url),
      headers: {HttpHeaders.contentTypeHeader: "application/json; charset=utf-8"}
    );

    //print(response.body);
    final body = json.decode(utf8.decode(response.bodyBytes));
    List<Saloon> saloons=[];
    for(var cada in body){
       Saloon saloon = Saloon.fromMap(cada);
       saloons.add(saloon);
    }
    return saloons;
    
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
     
      drawer: Drawer(
          child: Container()// Populate the Drawer in the next step.
      ),
      body: SingleChildScrollView(
        child: Container(
          color: Colors.white,
          padding: EdgeInsets.symmetric(vertical: 40,horizontal: 24),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              SizedBox(height: 10,),
              Text("Olá "+user.name+"! \n\nEncontre e reserve os serviços que procura", style: TextStyle(
                color: Colors.black87.withOpacity(0.8),
                fontSize: 23,
                fontWeight: FontWeight.w600
              ),),
              SizedBox(height: 40,),
              Container(
                padding: EdgeInsets.symmetric(horizontal: 24),
                height: 50,
                decoration: BoxDecoration(
                  color: Color(0xffEFEFEF),
                  borderRadius: BorderRadius.circular(14)
                ),
                child: Row(
                  children: <Widget>[
                    Icon(Icons.search),
                    SizedBox(width: 10,),
                   Container(
                                width: 220,
                                height: 50,
                                decoration: BoxDecoration(
                                  borderRadius: BorderRadius.circular(5.0),
                                  
                                ),
                                child: TextField(
                                 
                                  onChanged: (value) {
                                   if(value.length!=0){
                                      List<Saloon> tempFiltered=[];
                                      for(var cada in saloons){
                                        if(cada.name.toLowerCase()==value || cada.city.toLowerCase()==value){
                                           tempFiltered.add(cada);   

                                        }
                                      }  
                                      setState(() {
                                        filtered=tempFiltered;
                                      });
                                   }
                                   else setState(()=>filtered=saloons);
                              
                                  },
                                 
                                  
                                  style: TextStyle(
                                      color: Colors.black,
                                      fontFamily: 'Monteserrat',
                                      letterSpacing: 2,
                                      fontWeight: FontWeight.w900,
                                      fontSize: 15),
                                
                                  decoration: InputDecoration(
                                    contentPadding: EdgeInsets.all(10.0),
                                    
                                    hintText: "Procurar",
                                    fillColor: Colors.deepOrange,
                                  ),
                                ),
                              ),
                  ],
                ),
              ),
              SizedBox(height: 30,),
              Text("Categorias", style: TextStyle(
                  color: Colors.black87.withOpacity(0.8),
                  fontSize: 20,
                  fontWeight: FontWeight.w600
              ),),
              SizedBox(height: 20,),
              Container(
                height: 30,
                child: ListView.builder(
                itemCount: categories.length,
                    shrinkWrap: true,
                    physics: ClampingScrollPhysics(),
                    scrollDirection: Axis.horizontal,
                    itemBuilder: (context, index){
                  return CategorieTile(
                    categorie: categories[index],
                    isSelected: selectedCategorie == categories[index],
                    context: this,
                  );
                    }),
              ),
              SizedBox(height: 20,),
              Container(
                height: 250,
                child: ListView.builder(
                    itemCount: filtered.length,
                    shrinkWrap: true,
                    physics: ClampingScrollPhysics(),
                    scrollDirection: Axis.horizontal,
                    itemBuilder: (context, index){
                      return SpecialistTile(
                        user:user,
                        saloon: filtered[index],
                        image: filtered[index].image,
                        speciality: filtered[index].name,
                        city: filtered[index].city,
                        backColor: Colors.grey[200],
                      );
                    }),
              ),
              SizedBox(height: 20,),
              Text("As suas reservas", style: TextStyle(
                  color: Colors.black87.withOpacity(0.8),
                  fontSize: 20,
                  fontWeight: FontWeight.w600
              ),),
              SizedBox(height: 20,),
              DoctorsTile()
            ],
          ),
        ),
      ),
    );
  }
}


class CategorieTile extends StatefulWidget {

  final String categorie;
  final bool isSelected;
  _Home context;
  CategorieTile({this.categorie, this.isSelected,this.context});

  @override
  _CategorieTileState createState() => _CategorieTileState();
}


class _CategorieTileState extends State<CategorieTile> {



  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: (){
        widget.context.setState(() {
          selectedCategorie = widget.categorie;
          if(selectedCategorie=="Todos") setState((){filtered=saloons;});
          else{
         
            List<Saloon> tempFiltered=[];
            for(var cada in saloons){
              
              if(cada.type==selectedCategorie) {tempFiltered.add(cada);}
            }
            setState(() {
              filtered=tempFiltered;
            });
          }
        });
      },
      child: Container(
        alignment: Alignment.center,
        padding: EdgeInsets.symmetric(horizontal: 20),
        margin: EdgeInsets.only(left: 8),
        height: 30,
        decoration: BoxDecoration(
          color: widget.isSelected ? Color(0xffFFD0AA) : Colors.white,
          borderRadius: BorderRadius.circular(30)
        ),
        child: Text(widget.categorie, style: TextStyle(
          color: widget.isSelected ?  Colors.deepOrange : Color(0xffA1A1A1)
        ),),
      ),
    );
  }
}

class SpecialistTile extends StatelessWidget {

  final String image;
  final String speciality;
  final String city;
  final Color backColor;
  final Saloon saloon;
  final User user;

  SpecialistTile({@required this.user,@required this.saloon,@required this.image,@required this.speciality
    ,@required this.city, @required this.backColor});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap:() =>  Navigator.push(context, MaterialPageRoute(builder: (context) => Details(saloon:this.saloon,user:this.user))),
          child: Container(
        width: 150,
        margin: EdgeInsets.only(right: 16),
        decoration: BoxDecoration(
          color: backColor,
          borderRadius: BorderRadius.circular(24)
        ),
        padding: EdgeInsets.only(top: 16,right: 16,left: 16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Text(speciality, style: TextStyle(
              color: Colors.black,
              fontSize: 18
            ),),
            SizedBox(height: 7,),
            Text(city, style: TextStyle(
              color: Colors.black,
              fontSize: 13
            ),),
            Padding(
              padding: const EdgeInsets.only(top:10.0),
              child: Container(
        width: 140.0,
        height: 160.0,
        decoration: BoxDecoration(
          image: DecorationImage(
                fit: BoxFit.cover,
                image: NetworkImage(image)
              ),
          borderRadius: BorderRadius.all(Radius.circular(8.0)),
          
        ),
              ))
          ],
        ),
      ),
    );
  }
}

class DoctorsTile extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: (){
         
      },
      child: Container(
        decoration: BoxDecoration(
          color: Color(0xffFFEEE0),
          borderRadius: BorderRadius.circular(20)
        ),
        padding: EdgeInsets.only(left: 10,
        top: 18,bottom:18,right:15),
        child: Row(
          children: <Widget>[
          
            SizedBox(width: 17,),
            Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                Text("Veja aqui reservas efetuadas    ", style: TextStyle(
                  color: Colors.deepOrange,
                  fontSize: 15
                ),),
                
              ],
            ),
         
            GestureDetector(
              onTap: ()=> Navigator.push(context, MaterialPageRoute(builder: (context) => MyReservations(user:user))),
                          child: Container(
                padding: EdgeInsets.symmetric(horizontal: 10,
                vertical: 9),
                decoration: BoxDecoration(
                  color:Colors.deepOrange,
                  borderRadius: BorderRadius.circular(13)
                ),
                child: Text("Ver", style: TextStyle(
                  color: Colors.white,
                  fontSize: 13,
                  fontWeight: FontWeight.w500
                ),),
              ),
            )
          ],
        ),
      ),
    ); 
  }
}