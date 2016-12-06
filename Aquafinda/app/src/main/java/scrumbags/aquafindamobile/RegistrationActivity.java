package scrumbags.aquafindamobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegistrationActivity extends AppCompatActivity {

	EditText passwordField, confirmField, usernameField;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);

		passwordField = (EditText)findViewById(R.id.passwordField);
		confirmField = (EditText)findViewById(R.id.confirmField);
		usernameField = (EditText)findViewById(R.id.usernameField);

		Button button = (Button) findViewById(R.id.registerButton);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
					String username = usernameField.getText().toString();
					String password = passwordField.getText().toString();
					if(username.length() >= 6 && username.length() <= 20){
						if(password.length() >= 6 && password.length() <= 40) {
							if(confirmField.getText().toString().equals(passwordField.getText().toString())) {
								User user = CustomLoginActivity.client.registerUser(
										new User(
												((EditText) findViewById(R.id.firstField)).getText().toString(),
												((EditText) findViewById(R.id.lastField)).getText().toString(),
												username,
												Authorization.valueOf(((Spinner) findViewById(R.id.accountTypeSpinner)).getSelectedItem().toString().toLowerCase())),
										password);
								if(user == null) {
									usernameField.setError("Username taken.");
								} else {
									CustomLoginActivity.user = user;
									Client.user = user;
									CustomLoginActivity.client.requestAllReports();
									Intent intent = new Intent(getApplicationContext(), MainActivity.class);
									startActivity(intent);
								}
							} else {
								confirmField.setError("Passwords don't match.");
							}
						} else {
							passwordField.setError("Password not between 6 and 40 characters.");
						}
					} else {
						usernameField.setError("Username not between 6 and 20 characters.");
					}
				}
		});
		Button button2 = (Button) findViewById(R.id.cancelButton);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), CustomLoginActivity.class);
				startActivity(intent);
			}
		});

		final Spinner countryView = (Spinner) findViewById(R.id.accountTypeSpinner);
		final ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.account_type_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		countryView.setAdapter(adapter);
	}
}
