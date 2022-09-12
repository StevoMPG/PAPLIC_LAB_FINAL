package presentacion;



import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ItemListener;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ItemEvent;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.swing.event.PopupMenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.PopupMenuEvent;

import logica.IcontroladorUsuario;
import datatypes.DtUsuario;
import excepciones.InstitucionException;
import datatypes.DtSocio;
import datatypes.DtProfesor;
import datatypes.DtFechaHora;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;


@SuppressWarnings("serial")
public class AltaUsuario extends JInternalFrame {

	Set<String> instituciones;
	private IcontroladorUsuario controlUsr;
	private JTextField textFieldNickname;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldEmail;
	private JComboBox<String> comboBoxTipoDeUsuario;
	private JLabel labelTipoDeUsuario;
	private JTextArea textAreaBiografia;
	private JTextField textFieldWebsite;
	private JLabel labelWebsite;
	private JLabel labelBiografia;
	private JLabel labelDescripcion;
	private JLabel labelInstitucion;
	private JLabel labelAclaracionProfesor1;
	private JLabel labelAclaracionProfesor2;
	private JLabel labelAclaracionProfesor3;
	private JLabel labelAclaracionProfesor4;
	private JComboBox<String> comboBoxInstitucion;
	private JScrollPane scrollPane;
	private JTextArea textAreaDescripcion;
	private JScrollPane scrollPane_1;
	private JLabel labelAclaracionFecha;
	private JTextField inicioAnio;
	
	// Seleccion de Fecha de Inicio:
	private JComboBox<String> boxIDia; // Depende de mes;
	private JComboBox<String> boxIMes;
	private Component verticalStrut;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	
	private ImageIcon image;
	private Icon icon;
	private JLabel lblFoto;
	
	
	
	public AltaUsuario(IcontroladorUsuario controlUsr) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		
		this.instituciones = new HashSet<>();
		this.controlUsr = controlUsr;
		
		/* 
		 *  Parametrizacion de dimensiones
		 */
		int columns = 8;
		int rows = 9;
		int iframeWidth = 450;
		int iframeHeight = 625;
		int gridWidth = iframeWidth/columns;
		int gridHeight = iframeHeight/rows;
		int x = gridWidth+gridHeight;
		setBounds(100, 25, 440, 710); // w,h
		
		setTitle("Alta de usuario");
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("1px"),
				ColumnSpec.decode("16px"),
				ColumnSpec.decode("30px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("149px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("55px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("73px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("72px"),},
			new RowSpec[] {
				RowSpec.decode("1px"),
				RowSpec.decode("24px"),
				RowSpec.decode("20px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("14px"),
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("14px"),
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("14px"),
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("17px"),
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("22px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("14px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("14px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("48px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("14px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("48px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("14px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				RowSpec.decode("35px"),
				RowSpec.decode("23px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		verticalStrut = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut, "1, 1, center, center");
		
		labelTipoDeUsuario = new JLabel("Tipo de usuario");
		getContentPane().add(labelTipoDeUsuario, "9, 3, right, center");
		
		comboBoxTipoDeUsuario = new JComboBox<String>();
		comboBoxTipoDeUsuario.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				/*
				 * Si se eligio profesor en el comboBox
				 * se hacen visibles los campos que solo le corresponden a este
				 */
				boolean esProfesor = false;
				esProfesor = comboBoxTipoDeUsuario.getSelectedItem().toString() == "Profesor";
				textAreaBiografia.setEnabled(esProfesor);
				textAreaDescripcion.setEnabled(esProfesor);
				textFieldWebsite.setEnabled(esProfesor);
				comboBoxInstitucion.setEnabled(esProfesor);
				if(esProfesor) {
					textAreaBiografia.setText("");
					textAreaDescripcion.setText("");
					textFieldWebsite.setText("");
					comboBoxInstitucion.setSelectedIndex(0);
				}
	
			}
		});
		comboBoxTipoDeUsuario.setModel(new DefaultComboBoxModel<>(new String[] {"-", "Socio", "Profesor"}));
		getContentPane().add(comboBoxTipoDeUsuario, "11, 3, fill, bottom");
		
		JLabel labelNickname = new JLabel("Nickname");
		getContentPane().add(labelNickname, "3, 5, 3, 1, fill, bottom");
		
		textFieldNickname = new JTextField();
		getContentPane().add(textFieldNickname, "3, 7, 9, 1, fill, fill");
		textFieldNickname.setColumns(10);
		
		JLabel labelNombre = new JLabel("Nombre");
		getContentPane().add(labelNombre, "3, 9, 3, 1, fill, bottom");
		
		JLabel labelApellido = new JLabel("Apellido");
		getContentPane().add(labelApellido, "7, 9, center, bottom");
		
		textFieldNombre = new JTextField();
		getContentPane().add(textFieldNombre, "3, 11, 3, 1, fill, fill");
		textFieldNombre.setColumns(10);
		
		textFieldApellido = new JTextField();
		getContentPane().add(textFieldApellido, "7, 11, 5, 1, fill, fill");
		textFieldApellido.setColumns(10);
		
		JLabel labelEmail = new JLabel("Correo electronico");
		getContentPane().add(labelEmail, "3, 13, 3, 1, left, bottom");
		
		textFieldEmail = new JTextField();
		getContentPane().add(textFieldEmail, "3, 15, 9, 1, fill, fill");
		textFieldEmail.setColumns(10);
        
        
        // Arrays auxiliares para Fecha y Hora:
        String[] meses = new String[] { "-", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
        		"Setiembre", "Octubre", "Noviembre", "Diciembre" };
        
        // JComboBox:
        DefaultComboBoxModel<String> comboModelDia = new DefaultComboBoxModel<>();
        comboModelDia.addElement("-");
        for(int i = 1; i < 32; i++) {
        	comboModelDia.addElement( String.valueOf(i) );
        }
        
        DefaultComboBoxModel<String> comboModelMes = new DefaultComboBoxModel<>(meses);
		JLabel labelFechaNacimiento = new JLabel("Fecha de nacimiento");
		getContentPane().add(labelFechaNacimiento, "3, 17, 3, 1, left, bottom");
		
		
		labelAclaracionFecha = new JLabel("(dd/mm/aaaa)");
		getContentPane().add(labelAclaracionFecha, "11, 17, center, center");
		
		boxIDia = new JComboBox<>( comboModelDia );
		getContentPane().add(boxIDia, "3, 19, fill, center");
        boxIMes = new JComboBox<>(comboModelMes);
        boxIMes.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		if ((boxIMes.getSelectedIndex() % 2 == 0) && (boxIMes.getSelectedIndex() < 7) || 
        				(boxIMes.getSelectedIndex() % 2 == 1) && (boxIMes.getSelectedIndex() > 8)) {
        			if (boxIMes.getSelectedIndex() == 2)
        				boxIDia.removeItem("30");
        			boxIDia.removeItem("31");
        		} else {
        			if (comboModelDia.getIndexOf("30") == -1)
        				comboModelDia.addElement("30");
        			if (comboModelDia.getIndexOf("31") == -1)
        				comboModelDia.addElement("31");
        		}
        	}
        });
        getContentPane().add(boxIMes, "5, 19, fill, center");
		
        inicioAnio = new JTextField();
        inicioAnio.setText("yyyy");
        inicioAnio.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
        		inicioAnio.setText("");
        	}
        });
        getContentPane().add(inicioAnio, "7, 19, fill, fill");
		
		labelInstitucion = new JLabel("Nombre de Institucion");
		getContentPane().add(labelInstitucion, "3, 21, 3, 1, left, center");
		
		labelAclaracionProfesor1 = new JLabel("(Solo profesor)");
		getContentPane().add(labelAclaracionProfesor1, "11, 21, center, center");
		
		comboBoxInstitucion = new JComboBox<String>();
		comboBoxInstitucion.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				comboBoxInstitucion.removeAllItems();
				comboBoxInstitucion.addItem("-");
				for(String ins:controlUsr.obtenerInstituciones())
					comboBoxInstitucion.addItem(ins);
			}
		});
		comboBoxInstitucion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
		});
		comboBoxInstitucion.setModel(new DefaultComboBoxModel<String>(new String[] {"-"}));
		comboBoxInstitucion.setEnabled(false);
		getContentPane().add(comboBoxInstitucion, "3, 23, 9, 1, fill, center");
		
		labelDescripcion = new JLabel("Descripcion");
		getContentPane().add(labelDescripcion, "3, 25, 3, 1, left, center");
		
		labelAclaracionProfesor2 = new JLabel("(Solo profesor)");
		getContentPane().add(labelAclaracionProfesor2, "11, 25, center, center");
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "3, 27, 9, 1, fill, fill");
		
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setEnabled(false);
		scrollPane.setViewportView(textAreaDescripcion);
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setWrapStyleWord(true);
		
		labelBiografia = new JLabel("Biografia (opcional)");
		getContentPane().add(labelBiografia, "3, 29, 3, 1, left, center");
		
		labelAclaracionProfesor3 = new JLabel("(Solo profesor)");
		getContentPane().add(labelAclaracionProfesor3, "11, 29, center, center");
		
		scrollPane_1 = new JScrollPane();
		getContentPane().add(scrollPane_1, "3, 31, 9, 1, fill, fill");
		
		textAreaBiografia = new JTextArea();
		textAreaBiografia.setEnabled(false);
		scrollPane_1.setViewportView(textAreaBiografia);
		textAreaBiografia.setLineWrap(true);
		textAreaBiografia.setWrapStyleWord(true);
		
		labelWebsite = new JLabel("Website (opcional)");
		getContentPane().add(labelWebsite, "3, 33, 3, 1, left, center");
		
		labelAclaracionProfesor4 = new JLabel("(Solo profesor)");
		getContentPane().add(labelAclaracionProfesor4, "11, 33, center, center");
		
		textFieldWebsite = new JTextField();
		textFieldWebsite.setBackground(Color.WHITE);
		textFieldWebsite.setEnabled(false);
		getContentPane().add(textFieldWebsite, "3, 35, 9, 1, fill, center");
		textFieldWebsite.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tomarDatos()==0)
					clear();
					setVisible(false);
			}
		});
		
		btnNewButton_1 = new JButton("Add Photo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				final JLabel img = new JLabel();
				img.setPreferredSize(new Dimension(900,900));
				img.setHorizontalAlignment(JLabel.CENTER);
				final JFileChooser v = new JFileChooser();
				v.setAccessory(img);
				v.addPropertyChangeListener(new PropertyChangeListener() {
					
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						try {
							if(evt.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)) {
								img.setText("");
								img.setIcon(new ImageIcon(v.getSelectedFile().getPath()));
							}
						}catch(Exception ex) {
							img.setText("");
							img.setIcon(new ImageIcon());
						}
					}
				});
				
				int Abrir = v.showOpenDialog(btnAceptar);
				v.setDialogTitle("Imagenes");
				if(Abrir==JFileChooser.APPROVE_OPTION) {
					String url = v.getSelectedFile().getPath();
					lblFoto.setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
					
					
				}
				
			}
		});
		getContentPane().add(btnNewButton_1, "9, 36, 2, 1, center, center");
		
		lblFoto = new JLabel("");
		lblFoto.setBackground(Color.GRAY);
		getContentPane().add(lblFoto, "5, 37, 1, 11, center, center");
		getContentPane().add(btnAceptar, "9, 37, center, top");
		
		JButton btnCancelar = new JButton("Limpiar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		getContentPane().add(btnCancelar, "11, 37, center, top");
		

	}
	
	
	
	 //Se encarga de limpiar datos ingresados por el usuario
	 
	public void clear() {
		comboBoxTipoDeUsuario.setSelectedIndex(0);
        textFieldNombre.setText("");
        textFieldApellido.setText("");
        textFieldNickname.setText("");
        textFieldEmail.setText("");
    	boxIDia.setSelectedIndex(0);
    	inicioAnio.setText("");
    	boxIMes.setSelectedIndex(0);
    	textAreaDescripcion.setText("");
    	textAreaBiografia.setText("");
    	textFieldWebsite.setText("");
    	comboBoxInstitucion.setSelectedIndex(0);
    }
	
	
	 
	private int tomarDatos() {
		try {
			String tipoU;
			String nicknameU;
			String nombreU;
	        String apellidoU;
	        String emailU;
	        int diaU;
	        int mesU;
	        int anioU;
	        String institutoU;
	        String descripcionU;
	        String biografiaU;
	        String websiteU;
	        
	        if(!checkFormulario()) {
	        	return 1;
	        }
			tipoU = this.comboBoxTipoDeUsuario.getSelectedItem().toString().trim();
			nicknameU = this.textFieldNickname.getText().trim();
			nombreU = this.textFieldNombre.getText().trim();
	        apellidoU = this.textFieldApellido.getText().trim();
	        emailU = this.textFieldEmail.getText().trim();
	        diaU = boxIDia.getSelectedIndex();
	        mesU = boxIMes.getSelectedIndex();
	        anioU = Integer.parseInt(inicioAnio.getText().trim());
	        institutoU = this.comboBoxInstitucion.getSelectedItem().toString().trim();
	        descripcionU = this.textAreaDescripcion.getText().trim();
	        biografiaU = this.textAreaBiografia.getText().trim();
	        websiteU = this.textFieldWebsite.getText().trim();
	        
			/*
			 * Crea el tipo de dato segun el tipo de usuario seleccionado
			 */
			DtUsuario datosUser;
			if(tipoU == "Profesor") {
				datosUser = new DtProfesor(nicknameU,nombreU,apellidoU,emailU, new DtFechaHora(anioU,mesU,diaU,0,0,0),institutoU, descripcionU,biografiaU,websiteU, null);
			}else { //Se asume que si no es profesor es socio
				datosUser = new DtSocio(nicknameU,nombreU,apellidoU,emailU, new DtFechaHora(anioU,mesU,diaU,0,0,0), null);
			}
			if(controlUsr.ingresarDatosUsuario(datosUser) != 0) {
				JOptionPane.showMessageDialog(this, "Ya existe un usuario con los datos ingresados.", this.getTitle(), JOptionPane.ERROR_MESSAGE);
				return 1;
			} else {
				JOptionPane.showMessageDialog(this, "El usuario ha sido registrado de forma exitosa.", this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
				return 0;
			}
		} catch (InstitucionException e) {
	        JOptionPane.showMessageDialog(this, e.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
	        return 0;
		}
	}
	
	
	 // Valida los datos ingresados por el usuario
	 
	private boolean checkFormulario() {
		String tipoU  = this.comboBoxTipoDeUsuario.getSelectedItem().toString().trim();
		String nicknameU = this.textFieldNickname.getText().trim();
		String nombreU = this.textFieldNombre.getText().trim();
        String apellidoU = this.textFieldApellido.getText().trim();
        String emailU = this.textFieldEmail.getText().trim();
        int diaU = boxIDia.getSelectedIndex();
        int mesU = this.boxIMes.getSelectedIndex();
        String anioU = inicioAnio.getText().trim();
        String institutoU = this.comboBoxInstitucion.getSelectedItem().toString().trim();
        String descripcionU = this.textAreaDescripcion.getText().trim();

        // Celdas vacias
        if (tipoU == "-" || nicknameU.isEmpty() || nombreU.isEmpty() || apellidoU.isEmpty() || emailU.isEmpty() || diaU < 1 || mesU < 1 || anioU.isEmpty() || ((tipoU == "Profesor") && (institutoU == "-" || descripcionU.isEmpty()))) {
            JOptionPane.showMessageDialog(this, "Exisisten campos obligatorios vacios/sin seleccionar.", this.getTitle(), JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
      // Funcion de stackOverFlow Numeros no son numeros
        try {
            Integer.parseInt(anioU);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La fecha de ingresada no es valida", this.getTitle(), JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
	}
	
	
}

 