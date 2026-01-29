package command;

import java.awt.Color;

import mvc.DrawingModel;
import geometry.Point;

public class TestCommand {

	public static void main(String[] args) {
		// Dodavanje tacke
		DrawingModel model = new DrawingModel();
		Point p1 = new Point(10, 10, Color.BLACK);

		Command addCmd = new AddShapeCmd(model, p1);
		addCmd.execute();
		System.out.println(model.getShapes());

		// Brisanje tačke
		Command removeCmd = new RemoveShapeCmd(model, p1);
		removeCmd.execute();
		System.out.println(model.getShapes());

		removeCmd.unexecute();
		System.out.println(model.getShapes());

	}

}
