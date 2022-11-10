
package webservices;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtClasesCuponeraWS complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtClasesCuponeraWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cantidadClases" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nombreActividad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtClasesCuponeraWS", propOrder = {
    "cantidadClases",
    "nombreActividad"
})
public class DtClasesCuponeraWS {

    protected int cantidadClases;
    protected String nombreActividad;

    /**
     * Obtiene el valor de la propiedad cantidadClases.
     * 
     */
    public int getCantidadClases() {
        return cantidadClases;
    }

    /**
     * Define el valor de la propiedad cantidadClases.
     * 
     */
    public void setCantidadClases(int value) {
        this.cantidadClases = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreActividad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreActividad() {
        return nombreActividad;
    }

    /**
     * Define el valor de la propiedad nombreActividad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreActividad(String value) {
        this.nombreActividad = value;
    }

}
