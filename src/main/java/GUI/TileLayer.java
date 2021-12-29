/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.HashMap;

public class TileLayer {
    private int[][] integerMap;
    private int heightInTiles;
    private int widthInTiles;
    private int heightInPixels;
    private int widthInPixels;
    private static HashMap<Integer, Tile> IDMAPPING;
    
    public static final Tile TRANSPARENT = new Tile(0);
    public static final Tile GRASS = new Tile(1);
    public static final Tile SAND = new Tile(2);
    public static final Tile WATER = new Tile(3);
    public static final Tile DEEPWATER = new Tile(4);
    public static final Tile PLAYER = new Tile(5);
    public static final Tile TREE = new Tile(6);
    
    public static final Tile[] TILELIST = {TRANSPARENT, GRASS, SAND, WATER, DEEPWATER, PLAYER, TREE};
    
    public TileLayer(int[][] map) {
        this.integerMap = map;
        this.heightInTiles = map.length;
        this.widthInTiles = map[0].length;
        this.heightInPixels = (heightInTiles) * Tile.TILE_HEIGHT;
        this.widthInPixels = (widthInTiles) * Tile.TILE_WIDTH;
        }
    
    public int getHeightInTiles() {
        return heightInTiles;
    }

    public int getWidthInTiles() {
        return widthInTiles;
    }

    public int getHeightInPixels() {
        return heightInPixels;
    }

    public int getWidthInPixels() {
        return widthInPixels;
    }
    
    public int[][] getIntegerMap() {
        return integerMap;
    }
}


//NOTES

//WILL HAVE AT LEAST 2 TILEMAPS
    //BOTTOM TILEMAP IS THE RENDERED GROUND
    //TOP TILEMAP IS AN OBJECT TILEMAP HOLDING CHARACTER POSITION/ENCOUNTERS
