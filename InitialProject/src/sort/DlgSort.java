package sort;

import java.awt.BorderLayout;
//import java.awt.Color;
import java.awt.FlowLayout;
//import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
//import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
//import javax.swing.border.LineBorder;
//import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgSort extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtX1;
	private JTextField txtY1;
	private JTextField txtRadius1;
	private JTextField txtInnerRadius1;
	private boolean isOk=false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgSort dialog = new DlgSort();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgSort() {
		setTitle("Add donut");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblX = new JLabel("X:");
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.insets = new Insets(0, 0, 5, 5);
			gbc_lblX.anchor = GridBagConstraints.EAST;
			gbc_lblX.gridx = 0;
			gbc_lblX.gridy = 0;
			contentPanel.add(lblX, gbc_lblX);
		}
		{
			txtX1 = new JTextField();
			GridBagConstraints gbc_txtX1 = new GridBagConstraints();
			gbc_txtX1.insets = new Insets(0, 0, 5, 0);
			gbc_txtX1.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX1.gridx = 1;
			gbc_txtX1.gridy = 0;
			contentPanel.add(txtX1, gbc_txtX1);
			txtX1.setColumns(10);
		}
		{
			JLabel lblY = new JLabel("Y:");
			GridBagConstraints gbc_lblY = new GridBagConstraints();
			gbc_lblY.anchor = GridBagConstraints.EAST;
			gbc_lblY.insets = new Insets(0, 0, 5, 5);
			gbc_lblY.gridx = 0;
			gbc_lblY.gridy = 1;
			contentPanel.add(lblY, gbc_lblY);
		}
		{
			txtY1 = new JTextField();
			GridBagConstraints gbc_txtY1 = new GridBagConstraints();
			gbc_txtY1.insets = new Insets(0, 0, 5, 0);
			gbc_txtY1.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY1.gridx = 1;
			gbc_txtY1.gridy = 1;
			contentPanel.add(txtY1, gbc_txtY1);
			txtY1.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Outer radius:");
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.anchor = GridBagConstraints.EAST;
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 0;
			gbc_lblRadius.gridy = 2;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			txtRadius1 = new JTextField();
			GridBagConstraints gbc_txtRadius1 = new GridBagConstraints();
			gbc_txtRadius1.insets = new Insets(0, 0, 5, 0);
			gbc_txtRadius1.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtRadius1.gridx = 1;
			gbc_txtRadius1.gridy = 2;
			contentPanel.add(txtRadius1, gbc_txtRadius1);
			txtRadius1.setColumns(10);
		}
		{
			JLabel lblInnerRadius1 = new JLabel("Inner radius:");
			GridBagConstraints gbc_lblInnerRadius1 = new GridBagConstraints();
			gbc_lblInnerRadius1.anchor = GridBagConstraints.EAST;
			gbc_lblInnerRadius1.insets = new Insets(0, 0, 0, 5);
			gbc_lblInnerRadius1.gridx = 0;
			gbc_lblInnerRadius1.gridy = 3;
			contentPanel.add(lblInnerRadius1, gbc_lblInnerRadius1);
		}
		{
			txtInnerRadius1 = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 3;
			contentPanel.add(txtInnerRadius1, gbc_textField);
			txtInnerRadius1.setColumns(10);
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
				            if (txtX1.getText().isEmpty() || txtY1.getText().isEmpty() || txtRadius1.getText().isEmpty() || txtInnerRadius1.getText().isEmpty()) {
				                JOptionPane.showMessageDialog(null, "Please enter a value for all fields.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
				            } else {
				                int radius = Integer.parseInt(txtRadius1.getText());
				                int innerRadius = Integer.parseInt(txtInnerRadius1.getText());
				                
				                if (innerRadius > 0 && radius > 0) {
				                    if (innerRadius < radius) {
				                        isOk = true; 
				                    } else {
				                        JOptionPane.showMessageDialog(null, "Outer radius must be greater than the inner radius!", "Error message", JOptionPane.ERROR_MESSAGE);
				                    }
				                } else {
				                    JOptionPane.showMessageDialog(null, "Outer and inner radius must be greater than 0.", "Error message", JOptionPane.ERROR_MESSAGE);
				                }
				            }
				        } catch (NumberFormatException e1) {
				            JOptionPane.showMessageDialog(null, "Values must be integers!", "Error message", JOptionPane.ERROR_MESSAGE);
				        }
				        
				        if (isOk) {
				            setVisible(false); 
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

	public JTextField getTxtRadius1() {
		return txtRadius1;
	}

	public void setTxtRadius1(JTextField txtRadius1) {
		this.txtRadius1 = txtRadius1;
	}

	public JTextField getTxtInnerRadius1() {
		return txtInnerRadius1;
	}

	public void setTxtInnerRadius1(JTextField txtInnerRadius1) {
		this.txtInnerRadius1 = txtInnerRadius1;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}
	
	

}
