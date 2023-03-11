package logica.manejadores;

import java.util.LinkedList;
import java.util.List;

import logica.clases.Compra;
import logica.clases.Turista;

public class ManejadorCompras {
	private List<Compra> compras;
	private static ManejadorCompras instancia = null;

	private ManejadorCompras() {
		compras = new LinkedList<Compra>();
	}

	public static ManejadorCompras getinstance() {
		if (instancia == null)
			instancia = new ManejadorCompras();
		return instancia;
	}

	public void addCompra(Compra c) {
		compras.add(c);
	}

	// Devuelve todas las compras del turista indicado
	public List<Compra> obtenerComprasDelTurista(String nickname) {
		ManejadorUsuario mu = ManejadorUsuario.getinstance();
		Turista t = mu.obtenerTurista(nickname);
		return t.getCompras();
	}

	// Devuelve todas las compras del sistema
	public List<Compra> obtenerCompras() {
		return compras;
	}

}
