package com.gxa.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.gxa.bean.Logon;

public class AdminInfoUtil {

	//取得系统class路径 
    public static String CONFIGPATH = FileUtil.getAppPath()+"/";
    //配置文件路径
    private static String prtFile = "admin.properties";
    
  //用户密码修改
    public static void setAdmin(String username,String password)throws Exception{
        String prtPath = CONFIGPATH+prtFile;
        Properties prt = new Properties();
         OutputStream os = null;
         try{
         os = new FileOutputStream(prtPath);

         }
         catch(Exception e){
             e.printStackTrace();
            throw new Exception("读取配置信息出错:"+e.toString());

         }
         prt.setProperty("username",username);
         prt.setProperty("password",password);
          prt.store(os,"adminInfo");//保存
          os.close();
    }
    
  //读取用户密码
    public static Logon getAdmin()throws Exception{
       String prtPath = CONFIGPATH+prtFile;
       Properties prt = new Properties();
       InputStream is = null;
       try{
       is = new FileInputStream(prtPath);

       }
       catch(Exception e){
           e.printStackTrace();
          throw new Exception("读取配置信息出错:"+e.toString());

       }
       prt.load(is);
       //--------记取值---------
       String username = prt.getProperty("username");
       String password = prt.getProperty("password");
       Logon logon = new Logon();
       logon.setUsername(username);
       logon.setPassword(password);
       is.close();
       //---------返回-------
       return logon;
  }
    

  //---------------检查用户名密码--------------
  public static boolean checkAdmin(String username,String password){
      boolean isAdmin = false;
      try{
               Logon logon = AdminInfoUtil.getAdmin();
               //登陆成功
               if((logon.getUsername().equals(username))&&(logon.getPassword().equals(password))){
              isAdmin = true;
               }
           }catch(Exception e){
              isAdmin = false;
       }
          return isAdmin;
  }

}
