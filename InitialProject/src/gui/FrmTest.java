package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JToggleButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmTest extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private DefaultListModel dlm= new DefaultListModel();
	private JTextField txtUnosBoje;
	private DlgTest dialog = new DlgTest();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmTest frame = new FrmTest(); //pozvan konnstr
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
	public FrmTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //metode kad x stisnemo da se zatvori
		setBounds(100, 100, 450, 300); //za velicinu frejma
		setTitle("Nikolina Azasevac"); 
		contentPane = new JPanel(); //instanciramo jpanel odnosno pozivamo ga
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); //ivice panela

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		GridBagLayout gbl_pnlCenter = new GridBagLayout();
		gbl_pnlCenter.columnWidths = new int[]{0, 0, 0, 0};
		gbl_pnlCenter.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_pnlCenter.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlCenter.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		pnlCenter.setLayout(gbl_pnlCenter);
		
		JToggleButton tglbtnCrvena = new JToggleButton("Crvena");
		tglbtnCrvena.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dlm.addElement(tglbtnCrvena.getText());
			}
		});
		buttonGroup.add(tglbtnCrvena);
		GridBagConstraints gbc_tglbtnCrvena = new GridBagConstraints();
		gbc_tglbtnCrvena.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnCrvena.gridx = 0;
		gbc_tglbtnCrvena.gridy = 0;
		pnlCenter.add(tglbtnCrvena, gbc_tglbtnCrvena);
		
		JLabel lblCrvena = new JLabel("Crvena");
		GridBagConstraints gbc_lblCrvena = new GridBagConstraints();
		gbc_lblCrvena.anchor = GridBagConstraints.EAST;
		gbc_lblCrvena.insets = new Insets(0, 0, 5, 5);
		gbc_lblCrvena.gridx = 1;
		gbc_lblCrvena.gridy = 0;
		pnlCenter.add(lblCrvena, gbc_lblCrvena);
		
		JToggleButton tglbtnPlava = new JToggleButton("Plava");
		tglbtnPlava.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dlm.addElement(tglbtnPlava.getText());
			}
		});
		
		txtUnosBoje = new JTextField();
		txtUnosBoje.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					dlm.addElement(txtUnosBoje.getText());
					txtUnosBoje.setText("");
				}
			}
		});
		GridBagConstraints gbc_txtUnosBoje = new GridBagConstraints();
		gbc_txtUnosBoje.insets = new Insets(0, 0, 5, 0);
		gbc_txtUnosBoje.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUnosBoje.gridx = 2;
		gbc_txtUnosBoje.gridy = 0;
		pnlCenter.add(txtUnosBoje, gbc_txtUnosBoje);
		txtUnosBoje.setColumns(10);
		buttonGroup.add(tglbtnPlava);
		GridBagConstraints gbc_tglbtnPlava = new GridBagConstraints();
		gbc_tglbtnPlava.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnPlava.gridx = 0;
		gbc_tglbtnPlava.gridy = 1;
		pnlCenter.add(tglbtnPlava, gbc_tglbtnPlava);
		
		JLabel lblPlava = new JLabel("Plava");
		GridBagConstraints gbc_lblPlava = new GridBagConstraints();
		gbc_lblPlava.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlava.gridx = 1;
		gbc_lblPlava.gridy = 1;
		pnlCenter.add(lblPlava, gbc_lblPlava);
		
		JToggleButton tglbtnZuta = new JToggleButton("Zuta");
		buttonGroup.add(tglbtnZuta);
		GridBagConstraints gbc_tglbtnZuta = new GridBagConstraints();
		gbc_tglbtnZuta.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnZuta.gridx = 0;
		gbc_tglbtnZuta.gridy = 2;
		pnlCenter.add(tglbtnZuta, gbc_tglbtnZuta);
		
		JLabel lblZuta = new JLabel("Zuta");
		lblZuta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dlm.addElement(tglbtnZuta.getText());
			}
		});
		GridBagConstraints gbc_lblZuta = new GridBagConstraints();
		gbc_lblZuta.insets = new Insets(0, 0, 5, 5);
		gbc_lblZuta.gridx = 1;
		gbc_lblZuta.gridy = 2;
		pnlCenter.add(lblZuta, gbc_lblZuta);
		
		JComboBox<String> cmbDodatneBoje = new JComboBox<String>();
		cmbDodatneBoje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlm.addElement(cmbDodatneBoje.getSelectedItem().toString());
			}
		});
		cmbDodatneBoje.setModel(new DefaultComboBoxModel(new String[] {"narandzasta", "ljubicasta", "zelena"}));
		GridBagConstraints gbc_cmbDodatneBoje = new GridBagConstraints();
		gbc_cmbDodatneBoje.insets = new Insets(0, 0, 0, 5);
		gbc_cmbDodatneBoje.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbDodatneBoje.gridx = 0;
		gbc_cmbDodatneBoje.gridy = 3;
		pnlCenter.add(cmbDodatneBoje, gbc_cmbDodatneBoje);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 3;
		pnlCenter.add(scrollPane, gbc_scrollPane);
		
		JList lstNoveBoje = new JList();
		scrollPane.setViewportView(lstNoveBoje);
		lstNoveBoje.setModel(dlm);
		
		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
		JButton btnIspisi = new JButton("Antistres dugme :)");
		btnIspisi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Antistres dugme", "Poruka", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		pnlSouth.add(btnIspisi);
		
		JButton btnNovaBoja = new JButton("Dodaj boju");
		btnNovaBoja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dialog.setVisible(true);
				if(dialog.isOk==true) {
					dlm.addElement(dialog.txtRed.getText()+ " " + dialog.txtGreen.getText()+ " " + dialog.txtBlue.getText());
					pnlSouth.setBackground(new Color(
							Integer.parseInt(dialog.txtRed.getText()),
							Integer.parseInt(dialog.txtGreen.getText()),
							Integer.parseInt(dialog.txtBlue.getText())
							));
				}
			}
		});
		pnlSouth.add(btnNovaBoja);
		//promena za commit 
		
	}

}
