package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
//import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import geometry.Line;
import geometry.Point;
import geometry.Shape;

public class PnlDrawing extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Shape> shapes = new ArrayList <Shape>();
	private Shape selectedShape; //referenca na selektovan oblikk
	private Point startPoint;

	/**
	 * Create the panel.
	 */
	public PnlDrawing() {
		setBackground(new Color(255, 255, 255));
		Border blackLine = BorderFactory.createLineBorder(Color.black);
        setBorder(blackLine);
	}
	
	public void draw(MouseEvent e, FrmDrawing frame) {
		
		Point click = new Point(e.getX(), e.getY());
		switch(frame.getChoice()) {
		case "point": {
		    DlgPoint dialog = frame.getDlgPoint();
		    dialog.writePoint(click);
		    dialog.getTxtX().setEnabled(false);
		    dialog.getTxtY().setEnabled(false);
			
		    dialog.setVisible(true);
		    if(dialog.isOk()) {
		    	Point point = dialog.drawPoint(); // Preuzmi liniju sa bojom iz dijaloga
                shapes.add(point);
                /*
			    Point point = dialog.drawPoint();
			    shapes.add(point);*/
			}	
		} repaint();break;
		
		case "line":{
			if (startPoint == null) {
				startPoint = click;
			} else {
				Point point1 = new Point(startPoint.getX(), startPoint.getY());
				Point point2 = new Point(e.getX(), e.getY());
				Line line = new Line(point1, point2);
				
				DlgLine dialog = frame.getDlgLine();
				dialog.getTxtX1().setEnabled(false);
				dialog.getTxtY1().setEnabled(false);
				dialog.getTxtX2().setEnabled(false);
				dialog.getTxtY2().setEnabled(false);
				
				dialog.writeLine(line);
				dialog.setVisible(true);
				if(dialog.isOk()) {
					line = dialog.drawLine(); // Preuzmi liniju sa bojom iz dijaloga
                    shapes.add(line);
                    /*
					line.setColor(dialog.getColor());
					System.out.println(line.getColor().toString());
					shapes.add(line);
					*/
				}
				startPoint = null;
			}

		}repaint(); break;
}
			
		//repaint();
}
	public void clearSelection() {
	    if (selectedShape != null) {
	        selectedShape.setSelected(false);
	        selectedShape = null;
	        repaint();
	    }
	}
	
	@Override
	public void paint(Graphics g) { 
	    super.paint(g);
	    Graphics2D g2d = (Graphics2D) g;
	    for (Shape shape : shapes) {
	        g2d.setColor(shape.getColor()); // Postavi boju za svaki oblik
	        shape.draw(g2d); //// pozivam draw metodu za svaki oblik
	    }
	}
	/*
	public void paint(Graphics g) { 
	    super.paint(g);
	    Graphics2D g2d = (Graphics2D) g;
	    for (Shape shape : shapes) {
	        if (shape instanceof Line) {
	            Line line = (Line) shape;
	            g2d.setColor(line.getColor());
	            line.draw(g2d);
	        } else {
	            shape.draw(g2d);
	        }
	    }
	}*/
	/*
	public void paint1(Graphics g1) { 
		super.paint(g1);
		Iterator<Shape> iterator = shapes.iterator();
		while (iterator.hasNext())
			iterator.next().draw(g1);	

	}
	*/
	/*
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Iterator<Shape> iterator = shapes.iterator();
		while (iterator.hasNext()) {
			Shape shape = iterator.next();
			shape.draw(g2d);
		}
	}
	*/

	public Shape getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}
	

}
