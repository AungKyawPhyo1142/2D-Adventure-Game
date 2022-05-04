package Object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject{
     public OBJ_Door(){
        name = "Door";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("../res/objects/door.png"));
        }catch(IOException ie){
            ie.printStackTrace();
        }
        collision=true;
    }
   
}
