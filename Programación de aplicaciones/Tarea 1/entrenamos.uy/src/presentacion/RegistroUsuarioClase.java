package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class RegistroUsuarioClase extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroUsuarioClase frame = new RegistroUsuarioClase();
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
	public RegistroUsuarioClase() {
		setBounds(100, 100, 450, 300);

	}

}
