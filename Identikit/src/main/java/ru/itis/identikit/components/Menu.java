package ru.itis.identikit.components;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel{
    private MenuItem menuitem;
    private Elements elements;

    public Menu(MainFrame mainFrame){
        this.setMinimumSize(new Dimension(mainFrame.getWidth()/3, mainFrame.getHeight()/3));
        this.setBackground(Color.pink);
        elements = new Elements(mainFrame.getCanvas());
        menuitem = new MenuItem(elements);

        initialize();
    }

    private void initialize(){
        this.setLayout(new BorderLayout());

        this.add(menuitem, BorderLayout.NORTH);
        this.add(elements, BorderLayout.CENTER);
    }
}
