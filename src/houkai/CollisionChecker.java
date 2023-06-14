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

    GamePanel gamePanel;

    //--> Constructor untuk memeriksa tabrakan
    public CollisionChecker(GamePanel gp) {
        this.gamePanel = gp;
    }

    //--> Untuk pengecekan ubin
    public void checkTile(Entity entity) {
        //--> Untuk pengecekan berdasarkan koordinat Solid Area (daerah yg padat player)
        int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x; //kiriX = 8
        int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width; //kananX = 16
        int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y; //atasY = 32
        int entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height; //bawahY = 32

        //--> Untuk mencari Nomor Col dan Row didapatkan dari koordinat diatas 
        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;

        //--> Untuk memeriksa arah entity mau kemana
        switch (entity.getDirection()) {
            case "up" -> {
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];

                //--> Untuk pengecekan jika ada salah 1 atau keduanya benar, pemain menabrak petak padat sehingga tidak dapat bergerak ke arah ini
                if (gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true) {
                    entity.setCollisionOn(true);
                }
            }

            case "down" -> {
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true) {
                    entity.setCollisionOn(true);
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX + entity.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true) {
                    entity.setCollisionOn(true);
                }
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX - entity.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true) {
                    entity.setCollisionOn(true);
                }
            }

        }
    }

    public int checkObject(Entity entity, boolean isPlayer) {
        int index = 999;

        for (Items item : gamePanel.items) {
            if (item != null) {
                // untuk mendapatkan entity petaknya padat
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
                // untuk mendapatkan object petaknya padat
                item.getSolidArea().x = item.getWorldX() + item.getSolidArea().x;
                item.getSolidArea().y = item.getWorldY() + item.getSolidArea().y;
                switch (entity.getDirection()) {
                    case "up" -> {
                        entity.getSolidArea().y = entity.getSolidArea().y - entity.getSpeed();
                        if (entity.getSolidArea().intersects(item.getSolidArea())) {
                            // untuk memberi tanda jika entity tabrakan
                            if (item.isCollision() == true) {
                                entity.setCollisionOn(true);
                            }
                            if (isPlayer == true) {
                                index = 1;
                            }
                        }
                    }
                    case "down" -> {
                        entity.getSolidArea().y = entity.getSolidArea().y + entity.getSpeed();
                        if (entity.getSolidArea().intersects(item.getSolidArea())) {
                            if (item.isCollision() == true) {
                                entity.setCollisionOn(true);
                            }
                            if (isPlayer == true) {
                                index = 1;
                            }
                        }
                    }
                    case "left" -> {
                        entity.getSolidArea().x = entity.getSolidArea().x - entity.getSpeed();
                        if (entity.getSolidArea().intersects(item.getSolidArea())) {
                            if (item.isCollision() == true) {
                                entity.setCollisionOn(true);
                            }
                            if (isPlayer == true) {
                                index = 1;
                            }
                        }
                    }
                    case "right" -> {
                        entity.getSolidArea().x = entity.getSolidArea().x + entity.getSpeed();
                        if (entity.getSolidArea().intersects(item.getSolidArea())) {
                            if (item.isCollision() == true) {
                                entity.setCollisionOn(true);
                            }
                            if (isPlayer == true) {
                                index = 1;
                            }
                        }
                    }
                }
                // untuk menentukan arah entity / entitas
                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                item.getSolidArea().x = item.getSolidAreaDefaultX();
                item.getSolidArea().y = item.getSolidAreaDefaultY();
            }
        }
        return index;
    }
}
