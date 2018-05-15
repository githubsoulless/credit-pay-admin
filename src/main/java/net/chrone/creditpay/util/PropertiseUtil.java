package net.chrone.creditpay.util;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class PropertiseUtil {
	private final static Logger logger = Logger.getLogger(PropertiseUtil.class);
	
	/**
	 * 获取指定配置文件的指定key值
	 * @param fileName 配置文件名称
	 * @param key
	 * @return
	 * @author buybal
	 */
	public static String getDataFromPropertiseFile(String fileName,String key){
		if(fileName == null || "".equals(fileName)){
			logger.error("getDataFromPropertiseFile fileName is null");
			return null;
		}
		if(key == null || "".equals(key)){
			logger.error("getDataFromPropertiseFile key is null");
			return null;
		}
		try{
			ResourceBundle resource = ResourceBundle.getBundle(fileName);
			if(resource == null){
				logger.error(fileName+"配置文件不存在");
				return null;
			}
			return resource.getString(key);
		}catch(Exception e){
			logger.error(fileName+"配置文件获取参数异常,key="+key);
			return null;
		}
		
	}
	/**
	 * 读取字符串配置项
	 * @param fileName
	 * @param key
	 * @return
	 */
	public static String getString(String fileName, String key) {
		String val = getDataFromPropertiseFile(fileName, key);
		return val;
	}
	/**
	 * 读取整形配置项
	 * @param fileName
	 * @param key
	 * @return
	 */
	public static Integer getInteger(String fileName, String key) {
		String val = getDataFromPropertiseFile(fileName, key);
		try {
			Integer n = Integer.valueOf(val);
			return n;
		} catch(NumberFormatException e) {
			return null;
		}
	}
	/**
	 * 读取整形配置项
	 * @param fileName长
	 * @param key
	 * @return
	 */
	public static Long getLong(String fileName, String key) {
		String val = getDataFromPropertiseFile(fileName, key);
		try {
			Long n = Long.valueOf(val);
			return n;
		} catch(NumberFormatException e) {
			return null;
		}
	}
	/**
	 * 读取布尔型配置项
	 * @param fileName长
	 * @param key
	 * @return
	 */
	public static Boolean getBoolean(String fileName, String key) {
		String val = getDataFromPropertiseFile(fileName, key);
		if(val == null){
		    return null;
		}
		if (val.equalsIgnoreCase("true")) {
			return Boolean.valueOf(true);
		}
		if (val.equalsIgnoreCase("false")) {
			return Boolean.valueOf(false);
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(PropertiseUtil.getDataFromPropertiseFile("jdbc", "url"));
		
		System.out.println(Boolean.valueOf("true"));
		System.out.println(Boolean.valueOf("True"));
		System.out.println(Boolean.valueOf("tRue"));
		System.out.println(Boolean.valueOf("trUe"));
		System.out.println(Boolean.valueOf("truE"));
	}
	
}
