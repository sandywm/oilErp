package com.oil.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oil.action.base.Transcode;
import com.oil.util.WebUrl;

public class CommonTools {
	
	/**
	 * 封装json
	*  @author  Administrator
	*  @ModifiedBy  
	*  @date  2018-8-21 下午10:17:05
	*  @param obj
	*  @param response
	*  @throws IOException
	 */
	public static void getJsonPkg(Object obj,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String json = JSON.toJSONString(obj);
        PrintWriter pw = response.getWriter();  
        pw.write(json); 
        pw.flush();  
        pw.close();
	}
	
	/**
	 * null转换为""
	 * @description
	 * @author wm
	 * @date 2017-5-2 上午10:32:59
	 * @param inputStr
	 * @return
	 */
	public static String getFinalStr(String inputStr){
		inputStr = String.valueOf(inputStr);
		if(inputStr == "null"){
			return "";
		}else{
			return inputStr;
		}
	}
	
	/**
	 * null转换为""
	 * @description
	 * @author wm
	 * @date 2018-8-25 下午06:01:28
	 * @param inputStr
	 * @param request
	 * @return
	 */
	public static String getFinalStr(String inputStr,HttpServletRequest request){
		inputStr = String.valueOf(request.getParameter(inputStr));
		if(inputStr == "null"){
			return "";
		}else{
			return inputStr;
		}
	}
	
	/**
	 * 输入的数字类型转换成数据类型
	 * @description
	 * @author wm
	 * @date 2016-9-6 下午03:09:46
	 * @param inputData
	 * @return
	 */
	public static Integer getFinalInteger(String inputData){
		inputData = String.valueOf(inputData);
		if(inputData.equals("") || inputData.equals("null")){
			return 0;
		}else{
			return Integer.parseInt(inputData);
		}
	}
	
	/**
	 * 输入的数字类型转换成数据类型
	 * @description
	 * @author wm
	 * @date 2016-9-6 下午03:09:46
	 * @param inputData
	 * @return
	 */
	public static Integer getFinalInteger(String inputData,HttpServletRequest request){
		inputData = String.valueOf(request.getParameter(inputData));
		if(inputData.equals("") || inputData.equals("null")){
			return 0;
		}else{
			return Integer.parseInt(inputData);
		}
	}
	
	/**
	 * 输入的数字类型转换成数据类型
	 * @description
	 * @author wm
	 * @date 2017-4-21 上午11:16:34
	 * @param inputData
	 * @return
	 */
	public static Float getFinalFloat(String inputData){
		inputData = String.valueOf(inputData);
		if(inputData.equals("") || inputData.equals("null")){
			return 0f;
		}else{
			return Float.parseFloat(inputData);
		}
	}

	/**
	 * 输入的数字类型转换成数据类型
	 * @description
	 * @author wm
	 * @date 2017-4-21 上午11:16:34
	 * @param inputData
	 * @return
	 */
	public static Float getFinalFloat(String inputData,HttpServletRequest request){
		inputData = String.valueOf(request.getParameter(inputData));
		if(inputData.equals("") || inputData.equals("null")){
			return 0f;
		}else{
			return Float.parseFloat(inputData);
		}
	}
	
	/**
	 * 输入的数字类型转换成数据类型
	 * @description
	 * @author wm
	 * @date 2017-4-21 上午11:16:34
	 * @param inputData
	 * @return
	 */
	public static Double getFinalDouble(String inputData,HttpServletRequest request){
		inputData = String.valueOf(request.getParameter(inputData));
		if(inputData.equals("") || inputData.equals("null")){
			return 0.0;
		}else{
			return Double.parseDouble(inputData);
		}
	}
	
	/**
     * 写入xss/sql注入攻击报告
     * @description
     * @author wm
     * @date 2016-4-29 上午08:34:23
     * @param subject
     * @param request
     */
    public static void sendAttackReport(String filePath,String content,HttpServletRequest request,String attackUrl){
		String attackInfo = "      攻击时间："+CurrentTime.getCurrentTime() + "      攻击IP："+CommonTools.getIpAddress(request) + "     攻击URL："+attackUrl;
		File oldfile = new File(filePath);  
		if(!oldfile.exists()){
			 try {
				oldfile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileWriter fw;
		try {
			fw = new FileWriter(oldfile, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("  "+content+attackInfo);
			pw.flush();
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
  //获取真实IP地址
	public static String getIpAddress(HttpServletRequest request){
		String ipAddress = "";
		ipAddress = request.getHeader("x-forwarded-for");    
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {    
			ipAddress = request.getHeader("Proxy-Client-IP");    
	    }
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {    
	        ipAddress = request.getHeader("WL-Proxy-Client-IP");    
	    }  
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
	        ipAddress = request.getHeader("HTTP_CLIENT_IP");  
	    }  
	    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
	        ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");  
	    }  
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {    
			ipAddress = request.getRemoteAddr();    
			if(ipAddress.equals("127.0.0.1") ||ipAddress.equals("0:0:0:0:0:0:0:1")){    
				//根据网卡取本机配置的IP    
				InetAddress inet=null;    
			    try {    
			    	inet = InetAddress.getLocalHost();    
			    } catch (UnknownHostException e) {    
			    	e.printStackTrace();    
			    }    
			    ipAddress= inet.getHostAddress();
			}          
	    }   
		//对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割    
	    if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15    
	    	if(ipAddress.indexOf(",")>0){    
	    		ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));    
	    	}    
	    }    
		return ipAddress;
	}
	
	/**
	 * 根据IP地址获取当前省、市
	 * @description
	 * @author wm
	 * @date 2018-8-2 下午04:18:06
	 * @param ip
	 * @return
	 */
	public static String getSelfArea(String ip) {
		String address="";
		String prov = "",city = "";
		String strUrl="https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query="+ip+"&co=&resource_id=6006&t=1444747793291&ie=utf8&oe=utf8&cb=op_aladdin_callback&format=json&tn=baidu&cb=jQuery110207215902183078953_1444747767470&_=1444747767472";
		try { 
			URL url = new URL(strUrl);
			
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
	        //add reuqest header
	        con.setRequestMethod("GET");
	        // Send post request
	        con.setDoOutput(true);
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(con.getInputStream(),Charset.forName("UTF-8")));
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	 
	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();
	 
	        int idx=response.indexOf("{\"location\":\"");
	        if(idx != -1){
		        String response2 = response.substring(response.indexOf("{\"location\":\"")+13,response.length());
		        address = response2.substring(0,response2.indexOf("\""));
		        String[] zzq = {"内蒙古","新疆","西藏","广西","宁夏"};
				String[] zxs = {"北京","天津","上海","重庆"};
				String[] xzq = {"香港","澳门"};
				if(address.contains("自治区")){//自治区
					for(Integer i = 0 ; i < zzq.length ; i++){
						if(address.contains(zzq[i])){
							prov = zzq[i];
							Integer startIndex = address.indexOf("自治区");
							city = address.substring(startIndex+3, address.indexOf("市"));
							break;
						}
					}
				}else if(address.contains("省")){//省、市
					Integer startIndex = address.indexOf("省");
					Integer endIndex = address.indexOf("市");
					prov = address.substring(0,startIndex);
					city = address.substring(startIndex+1, endIndex);
				}else if(address.contains("行政区")){//特别行政区
					for(Integer i = 0 ; i < xzq.length ; i++){
						if(address.contains(xzq[i])){
							prov = city = xzq[i];
							break;
						}
					}
				}else{//直辖市
					for(Integer i = 0 ; i < zxs.length ; i++){
						if(address.contains(zxs[i])){
							prov = city = zxs[i];
							break;
						}
					}
				}
				address = prov + ":" + city;
	        }
	        else
	        	address = "un-know";
		} catch (Exception e) {
			e.printStackTrace();
			address = "un-know";
		}
		return address;
	}
	
	/**
	 * 获取客户端信息（上述2种方法的整合）分清安卓、ios、pc、移动浏览器
	 * @description
	 * @author wm
	 * @date 2016-7-14 上午08:38:13
	 * @param request
	 * @return
	 */
	public static String getCilentInfo_new(HttpServletRequest request){
		String clientInfo = request.getHeader("User-agent");
		String cilentQuip = "";
		if(clientInfo != null){
			if(clientInfo.indexOf("Android") >= 0 || clientInfo.indexOf("iPad") >= 0 || clientInfo.indexOf("iPhone") >= 0){
				if(clientInfo.indexOf("AppleWebKit") > 0){
					cilentQuip = "mobileBrowser";//移动端浏览器
				}else{
					if(clientInfo.indexOf("Android") >= 0){//移动端APP
						cilentQuip = "andriodApp";
					}else if(clientInfo.indexOf("iPad") >= 0 || clientInfo.indexOf("iPhone") >= 0){
						cilentQuip = "iphoneApp";
					}
				}
			}else{
				cilentQuip = "pc";//PC端
			}
		}else{
			cilentQuip = "";//无法获取客户端信息
		}
		return cilentQuip;
	}
	
	/**
	 * 根据传递的入库单编号自动获取下一个
	 * @description
	 * @author wm
	 * @date 2017-5-11 上午09:30:26
	 * @param insNoStr 数据库中最后一个入库单编号
	 * @param preSuffix 前缀字母代号
	 * @return
	 */
	public static String getInStoreNo(String insNoStr){
		String insNo_base = insNoStr.split("_")[1];
		String preStr = insNoStr.split("_")[0];
		Integer insNoLength = insNo_base.length();
		String insNo_curr_str = String.valueOf((Integer.parseInt(insNo_base) + 1));
		Integer insNo_curr_length = insNo_curr_str.length();
		Integer diff = insNoLength - insNo_curr_length;
		preStr += "_"; 
		for(Integer i = 0 ; i < diff ; i++){
			preStr += "0";
		}
		return preStr + insNo_curr_str;
	}
	
	public static boolean checkMobile(String mobile) {
		if (mobile.equals("")) {
			return false;
		} else {
			String regex = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(mobile);

			return m.find();
		}

	}

	/**
	 * 长整形对象转成整形（用于获取记录数时使用）
	 * @description
	 * @author wm
	 * @date 2018-7-25 上午10:07:53
	 * @param count_obj
	 * @return
	 */
	public static Integer longToInt(Object count_obj){
		long count = 0;
		if(count_obj != null){
			count =  Long.parseLong(String.valueOf( count_obj));
		}
		return (int)count;
	}
	
	/**
	 * 检查邮箱格式
	 * @description
	 * @author wm
	 * @date 2018-7-30 下午04:14:40
	 * @param inputEmail
	 * @return 证成功返回true，验证失败返回false 
	 */
	public static boolean checkEmail(String inputEmail){
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return Pattern.matches(regex, inputEmail);   
	}
	
	/**
	 * 往json中追加记录
	 * @param fileName
	 * @param month
	 * @param fxDate
	 * @param year
	 * @param filePath
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	public static void addJsonData(String fileName,String month,String fxDate,String year,String filePath) throws UnsupportedEncodingException, Exception{
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
        //新增加的记录
        JSONObject appObject = new JSONObject();
        appObject.put("fileName", fileName);
        appObject.put("month", month);
        appObject.put("fxDate", fxDate);
        appObject.put("year", year);
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
        FileWriter fileWriter = new FileWriter("e:/hgl.json");
    	PrintWriter out = new PrintWriter(fileWriter);
    	out.write(newStr);
    	out.println();
    	fileWriter.close();
    	out.close();
	}
	
	
	/**
	 * 设置单个单元格的边框
	 * @description
	 * @author Administrator
	 * @date 2018-11-21 上午09:49:59
	 * @param style
	 */
	public static void setBorderStyle(XSSFCellStyle style){
		style.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框    
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框    
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框    
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框 
	}
	
	public static void setJoinBorderStyle(int border, Integer rowIndex, Integer lastRow, Integer firstColumn, Integer lastColumn, XSSFSheet sheet, XSSFWorkbook wb){
		CellRangeAddress region = new CellRangeAddress(rowIndex,lastRow,firstColumn,lastColumn);//first row (0-based)  from 行
		sheet.addMergedRegion(region);
		
        RegionUtil.setBorderBottom(border, region, sheet, wb);   //下边框
        RegionUtil.setBorderLeft(border, region, sheet, wb);     //左边框
        RegionUtil.setBorderRight(border, region, sheet, wb);    //右边框
        RegionUtil.setBorderTop(border, region, sheet, wb);      //上边框
    }
	
	
	public static void main(String[] args) throws Exception{
		
		String absoFilePath = "d:\\tpjxl1.xlsx";
		File f = new File(absoFilePath);
    	InputStream inputStream = new FileInputStream(f);
    	XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
    	Integer sheetNum = xssfWorkbook.getNumberOfSheets();
    	if(sheetNum >= 1){
    		XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
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
            
            XSSFRow row0 = sheet.getRow(1);
            String jhTxt = row0.getCell(1).getStringCellValue().replace(" ", "").replace("\t", "");//井号
            String tprqTxt = row0.getCell(2).getStringCellValue().replace(" ", "").replace("\t", "");//调配日期
            String yjhTxt = sheet.getRow(2).getCell(12).getStringCellValue().replace(" ", "").replace("\t", "");//油井号
            Double rcy_total_front_up = 0.0;
            Double rcy_total_front_down = 0.0;
            Double rcy_total_front_temp = 0.0;
            Double rcy_total_front = 0.0;
            
            Double rcy_total_back_up = 0.0;
            Double rcy_total_back_down = 0.0;
            Double rcy_total_back_temp = 0.0;
            Double rcy_total_back = 0.0;
            System.out.println(yjhTxt);
            if(jhTxt.equals("井号") && tprqTxt.equals("调配日期") && yjhTxt.equals("井号")){
            	for(int i = 3 ; i < sheet.getLastRowNum()+1 ; i++){
	            	XSSFRow row1 = sheet.getRow(i);
	            	Integer cellType = row1.getCell(0).getCellType();
	            	String tpDate = row1.getCell(2).getStringCellValue().replace(".", "-");//调配日期
	            	String yjh = row1.getCell(12).getStringCellValue().replace(".", "-");//油井号
	            	if(!tpDate.equals("")){
	            		tpDate = CurrentTime.dateConvertToString(CurrentTime.stringToDate_1(tpDate));
	            		String tpFrontDate = CurrentTime.getFinalDate(tpDate,-9);//调配前往前推10天
	            		String tpBackDate_s = CurrentTime.getSpecNewDate_1(tpDate, 1);//调配后推后一个月开始日期
	            		String tpBackDate_e = CurrentTime.getSpecNewDate_1(tpDate, 2);//调配后推后一个月结束日期
	            		System.out.println("油井---"+yjh+" 调配前日期---"+tpFrontDate+" 至 "+tpDate+" 调配后日期---"+tpBackDate_s+" 至 "+tpBackDate_e);
//	            		Double rcy = row1.getCell(14).getNumericCellValue();
//	            		rcy_total_front_temp += rcy;
//	            		rcy_total_back_temp += row1.getCell(19).getNumericCellValue();
	            	}else{
	            		if(cellType.equals(0)){
	            			continue;
	            		}else{
	            			if(row1.getCell(0).getStringCellValue().equals("下调合计")){
	            				rcy_total_front_down = Convert.convertInputNumber_6(rcy_total_front_temp);
	            				rcy_total_front_temp = 0.0;
	            				
	            				rcy_total_back_down = Convert.convertInputNumber_6(rcy_total_back_temp);
	            				rcy_total_back_temp = 0.0;
//	            				System.out.println("调配前：下调合计--"+rcy_total_front_down+" 调配后：下调合计--"+rcy_total_back_down);
	            			}else if(row1.getCell(0).getStringCellValue().equals("上调小计")){
	            				rcy_total_front_up = Convert.convertInputNumber_6(rcy_total_front_temp);
	            				rcy_total_back_up = Convert.convertInputNumber_6(rcy_total_back_temp);
//	            				System.out.println("调配前：上调合计--"+rcy_total_front_up+" 调配后：上调合计--"+rcy_total_back_up);
	            			}else if(row1.getCell(0).getStringCellValue().equals("总合计")){
	            				rcy_total_front = Convert.convertInputNumber_6(rcy_total_front_down + rcy_total_front_up);
	            				rcy_total_back = Convert.convertInputNumber_6(rcy_total_back_down + rcy_total_back_up);
//	            				System.out.println("调配前：总合计--"+rcy_total_front+" 调配后：总合计--"+rcy_total_back);
	            				break;
	            			}
	            		}
	            	}
            	}
            }
//            System.out.println("调配前：下调合计--"+rcy_total_front_down+"  上调小计--"+rcy_total_front_up + "  总合计--"+rcy_total_front);
//            System.out.println("调配后：下调合计--"+rcy_total_back_down+"  上调小计--"+rcy_total_back_up + "  总合计--"+rcy_total_back);
    	}
	}
}
