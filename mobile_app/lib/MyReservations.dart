import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

import 'package:mobile_app/models/Reservation.dart';
import 'package:mobile_app/models/User.dart';


List<Reservation> reservations=[];
String proxima="          ";

class MyReservations extends StatefulWidget {

  User user;

  MyReservations({this.user});

  @override
  _MyReservationsState createState() => _MyReservationsState(user:this.user);
}

class _MyReservationsState extends State<MyReservations> {

  User user;

  _MyReservationsState({this.user});

  Future<List> getData() async {
    var url = "http://518c2d06fb72.ngrok.io/reservation";
        
    http.Response response = await http.get(
      //Uri.encodeFull removes all the dashes or extra characters present in our Uri
      Uri.encodeFull(url),
    );

    //print(response.body);
    final body = json.decode(utf8.decode(response.bodyBytes));
    List<Reservation> reservations=[];
    for(var cada in body){
       Reservation r=Reservation.fromMap(cada);
       if(r.user.email==this.user.email) reservations.add(r);
    }
    return reservations;
    
  }


  @override
  void initState() {
  

    getData().then((value) {

      if(value.length>1 ){
       

      int yearF=int.parse(value[0].date.substring(0,4));
    
      int monthF=int.parse(value[0].date.substring(5,7));
   
      int dayF=int.parse(value[0].date.substring(8,10));
   
      DateTime first= new DateTime(yearF,monthF,dayF);
      String proximaF=value[0].date;

      print(first);

      for(var reserva in value.sublist(1)) {
       
        int year=int.parse(reserva.date.substring(0,4));
    
        int month=int.parse(reserva.date.substring(5,7));
   
        int day=int.parse(reserva.date.substring(8,10));
   
       DateTime data= new DateTime(year,month,day);
      

        if(year<=first.year && month==first.month && day<=first.day) {
          
          proximaF=reserva.date;
          first=data;
       }
       else if(year<=first.year && month<first.month) {
          
          proximaF=reserva.date;
          first=data;
       }
       
        
      }
      setState(() {
        proxima=proximaF;
        reservations=value;
       
      });
      }
      else if(value.length==1){
        
        setState(() {
          proxima=value[0].date;
          reservations=value;
        });
      }
      
    });
    
    super.initState();
   
  }

   @override
  Widget build(BuildContext context) {
    
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        elevation: 0.0,
        backgroundColor: Colors.transparent,
        
        leading: IconButton(
          onPressed: () {Navigator.pop(context);},
          icon: Icon(Icons.arrow_back),
          color: Colors.black,
        ),
      ),
      body: SafeArea(
        child: Stack(
          children: <Widget>[
            Positioned(
              left: 0,
              right: 0,
              top: 0,
              height: MediaQuery.of(context).size.height / 3,
              child: Row(
                children: <Widget>[
                  Flexible(
                    flex: 3,
                    child: Container(
                      color: Colors.deepOrange,
                    ),
                  ),
                  Flexible(
                    flex: 1,
                    child: Container(
                      color: Colors.deepOrange,
                    ),
                  ),
                ],
              ),
            ),
            Positioned(
              top: 0,
              left: 0,
              right: 0,
              bottom: 70,
              child: Padding(
                padding: const EdgeInsets.all(15.0),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    Spacer(),
                    Text("As suas reservas", style: TextStyle(
                color: Colors.white,
                fontSize: 23,
                fontWeight: FontWeight.w600
              ),),
                    SizedBox(
                      height: 30,
                    ),
                    ClipRRect(
                      borderRadius: BorderRadius.circular(15.0),
                      child: Container(
                        decoration: BoxDecoration(
                          color: Colors.white,
                        ),
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: <Widget>[
                            SizedBox(
                              height: 15.0,
                            ),
                            Padding(
                              padding:
                                  const EdgeInsets.symmetric(horizontal: 15.0),
                              child: Row(
                                children: <Widget>[
                                  
                                  SizedBox(
                                    width: 15.0,
                                  ),
                                  Text(
                                    user.name,
                                    style: Theme.of(context)
                                        .textTheme
                                        .headline
                                        .apply(
                                            color: Colors.deepOrange,
                                            fontWeightDelta: 2),
                                  ),
                                  
                                ],
                              ),
                            ),
                            SizedBox(
                              height: 15.0,
                            ),
                            Padding(
                              padding:
                                  const EdgeInsets.symmetric(horizontal: 23.0),
                              child: RichText(
                                text: TextSpan(
                                  children: [
                                    TextSpan(
                                      text: reservations.length.toString(),
                                      style: Theme.of(context)
                                          .textTheme
                                          .headline
                                          .apply(
                                              color: Colors.deepOrange,
                                              fontWeightDelta: 2),
                                    ),
                                    TextSpan(
                                      text: " reserva(s) efetuadas ",
                                      style: Theme.of(context)
                                          .textTheme
                                          .body2
                                          .apply(color:Colors.deepOrange),
                                    )
                                  ],
                                ),
                              ),
                            ),
                            SizedBox(
                              height: 15.0,
                            ),
                            Container(
                              padding: const EdgeInsets.all(25.0),
                             color: Color(0xffFFEEE0),
                              child: Row(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: <Widget>[
                                  Align(
                                    alignment: Alignment.center,
                                    child: Text(
                                      "Próxima reserva:",
                                       style: TextStyle(
                color: Colors.black,
                fontSize: 25,
                fontWeight: FontWeight.w600
              ),),
                                  ),
                                  Spacer(),
                                  Text(
                                    proxima.substring(8,10),
                                    style: Theme.of(context)
                                        .textTheme
                                        .headline
                                        .apply(color: Colors.black),
                                  ),
                                  Text(
                                    "  / "+proxima.substring(5,7),
                                    style: Theme.of(context)
                                        .textTheme
                                        .body1
                                        .apply(color: Colors.deepOrange),
                                  )
                                  
                                ],
                              ),
                            )
                          ],
                        ),
                      ),
                    ),
                    Spacer(),
                    Text(
                      "Todas",
                       style: TextStyle(
                color: Colors.black,
                fontSize: 23,
                fontWeight: FontWeight.w600
              ),
                    ),
                    SizedBox(
                      height: 30,
                    ),
                    Container(
                      height: MediaQuery.of(context).size.height / 4,
                      child: ListView.builder(
                        scrollDirection: Axis.horizontal,
                        itemCount: reservations.length,
                        itemBuilder: (ctx, index) {
                          
                            
                            return Container(
                              width: 200,
                              margin:
                                  const EdgeInsets.symmetric(horizontal: 11.0),
                              child: ClipRRect(
                                borderRadius: BorderRadius.circular(15.0),
                                child: Stack(
                                  children: <Widget>[
                                   
                                    Positioned.fill(
                                      child: Image.network(
                                        reservations[index].service.saloon.image,
                                        fit: BoxFit.cover,
                                      ),
                                    ),
                                    Positioned(
                                      bottom: 0,
                                      left: 0,
                                      right: 0,
                                      child: Container(
                                        padding: EdgeInsets.symmetric(horizontal: 9.0, vertical: 5.0),
                                        decoration: BoxDecoration(
                                          
                                          borderRadius: BorderRadius.only(
                                            topRight: Radius.circular(15),
                                          ),
                                        ),
                                        child: Column(
                                          crossAxisAlignment: CrossAxisAlignment.start,
                                          children: <Widget>[
                                            
                                            GestureDetector(

                                              onTap: () async { await _delete(reservations[index]); getData().then((value) {

                                                 if(value.length>1 ){
       

      int yearF=int.parse(value[0].date.substring(0,4));
    
      int monthF=int.parse(value[0].date.substring(5,7));
   
      int dayF=int.parse(value[0].date.substring(8,10));
   
      DateTime first= new DateTime(yearF,monthF,dayF);
      String proximaF=value[0].date;

      for(var reserva in value.sublist(1)) {

        int year=int.parse(reserva.date.substring(0,4));
    
        int month=int.parse(reserva.date.substring(5,7));
   
        int day=int.parse(reserva.date.substring(8,10));
   
       DateTime data= new DateTime(year,month,day);
      

        if(year<=first.year && month<=first.month && day<=first.day) {
          
          proximaF=reserva.date;
          first=data;
       }
       else if(year<=first.year && month<first.month) {
          
          proximaF=reserva.date;
          first=data;
       }
        
      }
      setState(() {
        proxima=proximaF;
        reservations=value;
       
      });
      }
      else if(value.length==1){
        
        setState(() {
          proxima=value[0].date;
          reservations=value;
        });
      }
      else if (value.length==0){
        setState(() {
          proxima="           ";
          reservations=value;
        });
      }
      
    });

                                                },
                                                                                          child: Padding(
                                                padding: const EdgeInsets.only(left:130,bottom:40.0),
                                                child: Container(height:50,width:50,child: Icon(Icons.delete,color:Colors.deepOrange)),
                                              ),
                                            ),
                                            
                                            Padding(
                                              padding: const EdgeInsets.all(5.0),
                                              child: Text(
                                                reservations[index].service.saloon.name+ "           "+reservations[index].service.saloon.city, 
                                               style: TextStyle(
                                                      fontSize: 13,
                                                      fontWeight: FontWeight.bold,
                                                      color: Colors.white),
                                                      
                                              ),
                                            ),
                                            Padding(
                                              padding: const EdgeInsets.all(5.0),
                                              child: Text(
                                                  reservations[index].service.name,
                                                  style: TextStyle(
                                                      fontSize: 13,
                                                      fontWeight: FontWeight.bold,
                                                      color: Colors.white)),
                                            ),
                                            Padding(
                                              padding: const EdgeInsets.all(8.0),
                                              child: Text(
                                                  reservations[index].date+"    "+reservations[index].time.substring(0,5),
                                                  style: TextStyle(
                                                      fontSize: 17,
                                                      fontWeight: FontWeight.bold,
                                                      color: Colors.yellow)),
                                            ),
                                          ],
                                        ),
                                      ),
                                    )
                                  ],
                                ),
                              ),
                           
                          );
                        },
                      ),
                    ),
                    Spacer(),
                  ],
                ),
              ),
            ),
            
          ],
        ),
      ),
    );
  }
  


  _delete(Reservation r) async {
   
    var url = "http://518c2d06fb72.ngrok.io/reservation/"+r.id.toString();
        
    http.Response response = await http.delete(
      //Uri.encodeFull removes all the dashes or extra characters present in our Uri
      Uri.encodeFull(url),
    );
    
  
  }
}

