package com.company;

import java.awt.*;

public class O_figure extends Figures{

    static void rotate() {

    }

    @Override
    public void drawfigure(Graphics g,int x,int y) {
        g.setColor(Color.red);
        g.fillRect(x,y,60,60);
    }
}
