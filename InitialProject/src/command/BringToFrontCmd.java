package command;

import java.util.List;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToFrontCmd implements Command {
	private final DrawingModel model;
	private final Shape shape;
	private int originalIndex;
	private int newIndex;

	public BringToFrontCmd(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		List<Shape> shapes = model.getShapes();
		originalIndex = shapes.indexOf(shape);
		if (originalIndex < 0 || originalIndex == shapes.size() - 1) {
			newIndex = originalIndex;
			return;
		}

		newIndex = shapes.size() - 1;
		shapes.remove(originalIndex);
		shapes.add(shape);
	}

	@Override
	public void unexecute() {
		if (originalIndex < 0 || newIndex == originalIndex) {
			return;
		}

		List<Shape> shapes = model.getShapes();
		shapes.remove(shape);
		shapes.add(originalIndex, shape);
	}

	@Override
	public String toString() {
		return "Bring to front " + shape;
	}
}
