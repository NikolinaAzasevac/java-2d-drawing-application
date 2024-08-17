package geometry;

import java.awt.Graphics;
import java.awt.Color;

public class Rectangle extends Shape {

	private Point upperLeftPoint;
	private int width;
	private int height;
	private Color borderColor;
	private Color color;

	public Rectangle() {

	}

	public Rectangle(Point upperLeftpoint, int width, int height) {
		this.upperLeftPoint = upperLeftpoint;
		this.width = width;
		this.height = height;
	}

	public Rectangle(Point upperLeftpoint, int width, int height, boolean selected) {
		this(upperLeftpoint, width, height);
		this.selected = selected;
	}

	public Rectangle(Point upperLeftpoint, int width, int height, Color color, Color borderColor) {
		this(upperLeftpoint, width, height);
		this.color = (color != null) ? color : Color.BLACK;
		this.borderColor = (borderColor != null) ? borderColor : Color.BLACK;
		// this.color = color;
		// this.borderColor = borderColor;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
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
		return (x >= this.upperLeftPoint.getX() && x <= this.upperLeftPoint.getX() + width
				&& y >= this.upperLeftPoint.getY() && y <= this.upperLeftPoint.getY() + height);
	}

	// overloading nam sluzi kad imamo konstruktore da pravimo vise metoda koje se
	// isto zovu
	public boolean contains(Point sadrziTacku) {
		return (sadrziTacku.getX() >= this.upperLeftPoint.getX()
				&& sadrziTacku.getX() <= this.upperLeftPoint.getX() + width
				&& sadrziTacku.getY() >= this.upperLeftPoint.getY()
				&& sadrziTacku.getY() <= this.upperLeftPoint.getY() + height);
	}

	@Override
	public void draw(Graphics g) {

		g.setColor(borderColor);
		g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
		g.setColor(color);
		g.fillRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);

		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(upperLeftPoint.getX() - 2, upperLeftPoint.getY() - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() + width - 2, upperLeftPoint.getY() - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() - 2, upperLeftPoint.getY() + height - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() + width - 2, upperLeftPoint.getY() + height - 2, 4, 4);
			g.setColor(Color.black);
		}
	}

	public void moveTo(int x, int y) {
		upperLeftPoint.moveTo(x, y);
	}

	@Override
	public void moveBy(int x, int y) {
		upperLeftPoint.moveBy(x, y);
	}

	@Override
	public int compareTo(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle shapeToCompare = (Rectangle) obj;
			return this.area() - shapeToCompare.area();
		}
		return 0;
	}

	public int area() {
		return width * height;
	}

	public int circumference() {
		return 2 * width + 2 * height;
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
		return "Upper left point: " + upperLeftPoint + ", width = " + width + ", height = " + height;
	}

}