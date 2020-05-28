import 'package:mobile_app/models/Service.dart';


class Schedule{
  String startTime;
  String endTime;
  Service service;
  int id;



  Schedule();

  Schedule.fromMap(Map<String, dynamic> data){
    id=data['id'];
    startTime=data['startTime'];
    endTime=data['endTime'];
    service=Service.fromMap(data['service']);
  
  }

}
