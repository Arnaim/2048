
import java.awt.*;
import javax.swing.*;

/**
 * Singleton for managing the game's main window and overall state.
 * Ensures that only one instance of the game's main window exists.
 */
public class GameManager extends JFrame {

    private static GameManager instance;
    private static final String TITLE = "2048";
    public static final String WIN_MSG = "Winner, continue?";
    public static final String LOSE_MSG = "Loser, R to reset the game, U to undo last move";
    
    private JLabel statusBar;
    private Grid board;

    // Private constructor to prevent direct instantiation
    private GameManager() {
        initUI();
    }

    /**
     * Provides the global access point for the GameManager instance.
     * @return The single instance of the GameManager.
     */
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }
    
    // Initializes the UI components.
    private void initUI() {
        setTitle(TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(340, 400);
        setResizable(false);

        statusBar = new JLabel("");
        add(statusBar, BorderLayout.SOUTH);
        
        board = Grid.getInstance();
        board.setGameManager(this);
        KeyPress kb = KeyPress.getKeyPress(board);
        board.addKeyListener(kb);
        add(board);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void win() {
        statusBar.setText(WIN_MSG);
    }

    public void lose() {
        statusBar.setText(LOSE_MSG);
    }

    public void clearStatus() {
        statusBar.setText("");
    }
    
    public Grid getBoard() {
        return board;
    }
}
