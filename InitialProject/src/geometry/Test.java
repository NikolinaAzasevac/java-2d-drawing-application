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
		
		double distance = prvaTackaTest.distance(drugaTacka);
		System.out.println(distance);
		
		//Zadacici
		//1.
		Point point1 = new Point();	
		Point point2 = new Point();
		Line line1=new Line();
		//2.
		point1.setX(20);
		point1.setY(40);
		point2.setX(60);
		point2.setY(30);
		//3.
		point1.setX(point2.getY());
		System.out.println(point1.getX());
		//4.
		//System.out.println(line1.getStartPoint().getX()); prethodna llinija vraca null, jer startpoint nije inicijalizovan
		//5.
		line1.setStartPoint(point1);
		line1.setEndPoint(point2);
		
		System.out.println(line1.getStartPoint().getX());
		System.out.println(line1.getStartPoint().getY());
		
		System.out.println(line1.getEndPoint().getX());
		System.out.println(line1.getEndPoint().getY());
		//6.
		line1.getEndPoint().setY(23);//kada menjamo koordinatu jedne tacke menjamo ceo memorijski prostor
		System.out.println(line1.getEndPoint().getY());
		//prenos po referenci!!
		System.out.println(point2.getY());
		
		//7.
		line1.getStartPoint().setX(line1.getEndPoint().getY());
		//8.
		System.out.println(point1.getX());
		System.out.println(point2.getY());
		//9.
		line1.getEndPoint().setX((int)(line1.length() - 
		line1.getStartPoint().getX()
		+line1.getStartPoint().getY()));		
	}
}
