/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package houkai;

import items.Chest; // ini untuk item chest
import items.Door; // ini untuk item door
import items.Key; // ini untuk item key

/**
 *
 * @author TOSHIBA
 */
public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        // kalau array diatur 10 maka item yg bisa ditampilkan bisa sampai 10, kalau terlalu banyak membuat game lambat
//        gp.obj[0] = new OBJ_key();
//        gp.obj[0].worldX = 520 * 3;
//        gp.obj[0].worldY = 720 * 3; // item ke 1
//        
        gp.obj[1] = new Key();
        gp.obj[1].worldX = 595 * 3;
        gp.obj[1].worldY = 735 * 3; // item ke 2

//        gp.obj[2] = new OBJ_door();
//        gp.obj[2].worldX = 43 * gp.tileSize;
//        gp.obj[2].worldY = 9 * gp.tileSize; // item ke 3
//        
//        gp.obj[3] = new OBJ_door();
//        gp.obj[3].worldX = 2 * gp.tileSize;
//        gp.obj[3].worldY = 22 * gp.tileSize; // item ke 4
//        
//        gp.obj[4] = new OBJ_chest();
//        gp.obj[4].worldX = 33 * gp.tileSize;
//        gp.obj[4].worldY = 9 * gp.tileSize; // item ke 5
//        
//        gp.obj[5] = new OBJ_chest();
//        gp.obj[5].worldX = 28 * gp.tileSize;
//        gp.obj[5].worldY = 4 * gp.tileSize; // item ke 6    
    }
}
