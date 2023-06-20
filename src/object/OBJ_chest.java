/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import houkai.GamePanel;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author TOSHIBA
 */
public class OBJ_chest extends superObject {
    GamePanel gp;
      public OBJ_chest(GamePanel gp){
        this.gp = gp;
        name = "chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/tiles/chest_1.png"));  
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}