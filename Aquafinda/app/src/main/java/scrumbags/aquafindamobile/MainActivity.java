package scrumbags.aquafindamobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener,OnMapReadyCallback {

	TextView name;
	TextView email;
	private GoogleMap mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map2);
		mapFragment.getMapAsync(this);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// Click action
				Intent intent = new Intent(getApplicationContext(), SourceReportActivity.class);
				startActivity(intent);
			}
		});
		FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
		fab2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// Click action
				Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
				startActivity(intent);
			}
		});

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
		View header=navigationView.getHeaderView(0);
/*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
		name = (TextView)header.findViewById(R.id.username_header);
		email = (TextView)header.findViewById(R.id.email_header);
		name.setText(Client.user.getFullName());
		email.setText(Client.user.getEmail());
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_about) {
			return true;
		}
		if (id == R.id.action_settings) {
			return true;
		}

		if (id == R.id.action_logout) {
			CustomLoginActivity.client.logout();
			Intent intent = new Intent(getApplicationContext(), CustomLoginActivity.class);
			startActivity(intent);
			return true;
		}


		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {

		// Create a new fragment and specify the fragment to show based on nav item clicked
		//Fragment fragment = null;
		//Class fragmentClass = null;

		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if (id == R.id.nav_source) {
			//fragmentClass = SourceReportActivity.class;
			Intent intent = new Intent(getApplicationContext(), SourceReportActivity.class);
			startActivity(intent);
		} else if (id == R.id.nav_all) {

		} else if (id == R.id.nav_historical) {

		} else if (id == R.id.nav_edit) {

		} else if (id == R.id.nav_logout) {
			CustomLoginActivity.client.logout();
			Intent intent = new Intent(getApplicationContext(), CustomLoginActivity.class);
			startActivity(intent);
		}
		/*try {
			fragment = (Fragment) fragmentClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Insert the fragment by replacing any existing fragment
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
		*/

		// Highlight the selected item has been done by NavigationView
		item.setChecked(true);
		// Set action bar title
		setTitle(item.getTitle());

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	/**
	 * Manipulates the map once available.
	 * This callback is triggered when the map is ready to be used.
	 * This is where we can add markers or lines, add listeners or move the camera. In this case,
	 * we just add a marker near Sydney, Australia.
	 * If Google Play services is not installed on the device, the user will be prompted to install
	 * it inside the SupportMapFragment. This method will only be triggered once the user has
	 * installed Google Play services and returned to the app.
	 */
	@Override
	public void onMapReady(GoogleMap googleMap) {
		mMap = googleMap;

		// Add a marker in Sydney and move the camera
		LatLng sydney = new LatLng(34, -84);
		mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
		for(WaterReport wr: Client.reports)
		{
			mMap.addMarker(new MarkerOptions().position(new LatLng(wr.getLocation().getLatitude(),wr.getLocation().getLongitude())).title(wr.toString()));
		}
	}
}
