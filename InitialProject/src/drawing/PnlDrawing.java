package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import geometry.Point;
import geometry.Shape;

public class PnlDrawing extends JPanel {

	private static final long serialVersionUID = 1L;
	//private FrmDrawing frame;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private Point startPoint;
	private Shape selectedShape;

	/**
	 * Create the panel.
	 */
	//public PnlDrawing() {}
	
	
	public PnlDrawing() {
		/*
		setBackground(Color.WHITE);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				handleMouseClick(e);
			}
		});*/
	}
/*
	public PnlDrawing(FrmDrawing frame) { // konstruktor koji prima instancu FrmDrawing kao parametar da PnlDrawing zna
											// u kojem prozoru se nalazi
		this();
		this.frame = frame; // dodeljuje prosledjenu instancu FrmDrawing varijabli frame da panel komunicira
							// sa glavnim prozorom
	};*/
/*
	private void handleMouseClick(MouseEvent e) {
		drawing(e, frame);
	}*/

	public void drawing(MouseEvent e, FrmDrawing frame) {
		Point click = new Point(e.getX(), e.getY());
		switch (frame.getChoice()) {
		case "Point": {
			/*
			 * DlgPoint dialog = frame.getDlgPoint(); dialog.writePoint(click);
			 * dialog.getTxtX().setEnabled(false); dialog.getTxtY().setEnabled(false);
			 * dialog.setVisible(true); if (dialog.isConfirm()) { Point point =
			 * dialog.makePoint(); shapes.add(point); } repaint();
			 */
			DlgPoint dialog = frame.getDlgPoint();
			dialog.writePoint(click); // prosledi kliknu tačku u dijalog
			dialog.getTxtX().setEnabled(false);
			dialog.getTxtY().setEnabled(false);
			
			dialog.setVisible(true);
			if (dialog.isConfirm()) {
				Point point = dialog.makePoint();
				shapes.add(point);
				//repaint();
			}
			repaint();
			break;
		}

		}
	}
/*
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> iterator = shapes.iterator();
		while (iterator.hasNext())
			iterator.next().draw(g);
	}*/
	
	 @Override
	    public void paint(Graphics g) {
	        super.paint(g); // Poziva paint metode nadklase
	        Iterator<Shape> iterator = shapes.iterator();
	        while (iterator.hasNext()) {
	            Shape shape = iterator.next();
	            shape.draw(g);
	        }
	    }

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
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