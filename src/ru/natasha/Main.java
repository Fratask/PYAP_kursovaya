package ru.natasha;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static ru.natasha.FileManager.readLine;

public class Main {

    private static  int countLines;
    private static HashMap idCurves = new HashMap();

    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "/home/amir/IdeaProjects/PYAP_Kursovaya/src/ru/natasha/bases/base2.txt";
        countLines = FileManager.countLines(fileName);
        Location locations[] = new Location[countLines];

        double earthsRadius = 6371;
        //Input locations from txt file to array
        for (int i = 0; i < countLines; i++){
            String strLine = readLine(fileName,i);
            int counter = 0;
            String phi = "";
            String tetta = "";
            String time = "";
            int k = 0;
            for (int j = 0; j < strLine.length(); j++){
                if (strLine.charAt(j)=='|'){
                    counter++;
                    String tmp = "";
                    for (k = k; k < j; k++)
                        tmp = tmp + strLine.charAt(k);
                    k++;
                    switch (counter){
                        case 1:
                            tetta = tmp;
                            break;
                        case 2:
                            phi = tmp;
                            break;
                        case 4:
                            time = tmp;
                            break;
                    }
                }
            }
            time = time.substring(11,19);
            tetta = tetta.replace(',','.');
            phi = phi.replace(',','.');
            double timeDouble = Double.parseDouble(time.substring(0,2))*3600+Double.parseDouble(time.substring(3,5))*60+Double.parseDouble(time.substring(6,8));
            locations[i] = new Location(earthsRadius,Math.toRadians(Double.parseDouble(tetta)),Math.toRadians(Double.parseDouble(phi)),timeDouble);

        }

        Location startLoc = locations[0];
        int idStartLoc = 0;
        int idEndLoc = 2;

        double distance = locations[0].distanceTo(locations[1]);
        double timeSum = 0;
        int countOfPoints = 1;
        double prevRadius = radius(locations[0].distanceTo(locations[1]), locations[1].distanceTo(locations[2]), locations[0].distanceTo(locations[2]));
        int deltaLoc = 0;

        int counter = 1;
        int i = 1;

        while (i < locations.length-1){
            // a, b ,c - sides of next triangle, where c is hypotenuse
            double a = locations[i-1].distanceTo(locations[i]);
            double b = locations[i].distanceTo(locations[i+1]);
            double c = locations[i-1].distanceTo(locations[i+1]);
            double alpha = 360*Math.asin(a/(2*prevRadius))/3.1416;
            double radius = radius(a, b, c);
            if ((alpha < 45) || (alpha > 170) || ((a+b<=c) || (a+c<=b) || (c+b<=a))) {
                if (countOfPoints > 2){
                    double angle = Math.acos((2*prevRadius*prevRadius-Math.pow(startLoc.distanceTo(locations[i]),2))/(2*prevRadius*prevRadius))*360/3.1416;
                    if (distance > prevRadius*3.1416) angle = 360-angle;
                    if (distance > 2*prevRadius*3.1416) angle = 360*distance/((int) (2*prevRadius*3.1416))+angle;
                    double angleSpeed = angle/timeSum;
                    System.out.println("------------------------------------------------------------------------------");
                    System.out.println("Curve " + counter++);
                    System.out.println("Count of points: " + countOfPoints);
                    System.out.println("Distance: " + distance);
                    System.out.println("Time: " + timeSum);
                    System.out.println("Radius: " + prevRadius);
                    System.out.println("Angle per second: " + angleSpeed);
                    System.out.println("StartLoc: " + idStartLoc);
                    System.out.println("EndLoc: " + idEndLoc);
                    System.out.println("------------------------------------------------------------------------------");
                    idCurves.put(idStartLoc,idEndLoc);
                }
                idEndLoc = i+1;
                timeSum = locations[i+1].getTime()-locations[i].getTime();
                i += idStartLoc;
                idStartLoc = i - idStartLoc;
                i = i - idStartLoc + 1;
                deltaLoc = 0;
                startLoc = locations[i];
                prevRadius = radius;
                countOfPoints = 2;
                distance = b;
            } else {
                if (alpha <= 45 || alpha >=150) deltaLoc++;
                idEndLoc++;
                prevRadius = radius;
                countOfPoints++;
                distance =distance+b;
                timeSum += locations[i+1].getTime()-locations[i].getTime();
            }
            i++;
        }

        Graph graph = new Graph(locations);

    }

    public static double perimeter(double a, double b, double c){
        return a + b + c;
    }

    public static double square(double a, double b, double c){
        double perimeter = perimeter(a, b, c);
        return roundWithN(Math.sqrt((perimeter/2) * (perimeter/2 - a) * (perimeter/2 - b) * (perimeter/2 - c)),10);
    }

    public static double radius(double a, double b, double c){
        return roundWithN(a * b * c / (4 * square(a, b, c)),10);
    }

    public static double roundWithN(double a, int n){
        return Math.round(a*Math.pow(10,n))/Math.pow(10,n);
    }

    public static int getCountLines() {
        return countLines;
    }

    public static HashMap getIdCurves() {
        return idCurves;
    }
}
