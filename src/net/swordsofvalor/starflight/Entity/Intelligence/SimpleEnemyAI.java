package net.swordsofvalor.starflight.Entity.Intelligence;

import java.util.Random;

import net.swordsofvalor.starflight.StarFlight;
import net.swordsofvalor.starflight.Entity.PlayerEntity;
import net.swordsofvalor.starflight.Entity.Ship;

import org.newdawn.slick.GameContainer;

public class SimpleEnemyAI extends ArtificialIntelligence {

	public SimpleEnemyAI() {
		super(275);
	}

	@Override
	protected void onUpdate(GameContainer gc, Ship s, int i) {
		PlayerEntity p = StarFlight.player;
		s.a = Math.toDegrees(Math.atan2(p.y - s.y - (p.size / 2), p.x - s.x - (p.size / 2)));
		if (aiRangeOf(0, 50)) {
			if (s.v.lengthSquared() < s.MAX_V * s.MAX_V) {
				s.v.x -= 0.8 * Math.sin(-1 * Math.toRadians(s.a));
				s.v.y -= 0.8 * Math.cos(-1 * Math.toRadians(s.a));
			}
		}
		aiRangeOf(50, 75);
		if (aiRangeOf(75, 100)) s.shoot(gc, 0);
		if (aiRangeOf(100, 150)) {
			if (s.v.lengthSquared() < s.MAX_V * s.MAX_V) {
				s.v.x -= 0.8 * Math.sin(-1 * Math.toRadians(s.a - 180));
				s.v.y -= 0.8 * Math.cos(-1 * Math.toRadians(s.a - 180));
				s.shoot(gc, 0);
			}
		}
		if (aiRangeOf(150, 200)) s.shoot(gc, 0);
		if (aiRangeOf(200,250)) {
			if (s.v.lengthSquared() < s.MAX_V * s.MAX_V) {
				s.v.x += 1.25 * Math.sin(-1 * Math.toRadians(s.a - 90));
				s.v.y += 1.25 * Math.cos(-1 * Math.toRadians(s.a - 90));
				s.shoot(gc, 0);
			}
		}
		if (s.distanceSquared(p) > (2000 * 2000)) {
			ai = 200;
		}
	}
	
	@Override
	public void handleIntelligence(GameContainer gc, Ship s) {
		onUpdate(gc, s, ai);
		ai++;

		if (ai > aiRange) randomRange();
	}
	
	@Override
	protected boolean aiRangeOf(int start, int end) {
		if (ai == end) {
			randomRange();
		}
		return end > ai && ai >= start;
	}
	
	private void randomRange() {
		int[] ranges = {0,50,100,150,200};
		ai = ranges[new Random().nextInt(ranges.length)];
	}
	
}
