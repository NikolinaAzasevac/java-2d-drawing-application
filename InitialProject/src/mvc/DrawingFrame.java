package mvc;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class DrawingFrame extends JFrame {
    private final DrawingView view = new DrawingView();
    private DrawingController controller;

    public DrawingFrame() {
        setLayout(new BorderLayout());
        add(view, BorderLayout.CENTER);

        view.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (controller != null) controller.mouseClicked(e);
            }
        });
    }

    public DrawingView getView() {
        return view;
    }

    public void setController(DrawingController controller) {
        this.controller = controller;
    }
}
