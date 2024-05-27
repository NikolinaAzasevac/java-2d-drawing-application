package geometry;

import java.awt.Graphics;
import java.awt.Color;

public class Line extends Shape {
	
	private Point startPoint; // Point je slozeni deo podatka, to vec prethodno imam, umesto npr int double
	private Point endPoint;
	//zbog enkapsulacije metode su public sve ostalo je private
	
	public Line() {}
	
	public Line (Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		this.selected=selected;

	}
	public double length() { //nista ne prosledjujemo jer u line ima i start i end point
		//reusable
		double length = startPoint.distance(endPoint);
		return length;
	}
	
	public boolean equals(Object obj) { //primitvne poredimo sa == , a obj i jos nes sa equals
		if (obj instanceof Line) {
			Line pomocna = (Line)obj;
			if(this.startPoint
					.equals(pomocna.startPoint)
					&& this.endPoint
					.equals(pomocna.endPoint)) //cast obj //u nekoj od ovih boolean metoda mi fali nesto sa this i ==
				return true;
			else
				return false;
		} else
			return false;  //ctrl shift f da formatira kod 
	}
	
	public boolean contains(int x, int y) {
		Point sadrziTacku = new Point(x,y);
		return this.startPoint.distance(sadrziTacku) + this.endPoint.distance(sadrziTacku) - length() <= 2;
	}
	
	@Override
 	public void draw(Graphics g) {
 		g.drawLine(startPoint.getX(), startPoint.getY(),
 				endPoint.getX(), endPoint.getY());	
 		
 		if(isSelected()) {
 			g.setColor(Color.BLUE);
 			g.drawRect(startPoint.getX()-2, startPoint.getY()-2, 4, 4);
 			g.drawRect(endPoint.getX()-2, endPoint.getY()-2, 4, 4);
 			g.setColor(Color.black);}
 	}
	
	public void moveTo(int x, int y) {
 		//ako bismo je implementirali 
 		//od linije bismo dobili tacku
 	}

 	@Override
 	public void moveBy(int x, int y) {
 		startPoint.moveBy(x, y);
 		endPoint.moveBy(x, y);
 	}

 	@Override
 	public int compareTo(Object obj) {
 		if(obj instanceof Line) {
 			Line shapeToCompare = (Line)obj;
 			return (int)(this.length() 
 					- shapeToCompare.length());
 		}
 		return 0;
 	}

	
	public Point getStartPoint() {
		return this.startPoint; // this u get metodi moze a ne mora da stoji
	}
	
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint; //ovde je this neophodno
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public String toString() {
		return startPoint.toString()+"-->"+endPoint+")"; // (xS, yS) --> (xE, yE)
	}
}

