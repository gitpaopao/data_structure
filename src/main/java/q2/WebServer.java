package q2;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    public void startServer(int port) {
        try {

            @SuppressWarnings("resource")
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {

                Socket socket = serverSocket.accept();
                new HttpServer(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new WebServer().startServer(8081);
    }
}
