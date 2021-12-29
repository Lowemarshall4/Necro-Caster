/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Containers;
import GUI.GameModel;
import GUI.Tile;
import GUI.TileLayer;
import GUI.TileMap;
import java.awt.*;
import javax.swing.*;


public class MapPanel extends JPanel{
    public TileMap map;
    private final ImageIcon playerImage;
    public JLabel player;
    
    public MapPanel(TileMap map){
        setVisible(false);
        this.map = map;
        setPreferredSize(new Dimension(map.getLayers()[0].getWidthInPixels(),map.getLayers()[0].getHeightInPixels()));
        playerImage = GameModel.PLAYERMAPIMAGE;
        player = new JLabel(playerImage);
        add(player);
        }
    
    public Image getTileImage(int id) {
       return Toolkit.getDefaultToolkit().getImage(Tile.class.getResource(String.format("/tiles/%1$d.png",id )));
    }
        
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (TileLayer layer : map.getLayers()) {                                                                                             //FOR EACH MAP LAYER
            for (int h = 0; h < layer.getHeightInTiles(); h++) {                                                                             //FOR EACH ROW
                for (int w = 0; w < layer.getWidthInTiles(); w++) {                                                                         //FOR EACH COLUMN
                    g.drawImage(getTileImage(layer.getIntegerMap()[h][w]), (w*Tile.TILE_WIDTH), (h*Tile.TILE_HEIGHT), null);               //DRAW THE TILE'S IMAGE AT THAT LOCATION
                }
            }
        }
    }
}







