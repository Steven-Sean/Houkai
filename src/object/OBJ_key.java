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
public class OBJ_key extends superObject { // class ini untuk memperluas superobject dan konstraktor
    public OBJ_key(){        
        name = "key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/tiles/key.png")); // untuk memuat gambar
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
    

