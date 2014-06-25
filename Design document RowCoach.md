Design document
===============

Tables
------
A ListView will be implemented to view all the intervaltimes of the current race. There will be three collums: the distance raced, the total time and the intervaltime.

Classes
-------
Several classes will be implemented. All variables are implemented as private variables and each variable has a get() and set() function. The classes are defined as below.

*StopWatch*, containing:
*	*int pause*
	This float stores the stopwatch refreshing time
*	*Handler handler*
	This handler can refresh the stopwatch
*	*float pausetime*
	This float stores the time when the stopwatch is stopped
*	*float time*
	This float stores the current time.
*	*float startingtime*
	This float stores the starting time of the application.
*	*float centiseconds*
	This float stores the amount of centiseconds.
* 	*float deciseconds*
	This float stores the amount of deciseconds.
* 	*float seconds*
	This float stores the amount of seconds.
* 	*float minutes*
	This float stores the amount of minutes.
*	float finalDeciseconds*
	This float stores the amount of deciseconds viewed in the stopwatch.
* 	*float finalSeconds*
	This float stores the amount of seconds viewed in the stopwatch.
* 	*float finalMinutes*
	This float stores the amount of minutes viewed in the stopwatch. 	
*	*public void start(TextView clockField)*
	The function *start()* starts the stopwatch in the given textview.
*	*public void interval()*
	The function *interval()* will store the current stopwatch time in the table. 
*	*public void stop()*
	The function *stop* stops the stopwatch.
*	*public void reset()*
	The function *reset()* resets the stopwatch
	

IntervalTime, containing:
*	*float interval*
	This float stores the current interval (the total distance raced).
*	*float time*
	This float stores the total time to get to the interval
*	*float intervaltime*
	This float stores the elapsed time of the last interval


Settings, containing:
*	*int strokeCount* 
	This integer stores the number of strokes rowed between two buttonclicks.
*	*int distance*
	This integer stores the length of the interval.


