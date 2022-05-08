package main;

import Object.OBJ_Key;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
 
    GamePanel gp;
    Font arial40;
    Font arial80B;
    
    public boolean messageOn=false;
    public String message = "";
    
    int messageCounter=0;
    public boolean gameFinished = false;

    BufferedImage keyImage;
    
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    
    double playTime=0;
    
    public UI(GamePanel gp){
        this.gp = gp;
        arial40 = new Font("Arial",Font.PLAIN,40);
        arial80B = new Font("Arial",Font.BOLD,80);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
    }
    
    public void draw(Graphics2D g2){

        if(gameFinished==true){
            String text; 
            int textLength;
            int x,y;
            
            g2.setFont(arial40);
            g2.setColor(Color.white);
            text = "You found the treasure!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();            
            x = gp.gameWidth/2 - textLength/2;
            y = gp.gameHeight/2 - gp.tileSize*3; 
            g2.drawString(text, x, y);

            g2.setFont(arial40);
            g2.setColor(Color.white);
            text = "Your time is "+dFormat.format(playTime)+"!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();            
            x = gp.gameWidth/2 - textLength/2;
            y = gp.gameHeight/2 + gp.tileSize*4; 
            g2.drawString(text, x, y);

            
            g2.setFont(arial80B);
            g2.setColor(Color.yellow);
            text = "Congratulations";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();            
            x = gp.gameWidth/2 - textLength/2;
            y = gp.gameHeight/2 + gp.tileSize*2; 
            g2.drawString(text, x, y);
            
            gp.gameThread = null;

        }
        else{
            
            g2.setFont(arial40);
            g2.setColor(Color.white);
            playTime += (double) 1/60;
            g2.drawString("Time: "+dFormat.format(playTime), gp.tileSize*11, 65);
            
            g2.setFont(arial40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2,gp.tileSize,gp.tileSize,null);
            g2.drawString("x "+gp.player.hasKey, 74, 65);

            if(messageOn==true){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message,gp.tileSize/2,gp.tileSize*5);
            }

            messageCounter++;

            if(messageCounter>120){
                messageCounter=0;
                messageOn=false;
            }

        }
        
    }
    
    public void showMessage(String text){
        message = text;
        messageOn=true;
    }
    
}
