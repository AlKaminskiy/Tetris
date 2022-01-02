package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyPanel extends JPanel {
    static Color[][] colmatrix = new Color[22][10];

    static boolean isspawned = false;
    JButton exit = new JButton();
    static JLabel score = new JLabel();

    static Timer timer;
    JButton newgame = new JButton();
    static int x = 150;
    static int y = 30;
    static int dir = -1;// 0 - вниз | 1 - вправо | 2 - влево
    static int spfig=-1;
    static int delay = 300;//300 - normal
    boolean flag  = false;
    static int num = 0;
    static boolean ispainted = false;
    boolean showmessage=false;
    MyPanel() {
        setLayout(null);
        exit.setFocusable(false);
        newgame.setFocusable(false);
        exit.setText("Выход");
        exit.setBounds(370, 150, 100, 50);
        exit.addActionListener(new Exit());
        newgame.addActionListener(new NewGame());
        add(exit);
        newgame.setText("Новая игра");
        newgame.setBounds(370, 90, 100, 50);
        add(newgame);
        score.setBounds(370,30,100,30);
        score.setFocusable(false);
        setFocusable(true);
        score.setText("Score: " + num);
        Font labelFont = score.getFont();
        score.setFont(new Font(labelFont.getName(), Font.PLAIN, 17));
        newgame.setFont(new Font(labelFont.getName(), Font.PLAIN, 12));
        exit.setFont(new Font(labelFont.getName(), Font.PLAIN, 12));
        add(score);
        addKeyListener(new PlayerMove());

        timer = new Timer(delay, (ActionListener) e -> {
            setFocusable(true);
            if (!flag) {
                if (!Logic.touchwc()){
                    if (ispainted) {
                        y += 30;
                        Logic.matrix = Logic.figdown();
                        switch (spfig) {
                            case 0:
                                L_figure.loc[0]++;
                                break;
                            case 1:
                                I_figure.loc[0]++;
                                break;
                            //case 2:O_figure.loc[0]++;break;
                            case 3:
                                S_figure.loc[0]++;
                                break;
                            case 4:
                                T_figure.loc[0]++;
                                break;
                        }
                        Logic.showmatr();
                    }
                    repaint();
                }
            }
            if (Logic.touchwc()){
                if (flag){
                    Logic.touch();
                }
                flag = true;
            }else {
                flag = false;
            }

        });
        startgame();


    }

    public static Color[][] create_colrmatr_copy() {
        Color[][] copymatr = new Color[22][10];
        for (int i = 0;i<22;i++){
            for (int j = 0;j<10;j++){
                copymatr[i][j] = colmatrix[i][j];
            }
        }
        return copymatr;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        for (int i = 0; i < 11; i++) {
            g.drawLine(30 + 30 * i, 30, 30 + 30 * i, 690);
        }
        for (int i = 0; i < 23; i++) {
            g.drawLine(30, 30 + 30 * i, 330, 30 + 30 * i);
        }
        switch (spfig){
            case 0:{
                L_figure lFigure = new L_figure();
                lFigure.drawfigure(g,x,y);
                ispainted = true;
                break;
            }
            case 1:{
                I_figure iFigure = new I_figure();
                iFigure.drawfigure(g,x,y);
                ispainted = true;
                break;
            }
            case 2:
                O_figure oFigure = new O_figure();
                oFigure.drawfigure(g,x,y);
                ispainted = true;
                break;
            case 3:
                S_figure sFigure = new S_figure();
                sFigure.drawfigure(g,x,y);
                ispainted = true;
                break;
            case 4:
                T_figure tFigure = new T_figure();
                tFigure.drawfigure(g,x,y);
                ispainted = true;
            default:
        }
        for (int i = 0;i<22;i++){
            for (int j = 0;j<10;j++){
                if (colmatrix[i][j] != null){
                g.setColor(colmatrix[i][j]);
                g.fillRect(30+30*j,30+30*i,30,30);
                }
            }
        }
        if (Logic.isdead){
            if (!showmessage){
                showmessage = true;
                JOptionPane.showMessageDialog(null,"Погиб смертью храбрых");

                paintComponent(getGraphics());
                repaint();

            }
            System.out.println();
            timer.stop();
        }
    }

    void initColormatr(){
        for (int i = 0;i<22;i++){
            for (int j = 0;j<10;j++){
            colmatrix[i][j] = null;
            }
        }
    }

    void startgame(){
        initColormatr();
        Logic.update_matrix();
        spawning();
        timer.start();
        num = 0;

    }
     static void spawning(){
        int spfig = Logic.spawnfigure();
        x = 150;
        y = 30;
        isspawned = true;
    }

    class PlayerMove implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {

                switch (e.getKeyCode()) {

                    case 40: {
                        if (!Logic.touchwc()) {
                            if (!Logic.isdead) {
                                MyPanel.dir = 0;
                                Logic.matrix = Logic.plfigmove(0);
                                switch (spfig){
                                    case 0:L_figure.loc[0]++;break;
                                    case 1:I_figure.loc[0]++;break;
                                    case 3:S_figure.loc[0]++;break;
                                    case 4:T_figure.loc[0]++;break;
                                }
                                MyPanel.y += 30;
                                paintComponent(getGraphics());
                                repaint();
                            }
                        }
                        break;
                    }
                    case 37: {
                        if (Logic.isnear(false) && !Logic.isdead) {
                            MyPanel.dir = 1;
                            Logic.matrix = Logic.plfigmove(1);
                            switch (spfig){
                                case 0:L_figure.loc[1]--;break;
                                case 1:I_figure.loc[1]--;break;
                                //case 2:O_figure.loc[1]--;break;
                                case 3:S_figure.loc[1]--;break;
                                case 4:T_figure.loc[1]--;break;
                            }
                            MyPanel.x -= 30;
                            paintComponent(getGraphics());
                            repaint();
                        }
                        break;
                    }
                    case 39: {
                        if (Logic.isnear(true) && !Logic.isdead) {
                            MyPanel.dir = 2;
                            Logic.matrix = Logic.plfigmove(2);
                            switch (spfig){
                                case 0:L_figure.loc[1]++;break;
                                case 1:I_figure.loc[1]++;break;
                                //case 2:O_figure.loc[1]--;break;
                                case 3:S_figure.loc[1]++;break;
                                case 4:T_figure.loc[1]++;break;
                            }
                            MyPanel.x += 30;
                            paintComponent(getGraphics());
                            repaint();
                        }
                        break;
                    }
                    case 38: {
                        if (!Logic.isdead) {
                            switch (spfig) {
                                case 0:
                                    L_figure.rotate();
                                    break;
                                case 1:
                                    I_figure.rotate();
                                    break;
                                case 3:
                                    S_figure.rotate();
                                    break;
                                case 4:
                                    T_figure.rotate();
                                    break;

                            }
                            paintComponent(getGraphics());
                            repaint();
                        }
                        break;
                    }

                }

            if (e.getKeyCode() == 83){
                Logic.showmatr();
            }
            System.out.println(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            MyPanel.dir = 0;
        }
    }
    class NewGame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        timer.stop();
        dir = -1;
        spfig=-1;
        ispainted = false;
        showmessage=false;
        Logic.isdead = false;
        Logic.fig = -1;
        startgame();

        }
    }
}

class Exit implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}

