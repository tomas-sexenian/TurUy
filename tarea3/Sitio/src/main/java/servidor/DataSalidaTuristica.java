
package servidor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dataSalidaTuristica complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dataSalidaTuristica">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="actividad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="cantMaxTuristas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="fechaAlta" type="{http://servidor/}localDate" minOccurs="0"/>
 *         <element name="fechaSalida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="horaSalida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="imgSrc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="lugarSalida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="lugaresDisponibles" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataSalidaTuristica", propOrder = {
    "actividad",
    "cantMaxTuristas",
    "fechaAlta",
    "fechaSalida",
    "horaSalida",
    "imgSrc",
    "lugarSalida",
    "lugaresDisponibles",
    "nombre"
})
public class DataSalidaTuristica {

    protected String actividad;
    protected int cantMaxTuristas;
    protected LocalDate fechaAlta;
    protected String fechaSalida;
    protected String horaSalida;
    protected String imgSrc;
    protected String lugarSalida;
    protected int lugaresDisponibles;
    protected String nombre;

    /**
     * Obtiene el valor de la propiedad actividad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActividad() {
        return actividad;
    }

    /**
     * Define el valor de la propiedad actividad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActividad(String value) {
        this.actividad = value;
    }

    /**
     * Obtiene el valor de la propiedad cantMaxTuristas.
     * 
     */
    public int getCantMaxTuristas() {
        return cantMaxTuristas;
    }

    /**
     * Define el valor de la propiedad cantMaxTuristas.
     * 
     */
    public void setCantMaxTuristas(int value) {
        this.cantMaxTuristas = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaAlta.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Define el valor de la propiedad fechaAlta.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setFechaAlta(LocalDate value) {
        this.fechaAlta = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaSalida.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaSalida() {
        return fechaSalida;
    }

    /**
     * Define el valor de la propiedad fechaSalida.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaSalida(String value) {
        this.fechaSalida = value;
    }

    /**
     * Obtiene el valor de la propiedad horaSalida.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoraSalida() {
        return horaSalida;
    }

    /**
     * Define el valor de la propiedad horaSalida.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoraSalida(String value) {
        this.horaSalida = value;
    }

    /**
     * Obtiene el valor de la propiedad imgSrc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImgSrc() {
        return imgSrc;
    }

    /**
     * Define el valor de la propiedad imgSrc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImgSrc(String value) {
        this.imgSrc = value;
    }

    /**
     * Obtiene el valor de la propiedad lugarSalida.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarSalida() {
        return lugarSalida;
    }

    /**
     * Define el valor de la propiedad lugarSalida.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarSalida(String value) {
        this.lugarSalida = value;
    }

    /**
     * Obtiene el valor de la propiedad lugaresDisponibles.
     * 
     */
    public int getLugaresDisponibles() {
        return lugaresDisponibles;
    }

    /**
     * Define el valor de la propiedad lugaresDisponibles.
     * 
     */
    public void setLugaresDisponibles(int value) {
        this.lugaresDisponibles = value;
    }

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

}
