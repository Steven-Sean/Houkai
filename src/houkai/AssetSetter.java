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

    GamePanel gamePanel;

    public AssetSetter(GamePanel gp) {
        this.gamePanel = gp;
    }

    public void setItems() {

        // kalau array diatur 10 maka item yg bisa ditampilkan bisa sampai 10, kalau terlalu banyak membuat game lambat
//        gamePanel.items[0] = new Key();
//        gamePanel.items[0].worldX = 520 * 3;
//        gamePanel.items[0].worldY = 720 * 3; // item ke 1
        
        gamePanel.items.set(1, new Key(595 * 3, 735 * 3)); // item ke 2

//        gamePanel.items[2] = new Door();
//        gamePanel.items[2].worldX = 43 * gamePanel.tileSize;
//        gamePanel.items[2].worldY = 9 * gamePanel.tileSize; // item ke 3
//        
//        gamePanel.items[3] = new Door();
//        gamePanel.items[3].worldX = 2 * gamePanel.tileSize;
//        gamePanel.items[3].worldY = 22 * gamePanel.tileSize; // item ke 4
//        
//        gamePanel.items[4] = new Chest();
//        gamePanel.items[4].worldX = 33 * gamePanel.tileSize;
//        gamePanel.items[4].worldY = 9 * gamePanel.tileSize; // item ke 5
//        
//        gamePanel.items[5] = new Chest();
//        gamePanel.items[5].worldX = 28 * gamePanel.tileSize;
//        gamePanel.items[5].worldY = 4 * gamePanel.tileSize; // item ke 6    
    }
}
