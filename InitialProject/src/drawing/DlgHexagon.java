package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import adapter.HexagonAdapter;

public class DlgHexagon extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtR;
	private boolean confirm;
	private Color areaColor;
	private Color borderColor;
	private JButton btnAreaColor;
	private JButton btnBorderColor;

	public DlgHexagon() {
		setModal(true);
		setTitle("Add or modify hexagon");
		setBounds(100, 100, 450, 280);
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
			JLabel lblCenter = new JLabel("Center of the hexagon:");
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
			gbc_lblY.insets = new Insets(0, 0, 5, 5);
			gbc_lblY.anchor = GridBagConstraints.EAST;
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
			JLabel lblR = new JLabel("Radius:");
			GridBagConstraints gbc_lblR = new GridBagConstraints();
			gbc_lblR.insets = new Insets(0, 0, 5, 5);
			gbc_lblR.anchor = GridBagConstraints.EAST;
			gbc_lblR.gridx = 0;
			gbc_lblR.gridy = 3;
			contentPanel.add(lblR, gbc_lblR);
		}
		{
			txtR = new JTextField();
			GridBagConstraints gbc_txtR = new GridBagConstraints();
			gbc_txtR.insets = new Insets(0, 0, 5, 0);
			gbc_txtR.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtR.gridx = 1;
			gbc_txtR.gridy = 3;
			contentPanel.add(txtR, gbc_txtR);
			txtR.setColumns(10);
		}
		{
			JLabel lblChooseInner = new JLabel("Choose inner:");
			GridBagConstraints gbc_lblChooseInner = new GridBagConstraints();
			gbc_lblChooseInner.insets = new Insets(0, 0, 5, 5);
			gbc_lblChooseInner.anchor = GridBagConstraints.EAST;
			gbc_lblChooseInner.gridx = 0;
			gbc_lblChooseInner.gridy = 4;
			contentPanel.add(lblChooseInner, gbc_lblChooseInner);
		}
		{
			btnAreaColor = new JButton("COLOR");
			btnAreaColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					areaColor = JColorChooser.showDialog(null, "Choose inner color", Color.white);
					if (areaColor != null) {
						btnAreaColor.setBackground(areaColor);
						btnAreaColor.setOpaque(true);
					}
				}
			});
			GridBagConstraints gbc_btnAreaColor = new GridBagConstraints();
			gbc_btnAreaColor.insets = new Insets(0, 0, 5, 0);
			gbc_btnAreaColor.anchor = GridBagConstraints.WEST;
			gbc_btnAreaColor.gridx = 1;
			gbc_btnAreaColor.gridy = 4;
			contentPanel.add(btnAreaColor, gbc_btnAreaColor);
		}
		{
			JLabel lblChooseBorder = new JLabel("Choose border:");
			GridBagConstraints gbc_lblChooseBorder = new GridBagConstraints();
			gbc_lblChooseBorder.insets = new Insets(0, 0, 5, 5);
			gbc_lblChooseBorder.anchor = GridBagConstraints.EAST;
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
			gbc_btnBorderColor.insets = new Insets(0, 0, 5, 0);
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
						try {
							if (txtX.getText().isEmpty() || txtY.getText().isEmpty() || txtR.getText().isEmpty()) {
								JOptionPane.showMessageDialog(null, "Please, enter values. All values are required!",
										"Error message", JOptionPane.ERROR_MESSAGE);
							} else if (Integer.parseInt(txtR.getText()) <= 0 || Integer.parseInt(txtX.getText()) < 0
									|| Integer.parseInt(txtY.getText()) < 0) {
								JOptionPane.showMessageDialog(null, "Values must be greater than 0!", "Error message",
										JOptionPane.ERROR_MESSAGE);
							} else {
								setConfirm(true);
								setVisible(false);
							}
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "Enter numbers only!", "Error",
									JOptionPane.ERROR_MESSAGE);
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

	public void writeHexagon(HexagonAdapter hexagon) {
		txtX.setText(String.valueOf(hexagon.getX()));
		txtY.setText(String.valueOf(hexagon.getY()));
		txtR.setText(String.valueOf(hexagon.getR()));
	}

	public HexagonAdapter makeHexagon() {
		int x = Integer.parseInt(txtX.getText());
		int y = Integer.parseInt(txtY.getText());
		int r = Integer.parseInt(txtR.getText());
		return new HexagonAdapter(x, y, r, borderColor, areaColor);
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public JTextField getTxtR() {
		return txtR;
	}

	public Color getAreaColor() {
		return areaColor;
	}

	public void setAreaColor(Color areaColor) {
		this.areaColor = areaColor;
		if (btnAreaColor != null && areaColor != null) {
			btnAreaColor.setBackground(areaColor);
			btnAreaColor.setOpaque(true);
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
