package drawing;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class PnlDrawing extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private FrmDrawing frame; //cuva referencu na instancu FrmDrawing, omogucava panelu da pristupa metodama i atributima

	/**
	 * Create the panel.
	 */
	public PnlDrawing() {}
	
	public PnlDrawing (FrmDrawing frame) { //konstruktor koji prima instancu FrmDrawing kao parametar da PnlDrawing zna u kojem prozoru se nalazi
		this.frame = frame;	//dodeljuje prosledjenu instancu FrmDrawing varijabli frame da panel komunicira sa glavnim prozorom
		setBackground(Color.WHITE);
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				mouseClicked(me); 		
			}
		});

	}
}