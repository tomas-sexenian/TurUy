
package servidor;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dataSalidaTuristicaP complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dataSalidaTuristicaP">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="cantMaxTuristas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="fechaAlta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaSalida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="horaSalida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="inscripciones" type="{http://servidor/}dataInscripcionP" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="lugarSalida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "dataSalidaTuristicaP", propOrder = {
    "cantMaxTuristas",
    "fechaAlta",
    "fechaSalida",
    "horaSalida",
    "inscripciones",
    "lugarSalida",
    "nombre"
})
public class DataSalidaTuristicaP {

    protected int cantMaxTuristas;
    protected String fechaAlta;
    protected String fechaSalida;
    protected String horaSalida;
    @XmlElement(nillable = true)
    protected List<DataInscripcionP> inscripciones;
    protected String lugarSalida;
    protected String nombre;

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
     *     {@link String }
     *     
     */
    public String getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Define el valor de la propiedad fechaAlta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaAlta(String value) {
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
     * Gets the value of the inscripciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the inscripciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInscripciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataInscripcionP }
     * 
     * 
     * @return
     *     The value of the inscripciones property.
     */
    public List<DataInscripcionP> getInscripciones() {
        if (inscripciones == null) {
            inscripciones = new ArrayList<>();
        }
        return this.inscripciones;
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
