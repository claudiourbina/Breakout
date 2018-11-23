package main.java.logic;

import main.java.controller.Game;

import java.util.Observer;

public interface Logic {
    void acceptGame(Game game);

    void suscribe(Observer o);
}
