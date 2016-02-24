package gpsuse.eggwallet.com.gpsuse;

import android.app.Activity;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UseNetwork extends Activity {

    Button btnShowLocation;
    AppLocationService appLocationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.use_network);
        location();
    }

    private void location() {
        appLocationService = new AppLocationService(UseNetwork.this);
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Location nwLocation = appLocationService.getLocation(LocationManager.NETWORK_PROVIDER);
                if (nwLocation != null) {
                    double latitude = nwLocation.getLatitude();
                    double longitude = nwLocation.getLongitude();
                    Toast.makeText(getApplicationContext(), "Mobile Location :\nLatitude:" + latitude + "\nLongitude:" + longitude, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}