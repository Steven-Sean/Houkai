/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package houkai;

import entity.Entity;

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
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

                //--> Untuk pengecekan jika ada salah 1 atau keduanya benar, pemain menabrak petak padat sehingga tidak dapat bergerak ke arah ini
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.setCollisionOn(true);
                }
                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX + entity.getSpeed()) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX - entity.getSpeed()) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.setCollisionOn(true);
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {

                // untuk mendapatkan entity petaknya padat
                entity.solidArea.x = entity.getWorldX() + entity.getSolidArea().x;
                entity.solidArea.y = entity.getWorldY() + entity.getSolidArea().y;

                // untuk mendapatkan object petaknya padat
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.getDirection()) { // untuk menentukan arah entity / entitas
                    case "up":
                        entity.solidArea.y = entity.getSolidArea().y - entity.getSpeed();
                        if (entity.getSolidArea().intersects(gp.obj[i].solidArea)) { // untuk memberi tanda jika entity tabrakan
                            if (gp.obj[i].coliision == true) {
                                entity.setCollisionOn(true);
                            }
                            if (player == true) {
                                index = 1;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y = entity.getSolidArea().y + entity.getSpeed();
                        if (entity.getSolidArea().intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].coliision == true) {
                                entity.setCollisionOn(true);
                            }
                            if (player == true) {
                                index = 1;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x = entity.getSolidArea().x - entity.getSpeed();
                        if (entity.getSolidArea().intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].coliision == true) {
                                entity.setCollisionOn(true);
                            }
                            if (player == true) {
                                index = 1;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x = entity.getSolidArea().x + entity.getSpeed();
                        if (entity.getSolidArea().intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].coliision == true) {
                                entity.setCollisionOn(true);
                            }
                            if (player == true) {
                                index = 1;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.getSolidAreaDefaultX();
                entity.solidArea.y = entity.getSolidAreaDefaultY();
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
