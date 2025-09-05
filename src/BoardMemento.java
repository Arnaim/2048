
import java.io.Serializable;
import java.util.Arrays;

/**
 * Memento class to store the state of the Grid.
 * It's a simple, read-only data container.
 */
public final class BoardMemento implements Serializable {
    private final Tile[] tiles;

    /**
     * Constructs a Memento object by copying the current tiles array.
     * @param tiles The current tiles array to be saved.
     */
    public BoardMemento(Tile[] tiles) {
        // Deep copy the array to prevent external modification
        this.tiles = Arrays.copyOf(tiles, tiles.length);
    }

    /**
     * Retrieves the saved tiles state.
     * @return A copy of the saved tiles array.
     */
    public Tile[] getSavedState() {
        return Arrays.copyOf(this.tiles, this.tiles.length);
    }
}
