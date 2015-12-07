package com.capthed.abyss;

public abstract class Timer {

	public static final long SECOND = 1000;
	private static long startTime = 0;
	private static long delta;
	
	public static void start() {
		startTime = getTime();
	}
	
	// Returns tje current time in ms
	public static long getTime() { return System.currentTimeMillis(); }
	
	// Returns the time passed from last frame to this one
	public static long getDelta() { return delta; }
	
	static void setDelta(long d) { delta = d; }

	// Returns the time passed since the program has been started in ms
	public static long getTimeRunning() {
		return getTime() - startTime;
	}
}