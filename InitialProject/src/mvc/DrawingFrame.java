package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;

import javax.swing.JColorChooser;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JFileChooser;
import java.awt.FlowLayout;

import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import observer.Observer;

public class DrawingFrame extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;

	private final DrawingView view = new DrawingView();
	private DrawingController controller;
	private DrawingModel model;

	private final ButtonGroup btnGroup = new ButtonGroup();

	private final JToggleButton tglbtnPoint = new JToggleButton("Point");
	private final JToggleButton tglbtnLine = new JToggleButton("Line");
	private final JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	private final JToggleButton tglbtnCircle = new JToggleButton("Circle");
	private final JToggleButton tglbtnDonut = new JToggleButton("Donut");
	private final JToggleButton tglbtnHexagon = new JToggleButton("Hexagon");

	private final JToggleButton tglbtnSelect = new JToggleButton("Select");
	private final JToggleButton tglbtnModify = new JToggleButton("Modify");
	private final JToggleButton tglbtnDelete = new JToggleButton("Delete");

	private final JButton btnBorderColor = new JButton("Border");
	private final JButton btnFillColor = new JButton("Fill");

	private final JButton btnUndo = new JButton("Undo");
	private final JButton btnRedo = new JButton("Redo");
	private final JButton btnToFront = new JButton("To Front");
	private final JButton btnToBack = new JButton("To Back");
	private final JButton btnBringToFront = new JButton("Bring To Front");
	private final JButton btnBringToBack = new JButton("Bring To Back");
	private final JButton btnSaveLog = new JButton("Save Log");
	private final JButton btnLoadLog = new JButton("Load Log");
	private final JButton btnSaveDrawing = new JButton("Save Drawing");
	private final JButton btnLoadDrawing = new JButton("Load Drawing");

	private final JTextArea txtLog = new JTextArea(30, 30);

	private String choice = "point";

	private final DlgPoint dlgPoint = new DlgPoint();
	private final DlgLine dlgLine = new DlgLine();
	private final DlgRectangle dlgRectangle = new DlgRectangle();
	private final DlgCircle dlgCircle = new DlgCircle();
	private final DlgDonut dlgDonut = new DlgDonut();
	private final DlgHexagon dlgHexagon = new DlgHexagon();

	public DrawingFrame() {
		setTitle("Nikolina Azasevac IT9/2023");
		setResizable(true);

		getContentPane().setLayout(new BorderLayout());

		JPanel pnlNorth = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 5));
		pnlNorth.setBackground(new Color(255, 224, 250));
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		pnlSouth.setBackground(new Color(255, 224, 250));
		JPanel pnlSelection = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlSelection.setBackground(new Color(255, 224, 250));
		JPanel pnlOrder = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlOrder.setBackground(new Color(255, 224, 250));
		JPanel pnlFile = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlFile.setBackground(new Color(255, 224, 250));
		JPanel pnlColors = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlColors.setBackground(new Color(255, 224, 250));
		JLabel lblNorthSeparator = new JLabel("|");
		lblNorthSeparator.setForeground(new Color(230, 170, 205));
		JLabel lblSouthSeparatorOne = new JLabel("|");
		lblSouthSeparatorOne.setForeground(new Color(230, 170, 205));
		JLabel lblSouthSeparatorTwo = new JLabel("|");
		lblSouthSeparatorTwo.setForeground(new Color(230, 170, 205));
		JLabel lblSouthSeparatorThree = new JLabel("|");
		lblSouthSeparatorThree.setForeground(new Color(230, 170, 205));

		JPanel pnlEast = new JPanel(new BorderLayout());
		pnlEast.setBackground(new Color(255, 224, 250));
		txtLog.setEditable(false);
		JScrollPane logScroll = new JScrollPane(txtLog);
		logScroll.setPreferredSize(new Dimension(320, 0));
		pnlEast.add(logScroll, BorderLayout.CENTER);

		// grupisanje
		btnGroup.add(tglbtnPoint);
		btnGroup.add(tglbtnLine);
		btnGroup.add(tglbtnRectangle);
		btnGroup.add(tglbtnCircle);
		btnGroup.add(tglbtnDonut);
		btnGroup.add(tglbtnHexagon);
		btnGroup.add(tglbtnSelect);
		btnGroup.add(tglbtnModify);
		btnGroup.add(tglbtnDelete);

		// boje teksta
		tglbtnPoint.setForeground(Color.PINK);
		tglbtnLine.setForeground(Color.PINK);
		tglbtnRectangle.setForeground(Color.PINK);
		tglbtnCircle.setForeground(Color.PINK);
		tglbtnDonut.setForeground(Color.PINK);
		tglbtnHexagon.setForeground(Color.PINK);
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
		pnlNorth.add(tglbtnHexagon);
		pnlNorth.add(lblNorthSeparator);
		pnlNorth.add(btnUndo);
		pnlNorth.add(btnRedo);

		// south
		pnlSelection.add(tglbtnSelect);
		pnlSelection.add(tglbtnModify);
		pnlSelection.add(tglbtnDelete);
		pnlOrder.add(btnToFront);
		pnlOrder.add(btnToBack);
		pnlOrder.add(btnBringToFront);
		pnlOrder.add(btnBringToBack);
		pnlFile.add(btnSaveLog);
		pnlFile.add(btnLoadLog);
		pnlFile.add(btnSaveDrawing);
		pnlFile.add(btnLoadDrawing);
		pnlColors.add(btnBorderColor);
		pnlColors.add(btnFillColor);
		pnlSouth.add(pnlSelection);
		pnlSouth.add(lblSouthSeparatorOne);
		pnlSouth.add(pnlOrder);
		pnlSouth.add(lblSouthSeparatorTwo);
		pnlSouth.add(pnlFile);
		pnlSouth.add(lblSouthSeparatorThree);
		pnlSouth.add(pnlColors);

		// akcije - samo postavljaju choice
		tglbtnPoint.addActionListener(e -> choice = "point");
		tglbtnLine.addActionListener(e -> choice = "line");
		tglbtnRectangle.addActionListener(e -> choice = "rectangle");
		tglbtnCircle.addActionListener(e -> choice = "circle");
		tglbtnDonut.addActionListener(e -> choice = "donut");
		tglbtnHexagon.addActionListener(e -> choice = "hexagon");
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

		btnBorderColor.addActionListener(e -> {
			Color current = model != null ? model.getActiveBorderColor() : Color.BLACK;
			Color chosen = JColorChooser.showDialog(this, "Choose border color", current);
			if (chosen != null) {
				if (model != null) {
					model.setActiveBorderColor(chosen);
				}
				updateColorButtons();
			}
		});

		btnFillColor.addActionListener(e -> {
			Color current = model != null ? model.getActiveFillColor() : Color.WHITE;
			Color chosen = JColorChooser.showDialog(this, "Choose fill color", current);
			if (chosen != null) {
				if (model != null) {
					model.setActiveFillColor(chosen);
				}
				updateColorButtons();
			}
		});

		btnUndo.addActionListener(e -> {
			if (controller != null)
				controller.undo();
		});

		btnRedo.addActionListener(e -> {
			if (controller != null)
				controller.redo();
		});

		btnToFront.addActionListener(e -> {
			if (controller != null)
				controller.toFront();
		});

		btnToBack.addActionListener(e -> {
			if (controller != null)
				controller.toBack();
		});

		btnBringToFront.addActionListener(e -> {
			if (controller != null)
				controller.bringToFront();
		});

		btnBringToBack.addActionListener(e -> {
			if (controller != null)
				controller.bringToBack();
		});

		btnSaveLog.addActionListener(e -> {
			if (controller == null) {
				return;
			}

			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				controller.saveLog(fileChooser.getSelectedFile().getAbsolutePath());
			}
		});

		btnLoadLog.addActionListener(e -> {
			if (controller == null) {
				return;
			}

			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				controller.loadLog(fileChooser.getSelectedFile().getAbsolutePath());
			}
		});

		btnSaveDrawing.addActionListener(e -> {
			if (controller == null) {
				return;
			}

			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				controller.saveDrawing(fileChooser.getSelectedFile().getAbsolutePath());
			}
		});

		btnLoadDrawing.addActionListener(e -> {
			if (controller == null) {
				return;
			}

			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				controller.loadDrawing(fileChooser.getSelectedFile().getAbsolutePath());
			}
		});

		updateColorButtons();
		updateUndoRedoButtons(false, false);
		updateZOrderButtons(false, false, false, false);

		// dodavanje na frame
		add(pnlNorth, BorderLayout.NORTH);
		add(view, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
		add(pnlEast, BorderLayout.EAST);

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

	public void setModel(DrawingModel model) {
		this.model = model;
		updateColorButtons();
		updateUndoRedoButtons(false, false);
		refreshLog();
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
	
	public DlgHexagon getDlgHexagon() {
		return dlgHexagon;
	}

	public void refreshActiveColors() {
		updateColorButtons();
	}

	public void refreshLog() {
		if (model == null)
			return;
		StringBuilder sb = new StringBuilder();
		for (String entry : model.getLogEntries()) {
			sb.append(entry).append("\n");
		}
		txtLog.setText(sb.toString());
		txtLog.setCaretPosition(txtLog.getDocument().getLength());
	}

	private void updateColorButtons() {
		Color border = model != null ? model.getActiveBorderColor() : Color.BLACK;
		Color fill = model != null ? model.getActiveFillColor() : Color.WHITE;
		btnBorderColor.setBackground(border);
		btnBorderColor.setOpaque(true);
		btnFillColor.setBackground(fill);
		btnFillColor.setOpaque(true);
	}

	public void updateUndoRedoButtons(boolean canUndo, boolean canRedo) {
		btnUndo.setEnabled(canUndo);
		btnRedo.setEnabled(canRedo);
	}

	public void updateZOrderButtons(boolean canToFront, boolean canToBack, boolean canBringToFront,
			boolean canBringToBack) {
		btnToFront.setEnabled(canToFront);
		btnToBack.setEnabled(canToBack);
		btnBringToFront.setEnabled(canBringToFront);
		btnBringToBack.setEnabled(canBringToBack);
	}

	@Override
	public void update(int selectedCount) {
		tglbtnDelete.setEnabled(selectedCount > 0);
		tglbtnModify.setEnabled(selectedCount == 1);

		if (model == null || selectedCount != 1) {
			updateZOrderButtons(false, false, false, false);
			return;
		}

		int selectedIndex = -1;
		int lastIndex = model.getShapes().size() - 1;
		for (int i = 0; i < model.getShapes().size(); i++) {
			if (model.getShapes().get(i).isSelected()) {
				selectedIndex = i;
				break;
			}
		}

		if (selectedIndex == -1) {
			updateZOrderButtons(false, false, false, false);
			return;
		}

		updateZOrderButtons(selectedIndex < lastIndex, selectedIndex > 0, selectedIndex < lastIndex, selectedIndex > 0);
	}

}
