package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;


import javax.swing.JFrame;
import javax.swing.JPanel;

public class Drawing extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
 		JFrame frame = new JFrame("Iscrtavanje oblika");
 		frame.setSize(800, 600);
 		Drawing drawing = new Drawing();
 		frame.getContentPane().add(drawing);
 		frame.setVisible(true);
 	}
	
	public Drawing() {

 	}

	public void paint(Graphics g) {
 		Point point1 = new Point(50,60);
 		point1.draw(g);
 		Shape point2 = new Point(80,90);
 		g.setColor(Color.red);
 		point2.draw(g);
 		((Point)point2).setX(100);
 		point1.setY(100);
 		point2.draw(g);
 		point1.draw(g);
 		
 		Circle circle1 = new Circle((Point)point2,50);
 		circle1.draw(g);
 		//iscrtaj centar kruga zelenom bojom
 		g.setColor(Color.green);
 		circle1.getCenter().draw(g);
 		
 		//7. vežbe
 		circle1.moveTo(100, 200);
 		point2.moveTo(50, 60);
 		circle1.draw(g);
 		point2.draw(g);
 		
 		Point p3 = new Point(50, 50);
 		Rectangle r10 = new Rectangle(p3, 10, 10);
 		Rectangle r20 = new Rectangle(p3, 20, 20);
 		Rectangle r30 = new Rectangle(p3, 30, 30);
 		Rectangle[] rectangles = { r30, r10, r20 };
 		System.out.println("\nNesortiran niz: ");
 		for (int i = 0; i < rectangles.length; i++) {
 			System.out.println(rectangles[i]);
 		}
 		// u pozadini sortira po compareTo
 		Arrays.sort(rectangles);
 		System.out.println("Sortiran niz: ");
 		for (int i = 0; i < rectangles.length; i++) {
 			System.out.println(rectangles[i]);
 		}
 		
 		//Vezbe 8
 		//Zadatak 1.
 		Point p81 = new Point(50,60);
 		Rectangle r81 = new Rectangle(p81, 20, 10);
 		Circle c81 = new Circle(p81, 10);
 		ArrayList<Shape> shapes=new ArrayList<Shape>();
 		shapes.add(p81);
 		shapes.add(r81);
 		shapes.add(c81);
 		Iterator<Shape> itShape = shapes.iterator();
 		System.out.println("Elementi liste");
 		while(itShape.hasNext()) {
 			Shape tempShape = itShape.next();
 			System.out.println(tempShape);
 			tempShape.draw(g);
 		}

 		try {
 			c81.setRadius(-50);
 			Integer.parseInt("pet");
 		} catch (ArrayIndexOutOfBoundsException e) {
 			System.out.println(e.getMessage());
 		} catch (Exception e) {
 			System.out.println(e.getMessage());
 		} finally {
 			System.out.print("Provera izvrsavanja finally bloka");
 		}
 		System.out.print("Provera izvrsavanja");

 		//selekcija
 		Point p82 = new Point(150,160, true);
 		g.setColor(Color.black);
 		p82.draw(g);
 		r81.draw(g);
	}

}
