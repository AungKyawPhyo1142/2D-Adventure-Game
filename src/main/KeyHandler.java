package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed,downPressed,leftPressed,rightPressed;
    

    @Override
    public void keyPressed(KeyEvent ke) {

        int code = ke.getKeyCode();
        
        if(code==KeyEvent.VK_W){
            upPressed=true;
        }
        else if(code==KeyEvent.VK_S){
            downPressed=true;
        }
        else if(code==KeyEvent.VK_A){
            leftPressed=true;
        }
        else if(code==KeyEvent.VK_D){
            rightPressed=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int code = ke.getKeyCode();
        
        if(code==KeyEvent.VK_W){
            upPressed=false;
        }
        else if(code==KeyEvent.VK_S){
            downPressed=false;
        }
        else if(code==KeyEvent.VK_A){
            leftPressed=false;
        }
        else if(code==KeyEvent.VK_D){
            rightPressed=false;
        }

    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }
    
}
