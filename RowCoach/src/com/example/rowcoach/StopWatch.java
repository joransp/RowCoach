package com.example.rowcoach;

import android.os.Handler;
import android.os.SystemClock;
import android.widget.TextView;

public class StopWatch {
	// define variable types
	private int pause = 1;
	private Handler handler = new Handler();
	private float startingtime = SystemClock.elapsedRealtime();
	private float pausetime = 0;
	private float time = 0;
	private float centiseconds;
	private float deciseconds;
	private float seconds;
	private float minutes;
	private float finalDeciseconds;
	private float finalSeconds;
	private float finalMinutes;
	private TextView textview;
	private String clockString;
	
	// create new timer 
	private Runnable timer = new Runnable() {
	    @Override 
	    public void run() {
	      time = SystemClock.elapsedRealtime() - startingtime + pausetime; // calculate current stopwatchtime
	      textview.setText(stringTime(time)); // show time
	      handler.postDelayed(timer, pause); // repeat actions
	    }
	};
	  
	// transforms time in miliseconds to viewable string
	public String stringTime(float inputTime) {
		   
	    centiseconds = inputTime % 100;
	    deciseconds = inputTime % 1000;
	    seconds = inputTime % 60000;
	    minutes = inputTime % 3600000;
	    
	    finalDeciseconds = (deciseconds - centiseconds) / 100;
	    finalSeconds = (seconds - deciseconds) / 1000;
	    finalMinutes = (minutes - seconds) / 60000;
	    
	    clockString = String.format("%.0f : %.0f.%.0f", finalMinutes, finalSeconds, finalDeciseconds);
	    return clockString;
	}
	
	// start timer
	public void start(TextView clockField) {
		startingtime = SystemClock.elapsedRealtime();
		textview = clockField;
		timer.run();
	}
	
	// reset timer
	public void reset() {
		handler.removeCallbacks(timer);
		startingtime = SystemClock.elapsedRealtime();
		pausetime = 0;
		textview.setText(stringTime(0));
	}
	
	// stop timer
	public void stop() {
		pausetime = time;
		handler.removeCallbacks(timer);
	}
	
	// return time
	public float getTime() {
		return time;
	}
}
