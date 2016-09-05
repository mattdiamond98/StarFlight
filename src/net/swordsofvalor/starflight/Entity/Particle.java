package net.swordsofvalor.starflight.Entity;

import net.swordsofvalor.starflight.StarFlight;
import net.swordsofvalor.starflight.Util.RenderUtil;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class Particle extends Entity {
	
	public float scale = 1F;
	private int display;
	
	public Particle(float x, float y, int displayLength) {
		super(x, y, 0);
		this.display = displayLength;
	}

	@Override
	public void act(GameContainer gc) {
		x += v.x;
		y += v.y;
		if (this.display <= 0) exists = false;
		display--;
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		image.setRotation((float) a);
		RenderUtil.drawEntityToScale(g, StarFlight.player, this, scale);
	}

}
