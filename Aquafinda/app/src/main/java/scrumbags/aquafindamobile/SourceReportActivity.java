package scrumbags.aquafindamobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SourceReportActivity extends AppCompatActivity {


	WaterType type;
	WaterCondition cond;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_source_report);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		final EditText latText = (EditText) findViewById(R.id.lat);
		final EditText longText = (EditText) findViewById(R.id.longt);

		final Spinner countryView = (Spinner) findViewById(R.id.typeSpinner);
		final ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.type_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		final Spinner countryView2 = (Spinner) findViewById(R.id.condSpinner);
		ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.condition_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		countryView.setAdapter(adapter);
		countryView2.setAdapter(adapter2);

		final double lat = Double.parseDouble(latText.getText().toString());
		final double longt = Double.parseDouble(longText.getText().toString());
		String t = countryView.getSelectedItem().toString();
		String c = countryView2.getSelectedItem().toString();

		for(WaterType wt:WaterType.values())
		{
			if(wt.toString().equals(t))
				type = wt;
		}
		for(WaterCondition wc:WaterCondition.values())
		{
			if(wc.toString().equals(c))
				cond = wc;
		}

		Button button = (Button) findViewById(R.id.submitButton);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Client client = CustomLoginActivity.client;
				WaterSourceReport report = new WaterSourceReport(new WaterLocation(lat,longt ),
						type , cond, CustomLoginActivity.user.getFullName());
				client.sendSourceReport(report);
			}
		});

		Button button2 = (Button) findViewById(R.id.cancelButton);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
				startActivity(intent);
			}
		});

	}

}
