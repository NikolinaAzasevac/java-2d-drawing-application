package strategy;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import adapter.HexagonAdapter;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
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

	public Shape parseShape(String line) {
		if (line.startsWith("Add Point(") || line.startsWith("Point(")) {
			return parsePoint(line);
		}
		if (line.startsWith("Add Line(") || line.startsWith("Line(")) {
			return parseLine(line);
		}
		if (line.startsWith("Add Rect(") || line.startsWith("Rect(")) {
			return parseRectangle(line);
		}
		if (line.startsWith("Add Circle(") || line.startsWith("Circle(")) {
			return parseCircle(line);
		}
		if (line.startsWith("Add Donut(") || line.startsWith("Donut(")) {
			return parseDonut(line);
		}
		if (line.startsWith("Add Hexagon(") || line.startsWith("Hexagon(")) {
			return parseHexagon(line);
		}
		return null;
	}

	private Point parsePoint(String line) {
		String content = line.substring(line.indexOf("Point(") + 6, line.lastIndexOf(")"));
		String[] parts = content.split(", ");

		int x = 0;
		int y = 0;
		Color color = Color.BLACK;
		boolean selected = false;

		for (String part : parts) {
			String[] keyValue = part.split("=");
			String key = keyValue[0];
			String value = keyValue[1];

			if (key.equals("x")) {
				x = Integer.parseInt(value);
			} else if (key.equals("y")) {
				y = Integer.parseInt(value);
			} else if (key.equals("color")) {
				color = Color.decode(value);
			} else if (key.equals("selected")) {
				selected = Boolean.parseBoolean(value);
			}
		}

		Point point = new Point(x, y, color);
		point.setSelected(selected);
		return point;
	}

	private Line parseLine(String line) {
		String content = line.substring(line.indexOf("Line(") + 5, line.lastIndexOf(")"));
		String[] parts = content.split(", ");

		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;
		Color color = Color.BLACK;
		boolean selected = false;

		for (String part : parts) {
			String[] keyValue = part.split("=");
			String key = keyValue[0];
			String value = keyValue[1];

			if (key.equals("x1")) {
				x1 = Integer.parseInt(value);
			} else if (key.equals("y1")) {
				y1 = Integer.parseInt(value);
			} else if (key.equals("x2")) {
				x2 = Integer.parseInt(value);
			} else if (key.equals("y2")) {
				y2 = Integer.parseInt(value);
			} else if (key.equals("color")) {
				color = Color.decode(value);
			} else if (key.equals("selected")) {
				selected = Boolean.parseBoolean(value);
			}
		}

		Line lineShape = new Line(new Point(x1, y1), new Point(x2, y2), color);
		lineShape.setSelected(selected);
		return lineShape;
	}

	private Rectangle parseRectangle(String line) {
		String content = line.substring(line.indexOf("Rect(") + 5, line.lastIndexOf(")"));
		String[] parts = content.split(", ");

		int x = 0;
		int y = 0;
		int width = 0;
		int height = 0;
		Color fillColor = Color.WHITE;
		Color borderColor = Color.BLACK;
		boolean selected = false;

		for (String part : parts) {
			String[] keyValue = part.split("=");
			String key = keyValue[0];
			String value = keyValue[1];

			if (key.equals("x")) {
				x = Integer.parseInt(value);
			} else if (key.equals("y")) {
				y = Integer.parseInt(value);
			} else if (key.equals("w")) {
				width = Integer.parseInt(value);
			} else if (key.equals("h")) {
				height = Integer.parseInt(value);
			} else if (key.equals("fill")) {
				fillColor = Color.decode(value);
			} else if (key.equals("border")) {
				borderColor = Color.decode(value);
			} else if (key.equals("selected")) {
				selected = Boolean.parseBoolean(value);
			}
		}

		Rectangle rectangle = new Rectangle(new Point(x, y), width, height, fillColor, borderColor);
		rectangle.setSelected(selected);
		return rectangle;
	}

	private Circle parseCircle(String line) {
		String content = line.substring(line.indexOf("Circle(") + 7, line.lastIndexOf(")"));
		String[] parts = content.split(", ");

		int x = 0;
		int y = 0;
		int radius = 0;
		Color fillColor = Color.WHITE;
		Color borderColor = Color.BLACK;
		boolean selected = false;

		for (String part : parts) {
			String[] keyValue = part.split("=");
			String key = keyValue[0];
			String value = keyValue[1];

			if (key.equals("x")) {
				x = Integer.parseInt(value);
			} else if (key.equals("y")) {
				y = Integer.parseInt(value);
			} else if (key.equals("r")) {
				radius = Integer.parseInt(value);
			} else if (key.equals("fill")) {
				fillColor = Color.decode(value);
			} else if (key.equals("border")) {
				borderColor = Color.decode(value);
			} else if (key.equals("selected")) {
				selected = Boolean.parseBoolean(value);
			}
		}

		Circle circle = new Circle(new Point(x, y), radius, fillColor, borderColor);
		circle.setSelected(selected);
		return circle;
	}

	private Donut parseDonut(String line) {
		String content = line.substring(line.indexOf("Donut(") + 6, line.lastIndexOf(")"));
		String[] parts = content.split(", ");

		int x = 0;
		int y = 0;
		int radius = 0;
		int innerRadius = 0;
		Color fillColor = Color.WHITE;
		Color borderColor = Color.BLACK;
		boolean selected = false;

		for (String part : parts) {
			String[] keyValue = part.split("=");
			String key = keyValue[0];
			String value = keyValue[1];

			if (key.equals("x")) {
				x = Integer.parseInt(value);
			} else if (key.equals("y")) {
				y = Integer.parseInt(value);
			} else if (key.equals("r")) {
				radius = Integer.parseInt(value);
			} else if (key.equals("rIn")) {
				innerRadius = Integer.parseInt(value);
			} else if (key.equals("fill")) {
				fillColor = Color.decode(value);
			} else if (key.equals("border")) {
				borderColor = Color.decode(value);
			} else if (key.equals("selected")) {
				selected = Boolean.parseBoolean(value);
			}
		}

		Donut donut = new Donut(new Point(x, y), radius, innerRadius, fillColor, borderColor);
		donut.setSelected(selected);
		return donut;
	}

	private HexagonAdapter parseHexagon(String line) {
		String content = line.substring(line.indexOf("Hexagon(") + 8, line.lastIndexOf(")"));
		String[] parts = content.split(", ");

		int x = 0;
		int y = 0;
		int radius = 0;
		Color fillColor = Color.WHITE;
		Color borderColor = Color.BLACK;
		boolean selected = false;

		for (String part : parts) {
			String[] keyValue = part.split("=");
			String key = keyValue[0];
			String value = keyValue[1];

			if (key.equals("x")) {
				x = Integer.parseInt(value);
			} else if (key.equals("y")) {
				y = Integer.parseInt(value);
			} else if (key.equals("r")) {
				radius = Integer.parseInt(value);
			} else if (key.equals("fill")) {
				fillColor = Color.decode(value);
			} else if (key.equals("border")) {
				borderColor = Color.decode(value);
			} else if (key.equals("selected")) {
				selected = Boolean.parseBoolean(value);
			}
		}

		HexagonAdapter hexagon = new HexagonAdapter(x, y, radius, borderColor, fillColor);
		hexagon.setSelected(selected);
		return hexagon;
	}

}
