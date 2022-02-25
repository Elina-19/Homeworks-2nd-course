package ru.itis.identikit.components.elements;

import javax.swing.*;
import java.awt.*;

public class Head extends JPanel{
    private Image image;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public void setHead(Image image){
        this.image = image;
    }
}
