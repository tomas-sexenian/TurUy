package presentacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import excepciones.ActividadNoExisteException;
import excepciones.SalidaNoExisteException;
import logica.Fabrica;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataSalidaTuristica;
import logica.interfaces.IControladorActividades;
import logica.interfaces.IControladorSalidas;

public class ContadorVisitas {

	private IControladorActividades iControladorActividades;
	private IControladorSalidas iControladorSalidas;

	public ContadorVisitas() {
		Fabrica fabrica = Fabrica.getInstance();
		iControladorActividades = fabrica.getIControladorActividades();
		iControladorSalidas = fabrica.getIControladorSalidas();
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
		list.sort(Entry.comparingByValue());
		Collections.reverse(list);
		int max = Math.max(10, list.size());
		Map<K, V> result = new LinkedHashMap<>();
		int cont = 0;
		for (Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
			cont++;
			if (cont >= max) {
				break;
			}
		}

		return result;
	}

	public String[][] obtenerEstadisticas() {
		Map<String, Integer> contador = iControladorActividades.getContadorActividades();
		contador.putAll(iControladorSalidas.getContadorSalidas());
		Map<String, Integer> contadorOrdenado = sortByValue(contador);
		String[][] est = new String[10][5];
		int icont = 0;
		for (String entidad : contadorOrdenado.keySet()) {
			try {
				DataActividadTuristica actividadTuristica = iControladorActividades.consultarActividad(entidad);
				est[icont][0] = Integer.toString(icont + 1);
				est[icont][1] = entidad;
				est[icont][2] = actividadTuristica.getProveedor();
				est[icont][3] = "Actividad";
				est[icont][4] = Integer.toString(contadorOrdenado.get(entidad));

			} catch (ActividadNoExisteException e) {
				try {
					DataSalidaTuristica salida = iControladorSalidas.consultarSalida(entidad);
					DataActividadTuristica actividadTuristica = iControladorActividades
							.consultarActividad(salida.getActividad());
					est[icont][0] = Integer.toString(icont + 1);
					est[icont][1] = entidad;
					est[icont][2] = actividadTuristica.getProveedor();
					est[icont][3] = "Salida";
					est[icont][4] = Integer.toString(contadorOrdenado.get(entidad));

				} catch (SalidaNoExisteException | ActividadNoExisteException e1) {
					// TODO Auto-generated catch block
				}
			}
			icont++;
			if (icont >= 10) {
				break;
			}
		}
		return est;

	}

}
