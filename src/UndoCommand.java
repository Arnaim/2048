

import javax.swing.*;
import java.util.Stack;

/**
 * ConcreteCommand for the Undo action. This class
 * encapsulates the request to restore the previous game state.
 */
public class UndoCommand implements Command {

    private final Grid grid;
    private final Stack<BoardMemento> history;

    /**
     * Constructs an UndoCommand.
     * @param grid The game board instance.
     * @param history The stack of game states (mementos).
     */
    public UndoCommand(Grid grid, Stack<BoardMemento> history) {
        this.grid = grid;
        this.history = history;
    }

    @Override
    public void execute() {
        if (!history.isEmpty()) {
            // Restore the state from the last memento
            BoardMemento memento = history.pop();
            grid.restoreState(memento);
            grid.repaint();
            // Clear any win/lose message
            GameManager.getInstance().clearStatus();
        } else {
            System.out.println("No more moves to undo.");
        }
    }
}
