package net.swordsofvalor.starflight;

public class Timer {
	
	private int time;
	private int tickLength;
	
	public Timer(int tickLength) {
		time = 0;
		this.tickLength = tickLength;
	}
	
	public void tick(int millis) {
		time += millis;
	}
	
	public boolean time() {
		return time >= tickLength;
	}
	
	public void reset() {
		time -= tickLength;
	}
	
}
