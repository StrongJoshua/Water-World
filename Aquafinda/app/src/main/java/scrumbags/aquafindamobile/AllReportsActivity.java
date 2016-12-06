package scrumbags.aquafindamobile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class AllReportsActivity extends AppCompatActivity implements Updateable {

	ListView lv;
	ArrayAdapter<WaterReport> arrayAdapter;
	boolean alt = true;
	LinearLayout linearLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_reports);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
			}
		});

		linearLayout = (LinearLayout)findViewById(R.id.allReportsList);
		for(WaterReport wr : Client.reports) {
			TextView valueTV = new TextView(this);
			valueTV.setText(wr.toString());
			if(alt) {
				valueTV.setTextColor(Color.BLACK);
				valueTV.setBackgroundColor(Color.parseColor("#ffffff"));
				alt = false;
			}
			else
			{
				valueTV.setTextColor(Color.WHITE);
				valueTV.setBackgroundColor(Color.parseColor("#89C4F4"));
				alt = true;
			}
			valueTV.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT));
			linearLayout.addView(valueTV);

			Client.updateable = this;
		}

		/*lv = (ListView) findViewById(R.id.allReportsListView);
		// Create an ArrayAdapter from List
		arrayAdapter = new ArrayAdapter<WaterReport>(this, android.R.layout.simple_list_item_1, (List)Client.reports);
		// DataBind ListView with items from ArrayAdapter
		lv.setAdapter(arrayAdapter);*/
		   /*
                    notifyDataSetChanged ()
                        Notifies the attached observers that the underlying
                        data has been changed and any View reflecting the
                        data set should refresh itself.
                 */
		//arrayAdapter.notifyDataSetChanged();


	}
	int i = 0;
	@Override
	public void update(final List<WaterReport> newReports) {
		runOnUiThread (new Thread(new Runnable() {
			public void run() {
				for(WaterReport wr: newReports)
				{
					TextView valueTV = new TextView(getApplicationContext());
					valueTV.setText(wr.toString());
					if(alt) {
						valueTV.setTextColor(Color.BLACK);
						valueTV.setBackgroundColor(Color.parseColor("#ffffff"));
						alt = false;
					}
					else
					{
						valueTV.setTextColor(Color.WHITE);
						valueTV.setBackgroundColor(Color.parseColor("#4286f4"));
						alt = true;
					}
					valueTV.setLayoutParams(new LinearLayout.LayoutParams(
							LinearLayout.LayoutParams.WRAP_CONTENT,
							LinearLayout.LayoutParams.WRAP_CONTENT));
					linearLayout.addView(valueTV);
				}
			}
		}));
	}
}
