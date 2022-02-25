package ru.itis.identikit.components;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class MainFrame extends JFrame{
    private Container container;

    private Canvas canvas;
    private Menu menu;

    private int width = 1300;
    private int height = 1000;

    public MainFrame(){
        this.setName("Identikit");
        container = this.getContentPane();

        canvas = new Canvas(this);
        menu = new Menu(this);

        initialize();
    }

    private void initialize(){
        this.setSize(1300, 1000);
        this.setMinimumSize(new Dimension(1300, 1000));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

        container.add(canvas);
        container.add(menu);

        this.setVisible(true);
    }
}
