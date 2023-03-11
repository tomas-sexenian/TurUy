
package servidor;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dataTurista complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dataTurista">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="apellido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="compras" type="{http://servidor/}dataCompra" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="imgSrc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="inscripciones" type="{http://servidor/}dataInscripcion" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="nacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nacionalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nickname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "dataTurista", propOrder = {
    "apellido",
    "compras",
    "email",
    "imgSrc",
    "inscripciones",
    "nacimiento",
    "nacionalidad",
    "nickname",
    "nombre"
})
public class DataTurista {

    protected String apellido;
    @XmlElement(nillable = true)
    protected List<DataCompra> compras;
    protected String email;
    protected String imgSrc;
    @XmlElement(nillable = true)
    protected List<DataInscripcion> inscripciones;
    protected String nacimiento;
    protected String nacionalidad;
    protected String nickname;
    protected String nombre;

    /**
     * Obtiene el valor de la propiedad apellido.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Define el valor de la propiedad apellido.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido(String value) {
        this.apellido = value;
    }

    /**
     * Gets the value of the compras property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the compras property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCompras().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataCompra }
     * 
     * 
     * @return
     *     The value of the compras property.
     */
    public List<DataCompra> getCompras() {
        if (compras == null) {
            compras = new ArrayList<>();
        }
        return this.compras;
    }

    /**
     * Obtiene el valor de la propiedad email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define el valor de la propiedad email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
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
     * {@link DataInscripcion }
     * 
     * 
     * @return
     *     The value of the inscripciones property.
     */
    public List<DataInscripcion> getInscripciones() {
        if (inscripciones == null) {
            inscripciones = new ArrayList<>();
        }
        return this.inscripciones;
    }

    /**
     * Obtiene el valor de la propiedad nacimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacimiento() {
        return nacimiento;
    }

    /**
     * Define el valor de la propiedad nacimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacimiento(String value) {
        this.nacimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad nacionalidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Define el valor de la propiedad nacionalidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacionalidad(String value) {
        this.nacionalidad = value;
    }

    /**
     * Obtiene el valor de la propiedad nickname.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Define el valor de la propiedad nickname.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNickname(String value) {
        this.nickname = value;
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
