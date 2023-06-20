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
public class Door extends Item {

    public Door(GamePanel gp, int worldX, int worldY) {
        super(gp, worldX, worldY);
        name = "door";
        try {
            image[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/door.png"));
            uTool.scaleImage(image[0], gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
