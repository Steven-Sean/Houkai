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
public final class TileManager {

    GamePanel gamePanel;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gamePanel = gp;
        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/worldMap.txt");
    }

    //--> Untuk Mengambil gambar
    public void getTileImage() {
        try {
            // ===== Road =====
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road_1.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road_2.png"));
            tile[1].collision = true; //--> Untuk petak yg tidak bisa tabrak atau di tembus

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road_3.png"));
            tile[2].collision = true;

            // ===== Base Map =====
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/map.png"));
            tile[3].collision = true; //--> Untuk petak yg tidak bisa tabrak atau di tembus

            // ===== Wall =====
            tile[4] = new Tile(); //top
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_top.png"));
            tile[4].collision = true;

            tile[5] = new Tile(); //down
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_down.png"));
            tile[5].collision = true;

            tile[6] = new Tile(); //left
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_left.png"));
            tile[6].collision = true;

            tile[7] = new Tile(); //right
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_right.png"));
            tile[7].collision = true;

            // ===== Corner Wall (1) =====
            tile[8] = new Tile(); //left_down
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/corner_left_down.png"));
            tile[8].collision = true;

            tile[9] = new Tile(); //right_down
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/corner_right_down.png"));
            tile[9].collision = true;

            tile[10] = new Tile(); //left_top
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/corner_left_top.png"));
            tile[10].collision = true;

            tile[11] = new Tile(); //right_top
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/corner_right_top.png"));
            tile[11].collision = true;

            // ===== Corner Wall (2) =====
            tile[12] = new Tile(); //left_down
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/c_down_left.png"));
            tile[12].collision = true;

            tile[13] = new Tile(); //right_down
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/c_down_right.png"));
            tile[13].collision = true;

            tile[14] = new Tile(); //left_top
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/c_top_left.png"));
            tile[14].collision = true;

            tile[15] = new Tile(); //right_top
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/c_top_right.png"));
            tile[15].collision = true;

            // ===== Pillar =====
            tile[16] = new Tile(); //top
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/pillar_top.png"));
            tile[16].collision = true;

            tile[17] = new Tile(); //down
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/pillar_down.png"));
            tile[17].collision = true;

            tile[18] = new Tile(); //left
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/tiles/pillar_left.png"));
            tile[18].collision = true;

            tile[19] = new Tile(); //right
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("/tiles/pillar_right.png"));
            tile[19].collision = true;
            
            // ===== Underground (1) =====
            tile[20] = new Tile(); //top
            tile[20].image = ImageIO.read(getClass().getResourceAsStream("/tiles/w_top.png"));
            tile[20].collision = true;
            
            tile[21] = new Tile(); //left
            tile[21].image = ImageIO.read(getClass().getResourceAsStream("/tiles/w_left.png"));
            tile[21].collision = true;
            
            tile[22] = new Tile(); //right
            tile[22].image = ImageIO.read(getClass().getResourceAsStream("/tiles/w_right.png"));
            tile[22].collision = true;
            
            // ===== Underground (2) =====
            tile[23] = new Tile(); //top leff
            tile[23].image = ImageIO.read(getClass().getResourceAsStream("/tiles/w_top_left_corner.png"));
            tile[23].collision = true;
            
            tile[24] = new Tile(); //top right
            tile[24].image = ImageIO.read(getClass().getResourceAsStream("/tiles/w_top_right_corner.png"));
            tile[24].collision = true;
            
            tile[25] = new Tile(); //down left
            tile[25].image = ImageIO.read(getClass().getResourceAsStream("/tiles/w_down_left_corner.png"));
            tile[25].collision = true;
            
            tile[26] = new Tile(); //down right
            tile[26].image = ImageIO.read(getClass().getResourceAsStream("/tiles/w_down_right_corner.png"));
            tile[26].collision = true;
            
            // ===== Underground (3) =====
            
            tile[27] = new Tile(); //left
            tile[27].image = ImageIO.read(getClass().getResourceAsStream("/tiles/w_mid_left.png"));
            tile[27].collision = true;
            
            tile[28] = new Tile(); //right
            tile[28].image = ImageIO.read(getClass().getResourceAsStream("/tiles/w_mid_right.png"));
            tile[28].collision = true;
            
            tile[29] = new Tile(); //left 2
            tile[29].image = ImageIO.read(getClass().getResourceAsStream("/tiles/w_mid_left-2.png"));
            tile[29].collision = true;
            
            tile[30] = new Tile(); //right 2
            tile[30].image = ImageIO.read(getClass().getResourceAsStream("/tiles/w_mid_right_2.png"));
            tile[30].collision = true;
            
            // ===== Ladder =====
            tile[31] = new Tile(); 
            tile[31].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ladder.png"));
            
            // ===== Stone =====
            tile[32] = new Tile(); //right 2
            tile[32].image = ImageIO.read(getClass().getResourceAsStream("/tiles/stone.png"));
            tile[32].collision = true;
            
            // ===== Lamp =====
            tile[33] = new Tile(); 
            tile[33].image = ImageIO.read(getClass().getResourceAsStream("/tiles/lamp_1.png"));
            tile[33].collision = true;
            
            tile[34] = new Tile(); 
            tile[34].image = ImageIO.read(getClass().getResourceAsStream("/tiles/lamp_2.png"));
            tile[34].collision = true;
            
            tile[35] = new Tile(); 
            tile[35].image = ImageIO.read(getClass().getResourceAsStream("/tiles/lamp_3.png"));
            tile[35].collision = true;
            
            tile[36] = new Tile(); 
            tile[36].image = ImageIO.read(getClass().getResourceAsStream("/tiles/lamp_4.png"));
            tile[36].collision = true;
            
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                int col = 0;
                int row = 0;
                
                //--> Untuk pengecekan agar tidak ada data diluar batas map.txt
                while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
                    String line = bufferedReader.readLine(); // --> Untuk membaca data 1 baris saja dalam bentuk String
                    
                    while (col < gamePanel.maxWorldCol) {
                        String numbers[] = line.split(" "); //--> Untuk memisahkan line menjadi 1/1 dan dimasukkan ke array
                        int num = Integer.parseInt(numbers[col]); //--> Untuk mengubah String ke Int
                        mapTileNum[col][row] = num; //--> index = col & row
                        col++;
                    }
                    if (col == gamePanel.maxWorldCol) {
                        col = 0;
                        row++;
                    }
                }
            }
        } catch (Exception e) {

        }
    }

//--> Untuk menggambar map     
    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow]; //--> Untuk mengubah num menjadi map dan akan dimasukkan ke array mapTileNum

            //--> Untuk player agar berada pada posisi tengah dan menampilkan map sesuai ukuran screen 
            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().screenX;
            int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().screenY;

            //--> berfungsi untuk menggambar tiles disekitar player saja jadi tidak melebihi screen
            if (worldX + gamePanel.tileSize > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().screenX
                    && worldX - gamePanel.tileSize < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().screenX
                    && worldY + gamePanel.tileSize > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().screenY
                    && worldY - gamePanel.tileSize < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().screenY) {

                //--> Untuk Mencetak Gambar Map 
                g2.drawImage(tile[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }
            worldCol++;

            if (worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
