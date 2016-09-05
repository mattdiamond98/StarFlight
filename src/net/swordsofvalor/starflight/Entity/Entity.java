package net.swordsofvalor.starflight.Entity;

import java.util.ArrayList;
import java.util.List;

import net.swordsofvalor.starflight.StarFlight;
import net.swordsofvalor.starflight.Math.Vector;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

public abstract class Entity {
	
	public static final List<Entity> ENTITY_LIST = new ArrayList<>();
	
	public Image image;
	
	public boolean exists;
	
	public float x;
	public float y;
	public double a;
	
	public int size;
	
	public Vector v;
	
	public Entity(float x, float y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
		v = new Vector();
		exists = true;
		ENTITY_LIST.add(this);
	}
	
	public abstract void act(GameContainer gc);
	public abstract void render(GameContainer gc, Graphics g);
	
	public Point getCenter() {
		return new Point(x, y);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((x - (size/2)), (y - (size/2)), 2*size, 2*size);
	}
	
	public Rectangle getVision() {
		return new Rectangle(x - StarFlight.SCREEN_X/2, y - StarFlight.SCREEN_Y/2, StarFlight.SCREEN_X, StarFlight.SCREEN_Y);
	}
	
	public boolean canSee(Entity e) {
		Rectangle entity = e.getBounds();
		Rectangle vision = getVision();
		return (vision.contains(entity.getMinX(), entity.getMinY()) ||
				vision.contains(entity.getMaxX(), entity.getMinY()) ||
				vision.contains(entity.getMinX(), entity.getMaxY()) ||
				vision.contains(entity.getMaxX(), entity.getMaxY()));
	}
	
	public boolean collidesWith(Entity e) {
		return this.contains(e) || e.contains(this);
	}
	
	public boolean contains(Entity e) {
		Rectangle rect1 = this.getBounds();
		Rectangle rect2 = e.getBounds();
		return (rect1.contains(rect2.getMinX(), rect2.getMinY()) ||
				rect1.contains(rect2.getMaxX(), rect2.getMinY()) ||
				rect1.contains(rect2.getMinX(), rect2.getMaxY()) ||
				rect1.contains(rect2.getMaxX(), rect2.getMaxY()));
	}
	
	public float distanceSquared(Entity e) {
		return distanceSquared(new Point(e.x, e.y));
	}
	
	public float distanceSquared(Point p) {
		return (float) (Math.pow(x-p.getX()+(size/2),2) + Math.pow(y-p.getX()+(size/2), 2));
	}
	
}
