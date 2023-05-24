package lab11;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class GameServer {
    // Define the port on which the server is listening
    public static final int PORT = 8100;

    public Vector<GameRoom> gameRooms = new Vector<>();

    public GameServer() {
        new Thread(this::serverLoop).run();
    }

    private void serverLoop() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                System.out.println("Waiting for a client ...");
                Socket socket = serverSocket.accept();
                // Execute the client's request in a new thread
                new ClientThread(socket, this).start();
            }
        } catch (IOException e) {
            System.err.println("Ooops... " + e);
        }
    }

    public synchronized GameRoom createGameRoom(ClientThread c) {
        GameRoom g = new GameRoom();
        gameRooms.add(g);

        g.addPlayer(c);

        System.out.println("Created new game room");

        return g;
    }

    public synchronized GameRoom joinAnyGameRoom(ClientThread c) {
        for(GameRoom i : gameRooms) {
            if (i.gameRoomStatus == GameRoomStatus.WaitingForPlayers) {
                i.addPlayer(c);
                return i;
            }
        }
        return createGameRoom(c);
    }
}