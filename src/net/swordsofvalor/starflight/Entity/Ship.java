package net.swordsofvalor.starflight.Entity;

import java.util.List;
import java.util.Random;

import net.swordsofvalor.starflight.StarFlight;
import net.swordsofvalor.starflight.Entity.Intelligence.Intelligence;
import net.swordsofvalor.starflight.Entity.Particles.Smoke;
import net.swordsofvalor.starflight.Util.RenderUtil;
import net.swordsofvalor.starflight.Weapons.Weapon;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Ship extends LivingEntity {
	
	public float MAX_V = 20.0F;
	public float ACCEL = 1.0F;
	
	private List<Weapon> weapons;
	protected int selectedWeapon = 0;
	
	protected float shieldOpac = 0;
	protected Image shieldImage;
	
	public Ship(float x, float y, int size, double maxHealth, double maxShields, List<Weapon> weapons, Intelligence intelligence, int team) {
		super(x, y, size, maxHealth, maxShields, intelligence, team);
		this.weapons = weapons;
		
		try {
			shieldImage = new Image("res/shield.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void shoot(GameContainer gc, int weapon) {
		if (weapons.size() > weapon) {
			if (weapons.get(weapon).canShoot(gc)) {
				weapons.get(weapon).shoot(gc, this);
			}
		}
	}
	
	public List<Weapon> getWeapons() {
		return weapons;
	}
	
	public abstract void render(GameContainer gc, Graphics g);
	
	public void shipRender(GameContainer gc, Graphics g) {
		for (int i = 0; i < shieldOpac; i++) {
			RenderUtil.drawImage(g, StarFlight.player, shieldImage.getScaledCopy(1.5F), x - 8, y - 8);
		}
		if (shieldOpac > 0) {
			shieldOpac -= 0.25;
		}
		for (int i = 0; i < weapons.size(); i++) weapons.get(i).renderWeapon(gc, g, i);
	}
	
	@Override
	public void onHit(double amt) {
		Random random = new Random();
		double chance = 1 - (health/maxHealth);
		for (int i = 0; i < 3; i++) {
			if (random.nextDouble() < chance) {
				Smoke s = new Smoke(x + (random.nextInt(16) - 4), y + (random.nextInt(16) - 4), 20);
				s.scale = (float) (random.nextFloat() * 2F * chance);
				s.a = random.nextFloat() * 360;
			}
		}
	}
	
	@Override
	public void onAbsorb(double amt) {
		shieldOpac += 2;
	}
	
}
