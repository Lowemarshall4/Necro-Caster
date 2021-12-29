/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Containers.BattlePanel;
import GUI.Containers.HowToPlayPanel;
import GUI.Containers.InventoryFrame;
import GUI.Containers.MainMenuPanel;
import GUI.Containers.MapPanel;
import GUI.Containers.StatsPanel;
import GUI.Containers.MainFrame;

/**
 *
 * @author Marshall
 */
public class GameView {
    public MainFrame mainFrame;
    public MainMenuPanel mainMenuPanel;
    public HowToPlayPanel howToPlayPanel;
    public InventoryFrame invFrame;
    public MapPanel mapPanel;
    public StatsPanel statsPanel;
    public GameModel model;
    public BattlePanel battlePanel;
            
            
            
    public GameView() {
    model = new GameModel();
    mainFrame = new MainFrame();
    mainMenuPanel = new MainMenuPanel();
    howToPlayPanel = new HowToPlayPanel();
    invFrame = new InventoryFrame();
    mapPanel = new MapPanel(model.getMap1());
    statsPanel = new StatsPanel();
    battlePanel = new BattlePanel();
    
    }
}
