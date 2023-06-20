/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package houkai;

import entity.Enemy;
import object.Boots;
import object.Chest;
import object.Door;
import object.Key;

/**
 *
 * @author TOSHIBA
 */
public class AssetSetter {
    GamePanel gp;
    
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
        //===== Keys =====
        gp.item[0] = new Key(gp);
        gp.item[0].worldX = 37 * gp.tileSize;
        gp.item[0].worldY = 46 * gp.tileSize; 
        
        gp.item[1] = new Key(gp);
        gp.item[1].worldX = 36 * gp.tileSize;
        gp.item[1].worldY = 46 * gp.tileSize; 
        
        gp.item[2] = new Key(gp);
        gp.item[2].worldX = 35 * gp.tileSize;
        gp.item[2].worldY = 46 * gp.tileSize; 
        
        gp.item[3] = new Key(gp);
        gp.item[3].worldX = 34 * gp.tileSize;
        gp.item[3].worldY = 46 * gp.tileSize; 
        
        //===== Doors =====
        gp.item[4] = new Door(gp);
        gp.item[4].worldX = 5 * gp.tileSize;
        gp.item[4].worldY = 6 * gp.tileSize;
        
        gp.item[5] = new Door(gp);
        gp.item[5].worldX = 32 * gp.tileSize;
        gp.item[5].worldY = 30 * gp.tileSize;
        
        //===== chest =====
        gp.item[6] = new Chest(gp);
        gp.item[6].worldX = 5 * gp.tileSize;
        gp.item[6].worldY = 3 * gp.tileSize;  
        
        gp.item[7] = new Chest(gp);
        gp.item[7].worldX = 32 * gp.tileSize;
        gp.item[7].worldY = 27 * gp.tileSize;  
    
        //===== Boots =====
        gp.item[8] = new Boots(gp);
        gp.item[8].worldX = 38 * gp.tileSize;
        gp.item[8].worldY = 40 * gp.tileSize;
    }
    

    public void setNPC(){
        gp.npc[0] = new Enemy(gp, 40, 40);
    }
}
