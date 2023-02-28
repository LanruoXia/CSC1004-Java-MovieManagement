package org.example;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AwtMain extends Frame implements ActionListener {
    TextField l;
    AwtMain(){
        Button bt = new Button("Click!");
        bt.setBounds(100, 100, 100, 100);
        l = new TextField();
        l.setBounds(100, 200, 100, 50);
        Label lb = new Label("xxx");
        lb.setBounds(100, 300, 100, 50);
        add(lb);
        add(l);
        bt.addActionListener(this);
        add(bt);
        setLayout(null);
        setVisible(true);
        setSize(500,500);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Check!");
        l.setText("Hi!");

    }
    public static void main(String[] args){
        new AwtMain();
    }



}
