package command;

import geometry.Rectangle;

public class UpdateRectangleCmd implements Command {
	private final Rectangle rectangle;
	private final Rectangle newState;
	private Rectangle original;

	public UpdateRectangleCmd(Rectangle rectangle, Rectangle newState) {
		this.rectangle = rectangle;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = rectangle.clone();
		rectangle.getUpperLeftPoint().setX(newState.getUpperLeftPoint().getX());
		rectangle.getUpperLeftPoint().setY(newState.getUpperLeftPoint().getY());
		rectangle.setWidth(newState.getWidth());
		rectangle.setHeight(newState.getHeight());
		rectangle.setColor(newState.getColor());
		rectangle.setBorderColor(newState.getBorderColor());
		rectangle.setSelected(newState.isSelected());
	}

	@Override
	public void unexecute() {
		rectangle.getUpperLeftPoint().setX(original.getUpperLeftPoint().getX());
		rectangle.getUpperLeftPoint().setY(original.getUpperLeftPoint().getY());
		rectangle.setWidth(original.getWidth());
		rectangle.setHeight(original.getHeight());
		rectangle.setColor(original.getColor());
		rectangle.setBorderColor(original.getBorderColor());
		rectangle.setSelected(original.isSelected());
	}

	@Override
	public String toString() {
		return "Update " + rectangle;
	}
}
