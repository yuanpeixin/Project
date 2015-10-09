package com.peixin.gameforum;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Setting extends Activity implements OnSeekBarChangeListener {

	private AudioManager am;
	private SeekBar sb;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.setting);
		sb = (SeekBar) findViewById(R.id.seekBar1);
		CheckBox cb = (CheckBox) findViewById(R.id.sCheckBox);
		am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		cb.setChecked(sp.getBoolean("musicCheck", true));
		int curV = am.getStreamVolume(AudioManager.STREAM_MUSIC);
		int maxV = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		sb.setMax(maxV);
		sb.setProgress(curV);
		sb.setOnSeekBarChangeListener(this);
		if (sp.getBoolean("musicCheck", true))
			GameList.mp.start();
		cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Editor edit = sp.edit();
				if (!isChecked) {
					edit.putBoolean("musicCheck", false);
					edit.commit();
					GameList.mp.pause();
					;
				} else {
					edit.putBoolean("musicCheck", true);
					edit.commit();
					GameList.mp.start();
				}
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		if (sp.getBoolean("musicCheck", true))
			GameList.mp.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (sp.getBoolean("musicCheck", true))
			GameList.mp.pause();
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
	}

	public void Back(View v) {
		finish();
	}
}
