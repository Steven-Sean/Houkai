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
public class Heart extends Item { 
    GamePanel gp;
    
    public Heart(GamePanel gp){   
        this.gp = gp;
        name = "heart";
        try {
            image[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/heart_full.png"));
            image[1] = ImageIO.read(getClass().getResourceAsStream("/tiles/heart_half.png"));
            image[2] = ImageIO.read(getClass().getResourceAsStream("/tiles/heart_blank.png"));
            image[0] = uTool.scaleImage(image[0], gp.tileSize, gp.tileSize);
            image[1] = uTool.scaleImage(image[1], gp.tileSize, gp.tileSize);
            image[2] = uTool.scaleImage(image[2], gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}