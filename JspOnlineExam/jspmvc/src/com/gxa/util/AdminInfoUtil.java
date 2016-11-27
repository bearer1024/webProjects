package com.gxa.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.gxa.bean.Logon;

public class AdminInfoUtil {

	//ȡ��ϵͳclass·�� 
    public static String CONFIGPATH = FileUtil.getAppPath()+"/";
    //�����ļ�·��
    private static String prtFile = "admin.properties";
    
  //�û������޸�
    public static void setAdmin(String username,String password)throws Exception{
        String prtPath = CONFIGPATH+prtFile;
        Properties prt = new Properties();
         OutputStream os = null;
         try{
         os = new FileOutputStream(prtPath);

         }
         catch(Exception e){
             e.printStackTrace();
            throw new Exception("��ȡ������Ϣ����:"+e.toString());

         }
         prt.setProperty("username",username);
         prt.setProperty("password",password);
          prt.store(os,"adminInfo");//����
          os.close();
    }
    
  //��ȡ�û�����
    public static Logon getAdmin()throws Exception{
       String prtPath = CONFIGPATH+prtFile;
       Properties prt = new Properties();
       InputStream is = null;
       try{
       is = new FileInputStream(prtPath);

       }
       catch(Exception e){
           e.printStackTrace();
          throw new Exception("��ȡ������Ϣ����:"+e.toString());

       }
       prt.load(is);
       //--------��ȡֵ---------
       String username = prt.getProperty("username");
       String password = prt.getProperty("password");
       Logon logon = new Logon();
       logon.setUsername(username);
       logon.setPassword(password);
       is.close();
       //---------����-------
       return logon;
  }
    

  //---------------����û�������--------------
  public static boolean checkAdmin(String username,String password){
      boolean isAdmin = false;
      try{
               Logon logon = AdminInfoUtil.getAdmin();
               //��½�ɹ�
               if((logon.getUsername().equals(username))&&(logon.getPassword().equals(password))){
              isAdmin = true;
               }
           }catch(Exception e){
              isAdmin = false;
       }
          return isAdmin;
  }

}
