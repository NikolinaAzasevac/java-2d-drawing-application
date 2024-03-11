package geometry;

public class Line {
	
	private Point startPoint; // Point je slozeni deo podatka, to vec prethodno imam, umesto npr int double
	private Point endPoint;
	private boolean selected;
	//zbog enkapsulacije metode su public sve ostalo je private
	
	
	public double length() { //nista ne prosledjujemo jer u line ima i start i end point
		//reusable
		double length = startPoint.distance(endPoint);
		return length;
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

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
