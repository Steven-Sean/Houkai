/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package houkai.tile;

import houkai.GamePanel;          //--> Mengatur tampilan dan perilaku permainan.
import houkai.UtilityTool;
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
        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; 
        getTileImage();
        loadMap("/maps/worldMap.txt");
    }
    
    //--> Untuk Mengambil gambar
    public void getTileImage(){
        // ===== Road =====
        setup(0,"road_1",false); //--> Untuk petak yg true bisa di tembus
        setup(1,"road_2",true);  //--> Untuk petak yg false tidak bisa di tembus
        setup(2,"road_3",true); 
            
        // ===== Base Map =====
        setup(3,"map",true); 
            
        // ===== Wall =====
        setup(4,"wall_top",true); //top
        setup(5,"wall_down",true); //down
        setup(6,"wall_left",true); //left
        setup(7,"wall_right",true); //right

        // ===== Corner Wall (1) =====
        setup(8,"corner_left_down",true); //left_down
        setup(9,"corner_right_down",true); //right_down
        setup(10,"corner_left_top",true); //left_top
        setup(11,"corner_right_top",true); //right_top

        // ===== Corner Wall (2) =====
        setup(12,"c_down_left",true); //left_down
        setup(13,"c_down_right",true); //right_down
        setup(14,"c_top_left",true); //left_down
        setup(15,"c_top_right",true); //right_top

        // ===== Pillar =====
        setup(16,"pillar_top",true); //top
        setup(17,"pillar_down",true); //down 
        setup(18,"pillar_left",true); //left
        setup(19,"pillar_right",true); //right
            
        // ===== Underground (1) =====
        setup(20,"w_top",true); //top
        setup(21,"w_left",true); //left
        setup(22,"w_right",true); //right
            
        // ===== Underground (2) =====
        setup(23,"w_top_left_corner",true); //top_left
        setup(24,"w_top_right_corner",true); //top_right
        setup(25,"w_down_left_corner",true); //down_left
        setup(26,"w_down_right_corner",true); //down_right
            
        // ===== Underground (3) =====
        setup(27,"w_mid_left",true); //left 
        setup(28,"w_mid_right",true); //right
        setup(29,"w_mid_left-2",true); //left_2
        setup(30,"w_mid_right_2",true); //right_2
            
        // ===== Ladder =====
        setup(31,"ladder",false);
            
        // ===== Stone =====
        setup(32,"stone",true); 
            
        // ===== Lamp =====
        setup(33,"lamp_1",true);
        setup(34,"lamp_2",true);
        setup(35,"lamp_3",true);
        setup(36,"lamp_4",true);
            
        // ===== chest =====
        setup(37,"chest_1",true);
           
    }
    
    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool ();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
            
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
            
            //--> Untuk pengecekan agar tidak ada data diluar batas map.txt
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
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            worldCol++;
            
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
