package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class ConsultaActividadDeportiva extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaActividadDeportiva frame = new ConsultaActividadDeportiva();
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
	public ConsultaActividadDeportiva() {
		setBounds(100, 100, 450, 300);

	}

}
