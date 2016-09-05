package net.swordsofvalor.starflight.Entity.Powerups;

import org.newdawn.slick.GameContainer;

import net.swordsofvalor.starflight.Entity.PlayerEntity;
import net.swordsofvalor.starflight.Entity.Powerup;

public abstract class TimedPowerup extends Powerup {
	
	protected int time;
	
	public TimedPowerup(float x, float y, int time) {
		super(x, y);
		this.time = time;
	}
	
	@Override
	public void onPickup(GameContainer gc, PlayerEntity p) {
		
	}
	
	
	
	public int getTime() {
		return time;
	}
	
}
