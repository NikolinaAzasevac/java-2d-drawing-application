package command;

import geometry.Donut;

public class UpdateDonutCmd implements Command {
	private final Donut donut;
	private final Donut newState;
	private Donut original;

	public UpdateDonutCmd(Donut donut, Donut newState) {
		this.donut = donut;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = donut.clone();
		donut.getCenter().setX(newState.getCenter().getX());
		donut.getCenter().setY(newState.getCenter().getY());
		donut.setRadius(newState.getRadius());
		donut.setInnerRadius(newState.getInnerRadius());
		donut.setColor(newState.getColor());
		donut.setBorderColor(newState.getBorderColor());
		donut.setSelected(newState.isSelected());
	}

	@Override
	public void unexecute() {
		donut.getCenter().setX(original.getCenter().getX());
		donut.getCenter().setY(original.getCenter().getY());
		donut.setRadius(original.getRadius());
		donut.setInnerRadius(original.getInnerRadius());
		donut.setColor(original.getColor());
		donut.setBorderColor(original.getBorderColor());
		donut.setSelected(original.isSelected());
	}

	@Override
	public String toString() {
		return "Update " + donut;
	}
}
