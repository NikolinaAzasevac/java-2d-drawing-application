package drawing;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class FrmDrawing extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static FrmDrawing frame; // cuva instancu FrmDrawing
	private PnlDrawing pnlDrawing = new PnlDrawing();
	private final ButtonGroup buttonGroup = new ButtonGroup(); // kako bi se iskljucilo medjusobno iskljucivanje dugmadi
    private DlgPoint dlgPoint = new DlgPoint();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new FrmDrawing(); //kreira instancu frmDrawing
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
		//getContentPane().setLayout(new BorderLayout(0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		initializePnlNorth();
        initializePnlSouth();
        initializePnlCenter();
	}
        private void initializePnlNorth() {
        
		JPanel pnlNorth = new JPanel();
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		
		JToggleButton tglbtnPoint = new JToggleButton("Point");
		tglbtnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pnlDrawing.getSelectedShape() != null) {         // proverava jel postoji selektovan obj
					pnlDrawing.getSelectedShape().setSelected(false); // ako postoji skida se 
					pnlDrawing.setSelectedShape(null);
					pnlDrawing.repaint(); // ponovo ga iscrtava
				}
					
				
			}
		});
		buttonGroup.add(tglbtnPoint);
		pnlNorth.add(tglbtnPoint);
		
		JToggleButton tglbtnLine = new JToggleButton("Line");
		buttonGroup.add(tglbtnLine);
		pnlNorth.add(tglbtnLine);
		
		JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
		buttonGroup.add(tglbtnRectangle);
		pnlNorth.add(tglbtnRectangle);
		
		JToggleButton tglbtnCircle = new JToggleButton("Circle");
		buttonGroup.add(tglbtnCircle);
		pnlNorth.add(tglbtnCircle);
		
		JToggleButton tglbtnDonut = new JToggleButton("Donut");
		buttonGroup.add(tglbtnDonut);
		pnlNorth.add(tglbtnDonut);
        }
        
        private void initializePnlSouth() {
        	
		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
		JToggleButton tglbtnSelect = new JToggleButton("Select");
		pnlSouth.add(tglbtnSelect);
		
		JToggleButton tglbtnModify = new JToggleButton("Modify");
		pnlSouth.add(tglbtnModify);
		
		JToggleButton tglbtnDelete = new JToggleButton("Delete");
		pnlSouth.add(tglbtnDelete);
        }
        
        private void initializePnlCenter() {
		//JPanel pnlCenter = new JPanel();
        	
        	pnlDrawing.addMouseListener((MouseListener) new MouseAdapter() {  // kastovano jer nije moglo raditi
    			@Override
    			public void mouseClicked(MouseEvent e) {
    				pnlDrawing.draw(e, frame);
    			}
    		});
    		
    		contentPane.add(pnlDrawing, BorderLayout.CENTER);
		//getContentPane().add(pnlDrawing, BorderLayout.CENTER);
        }

		public static FrmDrawing getFrame() {
			return frame;
		}

		public static void setFrame(FrmDrawing frame) {
			FrmDrawing.frame = frame;
		}

		public DlgPoint getDlgPoint() {
			return dlgPoint;
		}

		public void setDlgPoint(DlgPoint dlgPoint) {
			this.dlgPoint = dlgPoint;
		}    
        
	}


