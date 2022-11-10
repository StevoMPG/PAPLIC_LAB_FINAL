
package webservices;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tRegWS.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="tRegWS">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="cuponera"/>
 *     &lt;enumeration value="general"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tRegWS")
@XmlEnum
public enum TRegWS {

    @XmlEnumValue("cuponera")
    CUPONERA("cuponera"),
    @XmlEnumValue("general")
    GENERAL("general");
    private final String value;

    TRegWS(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TRegWS fromValue(String v) {
        for (TRegWS c: TRegWS.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
