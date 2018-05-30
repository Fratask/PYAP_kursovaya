package ru.natasha;

import java.io.FileNotFoundException;

import static ru.natasha.FileManager.readLine;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "C://Users/amira/Desktop/PYAP_Kursovaya/src/ru/natasha/base.txt";
        int countLines = 30;
        Location locations[] = new Location[10];
        int counter = 1;

        //Input locations from txt file to array
        for (int i = 0; i < locations.length; i++){
            double xCoord = Double.parseDouble(readLine(fileName, counter));
            double yCoord = Double.parseDouble(readLine(fileName, counter+1));
            double time = Double.parseDouble(readLine(fileName, counter+2));
            locations[i] = new Location(xCoord, yCoord, time);
            counter += 3;
        }

        Location startLoc = locations[0];
        Location endLoc = locations[2];

        // This is first triangle
        double side1 = locations[0].distanceTo(locations[1]);
        double side2 = locations[1].distanceTo(locations[2]);
        double side3 = locations[0].distanceTo(locations[2]);

        double distance = side1;
        double timeSum = locations[1].getTime()-locations[0].getTime();
        double countOfPoints = 2;
        double prevRadius = radius(side1, side2, side3);

        for (int i = 1; i < locations.length-1; i++){
            // a, b ,c - sides of next triangle, where c is hypotenuse
            double a = locations[i-1].distanceTo(locations[i]);
            double b = locations[i].distanceTo(locations[i+1]);
            double c = locations[i-1].distanceTo(locations[i+1]);

            double radius = radius(a, b, c);
            if ((c*c <= a*a + b*b) || (prevRadius!=radius)) {
                if (countOfPoints > 2){
                    double angle = Math.round(360*Math.asin(startLoc.distanceTo(locations[i])/(2*prevRadius))/3.1416);
                    if (distance > prevRadius*3.1416) angle = 360 - angle;
                    double angleSpeed = angle/timeSum;
                    System.out.println("Curve");
                    System.out.println("Distance: " + roundWithN(distance,4));
                    System.out.println("Time: " + roundWithN(timeSum,4));
                    System.out.println("Radius: " + roundWithN(prevRadius,4));
                    System.out.println("Angle per second: " + roundWithN(angleSpeed,4));
                }
                startLoc = locations[i];
                if (i+2 < locations.length) endLoc = locations[i+2];
                prevRadius = radius;
                countOfPoints = 2;
                distance = b;
                timeSum = locations[i+1].getTime()-locations[i].getTime();
            } else {
                prevRadius = radius;
                endLoc = locations[i+2];
                countOfPoints++;
                distance =roundWithN(distance+b,4);
                timeSum += locations[i+1].getTime()-locations[i].getTime();
            }
        }

    }

    public static double perimeter(double a, double b, double c){
        return a + b + c;
    }

    public static double square(double a, double b, double c){
        double perimeter = perimeter(a, b, c);
        return roundWithN(Math.sqrt((perimeter/2) * (perimeter/2 - a) * (perimeter/2 - b) * (perimeter/2 - c)),4);
    }

    public static double radius(double a, double b, double c){
        return roundWithN(a * b * c / (4 * square(a, b, c)),4);
    }



    public static double roundWithN(double a, int n){
        return Math.round(a*Math.pow(10,n))/Math.pow(10,n);
    }
}
