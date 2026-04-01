package command;

import geometry.Shape;

public class SelectShapeCmd implements Command {
	private final Shape shape;
	private boolean oldState;

	public SelectShapeCmd(Shape shape) {
		this.shape = shape;
	}

	@Override
	public void execute() {
		oldState = shape.isSelected();
		shape.setSelected(true);
	}

	@Override
	public void unexecute() {
		shape.setSelected(oldState);
	}

	@Override
	public String toString() {
		return "Select " + shape;
	}
}
