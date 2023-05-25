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
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    // sprite pixel size
    private static final int SPRITE_SIZE = 20;
    
    // Health bar
    private static final int TOPBAR = 80;
    
    // FPS Values
    private static final int FPS = 60;
    
    // Assets
    private PImage bg;
    private PImage title;

    private InputStream in;
    
    public void settings() {
        size(WIDTH,HEIGHT);
    }
    
    public void setup() {
        frameRate(FPS);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PApplet.main("houkai.Houkai");
    }
    
}
