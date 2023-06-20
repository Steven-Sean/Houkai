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

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        //===== Keys =====
        gp.item[0] = new Key(gp, 37, 46);

        gp.item[1] = new Key(gp, 36, 46);

        gp.item[2] = new Key(gp, 35, 46);

        gp.item[3] = new Key(gp, 34, 46);

        //===== Doors =====
        gp.item[4] = new Door(gp, 5, 6);

        gp.item[5] = new Door(gp, 32, 30);

        //===== chest =====
        gp.item[6] = new Chest(gp, 5, 3);

        gp.item[7] = new Chest(gp, 32, 27);

        //===== Boots =====
        gp.item[8] = new Boots(gp, 38, 40);
    }

    public void setNPC() {
        gp.npc[0] = new Enemy(gp, 40, 40);
    }
}
