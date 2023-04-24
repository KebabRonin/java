package org.example;

import org.example.Exceptions.GameDoneException;
import org.example.Exceptions.GameException;
import org.example.Exceptions.InvalidEdgeException;

import java.io.Serializable;

public class GameModel implements Serializable {
    GraphModel g;
    int currentPlayer = 0;
    public winners winner = winners.None;

    public void reset() {
        winner = winners.None;
        currentPlayer = 0;
        g.reset();
    }

    public enum winners {None, P1, P2};

    public GameModel(int numVertices, double edgeProbability) {
        g = new GraphModel(numVertices, edgeProbability);
    }

    protected void makeMove(int x, int y) throws GameException {

        if(winner != winners.None) {
            throw new GameDoneException(winner);
        }

        if (g.adjMatrix[x][y] != GraphModel.Edges.Some) {
            throw new InvalidEdgeException(x, y);
        }

        if (currentPlayer == 0) {
            g.adjMatrix[x][y] = g.adjMatrix[y][x] = GraphModel.Edges.P1;
            currentPlayer = 1;
        }
        else {
            g.adjMatrix[x][y] = g.adjMatrix[y][x] = GraphModel.Edges.P2;
            currentPlayer = 0;
        }

        winner = getWinner();
    }

    private winners getWinner() {
        for (int i = 0; i < g.numVertices; ++i) {
            for (int j = 0; j < g.numVertices; ++j) {
                for (int k = 0; k < g.numVertices; ++k) {
                    if (g.adjMatrix[i][j] != GraphModel.Edges.None && g.adjMatrix[i][j] == g.adjMatrix[j][k] && g.adjMatrix[j][k] == g.adjMatrix[k][i]) {
                        if(g.adjMatrix[i][j] == GraphModel.Edges.P1) {
                            return winners.P1;
                        }
                        else if(g.adjMatrix[i][j] == GraphModel.Edges.P2) {
                            return winners.P2;
                        }
                    }
                }
            }
        }
        return winners.None;
    }
}
