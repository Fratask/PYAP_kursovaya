package ru.natasha;

import static ru.natasha.Main.roundWithN;

public class Location {
    private double xCoord;
    private double yCoord;
    private double time;

    public Location(double xCoord, double yCoord, double time){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.time = time;
    }

    public double getxCoord() {
        return xCoord;
    }

    public void setxCoord(double xCoord) {
        this.xCoord = xCoord;
    }

    public double getyCoord() {
        return yCoord;
    }

    public void setyCoord(double yCoord) {
        this.yCoord = yCoord;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public boolean equalsCoord(Location loc){
        if (loc.getxCoord()==this.xCoord && loc.getyCoord()==this.getyCoord()){
            return true;
        } else
            return false;
    }

    public double distanceTo(Location loc){
        return roundWithN(Math.sqrt(Math.pow(xCoord-loc.getxCoord(),2)+Math.pow(yCoord-loc.getyCoord(),2)), 4);
    }
}
