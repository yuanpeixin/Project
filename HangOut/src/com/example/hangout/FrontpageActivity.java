package com.example.hangout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import com.peixin.hangout.R;

public class FrontpageActivity extends Activity{
	
	MediaPlayer ourSong;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frong_page);
		
		ourSong = MediaPlayer.create(this,R.raw.deserter);
		SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		
		if(getPrefs.getBoolean("checkbox", true)) ourSong.start();
		
		Thread timer = new Thread(){
			
			public void run() {
				// TODO Auto-generated method stub
				try{
					sleep(5000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent nextAction = new Intent("com.example.hangout.MENU");
					startActivity(nextAction);
				}
				
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSong.release();
		finish();
	}

	
}
