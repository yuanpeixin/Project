package com.example.hangout;

import java.util.Random;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.peixin.hangout.R;

public class PointlessWidget extends AppWidgetProvider{

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Random r = new Random();
		int random = r.nextInt(100000000);
		String rand = String.valueOf(random);
		
		final int N = appWidgetIds.length;
		for(int i = 0; i < N; i++){
			RemoteViews v = new RemoteViews(context.getPackageName(), R.layout.widget);
			v.setTextViewText(R.id.tvWidgetUpdate, rand);
			appWidgetManager.updateAppWidget(appWidgetIds[i], v);
		}
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();;
	}

	
}
