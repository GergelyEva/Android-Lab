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

        // Create and add voters to the array
        voters = new Voter[2]; // Assuming you want to add 2 voters
        voters[0] = new Voter("0987654321", generateOTP(), ""); // Generate OTP here
        voters[1] = new Voter("1234567890", generateOTP(), ""); // Generate OTP here

        // Initially, hide the OTP and vote fields, and show only the phone number field and send button
        otpEditText.setVisibility(View.GONE);
        voteEditText.setVisibility(View.GONE);
        sendButton1.setVisibility(View.GONE);
        sendButtonMiddle.setVisibility(View.GONE);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get phone number entered by the user
                String phoneNumber = phoneNumberEditText.getText().toString();

                // Check if the phone number is valid
                if (isValidPhoneNumber(phoneNumber)) {
                    String otp = getOTPByPhoneNumber(phoneNumber);

                    // Send notification with OTP
                    sendNotification(phoneNumber, otp);

                    // Show OTP field
                    otpEditText.setVisibility(View.VISIBLE);

                    // Hide sendButton and show sendButtonMiddle
                    sendButton.setVisibility(View.GONE);
                    sendButtonMiddle.setVisibility(View.VISIBLE);
                } else {
                    // Show toast for invalid phone number
                    Toast.makeText(MainActivity.this, "Invalid phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sendButtonMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get phone number and OTP entered by the user
                String phoneNumber = phoneNumberEditText.getText().toString();
                String enteredOTP = otpEditText.getText().toString();

                // Verify OTP
                if (isValidPhoneNumber(phoneNumber) && isValidOTP(enteredOTP, phoneNumber)) {
                    // Send notification for voting options
                    sendVotingOptionsNotification();

                    // Hide sendButtonMiddle and show sendButton1 and voteEditText
                    sendButtonMiddle.setVisibility(View.GONE);
                    sendButton1.setVisibility(View.VISIBLE);
                    voteEditText.setVisibility(View.VISIBLE);
                } else {
                    // Show toast for invalid OTP
                    Toast.makeText(MainActivity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sendButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send vote button clicked
                String vote = voteEditText.getText().toString();
                // Update the vote for the respective voter
                voters[0].setVote(vote); // Assuming you want to update the first voter's vote
                // For simplicity, just show a toast with the selected vote
                Toast.makeText(MainActivity.this, "Vote submitted for: " + vote, Toast.LENGTH_SHORT).show();
                sendThankYouNotification();
                finish();

            }
        });
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Implement your phone number validation logic here
        // For simplicity, I'm just checking if it's not empty
        return !phoneNumber.isEmpty();
    }

    private boolean isValidOTP(String enteredOTP, String phoneNumber) {
        // Validate OTP
        for (Voter voter : voters) {
            if (voter.getPhoneNumber().equals(phoneNumber) && voter.getOtpCode().equals(enteredOTP)) {
                return true;
            }
        }
        return false;
    }

    private String generateOTP() {
        // Implement your OTP generation logic here
        // For simplicity, I'm just generating a random 4-digit number
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
        // Create notification channel
        createNotificationChannel();

        // Build notification for OTP
        NotificationCompat.Builder otpBuilder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("OTP Received")
                .setContentText("Your OTP for " + phoneNumber + " is: " + otp)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Show OTP notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, otpBuilder.build());
    }

    private void sendVotingOptionsNotification() {
        // Build notification for voting options
        NotificationCompat.Builder voteBuilder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Vote Options")
                .setContentText("Vote for 1.aur 2.pnl")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Show voting options notification with a unique ID
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, voteBuilder.build());
    }

    private void sendThankYouNotification() {
        // Build notification for voting options
        NotificationCompat.Builder voteBuilder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Thank you")
                .setContentText("Thank you for your vote, have a nice day!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Show voting options notification with a unique ID
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
