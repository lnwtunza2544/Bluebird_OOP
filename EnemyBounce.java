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
public class EnemyBounce {

    URL imageUFOredURL = this.getClass().getResource("UFOred.png");
    Image imageUFOred = new ImageIcon(imageUFOredURL).getImage();

    private int x;
    private int y;
    private int BounceX;
    private int BounceY;

    public EnemyBounce(int x, int y) {
        this.x = x;
        this.y = y;
        this.BounceX = 8;
        this.BounceY = 8;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move() {
        x += BounceX;
        y += BounceY;

        
        if (x < 0 || x > 1205) {
            BounceX = -BounceX;
        }
        if (y < 0 || y > 645 ) {
            BounceY = -BounceY;
        }
    }
    
    void draw(Graphics g) {
        g.drawImage(imageUFOred, x, y, 80, 40, null);

    }

}
