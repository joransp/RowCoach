package com.example.rowcoach;

public class IntervalTime {
	// define variable types
	private float interval;
	private float time;
	private float intervaltime;
	
	public float getInterval() {
		return interval;
	}
	
	public float getTime() {
		return time;
	}
	
	public float getIntervaltime() {
		return intervaltime;
	}
	
	public void setInterval(float distance) {
		if(distance >= 0) {
			interval = distance;
		}
	}
	
	public void setTime(float racetime) {
		if(racetime >= 0) {
			time = racetime;
		}
	}
	
	public void setIntervalTime(float raceintervaltime) {
		if(raceintervaltime >= 0) {
			intervaltime = raceintervaltime;
		}
	}
}
