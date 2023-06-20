/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package houkai;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author AsuS
 */
public class UtilityTool {
    public BufferedImage scaleImage(BufferedImage original, int width, int height){
        
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics(); // menggambar grapics2D yang dapat digunakan untuk menggambar BufferedImage
        g2.drawImage(original,0,0,width,height,null); // untuk menggambar gambar yg diskalakan
        g2.dispose();
        
        return scaledImage; 
    }
}
