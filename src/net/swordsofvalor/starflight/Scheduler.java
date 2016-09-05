package net.swordsofvalor.starflight;

import java.util.ArrayList;
import java.util.Stack;

public class Scheduler {
	
	public Stack<Task> taskList = new Stack<>();
	
	public void scheduleSyncDelayedTask(Runnable run, long delay) {
		taskList.add(new Task(run, System.currentTimeMillis() + delay));
	}
	
	public void updateScheduler() {
		for (Task task : new ArrayList<>(taskList)) {
			if (task.isRunTime()) {
				task.runTask();
				taskList.remove(task);
			}
		}
	}
	
	private static class Task {
		
		public Runnable run;
		public long time;
		
		public Task(Runnable run, long time) {
			this.run = run;
			this.time = time;
		}
		
		public boolean isRunTime() {
			return System.currentTimeMillis() >= time;
		}
		
		public void runTask() {
			run.run();
		}
		
	}
	
}
