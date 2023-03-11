package logica.interfaces;

import java.time.LocalDate;
import java.util.List;

import excepciones.EmailRepetidoException;
import excepciones.NickameRepetidoException;
import excepciones.PaqueteCompradoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataCompra;
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
			LocalDate nacimiento, String nacionalidadTuristar, String password, String imgSrc)
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
			LocalDate nacimiento, String descripcionProveedor, String linkProveedor, String password, String imgSrc)
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
	 * Modifica la imagen del usuario
	 * 
	 * @param nickname es el parámetro exclusivo de búsqueda
	 * 
	 * @throws UsuarioNoExisteException si ningun usuario del sistema tiene por
	 *                                  nickname el enviado por parametro.
	 */
	public void modificarImagen(String nickname, String filename) throws UsuarioNoExisteException;

	/**
	 * Modifica la contraseña del usuario
	 * 
	 * @param nickname es el parámetro exclusivo de búsqueda
	 * 
	 * @throws UsuarioNoExisteException si ningun usuario del sistema tiene por
	 *                                  nickname el enviado por parametro.
	 *
	 */
	public void modificarContrasena(String nickname, String contraseña) throws UsuarioNoExisteException;

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

	public abstract List<DataInscripcion> obtenerInscripcionesTurista(String nickname) throws UsuarioNoExisteException;

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

	public abstract void compraTuristaPaquete(String nickname, String nombrePaq, int cantTuristas)
			throws PaqueteCompradoException;

	public abstract String obtenerLink(String nickname);

	public abstract String obtenerPassword(String nickname);

	public abstract String obtenerNickname(String email);

	public abstract boolean existeEmail(String email);

	/*
	 * Recibe el nickname de un usuario y devuelve una lista de todos los
	 * proveedores que lo siguen
	 */
	public abstract DataProveedor[] obtenerSeguidoresProv(String nickname);

	/*
	 * Recibe el nickname de un usuario y devuelve una lista de todos los turistas
	 * que lo siguen
	 */
	public abstract DataTurista[] obtenerSeguidoresTur(String nickname);

	/*
	 * Recibe el nickname de un usuario y devuelve una lista de todos los
	 * proveedores que sigue
	 */
	public abstract DataProveedor[] obtenerSeguidosProv(String nickname);

	/*
	 * Recibe el nickname de un usuario y devuelve una lsita de todos los turistas
	 * que sigue
	 */
	public abstract DataTurista[] obtenerSeguidosTur(String nickname);

	/*
	 * Recibe el nickename de un usuario y el nickname de otro usuario a quien el
	 * primero va dejar de seguir. Si el primero usuario no sigue al segundo,
	 * entonces la operacion no hace nada.
	 */
	public abstract void dejarDeSeguirUsr(String nicknameUsr, String nicknameSeguidoUsr);
	
	public abstract void seguirUsr(String nicknameUsr, String nicknameASeguirUsr);
	
	public abstract int cantSeguidores(String nickname);
	
	public abstract int cantSeguidos(String nickname);
	
	//Retorna true sii el usuario nickname sigue al usuario nicknameS
	
	public abstract boolean sigueAUsuario(String nickname, String nicknameS);

	/*
	 * Recibe el nickname de un usuario y el nombre de una actividad. La actividad es agregada a
	 * la lista de favoritas del usuario. 
	 * Si la actividad esta finalizada, la operacion no tiene efecto.
	 */
	public abstract void marcarFavorita(String nicknameUsr, String nombreActv);

	/*
	 * Recibe el nombre de un usuario y el nombre de una actividad que el usuario
	 * desea desmarcar de entre sus favoritas. Si la actividad no esta entre sus
	 * favoritas, la operacion no tiene efecto
	 */
	public abstract void desmarcarFavorita(String nicknameUsr, String nombreActv);

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

	public abstract List<DataCompra> obtenerComprasTurista(String nickname);
}
