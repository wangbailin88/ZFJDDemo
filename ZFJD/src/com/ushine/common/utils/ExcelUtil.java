package com.ushine.common.utils;
import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
public class ExcelUtil {
		 
	    private String excelPath="none";
		private String filename="0000-00-00";
		//传入数据集
		private Map map=null;
		private List list=null;
		//传入的字段以冒号分隔 如："a&b&c"
		private String fields="";
		//传入的显示在第一行的头 以"&"分隔 如："a&b&c"
		private String viewFields="";
		//构造传入参数
		private String title;
		public ExcelUtil(Map mapObj,String fields)
		{
			
			
			this.map=mapObj;
			this.fields=fields;
			
		}
		public ExcelUtil(String filename,String filePath,String viewFields,String fields,Map map){
			excelPath=filePath;
			this.filename=filename;
			this.map=map;
			this.fields=fields;
			this.viewFields=viewFields;
			//System.out.println(filename);
		}
		
		public ExcelUtil(String filename,String filePath,String viewFields,String fields,Map mapObj,String title){
			excelPath=filePath;
			this.filename=filename;
			this.map=mapObj;
			this.fields=fields;
			this.viewFields=viewFields;
			this.title=title;
			//System.out.println(filename);
		}
		
		public ExcelUtil(List listObj,String fields)
		{
			
			
			list=listObj;
			this.fields=fields;
			
		}
		public ExcelUtil(String filename,String filePath,String viewFields,String fields,List listObj){
			excelPath=filePath;
			this.filename=filename;
			list=listObj;
			this.fields=fields;
			this.viewFields=viewFields;
			//System.out.println(filename);
		}
		
		public ExcelUtil(String filename,String filePath,String viewFields,String fields,List listObj,String title){
			excelPath=filePath;
			this.filename=filename;
			list=listObj;
			this.fields=fields;
			this.viewFields=viewFields;
			this.title=title;
			//System.out.println(filename);
		}
		
		public boolean  writeExcel(HttpServletResponse response,boolean linenumber){
        
            boolean bool=true;
			try{
			File excelFilePath=new File(excelPath);
			if(!excelFilePath.exists()){
				excelFilePath.mkdirs();
				System.out.println(excelPath);
			}
		
			File excelFile=new File(excelPath+filename);
			   
			response.setContentType("application/vnd.ms-excel; charset=UTF-8");
	        response.setHeader("Content-Disposition","attachment; filename="+excelFile);
	        ServletOutputStream   os   =   response.getOutputStream();
			WritableWorkbook  wwb=Workbook.createWorkbook(os);
			WritableSheet ws=wwb.createSheet("sheet"+1,0);
			ws.getSettings().setDefaultColumnWidth(20); 
			ws.setColumnView(0,6);
			//写标题
			writeTitleLine(title,viewFields, ws);
			//写第一行
			writeFirstLine(viewFields,ws);
			//写所有数据
			if(linenumber){
			writeAllLine(fields,list,ws,linenumber);}
			else{
			 writeAllLine(fields,list,ws);
			}
			wwb.write();
			wwb.close();
			
			}
			catch(Exception e){
				bool=false;
				e.printStackTrace(); 
			}
			return bool;
		}
		
		
		public boolean  writeExcelMap(HttpServletResponse response,boolean linenumber){
            boolean bool=true;
			try{
			File excelFilePath=new File(excelPath);
			if(!excelFilePath.exists()){
				excelFilePath.mkdirs();
				System.out.println(excelPath);
			}
		
			File excelFile=new File(excelPath+filename);
			   
			response.setContentType("application/vnd.ms-excel; charset=UTF-8");
	        response.setHeader("Content-Disposition","attachment; filename="+excelFile);
	        ServletOutputStream   os   =   response.getOutputStream();
			WritableWorkbook  wwb=Workbook.createWorkbook(os);
			WritableSheet ws=wwb.createSheet("sheet"+1,0);
			ws.getSettings().setDefaultColumnWidth(20); 
			ws.setColumnView(1,6);
			ws.setColumnView(0,25);
			//写标题
			writeTitleLine(title,viewFields, ws);
			//写第一行
			writeFirstLine(viewFields,ws);
			//写所有数据
			if(linenumber){
			writeAllLine(fields,map,ws,linenumber);}
			else{
			 writeAllLine(fields,map,ws);
			    }
			wwb.write();
			wwb.close();
			
			}
			catch(Exception e){
				bool=false;
				e.printStackTrace(); 
			}
			return bool;
		}
		
        private void writeAllLine(String fields2, Map map2, WritableSheet ws)throws Exception {
        	 WritableFont wf2 = new jxl.write.WritableFont(WritableFont
				      .createFont("宋体"), 10, WritableFont.NO_BOLD, false,
				      UnderlineStyle.NO_UNDERLINE, Colour.BLACK); 

				    WritableCellFormat wcf2 = new WritableCellFormat(wf2); 
				    wcf2.setAlignment(Alignment.CENTRE);
				    wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
				    wcf2.setBorder(Border.ALL, BorderLineStyle.THIN);
				    wcf2.setWrap(true);
				    
				    WritableFont wf3 = new jxl.write.WritableFont(WritableFont
						      .createFont("宋体"), 10, WritableFont.NO_BOLD, false,
						      UnderlineStyle.NO_UNDERLINE, Colour.BLACK); 

					WritableCellFormat wcf3 = new WritableCellFormat(wf3); 
					wcf3.setAlignment(Alignment.RIGHT);
				    wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);
					wcf3.setBorder(Border.ALL, BorderLineStyle.THIN);
				    wcf3.setWrap(true);   
				   //用于数字的格式
				  NumberFormat nfdb = new NumberFormat("###,##0.00");
				  WritableFont fontDouble = new jxl.write.WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false,
				  UnderlineStyle.NO_UNDERLINE, Colour.BLACK); 						        						      
				  WritableCellFormat cellFormatDouble = new WritableCellFormat(fontDouble,nfdb); 
				  cellFormatDouble.setAlignment(Alignment.RIGHT);
				  cellFormatDouble.setVerticalAlignment(VerticalAlignment.CENTRE);
				  cellFormatDouble.setBorder(Border.ALL, BorderLineStyle.THIN);
				  cellFormatDouble.setWrap(true);		
		    
				if(map2!=null)
				{
					int count=0; 
					Iterator it=map2.keySet().iterator();
					 int temp=0;
					 while(it.hasNext())
					 {
						String key=(String)it.next();
						 List maplist=(List)map2.get(key);
						 if(maplist.size()>0){
						
						 Label labelAll=new Label(0,temp+3,key.toString(),wcf2);
						 ws.mergeCells(0,temp+3,0,temp+3+maplist.size()-1);
						 ws.addCell(labelAll);
						 temp=temp+maplist.size();
						
						 }
						if(maplist!=null&&maplist.size()>0)
						{  
							for(int i=0,n=maplist.size();i<n;i++){
					    		Object obj=maplist.get(i);
					    		StringTokenizer strToken=new StringTokenizer(fields,"&");
					    		int j=1;
 
					    		while(strToken.hasMoreTokens()){
					    			Field f_1 = obj.getClass().getDeclaredField(strToken.nextToken());
					    			f_1.setAccessible(true);
									Object oo = f_1.get(obj);
								
									if(oo!=null){
										String typeName=f_1.getType().getName();
										
										if(typeName.equals("java.lang.String")){
											Label labelAll=new Label(j,count+3,oo.toString(),wcf2);
									    ws.addCell(labelAll);
										 }
										
										
										if(typeName.equals("java.lang.Double") || typeName.equals("double")){
											Double price=(Double)oo;
											Number labelAll2=new Number(j,count+3,price.doubleValue(),cellFormatDouble);
										    ws.addCell(labelAll2);
											}
										if(typeName.equals("int")){
											Label labelAll=new Label(j,count+3,oo.toString(),wcf2);
										    ws.addCell(labelAll);
											}
										if(typeName.equals("long")){
											Long date=(Long)oo;
											Label labelAll=new Label(j,count+3,date.toString(),wcf2);
										    ws.addCell(labelAll);
											}
										if(typeName.equals("java.sql.Timestamp")||typeName.equals("java.sql.Date")||typeName.equals("java.util.Date")){
											Date date=(Date)oo;
											Label labelAll=new Label(j,i+3,date.toString(),wcf2);
										    ws.addCell(labelAll);
											}
										if(typeName.equals("java.math.BigDecimal"))
										{
											BigDecimal bi=(BigDecimal)oo;
											Number labelAll=new Number (j,count+3,bi.doubleValue(),cellFormatDouble);
										    ws.addCell(labelAll);
										}
									}
									else
									{
										Label labelAll=new Label (j,count+3,"",wcf2);
									    ws.addCell(labelAll);
									}
								   
									j++;
					    		}
					    	count++;
							}	 
						}
					 }
						  
				}
	    	
	    	
			
		}
		private void writeAllLine(String fields2, Map map2, WritableSheet ws, boolean linenumber)throws Exception {
    		 WritableFont wf2 = new jxl.write.WritableFont(WritableFont
				      .createFont("宋体"), 10, WritableFont.NO_BOLD, false,
				      UnderlineStyle.NO_UNDERLINE, Colour.BLACK); 

				    WritableCellFormat wcf2 = new WritableCellFormat(wf2); 
				    wcf2.setAlignment(Alignment.CENTRE);
				    wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
				    wcf2.setBorder(Border.ALL, BorderLineStyle.THIN);
				    wcf2.setWrap(true);
				    
				    
				    WritableFont wf3 = new jxl.write.WritableFont(WritableFont
						      .createFont("宋体"), 10, WritableFont.NO_BOLD, false,
						      UnderlineStyle.NO_UNDERLINE, Colour.BLACK); 

						    WritableCellFormat wcf3 = new WritableCellFormat(wf3); 
						    wcf3.setAlignment(Alignment.RIGHT);
						    wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);
						    wcf3.setBorder(Border.ALL, BorderLineStyle.THIN);
						    wcf3.setWrap(true);		
						    
						    
						    //用于数字的格式
					NumberFormat nfdb = new NumberFormat("###,##0.00");
					WritableFont fontDouble = new jxl.write.WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false,
					UnderlineStyle.NO_UNDERLINE, Colour.BLACK); 						        						      
					WritableCellFormat cellFormatDouble = new WritableCellFormat(fontDouble,nfdb); 
					cellFormatDouble.setAlignment(Alignment.RIGHT);
					cellFormatDouble.setVerticalAlignment(VerticalAlignment.CENTRE);
					cellFormatDouble.setBorder(Border.ALL, BorderLineStyle.THIN);
					cellFormatDouble.setWrap(true);				    
				if(map2!=null)
				{
					int count=0; 
					Iterator it=map2.keySet().iterator();
					 int temp=0;
					 while(it.hasNext())
					 {
						String key=(String)it.next();
						 List maplist=(List)map2.get(key);
						 if(maplist.size()>0){ 
						 Label labelAll=new Label(0,temp+3,key.toString(),wcf2);
						 ws.mergeCells(0,temp+3,0,temp+3+maplist.size()-1);
						 ws.addCell(labelAll);
						 temp=temp+maplist.size(); 
						 }
						if(maplist!=null&&maplist.size()>0)
						{  
							for(int i=0,n=maplist.size();i<n;i++){
					    		Object obj=maplist.get(i);
					    		StringTokenizer strToken=new StringTokenizer(fields,"&");
					    		int j=1;
					    		if(linenumber){
					    		if(i!=(n-1)){
					    		Label labelAll1=new Label(j,count+3,String.valueOf(i+1),wcf2);
								ws.addCell(labelAll1);
								
					    		}
					    		else
					    		{
					    			Label labelAll=new Label (j,count+3,"",wcf2);
								    ws.addCell(labelAll);
					    		}
								
					    		}
					    		
					    		else 
					    		{
					    			
					    			Label labelAll1=new Label(j,count+3,String.valueOf(count+1),wcf2);
									ws.addCell(labelAll1);
									
					    		}
					    		j=j+1;
					    		
					    		
					    		
					    		while(strToken.hasMoreTokens()){
					    			Field f_1 = obj.getClass().getDeclaredField(strToken.nextToken());
					    			f_1.setAccessible(true);
									Object oo = f_1.get(obj);
								
									if(oo!=null){
										String typeName=f_1.getType().getName();
										
										if(typeName.equals("java.lang.String")){
											Label labelAll=new Label(j,count+3,oo.toString(),wcf2);
									    ws.addCell(labelAll);
										 }
										
										
										if(typeName.equals("java.lang.Double") || typeName.equals("double")){
											Double price=(Double)oo;
											Number labelAll2=new Number(j,count+3,price.doubleValue(),cellFormatDouble);
										    ws.addCell(labelAll2);
											}
										if(typeName.equals("int")){
											
											Label labelAll=new Label(j,count+3,oo.toString(),wcf2);
										    ws.addCell(labelAll);
											}
										if(typeName.equals("long")){
											Long date=(Long)oo;
											Label labelAll=new Label(j,count+3,date.toString(),wcf2);
										    ws.addCell(labelAll);
											}
										if(typeName.equals("java.sql.Timestamp")||typeName.equals("java.sql.Date")||typeName.equals("java.util.Date")){
											Date date=(Date)oo;
											Label labelAll=new Label(j,i+3,date.toString(),wcf2);
										    ws.addCell(labelAll);
											}
										if(typeName.equals("java.math.BigDecimal"))
										{
											BigDecimal bi=(BigDecimal)oo;
											Number labelAll=new Number(j,count+3,bi.doubleValue(),cellFormatDouble);
										    ws.addCell(labelAll);
										}
									}
									else
									{
										Label labelAll=new Label (j,count+3,"",wcf2);
									    ws.addCell(labelAll);
									}
								   
									j++;
					    		}
					    	count++;
							}	 
						}
					 }
						  
				}
			
			
			
			
			
		}
		public void writeTitleLine(String title,String viewfield,WritableSheet ws)throws Exception
        {
        	int i=0;
        	StringTokenizer strToken=new StringTokenizer(viewfield,"&");
        	  String[] sf=viewfield.split("&");	 
        	  i=sf.length;	
        	
        	 WritableFont wf = new jxl.write.WritableFont(WritableFont
	    		      .createFont("黑体"), 18, WritableFont.NO_BOLD, false,
	    		      UnderlineStyle.NO_UNDERLINE, Colour.BLUE); 
	    		    WritableCellFormat wcf = new WritableCellFormat(wf); 
	    		    // 设置背景颜色
	    		    //wcf.setBackground(Colour.RED);
	    		    // 设置文字横向显示位置
	    		    wcf.setAlignment(Alignment.CENTRE);
	    		    // 设置文字纵向显示位置
	    		    wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
	    		    // 设置边框
	    		    wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
	    		    // 设置自动换行
	    		    wcf.setWrap(true);
	    		    Label labelTitle=new Label(0,0,title,wcf);
	    		    ws.mergeCells(0, 0, i-1,1 );   
		    		ws.addCell(labelTitle); 
        }
	    public void writeFirstLine(String view,WritableSheet ws) throws Exception{
	    	StringTokenizer strToken=new StringTokenizer(view,"&");
	    	
	    	int i=0;
	    	  WritableFont wf = new jxl.write.WritableFont(WritableFont
	    		      .createFont("黑体"), 12, WritableFont.NO_BOLD, false,
	    		      UnderlineStyle.NO_UNDERLINE, Colour.BLUE); 
	    		    WritableCellFormat wcf = new WritableCellFormat(wf); 
	    		    // 设置背景颜色
	    		    //wcf.setBackground(Colour.RED);
	    		    // 设置文字横向显示位置
	    		    wcf.setAlignment(Alignment.CENTRE);
	    		    // 设置文字纵向显示位置
	    		    wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
	    		    // 设置边框
	    		    wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
	    		    // 设置自动换行
	    		    wcf.setWrap(true);
	    		    ws.getSettings().setDefaultColumnWidth(18);
	    		    //设置标题   
	    	while(strToken.hasMoreTokens()){
	    		Label labelTitle=new Label(i,2,strToken.nextToken(),wcf);
	    		ws.addCell(labelTitle);
	    		i++;
	    	}
	    }
	    public void writeAllLine(String fields,List list,WritableSheet ws,boolean total) throws Exception{
	    	 WritableFont wf2 = new jxl.write.WritableFont(WritableFont
				      .createFont("宋体"), 10, WritableFont.NO_BOLD, false,
				      UnderlineStyle.NO_UNDERLINE, Colour.BLACK); 
			        
			      
				    WritableCellFormat wcf2 = new WritableCellFormat(wf2); 
				    wcf2.setAlignment(Alignment.CENTRE);
				    wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
				    wcf2.setBorder(Border.ALL, BorderLineStyle.THIN);
				    wcf2.setWrap(true);	
				 WritableFont wf3 = new jxl.write.WritableFont(WritableFont
						      .createFont("宋体"), 10, WritableFont.NO_BOLD, false,
						      UnderlineStyle.NO_UNDERLINE, Colour.BLACK); 
					        
					      
						    WritableCellFormat wcf3 = new WritableCellFormat(wf2); 
						    wcf3.setAlignment(Alignment.RIGHT);
						    wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);
						    wcf3.setBorder(Border.ALL, BorderLineStyle.THIN);
						    wcf3.setWrap(true);	   
				 //用于数字的格式
				 NumberFormat nfdb = new NumberFormat("###,##0.00");
				 WritableFont fontDouble = new jxl.write.WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false,
				 UnderlineStyle.NO_UNDERLINE, Colour.BLACK); 						        						      
				 WritableCellFormat cellFormatDouble = new WritableCellFormat(fontDouble,nfdb); 
				 cellFormatDouble.setAlignment(Alignment.RIGHT);
				 cellFormatDouble.setVerticalAlignment(VerticalAlignment.CENTRE);
				 cellFormatDouble.setBorder(Border.ALL, BorderLineStyle.THIN);
				 cellFormatDouble.setWrap(true);			
			for(int i=0,n=list.size();i<n;i++){
	    		Object obj=list.get(i);
	    		StringTokenizer strToken=new StringTokenizer(fields,"&");
	    		int j=0;
	    		if(total){
	    			
	    		if(i!=(n-1)){
	    		Label labelAll1=new Label(j,i+3,String.valueOf(i+1),wcf2);
				ws.addCell(labelAll1);
				
	    		}
	    		else
	    		{
	    			Label labelAll=new Label (j,i+3,"",wcf2);
				    ws.addCell(labelAll);
	    		}
				
	    		}
	    		
	    		else 
	    		{
	    			
	    			Label labelAll1=new Label(j,i+3,String.valueOf(i+1),wcf2);
					ws.addCell(labelAll1);
					
	    		}
	    		j=j+1;
	    		
	    		while(strToken.hasMoreTokens()){
	    			Field f_1 = obj.getClass().getDeclaredField(strToken.nextToken());
	    			f_1.setAccessible(true);
					Object oo = f_1.get(obj);
				  	if(oo!=null){
						String typeName=f_1.getType().getName();
						
						if(typeName.equals("java.lang.String")){
					    Label labelAll=new Label(j,i+3,oo.toString(),wcf2);
					    ws.addCell(labelAll);
						 }
						
						
						if(typeName.equals("java.lang.Double") || typeName.equals("double")){
							Double price=(Double)oo;
							Number labelAll=new Number(j,i+3,price.doubleValue(),cellFormatDouble);
						    ws.addCell(labelAll);
							}
						if(typeName.equals("int")||typeName.equals("java.lang.Integer")){
							Integer price=(Integer)oo;
							Number labelAll=new Number(j,i+3,price.intValue(),cellFormatDouble);
						    ws.addCell(labelAll);
							
							}
						if(typeName.equals("long")){
							Long date=(Long)oo;
							Label labelAll=new Label(j,i+3,date.toString(),wcf2);
						    ws.addCell(labelAll);
							}
						if(typeName.equals("java.sql.Timestamp")||typeName.equals("java.sql.Date")||typeName.equals("java.util.Date")){
							Date date=(Date)oo;
							Label labelAll=new Label(j,i+3,date.toString(),wcf2);
						    ws.addCell(labelAll);
							}
						if(typeName.equals("java.math.BigDecimal"))
						{
							BigDecimal bi=(BigDecimal)oo;
							Number labelAll=new Number(j,i+3,bi.doubleValue(),cellFormatDouble);
						    ws.addCell(labelAll);
						}
					}
					else
					{
						Label labelAll=new Label (j,i+3,"",wcf2);
					    ws.addCell(labelAll);
					}
				
					j++;
	    		}
	    	}
	
	    }
	    
	    public void writeAllLine(String fields,List list,WritableSheet ws) throws Exception{
	    	 WritableFont wf2 = new jxl.write.WritableFont(WritableFont
				      .createFont("宋体"), 10, WritableFont.NO_BOLD, false,
				      UnderlineStyle.NO_UNDERLINE, Colour.BLACK); 
			        
			      
				    WritableCellFormat wcf2 = new WritableCellFormat(wf2); 
				    wcf2.setAlignment(Alignment.CENTRE);
				    wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
				    wcf2.setBorder(Border.ALL, BorderLineStyle.THIN);
				    wcf2.setWrap(true);	
				    
				    //用于数字的格式
					 NumberFormat nfdb = new NumberFormat("###,##0.00");
					 WritableFont fontDouble = new jxl.write.WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false,
					 UnderlineStyle.NO_UNDERLINE, Colour.BLACK); 						        						      
					 WritableCellFormat cellFormatDouble = new WritableCellFormat(fontDouble,nfdb); 
					 cellFormatDouble.setAlignment(Alignment.RIGHT);
					 cellFormatDouble.setVerticalAlignment(VerticalAlignment.CENTRE);
					 cellFormatDouble.setBorder(Border.ALL, BorderLineStyle.THIN);
					 cellFormatDouble.setWrap(true);		    
	    	for(int i=0,n=list.size();i<n;i++){
	    		Object obj=list.get(i);
	    		StringTokenizer strToken=new StringTokenizer(fields,"&");
	    		int j=0;
	    	
	    	
	    		//System.out.println("***************************************************");
	    		while(strToken.hasMoreTokens()){
	    			Field f_1 = obj.getClass().getDeclaredField(strToken.nextToken());
	    			f_1.setAccessible(true);
					Object oo = f_1.get(obj);
				   
					if(oo!=null){
						 //System.out.println("========"+oo.toString());
						String typeName=f_1.getType().getName();
						
						if(typeName.equals("java.lang.String")){
					    Label labelAll=new Label(j,i+3,oo.toString(),wcf2);
					    ws.addCell(labelAll);
						 }
						
						
						if(typeName.equals("java.lang.Double") || typeName.equals("double")){
							Double price=(Double)oo;
							Number labelAll=new Number (j,i+3,price.doubleValue(),cellFormatDouble);
						    ws.addCell(labelAll);
							}
						if(typeName.equals("int")){
							Label labelAll=new Label(j,i+3,oo.toString(),wcf2);
						    ws.addCell(labelAll);
							}
						if(typeName.equals("long")){
							Long date=(Long)oo;
							Label labelAll=new Label(j,i+3,date.toString(),wcf2);
						    ws.addCell(labelAll);
							}
						
						if(typeName.equals("java.sql.Timestamp")||typeName.equals("java.sql.Date")||typeName.equals("java.util.Date")){
							
							Label labelAll=new Label(j,i+3,oo.toString(),wcf2);
						    ws.addCell(labelAll);
							}
						if(typeName.equals("java.math.BigDecimal"))
						{
							BigDecimal bi=(BigDecimal)oo;
							Number labelAll=new Number(j,i+3,bi.doubleValue(),cellFormatDouble);
						    ws.addCell(labelAll);
						}
						
					}
					else
					{
						Label labelAll=new Label (j,i+3,"",wcf2);
					    ws.addCell(labelAll);
					}
				
					j++;
	    		}
	    	}
	    	
	    }	
	    
	   public void writeallLinenorule(String fields,List list,WritableSheet ws)throws Exception
	    	{	
	    		
		   /*设置单元格内容格式 wcf2*/
	       WritableFont wf2 = new jxl.write.WritableFont(WritableFont
				      .createFont("宋体"), 10, WritableFont.NO_BOLD, false,
				      UnderlineStyle.NO_UNDERLINE, Colour.BLACK); 
			        
			      
				    WritableCellFormat wcf2 = new WritableCellFormat(wf2); 
				    wcf2.setAlignment(Alignment.CENTRE);
				    wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
				    wcf2.setBorder(Border.ALL, BorderLineStyle.THIN);
				    wcf2.setWrap(true);
				   
				    WritableCellFormat wcf3 = new WritableCellFormat(wf2); 
				    wcf3.setAlignment(Alignment.RIGHT);
				    wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);
				    wcf3.setBorder(Border.ALL, BorderLineStyle.THIN);
				    wcf3.setWrap(true);	
				    
				    //用于数字的格式
					 NumberFormat nfdb = new NumberFormat("###,##0.00");
					 WritableFont fontDouble = new jxl.write.WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false,
					 UnderlineStyle.NO_UNDERLINE, Colour.BLACK); 						        						      
					 WritableCellFormat cellFormatDouble = new WritableCellFormat(fontDouble,nfdb); 
					 cellFormatDouble.setAlignment(Alignment.RIGHT);
					 cellFormatDouble.setVerticalAlignment(VerticalAlignment.CENTRE);
					 cellFormatDouble.setBorder(Border.ALL, BorderLineStyle.THIN);
					 cellFormatDouble.setWrap(true);	
			for(int i=0,n=list.size();i<n;i++){
	    		Object obj=list.get(i);
	    		StringTokenizer strToken=new StringTokenizer(fields,"&");
	    		int j=0;
	    		
	    	
	    		
	    	
	    			Label labelAll1=new Label(j,i+4,String.valueOf(i+1),wcf2);
					ws.addCell(labelAll1);
					
	    	
	    		j=j+1;
		    		while(strToken.hasMoreTokens()){
		    			Field f_1 = obj.getClass().getDeclaredField(strToken.nextToken());
		    			f_1.setAccessible(true);
						Object oo = f_1.get(obj);
					
						if(oo!=null){
							String typeName=f_1.getType().getName();
							
							if(typeName.equals("java.lang.String")){
						    Label labelAll=new Label(j,i+4,oo.toString(),wcf2);
						    ws.addCell(labelAll);
							 }
							
							
							if(typeName.equals("java.lang.Double") || typeName.equals("double")){
								Double price=(Double)oo;
								Number labelAll=new Number(j,i+4,price.doubleValue(),cellFormatDouble);
							    ws.addCell(labelAll);
								}
							if(typeName.equals("int")){
								Label labelAll=new Label(j,i+4,oo.toString(),wcf2);
							    ws.addCell(labelAll);
								}
							if(typeName.equals("long")){
								Long date=(Long)oo;
								Label labelAll=new Label(j,i+4,date.toString(),wcf2);
							    ws.addCell(labelAll);
								}
							if(typeName.equals("java.sql.Timestamp")||typeName.equals("java.sql.Date")||typeName.equals("java.util.Date")){
								Date date=(Date)oo;
								Label labelAll=new Label(j,i+3,date.toString(),wcf2);
							    ws.addCell(labelAll);
								}
							if(typeName.equals("java.math.BigDecimal"))
							{
								BigDecimal bi=(BigDecimal)oo;
								Number labelAll=new Number(j,i+4,bi.doubleValue(),cellFormatDouble);
							    ws.addCell(labelAll);
							}
						}
						else
						{
							Label labelAll=new Label (j,i+4,"",wcf2);
						    ws.addCell(labelAll);
						}
					
						j++;
		    		}
		    	}
		    	
	    	}
	    	
	    	

	    	}	
	
	    	
     

	
	


