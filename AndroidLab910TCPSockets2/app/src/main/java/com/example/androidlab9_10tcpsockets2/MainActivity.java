package com.example.androidlab9_10tcpsockets2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private TextView tvReceiveData;
    private EditText etServerName, etServerPort;
    private Button btnClientConnect;
    private String serverName;
    private int serverPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvReceiveData = findViewById(R.id.tvReceivedData);
        etServerName = findViewById(R.id.etServerName);
        etServerPort = findViewById(R.id.etServerPort);
        btnClientConnect = findViewById(R.id.buttonconnect);
    }

    public void onClickConnect(View view) {
        serverName = etServerName.getText().toString();
        serverPort = Integer.parseInt(etServerPort.getText().toString());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket(serverName, serverPort);
                    BufferedReader br_input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    final String txtFromServer = br_input.readLine();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvReceiveData.setText("Connected to Server!");
                        }
                    });

                    // Close resources
                    br_input.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
