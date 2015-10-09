package com.example.hangout;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class GLCubEX extends Activity{
	
	GLSurfaceView ourSurface;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ourSurface = new GLSurfaceView(this);
		ourSurface.setRenderer(new GLCubeRenderer());
		setContentView(ourSurface);
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourSurface.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSurface.onPause();
	}
}
