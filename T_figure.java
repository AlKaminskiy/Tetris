package com.company;

import java.awt.*;

public class T_figure extends Figures {

    static int pos;

    static void rotate() {
        boolean flag = true;
        try {
            int i = loc[0];
            while ((i < loc[0] + 3) && (flag)) {
                int j = loc[1];
                while ((j < loc[1] + 3) && (flag)) {
                    if (Logic.matrix[i][j] == 1) {
                        flag = false;
                    }
                    j++;
                }
                i++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            flag = false;
        }
        if (flag) {
            switch (pos){
                case 0: {
                    changepos(2,0,0,2,2,0,2,0,0);
                    pos = 1;
                    break;
                }
                case 1:{
                    changepos(2,2,2,0,2,0,0,0,0);
                    pos = 2;
                    break;
                }
                case 2:{
                    changepos(0,0,2,0,2,2,0,0,2);
                    pos = 3;
                    break;
                }
                case 3:{
                    changepos(0,2,0,2,2,2,0,0,0);
                    pos = 0;
                    break;
                }
            }
        }

    }

    private static void changepos(int... val) {
        int k = 0;
        for (int i = loc[0]; i < loc[0] + 3; i++) {
            for (int j = loc[1]; j < loc[1] + 3; j++) {
                Logic.matrix[i][j] = val[k];
                k++;
            }
        }

    }


    @Override
    public void drawfigure(Graphics g, int x, int y) {
        g.setColor(Color.orange);
        switch (pos) {
            case 0: {
                g.fillRect(x+30, y, 30, 30);
                g.fillRect(x, y + 30, 90, 30);
                break;
            }
            case 1: {
                g.fillRect(x, y, 30, 90);
                g.fillRect(x+30, y + 30, 30, 30);
                break;
            }
            case 2: {
                g.fillRect(x, y, 90, 30);
                g.fillRect(x+30, y + 30, 30, 30);
                break;
            }
            case 3: {
                g.fillRect(x+30, y+30, 30, 30);
                g.fillRect(x+60, y, 30, 90);
                break;
            }

        }
    }
}
