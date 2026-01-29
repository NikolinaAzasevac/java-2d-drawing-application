package mvc;

import java.awt.event.MouseEvent;
import java.util.ListIterator;
import java.util.Stack;

import javax.swing.JOptionPane;

import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import command.Command;
import command.RemoveShapeCmd;
import command.AddShapeCmd;

public class DrawingController {

	private final DrawingModel model;
	private final DrawingFrame frame;
	private final Stack<Command> undoStack = new Stack<>();
	private final Stack<Command> redoStack = new Stack<>();

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
				model.setActiveBorderColor(dialog.getColor());
				frame.refreshActiveColors();
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
					model.setActiveBorderColor(dialog.getColor());
					frame.refreshActiveColors();
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
				model.setActiveFillColor(dialog.getColor());
				model.setActiveBorderColor(dialog.getBorderColor());
				frame.refreshActiveColors();
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
				model.setActiveFillColor(dialog.getColor());
				model.setActiveBorderColor(dialog.getBorderColor());
				frame.refreshActiveColors();
			}

			frame.repaint();
			break;
		}

		case "donut": {
			DlgDonut dialog = frame.getDlgDonut();
			dialog.setColor(model.getActiveFillColor());
			dialog.setInnerColor(model.getActiveFillColor());
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
				model.setActiveFillColor(dialog.getColor());
				model.setActiveBorderColor(dialog.getBorderColor());
				frame.refreshActiveColors();
			}

			frame.repaint();
			break;
		}

		case "select": {
			// isti algoritam unazad kroz listu
			ListIterator<Shape> it = model.getShapes().listIterator(model.getShapes().size());
			boolean shapeSelected = false;
			Shape selectedShape = model.getSelectedShape();

			while (it.hasPrevious()) {
				Shape temporary = it.previous();

				if (temporary.contains(e.getX(), e.getY())) {
					if (temporary.equals(selectedShape)) {
						temporary.setSelected(false);
						model.setSelectedShape(null);
						log("Deselect " + temporary);
					} else {
						if (selectedShape != null) {
							selectedShape.setSelected(false);
							log("Deselect " + selectedShape);
						}
						temporary.setSelected(true);
						model.setSelectedShape(temporary);
						log("Select " + temporary);
					}
					shapeSelected = true;
					break;
				}
			}

			if (!shapeSelected && model.getSelectedShape() != null) {
				Shape previouslySelected = model.getSelectedShape();
				previouslySelected.setSelected(false);
				model.setSelectedShape(null);
				log("Deselect " + previouslySelected);
			}

			frame.repaint();
			break;
		}

		default:
			JOptionPane.showMessageDialog(frame, "Choose one of the options", "Error Message",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void modify() {
		Shape selectedShape = model.getSelectedShape();

		if (selectedShape == null) {
			JOptionPane.showMessageDialog(frame,
					"There is no selected shape! Please, select the shape you want to modify.", "Error Message",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		if (selectedShape instanceof Point) {
			DlgPoint dialog = frame.getDlgPoint();
			dialog.setColor(((Point) selectedShape).getColor());
			dialog.writePoint((Point) selectedShape);
			dialog.getTxtX().setEnabled(true);
			dialog.getTxtY().setEnabled(true);

			dialog.setVisible(true);
			if (dialog.isConfirm()) {
				Point p = dialog.makePoint();
				((Point) selectedShape).setX(p.getX());
				((Point) selectedShape).setY(p.getY());
				((Point) selectedShape).setColor(p.getColor());
				model.setActiveBorderColor(p.getColor());
				frame.refreshActiveColors();
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
				((Line) selectedShape).getStartPoint().setX(l.getStartPoint().getX());
				((Line) selectedShape).getStartPoint().setY(l.getStartPoint().getY());
				((Line) selectedShape).getEndPoint().setX(l.getEndPoint().getX());
				((Line) selectedShape).getEndPoint().setY(l.getEndPoint().getY());
				((Line) selectedShape).setColor(dialog.getColor());
				model.setActiveBorderColor(dialog.getColor());
				frame.refreshActiveColors();
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
				((Rectangle) selectedShape).getUpperLeftPoint().setX(r.getUpperLeftPoint().getX());
				((Rectangle) selectedShape).getUpperLeftPoint().setY(r.getUpperLeftPoint().getY());
				((Rectangle) selectedShape).setHeight(r.getHeight());
				((Rectangle) selectedShape).setWidth(r.getWidth());
				((Rectangle) selectedShape).setColor(r.getColor());
				((Rectangle) selectedShape).setBorderColor(r.getBorderColor());
				model.setActiveFillColor(r.getColor());
				model.setActiveBorderColor(r.getBorderColor());
				frame.refreshActiveColors();
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
				((Circle) selectedShape).getCenter().setX(c.getCenter().getX());
				((Circle) selectedShape).getCenter().setY(c.getCenter().getY());
				((Circle) selectedShape).setRadius(c.getRadius());
				((Circle) selectedShape).setColor(c.getColor());
				((Circle) selectedShape).setBorderColor(c.getBorderColor());
				model.setActiveFillColor(c.getColor());
				model.setActiveBorderColor(c.getBorderColor());
				frame.refreshActiveColors();
			}

		} else if (selectedShape instanceof Donut) {
			DlgDonut dialog = frame.getDlgDonut();
			dialog.setColor(((Donut) selectedShape).getColor());
			dialog.setBorderColor(((Donut) selectedShape).getBorderColor());
			dialog.setInnerColor(((Donut) selectedShape).getInnerColor());
			dialog.writeDonut((Donut) selectedShape);
			dialog.getTxtX().setEnabled(true);
			dialog.getTxtY().setEnabled(true);

			dialog.setVisible(true);
			if (dialog.isConfirm()) {
				Donut d = dialog.makeDonut();
				((Donut) selectedShape).getCenter().setX(d.getCenter().getX());
				((Donut) selectedShape).getCenter().setY(d.getCenter().getY());
				((Donut) selectedShape).setInnerRadius(d.getInnerRadius());
				((Donut) selectedShape).setRadius(d.getRadius());
				((Donut) selectedShape).setColor(d.getColor());
				((Donut) selectedShape).setInnerColor(d.getInnerColor());
				((Donut) selectedShape).setBorderColor(d.getBorderColor());
				model.setActiveFillColor(d.getColor());
				model.setActiveBorderColor(d.getBorderColor());
				frame.refreshActiveColors();
			}
		}

		selectedShape.setSelected(false);
		model.setSelectedShape(null);
		frame.repaint();
	}

	public void delete() {
		Shape selectedShape = model.getSelectedShape();

		if (selectedShape == null) {
			JOptionPane.showMessageDialog(frame, "There is no selected shape. Select the shape you want to delete.",
					"Error Message", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete the selected shape?",
				"Confirm delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (confirm == JOptionPane.YES_OPTION) {
			Command cmd = new RemoveShapeCmd(model, selectedShape);
			executeCommand(cmd);
			model.setSelectedShape(null);
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
	
	public void undo() {
	    if (undoStack.isEmpty()) return;

	    Command cmd = undoStack.pop();
	    cmd.unexecute();
	    redoStack.push(cmd);
	    log("Undo " + cmd);

	    // (opciono) očisti selekciju da ne baguje posle undo
	    if (model.getSelectedShape() != null) {
	        model.getSelectedShape().setSelected(false);
	        model.setSelectedShape(null);
	    }

	    frame.updateUndoRedoButtons(!undoStack.isEmpty(), !redoStack.isEmpty());
	    frame.repaint();
	}

	public void redo() {
	    if (redoStack.isEmpty()) return;

	    Command cmd = redoStack.pop();
	    cmd.execute();
	    undoStack.push(cmd);
	    log("Redo " + cmd);

	    if (model.getSelectedShape() != null) {
	        model.getSelectedShape().setSelected(false);
	        model.setSelectedShape(null);
	    }

	    frame.updateUndoRedoButtons(!undoStack.isEmpty(), !redoStack.isEmpty());
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

}
