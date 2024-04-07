//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.04.06 at 04:24:38 PM CEST 
//


package com.mikalai.touristvouchers;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Transport.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Transport"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="air"/&gt;
 *     &lt;enumeration value="rail"/&gt;
 *     &lt;enumeration value="auto"/&gt;
 *     &lt;enumeration value="liner"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "Transport")
@XmlEnum
public enum Transport {

    @XmlEnumValue("air")
    AIR("air"),
    @XmlEnumValue("rail")
    RAIL("rail"),
    @XmlEnumValue("auto")
    AUTO("auto"),
    @XmlEnumValue("liner")
    LINER("liner");
    private final String value;

    Transport(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Transport fromValue(String v) {
        for (Transport c: Transport.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
