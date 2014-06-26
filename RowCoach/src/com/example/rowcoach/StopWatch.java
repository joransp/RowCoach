package com.example.rowcoach;

import android.os.Handler;
import android.os.SystemClock;
import android.widget.TextView;

public class StopWatch {
	// define variable types
	private final int PAUSE = 100;
	private Handler handler = new Handler();
	private float startingTime = SystemClock.elapsedRealtime();
	private float pauseTime = 0;
	private float time = 0;
	private TextView textView;
	
	// create new timer 
	private Runnable timer = new Runnable() {
	    @Override 
	    public void run() {
	      time = SystemClock.elapsedRealtime() - startingTime + pauseTime; // calculate current stopwatchtime
	      textView.setText(stringTime(time)); // show time
	      handler.postDelayed(timer, PAUSE); // repeat actions
	    }
	};
	  
	// transforms time in miliseconds to viewable string
	public String stringTime(float inputTime) {
		   
	    float centiSeconds = inputTime % 100;
	    float deciSeconds = inputTime % 1000;
	    float seconds = inputTime % 60000;
	    float minutes = inputTime % 3600000;
	    
	    float finalDeciSeconds = (deciSeconds - centiSeconds) / 100;
	    float finalSeconds = (seconds - deciSeconds) / 1000;
	    float finalMinutes = (minutes - seconds) / 60000;
	    
	    String clockString = String.format("%.0f : %.0f.%.0f", finalMinutes, finalSeconds, finalDeciSeconds);
	    return clockString;
	}
	
	/**
	 * Starts timer
	 * @param clockField TextField to view clock
	 */
	public void start(TextView clockField) {
		startingTime = SystemClock.elapsedRealtime();
		textView = clockField;
		timer.run();
	}
	
	/**
	 * Resets timer
	 */
	public void reset() {
		handler.removeCallbacks(timer);
		startingTime = SystemClock.elapsedRealtime();
		pauseTime = 0;
		textView.setText(stringTime(0));
	}
	
	/**
	 * stops timer
	 */
	public void stop() {
		pauseTime = time;
		handler.removeCallbacks(timer);
	}
	
	/**
	 * Gets current clocktime
	 * @return Returns variable time;
	 */
	public float getTime() {
		return time;
	}
}
