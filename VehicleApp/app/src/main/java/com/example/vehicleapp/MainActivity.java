package com.example.vehicleapp;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

import Services.NearbyActivity;

public class MainActivity extends AppCompatActivity implements LocationListener {
    //List of reasons for car breakdown
    private ArrayList<String> breakdownReasons;
    //Default location, in case that the app is run on the emulator, so it won't show the usual default
    double defaultLatitude = 48.001922460930615;
    double defaultLongitude = 21.723725990711486;
    private static final int PERMISSIONS_REQUEST_LOCATION = 1;
    private static final int PERMISSIONS_REQUEST_SMS = 2;

    private LocationManager locationManager;
    private ArrayList<String> emergencyContacts;
    private Location currentLocation;
    private TextView temperatureTextView;
    private TextView weatherConditionTextView;

    @Override
    public void onLocationChanged(Location location) {
        // Update current location
        currentLocation = location;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialization of the emergency contacts,
        // the exercise description required for it tob e hardcoded
        emergencyContacts = new ArrayList<>();
        emergencyContacts.add("+40743811531");

        // Initialize breakdown reasons
        initializeBreakdownReasons(); // Add this line to initialize breakdown reasons

        // Request location and SMS permissions separately
        requestLocationPermission();
        requestSmsPermission();

        // Initialize location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Set up SOS button click listener
        Button sosButton = findViewById(R.id.sos_button);
        sosButton.setOnClickListener(view -> sendSosSignal());

        // Initialize temperatureTextView
        temperatureTextView = findViewById(R.id.temperature_textview);

        // Initialize weatherConditionTextView
        weatherConditionTextView = findViewById(R.id.weather_condition_textview);

        // Fetch weather data when activity starts with the default location
        fetchWeatherData(defaultLatitude, defaultLongitude);

        Button nearbyServicesButton = findViewById(R.id.nearbyServices);
        nearbyServicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNearbyServices();
            }
        });

        Button diagnosticsButton = findViewById(R.id.diagnostics);
        diagnosticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the method to display a random breakdown reason
                displayRandomBreakdownReason();
            }
        });

        Button mapButton = findViewById(R.id.next_button);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMapActivity();
            }
        });
    }

    private void openMapActivity() {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    private void openNearbyServices() {
        Intent intent = new Intent(this, NearbyActivity.class);
        startActivity(intent);
    }

    private void requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_LOCATION);
        }
    }

    //Request permision for SMS
    private void requestSmsPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSIONS_REQUEST_SMS);
        }
    }

    //Permissions for location and sms
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                // Toast message that will show if the request is denied
                Toast.makeText(this, "Location permission is required to send SOS messages", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PERMISSIONS_REQUEST_SMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                // Toast message that will show if the request is denied
                Toast.makeText(this, "SMS permission is required to send SOS messages", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Update for location
    private void sendLocationUpdate() {
        String breakdownReason = "";
    //Randomizing a breakdown reason
        if (!breakdownReasons.isEmpty()) {
            // Select a random breakdown reason
            Random random = new Random();
            int index = random.nextInt(breakdownReasons.size());
            breakdownReason = breakdownReasons.get(index);
        }

        if (currentLocation != null) {
            // Message with user's location information and breakdown reason
            String message = "Current location: " + currentLocation.getLatitude() + ", " + currentLocation.getLongitude() +
                    " Weather condition: " + weatherConditionTextView.getText().toString() +
                    " Breakdown Reason: " + breakdownReason;

            // Sending message to emergency contacts
            for (String contact : emergencyContacts) {
                sendSms(contact, message);
            }

            // Toast with confirmation message
            Toast.makeText(this, "Location update sent to emergency contacts", Toast.LENGTH_SHORT).show();
        } else {
            // In case that the current location can't be determined, the default location will be used
            String message = "Current location: " + defaultLatitude + ", " + defaultLongitude +
                    " Weather condition: " + weatherConditionTextView.getText().toString() +
                    " Breakdown Reason: " + breakdownReason;

            // Sending message to emergency contacts
            for (String contact : emergencyContacts) {
                sendSms(contact, message);
            }

            // Toast with confirmation message
            Toast.makeText(this, "Location update sent to emergency contacts", Toast.LENGTH_SHORT).show();
        }
    }
//Checking permissions for location and sending message
    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    // Checking if permissions allowed for both location and SMS permissions

    private void sendSosSignal() {
        if (checkPermissions()) {
            // Sending default location update along with SOS signal
            sendLocationUpdate();
        }
    }
    //fetching weather data using OpenWeatherApi
    private void fetchWeatherData(double latitude, double longitude) {
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude +
                "&lon=" + longitude + "&appid=57e9f68b4e3750478e12167189acd74d";


        RequestQueue queue = Volley.newRequestQueue(this);
        //getting the temperature, based on the way it is described on the OpenWeatherApi
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONObject mainObject = response.getJSONObject("main");
                        double temperatureKelvin = mainObject.getDouble("temp");//getting the temperature in Kelvin
                        double temperatureCelsius = temperatureKelvin - 273.15; // Convert Kelvin to Celsius

                        // Update temperatureTextView with temperature in Celsius
                        temperatureTextView.setText(String.format("%.2f", temperatureCelsius) + "°C");

                        // Get weather condition (cloudy, sunny etc.)
                        JSONArray weatherArray = response.getJSONArray("weather");
                        String weatherCondition = "";
                        if (weatherArray.length() > 0) {
                            JSONObject weatherObject = weatherArray.getJSONObject(0);
                            weatherCondition = weatherObject.getString("main");
                        }

                        // Update weatherConditionTextView with weather condition
                        weatherConditionTextView.setText(weatherCondition);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
            error.printStackTrace();
        });

        // Add the request to the RequestQueue
        queue.add(jsonObjectRequest);
    }

    //sending text message (SOS message) with SMSManager
    private void sendSms(final String phoneNumber, final String message) {
        Timer smsSendingTimer = new Timer();
        smsSendingTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                } catch (Exception e) {
                    Log.e("MainActivity", "Failed to send SMS", e);
                    Toast.makeText(MainActivity.this, "Failed to send SMS", Toast.LENGTH_SHORT).show();
                }
            }
        }, 0, 30000); // Repeat every 30 seconds
    }

    //Creating and adding to breakdownReasons
    private void initializeBreakdownReasons() {
        breakdownReasons = new ArrayList<>();
        breakdownReasons.add("Mechanical Failure");
        breakdownReasons.add("Electrical Issues");
        breakdownReasons.add("Fuel System Problems");
        breakdownReasons.add("Overheating");
        breakdownReasons.add("Flat Tires");
        breakdownReasons.add("Battery Failure");
        breakdownReasons.add("Ignition System Problems");
        breakdownReasons.add("Brake System Issues");
        breakdownReasons.add("Exhaust System Failure");
        breakdownReasons.add("Transmission Problems");
        breakdownReasons.add("Engine Oil Issues");
        breakdownReasons.add("Fluid Leaks");
        breakdownReasons.add("Environmental Factors");
        breakdownReasons.add("Accidents or Collisions");
        breakdownReasons.add("Lack of Maintenance");
    }
//Randomizing and getting a breakdown reason
    private void displayRandomBreakdownReason() {
        if (breakdownReasons.isEmpty()) {
            // If the breakdown reasons list is empty, show a message
            Toast.makeText(this, "No breakdown reasons available", Toast.LENGTH_SHORT).show();
        } else {
            // Select a random breakdown reason
            Random random = new Random();
            int index = random.nextInt(breakdownReasons.size());
            String selectedReason = breakdownReasons.get(index);

            // Display the selected breakdown reason
            Toast.makeText(this, "Breakdown Reason: " + selectedReason, Toast.LENGTH_SHORT).show();
        }
    }
//Sending a message with the broadcast
    public void onBroadcastSendBtnClicked(View v){
        Intent intent = new Intent();
        intent.setAction("com.vehicleapp.sender");
intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
sendBroadcast(intent);
        Toast.makeText(this, "Broadcast sent ", Toast.LENGTH_SHORT).show();
    }
}
