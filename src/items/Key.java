/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package items;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author TOSHIBA
 */
public class Key extends Items { // class ini untuk memperluas superobject dan konstraktor

    public Key(int worldX, int worldY) {
        super(worldX, worldY);
        name = "key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/tiles/key.png")); // untuk memuat gambar
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
