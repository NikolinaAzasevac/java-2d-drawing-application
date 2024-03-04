package geometry;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//kreiramo konkretnu tacku, tj objekat klase Point
		Point prvaTackaTest=new Point ();
		System.out.println(prvaTackaTest.getX());
		Point drugaTacka = new Point();
		System.out.println(drugaTacka.getX());
		prvaTackaTest.setX(80);
		System.out.println(prvaTackaTest.getX());
		int y = 150;
		drugaTacka.setY(y);
		System.out.println(drugaTacka.getY());
		
		

	}

}
