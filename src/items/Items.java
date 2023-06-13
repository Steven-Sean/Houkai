/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package items;

import houkai.GamePanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author TOSHIBA
 */
public class Items { // ini jadi class induk dari segala segala class objek

    public BufferedImage image;
    public String name;
    public boolean coliision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // agar objek menjadi solid area atau seluruh petak objeknya padat
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gp) { // untuk menggambat
        int screenX = worldX - gp.player.getWorldX() + gp.player.screenX;
        int screenY = worldY - gp.player.getWorldY() + gp.player.screenY;
        //--> berfungsi untuk menggambar tiles disekitar player saja jadi tidak melebihi screen
        if (worldX + gp.tileSize > gp.player.getWorldX() - gp.player.screenX
                && worldX - gp.tileSize < gp.player.getWorldX() + gp.player.screenX
                && worldY + gp.tileSize > gp.player.getWorldY() - gp.player.screenY
                && worldY - gp.tileSize < gp.player.getWorldY() + gp.player.screenY) {

            //--> Untuk Mencetak Gambar Map 
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
