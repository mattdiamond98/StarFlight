package net.swordsofvalor.starflight.Entity;

public abstract class Damageable extends Entity {
	
	public double maxShields;
	public double shields;
	
	public double maxHealth;
	public double health;
	
	public Damageable(float x, float y, int size, double maxHealth, double maxShields) {
		super(x, y, size);
		this.maxHealth = maxHealth;
		this.maxShields = maxShields;
		this.health = maxHealth;
		this.shields = maxShields;
	}
	
	public void onDeath(Entity killer) {}
	public void onAbsorb(double damage) {}
	public void onHit(double damage) {}
	
	public void addShields(double amt) {
		shields += amt;
		if (shields > maxShields) {
			shields = maxShields;
		}
	}
	
	public void heal(double amt) {
		health += amt;
		if (health > maxHealth) {
			health = maxHealth;
		}
	}
	
	public void damage(double amt, Entity damageCause) {
		if (shields > 0) {
			shields -= amt;
			onAbsorb(amt);
			if (shields < 0) {
				shields = 0;
			}
		} else if (health >= 0){
			health -= amt;
			onHit(amt);
			if (health < 0) {
				exists = false;
				onDeath(damageCause);
			}
		}
	}
	
}
