package ru.itis;

import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Data
public class StatusBar {
    private JPanel status;
    private JLabel text;
    private JLabel action;

    public StatusBar(){
        status = new JPanel();
        text = new JLabel();
        action = new JLabel();
        action.setVisible(false);

        initialize();
    }

    private void initialize(){
        Font font = new Font("Font", Font.ITALIC, 20);
        text.setFont(font);
        action.setFont(font);

        status.add(text);
        status.add(action);
        text.setText("Status: ");

        status.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                action.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                action.setVisible(false);
            }
        });
    }
}
