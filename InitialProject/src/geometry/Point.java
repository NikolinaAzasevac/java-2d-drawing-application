package geometry;

public class Point {
	// stanje/atributi klase
	private int x;
	private int y;
	private boolean selected;
	
	public double distance(Point drugaTacka) {
		int a = this.x-drugaTacka.getX(); // jer smo u klasi point mozemo da pristupimo i sa drugaTacka.x
		int b = this.y-drugaTacka.y;
		double distance = Math.sqrt(a*a+b*b);
		return distance;
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

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
