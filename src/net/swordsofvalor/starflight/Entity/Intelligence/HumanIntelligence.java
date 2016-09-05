package net.swordsofvalor.starflight.Entity.Intelligence;

import net.swordsofvalor.starflight.StarFlight;
import net.swordsofvalor.starflight.Entity.Ship;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public final class HumanIntelligence extends ShipIntelligence {
	
	int weaponSelected = 0;
	
	@Override
	public void handleIntelligence(GameContainer gc, Ship s) {
		
		Input input = gc.getInput();
		
		float mouseX = input.getMouseX();
		float mouseY = input.getMouseY();
		
		s.a = Math.toDegrees(Math.atan2(mouseY - (StarFlight.SCREEN_Y / 2) - (s.size / 2), mouseX - (StarFlight.SCREEN_X / 2) - (s.size / 2)));
		if (input.isMouseButtonDown(0)) {
			s.shoot(gc, weaponSelected);
		} else if (input.isMouseButtonDown(1)) {
			if (weaponSelected + 1 < s.getWeapons().size())
			s.shoot(gc, weaponSelected + 1);
			else s.shoot(gc, 0);
		}
		for (int i = 0; i < s.getWeapons().size(); i++) {
			if (input.isKeyPressed(i + 2)) {
				weaponSelected = i;
			}
		}
		if (input.isKeyDown(Input.KEY_W)) {
			if (s.v.lengthSquared() < s.MAX_V * s.MAX_V) {
				s.v.x += s.ACCEL * 1.25 * Math.sin(-1 * Math.toRadians(s.a - 90));
				s.v.y += s.ACCEL * 1.25 * Math.cos(-1 * Math.toRadians(s.a - 90));
			}
		}
		if (input.isKeyDown(Input.KEY_S)) {
			if (s.v.lengthSquared() < s.MAX_V * s.MAX_V) {
				s.v.x -= s.ACCEL * 1.0 * Math.sin(-1 * Math.toRadians(s.a - 90));
				s.v.y -= s.ACCEL * 1.0 * Math.cos(-1 * Math.toRadians(s.a - 90));
			}
		}
		if (input.isKeyDown(Input.KEY_A)) {
			if (s.v.lengthSquared() < s.MAX_V * s.MAX_V) {
				s.v.x -= s.ACCEL * 0.8 * Math.sin(-1 * Math.toRadians(s.a));
				s.v.y -= s.ACCEL * 0.8 * Math.cos(-1 * Math.toRadians(s.a));
			}
		}
		if (input.isKeyDown(Input.KEY_D)) {
			if (s.v.lengthSquared() < s.MAX_V * s.MAX_V) {
				s.v.x -= s.ACCEL * 0.8 * Math.sin(-1 * Math.toRadians(s.a - 180));
				s.v.y -= s.ACCEL * 0.8 * Math.cos(-1 * Math.toRadians(s.a - 180));
			}
		}
		
	}

}
