package com.ushine.ssh.webservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.beans.factory.annotation.Autowired;

import com.ushine.core.cache.RequestPathCache;
import com.ushine.ssh.model.Han;
import com.ushine.ssh.model.VocationalWorkSystemVersion;
import com.ushine.ssh.model.VocationalWorkSystemVersionHan;
import com.ushine.ssh.service.IVocationalWorkSystemVersionService;

/**
 * webservice upload接口实现类
 * @author wangbailin
 *
 */
public class WebServiceUploadImpl implements IWebServiceUpload{
	@Autowired
	private IVocationalWorkSystemVersionService vocationalWorkSystemVersionService;
	@Override
	@WebResult(name = "result")
	public boolean sumitVocationalWorkSystemVersion(
			@WebParam(name = "han")VocationalWorkSystemVersionHan han) throws Exception {
		// TODO Auto-generated method stub
		DataHandler handler = han.getDataHandler();
		//获得上传jar的存储地址
		String path = RequestPathCache.getInstance().getPath()+"vocationalWorkJar"+File.separator+han.getNodeCode();
		File fileT = new File(path);
		if (!fileT.exists()) {
			 fileT.mkdirs();
	      }
		//获得上传的jar包
		InputStream is = handler.getInputStream();
		System.out.println(is);
		OutputStream os = new FileOutputStream(new File(path+File.separator+han.getFileName()));
		System.out.println(os);
		byte[] b = new byte[1024];
		int len = 0;
		while((len=is.read(b))!=-1){
			os.write(b, 0, len);
		}
		os.flush();
		os.close();
		is.close();
		VocationalWorkSystemVersion v = new VocationalWorkSystemVersion();
		v.setFileName(han.getFileName());
		v.setFilePath(han.getFilePath());
		v.setRemarks(han.getRemarks());
		v.setStatus(han.getStatus());
		v.setUploadTime(han.getUploadTime());
		v.setVersion(han.getVersion());
		v.setVocationalWorkSystemId(han.getVocationalWorkSystemId());
		vocationalWorkSystemVersionService.saveVocationalWorkSystemVersion(v);
		return true;
	}
}
