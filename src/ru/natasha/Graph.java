package ru.natasha;

import javax.swing.*;
import java.awt.*;

import static ru.natasha.Main.roundWithN;

public class Graph extends JFrame {
    Location[] locations = new Location[Main.getCountLines()];

    public Graph(Location[] locations){
        for (int i = 0; i < locations.length; i++){
            this.locations[i] = new Location(locations[i].getRadiusV(), locations[i].getTetta(), locations[i].getPhi(), locations[i].getTime());
        }
        setTitle("Graphic");
        setSize(1000, 1000);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void paint(Graphics g){
        // xCoordLine
        g.setColor(Color.orange);
        g.drawLine(950,50, 975, 31);
        g.drawLine(975,50, 950, 31);
        g.drawLine(0,31,1000,31);
        //
        // yCoordLine
        g.setColor(Color.red);
        g.drawLine(0,20,0,1000);
        g.drawLine(10,950,17,970);
        g.drawLine(15,1000,20,950);

        //Paint coordinates
        g.setColor(Color.GREEN);
        g.fillRect((int) (locations[0].getxCoord()),(int) (locations[0].getyCoord()),5,5);
        int i = 1;
        while (i < locations.length){
            if (Main.getIdCurves().containsKey(i)){
                for (int j = i; j <= (int) Main.getIdCurves().get(i); j++) {
                    g.setColor(Color.red);
                    int x1 = (int) ((locations[j-1].getxCoord()-roundWithN(locations[j-1].getxCoord(),0)+1)*3000)-2500;
                    int y1 = (int) ((locations[j-1].getyCoord()-roundWithN(locations[j-1].getyCoord(),0)+1)*3000)-2800;
                    int x = (int) ((locations[j].getxCoord()-roundWithN(locations[j].getxCoord(),0)+1)*3000)-2500;
                    int y = (int) ((locations[j].getyCoord()-roundWithN(locations[j].getyCoord(),0)+1)*3000)-2800;
                    g.drawLine(x, y, x1, y1);
                    g.setColor(Color.GREEN);
                    g.fillRect(x, y, 3, 3);
                    String numLoc = Integer.toString(j);
                    g.setColor(Color.black);
                    g.drawString(numLoc,x+5,y+5);
                }
                i = (int) Main.getIdCurves().get(i);
            } else {
                g.setColor(Color.GREEN);
                int x1 = (int) ((locations[i - 1].getxCoord() - roundWithN(locations[i - 1].getxCoord(), 0) + 1) * 3000) - 2500;
                int y1 = (int) ((locations[i - 1].getyCoord() - roundWithN(locations[i - 1].getyCoord(), 0) + 1) * 3000) - 2800;
                int x = (int) ((locations[i].getxCoord() - roundWithN(locations[i].getxCoord(), 0) + 1) * 3000) - 2500;
                int y = (int) ((locations[i].getyCoord() - roundWithN(locations[i].getyCoord(), 0) + 1) * 3000) - 2800;
                g.fillRect(x, y, 5, 5);
                g.setColor(Color.darkGray);
                g.drawLine(x, y, x1, y1);
                String numLoc = Integer.toString(i);
                g.setColor(Color.black);
                g.drawString(numLoc,x+5,y+5);

            }
            i++;
        }
    }
}
