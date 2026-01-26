package mvc;

import java.awt.Graphics;
import javax.swing.JPanel;

import geometry.Shape;

public class DrawingView extends JPanel {
    private DrawingModel model;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (model == null) return;

        for (Shape s : model.getShapes()) {
            s.draw(g);
        }
    }

    public void setModel(DrawingModel model) {
        this.model = model;
    }
}
