package ru.itis.identikit.components.elements;

import javax.swing.*;
import java.awt.*;

public class Eyes extends JPanel {
    private Image image;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.drawImage(image, this.getWidth()/7, this.getHeight()/5, this.getWidth()*3/4, this.getHeight()/4, this);;
    }

    public void setEyes(Image image){
        this.image = image;
    }
}
