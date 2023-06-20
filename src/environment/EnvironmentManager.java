/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package environment;

import houkai.GamePanel;
import java.awt.Graphics2D;

/**
 *
 * @author AsuS
 */
public class EnvironmentManager {

    GamePanel gp;
    Lighting lighting;

    public EnvironmentManager(GamePanel gp) {
        this.gp = gp;
    }

    public void setup() {
        lighting = new Lighting(gp, 1000); //576
    }

    public void draw(Graphics2D g2) {
        lighting.draw(g2);
    }
}
