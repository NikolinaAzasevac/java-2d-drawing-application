package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Line;
import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgLine extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtX1;
	private JTextField txtY1;
	private JTextField txtX2;
	private JTextField txtY2;
	private Color color;
	private boolean confirm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setModal(true);
		setTitle("Add or modify line");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblStartPoint = new JLabel("Start point:");
			GridBagConstraints gbc_lblStartPoint = new GridBagConstraints();
			gbc_lblStartPoint.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblStartPoint.insets = new Insets(0, 0, 5, 5);
			gbc_lblStartPoint.gridx = 0;
			gbc_lblStartPoint.gridy = 0;
			contentPanel.add(lblStartPoint, gbc_lblStartPoint);
		}
		{
			JLabel lblX1 = new JLabel("X coordinate:");
			GridBagConstraints gbc_lblX1 = new GridBagConstraints();
			gbc_lblX1.insets = new Insets(0, 0, 5, 5);
			gbc_lblX1.anchor = GridBagConstraints.EAST;
			gbc_lblX1.gridx = 0;
			gbc_lblX1.gridy = 1;
			contentPanel.add(lblX1, gbc_lblX1);
		}
		{
			txtX1 = new JTextField();
			GridBagConstraints gbc_txtX1 = new GridBagConstraints();
			gbc_txtX1.insets = new Insets(0, 0, 5, 0);
			gbc_txtX1.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX1.gridx = 1;
			gbc_txtX1.gridy = 1;
			contentPanel.add(txtX1, gbc_txtX1);
			txtX1.setColumns(10);
		}
		{
			JLabel lblY1 = new JLabel("Y coordinate:");
			GridBagConstraints gbc_lblY1 = new GridBagConstraints();
			gbc_lblY1.anchor = GridBagConstraints.EAST;
			gbc_lblY1.insets = new Insets(0, 0, 5, 5);
			gbc_lblY1.gridx = 0;
			gbc_lblY1.gridy = 2;
			contentPanel.add(lblY1, gbc_lblY1);
		}
		{
			txtY1 = new JTextField();
			GridBagConstraints gbc_txtY1 = new GridBagConstraints();
			gbc_txtY1.insets = new Insets(0, 0, 5, 0);
			gbc_txtY1.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY1.gridx = 1;
			gbc_txtY1.gridy = 2;
			contentPanel.add(txtY1, gbc_txtY1);
			txtY1.setColumns(10);
		}
		{
			JLabel lblEndPoint = new JLabel("End Point:");
			GridBagConstraints gbc_lblEndPoint = new GridBagConstraints();
			gbc_lblEndPoint.anchor = GridBagConstraints.WEST;
			gbc_lblEndPoint.insets = new Insets(0, 0, 5, 5);
			gbc_lblEndPoint.gridx = 0;
			gbc_lblEndPoint.gridy = 3;
			contentPanel.add(lblEndPoint, gbc_lblEndPoint);
		}
		{
			JLabel lblX2 = new JLabel("X coordinate:");
			GridBagConstraints gbc_lblX2 = new GridBagConstraints();
			gbc_lblX2.anchor = GridBagConstraints.EAST;
			gbc_lblX2.insets = new Insets(0, 0, 5, 5);
			gbc_lblX2.gridx = 0;
			gbc_lblX2.gridy = 4;
			contentPanel.add(lblX2, gbc_lblX2);
		}
		{
			txtX2 = new JTextField();
			GridBagConstraints gbc_txtX2 = new GridBagConstraints();
			gbc_txtX2.insets = new Insets(0, 0, 5, 0);
			gbc_txtX2.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX2.gridx = 1;
			gbc_txtX2.gridy = 4;
			contentPanel.add(txtX2, gbc_txtX2);
			txtX2.setColumns(10);
		}
		{
			JLabel lblY2 = new JLabel("Y coordinate:");
			GridBagConstraints gbc_lblY2 = new GridBagConstraints();
			gbc_lblY2.anchor = GridBagConstraints.EAST;
			gbc_lblY2.insets = new Insets(0, 0, 5, 5);
			gbc_lblY2.gridx = 0;
			gbc_lblY2.gridy = 5;
			contentPanel.add(lblY2, gbc_lblY2);
		}
		{
			txtY2 = new JTextField();
			GridBagConstraints gbc_txtY2 = new GridBagConstraints();
			gbc_txtY2.insets = new Insets(0, 0, 5, 0);
			gbc_txtY2.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY2.gridx = 1;
			gbc_txtY2.gridy = 5;
			contentPanel.add(txtY2, gbc_txtY2);
			txtY2.setColumns(10);
		}
		{
			JLabel lblChoose = new JLabel("Choose:");
			GridBagConstraints gbc_lblChoose = new GridBagConstraints();
			gbc_lblChoose.insets = new Insets(0, 0, 0, 5);
			gbc_lblChoose.gridx = 0;
			gbc_lblChoose.gridy = 6;
			contentPanel.add(lblChoose, gbc_lblChoose);
		}
		{
			JButton btnColor = new JButton("COLOR");
			btnColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					color = JColorChooser.showDialog(null, "Choose color of line", Color.black);
				}
			});
			GridBagConstraints gbc_btnColor = new GridBagConstraints();
			gbc_btnColor.anchor = GridBagConstraints.WEST;
			gbc_btnColor.gridx = 1;
			gbc_btnColor.gridy = 6;
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

						if (txtX1.getText().isEmpty() || txtY1.getText().isEmpty() || txtX2.getText().isEmpty()
								|| txtY2.getText().isEmpty()) {
							confirm = false;
							JOptionPane.showMessageDialog(null, "All fields are required!", "Error message",
									JOptionPane.ERROR_MESSAGE);
						} else {
							try {
								if (Integer.parseInt(txtX1.getText()) < 0 || Integer.parseInt(txtY1.getText()) < 0
										|| Integer.parseInt(txtX2.getText()) < 0
										|| Integer.parseInt(txtY2.getText()) < 0) {
									JOptionPane.showMessageDialog(null, "Insert values greater than 0!",
											"Error message", JOptionPane.ERROR_MESSAGE);

								} else {

									confirm = true;
									setVisible(false);

								}

							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, "Enter numbers only!", "Error",
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
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void writeLine(Line line) {
		txtX1.setText(String.valueOf(line.getStartPoint().getX()));
		txtY1.setText(String.valueOf(line.getStartPoint().getY()));
		txtX2.setText(String.valueOf(line.getEndPoint().getX()));
		txtY2.setText(String.valueOf(line.getEndPoint().getY()));
	}

	public Line makeLine() {
		int x1 = Integer.parseInt(txtX1.getText());
		int y1 = Integer.parseInt(txtY1.getText());
		int x2 = Integer.parseInt(txtX2.getText());
		int y2 = Integer.parseInt(txtY2.getText());
		Point point1 = new Point(x1, y1);
		Point point2 = new Point(x2, y2);
		if (color == null) {
			color = Color.BLACK; // podrazumevana boja ako nije izabrana
		}
		Line line = new Line(point1, point2, color);
		return line;
	}

	public JTextField getTxtX1() {
		return txtX1;
	}

	public void setTxtX1(JTextField txtX1) {
		this.txtX1 = txtX1;
	}

	public JTextField getTxtY1() {
		return txtY1;
	}

	public void setTxtY1(JTextField txtY1) {
		this.txtY1 = txtY1;
	}

	public JTextField getTxtX2() {
		return txtX2;
	}

	public void setTxtX2(JTextField txtX2) {
		this.txtX2 = txtX2;
	}

	public JTextField getTxtY2() {
		return txtY2;
	}

	public void setTxtY2(JTextField txtY2) {
		this.txtY2 = txtY2;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean ok) {
		this.confirm = ok;
	}
}