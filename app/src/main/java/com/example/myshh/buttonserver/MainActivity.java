package com.example.myshh.buttonserver;

import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ServerSocket serverSocket;
    List<String> msgQueue;
    TextView textViewIP;
    ConnectionReceivingThread connectionReceivingThread;
    ViewingThread viewingThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewIP = findViewById(R.id.textViewIP);

        msgQueue = new ArrayList<>();
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);

        textViewIP.setText(Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress()));

        connectionReceivingThread = new ConnectionReceivingThread(serverSocket, msgQueue);
        connectionReceivingThread.start();
        viewingThread = new ViewingThread(msgQueue, this);
        viewingThread.start();
    }

    public void btnNextMessageClick(View v){
        if(msgQueue.size() == 0) {
            final TextView tvUserIP = this.findViewById(R.id.tvUserIP);
            final LinearLayout linearLayout = this.findViewById(R.id.linearLayout);
            tvUserIP.setText("No messages");
            linearLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        viewingThread.nextMessage = true;
    }
}
