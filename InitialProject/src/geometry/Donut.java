package geometry;

import java.awt.Graphics;
import java.awt.Color;

public class Donut extends Circle {
	// nasledjuse center, radius i selected od Circle
	private int innerRadius;
	private Color color;
	private Color borderColor;

	public Donut() {
	}
	
	public Donut(Point center, int radius,  int innerRadius) {
		super(center, radius);
			this.innerRadius=innerRadius;
	}


	// ovaj prvi je vise asamo demostramncije radi
	public Donut(Point center, int radius, boolean selected) {
		super(center, radius, selected); // super je kao this samo sto poziva iz nadredjene klase - poziv konstruktora
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		// umesto usper mogu i
		this(center, radius, selected);
		this.innerRadius = innerRadius;
	}
	
	public Donut(Point center, int radius, int innerRadius,  Color color, Color borderColor) { 
		this(center, radius, innerRadius);
		this.color = (color != null) ? color : Color.white;
		this.borderColor = (borderColor != null) ? borderColor : Color.BLACK;
	}
	
	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}
	public double circumference() {
		return super.circumference() + 2 * innerRadius * Math.PI;
	}
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut pomocna = (Donut) obj;
			if (getCenter().equals(pomocna.getCenter()) && getRadius() == pomocna.getRadius()
					&& innerRadius == pomocna.getInnerRadius())
				return true;
			else
				return false;
		} else
			return false;
	}
	public boolean contains(int x, int y) {
		return super.contains(x, y) && getCenter().distance(new Point(x, y)) >= innerRadius;
	}
	public boolean contains(Point p) {

 		return this.contains(p.getX(), p.getY());
 	}

 	@Override
 	public void draw(Graphics g) {
 		super.draw(g);
 		g.setColor(getBorderColor());
 		g.drawOval(getCenter().getX()-innerRadius,
 				getCenter().getY()-innerRadius, 2*innerRadius,
 				2*innerRadius);
 		g.setColor(color);
 		g.fillOval(getCenter().getX()-innerRadius, 
				getCenter().getY()-innerRadius,
				2*innerRadius, 2*innerRadius);
		
 		
 		if (isSelected()) {
 			g.setColor(Color.BLUE);
 			g.drawRect(getCenter().getX() - 2, getCenter().getY() - 2, 4, 4);
 			g.drawRect(getCenter().getX() - innerRadius - 2, getCenter().getY() - 2, 4, 4);
 			g.drawRect(getCenter().getX() + innerRadius - 2, getCenter().getY() - 2, 4, 4);
 			g.drawRect(getCenter().getX() - 2, getCenter().getY() - innerRadius - 2, 4, 4);
 			g.drawRect(getCenter().getX() - 2, getCenter().getY() + innerRadius - 2, 4, 4);
 			g.setColor(Color.black);
 		}
 	}
 	

 	
 	@Override
 	public int compareTo(Object obj) {
 		if(obj instanceof Donut) {
 			Donut shapeToCompare = (Donut)obj;
 			return (int)(this.area() 
 					- shapeToCompare.area());
 		}
 		return 0;
 	}

	// ostale get i set nasledjuse iz Circle
	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadiusl) {
		this.innerRadius = innerRadiusl;
	}

	// redefinise toString iz Circle
	// da ne postoji u circle redefinisao bi onu toString
	// iz klase Object
	public String toString() {
		// ako kazemo toString() dobijamo rekurziju
		// zato treba reci super.toString
		return super.toString() + ", innerRadius = " + innerRadius;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	

}
