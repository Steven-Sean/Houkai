/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package houkai;

import java.io.InputStream;
import processing.core.*;

/**
 *
 * @author Steven
 */
public class Houkai extends PApplet {
    
    // resolution
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    // sprite pixel size
    public static final int SPRITESIZE = 20;
    
    // Health bar
    public static final int TOPBAR = 80;
    
    // FPS Values
    public static final int FPS = 60;
    
    // Assets
    public PImage bg;
    public PImage title;

    public InputStream in;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PApplet.main("houkai.Houkai");
    }
    
}
