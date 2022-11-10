
package webservices;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtClaseWS complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtClaseWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alumnos" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="calificacionesData" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="calificacionesHead" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="correoProfesor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaClase" type="{http://webServices/}dtFechaWS" minOccurs="0"/>
 *         &lt;element name="fechaRegistro" type="{http://webServices/}dtFechaWS" minOccurs="0"/>
 *         &lt;element name="imgName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maxSocios" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="minSocios" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nicknameProfesor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="premio" type="{http://webServices/}dtPremioWS" minOccurs="0"/>
 *         &lt;element name="soloNickAlumnos" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="urlVideo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlwebsite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtClaseWS", propOrder = {
    "alumnos",
    "calificacionesData",
    "calificacionesHead",
    "correoProfesor",
    "fechaClase",
    "fechaRegistro",
    "imgName",
    "maxSocios",
    "minSocios",
    "nicknameProfesor",
    "nombre",
    "premio",
    "soloNickAlumnos",
    "urlVideo",
    "urlwebsite"
})
public class DtClaseWS {

    @XmlElement(nillable = true)
    protected List<String> alumnos;
    @XmlElement(nillable = true)
    protected List<Integer> calificacionesData;
    @XmlElement(nillable = true)
    protected List<String> calificacionesHead;
    protected String correoProfesor;
    protected DtFechaWS fechaClase;
    protected DtFechaWS fechaRegistro;
    protected String imgName;
    protected int maxSocios;
    protected int minSocios;
    protected String nicknameProfesor;
    protected String nombre;
    protected DtPremioWS premio;
    @XmlElement(nillable = true)
    protected List<String> soloNickAlumnos;
    protected String urlVideo;
    protected String urlwebsite;

    /**
     * Gets the value of the alumnos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alumnos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlumnos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAlumnos() {
        if (alumnos == null) {
            alumnos = new ArrayList<String>();
        }
        return this.alumnos;
    }

    /**
     * Gets the value of the calificacionesData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the calificacionesData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCalificacionesData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getCalificacionesData() {
        if (calificacionesData == null) {
            calificacionesData = new ArrayList<Integer>();
        }
        return this.calificacionesData;
    }

    /**
     * Gets the value of the calificacionesHead property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the calificacionesHead property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCalificacionesHead().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCalificacionesHead() {
        if (calificacionesHead == null) {
            calificacionesHead = new ArrayList<String>();
        }
        return this.calificacionesHead;
    }

    /**
     * Obtiene el valor de la propiedad correoProfesor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorreoProfesor() {
        return correoProfesor;
    }

    /**
     * Define el valor de la propiedad correoProfesor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorreoProfesor(String value) {
        this.correoProfesor = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaClase.
     * 
     * @return
     *     possible object is
     *     {@link DtFechaWS }
     *     
     */
    public DtFechaWS getFechaClase() {
        return fechaClase;
    }

    /**
     * Define el valor de la propiedad fechaClase.
     * 
     * @param value
     *     allowed object is
     *     {@link DtFechaWS }
     *     
     */
    public void setFechaClase(DtFechaWS value) {
        this.fechaClase = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaRegistro.
     * 
     * @return
     *     possible object is
     *     {@link DtFechaWS }
     *     
     */
    public DtFechaWS getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Define el valor de la propiedad fechaRegistro.
     * 
     * @param value
     *     allowed object is
     *     {@link DtFechaWS }
     *     
     */
    public void setFechaRegistro(DtFechaWS value) {
        this.fechaRegistro = value;
    }

    /**
     * Obtiene el valor de la propiedad imgName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImgName() {
        return imgName;
    }

    /**
     * Define el valor de la propiedad imgName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImgName(String value) {
        this.imgName = value;
    }

    /**
     * Obtiene el valor de la propiedad maxSocios.
     * 
     */
    public int getMaxSocios() {
        return maxSocios;
    }

    /**
     * Define el valor de la propiedad maxSocios.
     * 
     */
    public void setMaxSocios(int value) {
        this.maxSocios = value;
    }

    /**
     * Obtiene el valor de la propiedad minSocios.
     * 
     */
    public int getMinSocios() {
        return minSocios;
    }

    /**
     * Define el valor de la propiedad minSocios.
     * 
     */
    public void setMinSocios(int value) {
        this.minSocios = value;
    }

    /**
     * Obtiene el valor de la propiedad nicknameProfesor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNicknameProfesor() {
        return nicknameProfesor;
    }

    /**
     * Define el valor de la propiedad nicknameProfesor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNicknameProfesor(String value) {
        this.nicknameProfesor = value;
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

    /**
     * Obtiene el valor de la propiedad premio.
     * 
     * @return
     *     possible object is
     *     {@link DtPremioWS }
     *     
     */
    public DtPremioWS getPremio() {
        return premio;
    }

    /**
     * Define el valor de la propiedad premio.
     * 
     * @param value
     *     allowed object is
     *     {@link DtPremioWS }
     *     
     */
    public void setPremio(DtPremioWS value) {
        this.premio = value;
    }

    /**
     * Gets the value of the soloNickAlumnos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the soloNickAlumnos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSoloNickAlumnos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSoloNickAlumnos() {
        if (soloNickAlumnos == null) {
            soloNickAlumnos = new ArrayList<String>();
        }
        return this.soloNickAlumnos;
    }

    /**
     * Obtiene el valor de la propiedad urlVideo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlVideo() {
        return urlVideo;
    }

    /**
     * Define el valor de la propiedad urlVideo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlVideo(String value) {
        this.urlVideo = value;
    }

    /**
     * Obtiene el valor de la propiedad urlwebsite.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlwebsite() {
        return urlwebsite;
    }

    /**
     * Define el valor de la propiedad urlwebsite.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlwebsite(String value) {
        this.urlwebsite = value;
    }

}
