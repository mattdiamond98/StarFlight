package net.swordsofvalor.starflight.Entity;

import net.swordsofvalor.starflight.Entity.Intelligence.Intelligence;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class LivingEntity extends Damageable {
	
	public int team;
	protected Intelligence intelligence;
	
	public LivingEntity(float x, float y, int size, double maxHealth, double maxShields, Intelligence intelligence, int team) {
		super(x, y, size, maxHealth, maxShields);
		this.intelligence = intelligence;
		this.team = team;
	}
	
	public void act(GameContainer gc) {
		handleIntelligence(gc);
		x += v.x;
		y += v.y;
		double length = 1 / v.invSqrt((float) v.lengthSquared());
		if (length > 0.5) {
			v = v.normalize(true).multiply(length - 0.5);
		} else {
			v = v.multiply(0);
		}
	}
	public abstract void render(GameContainer gc, Graphics g);
	
	public void handleIntelligence(GameContainer gc) {
		if (intelligence != null)
		intelligence.handleIntelligence(gc, this);
	}
	
}
