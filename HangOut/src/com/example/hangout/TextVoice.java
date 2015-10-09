package com.example.hangout;

import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.peixin.hangout.R;

public class TextVoice extends Activity	implements OnClickListener{

	static final String[] texts = {"SUp","Hello!","Fuck You","Goal!!!"};
	TextToSpeech tts;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.textvoice);
		Button b = (Button) findViewById(R.id.bTextVoice);
		b.setOnClickListener(this);
		tts = new TextToSpeech(TextVoice.this, new TextToSpeech.OnInitListener() {
			
			@Override
			public void onInit(int status) {
				// TODO Auto-generated method stub
				if(status != TextToSpeech.ERROR){
					tts.setLanguage(Locale.US);
				}
			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if(tts != null){
			tts.stop();
			tts.shutdown();
		}
		super.onPause();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Random r = new Random();
		String random = texts[r.nextInt(texts.length)];
		tts.speak(random, TextToSpeech.QUEUE_FLUSH, null);
		
	}
	

}
