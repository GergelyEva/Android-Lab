package com.example.otpapp4;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText otpEditText, phoneNumberEditText, voteEditText;
    private Button sendButton, sendButton1, sendButtonMiddle;
    private Voter[] voters; // Array to store Voter objects

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumberEditText = findViewById(R.id.phoneNumberTextView);
        otpEditText = findViewById(R.id.otpEditText);
        voteEditText = findViewById(R.id.voteEditText);
        sendButton = findViewById(R.id.sendButton);
        sendButton1 = findViewById(R.id.sendButton1);
        sendButtonMiddle = findViewById(R.id.sendButtonmiddle);


        voters = new Voter[2];
        voters[0] = new Voter("0987654321", generateOTP(), "");
        voters[1] = new Voter("1234567890", generateOTP(), "");


        otpEditText.setVisibility(View.GONE);
        voteEditText.setVisibility(View.GONE);
        sendButton1.setVisibility(View.GONE);
        sendButtonMiddle.setVisibility(View.GONE);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneNumberEditText.getText().toString();

                if (isValidPhoneNumber(phoneNumber)) {
                    String otp = getOTPByPhoneNumber(phoneNumber);

                    sendNotification(phoneNumber, otp);

                    otpEditText.setVisibility(View.VISIBLE);

                    sendButton.setVisibility(View.GONE);
                    sendButtonMiddle.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sendButtonMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneNumberEditText.getText().toString();
                String enteredOTP = otpEditText.getText().toString();

                if (isValidPhoneNumber(phoneNumber) && isValidOTP(enteredOTP, phoneNumber)) {
                    sendVotingOptionsNotification();

                    sendButtonMiddle.setVisibility(View.GONE);
                    sendButton1.setVisibility(View.VISIBLE);
                    voteEditText.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sendButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vote = voteEditText.getText().toString();
                voters[0].setVote(vote);
                Toast.makeText(MainActivity.this, "Vote submitted for: " + vote, Toast.LENGTH_SHORT).show();
                sendThankYouNotification();
                finish();

            }
        });
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return !phoneNumber.isEmpty();
    }

    private boolean isValidOTP(String enteredOTP, String phoneNumber) {
        for (Voter voter : voters) {
            if (voter.getPhoneNumber().equals(phoneNumber) && voter.getOtpCode().equals(enteredOTP)) {
                return true;
            }
        }
        return false;
    }

    private String generateOTP() {
        return String.valueOf((int) (Math.random() * 9000) + 1000);
    }

    private String getOTPByPhoneNumber(String phoneNumber) {
        for (Voter voter : voters) {
            if (voter.getPhoneNumber().equals(phoneNumber)) {
                return voter.getOtpCode();
            }
        }
        return null;
    }

    private void sendNotification(String phoneNumber, String otp) {
        createNotificationChannel();

        NotificationCompat.Builder otpBuilder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("OTP Received")
                .setContentText("Your OTP for " + phoneNumber + " is: " + otp)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, otpBuilder.build());
    }

    private void sendVotingOptionsNotification() {
        NotificationCompat.Builder voteBuilder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Vote Options")
                .setContentText("Vote for 1.aur 2.pnl")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, voteBuilder.build());
    }

    private void sendThankYouNotification() {

        NotificationCompat.Builder voteBuilder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Thank you")
                .setContentText("Thank you for your vote, have a nice day!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, voteBuilder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel Name";
            String description = "Channel Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel_id", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
