package com.example.hangout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.peixin.hangout.R;
import android.widget.TextView;

public class ReceiveData extends Activity implements OnClickListener, OnCheckedChangeListener{

	TextView question, answer;
	Button bReturn;
	RadioGroup rGroup;
	String name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_receive);
		initialize();
		getName();
		
		SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String et = getData.getString("name", "Simon");
		String values = getData.getString("list", "2");
		if(values.contentEquals("3")) question.setText(et); 
	}

	private void initialize(){
		question = (TextView) findViewById(R.id.rQuestion);
		answer = (TextView) findViewById(R.id.rAnswer);
		bReturn = (Button) findViewById(R.id.bReturn);
		rGroup = (RadioGroup) findViewById(R.id.rGroup);
		bReturn.setOnClickListener(this);
		rGroup.setOnCheckedChangeListener(this);
	}
	
	private void getName(){
		Bundle map = getIntent().getExtras();
		name = map.getString("name");
		question.setText(name + " is ");
	}
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		String temp = question.getText().toString();
		
		switch(checkedId){
		case R.id.rCrazy:
			temp += "Crazy";
			break;
		case R.id.rSmart:
			temp += "Smart";
			break;
		case R.id.rSexy:
			temp += "Sexy";
			break;
		}
		answer.setText(temp);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent();
		Bundle map = new Bundle();
		map.putString("answer", answer.getText().toString());
		i.putExtras(map);
		setResult(RESULT_OK,i);
		finish();
	}

}
