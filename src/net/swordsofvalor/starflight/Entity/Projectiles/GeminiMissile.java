package net.swordsofvalor.starflight.Entity.Projectiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

import net.swordsofvalor.starflight.StarFlight;
import net.swordsofvalor.starflight.Entity.Damageable;
import net.swordsofvalor.starflight.Entity.Entity;
import net.swordsofvalor.starflight.Entity.LivingEntity;
import net.swordsofvalor.starflight.Entity.Projectile;
import net.swordsofvalor.starflight.Entity.Particles.Smoke;
import net.swordsofvalor.starflight.Util.RenderUtil;

public class GeminiMissile extends Projectile {
	
	double rotation;
	List<Point> path = new ArrayList<>();
	Random random = new Random();
	int t = 0;
	
	public GeminiMissile(float x, float y, Entity entity, boolean dir) {
		super(x, y, 8, entity);
		try {
			image = new Image("res/missile.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 200; i++) {
			Point p;
			if (dir) {
				p = new Point(x + 12 * i, (float) (y + (16 * Math.sin(-1 * Math.toRadians(15*i)))));
			} else {
				p = new Point(x + 12 * i, (float) (y - (16 * Math.sin(-1 * Math.toRadians(15*i)))));
			}
			double x1 = p.getX() - entity.x;
			double y1 = p.getY() - entity.y;
			
			double x2 = x1 * Math.cos(Math.toRadians(entity.a)) - y1 * Math.sin(Math.toRadians(entity.a));
			double y2 = x1 * Math.sin(Math.toRadians(entity.a)) + y1 * Math.cos(Math.toRadians(entity.a));
			
			p.setX((float) (x2 + entity.x));
			p.setY((float) (y2 + entity.y));
			
			path.add(p);
		}
		v.x += (float) (5F * Math.sin(-1 * Math.toRadians(entity.a - 90)));
		v.y += (float) (5F * Math.cos(-1 * Math.toRadians(entity.a - 90)));
		v = v.normalize(true);
		v = v.multiply(5);
	}

	@Override
	public void act(GameContainer gc) {
		a += 20 + (random.nextInt(20) - 10);
		for (Entity e : new ArrayList<>(Entity.ENTITY_LIST)) {
			if (e instanceof Damageable && !e.equals(this) && !e.equals(shooter)) {
				if ((shooter instanceof LivingEntity && e instanceof LivingEntity)) {
					if (((LivingEntity)shooter).team != ((LivingEntity)e).team) {
						if (this.collidesWith(e)) {
							((Damageable)e).damage(2.0, this);
							if (((Damageable)e).shields > 0) {
								Random rand = new Random();
								for (int i = 0; i < 70; i++) {
									((Damageable)e).damage(1.0, this);
									new Smoke(x, y, 2).a = rand.nextFloat()*2F;
								}
							}
							exists = false;
						}
					}
				} else {
					if (this.collidesWith(e)) {
						((Damageable)e).damage(2.0, this);
						System.out.println(((Damageable)e).shields);
						if (((Damageable)e).shields > 0) {
							Random rand = new Random();
							for (int i = 0; i < 70; i++) {
								((Damageable)e).damage(1.0, this);
								new Smoke(x, y, 2).a = rand.nextFloat()*360F;
							}
						}
						exists = false;
					}
				}
			}
		}
		if (v.lengthSquared() < (30*30)) {
			float length = 1 / v.invSqrt((float) v.lengthSquared());
			v = v.normalize(true);
			v = v.multiply(length + 1);
		}
		
		x = path.get(t).getX();
		y = path.get(t).getY();
		
		t++;
		if (t >= path.size()) {
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
