package ru.itis.identikit.components;

import lombok.Data;
import ru.itis.identikit.components.elements.*;

import javax.swing.*;
import java.awt.*;

@Data
public class Canvas extends JPanel{
    private MainFrame mainFrame;
    private Head head;
    private Eyes eyes;
    private Nose nose;
    private Lips lips;
    private Hair hair;

    public Canvas(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        head = new Head();
        eyes = new Eyes();
        nose = new Nose();
        lips = new Lips();
        hair = new Hair();

        initialize();
    }

    public void setElement(String element, Image image){
        switch (element){
            case "head":
                setHead(image);
                break;
            case "eyes":
                setEyes(image);
                break;
            case "nose":
                setNose(image);
                break;
            case "lips":
                setLips(image);
                break;
            default: break;
        }
    }

    public void setHair(String hair){
        this.hair.setHair(hair);
        this.hair.validate();
        this.hair.repaint();
    }

    private void initialize(){
        this.setLayout(new OverlayLayout(this));

        this.add(eyes);
        this.add(head);
        this.add(nose);
        this.add(lips);
        this.add(hair);

        this.setPreferredSize(new Dimension(mainFrame.getWidth()*2/3, mainFrame.getHeight()*2/3));
    }

    private void setHead(Image image){
        head.setHead(image);
        head.validate();
        head.repaint();
    }

    private void setEyes(Image image){
        eyes.setEyes(image);
        eyes.repaint();
    }

    private void setNose(Image image){
        nose.setNose(image);
        nose.validate();
        nose.repaint();
    }

    private void setLips(Image image){
        lips.setLips(image);
        lips.validate();
        lips.repaint();
    }
}
