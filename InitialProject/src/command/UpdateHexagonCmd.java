package command;

import adapter.HexagonAdapter;

public class UpdateHexagonCmd implements Command {
	private final HexagonAdapter hexagon;
	private final HexagonAdapter newState;
	private HexagonAdapter original;

	public UpdateHexagonCmd(HexagonAdapter hexagon, HexagonAdapter newState) {
		this.hexagon = hexagon;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = hexagon.clone();
		hexagon.setX(newState.getX());
		hexagon.setY(newState.getY());
		hexagon.setR(newState.getR());
		hexagon.setAreaColor(newState.getAreaColor());
		hexagon.setBorderColor(newState.getBorderColor());
		hexagon.setSelected(newState.isSelected());
	}

	@Override
	public void unexecute() {
		hexagon.setX(original.getX());
		hexagon.setY(original.getY());
		hexagon.setR(original.getR());
		hexagon.setAreaColor(original.getAreaColor());
		hexagon.setBorderColor(original.getBorderColor());
		hexagon.setSelected(original.isSelected());
	}

	@Override
	public String toString() {
		return "Update " + hexagon;
	}
}
