package Object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Boot extends SuperObject {

    public OBJ_Boot(){
        name = "Boot";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("../res/objects/boots.png"));
        }catch(IOException ie){
            ie.printStackTrace();
        }
    }

    
}
