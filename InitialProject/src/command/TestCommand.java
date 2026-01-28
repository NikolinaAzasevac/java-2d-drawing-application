package command;

import java.awt.Color;

import mvc.DrawingModel;
import geometry.Point;

public class TestCommand {

	public static void main(String[] args) {
		// Dodavanje tačke
		DrawingModel model = new DrawingModel();
		Point p1 = new Point(10, 10, Color.BLACK);
		
		AddPointCmd addPointCmd = new AddPointCmd(model, p1);
		addPointCmd.execute();
		
		System.out.println(model.getShapes());
		
		// Brisanje tačke
		RemovePointCmd removePointCmd = new RemovePointCmd(model, p1);
		
		removePointCmd.execute();	
		System.out.println(model.getShapes());
		
		removePointCmd.unexecute();
		System.out.println(model.getShapes());
		

	}

}
