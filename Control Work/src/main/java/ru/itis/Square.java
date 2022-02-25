package ru.itis;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Square extends JComponent {
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g);
        g2.setColor(Color.red);

        double sin = Math.abs(Math.sin(30)), cos = Math.abs(Math.cos(30));
        int w = getWidth(), h = getHeight();
        int neww = (int)Math.floor(w*cos+h*sin), newh = (int) Math.floor(h * cos + w * sin);
        g2.translate((neww - w) / 2, (newh - h) / 2);
        g2.rotate(30, w / 2, h / 2);
        g2.fillRect(0, 0, w, h);
        g2.dispose();

//        int w = getWidth();
//        int h = getHeight();
//
//        double x = (h - w)/2.0;
//        double y = (w - h)/2.0;
        //AffineTransform at = AffineTransform.getTranslateInstance(x, y);

//        g2.rotate(Math.toRadians(30), w/2.0, h/2.0);
//        g2.fillRect(0, 0, getWidth(), getHeight());

        //try {
            //while (true) {
                //Thread.sleep(2000);
                //g2.rotate(50);
                g2.rotate(Math.toRadians(30), 3, 3);
            //}
//        }catch (InterruptedException e){
//            throw new IllegalStateException("Error in thread");
//        }
    }
}
