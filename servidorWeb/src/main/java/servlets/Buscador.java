package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import excepciones.ActividadDeportivaException;
import models.IActividadDeportivaController;
import models.ICuponeraController;
import models.LaFabricaWS;
/**
 * Servlet implementation class Buscador
 */
@WebServlet("/ajax/Buscador")
public class Buscador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buscador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ICuponeraController ICC = LaFabricaWS.getInstance().obtenerICuponeraController();
		IActividadDeportivaController ICAD = LaFabricaWS.getInstance().obtenerIActDeportivaController();
		request.setCharacterEncoding("UTF-8");
        JSONArray datos = new JSONArray();
        String coincidencia = request.getParameter("coincidencia");

        response.setContentType("application/x-www-form-urlencoded");
        Set<String> cuponeras = ICC.buscarCuponeras(coincidencia);
        Set<String> actividades = null;
		try {
			actividades = (Set<String>) ICAD.buscarActDep(coincidencia);
			
		       int contador = 0;

		        for (String cuponera : cuponeras) {
		            if (contador == 7) {
		                break;
		            }
		            JSONObject obj = new JSONObject();
		            obj.put("id", cuponera);
		            obj.put("tipo", "cuponera");
		            obj.put("link", "/servidorWeb/cuponeras?cuponera=" + cuponera);
		            datos.add(obj);
		            contador++;
		        	
		       }



		        for (String actividad : actividades) {
		          if (contador == 7) {
		               break;
		          }
		           JSONObject obj = new JSONObject();
		            obj.put("id", actividad);
		            obj.put("tipo", "actividad");
		            obj.put("link", "/servidorWeb/actividades?actividad=" + actividad);
		            datos.add(obj);
		            contador++;
		        }

		        PrintWriter out = response.getWriter();
		        out.print(datos);

			
			
			
			
		} catch (ActividadDeportivaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}      
 
}
