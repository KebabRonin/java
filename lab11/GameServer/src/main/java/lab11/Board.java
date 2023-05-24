package lab11;

import java.io.*;

public class Board implements Serializable {
    int size;
    BoardSlotState[][] board;

    BoardSlotState currentMove = BoardSlotState.Black;

    boolean finalised;
    BoardSlotState winStatus;
    public Board(int size) {
        this.size = size;
        finalised = false;
        board = new BoardSlotState[this.size][this.size];
        for (int i = 0; i < this.size; ++i) {
            for (int j = 0; j < this.size; ++j) {
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

    public synchronized boolean makeMove(int x, int y, BoardSlotState s) {
        if(this.isFilled()) {
            this.finalised = true;
            this.winStatus = BoardSlotState.Empty;
        }

        if(s != currentMove) {
            return false;
        }

        if (s == BoardSlotState.Empty || this.finalised) {
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
            currentMove = switch (currentMove) {
                case Black -> BoardSlotState.White;
                case Empty -> null;
                case White -> BoardSlotState.Black;
            };

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
        inDir += aux - 1;
        if(inDir == 5) {
            this.finalised = true;
            this.winStatus = board[x][y];
            return winStatus;
        }
        //horizontal
        inDir = 1;
        while( y + inDir < size && board[x][y + inDir] == board[x][y]) ++inDir;
        aux = 1;
        while( y - aux >= 0 && board[x][y - aux] == board[x][y]) ++aux;
        inDir += aux - 1;
        if(inDir == 5) {
            this.finalised = true;
            this.winStatus = board[x][y];
            return winStatus;
        }
        //diagonal
        inDir = 1;
        while( x + inDir < size &&  y + inDir < size && board[x + inDir][y + inDir] == board[x][y]) ++inDir;
        aux = 1;
        while( x - aux >= 0 && y - aux >= 0 && board[x - aux][y - aux] == board[x][y]) ++aux;
        inDir += aux - 1;

        if(inDir == 5) {
            this.finalised = true;
            this.winStatus = board[x][y];
            return winStatus;
        }
        return BoardSlotState.Empty;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Finalised: ").append(this.finalised).append("\n");
        if(finalised)
            sb.append("Winner: ").append(this.winStatus).append("\n");
        sb.append("Board size: ").append(this.size).append("\n");
        sb.append("==================================\n");
        for(int i = 0; i < this.size; ++i) {
            sb.append("|");
            for(int j = 0; j < this.size; ++j) {
                sb.append((board[i][j]).toString()).append(" ");
            }
            sb.append("|\n");
        }
        sb.append("==================================\n");
        return sb.toString();
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(size);
        out.writeObject(finalised);
        out.writeObject(winStatus);
        for(int i = 0; i < size; ++i) {
            for(int j = 0; j < size; ++j) {
                out.writeObject(board[i][j]);
            }
        }
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        size = (int) in.readObject();
        finalised = (boolean) in.readObject();
        winStatus = (BoardSlotState) in.readObject();
        board = new BoardSlotState[size][size];
        for(int i = 0; i < size; ++i) {
            for(int j = 0; j < size; ++j) {
                board[i][j] = (BoardSlotState) in.readObject();
            }
        }
    }
}
