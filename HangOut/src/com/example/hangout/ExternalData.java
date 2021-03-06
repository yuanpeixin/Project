package com.example.hangout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import com.peixin.hangout.R;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ExternalData extends Activity implements OnItemSelectedListener, OnClickListener{

	private TextView canWrite, canRead;
	private String state;
	boolean canW, canR;
	Spinner spinner;
	String[] paths = {"Music","Picture","Download"};
	File path = null;
	Button confirm, save;
	File file = null;
	EditText saveFile;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.externaldata);
		canWrite = (TextView) findViewById(R.id.tvCanWrite);
		canRead = (TextView) findViewById(R.id.tvCanRead);
		canWrite.setText("false");
		canRead.setText("false");
		confirm = (Button) findViewById(R.id.bConfirm);
		save = (Button) findViewById(R.id.bSaveAs);
		saveFile = (EditText) findViewById(R.id.etSaveAs);
		confirm.setOnClickListener(this);
		save.setOnClickListener(this);
		
		checkState();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExternalData.this,android.R.layout.simple_spinner_item, paths);
		spinner = (Spinner) findViewById(R.id.spinner1);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
			
	}

	private void checkState() {
		// TODO Auto-generated method stub
		state = Environment.getExternalStorageState();
		if(state.equals(Environment.MEDIA_MOUNTED)){
			canWrite.setText("true");
			canRead.setText("true");
			canW = canR = true;
			
		}else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
			canWrite.setText("false");
			canRead.setText("true");
			canW = false;
			canR = true;
		}else{
			canWrite.setText("false");
			canRead.setText("false");
			canW = canR = false;
		}
		
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		int index = spinner.getSelectedItemPosition();
		switch(index){
		case 0:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			break;
		case 1:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			break;
		case 2:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			break;
			
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bSaveAs:
			String f = saveFile.getText().toString();
			file = new File(path,f);
			checkState();
			
			if(canW == canR == true){
				
				path.mkdirs();
				try {
					OutputStream os = new FileOutputStream(file);
					InputStream is = getResources().openRawResource(R.drawable.greenball);
					byte[] data = new byte[is.available()];
					is.read(data);
					os.write(data);
					is.close();
					os.close();
					
					Toast t = Toast.makeText(ExternalData.this, "File has been saved", Toast.LENGTH_LONG);
					t.show();
					
					//Update files for the user to use
					MediaScannerConnection.scanFile(ExternalData.this, new String[] {file.toString()}, null, new MediaScannerConnection.OnScanCompletedListener() {
						
						@Override
						public void onScanCompleted(String path, Uri uri) {
							// TODO Auto-generated method stub
							Toast t = Toast.makeText(ExternalData.this, "scan complete", Toast.LENGTH_LONG);
						}
					});
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case R.id.bConfirm:
			save.setVisibility(View.VISIBLE);
			break;
		}
	}
	
	

}
