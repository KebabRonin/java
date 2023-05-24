package lab11;

import java.util.Vector;

public class GameRoom {
    Board board;
    Vector<Player> players = new Vector<>(2);

    long[] timer = {30000,30000};
    long[] timestamp = {0,0};

    GameRoomStatus gameRoomStatus = GameRoomStatus.WaitingForPlayers;

    public GameRoom(int size) {
        board = new Board(size);
    }

    public GameRoom() {this(15);}

    public synchronized boolean makeMove(int x, int y, ClientThread player) {

        if(gameRoomStatus != GameRoomStatus.InProgress) {
            player.send("Game not in progress");
            return false;
        }

        BoardSlotState color = BoardSlotState.Empty;
        int current_player_number;
        for (current_player_number = 0; current_player_number < 2; ++current_player_number) {
            Player p = players.get(current_player_number);
            if (p.thread == player) {
                color = p.color;
                break;
            }
        }

        long time_taken = System.currentTimeMillis() - timestamp[current_player_number];

        if (time_taken > timer[current_player_number]){
            board.finalised = true;
            switch (current_player_number) {
                case 0 -> board.winStatus = BoardSlotState.White;
                case 1 -> board.winStatus = BoardSlotState.Black;
            }
        }
        boolean temp = board.makeMove(x,y,color);

        if(board.finalised) {
            this.gameRoomStatus = GameRoomStatus.Finished;
            for(Player i : players) {
                i.thread.send();
                i.thread.send("Game won by " + board.winStatus + "!");
            }
        }
        else {
            if (temp) {
                timer[current_player_number] -= time_taken;
                timestamp[1 - current_player_number] = System.currentTimeMillis();
                for (int i = 0; i < 2; ++i) {
                    Player p = players.get(i);
                    p.thread.send();
                    if (p.color == board.currentMove) {
                        p.thread.send("Your turn! You have " + timer[i] + " ms left!");
                    }
                }
            } else {
                player.send("Invalid move");
            }
        }
        return temp;
    }

    public synchronized void addPlayer(ClientThread c) {
        Player p = new Player();
        p.thread = c;
        switch (players.size()) {
            case 0 -> p.color = BoardSlotState.Black;
            case 1 -> p.color = BoardSlotState.White;
            default -> p.color = BoardSlotState.Empty;
        }
        players.add(p);

        if(players.size() >= 2) {
            this.gameRoomStatus = GameRoomStatus.InProgress;

            timestamp[0] = System.currentTimeMillis();

            for(Player i : players) {
                if(i.color == this.board.currentMove) {
                    i.thread.send("Your turn! You have " + timer[0] + " ms left!");
                    break;
                }
            }
        }
    }

    public synchronized void removePlayer(ClientThread c) {
        System.out.println("Removing player");
        for(Player i : players) {
            if (i.thread == c) {
                players.remove(i);
                if (i.color != BoardSlotState.Empty && this.gameRoomStatus != GameRoomStatus.WaitingForPlayers) {
                    this.gameRoomStatus = GameRoomStatus.Finished;
                    System.out.println("Game room is finished!");
                    for(Player k : players) {
                        k.thread.send();
                        k.thread.send("Game is finished!");
                    }
                }
                c.gameRoom = null;
                return;
            }
        }
    }

}
