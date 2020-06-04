import 'package:mobile_app/models/User.dart';

class Saloon{

  int id;
  String name;
  String postalCode;
  String city;
  String country;
  String status;
  String type;
  String contact;
  String description;
  String address;
  User owner;
  String image;


  Saloon();

  Saloon.fromMap(Map<String, dynamic> data){
    id=data['id'];
    name=data['name'];
    postalCode=data['postalCode'];
    city=data['city'];
    country=data['country'];
    status=data['status'];
    type=data['type'];
    contact=data['contact'];
    description=data['description'];
    address=data['address'];
    owner=User.fromMap(data['owner']);
    image=data['image'];
  }

  Map<String, dynamic> toMap() {
    return {
      'id':id,
     'name':name,
     'postalCode':postalCode,
     'city':city,
     'country':country,
     'status':status,
     'type':type,
     'contact':contact,
     'description':description,
     'address':address,
     'owner':owner.toMap(),
     'image':image,    
    };
  }

}
