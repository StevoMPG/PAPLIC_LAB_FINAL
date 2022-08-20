package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class CrearCuponera extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearCuponera frame = new CrearCuponera();
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
	public CrearCuponera() {
		setBounds(100, 100, 450, 300);

	}

}
