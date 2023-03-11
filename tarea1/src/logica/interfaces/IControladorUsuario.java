package logica.interfaces;

import java.time.LocalDate;

import excepciones.EmailRepetidoException;
import excepciones.NickameRepetidoException;
import excepciones.PaqueteCompradoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataInscripcion;
import logica.datatypes.DataProveedor;
import logica.datatypes.DataTurista;

public interface IControladorUsuario {

	/**
	 * Registra al turista en el sistema.
	 * 
	 * @throws UsuarioRepetidoException Si el nickname y/o cedula del usuario se
	 *                                  encuentra registrada en el sistema.
	 * @throws NickameRepetidoException
	 * @throws EmailRepetidoException
	 */
	public abstract void crearTurista(String nickname, String nombre, String apellido, String email,
			LocalDate nacimiento, String nacionalidadTuristar, String password)
			throws EmailRepetidoException, NickameRepetidoException;

	/**
	 * Registra al proveedor en el sistema.
	 * 
	 * @throws UsuarioRepetidoException Si el nickname y/o cedula del usuario se
	 *                                  encuentra registrada en el sistema.
	 * @throws NickameRepetidoException
	 * @throws EmailRepetidoException
	 */
	public abstract void crearProveedor(String nickname, String nombre, String apellido, String email,
			LocalDate nacimiento, String descripcionProveedor, String linkProveedor, String password)
			throws EmailRepetidoException, NickameRepetidoException;

	/**
	 * Retorna la información de un turista con el nickname indicado.
	 * 
	 * @throws UsuarioNoExisteException Si ningun usuario del sistema tiene por
	 *                                  nickname el enviado por parametro.
	 */
	public abstract DataTurista verInfoTurista(String nickname) throws UsuarioNoExisteException;

	/**
	 * Retorna la información de un proveedor con el nickname indicado.
	 * 
	 * @throws UsuarioNoExisteException Si ningun usuario del sistema tiene por
	 *                                  nickname el enviado por parametro.
	 */
	public abstract DataProveedor verInfoProveedor(String nickname) throws UsuarioNoExisteException;

	/**
	 * Modifica la informacion del turista.
	 * 
	 * @param nickaname y @param email son parametros exclusivos de busqueda y no
	 *                  modificables.
	 * @throws UsuarioNoExisteException Si ningun usuario del sistema tiene por
	 *                                  nickname el enviado por parametro.
	 */
	public abstract void modificarTurista(String nickname, String nombre, String apellido, String email,
			LocalDate nacimiento, String nacionalidadTurista) throws UsuarioNoExisteException;

	/**
	 * Modifica la informacion del proveedor.
	 * 
	 * @param nickaname y @param email son parametros exclusivos de busqueda y no
	 *                  modificables.
	 * @throws UsuarioNoExisteException Si ningun usuario del sistema tiene por
	 *                                  nickname el enviado por parametro.
	 */
	public abstract void modificarProveedor(String nickname, String nombre, String apellido, String email,
			LocalDate nacimiento, String descripcionProveedor, String linkProveedor) throws UsuarioNoExisteException;

	/**
	 * Retorna la información de todos los turistas del sistema
	 * 
	 * @throws UsuarioNoExisteException Si ningun usuario del sistema tiene por
	 *                                  nickname el enviado por parametro.
	 */
	public abstract DataTurista[] getTuristas() throws UsuarioNoExisteException;

	/**
	 * Retorna la información de todos los proveedores del sistema
	 * 
	 * @throws UsuarioNoExisteException Si ningun usuario del sistema tiene por
	 *                                  nickname el enviado por parametro.
	 */
	public abstract DataProveedor[] getProveedores() throws UsuarioNoExisteException;

	/*
	 * Devuelve las inscripciones a salidas turisticas del turista indicado
	 */

	public abstract DataInscripcion[] obtenerInscripcionesTurista(String nickname) throws UsuarioNoExisteException;

	/*
	 * Devuelve las actividades turisticas del proveedor indicado
	 */
	public abstract DataActividadTuristica[] obtenerActividadesProveedor(String nickname);

	/*
	 * @param nicknames : contiene los nicknames de todos turistas que participaran
	 * de la compra
	 * 
	 * @param nombre : nombre del paquete de actividades
	 * 
	 * @param fecha : la fecha de compra Genera una nueva compra para los turistas
	 * indicados al paquete de actividades indicado
	 */
	public abstract void compraTuristaPaquete(String nickname, String nombrePaq, LocalDate fecha, int cantTuristas)
			throws PaqueteCompradoException;

	/*
	 * @param nicknames : contiene los nicknames de todos turistas que participaran
	 * de la inscripcion
	 * 
	 * @param nombre : nombre de la salida turistica
	 * 
	 * @param fecha : la fecha de inscripcion Genera una nueva inscripcion para los
	 * turistas indicados a la salida indicada
	 */
	// public abstract void inscribirTuristasSalida(String[] nicknames, String
	// nombre, LocalDate fecha) throws CantidadExcedidaException;
}
