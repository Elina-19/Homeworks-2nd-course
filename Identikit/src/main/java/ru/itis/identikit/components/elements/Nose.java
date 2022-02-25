package ru.itis.identikit.components.elements;

import javax.swing.*;
import java.awt.*;

public class Nose extends JPanel {
    private Image image;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.drawImage(image, this.getWidth()/3, this.getHeight()/3, this.getWidth()/4, this.getHeight()/3, this);;
    }

    public void setNose(Image image){
        this.image = image;
    }
}
