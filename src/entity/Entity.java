/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author AsuS
 */
public class Entity {
    public int worldX, worldY;
    public int speed;
    
    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public String direction;
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    //--> Untuk kelas ini kita dapat membuat persegi panjang yg tak terlihat
    public Rectangle solidArea; 
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
}
