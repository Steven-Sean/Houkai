/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package houkai.tile;

import houkai.GamePanel;          //--> Mengatur tampilan dan perilaku permainan.
import java.awt.Graphics2D;       //--> Mengatur transformasi dan kualitas gambar.
import java.io.BufferedReader;    //--> Untuk membaca data dari aliran input dengan buffering, yg memungkinkan pembacaan data lebih efisien.
import java.io.IOException;       //--> Untuk pengecualian yg dilemparkan ketika terjadi kesalahan atau gangguan dalam operasi input/output.
import java.io.InputStream;       //--> Untuk kelas abstrak yang merupakan dasar untuk semua aliran input byte.
import java.io.InputStreamReader; //--> Untuk membaca aliran byte dan menerjemahkannya menjadi karakter menggunakan kode karakter yg ditentukan.
import javax.imageio.ImageIO;     //--> Untuk membaca gambar dari file atau menulis gambar ke file dalam aplikasi Java.

/**
 *
 * @author AsuS
 */

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    
    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; 
        getTileImage();
        loadMap("/maps/worldMap.txt");
    }
    
    //--> Untuk Mengambil gambar
    public void getTileImage(){
        try{
           //===== Road =====
           tile[0] = new Tile();
           tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road_1.png"));
           
           tile[1] = new Tile();
           tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road_2.png"));
           tile[1].collision = true; //--> Untuk petak yg tidak bisa tabrak atau di tembus
           
           tile[2] = new Tile();
           tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road_3.png"));
           tile[2].collision = true; 
        
           // ===== Corner Wall =====
           tile[3] = new Tile();
           tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/corner_left_down.png"));
           tile[3].collision = true;
           
           tile[4] = new Tile();
           tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/corner_right_down.png"));
           tile[4].collision = true;
           
           tile[5] = new Tile();
           tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/corner_left_top.png"));
           tile[5].collision = true;
           
           tile[6] = new Tile();
           tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/corner_right_top.png"));
           tile[6].collision = true;
           
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); 
            
            int col = 0;
            int row = 0;
            
            //Untuk pengecekan agar tidak ada data diluar batas map.txt
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine(); // --> Untuk membaca data 1 baris saja dalam bentuk String 
                
                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" "); //--> Untuk memisahkan line menjadi 1/1 dan dimasukkan ke array
                    int num = Integer.parseInt(numbers[col]); //--> Untuk mengubah String ke Int
                    mapTileNum[col][row] = num; //--> index = col & row
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){
            
        }
    }
    
//--> Untuk menggambar map     
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;
        
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            int tileNum = mapTileNum[worldCol][ worldRow]; //--> Untuk mengubah num menjadi map dan akan dimasukkan ke array mapTileNum
            
            //--> Untuk player agar berada pada posisi tengah dan menampilkan map sesuai ukuran screen 
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY ;
            
            //--> berfungsi untuk menggambar tiles disekitar player saja jadi tidak melebihi screen
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                
                //--> Untuk Mencetak Gambar Map 
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;
            
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
