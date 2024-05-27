package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

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
	}

}
