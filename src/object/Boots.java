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
public class Boots extends Item {
    GamePanel gp;
    public Boots(GamePanel gp){        
        this.gp = gp;
        name = "boots";
        try {
            image[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/food.png"));
            uTool.scaleImage(image[0], gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
