<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import ="com.oreilly.servlet.MultipartRequest"%>
<%@ page import ="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import ="com.estone.test.DocConverter"%>
<%@ page import ="java.io.*"%>
<%
String path = request.getContextPath();
%>
<%
String swfpath = null;

String lastFileName=null;
//文件上传采用cos组件上传，可更换为commons-fileupload上传，文件上传后，保存在upload文件夹
//获取文件上传路径
String saveDirectory =this.getServletContext().getRealPath("/")+"upload";
//打印上传路径信息
System.out.println(saveDirectory);
//每个文件最大50m
int maxPostSize = 50 * 1024 * 1024 ;
//采用cos缺省的命名策略，重名后加1,2,3...如果不加dfp重名将覆盖
DefaultFileRenamePolicy dfp = new DefaultFileRenamePolicy();
//response的编码为"UTF-8",同时采用缺省的文件名冲突解决策略,实现上传,如果不加dfp重名将覆盖
MultipartRequest multi;
try {
	multi = new MultipartRequest(request, saveDirectory, maxPostSize,"UTF-8",dfp);
	//MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxPostSize,"UTF-8");
	//输出反馈信息
	Enumeration files = multi.getFileNames();
	     while (files.hasMoreElements()) {
	        System.err.println("ccc");
	       String name = (String)files.nextElement();
	       File f = multi.getFile(name);
	       
//	       newFile1Name=new Date().getTime()+f.getName().substring(f.getName().indexOf("."));
//	       File newf = new File(saveDirectory+newFile1Name);  
//	       f.renameTo(newf);
	       if(f!=null){
	         String fileName = multi.getFilesystemName(name);
	         //newFile1Name=new Date().getTime()+fileName.substring(fileName.indexOf("."));
	         //获取上传文件的扩展名
	         String extName=fileName.substring(fileName.lastIndexOf(".")+1);
	         //文件全路径
	         lastFileName=fileName;
	         //获取需要转换的文件名,将路径名中的'\'替换为'/'
	         String converfilename = saveDirectory.replaceAll("\\\\", "/")+"/"+fileName;
	         System.out.println(converfilename);
	         //调用转换类DocConverter,并将需要转换的文件传递给该类的构造方法
	         DocConverter d = new DocConverter(converfilename);
	         //调用conver方法开始转换，先执行doc2pdf()将office文件转换为pdf;再执行pdf2swf()将pdf转换为swf;
	     	 d.conver();
	     	 //调用getswfPath()方法，打印转换后的swf文件路径
	         System.out.println(d.getswfPath());
	     	 //生成swf相对路径，以便传递给flexpaper播放器
	         swfpath = "upload"+d.getswfPath().substring(d.getswfPath().lastIndexOf("/"));
	         System.out.println(swfpath);
	         //将相对路径放入sessio中保存
	         //session.setAttribute("swfpath", swfpath);
	       }
	 }
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
%>

<script language="javascript">
    document.write("上传成功");
	window.parent.document.getElementById("fujian").value="/upload/<%= lastFileName%>";
	window.parent.document.getElementById("fujianYuanshiming").value="<%= swfpath%>";
</script>
