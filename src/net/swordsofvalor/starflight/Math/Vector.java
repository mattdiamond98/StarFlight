package net.swordsofvalor.starflight.Math;

public class Vector {
	
	public float x;
	public float y;
	
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector(double x, double y) {
		this((float)x,(float)y);
	}
	
	public Vector(int x, int y) {
		this((float)x,(float)y);
	}
	
	public Vector() {
		this(0,0);
	}
	
	public Vector add(Vector vec) {
		x += vec.x;
		y += vec.y;
		return this;
	}
	
	public Vector subtract(Vector vec) {
		x -= vec.x;
		y -= vec.y;
		return this;
	}
	
	public Vector multiply(Vector vec) {
		x *= vec.x;
		y *= vec.y;
		return this;
	}
	
	public Vector multiply(float m) {
		x *= m;
		y *= m;
		return this;
	}
	
	public Vector multiply(double m) {
		x *= m;
		y *= m;
		return this;
	}
	
	public Vector divide(Vector vec) {
		x /= vec.x;
		y /= vec.y;
		return this;
	}
	
	public double length() {
		return Math.sqrt(lengthSquared());
	}
	
	public double lengthSquared() {
		return Math.pow(x, 2) + Math.pow(y, 2);
	}
	
	public Vector normalize() {
		double length = length();
		x /= length;
		y /= length;
		return this;
	}
	
	public Vector normalize(boolean speed) {
		if (speed) {
			double length = 1 / invSqrt((float) lengthSquared());
			x /= length;
			y /= length;
			return this;
		} else {
			return normalize();
		}
	}
	
	public float invSqrt(float x) {
	    float xhalf = 0.5f*x;
	    int i = Float.floatToIntBits(x);
	    i = 0x5f3759df - (i>>1);
	    x = Float.intBitsToFloat(i);
	    x = x*(1.5f - xhalf*x*x);
	    return x;
	}
	
}
