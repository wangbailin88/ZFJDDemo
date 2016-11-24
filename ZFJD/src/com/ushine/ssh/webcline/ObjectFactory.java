
package com.ushine.ssh.webcline;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ushine.ssh.webcline package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Exception_QNAME = new QName("http://webservice.ssh.ushine.com/", "Exception");
    private final static QName _SumitBlackBoxDataResponse_QNAME = new QName("http://webservice.ssh.ushine.com/", "sumitBlackBoxDataResponse");
    private final static QName _SumitVocationalWork_QNAME = new QName("http://webservice.ssh.ushine.com/", "sumitVocationalWork");
    private final static QName _SumitBlackBoxData_QNAME = new QName("http://webservice.ssh.ushine.com/", "sumitBlackBoxData");
    private final static QName _SaveSubordinateNodeInfo_QNAME = new QName("http://webservice.ssh.ushine.com/", "saveSubordinateNodeInfo");
    private final static QName _SumitVocationalWorkResponse_QNAME = new QName("http://webservice.ssh.ushine.com/", "sumitVocationalWorkResponse");
    private final static QName _SaveSubordinateNodeInfoResponse_QNAME = new QName("http://webservice.ssh.ushine.com/", "saveSubordinateNodeInfoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ushine.ssh.webcline
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link SumitVocationalWork }
     * 
     */
    public SumitVocationalWork createSumitVocationalWork() {
        return new SumitVocationalWork();
    }

    /**
     * Create an instance of {@link SumitBlackBoxDataResponse }
     * 
     */
    public SumitBlackBoxDataResponse createSumitBlackBoxDataResponse() {
        return new SumitBlackBoxDataResponse();
    }

    /**
     * Create an instance of {@link SumitBlackBoxData }
     * 
     */
    public SumitBlackBoxData createSumitBlackBoxData() {
        return new SumitBlackBoxData();
    }

    /**
     * Create an instance of {@link SumitVocationalWorkResponse }
     * 
     */
    public SumitVocationalWorkResponse createSumitVocationalWorkResponse() {
        return new SumitVocationalWorkResponse();
    }

    /**
     * Create an instance of {@link SaveSubordinateNodeInfo }
     * 
     */
    public SaveSubordinateNodeInfo createSaveSubordinateNodeInfo() {
        return new SaveSubordinateNodeInfo();
    }

    /**
     * Create an instance of {@link SaveSubordinateNodeInfoResponse }
     * 
     */
    public SaveSubordinateNodeInfoResponse createSaveSubordinateNodeInfoResponse() {
        return new SaveSubordinateNodeInfoResponse();
    }

    /**
     * Create an instance of {@link VocationalWork }
     * 
     */
    public VocationalWork createVocationalWork() {
        return new VocationalWork();
    }

    /**
     * Create an instance of {@link BlackBoxStatusInfo }
     * 
     */
    public BlackBoxStatusInfo createBlackBoxStatusInfo() {
        return new BlackBoxStatusInfo();
    }

    /**
     * Create an instance of {@link SubordinateNodeInfo }
     * 
     */
    public SubordinateNodeInfo createSubordinateNodeInfo() {
        return new SubordinateNodeInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ssh.ushine.com/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SumitBlackBoxDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ssh.ushine.com/", name = "sumitBlackBoxDataResponse")
    public JAXBElement<SumitBlackBoxDataResponse> createSumitBlackBoxDataResponse(SumitBlackBoxDataResponse value) {
        return new JAXBElement<SumitBlackBoxDataResponse>(_SumitBlackBoxDataResponse_QNAME, SumitBlackBoxDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SumitVocationalWork }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ssh.ushine.com/", name = "sumitVocationalWork")
    public JAXBElement<SumitVocationalWork> createSumitVocationalWork(SumitVocationalWork value) {
        return new JAXBElement<SumitVocationalWork>(_SumitVocationalWork_QNAME, SumitVocationalWork.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SumitBlackBoxData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ssh.ushine.com/", name = "sumitBlackBoxData")
    public JAXBElement<SumitBlackBoxData> createSumitBlackBoxData(SumitBlackBoxData value) {
        return new JAXBElement<SumitBlackBoxData>(_SumitBlackBoxData_QNAME, SumitBlackBoxData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveSubordinateNodeInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ssh.ushine.com/", name = "saveSubordinateNodeInfo")
    public JAXBElement<SaveSubordinateNodeInfo> createSaveSubordinateNodeInfo(SaveSubordinateNodeInfo value) {
        return new JAXBElement<SaveSubordinateNodeInfo>(_SaveSubordinateNodeInfo_QNAME, SaveSubordinateNodeInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SumitVocationalWorkResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ssh.ushine.com/", name = "sumitVocationalWorkResponse")
    public JAXBElement<SumitVocationalWorkResponse> createSumitVocationalWorkResponse(SumitVocationalWorkResponse value) {
        return new JAXBElement<SumitVocationalWorkResponse>(_SumitVocationalWorkResponse_QNAME, SumitVocationalWorkResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveSubordinateNodeInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ssh.ushine.com/", name = "saveSubordinateNodeInfoResponse")
    public JAXBElement<SaveSubordinateNodeInfoResponse> createSaveSubordinateNodeInfoResponse(SaveSubordinateNodeInfoResponse value) {
        return new JAXBElement<SaveSubordinateNodeInfoResponse>(_SaveSubordinateNodeInfoResponse_QNAME, SaveSubordinateNodeInfoResponse.class, null, value);
    }

}
