package com.example.hangout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.peixin.hangout.R;

public class SQLite extends Activity implements OnClickListener {

	Button sqlUpdate, sqlView, sqlModify, sqlGetInfo, sqlDelete;
	EditText sqlName, sqlHotness, sqlRow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqllite);

		sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
		sqlName = (EditText) findViewById(R.id.etSQLName);
		sqlHotness = (EditText) findViewById(R.id.etSQLHotness);
		sqlRow = (EditText) findViewById(R.id.etSQLrowInfo);
		sqlView = (Button) findViewById(R.id.bSQLopenView);
		sqlModify = (Button) findViewById(R.id.bSQLmodify);
		sqlGetInfo = (Button) findViewById(R.id.bSQLGetInfo);
		sqlDelete = (Button) findViewById(R.id.bSQLdelete);

		sqlUpdate.setOnClickListener(this);
		sqlView.setOnClickListener(this);
		sqlModify.setOnClickListener(this);
		sqlGetInfo.setOnClickListener(this);
		sqlDelete.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.bSQLUpdate:
			boolean ifWork = true;
			try {
				String name = sqlName.getText().toString();
				String hotness = sqlHotness.getText().toString();
				HotOrNot entry = new HotOrNot(SQLite.this);
				entry.open();
				entry.createEntry(name, hotness);
				entry.close();
			} catch (Exception e) {
				ifWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Damn it!!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			} finally {
				if (ifWork) {
					Dialog d = new Dialog(this);
					d.setTitle("It worked!!");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
			}
			break;
		case R.id.bSQLopenView:
			Intent i = new Intent(this, SQLView.class);
			startActivity(i);
			break;

		case R.id.bSQLmodify:
			String sRow = sqlRow.getText().toString();
			String mName = sqlName.getText().toString();
			String mHotness = sqlHotness.getText().toString();
			long lRow = Long.parseLong(sRow);

			HotOrNot ex = new HotOrNot(this);
			ex.open();
			ex.updateEntry(lRow, mName, mHotness);
			ex.close();

			break;
		case R.id.bSQLGetInfo:
			String s = sqlRow.getText().toString();
			long l = Long.parseLong(s);
			HotOrNot hon = new HotOrNot(this);
			hon.open();
			String returnedName = hon.getName(l);
			String returnedHotness = hon.getHotness(l);
			hon.close();
			sqlName.setText(returnedName);
			sqlHotness.setText(returnedHotness);
			break;
		case R.id.bSQLdelete:
			String sRow1 = sqlRow.getText().toString();
			long lRow1 = Long.parseLong(sRow1);
			HotOrNot ex1 = new HotOrNot(this);
			ex1.open();
			ex1.deleteEntry(lRow1);
			ex1.close();
			break;
		}
	}

}
