package geometry;

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

	// ostale get i set nasledjuse iz Circle
	public int getInnerRadiusl() {
		return innerRadius;
	}

	public void setInnerRadiusl(int innerRadiusl) {
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
