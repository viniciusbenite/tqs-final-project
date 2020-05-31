import 'package:flutter_driver/flutter_driver.dart';
import 'package:test/test.dart';


Future<void> delay([int milliseconds = 250]) async {
  await Future<void>.delayed(Duration(milliseconds: milliseconds));
}


void main() {
  group('TQS APP PÊLO', () {
   
    final email = find.byValueKey('email_input');
    final error = find.byValueKey('incorrect_credentials');
    final saloon = find.byValueKey('saloon_container');
    final reservationsNumber = find.byValueKey('reservations_number');
    final reservationsButton = find.byValueKey('reservations_button');
    final reservationsText = find.byValueKey('reservations_text');
    final password = find.byValueKey('password_input');
    final welcome = find.byValueKey('welcome_text');
    final entrar = find.byValueKey('entrar_button');
    final backButton = find.byValueKey("back_button");
    final servicesText = find.byValueKey('services_text');
    final serviceContainer = find.byValueKey("service_container");
    final date = find.byValueKey("date_picker");
    final time = find.byValueKey("time_picker");
    final reserveButton = find.byValueKey("reserve_button");
    final scroll = find.byValueKey("Scroll");
    final proximoDia = find.byValueKey("proximo_dia");

    FlutterDriver driver;

    setUpAll(() async {
      driver = await FlutterDriver.connect();
    });

   
    tearDownAll(() async {
      if (driver != null) {
        driver.close();
      }
    });

    test('Teste 1 : Efetuar login com credenciais incorretas', () async {
      await driver.tap(email);
      await driver.enterText("alina@gmail.com");
      await driver.tap(password);
      await delay(1300);
      await driver.enterText("olaaaaa");
      await driver.tap(entrar);
      await delay(1300);
      expect(await driver.getText(error), "E-mail ou password incorretos!");
    });

    test('Teste 2 : Efetuar login com credenciais corretas', () async {
      await driver.tap(email);
      await driver.enterText("alina@gmail.com");
      await driver.tap(password);
      await delay(1300);
      await driver.enterText("ola");
      await driver.tap(entrar);
      await delay(1300);
      expect(await driver.getText(welcome), "\n\nEncontre e reserve os serviços que procura");
    });

    


    test('Teste 3 : Verificar que ainda não há reservas', () async {
      await driver.scroll(scroll,0,-300,Duration(seconds: 2));
      await driver.waitFor(reservationsButton);
      await delay(1300);
      await driver.tap(reservationsButton);
      await driver.waitFor(reservationsText);
      expect(await driver.getText(reservationsText),"As suas reservas");
      await delay(1300);
      await driver.waitFor(reservationsNumber);
      expect(await driver.getText(reservationsNumber),"0");
    });


    test('Teste 4 : Fazer uma reserva e verificar nas minhas reservas', () async {
      await driver.waitFor(backButton);
      await delay(1300);
      await driver.tap(backButton);
      await delay(1300);
      await driver.waitFor(saloon);
      await driver.tap(saloon);
      await delay(1300);
      await driver.waitFor(servicesText);
      expect(await driver.getText(servicesText),"Serviços disponíveis");
      await driver.waitFor(serviceContainer);
      await driver.tap(serviceContainer);
      await delay(1300);
      await driver.waitFor(date);
      await driver.tap(date);
      await delay(1300);
      await driver.tap(find.text('31'));
      await delay(1300);
      await driver.tap(find.text('OK'));
      await delay(1300);
      await driver.waitFor(time);
      await driver.tap(time);
      await driver.waitFor(reserveButton);
      await driver.tap(reserveButton);
      await delay(1300);
      await driver.waitFor(reservationsButton);
      await driver.tap(reservationsButton);
      await delay(1300);
      await driver.waitFor(reservationsText);
      await driver.waitFor(reservationsNumber);
      await delay(1300);
      expect(await driver.getText(reservationsText),"As suas reservas");
      expect(await driver.getText(reservationsNumber),"1");
      await delay(1300);
      expect(await driver.getText(proximoDia),"31");

      
    });

   
  });
 
}
