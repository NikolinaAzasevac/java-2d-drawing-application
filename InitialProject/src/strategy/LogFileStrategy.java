package strategy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import mvc.DrawingModel;

public class LogFileStrategy implements FileStrategy {

	@Override
	public void save(DrawingModel model, String path) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
			for (String logEntry : model.getLogEntries()) {
				writer.write(logEntry);
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load(DrawingModel model, String path) {
		model.clearLog();
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = reader.readLine()) != null) {
				model.addLog(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
