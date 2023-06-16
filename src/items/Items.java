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
public abstract class Items { // ini jadi class induk dari segala segala class objek

    BufferedImage image; // imagenya berarti BufferdImage
    String name; 
    boolean collision = false; // berarti item gabisa ditabrak
    int worldX; // agar objek menjadi solid area atau seluruh petak objeknya padat
    int worldY;
    Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    int solidAreaDefaultX = 0;
    int solidAreaDefaultY = 0;

    public Items(int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
    }

    public void draw(Graphics2D g2, GamePanel gp) { // untuk menggambar
        int screenX = getWorldX() - gp.getPlayer().getWorldX() + gp.getPlayer().screenX;
        int screenY = getWorldY() - gp.getPlayer().getWorldY() + gp.getPlayer().screenY;
        //--> berfungsi untuk menggambar tiles disekitar player saja jadi tidak melebihi screen
        if (getWorldX() + gp.tileSize > gp.getPlayer().getWorldX() - gp.getPlayer().screenX
                && getWorldX() - gp.tileSize < gp.getPlayer().getWorldX() + gp.getPlayer().screenX
                && getWorldY() + gp.tileSize > gp.getPlayer().getWorldY() - gp.getPlayer().screenY
                && getWorldY() - gp.tileSize < gp.getPlayer().getWorldY() + gp.getPlayer().screenY) {

            //--> Untuk Mencetak Gambar Map 
            g2.drawImage(getImage(), screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
    
    
    public void resetSolidArea() {
        solidArea.x = solidAreaDefaultX;
        solidArea.y = solidAreaDefaultY;
    }

    /**
     * @return the image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the collision
     */
    public boolean isCollision() {
        return collision;
    }

    /**
     * @param collision the collision to set
     */
    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    /**
     * @return the worldX
     */
    public int getWorldX() {
        return worldX;
    }

    /**
     * @param worldX the worldX to set
     */
    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    /**
     * @return the worldY
     */
    public int getWorldY() {
        return worldY;
    }

    /**
     * @param worldY the worldY to set
     */
    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    /**
     * @return the solidArea
     */
    public Rectangle getSolidArea() {
        return solidArea;
    }

    /**
     * @param solidArea the solidArea to set
     */
    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    /**
     * @return the solidAreaDefaultX
     */
    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    /**
     * @param solidAreaDefaultX the solidAreaDefaultX to set
     */
    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }

    /**
     * @return the solidAreaDefaultY
     */
    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    /**
     * @param solidAreaDefaultY the solidAreaDefaultY to set
     */
    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }
}
