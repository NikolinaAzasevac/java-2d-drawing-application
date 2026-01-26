package mvc;

import java.util.ArrayList;
import java.util.List;

import geometry.Point;
import geometry.Shape;

public class DrawingModel {
	private final List<Shape> shapes = new ArrayList<>();
	private Shape selectedShape;
	private Point startPoint;

	public void add(Shape s) {
		shapes.add(s);
	}

	public void remove(Shape s) {
		shapes.remove(s);
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	public Shape getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
}
