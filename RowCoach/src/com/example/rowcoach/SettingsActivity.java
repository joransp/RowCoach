package com.example.rowcoach;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SettingsActivity extends ActionBarActivity {
	// define variable types
	SeekBar strokeNumber;
	int distance;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		this.strokeNumber = (SeekBar) findViewById(R.id.stroke_number);

		// View progress from seekBar strokeNumber
		strokeNumber.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
		            
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				((TextView) findViewById(R.id.stroke_number_sign)).setText("Number of strokes for strokecount: " + Integer.toString(arg1 + 1));	
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
			}
        });		
	}
	
	// When activity is started, set all variables to last values
	@Override
	public void onStart(){
	    super.onStart();
	    strokeNumber = (SeekBar) findViewById(R.id.stroke_number);
	    SharedPreferences preferences = getSharedPreferences("MyPreferences",  Context.MODE_PRIVATE);
	    strokeNumber.setProgress(preferences.getInt("stroke_number", 0) - 1); 
		((TextView) findViewById(R.id.stroke_number_sign)).setText("Number of strokes for strokecount: " + Integer.toString(strokeNumber.getProgress() + 1));	
		((EditText) findViewById(R.id.distance_input)).setText(String.format("%d", preferences.getInt("interval_distance", 0)));	

	}
	
	// When activity is stopped, store all values
	@Override
	protected void onStop() {
		super.onStop();
		strokeNumber = (SeekBar) findViewById(R.id.stroke_number);
		int strokeCount = strokeNumber.getProgress() + 1;
		EditText intervalDistance = (EditText) findViewById(R.id.distance_input);
		if (intervalDistance.getText().length() == 0) {
			distance = 0;
		}
		else {
			distance = Integer.valueOf(intervalDistance.getText().toString());
		}
	    SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
	    editor.putInt("stroke_number", strokeCount);
	    editor.putInt("interval_distance", distance);
	    editor.commit(); 
	        
	}

}
