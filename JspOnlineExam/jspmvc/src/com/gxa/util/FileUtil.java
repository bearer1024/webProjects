package com.gxa.util;


import java.io.*;

public class FileUtil {
	static String appPath = null;
	/**----------------------------------------------------------------------- 
	*getAppPath需要一个当前程序使用的Java类的class属性参数，它可以返回打包过的 
	*Java可执行文件（jar，war）所处的系统目录名或非打包Java程序所处的目录 
	*@param cls为Class类型 
	*@return 返回值为该类所在的Java程序运行的目录 
	-------------------------------------------------------------------------*/    
	public static String getAppPath(Class cls){    
	    //检查用户传入的参数是否为空    
	    if(cls==null)    
	     throw new java.lang.IllegalArgumentException("参数不能为空！");    
	     ClassLoader loader=cls.getClassLoader();    
	    //获得类的全名，包括包名    
	     String clsName=cls.getName()+".class";    
	    //获得传入参数所在的包    
	     Package pack=cls.getPackage();    
	     String path="";    
	    //如果不是匿名包，将包名转化为路径    
	    if(pack!=null){    
	         String packName=pack.getName();    
	       //此处简单判定是否是Java基础类库，防止用户传入JDK内置的类库    
	       if(packName.startsWith("java.")||packName.startsWith("javax."))    
	          throw new java.lang.IllegalArgumentException("不要传送系统类！");    
	        //在类的名称中，去掉包名的部分，获得类的文件名    
	         clsName=clsName.substring(packName.length()+1);    
	        //判定包名是否是简单包名，如果是，则直接将包名转换为路径，    
	        if(packName.indexOf(".")<0) path=packName+"/";    
	        else{//否则按照包名的组成部分，将包名转换为路径    
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
	    //调用ClassLoader的getResource方法，传入包含路径信息的类文件名    
	     java.net.URL url =loader.getResource(path+clsName);    
	    //从URL对象中获取路径信息    
	     String realPath=url.getPath();    
	    //去掉路径信息中的协议名"file:"    
	    int pos=realPath.indexOf("file:");    
	    if(pos>-1) realPath=realPath.substring(pos+5);    
	    //去掉路径信息最后包含类文件信息的部分，得到类所在的路径    
	     pos=realPath.indexOf(path+clsName);    
	     realPath=realPath.substring(0,pos-1);    
	    //如果类文件被打包到JAR等文件中时，去掉对应的JAR等打包文件名    
	    if(realPath.endsWith("!"))    
	         realPath=realPath.substring(0,realPath.lastIndexOf("/"));    
	  /*------------------------------------------------------------ 
	    ClassLoader的getResource方法使用了utf-8对路径信息进行了编码，当路径 
	     中存在中文和空格时，他会对这些字符进行转换，这样，得到的往往不是我们想要 
	     的真实路径，在此，调用了URLDecoder的decode方法进行解码，以便得到原始的 
	     中文及空格路径 
	   -------------------------------------------------------------*/    
	  try{    
	     realPath=java.net.URLDecoder.decode(realPath,"utf-8");    
	    }catch(Exception e){throw new RuntimeException(e);}    
	   return realPath;    
	}//getAppPath定义结束    

	public static String getAppPath(){
		if(appPath==null)
			appPath = getAppPath(FileUtil.class);
		return appPath;
	}
	public static void main(String[] args) throws IOException{
         
		String path = FileUtil.getAppPath(FileUtil.class);
		
		StringBuffer sBuf = new StringBuffer();
		System.out.print("请输入要清理的文件夹名:");
		int i = 0;
		String dirName = null;
		//文件夹名
		while((i = System.in.read())!=13){
			if(i==10){//去换行
				continue;
			}
			sBuf.append((char)i);
		}
		dirName = sBuf.toString().trim();
		File file = new File(dirName);
		if(!file.exists()){
			System.out.println("文件夹 "+dirName+" 不存在!");
			System.exit(-1);
		}
		//文件名
		
		System.out.print("请输入要清理的文件名:");
		String filename = null;
		sBuf = new StringBuffer();
		while((i = System.in.read())!=13){
			if(i==10){//去换行
				continue;
			}
			sBuf.append((char)i);
		}
		filename = sBuf.toString().trim();
		
		//文件名确认
		String filenameConfirm = null;
		sBuf = new StringBuffer();
		System.out.print("请再次输入要清理的文件名:");
		while((i = System.in.read())!=13){
			if(i==10){//去换行
				continue;
			}
			sBuf.append((char)i);
		}
		filenameConfirm = sBuf.toString().trim();
		System.out.println("要清理的文件名为:"+filenameConfirm);
		//System.out.println("filenameConfirm="+filenameConfirm);
		if(!filename.equals(filenameConfirm)){
			System.out.println("两次输入的文件名不一致!");
			System.exit(-1);
		}
		FileUtil.delFile(file, filename);
		System.out.println("清理完成!");
	}

	/**
	 * 删除文件及文件夹
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
			
			if(file.list().length==0){//删除空文件夹
				file.delete();
			}
	}
	

	/**
	 * 删除文件夹中包括子文件夹所有名为fileName的文件、文件夹
	 * 用于除去如版本控制之类产生的文件，减少应用大小
	 * 
	 * @param file
	 * @param fileName 文件或文件夹名
	 */
	public static void delFile(File file,String fileName){
		if(!file.exists()){
			return;
		}
		//找到文件，删除
		if(file.getName().equals(fileName)){
			System.out.print("删除文件 "+file.getPath());
			delFile(file);//删除其子文件
			if(!file.exists()){
				System.out.println(" 成功");
			}else{
				System.out.println("失败");
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
	 * 取得文件大小
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
	  * 复制文件
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
