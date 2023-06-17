/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author AsuS
 */
public class OBJ_boots extends superObject {
    public OBJ_boots(){        
        name = "boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/tiles/food.png")); // untuk memuat gambar
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
