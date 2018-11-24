package main.java.logic;

import main.java.controller.Game;

import java.util.Observer;
/**
 * Interface that represent a logic objects.
 *
 * @author Claudio Urbina
 */
public interface Logic {
    /**
     * Accept a Game game and it response to it activating an specific method.
     * @param game Accepted Game.
     */
    void acceptGame(Game game);

    /**
     * Allows a Game to Observe it.
     * @param o The observer.
     */
    void subscribe(Observer o);
}
