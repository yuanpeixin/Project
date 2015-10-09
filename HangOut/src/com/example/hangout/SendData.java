package com.example.hangout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.peixin.hangout.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SendData extends Activity implements OnClickListener{

	Button send, result;
	EditText sendET;
	TextView sendTV;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_send);
		initialiaze();
		
		AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

	}

	private void initialiaze() {
		send = (Button) findViewById(R.id.bSend);
		result = (Button) findViewById(R.id.bResult);
		sendET = (EditText) findViewById(R.id.sET);
		sendTV = (TextView) findViewById(R.id.sTV);
		send.setOnClickListener(this);
		result.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		case R.id.bSend:
			String temp = sendET.getText().toString();
			Bundle map = new Bundle();
			map.putString("name", temp);
			Intent a = new Intent(this,ReceiveData.class);
			a.putExtras(map);
			startActivityForResult(a,0);
			break;
		case R.id.bResult:
			Intent i = new Intent(this,ReceiveData.class);
			startActivityForResult(i,0);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			Bundle map = data.getExtras();
			sendTV.setText(map.getString("answer"));
		}
	}
	
}
