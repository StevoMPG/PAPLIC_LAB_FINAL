package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class AltaInstitucionDeportiva extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaInstitucionDeportiva frame = new AltaInstitucionDeportiva();
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
	public AltaInstitucionDeportiva() {
		setBounds(100, 100, 450, 300);

	}

}
