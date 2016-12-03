package scrumbags.aquafindamobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SourceReportActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_source_report);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);


		Spinner countryView = (Spinner) findViewById(R.id.typeSpinner);
		ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.type_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		Spinner countryView2 = (Spinner) findViewById(R.id.condSpinner);
		ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.condition_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		countryView.setAdapter(adapter);
		countryView2.setAdapter(adapter2);
	}

}
