package net.swordsofvalor.starflight.Weapons;

import org.newdawn.slick.GameContainer;

import net.swordsofvalor.starflight.Entity.Entity;
import net.swordsofvalor.starflight.Entity.Projectiles.Photon;

public class PhotonCannon extends Weapon {
	
	long time = System.currentTimeMillis();
	int count = 0;
	
	public PhotonCannon() {
//		try {
//			hudImage = new Image("res/photon.png").getScaledCopy(2);
//		} catch (SlickException e) {
//			e.printStackTrace();
//		}
	}
	
	@Override
	public void shoot(GameContainer gc, Entity shooter) {
		if (time + 300 < System.currentTimeMillis()) count = 0;
		float x = (float) (count%2==1 ? (shooter.x + (shooter.size/2)) - (10 * Math.sin(Math.toRadians(shooter.a))):
										(shooter.x + (shooter.size/2)) - (10 * Math.sin(Math.toRadians(shooter.a - 180))));
		float y = (float) (count%2==0 ? (shooter.y + (shooter.size/2)) - (10 * Math.cos(Math.toRadians(shooter.a))):
										(shooter.y + (shooter.size/2)) - (10 * Math.cos(Math.toRadians(shooter.a - 180))));
		x += 4 + ((30F * Math.sin(-1 * Math.toRadians(shooter.a - 90)))) + shooter.v.x;
		y += 4 + ((30F * Math.cos(-1 * Math.toRadians(shooter.a - 90)))) + shooter.v.x;
		new Photon(x, y, shooter.a, shooter);
		time = System.currentTimeMillis();
		count++;
		if (count >= 10) {
			count = 0;
			time += 800;
		}
	}

	@Override
	public boolean canShoot(GameContainer gc) {
		return System.currentTimeMillis() > time + 20;
	}

}
