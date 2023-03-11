
package servidor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dataInscripcionP complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dataInscripcionP">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="cantTuristas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="costo" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         <element name="fechaInscripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="salida" type="{http://servidor/}dataSalidaTuristicaP" minOccurs="0"/>
 *         <element name="turista" type="{http://servidor/}dataTuristaP" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataInscripcionP", propOrder = {
    "cantTuristas",
    "costo",
    "fechaInscripcion",
    "salida",
    "turista"
})
public class DataInscripcionP {

    protected int cantTuristas;
    protected double costo;
    protected String fechaInscripcion;
    protected DataSalidaTuristicaP salida;
    protected DataTuristaP turista;

    /**
     * Obtiene el valor de la propiedad cantTuristas.
     * 
     */
    public int getCantTuristas() {
        return cantTuristas;
    }

    /**
     * Define el valor de la propiedad cantTuristas.
     * 
     */
    public void setCantTuristas(int value) {
        this.cantTuristas = value;
    }

    /**
     * Obtiene el valor de la propiedad costo.
     * 
     */
    public double getCosto() {
        return costo;
    }

    /**
     * Define el valor de la propiedad costo.
     * 
     */
    public void setCosto(double value) {
        this.costo = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaInscripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    /**
     * Define el valor de la propiedad fechaInscripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaInscripcion(String value) {
        this.fechaInscripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad salida.
     * 
     * @return
     *     possible object is
     *     {@link DataSalidaTuristicaP }
     *     
     */
    public DataSalidaTuristicaP getSalida() {
        return salida;
    }

    /**
     * Define el valor de la propiedad salida.
     * 
     * @param value
     *     allowed object is
     *     {@link DataSalidaTuristicaP }
     *     
     */
    public void setSalida(DataSalidaTuristicaP value) {
        this.salida = value;
    }

    /**
     * Obtiene el valor de la propiedad turista.
     * 
     * @return
     *     possible object is
     *     {@link DataTuristaP }
     *     
     */
    public DataTuristaP getTurista() {
        return turista;
    }

    /**
     * Define el valor de la propiedad turista.
     * 
     * @param value
     *     allowed object is
     *     {@link DataTuristaP }
     *     
     */
    public void setTurista(DataTuristaP value) {
        this.turista = value;
    }

}
