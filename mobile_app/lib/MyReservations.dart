import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

import 'package:mobile_app/models/Reservation.dart';


List<Reservation> reservations=[];

class MyReservations extends StatefulWidget {
  @override
  _MyReservationsState createState() => _MyReservationsState();
}

class _MyReservationsState extends State<MyReservations> {

  Future<List> getData() async {
    var url = "http://1f56444784dc.ngrok.io/reservation";
        
    http.Response response = await http.get(
      //Uri.encodeFull removes all the dashes or extra characters present in our Uri
      Uri.encodeFull(url),
    );

    //print(response.body);
    final body = json.decode(response.body);
    List<Reservation> reservations=[];
    for(var cada in body){
       Reservation r=Reservation.fromMap(cada);
       reservations.add(r);
    }
    return reservations;
    
  }


  @override
  void initState() {

    getData().then((value) {
      setState(() {
        reservations=value;
       
      });
    });
    
    super.initState();
   
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
        child: Column(
                  children:[ Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: Text(
                "Todas as minhas marcações já efetuadas: ",
                style: TextStyle(
                      color: Color(0xff242424),
                      fontSize: 20,
                      fontWeight: FontWeight.w600),
              ),
                  ),
              SizedBox(
                height: 30,
              ), Container(
            height: 600,
            padding: EdgeInsets.symmetric(horizontal: 24),
            child: Row(
                                                children:[ Expanded(
                                                  child: GridView.count(
                              crossAxisCount: 1,
                              
                              children: List.generate(reservations.length, (index) {
                            
                            return Padding(
                              padding: const EdgeInsets.all(8.0),
                              child: Container(
                            
                                    
                                      padding: EdgeInsets.all(8),
                                      decoration: BoxDecoration(
                                        image: DecorationImage(
          image: NetworkImage(reservations[index].service.saloon.image),
          fit: BoxFit.cover,
          alignment: Alignment.topCenter,),
                                        color: Colors.grey[300],
                                        borderRadius: BorderRadius.circular(20)
                                      ),
                                      child:Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            SizedBox(height: 100,),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Text(reservations[index].service.saloon.name, style: TextStyle(
                color: Colors.white,
                fontWeight: FontWeight.w900,
                fontSize: 20
              ),),
            ),
            SizedBox(height: 5,),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Text(reservations[index].service.saloon.city, style: TextStyle(
                color: Colors.white,
                fontWeight: FontWeight.w900,
                fontSize: 15
              ),),
            ),
            SizedBox(height: 7,),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Text(reservations[index].service.name,style: TextStyle(
                color: Colors.white,
                fontWeight: FontWeight.w900,
                fontSize: 15
              ),),
            ),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Text("Dia:     "+reservations[index].date+"    Horas:    "+reservations[index].time.substring(0, reservations[index].time.length - 3), style: TextStyle(
                color: Colors.white,
                fontWeight: FontWeight.w900,
                fontSize: 15
              ),),
            ),
            
          ],
        ),
                                    ),
                                   
                                
                              
                            );})),
                          ),]
                        ),
          ),
                  ]),
      ),
    );
  }
}

