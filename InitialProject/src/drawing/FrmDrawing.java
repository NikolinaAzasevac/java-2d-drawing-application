package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class FrmDrawing extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PnlDrawing pnlDrawing = new PnlDrawing(); // instancira PnlDrawing koji ce biti panel na kojem moze da se
														// crta
	private JToggleButton tglbtnPoint = new JToggleButton("Point");
	private JToggleButton tglbtnLine = new JToggleButton("Line");
	private JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	private JToggleButton tglbtnCircle = new JToggleButton("Circle");
	private JToggleButton tglbtnDonut = new JToggleButton("Donut");

	ButtonGroup btnGroup = new ButtonGroup();

	private JToggleButton tglbtnSelect = new JToggleButton("Select");
	private JToggleButton tglbtnModify = new JToggleButton("Modify");
	private JToggleButton tglbtnDelete = new JToggleButton("Delete");

	private String choice = "";
	private static FrmDrawing frame;

	private DlgPoint dlgPoint = new DlgPoint();
	private DlgLine dlgLine = new DlgLine();
	private DlgRectangle dlgRectangle = new DlgRectangle();
	private DlgCircle dlgCircle = new DlgCircle();
	private DlgDonut dlgDonut = new DlgDonut();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new FrmDrawing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmDrawing() {
		setTitle("Nikolina Azasevac IT9/2023");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(Color.LIGHT_GRAY);
		contentPane.add(pnlNorth, BorderLayout.NORTH);

		btnGroup.add(tglbtnPoint);
		btnGroup.add(tglbtnLine);
		btnGroup.add(tglbtnRectangle);
		btnGroup.add(tglbtnCircle);
		btnGroup.add(tglbtnDonut);
		btnGroup.add(tglbtnSelect);
		btnGroup.add(tglbtnModify);
		btnGroup.add(tglbtnDelete);
		

		tglbtnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choice = "point";
				if (pnlDrawing.getSelectedShape() != null) {
					// ako je objekat selektovan deselektovace se
					pnlDrawing.getSelectedShape().setSelected(false);
					pnlDrawing.setSelectedShape(null);
					pnlDrawing.repaint();
				}
			}
		});
		pnlNorth.add(tglbtnPoint);

		tglbtnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choice = "line";
				if (pnlDrawing.getSelectedShape() != null) {
					pnlDrawing.getSelectedShape().setSelected(false);
					pnlDrawing.setSelectedShape(null);
					pnlDrawing.repaint();
				}
			}
		});
		pnlNorth.add(tglbtnLine);

		tglbtnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choice = "rectangle";
				if (pnlDrawing.getSelectedShape() != null) {
					pnlDrawing.getSelectedShape().setSelected(false);
					pnlDrawing.setSelectedShape(null);
					pnlDrawing.repaint();
				}
			}
		});
		pnlNorth.add(tglbtnRectangle);

		tglbtnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choice = "circle";
				if (pnlDrawing.getSelectedShape() != null) {
					pnlDrawing.getSelectedShape().setSelected(false);
					pnlDrawing.setSelectedShape(null);
					pnlDrawing.repaint();
				}
			}
		});
		pnlNorth.add(tglbtnCircle);

		tglbtnDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choice = "donut";
				if (pnlDrawing.getSelectedShape() != null) {
					pnlDrawing.getSelectedShape().setSelected(false);
					pnlDrawing.setSelectedShape(null);
					pnlDrawing.repaint();
				}
			}
		});
		pnlNorth.add(tglbtnDonut);

		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(Color.LIGHT_GRAY);
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		tglbtnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choice = "select";
				pnlDrawing.repaint();
			}
		});
		pnlSouth.add(tglbtnSelect);

		tglbtnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// proverava da li je selektovan oblik u pnlDrawing 
				if (pnlDrawing.getSelectedShape() != null) {
					// Ako je selektovano, poziva se modify
					pnlDrawing.modify(frame);
				} else {
					// ako oblik nije selektovan prikazuje se greska
					JOptionPane.showMessageDialog(null,
							"There is no selected shape! Please, select the shape you want to modify.", "Error Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
				// postavlja tglbtnSelect kao selektovano i azurira izbor na select
				tglbtnSelect.setSelected(true);
				choice = "select";
			}
		});
		pnlSouth.add(tglbtnModify);

		pnlSouth.add(tglbtnDelete);

		pnlDrawing.addMouseListener(new MouseAdapter() { // metoda bez koje se ne bi mogla izabrati tacka klikom
			@Override
			public void mouseClicked(MouseEvent e) {
				pnlDrawing.drawing(e, frame);
			}
		});
		contentPane.add(pnlDrawing, BorderLayout.CENTER);
	}

	public PnlDrawing getPnlDrawing() {
		return pnlDrawing;
	}

	public void setPnlDrawing(PnlDrawing pnlDrawing) {
		this.pnlDrawing = pnlDrawing;
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public void setTglbtnDonut(JToggleButton tglbtnDonut) {
		this.tglbtnDonut = tglbtnDonut;
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}

	public void setTglbtnSelect(JToggleButton tglbtnSelect) {
		this.tglbtnSelect = tglbtnSelect;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public DlgPoint getDlgPoint() {
		return dlgPoint;
	}

	public void setDlgPoint(DlgPoint dlgPoint) {
		this.dlgPoint = dlgPoint;
	}

	public static FrmDrawing getFrame() {
		return frame;
	}

	public static void setFrame(FrmDrawing frame) {
		FrmDrawing.frame = frame;
	}

	public DlgLine getDlgLine() {
		return dlgLine;
	}

	public void setDlgLine(DlgLine dlgLine) {
		this.dlgLine = dlgLine;
	}

	public DlgRectangle getDlgRectangle() {
		return dlgRectangle;
	}

	public void setDlgRectangle(DlgRectangle dlgRectangle) {
		this.dlgRectangle = dlgRectangle;
	}

	public DlgCircle getDlgCircle() {
		return dlgCircle;
	}

	public void setDlgCircle(DlgCircle dlgCircle) {
		this.dlgCircle = dlgCircle;
	}

	public DlgDonut getDlgDonut() {
		return dlgDonut;
	}

	public void setDlgDonut(DlgDonut dlgDonut) {
		this.dlgDonut = dlgDonut;
	}

}