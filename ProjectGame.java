/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projectgame;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Figereee
 */
public class ProjectGame extends JFrame implements ActionListener {

    MenuGame MenuGame1Scene = new MenuGame();
    Mission1 Mission1Scene = new Mission1();
    ScoreMenu scmenu = new ScoreMenu();
    HowtoMenu htmenu = new HowtoMenu();

    public ProjectGame() {
        this.setSize(1280, 720);
        this.add(htmenu);       
        htmenu.jbtGotit.addActionListener(this);
        htmenu.jbtGotit.setVisible(false);
        
        this.add(MenuGame1Scene);
        MenuGame1Scene.jbtStart.addActionListener(this);
        MenuGame1Scene.jbtExit.addActionListener(this);
        MenuGame1Scene.jbtHowto.addActionListener(this);

        
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == MenuGame1Scene.jbtStart) {
            System.out.println("start");
            this.setLocationRelativeTo(null);
            this.remove(MenuGame1Scene);
            this.setSize(1280, 720);
            this.add(Mission1Scene);
            Mission1Scene.requestFocusInWindow();

        } else if (e.getSource() == MenuGame1Scene.jbtExit) {
            System.exit(0);
        } else if (e.getSource() == MenuGame1Scene.jbtHowto) {
            this.setLocationRelativeTo(null);
            this.add(htmenu);            
            this.setSize(1280, 720);
            htmenu.jbtGotit.setVisible(true);
            MenuGame1Scene.setVisible(false);
            htmenu.requestFocusInWindow();
        } else if (e.getSource() == htmenu.jbtGotit) {
            this.setLocationRelativeTo(null);          
            this.setSize(1280, 720);
            htmenu.jbtGotit.setVisible(false);
            MenuGame1Scene.setVisible(true);
            MenuGame1Scene.requestFocusInWindow();
        }
        this.validate();
        this.repaint();
    }


    public static void main(String[] args) {
        JFrame frame = new ProjectGame();
        frame.setSize(1280, 720);
        frame.setTitle("Blue Bird");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }
}
