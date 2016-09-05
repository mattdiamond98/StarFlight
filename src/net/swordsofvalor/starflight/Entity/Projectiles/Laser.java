package net.swordsofvalor.starflight.Entity.Projectiles;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import net.swordsofvalor.starflight.StarFlight;
import net.swordsofvalor.starflight.Entity.Damageable;
import net.swordsofvalor.starflight.Entity.Entity;
import net.swordsofvalor.starflight.Entity.LivingEntity;
import net.swordsofvalor.starflight.Entity.Projectile;
import net.swordsofvalor.starflight.Util.RenderUtil;

public class Laser extends Projectile {
	
	Random random = new Random();
	int t = 0;
	
	public Laser(float x, float y, double a, Entity entity) {
		super(x, y, 4, entity);
		try {
			image = new Image("res/laser.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		v.x = entity.v.x;
		v.y = entity.v.y;
		v.x += (float) (40 * Math.sin(-1 * Math.toRadians(a - 90)));
		v.y += (float) (40 * Math.cos(-1 * Math.toRadians(a - 90)));
		v = v.normalize(true);
		v.multiply(40);
		this.a = entity.a + 90;
	}

	@Override
	public void act(GameContainer gc) {
		for (Entity e : new ArrayList<>(Entity.ENTITY_LIST)) {
			if (e instanceof Damageable && !e.equals(this) && !e.equals(shooter)) {
				if ((shooter instanceof LivingEntity && e instanceof LivingEntity)) {
					if (((LivingEntity)shooter).team != ((LivingEntity)e).team) {
						if (this.collidesWith(e)) {
							((Damageable)e).damage(10.0, this);
							exists = false;
						}
					}
				} else {
					if (this.collidesWith(e)) {
						((Damageable)e).damage(10.0, this);
						exists = false;
					}
				}
			}
		}
		
		x += v.x;
		y += v.y;
		
		t++;
		if (t > 1000) {
			exists = false;
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		image.setRotation((float) a);
		RenderUtil.drawEntity(g, StarFlight.player, this);
		
		if (StarFlight.isDebug(gc.getInput())) {
			RenderUtil.drawBounds(g, StarFlight.player, this);
		}
	}

}
