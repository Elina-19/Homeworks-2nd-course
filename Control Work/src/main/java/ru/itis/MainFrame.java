package ru.itis;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class MainFrame{
    private JFrame mainFrame;
    private Container container;

    private MenuItem menuItem;
    private MenuBar menuBar;
    private StatusBar statusBar;
    private CenterField centerField;

    public MainFrame(){
        mainFrame = new JFrame("Main frame");
        container = mainFrame.getContentPane();
        mainFrame.setLayout(new BorderLayout());

        centerField = new CenterField();
        menuItem = new MenuItem(this);
        menuBar = new MenuBar(this, centerField);
        statusBar = new StatusBar();

        initialize();
    }

    public void initialize(){
        mainFrame.setBounds(0, 0, 1000, 700);
        mainFrame.setMinimumSize(new Dimension(500, 350));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        centerField.setMinimumSize(new Dimension(300, 300));

        container.add(menuItem.getMenuItem(), BorderLayout.WEST);
        container.add(menuBar.getMenuBar(), BorderLayout.EAST);
        container.add(statusBar.getStatus(), BorderLayout.SOUTH);
        container.add(centerField, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }
}
