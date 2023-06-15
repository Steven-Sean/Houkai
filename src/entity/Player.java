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
public final class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    //--> Untuk menempatkan karakter pemain ditengah layar dan mengikuti latar belakang saat bergerak
    public final int screenX;
    public final int screenY;

    // untuk menunjukkan berapa kunci yg dimiliki pemain saat ini
    int keyCount = 0;

    //Constructor Player
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gamePanel = gp;
        this.keyHandler = keyH;

        //--> Untuk mengembalikan titik tengah layar
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

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
    public void setDefaultValue() {
        setWorldX(520 * 3);
        setWorldY(760 * 3);
        setSpeed(4);
        setDirection("up");
    }

    //--> Untuk dijadikan sprite player saat bergerak
    public void getPlayerImage() {
        try {
            String resourcePath = "/player/nu_";
            for (int i = 0; i < 3; i++) {
                getUp().set(i, ImageIO.read(getClass().getResourceAsStream(resourcePath +"up_0" + (i+1) + ".png")));
                getDown().set( i,ImageIO.read(getClass().getResourceAsStream(resourcePath +"down_0" + (i+1) + ".png")));
                getLeft().set( i,ImageIO.read(getClass().getResourceAsStream(resourcePath +"left_0" + (i+1) + ".png")));
                getRight().set(i, ImageIO.read(getClass().getResourceAsStream(resourcePath +"right_0" + (i+1) + ".png")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        //--> Untuk pengecekan jika key ditekan akan diupdate
        if (keyHandler.upPressed == true || keyHandler.downPressed == true || keyHandler.leftPressed == true || keyHandler.rightPressed) {
            //--> Untuk setiap penekanan key maka akan menambah / mengurang 4 pixel
            if (keyHandler.upPressed == true) {
                setDirection("up");
            } else if (keyHandler.downPressed == true) {
                setDirection("down");
            } else if (keyHandler.leftPressed == true) {
                setDirection("left");
            } else if (keyHandler.rightPressed == true) {
                setDirection("right");
            }

            //--> Untuk memeriksa / mengecek tabrakan pada ubin
            setCollisionOn(false);
            gamePanel.collisionChecker.checkTile(this);

            // untuk memeriksa tabrakan pada objek
            int objIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //--> untuk pengecekan jika Collision == false maka player dapat gerak dan sebaliknya
            if (isCollisionOn() == false) {
                switch (getDirection()) {
                    case "up" -> setWorldY(getWorldY() - getSpeed()); //key W
                    case "down" -> setWorldY(getWorldY() + getSpeed()); //key S
                    case "left" -> setWorldX(getWorldX() - getSpeed()); //key A
                    case "right" -> setWorldX(getWorldX() + getSpeed()); //key D
                }
            }
            setSpriteCounter(getSpriteCounter() + 1);

            //--> Untuk mengatur kecepatan sprite 
            if (getSpriteCounter() > 12) {
                if (getSpriteNum() == 1) {
                    setSpriteNum(2);
                } else if (getSpriteNum() == 2) {
                    setSpriteNum(1);
                }
                setSpriteCounter(0);
            }
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) { // kalau index bukan 999 maka kita telah menyentuh suatu object
            String objectName = gamePanel.getItems().get(i).getName();

            switch (objectName) {
                case "key" -> {
                    //gp.PlaySE(2);
                    keyCount++;
                    gamePanel.getItems().set(i, null);
                    System.out.println("Key:" + keyCount); // untuk mengetahui berapa key skrg
                }
                case "door" -> {
                    if (keyCount > 0) {
                        //gp.PlaySE(2);
                        gamePanel.getItems().set(i, null);
                        keyCount--;
                    }
                    System.out.println("Key:" + keyCount);
                }
                case "boots" -> {
                    gamePanel.PlaySE(1);
                    setSpeed(getSpeed() + 2); // jika dapat sepatu maka kecepatan akan bertambah
                    gamePanel.getItems().set(i, null);
                }

            }
        }
    }

    //--> Untuk mencetak gambar sesuai dengan urutan sprite sesuai dengan key yang kita tekan
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (getDirection()) {
            case "up" -> //key W
                image = getUp().get(getSpriteNum());
            case "down" -> //Key S
                image = getDown().get(getSpriteNum());
            case "left" -> //Key A
                image = getLeft().get(getSpriteNum());
            case "right" -> //Key D
                image = getRight().get(getSpriteNum());
        }

        //--> Untuk mencetak gambar pada frame
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }

}
