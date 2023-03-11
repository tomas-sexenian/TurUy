package logica.datatypes;

public class DataDepartamento {
	private String nombre;
	private String descripcion;
	private String link;

	public DataDepartamento(String nombre, String descripcion, String link) {
		super();
		setNombre(nombre);
		setDescripcion(descripcion);
		setLink(link);
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setLink(String link) {
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

	@Override
	public String toString() {
		return nombre;
	}

}
