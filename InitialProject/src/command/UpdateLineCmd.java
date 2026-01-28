package command;

import geometry.Line;

public class UpdateLineCmd implements Command {
	private Line line;
	private Line newLine;
	private Line original = new Line();

	public UpdateLineCmd(Line line, Line newLine) {
		this.line = line;
		this.newLine = newLine;
	}

	@Override
	public void execute() {
		original.getStartPoint().setX(line.getStartPoint().getX());
		original.getStartPoint().setY(line.getStartPoint().getY());
		original.getEndPoint().setX(line.getEndPoint().getX());
		original.getEndPoint().setY(line.getEndPoint().getY());
		original.setColor(line.getColor());
		
		line.getStartPoint().setX(newLine.getStartPoint().getX());
		line.getStartPoint().setY(newLine.getStartPoint().getY());
		line.getEndPoint().setX(newLine.getEndPoint().getX());
		line.getEndPoint().setY(newLine.getEndPoint().getY());
		line.setColor(newLine.getColor());

	}

	@Override
	public void unexecute() {
		line.getStartPoint().setX(original.getStartPoint().getX());
		line.getStartPoint().setY(original.getStartPoint().getY());
		line.getEndPoint().setX(original.getEndPoint().getX());
		line.getEndPoint().setY(original.getEndPoint().getY());
		line.setColor(original.getColor());

	}

}
