package controlWork;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class ButtonsPanel {
    private JPanel buttonsPanel;
    private InputField inputField;

    public ButtonsPanel(InputField inputField){
        buttonsPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        this.inputField = inputField;
        initialize();
    }

    private void initialize(){
        for (int i = 0; i < 10; i++){
            JButton button = new JButton(Integer.valueOf(i).toString());
            addActionListener(button);

            buttonsPanel.add(button);
        }

        JButton sum = new JButton("+");
        addActionListener(sum);
        JButton min = new JButton("-");
        addActionListener(min);
        JButton result = new JButton("=");
        result.addActionListener(e -> {

        });

        buttonsPanel.add(sum);
        buttonsPanel.add(min);
        buttonsPanel.add(result);
    }

    private void addActionListener(JButton button){
        button.addActionListener(event -> {
            JLabel label = inputField.getTextField();
            JTextField text = inputField.getInputField();

            label.setText(label.getText() + button.getText());
            text.setText(text.getText() + button.getText());
        });
    }
}
