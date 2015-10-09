package com.peixin.gameforum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class GameList extends ListActivity {

	private String[] names = new String[] { "Legion of Heroes", "Word of Warcraft", "Dragon Next", "Final Fantasy XIV",
			"League of Legends" };

	private int[] images = new int[] { R.drawable.legion_of_heroes, R.drawable.wow, R.drawable.dragon_nest,
			R.drawable.ffxiv, R.drawable.lol };

	private InterstitialAd adView;
	private SharedPreferences sp;
	public static MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.game_list);

		adView = new InterstitialAd(this);
		adView.setAdUnitId("ca-app-pub-3673983865885023/7580559792");
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		mp = MediaPlayer.create(this, R.raw.music);

		if (sp.getBoolean("musicCheck", true))
			mp.start();

		adView.setAdListener(new AdListener() {
			@Override
			public void onAdClosed() {
				loadAd();
			}

		});

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < names.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("image", Integer.toString(images[i]));
			map.put("name", names[i]);
			list.add(map);
		}

		String[] from = { "image", "name" };
		int[] to = { R.id.sgImage, R.id.sgName };
		ListAdapter adapter = new SimpleAdapter(this, list, R.layout.single_game, from, to);
		setListAdapter(adapter);
		loadAd();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (sp.getBoolean("musicCheck", true))
			mp.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (sp.getBoolean("musicCheck", true))
			mp.start();

	}

	public void logIn(View v) {
		Intent i = new Intent(this, Login.class);
		startActivity(i);
	}

	public void Setting(View v) {
		Intent i = new Intent(this, Setting.class);
		startActivity(i);
	}

	protected void loadAd() {
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (adView.isLoaded()) {
			adView.show();
			if (sp.getBoolean("musicCheck", true))
				mp.start();
		}
		String gameName = ((TextView) v.findViewById(R.id.sgName)).getText().toString();
		StoredData.getInstance().setGame(gameName);
		Intent i = new Intent(this, ReadComments.class);
		startActivity(i);
	}

}