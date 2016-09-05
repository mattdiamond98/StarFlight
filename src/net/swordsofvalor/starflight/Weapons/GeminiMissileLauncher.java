package net.swordsofvalor.starflight.Weapons;

import org.newdawn.slick.GameContainer;

import net.swordsofvalor.starflight.Entity.Entity;
import net.swordsofvalor.starflight.Entity.Projectiles.GeminiMissile;

public class GeminiMissileLauncher extends Weapon {
	
	long time = System.currentTimeMillis();
	int timeAmt = 1400;
	
	public GeminiMissileLauncher() {
//		try {
//			hudImage = new Image("res/missile.png").getScaledCopy(2);
//		} catch (SlickException e) {
//			e.printStackTrace();
//		}
	}
	public GeminiMissileLauncher(int timeAmt) {
//		try {
//			hudImage = new Image("res/missile.png").getScaledCopy(2);
//		} catch (SlickException e) {
//			e.printStackTrace();
//		}
		this.timeAmt = timeAmt;
	}
	
	@Override
	public void shoot(GameContainer gc, Entity shooter) {
		new GeminiMissile((float) (shooter.x + (shooter.size/2)),
				(float) (shooter.y + (shooter.size/2)), shooter, true);
	new GeminiMissile((float) (shooter.x + (shooter.size/2)),
				(float) (shooter.y + (shooter.size/2)), shooter, false);
		time = System.currentTimeMillis();
	}

	@Override
	public boolean canShoot(GameContainer gc) {
		return System.currentTimeMillis() > time + timeAmt;			
	}
}
