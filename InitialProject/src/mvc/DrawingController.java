package mvc;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import adapter.HexagonAdapter;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import command.Command;
import command.RemoveShapeCmd;
import command.AddShapeCmd;
import command.UpdateCircleCmd;
import command.UpdateDonutCmd;
import command.UpdateHexagonCmd;
import command.UpdateLineCmd;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
import command.BringToBackCmd;
import command.BringToFrontCmd;
import command.DeselectShapeCmd;
import command.SelectShapeCmd;
import command.ToFrontCmd;
import command.ToBackCmd;
import strategy.FileManager;
import strategy.DrawingFileStrategy;
import strategy.LogFileStrategy;

public class DrawingController {

	private final DrawingModel model;
	private final DrawingFrame frame;
	private final Stack<Command> undoStack = new Stack<>();
	private final Stack<Command> redoStack = new Stack<>();
	private final LogFileStrategy logParser = new LogFileStrategy();
	private java.awt.Point replayDialogLocation;

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}

	public void mouseClicked(MouseEvent e) {
		Point click = new Point(e.getX(), e.getY());

		switch (frame.getChoice()) {

		case "point": {
			DlgPoint dialog = frame.getDlgPoint();
			dialog.setColor(model.getActiveBorderColor());
			dialog.writePoint(click);
			dialog.getTxtX().setEnabled(false);
			dialog.getTxtY().setEnabled(false);

			dialog.setVisible(true);
			if (dialog.isConfirm()) {
				Point point = dialog.makePoint();
				Command cmd = new AddShapeCmd(model, point);
				executeCommand(cmd);
			}
			frame.repaint();
			break;
		}

		case "line": {
			if (model.getStartPoint() == null) {
				model.setStartPoint(click);
			} else {
				Point p1 = new Point(model.getStartPoint().getX(), model.getStartPoint().getY());
				Point p2 = new Point(e.getX(), e.getY());
				Line line = new Line(p1, p2);

				DlgLine dialog = frame.getDlgLine();
				dialog.setColor(model.getActiveBorderColor());
				dialog.writeLine(line);
				dialog.getTxtX1().setEnabled(false);
				dialog.getTxtY1().setEnabled(false);
				dialog.getTxtX2().setEnabled(false);
				dialog.getTxtY2().setEnabled(false);

				dialog.setVisible(true);
				if (dialog.isConfirm()) {
					line.setColor(dialog.getColor());
					Command cmd = new AddShapeCmd(model, line);
					executeCommand(cmd);
				}
				model.setStartPoint(null);
			}
			frame.repaint();
			break;
		}

		case "rectangle": {
			DlgRectangle dialog = frame.getDlgRectangle();
			dialog.setColor(model.getActiveFillColor());
			dialog.setBorderColor(model.getActiveBorderColor());
			dialog.getTxtX().setText(String.valueOf(click.getX()));
			dialog.getTxtY().setText(String.valueOf(click.getY()));
			dialog.getTxtWidth().setText("");
			dialog.getTxtHeight().setText("");
			dialog.getTxtX().setEnabled(false);
			dialog.getTxtY().setEnabled(false);

			dialog.setVisible(true);
			if (dialog.isConfirm()) {
				Rectangle rectangle = dialog.makeRectangle();
				Command cmd = new AddShapeCmd(model, rectangle);
				executeCommand(cmd);
			}

			frame.repaint();
			break;
		}

		case "circle": {
			DlgCircle dialog = frame.getDlgCircle();
			dialog.setColor(model.getActiveFillColor());
			dialog.setBorderColor(model.getActiveBorderColor());
			dialog.getTxtX().setText(String.valueOf(click.getX()));
			dialog.getTxtY().setText(String.valueOf(click.getY()));
			dialog.getTxtRadius().setText("");
			dialog.getTxtX().setEnabled(false);
			dialog.getTxtY().setEnabled(false);

			dialog.setVisible(true);
			if (dialog.isConfirm()) {
				Circle circle = dialog.makeCircle();
				Command cmd = new AddShapeCmd(model, circle);
				executeCommand(cmd);
			}

			frame.repaint();
			break;
		}

		case "donut": {
			DlgDonut dialog = frame.getDlgDonut();
			dialog.setColor(model.getActiveFillColor());
			dialog.setBorderColor(model.getActiveBorderColor());
			dialog.getTxtX().setText(String.valueOf(click.getX()));
			dialog.getTxtY().setText(String.valueOf(click.getY()));
			dialog.getTxtOuterRadius().setText("");
			dialog.getTxtInnerRadius().setText("");
			dialog.getTxtX().setEnabled(false);
			dialog.getTxtY().setEnabled(false);

			dialog.setVisible(true);
			if (dialog.isConfirm()) {
				Donut donut = dialog.makeDonut();
				Command cmd = new AddShapeCmd(model, donut);
				executeCommand(cmd);
			}

			frame.repaint();
			break;
		}
		
		case "hexagon": {
			DlgHexagon dialog = frame.getDlgHexagon();
			dialog.setAreaColor(model.getActiveFillColor());
			dialog.setBorderColor(model.getActiveBorderColor());
			dialog.getTxtX().setText(String.valueOf(click.getX()));
			dialog.getTxtY().setText(String.valueOf(click.getY()));
			dialog.getTxtR().setText("");
			dialog.getTxtX().setEnabled(false);
			dialog.getTxtY().setEnabled(false);

			dialog.setVisible(true);
			if (dialog.isConfirm()) {
				HexagonAdapter hexagon = dialog.makeHexagon();
				Command cmd = new AddShapeCmd(model, hexagon);
				executeCommand(cmd);
			}

			frame.repaint();
			break;
		}

		case "select": {
			ListIterator<Shape> it = model.getShapes().listIterator(model.getShapes().size());
			boolean shapeHandled = false;

			while (it.hasPrevious()) {
				Shape temporary = it.previous();

				if (temporary.contains(e.getX(), e.getY())) {
					if (temporary.isSelected()) {
						executeSelectionCommand(new DeselectShapeCmd(temporary));
						syncSelectedShapeWithSelection();
					} else {
						executeSelectionCommand(new SelectShapeCmd(temporary));
						model.setSelectedShape(temporary);
					}
					shapeHandled = true;
					break;
				}
			}

			if (!shapeHandled) {
				for (Shape shape : new ArrayList<>(getSelectedShapes())) {
					executeSelectionCommand(new DeselectShapeCmd(shape));
				}
				syncSelectedShapeWithSelection();
			}
			model.notifyObservers();
			frame.repaint();
			break;
		}

		default:
			JOptionPane.showMessageDialog(frame, "Choose one of the options", "Error Message",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void modify() {
		List<Shape> selectedShapes = getSelectedShapes();

		if (selectedShapes.size() != 1) {
			JOptionPane.showMessageDialog(frame,
					"Please, select exactly one shape to modify.", "Error Message",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		Shape selectedShape = selectedShapes.get(0);
		if (selectedShape instanceof Point) {
			DlgPoint dialog = frame.getDlgPoint();
			dialog.setColor(((Point) selectedShape).getColor());
			dialog.writePoint((Point) selectedShape);
			dialog.getTxtX().setEnabled(true);
			dialog.getTxtY().setEnabled(true);

			dialog.setVisible(true);
			if (dialog.isConfirm()) {
				Point p = dialog.makePoint();
				p.setSelected(selectedShape.isSelected());
				Command cmd = new UpdatePointCmd((Point) selectedShape, p);
				executeCommand(cmd);
			}

		} else if (selectedShape instanceof Line) {
			DlgLine dialog = frame.getDlgLine();
			dialog.setColor(((Line) selectedShape).getColor());
			dialog.writeLine((Line) selectedShape);
			dialog.getTxtX1().setEnabled(true);
			dialog.getTxtY1().setEnabled(true);
			dialog.getTxtX2().setEnabled(true);
			dialog.getTxtY2().setEnabled(true);

			dialog.setVisible(true);
			if (dialog.isConfirm()) {
				Line l = dialog.makeLine();
				l.setSelected(selectedShape.isSelected());
				Command cmd = new UpdateLineCmd((Line) selectedShape, l);
				executeCommand(cmd);
			}

		} else if (selectedShape instanceof Rectangle) {
			DlgRectangle dialog = frame.getDlgRectangle();
			dialog.setColor(((Rectangle) selectedShape).getColor());
			dialog.setBorderColor(((Rectangle) selectedShape).getBorderColor());
			dialog.writeRectangle((Rectangle) selectedShape);
			dialog.getTxtX().setEnabled(true);
			dialog.getTxtY().setEnabled(true);

			dialog.setVisible(true);
			if (dialog.isConfirm()) {
				Rectangle r = dialog.makeRectangle();
				r.setSelected(selectedShape.isSelected());
				Command cmd = new UpdateRectangleCmd((Rectangle) selectedShape, r);
				executeCommand(cmd);
			}

		} else if (selectedShape instanceof Circle && !(selectedShape instanceof Donut)) {
			DlgCircle dialog = frame.getDlgCircle();
			dialog.setColor(((Circle) selectedShape).getColor());
			dialog.setBorderColor(((Circle) selectedShape).getBorderColor());
			dialog.writeCircle((Circle) selectedShape);
			dialog.getTxtX().setEnabled(true);
			dialog.getTxtY().setEnabled(true);

			dialog.setVisible(true);
			if (dialog.isConfirm()) {
				Circle c = dialog.makeCircle();
				c.setSelected(selectedShape.isSelected());
				Command cmd = new UpdateCircleCmd((Circle) selectedShape, c);
				executeCommand(cmd);
			}

		} else if (selectedShape instanceof Donut) {
			DlgDonut dialog = frame.getDlgDonut();
			dialog.setColor(((Donut) selectedShape).getColor());
			dialog.setBorderColor(((Donut) selectedShape).getBorderColor());
			dialog.writeDonut((Donut) selectedShape);
			dialog.getTxtX().setEnabled(true);
			dialog.getTxtY().setEnabled(true);

			dialog.setVisible(true);
			if (dialog.isConfirm()) {
				Donut d = dialog.makeDonut();
				d.setSelected(selectedShape.isSelected());
				Command cmd = new UpdateDonutCmd((Donut) selectedShape, d);
				executeCommand(cmd);
			}
		} else if (selectedShape instanceof HexagonAdapter) {
			DlgHexagon dialog = frame.getDlgHexagon();
			HexagonAdapter hexagon = (HexagonAdapter) selectedShape;
			dialog.setAreaColor(hexagon.getAreaColor());
			dialog.setBorderColor(hexagon.getBorderColor());
			dialog.writeHexagon(hexagon);
			dialog.getTxtX().setEnabled(true);
			dialog.getTxtY().setEnabled(true);

			dialog.setVisible(true);
			if (dialog.isConfirm()) {
				HexagonAdapter h = dialog.makeHexagon();
				h.setSelected(selectedShape.isSelected());
				Command cmd = new UpdateHexagonCmd(hexagon, h);
				executeCommand(cmd);
			}
		}

		frame.repaint();
	}

	public void delete() {
		List<Shape> selectedShapes = getSelectedShapes();

		if (selectedShapes.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "There is no selected shape. Select shapes you want to delete.",
					"Error Message", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete the selected shape(s)?",
				"Confirm delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (confirm == JOptionPane.YES_OPTION) {
			for (Shape shape : selectedShapes) {
				Command cmd = new RemoveShapeCmd(model, shape);
				executeCommand(cmd);
			}
			model.setSelectedShape(null);
			model.notifyObservers();
			frame.repaint();
		}
	}

	private void executeCommand(Command cmd) {
		cmd.execute();
		undoStack.push(cmd);
		redoStack.clear(); // kad uradimo novu akciju posle undo, redo se brise
		log(describeCommand(cmd));
		frame.updateUndoRedoButtons(!undoStack.isEmpty(), !redoStack.isEmpty());
		frame.repaint();
	}

	private void executeSelectionCommand(Command cmd) {
		cmd.execute();
		undoStack.push(cmd);
		redoStack.clear();
		log(describeCommand(cmd));
		frame.updateUndoRedoButtons(!undoStack.isEmpty(), !redoStack.isEmpty());
		frame.repaint();
	}
	
	public void undo() {
	    if (undoStack.isEmpty()) return;

	    Command cmd = undoStack.pop();
	    cmd.unexecute();
	    redoStack.push(cmd);
	    log("Undo " + cmd);
	    syncSelectedShapeWithSelection();

	    model.notifyObservers();
	    frame.updateUndoRedoButtons(!undoStack.isEmpty(), !redoStack.isEmpty());
	    frame.repaint();
	}

	public void redo() {
	    if (redoStack.isEmpty()) return;

	    Command cmd = redoStack.pop();
	    cmd.execute();
	    undoStack.push(cmd);
	    log("Redo " + cmd);
	    syncSelectedShapeWithSelection();

	    model.notifyObservers();
	    frame.updateUndoRedoButtons(!undoStack.isEmpty(), !redoStack.isEmpty());
	    frame.repaint();
	}

	public void toFront() {
		List<Shape> selectedShapes = getSelectedShapes();

		if (selectedShapes.size() != 1) {
			return;
		}

		Shape selectedShape = selectedShapes.get(0);
		if (model.getShapes().indexOf(selectedShape) == model.getShapes().size() - 1) {
			return;
		}

		Command cmd = new ToFrontCmd(model, selectedShape);
		executeCommand(cmd);
		model.notifyObservers();
	}

	public void toBack() {
		List<Shape> selectedShapes = getSelectedShapes();

		if (selectedShapes.size() != 1) {
			return;
		}

		Shape selectedShape = selectedShapes.get(0);
		if (model.getShapes().indexOf(selectedShape) <= 0) {
			return;
		}

		Command cmd = new ToBackCmd(model, selectedShape);
		executeCommand(cmd);
		model.notifyObservers();
	}

	public void bringToFront() {
		List<Shape> selectedShapes = getSelectedShapes();

		if (selectedShapes.size() != 1) {
			return;
		}

		Shape selectedShape = selectedShapes.get(0);
		if (model.getShapes().indexOf(selectedShape) == model.getShapes().size() - 1) {
			return;
		}

		Command cmd = new BringToFrontCmd(model, selectedShape);
		executeCommand(cmd);
		model.notifyObservers();
	}

	public void bringToBack() {
		List<Shape> selectedShapes = getSelectedShapes();

		if (selectedShapes.size() != 1) {
			return;
		}

		Shape selectedShape = selectedShapes.get(0);
		if (model.getShapes().indexOf(selectedShape) <= 0) {
			return;
		}

		Command cmd = new BringToBackCmd(model, selectedShape);
		executeCommand(cmd);
		model.notifyObservers();
	}

	public void saveLog(String path) {
		FileManager fileManager = new FileManager(new LogFileStrategy());
		fileManager.save(model, path);
	}

	public void loadLog(String path) {
		FileManager fileManager = new FileManager(new LogFileStrategy());
		fileManager.load(model, path);
		model.getShapes().clear();
		clearSelections();
		undoStack.clear();
		redoStack.clear();
		frame.updateUndoRedoButtons(false, false);

		List<String> loadedEntries = new ArrayList<>(model.getLogEntries());
		model.clearLog();
		frame.refreshLog();

		for (String entry : loadedEntries) {
			showReplayDialog(entry);
			replayLogEntry(entry);
			model.addLog(entry);
			model.notifyObservers();
			frame.updateUndoRedoButtons(!undoStack.isEmpty(), !redoStack.isEmpty());
			frame.refreshLog();
			frame.repaint();
		}

		model.setSelectedShape(null);
		model.notifyObservers();
		frame.refreshLog();
		frame.repaint();
	}

	private void showReplayDialog(String entry) {
		JOptionPane optionPane = new JOptionPane(entry, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
		JDialog dialog = optionPane.createDialog(frame, "Load Log");
		dialog.setModal(true);
		if (replayDialogLocation != null) {
			dialog.setLocation(replayDialogLocation);
		}
		dialog.setVisible(true);
		replayDialogLocation = dialog.getLocation();
	}

	private void replayLogEntry(String entry) {
		if (entry.startsWith("Add ")) {
			Shape shape = logParser.parseShape(entry);
			if (shape != null) {
				executeReplayCommand(new AddShapeCmd(model, shape));
			}
			return;
		}

		if (entry.startsWith("Select ")) {
			Shape shape = findShapeByDescriptor(entry.substring("Select ".length()));
			if (shape != null) {
				executeReplayCommand(new SelectShapeCmd(shape));
				model.setSelectedShape(shape);
			}
			return;
		}

		if (entry.startsWith("Deselect ")) {
			Shape shape = findShapeByDescriptor(entry.substring("Deselect ".length()));
			if (shape != null) {
				executeReplayCommand(new DeselectShapeCmd(shape));
				syncSelectedShapeWithSelection();
			}
			return;
		}

		if (entry.startsWith("Delete ")) {
			Shape shape = findShapeByDescriptor(entry.substring("Delete ".length()));
			if (shape != null) {
				executeReplayCommand(new RemoveShapeCmd(model, shape));
			}
			return;
		}

		if (entry.startsWith("To front ")) {
			Shape shape = findShapeByDescriptor(entry.substring("To front ".length()));
			if (shape != null) {
				executeReplayCommand(new ToFrontCmd(model, shape));
			}
			return;
		}

		if (entry.startsWith("To back ")) {
			Shape shape = findShapeByDescriptor(entry.substring("To back ".length()));
			if (shape != null) {
				executeReplayCommand(new ToBackCmd(model, shape));
			}
			return;
		}

		if (entry.startsWith("Bring to front ")) {
			Shape shape = findShapeByDescriptor(entry.substring("Bring to front ".length()));
			if (shape != null) {
				executeReplayCommand(new BringToFrontCmd(model, shape));
			}
			return;
		}

		if (entry.startsWith("Bring to back ")) {
			Shape shape = findShapeByDescriptor(entry.substring("Bring to back ".length()));
			if (shape != null) {
				executeReplayCommand(new BringToBackCmd(model, shape));
			}
			return;
		}

		if (entry.startsWith("Update ")) {
			replayUpdate(entry.substring("Update ".length()));
			return;
		}

		if (entry.startsWith("Undo ")) {
			replayUndo();
			return;
		}

		if (entry.startsWith("Redo ")) {
			replayRedo();
		}
	}

	private void replayUpdate(String descriptor) {
		Shape selectedShape = getSingleSelectedShape();
		Shape newState = logParser.parseShape(descriptor);

		if (selectedShape == null || newState == null) {
			return;
		}

		Command cmd = null;
		if (selectedShape instanceof Point && newState instanceof Point) {
			cmd = new UpdatePointCmd((Point) selectedShape, (Point) newState);
		} else if (selectedShape instanceof Line && newState instanceof Line) {
			cmd = new UpdateLineCmd((Line) selectedShape, (Line) newState);
		} else if (selectedShape instanceof Rectangle && newState instanceof Rectangle) {
			cmd = new UpdateRectangleCmd((Rectangle) selectedShape, (Rectangle) newState);
		} else if (selectedShape instanceof Circle && !(selectedShape instanceof Donut) && newState instanceof Circle) {
			cmd = new UpdateCircleCmd((Circle) selectedShape, (Circle) newState);
		} else if (selectedShape instanceof Donut && newState instanceof Donut) {
			cmd = new UpdateDonutCmd((Donut) selectedShape, (Donut) newState);
		} else if (selectedShape instanceof HexagonAdapter && newState instanceof HexagonAdapter) {
			cmd = new UpdateHexagonCmd((HexagonAdapter) selectedShape, (HexagonAdapter) newState);
		}

		if (cmd != null) {
			executeReplayCommand(cmd);
		}
	}

	private void replayUndo() {
		if (undoStack.isEmpty()) {
			return;
		}

		Command cmd = undoStack.pop();
		cmd.unexecute();
		redoStack.push(cmd);
		syncSelectedShapeWithSelection();
	}

	private void replayRedo() {
		if (redoStack.isEmpty()) {
			return;
		}

		Command cmd = redoStack.pop();
		cmd.execute();
		undoStack.push(cmd);
		syncSelectedShapeWithSelection();
	}

	private void executeReplayCommand(Command cmd) {
		cmd.execute();
		undoStack.push(cmd);
		redoStack.clear();
	}

	private Shape findShapeByDescriptor(String descriptor) {
		Shape parsedShape = logParser.parseShape(descriptor);
		if (parsedShape == null) {
			return null;
		}

		String signature = normalizeShapeSignature(parsedShape.toString());
		for (Shape shape : model.getShapes()) {
			if (normalizeShapeSignature(shape.toString()).equals(signature)) {
				return shape;
			}
		}
		return null;
	}

	private String normalizeShapeSignature(String signature) {
		return signature.replace(", selected=true", "").replace(", selected=false", "");
	}

	private Shape getSingleSelectedShape() {
		List<Shape> selectedShapes = getSelectedShapes();
		if (selectedShapes.size() == 1) {
			return selectedShapes.get(0);
		}
		return null;
	}

	private void clearSelections() {
		for (Shape shape : model.getShapes()) {
			shape.setSelected(false);
		}
		model.setSelectedShape(null);
	}

	private void syncSelectedShapeWithSelection() {
		List<Shape> selectedShapes = getSelectedShapes();
		model.setSelectedShape(selectedShapes.isEmpty() ? null : selectedShapes.get(selectedShapes.size() - 1));
	}

	public void saveDrawing(String path) {
		FileManager fileManager = new FileManager(new DrawingFileStrategy());
		fileManager.save(model, path);
	}

	public void loadDrawing(String path) {
		FileManager fileManager = new FileManager(new DrawingFileStrategy());
		fileManager.load(model, path);
		undoStack.clear();
		redoStack.clear();
		model.setSelectedShape(null);
		model.getLogEntries().clear();
		model.notifyObservers();
		frame.updateUndoRedoButtons(false, false);
		frame.refreshLog();
		frame.repaint();
	}

	private void log(String message) {
		if (message == null || message.isEmpty())
			return;
		model.addLog(message);
		frame.refreshLog();
	}

	private String describeCommand(Command cmd) {
		if (cmd == null)
			return "";
		return cmd.toString();
	}

	private List<Shape> getSelectedShapes() {
		List<Shape> selected = new ArrayList<>();
		for (Shape shape : model.getShapes()) {
			if (shape.isSelected()) {
				selected.add(shape);
			}
		}
		return selected;
	}

}
