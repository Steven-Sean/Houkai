/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import houkai.GamePanel;
import houkai.UtilityTool;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author TOSHIBA
 */
public class Item { // ini jadi class induk dari segala segala class objek
    BufferedImage[] image = new BufferedImage[3];
    String name;
    boolean collision = false ;
    int worldX, worldY;
    //--> agar objek menjadi solid area atau seluruh petak objeknya padat
    Rectangle solidArea = new Rectangle (0,0,48,48); 
    UtilityTool uTool = new UtilityTool();

    public Item(GamePanel gp, int worldX, int worldY) {
        this.worldX = worldX * gp.tileSize;
        this.worldY = worldY * gp.tileSize;
    }

    public void draw(Graphics2D g2, GamePanel gp){ // untuk menggambat
        int screenX = worldX - gp.player.getWorldX() + gp.player.screenX;
        int screenY = worldY - gp.player.getWorldY() + gp.player.screenY ;
        //--> berfungsi untuk menggambar tiles disekitar player saja jadi tidak melebihi screen
        if (worldX + gp.tileSize > gp.player.getWorldX() - gp.player.screenX && 
            worldX - gp.tileSize < gp.player.getWorldX() + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.getWorldY() - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.getWorldY() + gp.player.screenY){
                
            //--> Untuk Mencetak Gambar Map 
            g2.drawImage(image[0], screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
    
    public BufferedImage getFirstImage() {
        return image[0];
    }

    public BufferedImage[] getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public boolean isCollidable() {
        return collision;
    }
}
