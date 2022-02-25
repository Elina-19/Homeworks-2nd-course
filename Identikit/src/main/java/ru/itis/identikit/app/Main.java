package ru.itis.identikit.app;

public class Main {
    public static void main(String[] args) {
//        int a = 256;
//
//        byte f = (byte)(a>>8);
//        byte s = (byte)a;
//
//        int b = (int)(f<<8 | s);
//
//        System.out.println(b);

//        MainFrame mainFrame = new MainFrame();
        char[] mess = "Привет, мир!".toCharArray();
        byte[] message = new byte[mess.length*2];
        for (int i = 0; i < mess.length; i++){
            message[i*2] = (byte)(mess[i]>>8);
            message[i*2+1] = (byte)mess[i];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length; i = i + 2){
            char a = (char) (message[i]<<8 | message[i+1]);
            sb.append(a);
        }

        System.out.println(sb.toString());

        char a = 'п';
        byte f = (byte)(a>>8);
        byte s = (byte)a;

        char b = (char) (f<<8 | s);
        System.out.println(b);
//        ImageIcon ic = new ImageIcon("src/main/java/ru/itis/identikit/app/1.jpg");
//        JFrame mainFrame = new JFrame("Test");
//        JPanel panel = new JPanel();
//        JLabel l = new JLabel("first");
//        JLabel l2 = new JLabel("second");
//        JLabel l3 = new JLabel("third");
//        l.setIcon(ic);
//        l2.setIcon(ic);
//        l3.setIcon(ic);
//        l.setVerticalTextPosition(JLabel.TOP);
//        l2.setVerticalTextPosition(JLabel.TOP);
//        l3.setVerticalTextPosition(JLabel.TOP);
//        panel.setBounds(3,3, 700, 100);
//        panel.setLayout(new GridLayout(1, 3, 5, 0));
//        panel.add(l);
//        panel.add(l2);
//        panel.add(l3);
//
//        JButton but = new JButton("Hey");
//        but.setText("hey");
//        panel.add(but);
//        mainFrame.getContentPane().add(but);
//        mainFrame.getContentPane().add(panel);
//        JLabel label = new JLabel("Hello world!");
//        label.setIcon(new ImageIcon("src/main/java/ru/itis/identikit/app/1.jpg"));
//        mainFrame.getContentPane().add(label);
//        label.setVerticalTextPosition(JLabel.BOTTOM);
//        mainFrame.setBounds(50, 50, 300, 600);
//        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        mainFrame.setVisible(true);
    }
}
