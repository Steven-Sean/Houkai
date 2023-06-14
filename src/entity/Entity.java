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

    int worldX;
    int worldY;
    int speed;

    BufferedImage[] up = new BufferedImage[3];
    BufferedImage[] down = new BufferedImage[3];
    BufferedImage[] left = new BufferedImage[3];
    BufferedImage[] right = new BufferedImage[3];
    String direction;

    int spriteCounter = 0;
    int spriteNum = 1;

    //--> Untuk kelas ini kita dapat membuat persegi panjang yg tak terlihat
    Rectangle solidArea;
    int solidAreaDefaultX;
    int solidAreaDefaultY;
    boolean collisionOn = false;
    
    public void resetSolidArea() {
        solidArea.x = solidAreaDefaultX;
        solidArea.y = solidAreaDefaultY;
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
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * @return the direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * @return the spriteCounter
     */
    public int getSpriteCounter() {
        return spriteCounter;
    }

    /**
     * @param spriteCounter the spriteCounter to set
     */
    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    /**
     * @return the spriteNum
     */
    public int getSpriteNum() {
        return spriteNum;
    }

    /**
     * @param spriteNum the spriteNum to set
     */
    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
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

    /**
     * @return the collisionOn
     */
    public boolean isCollisionOn() {
        return collisionOn;
    }

    /**
     * @param collisionOn the collisionOn to set
     */
    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    /**
     * @return the up
     */
    public BufferedImage[] getUp() {
        return up;
    }

    /**
     * @param up the up to set
     */
    public void setUp(BufferedImage[] up) {
        this.up = up;
    }

    /**
     * @return the down
     */
    public BufferedImage[] getDown() {
        return down;
    }

    /**
     * @param down the down to set
     */
    public void setDown(BufferedImage[] down) {
        this.down = down;
    }

    /**
     * @return the left
     */
    public BufferedImage[] getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(BufferedImage[] left) {
        this.left = left;
    }

    /**
     * @return the right
     */
    public BufferedImage[] getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(BufferedImage[] right) {
        this.right = right;
    }
}
