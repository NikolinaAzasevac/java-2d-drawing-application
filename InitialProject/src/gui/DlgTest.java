package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgTest extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	protected JTextField txtRed;
	protected JTextField txtGreen;
	protected JTextField txtBlue;
	protected boolean isOk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgTest dialog = new DlgTest();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//fjjdsjs

	/**
	 * Create the dialog.
	 */
	public DlgTest() {
		setBounds(100, 100, 450, 300);
		setTitle("Kreiranje nove boje");
		setModal(true); // postavljanje modalnosti dijaloga
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblRed = new JLabel("R");
			GridBagConstraints gbc_lblRed = new GridBagConstraints();
			gbc_lblRed.insets = new Insets(0, 0, 5, 5);
			gbc_lblRed.gridx = 4;
			gbc_lblRed.gridy = 3;
			contentPanel.add(lblRed, gbc_lblRed);
		}
		{
			txtRed = new JTextField();
			GridBagConstraints gbc_txtRed = new GridBagConstraints();
			gbc_txtRed.insets = new Insets(0, 0, 5, 0);
			gbc_txtRed.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtRed.gridx = 5;
			gbc_txtRed.gridy = 4;
			contentPanel.add(txtRed, gbc_txtRed);
			txtRed.setColumns(10);
		}
		{
			JLabel lblGreen = new JLabel("G");
			GridBagConstraints gbc_lblGreen = new GridBagConstraints();
			gbc_lblGreen.insets = new Insets(0, 0, 5, 5);
			gbc_lblGreen.gridx = 4;
			gbc_lblGreen.gridy = 4;
			contentPanel.add(lblGreen, gbc_lblGreen);
		}
		{
			txtGreen = new JTextField();
			GridBagConstraints gbc_txtGreen = new GridBagConstraints();
			gbc_txtGreen.insets = new Insets(0, 0, 5, 0);
			gbc_txtGreen.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtGreen.gridx = 5;
			gbc_txtGreen.gridy = 3;
			contentPanel.add(txtGreen, gbc_txtGreen);
			txtGreen.setColumns(10);
		}
		{
			JLabel lblBlue = new JLabel("B");
			GridBagConstraints gbc_lblBlue = new GridBagConstraints();
			gbc_lblBlue.insets = new Insets(0, 0, 0, 5);
			gbc_lblBlue.gridx = 4;
			gbc_lblBlue.gridy = 5;
			contentPanel.add(lblBlue, gbc_lblBlue);
		}
		{
			txtBlue = new JTextField();
			GridBagConstraints gbc_txtBlue = new GridBagConstraints();
			gbc_txtBlue.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtBlue.gridx = 5;
			gbc_txtBlue.gridy = 5;
			contentPanel.add(txtBlue, gbc_txtBlue);
			txtBlue.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						isOk=true;
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
