package Object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_Key extends SuperObject {

    GamePanel gp;
    public OBJ_Key(GamePanel gp){
        this.gp = gp;
        name = "Key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("../res/objects/key.png"));
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException ie){
            ie.printStackTrace();
        }
    }

}
