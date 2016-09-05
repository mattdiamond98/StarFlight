package net.swordsofvalor.starflight.Entity.Particles;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import net.swordsofvalor.starflight.Entity.Particle;

public class ShieldDrain extends Particle {

	public ShieldDrain(float x, float y, int displayLength, float a) {
		super(x, y, displayLength);
		try {
			image = new Image("res/shielddrain.png");
			this.a = a;
		} catch (SlickException e) {
			e.printStackTrace();
		}	
	}

}
