package command;

import geometry.Shape;

public class DeselectShapeCmd implements Command {
	private final Shape shape;
	private boolean oldState;

	public DeselectShapeCmd(Shape shape) {
		this.shape = shape;
	}

	@Override
	public void execute() {
		oldState = shape.isSelected();
		shape.setSelected(false);
	}

	@Override
	public void unexecute() {
		shape.setSelected(oldState);
	}

	@Override
	public String toString() {
		return "Deselect " + shape;
	}
}
