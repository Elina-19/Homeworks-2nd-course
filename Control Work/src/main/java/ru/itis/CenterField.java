package ru.itis;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class CenterField extends JPanel{
    private JPanel centerField;

    public CenterField(){
        centerField = new JPanel();
    }

    public void clean(){
        this.setBackground(Color.white);
        centerField = new JPanel();
    }
}
