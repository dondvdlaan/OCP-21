package dev.manyroads.sockets.clientserver1.server;

import java.io.*;
import java.net.*;

public class EchoServer {
    private static final int PORT = 34522;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                try (
                        Socket socket = server.accept(); // accept a new client
                        DataInputStream input = new DataInputStream(socket.getInputStream());
                        DataOutputStream output = new DataOutputStream(socket.getOutputStream())
                ) {
                    String msg = input.readUTF(); // read a message from the client
                    output.writeUTF(msg); // resend it to the client
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Session extends Thread{

    private final Socket socket;

    public Session(Socket socket) {
        this.socket = socket;
    }

    public  void run(){

    }
}