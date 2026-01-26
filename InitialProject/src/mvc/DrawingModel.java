package mvc;

import java.util.ArrayList;
import java.util.List;

import geometry.Shape;

public class DrawingModel {
    private final List<Shape> shapes = new ArrayList<>();

    public void add(Shape s) {
        shapes.add(s);
    }

    public void remove(Shape s) {
        shapes.remove(s);
    }

    public List<Shape> getShapes() {
        return shapes;
    }
}
