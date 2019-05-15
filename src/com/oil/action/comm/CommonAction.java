/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.oil.action.comm;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oil.factory.AppFactory;
import com.oil.module.Dba02;
import com.oil.service.Dba02Manager;
import com.oil.tools.CommonTools;
import com.oil.util.Constants;
import com.oil.action.base.Transcode;
import com.oil.tools.Convert;
import com.oil.tools.CheckImage;
import com.oil.tools.CurrentTime;
import com.oil.util.WebUrl;
import com.oil.page.PageConst;

/** 
 * MyEclipse Struts
 * Creation date: 05-01-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class CommonAction extends DispatchAction {
	
	
	/**
	 * 获取session中的用户角色名称
	 * @param request
	 * @return
	 */
	private String getLoginUserName(HttpServletRequest request){
        String userName = (String)request.getSession(false).getAttribute("userName");
        return userName;
	}
	
	/**
	 * 登录进来首页
	 * @author wm
	 * @date 2019-5-15 上午08:43:37
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward welcome(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("welcome");
	}
	
	/**
	 * 导向注水合格率页面
	 * @author wm
	 * @date 2019-5-14 下午08:49:46 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goZsPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession(false).invalidate();
		return mapping.findForward("zsPage");
	}
	
	/**
	 * 根据水井号、时间段分页获取水井列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getPageSjHglData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Dba02Manager dm = (Dba02Manager) AppFactory.instance(null).getApp(Constants.WEB_DBA_02_INFO);
		String jh = CommonTools.getFinalStr("jh", request);
		String sDate = CommonTools.getFinalStr("sDate", request);
		String eDate = CommonTools.getFinalStr("eDate", request);
		Integer count = dm.getSjCountByOpt(jh, sDate, eDate);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "暂无记录";
		if(count > 0){
			msg = "success";
			Integer pageSize = PageConst.getPageSize(String.valueOf(request.getParameter("limit")), 10);//等同于pageSize
			Integer pageNo = CommonTools.getFinalInteger("page", request);//等同于pageNo
			List<Dba02> dList = dm.listSjPageInfoByOpt(jh, sDate, eDate, pageNo, pageSize);
			List<Dba02> dList_zsts = dm.listSjInfo(jh, sDate, eDate,false);
			List<Dba02> dList_hgts = dm.listSjInfo(jh, sDate, eDate,true);
			//获取第一条数据的日期
			Integer hgDays = dList_hgts.size();//合格天数
			Integer zsDays = dList_zsts.size();//注水天数
			List<Object> list_d = new ArrayList<Object>();
			for(Iterator<Dba02> it = dList.iterator() ; it.hasNext();){
				Dba02 dba = it.next();
				if(dba != null){
					Map<String,Object> map_d = new HashMap<String,Object>();
					map_d.put("jh", dba.getJh());//井号
					map_d.put("db", dba.getDb());//队别
					map_d.put("rq", dba.getRq().substring(0, 10));//日期
					map_d.put("zsf", dba.getZsfs());//方
					map_d.put("scsj", dba.getScsj());//时间
					map_d.put("gxyl", dba.getGxyl());//泵压
					map_d.put("yy", dba.getYy());//油压
					map_d.put("ty", dba.getTy());//套压
					map_d.put("rzsl", dba.getRzsl());//日注
					map_d.put("rpzsl", dba.getRpzsl());//配注
					map_d.put("bz", dba.getBz());//备注
					Double rzsl = Double.parseDouble(String.valueOf(dba.getRzsl()));
					Double rpzsl = Double.parseDouble(String.valueOf(dba.getRpzsl()));
					Double hg = rzsl / rpzsl;
					if(hg >= 0.9 && hg <= 1.1){
						map_d.put("hg", "合格");//
					}else{
						map_d.put("hg", "不合格");//
					}
					list_d.add(map_d);
				}
			}
			map.put("data", list_d);
			map.put("count", count);
			map.put("code", 0);
			//统计信息
			map.put("jh_tj", dList.get(0).getJh());
			map.put("zsDays", zsDays);
			map.put("hgDays", hgDays);
			if(zsDays > 0){
				Double hg = hgDays * 100.0 / zsDays;
				map.put("hgRate", Convert.convertInputNumber_2(hg) + "%");
				if(hg >= 80){
					map.put("result", "合格");
				}else{
					map.put("result", "不合格");
				}
			}else{
				map.put("hgRate", "0.00%");
				map.put("result", "不合格");
			}
		}
		map.put("msg", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	
	/**
	 * 下载文件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward downZipFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		String fileUrl = Transcode.unescape_new1("fileUrl", request);
		String fileName = "";
		if(!fileUrl.equals("")){
			try{
				//下载文件到客户端
				fileName = fileUrl.substring(fileUrl.lastIndexOf("\\")+1,fileUrl.length());
				String absoFilePath = WebUrl.DATA_URL_UP_FILE_UPLOAD + "\\" +fileUrl;
		        OutputStream fos = null;
		        BufferedOutputStream bos = null;
		        InputStream fis = null;
		        BufferedInputStream bis = null;
		        fis = new FileInputStream(new File(absoFilePath));
				bis = new BufferedInputStream(fis);
				fos = response.getOutputStream();
				bos = new BufferedOutputStream(fos);
				fileName = URLEncoder.encode(fileName,"UTF-8");
				//这个就就是弹出下载对话框的关键代码
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "No-cache");
				response.setDateHeader("Expires", 0); 
		        response.setHeader("Content-disposition","attachment;filename=" +fileName);
		        response.setContentType("application/x-download");
		        int bytesRead = 0;
		        byte[] buffer = new byte[8192];
		        while ((bytesRead = bis.read(buffer,0,8192)) != -1) {
		        	fos.write(buffer, 0, bytesRead);
		        }
		        fos.flush();
		        fis.close();
		        bis.close();
		        fos.close();
		        bos.close();
			}catch (IOException e){  
//		        e.printStackTrace(); 
		    	System.out.println("用户取消文件下载");
		    } 
		}
		return null;
	}
	
	/**
	 * 上传文件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward uploadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String msg = "";
		boolean upFlag = false;
		String fileUrl = "";
		String filename = "";
		Map<String,Object> map = new HashMap<String,Object>();
		
			if (ServletFileUpload.isMultipartContent(request)){// 判断是否是上传文件
				DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();// 创建工厂对象
				ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory); // 创建上传对象
				try {
					List<FileItem> filelist = fileUpload.parseRequest(request);
					ListIterator<FileItem> iterator = filelist.listIterator();
					String userPath = WebUrl.DATA_URL_UP_FILE_UPLOAD + "\\";
					String filePre = "";
					while (iterator.hasNext()) {
						FileItem fileItem = iterator.next();// 获取文件对象
						// 处理文件上传
						filename = fileItem.getName();// 获取名字
						Integer lastIndex = filename.lastIndexOf(".");
						String suffix = filename.substring(lastIndex+1);
						filePre = filename.substring(0, lastIndex);
						filename = filePre + "_" + CurrentTime.getRadomTime() + "." + suffix;
						CheckImage ci = new CheckImage();
						//xls,xlsx
						String checkFileSuffixInfo = ci.getUpFileStuffix(suffix);
						if(checkFileSuffixInfo.equals("file")){//文件限制20M
							upFlag = ci.checkItemSize(fileItem, 20 * 1024 * 1024);
							if(!upFlag){
								msg = "outSize";
							}
						}else{
							msg = "suffixError";
						}
						if(upFlag){
							byte[] data = fileItem.get();// 获取数据
							//没有该文件夹先创建文件夹
				    		File file = new File(userPath);
				    		if(!file.exists()){
				    			file.mkdirs();
				    		}
				    		FileOutputStream fileOutputStream = new FileOutputStream(userPath + "/" + filename);
							fileOutputStream.write(data);// 写入文件
							fileOutputStream.close();// 关闭文件流
							msg = "success";
							fileUrl +=  WebUrl.NEW_DATA_URL_UP_FILE_UPLOAD  + "\\" + filename + ",";
						}
					}
					map.put("code", 0);
					map.put("msg", msg);
					if(!fileUrl.equals("")){
						fileUrl = fileUrl.substring(0, fileUrl.length() - 1);
					}
					map.put("data", fileUrl);
					map.put("fileName", filename);
					CommonTools.getJsonPkg(map, response);
				}catch (FileUploadException e) {
					e.printStackTrace();
				}catch (FileNotFoundException e) {
					e.printStackTrace();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		return null;
	}
	
	/**
	 * 处理注水合格率Excel
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dealZsExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> map_final = new HashMap<String,String>();
		System.out.println("数据分析开始--"+CurrentTime.getCurrentTime());
		Dba02Manager dm = (Dba02Manager) AppFactory.instance(null).getApp(Constants.WEB_DBA_02_INFO);
		String excelName = Transcode.unescape_new1("excelName", request);//2019-04_sj.xlsx
		String absoFilePath = WebUrl.DATA_URL_UP_FILE_UPLOAD + "/" + excelName;
		File f = new File(absoFilePath);
    	InputStream inputStream = new FileInputStream(f);
    	XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
    	XSSFSheet sheet = xssfWorkbook.getSheetAt(1);
    	XSSFSheet sheet_tj = xssfWorkbook.getSheetAt(0);
    	XSSFCellStyle style = xssfWorkbook.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框    
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框    
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框    
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框 
        
        XSSFFont font_1 = xssfWorkbook.createFont();    
        font_1.setFontName("宋体");    
        font_1.setFontHeightInPoints((short) 12);//设置字体大小  (备注)
        style.setFont(font_1);
        
        XSSFCellStyle style_pass = xssfWorkbook.createCellStyle();  
        style_pass.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style_pass.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        style_pass.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框    
        style_pass.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框    
        style_pass.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框    
        style_pass.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框 
        
        XSSFCellStyle style_no_pass = xssfWorkbook.createCellStyle();  
        style_no_pass.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style_no_pass.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        style_no_pass.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框    
        style_no_pass.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框    
        style_no_pass.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框    
        style_no_pass.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框 
        
        XSSFCellStyle style_tj = xssfWorkbook.createCellStyle();  
        style_tj.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居左格式  
        style_tj.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        style_tj.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框    
        style_tj.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框    
        style_tj.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框    
        style_tj.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框 
        
        XSSFFont font_pass = xssfWorkbook.createFont();    
        font_pass.setFontName("宋体");    
        font_pass.setFontHeightInPoints((short) 12);//设置字体大小  (备注)
        font_pass.setColor(HSSFColor.GREEN.index);
        
        XSSFFont font_no_pass = xssfWorkbook.createFont();    
        font_no_pass.setFontName("宋体");    
        font_no_pass.setFontHeightInPoints((short) 12);//设置字体大小  (备注)
        font_no_pass.setColor(HSSFColor.RED.index);
        
        style_pass.setFont(font_pass);
        style_no_pass.setFont(font_no_pass);
        
        String specDate = excelName.substring(0, excelName.indexOf("_"));//指定年份月份作业井2019-03
        Integer maxDays = CurrentTime.getMaxDays(specDate);//指定月份的天数
        Map<String,String> map_d = new HashMap<String,String>();
        for (int i = 3; i < sheet.getLastRowNum()+1; i++) {
        	XSSFRow row1 = sheet.getRow(i);
        	String jh = row1.getCell(1).getStringCellValue().replace(" ", "").replace("\t", "");//井号
        	if(jh.equals("")){
        		 break; 
        	}
        	String curr_unit = String.valueOf((int)row1.getCell(2).getNumericCellValue());//单位
        	String kjrq = CurrentTime.dateConvertToString(row1.getCell(3).getDateCellValue());//开井日期
        	Integer diffDays = CurrentTime.compareDate(kjrq, specDate+"-"+maxDays);
        	String sDate = "";
        	String eDate = "";
        	if(diffDays > maxDays){//相差天数超过一个月，用指定年份月份的月初和月末日期作为开始和结束日期
        		String[] seDate = CurrentTime.getFirstEndDay(specDate).split(":");
        		sDate = seDate[0];
        		eDate = seDate[1];
        	}else if(diffDays >= 0 && diffDays < maxDays){//用开井日期作为开始，月末日期作为结束
        		sDate = kjrq;
        		eDate = specDate + "-" + maxDays;
        	}else{
        		
        	}
        	if(!sDate.equals("") && !eDate.equals("")){
        		List<Dba02> dList_zs = dm.listSjInfo(jh,sDate,eDate,false);
        		List<Dba02> dList_hg = dm.listSjInfo(jh,sDate,eDate,true);
        		Map<String,Object> map = new HashMap<String,Object>();
        		if(dList_zs.size() > 0){
        			//获取第一条数据的日期
        			Integer hgDays = dList_hg.size();//合格天数
        			Integer zsDays = dList_zs.size();//注水天数
        			DecimalFormat df = new DecimalFormat("0.00");
        			Double hg = hgDays * 100.0 / zsDays;
        			map.put("合格率", df.format(hg) + "%");
        			
        			XSSFCell cell = row1.createCell(4);//注水天数
        			style.setFont(font_1);
        			cell.setCellStyle(style);
        			cell.setCellValue(String.valueOf(zsDays));
        			
        			cell = row1.createCell(5);//合格天数
        			style.setFont(font_1);
        			cell.setCellStyle(style);
        			cell.setCellValue(String.valueOf(hgDays));
        			
        			cell = row1.createCell(6);//合格率
        			style.setFont(font_1);
        			cell.setCellStyle(style);
        			cell.setCellValue(df.format(hg) + "%");
        			
        			cell = row1.createCell(7);//结论
        			String com_num = map_d.get("comNum_"+curr_unit) == null ? "" : map_d.get("comNum_"+curr_unit);//完井数,合格数
        			if(hg >= 80){
        				style_pass.setFont(font_pass);
        				cell.setCellStyle(style_pass);
        				cell.setCellValue("合格");
        				if(!com_num.equals("")){
        					map_d.put("comNum_"+curr_unit, (Integer.parseInt(com_num.split(",")[0]) + 1)+","+(Integer.parseInt(com_num.split(",")[1]) + 1));
        				}else{
        					map_d.put("comNum_"+curr_unit, "1,1");//完井井数
        				}
        			}else{
        				style_no_pass.setFont(font_no_pass);
        				cell.setCellStyle(style_no_pass);
        				cell.setCellValue("不合格");
        				if(!com_num.equals("")){
        					map_d.put("comNum_"+curr_unit, (Integer.parseInt(com_num.split(",")[0]) + 1)+","+com_num.split(",")[1]);
        				}else{
        					map_d.put("comNum_"+curr_unit, "1,0");//完井井数
        				}
        			}
        		}else{
        			continue;
        		}
        	}
        }
        if(map_d.size() > 0){
        	//这里将map.entrySet()转换成list
            List<Map.Entry<String,String>> list = new ArrayList<Map.Entry<String,String>>(map_d.entrySet());
            //然后通过比较器来实现排序
            Collections.sort(list,new Comparator<Map.Entry<String,String>>() {
                //升序排序
                public int compare(Entry<String, String> o1,
                        Entry<String, String> o2) {
                    return o1.getKey().compareTo(o2.getKey());
                }
                
            });
            Integer num = 0;
            Integer comNum_total = 0;
            Integer hgNum_total = 0;
            for(Map.Entry<String,String> map:list){ 
            	XSSFRow row_tj = sheet_tj.createRow(3+num);
            	row_tj.setHeight((short)500);
            	num++;
            	XSSFCell cell = row_tj.createCell(0);
    			style.setFont(font_1);
    			cell.setCellStyle(style);
    			cell.setCellValue(map.getKey().split("_")[1]+"区");
    			
    			String comNum = map.getValue().split(",")[0];
    			String hgNum = map.getValue().split(",")[1];
    			comNum_total += Integer.parseInt(comNum);
    			hgNum_total += Integer.parseInt(hgNum);
    			cell = row_tj.createCell(1);
    			style.setFont(font_1);
    			cell.setCellStyle(style);
    			cell.setCellValue(comNum);
    			
    			cell = row_tj.createCell(2);
    			style.setFont(font_1);
    			cell.setCellStyle(style);
    			cell.setCellValue(hgNum);
    			
    			String hgRate = Convert.convertInputNumber_1(Integer.parseInt(hgNum) * 100.0 / Integer.parseInt(comNum));
    			cell = row_tj.createCell(3);
    			style.setFont(font_1);
    			cell.setCellStyle(style);
    			cell.setCellValue(hgRate);
    			
    			cell = row_tj.createCell(4);
    			style.setFont(font_1);
    			cell.setCellStyle(style);
    			cell.setCellValue("");
            }
            XSSFRow row_tj = sheet_tj.createRow(3+num);
            row_tj.setHeight((short)500);
            XSSFCell cell = row_tj.createCell(0);
			style.setFont(font_1);
			cell.setCellStyle(style);
			cell.setCellValue("全厂");
			
			cell = row_tj.createCell(1);
			style.setFont(font_1);
			cell.setCellStyle(style);
			cell.setCellValue(String.valueOf(comNum_total));
			
			cell = row_tj.createCell(2);
			style.setFont(font_1);
			cell.setCellStyle(style);
			cell.setCellValue(String.valueOf(hgNum_total));
			
			String hgRate = Convert.convertInputNumber_1(hgNum_total * 100.0 / comNum_total);
			cell = row_tj.createCell(3);
			style.setFont(font_1);
			cell.setCellStyle(style);
			cell.setCellValue(hgRate);
			
			cell = row_tj.createCell(4);
			style.setFont(font_1);
			cell.setCellStyle(style);
			cell.setCellValue("");
			num++;
			
			XSSFRow row_zb = sheet_tj.createRow(3+num);
			row_zb.setHeight((short)500);
			cell = row_zb.createCell(0); 
            cell.setCellStyle(style_tj);  
            cell.setCellValue("制表人: "+this.getLoginUserName(request)); 
            CommonTools.setJoinBorderStyle(XSSFCellStyle.BORDER_THIN, 3+num, 3+num, 0, 4, sheet_tj, xssfWorkbook);
        }
        FileOutputStream fout = new FileOutputStream(absoFilePath);//存到服务器
    	xssfWorkbook.write(fout);  
        fout.close(); 
        
        System.out.println("数据分析结束--"+CurrentTime.getCurrentTime());

        System.out.println("增加注水合格率开始--"+CurrentTime.getCurrentTime());
        String month = excelName.split("_")[0];
        String s = null;
		String dataPath = WebUrl.DATA_URL_JSON + "/hgl.json";
		File file = new File(dataPath);
		InputStreamReader br = new InputStreamReader(new FileInputStream(file),"utf-8");//读取文件,同时指定编码
		StringBuffer sb = new StringBuffer();
        char[] ch = new char[128];  //一次读取128个字符
        int len = 0;
        while((len = br.read(ch,0, ch.length)) != -1){
            sb.append(ch, 0, len);
        }
        s = sb.toString();
        //新增加的记录
        JSONObject appObject = new JSONObject();
        appObject.put("fileName", excelName);
        appObject.put("month", month);
        appObject.put("fxDate", CurrentTime.getCurrentTime());
        appObject.put("year", month.split("-")[0]);
        appObject.put("filePath", WebUrl.NEW_DATA_URL_UP_FILE_UPLOAD + "/" + excelName);
        
        String newStr = "";
        if(s.equals("")){//新增加
        	JSONArray appArray = new JSONArray();
        	appArray.add(appObject);
        	JSONObject jsonObj = new JSONObject();
        	jsonObj.put("excelList", appArray);
        	newStr = jsonObj.toJSONString();
        }else{//追加
        	JSONObject dataJson = JSON.parseObject(s); 
            JSONArray features = dataJson.getJSONArray("excelList");// 找到features json数组
            features.add(appObject);
            newStr = dataJson.toJSONString();
        }
    	File file_1 = new File(dataPath);
    	Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file_1), "UTF-8"));
    	out.write(newStr);
    	out.flush();
    	out.close();
    	System.out.println("增加注水合格率结束--"+CurrentTime.getCurrentTime());
    	map_final.put("result", "success");
        CommonTools.getJsonPkg(map_final, response);
		return null; 	
	}
	
	/**
	 * 增加注水合格率分析记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addHglData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		 Map<String,String> map = new HashMap<String,String>();
		 String fileName = Transcode.unescape_new1("fileName", request);
		 String month = fileName.split("_")[0];
		 String filePath = CommonTools.getFinalStr("filePath", request);
		String s = null;
		String dataPath = WebUrl.DATA_URL_JSON + "/hgl.json";
		File file = new File(dataPath);
		InputStreamReader br = new InputStreamReader(new FileInputStream(file),"utf-8");//读取文件,同时指定编码
		StringBuffer sb = new StringBuffer();
        char[] ch = new char[128];  //一次读取128个字符
        int len = 0;
        while((len = br.read(ch,0, ch.length)) != -1){
            sb.append(ch, 0, len);
        }
        s = sb.toString();
        //新增加的记录
        JSONObject appObject = new JSONObject();
        appObject.put("fileName", fileName);
        appObject.put("month", month);
        appObject.put("fxDate", CurrentTime.getCurrentTime());
        appObject.put("year", month.split("-")[0]);
        appObject.put("filePath", filePath);
        
        String newStr = "";
        if(s.equals("")){//新增加
        	JSONArray appArray = new JSONArray();
        	appArray.add(appObject);
        	JSONObject jsonObj = new JSONObject();
        	jsonObj.put("excelList", appArray);
        	newStr = jsonObj.toJSONString();
        }else{//追加
        	JSONObject dataJson = JSON.parseObject(s); 
            JSONArray features = dataJson.getJSONArray("excelList");// 找到features json数组
            features.add(appObject);
            newStr = dataJson.toJSONString();
        }
    	File file_1 = new File(dataPath);
    	Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file_1), "UTF-8"));
    	out.write(newStr);
    	out.flush();
    	out.close();
        map.put("result", "success");
        CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取注水分析数据记录(下载用)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getHglData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String specYear = CommonTools.getFinalStr("specYear", request);
		String s = null;
		File file = new File(WebUrl.DATA_URL_JSON + "/hgl.json");
		InputStreamReader br = new InputStreamReader(new FileInputStream(file),"utf-8");//读取文件,同时指定编码
		StringBuffer sb = new StringBuffer();
        char[] ch = new char[128];  //一次读取128个字符
        int len = 0;
        while((len = br.read(ch,0, ch.length)) != -1){
            sb.append(ch, 0, len);
        }
        s = sb.toString();
        JSONObject dataJson = JSON.parseObject(s); 
        JSONArray features = dataJson.getJSONArray("excelList");// 找到features json数组
        Map<String,Object> map = new HashMap<String,Object>();
        List<Object> list_d = new ArrayList<Object>();
        String msg = "noInfo";
        for(Integer i = 0 ; i < features.size() ; i++){
        	JSONObject obj = features.getJSONObject(i);// 获取features数组的第i个json对象
        	Map<String,String> map_d = new HashMap<String,String>();
        	String year = obj.getString("year");//获取年份
        	if(year.equals(specYear)){
        		msg = "success";
        		map_d.put("fileName", obj.getString("fileName"));
        		map_d.put("month", obj.getString("month"));
        		map_d.put("fxDate", obj.getString("fxDate"));
        		map_d.put("filePath", obj.getString("filePath"));
        		list_d.add(map_d);
        	}else{
        		continue;
        	}
        }
        if(msg.equals("success")){
        	map.put("fileList", list_d);
        }
        map.put("result", msg);
        CommonTools.getJsonPkg(map, response);
		return null;
	}
	
}