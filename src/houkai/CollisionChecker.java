/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package houkai;

import entity.Entity;
import items.Items;

/**
 *
 * @author AsuS
 */
public class CollisionChecker {

    GamePanel gp;

    //--> Constructor untuk memeriksa tabrakan
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    //--> Untuk pengecekan ubin
    public void checkTile(Entity entity) {
        //--> Untuk pengecekan berdasarkan koordinat Solid Area (daerah yg padat player)
        int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x; //kiriX = 8
        int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width; //kananX = 16
        int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y; //atasY = 32
        int entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height; //bawahY = 32

        //--> Untuk mencari Nomor Col dan Row didapatkan dari koordinat diatas 
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        //--> Untuk memeriksa arah entity mau kemana
        switch (entity.getDirection()) {
            case "up" -> {
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

                //--> Untuk pengecekan jika ada salah 1 atau keduanya benar, pemain menabrak petak padat sehingga tidak dapat bergerak ke arah ini
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.setCollisionOn(true);
                }
            }

            case "down" -> {
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.setCollisionOn(true);
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX + entity.getSpeed()) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.setCollisionOn(true);
                }
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX - entity.getSpeed()) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.setCollisionOn(true);
                }
            }
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (Items obj : gp.obj) {
            if (obj != null) {
                // untuk mendapatkan entity petaknya padat
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
                // untuk mendapatkan object petaknya padat
                obj.solidArea.x = obj.worldX + obj.solidArea.x;
                obj.solidArea.y = obj.worldY + obj.solidArea.y;
                switch (entity.getDirection()) {
                    case "up" -> {
                        entity.getSolidArea().y = entity.getSolidArea().y - entity.getSpeed();
                        if (entity.getSolidArea().intersects(obj.solidArea)) {
                            // untuk memberi tanda jika entity tabrakan
                            if (obj.coliision == true) {
                                entity.setCollisionOn(true);
                            }
                            if (player == true) {
                                index = 1;
                            }
                        }
                    }
                    case "down" -> {
                        entity.getSolidArea().y = entity.getSolidArea().y + entity.getSpeed();
                        if (entity.getSolidArea().intersects(obj.solidArea)) {
                            if (obj.coliision == true) {
                                entity.setCollisionOn(true);
                            }
                            if (player == true) {
                                index = 1;
                            }
                        }
                    }
                    case "left" -> {
                        entity.getSolidArea().x = entity.getSolidArea().x - entity.getSpeed();
                        if (entity.getSolidArea().intersects(obj.solidArea)) {
                            if (obj.coliision == true) {
                                entity.setCollisionOn(true);
                            }
                            if (player == true) {
                                index = 1;
                            }
                        }
                    }
                    case "right" -> {
                        entity.getSolidArea().x = entity.getSolidArea().x + entity.getSpeed();
                        if (entity.getSolidArea().intersects(obj.solidArea)) {
                            if (obj.coliision == true) {
                                entity.setCollisionOn(true);
                            }
                            if (player == true) {
                                index = 1;
                            }
                        }
                    }
                }
                // untuk menentukan arah entity / entitas
                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                obj.solidArea.x = obj.solidAreaDefaultX;
                obj.solidArea.y = obj.solidAreaDefaultY;
            }
        }
        return index;
    }
}
