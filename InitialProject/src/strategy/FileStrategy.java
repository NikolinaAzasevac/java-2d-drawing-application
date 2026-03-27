package strategy;

import mvc.DrawingModel;

public interface FileStrategy {

	void save(DrawingModel model, String path);
	void load(DrawingModel model, String path);
	
}
