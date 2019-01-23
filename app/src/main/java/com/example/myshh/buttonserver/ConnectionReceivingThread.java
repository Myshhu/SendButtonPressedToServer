package com.example.myshh.buttonserver;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ConnectionReceivingThread extends Thread {

    private ServerSocket serverSocket;
    private List<String> msgQueue;

    public ConnectionReceivingThread(ServerSocket serverSocket, List<String> msgQueue){
        this.serverSocket = serverSocket;
        this.msgQueue = msgQueue;
    }

    @Override
    public void run() {
        super.run();

        try {
            serverSocket = new ServerSocket(50000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection");
                new SocketListeningThread(clientSocket, msgQueue).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
