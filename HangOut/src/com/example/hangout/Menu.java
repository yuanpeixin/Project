package com.example.hangout;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.peixin.hangout.R;

public class Menu extends ListActivity {

	String classes[] = { "MainActivity", "Text", "SendData", "GFX", "GFXSurface", "SoundStuff","Sliding","Tabs","SimpleBrowser","Flipper","Sharedpreference","InternalData","ExternalData","SQLite","Accelerate","HttpExample","WeatherXMLParse","GLExample","GLCubEX","Voice","TextVoice","StatusBar","SeekBarVolume"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String name = classes[position];

		try {
			Class listClass = Class.forName("com.example.hangout." + name);
			Intent intent = new Intent(Menu.this, listClass);
			startActivity(intent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.cool_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.abtouUs:
			Intent i = new Intent(this, AboutUs.class);
			startActivity(i);
			break;
		case R.id.preferences:
			Intent p = new Intent(this, Prefs.class);
			startActivity(p);
			break;
		case R.id.exit:
			finish();
			break;
		}
		return false;
	}

}