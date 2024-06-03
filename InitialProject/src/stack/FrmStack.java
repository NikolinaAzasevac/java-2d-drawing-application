package stack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Donut;

import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JLabel;

public class FrmStack extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultListModel<Donut> dlm= new DefaultListModel<Donut>();
	private JList<Donut> lstStack = new JList<>(dlm); 
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

	
	//commit
	/**
	 * Create the frame.
	 */
	public FrmStack() {
		setTitle("Azasevac Nikolina IT9/2023");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblAppTitle = new JLabel("Donuts on Stack");
		GridBagConstraints gbc_lblAppTitle = new GridBagConstraints();
		gbc_lblAppTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblAppTitle.gridx = 5;
		gbc_lblAppTitle.gridy = 1;
		contentPane.add(lblAppTitle, gbc_lblAppTitle);
		
		JList lstStack = new JList();
		GridBagConstraints gbc_lstStack = new GridBagConstraints();
		gbc_lstStack.gridheight = 4;
		gbc_lstStack.gridwidth = 5;
		gbc_lstStack.insets = new Insets(0, 0, 5, 5);
		gbc_lstStack.fill = GridBagConstraints.BOTH;
		gbc_lstStack.gridx = 3;
		gbc_lstStack.gridy = 4;
		contentPane.add(lstStack, gbc_lstStack);
		
		JButton btnAdd = new JButton("Add ");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.gridheight = 3;
		gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd.gridx = 3;
		gbc_btnAdd.gridy = 8;
		contentPane.add(btnAdd, gbc_btnAdd);
		
		JButton btnRemove = new JButton("Remove ");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.gridheight = 4;
		gbc_btnRemove.insets = new Insets(0, 0, 0, 5);
		gbc_btnRemove.gridx = 7;
		gbc_btnRemove.gridy = 8;
		contentPane.add(btnRemove, gbc_btnRemove);
	}

}
