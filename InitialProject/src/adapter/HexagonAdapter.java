package adapter;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Shape;
import hexagon.Hexagon;

public class HexagonAdapter extends Shape {
	private Hexagon hexagon;

	public HexagonAdapter(int x, int y, int r, Color borderColor, Color areaColor) {
		hexagon = new Hexagon(x, y, r);
		hexagon.setBorderColor(borderColor);
		hexagon.setAreaColor(areaColor);
	}

	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	public int getX() {
		return hexagon.getX();
	}

	public void setX(int x) {
		hexagon.setX(x);
	}

	public int getY() {
		return hexagon.getY();
	}

	public void setY(int y) {
		hexagon.setY(y);
	}

	public int getR() {
		return hexagon.getR();
	}

	public void setR(int r) {
		hexagon.setR(r);
	}

	public Color getBorderColor() {
		return hexagon.getBorderColor();
	}

	public void setBorderColor(Color color) {
		hexagon.setBorderColor(color);
	}

	public Color getAreaColor() {
		return hexagon.getAreaColor();
	}

	public void setAreaColor(Color color) {
		hexagon.setAreaColor(color);
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof HexagonAdapter) {
			return this.getR() - ((HexagonAdapter) o).getR();
		}
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof HexagonAdapter)) {
			return false;
		}
		HexagonAdapter other = (HexagonAdapter) obj;
		return getX() == other.getX() && getY() == other.getY() && getR() == other.getR();
	}

	@Override
	public String toString() {
		return "Hexagon(x=" + getX() + ", y=" + getY() + ", r=" + getR() + ", fill=" + colorToHex(getAreaColor())
				+ ", border=" + colorToHex(getBorderColor()) + ", selected=" + isSelected() + ")";
	}

	@Override
	public boolean isSelected() {
		return hexagon.isSelected();
	}

	@Override
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
	}

	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveBy(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public HexagonAdapter clone() {
		HexagonAdapter hexagonAdapter = new HexagonAdapter(getX(), getY(), getR(), getBorderColor(), getAreaColor());
		hexagonAdapter.setSelected(this.isSelected());
		return hexagonAdapter;
	}
}
