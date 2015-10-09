package com.example.hangout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.peixin.hangout.R;

public class SQLView extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlview);
		TextView tv = (TextView) findViewById(R.id.tvSQLinfo);
		HotOrNot info = new HotOrNot(this);
		info.open();
		String data = info.getData();
		info.close();
		tv.setText(data);
	}

}
