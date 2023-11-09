/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectgame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Figereee
 */
public class Mission1 extends JPanel implements ActionListener {

    ScoreMenu scmenu = new ScoreMenu();

    private final ImageIcon scene1 = new ImageIcon(this.getClass().getResource("Fightscene1BG.png"));
    private final ImageIcon scene2 = new ImageIcon(this.getClass().getResource("Fightscene2BG.png"));

    URL imageHeartURL = this.getClass().getResource("Heart.png");
    Image imageHeart = new ImageIcon(imageHeartURL).getImage();

    private final Random RNDM = new Random();

    private final int WIDTH = 0;
    private final int HEIGHT = 0;

    private List<Bullet> bullets = new ArrayList<>();
    private final List<Enemy> enemies = new ArrayList<>();
    private final List<EnemyBounce> enemiesbounce = new ArrayList<>();
    private final List<Cloud> clouds = new ArrayList<>();
    private final List<HPup> HPups = new ArrayList<>();

    private final Player player = new Player(WIDTH, HEIGHT);

    int Score = 0;
    int HP;
    int SpawnEnemySpeed;
    int SpawnEnemybounceSpeed;

    private double playerXSpeed = 0;
    private double playerYSpeed = 0;

    private boolean bulletFired = false;
    private boolean PlayerHit = false;
    private boolean PlayerHPup = false;
    private boolean gameRunning = true;

    //Thread Enemy
    Thread enemyThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                if (Score == 0) {
                    SpawnEnemySpeed = 1000;
                } else if (Score == 100) {
                    SpawnEnemySpeed = 500;
                } else if (Score == 700) {
                    SpawnEnemySpeed = 200;
                }
                spawnEnemy();
                try {
                    Thread.sleep(SpawnEnemySpeed);
                } catch (Exception e) {
                }
            }
        }
    });
    
    //Thread Enemy bounce
    Thread enemyBounceThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                if (Score == 200) {
                    SpawnEnemybounceSpeed = 2000;
                } else if (Score == 400) {
                    SpawnEnemybounceSpeed = 1000;
                } else if (Score == 1000) {
                    SpawnEnemybounceSpeed = 500;
                } else if (Score == 1300) {
                    SpawnEnemybounceSpeed = 300;
                }
                if (Score >= 200) {
                    spawnEnemyBounce();
                }
                try {
                    Thread.sleep(SpawnEnemybounceSpeed);
                } catch (Exception e) {
                }
            }
        }
    });
    
    //Thread Cloud
    Thread cloudThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                spawnCloud();
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
            }
        }
    });
    
    //Thread Hp up
    Thread HPupThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                spawnHPup();
                try {
                    Thread.sleep(15000);
                } catch (Exception e) {
                }
            }
        }
    });
    
    //Timre game
    Timer gameTimer = new Timer(20, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateGame();
            repaint();
        }
    });

    Mission1() {
        this.setLayout(null);
        this.setFocusable(true);

        bulletFired = false;

        HP = 3;

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (gameRunning) {
                    if (e.getKeyCode() == KeyEvent.VK_A) {
                        playerXSpeed = -15;
                    } else if (e.getKeyCode() == KeyEvent.VK_D) {
                        playerXSpeed = 15;
                    } else if (e.getKeyCode() == KeyEvent.VK_W) {
                        playerYSpeed = -15;
                    } else if (e.getKeyCode() == KeyEvent.VK_S) {
                        playerYSpeed = 15;
                    } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        if (!bulletFired) {
                            bulletFired = true;
                            bullets.add(new Bullet(player.playerPosX + 35, player.playerPosY));
                        }
                    }
                    repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (gameRunning) {
                    if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
                        playerXSpeed = 0;
                    } else if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
                        playerYSpeed = 0;
                    } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        bulletFired = false;
                    }
                }
            }
        }
        );
        HPupThread.start();

        cloudThread.start();

        enemyThread.start();

        enemyBounceThread.start();

        gameTimer.start();

        scmenu.jbtPlayagain.addActionListener(this);
        scmenu.jbtExit.addActionListener(this);

        scmenu.jbtPlayagain.setBounds(400, 525, 150, 50);
        scmenu.jbtPlayagain.setBorderPainted(false);
        add(scmenu.jbtPlayagain);
        scmenu.jbtPlayagain.setVisible(false);

        scmenu.jbtExit.setBounds(730, 525, 150, 50);
        scmenu.jbtExit.setBorderPainted(false);
        add(scmenu.jbtExit);
        scmenu.jbtExit.setVisible(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == scmenu.jbtPlayagain) {
            restartGame();
        } else if (e.getSource() == scmenu.jbtExit) {
            System.exit(0);
        }
    }
    
    //Restart game
    public void restartGame() {
        if (!gameRunning) {
            gameRunning = true;
            HP = 3;
            Score = 0;
            scmenu.jbtPlayagain.setVisible(false);
            scmenu.jbtExit.setVisible(false);

            if (!cloudThread.isAlive()) {
                cloudThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            spawnCloud();
                            try {
                                Thread.sleep(100);
                            } catch (Exception e) {
                            }
                        }
                    }
                });
                cloudThread.start();
            }

            if (!enemyThread.isAlive()) {
                enemyThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            if (Score == 0) {
                                SpawnEnemySpeed = 1000;
                            } else if (Score == 100) {
                                SpawnEnemySpeed = 500;
                            } else if (Score == 700) {
                                SpawnEnemySpeed = 200;
                            }
                            spawnEnemy();
                            try {
                                Thread.sleep(SpawnEnemySpeed);
                            } catch (Exception e) {
                            }
                        }
                    }
                });
                enemyThread.start();
            }

            if (!enemyBounceThread.isAlive()) {
                enemyBounceThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            if (Score == 200) {
                                SpawnEnemybounceSpeed = 2000;
                            } else if (Score == 400) {
                                SpawnEnemybounceSpeed = 1000;
                            } else if (Score == 1000) {
                                SpawnEnemybounceSpeed = 500;
                            } else if (Score == 1300) {
                                SpawnEnemybounceSpeed = 300;
                            }
                            if (Score >= 200) {
                                spawnEnemyBounce();
                            }
                            try {
                                Thread.sleep(SpawnEnemybounceSpeed);
                            } catch (Exception e) {
                            }
                        }
                    }
                });
                enemyBounceThread.start();
            }

            if (!HPupThread.isAlive()) {
                HPupThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            spawnHPup();
                            try {
                                Thread.sleep(10000);
                            } catch (Exception e) {
                            }
                        }
                    }
                });
                HPupThread.start();
            }
            gameTimer.start();
            repaint();
        }
    }
    
    //check Game Over
    private void checkGameOver() {
        if (HP <= 0) {
            gameRunning = false;

            scmenu.jbtPlayagain.setVisible(true);
            scmenu.jbtExit.setVisible(true);

            clouds.clear();
            enemies.clear();
            enemiesbounce.clear();
            HPups.clear();

            cloudThread.stop();
            enemyThread.stop();
            enemyBounceThread.stop();
            HPupThread.stop();
            gameTimer.stop();

        }
    }
    
    //spawn Enemy
    private void spawnEnemy() {
        if (getWidth() > 20) {
            int x = RNDM.nextInt(getWidth() - 75);
            int y = 0;
            enemies.add(new Enemy(x, y));
        }
    }

    //spawn Enemy bounce
    private void spawnEnemyBounce() {
        if (getWidth() > 20) {
            int x = RNDM.nextInt(getWidth() - 75);
            int y = 0;
            enemiesbounce.add(new EnemyBounce(x, y));
        }
    }
    
    //spawn Cloud
    private void spawnCloud() {
        if (getWidth() > 20) {
            int x = RNDM.nextInt(getWidth() - 75);
            int y = 0;
            clouds.add(new Cloud(x, y));
        }
    }
    
    //spawn HP up
    private void spawnHPup() {
        if (getWidth() > 20) {
            int x = RNDM.nextInt(getWidth() - 75);
            int y = 0;
            HPups.add(new HPup(x, y));
        }
    }

    private void updateGame() {
        if (gameRunning) {
            player.playerPosX += playerXSpeed;
            player.playerPosY += playerYSpeed;

            //check enemy
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                enemy.move();
                if (enemy.getY() > getHeight()) {
                    enemies.remove(i);
                }
            }

            //check bullet
            for (int i = 0; i < bullets.size(); i++) {
                Bullet bullet = bullets.get(i);
                bullet.move();

                if (bullet.getY() < 0) {
                    bullets.remove(i);
                    bulletFired = false;

                }
            }

            //check enemybounce
            for (int i = 0; i < enemiesbounce.size(); i++) {
                EnemyBounce enemybounce = enemiesbounce.get(i);
                enemybounce.move();

            }

            //check cloud
            for (int i = 0; i < clouds.size(); i++) {
                Cloud cloud = clouds.get(i);
                cloud.move();
                if (cloud.getY() > getHeight()) {
                    clouds.remove(i);
                }
            }

            //check cloud
            for (int i = 0; i < clouds.size(); i++) {
                Cloud cloud = clouds.get(i);
                cloud.move();
                if (cloud.getY() > getHeight()) {
                    clouds.remove(i);
                }
            }

            //check HP up
            for (int i = 0; i < HPups.size(); i++) {
                HPup hpup = HPups.get(i);
                hpup.move();
                if (hpup.getY() > getHeight()) {
                    HPups.remove(i);
                }
            }

            //check bullet hit enemy
            for (int i = 0; i < bullets.size(); i++) {
                for (int j = 0; j < enemies.size(); j++) {
                    Bullet bullet = bullets.get(i);
                    Enemy enemy = enemies.get(j);
                    if (bullet.BulletHits(bullet, enemy)) {
                        bullets.remove(i);
                        enemies.remove(j);
                        Score += 10;
                        break;
                    }
                }
            }

            //check bullet hit enemybounce
            for (int i = 0; i < bullets.size(); i++) {
                for (int j = 0; j < enemiesbounce.size(); j++) {
                    Bullet bullet = bullets.get(i);
                    EnemyBounce enemybounce = enemiesbounce.get(j);
                    if (bullet.BulletenmbHits(bullet, enemybounce)) {
                        bullets.remove(i);
                        enemiesbounce.remove(j);
                        Score += 20;
                        break;
                    }
                }
            }

            //check player hit enemy
            for (int i = enemies.size() - 1; i >= 0; i--) {
                Enemy enemy = enemies.get(i);
                if (player.PlayerHits(enemy)) {
                    enemies.remove(i);
                    HP -= 1;
                    PlayerHit = true;

                }
            }

            //check player hit HP up
            for (int i = HPups.size() - 1; i >= 0; i--) {
                HPup hpup = HPups.get(i);
                if (player.PlayerHits(hpup)) {
                    HPups.remove(i);
                    HP += 1;
                    PlayerHPup = true;

                }
            }

            //check player hit enemybounce
            for (int i = enemiesbounce.size() - 1; i >= 0; i--) {
                EnemyBounce enemyBounce = enemiesbounce.get(i);
                if (player.PlayerHits(enemyBounce)) {
                    enemiesbounce.remove(i);
                    HP -= 1;
                    PlayerHit = true;

                }
            }

            checkGameOver();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (gameRunning) {
            drawScene(g);

            //draw cloud
            for (Cloud cloud : clouds) {
                cloud.draw(g);
            }
            //draw bullets
            for (Bullet bullet : bullets) {
                bullet.draw(g);
            }
            //draw enemys
            for (Enemy enemy : enemies) {
                enemy.draw(g);
            }

            //draw enemys
            for (HPup hpup : HPups) {
                hpup.draw(g);
            }
            //CheckPlayerCollision
            if (player.playerPosX < 0) {
                player.playerPosX = 0;
            }
            if (player.playerPosY < -5) {
                player.playerPosY = -5;
            }
            if (player.playerPosX > getWidth() - 75) {
                player.playerPosX = getWidth() - 75;
            }
            if (player.playerPosY > getHeight() - 75) {
                player.playerPosY = getHeight() - 75;
            }
            player.draw(g);

            if (PlayerHit) {
                g2.setColor(Color.RED);
                g2.setStroke(new BasicStroke(30.0f));
                g2.drawRect(0, 0, getWidth(), getHeight());
                PlayerHit = false;
            } else if (PlayerHPup) {
                g2.setColor(Color.GREEN);
                g2.setStroke(new BasicStroke(30.0f));
                g2.drawRect(0, 0, getWidth(), getHeight());
                PlayerHPup = false;
            }

            for (EnemyBounce enemybounce : enemiesbounce) {
                enemybounce.draw(g);
            }
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(scmenu.imageUFOred, getWidth() - 780, getHeight() - 500, 300, 180, this);
            g.drawImage(scmenu.imageGOVText, getWidth() - 830, getHeight() - 700, 400, 180, this);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Courier", Font.PLAIN, 24));
            g.drawString("Total Score: " + Score, getWidth() - 720, getHeight() - 250);

        }

    }

    private void drawScene(Graphics g) {
        if (Score >= 200) {
            g.drawImage(scene2.getImage(), 0, 0, getWidth(), getHeight(), this);
        } else if (Score >= 0) {
            g.drawImage(scene1.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
        g.drawImage(imageHeart, 20, 585, 70, 55, this);

        g.setFont(new Font("Courier", Font.PLAIN, 27));
        g.setColor(Color.WHITE);
        g.drawString("x ", 95, 625);
        g.drawString("" + HP, 115, 627);
        g.setFont(new Font("Courier", Font.PLAIN, 24));
        g.drawString("SCORE: " + Score, getWidth() - 200, getHeight() - 60);
    }

}
