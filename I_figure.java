package com.company;

import java.awt.*;

public class I_figure extends Figures {



    static void rotate() {
        boolean flag = true;
            try {
                int i = loc[0];
                while ((i < loc[0] + 4) && (flag)) {
                    int j = loc[1];
                    while ((j < loc[1] + 4) && (flag)) {
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
                for (int i = loc[0]; i < loc[0] + 4; i++) {
                    for (int j = loc[1]; j < loc[1] + 4; j++) {
                        switch (pos){
                            case 0: {
                                if (j == loc[1]) {
                                    Logic.matrix[i][j] = 2;
                                } else
                                    Logic.matrix[i][j] = 0;
                                break;
                            }
                            case 1:{
                                if (i == loc[0]) {
                                    Logic.matrix[i][j] = 2;
                                } else
                                    Logic.matrix[i][j] = 0;
                                break;
                            }
                        }
                    }
                }
                if (pos == 1){
                    pos = 0;
                }else pos = 1;
            }

        }




    @Override
    public void drawfigure(Graphics g, int x, int y) {
        g.setColor(Color.green);
        switch (pos) {
            case 0:
            g.fillRect(x + 1, y + 1, 119, 29);
            break;
            case 1:
            g.fillRect(x+1,y+1,29,119);
        }
    }
}
