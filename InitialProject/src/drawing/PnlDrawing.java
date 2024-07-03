package drawing;

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
	private ArrayList<Shape> shapes = new ArrayList <Shape>();
	private Shape selectedShape; //referenca na selektovan oblikk

	/**
	 * Create the panel.
	 */
	//public PnlDrawing() {}
	
	 public PnlDrawing() {
	        addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                FrmDrawing frame = FrmDrawing.getFrame();
	                draw(e, frame);
	            }
	        });
	    }

	
	public void draw(MouseEvent e, FrmDrawing frame) {
		
		Point click = new Point(e.getX(), e.getY());
		DlgPoint dialog = frame.getDlgPoint();
		dialog.writePoint(click);
		dialog.getTxtX().setEnabled(false);
		dialog.getTxtY().setEnabled(false);
			
		dialog.setVisible(true);
		   if(dialog.isOk()) {
			Point point = dialog.drawPoint();
			shapes.add(point);
			}			
		repaint();
		}

	public void paint(Graphics g) { 
		super.paint(g);
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
