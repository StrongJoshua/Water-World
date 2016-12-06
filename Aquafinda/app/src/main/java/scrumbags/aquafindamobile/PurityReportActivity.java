package scrumbags.aquafindamobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;

public class PurityReportActivity extends AppCompatActivity {

	TextView latText;
	TextView longText;

	TextView virusText;
	TextView contText;

	WaterType type;
	WaterPurityCondition cond;

	Spinner countryView;
	ArrayAdapter adapter;

	WaterSourceReport sourceReport;

	double lat;
	double longt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_purity_report);

		latText = (TextView) findViewById(R.id.latText);
		longText = (TextView) findViewById(R.id.longText);

		virusText = (TextView) findViewById(R.id.virusText);
		contText = (TextView) findViewById(R.id.contaminantText);

		countryView = (Spinner) findViewById(R.id.overallConditionSpinner);
		adapter = ArrayAdapter.createFromResource(this, R.array.type_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		countryView.setAdapter(adapter);


		Button button = (Button) findViewById(R.id.submitButton);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String t = countryView.getSelectedItem().toString();

				for(WaterPurityCondition wc: WaterPurityCondition.values())
				{
					if(wc.toString().equals(t))
						cond = wc;
				}

				double virus = Double.parseDouble(virusText.getText().toString());
				double cont = Double.parseDouble(contText.getText().toString());

				Client client = CustomLoginActivity.client;
				WaterPurityReport report = new WaterPurityReport(sourceReport, new Date(), cond, virus, cont, CustomLoginActivity.user.getFullName());
				System.out.println(report);
				if (client.sendPurityReport(report, sourceReport))
				{
					Snackbar.make(v, "Success", Snackbar.LENGTH_LONG)
							.setAction("Action", null).show();
					Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
					startActivity(intent);
				}
				else
				{
					Snackbar.make(v, "Fuck You", Snackbar.LENGTH_LONG)
							.setAction("Action", null).show();
				}
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
