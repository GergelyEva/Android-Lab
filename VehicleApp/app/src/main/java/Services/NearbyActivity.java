package Services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vehicleapp.R;

import Services.Hotels.NearbyHotels;
import Services.Auto.NearbyServices;
import Services.Restaurants.NearbyRestaurants;

public class NearbyActivity extends AppCompatActivity {

    private CompanyDataSource dataSource;
    private CompanyDataSource dataSourceRestaurant;
    private CompanyDataSource dataSourceHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_services);

        dataSource = new CompanyDataSource(this);

        Button autoServices = findViewById(R.id.buttonAutoService);
        autoServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Assuming NearbyServices is the fragment class you want to display
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new NearbyServices()) // Pass an instance of the fragment class
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .commit();

                // Example: Saving auto services data to database
                saveAutoServicesDataToDatabase();
            }
        });

        // Initialize restaurant services
        dataSourceRestaurant = new CompanyDataSource(this);

        Button restaurantsbtn = findViewById(R.id.buttonRestaurant);
        restaurantsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Assuming NearbyRestaurants is the fragment class you want to display
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new NearbyRestaurants()) // Pass an instance of the fragment class for restaurant services
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .commit();

                // Example: Saving restaurant data to database
                saveRestaurantDataToDatabase();
            }
        });

        Button hotelbtn = findViewById(R.id.buttonHotel);
        hotelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Assuming NearbyRestaurants is the fragment class you want to display
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new NearbyHotels()) // Pass an instance of the fragment class for restaurant services
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .commit();

                // Example: Saving restaurant data to database
                saveHotelDataToDatabase();
            }
        });



    }

    private void saveAutoServicesDataToDatabase() {
        dataSource.open();

        // Add auto service companies to the database
        Company company1 = new Company();
        company1.setName("LANZS Autószerviz");
        company1.setLocation("Agyag u.,10");
        company1.setLatitude("48.001923");
        company1.setLongitude("21.723726");
        company1.setPhoneNumber("+36704337993");
        dataSource.addCompany(company1);

        Company company2 = new Company();
        company2.setName("Green Car Park Kft.");
        company2.setLocation("Debreceni út 318-A");
        company2.setLatitude("47.915041660826624");
        company2.setLongitude("21.724360787249484");
        company2.setPhoneNumber("+36 20 943 6460");
        dataSource.addCompany(company2);

        Company company3 = new Company();
        company3.setName("Kovács Bosch Car Service");
        company3.setLocation("Csermely köz 10");
        company3.setLatitude("47.953754037972175");
        company3.setLongitude("21.774052615448774");
        company3.setPhoneNumber("+3642480203");
        dataSource.addCompany(company3);

        dataSource.close();
    }

    private void saveRestaurantDataToDatabase() {
        dataSourceRestaurant.open();

        // Add restaurant companies to the database
        Company company4 = new Company();
        company4.setName("Szindbád Bisztró Nyíregyháza");
        company4.setLocation("Szinhaz u.,2");
        company4.setLatitude("47.95628322004887");
        company4.setLongitude("21.71137835067523");
        company4.setPhoneNumber("+36300724040");
        dataSourceRestaurant.addCompany(company4);

        Company company2 = new Company();
        company2.setName("Gedeon - Görög Étel es Kézműves Sör");
        company2.setLocation("Bethlen Gábor u. 2");
        company2.setLatitude("47.957545867422894");
        company2.setLongitude("21.714642744906367");
        company2.setPhoneNumber("+3642796719");
        dataSourceRestaurant.addCompany(company2);

        Company company3 = new Company();
        company3.setName("Umami Bar es Burger");
        company3.setLocation("Hunyadi u. 36");
        company3.setLatitude("47.96202622242975");
        company3.setLongitude("21.720316780645383");
        company3.setPhoneNumber("+36204201220");
        dataSourceRestaurant.addCompany(company3);

        dataSourceRestaurant.close();
    }

    private void saveHotelDataToDatabase() {
        dataSourceRestaurant.open();

        // Add restaurant companies to the database
        Company company4 = new Company();
        company4.setName("Park Hotel");
        company4.setLocation("Sostoi u.,70/a");
        company4.setLatitude("48.007877938503555");
        company4.setLongitude("21.722733300195205");
        company4.setPhoneNumber("+36701678934");
        dataSourceRestaurant.addCompany(company4);

        Company company2 = new Company();
        company2.setName("Sosto Noise");
        company2.setLocation("Szoda u. 14");
        company2.setLatitude("48.00719992679272");
        company2.setLongitude("21.730929116555597");
        company2.setPhoneNumber("+36302736646");
        dataSourceRestaurant.addCompany(company2);

        Company company3 = new Company();
        company3.setName("Hotel Jungle");
        company3.setLocation("blaha Lujza stny. 41");
        company3.setLatitude("48.00153255022875");
        company3.setLongitude("21.72747396807246");
        company3.setPhoneNumber("+3642479710");
        dataSourceRestaurant.addCompany(company3);

        dataSourceRestaurant.close();
    }

}
