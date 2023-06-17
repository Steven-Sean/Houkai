/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package houkai;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import object.OBJ_key;

/**
 *
 * @author AsuS
 */
public class UI {
    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    
    public UI(GamePanel gp){
        this.gp = gp;
        //--> Font 
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.PLAIN, 80);
        OBJ_key key = new OBJ_key();
        keyImage = key.image;
    }
    
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    
    //--> Untuk menggambar texs dengan graphics@D
    public void draw(Graphics2D g2){
        if(gameFinished == true){
            //--> Jangan membuat instance class disini
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            
            String text;
            int textLenght;
            int x;
            int y;
            
            text = "kamu mendapatkan harta karun!";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            //--> Untuk text agar sejajar di tengah 
            x = gp.screenWidth/2 - textLenght/2; 
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);
            
            text = "Waktu kamu adalah: " + dFormat.format(playTime) + "!";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            //--> Untuk text agar sejajar di tengah 
            x = gp.screenWidth/2 - textLenght/2; 
            y = gp.screenHeight/2 + (gp.tileSize*4);
            g2.drawString(text, x, y);
            
            //--> format font dan color
            g2.setFont(arial_80B);
            g2.setColor(Color.YELLOW);
            text = "SELAMAT!";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            //--> Untuk text agar sejajar di tengah 
            x = gp.screenWidth/2 - textLenght/2; 
            y = gp.screenHeight/2 + (gp.tileSize*2);
            g2.drawString(text, x, y);
            gp.gameThread = null;
            
        }else{
            //--> Jangan membuat instance class disini
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            //g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, null);
            g2.drawString("x " + gp.player.haskey, 74, 65); //--> x adalah 24+48 jadi 72

            //--> Untuk menghitung waktu
            playTime += (double)1/60;
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*11,65);
            
            //--> Untuk menampilkan pesan
            if(messageOn == true){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
                messageCounter++;

                if(messageCounter > 120){
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
