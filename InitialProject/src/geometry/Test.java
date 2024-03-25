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
        //10
		Rectangle rect1 = new Rectangle();
		//rect1.getUpperLeftPoint().setX(10); //kod vraca null point ekspresn jer nije def upperleftpoint u mem lokaciji
		Point upperLeftPoint = new Point();
		rect1.setUpperLeftPoint(upperLeftPoint);
		rect1.getUpperLeftPoint().setX(10);
		
		
		//vezbe 4
		Point novaTacka = new Point(10, 15);
		System.out.println(novaTacka.getX());
		System.out.println(novaTacka.isSelected());
		Point novaTacka2=new Point(10,15,true);
		
		Line novaLinija = new Line(novaTacka, novaTacka2);
		System.out.println(novaLinija.getStartPoint().getX());
		
		//pre redefinisanja toString() metode izbacuje referencu
		//nakon redef vraca (10,15)
		System.out.println(novaTacka.toString());
		System.out.println(novaTacka);
		//u pozadini poziva toString iz klase Object ako nije def
		//ako jeste onda pozvima tu metodu
		System.out.println(novaTacka);
		//test za line
		System.out.println(line1);
		System.out.println(line1.toString());
		//test ZA RECTANGLE
		System.out.println(novaTacka==novaTacka2); //kada poredimo primitivne tipove podataka == poredi vrednosti, kod slozenih == poredi reference
		//vraca true jer je pocetna tacka linije ustv novaTacka
		System.out.println(novaTacka==novaLinija.getStartPoint());
		
		//metoda equals() poredi po vrednosti AKKO JE REDEFINISEMO U NPR U KLASI POINT  AKO NE VRACA PROVERU PO REFERENCI JER NE ZNA STA SU PROPERTY TACKE
		//equals prihvata parametar tipa object, ja mogu da JOJ PROSLEDIM PARAMETAR TIPA POINT ZATO STO POINT NASKLEDJUJE OBJECT
		
		System.out.println(novaTacka.equals(novaTacka2));
		System.out.println(novaTacka.equals(novaLinija));
		
		//pete vezbe
		novaTacka.contains(5,10);
		novaLinija.contains(5, 15);
		rect1.contains(novaTacka2);
		Donut donut1  = new Donut(novaTacka, 50, true); //ja kao korisnik znam da postoji tacka radius inner radius i selected a ne da li je on krug ili nije
		Circle donut2 = new Donut(novaTacka, 50, true); //ne buni se 
		Object donut3 = new Donut(novaTacka, 50, true); //sa leve strane mozemo bilo sta ali desno mora Donut 
		//Donut donut4 = newCircle() // ne moze jer Circle ne zna za Donat
		//Donut donut4 = (Donut)circle1; // ovo moze jer smo down castovali //ovo smo zakomentarisali iz nekog razlog
		//ne moze downcast da se uradi ipak ona je pogresila
		Donut donut5  = new Donut(novaTacka, 50, 45, true);
		System.out.println(donut5);
		
		
		
		
		
		

	}
}
