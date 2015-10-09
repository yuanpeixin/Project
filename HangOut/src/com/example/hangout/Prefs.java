package com.example.hangout;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.peixin.hangout.R;

public class Prefs extends PreferenceActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);
	}

}
