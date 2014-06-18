Design document
===============

Tables
------
A ListView will be implemented to view all the intervaltimes of the current race. There will be three collums: the distance raced, the total time and the intervaltime.

Classes
-------
Several classes will be implemented. All variables are implemented as private variables and each variable has a get() and set() function. The classes are defined as below.

*StopWatch*, containing:
*	*float time*
	This float stores the current time.
*	*float startingTime*
	This float stores the starting time of the application.
* 	*float deciseconds*
	This float stores the amount of deciseconds viewed in the stopwatch.
* 	*float seconds*
	This float stores the amount of seconds viewed in the stopwatch.
* 	*float minutes*
	This float stores the amount of minutes viewed in the stopwatch.
* 	*float hours*
	This float stores the amount of hours viewed in the stopwatch.
*	*public void start()*
	The function *start()* starts the stopwatch.
*	*public void interval()*
	The function *interval()* will store the current stopwatch time in the table. 
*	*public void finish()*
	The function *finish* stops the stopwatch and stores the last interval to the table.

IntervalTime, containing:
*	*int interval*
	This integer stores the current interval (the total distance raced).
*	*float totalDeciseconds*
	This float stores the amount of deciseconds viewed in the time collum.
*	*float totalSeconds*
	This float stores the amount of seconds viewed in the time collum.
*	*float totalMinutes*
	This float stores the amount of minutes viewed in the time collum.
*	*float intervalDeciseconds*
	This float stores the amount of deciseconds viewed in the interval collum.
*	*float intervalSeconds*
	This float stores the amount of seconds viewed in the interval collum
*	*float intervalMinutes*
	This float stores the amount of minutes viewed in the interval collum


Settings, containing:
*	*int strokeAmount* 
	This integer stores the number of strokes rowed between two buttonclicks.
*	*float interval*
	This float stores the length of the interval.


