package com.example.server;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivityServer extends AppCompatActivity {

    private TextView tvServerName, tvServerPort, tvStatus;
    private String serverIP = "192.168.1.11";
    private int serverPort = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_server);
        tvServerName = findViewById(R.id.tvServerName);
        tvServerPort = findViewById(R.id.tvServerPort);
        tvStatus = findViewById(R.id.tvServerStatus);

        tvServerName.setText(serverIP);
        tvServerPort.setText(String.valueOf(serverPort)); // Convert int to String for setText()
    }

    private ServerThread serverThread;

    public void OnClickStartServer(View view) {
        serverThread = new ServerThread();
        serverThread.startServer();
    }

    public void OnClickStopServer(View view) {
        serverThread.stopServer();
    }

    class ServerThread extends Thread {
        private boolean serverRunning;
        private ServerSocket serverSocket;
        private int count = 0;

        public void startServer() {
            serverRunning = true;
            start();
        }

        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(serverPort);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvStatus.setText("Waiting for clients");
                    }
                });
                while (serverRunning) {
                    Socket socket = serverSocket.accept();
                    count++;

                    final Socket finalSocket = socket; // Declare final variable for use in inner class
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvStatus.setText("Connected to: " + finalSocket.getInetAddress() + " : " + finalSocket.getLocalPort());
                        }
                    });

                    PrintWriter output_Server = new PrintWriter(socket.getOutputStream(), true); // Auto flush
                    output_Server.println("Welcome to Server" + count);
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void stopServer() {
            serverRunning = false;
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvStatus.setText("Server Stopped");
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
