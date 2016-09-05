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
import net.swordsofvalor.starflight.Entity.Particles.Smoke;
import net.swordsofvalor.starflight.Util.RenderUtil;

public class ScorpioMissile extends Projectile {
	
	Random rand = new Random();
	
	double curveAmt = 0;
	boolean curveRight = true;
	double speed = 1;
	
	public ScorpioMissile(float x, float y, Entity entity) {
		super(x, y, 8, entity);
		this.a = entity.a;
		curveAmt = (Math.random() * 1) - 0.5;
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
		curveRight = Math.random() >= 0.5 ? true : false;
	}

	@Override
	public void act(GameContainer gc) {
		if (curveRight) curveAmt += 0.2;
		else curveAmt -= 0.2;
		a+= curveAmt;
		new Smoke(this.x, this.y, this.size).a = rand.nextFloat() * 360F;
		if (Math.random() > 0.9) {
			curveRight = !curveRight;
			if (curveRight) curveAmt += 1;
			else curveAmt -= 1d;
		}
		x += v.x;
		y += v.y;
		v.x += (float) (20 * Math.sin(-1 * Math.toRadians(a - 90)));
		v.y += (float) (20 * Math.cos(-1 * Math.toRadians(a - 90)));
		v = v.normalize(true);
		v.multiply(10 * speed);
		speed += 0.05;
		if (speed > 6) this.exists = false;
		for (Entity e : new ArrayList<>(Entity.ENTITY_LIST)) {
			if (e instanceof Damageable && !e.equals(this) && !e.equals(shooter)) {
				if ((shooter instanceof LivingEntity && e instanceof LivingEntity)) {
					if (((LivingEntity)shooter).team != ((LivingEntity)e).team) {
						if (this.collidesWith(e)) {
							((Damageable)e).damage(5.0, this);
							if (((Damageable)e).shields > 0) {
								for (int i = 0; i < 10; i++) {
									if (((Damageable)e).shields > 0)
									((Damageable)e).damage(5.0, this);
								}
							}
							exists = false;
						}
					}
				} else {
					if (this.collidesWith(e)) {
						((Damageable)e).damage(5.0, this);
						if (((Damageable)e).shields > 0) {
							for (int i = 0; i < 10; i++) {
								if (((Damageable)e).shields > 0)
								((Damageable)e).damage(5.0, this);
							}
						}
						exists = false;
					}
				}
			}
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
