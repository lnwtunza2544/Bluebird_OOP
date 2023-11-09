/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectgame;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Figereee
 */
public class HowtoMenu extends JPanel {

    URL imageHowtoURL = this.getClass().getResource("Howto.png");
    Image imageHowto = new ImageIcon(imageHowtoURL).getImage();
    private ImageIcon Gotit = new ImageIcon(this.getClass().getResource("gotit.png"));

    public JButton jbtGotit = new JButton(Gotit);

    HowtoMenu() {

        setLayout(null);
        jbtGotit.setBounds(900, 525, 150, 50);
        jbtGotit.setBorderPainted(false);
        add(jbtGotit);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageHowto, 0, 0, getWidth(), getHeight(), this);
    }
}
