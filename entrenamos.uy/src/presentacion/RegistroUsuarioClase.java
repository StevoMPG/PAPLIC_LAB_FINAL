package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Set;

import logica.IcontroladorClase;


import excepciones.ActividadDeportivaException;
import excepciones.ClaseException;
import excepciones.NoExisteCuponeraException;
import excepciones.InstitucionException;
import excepciones.UsuarioNoExisteException;

@SuppressWarnings("serial")
public class RegistroUsuarioClase extends JInternalFrame {
	
	/* Controlador de Dictado de Clase para las acciones del JInternalFrame */
	private IcontroladorClase controlClase;

	/* Componentes graficas */
	// JLabel:
	private JLabel lblSeleccionInstitucion;
	private JLabel lblSeleccionActividad;
	private JLabel lblSeleccionClase;
	private JLabel lblSeleccionSocio;
	private JLabel lblSeleccionTipo;
	private JLabel lblFechaRegistro;
	
	// JComboBox:
	private JComboBox<String> boxInstitucion;
	private JComboBox<String> boxActividad;
	private JComboBox<String> boxClase;
	private JComboBox<String> boxSocio;
	private JComboBox<String> boxTipo;
	// Seleccion de Fecha de Registro:
	private JComboBox<String> boxRDia; // Depende de mes;
	private JComboBox<String> boxRMes;
	
	// JTextField
	private JTextField regAnio;
	
	// JButton:
	private JButton btnAceptar;
    private JButton btnLimpiar;
    
    /* Crear frame */
	public RegistroUsuarioClase() {
		// Inicializa controlador Dictado de Clase:

		
		// Propiedades del JInternalFrame:
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Registro de Usuario a Dictado de Clase");
		setBounds(10, 40, 410, 460);
		
		// GridLayout:
		GridBagLayout gridBagLayout = new GridBagLayout();
	    gridBagLayout.columnWidths = new int[] { 30, 60, 60, 60, 60, 60, 30, 10 };
	    gridBagLayout.rowHeights = new int[] { 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30 };
	    gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
	    gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
	    getContentPane().setLayout(gridBagLayout);
        
        // JLabels:
        lblSeleccionInstitucion = new JLabel("Seleccione Institucion:");
        lblSeleccionInstitucion.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblSeleccionInstitucion = new GridBagConstraints();
        gbc_lblSeleccionInstitucion.gridwidth = 3;
        gbc_lblSeleccionInstitucion.fill = GridBagConstraints.BOTH;
        gbc_lblSeleccionInstitucion.insets = new Insets(0, 0, 5, 5);
        gbc_lblSeleccionInstitucion.gridx = 1;
        gbc_lblSeleccionInstitucion.gridy = 0;
        getContentPane().add(lblSeleccionInstitucion, gbc_lblSeleccionInstitucion);
        
        lblSeleccionActividad = new JLabel("Seleccione Actividad Deportiva:");
        lblSeleccionActividad.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblSeleccionActividad = new GridBagConstraints();
        gbc_lblSeleccionActividad.gridwidth = 4;
        gbc_lblSeleccionActividad.fill = GridBagConstraints.BOTH;
        gbc_lblSeleccionActividad.insets = new Insets(0, 0, 5, 5);
        gbc_lblSeleccionActividad.gridx = 1;
        gbc_lblSeleccionActividad.gridy = 2;
        getContentPane().add(lblSeleccionActividad, gbc_lblSeleccionActividad);
        
        lblSeleccionClase = new JLabel("Seleccione Clase:");
        lblSeleccionClase.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblSeleccionClase = new GridBagConstraints();
        gbc_lblSeleccionClase.gridwidth = 4;
        gbc_lblSeleccionClase.fill = GridBagConstraints.BOTH;
        gbc_lblSeleccionClase.insets = new Insets(0, 0, 5, 5);
        gbc_lblSeleccionClase.gridx = 1;
        gbc_lblSeleccionClase.gridy = 4;
        getContentPane().add(lblSeleccionClase, gbc_lblSeleccionClase);
        
        lblSeleccionSocio = new JLabel("Seleccione Socio:");
        lblSeleccionSocio.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblSeleccionSocio = new GridBagConstraints();
        gbc_lblSeleccionSocio.gridwidth = 4;
        gbc_lblSeleccionSocio.fill = GridBagConstraints.BOTH;
        gbc_lblSeleccionSocio.insets = new Insets(0, 0, 5, 5);
        gbc_lblSeleccionSocio.gridx = 1;
        gbc_lblSeleccionSocio.gridy = 6;
        getContentPane().add(lblSeleccionSocio, gbc_lblSeleccionSocio);
        
        lblSeleccionTipo = new JLabel("Seleccione el Tipo de Registro:");
        lblSeleccionTipo.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblSeleccionTipo = new GridBagConstraints();
        gbc_lblSeleccionTipo.gridwidth = 4;
        gbc_lblSeleccionTipo.fill = GridBagConstraints.BOTH;
        gbc_lblSeleccionTipo.insets = new Insets(0, 0, 5, 5);
        gbc_lblSeleccionTipo.gridx = 1;
        gbc_lblSeleccionTipo.gridy = 8;
        getContentPane().add(lblSeleccionTipo, gbc_lblSeleccionTipo);
        
        lblFechaRegistro = new JLabel("Seleccione la Fecha de Registro:");
        lblFechaRegistro.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblFechaRegistro = new GridBagConstraints();
        gbc_lblFechaRegistro.gridwidth = 4;
        gbc_lblFechaRegistro.fill = GridBagConstraints.BOTH;
        gbc_lblFechaRegistro.insets = new Insets(0, 0, 5, 5);
        gbc_lblFechaRegistro.gridx = 1;
        gbc_lblFechaRegistro.gridy = 10;
        getContentPane().add(lblFechaRegistro, gbc_lblFechaRegistro);
        
        // Arrays auxiliares para Fecha y Hora:
        String[] meses = new String[] { "-", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
        		"Setiembre", "Octubre", "Noviembre", "Diciembre" };
        
        // JComboBox:
        DefaultComboBoxModel<String> comboModelDia = new DefaultComboBoxModel<>();
        comboModelDia.addElement("-");
        for(int i = 1; i < 32; i++) {
        	comboModelDia.addElement( String.valueOf(i) );
        }
        boxRDia = new JComboBox<>(comboModelDia);        
        GridBagConstraints gbc_boxIDia = new GridBagConstraints();
        gbc_boxIDia.insets = new Insets(0, 0, 5, 5);
        gbc_boxIDia.fill = GridBagConstraints.BOTH;
        gbc_boxIDia.gridx = 1;
        gbc_boxIDia.gridy = 11;
        getContentPane().add(boxRDia, gbc_boxIDia);
        
        DefaultComboBoxModel<String> comboModelMes = new DefaultComboBoxModel<>(meses);
        boxRMes = new JComboBox<>(comboModelMes);
        boxRMes.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		if ((boxRMes.getSelectedIndex() % 2 == 0) && (boxRMes.getSelectedIndex() < 7) || 
        				(boxRMes.getSelectedIndex() % 2 == 1) && (boxRMes.getSelectedIndex() > 8)) {
        			if (boxRMes.getSelectedIndex() == 2)
        				boxRDia.removeItem("30");
        			boxRDia.removeItem("31");
        		} else {
        			if (comboModelDia.getIndexOf("30") == -1)
        				comboModelDia.addElement("30");
        			if (comboModelDia.getIndexOf("31") == -1)
        				comboModelDia.addElement("31");
        		}
        	}
        });
        GridBagConstraints gbc_boxIMes = new GridBagConstraints();
        gbc_boxIMes.gridwidth = 2;
        gbc_boxIMes.insets = new Insets(0, 0, 5, 5);
        gbc_boxIMes.fill = GridBagConstraints.BOTH;
        gbc_boxIMes.gridx = 2;
        gbc_boxIMes.gridy = 11;
        getContentPane().add(boxRMes, gbc_boxIMes);

        boxInstitucion = new JComboBox<>();
        boxInstitucion.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		String z = null,x = null,t=(String) boxInstitucion.getSelectedItem();
        		if(boxActividad.isEnabled()) 
        			z=(String) boxActividad.getSelectedItem();
        		if(boxClase.isEnabled())
        			x=(String) boxClase.getSelectedItem();

        		boxInstitucion.setSelectedItem(t);
        		if(boxActividad.isEnabled()) 
        			boxActividad.setSelectedItem(z);
        		if(boxClase.isEnabled())
        			boxClase.setSelectedItem(x);
        	}
        });
        boxInstitucion.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		// Falta funcion
        	}
        });
        GridBagConstraints gbc_boxInstitucion = new GridBagConstraints();
        gbc_boxInstitucion.gridwidth = 4;
        gbc_boxInstitucion.insets = new Insets(0, 0, 5, 5);
        gbc_boxInstitucion.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxInstitucion.gridx = 1;
        gbc_boxInstitucion.gridy = 1;
        getContentPane().add(boxInstitucion, gbc_boxInstitucion);
        
        boxActividad = new JComboBox<>();
        boxActividad.setEnabled(false);
        boxActividad.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		// Falta funcion
        	}
        });
        GridBagConstraints gbc_boxActividad = new GridBagConstraints();
        gbc_boxActividad.gridwidth = 4;
        gbc_boxActividad.insets = new Insets(0, 0, 5, 5);
        gbc_boxActividad.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxActividad.gridx = 1;
        gbc_boxActividad.gridy = 3;
        getContentPane().add(boxActividad, gbc_boxActividad);
        
        boxClase = new JComboBox<>();
        GridBagConstraints gbc_boxClase = new GridBagConstraints();
        gbc_boxClase.gridwidth = 4;
        gbc_boxClase.insets = new Insets(0, 0, 5, 5);
        gbc_boxClase.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxClase.gridx = 1;
        gbc_boxClase.gridy = 5;
        getContentPane().add(boxClase, gbc_boxClase);
        boxClase.setEnabled(false);
        
        boxSocio = new JComboBox<>();
        GridBagConstraints gbc_boxSocio = new GridBagConstraints();
        gbc_boxSocio.gridwidth = 4;
        gbc_boxSocio.insets = new Insets(0, 0, 5, 5);
        gbc_boxSocio.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxSocio.gridx = 1;
        gbc_boxSocio.gridy = 7;
        getContentPane().add(boxSocio, gbc_boxSocio);
        
        boxTipo = new JComboBox<>();
        boxTipo.addItem("-");
        boxTipo.addItem("General");
        boxTipo.addItem("Cuponera");
        GridBagConstraints gbc_boxTipo = new GridBagConstraints();
        gbc_boxTipo.gridwidth = 2;
        gbc_boxTipo.insets = new Insets(0, 0, 5, 5);
        gbc_boxTipo.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxTipo.gridx = 1;
        gbc_boxTipo.gridy = 9;
        getContentPane().add(boxTipo, gbc_boxTipo);
        
        // JTextField
        regAnio = new JTextField();
        regAnio.setText("yyyy");
        regAnio.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
        		regAnio.setText("");
        	}
        });
        GridBagConstraints gbc_regAnio = new GridBagConstraints();
        gbc_regAnio.gridwidth = 1;
        gbc_regAnio.fill = GridBagConstraints.BOTH;
        gbc_regAnio.insets = new Insets(0, 0, 5, 5);
        gbc_regAnio.gridx = 4;
        gbc_regAnio.gridy = 11;
        getContentPane().add(regAnio, gbc_regAnio);
        
        // JButton:
        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	// Funcion
            }
        });
        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
        gbc_btnAceptar.fill = GridBagConstraints.BOTH;
        gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
        gbc_btnAceptar.gridx = 3;
        gbc_btnAceptar.gridy = 13;
        getContentPane().add(btnAceptar, gbc_btnAceptar);
        
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });    
        GridBagConstraints gbc_btnLimpiar = new GridBagConstraints();
        gbc_btnLimpiar.insets = new Insets(0, 0, 0, 5);
        gbc_btnLimpiar.gridwidth = 2;
        gbc_btnLimpiar.fill = GridBagConstraints.BOTH;
        gbc_btnLimpiar.gridx = 4;
        gbc_btnLimpiar.gridy = 13;
        getContentPane().add(btnLimpiar, gbc_btnLimpiar);
        
        
	}
	



	// Limpia el JInternalFrame
	public void clear() {
		regAnio.setText("yyyy");
		boxRDia.setSelectedIndex(0);
        boxRMes.setSelectedIndex(0);
	    boxInstitucion.removeAllItems();
	    boxActividad.removeAllItems();
	    boxActividad.setEnabled(false);
	    boxClase.removeAllItems();
	    boxClase.setEnabled(false);
	    boxSocio.removeAllItems();
	    boxTipo.setSelectedIndex(0);
	}
}