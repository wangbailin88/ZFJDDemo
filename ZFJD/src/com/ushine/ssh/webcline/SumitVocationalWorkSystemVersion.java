
package com.ushine.ssh.webcline;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>sumitVocationalWorkSystemVersion complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="sumitVocationalWorkSystemVersion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="han" type="{http://webservice.ssh.ushine.com/}vocationalWorkSystemVersionHan" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sumitVocationalWorkSystemVersion", propOrder = {
    "han"
})
public class SumitVocationalWorkSystemVersion {

    protected VocationalWorkSystemVersionHan han;

    /**
     * 获取han属性的值。
     * 
     * @return
     *     possible object is
     *     {@link VocationalWorkSystemVersionHan }
     *     
     */
    public VocationalWorkSystemVersionHan getHan() {
        return han;
    }

    /**
     * 设置han属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link VocationalWorkSystemVersionHan }
     *     
     */
    public void setHan(VocationalWorkSystemVersionHan value) {
        this.han = value;
    }

}
