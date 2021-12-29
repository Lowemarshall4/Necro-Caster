/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.awt.*;



public class Tile {
    public static final int TILE_HEIGHT = 32;
    public static final int TILE_WIDTH = 32;
    private int id;
    private Image image;
    
    
    public Tile(int id) {
        this.id = id;
        this.image = Toolkit.getDefaultToolkit().getImage(Tile.class.getResource(String.format("/tiles/%1$d.png", id)));
    }    

    public static int getTILE_HEIGHT() {
        return TILE_HEIGHT;
    }

    public static int getTILE_WIDTH() {
        return TILE_WIDTH;
    }

    public int getId() {
        return id;
    }

    public Image getImage() {
        return image;
    }
    
    

    
}
