package servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import models.GestorWeb;

@WebListener
public class Initializer implements ServletContextListener{
	
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ENTRENAMOS.UY SERVLET INICIADO");
        GestorWeb.getInstance();
        System.out.println("EL MODELO DEFAULT SE HA CARGADO EXITOSAMENTE.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("APAGANDO ENTRENAMOS.UY WEBSERVER");
    }
}
