package logica.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


import logica.ActividadDeportiva;
import logica.Clase;
import logica.Profesor;
import logica.Socio;
import logica.Usuario;
import logica.compraClase;
import logica.persistencia.Datatypes.TipoUsuario;
import logica.persistencia.Entidades.*;

public class DataPersistencia {
	@PersistenceUnit(name="ActividadDB")
	private EntityManagerFactory emFabrica = Persistence.createEntityManagerFactory("ActividadDB");

	private static DataPersistencia instancia = null;
	
	private DataPersistencia() { }

	public static DataPersistencia getInstance() {
		if (instancia == null)
			instancia = new DataPersistencia();
		return instancia;
	}
	
	public void persistir(Profesor p) {
		EntityManager em = emFabrica.createEntityManager();
		try {
			em.getTransaction().begin();
			TypedQuery<Profesores> creatorQuery = em.createQuery("SELECT a FROM Profesores a WHERE a.nickname = :nickname",Profesores.class);
			Profesores profe=null;
		    creatorQuery.setParameter("nickname", p.getNickname());
		    List<Profesores> creatorQueryRes = creatorQuery.getResultList();
		    em.getTransaction().commit();
		    if (creatorQueryRes.isEmpty()) {
		    	profe = new Profesores();
		    	profe.setNickname(p.getNickname());
		    	profe.setEmail(p.getCorreo());
		    	profe.setNombre(p.getNombre());
		    	profe.setApellido(p.getApellido());
		    	profe.setFechaNacimiento(p.getFecha().toCalendar());
		    	profe.setTipoUsuario(TipoUsuario.Profesor);

		    	em.getTransaction().begin();
		    	em.persist(profe);
		    	em.getTransaction().commit();
		    }

			
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
	
	public void nuketownDetonator() {
		EntityManager em = emFabrica.createEntityManager();
		em.getTransaction().begin();
		try {
		    Query q1 = em.createQuery("DELETE FROM Registros");
		    Query q2 = em.createQuery("DELETE FROM Clases");
		    Query q3 = em.createQuery("DELETE FROM ActividadesDeportivas");
		    Query q4 = em.createQuery("DELETE FROM Profesores");
		    Query q5 = em.createQuery("DELETE FROM Socios");
		    q1.executeUpdate();
		    q2.executeUpdate();
		    q3.executeUpdate();
		    q4.executeUpdate();
		    q5.executeUpdate();
		    em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
}