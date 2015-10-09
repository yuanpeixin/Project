package com.example.hangout;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.peixin.hangout.R;

public class WeatherXMLParse extends Activity implements OnClickListener {

	static final String baseURL = "http://www.google.com/ig/api?weather=";
	EditText city, state;
	TextView weather;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xmlparse);
		Button b = (Button) findViewById(R.id.bWeather);
		city = (EditText) findViewById(R.id.etCity);
		state = (EditText) findViewById(R.id.etState);
		weather = (TextView) findViewById(R.id.tvWeather);

		b.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String c = city.getText().toString();
		String s = state.getText().toString();
		
		StringBuilder URL = new StringBuilder(baseURL);
		URL.append(c + "," + s);
		String fullUrl = URL.toString();
		try{
			URL website = new URL(fullUrl);
			//geting xml reader to parse data
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			
			HandlingXML doingWork = new HandlingXML();
			xr.setContentHandler(doingWork);
			xr.parse(new InputSource(website.openStream()));
			
			String information = doingWork.getInformation();
			weather.setText(information);
		}catch (Exception e){
			weather.setText("error");
		}
		
	}

}