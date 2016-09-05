package net.swordsofvalor.starflight.Entity.Particles;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import net.swordsofvalor.starflight.Entity.Particle;

public class Smoke extends Particle {

	public Smoke(float x, float y, int displayLength) {
		super(x, y, displayLength);
		try {
			image = new Image("res/smoke.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
