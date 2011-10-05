package com.example.android.skeletonapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
//import android.os.Bundle;
//import android.app.Activity;

public class CustomDrawableView extends View implements SensorEventListener{
    private ShapeDrawable mDrawable;
    private float x;
    private float y;
    private float z;
    
 private SensorManager mSensorManager;
    
    private Sensor mMagnetometer;

    

    public CustomDrawableView(Context context) {
        super(context);
 mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        
        mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        
        mSensorManager.registerListener(this, mMagnetometer, SensorManager.SENSOR_DELAY_UI);

        //int x = 10;
       // int y = 10;
        
        mDrawable = new ShapeDrawable(new OvalShape());
        mDrawable.getPaint().setColor(0xff74AC23);
        
    }

    protected void onDraw(Canvas canvas) {
    	
    	int width = 300;
        int height = 50;
        
        mDrawable.setBounds((int)x, (int)y, (int)x + width, (int)y + height);
        mDrawable.draw(canvas);
        invalidate();
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() != Sensor.TYPE_MAGNETIC_FIELD)
            return;
        this.x = event.values[0];
        this.y = event.values[1];
        this.z = event.values[2];
        //mEditor.setText("Value0=" + event.values[0] + "Value1=" + event.values[1] + "Value2=" + event.values[2]);
        
    }
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}