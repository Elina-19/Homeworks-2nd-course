package ru.itis.identikit.app;

import ru.itis.identikit.components.MainFrame;

public class App{
    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    private void start(){
        MainFrame mainFrame = new MainFrame();
    }
}
