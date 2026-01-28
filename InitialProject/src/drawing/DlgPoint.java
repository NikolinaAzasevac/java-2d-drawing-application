package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgPoint extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private Color color;
	private boolean confirm;
	private Point point;
	private JButton btnColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgPoint dialog = new DlgPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgPoint() {
		setModal(true);
		setTitle("Add or modify point");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblX = new JLabel("X coordinate:");
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.insets = new Insets(0, 0, 5, 5);
			gbc_lblX.anchor = GridBagConstraints.EAST;
			gbc_lblX.gridx = 0;
			gbc_lblX.gridy = 0;
			contentPanel.add(lblX, gbc_lblX);
		}
		{
			txtX = new JTextField();
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.insets = new Insets(0, 0, 5, 0);
			gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX.gridx = 1;
			gbc_txtX.gridy = 0;
			contentPanel.add(txtX, gbc_txtX);
			txtX.setColumns(10);
			txtX.setEnabled(false);
		}
		{
			JLabel lblY = new JLabel("Y coordinate:");
			GridBagConstraints gbc_lblY = new GridBagConstraints();
			gbc_lblY.anchor = GridBagConstraints.EAST;
			gbc_lblY.insets = new Insets(0, 0, 5, 5);
			gbc_lblY.gridx = 0;
			gbc_lblY.gridy = 1;
			contentPanel.add(lblY, gbc_lblY);
		}
		{
			txtY = new JTextField();
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.insets = new Insets(0, 0, 5, 0);
			gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY.gridx = 1;
			gbc_txtY.gridy = 1;
			contentPanel.add(txtY, gbc_txtY);
			txtY.setColumns(10);
			txtX.setEnabled(false);
		}
		{
			JLabel lblChoose = new JLabel("Choose:");
			GridBagConstraints gbc_lblChoose = new GridBagConstraints();
			gbc_lblChoose.insets = new Insets(0, 0, 0, 5);
			gbc_lblChoose.gridx = 0;
			gbc_lblChoose.gridy = 2;
			contentPanel.add(lblChoose, gbc_lblChoose);
		}
		{
			btnColor = new JButton("COLOR");
			btnColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					color = JColorChooser.showDialog(null, "Choose color of point", Color.black);
					if (color != null) {
						btnColor.setBackground(color); // postavlja pozadinsku boju dugmeta
						btnColor.setOpaque(true); // omogućava prikaz pozadinske boje
					}

				}
			});
			GridBagConstraints gbc_btnColor = new GridBagConstraints();
			gbc_btnColor.anchor = GridBagConstraints.WEST;
			gbc_btnColor.gridx = 1;
			gbc_btnColor.gridy = 2;
			contentPanel.add(btnColor, gbc_btnColor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						if (txtX.getText().isEmpty() || txtY.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Please enter coordinates!", "Error message",
									JOptionPane.ERROR_MESSAGE);
						} else {

							try {
								if (Integer.parseInt(txtX.getText()) < 0 || Integer.parseInt(txtY.getText()) < 0) {
									JOptionPane.showMessageDialog(null, "Please enter number greater than 0!",
											"Error message", JOptionPane.ERROR_MESSAGE);
								} else {
									confirm = true;
									setVisible(false);

								}
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "Please enter only numbers!", "Error message",
										JOptionPane.ERROR_MESSAGE);

							}
						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel?",
								"Confirm  or cancel", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (confirm == JOptionPane.YES_OPTION) {
							dispose(); // zatvori dijalog ako korisnik potvrdi
						}
						// Ako korisnik izabere no dijalog ostaje otvoren
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

	}

	public void writePoint(Point point) {
		txtX.setText(String.valueOf(point.getX()));
		txtY.setText(String.valueOf(point.getY()));
	}

	public Point makePoint() {
		int x = Integer.parseInt(txtX.getText());
		int y = Integer.parseInt(txtY.getText());
		Point point = new Point(x, y, color);
		return point;
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		if (btnColor != null && color != null) {
			btnColor.setBackground(color);
			btnColor.setOpaque(true);
		}
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

}
