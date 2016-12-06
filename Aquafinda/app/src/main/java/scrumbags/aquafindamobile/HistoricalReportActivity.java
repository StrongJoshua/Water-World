package scrumbags.aquafindamobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class HistoricalReportActivity extends AppCompatActivity {

	private LineChart mChart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_historical_report);



		mChart = (LineChart) findViewById(R.id.chart1);

		// create a custom MarkerView (extend MarkerView) and specify the layout
		// to use for it
		//MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
		//mv.setChartView(mChart); // For bounds control
		//mChart.setMarker(mv); // Set the marker to the chart

		// x-axis limit line
		LimitLine llXAxis = new LimitLine(10f, "Index 10");
		llXAxis.setLineWidth(4f);
		llXAxis.enableDashedLine(10f, 10f, 0f);
		llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
		llXAxis.setTextSize(10f);

		XAxis xAxis = mChart.getXAxis();
		xAxis.enableGridDashedLine(10f, 10f, 0f);
		//xAxis.setValueFormatter(new MyCustomXAxisValueFormatter());
		//xAxis.addLimitLine(llXAxis); // add x-axis limit line


		//Typeface tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

		LimitLine ll1 = new LimitLine(150f, "Upper Limit");
		ll1.setLineWidth(4f);
		ll1.enableDashedLine(10f, 10f, 0f);
		ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
		ll1.setTextSize(10f);
		//ll1.setTypeface(tf);

		LimitLine ll2 = new LimitLine(-30f, "Lower Limit");
		ll2.setLineWidth(4f);
		ll2.enableDashedLine(10f, 10f, 0f);
		ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
		ll2.setTextSize(10f);
		//ll2.setTypeface(tf);

		YAxis leftAxis = mChart.getAxisLeft();
		leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
		leftAxis.addLimitLine(ll1);
		leftAxis.addLimitLine(ll2);
		leftAxis.setAxisMaximum(200f);
		leftAxis.setAxisMinimum(-50f);
		//leftAxis.setYOffset(20f);
		leftAxis.enableGridDashedLine(10f, 10f, 0f);
		leftAxis.setDrawZeroLine(false);

		// limit lines are drawn behind data (and not on top)
		leftAxis.setDrawLimitLinesBehindData(true);

		mChart.getAxisRight().setEnabled(false);

		//mChart.getViewPortHandler().setMaximumScaleY(2f);
		//mChart.getViewPortHandler().setMaximumScaleX(2f);


		List<Entry> entries = new ArrayList<Entry>();

			// turn your data into Entry objects
			entries.add(new Entry(3, 5));

		LineDataSet dataSet = new LineDataSet(entries, "Label");

		LineData lineData = new LineData(dataSet);

		// add data
		mChart.setData(lineData);

//        mChart.setVisibleXRange(20);
//        mChart.setVisibleYRange(20f, AxisDependency.LEFT);
//        mChart.centerViewTo(20, 50, AxisDependency.LEFT);

		mChart.animateX(2500);
		//mChart.invalidate();

		// get the legend (only possible after setting data)
		Legend l = mChart.getLegend();

		// modify the legend ...
		l.setForm(Legend.LegendForm.LINE);

		// // dont forget to refresh the drawing
		// mChart.invalidate();
	}
}
