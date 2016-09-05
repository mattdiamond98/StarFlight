package net.swordsofvalor.starflight.Entity;

import net.swordsofvalor.starflight.StarFlight;
import net.swordsofvalor.starflight.Util.RenderUtil;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Powerup extends Entity {
	
	protected Image shell1;
	protected Image shell2;
	private float r1;
	private float r2;
	
	public Powerup(float x, float y) {
		super(x, y, 16);
		try {
			shell1 = new Image("res/powerupshell.png");
			shell2 = new Image("res/powerupshell.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void act(GameContainer gc) {
		if (this.collidesWith(StarFlight.player)) {
			onPickup(gc, StarFlight.player);
			exists = false;
		}
		r1 += 5F;
		r2 += 8F;
		
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		shell1.setRotation(r1);
		shell2.setRotation(r2);
		RenderUtil.drawImage(g, StarFlight.player, shell1, x - size, y - size);
		RenderUtil.drawImage(g, StarFlight.player, shell2, x - size, y - size);
		RenderUtil.drawImage(g, StarFlight.player, image.getScaledCopy(1.2F), x - (size * 1.2F), y - (size * 1.2F));
	}
	
	public abstract void onPickup(GameContainer gc, PlayerEntity p);

}
