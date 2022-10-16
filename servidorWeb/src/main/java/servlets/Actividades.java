package servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.Parametrizer;
import logica.Fabrica;
import datatypes.DtSocioExtra;
import datatypes.DtUsuarioExtra;
import datatypes.DtActividadDeportivaExtra;
import datatypes.DtClaseExtra;
import datatypes.DtCuponera;
import excepciones.ActividadDeportivaException;
import excepciones.ClaseException;
import excepciones.UsuarioNoExisteException;

@WebServlet ("/actividades")
public class Actividades extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Actividades() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	Parametrizer.loadStdRequests(request);
    	String nombreActDep = request.getParameter("actividad");
		DtActividadDeportivaExtra datosActDep = null;
    	String nickUser = (String) request.getSession().getAttribute("nickname");
    	boolean esSocio = false;
    	DtUsuarioExtra datosCreador = null;
    	String institucion = null;
    	Set<DtClaseExtra> datosClases = new HashSet<>();
    	Set<DtCuponera> datosCuponeras = new HashSet<>();
    	
		try {
			datosActDep = buscarActDep(nombreActDep);
			institucion = Fabrica.getInstance().obtenerIcontroladorDictadoClase().obtenerInstitucionActDep(datosActDep.getNombre());
		} catch(ActividadDeportivaException e) {
			request.setAttribute("nombreActDep",  null);
			request.setAttribute("institucion",  null);
			response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
			return;
		}
		
		if (datosActDep != null) {
			if (datosActDep.getCreador().equals("Administrador")) {
				datosCreador = new DtUsuarioExtra("Administrador", null, null, null, null, null, "Administrador.png".getBytes(), null, null);
				request.setAttribute("datosCreador",  datosCreador);
			} else {
				try {
					datosCreador = Fabrica.getInstance().obtenerIcontroladorUsuario().seleccionarUsuario(datosActDep.getCreador());
					request.setAttribute("datosCreador",  datosCreador);
				} catch(UsuarioNoExisteException ignore) { }
			}
		}
		
		try {
			if (Fabrica.getInstance().obtenerIcontroladorUsuario().seleccionarUsuario(nickUser) instanceof DtSocioExtra) {
				esSocio = true;
			}
		} catch(UsuarioNoExisteException ignore) { } 
		
		//Set de DtClaseExtra
		try {
			Set<String> clases = datosActDep.getClases();
			for (String clase : clases) { 
				datosClases.add(Fabrica.getInstance().obtenerIcontroladorDictadoClase().buscarClase(clase));
			}
		} catch(ClaseException e) {
			request.setAttribute("datosClases",  null);
		}
		
		//Set de DtCuponera
		try {
			Set<String> cuponeras = datosActDep.getCuponeras();
			for (String cup : cuponeras) {
				datosCuponeras.add(Fabrica.getInstance().obtenerIcontroladorCuponera().seleccionarCuponera(cup));
			}
		} catch(Exception e) {
			request.setAttribute("datosCuponeras",  null);
		}
		request.setAttribute("actDep",  datosActDep);
		request.setAttribute("institucion",  institucion);
		request.setAttribute("esSocio",  esSocio);
		request.setAttribute("datosClases",  datosClases);
		request.setAttribute("datosCuponeras",  datosCuponeras);
		request.getRequestDispatcher("pages/actividades.jsp").forward(request,  response);
	}
    
    protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
		processRequest(request,  response);
	}
    
	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        processRequest(request,  response);
	}
	
	private DtActividadDeportivaExtra buscarActDep(String nombreActDep) throws ActividadDeportivaException {
    	DtActividadDeportivaExtra datosActDep = Fabrica.getInstance().obtenerIcontroladorActDeportiva().buscarActDep(nombreActDep);
    	return datosActDep;
    }
}
