package com.example.rowcoach;

public class IntervalTime {
	// define variable types
	private float interval;
	private float time;
	private float intervalTime;
	
	/**
	 * Gets current interval
	 * @return Returns float interval
	 */
	public float getInterval() {
		return interval;
	}
	
	/**
	 * Gets total time to interval
	 * @return Returns float time
	 */
	public float getTime() {
		return time;
	}
	
	/**
	 * Gets interval time
	 * @return Returns float intervalTime 
	 */
	public float getIntervaltime() {
		return intervalTime;
	}
	
	/**
	 * Sets interval distance
	 * @param distance Distance of the interval
	 */
	public void setInterval(float distance) {
		if(distance >= 0) {
			interval = distance;
		}
	}
	
	/**
	 * Sets total time to interval
	 * @param racetime Total time to interval
	 */
	public void setTime(float raceTime) {
		if(raceTime >= 0) {
			time = raceTime;
		}
	}
	
	/**
	 * Sets current interval time
	 * @param raceintervaltime time of last interval
	 */
	public void setIntervalTime(float raceIntervalTime) {
		if(raceIntervalTime >= 0) {
			intervalTime = raceIntervalTime;
		}
	}
}
