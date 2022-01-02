package com.company;

import javax.swing.*;

public class MyFrame extends JFrame {
    MyPanel panel = new MyPanel();

    MyFrame(String s){
        setBounds(0,0,550,800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(s);
        setContentPane(panel);
    }
}