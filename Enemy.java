/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectgame;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Figereee
 */
public class Enemy {

    URL imageUFOURL = this.getClass().getResource("UFO.png");
    Image imageUFO = new ImageIcon(imageUFOURL).getImage();

    private int x;
    private int y;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move() {
        y += 10;
    }

    void draw(Graphics g) {
        g.drawImage(imageUFO, x, y, 80, 40, null);

    }
}
