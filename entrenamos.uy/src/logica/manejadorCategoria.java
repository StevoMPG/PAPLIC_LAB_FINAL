package logica;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;

import excepciones.CategoriaException;
import logica.persistencia.DataPersistencia;

public class manejadorCategoria {
	
	private static manejadorCategoria instancia = null;
	private Map<String,  Categoria> categorias;
	
	private manejadorCategoria() {
		categorias = new HashMap<>();
	}
	
	public static manejadorCategoria getInstance() {
		if (instancia == null) {
			instancia = new manejadorCategoria(); 
		}
		return instancia;
	}
	
	public void addCategoria(Categoria cat) throws CategoriaException {
		if (categorias.containsKey(cat.getNombre())) {
			throw new CategoriaException("Ya existe una Categoria en el Sistema con ese nombre.");
		} else {
			categorias.put(cat.getNombre(),  cat);
			DataPersistencia.getInstance().persistirCategorias(cat);
		}
	}
	
	public Categoria findCategoria(String catNombre) throws CategoriaException {
		if (categorias.containsKey(catNombre)) {
			return categorias.get(catNombre);
		} else {
			throw new CategoriaException("No existe una Categoria en el Sistema con ese nombre.");
		}
	}
	
	public boolean existeCategoria(String catNombre) {
		return categorias.containsKey(catNombre);
	}
	
	public Set<String> getNombreCategorias() {
		return categorias.keySet();
	}
}