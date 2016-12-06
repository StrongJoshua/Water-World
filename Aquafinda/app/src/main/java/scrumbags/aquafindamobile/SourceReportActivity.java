package scrumbags.aquafindamobile;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class SourceReportActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
		GoogleApiClient.OnConnectionFailedListener, LocationListener {

	EditText latText;
	EditText longText;

	WaterType type;
	WaterCondition cond;

	double lat;
	double longt;

	LocationManager mLocationManager;

	GoogleApiClient mGoogleApiClient;
	Location location;
	private LocationRequest mLocationRequest;

	protected LocationManager locationManager;
	protected LocationListener locationListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_source_report);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		mGoogleApiClient = new GoogleApiClient.Builder(this)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.addApi(LocationServices.API)
				.build();
		// Create the LocationRequest object
		mLocationRequest = LocationRequest.create()
				.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
				.setInterval(10 * 1000)        // 10 seconds, in milliseconds
				.setFastestInterval(1 * 1000); // 1 second, in milliseconds

		latText = (EditText) findViewById(R.id.lat);
		longText = (EditText) findViewById(R.id.longt);


		final Spinner countryView = (Spinner) findViewById(R.id.typeSpinner);
		final ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.type_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		final Spinner countryView2 = (Spinner) findViewById(R.id.condSpinner);
		ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.condition_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		countryView.setAdapter(adapter);
		countryView2.setAdapter(adapter2);


		Button button = (Button) findViewById(R.id.submitButton);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (latText.getText().toString().length() > 0 && longText.getText().toString().length() > 0) {
					lat = Double.parseDouble(latText.getText().toString());
					longt = Double.parseDouble(longText.getText().toString());
				}
				String t = countryView.getSelectedItem().toString();
				String c = countryView2.getSelectedItem().toString();

				for (WaterType wt : WaterType.values()) {
					if (wt.toString().equals(t))
						type = wt;
				}
				for (WaterCondition wc : WaterCondition.values()) {
					if (wc.toString().equals(c))
						cond = wc;
				}

				Client client = CustomLoginActivity.client;
				WaterSourceReport report = new WaterSourceReport(new WaterLocation(lat, longt),
						type, cond, client.user.getFullName());

				System.out.println(report);
				if (client.sendSourceReport(report)) {
					Snackbar.make(v, "Success", Snackbar.LENGTH_LONG)
							.setAction("Action", null).show();
					Intent intent = new Intent(getApplicationContext(), MainActivity.class);
					startActivity(intent);
				} else {
					Snackbar.make(v, "Fuck You", Snackbar.LENGTH_LONG)
							.setAction("Action", null).show();
				}
			}
		});

		Button button2 = (Button) findViewById(R.id.cancelButton);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
			}
		});

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);


		Client.updateable = null;
	}

	@Override
	public void onConnected(@Nullable Bundle bundle) {
		if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return;
		}
		location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
		if (location == null) {
			LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (com.google.android.gms.location.LocationListener) getApplicationContext());
			latText.setText(location.getLatitude() + "");
			longText.setText(location.getLongitude() + "");
			System.out.println(location.getLatitude() + " " + location.getLongitude());
		}
		else {
			//handleNewLocation(location);
		}

	}

	@Override
	public void onConnectionSuspended(int i) {

	}

	@Override
	public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

	}

	@Override
	public void onLocationChanged(Location location) {
		//txtLat = (TextView) findViewById(R.id.textview1);
		//txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
		System.out.println("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());

	}

	@Override
	public void onStatusChanged(String s, int i, Bundle bundle) {

	}

	@Override
	public void onProviderEnabled(String s) {

	}

	@Override
	public void onProviderDisabled(String s) {

	}
}