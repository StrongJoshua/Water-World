package scrumbags.aquafindamobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class HistoricalReportPromptActivity extends AppCompatActivity {

	public static WaterSourceReport sourceReport;
	public static String reportType;
	public  static  String year;

	TextView latText;
	TextView longText;

	Spinner countryView;
	ArrayAdapter adapter;

	Spinner countryView2;
	ArrayAdapter adapter2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_historical_report_prompt);

		for(WaterReport wr :Client.reports)
		{
			if(MainActivity.selectedID == wr.getId())
				sourceReport = (WaterSourceReport) wr;
		}

		latText = (TextView) findViewById(R.id.lat);
		longText = (TextView) findViewById(R.id.longt);

		latText.setText(sourceReport.getLocation().getLatitude() + "");
		longText.setText(sourceReport.getLocation().getLongitude() + "");

		countryView = (Spinner) findViewById(R.id.typeSpinner);
		adapter = ArrayAdapter.createFromResource(this, R.array.hist_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		countryView.setAdapter(adapter);

		reportType = countryView.getSelectedItem().toString();

		countryView2 = (Spinner) findViewById(R.id.yearSpinner);
		adapter2 = ArrayAdapter.createFromResource(this, R.array.years_array, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		countryView2.setAdapter(adapter2);

		year = countryView2.getSelectedItem().toString();


		Button button = (Button) findViewById(R.id.submitButton);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), HistoricalReportActivity.class);
				startActivity(intent);
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

		Client.updateable = null;
	}
}
