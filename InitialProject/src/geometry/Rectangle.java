package geometry;

import java.awt.Graphics;

public class Rectangle extends Shape{

	private Point upperLeftPoint;
	private int width;
	private int height;
	
	public Rectangle () {
		
	}
	public Rectangle(Point upperLeftPoint, int width, int height) {
		this.upperLeftPoint = upperLeftPoint;
		this.width = width;
		this.height = height;
	}

	
	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected) {
		this(upperLeftPoint, width, height);
		this.selected = selected;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Rectangle) {
			Rectangle pomocna = (Rectangle) obj;
			if (this.upperLeftPoint.equals(pomocna.upperLeftPoint) && this.width == pomocna.width 
					&& this.height == pomocna.height)
				return true;
			else 
				return false;
		} else
			return false;
	}
	
	public boolean contains(int x, int y) {
		return (x >= this.upperLeftPoint.getX() 
				&& x <= this.upperLeftPoint.getX() + width
				&& y >= this.upperLeftPoint.getY()
				&& y <= this.upperLeftPoint.getY() + height);
	}
	
	//overloading nam sluzi kad imamo konstruktore da pravimo vise metoda koje se isto zovu 
	public boolean contains(Point sadrziTacku)
	{
		return (sadrziTacku.getX() >= this.upperLeftPoint.getX() 
				&& sadrziTacku.getX()<= this.upperLeftPoint.getX() + width
				&& sadrziTacku.getY() >= this.upperLeftPoint.getY()
				&& sadrziTacku.getY() <= this.upperLeftPoint.getY() + height);
	}
	
	@Override
 	public void draw(Graphics g) {
 		g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(),
 				width, height);
 	}

	public int area() {
		return width * height;
	}
	
	public int circumference() {
		return 2*width+2*height;
	}
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public String toString() {
		return "Upper left point: " + upperLeftPoint + ", width = " + width +", height = " + height;
	}
	
}
