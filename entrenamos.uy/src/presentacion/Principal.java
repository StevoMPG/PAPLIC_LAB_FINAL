package presentacion;


import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import logica.Fabrica;
import logica.IcontroladorUsuario;
import logica.IcontroladorActividadDeportiva;
import logica.IcontroladorClase;
import logica.IcontroladorCuponera;

import datatypes.DtActividadDeportiva;
import datatypes.DtClase;
import datatypes.DtFechaHora;
import datatypes.DtProfesor;
import datatypes.DtSocio;
import datatypes.tipoRegistro;


import excepciones.NoExisteCuponeraException;
import excepciones.ActividadDeportivaException;
import excepciones.ClaseException;
import excepciones.CuponeraRepetidaException;
import excepciones.InstitucionException;
import excepciones.UsuarioNoExisteException;



public class Principal {

	private JFrame entrenamosUy;
	private JDesktopPane desktopPane;
	private IcontroladorUsuario IUC;
	private IcontroladorActividadDeportiva IADC;
	private IcontroladorCuponera IDC;
	private IcontroladorClase IDCC;
	
	// Declaracion de los JInternalFrames
	
    private AltaUsuario altaUsuario;
	private AltaActividadDeportiva altaActDep;
	private AltaDictadoClase altaClase;
	private AltaInstitucionDeportiva altaIns;
	private CrearCuponera altaCup;
	private RegistroUsuarioClase regUsuClass;
	private ConsultaDictadoClase consultaClass;
	private ConsultaActividadDeportiva consActDep;
	private ConsultaCuponeras consultaCup;
	private ConsultaUsuario consultaUsu;
	private ModificarDatosUsuario modificarUsu;
	private AgregarActividadDeportivaCuponera aggCup;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.entrenamosUy.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
		
		Fabrica fabrica = Fabrica.getInstance();
		IUC = fabrica.obtenerIcontroladorUsuario();
		IADC = fabrica.obtenerIcontroladorActDeportiva();
		IDC = fabrica.obtenerIcontroladorCuponera();
		IDCC = fabrica.obtenerIcontroladorDictadoClase();	

		
		//Preinicializacion de JInternalFrames con visibilidad = false
		
				//AltaUsuario:
				altaUsuario = new AltaUsuario(IUC);
				altaUsuario.setLocation(462, 25);
				altaUsuario.setVisible(false);
				desktopPane.add(altaUsuario);	
				
				//AltaActividadDeportiva
				altaActDep = new AltaActividadDeportiva(IADC);
				altaActDep.setLocation(20, 20);
				altaActDep.setSize(450, 500);
				altaActDep.setVisible(false);
				desktopPane.add(altaActDep);

				// AltaDictadoClase:
				altaClase = new AltaDictadoClase(IDCC);
				altaClase.setLocation(10, 11);
				altaClase.setVisible(false);
				desktopPane.add(altaClase);
				
				// AltaInstitucionDeporitva:
				altaIns = new AltaInstitucionDeportiva(IADC);
				altaIns.setBounds(212, 37, 354, 344);
				altaIns.setVisible(false);
				desktopPane.add(altaIns);
				
				// RegistroUsuarioClase:
				regUsuClass = new RegistroUsuarioClase(IDCC);
				regUsuClass.setVisible(false);
				desktopPane.add(regUsuClass);
				
				// ConsultaDictadoClase:
				consultaClass = new ConsultaDictadoClase(IDCC);
				consultaClass.setBounds(10, 40, 382, 545);
				consultaClass.setVisible(false);
				desktopPane.add(consultaClass);
				
				// ConsultaCuponeras:
				consultaCup = new ConsultaCuponeras(IDC);
				consultaCup.setBounds(200, 100, 400, 458);
				consultaCup.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				consultaCup.setVisible(false);
				desktopPane.add(consultaCup);
				
				//ConsultaUsuario
				consultaUsu = new ConsultaUsuario(IUC);
				consultaUsu.setVisible(false);
				desktopPane.add(consultaUsu);

				// ConsultaActividadDeportiva
				consActDep = new ConsultaActividadDeportiva(IADC);
				consActDep.setBounds(143, 20, 457, 719);
				consActDep.setVisible(false);
				desktopPane.add(consActDep);
				
				//ModificarDatosUsuario
				modificarUsu = new ModificarDatosUsuario(IUC);
				modificarUsu.setVisible(false);
				desktopPane.add(modificarUsu);
				
				altaCup = new CrearCuponera(IDC);
				altaCup.setBounds(100, 100, 500, 483);
				altaCup.setVisible(false);
				desktopPane.add(altaCup);
				
				aggCup = new AgregarActividadDeportivaCuponera(IDC, IADC);
				aggCup.setVisible(false);
				desktopPane.add(aggCup);
				
				consActDep.setRef(consultaClass,consultaCup);
				consultaCup.setRef(consActDep);
				consultaUsu.setRef(consultaClass,consActDep);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// Frame con dimensiones 
		entrenamosUy = new JFrame();
		entrenamosUy.setTitle("entrenamos.uy - Administrador");
		entrenamosUy.setBounds(100, 100, 1200, 700);
		entrenamosUy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		entrenamosUy.setResizable(true);
		entrenamosUy.setIconImage(new ImageIcon(getClass().getResource("/img/entrenamos.png")).getImage());
		
		// El "escritorio"
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(111,188,199));
		entrenamosUy.getContentPane().add(desktopPane);
		
		
		// Crea el JMenuBar (la barra del menu de arriba)
		JMenuBar menuBar = new JMenuBar();
        entrenamosUy.setJMenuBar(menuBar);

        
        
        //////////////////////////////////////////////////////////////
        // Comienza con las opciones del sistema
        JMenu menuSistema = new JMenu("Sistema\r\n");
        menuBar.add(menuSistema);
		
        //Limpia el desktop
        JMenuItem itemLimpiar = new JMenuItem ("Limpiar el desktop");
        menuSistema.add(itemLimpiar);
        itemLimpiar.addActionListener(new ActionListener() {
        	public void actionPerformed (ActionEvent e) {
        		cerrar();
        	}
        });
        
        // Salgo de la aplicación
        JMenuItem itemSalir = new JMenuItem("Salir");
        menuSistema.add(itemSalir);
        itemSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	entrenamosUy.dispose();
            }
        });
       
        ////////////// Fin de menu Sistema ///////////////////////////
        
        
        //////////////////////////////////////////////////////////////
        
        // Comienza con las opciones de registro
        JMenu menuRegistro = new JMenu("Registrar");
		menuBar.add(menuRegistro);
		
		// subMenu de Usuario
		JMenu subMenuUsuario = new JMenu("Usuario");
		menuRegistro.add(subMenuUsuario);
		
		// Caso de uso para Alta Usuario
		JMenuItem itemRegistrarUsuario = new JMenuItem("Alta Usuario");
		subMenuUsuario.add(itemRegistrarUsuario);
		itemRegistrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (altaUsuario.isVisible()) 
					altaUsuario.toFront();
				else {
					altaUsuario.clear();
					altaUsuario.setVisible(true);
				}
			}
		});		
		
		
		// subMenu Institución
		JMenu subMenuInstitucion = new JMenu("Institucion");
		menuRegistro.add(subMenuInstitucion);
		

		JMenuItem itemAltaInstitucion = new JMenuItem("Alta Institucion Deportiva");
		subMenuInstitucion.add(itemAltaInstitucion);
		itemAltaInstitucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (altaIns.isVisible()) 
					altaIns.toFront();
				else {
					altaIns.clear(); 
					altaIns.setVisible(true);
				}
			}
		});
		
		// subMenu Act deportiva
		JMenu subMenuActDep = new JMenu("Actividad Deportiva");
		menuRegistro.add(subMenuActDep);
		
		// Caso de uso para Alta actividad deportiva
		JMenuItem itemAltaActividad = new JMenuItem("Alta Actividad Deportiva");
		subMenuActDep.add(itemAltaActividad);
		itemAltaActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (altaActDep.isVisible()) 
					altaActDep.toFront();
				else {
					altaActDep.clear();
					altaActDep.setVisible(true);
				}
			}
		});		
		
		// subMenu Clase
		JMenu subMenuDictado = new JMenu("Clase");
		menuRegistro.add(subMenuDictado);
		
		// Caso de uso para Alta de dictado de clase
		JMenuItem itemAltaDictado = new JMenuItem("Alta de Dictado de Clase");
		itemAltaDictado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (altaClase.isVisible())
					altaClase.toFront();
				else {
					altaClase.clear(); 
					altaClase.setVisible(true);
				}
			}
		});
		subMenuDictado.add(itemAltaDictado);
		
		
		// Caso de uso para Registro de usuario a dictado de clase
		JMenuItem itemRegistroAClase = new JMenuItem("Registro de Usuario a Dictado de Clase");
		itemRegistroAClase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (regUsuClass.isVisible())
					regUsuClass.toFront();
				else {
					regUsuClass.clear(); 
					regUsuClass.setVisible(true);
				}
			}
		});
		subMenuDictado.add(itemRegistroAClase);
		
		// subMenu Cuponera
		JMenu subMenuCuponera = new JMenu("Cuponera");
		menuRegistro.add(subMenuCuponera);
		
		// Caso de uso para Crear una cuponera de una act deport
		JMenuItem itemCrearCuponera = new JMenuItem("Crear Cuponera de Actividad Deportiva");
		subMenuCuponera.add(itemCrearCuponera);
		itemCrearCuponera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (altaCup.isVisible()) 
					altaCup.toFront();
				else {
					altaCup.clear(); 
					altaCup.setVisible(true);
				}
			}
		});
		
		// Caso de uso para Agregar Actividad Deportiva a Cuponera
		JMenuItem itemAgregarActividad = new JMenuItem("Agregar Actividad Deportiva a Cuponera");
		subMenuCuponera.add(itemAgregarActividad);
		itemAgregarActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (aggCup.isVisible()) 
					aggCup.toFront();
				else {
					aggCup.clear();
					aggCup.setVisible(true);
				}
			}
		});
		
		//////////////Fin de menu Registrar ///////////////////////////
        
        
		//////////////////////////////////////////////////////////////
		// Comienza con las opciones de consulta
		JMenu menuConsultas = new JMenu("Consultar");
		menuBar.add(menuConsultas);
		
		// Caso de uso para Consultar Usuario
		JMenuItem itemConsultaUsuario = new JMenuItem("Consulta de Usuario");
		menuConsultas.add(itemConsultaUsuario);
		itemConsultaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (consultaUsu.isVisible()) 
					consultaUsu.toFront();
				else {
					consultaUsu.clear(); //Descomentar cuando este implementado en el caso de uso
					consultaUsu.setVisible(true);
				}
			}
		});
		
		// Caso de uso para Consulta de Actividad Deportiva
		JMenuItem itemConsultaActividad = new JMenuItem("Consulta de Actividad Deportiva");
		menuConsultas.add(itemConsultaActividad);
		itemConsultaActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (consActDep.isVisible()) 
					consActDep.toFront();
				else {
					consActDep.clear(); 
					consActDep.setVisible(true);
				}
			}
		});
		
		// Caso de uso para Consulta de Dictado de Clase
		JMenuItem itemConsultaClase = new JMenuItem("Consulta de Dictado de Clase");
		menuConsultas.add(itemConsultaClase);
		itemConsultaClase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (consultaClass.isVisible()) 
					consultaClass.toFront();
				else {
					consultaClass.clear(); 
					consultaClass.setVisible(true);
				}
			}
		});
		
		// Caso de uso para Consulta de Cuponeras de Actividades Deportivas
		JMenuItem itemConsultaCuponera = new JMenuItem("Consulta de Cuponeras de Actividades Deportivas");
		menuConsultas.add(itemConsultaCuponera);
		itemConsultaCuponera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (consultaCup.isVisible())
					consultaCup.toFront();
				else {
					//altaClase.limpiar() //Verificar luego
					consultaCup.setVisible(true);
				}
			}
		});
		
		//////////////Fin de menu Consultar ///////////////////////////
        
        
		//////////////////////////////////////////////////////////////
		// Comienza con las opciones de modificación
		JMenu menuModificaciones = new JMenu("Modificar");
		menuBar.add(menuModificaciones);
		
		// Caso de uso para Modificar Datos de Usuario
		JMenuItem itemModUsuario = new JMenuItem("Modificar Datos de Usuario");
		menuModificaciones.add(itemModUsuario);
		itemModUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (modificarUsu.isVisible()) 
					modificarUsu.toFront();
				else {
					modificarUsu.clear();
					modificarUsu.setVisible(true);
				}
			}
		});
	}


	// Funcion para limpiar el desktop
	private void cerrar() {
		altaUsuario.setVisible(false);
		altaActDep.setVisible(false);
		altaClase.setVisible(false);
		altaIns.setVisible(false);
	    altaCup.setVisible(false);
		regUsuClass.setVisible(false);
		consultaClass.setVisible(false);
		consActDep.setVisible(false);
		consultaCup.setVisible(false);
		consultaUsu.setVisible(false);
		modificarUsu.setVisible(false);
		aggCup.setVisible(false);
	}
	
}

