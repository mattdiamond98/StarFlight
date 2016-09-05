package net.swordsofvalor.starflight.Entity.Intelligence;

import net.swordsofvalor.starflight.Entity.Entity;
import net.swordsofvalor.starflight.Entity.Ship;

import org.newdawn.slick.GameContainer;

public abstract class ShipIntelligence implements Intelligence {

	@Override
	public void handleIntelligence(GameContainer gc, Entity e) {
		if (e instanceof Ship) {
			handleIntelligence(gc, (Ship) e);
		}
	}
	
	public abstract void handleIntelligence(GameContainer gc, Ship s);
	
}
