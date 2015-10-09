package com.example.hangout;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class GLTriangle {
	private float vertices[] = {
		0f,1f, //p1
		1f, -1f, //p2
		-1f, -1f //p3
	};
	
	private float rgbVals[] = {
			1, 1, 0, 0.5f,
			0.24f, 0, 0.85f, 1,
			0, 1, 1, 1
	};
	
	private FloatBuffer vertBuff, colorBuff;
	private short[] pIndex = {0,1,2};
	private ShortBuffer pBuff;
	
	
	public GLTriangle(){
		ByteBuffer bBuff = ByteBuffer.allocateDirect(vertices.length * 4);
		bBuff.order(ByteOrder.nativeOrder());
		vertBuff = bBuff.asFloatBuffer();
		vertBuff.put(vertices);
		vertBuff.position(0);
		
		ByteBuffer pbBuff = ByteBuffer.allocateDirect(pIndex.length * 2);
		pbBuff.order(ByteOrder.nativeOrder());
		pBuff = pbBuff.asShortBuffer();
		pBuff.put(pIndex);
		pBuff.position(0);
		
		ByteBuffer cBuff = ByteBuffer.allocateDirect(rgbVals.length * 4);
		cBuff.order(ByteOrder.nativeOrder());
		colorBuff = cBuff.asFloatBuffer();
		colorBuff.put(rgbVals);
		colorBuff.position(0);
	}
	
	public void draw(GL10 gl){
		 gl.glFrontFace(GL10.GL_CW);
		 gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		 gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		 
		 gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuff);
		 gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertBuff);
		 gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length, GL10.GL_UNSIGNED_SHORT, pBuff);
		 
		 gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		 gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
	}
}
