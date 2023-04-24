package org.example.Exceptions;

public class InvalidEdgeException extends GameException {
    int x, y;

    public InvalidEdgeException(int x, int y) {
        this.x=x;
        this.y=y;
    }
}
