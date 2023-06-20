/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import houkai.GamePanel;
import houkai.UtilityTool;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author AsuS
 */
public class Entity {

    GamePanel gp;
    int worldX, worldY;
    int speed;

    BufferedImage[] up = new BufferedImage[3];
    BufferedImage[] down = new BufferedImage[3];
    BufferedImage[] left = new BufferedImage[3];
    BufferedImage[] right = new BufferedImage[3];
    String direction;

    int spriteCounter = 0;
    int spriteNum = 1;

    //--> Untuk kelas ini kita dapat membuat persegi panjang yg tak terlihat
    Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    boolean collisionOn = false;
    int actionLockCounter = 0;
    String dialogues[] = new String[20];
    int dialogueIndex = 0;

    //--> Untuk status karakter
    int maxLife;
    int life;

    public Entity(GamePanel gp, int worldX, int worldY) {
        this(gp);
        this.worldX = worldX * gp.tileSize;
        this.worldY = worldY * gp.tileSize;
    }

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {

    }

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.houkaiUI.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction) {
            case "up":
                direction = "up";
                break;
            case "down":
                direction = "down";
                break;
            case "right":
                direction = "right";
                break;
            case "left":
                direction = "left";
                break;

        }
    }

    public void update() {
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkItem(this, false);
        gp.cChecker.checkPlayer(this);

        //--> untuk pengecekan jika Collision == false maka player dapat gerak dan sebaliknya
        if (collisionOn == false) {
            switch (direction) {
                case "up":
                    worldY -= speed; //key W
                    break;
                case "down":
                    worldY += speed; //key S
                    break;
                case "left":
                    worldX -= speed; //key A
                    break;
                case "right":
                    worldX += speed; //key D
                    break;
            }
        }
        spriteCounter++;

        //--> Untuk mengatur kecepatan sprite 
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        //--> berfungsi untuk menggambar tiles disekitar player saja jadi tidak melebihi screen
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            image = getImageByDirection(direction);
            //--> Untuk Mencetak Gambar Map 
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

    BufferedImage setup(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public BufferedImage getImageByDirection(String direction) {
        switch (direction) {
            case "up": //key W
                return up[spriteNum - 1];
            case "down": //Key S
                return down[spriteNum - 1];
            case "left": //Key A
                return left[spriteNum - 1];
            case "right": //Key D
                return right[spriteNum - 1];
        }
        return null;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public String getDirection() {
        return direction;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }

    public int getLife() {
        return life;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public int getSpeed() {
        return speed;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public void setLife(int life) {
        this.life = life;
    }

}
