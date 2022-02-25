package ru.itis.identikit.app;

import javax.swing.*;
import java.awt.*;

public class Calc {
    public static void main(String[] args) {
        JFrame frame = new JFrame("calculator");
        Container container = frame.getContentPane();

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new OverlayLayout(panel));
        panel.setMinimumSize(new Dimension(300, 300));

        JLabel label1 = new JLabel("Hello");
        label1.setFont(label1.getFont().deriveFont(30f));
        JLabel label2 = new JLabel("World");
        label2.setFont(label2.getFont().deriveFont(40f));
        JLabel label3 = new JLabel("Что");
        label3.setFont(label2.getFont().deriveFont(50f));

        panel.add(label1);
        panel.add(label2);
        panel.add(label3);

        frame.getContentPane().add(panel);

//        JMenu menuItem = new JMenu("test");
//        menuItem.setMinimumSize(new Dimension(100, 100));
//        JMenuItem it1 = new JMenuItem("t1");
//        JMenuItem it2 = new JMenuItem("t2");
//        menuItem.add(it1);
//        menuItem.add(it2);
//        container.add(menuItem);
//
//        JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new FlowLayout());
//
//        JPanel resultPanel = new JPanel(new BorderLayout());
//        JLabel label = new JLabel();
//        resultPanel.add(label, BorderLayout.NORTH);
//        JTextField field = new JTextField(50);
//        resultPanel.add(field, BorderLayout.CENTER);
//
//        mainPanel.add(resultPanel);
//
//        JPanel buttons = new JPanel();
//        buttons.setLayout(new GridLayout(2, 2, 5, 5));
//        JButton button = new JButton("+");
//        JButton button2 = new JButton("-");
//        JButton button3 = new JButton("=");
//        button.addActionListener(e -> {
//            label.setText(field.getText());
//        });
//        button3.addActionListener(e -> {
//            Integer res = Integer.valueOf(label.getText()) + Integer.valueOf(field.getText());
//            field.setText(res.toString());
//            label.setText("");
//        });
//        buttons.add(button);
//        buttons.add(button2);
//        buttons.add(button3);
//
//        mainPanel.add(buttons, BorderLayout.SOUTH);
//        container.add(mainPanel);

        frame.setVisible(true);
    }
}
