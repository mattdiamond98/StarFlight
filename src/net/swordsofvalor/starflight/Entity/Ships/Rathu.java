package net.swordsofvalor.starflight.Entity.Ships;

import java.util.Arrays;

import net.swordsofvalor.starflight.StarFlight;
import net.swordsofvalor.starflight.Entity.Entity;
import net.swordsofvalor.starflight.Entity.Ship;
import net.swordsofvalor.starflight.Entity.Intelligence.SimpleEnemyAI;
import net.swordsofvalor.starflight.Util.RenderUtil;
import net.swordsofvalor.starflight.Weapons.PhotonCannon;
import net.swordsofvalor.starflight.Weapons.Weapon;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Rathu extends Ship {
	
	public Rathu(float x, float y) {
		super(x, y, 16, 25, 25, Arrays.asList((Weapon) new PhotonCannon()), new SimpleEnemyAI(), 0);
		try {
			image = new Image("res/rathu.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public Rathu(float x, float y, boolean noAi) {
		this(x,y);
		if (noAi) {
			this.intelligence = null;
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
		StarFlight.score += 150;
	}
	
}
