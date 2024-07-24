package geometry;

import java.awt.Graphics;

public interface Moveable {
	
	//nema property
	//nema konstruktore

	public abstract void moveTo(int x, int y);
	/*public abstract*/ void moveBy(int x, int y);
	void draw(Graphics g);
}

