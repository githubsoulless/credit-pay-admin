package net.chrone.creditpay.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	/**
	 * 读取TXT文件
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static List<List<String>> readTxtFile(File file) throws Exception{
		
		List<List<String>> dataLists=new ArrayList<List<String>>();
		FileInputStream fis=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		try {
			fis=new FileInputStream(file);
			isr=new InputStreamReader(fis, "gbk");
			br=new BufferedReader(isr);
			String str="";
			while((str=br.readLine())!=null){
				List<String> list=new ArrayList<String>();
				list.add(str);
				dataLists.add(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(br!=null){
				br.close();
			}
			if(isr!=null){
				isr.close();
			}
			if(fis!=null){
				fis.close();
			}
		}
		return dataLists;
	}
	
	/**
	 * 复制文件
	 * 
	 * @param desc
	 *            目标
	 * @param orgin
	 *            源文件
	 * @throws Exception
	 */
	public static void copyFile(File desc, File orgin) throws Exception {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(orgin);
			fos = new FileOutputStream(desc);
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = fis.read(b)) != -1) {
				fos.write(b, 0, len);
			}
			fos.flush();
		} catch (Exception e2) {
			e2.printStackTrace();
			throw new Exception(e2); 
		} finally {
			if (fos != null) {
				fos.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
	}
	/**
	 * 保存TXT
	 * @param content
	 * @param filePath
	 * @throws IOException
	 */
	public static void writeBackFile(List<String> content, String filePath)
			throws IOException {
		BufferedWriter writer = null;
		OutputStreamWriter osw = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath, true);
			osw = new OutputStreamWriter(fos, "GBK");
			writer = new BufferedWriter(osw); // true为追加 false为覆盖
			for (int i = 0; i < content.size(); i++) {
				writer.write(content.get(i));
				writer.write("\r\n");
			}
			writer.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
			throw e1;
		} finally {
			if (writer != null) {
				writer.close();
			}
			if (osw != null) {
				osw.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
	}
	
	/**
	 * 读取TXT文件
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static List<String> readFile(File file) throws Exception{
		
		List<String> dataLists=new ArrayList<String>();
		FileInputStream fis=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		try {
			fis=new FileInputStream(file);
			isr=new InputStreamReader(fis, "gbk");
			br=new BufferedReader(isr);
			String str="";
			while((str=br.readLine())!=null){
				dataLists.add(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(br!=null){
				br.close();
			}
			if(isr!=null){
				isr.close();
			}
			if(fis!=null){
				fis.close();
			}
		}
		return dataLists;
	}
	
	/**
	 * 上传文件(重命名文件)
	 * @param file
	 * @return 文件名
	 * @throws Exception
	 */
	public static String upload(MultipartFile file,String savePath) throws Exception{
		File saveFile = new File(savePath);

		try {
			if (!saveFile.exists()) {
				saveFile.mkdirs();
			}
		} catch (Exception e) {
			LogWriter.error("[创建文件夹异常]");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		// 新文件名 当前时间的年月日小时分钟秒+随即三位数
		String filePath = savePath;
		String ymd = sdf.format(new Date());
		String newName = DigestUtils.md5Hex(ymd + new Random().nextInt(30000000));
		String fileName = file.getOriginalFilename();
		newName = newName+fileName.substring(fileName.lastIndexOf("."));
		filePath=filePath+newName;
		File localFile = new File(filePath);
		file.transferTo(localFile);
		return newName;
	} 
	
	/**
	 * 上传文件,自定义文件名
	 * @param file
	 * @return 文件名
	 * @throws Exception
	 */
	public static String uploadByCustom(MultipartFile file,String savePath,String fileName) throws Exception{
		File saveFile = new File(savePath);

		try {
			if (!saveFile.exists()) {
				saveFile.mkdirs();
			}
		} catch (Exception e) {
			LogWriter.error("[创建文件夹异常]");
		}
		String filePath = savePath;
		String suffix = file.getOriginalFilename();
		suffix = suffix.substring(suffix.lastIndexOf("."));
		fileName = fileName+suffix;
		filePath=filePath+fileName;
		File localFile = new File(filePath);
		file.transferTo(localFile);
		return filePath;
	} 
	
	public static void main(String[] args) {
//		List<String> list = new ArrayList<String>();
//		list.add("aa|bb|cc|dd|ee|ff|gg");
//		list.add("hh|ii|jj|kk|ll|mm|nn");
//		try {
//			writeBackFile(list, "D:/ftp/ftpupload/0002900F0096235/test/tst.txt");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		String fileName="a.jpg";
		System.out.println(fileName.substring(fileName.lastIndexOf(".")));
	}
}
