package ru.itis;

import lombok.Data;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

@Data
public class MenuBar {
    private MainFrame mainFrame;

    private JPanel menuBar;
    private JPanel buttons;
    private CenterField centerField;

    public MenuBar(MainFrame mainFrame, CenterField centerField){
        this.mainFrame = mainFrame;

        menuBar = new JPanel();
        buttons = new JPanel(new GridLayout(3, 1, 5, 5));
        this.centerField = centerField;

        menuBar.add(buttons);

        initialize();
    }

    private void initialize(){
        menuBar.setPreferredSize(new Dimension(200, 400));

        createButtons();
    }

    private void createButtons(){
        JButton red = new JButton("Red");
        JButton photo = new JButton("Photo");
        JButton square = new JButton("Square");

        red.addActionListener(e -> {
            StatusBar status = mainFrame.getStatusBar();
            status.getAction().setText("Red");

            centerField.clean();
            centerField.setBackground(Color.red);
        });

        photo.addActionListener(event -> {
            StatusBar status = mainFrame.getStatusBar();
            status.getAction().setText("Photo");

            SwingUtilities.invokeLater(() -> {
                try {
                    BufferedImage image = ImageIO.read(new URL("http://almode.ru/uploads/posts/2021-03/1616402749_37-p-nikolas-keidzh-38.jpg"));
                    JLabel label = new JLabel(new ImageIcon(image));

                    centerField.clean();
                    centerField.getCenterField().add(label);
                } catch (IOException e) {
                    throw new IllegalArgumentException("Incorrect path to image");
                }
            });
        });

        square.addActionListener(e -> {
            StatusBar status = mainFrame.getStatusBar();
            status.getAction().setText("Square");

            Square shape = new Square();
            SwingUtilities.invokeLater(()->{
                shape.setPreferredSize(new Dimension(100, 100));
                centerField.add(shape);
            });

            shape.repaint();
        });

        buttons.add(red);
        buttons.add(photo);
        buttons.add(square);
    }
}
