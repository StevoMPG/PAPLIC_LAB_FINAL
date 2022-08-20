package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

//Descomentar cuando lo vayamos a usar

import logica.IcontroladorActividadDeportiva;
import logica.IcontroladorCuponera;

@SuppressWarnings("serial")
public class AgregarActividadDeportivaCuponera extends JInternalFrame {

	private JComboBox<String> comboBoxCup;
	private JComboBox<String> comboBoxInstitucion;
	private JComboBox<String> deltaI;
	private JTextField CantClases;
	private JButton Acept;
	private JButton Cancel;
	
	
	public AgregarActividadDeportivaCuponera() {

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setBounds(100, 100, 435, 254);
		setTitle("Agrear actividad deportiva a cuponera");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel cupo = new JLabel("Cuponeras");
		GridBagConstraints gbc_cupo = new GridBagConstraints();
		gbc_cupo.fill = GridBagConstraints.HORIZONTAL;
		gbc_cupo.gridwidth = 11;
		gbc_cupo.insets = new Insets(0, 0, 5, 5);
		gbc_cupo.gridx = 1;
		gbc_cupo.gridy = 1;
		getContentPane().add(cupo, gbc_cupo);
//---------------------------------------------------------------------------
		
		comboBoxCup = new JComboBox<String>();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("-");
		
		comboBoxCup.setModel(model);
		comboBoxCup.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					String xx = (String) comboBoxCup.getSelectedItem();
					String yy = (String) comboBoxInstitucion.getSelectedItem();
					if(xx==null||yy==null)
						return;
					deltaI.removeAllItems();
					deltaI.addItem("-");
					deltaI.setSelectedItem("-");
				} catch (Exception ignore) { }
			}
		});

		comboBoxCup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// Aca va una funcion, toca pensarla luego
			}
		});

		GridBagConstraints gbc_cup = new GridBagConstraints();
		gbc_cup.gridwidth = 11;
		gbc_cup.insets = new Insets(0, 0, 5, 5);
		gbc_cup.fill = GridBagConstraints.HORIZONTAL;
		gbc_cup.gridx = 1;
		gbc_cup.gridy = 2;
		getContentPane().add(comboBoxCup, gbc_cup);
//-------------------------------------------------------------------------		
		JLabel inst = new JLabel("Instituciones\r\n");
		GridBagConstraints gbc_inst = new GridBagConstraints();
		gbc_inst.fill = GridBagConstraints.HORIZONTAL;
		gbc_inst.gridwidth = 11;
		gbc_inst.insets = new Insets(0, 0, 5, 5);
		gbc_inst.gridx = 1;
		gbc_inst.gridy = 3;
		getContentPane().add(inst, gbc_inst);
		
//-----------------------------------------------------------------------------------

		DefaultComboBoxModel<String> comboModelInstitucion = new DefaultComboBoxModel<>();
		comboModelInstitucion.addElement("-");
		
		
		comboBoxInstitucion = new JComboBox<>();
		comboBoxInstitucion.setModel(comboModelInstitucion);
		comboBoxInstitucion.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					String xx = (String) comboBoxCup.getSelectedItem();
					String yy = (String) comboBoxInstitucion.getSelectedItem();
					if(xx==null||yy==null)
						return;
					deltaI.removeAllItems();
					deltaI.addItem("-");
					deltaI.setSelectedItem("-");
				} catch (Exception ignore) { }
			}
		});
		comboBoxInstitucion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// Recordatorio: Aca va un metodo, toca ver luego como es bien
			}
		});
		
		GridBagConstraints gbc_insti = new GridBagConstraints();
		gbc_insti.gridwidth = 11;
		gbc_insti.insets = new Insets(0, 0, 5, 5);
		gbc_insti.fill = GridBagConstraints.HORIZONTAL;
		gbc_insti.gridx = 1;
		gbc_insti.gridy = 4;
		getContentPane().add(comboBoxInstitucion, gbc_insti);

//--------------------------------------------------------------------------------		
		JLabel lblActividadDeportiva = new JLabel("Actividad Deportiva");
		GridBagConstraints gbc_lblActividadDeportiva = new GridBagConstraints();
		gbc_lblActividadDeportiva.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblActividadDeportiva.gridwidth = 11;
		gbc_lblActividadDeportiva.insets = new Insets(0, 0, 5, 5);
		gbc_lblActividadDeportiva.gridx = 1;
		gbc_lblActividadDeportiva.gridy = 5;
		getContentPane().add(lblActividadDeportiva, gbc_lblActividadDeportiva);

//------------------------------------------------------------------------------		


		DefaultComboBoxModel<String> comboModelDeltaI = new DefaultComboBoxModel<>();
		comboModelDeltaI.addElement("-");
		deltaI = new JComboBox<>(comboModelDeltaI);

		GridBagConstraints gbc_deltaI = new GridBagConstraints();
		gbc_deltaI.gridwidth = 11;
		gbc_deltaI.insets = new Insets(0, 0, 5, 5);
		gbc_deltaI.fill = GridBagConstraints.HORIZONTAL;
		gbc_deltaI.gridx = 1;
		gbc_deltaI.gridy = 6;
		getContentPane().add(deltaI, gbc_deltaI);

//_-----------------------------------------------------------------------------------		
		Acept = new JButton("Aceptar\r\n");
		Acept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tomarDatos (); // Aca va una funcion, toca hacerla despues de hacer la logica, ya la dejo declarada abajo
			}
		});
		GridBagConstraints gbc_Acept = new GridBagConstraints();
		gbc_Acept.insets = new Insets(0, 0, 5, 5);
		gbc_Acept.gridx = 10;
		gbc_Acept.gridy = 8;
		getContentPane().add(Acept, gbc_Acept);
		
		JLabel lblNewLabel = new JLabel("Cantidad de clases");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridwidth = 5;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 7;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		CantClases = new JTextField();
		GridBagConstraints gbc_CantClases = new GridBagConstraints();
		gbc_CantClases.gridwidth = 3;
		gbc_CantClases.insets = new Insets(0, 0, 5, 5);
		gbc_CantClases.fill = GridBagConstraints.HORIZONTAL;
		gbc_CantClases.gridx = 2;
		gbc_CantClases.gridy = 8;
		getContentPane().add(CantClases, gbc_CantClases);
		CantClases.setColumns(10);

		
		Cancel = new JButton("Limpiar");
		Cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
				}
			});
		GridBagConstraints gbc_Cancel = new GridBagConstraints();
		gbc_Cancel.insets = new Insets(0, 0, 5, 5);
		gbc_Cancel.gridx = 11;
		gbc_Cancel.gridy = 8;
		getContentPane().add(Cancel, gbc_Cancel);
	}

	public void clear() {
		deltaI.setSelectedItem("-");
		comboBoxCup.setSelectedItem("-");
		comboBoxInstitucion.setSelectedItem("-");
		CantClases.setText("");
		
	}
	
	
	private void tomarDatos() {
		
	}
}

