
package com.ushine.ssh.webcline;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>saveSubordinateNodeInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="saveSubordinateNodeInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="str" type="{http://webservice.ssh.ushine.com/}subordinateNodeInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saveSubordinateNodeInfo", propOrder = {
    "str"
})
public class SaveSubordinateNodeInfo {

    protected SubordinateNodeInfo str;

    /**
     * ��ȡstr���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link SubordinateNodeInfo }
     *     
     */
    public SubordinateNodeInfo getStr() {
        return str;
    }

    /**
     * ����str���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link SubordinateNodeInfo }
     *     
     */
    public void setStr(SubordinateNodeInfo value) {
        this.str = value;
    }

}
