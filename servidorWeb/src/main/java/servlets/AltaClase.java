package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import datatypes.DtFechaHora;
import datatypes.DtClase;
import datatypes.DtUsuarioExtra;
import tools.Parametrizer;
import logica.IcontroladorClase;
import logica.Fabrica;

@MultipartConfig
@WebServlet ("/altaClase")
public class AltaClase extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private IcontroladorClase IDCC;
    public AltaClase() {
        super();
        IDCC = Fabrica.getInstance().obtenerIcontroladorDictadoClase();
    }
    protected void processRequest(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	Parametrizer.loadStdRequests(request);
    	String r = new String() + request.getContextPath() + "/actividades?actividad=" + URLEncoder.encode(rp(request, "nombreActDep"), "utf-8");
        try {
        	String imgClase = null;
            if (request.getPart("img")!=null && request.getPart("img").getSize()>0) {
            	Part filePart = request.getPart("img");
        		String [] s = Paths.get(filePart.getSubmittedFileName()).getFileName().toString().split("[.]");
        		String ext = s[s.length-1];
        		imgClase=rp(request, "nombreClase")+"."+ext;
            }
        	String fechaClase = rp(request, "fechaInicio");        	
	        DtUsuarioExtra datosP = ((DtUsuarioExtra) request.getSession().getAttribute("loggedUser"));

	        if (IDCC.ingresarDatosClase(rp(request, "institucionAsociada"),  rp(request, "nombreActDep"),  new DtClase(rp(request, "nombreClase"), datosP.getNickname(), datosP.getEmail(), 
	        	Integer.parseInt(rp(request, "cantMin")),  Integer.parseInt(rp(request, "cantMax")),  rp(request, "url"), 
	        	new DtFechaHora(Integer.parseInt(fechaClase.substring(0, 4)), Integer.parseInt(fechaClase.substring(5, 7)), Integer.parseInt(fechaClase.substring(8, 10)), 				   Integer.parseInt(rp(request, "hora")), Integer.parseInt(rp(request, "minutos")), 0),  new DtFechaHora()))!=0) {
	        	r=Parametrizer.addParam(r,  "e",  "8");
	        	response.sendRedirect(r);
	        	return;
	        }	        
    		if (request.getPart("img")!=null && request.getPart("img").getSize()>0) {
	        	Part filePart = request.getPart("img");
	        	InputStream fileContent = filePart.getInputStream();
	        	String path = request.getServletContext().getRealPath("/assets/images/classes/"+imgClase);
	        	Files.copy(fileContent,  Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
	           System.out.println( request.getServletContext().getRealPath("/assets/images/users/"+imgClase));
	        }
	        r=Parametrizer.remParam(r,  "e", "8");
	        r=Parametrizer.addParam(r,  "e",  "9");
        	DtUsuarioExtra userReload = Fabrica.getInstance().obtenerIcontroladorUsuario().seleccionarUsuario(datosP.getNickname());
			request.getSession().setAttribute("loggedUser",  userReload);
        } catch(Exception e) {
        	e.printStackTrace();
        	r=Parametrizer.addParam(r,  "e",  "8");
        }
        response.sendRedirect(r);
    }
    
	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        processRequest(request,  response);
	}
	private String rp(HttpServletRequest request, String param) {
		return request.getParameter(param);
	}
}
