package scrumbags.aquafindamobile;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

	boolean editMode;
	FloatingActionButton edit;
	Button submit, cancel;
	TextView email, address;
	EditText emailEdit, addressEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		editMode = false;

		edit = (FloatingActionButton) findViewById(R.id.fab);
		submit = (Button)findViewById(R.id.submitButton);
		cancel = (Button)findViewById(R.id.cancelButton);
		email = (TextView)findViewById(R.id.emailText);
		address = (TextView)findViewById(R.id.addressText);
		emailEdit = (EditText)findViewById(R.id.emailEdit);
		addressEdit = (EditText)findViewById(R.id.addressEdit);

		((TextView)findViewById(R.id.accountType)).setText(Client.user.getAuthorization().toString());
		email.setText(Client.user.getEmail());
		address.setText(Client.user.getAddress());
		email.setVisibility(View.VISIBLE);
		address.setVisibility(View.VISIBLE);
		emailEdit.setVisibility(View.GONE);
		addressEdit.setVisibility(View.GONE);

		edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				edit.setVisibility(View.GONE);
				submit.setVisibility(View.VISIBLE);
				cancel.setVisibility(View.VISIBLE);
				email.setVisibility(View.GONE);
				address.setVisibility(View.GONE);
				emailEdit.setVisibility(View.VISIBLE);
				addressEdit.setVisibility(View.VISIBLE);
				emailEdit.setText(email.getText());
				addressEdit.setText(address.getText());
			}
		});

		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				CustomLoginActivity.client.updateUserInfo(emailEdit.getText().toString(), addressEdit.getText().toString());
				edit.setVisibility(View.VISIBLE);
				submit.setVisibility(View.GONE);
				cancel.setVisibility(View.GONE);
				email.setVisibility(View.VISIBLE);
				address.setVisibility(View.VISIBLE);
				emailEdit.setVisibility(View.GONE);
				addressEdit.setVisibility(View.GONE);
				email.setText(emailEdit.getText());
				address.setText(addressEdit.getText());
			}
		});

		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				edit.setVisibility(View.VISIBLE);
				submit.setVisibility(View.GONE);
				cancel.setVisibility(View.GONE);
				email.setVisibility(View.VISIBLE);
				address.setVisibility(View.VISIBLE);
				emailEdit.setVisibility(View.GONE);
				addressEdit.setVisibility(View.GONE);
			}
		});
	}

}
