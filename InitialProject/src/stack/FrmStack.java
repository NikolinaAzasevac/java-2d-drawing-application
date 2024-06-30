package stack;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmStack extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	DefaultListModel <Donut> dlm = new DefaultListModel<Donut>();

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
		contentPane.setBackground(new Color(255, 240, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblStack = new JLabel("Stack for donuts");
		lblStack.setForeground(new Color(219, 112, 147));
		lblStack.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		
		JScrollPane scrlPane = new JScrollPane();
		scrlPane.setBorder(new LineBorder(new Color(219, 112, 147), 2));
		
		JList<Donut> lstStack = new JList<Donut>();
		scrlPane.setViewportView(lstStack); //lista povezana sa skrolbarom
		lstStack.setModel(dlm); //postavlja listu na dlm model
		
		JButton btnPush = new JButton("Push");
		btnPush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgStack dlgStack = new DlgStack();
				dlgStack.setVisible(true);
				
				if(dlgStack.isOk()) {
					try {
						int x = Integer.parseInt(dlgStack.getTxtX().getText());
						int y = Integer.parseInt(dlgStack.getTxtY().getText());
						int radius = Integer.parseInt(dlgStack.getTxtRadius().getText());
						int innerRadius = Integer.parseInt(dlgStack.getTxtInnerRadius().getText());
						Donut donut = new Donut(new Point(x,y), radius, innerRadius, false);
						dlm.add(0,donut);
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "Please, insert valid values!", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
	                JOptionPane.showMessageDialog(null, "Operation cancelled.", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
	            }
			}
		});
		btnPush.setForeground(new Color(219, 112, 147));
		btnPush.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		
		JButton btnPop = new JButton("Pop"); 
		btnPop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!dlm.isEmpty()) {
			            Donut donut = dlm.getElementAt(0); 

			            DlgStack dlgStack = new DlgStack();
			            dlgStack.setTitle("Pop donut");

			            dlgStack.getTxtX().setText(Integer.toString(donut.getCenter().getX()));
			            dlgStack.getTxtY().setText(Integer.toString(donut.getCenter().getY()));
			            dlgStack.getTxtRadius().setText(Integer.toString(donut.getRadius()));
			            dlgStack.getTxtInnerRadius().setText(Integer.toString(donut.getInnerRadius()));

			            dlgStack.getTxtX().setEnabled(false);
			            dlgStack.getTxtY().setEnabled(false);
			            dlgStack.getTxtRadius().setEnabled(false);
			            dlgStack.getTxtInnerRadius().setEnabled(false);

			            dlgStack.setVisible(true);

			            if (dlgStack.isOk()) {
			                dlm.removeElementAt(0);
			            } else {
			                JOptionPane.showMessageDialog(null, "Operation cancelled.", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			            }
			        } else {
			            JOptionPane.showMessageDialog(null, "Stack is empty.", "INF0RMATION", JOptionPane.INFORMATION_MESSAGE);
			        }
			    }		
		});
		btnPop.setForeground(new Color(219, 112, 147));
		btnPop.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING) 
			
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(143) //razmak
					.addComponent(lblStack, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(170, Short.MAX_VALUE)) // da bude centralno
				
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(90) 
					.addComponent(scrlPane, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(90, Short.MAX_VALUE)) 
				
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(90) 
					.addComponent(btnPop, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(52) //razmak izmedju dugmadi
					.addComponent(btnPush, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(50))
		);
		
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(lblStack)
					
					.addGap(20)
					.addComponent(scrlPane, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE) //velicina srclpane
					.addGap(25)
				
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
					.addGap(150)
					.addComponent(btnPush) //dodaje se btnAdd
					.addComponent(btnPop))
				.addContainerGap(41, Short.MAX_VALUE)) //dodatni prostor dole
		);
		contentPane.setLayout(gl_contentPane); // postavlja se layout na contentPane
	}
}
