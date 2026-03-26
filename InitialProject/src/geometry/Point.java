package geometry;

import java.awt.Graphics;
import java.awt.Color;

public class Point extends Shape {
	// stanje/atributi klase
	private int x;
	private int y;
	private boolean selected;
	private Color color;

	public Point() {

	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(int x, int y, boolean selected) {
		this(x, y);
		this.selected = selected;
	}

	public Point(int x, int y, Color color) {
		this(x, y);
		setColor(color);
	}

	public double distance(Point drugaTacka) {
		int a = this.x - drugaTacka.getX(); // jer smo u klasi point mozemo da pristupimo i sa drugaTacka.x
		int b = this.y - drugaTacka.y;
		double distance = Math.sqrt(a * a + b * b);
		return distance;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			if (this.x == ((Point) obj).x && this.y == ((Point) obj).y && this.selected == ((Point) obj).selected) // cast
																													// obj
				return true;
			return false;
		}
		return false;
	}

	public boolean contains(int x, int y) {
		Point sadrziTacku = new Point(x, y);
		return this.distance(sadrziTacku) <= 2;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(x - 2, y, x + 2, y);
		g.drawLine(x, y - 2, x, y + 2);
		if (this.selected == true) {
			g.setColor(Color.BLUE);
			g.drawRect(x - 2, y - 2, 4, 4);
		}
		g.setColor(Color.black);

	}

	@Override
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void moveBy(int x, int y) {
		this.x += x;
		this.y += y;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Point) {
			Point shapeToCompare = (Point) o;
			return (int) this.distance(new Point(0, 0)) - (int) shapeToCompare.distance(new Point(0, 0));
		}
		return 0;
	}

	// metode pristupa - public
	// metoda istance
	public int getX() {
		// return x;
		return this.x;
	}
	// prvi nacin - onaj koji nije ispravavan
	/*
	 * public void setX(int xKoordinate) { x=xKoordinate; }
	 */

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Point clone() {
		Point point = new Point();
		point.setX(this.getX());
		point.setY(this.getY());
		point.setColor(this.getColor());
		point.setSelected(this.isSelected());
		return point;
	}

	// isti potpis kao i u klasi oBject
	// nakon redefinisanja vraca (10,15).
	public String toString() {
		return "Point(x=" + this.x + ", y=" + this.y + ", color=" + colorToHex(this.color) + ", selected="
				+ this.selected + ")";
	}
}
