package geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape implements Moveable,Comparable{
	protected boolean selected;

 	public Shape() {}

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

	public abstract Color getColor();
}
