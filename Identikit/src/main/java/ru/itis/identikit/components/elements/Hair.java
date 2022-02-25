package ru.itis.identikit.components.elements;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Hair extends JPanel{
    private String hair;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        if (hair != null) {
            System.out.println(hair);
            switch (hair) {
                case "-":
                    for (int i = 0; i < 10; i++)
                        g2.drawLine(this.getWidth() * i / 10, 0, this.getWidth() * i / 10, this.getHeight());
                    break;
                case "...":
                    //
                case "-.-":
                    for (int i = 0; i < 10; i++)
                        g2.draw(new Line2D.Float( 132.50f, 21.50f, 132.50f, 459.50f));
                    break;
                default:
                    break;
            }
        }
    }

    public void setHair(String hair){
        this.hair = hair;
    }
}
