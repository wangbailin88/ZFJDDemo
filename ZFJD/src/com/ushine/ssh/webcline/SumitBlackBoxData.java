
package com.ushine.ssh.webcline;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>sumitBlackBoxData complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="sumitBlackBoxData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="str" type="{http://webservice.ssh.ushine.com/}blackBoxStatusInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sumitBlackBoxData", propOrder = {
    "str"
})
public class SumitBlackBoxData {

    protected List<BlackBoxStatusInfo> str;

    /**
     * Gets the value of the str property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the str property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStr().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BlackBoxStatusInfo }
     * 
     * 
     */
    public List<BlackBoxStatusInfo> getStr() {
        if (str == null) {
            str = new ArrayList<BlackBoxStatusInfo>();
        }
        return this.str;
    }

}
