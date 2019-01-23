package com.example.myshh.buttonserver;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ViewingThread extends Thread {

    /**
     * 1 - Blue
     * 2 - Yellow
     * 3 - Red
     */

    private List<String> msgQueue;
    private MainActivity context;
    public boolean nextMessage = true;

    public ViewingThread(List<String> msgQueue, MainActivity context) {
        this.msgQueue = msgQueue;
        this.context = context;
    }

    @Override
    public void run() {
        super.run();
        final TextView textView = context.findViewById(R.id.textView);
        final TextView tvUserIP = context.findViewById(R.id.tvUserIP);
        final LinearLayout linearLayout = context.findViewById(R.id.linearLayout);

        new Thread(() -> {
            while (true) {
                context.runOnUiThread(() -> {
                    textView.setText("List has " + msgQueue.size() + " elements.");
                });
                try {
                    TimeUnit.MILLISECONDS.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        while (true) {
            if (!msgQueue.isEmpty()) {
                if (nextMessage) {
                    String color = msgQueue.get(0).split(" ")[0];
                    String IP = msgQueue.get(0).split(" ")[1];
                    context.runOnUiThread(() -> {
                        tvUserIP.setText(IP);
                        linearLayout.setBackgroundColor(Color.parseColor(color));
                    });
                    if (msgQueue.size() != 0) {
                        nextMessage = false;
                        msgQueue.remove(0);
                        System.out.println("Removed");
                    }
                }
            } /*else {
                if(nextMessage) {
                    context.runOnUiThread(() -> {
                        tvUserIP.setText("No messages");
                        linearLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    });
                }
            }*/
        }
    }
}
