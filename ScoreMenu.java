/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectgame;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Figereee
 */
public class ScoreMenu extends JPanel {
    
    URL imageUFOredURL = this.getClass().getResource("UFOred.png");
    Image imageUFOred = new ImageIcon(imageUFOredURL).getImage();
    URL imageGOVTextURL = this.getClass().getResource("gameovertext.png");
    Image imageGOVText = new ImageIcon(imageGOVTextURL).getImage();
    ImageIcon PlayAgain = new ImageIcon(this.getClass().getResource("playagain.png"));    
    ImageIcon exit = new ImageIcon(this.getClass().getResource("ExitBT.png"));
    
    public JButton jbtPlayagain = new JButton(PlayAgain);
    public JButton jbtExit = new JButton(exit);
    
}
