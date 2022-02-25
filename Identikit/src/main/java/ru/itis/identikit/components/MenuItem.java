package ru.itis.identikit.components;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class MenuItem extends JMenuBar{
    private static final Path imageSource = Paths.get("src/main/java/ru/itis/identikit/images");

    private Elements elements;

    public MenuItem(Elements elements){
        this.elements = elements;
        initialize();
    }

    private void initialize(){
        List<File> items = Arrays.asList(imageSource.toFile().listFiles());

        JMenu menuItem = new JMenu("menu");
        for (File file: items){
            JMenuItem item = new JMenuItem(file.getName());

            item.addActionListener(e -> {
                elements.setImages(file.getName());
            });

            menuItem.add(item);
        }

        JMenuItem item = new JMenuItem("hair");
        item.addActionListener(e -> {
            elements.setHair();
        });
        menuItem.add(item);

        this.add(menuItem);
    }
}
