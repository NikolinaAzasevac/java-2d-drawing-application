package drawing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JToggleButton;

public class FrmDrawing extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
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
		getContentPane().setLayout(new BorderLayout(0, 0));
		setTitle("Nikolina Azasevac IT9/2023");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		initializePnlNorth();
        initializePnlSouth();
        initializePnlCenter();
	}
        private void initializePnlNorth() {
        
		JPanel pnlNorth = new JPanel();
		getContentPane().add(pnlNorth, BorderLayout.NORTH);
		
		JToggleButton tglbtnPoint = new JToggleButton("Point");
		pnlNorth.add(tglbtnPoint);
		
		JToggleButton tglbtnLine = new JToggleButton("Line");
		pnlNorth.add(tglbtnLine);
		
		JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
		pnlNorth.add(tglbtnRectangle);
		
		JToggleButton tglbtnCircle = new JToggleButton("Circle");
		pnlNorth.add(tglbtnCircle);
		
		JToggleButton tglbtnDonut = new JToggleButton("Donut");
		pnlNorth.add(tglbtnDonut);
        }
		
        private void initializePnlSouth() {
        	
		JPanel pnlSouth = new JPanel();
		getContentPane().add(pnlSouth, BorderLayout.SOUTH);
		
		JToggleButton tglbtnSelect = new JToggleButton("Select");
		pnlSouth.add(tglbtnSelect);
		
		JToggleButton tglbtnModify = new JToggleButton("Modify");
		pnlSouth.add(tglbtnModify);
		
		JToggleButton tglbtnDelete = new JToggleButton("Delete");
		pnlSouth.add(tglbtnDelete);
        }
        
        private void initializePnlCenter() {
		JPanel pnlCenter = new JPanel();
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
        }
		
	}


