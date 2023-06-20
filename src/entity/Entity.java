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
    public int worldX, worldY;
    public int speed;
    
    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public String direction;
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    //--> Untuk kelas ini kita dapat membuat persegi panjang yg tak terlihat
    public Rectangle solidArea = new Rectangle(0,0,48,48); 
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    String dialogues[] = new String[20];
    int dialogueIndex = 0;
    
    //--> Untuk status karakter
    public int maxLife;
    public int life;
    

    public Entity(GamePanel gp){
        this.gp = gp;
    }
    
    public void setAction(){
        
    }
    
    public void speak(){
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
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
    public void update(){
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);
        
        //--> untuk pengecekan jika Collision == false maka player dapat gerak dan sebaliknya
        if(collisionOn == false){
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
        if(spriteCounter > 12){
            if(spriteNum == 1){
                spriteNum = 2;
            }else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY ;
        //--> berfungsi untuk menggambar tiles disekitar player saja jadi tidak melebihi screen
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
            
            switch(direction){
            case "up": //key W
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                if(spriteNum == 3){
                    image = up3;
                }
                break;
            case "down": //Key S
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                if(spriteNum == 3){
                    image = down3;
                }
                break;
            case "left": //Key A
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                if(spriteNum == 3){
                    image = left3;
                }
                break;
            case "right": //Key D
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                if(spriteNum == 3){
                    image = right3;
                }
                break;
            }
            //--> Untuk Mencetak Gambar Map 
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
    
    public BufferedImage setup(String imagePath){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        
        try{
           image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
           image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }
}
