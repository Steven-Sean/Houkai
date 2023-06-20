/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package houkai;

import entity.Entity;
import java.awt.Rectangle;
/**
 *
 * @author AsuS
 */
public class CollisionChecker {
    GamePanel gp;
    
    //--> Constructor untuk memeriksa tabrakan
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    
    //--> Untuk pengecekan ubin
    public void checkTile(Entity entity){
        //--> Untuk pengecekan berdasarkan koordinat Solid Area (daerah yg padat player)
        int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x; //kiriX = 8
        int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width; //kananX = 16
        int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y; //atasY = 32
        int entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height; //bawahY = 32
        
        //--> Untuk mencari Nomor Col dan Row didapatkan dari koordinat diatas 
        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;
        
        int tileNum1 = 0, tileNum2 = 0;
        
        //--> Untuk memeriksa arah entity mau kemana
        boolean check = false;
        switch(entity.getDirection()){
        case "up":
            entityTopRow = (entityTopWorldY - entity.getSpeed())/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            check = true;
            break;
        case "down":
            entityBottomRow = (entityBottomWorldY + entity.getSpeed())/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            check = true;
            break;
        case "left":
            entityLeftCol = (entityLeftWorldX + entity.getSpeed())/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            check = true;
            break;
        case "right":
            entityRightCol = (entityRightWorldX - entity.getSpeed())/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            check = true;
            break;
        }
        //--> Untuk pengecekan jika ada salah 1 atau keduanya benar, pemain menabrak petak padat sehingga tidak dapat bergerak ke arah ini
        if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
            entity.setCollisionOn(true);
        }
    }
        
    public int checkObject(Entity entity, boolean player){
        int index = 999;
        
        for (int i=0; i<gp.item.length; i++){
            if (gp.item[i] != null){
                Rectangle entitySolid = (Rectangle) entity.getSolidArea().clone();
                //--> Untuk mendapatkan entity petaknya padat
                entitySolid.x = entity.getWorldX() + entitySolid.x;
                entitySolid.y = entity.getWorldY() + entitySolid.y;
                
                //--> Untuk mendapatkan object petaknya padat
                gp.item[i].solidArea.x = gp.item[i].worldX + gp.item[i].solidArea.x;
                gp.item[i].solidArea.y = gp.item[i].worldY + gp.item[i].solidArea.y;

                //--> Untuk menentukan arah entity
                boolean check = false;
                switch (entity.getDirection()){ 
                    case "up":
                        entitySolid.y -= entity.getSpeed();
                        check = true;
                        break;
                    case "down":
                        entitySolid.y += entity.getSpeed();
                        check = true;
                        break;
                    case "left":
                        entitySolid.x -= entity.getSpeed();
                        check = true;
                        break;
                    case "right":
                        entitySolid.x += entity.getSpeed();
                        check = true;
                        break;
                }
                
                //--> Untuk memberi tanda jika entity tabrakan
                if (entitySolid.intersects(gp.item[i].solidArea)){
                    if (gp.item[i].collision == true){
                        entity.setCollisionOn(true);
                    }
                    if (player == true){
                        index = i;
                    }
                }
                
                //--> Untuk mengatur ulang solidArea entity dan object
                gp.item[i].solidArea.x = gp.item[i].solidAreaDefaultX;
                gp.item[i].solidArea.y = gp.item[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    
    //--> Untuk NPC dan Monster
    public int checkEntity(Entity entity, Entity[] target){
        int index = 999;
        
        for (int i=0; i<target.length; i++){
            if (target[i] != null){
                Rectangle entitySolid = (Rectangle) entity.getSolidArea().clone();
                Rectangle targetSolid = (Rectangle) target[i].getSolidArea().clone();
                
                //--> Untuk mendapatkan entity petaknya padat
                entitySolid.x = entity.getWorldX() + entitySolid.x;
                entitySolid.y = entity.getWorldY() + entitySolid.y;
                
                //--> Untuk mendapatkan object petaknya padat
                targetSolid.x = target[i].getWorldX() + targetSolid.x;
                targetSolid.y = target[i].getWorldY() + targetSolid.y;

                //--> Untuk menentukan arah entity
                boolean check = false;
                switch (entity.getDirection()){ 
                    case "up":
                        entitySolid.y -= entity.getSpeed();
                        check = true;
                        break;
                    case "down":
                        entitySolid.y += entity.getSpeed();
                        check = true;
                        break;
                    case "left":
                        entitySolid.x -= entity.getSpeed();
                        check = true;
                        break;
                    case "right":
                        entitySolid.x += entity.getSpeed();
                        check = true;
                        break;
                }
                
                //--> Untuk memberi tanda jika entity tabrakan
                if (entitySolid.intersects(targetSolid)){
                    entity.setCollisionOn(true);
                    index = i;
                }
            }
        }
        return index;
    }
    public void checkPlayer(Entity entity){
        Rectangle entitySolid = (Rectangle) entity.getSolidArea().clone();
        Rectangle playerSolid = (Rectangle) gp.player.getSolidArea().clone();
        //--> Untuk mendapatkan entity petaknya padat
        entitySolid.x = entity.getWorldX() + entitySolid.x;
        entitySolid.y = entity.getWorldY() + entitySolid.y;
                
        //--> Untuk mendapatkan object petaknya padat
        playerSolid.x = gp.player.getWorldX() + playerSolid.x;
        playerSolid.y = gp.player.getWorldY() + playerSolid.y;

        //--> Untuk menentukan arah entity
        boolean check = false;
        switch (entity.getDirection()){ 
            case "up":
                entitySolid.y -= entity.getSpeed();
                break;
            case "down":
                entitySolid.y += entity.getSpeed();
                break;
            case "left":
                entitySolid.x -= entity.getSpeed();
                break;
            case "right":
                entitySolid.x += entity.getSpeed();
                break;
        }
        
        //--> Untuk memberi tanda jika entity tabrakan
        if (check && entitySolid.intersects(playerSolid)){
            entity.setCollisionOn(true);
        }
    }
}
