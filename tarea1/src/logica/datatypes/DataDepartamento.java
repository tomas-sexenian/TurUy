package logica.datatypes;

public class DataDepartamento {
	private String nombre;
	private String descripcion;
	private String link;
	public DataDepartamento(String nombre, String descripcion, String link) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.link = link;
	}
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getLink() {
		return link;
	}
	/*@Override
	public String toString() {
		return "DataDepartamento {nombre=" + nombre + ", descripcion=" + descripcion + ", link=" + link + "}";
	}*/
	
}
