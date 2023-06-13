/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package houkai;

import entity.Player;
import houkai.tile.TileManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author AsuS
 */

public class GamePanel extends JPanel implements Runnable{
    //--> Untuk screen setting
    final int originalTileSize = 16; //16x16
    final int scale = 3;
    
    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 26;
    public final int maxScreenRow = 15;
    public final int screenWidth = tileSize * maxScreenCol; // 1248 pixels 
    public final int screenHeight = tileSize * maxScreenRow; // 720 pixels 

    //--> Untuk setting world 
    public final int maxWorldCol = 50; 
    public final int maxWorldRow = 50; 
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
    //--> Untuk setting FPS
    int FPS = 60;
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this,keyH);
       
    
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground( new Color(6, 1, 31) ); //--> Untuk background diluar tile
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH); //--> Untuk mengenali inputan
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        //--> Untuk game loop
        double drawInterval = 1000000000/FPS; //--> Untuk 0.01666 seconds dapat menggambar 60 layar
        double delta = 0;
        long lastTime = System.nanoTime();
        long currenTime;
        long timer = 0;
        long drawCount = 0;
        
        while(gameThread != null){
            currenTime = System.nanoTime(); //--> Untuk 1 miliar nanodetik sama dengan 1 detik
            delta += (currenTime - lastTime) / drawInterval;
            timer += (currenTime - lastTime);
            lastTime = currenTime;
            
            if(delta >= 1){
                // 1. Update: memperbarui informasi seperti posisi karakter
                update();
                // 2. Draw: menggambar layar dengan informasi terbaru
                repaint();
                delta--;
                drawCount++;
            }
            //--> Untuk pengecekan FPS
            if(timer <= 1000000000){
                System.out.println("FPS: " + drawCount);
            }
        }
    }
    
    //--> Untuk update player
    public void update(){
        player.update();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //--> Untuk mengonversi Graphics ke class graphics 2D
        //--> Untuk fungsi Graphics2D class extends Graphics class untuk memberikan kontrol yang lebih canggih atas
        //    geometri, transformasi koordinat, manajemen warna, dan tata letak teks.
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2); //pastikan kita draw tile dulu lalu player
        player.draw(g2);
        g2.dispose();
    }
}
