package command;

import mvc.DrawingModel;
import geometry.Shape;

public class RemoveShapeCmd implements Command {
	private DrawingModel model;
	private Shape shape;
	private int index;
	
	public RemoveShapeCmd(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		index = model.getShapes().indexOf(shape);
		model.remove(shape);

	}

	@Override
	public void unexecute() {
		if (index >= 0 && index <= model.getShapes().size()) {
			model.getShapes().add(index, shape);
		} else {
			model.add(shape);
		}

	}

	@Override
	public String toString() {
		return "Delete " + shape;
	}

}
