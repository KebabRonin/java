package lab10;

import java.io.*;
import java.net.*;

public class GameClient {

    static Socket socket;

    public static void main (String[] args) throws IOException {
        String serverAddress = "0.0.0.0"; // The server's IP address
        int PORT = 8100; // The server's port
        GameClient.socket = new Socket(serverAddress, PORT);
        try (

                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
//                BufferedReader in = new BufferedReader (
//                        new InputStreamReader(socket.getInputStream()));
                ) {
            new Thread(GameClient::readThread).start();
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            // Send a request to the server
            System.out.println("Running");
            while(true) {
                String request = keyboard.readLine();
                if(request.equals("exit")) {
                    System.exit(0);
                }
                System.out.println("Sent :" + request);
                out.writeObject(request);
            }
        } catch (ConnectException | UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
    }

    static public void readThread() {
        try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            while (true) {
                try {
                    Object rsp = ois.readObject();
                    if (rsp instanceof Board b) {
                        System.out.println(b);
                    } else if (rsp instanceof String msg) {
                        System.out.println(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}