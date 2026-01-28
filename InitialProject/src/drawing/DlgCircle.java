package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgCircle extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	private Circle circle;
	private boolean confirm;
	private Color color;
	private Color borderColor;
	private JButton btnInnerColor;
	private JButton btnBorderColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCircle dialog = new DlgCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCircle() {
		setModal(true);
		setTitle("Add or modify circle");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblCenter = new JLabel("Center of the circle:");
			GridBagConstraints gbc_lblCenter = new GridBagConstraints();
			gbc_lblCenter.insets = new Insets(0, 0, 5, 5);
			gbc_lblCenter.gridx = 0;
			gbc_lblCenter.gridy = 0;
			contentPanel.add(lblCenter, gbc_lblCenter);
		}
		{
			JLabel lblX = new JLabel("X coordinate:");
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.insets = new Insets(0, 0, 5, 5);
			gbc_lblX.anchor = GridBagConstraints.EAST;
			gbc_lblX.gridx = 0;
			gbc_lblX.gridy = 1;
			contentPanel.add(lblX, gbc_lblX);
		}
		{
			txtX = new JTextField();
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.insets = new Insets(0, 0, 5, 0);
			gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX.gridx = 1;
			gbc_txtX.gridy = 1;
			contentPanel.add(txtX, gbc_txtX);
			txtX.setColumns(10);
		}
		{
			JLabel lblY = new JLabel("Y coordinate:");
			GridBagConstraints gbc_lblY = new GridBagConstraints();
			gbc_lblY.anchor = GridBagConstraints.EAST;
			gbc_lblY.insets = new Insets(0, 0, 5, 5);
			gbc_lblY.gridx = 0;
			gbc_lblY.gridy = 2;
			contentPanel.add(lblY, gbc_lblY);
		}
		{
			txtY = new JTextField();
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.insets = new Insets(0, 0, 5, 0);
			gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY.gridx = 1;
			gbc_txtY.gridy = 2;
			contentPanel.add(txtY, gbc_txtY);
			txtY.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Radius:");
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.anchor = GridBagConstraints.EAST;
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 0;
			gbc_lblRadius.gridy = 3;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			txtRadius = new JTextField();
			GridBagConstraints gbc_txtRadius = new GridBagConstraints();
			gbc_txtRadius.insets = new Insets(0, 0, 5, 0);
			gbc_txtRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtRadius.gridx = 1;
			gbc_txtRadius.gridy = 3;
			contentPanel.add(txtRadius, gbc_txtRadius);
			txtRadius.setColumns(10);
		}
		{
			JLabel lblChooseInner = new JLabel("Choose inner: ");
			GridBagConstraints gbc_lblChooseInner = new GridBagConstraints();
			gbc_lblChooseInner.insets = new Insets(0, 0, 5, 5);
			gbc_lblChooseInner.gridx = 0;
			gbc_lblChooseInner.gridy = 4;
			contentPanel.add(lblChooseInner, gbc_lblChooseInner);
		}
		{
			btnInnerColor = new JButton("COLOR");
			btnInnerColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					color = JColorChooser.showDialog(null, "Choose inner color", Color.white);
					if (color != null) {
						btnInnerColor.setBackground(color); 
						btnInnerColor.setOpaque(true); 
					}
				}
			});
			GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
			gbc_btnInnerColor.insets = new Insets(0, 0, 5, 0);
			gbc_btnInnerColor.anchor = GridBagConstraints.WEST;
			gbc_btnInnerColor.gridx = 1;
			gbc_btnInnerColor.gridy = 4;
			contentPanel.add(btnInnerColor, gbc_btnInnerColor);
		}
		{
			JLabel lblChooseBorder = new JLabel("Choose border:");
			GridBagConstraints gbc_lblChooseBorder = new GridBagConstraints();
			gbc_lblChooseBorder.insets = new Insets(0, 0, 0, 5);
			gbc_lblChooseBorder.gridx = 0;
			gbc_lblChooseBorder.gridy = 5;
			contentPanel.add(lblChooseBorder, gbc_lblChooseBorder);
		}
		{
			btnBorderColor = new JButton("COLOR");
			btnBorderColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					borderColor = JColorChooser.showDialog(null, "Choose border color", Color.black);
					if (borderColor != null) {
						btnBorderColor.setBackground(borderColor); 
						btnBorderColor.setOpaque(true); 
					}

				}
			});
			GridBagConstraints gbc_btnBorderColor = new GridBagConstraints();
			gbc_btnBorderColor.anchor = GridBagConstraints.WEST;
			gbc_btnBorderColor.gridx = 1;
			gbc_btnBorderColor.gridy = 5;
			contentPanel.add(btnBorderColor, gbc_btnBorderColor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txtX.getText().isEmpty() || txtY.getText().isEmpty() || txtRadius.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Please, enter values.All fields are required!",
									"Error message", JOptionPane.ERROR_MESSAGE);
						} else {
							try {
								if (Integer.parseInt(txtRadius.getText()) <= 0 || Integer.parseInt(txtX.getText()) < 0
										|| Integer.parseInt(txtY.getText()) < 0) {
									JOptionPane.showMessageDialog(null, "Enter a number greater than 0!",
											"Error message", JOptionPane.ERROR_MESSAGE);
								} else {
									setConfirm(true);
									setVisible(false);
								}
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, "Enter numbers!", "Error",
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
								"Confirm or cancel", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (confirm == JOptionPane.YES_OPTION) {
							dispose(); 
						}
						
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void writeCircle(Circle circle) { // za ispis vrednosti
		txtX.setText(String.valueOf(circle.getCenter().getX()));
		txtY.setText(String.valueOf(circle.getCenter().getY()));
		txtRadius.setText(String.valueOf(circle.getRadius()));
	}

	public Circle makeCircle() { // pravljenje obj pomoci prethodnih vrednosti
		int x = Integer.parseInt(txtX.getText());
		int y = Integer.parseInt(txtY.getText());
		int radius = Integer.parseInt(txtRadius.getText());
		Circle circle = new Circle(new Point(x, y), radius, color, borderColor);
		return circle;
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

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		if (btnInnerColor != null && color != null) {
			btnInnerColor.setBackground(color);
			btnInnerColor.setOpaque(true);
		}
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
		if (btnBorderColor != null && borderColor != null) {
			btnBorderColor.setBackground(borderColor);
			btnBorderColor.setOpaque(true);
		}
	}
}
