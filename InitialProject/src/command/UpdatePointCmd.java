package command;

import geometry.Point;

public class UpdatePointCmd implements Command {
	private final Point point;
	private final Point newState;
	private Point original;

	public UpdatePointCmd(Point point, Point newState) {
		this.point = point;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = point.clone();
		point.setX(newState.getX());
		point.setY(newState.getY());
		point.setColor(newState.getColor());
		point.setSelected(newState.isSelected());
	}

	@Override
	public void unexecute() {
		point.setX(original.getX());
		point.setY(original.getY());
		point.setColor(original.getColor());
		point.setSelected(original.isSelected());
	}

	@Override
	public String toString() {
		return "Update " + point;
	}

}
