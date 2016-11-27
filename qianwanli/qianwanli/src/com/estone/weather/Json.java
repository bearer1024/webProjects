package com.estone.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringEscapeUtils;


public class Json {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String path = "http://m.weather.com.cn/data/101010100.html";
		URL url;
		String inputline="";String info="";
		
		try {
			url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setReadTimeout(10*1000);
			conn.setRequestMethod("GET");
			
			InputStreamReader  inStream = new InputStreamReader(conn.getInputStream(),"UTF-8");
			BufferedReader buffer=new BufferedReader(inStream);
			 
			while((inputline=buffer.readLine())!=null){
				info+=inputline;
			}
			
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		JSONObject jsonob = JSONObject.fromObject((JSONObject.fromObject(info).getString("weatherinfo")));
		String city = jsonob.getString("city");
		System.out.println(city);
		
		String date_y = jsonob.getString("date_y");
		System.out.println(date_y);
		
		String temp1 = jsonob.getString("temp1");
		System.out.println(temp1);
		
		String index_d = jsonob.getString("index_d");
		System.out.println(index_d);
		
		
		
		
		//解析的数据格式2：{"classroom":"0801","peoples":[{"field1":"name1","field2":"age1"},{"field0":"name2","field2":"age2说"}]}
		info="{\"classroom\":\"0801\",\"peoples\":[{\"field1\":\"name1\",\"field2\":\"age1\"},{\"field1\":\"name2\",\"field2\":\"age2\"}]}";
		
		jsonob = JSONObject.fromObject(info);
		
		String classname = jsonob.getString("classroom");
		System.out.println(classname);
		
		JSONArray jsons = jsonob.getJSONArray("peoples");
		int jsonLength = jsons.size();
		
		//对json数组进行循环
		for (int i = 0; i < jsonLength; i++) {
	       JSONObject tempJson = JSONObject.fromObject(jsons.get(i));
	       
	       String name = StringEscapeUtils.escapeSql(tempJson.getString("field1"));
	       String age = StringEscapeUtils.escapeSql(tempJson.getString("field2"));
	       
	       System.out.println(name+"-"+age);
	    }
	
	
	}

}