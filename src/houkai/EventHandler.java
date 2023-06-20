/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package houkai;

import java.awt.Rectangle;

/**
 *
 * @author TOSHIBA
 */
public class EventHandler {

    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public void EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent() {

        //if(hit(27, 16, "right") == true){damagePit(gp.dialogueState);} 
        //if(hit(23, 12, "up") == true){healingPool(gp.dialogueState);}
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;

        Rectangle playerSolid = (Rectangle) gp.player.getSolidArea().clone();

        playerSolid.x = gp.player.getWorldX() + playerSolid.x;
        playerSolid.y = gp.player.getWorldY() + playerSolid.y;
        eventRect.x = eventCol * gp.tileSize + eventRect.x;
        eventRect.y = eventRow * gp.tileSize + eventRect.y;

        if (playerSolid.intersects(eventRect)) {
            if (gp.player.getDirection().contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }

        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }

    public void damagePit(int gameState) {
        gp.gameState = gameState;
        gp.houkaiUI.currentDialogue = "You fall into a pit!";
        gp.player.setLife(gp.player.getLife() - 1);
    }

    public void healingPool(int gameState) {
        if (gp.keyH.enterPressed == true) {
            gp.gameState = gameState;
            gp.houkaiUI.currentDialogue = " You Drink The Water. \nYour life has been recorvered.";
            gp.player.setLife(gp.player.getMaxLife());
        }

    }
}
