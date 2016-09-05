package net.swordsofvalor.starflight.Entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class Projectile extends Entity {

	public Entity shooter;
	
	public Projectile(float x, float y, int size, Entity shooter) {
		super(x, y, size);
		this.shooter = shooter;
	}
	
	public abstract void act(GameContainer gc);
	public abstract void render(GameContainer gc, Graphics g);

}
