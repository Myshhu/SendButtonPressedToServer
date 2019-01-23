package com.example.myshh.buttonserver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.Reader;
import java.net.Socket;
import java.util.List;

public class SocketListeningThread extends Thread {

    /**
     * 1 - Blue
     * 2 - Yellow
     * 3 - Red
     */


    Socket socket;
    List<String> msgQueue;
    String myMsg;

    public SocketListeningThread(Socket socket, List<String> msgQueue) {
        this.socket = socket;
        this.msgQueue = msgQueue;
    }

    @Override
    public void run() {
        super.run();
        try {
            //ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Reader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                try {
                    System.out.println("Before reading");
                    myMsg = ((BufferedReader) in).readLine();
                    System.out.println("After reading");
                    msgQueue.add(myMsg);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
