package sort;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import geometry.Donut;
import geometry.Point;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmSort extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	DefaultListModel<Donut> dlm = new DefaultListModel<Donut>();
	ArrayList<Donut> listSort = new ArrayList<Donut>();

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
		setTitle("Nikolina Azasevac IT9/2023");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		JLabel lblSort = new JLabel("List for donuts");
		lblSort.setForeground(new Color(153, 102, 255));
		lblSort.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));

		JScrollPane scrlPane = new JScrollPane();
		scrlPane.setBorder(new LineBorder(new Color(153, 102, 255), 2));

		JList<Donut> lstSort = new JList<Donut>();
		scrlPane.setViewportView(lstSort);
		lstSort.setModel(dlm);

		JButton btnAdd = new JButton("Add");
		btnAdd.setForeground(new Color(153, 102, 255));
		btnAdd.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgSort dlgSort = new DlgSort();
				dlgSort.setVisible(true);
				if (dlgSort.isOk()) {
					try {
						int x = Integer.parseInt(dlgSort.getTxtX1().getText());
						int y = Integer.parseInt(dlgSort.getTxtY1().getText());
						int radius = Integer.parseInt(dlgSort.getTxtRadius1().getText());
						int innerRadius = Integer.parseInt(dlgSort.getTxtInnerRadius1().getText());
						Donut donut = new Donut(new Point(x, y), radius, innerRadius, false);
						dlm.addElement(donut);
						listSort.add(donut);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Please, insert valid numeric values!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Operation cancelled.", "INFORMATION",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		JButton btnSort = new JButton("Sort");
		btnSort.setForeground(new Color(153, 102, 255));
		btnSort.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dlm.isEmpty()) {
					JOptionPane.showMessageDialog(null, "List is empty, please enter values!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else {

					listSort.sort(Comparator.comparingDouble(Donut::area).reversed());
					dlm.clear();
					dlm.addAll(listSort);

				}
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)

				.addGroup(gl_contentPane.createSequentialGroup().addGap(143) // razmak
						.addComponent(lblSort, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(170, Short.MAX_VALUE)) // da bude centralno

				.addGroup(gl_contentPane.createSequentialGroup().addGap(90)
						.addComponent(scrlPane, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(90, Short.MAX_VALUE))

				.addGroup(gl_contentPane.createSequentialGroup().addGap(90)
						.addComponent(btnSort, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE).addGap(52) // razmak
																														// izmedju
																														// dugmadi
						.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE).addGap(50)));

		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(10).addComponent(lblSort)

						.addGap(20).addComponent(scrlPane, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE) // velicina
																														// srclpane
						.addGap(25)

						.addGroup(
								gl_contentPane.createParallelGroup(Alignment.BASELINE).addGap(150).addComponent(btnAdd) // dodaje
																														// se
																														// btnAdd
										.addComponent(btnSort))
						.addContainerGap(41, Short.MAX_VALUE)) // dodatni prostor dole
		);
		contentPane.setLayout(gl_contentPane); // postavlja se layout na contentPane

	}
}
