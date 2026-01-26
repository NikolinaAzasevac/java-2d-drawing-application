package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;

import geometry.Point;

public class DrawingController {
    private final DrawingModel model;
    private final DrawingFrame frame;

    public DrawingController(DrawingModel model, DrawingFrame frame) {
        this.model = model;
        this.frame = frame;
    }

    public void mouseClicked(MouseEvent e) {
        Point p = new Point(e.getX(), e.getY(), Color.BLACK);
        model.add(p);
        frame.repaint();
    }
}
