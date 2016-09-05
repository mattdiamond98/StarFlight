package net.swordsofvalor.starflight.Weapons;

import org.newdawn.slick.GameContainer;

import net.swordsofvalor.starflight.Entity.Entity;
import net.swordsofvalor.starflight.Entity.Projectiles.Bullet;

public class KineticBlaster extends Weapon {
	
	int count = 0;
	long time = System.currentTimeMillis();
		
	@Override
	public void shoot(GameContainer gc, Entity shooter) {
		float x = (float) (count%2==1 ? (shooter.x + (shooter.size/2)) - (8 * Math.sin(Math.toRadians(shooter.a))):
										(shooter.x + (shooter.size/2)) - (8 * Math.sin(Math.toRadians(shooter.a - 180))));
		float y = (float) (count%2==0 ? (shooter.y + (shooter.size/2)) - (8 * Math.cos(Math.toRadians(shooter.a))):
										(shooter.y + (shooter.size/2)) - (8 * Math.cos(Math.toRadians(shooter.a - 180))));
		x += 4 + ((30F * Math.sin(-1 * Math.toRadians(shooter.a - 90))));
		y += 4 + ((30F * Math.cos(-1 * Math.toRadians(shooter.a - 90))));
		new Bullet(x, y, shooter);
		time = System.currentTimeMillis();
		count++;
	}

	@Override
	public boolean canShoot(GameContainer gc) {
		return System.currentTimeMillis() > time + 20;
	}

}
