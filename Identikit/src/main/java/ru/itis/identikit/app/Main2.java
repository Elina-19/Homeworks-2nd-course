package ru.itis.identikit.app;

import javax.swing.*;
import java.awt.*;

public class Main2 {
    public static void main(String[] args) {
        JFrame fr = new JFrame("hello");
        Container container = fr.getContentPane();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton button = new JButton("add");
        button.setAlignmentX(JFrame.CENTER_ALIGNMENT);
        panel.add(button);

        JScrollPane pane = new JScrollPane(panel);

        button.addActionListener(e -> {
            JLabel label = new JLabel("hello");
            label.setFont(new Font("font", Font.ITALIC, 20));
            label.setAlignmentX(JFrame.CENTER_ALIGNMENT);
            panel.add(label);
            pane.revalidate();
        });

        container.add(pane);

        fr.setSize(1000, 300);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }
}
