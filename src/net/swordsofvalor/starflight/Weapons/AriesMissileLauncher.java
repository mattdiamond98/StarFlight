package net.swordsofvalor.starflight.Weapons;

import org.newdawn.slick.GameContainer;

import net.swordsofvalor.starflight.Entity.Entity;
import net.swordsofvalor.starflight.Entity.Projectiles.AriesMissile;

public class AriesMissileLauncher extends Weapon {
	
	long time = System.currentTimeMillis();
	AriesMissile missile;

	@Override
	public void shoot(GameContainer gc, Entity shooter) {
		if (missile != null ? !missile.exists : true) {
			if (System.currentTimeMillis() > time) {
				missile = new AriesMissile(shooter.x + (shooter.size/2), shooter.y + (shooter.size/2), shooter);
				time = System.currentTimeMillis() + 1000;
			}
		} else {
			if (System.currentTimeMillis() > time) {
				missile.detonate(gc);
				time = System.currentTimeMillis() + 2000;
			}
		}
	}

	@Override
	public boolean canShoot(GameContainer gc) {
		return true;	
	}

}
