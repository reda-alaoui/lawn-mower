//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.03.11 at 05:20:28 PM CET 
//


package com.mowitnow.lawnmower.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://localhost/mowitnow}lawnConfiguration"/>
 *         &lt;element ref="{http://localhost/mowitnow}mowersConfiguration"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "lawnConfiguration",
    "mowersConfiguration"
})
@XmlRootElement(name = "configuration")
public class Configuration {

    @XmlElement(required = true)
    protected LawnConfiguration lawnConfiguration;
    @XmlElement(required = true)
    protected MowersConfiguration mowersConfiguration;

    /**
     * Gets the value of the lawnConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link LawnConfiguration }
     *     
     */
    public LawnConfiguration getLawnConfiguration() {
        return lawnConfiguration;
    }

    /**
     * Sets the value of the lawnConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link LawnConfiguration }
     *     
     */
    public void setLawnConfiguration(LawnConfiguration value) {
        this.lawnConfiguration = value;
    }

    /**
     * Gets the value of the mowersConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link MowersConfiguration }
     *     
     */
    public MowersConfiguration getMowersConfiguration() {
        return mowersConfiguration;
    }

    /**
     * Sets the value of the mowersConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link MowersConfiguration }
     *     
     */
    public void setMowersConfiguration(MowersConfiguration value) {
        this.mowersConfiguration = value;
    }

}
