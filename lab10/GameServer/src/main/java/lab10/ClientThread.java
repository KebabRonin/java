package lab10;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientThread {
    Socket socket;

    ObjectInputStream in = null;
    ObjectOutputStream out = null;

    GameServer gameServer;

    GameRoom gameRoom = null;

    static Pattern move_p = Pattern.compile("(\\d+),(\\d+)");

    public void kill() {
        if(this.gameRoom != null) {
            this.gameRoom.removePlayer(this);
        }
        try {
            out.close();
        } catch (NullPointerException ignored) {} catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (NullPointerException ignored) {} catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ClientThread(Socket socket, GameServer gs) throws IOException{
        this.socket = socket;
        this.gameServer = gs;
    }

    public void start() {
        new Thread(this::run).start();
    }

    public void run() {
        try {

            //out.flush();
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Running");
            while (true) {
                Object obj = in.readObject();
                if (obj instanceof String req) {

                    System.out.println("Read '" + req + "'");

                    interpretRequest(req, out);
                }
            }
        } catch (Exception e) {
            System.out.println("Error in gameThread" + e);
        } finally {
            this.kill();
        }
    }

    private void interpretRequest(String req, ObjectOutputStream out) {
        try {
            System.out.println("Interpreting request '" + req + "'");
            switch (req) {
                case "stop" -> {
                    this.send("Server stopped");
                    System.out.println("Server stopped");
                    System.exit(0);
                }
                case "create" -> {this.gameRoom = this.gameServer.createGameRoom(this); this.send();}
                case "join" -> {this.gameRoom = this.gameServer.joinAnyGameRoom(this); this.send();}
                default -> {
                    System.out.println("Server received the request...");
                    System.out.println(req);
                    Matcher m = move_p.matcher(req);
                    if (m.matches()) {                          //if is move syntax
                        int x = Integer.parseInt(m.group(1));
                        int y = Integer.parseInt(m.group(2));

                        if(gameRoom == null) {
                            this.send("Not connected");
                            break;
                        }

                        if (!gameRoom.makeMove(x, y, this)) {
                            System.out.println("Move not valid:" + x + "," + y);
                        }

                        System.out.println(gameRoom.board);


                    } else {
                        System.out.println(req + " was not an ok move command");
                        this.send("Not ok");
                        //out.writeObject(gameRoom.board);
                    }
                }
            }
        }  catch (Exception e) {
            e.printStackTrace();
            this.kill();
        }
    }

    public void send() {
        try {
            out.reset();
            out.writeObject(gameRoom.board);
        } catch(Exception e) {
            e.printStackTrace();
            this.kill();
        }
    }

    public void send(String msg) {
        try {
            out.reset();
            out.writeObject(msg);
        } catch(Exception e) {
            e.printStackTrace();
            this.kill();
        }
    }
}
