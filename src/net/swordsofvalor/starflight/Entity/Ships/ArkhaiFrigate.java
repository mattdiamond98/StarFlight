package net.swordsofvalor.starflight.Entity.Ships;

import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import net.swordsofvalor.starflight.StarFlight;
import net.swordsofvalor.starflight.Entity.Ship;
import net.swordsofvalor.starflight.Entity.Intelligence.SimpleEnemyAI;
import net.swordsofvalor.starflight.Util.RenderUtil;
import net.swordsofvalor.starflight.Weapons.GeminiMissileLauncher;
import net.swordsofvalor.starflight.Weapons.Weapon;

public class ArkhaiFrigate extends Ship {

	public ArkhaiFrigate(float x, float y) {
		super(x, y, 56, 500, 400, Arrays.asList((Weapon)new GeminiMissileLauncher()), new SimpleEnemyAI(), 0);
		try {
			image = new Image("res/arkhai.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		image.setRotation(Math.round(a));
		RenderUtil.drawEntity(g, StarFlight.player, this);
		for (int i = 0; i < shieldOpac; i++) {
			RenderUtil.drawImage(g, StarFlight.player, shieldImage.getScaledCopy(5F), x - 20, y - 20);
		}
		if (shieldOpac > 0) {
			shieldOpac -= 0.25;
		}
		if (StarFlight.isDebug(gc.getInput())) {
			RenderUtil.drawBounds(g, StarFlight.player, this);
			g.drawString("rhp: " + (health + shields), 20, 400);
		}
	}

}
