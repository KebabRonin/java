package org.example;

public class Game {
    Board board;
    Player[] players;

    public Game(int size) {
        board = new Board(15);
    }

    public void start() {
        while(true) {
            if(board.isFilled()) {
                //Draw
                return;
            }

        }
    }

}
