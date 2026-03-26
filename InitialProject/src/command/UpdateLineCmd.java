package command;

import geometry.Line;

public class UpdateLineCmd implements Command {
	private final Line line;
	private final Line newLine;
	private Line original;

	public UpdateLineCmd(Line line, Line newLine) {
		this.line = line;
		this.newLine = newLine;
	}

	@Override
	public void execute() {
		original = line.clone();
		line.getStartPoint().setX(newLine.getStartPoint().getX());
		line.getStartPoint().setY(newLine.getStartPoint().getY());
		line.getEndPoint().setX(newLine.getEndPoint().getX());
		line.getEndPoint().setY(newLine.getEndPoint().getY());
		line.setColor(newLine.getColor());
		line.setSelected(newLine.isSelected());
	}

	@Override
	public void unexecute() {
		line.getStartPoint().setX(original.getStartPoint().getX());
		line.getStartPoint().setY(original.getStartPoint().getY());
		line.getEndPoint().setX(original.getEndPoint().getX());
		line.getEndPoint().setY(original.getEndPoint().getY());
		line.setColor(original.getColor());
		line.setSelected(original.isSelected());
	}

	@Override
	public String toString() {
		return "Update " + line;
	}

}
