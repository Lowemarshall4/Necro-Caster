/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Containers;

import GUI.Tile;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Marshall
 */
public class MainFrame extends JFrame{
    
    public MainFrame(){
       
        this.setTitle("Necro Caster");
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Tile.class.getResource("/characterImages/0.png")));
        this.setIconImage(icon.getImage());
        setLayout(new BorderLayout());
        
        
    }
    
    public void beginGame() {
        
        MainMenuPanel mainMenu = new MainMenuPanel();
        add(mainMenu);
        pack();
        setVisible(true);

    }
    public void close() {
        this.dispose();
    }
    
}
