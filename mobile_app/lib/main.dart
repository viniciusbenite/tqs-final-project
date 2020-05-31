import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import './Login.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      localizationsDelegates: [
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate, // if it's a RTL language
      ],
      supportedLocales: [
        const Locale('pt', 'PT'), // include country code too
      ],
      title: 'TQS APP PÊLO',
      theme: ThemeData(
       
        primarySwatch: Colors.blue,
      ),
      home: Login(),
    );
  }
}
