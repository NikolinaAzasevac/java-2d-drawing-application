package geometry;

import java.awt.Graphics;
import java.io.Serializable;
import java.awt.Color;

public abstract class Shape implements Moveable, Comparable, Serializable {
	private static final long serialVersionUID = 1L;
	protected boolean selected;

	public Shape() {
	}

	public Shape(boolean selected) {
		this.selected = selected;
	}

	public abstract boolean contains(int x, int y);

	public abstract String toString();

	public abstract boolean equals(Object obj);

	public abstract void draw(Graphics g);

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	protected static String colorToHex(Color color) {
		if (color == null) {
			return "null";
		}
		return String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
	}
}
