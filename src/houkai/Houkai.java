/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package houkai;

import javax.swing.JFrame;

/**
 *
 * @author Steven
 */
public class Houkai { 
    public static void main(String[] args) {
        JFrame window = new JFrame();
    
        //--> Untuk memungkinkan jendela menutup dengan benar ketika pengguna mengklik tombol tutup ("x").
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false); //--> Untuk menetapkan frame agar ukuran framenya tidak dapat diubah
        window.setTitle("Houkai"); //--> judul game
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        
        window.setLocationRelativeTo(null); //--> untuk menampilkan Jendela yg akan ditampilkan di tengah layar
        window.setVisible(true);
    
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
