package net.swordsofvalor.starflight.Entity.Projectiles;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;

import net.swordsofvalor.starflight.StarFlight;
import net.swordsofvalor.starflight.Entity.Entity;
import net.swordsofvalor.starflight.Entity.Projectile;
import net.swordsofvalor.starflight.Util.RenderUtil;

public class LineProjectile extends Projectile {
	
	boolean shot = false;
	
	public LineProjectile(float x, float y, Entity shooter) {
		super(x, y, 0, shooter);
		a = shooter.a;
	}

	@Override
	public void act(GameContainer gc) {
		if (shot) exists = false;
		else {
			shot = true;
//			for (Entity e : Entity.ENTITY_LIST) {
//				
//			}
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		float x = (float) (shooter.x + (shooter.size/2) - (800 * Math.sin(Math.toRadians(270 - shooter.a))));
		float y = (float) (shooter.y + (shooter.size/2) - (800 * Math.cos(Math.toRadians(270 - shooter.a))));
		RenderUtil.drawLine(g, StarFlight.player, new Line(shooter.x + shooter.size, shooter.y + shooter.size, x, y));
	}

}
