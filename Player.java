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
public class Player {

    URL imagePlayerURL = this.getClass().getResource("Player.png");
    Image imagePlayer = new ImageIcon(imagePlayerURL).getImage();

    private int x;
    private int y;
    int playerPosX;
    int playerPosY;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.playerPosX = 600;
        this.playerPosY = 580;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    void draw(Graphics g) {
        g.drawImage(imagePlayer, playerPosX, playerPosY, 75, 75, null);

    }

    public boolean PlayerHits(Enemy enemy) {
        return enemy.getX() < playerPosX + 75
                && enemy.getX() + 80 > playerPosX
                && enemy.getY() < playerPosY + 75
                && enemy.getY() + 40 > playerPosY;
    }

    public boolean PlayerHits(EnemyBounce enemyBounce) {
        return enemyBounce.getX() < playerPosX + 75
                && enemyBounce.getX() + 80 > playerPosX
                && enemyBounce.getY() < playerPosY + 75
                && enemyBounce.getY() + 40 > playerPosY;
    }
    
    public boolean PlayerHits(HPup hpup) {
        return hpup.getX() < playerPosX + 75
                && hpup.getX() + 30 > playerPosX
                && hpup.getY() < playerPosY + 75
                && hpup.getY() + 30 > playerPosY;
    }

}
