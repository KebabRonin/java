package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.stream.Collectors;

public class ClientThread {
    Socket socket;
    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        new Thread(this::runGame).start();
    }

    public void runGame() {
        System.out.println("Running");
        try (
            PrintWriter out =
                    new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader (
                    new InputStreamReader(socket.getInputStream())) ) {
            while(true) {
                String req = in.readLine();
                if(req == null) {
                    System.out.println("Client disconnected");
                    return;
                }
                switch (req) {
                    case "stop":
                        System.out.println("Server stopped");
                        out.println("Server stopped");
                        System.exit(0);
                    default:
                        System.out.println("Server received the request...");
                        System.out.println(req);
                        out.println(req);
                }
            }
        } catch (IOException e) {
            System.out.println("Error in gameThread");
            System.out.println(e);
            return;
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
    }
}
