package Object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_Boot extends SuperObject {
    GamePanel gp;
    public OBJ_Boot(GamePanel gp){
        this.gp = gp;
        name = "Boot";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("../res/objects/boots.png"));
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException ie){
            ie.printStackTrace();
        }
    }

    
}
