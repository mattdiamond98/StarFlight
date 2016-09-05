package net.swordsofvalor.starflight.Effect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.swordsofvalor.starflight.StarFlight;
import net.swordsofvalor.starflight.Util.MathUtil;
//import net.swordsofvalor.starflight.Util.MathUtil;
import net.swordsofvalor.starflight.Util.RenderUtil;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

public class LightningEffect extends Effect {
	
	List<Line> linesToRender;
	Line baseLine;
	int lineIterations;
	int c = 0;
	
	public LightningEffect(Point p1, Point p2, int lineIterations) {
		baseLine = new Line(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		this.lineIterations = lineIterations;
		linesToRender = new ArrayList<>();
		linesToRender.add(baseLine);
	}
	
	boolean tmp = false;
	@Override
	public void render(GameContainer gc, Graphics g) {
		Point p1 = new Point(baseLine.getX1(),baseLine.getY1());
		Point p2 = new Point(baseLine.getX2(),baseLine.getY2());
		//Point relative = new Point(p2.getX() - p1.getX(), p2.getY() - p1.getY());
		
		//double angle = Math.atan2(Math.cos(Math.toRadians(relative.getX())), Math.sin(Math.toRadians(relative.getY())));
		long distance = distanceBetween(p1, p2);
		
		Line l = new Line(p1.getX(), p1.getY(), p1.getX() + distance, p1.getY());
		linesToRender.clear();
		linesToRender.add(l);
		for (int i = 0; i < lineIterations; i++) {
			linesToRender = fractalize(linesToRender);
		}
//		for (Line line : linesToRender) {
//			line.set(, sy, ex, ey);
//		}
		
		if (tmp) {
			for (int i = 0; i < lineIterations; i++) {
				linesToRender = fractalize(linesToRender);
			}
			tmp = false;
		}
		for (Line line : linesToRender) {
			RenderUtil.drawPlayerLine(g, StarFlight.player, line);
		}
		linesToRender.clear();
		linesToRender.add(baseLine);
		c++;
	}
	
	private List<Line> fractalize(List<Line> lines) {
		List<Line> fractalLines = new ArrayList<Line>();
		for (Line line : lines) {
			Point start = new Point(line.getX1(), line.getY1());
			Point end = new Point(line.getX2(), line.getY2());
			
			Point p1 = getRandomPoint(start, end);
			Point p2;
			if (distanceBetween(start,p1) < distanceBetween(p1,end)) {
				p2 = getRandomPoint(p1,end);
			} else {
				p2 = getRandomPoint(start,p1);
			}
			fractalLines.add(lineBetween(start, p1));
			fractalLines.add(lineBetween(p1,p2));
			fractalLines.add(lineBetween(p2, end));
		}
		return fractalLines;
	}
	
	public List<Line> rotateLines(List<Line> lines, double degrees) {
		List<Line> fractalLines = new ArrayList<Line>();
		
		
		
		return fractalLines;
	}
	
	public Point rotatePoint(Point p1, Point center, float angle) {
		double x1 = p1.getX() - center.getX();
		double y1 = p1.getY() - center.getY();

		double x2 = x1 * Math.cos(angle) - y1 * Math.sin(angle);
		double y2 = x1 * Math.sin(angle) + y1 * Math.cos(angle);

		p1.setX((float) (x2 + center.getX()));
		p1.setY((float) (y2 + center.getY()));
		
		return p1;
	}
	
	private Point getRandomPoint(Point p1, Point p2) {
		Point min = new Point(p1.getX() < p2.getX() ? p1.getX() : p2.getX(),
								p1.getY() < p2.getY() ? p1.getY() : p2.getY());
		
		Point max = new Point(p1.getX() > p2.getX() ? p1.getX() : p2.getX(),
								p1.getY() > p2.getY() ? p1.getY() : p2.getY());
		
		Random rand = new Random();
		Rectangle bounds = new Rectangle(min.getX(), min.getY(), max.getX() - min.getX(), max.getY() - min.getY());
		float height = bounds.getHeight();
		float width = bounds.getWidth();
		return new Point(bounds.getMinX() + (rand.nextFloat() * width), bounds.getMinY() + (rand.nextFloat() * height));
	}
	
	private Line lineBetween(Point p1, Point p2) {
		return new Line(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}
	
	private long distanceBetween(Point p1, Point p2) {
		return (long) (1 / MathUtil.invSqrt((float) (Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2))));
	}
	
}
