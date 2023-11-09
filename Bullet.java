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
public class Bullet {

    URL imageBulletURL = this.getClass().getResource("Bullet.png");
    Image imageBullet = new ImageIcon(imageBulletURL).getImage();

    private int x;
    private int y;

    public Bullet(int x, int y) {
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
        y -= 10;
    }

    void draw(Graphics g) {
        g.drawImage(imageBullet, x, y, 5, 10, null);

    }

    public boolean BulletHits(Bullet bullet, Enemy enemy) {
        return bullet.getX() < enemy.getX() + 80
                && bullet.getX() + 5 > enemy.getX()
                && bullet.getY() < enemy.getY() + 40
                && bullet.getY() + 10 > enemy.getY();
    }

    public boolean BulletenmbHits(Bullet bullet, EnemyBounce enemybounce) {
        return bullet.getX() < enemybounce.getX() + 80
                && bullet.getX() + 5 > enemybounce.getX()
                && bullet.getY() < enemybounce.getY() + 40
                && bullet.getY() + 10 > enemybounce.getY();
    }

}
