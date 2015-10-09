package com.peixin.gameforum;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;

public class FrontPage extends Activity {

	MediaPlayer ourSong;
	private SharedPreferences sp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.front_page);
		ourSong = MediaPlayer.create(this, R.raw.deserter);
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		if (sp.getBoolean("musicCheck", true))
			ourSong.start();

		Thread timer = new Thread() {

			public void run() {
				try {
					sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent nextAction = new Intent("com.peixin.gameforum.GAMELIST");
					startActivity(nextAction);
				}

			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (sp.getBoolean("musicCheck", true))
			ourSong.release();
		finish();
	}

}