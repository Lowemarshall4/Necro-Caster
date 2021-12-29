/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Containers;
import java.awt.*;
import javax.swing.*;



/**
 *
 * @author Marshall
 */
public class InventoryFrame extends JFrame {
    public JPanel materialsPanel;
    public JPanel equipmentPanel;
    public JPanel craftablesPanel;
    public JButton btnExit;
    
    
    
    public InventoryFrame() {
        setVisible(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(400,400));
        setLayout(new BorderLayout());
        add(createMaterialsPanel(), BorderLayout.WEST);
        add(createEquipmentPanel(), BorderLayout.CENTER);
        add(createCraftablesPanel(), BorderLayout.EAST);
        btnExit = new JButton("Exit");
        add(btnExit, BorderLayout.SOUTH);
        pack();
    }
    
    public JPanel createMaterialsPanel() {
        materialsPanel = new JPanel();
        materialsPanel.setLayout(new BoxLayout(materialsPanel, BoxLayout.Y_AXIS));
        JLabel mats = new JLabel("MATERIALS");
        mats.setHorizontalAlignment(JLabel.CENTER);
        materialsPanel.add(mats);
        return materialsPanel;
    }
    
    public JPanel createEquipmentPanel() {
        equipmentPanel = new JPanel();
        equipmentPanel.setLayout(new BoxLayout(equipmentPanel, BoxLayout.Y_AXIS));
        JLabel eq = new JLabel("EQUIPMENT");
        eq.setHorizontalAlignment(JLabel.CENTER);
        equipmentPanel.add(eq);
        return equipmentPanel;
    }
    
    public JPanel createCraftablesPanel() {
        craftablesPanel = new JPanel();
        craftablesPanel.setLayout(new BoxLayout(craftablesPanel, BoxLayout.Y_AXIS));
        JLabel cr = new JLabel("CRAFTABLES");
        cr.setHorizontalAlignment(JLabel.CENTER);
        craftablesPanel.add(cr);
        return craftablesPanel;
   }
    
   public JButton getBtnExit() {
       return btnExit;
   }
    
}
