
package com.ushine.ssh.webcline;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>sumitVocationalWork complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡstr���Ե�ֵ��
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
     * ����str���Ե�ֵ��
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
