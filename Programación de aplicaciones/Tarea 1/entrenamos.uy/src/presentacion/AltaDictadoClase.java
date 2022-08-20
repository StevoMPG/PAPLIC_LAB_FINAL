package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class AltaDictadoClase extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaDictadoClase frame = new AltaDictadoClase();
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
	public AltaDictadoClase() {
		setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel = new JLabel("agregar actividad");
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);

	}

}
