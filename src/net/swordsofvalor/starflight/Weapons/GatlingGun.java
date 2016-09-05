package net.swordsofvalor.starflight.Weapons;

import net.swordsofvalor.starflight.Entity.Entity;
import net.swordsofvalor.starflight.Entity.Projectiles.LineProjectile;

import org.newdawn.slick.GameContainer;

public class GatlingGun extends Weapon {

	int count = 0;
	long time = System.currentTimeMillis();
		
	@Override
	public void shoot(GameContainer gc, Entity shooter) {
		float x = shooter.x;
		float y = shooter.y;
		new LineProjectile(x, y, shooter);
		time = System.currentTimeMillis();
		count++;
	}

	@Override
	public boolean canShoot(GameContainer gc) {
		return System.currentTimeMillis() > time + 120;
	}

}
