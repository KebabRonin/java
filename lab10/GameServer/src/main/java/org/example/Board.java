package org.example;

public class Board {
    int size;
    BoardSlotState[][] board;

    boolean finalised;
    BoardSlotState winStatus;
    public Board(int size) {
        size = size;
        finalised = false;
        board = new BoardSlotState[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                board[i][j] = BoardSlotState.Empty;
            }
        }
    }

    boolean isFilled() {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (board[i][j] == BoardSlotState.Empty) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean makeMove(int x, int y, BoardSlotState s) throws GameException {
        if (s == BoardSlotState.Empty) {
            return false;
            //throw new GameException();
        }
        try {
            if (board[x][y] != BoardSlotState.Empty) {
                return false;
                //throw new GameException();
            }

            board[x][y] = s;

            validateBoardPoint(x, y);
            return true;
        } catch(IndexOutOfBoundsException e) {
            return false;
            //throw new GameException();
        }
    }

    private BoardSlotState validateBoardPoint(int x, int y) {
        int inDir, aux;
        //vertical
        inDir = 1;
        while( x + inDir < size && board[x + inDir][y] == board[x][y]) ++inDir;
        aux = 1;
        while( x - aux >= 0 && board[x - aux][y] == board[x][y]) ++aux;
        inDir += aux;

        if(inDir == 5) {
            finalised = true;
            winStatus = board[x][y];
            return board[x][y];
        }
        return BoardSlotState.Empty;
    }
}
