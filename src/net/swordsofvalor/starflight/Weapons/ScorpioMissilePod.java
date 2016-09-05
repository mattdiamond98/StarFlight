package net.swordsofvalor.starflight.Weapons;

import net.swordsofvalor.starflight.Entity.Entity;
import net.swordsofvalor.starflight.Entity.Projectiles.ScorpioMissile;

import org.newdawn.slick.GameContainer;

public class ScorpioMissilePod extends Weapon {
	
	private long time = System.currentTimeMillis();
	int count = 0;
	
	@Override
	public void shoot(GameContainer gc, Entity shooter) {
		if (time + 300 < System.currentTimeMillis()) count = 0;
		float x = shooter.x;
		float y = shooter.y;
		x += 4 + shooter.v.x;
		y += 4 + shooter.v.x;
		new ScorpioMissile(x, y, shooter);
		time = System.currentTimeMillis();
		count++;
		if (count >= 4) {
			count = 0;
			time += 800;
		}
	}

	@Override
	public boolean canShoot(GameContainer gc) {
		return System.currentTimeMillis() > time + 40;
	}

}
