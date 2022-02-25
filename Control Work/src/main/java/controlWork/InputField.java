package controlWork;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class InputField {
    private JPanel panel;
    private JLabel textField;
    private JTextField inputField;

    public InputField(){
        panel = new JPanel(new BorderLayout());
        textField = new JLabel();

        inputField = new JTextField(50);
        panel.add(textField, BorderLayout.NORTH);
        panel.add(inputField, BorderLayout.CENTER);
    }
}
