import 'package:mobile_app/models/Saloon.dart';


class Service{
  String name;
  double price;
  String description;
  String available;
  Saloon saloon;
  int id;



  Service();

  Service.fromMap(Map<String, dynamic> data){
    id=data['id'];
    name=data['name'];
    price=data['price'];
    description=data['description'];
    available=data['available'];
    saloon=Saloon.fromMap(data['saloon']);
  
  }

  Map<String, dynamic> toMap() {
    return {
      'id':id,
     'name':name,
    'price':price,
    'description':description,
    'available':available,
    'saloon':saloon.toMap()
    };
  }

}
