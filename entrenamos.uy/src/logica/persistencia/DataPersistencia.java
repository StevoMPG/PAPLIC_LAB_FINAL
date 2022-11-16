package logica.persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import javax.persistence.*;


import datatypes.DtActividadDeportivaExtra;
import datatypes.DtClaseExtra;
import datatypes.DtSocio;
import datatypes.DtUsuario;
import datatypes.DtUsuarioExtra;
import datatypes.tipoEstado;
import excepciones.ActividadDeportivaException;
import excepciones.ClaseException;
import excepciones.InstitucionException;
import excepciones.UsuarioNoExisteException;
import logica.ActividadDeportiva;
import logica.Calificacion;
import logica.Categoria;
import logica.Clase;
import logica.ClasesCuponera;
import logica.Cuponera;
import logica.Institucion;
import logica.Profesor;
import logica.Socio;
import logica.Usuario;
import logica.compraClase;
import logica.manejadorUsuario;
import logica.persistencia.Datatypes.TipoUsuario;
import logica.persistencia.Entidades.*;

public class DataPersistencia {
	@PersistenceUnit(name="actividaddb")
	private EntityManagerFactory emFabrica = Persistence.createEntityManagerFactory("actividaddb");

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
		    ap.setEstado(act.getEstado().name());
		    ap.setProfesor(act.getCreador().getNickname());
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
				x.setDesPremio(clase.getPrize().getDescription());
				x.setUrlVideo(clase.getUrlVideo());
				x.setPremios(clase.getPrize().getCantidad());
			
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
				s.setContrasenia(user.getContrasenia());
				s.setApellido(user.getApellido());
				s.setEmail(user.getCorreo());
				s.setFechaNacimiento(user.getFecha().toCalendar());
				s.setTipoUsuario(TipoUsuario.Socio);
			//	s.setImagen(user.getImagen());
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
	
	public void persistirSocioMod(Usuario user) {
		EntityManager em = emFabrica.createEntityManager();

		try {

				Query s = em.createNativeQuery("UPDATE `SOCIOS` "
		                + "SET "
		                + "`NOMBRE` = ? "
		                + "WHERE `NICKNAME` = ?; ");
				
				Query q = em.createNativeQuery("UPDATE `SOCIOS` "
		                + "SET "
		                + "`APELLIDO` = ? "
		                + "WHERE `NICKNAME` = ?; ");
				
				Query w = em.createNativeQuery("UPDATE `SOCIOS` "
		                + "SET "
		                + "`FECHA_NACIMIENTO` = ? "
		                + "WHERE `NICKNAME` = ?; ");
				
				Query y = em.createNativeQuery("UPDATE `SOCIOS` "
		                + "SET "
		                + "`CONTRASENIA` = ? "
		                + "WHERE `NICKNAME` = ?; ");
	
				s.setParameter(1, user.getNombre());
				q.setParameter(1, user.getApellido());
				w.setParameter(1, user.getFecha().toCalendar()); 
				y.setParameter(1, user.getContrasenia());
				s.setParameter(2, user.getNickname());
				q.setParameter(2, user.getNickname());
				w.setParameter(2, user.getNickname());
				y.setParameter(2, user.getNickname());
			//	s.setImagen(user.getImagen());
				em.getTransaction().begin();
				s.executeUpdate();
				q.executeUpdate();
				w.executeUpdate();
				y.executeUpdate();
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
					s.setContrasenia(p.getContrasenia());
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
		
		
		public void persistirProfesorMod(Profesor user) {
			EntityManager em = emFabrica.createEntityManager();

			try {

				Query s = em.createNativeQuery("UPDATE `PROFESORES` "
		                + "SET "
		                + "`NOMBRE` = ? "
		                + "WHERE `NICKNAME` = ?; ");
				
				
				Query q = em.createNativeQuery("UPDATE `PROFESORES` "
		                + "SET "
		                + "`APELLIDO` = ? "
		                + "WHERE `NICKNAME` = ?; ");
				
				Query w = em.createNativeQuery("UPDATE `PROFESORES` "
		                + "SET "
		                + "`FECHA_NACIMIENTO` = ? "
		                + "WHERE `NICKNAME` = ?; ");
				
				Query e = em.createNativeQuery("UPDATE `PROFESORES` "
		                + "SET "
		                + "`DESCRIPCION` = ? "
		                + "WHERE `NICKNAME` = ?; ");
				
				Query r = em.createNativeQuery("UPDATE `PROFESORES` "
		                + "SET "
		                + "`BIOGRAFIA` = ? "
		                + "WHERE `NICKNAME` = ?; ");
				
				Query t = em.createNativeQuery("UPDATE `PROFESORES` "
		                + "SET "
		                + "`WEB` = ? "
		                + "WHERE `NICKNAME` = ?; ");


				Query y = em.createNativeQuery("UPDATE `PROFESORES` "
		                + "SET "
		                + "`CONTRASENIA` = ? "
		                + "WHERE `NICKNAME` = ?; ");
	
				s.setParameter(1, user.getNombre());
				q.setParameter(1, user.getApellido());
				w.setParameter(1, user.getFecha().toCalendar()); 
				e.setParameter(1, user.getDescripcion());
				r.setParameter(1, user.getBiografia());
				t.setParameter(1, user.getWebsite());
				y.setParameter(1, user.getContrasenia());
				s.setParameter(2, user.getNickname());
				q.setParameter(2, user.getNickname());
				w.setParameter(2, user.getNickname());
				e.setParameter(2, user.getNickname());
				r.setParameter(2, user.getNickname());
				t.setParameter(2, user.getNickname());
				y.setParameter(2, user.getNickname());
			//	s.setImagen(user.getImagen());
				em.getTransaction().begin();
				s.executeUpdate();
				q.executeUpdate();
				w.executeUpdate();
				e.executeUpdate();
				r.executeUpdate();
				t.executeUpdate();
				y.executeUpdate();
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
		
	 public void persistirRegistroClase(compraClase cup, ActividadDeportiva act) {
			EntityManager em = emFabrica.createEntityManager();
			try {
					Registros s = new Registros();
					s.setSocio(cup.getNick());
					s.setClase(cup.getNombreClase());
					s.setFechaRegistro(cup.getFechaInscripcion().toCalendar());
					s.setCosto(cup.getCosto());
					s.setTipoPago(cup.esTipoCuponera());
					s.setAct(act.getNombre());
					s.setEstado(act.getEstado().name());
					

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
					s.setClases(cp.getCantidadClases());

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
	 
	 public void persistirCategorias(Categoria c) {
			EntityManager em = emFabrica.createEntityManager();
			try {
					Categorias s = new Categorias();
					s.setNombre(c.getNombre());
					
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
	 

	 
	 

	 public void persistirSeguidores(String seguidor,  String seguido) throws UsuarioNoExisteException {
			EntityManager em = emFabrica.createEntityManager();
			manejadorUsuario handlerUsuario = manejadorUsuario.getInstance();
			Usuario seguidorU = handlerUsuario.findUsuario(seguidor);
			Usuario seguidoU = handlerUsuario.findUsuario(seguido);
			try {
					Seguidores s = new Seguidores();
					s.setNombre(seguidorU.getNickname());
					s.setNombrec(seguidoU.getNickname());

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
	 
	 
	 public void persistirValoraciones(Socio nickSocio, Clase cla, int valor) throws UsuarioNoExisteException {
			EntityManager em = emFabrica.createEntityManager();
			
			try {
				
				em.getTransaction().begin();
				TypedQuery<Valoraciones> select = em.createQuery("SELECT val FROM Valoraciones val WHERE val.nombreSoc=:nombreSoc AND val.nombreClase=:nombreClase AND val.nombreProf=:nombreProf ", Valoraciones.class);
				select.setParameter("nombreSoc", nickSocio.getNickname());
				select.setParameter("nombreClase", cla.getNombre());
				select.setParameter("nombreProf", cla.getProfesor().getNickname());
				if (select.getResultList().size() == 0) {
				
					Valoraciones s = new Valoraciones();
					s.setNombreSoc(nickSocio.getNickname());
					s.setNombreClase(cla.getNombre());
					s.setValoracion(valor);
					s.setNombreProf(cla.getProfesor().getNickname());

					em.persist(s);
					em.getTransaction().commit();					
				}
				else {
					
					Query y = em.createNativeQuery("UPDATE `VALORACIONES` "
			                + "SET "
			                + "`VALORACION` = ? "
			                + "WHERE `SOCIO` = ? AND `CLASE` = ? AND `PROFESOR` = ?; ");
		
					y.setParameter(1, valor);
					y.setParameter(2, nickSocio.getNickname());
					y.setParameter(3, cla.getNombre());
					y.setParameter(4, cla.getProfesor().getNickname());


					y.executeUpdate();
					em.getTransaction().commit();
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				em.getTransaction().rollback();
			} finally {
				em.close();
			}
	 }
	 
	 
	 public void persistirFavoritas(Socio soc, String act) throws UsuarioNoExisteException {
			EntityManager em = emFabrica.createEntityManager();
			
			try {	
				
			em.getTransaction().begin();
			TypedQuery<Favoritas> select = em.createQuery("SELECT fav FROM Favoritas fav WHERE fav.nombreSoc=:nombreSoc AND fav.nombreAct=:nombreAct", Favoritas.class);
			select.setParameter("nombreSoc", soc.getNickname());
			select.setParameter("nombreAct", act);

				if (select.getResultList().size() == 0) {	
				
						Favoritas s = new Favoritas();
						s.setNombreAct(act);
						s.setNombreSoc(soc.getNickname());

						em.persist(s);
						em.getTransaction().commit();
				}
			} catch (Exception e) {
				e.printStackTrace();
				em.getTransaction().rollback();
			} finally {
				em.close();
			}
	 }
	 	 
	 
	 public void persistirAprobarActividad(ActividadDeportiva act) {
			EntityManager em = emFabrica.createEntityManager();

			try {

				Query s = em.createNativeQuery("UPDATE `ACTIVIDADES_DEPORTIVAS` "
		                + "SET "
		                + "`ESTADO` = ? "
		                + "WHERE `NOMBRE_ACTIVIDAD` = ?; ");
				
			
	
				s.setParameter(1, act.getEstado().name());
				
				s.setParameter(2, act.getNombre());

			//	s.setImagen(user.getImagen());
				em.getTransaction().begin();
				s.executeUpdate();
				em.getTransaction().commit();
			
			} catch (Exception e) {
				e.printStackTrace();
				em.getTransaction().rollback();
			} finally {
				em.close();
			}
		}
	 
	 
	 public void persistirFinalizarActividad(ActividadDeportiva act) {
			EntityManager em = emFabrica.createEntityManager();

			try {

				Query s = em.createNativeQuery("UPDATE `ACTIVIDADES_DEPORTIVAS` "
		                + "SET "
		                + "`ESTADO` = ? "
		                + "WHERE `NOMBRE_ACTIVIDAD` = ?; ");
				
			
			
				s.setParameter(1, act.getEstado().name());
				
				s.setParameter(2, act.getNombre());

			//	s.setImagen(user.getImagen());
				em.getTransaction().begin();
				s.executeUpdate();
				em.getTransaction().commit();
			
			} catch (Exception e) {
				e.printStackTrace();
				em.getTransaction().rollback();
			} finally {
				em.close();
			}
		}
	 
	 
	 public void persistirFinalizarActividadReg(ActividadDeportiva act) {
			EntityManager em = emFabrica.createEntityManager();

			try {

				Query s = em.createNativeQuery("UPDATE `REGISTROS_CLASES` "
		                + "SET "
		                + "`ESTADO_ACTIVIDAD` = ? "
		                + "WHERE `NOMBRE_ACT` = ?; ");
				
			
	
				s.setParameter(1, act.getEstado().name());
				
				s.setParameter(2, act.getNombre());

	
				em.getTransaction().begin();
				s.executeUpdate();
				em.getTransaction().commit();
			
			} catch (Exception e) {
				e.printStackTrace();
				em.getTransaction().rollback();
			} finally {
				em.close();
			}
		}
	/*	getHU().findUsuario(user1).removerSeguido(getHU().findUsuario(user2));
		getHU().findUsuario(user2).removerSeguidor(getHU().findUsuario(user1));
	*/	
			public void persistirDESSeguidores(String user1,  String user2) throws UsuarioNoExisteException {
				EntityManager em = emFabrica.createEntityManager();
				manejadorUsuario HU = manejadorUsuario.getInstance();
				Usuario seguidorU = HU.findUsuario(user1);
				Usuario seguidoU = HU.findUsuario(user2);
				try {

						Query s = em.createQuery("SELECT  FROM Seguidores WHERE NOMBRE_SEGUIDOR = ?;");
					
						s.setParameter(1, seguidoU );
						
					//	s.setParameter(2, seguidorU);

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
		    Query q9 = em.createQuery("DELETE FROM Categorias");
		    Query q10 = em.createQuery("DELETE FROM Seguidores");
		    Query q11 = em.createQuery("DELETE FROM Valoraciones");
		    Query q12 = em.createQuery("DELETE FROM Favoritas");
		    q1.executeUpdate();
		    q2.executeUpdate();
		    q3.executeUpdate();
		    q4.executeUpdate();
		    q5.executeUpdate();
		    q6.executeUpdate();
		    q7.executeUpdate();
		    q8.executeUpdate();
		    q9.executeUpdate();
		    q10.executeUpdate();
		    q11.executeUpdate();
		    q12.executeUpdate();
		    em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
	
	

public Vector<String> consultarCategorias() {
	EntityManager em = emFabrica.createEntityManager();
	String query = "SELECT NOMBRE_CATEGORIA FROM CATEGORIAS";
	@SuppressWarnings("unchecked")
	List<String> r = (List<String>) em.createNativeQuery(query).getResultList();
	return (Vector<String>) r;
	
}

public Vector<String> consultarInstituciones() {
	EntityManager em = emFabrica.createEntityManager();
	String query = "SELECT NOMBRE_INSTITUCION FROM INSTITUCIONES";
	@SuppressWarnings("unchecked")
	List<String> r = (List<String>) em.createNativeQuery(query).getResultList();
	return (Vector<String>) r;
	
}

	





public Set<String> obtenerActividades() {
	EntityManager em = emFabrica.createEntityManager();
	Set<String> nombreActividades = new HashSet<>();
	try {
		em.getTransaction().begin();
		TypedQuery<ActividadesDeportivas> select = em.createQuery("SELECT a FROM ActividadesDeportivas a ORDER BY a.nombreAct DESC",
				ActividadesDeportivas.class);
		for (ActividadesDeportivas actDep : select.getResultList()) {
			nombreActividades.add(actDep.getNombre());
		}
	} catch (Exception e) {
		e.printStackTrace();
		em.getTransaction().rollback();
	} finally {
		em.close();
	}
	return nombreActividades;
}

public Set<String> obtenerClases() {
	EntityManager em = emFabrica.createEntityManager();
	Set<String> nombreClases = new HashSet<>();
	try {
		em.getTransaction().begin();
		TypedQuery<Clases> select = em.createQuery("SELECT c FROM Clases c ORDER BY c.nombre DESC",	Clases.class);
		for (Clases claseDB : select.getResultList()) {
			nombreClases.add(claseDB.getNombre());
		}
	} catch (Exception e) {
		e.printStackTrace();
		em.getTransaction().rollback();
	} finally {
		em.close();
	}
	return nombreClases;
}

public Set<String> obtenerClases(String nombreActividad) {
	EntityManager em = emFabrica.createEntityManager();
	Set<String> nombreClases = new HashSet<>();
	try {
		em.getTransaction().begin();
		TypedQuery<Clases> select = em.createQuery("SELECT c FROM Clases c INNER JOIN ActividadesDeportivas ad" +
				" WHERE (ad.nombreAct = :nombreAct) ORDER BY c.nombre DESC",	Clases.class);
	    select.setParameter("nombre", nombreActividad);
		for (Clases claseDB : select.getResultList()) {
			nombreClases.add(claseDB.getNombre());
		}
	} catch (Exception e) {
		e.printStackTrace();
		em.getTransaction().rollback();
	} finally {
		em.close();
	}
	return nombreClases;
}

public Set<String> obtenerActividades(String nickProfesor){
	EntityManager em = emFabrica.createEntityManager();
	Set<String> nombresAD = new HashSet<>();
	try {
		em.getTransaction().begin();
		TypedQuery<ActividadesDeportivas> select = em.createQuery("SELECT ad FROM ActividadesDeportivas ad INNER JOIN Profesores p "
				+ "WHERE p.nickname=:nombre",ActividadesDeportivas.class);
		select.setParameter("nombre", nickProfesor);
		for (ActividadesDeportivas adDB : select.getResultList()) {
			if(adDB.getProfesor().equals(nickProfesor))
				nombresAD.add(adDB.getNombre());
		}
	} catch (Exception e) {
		e.printStackTrace();
		em.getTransaction().rollback();
	} finally {
		em.close();
	}
	return nombresAD;
}	


public Map<String, Set<String>> obtenerActividadxClasesSocio(String nombreSocio) {
	EntityManager em = emFabrica.createEntityManager();
	Map<String, Set<String>> res = new HashMap<>();
	try {
		em.getTransaction().begin();
		TypedQuery<Registros> select = em.createQuery("SELECT reg FROM Registros reg WHERE reg.tipoEstado=:tipoEstado", Registros.class);
		select.setParameter("tipoEstado", tipoEstado.finalizada.toString());
		Map<String,String> clasexact = new HashMap<>();
		for(Registros x: select.getResultList()) {
			if (x.getSocio().equals(nombreSocio))
				clasexact.put(x.getClase(), x.getAct());
		}
		for(Entry<String, String> x: clasexact.entrySet()) {
			if(!res.containsKey(x.getValue()))
				res.put(x.getValue(), new HashSet<String>());
			res.get(x.getValue()).add(x.getKey());
		}
	} catch (Exception e) {
		e.printStackTrace();
		em.getTransaction().rollback();
	} finally {
		em.close();
	}
	return res;
}

// PRECONDICION!!! Existe al menos una actividad con 'nombreActDep' en la persistencia.
public DtActividadDeportivaExtra getActividad(String nombreActDep) throws ActividadDeportivaException {
	EntityManager em = emFabrica.createEntityManager();
	try {
		em.getTransaction().begin();
		TypedQuery<ActividadesDeportivas> select = em.createQuery("SELECT act FROM ActividadesDeportivas act WHERE act.nombreAct=:nombreAct",ActividadesDeportivas.class);
		select.setParameter("nombreAct", nombreActDep);
		if (select.getResultList().size() > 0) {
			ActividadesDeportivas act = select.getSingleResult();
			return act.toDtActividadDeportivaExt();
		}
	} catch (Exception e) {
		e.printStackTrace();
		em.getTransaction().rollback(); 
	} finally {
		em.close();
	}
	throw new ActividadDeportivaException("La actividad deportiva "+nombreActDep+" no se encuentra presente en el sistema.");
}

// PRECONDICION!!! Existe al menos una clase con 'nombreClase' en la persistencia.
public DtClaseExtra getClase(String nombreClase) throws ClaseException {
	EntityManager em = emFabrica.createEntityManager();
	try {
		em.getTransaction().begin();
		TypedQuery<Clases> select = em.createQuery("SELECT cl FROM Clases cl WHERE cl.nombre=:nombre",Clases.class);
		select.setParameter("nombre", nombreClase);
		if (select.getResultList().size() > 0) {
			Clases clase = select.getSingleResult();
			return clase.toDtClaseExt();
		}
	} catch (Exception e) {
		e.printStackTrace();
		em.getTransaction().rollback(); 
	} finally {
		em.close();
	}
	throw new ClaseException("La clase "+nombreClase+" no se encuentra presente en el sistema.");
}

public DtUsuarioExtra getUsuario(String nombreSocio) throws UsuarioNoExisteException {
	EntityManager em = emFabrica.createEntityManager();
	try {
		em.getTransaction().begin();
		TypedQuery<Socios> select = em.createQuery("SELECT s FROM Socios s WHERE s.nickname=:nombre",Socios.class);
		select.setParameter("nombre", nombreSocio);
		if(select.getResultList().size()>0) {
			Socios s = select.getSingleResult();
			em.getTransaction().commit();
			//System.out.println("SOCIO: "+s.toString());
			return s.toDtUsuarioExt();
		}
		else{
			TypedQuery<Profesores> select2 = em.createQuery("SELECT s FROM Profesores s WHERE s.nickname=:nombre",Profesores.class);
			select2.setParameter("nombre", nombreSocio);
			if(select2.getResultList().size()>0) {
				Profesores s = select2.getSingleResult();
				em.getTransaction().commit();
				//System.out.println("PROF: "+s.toString());
				return s.toDtUsuarioExt();
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
		em.getTransaction().rollback(); 
	} finally {
		em.close();
	}
	throw new UsuarioNoExisteException("El usuario "+nombreSocio+" no se encuentra presente en el sistema.");
}

}


