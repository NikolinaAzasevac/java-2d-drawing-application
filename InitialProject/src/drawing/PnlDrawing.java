package drawing;

import java.awt.Color;
import java.awt.Graphics;
//import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import geometry.Circle;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class PnlDrawing extends JPanel {

	private static final long serialVersionUID = 1L;
	// private FrmDrawing frame;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private Point startPoint;
	private Shape selectedShape;

	/**
	 * Create the panel.
	 */
	// public PnlDrawing() {}

	public PnlDrawing() {
		setBackground(new Color(255, 255, 255));
		Border blackLine = BorderFactory.createLineBorder(Color.black);
		setBorder(blackLine);
	}

	public void drawing(MouseEvent e, FrmDrawing frame) {
		Point click = new Point(e.getX(), e.getY());
		switch (frame.getChoice()) {
		case "point": {
			DlgPoint dialog = frame.getDlgPoint();
			dialog.writePoint(click); // prosledi kliknu tačku u dijalog
			dialog.getTxtX().setEnabled(false);
			dialog.getTxtY().setEnabled(false);

			dialog.setVisible(true);
			if (dialog.isConfirm()) {
				Point point = dialog.makePoint();
				shapes.add(point);
				// repaint();
			}
			repaint();
			break;
		}

		case "line": {
			if (startPoint == null) {
				startPoint = click;
			} else {
				Point p1 = new Point(startPoint.getX(), startPoint.getY());
				Point p2 = new Point(e.getX(), e.getY());
				Line line = new Line(p1, p2);
				DlgLine dialog = frame.getDlgLine();
				dialog.getTxtX1().setEnabled(false);
				dialog.getTxtY1().setEnabled(false);
				dialog.getTxtX2().setEnabled(false);
				dialog.getTxtY2().setEnabled(false);
				dialog.writeLine(line);
				dialog.setVisible(true);
				if (dialog.isConfirm()) {
					line.setColor(dialog.getColor());
					System.out.println(line.getColor().toString());
					shapes.add(line);
				}
				startPoint = null;
			}
			repaint();
			break;

		}
		
		case "rectangle": {
		    if (startPoint == null) {
		        startPoint = click;  // postavlja pocetnu tacku
		    } else {
		        // kreira pravougaonik
		        DlgRectangle dialog = frame.getDlgRectangle();
		        dialog.getTxtX().setText(String.valueOf(startPoint.getX())); // postavi x pocetku tacku
		        dialog.getTxtY().setText(String.valueOf(startPoint.getY())); // postavi y
		        dialog.getTxtWidth().setText("");  // prazno polje za sirinu
		        dialog.getTxtHeight().setText(""); //  za visinu
		        dialog.getTxtX().setEnabled(false); // onemogui unos X
		        dialog.getTxtY().setEnabled(false); // onemogući unos Y
		        dialog.setVisible(true);  
		        
		        if (dialog.isConfirm()) {
		            // kada je korisnik potvrdio unos
		            Rectangle rectangle = dialog.makeRectangle();  //kreira pravougaonik iz dijaloga
		            shapes.add(rectangle);  // doda pravougaonik u listu
		        }
		        startPoint = null;  // resetuje pocetnu tacku
		    }
		    repaint();  // osvezi panel
		    break;
		}


		case "circle": {
			DlgCircle dlgCircle = frame.getDlgCircle();
			dlgCircle.getTxtX().setText(String.valueOf(click.getX()));
			dlgCircle.getTxtY().setText(String.valueOf(click.getY()));
			dlgCircle.getTxtRadius().setText(String.valueOf(""));
			dlgCircle.getTxtX().setEnabled(false);
			dlgCircle.getTxtY().setEnabled(false);
			dlgCircle.setVisible(true);
			if (dlgCircle.isConfirm()) {
				Circle circle = dlgCircle.makeCircle();
				shapes.add(circle);
			}
		}
			repaint();
			break;
		}

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g); // Poziva paint metode nadklase
		Iterator<Shape> iterator = shapes.iterator();
		while (iterator.hasNext())
			iterator.next().draw(g);

	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Shape getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}

}