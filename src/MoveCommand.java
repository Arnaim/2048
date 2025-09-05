import java.awt.*;
import java.util.Objects;
import javax.swing.*;

/**
 * ConcreteCommand for executing a game move. This class
 * encapsulates the request to move tiles on the game board.
 */
public class MoveCommand implements Command {

    private final Grid grid;
    private final String direction;

    /**
     * Constructs a MoveCommand.
     * @param grid The game board instance.
     * @param direction The direction of the move (e.g., "up", "down").
     */
    public MoveCommand(Grid grid, String direction) {
        this.grid = Objects.requireNonNull(grid);
        this.direction = Objects.requireNonNull(direction);
    }

    @Override
    public void execute() {
        // Save the state before executing the move for Memento pattern
        Grid.getInstance().saveState();
        
        // Execute the move based on the direction string
        switch (direction.toLowerCase()) {
            case "up":
                grid.up();
                break;
            case "down":
                grid.down();
                break;
            case "left":
                grid.left();
                break;
            case "right":
                grid.right();
                break;
        }
        grid.repaint();

        // After the move, check if the game is over.
        if (!grid.checkIfCanMove()) {
            GameManager.getInstance().lose();
        }
    }
}
