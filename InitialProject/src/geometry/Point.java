package geometry;

import java.awt.Graphics;

public class Point extends Shape {
	// stanje/atributi klase
	private int x;
	private int y;
	private boolean selected;
	
	public Point() {
		
	}
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(int x, int y, boolean selected) {
		this(x,y);
		this.selected=selected;
	}
	
	public double distance(Point drugaTacka) {
		int a = this.x-drugaTacka.getX(); // jer smo u klasi point mozemo da pristupimo i sa drugaTacka.x
		int b = this.y-drugaTacka.y;
		double distance = Math.sqrt(a*a+b*b);
		return distance;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			if(this.x==((Point)obj).x && this.y == ((Point)obj).y && this.selected == ((Point)obj).selected) //cast obj
				return true;
			return false;
		}
		return false;
	}
	
	public boolean contains(int x, int y) {
		Point sadrziTacku = new Point(x,y);
		return this.distance(sadrziTacku) <= 2;
	}
	
	@Override
 	public void draw(Graphics g) {
 		g.drawLine(x-2, y, x+2, y);
 		g.drawLine(x, y-2, x, y+2);
 	}
	
	//metode pristupa - public
	//metoda istance
	public int getX() {
		//return x;
		return this.x;
	}
	//prvi nacin - onaj koji nije ispravavan
	/*
	public void setX(int xKoordinate) {
		x=xKoordinate;
	}
	*/
	
	public void setX(int x) {
		this.x=x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	//isti potpis kao i u klasi oBject
	//nakon redefinisanja vraca (10,15).
	public String toString() {
		return "("+this.x+","+this.y+")";
	}
}
