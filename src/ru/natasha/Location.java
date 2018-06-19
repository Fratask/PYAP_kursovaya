package ru.natasha;

import static ru.natasha.Main.roundWithN;

public class Location {
    private double radiusV;

    //phi & tetta in radians
    private double phi;
    private double tetta;

    private double xCoord;
    private double yCoord;
    private double time;

    public Location(double radiusV, double tetta, double phi, double time){
        this.radiusV = radiusV;

        this.phi = phi;
        this.tetta = tetta;

        this.xCoord =  roundWithN(radiusV * phi,10);
        this.yCoord =  roundWithN(radiusV * (3.1416/2-tetta),10);

        this.time = time;
    }

    public double getxCoord() {
        return xCoord;
    }

    public double getyCoord() {
        return yCoord;
    }

    public double getRadiusV() {
        return radiusV;
    }

    public double getPhi() {
        return phi;
    }

    public double getTetta() {
        return tetta;
    }

    public double getTime() {
        return time;
    }

    public boolean equalsCoord(Location loc){
        if (loc.getxCoord()==this.xCoord && loc.getyCoord()==this.getyCoord()){
            return true;
        } else
            return false;
    }

    public double distanceTo(Location loc){
        return roundWithN(Math.sqrt(Math.pow(loc.getxCoord()-xCoord,2)+Math.pow(loc.getyCoord()-yCoord,2)),10);
    }
}
