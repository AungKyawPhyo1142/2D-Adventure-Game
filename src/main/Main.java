package main;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
    public Main( ){
        super("Java 2D Game");
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel gamePanel = new GamePanel();
        this.add(gamePanel);
        gamePanel.setUpGame();
        this.pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[]args){
        Main main = new Main();
    }
}
