package scrumbags.aquafindamobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CustomLoginActivity extends AppCompatActivity {
	public static Client client;
	public static User user;
	boolean connect;
	EditText username;
	EditText password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_login);

		username = (EditText) findViewById(R.id.usernameField);
		password = (EditText) findViewById(R.id.passwordField);
		client = new Client();
		client.start();

		Button button= (Button) findViewById(R.id.loginButton);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				user = client.loginUser(username.getText().toString(), password.getText().toString());
				System.out.println("Reconnecting");
				if(client.isLoggedIn()) {
					Log.d(user.toString(),"");
					client.requestAllReports();
					Intent intent = new Intent(getApplicationContext(), MainActivity.class);
					startActivity(intent);
				}
				else
				{
					password.setError("Incorrect Login!");
					Snackbar.make(v, "Fuck You", Snackbar.LENGTH_LONG)
							.setAction("Action", null).show();
				}
			}
		});

		Button button2= (Button) findViewById(R.id.registerButton);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
				startActivity(intent);
			}
		});
		Client.updateable = null;
	}
	public  void disconnect () {
		if(connect) {
			user = null;
			client = new Client();
			client.start();
			user = client.loginUser(username.getText().toString(), password.getText().toString());
			System.out.println("Reconnecting");
			connect = false;
		}
	}
}
