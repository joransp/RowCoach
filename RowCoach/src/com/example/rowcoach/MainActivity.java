package com.example.rowcoach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	// Define variable types
	TextView clock;
	TextView strokes;
	StopWatch stopwatch;
	boolean secondClick = false;
	boolean counting = false;
	IntervalTime intervalTime;
	float previousTime = 0;
	float startingTime;
	float stoppingTime;
	float elapsed;
	SharedPreferences preferences;
	int strokeNumber;
	int intervalDistance;
	int total = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// create new stopwatch
		this.stopwatch = new StopWatch();
		this.clock = (TextView) findViewById(R.id.clock);
		
		// create new interval
		this.intervalTime = new IntervalTime();
		
		// find stroke textview
		this.strokes = (TextView) findViewById(R.id.stroke_sign);
		
		// find gridview and set new adapter
		final GridView gridView = (GridView) findViewById(R.id.intervallist);
		final ArrayList<String> list = new ArrayList<String>();
		final Adapter adapter = new Adapter(this,
				android.R.layout.simple_list_item_1, list);
		gridView.setAdapter(adapter);
		
		// find button to start stopwatch when clicked
		final Button startStop = (Button) findViewById(R.id.startStop);
        startStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	// start stopwatch at the first click and pause at second click
            	if(secondClick) {
            		stopwatch.stop();
            		startStop.setText(R.string.start);
            	}
            	else {
            		stopwatch.start(clock);	
            		startStop.setText(R.string.stop);
            	}
            	secondClick = !secondClick;
            }    
        });
        
		// find button to reset stopwatch when clicked
        final Button reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	stopwatch.reset(); // reset stopwatch
            	secondClick = false; // set startStop button to default
            	startStop.setText("START");
            	list.clear(); // clear gridview
            	total = 0;
            }
        });
        
        // find button to set interval when clicked
        final Button interval = (Button) findViewById(R.id.interval);
        interval.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	if (secondClick) {
            		// load intervaldistance
	            	preferences = getSharedPreferences("MyPreferences",  Context.MODE_PRIVATE);
	                intervalDistance = preferences.getInt("interval_distance", 0);	
	                total += intervalDistance;
	                
	                // set intervaltime class
	            	intervalTime.setInterval(total);
	            	intervalTime.setTime(stopwatch.getTime());
	            	intervalTime.setIntervalTime(stopwatch.getTime() - previousTime);
	            	
	            	// remember last stopwatch pause
	            	previousTime = stopwatch.getTime();
	            	
	            	// add data to gridview and update gridview
	            	list.add(0, stopwatch.stringTime(intervalTime.getIntervaltime()));
	            	list.add(0, stopwatch.stringTime(intervalTime.getTime()));
	            	list.add(0, String.format("%.0f", intervalTime.getInterval()));
	            	adapter.notifyDataSetChanged();
            	}
            	
            }
        });
        // find button to start counting strokes when clicked
        final Button stroke = (Button) findViewById(R.id.stroke);
        stroke.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	// start counting at the first click and stop at the second
            	if (counting) {
            		preferences = getSharedPreferences("MyPreferences",  Context.MODE_PRIVATE); // load stroke_number
                    strokeNumber = preferences.getInt("stroke_number", 0);
            		stoppingTime = SystemClock.elapsedRealtime();
            		elapsed = stoppingTime - startingTime; // calculate elapsed time
            		float strokeRate = strokeNumber * 60000 / elapsed; // calculate strokerate
            		strokes.setText(String.format("SR: %.1f", strokeRate)); // show strokerate
            		counting = !counting;
            	}
            	else {
            		startingTime = SystemClock.elapsedRealtime(); // start counting
            		counting = !counting;
            		strokes.setText("counting...");
            	}
            }    
        });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// inflates the menu when clicked on
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// go to SettingsActivity when clicked on settings
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			goSettings();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void goSettings() {
    	// Define intent to go to settings
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
	
	// define adapter
	private class Adapter extends ArrayAdapter<String> {
	    HashMap<String, Integer> Map = new HashMap<String, Integer>();
	    public Adapter(Context context, int textViewResourceId,
	        List<String> objects) {
	      super(context, textViewResourceId, objects);
	      for (int i = 0; i < objects.size(); ++i) {
	        Map.put(objects.get(i), i);
	      }
	    }
	}
}
