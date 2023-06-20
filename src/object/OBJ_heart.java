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
 * @author AsuS
 */
public class OBJ_heart extends superObject { 
    GamePanel gp;
    
    public OBJ_heart(GamePanel gp){   
        this.gp = gp;
        name = "heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/tiles/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/tiles/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/tiles/heart_blank.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image3 = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}