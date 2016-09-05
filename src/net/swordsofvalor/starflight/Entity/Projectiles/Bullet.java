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

public class Bullet extends Projectile {
	
	Random random = new Random();
	int t = 0;
	
	public Bullet(float x, float y, Entity entity) {
		super(x, y, 3, entity);
		try {
			image = new Image("res/bullet.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		a = entity.a;
		v.x = entity.v.x;
		v.y = entity.v.y;
		v.x += (float) (35F * Math.sin(-1 * Math.toRadians(a - 90)));
		v.y += (float) (35F * Math.cos(-1 * Math.toRadians(a - 90)));
		v = v.normalize(true);
		v.multiply(35);
	}

	@Override
	public void act(GameContainer gc) {
		for (Entity e : new ArrayList<>(Entity.ENTITY_LIST)) {
			if (e instanceof Damageable && !e.equals(this) && !e.equals(shooter)) {
				if ((shooter instanceof LivingEntity && e instanceof LivingEntity)) {
					if (((LivingEntity)shooter).team != ((LivingEntity)e).team) {
						if (this.collidesWith(e)) {
							if (((Damageable)e).shields > 0) {
								((Damageable)e).damage(0.0, this);
								v.x = random.nextInt(70) - 35;
								v.y = random.nextInt(70) - 35;
								v = v.normalize(true);
								v = v.multiply(25);
								a = random.nextInt(360);
							}
							if (((Damageable)e).shields <= 0) {
								((Damageable)e).damage(3.0, this);
								exists = false;
							}
						}
					}
				} else {
					if (this.collidesWith(e)) {
						((Damageable)e).damage(1.0, this);
						if (((Damageable)e).shields > 0) {
							((Damageable)e).damage(2.0, this);
						}
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
