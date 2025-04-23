package assignment9;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class BodySegment {

	private double x, y, size;
	private Color color;
	
	public BodySegment(double x, double y, double size) {
		//FIXME
	    
		//See ColorUtils for some color options (or choose your own)
		this.x=x;
		this.y=y;
		this.size=size;
		this.color=ColorUtils.solidColor();
	}
	public double getX() {
		return (this.x);
	}
	
	public double getY() {
		return (this.y);
	}
	
	public Color getColor() {
		// FIXME
		return (this.color);
	}
	
	public void setX(double newx) {
		this.x=newx;
	}
	
	public void setY(double newy) {
		this.y=newy;
	}
	
	/**
	 * Draws the segment
	 */
	public void draw() {
		//FIXME
		StdDraw.setPenColor(this.color);
		StdDraw.filledCircle(this.x, this.y, this.size);
	}
	
}