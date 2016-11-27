package com.gxa.util;


import java.io.*;

public class FileUtil {
	static String appPath = null;
	/**----------------------------------------------------------------------- 
	*getAppPath��Ҫһ����ǰ����ʹ�õ�Java���class���Բ����������Է��ش������ 
	*Java��ִ���ļ���jar��war��������ϵͳĿ¼����Ǵ��Java����������Ŀ¼ 
	*@param clsΪClass���� 
	*@return ����ֵΪ�������ڵ�Java�������е�Ŀ¼ 
	-------------------------------------------------------------------------*/    
	public static String getAppPath(Class cls){    
	    //����û�����Ĳ����Ƿ�Ϊ��    
	    if(cls==null)    
	     throw new java.lang.IllegalArgumentException("��������Ϊ�գ�");    
	     ClassLoader loader=cls.getClassLoader();    
	    //������ȫ������������    
	     String clsName=cls.getName()+".class";    
	    //��ô���������ڵİ�    
	     Package pack=cls.getPackage();    
	     String path="";    
	    //���������������������ת��Ϊ·��    
	    if(pack!=null){    
	         String packName=pack.getName();    
	       //�˴����ж��Ƿ���Java������⣬��ֹ�û�����JDK���õ����    
	       if(packName.startsWith("java.")||packName.startsWith("javax."))    
	          throw new java.lang.IllegalArgumentException("��Ҫ����ϵͳ�࣡");    
	        //����������У�ȥ�������Ĳ��֣��������ļ���    
	         clsName=clsName.substring(packName.length()+1);    
	        //�ж������Ƿ��Ǽ򵥰���������ǣ���ֱ�ӽ�����ת��Ϊ·����    
	        if(packName.indexOf(".")<0) path=packName+"/";    
	        else{//�����հ�������ɲ��֣�������ת��Ϊ·��    
	            int start=0,end=0;    
	             end=packName.indexOf(".");    
	            while(end!=-1){    
	                 path=path+packName.substring(start,end)+"/";    
	                 start=end+1;    
	                 end=packName.indexOf(".",start);    
	             }    
	             path=path+packName.substring(start)+"/";    
	         }    
	     }    
	    //����ClassLoader��getResource�������������·����Ϣ�����ļ���    
	     java.net.URL url =loader.getResource(path+clsName);    
	    //��URL�����л�ȡ·����Ϣ    
	     String realPath=url.getPath();    
	    //ȥ��·����Ϣ�е�Э����"file:"    
	    int pos=realPath.indexOf("file:");    
	    if(pos>-1) realPath=realPath.substring(pos+5);    
	    //ȥ��·����Ϣ���������ļ���Ϣ�Ĳ��֣��õ������ڵ�·��    
	     pos=realPath.indexOf(path+clsName);    
	     realPath=realPath.substring(0,pos-1);    
	    //������ļ��������JAR���ļ���ʱ��ȥ����Ӧ��JAR�ȴ���ļ���    
	    if(realPath.endsWith("!"))    
	         realPath=realPath.substring(0,realPath.lastIndexOf("/"));    
	  /*------------------------------------------------------------ 
	    ClassLoader��getResource����ʹ����utf-8��·����Ϣ�����˱��룬��·�� 
	     �д������ĺͿո�ʱ���������Щ�ַ�����ת�����������õ�����������������Ҫ 
	     ����ʵ·�����ڴˣ�������URLDecoder��decode�������н��룬�Ա�õ�ԭʼ�� 
	     ���ļ��ո�·�� 
	   -------------------------------------------------------------*/    
	  try{    
	     realPath=java.net.URLDecoder.decode(realPath,"utf-8");    
	    }catch(Exception e){throw new RuntimeException(e);}    
	   return realPath;    
	}//getAppPath�������    

	public static String getAppPath(){
		if(appPath==null)
			appPath = getAppPath(FileUtil.class);
		return appPath;
	}
	public static void main(String[] args) throws IOException{
         
		String path = FileUtil.getAppPath(FileUtil.class);
		
		StringBuffer sBuf = new StringBuffer();
		System.out.print("������Ҫ������ļ�����:");
		int i = 0;
		String dirName = null;
		//�ļ�����
		while((i = System.in.read())!=13){
			if(i==10){//ȥ����
				continue;
			}
			sBuf.append((char)i);
		}
		dirName = sBuf.toString().trim();
		File file = new File(dirName);
		if(!file.exists()){
			System.out.println("�ļ��� "+dirName+" ������!");
			System.exit(-1);
		}
		//�ļ���
		
		System.out.print("������Ҫ������ļ���:");
		String filename = null;
		sBuf = new StringBuffer();
		while((i = System.in.read())!=13){
			if(i==10){//ȥ����
				continue;
			}
			sBuf.append((char)i);
		}
		filename = sBuf.toString().trim();
		
		//�ļ���ȷ��
		String filenameConfirm = null;
		sBuf = new StringBuffer();
		System.out.print("���ٴ�����Ҫ������ļ���:");
		while((i = System.in.read())!=13){
			if(i==10){//ȥ����
				continue;
			}
			sBuf.append((char)i);
		}
		filenameConfirm = sBuf.toString().trim();
		System.out.println("Ҫ������ļ���Ϊ:"+filenameConfirm);
		//System.out.println("filenameConfirm="+filenameConfirm);
		if(!filename.equals(filenameConfirm)){
			System.out.println("����������ļ�����һ��!");
			System.exit(-1);
		}
		FileUtil.delFile(file, filename);
		System.out.println("�������!");
	}

	/**
	 * ɾ���ļ����ļ���
	 * @param file
	 */
	public static void delFile(File file){
		if(!file.exists()){
			return;
		}
		if(file.isFile()){
			file.delete();
			return;
		} 
			File files[] = file.listFiles();
			
			for (int i = 0; i < files.length; i++) {
                delFile(files[i]);
                
			}
			
			if(file.list().length==0){//ɾ�����ļ���
				file.delete();
			}
	}
	

	/**
	 * ɾ���ļ����а������ļ���������ΪfileName���ļ����ļ���
	 * ���ڳ�ȥ��汾����֮��������ļ�������Ӧ�ô�С
	 * 
	 * @param file
	 * @param fileName �ļ����ļ�����
	 */
	public static void delFile(File file,String fileName){
		if(!file.exists()){
			return;
		}
		//�ҵ��ļ���ɾ��
		if(file.getName().equals(fileName)){
			System.out.print("ɾ���ļ� "+file.getPath());
			delFile(file);//ɾ�������ļ�
			if(!file.exists()){
				System.out.println(" �ɹ�");
			}else{
				System.out.println("ʧ��");
			}
			return;
		}
		if(file.isFile()){
			return;
		} 
			File files[] = file.listFiles();
			
			for (int i = 0; i < files.length; i++) {
                delFile(files[i],fileName);
                
			}
			
	}
	
	/**
	 * ȡ���ļ���С
	 * @param f
	 * @return
	 * @throws Exception
	 */
	 public static long getFileSizes(File f){
	        long s=0;
	        FileInputStream fis = null;
	        try{
	        	if (f.exists()) {
	 	           
		           fis = new FileInputStream(f);
		           s= fis.available();
		        } 
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        finally{
	        	try{fis.close();}catch(Exception e){}
	        }
	        
	        return s;
	    }
	 
	
	 /**
	  * �����ļ�
	  * @param sourceFile
	  * @param destFile
	  * @throws Exception
	  */
	 public static void copy(String sourceFile, String destFile)throws Exception{
		 FileInputStream fis = null;
		 FileOutputStream fos = null;
		 try{
			 byte[] buf = new byte[1024];
			 fis = new FileInputStream(sourceFile);
			 fos = new FileOutputStream(destFile);
			 int len = 0;
			 while((len = fis.read(buf))>0){
				 fos.write(buf,0,len);
			 }
			 
		 }catch(Exception e){
			 e.printStackTrace();
			 throw e;
		 }
		 finally{
			 if(fis!=null){
				 fis.close();
			 }
			 if(fos!=null){
				 fos.close();
			 }
		 }
	 }
}
