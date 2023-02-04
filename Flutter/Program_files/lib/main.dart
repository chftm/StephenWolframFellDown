import 'package:flutter/material.dart';
import 'dart:math' as math;

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: HomePage(),
      title: 'Wolfram',
    );
  }
}

class HomePage extends StatelessWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
          title: Text(
            'Стивен Вольфрам готов к падению!'.toUpperCase(),
            style: const TextStyle(fontSize: 14),
          ),
          backgroundColor: Colors.brown),
      body: const Center(
        child: Button(),
      )
    );
  }
}

class Button extends StatefulWidget {
  const Button({Key? key}) : super(key: key);

  @override
  State<Button> createState() => _ButtonState();
}

class _ButtonState extends State<Button> {
  double rotation = 0;
  double count = 1;
  double op = 0;
  var text = 'Уронить';
  void onPressed() {
    setState(() {
      if(count == 1){
        rotation = math.pi/2;
        op = 1;
        text = 'Поднять';
      }
      else{
        rotation = 0;
        op = 0;
        text = 'Уронить';
      }
      count*=-1;
    });
  }

  @override
  Widget build(BuildContext context) {

    var width = MediaQuery.of(context).size.width;
    var height = MediaQuery.of(context).size.height;
    return Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: [
        Center(child: Text(
            'Стивен Вольфрам упал!',
            style: TextStyle(fontSize: width/15, color: Colors.brown.withOpacity(op)),
        ),),
        Center(child: AnimatedRotation(turns: rotation/(2*math.pi), duration: const Duration(milliseconds: 250), child:
          Container(width: height/3,height: height/3,decoration: BoxDecoration(border: Border.all(width: 5, color: Colors.brown)),
            child: Image.asset('Images/178185_320.jpg',
              width: width/1.5,
              height: height/1.5,
              fit: BoxFit.cover,
              ),
          )
        ),),
        Center(
          child: SizedBox(width: width/2, child: TextButton(
            onPressed: () => {onPressed()},
            style: const ButtonStyle(backgroundColor: MaterialStatePropertyAll<Color>(Colors.brown), foregroundColor: MaterialStatePropertyAll<Color>(Colors.white)),
            child: Text(text),
          ))
      ),
    ]
);
}}
