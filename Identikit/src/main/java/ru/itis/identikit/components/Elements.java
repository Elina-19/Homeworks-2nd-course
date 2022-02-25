package ru.itis.identikit.components;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Elements extends JPanel{
    private static final Path imageSource = Paths.get("src/main/java/ru/itis/identikit/images");
    private static final String DEFAULT_PATH = "head";

    private Canvas canvas;

    public Elements(Canvas canvas){
        this.canvas = canvas;
        initialize();
    }

    private void initialize(){
        GridLayout layout = new GridLayout(3, 5, 5, 5);

        this.setLayout(layout);
        setImages(DEFAULT_PATH);
    }

    public void setImages(String path){
        clean();
        List<File> images = Arrays.asList(imageSource.resolve(path).toFile().listFiles());

        for (File file: images){
            JButton button = new JButton();
            button.setBackground(Color.white);
            ImageIcon image = new ImageIcon(file.toString());
            button.setIcon(image);

            button.addActionListener(e -> {
                canvas.setElement(path, image.getImage());
            });

            this.add(button);
        }
        this.validate();
        this.repaint();
    }

    public void setHair(){
        clean();

        JButton button = new JButton("-");
        button.setBackground(Color.white);

        JButton button2 = new JButton("...");
        button2.setBackground(Color.white);

        JButton button3 = new JButton("-.-");
        button3.setBackground(Color.white);

        button.addActionListener(e -> {
            canvas.setHair(button.getText());
        });
        button2.addActionListener(e -> {
            canvas.setHair(button2.getText());
        });
        button3.addActionListener(e -> {
            canvas.setHair(button3.getText());
        });

        this.add(button);
        this.add(button2);
        this.add(button3);

        this.validate();
        this.repaint();
    }

    private void clean(){
        Component[] components = this.getComponents();

        for (int i = 0; i < components.length; i++) {
            this.remove(components[i]);
        }

        this.repaint();
    }
}
