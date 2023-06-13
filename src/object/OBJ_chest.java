/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author TOSHIBA
 */
public class OBJ_chest extends superObject {
      public OBJ_chest(){
        name = "chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/tiles/chest_1.png")); // untuk memuat gambar                    
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}