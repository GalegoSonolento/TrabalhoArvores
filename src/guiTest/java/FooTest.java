import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FooTest implements ActionListener{

    private int count = 0;
    private JLabel label;
    private JFrame frame;
    private JButton button;
    private JPanel panel;

    public FooTest(){

        frame = new JFrame();

        button = new JButton("Click me");
        button.addActionListener(this);


        label = new JLabel("NUmber of clicks: 0");


        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Our GUI");
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {

        new FooTest();

//        JFrame f = new JFrame("A JFrame");
//        f.setSize(250, 250);
//        f.setLocation(300,200);
//        final JTextArea textArea = new JTextArea(10, 40);
//        f.getContentPane().add(BorderLayout.CENTER, textArea);
//        final JButton button = new JButton("Click Me");
//        f.getContentPane().add(BorderLayout.SOUTH, button);
//        button.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                textArea.append("Button was clicked\n");
//
//            }
//        });
//
//        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        count ++;
        label.setText("NUmber of clicks: " + count);
    }
}