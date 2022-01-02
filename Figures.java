package com.company;

import java.awt.*;

public abstract class Figures {
    static int pos = 0;//как повернута
    static int loc[] = new int[2];//где находится
    abstract void drawfigure(Graphics g, int x, int y);

}
