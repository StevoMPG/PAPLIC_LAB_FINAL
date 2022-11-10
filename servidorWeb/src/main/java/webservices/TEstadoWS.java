
package webservices;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tEstadoWS.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="tEstadoWS">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ingresada"/>
 *     &lt;enumeration value="aceptada"/>
 *     &lt;enumeration value="rechazada"/>
 *     &lt;enumeration value="finalizada"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tEstadoWS")
@XmlEnum
public enum TEstadoWS {

    @XmlEnumValue("ingresada")
    INGRESADA("ingresada"),
    @XmlEnumValue("aceptada")
    ACEPTADA("aceptada"),
    @XmlEnumValue("rechazada")
    RECHAZADA("rechazada"),
    @XmlEnumValue("finalizada")
    FINALIZADA("finalizada");
    private final String value;

    TEstadoWS(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TEstadoWS fromValue(String v) {
        for (TEstadoWS c: TEstadoWS.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
