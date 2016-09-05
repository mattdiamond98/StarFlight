package net.swordsofvalor.starflight.Entity.Projectiles;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import net.swordsofvalor.starflight.StarFlight;
import net.swordsofvalor.starflight.Entity.Entity;
import net.swordsofvalor.starflight.Entity.Projectile;
import net.swordsofvalor.starflight.Entity.Particles.ShieldDrain;
import net.swordsofvalor.starflight.Util.RenderUtil;

public class AriesMissile extends Projectile {
	
	Random random = new Random();
	int t = 0;
	
	public AriesMissile(float x, float y, Entity entity) {
		super(x, y, 8, entity);
		this.a = entity.a;
		try {
			image = new Image("res/asmissile.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		v.x = entity.v.x;
		v.y = entity.v.y;
		v.x += (float) (20 * Math.sin(-1 * Math.toRadians(a - 90)));
		v.y += (float) (20 * Math.cos(-1 * Math.toRadians(a - 90)));
		v = v.normalize(true);
		v.multiply(20);
	}

	@Override
	public void act(GameContainer gc) {
		x += v.x;
		y += v.y;
		
		t++;
		if (t > 2000) {
			detonate(gc);
		}
	}

	@Override
	public void render(GameContainer gc, final Graphics g) {
		image.setRotation((float) a);
		RenderUtil.drawEntity(g, StarFlight.player, this);
		
		if (StarFlight.isDebug(gc.getInput())) {
			RenderUtil.drawBounds(g, StarFlight.player, this);
		}
	}
	
	public void detonate(GameContainer gc) {
		for (int i = 0; i < 20; i++) {
			new ShieldDrain(x-64,y-64,i,random.nextFloat() * 360);
		}
		exists = false;
	}
}
