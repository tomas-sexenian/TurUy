package logica.interfaces;

import excepciones.DepartamentoNoExisteException;
import excepciones.DepartamentoRepetidoException;
import logica.datatypes.*;

public interface IControladorDepartamentos {

	public abstract void crearDepartamento(String nombre, String descripcion, String link) throws DepartamentoRepetidoException;
	
	public abstract DataDepartamento consultarDepartamento(String nombre) throws DepartamentoNoExisteException;
	
	public abstract void modificarDepartamento(String nombre, String descripcion, String link) throws DepartamentoNoExisteException;
	
	public abstract DataDepartamento[] obtenerDepartamentos() throws DepartamentoNoExisteException;
}
