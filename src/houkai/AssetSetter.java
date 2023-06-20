/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package houkai;

import entity.npc_1;
import object.OBJ_boots;
import object.OBJ_chest;
import object.OBJ_door;
import object.OBJ_key;

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
        gp.obj[0] = new OBJ_key(gp);
        gp.obj[0].worldX = 37 * gp.tileSize;
        gp.obj[0].worldY = 46 * gp.tileSize; 
        
        gp.obj[1] = new OBJ_key(gp);
        gp.obj[1].worldX = 36 * gp.tileSize;
        gp.obj[1].worldY = 46 * gp.tileSize; 
        
        gp.obj[2] = new OBJ_key(gp);
        gp.obj[2].worldX = 35 * gp.tileSize;
        gp.obj[2].worldY = 46 * gp.tileSize; 
        
        gp.obj[3] = new OBJ_key(gp);
        gp.obj[3].worldX = 34 * gp.tileSize;
        gp.obj[3].worldY = 46 * gp.tileSize; 
        
        //===== Doors =====
        gp.obj[4] = new OBJ_door(gp);
        gp.obj[4].worldX = 5 * gp.tileSize;
        gp.obj[4].worldY = 6 * gp.tileSize;
        
        gp.obj[5] = new OBJ_door(gp);
        gp.obj[5].worldX = 32 * gp.tileSize;
        gp.obj[5].worldY = 30 * gp.tileSize;
        
        //===== chest =====
        gp.obj[6] = new OBJ_chest(gp);
        gp.obj[6].worldX = 5 * gp.tileSize;
        gp.obj[6].worldY = 3 * gp.tileSize;  
        
        gp.obj[7] = new OBJ_chest(gp);
        gp.obj[7].worldX = 32 * gp.tileSize;
        gp.obj[7].worldY = 27 * gp.tileSize;  
    
        //===== Boots =====
        gp.obj[8] = new OBJ_boots(gp);
        gp.obj[8].worldX = 38 * gp.tileSize;
        gp.obj[8].worldY = 40 * gp.tileSize;
    }
    

    public void setNPC(){
        gp.npc[0] = new npc_1(gp);
        gp.npc[0].worldX = 40 * gp.tileSize;
        gp.npc[0].worldY = 40 * gp.tileSize;
        
        
    }
}
