package com.example.android.skeletonapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.PathShape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
//import android.os.Bundle;
//import android.app.Activity;

public class CustomDrawableView extends View implements SensorEventListener {
	private ShapeDrawable mDrawablex;
	private ShapeDrawable mDrawabley;
	private ShapeDrawable mDrawablez;

	private float[] x;
	private float[] y;
	private float[] z;

	 Path pathx;
	 Path pathy;
	 Path pathz;

	private int ix;

	private SensorManager mSensorManager;

	private Sensor mMagnetometer;

	public CustomDrawableView(Context context) {
		super(context);
		mSensorManager = (SensorManager) context
		.getSystemService(Context.SENSOR_SERVICE);

		mMagnetometer = mSensorManager
		.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

		mSensorManager.registerListener(this, mMagnetometer,
				SensorManager.SENSOR_DELAY_UI);

		// int x = 10;
		// int y = 10;

		x = new float[80];
		y = new float[80];
		z = new float[80];

		pathx = new Path();
		pathy = new Path();
		pathz = new Path();

		mDrawablex = new ShapeDrawable(new PathShape(pathx, 160,120));
		mDrawablex.getPaint().setColor(0xff74AC23);
		mDrawablex.getPaint().setStyle(Paint.Style.STROKE);
		mDrawablex.setBounds(0, 60, 240, 159);
		
		mDrawabley = new ShapeDrawable(new PathShape(pathy, 160,120));
		mDrawabley.getPaint().setColor(0xff74AC23);
		mDrawabley.getPaint().setStyle(Paint.Style.STROKE);
		mDrawabley.setBounds(0, 160, 240, 259);

		mDrawablez = new ShapeDrawable(new PathShape(pathz, 160,120));
		mDrawablez.getPaint().setColor(0xff74AC23);
		mDrawablez.getPaint().setStyle(Paint.Style.STROKE);
		mDrawablez.setBounds(0, 260, 240, 320);

	}

	protected void onDraw(Canvas canvas) {

		// int width = 300;
		// int height = 50;

		// if(x.length != 80)
		// return;
		// if(y.length != 80)
		// return;
		// if(z.length != 80)
		// return;

		float[] tempx;
		float[] tempy;
		float[] tempz;

		tempx = x;
		tempy = y;
		tempz = z;
		
		pathx.rewind();
		pathy.rewind();
		pathz.rewind();
		
		
		
		float yscale = 100;

		for (int i = 1; i < tempx.length; i++) {
			pathx.lineTo(i*3, (x[i] - x[i - 1])*yscale);
			

		}

		for (int i = 1; i < tempy.length; i++) {
			pathy.lineTo(i*3, (y[i] - y[i - 1])*yscale);
			

		}

		for (int i = 1; i < tempz.length; i++) {
			pathz.lineTo(i*3, (z[i] - z[i - 1])*yscale);
			

		}
		
		mDrawablex.draw(canvas);
		
		mDrawabley.draw(canvas);
		
		mDrawablez.draw(canvas);
		
		invalidate();
	}

	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() != Sensor.TYPE_MAGNETIC_FIELD)
			return;

		ix = ix + 1;
		if (ix == 80) {
			ix = 0;
		}
		this.x[ix] = event.values[0];
		this.y[ix] = event.values[1];
		this.z[ix] = event.values[2];
		// mEditor.setText("Value0=" + event.values[0] + "Value1=" +
		// event.values[1] + "Value2=" + event.values[2]);

	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
}