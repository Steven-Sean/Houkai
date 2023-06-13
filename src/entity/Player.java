/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import houkai.GamePanel;             //--> Mengatur tampilan dan perilaku permainan.
import houkai.KeyHandler;            //--> Mengatur pemrosesan input tombol kunci dalam permainan.
import java.awt.Graphics2D;          //--> Mengatur transformasi dan kualitas gambar.
import java.awt.Rectangle;
import java.awt.image.BufferedImage; //--> Untuk merepresentasikan gambar, dimodifikasi aplikasi Java, membaca, menulis, dan memanipulasi gambar.
import java.io.IOException;          //--> Untuk pengecualian yg dilemparkan ketika terjadi kesalahan atau gangguan dalam operasi input/output.
import javax.imageio.ImageIO;        //--> Untuk membaca gambar dari file atau menulis gambar ke file dalam aplikasi Java.

/**
 *
 * @author AsuS
 */
public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    
    //--> Untuk menempatkan karakter pemain ditengah layar dan mengikuti latar belakang saat bergerak
    public final int screenX;
    public final int screenY;
    
    // untuk menunjukkan berapa kunci yg dimiliki pemain saat ini
    int haskey = 0;

    //Constructor Player
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        
        //--> Untuk mengembalikan titik tengah layar
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        //--> Untuk membuat kotak kecil untuk player
        solidArea = new Rectangle(); 
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        
        // untuk merekam nilai default 
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        setDefaultValue();
        getPlayerImage();
    }
    
    //--> Untuk dijadikan posisi awal player
    public void setDefaultValue(){
        worldX = 520 * 3;
        worldY = 760 * 3;
        speed = 4;
        direction = "up";
    }
    
    //--> Untuk dijadikan sprite player saat bergerak
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/nu_up_01.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/nu_up_02.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/nu_up_03.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/nu_down_01.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/nu_down_02.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/nu_down_03.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/nu_left_01.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/nu_left_02.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/nu_left_03.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/nu_right_01.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/nu_right_02.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/nu_right_03.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void update(){
        //--> Untuk pengecekan jika key ditekan akan diupdate
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed){
            //--> Untuk setiap penekanan key maka akan menambah / mengurang 4 pixel
            if(keyH.upPressed == true){
                direction = "up";
            }else if(keyH.downPressed == true){
                direction = "down";
            }else if(keyH.leftPressed == true){
                direction = "left";
            }else if(keyH.rightPressed == true){
                direction = "right";
            }

            //--> Untuk memeriksa / mengecek tabrakan pada ubin
            collisionOn = false;
            gp.cChecker.checkTile(this);
            
            // untuk memeriksa tabrakan pada objek
            int objIndex = gp.cChecker.checkObject(this,true);
            pickUpObject(objIndex);
            
            //--> untuk pengecekan jika Collision == false maka player dapat gerak dan sebaliknya
            if(collisionOn == false){
                switch (direction) {
                case "up":
                    worldY -= speed; //key W
                    break;
                case "down":
                    worldY += speed; //key S
                    break;
                case "left":
                    worldX -= speed; //key A
                    break;
                case "right":
                    worldX += speed; //key D
                    break;
                }
            }
            spriteCounter++;
            
            //--> Untuk mengatur kecepatan sprite 
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    
    public void pickUpObject (int i){
        if (i!=999){ // kalau index bukan 999 maka kita telah menyentuh suatu object
            String objectName = gp.obj[i].name;
            
            switch (objectName){
                case "key":
                    //gp.PlaySE(2);
                    haskey ++;
                    gp.obj[i] = null ;
                    System.out.println("Key:" + haskey); // untuk mengetahui berapa key skrg
                    break;
                case "door":
                    if (haskey>0){
                        //gp.PlaySE(2);
                        gp.obj[i] = null ;
                        haskey --;
                    }
                    System.out.println("Key:" + haskey); 
                    break;
                case "boots":
                    gp.PlaySE(1);
                    speed = speed + 2; // jika dapat sepatu maka kecepatan akan bertambah
                    gp.obj[i] = null;
                    break;
            }
        }
    }
    
    //--> Untuk mencetak gambar sesuai dengan urutan sprite sesuai dengan key yang kita tekan
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        
        switch(direction){
        case "up": //key W
            if(spriteNum == 1){
                image = up1;
            }
            if(spriteNum == 2){
                image = up2;
            }
            if(spriteNum == 3){
                image = up3;
            }
            break;
        case "down": //Key S
            if(spriteNum == 1){
                image = down1;
            }
            if(spriteNum == 2){
                image = down2;
            }
            if(spriteNum == 3){
                image = down3;
            }
            break;
        case "left": //Key A
            if(spriteNum == 1){
                image = left1;
            }
            if(spriteNum == 2){
                image = left2;
            }
            if(spriteNum == 3){
                image = left3;
            }
            break;
        case "right": //Key D
            if(spriteNum == 1){
                image = right1;
            }
            if(spriteNum == 2){
                image = right2;
            }
            if(spriteNum == 3){
                image = right3;
            }
            break;
        }
        
        //--> Untuk mencetak gambar pada frame
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
    
}
