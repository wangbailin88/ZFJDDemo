
package com.ushine.ssh.webcline;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>sumitVocationalWork complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="sumitVocationalWork">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="str" type="{http://webservice.ssh.ushine.com/}vocationalWork" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sumitVocationalWork", propOrder = {
    "str"
})
public class SumitVocationalWork {

    protected VocationalWork str;

    /**
     * 获取str属性的值。
     * 
     * @return
     *     possible object is
     *     {@link VocationalWork }
     *     
     */
    public VocationalWork getStr() {
        return str;
    }

    /**
     * 设置str属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link VocationalWork }
     *     
     */
    public void setStr(VocationalWork value) {
        this.str = value;
    }

}
