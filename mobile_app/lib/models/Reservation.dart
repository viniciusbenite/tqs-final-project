import 'package:mobile_app/models/Service.dart';
import 'package:mobile_app/models/User.dart';


class Reservation{
  String date;
  String time;
  int id;
  User user;
  Service service;



  Reservation();

  Reservation.post(String date, String time, User user, Service service){
    this.date=date;
    this.time=time;
    this.user=user;
    this.service=service;
  }

  Reservation.fromMap(Map<String, dynamic> data){
    id=data['id'];
    date=data['date'];
    time=data['time'];
    user=User.fromMap(data['users']);
    service=Service.fromMap(data['services']);
  
  }

}
