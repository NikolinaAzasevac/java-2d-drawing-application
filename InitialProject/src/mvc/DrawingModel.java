package mvc;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import geometry.Point;
import geometry.Shape;
import observer.Observable;
import observer.Observer;

public class DrawingModel implements Observable {
	private final List<Shape> shapes = new ArrayList<>();
	private final List<String> commandLog = new ArrayList<>();
	private final List<Observer> observers = new ArrayList<>();
	private Shape selectedShape;
	private Point startPoint;
	private Color activeBorderColor = Color.BLACK;
	private Color activeFillColor = Color.WHITE;

	public void add(Shape s) {
		shapes.add(s);
	}

	public void remove(Shape s) {
		shapes.remove(s);
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	public void addLog(String entry) {
		commandLog.add(entry);
	}

	public List<String> getLogEntries() {
		return commandLog;
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

	public Color getActiveBorderColor() {
		return activeBorderColor;
	}

	public void setActiveBorderColor(Color activeBorderColor) {
		this.activeBorderColor = activeBorderColor;
	}

	public Color getActiveFillColor() {
		return activeFillColor;
	}

	public void setActiveFillColor(Color activeFillColor) {
		this.activeFillColor = activeFillColor;
	}

	public int getSelectedCount() {
		int count = 0;
		for (Shape shape : shapes) {
			if (shape.isSelected()) {
				count++;
			}
		}
		return count;
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		int selectedCount = getSelectedCount();
		for (Observer observer : observers) {
			observer.update(selectedCount);
		}
	}
}
