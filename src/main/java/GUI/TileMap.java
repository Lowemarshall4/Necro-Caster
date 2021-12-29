/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Marshall
 */
public class TileMap {
    
    private TileLayer[] layers;
    public int[] topMapChangeCoords;
    public int[] bottomMapChangeCoords;
    public TileMap belowMap;
    public TileMap aboveMap;
    
    
    
    public TileMap(TileLayer[] layers, int[] topMapChangeCoors, int[] bottomMapChangeCoords) {
        this.layers = layers;
        this.topMapChangeCoords = topMapChangeCoors;
        this.bottomMapChangeCoords = bottomMapChangeCoords;
    }

    public TileLayer[] getLayers() {
        return layers;
    }
    
    
    
    
    
}
