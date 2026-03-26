package geometry;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle {
	// nasledjuse center, radius i selected od Circle
	private int innerRadius;

	public Donut() {
	}

	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
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

	public Donut(Point center, int radius, int innerRadius, Color color, Color borderColor) {
		super(center, radius, color, borderColor);
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
		Graphics2D g2d = (Graphics2D) g;
		int x = getCenter().getX();
		int y = getCenter().getY();

		Ellipse2D outer = new Ellipse2D.Double(x - getRadius(), y - getRadius(), getRadius() * 2, getRadius() * 2);
		Ellipse2D inner = new Ellipse2D.Double(x - innerRadius, y - innerRadius, innerRadius * 2, innerRadius * 2);

		Area ring = new Area(outer);
		ring.subtract(new Area(inner));

		g2d.setColor(getColor());
		g2d.fill(ring);
		g2d.setColor(getBorderColor());
		g2d.draw(outer);
		g2d.draw(inner);

		if (isSelected()) {
			g2d.setColor(Color.BLUE);
			g2d.drawRect(x - 2, y - 2, 4, 4);
			g2d.drawRect(x - innerRadius - 2, y - 2, 4, 4);
			g2d.drawRect(x + innerRadius - 2, y - 2, 4, 4);
			g2d.drawRect(x - 2, y - innerRadius - 2, 4, 4);
			g2d.drawRect(x - 2, y + innerRadius - 2, 4, 4);
			g2d.setColor(Color.black);
		}
	}

	@Override
	public int compareTo(Object obj) {
		if (obj instanceof Donut) {
			Donut shapeToCompare = (Donut) obj;
			return (int) (this.area() - shapeToCompare.area());
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

	public Donut clone() {
		Donut donut = new Donut();
		donut.setCenter(this.getCenter().clone());
		donut.setRadius(this.getRadius());
		donut.setInnerRadius(this.getInnerRadius());
		donut.setColor(this.getColor());
		donut.setBorderColor(this.getBorderColor());
		donut.setSelected(this.isSelected());
		return donut;
	}

	// redefinise toString iz Circle
	// da ne postoji u circle redefinisao bi onu toString
	// iz klase Object
	public String toString() {
		return "Donut(x=" + getCenter().getX() + ", y=" + getCenter().getY() + ", r=" + getRadius() + ", rIn="
				+ innerRadius + ", fill=" + colorToHex(getColor()) + ", border=" + colorToHex(getBorderColor())
				+ ", selected=" + selected + ")";
	}

}
