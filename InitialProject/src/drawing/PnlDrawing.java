package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import geometry.Circle;
import geometry.Donut;
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
					shapes.add(line);
				}
				startPoint = null;
			}
			repaint();
			break;

		}

		case "rectangle": {
			if (startPoint == null) {
				startPoint = click; // postavlja pocetnu tacku
			} else {
				// kreira pravougaonik
				DlgRectangle dialog = frame.getDlgRectangle();
				dialog.getTxtX().setText(String.valueOf(startPoint.getX())); // postavi x pocetku tacku
				dialog.getTxtY().setText(String.valueOf(startPoint.getY())); // postavi y
				dialog.getTxtWidth().setText(""); // prazno polje za sirinu
				dialog.getTxtHeight().setText(""); // za visinu
				dialog.getTxtX().setEnabled(false); // onemogui unos X
				dialog.getTxtY().setEnabled(false); // onemogući unos Y
				dialog.setVisible(true);

				if (dialog.isConfirm()) {
					// kada je korisnik potvrdio unos
					Rectangle rectangle = dialog.makeRectangle(); // kreira pravougaonik iz dijaloga
					shapes.add(rectangle); // doda pravougaonik u listu
				}
				startPoint = null; // resetuje pocetnu tacku
			}
			repaint(); // osvezi panel
			break;
		}

		case "circle": {
			DlgCircle dialog = frame.getDlgCircle();
			dialog.getTxtX().setText(String.valueOf(click.getX()));
			dialog.getTxtY().setText(String.valueOf(click.getY()));
			dialog.getTxtRadius().setText(String.valueOf(""));
			dialog.getTxtX().setEnabled(false);
			dialog.getTxtY().setEnabled(false);
			dialog.setVisible(true);
			if (dialog.isConfirm()) {
				Circle circle = dialog.makeCircle();
				shapes.add(circle);
			}

			repaint();
			break;
		}

		case "donut": {
			DlgDonut dialog = frame.getDlgDonut();
			dialog.getTxtX().setText(String.valueOf(click.getX()));
			dialog.getTxtY().setText(String.valueOf(click.getY()));
			dialog.getTxtOuterRadius().setText(String.valueOf("")); // da ne ostaje od proslog crtanja
			dialog.getTxtInnerRadius().setText(String.valueOf(""));
			dialog.getTxtX().setEnabled(false);
			dialog.getTxtY().setEnabled(false);
			dialog.setVisible(true);
			if (dialog.isConfirm()) {
				Donut donut = dialog.makeDonut();
				shapes.add(donut);
			}
			repaint();
			break;
		}

		case "select": {

			ListIterator<Shape> itShape = shapes.listIterator(shapes.size()); // kreiramo list iterator koji prolazi
																				// unazad kroz listu
			boolean shapeSelected = false; // promenljiva koja prati da li je neki oblik selektovan
			while (itShape.hasPrevious()) {
				Shape temporary = itShape.previous(); // uzima sledeci (zapravo prethodni) oblik iz liste
				if (temporary.contains(e.getX(), e.getY()) && !shapeSelected) {
					System.out.println(temporary.toString());
					temporary.setSelected(true); // selektujemo oblik
					selectedShape = temporary; // trenutno selektovani oblik je selected shape
					shapeSelected = true; // postavljamo da je oblik selektovan
				} else if (temporary.isSelected()) {
					temporary.setSelected(false); // deselektujemo sve ostale oblike
				}
			}
			repaint();
			break;
		}

		case "delete": {
			if (selectedShape == null)
				JOptionPane.showMessageDialog(null, "You have to select shape.", "Error Message",
						JOptionPane.INFORMATION_MESSAGE);
		}
			break;

		default:
			JOptionPane.showMessageDialog(null, "Choose one of the options", "Error Message",
					JOptionPane.INFORMATION_MESSAGE);

		}

		repaint();

	}

	public void modify(FrmDrawing frame) {
		// proverava da li je selektovani oblik instanca klase point
		if (selectedShape instanceof Point) {

			// kreira dijalog za unos podataka o tacki
			DlgPoint dialog = frame.getDlgPoint();
			// postavlja trenutne vrednosti oblika u dlg
			dialog.writePoint((Point) selectedShape);
			// omogucava korisniku da menja koordinate
			dialog.getTxtX().setEnabled(true);
			dialog.getTxtY().setEnabled(true);
			// prikazuje dijalog korisniku
			dialog.setVisible(true);

			// proverava da li su promene potvrdjene
			if (dialog.isConfirm()) {
				// azurira koordinate i boju oblika sa novim vrednostima iz dlg
				((Point) selectedShape).setX(dialog.makePoint().getX());
				((Point) selectedShape).setY(dialog.makePoint().getY());
				((Point) selectedShape).setColor(dialog.makePoint().getColor());
			}
			// deselectuje oblik i postavlja selectedShape na null
			selectedShape.setSelected(false);
			selectedShape = null;

		} else if ((selectedShape instanceof Line)) {
			DlgLine dialog = frame.getDlgLine();
			dialog.writeLine((Line) selectedShape);
			dialog.getTxtX1().setEnabled(true);
			dialog.getTxtY1().setEnabled(true);
			dialog.getTxtX2().setEnabled(true);
			dialog.getTxtY2().setEnabled(true);
			dialog.setVisible(true);
			if (dialog.isConfirm()) {
				((Line) selectedShape).getStartPoint().setX(dialog.makeLine().getStartPoint().getX());
				((Line) selectedShape).getStartPoint().setY(dialog.makeLine().getStartPoint().getY());
				((Line) selectedShape).getEndPoint().setX(dialog.makeLine().getEndPoint().getX());
				((Line) selectedShape).getEndPoint().setY(dialog.makeLine().getEndPoint().getY());
				((Line) selectedShape).setColor(dialog.getColor());
			}
			selectedShape.setSelected(false);
			selectedShape = null;

		} else if (selectedShape instanceof Rectangle) {
			DlgRectangle dialog = frame.getDlgRectangle();
			dialog.writeRectangle((Rectangle) selectedShape);
			dialog.getTxtX().setEnabled(true);
			dialog.getTxtY().setEnabled(true);
			dialog.setVisible(true);
			if (dialog.isConfirm()) {
				((Rectangle) selectedShape).getUpperLeftPoint().setX(dialog.makeRectangle().getUpperLeftPoint().getX());
				((Rectangle) selectedShape).getUpperLeftPoint().setY(dialog.makeRectangle().getUpperLeftPoint().getY());
				((Rectangle) selectedShape).setHeight(dialog.makeRectangle().getHeight());
				((Rectangle) selectedShape).setWidth(dialog.makeRectangle().getWidth());
				((Rectangle) selectedShape).setColor(dialog.makeRectangle().getColor());
				((Rectangle) selectedShape).setBorderColor(dialog.makeRectangle().getBorderColor());
			}
			selectedShape.setSelected(false);
			selectedShape = null;

		} else if (selectedShape instanceof Circle && !(selectedShape instanceof Donut)) { // dodato da je razlicito od
																							// instance donuta jer mi je
																							// prilikom modifikacije
																							// donuta izbacivao circle
			DlgCircle dialog = frame.getDlgCircle();
			dialog.writeCircle((Circle) selectedShape);
			dialog.getTxtX().setEnabled(true);
			dialog.getTxtY().setEnabled(true);
			dialog.setVisible(true);
			if (dialog.isConfirm()) {
				((Circle) selectedShape).getCenter().setX(dialog.makeCircle().getCenter().getX());
				((Circle) selectedShape).getCenter().setY(dialog.makeCircle().getCenter().getY());
				((Circle) selectedShape).setRadius(dialog.makeCircle().getRadius());
				((Circle) selectedShape).setColor(dialog.makeCircle().getColor());
				((Circle) selectedShape).setBorderColor(dialog.makeCircle().getBorderColor());
			}
			selectedShape.setSelected(false);
			selectedShape = null;

		} else if (selectedShape instanceof Donut) {
			DlgDonut dialog = frame.getDlgDonut();
			dialog.writeDonut((Donut) selectedShape);
			dialog.getTxtX().setEnabled(true);
			dialog.getTxtY().setEnabled(true);
			dialog.setVisible(true);
			if (dialog.isConfirm()) {
				((Donut) selectedShape).getCenter().setX(dialog.makeDonut().getCenter().getX());
				((Donut) selectedShape).getCenter().setY(dialog.makeDonut().getCenter().getY());
				((Donut) selectedShape).setInnerRadius(dialog.makeDonut().getInnerRadius());
				((Donut) selectedShape).setRadius(dialog.makeDonut().getRadius());
				((Donut) selectedShape).setColor(dialog.makeDonut().getColor());
				((Donut) selectedShape).setInnerColor(dialog.makeDonut().getInnerColor());
				((Donut) selectedShape).setBorderColor(dialog.makeDonut().getBorderColor());
			}
			selectedShape.setSelected(false);
			selectedShape = null;

		}

		repaint();
	}

	public void delete() { // metoda za uklanjanje
		shapes.remove(selectedShape);
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g); // Poziva paint metode nadklase
		Iterator<Shape> iterator = shapes.iterator();
		while (iterator.hasNext())
			iterator.next().draw(g);

	}

	public Shape getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}

}