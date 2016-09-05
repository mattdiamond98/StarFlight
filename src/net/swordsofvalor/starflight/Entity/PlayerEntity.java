package net.swordsofvalor.starflight.Entity;

import java.util.Arrays;

import net.swordsofvalor.starflight.StarFlight;
import net.swordsofvalor.starflight.Entity.Intelligence.HumanIntelligence;
import net.swordsofvalor.starflight.Util.RenderUtil;
import net.swordsofvalor.starflight.Weapons.GeminiMissileLauncher;
import net.swordsofvalor.starflight.Weapons.PhotonCannon;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;

public class PlayerEntity extends Ship {
	
	public PlayerEntity(int x, int y) {
		super(x, y, 16, 100, 100, Arrays.asList(new PhotonCannon(), new GeminiMissileLauncher()), new HumanIntelligence(), 1);
		try {
			image = new Image("res/player.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		RenderUtil.drawEntity(g, this, this);
		image.setRotation(Math.round(a));
		
		if (StarFlight.isDebug(gc.getInput())) {
			RenderUtil.drawPlayerLine(g, this, new Line(x + (size),y + (size), x + (size) + v.x*20, y + (size) + v.y*20));			
			RenderUtil.drawBounds(g, this, this);
			g.drawString("x: " + x, 20, 500);
			g.drawString("y: " + y, 20, 520);
			g.drawString("a: " + a, 20, 540);
			g.drawString("vx: " + v.x, 20, 560);
			g.drawString("vy: " + v.y, 20, 580);
			g.drawString("V^2: " + v.lengthSquared() , 20, 600);
		}
		
	}
	
}
