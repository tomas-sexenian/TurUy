
package servidor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dataCompra complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dataCompra">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="cantTuristas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="costo" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         <element name="fechaCompra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="paquete" type="{http://servidor/}dataPaqueteActividades" minOccurs="0"/>
 *         <element name="valid" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         <element name="validez" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataCompra", propOrder = {
    "cantTuristas",
    "costo",
    "fechaCompra",
    "paquete",
    "valid",
    "validez"
})
public class DataCompra {

    protected int cantTuristas;
    protected double costo;
    protected String fechaCompra;
    protected DataPaqueteActividades paquete;
    protected boolean valid;
    protected String validez;

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
     * Obtiene el valor de la propiedad fechaCompra.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaCompra() {
        return fechaCompra;
    }

    /**
     * Define el valor de la propiedad fechaCompra.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaCompra(String value) {
        this.fechaCompra = value;
    }

    /**
     * Obtiene el valor de la propiedad paquete.
     * 
     * @return
     *     possible object is
     *     {@link DataPaqueteActividades }
     *     
     */
    public DataPaqueteActividades getPaquete() {
        return paquete;
    }

    /**
     * Define el valor de la propiedad paquete.
     * 
     * @param value
     *     allowed object is
     *     {@link DataPaqueteActividades }
     *     
     */
    public void setPaquete(DataPaqueteActividades value) {
        this.paquete = value;
    }

    /**
     * Obtiene el valor de la propiedad valid.
     * 
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Define el valor de la propiedad valid.
     * 
     */
    public void setValid(boolean value) {
        this.valid = value;
    }

    /**
     * Obtiene el valor de la propiedad validez.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidez() {
        return validez;
    }

    /**
     * Define el valor de la propiedad validez.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidez(String value) {
        this.validez = value;
    }

}
