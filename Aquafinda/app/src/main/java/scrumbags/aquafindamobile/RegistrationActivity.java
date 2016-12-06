package scrumbags.aquafindamobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class RegistrationActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);

		Button button= (Button) findViewById(R.id.registerButton);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Register
			}
		});
		Button button2= (Button) findViewById(R.id.cancelButton);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), CustomLoginActivity.class);
				startActivity(intent);
			}
		});

		final Spinner countryView = (Spinner) findViewById(R.id.typeSpinner);
		final ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.account_type_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		countryView.setAdapter(adapter);
	}
}
