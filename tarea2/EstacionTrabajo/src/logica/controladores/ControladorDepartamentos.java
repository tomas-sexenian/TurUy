package logica.controladores;

import excepciones.DepartamentoNoExisteException;
import excepciones.DepartamentoRepetidoException;
import logica.clases.Departamento;
import logica.datatypes.DataDepartamento;
import logica.interfaces.IControladorDepartamentos;
import logica.manejadores.ManejadorDepartamentos;

public class ControladorDepartamentos implements IControladorDepartamentos {

	public void crearDepartamento(String nombre, String descripcion, String link) throws DepartamentoRepetidoException {
		ManejadorDepartamentos maDepartamentos = ManejadorDepartamentos.getinstance();
		if (maDepartamentos.existeDepartamento(nombre)) {
			throw new DepartamentoRepetidoException("El departamento " + nombre + " ya esta registrada");
		} else {
			Departamento dpto = new Departamento(nombre, descripcion, link);
			maDepartamentos.addDepartamento(dpto);
		}
	}

	public DataDepartamento consultarDepartamento(String nombre) throws DepartamentoNoExisteException {
		ManejadorDepartamentos maDepartamentos = ManejadorDepartamentos.getinstance();
		if (!maDepartamentos.existeDepartamento(nombre)) {
			throw new DepartamentoNoExisteException("El departamento " + nombre + " no existe");
		} else {
			Departamento dpto = maDepartamentos.obtenerDepartamento(nombre);
			DataDepartamento dataDpto = new DataDepartamento(dpto.getNombre(), dpto.getDescripcion(), dpto.getLink());
			return dataDpto;
		}
	}

	public void modificarDepartamento(String nombre, String descripcion, String link)
			throws DepartamentoNoExisteException {
		ManejadorDepartamentos maDepartamentos = ManejadorDepartamentos.getinstance();
		if (!maDepartamentos.existeDepartamento(nombre)) {
			throw new DepartamentoNoExisteException("El departamento " + nombre + " no existe");
		} else {
			Departamento dpto = maDepartamentos.obtenerDepartamento(nombre);
			dpto.setDescripcion(descripcion);
			dpto.setLink(link);
		}
	}

	public DataDepartamento[] obtenerDepartamentos() throws DepartamentoNoExisteException {
		ManejadorDepartamentos maDepartamentos = ManejadorDepartamentos.getinstance();
		if (maDepartamentos.getDepartamentos().isEmpty()) {
			throw new DepartamentoNoExisteException("No hay departamentos en el sistema");
		} else {
			DataDepartamento[] dds = new DataDepartamento[maDepartamentos.getDepartamentos().size()];
			int cont = 0;
			for (Departamento dpto : maDepartamentos.getDepartamentos().values()) {
				DataDepartamento dataDpto = new DataDepartamento(dpto.getNombre(), dpto.getDescripcion(),
						dpto.getLink());
				dds[cont] = dataDpto;
				cont++;
			}
			return dds;
		}
	}
}
