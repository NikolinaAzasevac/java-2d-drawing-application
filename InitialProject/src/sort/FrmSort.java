package sort;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JList;
//import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import geometry.Donut;

public class FrmSort extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	DefaultListModel<Donut>dlm=new DefaultListModel<Donut>();
	ArrayList <Donut> lstSort = new ArrayList<Donut>();
	//private JButton btnAdd = new JButton("Add");
	//private JButton btnSort = new JButton("Sort");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSort frame = new FrmSort();
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
	public FrmSort() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblSort = new JLabel("List for donuts");
		
		JScrollPane scrlPane = new JScrollPane();
		
		JList <Donut>lstSort = new JList<Donut>();
		scrlPane.setViewportView(lstSort);
		lstSort.setModel(dlm);
		
		JButton btnAdd = new JButton("Add");
		JButton btnSort = new JButton("Sort");
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING) 
			
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(143) //razmak
					.addComponent(lblSort, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(170, Short.MAX_VALUE)) // da bude centralno
				
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(90) 
					.addComponent(scrlPane, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(90, Short.MAX_VALUE)) 
				
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(90) 
					.addComponent(btnSort, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(52) //razmak izmedju dugmadi
					.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(50))
		);
		
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(lblSort)
					
					.addGap(20)
					.addComponent(scrlPane, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE) //velicina srclpane
					.addGap(25)
				
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
					.addGap(150)
					.addComponent(btnAdd) //dodaje se btnAdd
					.addComponent(btnSort))
				.addContainerGap(41, Short.MAX_VALUE)) //dodatni prostor dole
		);
		
	}
}
