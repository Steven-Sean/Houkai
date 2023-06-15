/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import houkai.GamePanel;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Steven
 */
public final class Enemy extends Entity {

    public Enemy(int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
        
        getImage();
    }
    
    public void getImage() {
        try {
            String resourcePath = "/player/nu_";
            for (int i = 0; i < 3; i++) {
                getUp().set(i, ImageIO.read(getClass().getResourceAsStream(resourcePath +"up_0" + (i+1) + ".png")));
                getDown().set( i,ImageIO.read(getClass().getResourceAsStream(resourcePath +"down_0" + (i+1) + ".png")));
                getLeft().set( i,ImageIO.read(getClass().getResourceAsStream(resourcePath +"left_0" + (i+1) + ".png")));
                getRight().set(i, ImageIO.read(getClass().getResourceAsStream(resourcePath +"right_0" + (i+1) + ".png")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, GamePanel gp) { // untuk menggambar
        int screenX = getWorldX() - gp.getPlayer().getWorldX() + gp.getPlayer().screenX;
        int screenY = getWorldY() - gp.getPlayer().getWorldY() + gp.getPlayer().screenY;
        //--> berfungsi untuk menggambar tiles disekitar player saja jadi tidak melebihi screen
        BufferedImage image = down.get(getSpriteNum());
        
        if (getWorldX() + gp.tileSize > gp.getPlayer().getWorldX() - gp.getPlayer().screenX
                && getWorldX() - gp.tileSize < gp.getPlayer().getWorldX() + gp.getPlayer().screenX
                && getWorldY() + gp.tileSize > gp.getPlayer().getWorldY() - gp.getPlayer().screenY
                && getWorldY() - gp.tileSize < gp.getPlayer().getWorldY() + gp.getPlayer().screenY) {

            //--> Untuk Mencetak Gambar Map 
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
    
}
