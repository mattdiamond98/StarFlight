package net.swordsofvalor.starflight.Entity.Intelligence;

import net.swordsofvalor.starflight.Entity.Ship;

import org.newdawn.slick.GameContainer;

public abstract class ArtificialIntelligence extends ShipIntelligence {
	
	protected int aiRange;
	protected int ai;
	
	public ArtificialIntelligence(int aiRange) {
		this.aiRange = aiRange;
	}
	
	public void handleIntelligence(GameContainer gc, Ship s) {
		onUpdate(gc, s, ai);
		ai++;
		if (ai > aiRange) ai = 0;
	}
	
	protected abstract void onUpdate(GameContainer gc, Ship s, int i);
	
	protected boolean aiRangeOf(int start, int end) {
		return end > ai && ai >= start;
	}
	
}
