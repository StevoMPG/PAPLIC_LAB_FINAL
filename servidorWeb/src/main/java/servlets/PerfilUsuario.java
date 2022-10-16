package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datatypes.DtActividadDeportivaExtra;
import datatypes.DtClaseExtra;
import datatypes.DtCuponera;
import datatypes.DtProfesorExtra;
import datatypes.DtSocioExtra;
import datatypes.DtUsuarioExtra;
import logica.IcontroladorActividadDeportiva;
import logica.IcontroladorCuponera;
import logica.IcontroladorClase;
import logica.IcontroladorUsuario;
import logica.Fabrica;
import tools.Parametrizer;

// Servlet login. Obedece el protoclo inicio sesión.
// Si la combinación tiene exito. El servlet establece como atributo de sesión al usuario.
@WebServlet ("/usuarios")
public class PerfilUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IcontroladorActividadDeportiva IADC;
	private IcontroladorUsuario IUC;
	private IcontroladorCuponera ICC;
	private IcontroladorClase IDCC;
    public PerfilUsuario() {
        super();
        IUC = Fabrica.getInstance().obtenerIcontroladorUsuario();
        IADC = Fabrica.getInstance().obtenerIcontroladorActDeportiva();
        ICC = Fabrica.getInstance().obtenerIcontroladorCuponera();
        IDCC = Fabrica.getInstance().obtenerIcontroladorDictadoClase();
    }
    
    protected void processRequest(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	Parametrizer.loadStdRequests(request);
        try {
        	//Obtención de datos de usuario
        	String nickname = (String) request.getParameter("nickname");
        	if (nickname.equals("Administrador")) {
            	response.sendRedirect(request.getContextPath() + "/pages/403.jsp");
            	return;
        	}
        	DtUsuarioExtra usr = IUC.seleccionarUsuario(nickname);
        	
        	//Obtención de clases dictadas (Profesor)
        	List<DtClaseExtra> clasesDictadasProfesor = new ArrayList<>();
        	if (usr instanceof DtProfesorExtra) {
        		Set<String> clases = ((DtProfesorExtra)usr).getClasesDictadas();
        		for (String x : clases) {
        			clasesDictadasProfesor.add(IDCC.buscarClase(x));
        		}
        	}
        	
        	//Obtención de clases a las que se está inscripto (Socio)
        	List<DtClaseExtra> clasesInscriptoSocio = new ArrayList<>();
        	if (usr instanceof DtSocioExtra) {
        		Set<String> clases = ((DtSocioExtra)usr).getClases();
        		for (String x : clases) {
        			clasesInscriptoSocio.add(IDCC.buscarClase(x));
        		}
        	}
        	
        	//Obtención de seguidores
        	List<DtUsuarioExtra> seguidores = new ArrayList<>();
    		Set<String> seguidoresNick = usr.getSeguidoresNickname();
    		for (String x : seguidoresNick) {
    			seguidores.add(IUC.seleccionarUsuario(x));
        	}
        	
    		//Obtención de seguidores
        	List<DtUsuarioExtra> seguidos = new ArrayList<>();
    		Set<String> seguidosNick = usr.getSeguidosNickname();
    		for (String x : seguidosNick) {
    			seguidos.add(IUC.seleccionarUsuario(x));
        	}
    		
    		//Obtención de cuponeras
        	List<DtCuponera> cuponerasIngresadasSocio = new ArrayList<>();
        	if (usr instanceof DtSocioExtra) {
        		Set<String> cuponeras = ((DtSocioExtra)usr).getCuponerasCompradas();
        		for (String x : cuponeras) {
        			cuponerasIngresadasSocio.add(ICC.seleccionarCuponera(x));
        		}
        	}
        	
        	//Obtención de actividades asociadas
        	List<DtActividadDeportivaExtra> actAsociadasProfesor = new ArrayList<>();
        	if (usr instanceof DtProfesorExtra) {
        		Set<String> actividades = ((DtProfesorExtra)usr).getActividadesDepAsociadas();
        		for (String x : actividades) {	
        			actAsociadasProfesor.add(IADC.getActDepExt(((DtProfesorExtra)usr).getNombreInstitucion(),  x));
        		}
        	}
        	
        	//Obtención de actividades ingresadas
        	List<DtActividadDeportivaExtra> actIngresadasProfesor = new ArrayList<>();
        	if (usr instanceof DtProfesorExtra) {
        		Set<String> actividades = ((DtProfesorExtra)usr).getActividadesIngresadas();
        		for (String x : actividades) {
        			actIngresadasProfesor.add(IADC.getActDepExt(((DtProfesorExtra)usr).getNombreInstitucion(),  x));
        		}
        	}
        	        	
        	request.setAttribute("datoUsuario",  usr);
        	request.setAttribute("clasesDictadas",  clasesDictadasProfesor);
        	request.setAttribute("clasesInscripto",  clasesInscriptoSocio);
        	request.setAttribute("seguidores",  seguidores);
        	request.setAttribute("seguidos",  seguidos);
        	request.setAttribute("cuponeras",  cuponerasIngresadasSocio);
        	request.setAttribute("actividadesAsociadas",  actAsociadasProfesor);
        	request.setAttribute("actividadesIngresadas",  actIngresadasProfesor);
        	
        } catch(Exception e) {
        	e.printStackTrace();
        	response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
        	return;
        }
        request.getRequestDispatcher("pages/usuarios.jsp").forward(request,  response);
    }
    
	protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        processRequest(request,  response);
	}
	
	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        processRequest(request,  response);
	}
}