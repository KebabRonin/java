package org.example;

public class GameModel {
    GraphModel g;
    int currentPlayer = 0;
    protected void update(int x, int y) throws GameException {
        if (g.adjMatrix[x][y] != GraphModel.Edges.Some) {
            throw new GameException("Invalid edge");
        }
        if (currentPlayer == 0) {
            g.adjMatrix[x][y] = GraphModel.Edges.P1;
            currentPlayer = 1;
        }
        else {
            currentPlayer = 0;
            g.adjMatrix[x][y] = GraphModel.Edges.P2;
        }
    }
}
