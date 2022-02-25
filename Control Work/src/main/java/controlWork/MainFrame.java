package controlWork;

import ru.itis.CenterField;
import ru.itis.MenuBar;
import ru.itis.StatusBar;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private JFrame mainFrame;
    private Container container;

    private InputField inputField;
    private ButtonsPanel buttonsPanel;

    public MainFrame(){
        mainFrame = new JFrame("Main frame");
        container = mainFrame.getContentPane();
        mainFrame.setLayout(new BorderLayout());

        inputField = new InputField();
        buttonsPanel = new ButtonsPanel(inputField);

        initialize();
    }

    public void initialize(){
        mainFrame.setBounds(0, 0, 600, 400);
        mainFrame.setMinimumSize(new Dimension(500, 350));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.setLayout(new FlowLayout());

        container.add(inputField.getInputField());
        container.add(buttonsPanel.getButtonsPanel());
        buttonsPanel.getButtonsPanel().setMinimumSize(new Dimension(300, 300));

        mainFrame.setVisible(true);
    }
}
