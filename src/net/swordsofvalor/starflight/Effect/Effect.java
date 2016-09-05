package net.swordsofvalor.starflight.Effect;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class Effect {
	
	public static final List<Effect> EFFECTS_LIST = new ArrayList<>();
	
	public Effect() {
		EFFECTS_LIST.add(this);
	}
	
	public abstract void render(GameContainer gc, Graphics g);
	
}
