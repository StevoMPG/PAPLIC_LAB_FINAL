package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datatypes.DtActividadDeportivaExtra;
import datatypes.DtClaseExtra;
import datatypes.DtCuponera;
import datatypes.DtPremio;
import datatypes.DtProfesorExtra;
import datatypes.DtSocioExtra;
import datatypes.DtUsuarioExtra;
import models.IActividadDeportivaController;
import models.ICuponeraController;
import models.IDictadoClaseController;
import models.IUsuarioController;
import models.LaFabricaWS;
import tools.Parametrizer;

// Servlet login. Obedece el protoclo inicio sesión.
// Si la combinación tiene exito. El servlet establece como atributo de sesión al usuario.
@WebServlet ("/usuarios")
public class PerfilUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IActividadDeportivaController IADC;
	private IUsuarioController IUC;
	private ICuponeraController ICC;
	private IDictadoClaseController IDCC;
    public PerfilUsuario() {
        super();
        IUC = LaFabricaWS.getInstance().obtenerIUsuarioController();
        IADC = LaFabricaWS.getInstance().obtenerIActDeportivaController();
        ICC = LaFabricaWS.getInstance().obtenerICuponeraController();
        IDCC = LaFabricaWS.getInstance().obtenerIDictadoClaseController();
    }
    
    protected void processRequest(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
    	String ua=request.getHeader("User-Agent").toLowerCase();
    	if(ua.matches("(?i).*((android|bb\\d+|meego).+mobile|avantgo|bada\\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\\.(browser|link)|vodafone|wap|windows ce|xda|xiino).*")||ua.substring(0,4).matches("(?i)1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\\-|your|zeto|zte\\-")) {
    		request.getRequestDispatcher("pages/404.jsp").forward(request,  response);
    		return;
    	}
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	Parametrizer.loadStdRequests(request);
        try {
        	
        	//Determina si el usuario está persistido.
        	boolean db = (request.getParameter("db")!=null);
        	
        	
        	//Obtención de datos de usuario
        	String nickname = (String) request.getParameter("nickname");
        	if (nickname.equals("Administrador")) {
        		request.getRequestDispatcher("pages/403.jsp").forward(request,  response);
            	return;
        	}
        	DtUsuarioExtra usr = IUC.seleccionarUsuario((!db) ? nickname: nickname+"\uEAEA");
        	
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
        	//Obtención de clases a las que está inscripto pero finalizó su actdep 
        	Map<String,Set<String>> clasesFinalizadas = new HashMap<>();
        	if( usr instanceof DtSocioExtra) {
        		clasesFinalizadas = ((DtSocioExtra) usr).getClasesDeActividadesFinalizadas();
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
        			actAsociadasProfesor.add(IADC.getActDepExtra(((DtProfesorExtra)usr).getNombreInstitucion(),  x));
        		}
        	}
        	
        	//Obtención de actividades ingresadas
        	List<DtActividadDeportivaExtra> actIngresadasProfesor = new ArrayList<>();
        	if (usr instanceof DtProfesorExtra) {
        		Set<String> actividades = ((DtProfesorExtra)usr).getActividadesIngresadas();
        		for (String x : actividades) {
        			actIngresadasProfesor.add(IADC.buscarActDep(x));
        		}
        	}
        	
        	float valoracion = 0;
        	if (usr instanceof DtProfesorExtra) {
        		valoracion = ((DtProfesorExtra) usr).getValoracion();
        		int numRedoneados = 2;
        		valoracion = (float) (Math.round(valoracion * Math.pow(10, numRedoneados)) / Math.pow(10, numRedoneados));
        	}
        	
        	List<Integer> valoraciones = new ArrayList<>();
        	if (usr instanceof DtProfesorExtra) {
	        	
	        	for( String x: ((DtProfesorExtra)usr).getClasesDictadas()) {
	        		for( Integer y:IDCC.buscarClase(x).getCalificaciones().values()) {
	        			valoraciones.add(y);
	        		}
	        	}
        	}
        	Map<String,DtPremio> premios = new HashMap<>();
        	Set<String> favs = new HashSet<>();
        	if(usr instanceof DtSocioExtra) {
        		premios = ((DtSocioExtra) usr).getPremios();
        		favs = ((DtSocioExtra)usr).getActividadesFavoritas();
        	}
        	
        	request.setAttribute("db", db);
        	request.setAttribute("favs", favs);
        	request.setAttribute("premios", premios);
        	request.setAttribute("valoracion",  valoracion);
        	request.setAttribute("valoraciones",  valoraciones);    
        	request.setAttribute("datoUsuario",  usr);
        	request.setAttribute("clasesDictadas",  clasesDictadasProfesor);
        	request.setAttribute("clasesInscripto",  clasesInscriptoSocio);
        	request.setAttribute("seguidores",  seguidores);
        	request.setAttribute("seguidos",  seguidos);
        	request.setAttribute("cuponeras",  cuponerasIngresadasSocio);
        	request.setAttribute("actividadesAsociadas",  actAsociadasProfesor);
        	request.setAttribute("actividadesIngresadas",  actIngresadasProfesor);
        	request.setAttribute("clasesFinalizadas",  clasesFinalizadas);
        	
        } catch(Exception e) {
        	e.printStackTrace();
        	request.getRequestDispatcher("pages/404.jsp").forward(request,  response);
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
