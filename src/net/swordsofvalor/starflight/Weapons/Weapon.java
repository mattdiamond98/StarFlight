package net.swordsofvalor.starflight.Weapons;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import net.swordsofvalor.starflight.Entity.Entity;

public abstract class Weapon {
	
	Image hudImage;
	
	public static enum WeaponType {
		LIGHT,MEDIUM,HEAVY;
	}
	
	public void renderWeapon(GameContainer gc, Graphics g, int position) {
		if (hudImage != null) {
			g.drawImage(hudImage, 352 + (42*position), 616);
		}
	}
	
	public abstract void shoot(GameContainer gc, Entity shooter);
	public abstract boolean canShoot(GameContainer gc);
	
}
