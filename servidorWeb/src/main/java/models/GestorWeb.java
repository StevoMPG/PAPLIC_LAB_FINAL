package models;

import java.util.HashSet;
import java.util.Set;

import logica.Fabrica;
import logica.IcontroladorUsuario;
import logica.IcontroladorActividadDeportiva;
import logica.IcontroladorClase;
import logica.IcontroladorCuponera;
import datatypes.DtActividadDeportiva;
import datatypes.DtCategoria;
import datatypes.DtClase;
import datatypes.DtFechaHora;
import datatypes.DtProfesor;
import datatypes.DtSocio;
import datatypes.DtUsuarioExtra;
import datatypes.tipoEstado;
import datatypes.tipoRegistro;
import excepciones.UsuarioNoExisteException;

public class GestorWeb {
	private static GestorWeb instancia;
	private static IcontroladorActividadDeportiva IADC;
	private static IcontroladorUsuario IUC;
	private static IcontroladorCuponera ICC;
	private static IcontroladorClase IDCC;

	public static GestorWeb getInstance() {
		if (instancia == null)
			instancia = new GestorWeb();
		return instancia;
	}
	
	private GestorWeb() {
		Fabrica fabricaSistema = Fabrica.getInstance();
		IADC = fabricaSistema.obtenerIcontroladorActDeportiva();
		IUC = fabricaSistema.obtenerIcontroladorUsuario();
		ICC = fabricaSistema.obtenerIcontroladorCuponera();
		IDCC = fabricaSistema.obtenerIcontroladorDictadoClase();
		cargaDeCasos();
	}


	public static void cargaDeCasos() {
		try {
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
			IUC.ingresarDatosUsuario(new DtSocio (null, null, null, null, null, null, null));
			
			IUC.ingresarDatosUsuario(new DtSocio ("Emi71", "Emiliano", "Lucas", "emi71@gmail.com",  "1234 ",  new DtFechaHora(1971, 12, 31, 0, 0, 0),  "Emi71.webp".getBytes()));
			// caro #CO
			IUC.ingresarDatosUsuario(new DtSocio("caro", "Carolina", "Omega", "caro@gmail.com",  "1234 ",  new DtFechaHora(1983, 11, 15, 0, 0, 0),  "caro.webp".getBytes()));
			// euge #EW
			IUC.ingresarDatosUsuario(new DtSocio("euge", "Eugenia", "Williams", "e.will@gmail.com",  "1234 ",  new DtFechaHora(1990, 4, 15, 0, 0, 0),  "euge.jpg".getBytes()));
			// guille #GH
			IUC.ingresarDatosUsuario(new DtSocio("guille", "Guillermo", "Hector", "ghector@gmail.com",  "1234 ",  new DtFechaHora(1959, 5, 15, 0, 0, 0),  "guille.jpg".getBytes()));
			// sergiop #SP
			IUC.ingresarDatosUsuario(new DtSocio("sergiop", "Sergio", "Perez", "sergi@gmail.com.uy",  "1234 ",  new DtFechaHora(1950, 1, 28, 0, 0, 0),  "sergiop.jpg".getBytes()));
			// andy #AR
			IUC.ingresarDatosUsuario(new DtSocio("andy", "Andrés", "Roman", "chino@gmail.org.uy",  "1234 ",  new DtFechaHora(1976, 3, 17, 0, 0, 0),  "andy.jpg".getBytes()));
			// tonyp #AP
			IUC.ingresarDatosUsuario(new DtSocio("tonyp", "Antonio", "Paz", "eltony@gmail.org.uy",  "1234 ",  new DtFechaHora(1955, 2, 14, 0, 0, 0),  "tonyp.jpg".getBytes()));
			// m1k4 #ML
			IUC.ingresarDatosUsuario(new DtSocio("m1k4", "Micaela", "Lopez", "mika@gmail.com.ar",  "1234 ",  new DtFechaHora(1987, 2, 23, 0, 0, 0),  "m1k4.webp".getBytes()));
			// charly #CB
			IUC.ingresarDatosUsuario(new DtSocio("charly", "Carlos", "Boston", "charly@gmail.com.uy",  "1234 ",  new DtFechaHora(1937, 5, 8, 0, 0, 0),  "charly.jpg".getBytes()));	
			
			// PROFESORES
			String desc;
			String bio;
			// viktor #VP
			desc = "Victor es un apasionado de los músculos. Sus clases son organizadas en función de distintos "
					+ "aparatos y pesas con el objetivo de desarrollar músculos.";
			bio = "Victor nació en Moscow en 1977. En el año 2005 emigró a Uruguay luego de quedar "
					+ "encantado con el país en un viaje turístico.";
			IUC.ingresarDatosUsuario(new DtProfesor("viktor", "Victor", "Perez", "vperez@fuerza.com",  "1234 ",  new DtFechaHora(1977, 1, 1, 0, 0, 0), 
					"Fuerza Bruta",  desc,  bio , "www.vikgym.com",  "viktor.jpg".getBytes()));
			// denis #DM
			desc = "A Denis le interesan los deportes con pelota,  principalmente el voleibol y el handball.";
			bio = "Denis fue un jugador de voleibol profesional.";
			IUC.ingresarDatosUsuario(new DtProfesor("denis", "Denis", "Miguel", "den80@fuerza.com",  "1234 ",  new DtFechaHora(1980, 6, 14, 0, 0, 0), 
					"Telón",  desc,  bio , "www.depecho.com",  "denis.jpg".getBytes()));
			// clazar #CL
			desc = "Carlos es un profesor muy divertido cuyas clases de aeróbica están cargadas de energía.";
			bio = "El interés por la actividad física llevo a Carlos a dejar su trabajo en un estudio "
					+ "contable y abrir su propio gimnasio.";
			IUC.ingresarDatosUsuario(new DtProfesor("clazar", "Carlos", "Lazaro", "claz4r0@hotmail.com",  "1234 ",  new DtFechaHora(1953, 6, 22, 0, 0, 0), 
					"Instituto Natural",  desc,  bio , "www.enforma.com",  "clazar.jpg".getBytes()));
			// TheBoss #BS
			desc = "Bruno es un ex-boxeardor que busca entrenar a futuros campeones.";
			bio = "Bruno,  mejor conocido como Bruce en el ring,  compitió como boxeador entre los años 60s y 70s.";
			IUC.ingresarDatosUsuario(new DtProfesor("TheBoss", "Bruno", "Sosa", "bruceTheBoss@gmail.com",  "1234 ",  new DtFechaHora(1949, 9, 23, 0, 0, 0), 
					"Fuerza Bruta",  desc,  bio , "www.bruce.net",  "TheBoss.jpg".getBytes()));
			// Nelson #TN
			desc = "Profesor de natación. Especializado en braza y mariposa.";
			bio = "";
			IUC.ingresarDatosUsuario(new DtProfesor("Nelson", "Luis", "Nelson", "nelson@hotmail.com",  "1234 ",  new DtFechaHora(1998, 1, 1, 0, 0, 0), 
					"Telón",  desc,  bio , "www.nelson.uy",  "Nelson.jpg".getBytes()));
			// lale #LL
			desc = "Luego de una exitosa carrera como jugadora de futbol profesional. Laura dedica sus clases a "
					+ "enseñar tácticas de futbol.";
			bio = "Jugadora profesional de futbol desde 2010 a 2020.";
			IUC.ingresarDatosUsuario(new DtProfesor("lale", "Laura", "Leyes", "la_le@outlook.com",  "1234 ",  new DtFechaHora(1987, 2, 14, 0, 0, 0), 
					"Telón",  desc,  bio , "www.laley.com",  "lale.jpg".getBytes()));
			// prisc #PI
			desc = "Laura tiene un gran interés por los deportes olímpicos.";
			bio = "";
			IUC.ingresarDatosUsuario(new DtProfesor("prisc", "Priscila", "Pappo", "pripa@gmail.com",  "1234 ",  new DtFechaHora(1981, 8, 13, 0, 0, 0), 
					"Olympic",  desc,  bio , "www.pi314.net",  null));
			// dagost #DY
			desc = "Profesora dedicada y exigente. No acepta un " + '"' + "no puedo" + '"' + " como respuesta.";
			bio = "";
			IUC.ingresarDatosUsuario(new DtProfesor("dagost", "Daiana", "Agostini", "d_1940_ago@gmail.com",  "1234 ",  new DtFechaHora(1940, 3, 5, 0, 0, 0), 
					"Olympic",  desc,  bio , "www.dygym.com",  "dagost.webp".getBytes()));
			// aldo #AL
			desc = "Dada su gran estatura Aldo siempre jugó al basquetbol,  hoy se dedica a enseñarlo.";
			bio = "";
			IUC.ingresarDatosUsuario(new DtProfesor("aldo", "Aldo", "Vivaldi", "aldo@outlook.com",  "1234 ",  new DtFechaHora(1952, 7, 17, 0, 0, 0), 
					"Telón",  desc,  bio , "www.sportsaldo.net",  "aldo.jpg".getBytes()));
				
			//LOS SEGUIDOS/SEGUIDORES
			String[] a = {"Emi71", "caro", "euge", "guille", "sergiop", "andy", "tonyp", "m1k4", "charly", "viktor", "denis", "clazar", "TheBoss", "Nelson", "lale", 
			              "prisc", "dagost", "aldo"};
			IUC.seguir(a[0],  a[3]);
			IUC.seguir(a[1],  a[2]);
			IUC.seguir(a[1],  a[3]);
			IUC.seguir(a[2],  a[0]);
			IUC.seguir(a[2],  a[1]);
			IUC.seguir(a[2],  a[7]);
			IUC.seguir(a[3],  a[0]);
			IUC.seguir(a[3],  a[1]);
			IUC.seguir(a[3],  a[2]);
			IUC.seguir(a[3],  a[12]);
			IUC.seguir(a[4],  a[2]);
			IUC.seguir(a[4],  a[5]);
			IUC.seguir(a[4],  a[11]);
			IUC.seguir(a[5],  a[1]);
			IUC.seguir(a[5],  a[6]);
			IUC.seguir(a[5],  a[11]);
			IUC.seguir(a[6],  a[1]);
			IUC.seguir(a[6],  a[7]);
			IUC.seguir(a[6],  a[8]);
			IUC.seguir(a[7],  a[4]);
			IUC.seguir(a[7],  a[6]);
			IUC.seguir(a[8],  a[6]);
			IUC.seguir(a[8],  a[13]);
			IUC.seguir(a[9],  a[6]);
			IUC.seguir(a[9],  a[7]);
			IUC.seguir(a[9],  a[11]);
			IUC.seguir(a[9],  a[14]);
			IUC.seguir(a[9],  a[15]);
			IUC.seguir(a[10],  a[0]);
			IUC.seguir(a[10],  a[1]);
			IUC.seguir(a[10],  a[2]);
			IUC.seguir(a[10],  a[3]);
			IUC.seguir(a[10],  a[4]);
			IUC.seguir(a[10],  a[5]);
			IUC.seguir(a[10],  a[6]);
			IUC.seguir(a[10],  a[7]);
			IUC.seguir(a[10],  a[8]);
			IUC.seguir(a[11],  a[1]);
			IUC.seguir(a[11],  a[2]);
			IUC.seguir(a[11],  a[3]);
			IUC.seguir(a[11],  a[12]);
			IUC.seguir(a[12],  a[3]);
			IUC.seguir(a[12],  a[5]);
			IUC.seguir(a[12],  a[7]);
			IUC.seguir(a[13],  a[0]);
			IUC.seguir(a[13],  a[5]);
			IUC.seguir(a[13],  a[6]);
			IUC.seguir(a[13],  a[14]);
			IUC.seguir(a[13],  a[15]);
			IUC.seguir(a[13],  a[16]);
			IUC.seguir(a[14],  a[8]);
			IUC.seguir(a[14],  a[13]);
			IUC.seguir(a[15],  a[8]);
			IUC.seguir(a[15],  a[13]);
			IUC.seguir(a[16],  a[6]);
			IUC.seguir(a[16],  a[8]);
			IUC.seguir(a[17],  a[5]);
			IUC.seguir(a[17],  a[6]);
			IUC.seguir(a[17],  a[8]);
			IUC.seguir(a[17],  a[14]);
			IUC.seguir(a[17],  a[15]);
			IUC.seguir(a[17],  a[16]);
			
			
			//CATEGORIAS
			IADC.ingresarCatergoria(new DtCategoria("Al aire libre"));
			IADC.ingresarCatergoria(new DtCategoria("Deportes"));
			IADC.ingresarCatergoria(new DtCategoria("Fitness"));
			IADC.ingresarCatergoria(new DtCategoria("Gimnasia"));
			
			//CATEGOIRAS DE LAS ACTDEP
			Set<String> A1cat = new HashSet<>(); A1cat.add("Fitness");
			Set<String> A2cat = new HashSet<>(); A2cat.add("Gimnasia"); A2cat.add("Deportes");
			Set<String> A3cat = new HashSet<>(); A3cat.add("Al aire libre");
			Set<String> A4cat = new HashSet<>(); A4cat.add("Deportes");
			Set<String> A5cat = new HashSet<>(); A5cat.add("Deportes");
			Set<String> A6cat = new HashSet<>(); A6cat.add("Deportes");
			Set<String> A7cat = new HashSet<>(); A7cat.add("Fitness");
			Set<String> A8cat = new HashSet<>(); A8cat.add("Gimnasia");
			Set<String> A9cat = new HashSet<>(); A9cat.add("Deportes"); A9cat.add("Al aire libre");
			Set<String> A10cat = new HashSet<>();A10cat.add("Gimnasia");
			
			// ALTA ACTIVIDAD DEPORTIVA
	        // Aparatos y pesas #A1
			IADC.ingresarDatosActividadDep("Fuerza Bruta",  new DtActividadDeportiva("Aparatos y pesas", 
					"Clases de aparatos,  pesas y calistenia.",  90,  550,  new DtFechaHora(2021, 3, 31, 0, 0, 0),  A1cat,  tipoEstado.aceptada,  "viktor", "Aparatos y pesas.jpeg"));
			// Voleibol #A2
			IADC.ingresarDatosActividadDep("Telón",  new DtActividadDeportiva("Voleibol", 
					"Voleibol en todas sus formas.",  120,  750,  new DtFechaHora(2021, 4, 20, 0, 0, 0),  A2cat,  tipoEstado.aceptada,  "denis", "Voleibol.jpeg"));
			// Aeróbica #A3
			IADC.ingresarDatosActividadDep("Instituto Natural",  new DtActividadDeportiva("Aeróbica", 
					"Para cuidar el aparato cardiovascular.",  110,  800,  new DtFechaHora(2021, 5, 30, 0, 0, 0),  A3cat,  tipoEstado.aceptada,  "Administrador", "Aeróbica.jpg"));
			// Kickboxing #A4
			IADC.ingresarDatosActividadDep("Fuerza Bruta",  new DtActividadDeportiva("Kickboxing", 
					"En busca del nuevo campeón de boxeo.",  100,  980,  new DtFechaHora(2021, 6, 7, 0, 0, 0),  A4cat,  tipoEstado.aceptada,  "TheBoss", "Kickboxing.jpg"));
			// Atletismo #A5
			IADC.ingresarDatosActividadDep("Telón",  new DtActividadDeportiva("Atletismo", 
					"100m ,  200m,  postas y carreras con obstaculos.",  150,  500,  new DtFechaHora(2021, 7, 8, 0, 0, 0),  A5cat,  tipoEstado.aceptada,  "denis", "Atletismo.webp"));
			// Basquetbol #A6
			IADC.ingresarDatosActividadDep("Telón",  new DtActividadDeportiva("Basquetbol", 
					"Espectáculo conmemorando los 30 años de Violeta.",  80,  450,  new DtFechaHora(2021, 7, 31, 0, 0, 0),  A6cat,  tipoEstado.aceptada,  "Nelson", "Basquetbol.jpg"));
	        // AparatosII #A7
			IADC.ingresarDatosActividadDep("Fuerza Bruta",  new DtActividadDeportiva("Aparatos II", 
					"Clases de aparatos avanzadas.",  60,  1500,  new DtFechaHora(2021, 8, 15, 0, 0, 0),  A7cat,  tipoEstado.rechazada,  "Administrador"));
			// Pilates #A8
			IADC.ingresarDatosActividadDep("Instituto Natural",  new DtActividadDeportiva("Pilates", 
					"El Método Pilates combina diferentes capacidades físicas.",  45,  600,  new DtFechaHora(2021, 8, 30, 0, 0, 0),  A8cat,  tipoEstado.ingresada,  "clazar"));
			// VoleibolII #A9
			IADC.ingresarDatosActividadDep("Telón",  new DtActividadDeportiva("Voleibol II", 
					"Voleibol avanzado.",  120,  1000,  new DtFechaHora(2021, 9, 1, 0, 0, 0),  A9cat,  tipoEstado.rechazada,  "denis", "Voleibol II.jpeg"));
			// BasquetbolII #A10
			IADC.ingresarDatosActividadDep("Telón",  new DtActividadDeportiva("Basquetbol II", 
					"Basequetbol avanzado.",  80,  600,  new DtFechaHora(2021, 9, 7, 0, 0, 0),  A10cat,  tipoEstado.ingresada,  "denis"));
			
	        // ALTA CLASE
	        // Calistenia #C1
	        IDCC.ingresarDatosClase("Fuerza Bruta",  "Aparatos y pesas",  new DtClase("Calistenia",  "viktor",  "viktor",  
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
	        		8,  16,  "https://telon.com.uy/posta",  new DtFechaHora(2021, 11, 25, 17, 45, 0),  new DtFechaHora(2021, 7, 8, 0, 0, 0)));
	        // Basquet I #C16
	        IDCC.ingresarDatosClase("Telón",  "Basquetbol",  new DtClase("Basquet I",  "aldo",  "aldo", 
	        		10,  15,  "https://telon.com.uy/bball1",  new DtFechaHora(2021, 11, 3, 21, 0, 0),  new DtFechaHora(2021, 7, 31, 0, 0, 0)));
	        // Basquet II #C17
	        IDCC.ingresarDatosClase("Telón",  "Basquetbol",  new DtClase("Basquet II",  "aldo",  "aldo", 
	        		10,  10,  "https://telon.com.uy/bball2",  new DtFechaHora(2021, 11, 21, 21, 0, 0),  new DtFechaHora(2021, 7, 31, 0, 0, 0)));
	        
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
	        // Músculos #P2
	        ICC.ingresarCuponera("Músculos",  "Pesas.",  new DtFechaHora(2021, 8, 15, 0, 0, 0),  new DtFechaHora(2021, 11, 15, 23, 59, 59),  
	        		10,  new DtFechaHora(2021, 8, 1, 0, 0, 0));
	        ICC.agregarActividadCuponera("Músculos",  "Fuerza Bruta",  "Kickboxing",  11);
	        ICC.agregarActividadCuponera("Músculos",  "Fuerza Bruta",  "Aparatos y pesas",  12);
	        
	        // COMPRA CUPONERAS
	        IUC.comprarCuponera("Pelota", "guille", new DtFechaHora());
	        IUC.comprarCuponera("Gimnasia", "m1k4", new DtFechaHora());
	        IUC.comprarCuponera("Gimnasia", "caro", new DtFechaHora());
	        IUC.comprarCuponera("Músculos", "sergiop", new DtFechaHora());
	        IUC.comprarCuponera("Músculos", "andy", new DtFechaHora());
	        IUC.comprarCuponera("Pelota", "Emi71", new DtFechaHora());
	        
	        
	        // REGISTRO A CLASE
        	// #R1
        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Calistenia",  "caro",  tipoRegistro.general,  
        			new DtFechaHora(2021, 4, 9, 0, 0, 0),  null);
        	// #R2
        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Calistenia",  "sergiop",  tipoRegistro.general,  
        			new DtFechaHora(2021, 4, 10, 0, 0, 0),  null);
        	// #R3
        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Calistenia",  "andy",  tipoRegistro.general,  
        			new DtFechaHora(2021, 4, 12, 0, 0, 0),  null);
        	// #R4
        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Peso libre",  "andy",  tipoRegistro.general,  
        			new DtFechaHora(2021, 4, 15, 0, 0, 0),  null);
        	// #R5
        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Peso libre",  "tonyp",  tipoRegistro.general,  
        			new DtFechaHora(2021, 4, 20, 0, 0, 0),  null);
        	// #R6
        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Peso libre",  "caro",  tipoRegistro.general,  
        			new DtFechaHora(2021, 4, 25, 0, 0, 0),  null);
        	// #R7
        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Peso libre",  "m1k4",  tipoRegistro.general,  
        			new DtFechaHora(2021, 4, 28, 0, 0, 0),  null);
        	// #R8
        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Aparatos",  "charly",  tipoRegistro.general,  
        			new DtFechaHora(2021, 4, 16, 0, 0, 0),  null);
        	// #R9
        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Aparatos",  "caro",  tipoRegistro.general,  
        			new DtFechaHora(2021, 5, 14, 0, 0, 0),  null);
        	// #R10
        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Aparatos",  "m1k4",  tipoRegistro.general,  
        			new DtFechaHora(2021, 5, 20, 0, 0, 0),  null);
        	// #R11
        	IDCC.inscribirSocio("Telón",  "Voleibol",  "Voleibol",  "Emi71",  tipoRegistro.general,  
        			new DtFechaHora(2021, 5, 5, 0, 0, 0),  null);
        	// #R12
        	IDCC.inscribirSocio("Telón",  "Voleibol",  "Voleibol",  "euge",  tipoRegistro.general,  
        			new DtFechaHora(2021, 5, 10, 0, 0, 0),  null);
        	// #R13
        	IDCC.inscribirSocio("Telón",  "Voleibol",  "Voleibol",  "sergiop",  tipoRegistro.general,  
        			new DtFechaHora(2021, 5, 15, 0, 0, 0),  null);
			// #R14
			IDCC.inscribirSocio("Telón",  "Voleibol",  "Voleibol",  "tonyp",  tipoRegistro.general,  
					new DtFechaHora(2021, 5, 20, 0, 0, 0),  null);
			// #R15
			IDCC.inscribirSocio("Telón",  "Voleibol",  "Braza",  "guille",  tipoRegistro.general,  
					new DtFechaHora(2021, 6, 8, 0, 0, 0),  null);
			// #R16
			IDCC.inscribirSocio("Telón",  "Voleibol",  "Braza",  "euge",  tipoRegistro.general,  
					new DtFechaHora(2021, 6, 13, 0, 0, 0),  null);
			// #R17
			IDCC.inscribirSocio("Telón",  "Voleibol",  "Braza",  "m1k4",  tipoRegistro.general,  
					new DtFechaHora(2021, 6, 25, 0, 0, 0),  null);
			// #R18
			IDCC.inscribirSocio("Telón",  "Voleibol",  "Mariposa",  "charly",  tipoRegistro.general,  
					new DtFechaHora(2021, 7, 5, 0, 0, 0),  null);
			// #R19
			IDCC.inscribirSocio("Telón",  "Voleibol",  "Mariposa",  "sergiop",  tipoRegistro.general,  
					new DtFechaHora(2021, 7, 11, 0, 0, 0),  null);
			// #R20
			IDCC.inscribirSocio("Telón",  "Voleibol",  "Mariposa",  "andy",  tipoRegistro.general,  
					new DtFechaHora(2021, 7, 18, 0, 0, 0),  null);
			// #R21
			IDCC.inscribirSocio("Instituto Natural",  "Aeróbica",  "Aeróbica niños",  "m1k4",  tipoRegistro.cuponera,  
					new DtFechaHora(2021, 7, 19, 0, 0, 0),  "Gimnasia");
			// #R22
			IDCC.inscribirSocio("Instituto Natural",  "Aeróbica",  "Aeróbico adulto mayor",  "Emi71",  tipoRegistro.general,  
					new DtFechaHora(2021, 8, 17, 0, 0, 0),  null);
			// #R23
			IDCC.inscribirSocio("Instituto Natural",  "Aeróbica",  "Aeróbico adulto mayor",  "guille",  tipoRegistro.general,  
					new DtFechaHora(2021, 8, 20, 0, 0, 0),  null);
			// #R24
			IDCC.inscribirSocio("Instituto Natural",  "Aeróbica",  "Aeróbico adulto mayor",  "andy",  tipoRegistro.general,  
					new DtFechaHora(2021, 8, 23, 0, 0, 0),  null);
			// #R25
			IDCC.inscribirSocio("Instituto Natural",  "Aeróbica",  "Aeróbica",  "caro",  tipoRegistro.cuponera,  
					new DtFechaHora(2021, 8, 15, 0, 0, 0),  "Gimnasia"); // R25 C9 CO 15/08/21 560
			// #R26
			IDCC.inscribirSocio("Instituto Natural",  "Aeróbica",  "Aeróbica",  "euge",  tipoRegistro.general,  
					new DtFechaHora(2021, 8, 26, 0, 0, 0),  null);
			// #R27
			IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Boxeo I",  "andy",  tipoRegistro.cuponera,  
					new DtFechaHora(2021, 7, 19, 0, 0, 0),  "Músculos");
			// #R28
			IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Boxeo I",  "tonyp",  tipoRegistro.general,  
					new DtFechaHora(2021, 8, 16, 0, 0, 0),  null);
			// #R29
			IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Boxeo I",  "m1k4",  tipoRegistro.general,  
					new DtFechaHora(2021, 8, 24, 0, 0, 0),  null);
			// #R30
			IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Boxeo II",  "sergiop",  tipoRegistro.cuponera,  
					new DtFechaHora(2021, 8, 1, 0, 0, 0),  "Músculos");
			// #R31
			IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Boxeo II",  "guille",  tipoRegistro.general,  
					new DtFechaHora(2021, 8, 30, 0, 0, 0),  null);
			// #R32
			IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Músculos para boxeo",  "Emi71",  tipoRegistro.general,  
					new DtFechaHora(2021, 8, 16, 0, 0, 0),  null);
			// #R33
			IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Músculos para boxeo",  "caro",  tipoRegistro.general,  
					new DtFechaHora(2021, 8, 16, 0, 0, 0),  null);
			// #R34
			IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Músculos para boxeo",  "euge",  tipoRegistro.general,  
					new DtFechaHora(2021, 9, 1, 0, 0, 0),  null);
			// #R35
			IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Músculos para boxeo",  "sergiop",  tipoRegistro.general,  
					new DtFechaHora(2021, 9, 5, 0, 0, 0),  null);
			// #R36
			IDCC.inscribirSocio("Telón",  "Atletismo",  "100 M",  "guille",  tipoRegistro.general,  
					new DtFechaHora(2021, 8, 16, 0, 0, 0),  null);
			// #R37
			IDCC.inscribirSocio("Telón",  "Atletismo",  "100 M",  "charly",  tipoRegistro.general,  
					new DtFechaHora(2021, 9, 3, 0, 0, 0),  null);
			// #R38
			IDCC.inscribirSocio("Telón",  "Atletismo",  "200 M",  "Emi71",  tipoRegistro.general,  
					new DtFechaHora(2021, 8, 16, 0, 0, 0),  null);
			// #R39
			IDCC.inscribirSocio("Telón",  "Atletismo",  "200 M",  "charly",  tipoRegistro.general,  
					new DtFechaHora(2021, 9, 6, 0, 0, 0),  null);
			// #R40
			IDCC.inscribirSocio("Telón",  "Atletismo",  "Posta",  "caro",  tipoRegistro.general,  
					new DtFechaHora(2021, 9, 1, 0, 0, 0),  null);
			// #R41
			IDCC.inscribirSocio("Telón",  "Basquetbol",  "Basquet I",  "sergiop",  tipoRegistro.general,  
					new DtFechaHora(2021, 8, 16, 0, 0, 0),  null);
			// #R42
			IDCC.inscribirSocio("Telón",  "Basquetbol",  "Basquet I",  "Emi71",  tipoRegistro.general,  
					new DtFechaHora(2021, 8, 20, 0, 0, 0),  null);
			// #R43
			IDCC.inscribirSocio("Telón",  "Basquetbol",  "Basquet I",  "tonyp",  tipoRegistro.general,  
					new DtFechaHora(2021, 8, 31, 0, 0, 0),  null);
			// #R44
			IDCC.inscribirSocio("Telón",  "Basquetbol",  "Basquet II",  "andy",  tipoRegistro.general,  
					new DtFechaHora(2021, 8, 16, 0, 0, 0),  null);
			// #R45
			IDCC.inscribirSocio("Telón",  "Basquetbol",  "Basquet II",  "tonyp",  tipoRegistro.general,  
					new DtFechaHora(2021, 8, 20, 0, 0, 0),  null);
			// #R46
			IDCC.inscribirSocio("Telón",  "Basquetbol",  "Basquet II",  "caro",  tipoRegistro.general,  
					new DtFechaHora(2021, 9, 2, 0, 0, 0),  null);
		} catch (Exception e) {
        	e.printStackTrace();
		}
	}
	

    public static DtUsuarioExtra buscarUsuario(String nickEmail) throws UsuarioNoExisteException {
        DtUsuarioExtra res;
        try {
            res = IUC.seleccionarUsuario(nickEmail);
        } catch (UsuarioNoExisteException e) {
            res = IUC.seleccionarUsuarioEmail(nickEmail);
        }
        return res;
    }
    
    //Las siguientes funciones te parmiten escribir menos al invocar funciones del modelo (lógica)
    public static IcontroladorActividadDeportiva getIADC() {
    	return IADC;
    }
    public static IcontroladorUsuario getIUC() {
    	return IUC;
    }
    public static IcontroladorCuponera getICC() {
    	return ICC;
    }
    public static IcontroladorClase getIDCC() {
    	return IDCC;
    }
}