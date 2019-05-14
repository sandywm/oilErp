/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.oil.action.login;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oil.tools.CommonTools;
import com.oil.util.WebUrl;

/** 
 * MyEclipse Struts
 * Creation date: 05-09-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LoginAction extends DispatchAction {
	

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * @throws UnsupportedEncodingException 
	 */
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String result = "";//格式为登录状态:管理科室：模块(1:油综调配见效率,2:水综合调配方案,3:水综层段合格率,4:作业注水合格率)
		String account = CommonTools.getFinalStr("account", request);
		String password = CommonTools.getFinalStr("password", request);
		Map<String,String> map = new HashMap<String,String>();
		if(!account.equals("") && !password.equals("")){
			String s = null;
			File file = new File(WebUrl.DATA_URL_JSON + "/loginUser.json");
			InputStreamReader br = new InputStreamReader(new FileInputStream(file),"UTF-8");//读取文件,同时指定编码
			StringBuffer sb = new StringBuffer();
	        char[] ch = new char[128];  //一次读取128个字符
	        int len = 0;
	        while((len = br.read(ch,0, ch.length)) != -1){
	            sb.append(ch, 0, len);
	        }
	        s = sb.toString();
	        String account_json = "";
	        String password_json = "";
	        
	        JSONObject dataJson = JSON.parseObject(s); 
	        JSONArray features = dataJson.getJSONArray("userList");// 找到features json数组
	        for(Integer i = 0 ; i < features.size() ; i++){
	        	JSONObject stuInfo = features.getJSONObject(i);// 获取features数组的第i个json对象
	        	account_json = stuInfo.getString("account");//获取账号
	        	password_json = stuInfo.getString("password");
	        	if(account_json.equals(account) && password_json.equals(password)){//账号和密码都要相同
	        		result = "succ";
	        		map.put("groupName", stuInfo.getString("groupName"));
	        		map.put("moduleId", stuInfo.getString("moduleId"));
	        		map.put("userName", stuInfo.getString("userName"));
	        		request.getSession(false).setAttribute("userName", stuInfo.getString("userName"));
	        		break;
	        	}else{
	        		result = "fail";
	        	}
	        }
		}
		map.put("result", result);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
}