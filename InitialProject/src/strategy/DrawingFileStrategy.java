package strategy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import geometry.Shape;
import mvc.DrawingModel;

public class DrawingFileStrategy implements FileStrategy {

	@Override
	public void save(DrawingModel model, String path) {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))) {
			outputStream.writeObject(model.getShapes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load(DrawingModel model, String path) {
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path))) {
			List<Shape> loadedShapes = (List<Shape>) inputStream.readObject();
			model.getShapes().clear();
			model.getShapes().addAll(loadedShapes);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
