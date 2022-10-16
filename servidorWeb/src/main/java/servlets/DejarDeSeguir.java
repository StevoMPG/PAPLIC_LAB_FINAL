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
import datatypes.DtProfesorExtra;
import datatypes.DtUsuarioExtra;
import excepciones.ActividadDeportivaException;
import excepciones.InstitucionException;
import excepciones.UsuarioNoExisteException;
import logica.IcontroladorActividadDeportiva;
import logica.IcontroladorUsuario;
import logica.Fabrica;
import tools.Parametrizer;

// Servlet login. Obedece el protoclo inicio sesión.
// Si la combinación tiene exito. El servlet establece como atributo de sesión al usuario.
@WebServlet ("/dejarDeSeguir")
public class DejarDeSeguir extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IcontroladorUsuario IUC;
	private IcontroladorActividadDeportiva IADC;
    public DejarDeSeguir() {
        super();
        IUC = Fabrica.getInstance().obtenerIcontroladorUsuario();
        IADC = Fabrica.getInstance().obtenerIcontroladorActDeportiva();
    } 
    
    protected void processRequest(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException,  UsuarioNoExisteException,  InstitucionException,  ActividadDeportivaException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	Parametrizer.loadStdRequests(request);
    	DtUsuarioExtra usr = null;
    	try {
    		String nombreUsrProfile = (String) request.getParameter("nickname");
        	usr = IUC.seleccionarUsuario(nombreUsrProfile);
        	DtUsuarioExtra usrLogged = (DtUsuarioExtra) request.getSession().getAttribute("loggedUser");
    		
        	//Caso de uso
        	IUC.dejarDeSeguir(usrLogged.getNickname(), nombreUsrProfile);
    		
    		//Actualización y obtención de datos
    		List<DtActividadDeportivaExtra> actIngresadasProfesor = new ArrayList<>();
        	if (usr instanceof DtProfesorExtra) {
        		Set<String> actividades = ((DtProfesorExtra)usr).getActividadesIngresadas();
        		for (String x : actividades) {
        			actIngresadasProfesor.add(IADC.getActDepExt(((DtProfesorExtra)usr).getNombreInstitucion(),  x));
        		}
        	}
        	usrLogged = IUC.seleccionarUsuario(usrLogged.getNickname());
        	usr = IUC.seleccionarUsuario(usr.getNickname());
        	
        	//Envio de información actualizada
        	request.setAttribute("actividadesIngresadas",  actIngresadasProfesor);
        	request.getSession().setAttribute("loggedUser", usrLogged);
        	request.setAttribute("datoUsuario",  usr);
        	//request.getRequestDispatcher("/usuarios?nickname=" + usr.getNickname()).forward(request,  response);
        	response.sendRedirect(request.getContextPath() +"/usuarios?nickname=" + usr.getNickname());
		} catch (Exception e2) {
			e2.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
			return;
		}
    }
    
	protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        try {
			processRequest(request,  response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/pages/400.jsp");
			return;
		}
	}
	
	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
		try {
			processRequest(request,  response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/pages/400.jsp");
			return;
		}
	}


}