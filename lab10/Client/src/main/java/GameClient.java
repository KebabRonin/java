import java.io.*;
import java.net.*;

public class GameClient {
    public static void main (String[] args) throws IOException {
        String serverAddress = "0.0.0.0"; // The server's IP address
        int PORT = 8100; // The server's port
        try (
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader (
                        new InputStreamReader(socket.getInputStream())) ) {
            // Send a request to the server
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            while(true) {
                String request = keyboard.readLine();
                out.println(request);
                // Wait the response from the server ("Hello World!")
                switch (request) {
                    case "exit":
                        return;
                    default:
                        try {
                            String response = in.readLine();
                            System.out.println(response);
                        } catch (SocketException e) {
                            System.out.println(e);
                            return;
                        }
                }

            }
        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
    }
}