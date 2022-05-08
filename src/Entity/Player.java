package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    
    public final int screenX;
    public final int screenY;
    public int hasKey=0;
    
    
    public Player(GamePanel gp,KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        screenX = (gp.gameWidth/2) - (gp.tileSize/2);
        screenY = (gp.gameHeight/2) - (gp.tileSize/2);
        
        solidArea = new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=32;
        solidArea.height=32;
        
        setDefaultValues();
        getPlayerImage();
    }
    

    
    public void setDefaultValues(){
        worldX=gp.tileSize*23;
        worldY=gp.tileSize*21;
        speed=4;
        direction = "down"; // set the default dir to down, any dir is fine
    }
    
    public void update(){

        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
        
            if(keyH.upPressed){
                direction="up";
            }
            else if(keyH.downPressed){
                direction="down";
            }
            else if(keyH.leftPressed){
                direction="left";
            }
            else if(keyH.rightPressed){
                direction="right";
            }            
        
            collisionOn=false;
            gp.cChecker.checkTile(this);
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            
            // if there is no collison, player can move
            if(collisionOn==false){
                switch(direction){
                    case "up": worldY-=speed; break;
                        
                    case "down": worldY+=speed; break;
                    
                    case "left": worldX-=speed; break;
                    
                    case "right": worldX+=speed; break;
                }
            }
            
            spriteCounter++;
            if(spriteCounter>12){
                if(spriteNum==1){ spriteNum=2; }
                else if(spriteNum==2){ spriteNum=1; }
                spriteCounter=0;
            }
            
        }
        
    }
    
    public void getPlayerImage(){
        
        up1 = setup("boy_up_1");
        up2 = setup("boy_up_2");

        down1 = setup("boy_down_1");
        down2 = setup("boy_down_2");

        left1 = setup("boy_left_1");
        left2 = setup("boy_left_2");

        right1 = setup("boy_right_1");
        right2 = setup("boy_right_2");
        
    }
    
    public BufferedImage setup(String imgName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaleImage = null;
        try{
            scaleImage = ImageIO.read(getClass().getResourceAsStream("../res/player/"+imgName+".png"));
            scaleImage = uTool.scaledImage(scaleImage, gp.tileSize, gp.tileSize);
        }catch(IOException ie){
            System.out.println(""+ie.getStackTrace());
        }
        return scaleImage;
    }
    
    public void pickUpObject(int i){
        if(i!=999){
            String objName = gp.obj[i].name;
            switch(objName){
                case "Key": gp.playSE(1); // play key pick up sound Effect
                            hasKey++;
                            gp.obj[i] = null;
                            gp.ui.showMessage("You got a key!");
                            break;
                case "Door":
                    if(hasKey>0){
                        gp.playSE(3); // play unlocked door sE
                        gp.obj[i] = null;
                        gp.ui.showMessage("You opened the door!");
                        hasKey--;
                    }
                    else{
                        gp.ui.showMessage("You need a key!");
                    }
                    break;
                
                case "Boot":
                    gp.playSE(2);
                    speed+=2;
                    gp.obj[i]=null;
                    gp.ui.showMessage("Speed Up!!");
                    break;
                
                case "Chest":
                    gp.ui.gameFinished=true;
                    gp.stopMusic();
                    gp.playSE(4); // play the finished game
                    break;
            }
        }
    }
    
    public void draw(Graphics2D g2){
        BufferedImage image=null;

            switch(direction){
                case "up": 
                            if(spriteNum==1){image=up1;}
                            if(spriteNum==2){image=up2;}
                            break;

                case "down": 
                            if(spriteNum==1){image=down1;}
                            if(spriteNum==2){image=down2;}
                            break;

                case "left": 
                            if(spriteNum==1){image=left1;}
                            if(spriteNum==2){image=left2;}
                            break;

                case "right": 
                            if(spriteNum==1){image=right1;}
                            if(spriteNum==2){image=right2;}
                            break;

            }

        g2.drawImage(image,screenX,screenY,null);
        
    }
    
}
