package geometry;

import java.awt.Graphics;
import java.awt.Color;

public class Circle extends Shape{

	private Point center;
	private int radius; 
	private Color color;
	private Color borderColor;
	
	public Circle() {
	}
	
	public Circle(Point center, int radius) {
		//super();
		this.center = center;
		this.radius = radius;
	}
	
	
	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		this.selected = selected;
	}
	
	public Circle(Point center, int radius, boolean selected, Color borderColor) {
		this(center, radius, selected);
		setColor(borderColor);
	}
	
	public Circle(Point center, int radius, Color color, Color innerColor) {
		this(center, radius);
		setColor(color);
		setColor(innerColor);
	} 

	public Circle(Point center, int radius, boolean selected, Color borderColor, Color color) {
		this(center, radius, selected, color);
		setColor(color);
	} 
	
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle pomocna = (Circle) obj; 
			if (this.center.equals(pomocna.center) && this.radius == pomocna.radius) 
				return true; 
			else
				return false; 
		} else
			return false;
	}
	
	public boolean contains(int x, int y) {
		Point sadrziTacku = new Point(x,y);
		return (this.center.distance(sadrziTacku) <= this.radius);
	}
	
	public boolean contains(Point sadrziTacku)
	{
		//linija ispod je nepotrebna i nema smisla stavljato je 
		return (this.center.distance(sadrziTacku) <= this.radius);
	}
	
	@Override
 	public void draw(Graphics g) {
		g.setColor(borderColor);
 		g.drawOval(center.getX()-radius,center.getY()-radius, 2*radius, 2*radius);
 		g.setColor(color);
		g.fillOval(center.getX()-radius,center.getY()-radius , radius*2, radius*2); //fill za unutrasnjost

 		if (isSelected()) {
 			g.setColor(Color.BLUE);
 			g.drawRect(center.getX() - 2, center.getY() - 2, 4, 4);
 			g.drawRect(center.getX() - radius - 2, center.getY() - 2, 4, 4);
 			g.drawRect(center.getX() + radius - 2, center.getY() - 2, 4, 4);
 			g.drawRect(center.getX() - 2, center.getY() - radius - 2, 4, 4);
 			g.drawRect(center.getX() - 2, center.getY() + radius - 2, 4, 4);
 			g.setColor(Color.black);
 		}
 	}

	public void moveTo(int x, int y) {
 		center.moveTo(x, y);
 	}
 	@Override
 	public void moveBy(int x, int y) {
 		center.moveBy(x, y);
 	}
 	@Override
 	public int compareTo(Object obj) {
 		if(obj instanceof Circle) {
 			Circle shapeToCompare = (Circle)obj;
 			return (int)(this.area() 
 					- shapeToCompare.area());
 		}
 		return 0;
 	}
	public double area() {
		return radius*radius*Math.PI;
	}
	
	public double circumference() {
		return 2*radius*Math.PI; 
	}
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) throws Exception{
	 	if(radius < 0) {
	 		throw new Exception("Radius ne sme "
	 					+ "biti negativna vrednost");
	 		}
	}
	
	
	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String toString() {
 		return "Center: " + center + ", radius = " + radius;
	}
}