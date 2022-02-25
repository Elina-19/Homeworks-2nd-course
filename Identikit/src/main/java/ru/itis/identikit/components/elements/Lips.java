package ru.itis.identikit.components.elements;

import javax.swing.*;
import java.awt.*;

public class Lips extends JPanel {
    private Image image;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.drawImage(image, this.getWidth()*5/16, this.getHeight()*11/16, this.getWidth()/3, this.getHeight()/5, this);;
    }

    public void setLips(Image image){
        this.image = image;
    }
}
