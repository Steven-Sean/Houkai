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
public class Key extends Item { // class ini untuk memperluas superobject dan konstraktor
    GamePanel gp;
    public Key(GamePanel gp){        
        this.gp = gp;
        name = "key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/tiles/key.png")); // untuk memuat gambar
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
    

