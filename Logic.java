package com.company;

import javax.swing.*;
import java.awt.*;

public class Logic {
    static boolean isdead = false;
    static int[][] matrix = new int[26][10];
    static int fig = -1;// 0 - L; 1 - I; 2 - O; 3 - S; 4 - T
    static void update_matrix() {
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 10; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    static int spawnfigure() {
        fig = (int) (Math.random() * 5);
      //  fig = 4;
        MyPanel.spfig = fig;

        switch (fig) {
            case 0: {
                if (matrix[4][4] == 0 && matrix[5][4] == 0 && matrix[6][4] == 0 && matrix[6][5] == 0) {
                    matrix[4][4] = 2;//4 - начало
                    matrix[5][4] = 2;
                    matrix[6][4] = 2;
                    matrix[6][5] = 2;
                    L_figure.loc[0] = 4;
                    L_figure.loc[1] = 4;
                    L_figure.pos = 0;
                }else isdead = true;
                break;
            }
            case 1: {
                if (matrix[4][4] == 0 && matrix[4][5] == 0 && matrix[4][6] == 0 && matrix[4][7] == 0) {
                    matrix[4][4] = 2;//4 - начало
                    matrix[4][5] = 2;
                    matrix[4][6] = 2;
                    matrix[4][7] = 2;
                    I_figure.loc[0] = 4;
                    I_figure.loc[1] = 4;
                    I_figure.pos = 0;
                }else isdead = true;
                break;
            }
            case 2: {
                if (matrix[4][4] == 0 && matrix[4][5] == 0 && matrix[5][4] == 0 && matrix[5][5] == 0) {
                    matrix[4][4] = 2;//4 - начало
                    matrix[4][5] = 2;
                    matrix[5][4] = 2;
                    matrix[5][5] = 2;
                }else isdead = true;
                break;
            }
            case 3: {
                if (matrix[4][4] == 0 && matrix[5][4] == 0 && matrix[5][5] == 0 && matrix[6][5] == 0) {
                    matrix[4][4] = 2;//4 - начало
                    matrix[5][4] = 2;
                    matrix[5][5] = 2;
                    matrix[6][5] = 2;
                    S_figure.loc[0] = 4;
                    S_figure.loc[1] = 4;
                    S_figure.pos = 0;
                }else isdead = true;
                break;
            }
            case 4: {
                if (matrix[4][5] == 0 && matrix[5][4] == 0 && matrix[5][5] == 0 && matrix[5][6] == 0) {
                    matrix[4][5] = 2;//4 - начало
                    matrix[5][4] = 2;
                    matrix[5][5] = 2;
                    matrix[5][6] = 2;
                    T_figure.loc[0] = 4;
                    T_figure.loc[1] = 4;
                    T_figure.pos = 0;
                }else isdead = true;
                break;
            }
        }
        MyPanel.ispainted = false;
        return fig;
    }

    static private Color defineColor(){
        switch (fig){
            case 0:return Color.blue;
            case 1:return Color.green;
            case 2:return Color.red;
            case 3:return Color.gray;
            case 4:return Color.ORANGE;
            default: return null;
        }
    }
    static private int[][] create_matr_copy() {
        int[][] copymatr = new int[26][10];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 10; j++) {
                if (matrix[i][j] == 1) {
                    copymatr[i][j] = matrix[i][j];
                }else copymatr[i][j] = 0;
            }
        }
        return copymatr;
    }
    static void showmatr(){
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    static int[][] plfigmove(int dir) {
        int[][] copymatr;
        copymatr = create_matr_copy();
        try {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 10; j++) {
                    if (matrix[i][j] == 2) {
                        switch (dir) {
                            case 0: {
                                if (matrix[i+1][j]!=1){
                                    copymatr[i + 1][j] = 2; //вниз
                                    break;
                                }
                                else return matrix;
                            }
                            case 1: {
                                if (matrix[i][j-1]!=1) {
                                    copymatr[i][j - 1] = 2;//лево
                                    break;
                                }
                                else return matrix;
                            }
                            case 2: {
                                if (matrix[i][j+1]!=1) {
                                    copymatr[i][j + 1] = 2;//право
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return matrix;
        }
        return copymatr;
    }

    static int[][] figdown() {
        int[][] copymatr;
        copymatr = create_matr_copy();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 10; j++) {
                if (matrix[i][j] == 2) {
                    copymatr[i + 1][j] = 2; //вниз
                }
            }
        }
        return copymatr;
    }

    static void touch(){
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 10; j++) {
                if (matrix[i][j] == 2) {
                    matrix[i][j] = 1;

                    MyPanel.colmatrix[i-4][j] = defineColor();

                }
            }
        }
        MyPanel.isspawned = false;
        checkcolmatr();
        if (!MyPanel.isspawned){
            MyPanel.spawning();
        }

    }

    static boolean touchwc() {
        boolean prov = false;
        boolean flag = false;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 10; j++) {
                if (matrix[i][j] == 2 && (i >= 25 || matrix[i + 1][j] == 1)) {
                    flag = true;
                    prov = true;
                    break;
                }
            }
            if (prov) {
                break;
            }
        }
        return flag;

    }


    private static void checkcolmatr(){

        boolean flag;
        for (int i = 0;i<26;i++){
            Color[][] copycolmatr = MyPanel.create_colrmatr_copy();

            int[][] copymatr = create_matr_copy();
            flag = true;int j = 0;
            while (j<10 && flag){
                if (matrix[i][j] != 1){
                    flag = false;
                }
                j++;
            }
            if (flag){
                MyPanel.num+=10;
                MyPanel.score.setText("Score: " + MyPanel.num);
                for (int k = 0; k<=i;k++){
                    for (int m = 0;m<10;m++){
                        if (k == 0){
                            matrix[k][m] = 0;
                            MyPanel.colmatrix[k][m] = null;
                        }
                        else{
                            matrix[k][m] = copymatr[k-1][m];
                            if (k>4)
                            MyPanel.colmatrix[k-4][m] = copycolmatr[k-5][m];
                        }

                    }
                }
            }
        }
    }

    static boolean isnear(boolean dir){//1 - право; 0 - лево
        byte rez = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 10; j++) {
                if (dir) {
                    if (matrix[i][j] == 2 && (j == 9 || matrix[i][j + 1] == 1)) {

                        return false;
                    }
                }else
                    if (matrix[i][j] == 2 && (j == 0 || matrix[i][j-1] == 1)){
                        return false;
                }

            }
        }
        return true;
    }

}



