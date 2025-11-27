package factory;

import model.Tile;

public class TileFactory {

    /**
     * Creates either a 2-tile or 4-tile based on probability.
     * This replaces the previous randomTile() static method.
     *
     * @return A new Tile instance (2 or 4).
     */
    public Tile createRandomTile() {
        return Math.random() < 0.15 ? Tile.FOUR : Tile.TWO;
    }
}
