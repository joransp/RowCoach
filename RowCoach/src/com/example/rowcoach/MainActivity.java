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
	boolean secondclick = false;
	boolean counting = false;
	IntervalTime intervaltime;
	float previoustime = 0;
	float startingtime;
	float stoppingtime;
	float elapsed;
	float strokerate;
	SharedPreferences preferences;
	int strokenumber;
	int intervaldistance;
	int total = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// create new stopwatch
		stopwatch = new StopWatch();
		clock = (TextView) findViewById(R.id.clock);
		
		// create new interval
		intervaltime = new IntervalTime();
		
		// find stroke textview
		strokes = (TextView) findViewById(R.id.stroke_sign);
		
		// find gridview and set new adapter
		final GridView gridview = (GridView) findViewById(R.id.intervallist);
		final ArrayList<String> list = new ArrayList<String>();
		final Adapter adapter = new Adapter(this,
				android.R.layout.simple_list_item_1, list);
		gridview.setAdapter(adapter);
		
		// find button to start stopwatch when clicked
		final Button startStop = (Button) findViewById(R.id.startStop);
        startStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	// start stopwatch at the first click and pause at second click
            	if(secondclick) {
            		stopwatch.stop();
            		startStop.setText("START");
            	}
            	else {
            		stopwatch.start(clock);	
            		startStop.setText("STOP");
            	}
            	secondclick = !secondclick;
            }    
        });
        
		// find button to reset stopwatch when clicked
        final Button reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	stopwatch.reset(); // reset stopwatch
            	secondclick = false; // set startStop button to default
            	startStop.setText("START");
            	list.clear(); // clear gridview
            	total = 0;
            }
        });
        
        // find button to set interval when clicked
        final Button interval = (Button) findViewById(R.id.interval);
        interval.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	if (secondclick) {
            		// load intervaldistance
	            	preferences = getSharedPreferences("MyPreferences",  Context.MODE_PRIVATE);
	                intervaldistance = preferences.getInt("interval_distance", 0);	
	                total += intervaldistance;
	                
	                // set intervaltime class
	            	intervaltime.setInterval(total);
	            	intervaltime.setTime(stopwatch.getTime());
	            	intervaltime.setIntervalTime(stopwatch.getTime() - previoustime);
	            	
	            	// remember last stopwatch pause
	            	previoustime = stopwatch.getTime();
	            	
	            	// add data to gridview and update gridview
	            	list.add(0, stopwatch.stringTime(intervaltime.getIntervaltime()));
	            	list.add(0, stopwatch.stringTime(intervaltime.getTime()));
	            	list.add(0, String.format("%.0f", intervaltime.getInterval()));
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
                    strokenumber = preferences.getInt("stroke_number", 0);
            		stoppingtime = SystemClock.elapsedRealtime();
            		elapsed = stoppingtime - startingtime; // calculate elapsed time
            		strokerate = strokenumber * 60000 / elapsed; // calculate strokerate
            		strokes.setText(String.format("SR: %.1f", strokerate)); // show strokerate
            		counting = !counting;
            	}
            	else {
            		startingtime = SystemClock.elapsedRealtime(); // start counting
            		counting = !counting;
            		strokes.setText("counting...");
            	}
            }    
        });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			goSettings();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void goSettings() {
    	// Define intent to go to settings
        Intent intent = new Intent(this, Settings.class);
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
