package net.swordsofvalor.starflight.Util;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import net.swordsofvalor.starflight.StarFlight;
import net.swordsofvalor.starflight.Entity.Entity;
import net.swordsofvalor.starflight.Entity.PlayerEntity;

public final class RenderUtil {
	
	public static void drawEntity(Graphics g, PlayerEntity p, Entity e) {
		if (p.canSee(e)) {
			g.drawImage(e.image, StarFlight.SCREEN_X / 2- (p.x - e.x), StarFlight.SCREEN_Y / 2 - (p.y - e.y));
		}
	}
	
	public static void drawEntityToScale(Graphics g, PlayerEntity p, Entity e, float scale) {
		if (p.canSee(e)) {
			g.drawImage(e.image.getScaledCopy(scale), StarFlight.SCREEN_X / 2- (p.x - e.x), StarFlight.SCREEN_Y / 2 - (p.y - e.y));
		}
	}
	
	public static void drawImage(Graphics g, PlayerEntity p, Image image, float x, float y) {
		g.drawImage(image, StarFlight.SCREEN_X / 2 - (p.x - x), StarFlight.SCREEN_Y / 2 - (p.y - y));
	}
	
	public static void drawPlayerLine(Graphics g, PlayerEntity p, Line line) {
		g.drawLine((StarFlight.SCREEN_X / 2 - (p.x - line.getX1())), (StarFlight.SCREEN_Y / 2 - (p.y - line.getY1())),
				(StarFlight.SCREEN_X / 2 - (p.x - line.getX2())), (StarFlight.SCREEN_Y / 2 - (p.y - line.getY2()))); 
	}
	
	public static void drawLine(Graphics g, PlayerEntity p, Line line) {
//		g.drawImage(e.image, StarFlight.SCREEN_X / 2- (p.x - e.x), StarFlight.SCREEN_Y / 2 - (p.y - e.y));
		g.drawLine(StarFlight.SCREEN_X / 2 - (p.x - line.getX1()), 
				StarFlight.SCREEN_Y / 2 - (p.y - line.getY1()),
				StarFlight.SCREEN_X / 2 - (p.x - line.getX2()), 
				StarFlight.SCREEN_Y / 2 - (p.y - line.getY2()));
	}
	
	public static void fillShape(Graphics g, PlayerEntity p, Shape shape, float x, float y) {
		shape.setCenterX(StarFlight.SCREEN_X / 2 - (p.x - x));
		shape.setCenterY(StarFlight.SCREEN_Y / 2 - (p.y - y));
		g.draw(shape);
	}
	
	public static void fillShape(Graphics g, PlayerEntity p, Shape shape, float x, float y, Color c) {
		Color setTo = g.getColor();
		g.setColor(c);
		fillShape(g, p, shape, x , y);
		g.setColor(setTo);
	}
	
	public static void drawBounds(Graphics g, PlayerEntity p, Entity e) {
		if (p.canSee(e)) {
			Rectangle rect = e.getBounds();
			rect.setBounds(StarFlight.SCREEN_X / 2 - (p.x - e.x), StarFlight.SCREEN_Y/2 - (p.y - e.y), rect.getWidth(), rect.getHeight());
			g.draw(rect);
		}
	}
	
	public static int relX(PlayerEntity player) {
		return StarFlight.SCREEN_X / 2 + (player.size/2);
	}
	
	public static int relY(PlayerEntity player) {
		return StarFlight.SCREEN_Y /2 + (player.size/2);
	}
	
}
