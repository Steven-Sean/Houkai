/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package houkai;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import object.Heart;
import object.Key;
import object.Item;

/**
 *
 * @author AsuS
 */
public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        //--> Font 
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.PLAIN, 80);
        Key key = new Key(gp, 0, 0);
        key.getFirstImage();

        //--> Untuk create HUB object
        Item heart = new Heart(gp, 0, 0);
        heart_full = heart.getImage()[0];
        heart_half = heart.getImage()[1];
        heart_blank = heart.getImage()[2];
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    //--> Untuk menggambar texs dengan graphics@D
    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);
        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        //--> Untuk play state
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
        }
        //--> Untuk pause game
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        //--> Untuk dialogue state
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }

        if (gameFinished == true) {
            //--> Jangan membuat instance class disini
            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLenght;
            int x;
            int y;

            text = "kamu mendapatkan harta karun!";
            textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            //--> Untuk text agar sejajar di tengah 
            x = gp.screenWidth / 2 - textLenght / 2;
            y = gp.screenHeight / 2 - (gp.tileSize * 3);
            g2.drawString(text, x, y);

            text = "Waktu kamu adalah: " + dFormat.format(playTime) + "!";
            textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            //--> Untuk text agar sejajar di tengah 
            x = gp.screenWidth / 2 - textLenght / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 4);
            g2.drawString(text, x, y);

            //--> format font dan color
            g2.setFont(arial_80B);
            g2.setColor(Color.YELLOW);
            text = "SELAMAT!";
            textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            //--> Untuk text agar sejajar di tengah 
            x = gp.screenWidth / 2 - textLenght / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 2);
            g2.drawString(text, x, y);
            gp.gameThread = null;

        } else {
            //--> Jangan membuat instance class disini
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            //g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, null);
            g2.drawString("Key: " + gp.player.keyCount, 30, 100); //--> x adalah 24+48 jadi 72

            //--> Untuk menghitung waktu
            playTime += (double) 1 / 60;
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 11, 65);

            //--> Untuk menampilkan pesan
            if (messageOn == true) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);
                messageCounter++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }

    public void drawPlayerLife() {
        //gp.player.life = 5;
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        //draw Max life
        while (i < gp.player.getMaxLife() / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        //Reset
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        //Draw current life
        while (i < gp.player.getLife()) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.getLife()) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawTitleScreen() {
        if (titleScreenState == 0) {
            g2.setColor(new Color(6, 1, 31));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            //  TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "Dragon Honkai";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;

            // SHADOW
            g2.setColor(Color.gray);
            g2.drawString(text, x + 5, y + 5);

            // MAIN utama
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // DRAGON HONKAI IMAGE 
            x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
            y += gp.tileSize * 3;
            g2.drawImage(gp.player.getImageByDirection("down"), x, y, gp.tileSize * 2, gp.tileSize * 2, null);

            // MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28));

            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize * 4;
            g2.drawString(text, x, y);

            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "QUIT";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        } else if (titleScreenState == 1) {
            //  CLASS SELECTION STRING
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "Select Your Mode!";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Easy";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Normal";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Hard";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Back";
            x = getXforCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }
    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);  // ini untuk teks yang terpusat
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
        //Window
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        //--> Untuk mengatur posisi dialog screen 
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        //--> Untuk mengatur kecerahan window
        Color c = new Color(0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(225, 225, 225);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
