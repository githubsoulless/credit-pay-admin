package net.chrone.creditpay.util;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * Excel工具类
 * 
 * @author aojiong
 * 
 */
public class ExcelUtil {
	public static Workbook addDataToExcel(Workbook workbook,String sheetNm, String[] titleNms,
			String[] columMethodNms, List<?> list,int start) throws Exception {
		//设置格式
		CellStyle titleStyle=titleStyle(workbook);
		CellStyle dataStyle=dataStyle(workbook);
		
		//创建sheet和设置标题
		Cell cell = null;
		Sheet sheet=workbook.getSheet(StringUtils.isEmpty(sheetNm) ? "sheet1" : sheetNm);
		if(sheet==null){
			sheet = workbook
					.createSheet(StringUtils.isEmpty(sheetNm) ? "sheet1" : sheetNm);
			// 标题
			Row titleRow = sheet.createRow(0); // 创建标题行(第一行)
			titleRow.setHeight((short) 400);// 设置第一行的行高
			for (int i = 0; i < titleNms.length; i++) {
				sheet.setColumnWidth(i, 3500);// 设置单元格的宽
				cell = titleRow.createCell(i);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(titleNms[i]);
				cell.setCellStyle(titleStyle);
			}
		}
		// 数据
		Row dataRow = null;
		for (int i = 0; i < list.size(); i++) {
			dataRow = sheet.createRow(i + 1+start);// 创建行
			for (int j = 0; j < columMethodNms.length; j++) {
				cell = dataRow.createCell(j);// 创建列
				// 设值
				Method method = list
						.get(i)
						.getClass()
						.getMethod(columMethodNms[j]);
				String returnType = method.getReturnType().getName()
						.toLowerCase();
				Object value = method.invoke(list.get(i));
				cell.setCellStyle(dataStyle);
				if (returnType.indexOf("string") != -1) {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(value == null ? "" : value.toString());
				} else if (returnType.indexOf("integer") != -1
						|| returnType.indexOf("int") != -1
						|| returnType.indexOf("bigdecimal") != -1
						|| returnType.indexOf("double") != -1
						|| returnType.indexOf("long") != -1
						|| returnType.indexOf("float") != -1) {
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(value == null ? null : new Double(value
							.toString()));
				} else if (returnType.indexOf("date") != -1) {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(value == null ? null : DateUtils.formatDate((Date)value, "yyyy-MM-dd HH:mm:ss"));
				} else {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(value == null ? "" : value.toString());
				}
			}
		}
		return workbook;
	}
	
	/**
	 * 创建excel导出
	 * @param version
	 * @param sheetNm
	 * @param titleNms
	 * @param columMethodNms
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static Workbook createExcel(int version,String sheetNm, String[] titleNms,
			String[] columMethodNms, List<?> list) throws Exception {
		Workbook workbook=null;
		if(version==2007){
			workbook = new XSSFWorkbook();
		}else{
			workbook = new HSSFWorkbook();
		}
		Sheet sheet = workbook
				.createSheet(StringUtils.isEmpty(sheetNm) ? "sheet1" : sheetNm);
		CellStyle titleStyle=titleStyle(workbook);
		CellStyle dataStyle=dataStyle(workbook);
		// 标题
		Row titleRow = sheet.createRow(0); // 创建标题行(第一行)
		titleRow.setHeight((short) 400);// 设置第一行的行高
		Cell cell = null;
		for (int i = 0; i < titleNms.length; i++) {
			sheet.setColumnWidth(i, 3500);// 设置单元格的宽
			cell = titleRow.createCell(i);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(titleNms[i]);
			cell.setCellStyle(titleStyle);
		}

		// 数据
		Row dataRow = null;
		for (int i = 0; i < list.size(); i++) {
			dataRow = sheet.createRow(i + 1);// 创建行
			for (int j = 0; j < columMethodNms.length; j++) {
				cell = dataRow.createCell(j);// 创建列
				// 设值
				Method method = list
						.get(i)
						.getClass()
						.getMethod(columMethodNms[j]);
				String returnType = method.getReturnType().getName()
						.toLowerCase();
				Object value = method.invoke(list.get(i));
				cell.setCellStyle(dataStyle);
				if (returnType.indexOf("string") != -1) {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(value == null ? "" : value.toString());
				} else if (returnType.indexOf("integer") != -1
						|| returnType.indexOf("int") != -1
						|| returnType.indexOf("bigdecimal") != -1
						|| returnType.indexOf("double") != -1
						|| returnType.indexOf("long") != -1
						|| returnType.indexOf("float") != -1) {
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(value == null ? null : new Double(value
							.toString()));
				} else if (returnType.indexOf("date") != -1) {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(value == null ? null : DateUtils.formatDate((Date)value, "yyyy-MM-dd HH:mm:ss"));
				} else {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(value == null ? "" : value.toString());
				}
			}
		}
		return workbook;
	}

	private static CellStyle titleStyle(Workbook workbook) {
		CellStyle titleStyle = workbook.createCellStyle();
		titleStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		titleStyle.setAlignment(CellStyle.ALIGN_CENTER_SELECTION); // 居中
		titleStyle.setBorderLeft((short) 1);
		titleStyle.setBorderRight((short) 1);
		titleStyle.setBorderBottom((short) 1);
		titleStyle.setBorderTop((short) 1);
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);// 设置字体大小
		titleStyle.setFont(font);// 选择需要用到的字体格式
		return titleStyle;
	}

	private static CellStyle dataStyle(Workbook workbook) {
		CellStyle dataStyle = workbook.createCellStyle();
		dataStyle.setBorderBottom((short) 1);
		dataStyle.setBorderLeft((short) 1);
		dataStyle.setBorderRight((short) 1);
		dataStyle.setBorderTop((short) 1);
		dataStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		return dataStyle;
	}
	
	public static void workbook2InputStream(HttpServletResponse response,Workbook workbook, String fileName,String sufferNm) throws Exception {  
		 response.setCharacterEncoding("utf-8");
		 response.setHeader("Content-type", "application/vnd.ms-excel");
		 response.setHeader("Content-Disposition", "attachment; filename="+ new String((fileName).getBytes("gb2312"), "ISO8859-1")+ sufferNm);
		 // 设置下载头信息
		 response.setContentType("application nd.ms-excel; charset=utf-8");
		 workbook.write(response.getOutputStream());
		 response.getOutputStream().flush();
		 response.getOutputStream().close();
	}  
	/**
	 * 出错导出
	 * @param response
	 * @param fileName
	 * @param sufferNm
	 * @throws Exception
	 */
	 @SuppressWarnings("resource")
	public static void errorInputStream(HttpServletResponse response,String fileName,String sufferNm) throws Exception {  
		 Workbook workbook=null;
		 if(sufferNm.equals(".xls")){
			 workbook=new HSSFWorkbook();
		 }else{
			 workbook=new XSSFWorkbook();
		 }
		 response.setCharacterEncoding("utf-8");
		 response.setHeader("Content-type", "application/vnd.ms-excel");
		 response.setHeader("Content-Disposition", "attachment; filename="+ new String(fileName.getBytes("gb2312"), "ISO8859-1")+sufferNm);
		 // 设置下载头信息
		 response.setContentType("application nd.ms-excel; charset=utf-8");
		 workbook.write(response.getOutputStream());
		 response.getOutputStream().flush();
		 response.getOutputStream().close();
	 }  
	 /**
	  * 读取excel数据
	  * @param file
	  * @param suffixNm 
	  * @param colunmLength 列个数 (1开始)
	  * @return
	  * @throws Exception
	  */
	 @SuppressWarnings("resource")
	public static List<List<String>> readExcelFile(File file,String suffixNm,int colunmLength) throws Exception{
		 List<List<String>>dataLists=new ArrayList<List<String>>();
		 Workbook wb=null;
		if(suffixNm.equalsIgnoreCase(".xls")){
			POIFSFileSystem poifsfilesystem = new POIFSFileSystem(new FileInputStream(file)); // 打开输入流
			wb = new HSSFWorkbook(poifsfilesystem);
		}else{
			wb=new XSSFWorkbook(new FileInputStream(file));
		}
		Sheet firstSheet = wb.getSheetAt(0);
		int sumRows = firstSheet.getLastRowNum();// 总行数=+1
		if (firstSheet != null&&sumRows>0) {
			for (int i = 0; i <= sumRows; i++) {
				List<String> list = new ArrayList<String>();
				Row row = firstSheet.getRow(i);
				if(row==null){
					continue;
				}
				for(int j=1;j<=colunmLength;j++){
					list.add(getCellValue(row.getCell(j-1)));
				}
				dataLists.add(list);
			}
		}
		 return dataLists;
	 }
	 
	/**
	 * 得到解析的列并转换类型
	 * 
	 * @param cell
	 * @return
	 */
	private static String getCellValue(Cell cell) {
		String value = "";
		if (null == cell) {
			return value;
		}
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC: // 数值型
			if (DateUtil.isCellDateFormatted(cell)) {
				// 如果是date类型则 ，获取该cell的date值
				value =  new SimpleDateFormat("yyyy-MM-dd").format(DateUtil.getJavaDate(cell.getNumericCellValue()));
			} else {// 纯数字
				DecimalFormat df = new DecimalFormat();
				Number num = null;
				try {
					num = df.parse(cell.toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (num != null) {
					value = num.toString();
				} else {
					value = String.valueOf(cell.getNumericCellValue());
				}
			}
			break;
		/* 此行表示单元格的内容为string类型 */
		case Cell.CELL_TYPE_STRING: // 字符串型
			value = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_FORMULA:// 公式型
			// 读公式计算值
			value = String.valueOf(cell.getNumericCellValue());
			if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
				value = cell.getStringCellValue().toString();
			}
			cell.getCellFormula();
			break;
		case Cell.CELL_TYPE_BOOLEAN:// 布尔
			value = " " + cell.getBooleanCellValue();
			break;
		/* 此行表示该单元格值为空 */
		case Cell.CELL_TYPE_BLANK: // 空值
			value = "";
			break;
		case Cell.CELL_TYPE_ERROR: // 故障
			value = "";
			break;
		default:
			value = cell.getStringCellValue().toString();
		}
		return value;
	}
	public static void main(String[] args) throws Exception {
//		String[] columMethodNms={"getBusiCd","getBusiDesc"};
//		String[] titleNms={"业务代码","业务描述"};
//		List<Object> list = new ArrayList<Object>();
//		TBusiCd busiCd = new TBusiCd(); 
//		busiCd.setBusiCd("AC0"+1);
//		busiCd.setBusiDesc("备注"+1);
//		list.add(busiCd);
//		Workbook workbook = ExcelUtil.createExcel(2007, "测试",titleNms,columMethodNms, list);
//		for(int i=1;i<=10;i++){
//			list = new ArrayList<Object>();
//			busiCd = new TBusiCd(); 
//			busiCd.setBusiCd("AC0"+(i+1));
//			busiCd.setBusiDesc("备注"+(i+1));
//			list.add(busiCd);
//			workbook = ExcelUtil.addDataToExcel(workbook, "测试", titleNms, columMethodNms, list, i);
//		}
//		FileOutputStream out = new FileOutputStream("C:\\Users\\sun\\Desktop\\test.xlsx");
//		workbook.write(out);
//		out.close();
//		WithdrawaLog withdrawaLog=new WithdrawaLog();
//		String[] a=getMethod(withdrawaLog);
	}
	
	@SuppressWarnings("rawtypes")
	public static String[] getMethod(Object obj) throws Exception {
		List<String> list=new ArrayList<String>();
		Class clazz = obj.getClass();
		Field[] fields = obj.getClass().getDeclaredFields();// 获得属性
		for (Field field : fields) {
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
			Method getMethod = pd.getReadMethod();// 获得get方法
			String methodName=getMethod.getName();
			list.add(methodName);
		}
		System.out.println(list);
		String[] columMethodNms=new String[list.size()];
		return list.toArray(columMethodNms);
	}
    /**
     * @Title: processFileName
     * @Description: ie,chrom,firfox下处理文件名显示乱码
     */
    public static String processFileName(HttpServletRequest request, String fileNames) {
        String codedfilename = null;
        try {
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident")
                    || null != agent && -1 != agent.indexOf("Edge")) {// ie
                String name = java.net.URLEncoder.encode(fileNames, "UTF8");
                codedfilename = name;
            } else {// 火狐,chrome等
                codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
            }
        } catch (Exception e) {
            LogWriter.error("转换出错",e);
        }
        return codedfilename;
    }
}
