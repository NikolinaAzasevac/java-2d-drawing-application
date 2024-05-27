package geometry;

import java.awt.Graphics;

public class Donut extends Circle {
	// nasledjuse center, radius i selected od Circle
	private int innerRadius;

	public Donut() {
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
 		g.drawOval(getCenter().getX()-innerRadius,
 				getCenter().getY()-innerRadius, 2*innerRadius,
 				2*innerRadius);
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

}
