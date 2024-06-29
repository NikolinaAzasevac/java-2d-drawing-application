package stack;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import geometry.Donut;
import geometry.Point;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmStack extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	DefaultListModel <Donut> dlm = new DefaultListModel<Donut>();
	private JButton btnAdd = new JButton("Add");
	private JButton btnRemove = new JButton("Remove"); 
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmStack frame = new FrmStack();
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
	public FrmStack() {
		setTitle("Nikolina Azasevac IT9/2023");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblStack = new JLabel("Stack for donuts");
		
		JScrollPane scrlPane = new JScrollPane();
		
		JList<Donut> lstStack = new JList<Donut>();
		scrlPane.setViewportView(lstStack);
		lstStack.setModel(dlm);
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgStack dlgStack = new DlgStack();
				dlgStack.setVisible(true);
				
				if(dlgStack.isConfirmed()) {
					try {
						int x = Integer.parseInt(dlgStack.getTxtX().getText());
						int y = Integer.parseInt(dlgStack.getTxtY().getText());
						int radius = Integer.parseInt(dlgStack.getTxtRadius().getText());
						int innerRadius = Integer.parseInt(dlgStack.getTxtInnerRadius().getText());
						Donut donut = new Donut(new Point(x,y), radius, innerRadius, false);
						dlm.add(0, donut);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Please, insert valid values!");
					}
				}
			}
		});
		
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!dlm.isEmpty()) {
					dlm.removeElementAt(0);
				}else {
					JOptionPane.showMessageDialog(null, "Stack is empty, please enter a value!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(339, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(scrlPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(97))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lstStack, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addGap(33))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAdd)
					.addContainerGap(317, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnRemove)
					.addContainerGap(317, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(178)
					.addComponent(lblStack)
					.addContainerGap(201, Short.MAX_VALUE))
		);
		
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblStack)
					.addGap(9)
					.addComponent(btnAdd)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lstStack, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(btnRemove)
					.addGap(9)
					.addComponent(scrlPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(141, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
