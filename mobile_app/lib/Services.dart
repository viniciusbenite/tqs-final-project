import 'package:flutter/material.dart';
import 'package:mobile_app/Home.dart';
import 'package:mobile_app/models/Reservation.dart';
import 'package:mobile_app/models/Schedule.dart';
import 'package:mobile_app/models/Service.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

import 'package:mobile_app/models/User.dart';

List horas=[];


class ServicesBook extends StatefulWidget {

  Service service;
  User user;

  ServicesBook({this.service,this.user});

  @override
  _ServicesBookState createState() => _ServicesBookState(service:this.service,user:this.user);
}

class _ServicesBookState extends State<ServicesBook> {
 

  var selectedTime = '';
  var selectedTimeFinal = '';
  var _dateTime;
Service service;
User user;

  _ServicesBookState({this.service,this.user});


@override
  void initState() {

    

    
    getData().then((value) {
      setState(() {
        horas=value;
      });
    });

    super.initState();


   
  }


Future<List> getData() async {
    var url = "http://1f56444784dc.ngrok.io/schedule";
        
    http.Response response = await http.get(
      //Uri.encodeFull removes all the dashes or extra characters present in our Uri
      Uri.encodeFull(url),
    );

    //print(response.body);
    final body = json.decode(response.body);
    List<Schedule> schedules=[];
    for(var cada in body){
       Schedule schedule = Schedule.fromMap(cada);
       if(schedule.service.id==this.service.id) schedules.add(schedule);
    }
    return schedules;
    
  }


Future<http.Response> postData(Reservation reservation) async {
  return await http.post(
    'http://1f56444784dc.ngrok.io/reservation/',
    headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    },
    body: jsonEncode(<String, dynamic>{
      'date': reservation.date,
      'time':reservation.time,
      'users':reservation.user.toMap(),
      'services':reservation.service.toMap(),
    }),
  );
}


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        elevation: 0.0,
        backgroundColor: Colors.white,
        
        leading: IconButton(
          onPressed: () {Navigator.pop(context);},
          icon: Icon(Icons.arrow_back),
          color: Colors.black,
        ),
      ),
      body: ListView(
        children: <Widget>[
          Padding(
            padding: const EdgeInsets.only(left:30.0,top:20),
            child: Text(
                service.name,
                style: TextStyle(
                    letterSpacing: 2.0,
                    fontFamily: 'Nunito',
                    fontSize: 20.0,
                    color: Colors.black.withOpacity(0.6),
                    fontWeight: FontWeight.bold),
              ),
          ),
          Padding(
              padding: const EdgeInsets.only(top: 50.0, left:30.0),
              child: new Row(
                children: <Widget>[
                  Wrap(
                    direction: Axis.horizontal,
                    alignment: WrapAlignment.spaceEvenly,
                    runAlignment: WrapAlignment.start,
                    children: <Widget>[
                      Text(
              'Escolha o dia:',
              style: TextStyle(
                  letterSpacing: 2.0,
                  fontFamily: 'Nunito',
                  fontSize: 20.0,
                  color: Colors.black.withOpacity(0.6),
                  fontWeight: FontWeight.bold),
            ),
                      Padding(
                        padding: const EdgeInsets.only(left:20.0,top:5),
                        child: Text(
                          _dateTime == null
                              ? ''
                              : _dateTime.toString().split(' ')[0],
                          
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.only(left:10.0),
                        child: IconButton(
                          icon: Icon(Icons.calendar_today),
                  
                          onPressed: () {
                            showDatePicker(
                              context: context,
                              initialDate:
                                  _dateTime == null ? DateTime.now() : _dateTime,
                              firstDate: DateTime(2020),
                              lastDate: DateTime(2021),
                           
                            ).then((date) {
                              setState(() {
                                _dateTime = date;
                               
                              });
                            });
                          },
                        ),
                      ),
                    ],
                  ),
                ],
              ),
            ),
          SizedBox(height: 35.0),
          Padding(
            padding: const EdgeInsets.only(left:30.0,top:20),
            child: Text(
                'Escolha uma das horas disponíveis:',
                style: TextStyle(
                    letterSpacing: 2.0,
                    fontFamily: 'Nunito',
                    fontSize: 20.0,
                    color: Colors.black.withOpacity(0.6),
                    fontWeight: FontWeight.bold),
              ),
          ),
         
        
 SizedBox(height: 50.0),
          Container(
            height: 150.0,
            child: ListView(
              scrollDirection: Axis.horizontal,
              children:  List.generate(horas.length, (index) {
                          
                          return
                Padding(
                  padding: const EdgeInsets.all(20.0),
                  child: Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: getTime(horas[index].startTime,horas[index].endTime),
                  ),
                );
                            })
                
              
            ),
          ),
          SizedBox(height: 100.0),
          Padding(
            padding: const EdgeInsets.only(left: 15.0, right: 15.0),
            child: InkWell(
              onTap: () async {
                if(selectedTime!="" && _dateTime.toString()!="") {
                Reservation reservation= new Reservation.post(_dateTime.toString().split(' ')[0],selectedTime,this.user,this.service);
                await postData(reservation);
                 Navigator.push(context, MaterialPageRoute(builder: (context) => Home(user:user)));
                }

              },
              child: Container(
                height: 50.0,
                width: double.infinity,
                decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(7.0),
                    color: Colors.black),
                child: Center(
                  child: Text(
                    'Marcar',
                    style: TextStyle(
                        letterSpacing: 2.0,
                        fontFamily: 'FirSans',
                        fontSize: 17.0,
                        color: Colors.white,
                        fontWeight: FontWeight.bold),
                  ),
                ),
              ),
            ),
          )
        ],
      ),
    );
  }

  Color switchTimeColor(time) {
    if (time == selectedTime) {
      return Colors.black.withOpacity(0.8);
    } else {
      return Colors.grey.withOpacity(0.2);
    }
  }

  Color switchTimeContentColor(time) {
    if (time == selectedTime) {
      return Colors.white;
    } else {
      return Colors.black;
    }
  }

  selectTime(time,timeFinal) {
    setState(() {
      selectedTime = time;
      selectedTimeFinal= timeFinal;
    });
  }

  Widget getTime(time,timeFinal) {
  String inicialTime=time.toString();
  inicialTime=inicialTime.substring(0, inicialTime.length - 3);
  String finalTime=timeFinal.toString();
  finalTime=finalTime.substring(0, finalTime.length - 3);

    return AnimatedContainer(
      duration: Duration(milliseconds: 500),
      curve: Curves.easeIn,
      decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(10.0),
          color: switchTimeColor(time)),
      height: 50.0,
      width: 75.0,
      child: InkWell(
        onTap: () {
          selectTime(time,timeFinal);
        },
        child: Column(
                  children:[ Center(
            child: Text(
              inicialTime,
              style: TextStyle(
                  fontFamily: 'Nunito',
                  fontSize: 17.0,
                  fontWeight: FontWeight.bold,
                  color: switchTimeContentColor(time)),
            ),
          ),Padding(
            padding: const EdgeInsets.only(top:10.0),
            child: Center(
              child: Text(
                "-",
                style: TextStyle(
                    fontFamily: 'Nunito',
                    fontSize: 17.0,
                    fontWeight: FontWeight.bold,
                    color: switchTimeContentColor(timeFinal)),
              ),
            ),
          ),Padding(
            padding: const EdgeInsets.only(top:20.0),
            child: Center(
              child: Text(
                finalTime,
                style: TextStyle(
                    fontFamily: 'Nunito',
                    fontSize: 17.0,
                    fontWeight: FontWeight.bold,
                    color: switchTimeContentColor(timeFinal)),
              ),
            ),
          ),]
        ),
      ),
    );
  }

 

  Widget getService(String name, int price) {
    return Container(
      child: Row(
        children: <Widget>[
          Text(
            name,
            style: TextStyle(
                fontFamily: 'Nunito', fontSize: 17.0, color: Colors.black),
          ),
          SizedBox(width: 5.0),
          Text(
            '\$' + price.toString(),
            style: TextStyle(
                fontFamily: 'Nunito', fontSize: 17.0, color: Colors.grey),
          ),
          IconButton(
            icon: Icon(Icons.close),
            onPressed: () {},
          )
        ],
      ),
    );
  }

 

  
 

}


