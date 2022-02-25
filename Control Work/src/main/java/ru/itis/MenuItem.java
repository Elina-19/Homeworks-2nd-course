package ru.itis;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class MenuItem {
    private MainFrame mainFrame;

    private JPanel menuItem;
    private JPanel buttons;

    public MenuItem(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        menuItem = new JPanel();
        buttons = new JPanel(new GridLayout(3, 1, 5, 5));

        menuItem.add(buttons, BorderLayout.NORTH);

        initialize();
    }

    private void initialize(){
        menuItem.setPreferredSize(new Dimension(200, 400));

        createButtons();
    }

    private void createButtons(){
//        JButton file = new JButton("File");
//        JButton about = new JButton("About");
//        JButton exit = new JButton("Exit");

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem it = new JMenuItem("About");
        JMenuItem it2 = new JMenuItem("Exit");

        it.addActionListener(e -> {
            StatusBar status = mainFrame.getStatusBar();
            status.setAction(new JLabel("About"));

            JOptionPane.showMessageDialog(mainFrame.getMainFrame(), "Data has been saved.");

            status.setAction(new JLabel(""));
        });

        it2.addActionListener(e -> {
            StatusBar status = mainFrame.getStatusBar();
            status.setAction(new JLabel("Exit"));

            mainFrame.getMainFrame().dispose();

            status.setAction(new JLabel(""));
        });

        menuBar.add(menu);
        menu.add(it);
        menu.add(it2);

        buttons.add(menuBar);
    }
}
