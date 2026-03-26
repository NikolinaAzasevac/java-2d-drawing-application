package command;

import geometry.Circle;

public class UpdateCircleCmd implements Command {
	private final Circle circle;
	private final Circle newState;
	private Circle original;

	public UpdateCircleCmd(Circle circle, Circle newState) {
		this.circle = circle;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = circle.clone();
		circle.getCenter().setX(newState.getCenter().getX());
		circle.getCenter().setY(newState.getCenter().getY());
		circle.setRadius(newState.getRadius());
		circle.setColor(newState.getColor());
		circle.setBorderColor(newState.getBorderColor());
		circle.setSelected(newState.isSelected());
	}

	@Override
	public void unexecute() {
		circle.getCenter().setX(original.getCenter().getX());
		circle.getCenter().setY(original.getCenter().getY());
		circle.setRadius(original.getRadius());
		circle.setColor(original.getColor());
		circle.setBorderColor(original.getBorderColor());
		circle.setSelected(original.isSelected());
	}

	@Override
	public String toString() {
		return "Update " + circle;
	}
}
