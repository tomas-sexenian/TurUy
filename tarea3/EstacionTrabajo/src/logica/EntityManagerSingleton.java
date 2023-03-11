package logica;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.*;
import logica.clasesPersistencia.ProveedorP;
import logica.clasesPersistencia.TuristaP;

public class EntityManagerSingleton {
	@PersistenceUnit(name = "turismo")
	private static EntityManagerSingleton instancia = null;
	private EntityManagerFactory entitymf = Persistence.createEntityManagerFactory("turismo");
	private Map<String, TuristaP> turistaNickname;
	private Map<String, ProveedorP> proveedorNickname;

	private EntityManagerSingleton() {
		turistaNickname = new HashMap<String, TuristaP>();
		proveedorNickname = new HashMap<String, ProveedorP>();
	}

	public static EntityManagerSingleton getInstance() {
		if (instancia == null) {
			instancia = new EntityManagerSingleton();
		}
		return instancia;
	}

	public EntityManagerFactory getFactory() {
		return entitymf;
	}

	public void addTurista(TuristaP turista) {
		if (!existeTuristaNick(turista.getNombre()))
			turistaNickname.put(turista.getNickname(), turista);
	}

	/*
	 * Agrega el proveedor al sistema. SI ya existe un proveedor de igual nombre, la
	 * operacion no tiene efecto
	 */
	public void addProveedor(ProveedorP proveedor) {
		if (!existeProveedorNick(proveedor.getNombre()))
			proveedorNickname.put(proveedor.getNickname(), proveedor);
	}

	/*
	 * Devuelve el proveedor indicado en caso de que exista, en caso contrario
	 * devuelve null
	 */
	public ProveedorP obtenerProveedor(String nickname) {
		EntityManager em = entitymf.createEntityManager();
		TypedQuery<ProveedorP> query = em.createQuery("SELECT p FROM ProveedorP p WHERE p.nickname = :name",
				ProveedorP.class);
		try {
			ProveedorP prov = query.setParameter("name", nickname).getSingleResult();
			return prov;
		} catch (NoResultException e) {
			return null;
		}

	}

	/*
	 * Devuelve el turista indicado en caso de que exista, en caso contrario
	 * devuelve null
	 */

	public TuristaP obtenerTurista(String nickname) {
		EntityManager em = entitymf.createEntityManager();
		TypedQuery<TuristaP> query = em.createQuery("SELECT t FROM TuristaP t WHERE t.nickname = :name",
				TuristaP.class);
		try {
			TuristaP tur = query.setParameter("name", nickname).getSingleResult();
			return tur;
		} catch (NoResultException e) {
			return null;
		}

	}

	public boolean existeTuristaNick(String nickname) {
		for (TuristaP tur : turistaNickname.values()) {
			if (tur.getNickname().toLowerCase().equals(nickname.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Devuelve true si en el sistema existe un proveedor con el nickname enviado
	 * por parametro
	 */
	public boolean existeProveedorNick(String nickname) {
		for (ProveedorP p : proveedorNickname.values()) {
			if (p.getNickname().toLowerCase().equals(nickname.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

}
