//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.04.06 at 04:24:38 PM CEST 
//


package com.mikalai.touristvouchers_jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExtendedHotelCharacteristic complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ExtendedHotelCharacteristic"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://example.com/TouristVouchers}HotelCharacteristic"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="wifiIncluded" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtendedHotelCharacteristic", propOrder = {
        "wifiIncluded"
})
public class ExtendedHotelCharacteristic
        extends HotelCharacteristic {

  protected boolean wifiIncluded;

  /**
   * Gets the value of the wifiIncluded property.
   */
  public boolean isWifiIncluded() {
    return wifiIncluded;
  }

  /**
   * Sets the value of the wifiIncluded property.
   */
  public void setWifiIncluded(boolean value) {
    this.wifiIncluded = value;
  }

}
