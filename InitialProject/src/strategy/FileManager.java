package strategy;

import mvc.DrawingModel;

public class FileManager {
	private FileStrategy fileStrategy;

	public FileManager(FileStrategy fileStrategy) {
		this.fileStrategy = fileStrategy;
	}

	public void save(DrawingModel model, String path) {
		fileStrategy.save(model, path);
	}

	public void load(DrawingModel model, String path) {
		fileStrategy.load(model, path);
	}

}
