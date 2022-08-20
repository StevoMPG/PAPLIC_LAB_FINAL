package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class ConsultaDictadoClase extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaDictadoClase frame = new ConsultaDictadoClase();
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
	public ConsultaDictadoClase() {
		setBounds(100, 100, 450, 300);

	}

}
