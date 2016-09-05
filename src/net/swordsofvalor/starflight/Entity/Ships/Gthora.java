package net.swordsofvalor.starflight.Entity.Ships;

import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import net.swordsofvalor.starflight.StarFlight;
import net.swordsofvalor.starflight.Entity.Entity;
import net.swordsofvalor.starflight.Entity.Ship;
import net.swordsofvalor.starflight.Entity.Intelligence.MediumEnemyAI;
import net.swordsofvalor.starflight.Entity.Powerups.HealthPowerup;
import net.swordsofvalor.starflight.Util.RenderUtil;
import net.swordsofvalor.starflight.Weapons.LaserCannon;
import net.swordsofvalor.starflight.Weapons.ScorpioMissilePod;

public class Gthora extends Ship {

	public Gthora(float x, float y) {
		super(x, y, 16, 50, 50, Arrays.asList(new LaserCannon(), new ScorpioMissilePod()), new MediumEnemyAI(), 0);
		try {
			image = new Image("res/gthora.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		image.setRotation(Math.round(a));
		RenderUtil.drawEntity(g, StarFlight.player, this);
		for (int i = 0; i < shieldOpac; i++) {
			RenderUtil.drawImage(g, StarFlight.player, shieldImage.getScaledCopy(1.5F), x - 8, y - 8);
		}
		if (shieldOpac > 0) {
			shieldOpac -= 0.25;
		}
		if (StarFlight.isDebug(gc.getInput())) {
			RenderUtil.drawBounds(g, StarFlight.player, this);
			g.drawString("rhp: " + (health + shields), 20, 400);
		}
	}
	
	@Override
	public void onDeath(Entity killer) {
		StarFlight.score += 350;
		new HealthPowerup(x + 16, y + 16);
	}
	
}
