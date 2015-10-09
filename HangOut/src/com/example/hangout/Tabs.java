package com.example.hangout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import com.peixin.hangout.R;
import android.widget.TextView;

public class Tabs extends Activity implements OnClickListener {

	TabHost th;
	TextView showResults;
	long start, stop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		th = (TabHost) findViewById(R.id.tabhost);
		Button newTab = (Button) findViewById(R.id.bAddTab);
		Button bStart = (Button) findViewById(R.id.bStartWatch);
		Button bStop = (Button) findViewById(R.id.bStropWatch);
		showResults = (TextView) findViewById(R.id.tvShowResults);
		start = stop = 0;
		
		newTab.setOnClickListener(this);
		bStart.setOnClickListener(this);
		bStop.setOnClickListener(this);

		th.setup();
		TabSpec specs = th.newTabSpec("tag1");
		specs.setContent(R.id.tab1);
		specs.setIndicator("StopWatch");
		th.addTab(specs);

		specs = th.newTabSpec("tag2");
		specs.setContent(R.id.tab2);
		specs.setIndicator("Tab 2");
		th.addTab(specs);

		specs = th.newTabSpec("tag3");
		specs.setContent(R.id.tab3);
		specs.setIndicator("Add a Tab");
		th.addTab(specs);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.bAddTab:

			TabSpec ourSpec = th.newTabSpec("tag1");
			ourSpec.setContent(new TabHost.TabContentFactory() {

				@Override
				public View createTabContent(String tag) {
					// TODO Auto-generated method stub
					TextView text = new TextView(Tabs.this);
					text.setText("New Tab!!");
					return text;
				}
			});
			ourSpec.setIndicator("New");
			th.addTab(ourSpec);

			break;
		case R.id.bStartWatch:
			start = System.currentTimeMillis();
			
			break;
		case R.id.bStropWatch:
			stop = System.currentTimeMillis();
			if(start!=0){
				long result = stop - start;
				int millis = (int) result;
				int seconds = millis / 1000;
				int minutes = seconds / 60;
				millis %= 100;
				seconds %= 60;
				
				showResults.setText(String.format("%d:%02d:%02d",minutes,seconds,millis));
			}
			break;
		}
	}

}
