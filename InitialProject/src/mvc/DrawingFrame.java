package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;

public class DrawingFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private final DrawingView view = new DrawingView();
	private DrawingController controller;

	private final ButtonGroup btnGroup = new ButtonGroup();

	private final JToggleButton tglbtnPoint = new JToggleButton("Point");
	private final JToggleButton tglbtnLine = new JToggleButton("Line");
	private final JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	private final JToggleButton tglbtnCircle = new JToggleButton("Circle");
	private final JToggleButton tglbtnDonut = new JToggleButton("Donut");

	private final JToggleButton tglbtnSelect = new JToggleButton("Select");
	private final JToggleButton tglbtnModify = new JToggleButton("Modify");
	private final JToggleButton tglbtnDelete = new JToggleButton("Delete");

	private String choice = "point";

	private final DlgPoint dlgPoint = new DlgPoint();
	private final DlgLine dlgLine = new DlgLine();
	private final DlgRectangle dlgRectangle = new DlgRectangle();
	private final DlgCircle dlgCircle = new DlgCircle();
	private final DlgDonut dlgDonut = new DlgDonut();

	public DrawingFrame() {
		setTitle("Nikolina Azasevac IT9/2023");
		setResizable(true);

		getContentPane().setLayout(new BorderLayout());

		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(new Color(255, 224, 250));
		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(new Color(255, 224, 250));

		// grupisanje
		btnGroup.add(tglbtnPoint);
		btnGroup.add(tglbtnLine);
		btnGroup.add(tglbtnRectangle);
		btnGroup.add(tglbtnCircle);
		btnGroup.add(tglbtnDonut);
		btnGroup.add(tglbtnSelect);
		btnGroup.add(tglbtnModify);
		btnGroup.add(tglbtnDelete);

		// boje teksta
		tglbtnPoint.setForeground(Color.PINK);
		tglbtnLine.setForeground(Color.PINK);
		tglbtnRectangle.setForeground(Color.PINK);
		tglbtnCircle.setForeground(Color.PINK);
		tglbtnDonut.setForeground(Color.PINK);
		tglbtnSelect.setForeground(Color.PINK);
		tglbtnModify.setForeground(Color.PINK);
		tglbtnDelete.setForeground(Color.PINK);

		// default
		tglbtnPoint.setSelected(true);

		// north
		pnlNorth.add(tglbtnPoint);
		pnlNorth.add(tglbtnLine);
		pnlNorth.add(tglbtnRectangle);
		pnlNorth.add(tglbtnCircle);
		pnlNorth.add(tglbtnDonut);

		// south
		pnlSouth.add(tglbtnSelect);
		pnlSouth.add(tglbtnModify);
		pnlSouth.add(tglbtnDelete);

		// akcije - samo postavljaju choice
		tglbtnPoint.addActionListener(e -> choice = "point");
		tglbtnLine.addActionListener(e -> choice = "line");
		tglbtnRectangle.addActionListener(e -> choice = "rectangle");
		tglbtnCircle.addActionListener(e -> choice = "circle");
		tglbtnDonut.addActionListener(e -> choice = "donut");
		tglbtnSelect.addActionListener(e -> choice = "select");

		// modify/delete pozivaju controller
		tglbtnModify.addActionListener(e -> {
			if (controller != null) {
				controller.modify();
			} else {
				JOptionPane.showMessageDialog(this, "Controller is null.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			tglbtnSelect.setSelected(true);
			choice = "select";
		});

		tglbtnDelete.addActionListener(e -> {
			if (controller != null) {
				controller.delete();
			} else {
				JOptionPane.showMessageDialog(this, "Controller is null.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			tglbtnSelect.setSelected(true);
			choice = "select";
		});

		// dodavanje na frame
		add(pnlNorth, BorderLayout.NORTH);
		add(view, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);

		// klik na view ide controlleru
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (controller != null)
					controller.mouseClicked(e);
			}
		});
	}

	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	public String getChoice() {
		return choice;
	}

	// getteri za dijaloge (controlleru trebaju)
	public DlgPoint getDlgPoint() {
		return dlgPoint;
	}

	public DlgLine getDlgLine() {
		return dlgLine;
	}

	public DlgRectangle getDlgRectangle() {
		return dlgRectangle;
	}

	public DlgCircle getDlgCircle() {
		return dlgCircle;
	}

	public DlgDonut getDlgDonut() {
		return dlgDonut;
	}
}
