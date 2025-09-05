
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;
import static java.awt.event.KeyEvent.VK_R;
import static java.awt.event.KeyEvent.VK_U;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * The KeyPress class acts as a client for the Command pattern. It
 * maps key presses to specific command objects, decoupling the input
 * from the game logic. It also manages the game state history for the undo command.
 */
public class KeyPress extends KeyAdapter {

    // Caretaker for the Memento pattern.
    private final Stack<BoardMemento> history = new Stack<>();
    // Client for the Command pattern
    private final Map<Integer, Command> keyMapping = new HashMap<>();

    private final Grid board;
    private static KeyPress instance;

    // Private constructor for Singleton pattern
    private KeyPress(Grid grid) {
        this.board = grid;
        initCommands();
    }

    /**
     * Provides the global access point for the KeyPress instance.
     * @param grid The game board instance.
     * @return The single instance of KeyPress.
     */
    public static KeyPress getKeyPress(Grid grid) {
        if (instance == null) {
            instance = new KeyPress(grid);
        }
        return instance;
    }
    
    private void initCommands() {
        // Map key codes to specific commands
        keyMapping.put(VK_UP, new MoveCommand(board, "up"));
        keyMapping.put(VK_DOWN, new MoveCommand(board, "down"));
        keyMapping.put(VK_LEFT, new MoveCommand(board, "left"));
        keyMapping.put(VK_RIGHT, new MoveCommand(board, "right"));
        keyMapping.put(VK_R, () -> {
            board.initializeTiles();
            history.clear(); // Clear history on reset
            board.repaint();
        });
        keyMapping.put(VK_U, new UndoCommand(board, history));
    }

    @Override
    public void keyPressed(KeyEvent k) {
        super.keyPressed(k);
        Command action = keyMapping.get(k.getKeyCode());
        if (action != null) {
            action.execute();
        }
    }

    // Accessor for the Memento history stack
    public Stack<BoardMemento> getHistory() {
        return history;
    }
}
