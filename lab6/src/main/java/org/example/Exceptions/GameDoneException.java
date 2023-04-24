package org.example.Exceptions;

import org.example.GameModel;

public class GameDoneException extends GameException {

    public GameModel.winners winner;

    public GameDoneException(GameModel.winners winner) {
        this.winner = winner;
    }
}
