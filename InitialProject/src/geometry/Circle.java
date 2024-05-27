package geometry;

public class Circle {

	private Point center;
	private int radius; 
	private boolean selected;
	
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
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
