package com.example.myshh.sendbuttonpressedtoserver;

import java.io.Serializable;

public class Message implements Serializable {

    /**
     * 1 - Blue
     * 2 - Yellow
     * 3 - Red
     */

    public int color;
    public String IP;

    public Message(int color, String IP) {
        this.color = color;
        this.IP = IP;
    }
}
