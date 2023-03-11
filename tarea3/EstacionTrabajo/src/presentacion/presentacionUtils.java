package presentacion;

import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataSalidaTuristica;

/*
 * Recopilado de clases auxiliares que pueden ser utilizadas para facilitar la
 * programacion de eventos de la GUI.
 * 
 * Los Combo{ClaveClase}{NombreClase} son utilizados para mostrar en un combo box 
 * la clave del dato pero almacenando toda su informacion
 * 
 * La clase ItemTexto almacena solo texto, puede ser utilizada para guardar solo claves
 * o desplegar opciones como seleccionar el tipo de usuario
 */

class ComboNombreSalida {
	private String key;
	private DataSalidaTuristica value;

	public ComboNombreSalida(String key, DataSalidaTuristica value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return value.toString();
	}

	public String getKey() {
		return key;
	}

	public DataSalidaTuristica getValue() {
		return value;
	}
}

class ComboNombreActividad {
	private String key;
	private DataActividadTuristica value;

	public ComboNombreActividad(String key, DataActividadTuristica value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return value.toString();
	}

	public String getKey() {
		return key;
	}

	public DataActividadTuristica getValue() {
		return value;
	}
}

class ItemTexto {
	private String value;

	public ItemTexto(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public String getValue() {
		return value;
	}
}
