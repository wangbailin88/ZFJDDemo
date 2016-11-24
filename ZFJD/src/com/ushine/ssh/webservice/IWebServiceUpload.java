package com.ushine.ssh.webservice;
/**
 * webservice upload接口

 * 
 */

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.soap.MTOM;

import com.ushine.ssh.model.Han;
import com.ushine.ssh.model.VocationalWorkSystemVersion;
import com.ushine.ssh.model.VocationalWorkSystemVersionHan;
@WebService
@MTOM
public interface IWebServiceUpload {
	@WebResult(name = "result")
	public boolean sumitVocationalWorkSystemVersion(@WebParam(name = "han")VocationalWorkSystemVersionHan han)throws Exception;
	
}
