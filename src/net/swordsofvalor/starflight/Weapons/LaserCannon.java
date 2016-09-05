package net.swordsofvalor.starflight.Weapons;

import org.newdawn.slick.GameContainer;

import net.swordsofvalor.starflight.Entity.Entity;
import net.swordsofvalor.starflight.Entity.Projectiles.Laser;

public class LaserCannon extends Weapon {

	long time = System.currentTimeMillis();
	int shootDelay = 200;
	
	public LaserCannon() {}
	public LaserCannon(int shootDelay) {
		this.shootDelay = shootDelay;
	}
	
	@Override
	public void shoot(GameContainer gc, Entity shooter) {
		float x = shooter.x + shooter.size/2;
		float y = shooter.y + shooter.size/2;
		x += ((30F * Math.sin(-1 * Math.toRadians(shooter.a - 90)))) + shooter.v.x;
		y += ((30F * Math.cos(-1 * Math.toRadians(shooter.a - 90)))) + shooter.v.x;
		new Laser(x, y, shooter.a, shooter);
		time = System.currentTimeMillis();
	}

	@Override
	public boolean canShoot(GameContainer gc) {
		return System.currentTimeMillis() > time + shootDelay;
	}

}
