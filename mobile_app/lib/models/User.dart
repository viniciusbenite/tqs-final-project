class User{
  String name;
  String email;
  String password;
  String type;
  int id;

  User();

  User.fromMap(Map<String, dynamic> data){
    id=data['id'];
    name=data['name'];
    email=data['email'];
    password=data['password'];
    type=data['type'];
  }

  Map<String, dynamic> toMap() {
    return {
      'id':id,
     'name':name,
     'email':email,
     'password':password,
     'type':type,

      
    };
  }

}

