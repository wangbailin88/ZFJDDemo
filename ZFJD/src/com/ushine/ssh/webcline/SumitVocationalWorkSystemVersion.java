
package com.ushine.ssh.webcline;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>sumitVocationalWorkSystemVersion complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡhan���Ե�ֵ��
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
     * ����han���Ե�ֵ��
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
