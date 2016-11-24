package com.ushine.ssh.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ushine.common.vo.PagingObject;
import com.ushine.common.vo.ViewObject;
import com.ushine.core.cache.ThisNodeInfoCache;
import com.ushine.ssh.model.ThisNodeInfo;
import com.ushine.ssh.model.VocationalWork;
import com.ushine.ssh.model.VocationalWorkSystemVersion;
import com.ushine.ssh.service.IVocationalWorkSystemVersionService;
import com.ushine.util.FileUtils;
import com.ushine.util.StringUtil;
import com.ushine.util.UpLoadUtil;
/**
 * 业务系统插件控制器
 * @author wangbailin
 *
 */
@Controller
public class VocationalWorkSystemVersionController {
	@Autowired
	private IVocationalWorkSystemVersionService systemVersionService;
	/**
	 * 新增业务系统插件控制器
	 * @return
	 */
	@RequestMapping(value="/saveVocationalWorkSystemVersionService.do",method=RequestMethod.POST)
	@ResponseBody
	public String saveVocationalWorkSystemVersionService(
			HttpServletRequest request){
		return null;
	}
	
	
	/**
	 * 增加临时jar包
	 * @param request
	 * @param response
	 * @return  返回文件名字
	 */
	@RequestMapping(value = "/addTempVocationalWorkSystemJar.do", method = RequestMethod.POST)
	@ResponseBody
	public String addMarkRecordUpload(
			@RequestParam("number") String number,
			HttpServletRequest request, 
			HttpServletResponse response) {
		String path = request.getSession().getServletContext().getRealPath(File.separator+"TempVocationalWorkSystemJar");
		//如果文件夹不存在则创建文件夹
		File fileT = new File(path);
		 if (!fileT.exists()) {
			 fileT.mkdirs();
	      }
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		String fileName="";
		try {
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			for (FileItem fileItem : list) {
				if(!"null".equals(fileItem.getName())||null!=fileItem.getName()){
						int index = fileItem.getName().lastIndexOf(".");
						String postfix = fileItem.getName().substring(index);
						fileName = number+"_"+fileItem.getName();
						File file = new File(path +File.separator+fileName);
						fileItem.write(file);
						fileItem.delete();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "文件上传出错";
			return new ViewObject(-1, msg).toJSon();
		}
		return new ViewObject(ViewObject.RET_SUCCEED,fileName).toJSon();
	}
	/**
	 * 希增业务系统插件
	 * @return
	 */
	@RequestMapping(value="/saveVocationalWorkSystemVersion.do",method=RequestMethod.POST)
	@ResponseBody
	public String saveVocationalWorkSystemVersion(
			HttpServletRequest request,
			@RequestParam("version") String version,
			@RequestParam("remarks") String remarks,
			@RequestParam("jar") String jar,
			@RequestParam("systemId") String systemId){
		//取得当前节点信息
		ThisNodeInfo thisNodeInfo = ThisNodeInfoCache.getInstance().getThisNodeInfo();
		//待copy的file路径
		String path = request.getSession().getServletContext().getRealPath(File.separator+"TempVocationalWorkSystemJar");
		//copy路径地址
		String path1 = request.getSession().getServletContext().getRealPath(File.separator+"vocationalWorkJar"+File.separator+thisNodeInfo.getNodeCode());
		
		
		
		//把临时的jar包写进vocationalWork/nodeCode包中
		boolean b = FileUtils.copyFile(path1, new File(path+File.separator+jar));
		if(b){
			VocationalWorkSystemVersion v = new VocationalWorkSystemVersion();
			v.setFileName(jar);
			v.setFilePath("vocationalWorkJar"+File.separator+thisNodeInfo.getNodeCode()+File.separator+jar);
			v.setRemarks(remarks);
			v.setUploadTime(StringUtil.dates());
			v.setVersion(version);
			v.setVocationalWorkSystemId(systemId);
			v.setStatus("0");
			try {
				systemVersionService.saveVocationalWorkSystemVersion(v);
				FileUtils.deleteFile(path+"/"+jar);
				return new ViewObject(ViewObject.RET_SUCCEED, "添加业务系统插件成功!").toJSon();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return new ViewObject(ViewObject.RET_FAILURE, "添加业务系统插件失败!").toJSon();
	}
	
	/**
	 * 根据业务系统id查询业务系统插件
	 * @return
	 */
	@RequestMapping(value="/findVocationalWorkVersionBySystemId",method=RequestMethod.GET)
	@ResponseBody
	public String findVocationalWorkVersionBySystemId(
			HttpServletRequest request,
			@RequestParam("systemId") String systemId,
			@RequestParam("page") int nextPage,
			@RequestParam("limit") int size){
		try {
			PagingObject<VocationalWorkSystemVersion> object = systemVersionService.findVocationalWorkVersionBySystemId(systemId, nextPage, size);
			return findVocationalWorkVersionBySystemIdVoToJSon(object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String findVocationalWorkVersionBySystemIdVoToJSon(PagingObject<VocationalWorkSystemVersion> vo) throws Exception {
		JSONObject root = new JSONObject();
		root.element("paging", vo.getPaging());
		JSONArray array = new JSONArray();
		for (VocationalWorkSystemVersion v : vo.getArray()) {
			JSONObject obj = new JSONObject();
			obj.put("id", v.getId());
			obj.put("fileName", v.getFileName());
			obj.put("version", v.getVersion());
			obj.put("uploadTime", v.getUploadTime());
			obj.put("remarks", v.getRemarks());
			array.add(obj);
		}
		root.element("datas", array);
		return root.toString();
	}
}
