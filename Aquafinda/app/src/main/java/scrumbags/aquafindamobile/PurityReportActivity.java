package scrumbags.aquafindamobile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class PurityReportActivity extends AppCompatActivity {

	TextView latText;
	TextView longText;

	TextView virusText;
	TextView contText;

	EditText dateText;

	WaterType type;
	WaterPurityCondition cond;

	Spinner countryView;
	ArrayAdapter adapter;

	WaterSourceReport sourceReport;

	Calendar myCalendar;

	DatePickerDialog.OnDateSetListener date;

	double lat;
	double longt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_purity_report);

		for(WaterReport wr :Client.reports)
		{
			if(MainActivity.selectedID == wr.getId())
				sourceReport = (WaterSourceReport) wr;
		}

		latText = (TextView) findViewById(R.id.latText);
		longText = (TextView) findViewById(R.id.longText);

		latText.setText(sourceReport.getLocation().getLatitude() + "");
		longText.setText(sourceReport.getLocation().getLongitude() + "");

		virusText = (TextView) findViewById(R.id.virusText);
		contText = (TextView) findViewById(R.id.contaminantText);

		dateText = (EditText) findViewById(R.id.dateText);

		countryView = (Spinner) findViewById(R.id.overallConditionSpinner);
		adapter = ArrayAdapter.createFromResource(this, R.array.condition_array_2, android.R.layout.simple_spinner_item);
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
				WaterPurityReport report = new WaterPurityReport(sourceReport, myCalendar.getTime(), cond, virus, cont, CustomLoginActivity.user.getFullName());
				System.out.println(report);
				if (client.sendPurityReport(report, sourceReport.getId()))
				{
					Snackbar.make(v, "Success", Snackbar.LENGTH_LONG)
							.setAction("Action", null).show();
					Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
			}
		});

		 myCalendar = Calendar.getInstance();

		 date = new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
								  int dayOfMonth) {
				// TODO Auto-generated method stub
				myCalendar.set(Calendar.YEAR, year);
				myCalendar.set(Calendar.MONTH, monthOfYear);
				myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				dateText.setText(myCalendar.getTime() + "");

			}

		};
		Button button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(PurityReportActivity.this, date, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});


		Client.updateable = null;
	}
}
