/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import houkai.GamePanel;
import java.util.Random;

/**
 *
 * @author AsuS
 */
public final class Enemy extends Entity{
    public Enemy(GamePanel gp){
        super(gp);
        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
    }
    
    //--> Untuk dijadikan sprite player saat bergerak
    public void getImage(){
        for (int i = 0; i < 3; i++) {
            up[i] = setup("/npc/wb_up_" + (i+1));
            down[i] = setup("/npc/wb_down_" + (i+1));
            left[i] = setup("/npc/wb_left_" + (i+1));
            right[i] = setup("/npc/wb_right_" + (i+1));
        }
    }
    
    //--> Untuk edit tulisan yang ingin ditampilkan 
    public void setDialogue(){
        dialogues[0] = "Hello World";
        dialogues[1] = "aku ngantuk bre";
        dialogues[2] = "paling enak bobok di kasur empuk";
        dialogues[3] = "hehe";
    }
    
    //--> Untuk mengatur pergerakan npc secara acak
    public void setAction(){
        actionLockCounter++;
        
        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if(i<=25){
                direction = "up";
            }
            if(i>25 && i<= 50){
                direction = "down";
            }
            if(i>50 && i<= 75){
                direction = "left";
            }
            if(i>75 && i<= 100){
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
    public void speak(){
        //--> Untuk melakukan hal khusus karakter
        super.speak();
    }
}
