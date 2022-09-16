package logica.persistencia;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.*;  

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import datatypes.DtActividadDeportiva;
import logica.ActividadDeportiva;
import logica.Clase;
import logica.ClasesCuponera;
import logica.Cuponera;
import logica.Institucion;
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
	

	public void persistirActividad(ActividadDeportiva act, Institucion ins) {
		EntityManager em = emFabrica.createEntityManager();
		try {
			
			ActividadesDeportivas ap = new ActividadesDeportivas();
			ap.setNombre(act.getNombre());
		    ap.setDescripcion(act.getDescripcion());
		    ap.setDuracion(act.getDuracionMinutos());
		    ap.setCosto(act.getCosto());
		    ap.setFechaAlta(act.getFechaRegistro().toCalendar());
		    ap.setInstitucion(ins.getNombre());
		    em.getTransaction().begin();
	    	em.persist(ap);
	    	em.getTransaction().commit();
	
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
	
	public void persistirClase(Clase clase, Institucion ins) {
		EntityManager em = emFabrica.createEntityManager();
		try {

				Clases x = new Clases();
				x.setNombre(clase.getNombre());
				x.setFechaInicio(clase.getFechaClase().toCalendar());
				x.setFechaAlta(clase.getFechaRegistro().toCalendar());
				x.setSociosMaximos(clase.getMaxSocios());
				x.setSociosMinimos(clase.getMinSocios());
				x.setUrl(clase.getURL());
				x.setnicknameProfesor(clase.getProfesor().getNickname());
				x.setActividad(clase.getAD().getNombre());
				x.setInstitucion(ins.getNombre());
			    em.getTransaction().begin();
		    	em.persist(x);
		    	em.getTransaction().commit();
	
			
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		
	}
	
	public void persistirSocio(Usuario user) {
		EntityManager em = emFabrica.createEntityManager();
		try {

				Socios s = new Socios();
				s.setNickname(user.getNickname());
				s.setNombre(user.getNombre());
				s.setApellido(user.getApellido());
				s.setEmail(user.getCorreo());
				s.setFechaNacimiento(user.getFecha().toCalendar());
				s.setTipoUsuario(TipoUsuario.Socio);
				em.getTransaction().begin();
				em.persist(s);
				em.getTransaction().commit();
		
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
		
		public void persistirProfesor(Profesor p) {
			EntityManager em = emFabrica.createEntityManager();
			try {

					Profesores s = new Profesores();
					s.setNickname(p.getNickname());
					s.setNombre(p.getNombre());
					s.setApellido(p.getApellido());
					s.setEmail(p.getCorreo());
					s.setFechaNacimiento(p.getFecha().toCalendar());
					s.setTipoUsuario(TipoUsuario.Profesor);
					s.setBiografia(p.getBiografia());
					s.setDescripcion(p.getDescripcion());
					s.setLink(p.getWebsite());
					s.setInstitucion(p.getInstitucion().getNombre());
					em.getTransaction().begin();
					em.persist(s);
					em.getTransaction().commit();
		
			} catch (Exception e) {
				e.printStackTrace();
				em.getTransaction().rollback();
			} finally {
				em.close();
			}
	}
		
		public void persistirInstitucion(Institucion i) {
			EntityManager em = emFabrica.createEntityManager();
			try {
					Instituciones s = new Instituciones();
					s.setNombre(i.getNombre());
					s.setDescripcion(i.getDescripcion());
					s.setUrl(i.getURL());
					em.getTransaction().begin();
					em.persist(s);
					em.getTransaction().commit();
		
			} catch (Exception e) {
				e.printStackTrace();
				em.getTransaction().rollback();
			} finally {
				em.close();
			}
	}
		
		
		public void persistirCuponeras(Cuponera cup) {
			EntityManager em = emFabrica.createEntityManager();
			try {
					Cuponeras s = new Cuponeras();
					s.setNombre(cup.getNombre());
					s.setDescripcion(cup.getDescripcion());
					s.setDescuento(cup.getDescuento());
					s.setFechaInicio(cup.getFechaInicio().toCalendar());
					s.setFechaFin(cup.getFechaFin().toCalendar());
					s.setFechaAlta(cup.getFechaAlta().toCalendar());
			
					em.getTransaction().begin();
					em.persist(s);
					em.getTransaction().commit();
		
			} catch (Exception e) {
				e.printStackTrace();
				em.getTransaction().rollback();
			} finally {
				em.close();
			}
	}
		
	 public void persistirRegistroClase(compraClase cup) {
			EntityManager em = emFabrica.createEntityManager();
			try {
					Registros s = new Registros();
					s.setSocio(cup.getNick());
					s.setClase(cup.getNombreClase());
					s.setFechaRegistro(cup.getFechaInscripcion().toCalendar());
					s.setCosto(cup.getCosto());
					s.setTipoPago(cup.esTipoCuponera());

					em.getTransaction().begin();
					em.persist(s);
					em.getTransaction().commit();
		
			} catch (Exception e) {
				e.printStackTrace();
				em.getTransaction().rollback();
			} finally {
				em.close();
			}
	} 
		
	 public void persistirActividadesCuponeras(ClasesCuponera cp) {
			EntityManager em = emFabrica.createEntityManager();
			try {
					ActividadesCuponeras s = new ActividadesCuponeras();
					s.setNombre(cp.getNombreActDep());
					s.setNombrec(cp.getNombreCuponera());

					em.getTransaction().begin();
					em.persist(s);
					em.getTransaction().commit();
		
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
		    Query q6 = em.createQuery("DELETE FROM Instituciones");
		    Query q7 = em.createQuery("DELETE FROM Cuponeras");
		    Query q8 = em.createQuery("DELETE FROM ActividadesCuponeras");
		    q1.executeUpdate();
		    q2.executeUpdate();
		    q3.executeUpdate();
		    q4.executeUpdate();
		    q5.executeUpdate();
		    q6.executeUpdate();
		    q7.executeUpdate();
		    q8.executeUpdate();
		    em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
}

