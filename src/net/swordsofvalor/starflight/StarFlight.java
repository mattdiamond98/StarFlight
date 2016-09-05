package net.swordsofvalor.starflight;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.swordsofvalor.starflight.Effect.Effect;
import net.swordsofvalor.starflight.Entity.Entity;
import net.swordsofvalor.starflight.Entity.LivingEntity;
import net.swordsofvalor.starflight.Entity.PlayerEntity;
import net.swordsofvalor.starflight.Entity.Ship;
import net.swordsofvalor.starflight.Entity.Ships.Gthora;
import net.swordsofvalor.starflight.Entity.Ships.Rathu;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class StarFlight extends BasicGame {
	
	private static AppGameContainer appgc;
	
	private Timer timer;
	private static Scheduler scheduler;
	
	public static final int SCREEN_X = 1100;
	public static final int SCREEN_Y = 650;
	
	public static int difficulty = 0;
	public static int score = 0;
	public static boolean spawnEntities = true;
	public static boolean update = true;
	
	private static Image background;
	private static Image hud;
	private static Image shieldgradient;
	private static Image healthgradient;
	private static Image minimapbackground;
	
	public static PlayerEntity player;
	
	public StarFlight() {
		super("StarFlight");
	}

	public static void main(String[] args) {
		try {
			appgc = new AppGameContainer(new StarFlight());
			appgc.setDisplayMode(SCREEN_X, SCREEN_Y, false);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(StarFlight.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		timer = new Timer(50);
		scheduler = new Scheduler();
		player = new PlayerEntity(0,0);
		difficulty = 0;
		score = 0;
		background = new Image("res/spacebackground.jpg").getScaledCopy(20);
		hud = new Image("res/hud.png");
		shieldgradient = new Image("res/shieldgradient.png");
		healthgradient = new Image("res/healthgradient.png");
		minimapbackground = new Image("res/minimap.png");
		
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		timer.tick(delta);
		while (timer.time()) {
			tick(gc);
			timer.reset();
		}
		if (!player.exists) {
			if (gc.getInput().isKeyDown(Input.KEY_ESCAPE)) {
				Entity.ENTITY_LIST.clear();
				gc.reinit();
			}
		}
	}
	
	public void tick(GameContainer gc) {
		scheduler.updateScheduler();
		boolean nextLevel = true;
		for (Entity e : new ArrayList<>(Entity.ENTITY_LIST)) {
			if (e.exists) {
				e.act(gc);
				if (e instanceof LivingEntity) {
					if (((LivingEntity)e).team != ((LivingEntity)player).team) {
						nextLevel = false;
					}
				}
			} else {
				Entity.ENTITY_LIST.remove(e);
			}
		}
		if (nextLevel && update) {
			scheduler.scheduleSyncDelayedTask(new Runnable() {
				@Override
				public void run() {
					nextLevel();
					update = true;
				}
			}, 1000);
			update = false;
			score += difficulty * 500;
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
//		new LightningEffect(new Point(0,0), new Point(100,100), 6).render(gc, g);
		g.drawImage(background, -11000 - (player.x/1), -7000 - (player.y/1));
		g.drawImage(hud, 0, SCREEN_Y - hud.getHeight());
		g.drawString("Level " + difficulty, 10, 60);
		g.drawString("Score " + score, 10, 74);
		renderHud(g);
		for (Entity e : Entity.ENTITY_LIST) {
			if (e.exists) {
				e.render(gc, g);
				if (e instanceof Ship) {
					((Ship)e).shipRender(gc, g);
				}
			}
		}
		for (Effect e : Effect.EFFECTS_LIST) {
			e.render(gc, g);
		}
		if (!player.exists) {
			g.drawString("GAME OVER", SCREEN_X/2-24, SCREEN_Y/2-15);
			g.drawString("Press ESC to restart", SCREEN_X/2-34, SCREEN_Y/2);
		}
		if (isDebug(gc.getInput())) {
			Input in = gc.getInput();
			g.drawString("mouse x: " + in.getAbsoluteMouseX(), 10, 40);
			g.drawString("mouse y: " + in.getAbsoluteMouseY(), 10, 60);
		}
	}
	
	private void renderHud(Graphics g) {
		g.fillRect(11, SCREEN_Y - 39, (float) (303 * (player.shields/player.maxShields)), 15, shieldgradient, 0, 0);
		g.fillRect(11, SCREEN_Y - 24, (float) (303 * (player.health/player.maxHealth)), 15, healthgradient, 0, 0);
		g.drawImage(minimapbackground.getScaledCopy(2), SCREEN_X - 128, SCREEN_Y - 128);
		g.fillRect(SCREEN_X - 128 + 60, SCREEN_Y - 128 + 60, 4, 4, shieldgradient, 0, 0);
		for (Entity e : Entity.ENTITY_LIST) {
			if (e instanceof LivingEntity) {
				if (((LivingEntity)e).team != player.team) {
					g.fillRect(SCREEN_X - 128 + 60 - (player.x - e.x)/50, SCREEN_Y - 128 + 60 - (player.y - e.y)/50, 4, 4, healthgradient, 0, 0);
				}
			}
		}
	}
	
	public static void nextLevel() {
		Random rand = new Random();
		player.shields = player.maxShields;
		if (spawnEntities) {
			difficulty++;
			for (int i = 0; i < StarFlight.difficulty - StarFlight.difficulty/3; i++) {
				new Rathu(StarFlight.player.x + rand.nextInt(1000) - 500, StarFlight.player.y + rand.nextInt(1000) - 500);
			}
			for (int i = 0; i < StarFlight.difficulty / 3; i++) {
				new Gthora(StarFlight.player.x + rand.nextInt(1000) - 500, StarFlight.player.y + rand.nextInt(1000) - 500);
			}
		}
	}
	
	public static boolean isDebug(Input in) {
		return in.isKeyDown(Input.KEY_F3);
	}
	
	public static Scheduler getScheduler() {
		return scheduler;
	}
	
}
