package main;



import java.awt.EventQueue;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import datatypes.DtActividadDeportiva;
import datatypes.DtClase;
import datatypes.DtFechaHora;
import datatypes.DtProfesor;
import datatypes.DtSocio;

import datatypes.tipoRegistro;
import logica.Fabrica;
import logica.IcontroladorUsuario;
import logica.IcontroladorActividadDeportiva;
import logica.IcontroladorClase;
import logica.IcontroladorCuponera;
import logica.persistencia.DataPersistencia;
import presentacion.Principal;

public class Main {
	private static IcontroladorActividadDeportiva IADC;
	private static IcontroladorUsuario IUC;
	private static IcontroladorCuponera ICC;
	private static IcontroladorClase IDCC;
	public static Properties config;
    /**
     * @param args the command line arguments
     */
	 public static void main(String[] args) {
	    	Set<String> flags = new HashSet<>();
	    	flags.addAll(Arrays.asList(args));

	    	if(!flags.contains("--empty")) {
	    		try {
	    			// Nukeamos la persistencia.
	    			DataPersistencia.getInstance().nuketownDetonator();
	    			// cargaDeCasos();
	    		} catch(Exception e) {
	    			System.out.println("Oh noes! Ha habido un problema al cargar los casos de prueba. ");
	    			e.printStackTrace();
	    		}
	    	}
	    	
	    	
	    	
	    	if(!flags.contains("--adm-only")) {
		        System.out.println("Iniciando puesto de trabajo del administrador...");
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Principal window = new Principal();
							window.getEntrenamosUy().setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
	    	}
	    }


	    
	    public static void cargaDeCasos() throws Exception{
			try {
				Fabrica fabricaSistema = Fabrica.getInstance();
				IADC = fabricaSistema.obtenerIcontroladorActDeportiva();
				IUC = fabricaSistema.obtenerIcontroladorUsuario();
				ICC = fabricaSistema.obtenerIcontroladorCuponera();
				IDCC = fabricaSistema.obtenerIcontroladorDictadoClase();
				
				// ALTA INSTITUCIONES
				// Instituto Natural #IN
				IADC.altaInstitucion("Instituto Natural",  "Clases de gimnasia,  aeróbica,  spinning y yoga.", "https://www.inatural.com");
				// Fuerza Bruta #FB
				IADC.altaInstitucion("Fuerza Bruta",  "Gimnasio especializado en el desarrollo de la musculatura.", "https://www.musculos.com");
				// Telón #TL
				IADC.altaInstitucion("Telón",  "Actividades deportivas para todas las edades.", "https://telon.com.uy");
				// Olympic #YT
				IADC.altaInstitucion("Olympic",  "Gimnasia y Aparatos.", "https://www.olympic21.com");
				
				// ALTA USUARIOS
	    
				// SOCIOS

				// Emi71 #EL
				IUC.ingresarDatosUsuario(new DtSocio("Emi71", "Emiliano", "Lucas", "emi71@gmail.com",  new DtFechaHora(1971, 12, 31, 0, 0, 0),  "Emi71.jpg".getBytes()));
				// caro #CO
				IUC.ingresarDatosUsuario(new DtSocio("caro", "Carolina", "Omega", "caro@gmail.com",  new DtFechaHora(1983, 11, 15, 0, 0, 0),  "caro.jpg".getBytes()));
				// euge #EW
				IUC.ingresarDatosUsuario(new DtSocio("euge", "Eugenia", "Williams", "e.will@gmail.com",  new DtFechaHora(1990, 4, 15, 0, 0, 0),  "euge.jpg".getBytes()));
				// guille #GH
				IUC.ingresarDatosUsuario(new DtSocio("guille", "Guillermo", "Hector", "ghector@gmail.com",  new DtFechaHora(1959, 5, 15, 0, 0, 0),  "guille.jpg".getBytes()));
				// sergiop #SP
				IUC.ingresarDatosUsuario(new DtSocio("sergiop", "Sergio", "Perez", "sergi@gmail.com.uy",  new DtFechaHora(1950, 1, 28, 0, 0, 0),  "sergiop.jpg".getBytes()));
				// andy #AR
				IUC.ingresarDatosUsuario(new DtSocio("andy", "Andrés", "Roman", "chino@gmail.org.uy",  new DtFechaHora(1976, 3, 17, 0, 0, 0),  "andy.jpg".getBytes()));
				// tonyp #AP
				IUC.ingresarDatosUsuario(new DtSocio("tonyp", "Antonio", "Paz", "eltony@gmail.org.uy",  new DtFechaHora(1955, 2, 14, 0, 0, 0),  "tonyp.jpg".getBytes()));
				// m1k4 #ML
				IUC.ingresarDatosUsuario(new DtSocio("m1k4", "Micaela", "Lopez", "mika@gmail.com.ar",  new DtFechaHora(1987, 2, 23, 0, 0, 0),  "m1k4.jpg".getBytes()));
				// charly #CB
				IUC.ingresarDatosUsuario(new DtSocio("charly", "Carlos", "Boston", "charly@gmail.com.uy",  new DtFechaHora(1937, 5, 8, 0, 0, 0),  "charly.jpg".getBytes()));	

				
				// PROFESORES
				String desc;
				String bio;
				// viktor #VP
				desc = "Victor es un apasionado de los músculos. Sus clases son organizadas en función de distintos "
									+ "aparatos y pesas con el objetivo de desarrollar músculos.";
				bio = "Victor nació en Moscow en 1977. En el año 2005 emigró a Uruguay luego de quedar "
									+ "encantado con el país en un viaje turístico.";
				IUC.ingresarDatosUsuario(new DtProfesor("viktor", "Victor", "Perez", "vperez@fuerza.com",   new DtFechaHora(1977, 1, 1, 0, 0, 0), 
									"Fuerza Bruta",  desc,  bio , "www.vikgym.com", "viktor.jpg".getBytes()));
				// denis #DM
				desc = "A Denis le interesan los deportes con pelota,  principalmente el voleibol y el handball.";
				bio = "Denis fue un jugador de voleibol profesional.";
				IUC.ingresarDatosUsuario(new DtProfesor("denis", "Denis", "Miguel", "den80@fuerza.com",  new DtFechaHora(1980, 6, 14, 0, 0, 0), 
									"Telón",  desc,  bio , "www.depecho.com",  "denis.jpg".getBytes()));
				// clazar #CL
				desc = "Carlos es un profesor muy divertido cuyas clases de aeróbica están cargadas de energía.";
				bio = "El interés por la actividad física llevo a Carlos a dejar su trabajo en un estudio "
									+ "contable y abrir su propio gimnasio.";
				IUC.ingresarDatosUsuario(new DtProfesor("clazar", "Carlos", "Lazaro", "claz4r0@hotmail.com",  new DtFechaHora(1953, 6, 22, 0, 0, 0), 
									"Instituto Natural",  desc,  bio , "www.enforma.com", "clazar.jpg".getBytes()));
				// TheBoss #BS
				desc = "Bruno es un ex-boxeardor que busca entrenar a futuros campeones.";
				bio = "Bruno,  mejor conocido como Bruce en el ring,  compitió como boxeador entre los años 60s y 70s.";
				IUC.ingresarDatosUsuario(new DtProfesor("TheBoss", "Bruno", "Sosa", "bruceTheBoss@gmail.com",  new DtFechaHora(1949, 9, 23, 0, 0, 0), 
									"Fuerza Bruta",  desc,  bio , "www.bruce.net", "TheBoss.jpg".getBytes()));
				// Nelson #TN
				desc = "Profesor de natación. Especializado en braza y mariposa.";
				bio = "";
				IUC.ingresarDatosUsuario(new DtProfesor("Nelson", "Luis", "Nelson", "nelson@hotmail.com",  new DtFechaHora(1998, 1, 1, 0, 0, 0), 
									"Telón",  desc,  bio , "www.nelson.uy", "Nelson.jpg".getBytes()));
				// lale #LL
				desc = "Luego de una exitosa carrera como jugadora de futbol profesional. Laura dedica sus clases a "
									+ "enseñar tácticas de futbol.";
				bio = "Jugadora profesional de futbol desde 2010 a 2020.";
				IUC.ingresarDatosUsuario(new DtProfesor("lale", "Laura", "Leyes", "la_le@outlook.com",  new DtFechaHora(1987, 2, 14, 0, 0, 0), 
									"Telón",  desc,  bio , "www.laley.com",  "lale.jpg".getBytes()));
				// prisc #PI
				desc = "Laura tiene un gran interés por los deportes olímpicos.";
				bio = "";
				IUC.ingresarDatosUsuario(new DtProfesor("prisc", "Priscila", "Pappo", "pripa@gmail.com",  new DtFechaHora(1981, 8, 13, 0, 0, 0), 
									"Olympic",  desc,  bio , "www.pi314.net", null));
				// dagost #DY
				desc = "Profesora dedicada y exigente. No acepta un " + '"' + "no puedo" + '"' + " como respuesta.";
				bio = "";
				IUC.ingresarDatosUsuario(new DtProfesor("dagost", "Daiana", "Agostini", "d_1940_ago@gmail.com",  new DtFechaHora(1940, 3, 5, 0, 0, 0), 
									"Olympic",  desc,  bio , "www.dygym.com", "dagost.jpg".getBytes()));
				// aldo #AL
				desc = "Dada su gran estatura Aldo siempre jugó al basquetbol,  hoy se dedica a enseñarlo.";
				bio = "";
				IUC.ingresarDatosUsuario(new DtProfesor("aldo", "Aldo", "Vivaldi", "aldo@outlook.com",  new DtFechaHora(1952, 7, 17, 0, 0, 0), 
									"Telón",  desc,  bio , "www.sportsaldo.net", "aldo.jpg".getBytes()));

				
				// ALTA ACTIVIDAD DEPORTIVA
		        // Aparatos y pesas #A1
				IADC.ingresarDatosActividadDep("Fuerza Bruta",  new DtActividadDeportiva("Aparatos y pesas", 
						"Clases de aparatos,  pesas y calistenia.",  90,  550,  new DtFechaHora(2021, 3, 31, 0, 0, 0)));
				// Voleibol #A2
				IADC.ingresarDatosActividadDep("Telón",  new DtActividadDeportiva("Voleibol", 
						"Voleibol en todas sus formas.",  120,  750,  new DtFechaHora(2021, 4, 20, 0, 0, 0)));
				// Aeróbica #A3
				IADC.ingresarDatosActividadDep("Instituto Natural",  new DtActividadDeportiva("Aeróbica", 
						"Para cuidar el aparato cardiovascular.",  110,  800,  new DtFechaHora(2021, 5, 30, 0, 0, 0)));
				// Kickboxing #A4
				IADC.ingresarDatosActividadDep("Fuerza Bruta",  new DtActividadDeportiva("Kickboxing", 
						"En busca del nuevo campeón de boxeo.",  100,  980,  new DtFechaHora(2021, 6, 7, 0, 0, 0)));
				// Atletismo #A5
				IADC.ingresarDatosActividadDep("Telón",  new DtActividadDeportiva("Atletismo", 
						"100m ,  200m,  postas y carreras con obstaculos.",  150,  500,  new DtFechaHora(2021, 7, 8, 0, 0, 0)));
				// Basquetbol #A6
				IADC.ingresarDatosActividadDep("Telón",  new DtActividadDeportiva("Basquetbol", 
						"Espectáculo conmemorando los 30 años de Violeta.",  80,  450,  new DtFechaHora(2021, 7, 31, 0, 0, 0)));

			
		        // ALTA CLASE
		        // Calistenia #C1 -
		        IDCC.ingresarDatosClase("Fuerza Bruta",  "Aparatos y pesas",  new DtClase("Calistenia",  "viktor",  "vperez@fuerza.com",  
		        		1,  5,  "https://www.musculos.com/Calistenia",  new DtFechaHora(2021, 4, 15, 15, 30, 0),  new DtFechaHora(2021, 3, 31, 0, 0, 0)));
		        // Peso libre #C2
		        IDCC.ingresarDatosClase("Fuerza Bruta",  "Aparatos y pesas",  new DtClase("Peso libre",  "viktor",  "viktor",  
		        		1,  5,  "https://www.musculos.com/pesolibre",  new DtFechaHora(2021, 5, 1, 17, 0, 0),  new DtFechaHora(2021, 3, 31, 0, 0, 0)));
		        // Aparatos #C3
		        IDCC.ingresarDatosClase("Fuerza Bruta",  "Aparatos y pesas",  new DtClase("Aparatos",  "viktor",  "viktor",  
		        		1,  7,  "https://www.musculos.com/aparatos",  new DtFechaHora(2021, 6, 1, 18, 0, 0),  new DtFechaHora(2021, 3, 31, 0, 0, 0)));
		        // Voleibol #C4
		        IDCC.ingresarDatosClase("Telón",  "Voleibol",  new DtClase("Voleibol",  "denis",  "denis", 
		        		10,  21,  "https://telon.com.uy/voley",  new DtFechaHora(2021, 6, 10, 19, 0, 0),  new DtFechaHora(2021, 4, 20, 0, 0, 0)));
		        // Braza #C5
		        IDCC.ingresarDatosClase("Telón",  "Voleibol",  new DtClase("Braza",  "Nelson",  "Nelson", 
		        		2,  6,  "https://telon.com.uy/natacionB",  new DtFechaHora(2021, 7, 10, 20, 0, 0),  new DtFechaHora(2021, 4, 20, 0, 0, 0)));
		        // Mariposa #C6
		        IDCC.ingresarDatosClase("Telón",  "Voleibol",  new DtClase("Mariposa",  "Nelson",  "Nelson", 
		        		2,  6,  "https://telon.com.uy/natacionM",  new DtFechaHora(2021, 8, 10, 17, 45, 0),  new DtFechaHora(2021, 4, 20, 0, 0, 0)));
		        // Aeróbica niños #C7
		        IDCC.ingresarDatosClase("Instituto Natural",  "Aeróbica",  new DtClase("Aeróbica niños",  "clazar",  "clazar", 
		        		5,  10,  "https://www.inatural.com/aeroni",  new DtFechaHora(2021, 8, 15, 16, 30, 0),  new DtFechaHora(2021, 5, 30, 0, 0, 0)));
		        // Aeróbico adulto mayor #C8
		        IDCC.ingresarDatosClase("Instituto Natural",  "Aeróbica",  new DtClase("Aeróbico adulto mayor",  "clazar",  "clazar", 
		        		5,  12,  "https://www.inatural.com/aeroam",  new DtFechaHora(2021, 8, 31, 19, 30, 0),  new DtFechaHora(2021, 5, 30, 0, 0, 0)));
		        // Aeróbico #C9
		        IDCC.ingresarDatosClase("Instituto Natural",  "Aeróbica",  new DtClase("Aeróbica",  "clazar",  "clazar", 
		        		5,  20,  "https://www.inatural.com/aerogral",  new DtFechaHora(2021, 9, 30, 20, 0, 0),  new DtFechaHora(2021, 5, 30, 0, 0, 0)));
		        // Boxeo I #C10
		        IDCC.ingresarDatosClase("Fuerza Bruta",  "Kickboxing",  new DtClase("Boxeo I",  "TheBoss",  "TheBoss", 
		        		1,  4,  "https://www.musculos.com/boxeo1",  new DtFechaHora(2021, 9, 1, 19, 30, 0),  new DtFechaHora(2021, 6, 7, 0, 0, 0)));
		        // Boxeo II #C11
		        IDCC.ingresarDatosClase("Fuerza Bruta",  "Kickboxing",  new DtClase("Boxeo II",  "TheBoss",  "TheBoss", 
		        		2,  2,  "https://www.musculos.com/boxeo2",  new DtFechaHora(2021, 9, 30, 17, 0, 0),  new DtFechaHora(2021, 6, 7, 0, 0, 0)));
		        // Músculos para boxeo #C12
		        IDCC.ingresarDatosClase("Fuerza Bruta",  "Kickboxing",  new DtClase("Músculos para boxeo",  "viktor",  "viktor", 
		        		1,  5,  "https://www.musculos.com/muscbox",  new DtFechaHora(2021, 10, 15, 20, 0, 0),  new DtFechaHora(2021, 6, 7, 0, 0, 0)));
		        // 100 M #C13
		        IDCC.ingresarDatosClase("Telón",  "Atletismo",  new DtClase("100 M",  "lale",  "lale", 
		        		3,  10,  "https://telon.com.uy/100m",  new DtFechaHora(2021, 9, 25, 19, 0, 0),  new DtFechaHora(2021, 7, 8, 0, 0, 0)));
		        // 200 M #C14
		        IDCC.ingresarDatosClase("Telón",  "Atletismo",  new DtClase("200 M",  "lale",  "lale", 
		        		3,  10,  "https://telon.com.uy/200m",  new DtFechaHora(2021, 11, 5, 18, 30, 0),  new DtFechaHora(2021, 7, 8, 0, 0, 0)));
		        // Posta #C15
		        IDCC.ingresarDatosClase("Telón",  "Atletismo",  new DtClase("Posta",  "lale",  "lale", 
		        		8,  16,  "https://telon.com.uy/posta",  new DtFechaHora(2021, 11, 29, 17, 45, 0),  new DtFechaHora(2021, 7, 8, 0, 0, 0)));
		        // Basquet I #C16
		        IDCC.ingresarDatosClase("Telón",  "Basquetbol",  new DtClase("Basquet I",  "aldo",  "aldo", 
		        		10,  15,  "https://telon.com.uy/bball1",  new DtFechaHora(2021, 11, 3, 21, 0, 0),  new DtFechaHora(2021, 7, 31, 0, 0, 0)));
		        // Basquet II #C17
		        IDCC.ingresarDatosClase("Telón",  "Basquetbol",  new DtClase("Basquet II",  "aldo",  "aldo", 
		        		10,  10,  "https://telon.com.uy/bball2",  new DtFechaHora(2021, 11, 20, 21, 0, 0),  new DtFechaHora(2021, 7, 31, 0, 0, 0)));
		           
		        // CUPONERAS 	
		        // Pelota #P1
		        ICC.ingresarCuponera("Pelota",  "Deportes con pelota.",  new DtFechaHora(2021, 5, 1, 0, 0, 0),  new DtFechaHora(2021, 7, 31, 23, 59, 59),  
		        		20,  new DtFechaHora(2021, 4, 30, 0, 0, 0));
		        ICC.agregarActividadCuponera("Pelota",  "Telón",  "Voleibol",  7);
		        ICC.agregarActividadCuponera("Pelota",  "Telón",  "Basquetbol",  18);
		        // Gimnasia #P2
		        ICC.ingresarCuponera("Gimnasia",  "Aeróbica y aparatos.",  new DtFechaHora(2021, 8, 1, 0, 0, 0),  new DtFechaHora(2021, 9, 30, 23, 59, 59),  
		        		30,  new DtFechaHora(2021, 7, 15, 0, 0, 0));
		        ICC.agregarActividadCuponera("Gimnasia",  "Instituto Natural",  "Aeróbica",  2);
		        ICC.agregarActividadCuponera("Gimnasia",  "Fuerza Bruta",  "Aparatos y pesas",  8);
		        // Músculos #P3
		        ICC.ingresarCuponera("Músculos",  "Pesas.",  new DtFechaHora(2021, 8, 15, 0, 0, 0),  new DtFechaHora(2021, 12, 15, 23, 59, 59),  
		        		10,  new DtFechaHora(2021, 8, 1, 0, 0, 0));
		        ICC.agregarActividadCuponera("Músculos",  "Fuerza Bruta",  "Kickboxing",  11);
		        ICC.agregarActividadCuponera("Músculos",  "Fuerza Bruta",  "Aparatos y pesas",  12);
		        
		        // COMPRA CUPONERAS
		        IUC.comprarCuponera("Pelota", "guille", new DtFechaHora());
		        IUC.comprarCuponera("Gimnasia", "m1k4", new DtFechaHora());
		        IUC.comprarCuponera("Gimnasia", "caro", new DtFechaHora());
		        IUC.comprarCuponera("Músculos", "sergiop", new DtFechaHora());
		        IUC.comprarCuponera("Músculos", "andy", new DtFechaHora());
		        
		        
		        // REGISTRO A CLASE
	        	// #R1
	        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Calistenia",  "caro",  tipoRegistro.general,  
	        			new DtFechaHora(2021, 4, 9, 0, 0, 0));
	        	// #R2
	        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Calistenia",  "sergiop",  tipoRegistro.general,  
	        			new DtFechaHora(2021, 4, 10, 0, 0, 0));
	        	// #R3
	        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Calistenia",  "andy",  tipoRegistro.general,  
	        			new DtFechaHora(2021, 4, 12, 0, 0, 0));
	        	// #R4
	        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Peso libre",  "andy",  tipoRegistro.general,  
	        			new DtFechaHora(2021, 4, 15, 0, 0, 0));
	        	// #R5
	        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Peso libre",  "tonyp",  tipoRegistro.general,  
	        			new DtFechaHora(2021, 4, 20, 0, 0, 0));
	        	// #R6
	        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Peso libre",  "caro",  tipoRegistro.general,  
	        			new DtFechaHora(2021, 4, 25, 0, 0, 0));
	        	// #R7
	        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Peso libre",  "m1k4",  tipoRegistro.general,  
	        			new DtFechaHora(2021, 4, 28, 0, 0, 0));
	        	// #R8
	        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Aparatos",  "charly",  tipoRegistro.general,  
	        			new DtFechaHora(2021, 4, 16, 0, 0, 0));
	        	// #R9
	        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Aparatos",  "caro",  tipoRegistro.general,  
	        			new DtFechaHora(2021, 5, 14, 0, 0, 0));
	        	// #R10
	        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Aparatos",  "m1k4",  tipoRegistro.general,  
	        			new DtFechaHora(2021, 5, 20, 0, 0, 0));
	        	// #R11
	        	IDCC.inscribirSocio("Telón",  "Voleibol",  "Voleibol",  "Emi71",  tipoRegistro.general,  
	        			new DtFechaHora(2021, 5, 5, 0, 0, 0));
	        	// #R12
	        	IDCC.inscribirSocio("Telón",  "Voleibol",  "Voleibol",  "euge",  tipoRegistro.general,  
	        			new DtFechaHora(2021, 5, 10, 0, 0, 0));
	        	// #R13
	        	IDCC.inscribirSocio("Telón",  "Voleibol",  "Voleibol",  "sergiop",  tipoRegistro.general,  
	        			new DtFechaHora(2021, 5, 15, 0, 0, 0));
				// #R14
				IDCC.inscribirSocio("Telón",  "Voleibol",  "Voleibol",  "tonyp",  tipoRegistro.general,  
						new DtFechaHora(2021, 5, 20, 0, 0, 0));
				// #R15
				IDCC.inscribirSocio("Telón",  "Voleibol",  "Braza",  "guille",  tipoRegistro.general,  
						new DtFechaHora(2021, 6, 8, 0, 0, 0));
				// #R16
				IDCC.inscribirSocio("Telón",  "Voleibol",  "Braza",  "euge",  tipoRegistro.general,  
						new DtFechaHora(2021, 6, 13, 0, 0, 0));
				// #R17
				IDCC.inscribirSocio("Telón",  "Voleibol",  "Braza",  "m1k4",  tipoRegistro.general,  
						new DtFechaHora(2021, 6, 25, 0, 0, 0));
				// #R18
				IDCC.inscribirSocio("Telón",  "Voleibol",  "Mariposa",  "charly",  tipoRegistro.general,  
						new DtFechaHora(2021, 7, 5, 0, 0, 0));
				// #R19
				IDCC.inscribirSocio("Telón",  "Voleibol",  "Mariposa",  "sergiop",  tipoRegistro.general,  
						new DtFechaHora(2021, 7, 11, 0, 0, 0));
				// #R20
				IDCC.inscribirSocio("Telón",  "Voleibol",  "Mariposa",  "andy",  tipoRegistro.general,  
						new DtFechaHora(2021, 7, 18, 0, 0, 0));
				// #R21 //cambiado tipoRegistro
				IDCC.inscribirSocio("Instituto Natural",  "Aeróbica",  "Aeróbica niños",  "m1k4",  tipoRegistro.general,  
						new DtFechaHora(2021, 8, 1, 0, 0, 0));
				// #R22
				IDCC.inscribirSocio("Instituto Natural",  "Aeróbica",  "Aeróbico adulto mayor",  "Emi71",  tipoRegistro.general,  
						new DtFechaHora(2021, 8, 17, 0, 0, 0));
				// #R23
				IDCC.inscribirSocio("Instituto Natural",  "Aeróbica",  "Aeróbico adulto mayor",  "guille",  tipoRegistro.general,  
						new DtFechaHora(2021, 8, 20, 0, 0, 0));
				// #R24
				IDCC.inscribirSocio("Instituto Natural",  "Aeróbica",  "Aeróbico adulto mayor",  "andy",  tipoRegistro.general,  
						new DtFechaHora(2021, 8, 23, 0, 0, 0));
				// #R25 CAMBIADO
				IDCC.inscribirSocio("Instituto Natural",  "Aeróbica",  "Aeróbica",  "caro",  tipoRegistro.general,  
						new DtFechaHora(2021, 8, 15, 0, 0, 0)); // R25 C9 CO 15/08/21 560
				// #R26
				IDCC.inscribirSocio("Instituto Natural",  "Aeróbica",  "Aeróbica",  "euge",  tipoRegistro.general,  
						new DtFechaHora(2021, 8, 26, 0, 0, 0));
				// #R27 CAMBIADO
				IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Boxeo I",  "andy",  tipoRegistro.general,  
						new DtFechaHora(2021, 7, 19, 0, 0, 0));
				// #R28
				IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Boxeo I",  "tonyp",  tipoRegistro.general,  
						new DtFechaHora(2021, 8, 16, 0, 0, 0));
				// #R29
				IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Boxeo I",  "m1k4",  tipoRegistro.general,  
						new DtFechaHora(2021, 8, 24, 0, 0, 0));
				// #R30 CAMBIADO
				IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Boxeo II",  "sergiop",  tipoRegistro.general,  
						new DtFechaHora(2021, 8, 1, 0, 0, 0));
				// #R31
				IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Boxeo II",  "guille",  tipoRegistro.general,  
						new DtFechaHora(2021, 8, 30, 0, 0, 0));
				// #R32
				IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Músculos para boxeo",  "Emi71",  tipoRegistro.general,  
						new DtFechaHora(2021, 8, 16, 0, 0, 0));
				// #R33
				IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Músculos para boxeo",  "caro",  tipoRegistro.general,  
						new DtFechaHora(2021, 8, 16, 0, 0, 0));
				// #R34
				IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Músculos para boxeo",  "euge",  tipoRegistro.general,  
						new DtFechaHora(2021, 9, 1, 0, 0, 0));
				// #R35
				IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Músculos para boxeo",  "sergiop",  tipoRegistro.general,  
						new DtFechaHora(2021, 9, 5, 0, 0, 0));
				// #R36
				IDCC.inscribirSocio("Telón",  "Atletismo",  "100 M",  "guille",  tipoRegistro.general,  
						new DtFechaHora(2021, 8, 16, 0, 0, 0));
				// #R37
				IDCC.inscribirSocio("Telón",  "Atletismo",  "100 M",  "charly",  tipoRegistro.general,  
						new DtFechaHora(2021, 9, 3, 0, 0, 0));
				// #R38
				IDCC.inscribirSocio("Telón",  "Atletismo",  "200 M",  "Emi71",  tipoRegistro.general,  
						new DtFechaHora(2021, 8, 16, 0, 0, 0));
				// #R39
				IDCC.inscribirSocio("Telón",  "Atletismo",  "200 M",  "charly",  tipoRegistro.general,  
						new DtFechaHora(2021, 9, 6, 0, 0, 0));
				// #R40 CAMBIADO
				IDCC.inscribirSocio("Telón",  "Atletismo",  "Posta",  "caro",  tipoRegistro.general,  
						new DtFechaHora(2021, 9, 1, 0, 0, 0));
				// #R41
				IDCC.inscribirSocio("Telón",  "Basquetbol",  "Basquet I",  "sergiop",  tipoRegistro.general,  
						new DtFechaHora(2021, 8, 16, 0, 0, 0));
				// #R42
				IDCC.inscribirSocio("Telón",  "Basquetbol",  "Basquet I",  "Emi71",  tipoRegistro.general,  
						new DtFechaHora(2021, 8, 20, 0, 0, 0));
				// #R43
				IDCC.inscribirSocio("Telón",  "Basquetbol",  "Basquet I",  "tonyp",  tipoRegistro.general,  
						new DtFechaHora(2021, 8, 31, 0, 0, 0));
				// #R44
				IDCC.inscribirSocio("Telón",  "Basquetbol",  "Basquet II",  "andy",  tipoRegistro.general,  
						new DtFechaHora(2021, 8, 16, 0, 0, 0));
				// #R45
				IDCC.inscribirSocio("Telón",  "Basquetbol",  "Basquet II",  "tonyp",  tipoRegistro.general,  
						new DtFechaHora(2021, 8, 20, 0, 0, 0));
				// #R46
				IDCC.inscribirSocio("Telón",  "Basquetbol",  "Basquet II",  "caro",  tipoRegistro.general,  
						new DtFechaHora(2021, 9, 2, 0, 0, 0)); 
				

				
			} catch (Exception e) {
	        	throw e;
			}
	    }
	}