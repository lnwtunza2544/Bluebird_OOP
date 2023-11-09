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
public class HPup {
    URL imageHPupURL = this.getClass().getResource("HeartUp.png");
    Image imageHPup = new ImageIcon(imageHPupURL).getImage();

    private int x;
    private int y;

    public HPup(int x, int y) {
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
        y += 5;
    }

    void draw(Graphics g) {
        g.drawImage(imageHPup, x, y, 30, 30, null);

    }
}
