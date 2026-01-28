package command;

import mvc.DrawingModel;
import geometry.Point;

public class RemovePointCmd implements Command {
	private DrawingModel model;
	private Point point;

	public RemovePointCmd(DrawingModel model, Point point) {
		this.model = model;
		this.point = point;
	}

	@Override
	public void execute() {
		model.remove(point);

	}

	@Override
	public void unexecute() {
		model.add(point);

	}

}
