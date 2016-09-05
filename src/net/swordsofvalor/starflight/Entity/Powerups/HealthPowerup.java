package net.swordsofvalor.starflight.Entity.Powerups;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import net.swordsofvalor.starflight.Entity.PlayerEntity;
import net.swordsofvalor.starflight.Entity.Powerup;

public class HealthPowerup extends Powerup {

	public HealthPowerup(float x, float y) {
		super(x, y);
		try {
			image = new Image("res/healthpowerup.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onPickup(GameContainer gc, PlayerEntity p) {
		p.heal(30);
	}

}
