/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectgame;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Figereee
 */
public class MenuGame extends JPanel{
    private ImageIcon bg = new ImageIcon(this.getClass().getResource("MenuBG.png"));
    private ImageIcon start = new ImageIcon(this.getClass().getResource("StartBT.png"));
    private ImageIcon exit = new ImageIcon(this.getClass().getResource("ExitBT.png"));
    private ImageIcon howto = new ImageIcon(this.getClass().getResource("light.png"));
    
    public JButton jbtStart = new JButton(start);
    public JButton jbtExit = new JButton(exit);
    public JButton jbtHowto = new JButton(howto);
    
    MenuGame(){
        setLayout(null);
        jbtStart.setBounds(400, 525, 150, 50);
        jbtStart.setBorderPainted(false);
        add(jbtStart);
        
        jbtExit.setBounds(730, 525, 150,50);
        jbtExit.setBorderPainted(false);
        add(jbtExit);
        
        jbtHowto.setBounds(1215, 625, 50,55);
        jbtHowto.setContentAreaFilled(false);
        jbtHowto.setBorderPainted(false);
        add(jbtHowto);
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bg.getImage(), 0, 0, getWidth(), getHeight(), this);   
    }
    
}
