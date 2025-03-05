package net.syscon.s4.common;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

public class Utilities {

	private static Logger logger = LogManager.getLogger(Utilities.class.getName());

	public static final String DATE_PATTEN = "yyyy/MM/dd";

	/**
	 * 
	 */
	public String subStr(final String str, final int beginIndex, final int endIndex) {
		String strObj = str;
		try {
			strObj = str.substring(beginIndex, endIndex);
		} catch (Exception e) {
			logger.error("Exception in Utilities Class", e);
		}
		return strObj;
	}

	/**
	 * 
	 */
	public int length(final String str) {
		int length;
		length = str.length();
		return length;
	}

	/**
	 * 
	 */
	public String lTrim(final String str) {
		return str.trim();
	}

	/**
	 * 
	 */
	public String rTrim(final String str) {
		return str.trim();
	}

	/**
	 * 
	 */
	public void fClose(File fileName) {
		if (fileName != null) {
			fileName = null;
		}
	}

	/**
	 * @throws IOException
	 * 
	 */
	public void putLine(final File fileName, final String cLinea) throws IOException {
		try {
			FileWriter fw = null;
			fw = new FileWriter(fileName);
			fw.write(cLinea);
			fw.close();
		} catch (FileNotFoundException ex) {
			throw ex;
		}
	}

	/**
	 * @throws IOException
	 * 
	 */
	public String getLine(File fileName,String cLinea) throws IOException {
		String inputCLinea = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while ((inputCLinea = br.readLine()) != null) {
				inputCLinea = inputCLinea + br.readLine();
			}
			br.close();
		} catch (FileNotFoundException ex) {
			throw ex;
		}
		return inputCLinea;
	}

	/**
	 * 
	 */
	public Date toDate(final String pDate, final String pFormatter) throws ParseException {
		final SimpleDateFormat formatter = new SimpleDateFormat("pFormatter");
		final Date date = formatter.parse(pDate);
		return date;
	}

	/**
	 * 
	 */
	public int message(final String title, final String message,final  String alertStyle, final String button1, final String button2) {
		int i;
		i = 0;
		return i;
	}

	/**
	 * 
	 */
	public int message(final String title,final  String message) {
		return message(title, message, "", "", "");
	}

	/**
	 * 
	 */
	public int message(final String message) {
		return message(" Information", message, "", "", "");
	}

	/**
	 * 
	 */
	public int message(final String title,final  String message, final String alertStyle) {
		return message(title, message, alertStyle, "", "");
	}

	/**
	 * 
	 */
	public char toChar(final int value) {
		try {
			return (char) value;
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	/**
	 * 
	 */
	public int toNumber(final String value) {
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	/**
	 * 
	 */
	public int showAlert(final String title, final String message) {
		return message(title, message);
	}

	/**
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date getDate(final Date date, final String pattern) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date res = null;
		try {
			res = dateFormat.parse(dateFormat.format(date));
		} catch (Exception e) {
			logger.error(e);
		}
		return res;
	}

	public static Date convertDate(final Date date) {
		if (date != null) {
			return new Date(date.getTime());
		}
		return null;
	}

	public static Timestamp convertDateTime(final Date date) {
		if (date != null) {
			return new Timestamp(date.getTime());
		}
		return null;
	}

	public static BigDecimal abs(BigDecimal number) {
		if (number.compareTo(BigDecimal.ZERO)<0) {
			number = number.multiply(BigDecimal.valueOf(-1));
		}
		return number;
	}
	
	public static String capitalize(String value) {
		String firstLetStr = value.substring(0, 1);
		String remLetStr = value.substring(1);
		firstLetStr = firstLetStr.toUpperCase();
		String firstLetterCapitalizedName = firstLetStr + remLetStr;
		return firstLetterCapitalizedName;
	}
	

	public static byte[] compressFiles(List<File> inFiles) {
		ByteArrayOutputStream fos = new ByteArrayOutputStream();
		ZipOutputStream zipOut = new ZipOutputStream(fos);
		try {
			for (File fileToZip : inFiles) {
				FileInputStream fis = new FileInputStream(fileToZip);
				ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
				zipOut.putNextEntry(zipEntry);

				byte[] bytes = new byte[1024];
				int length;
				while((length = fis.read(bytes)) >= 0) {
					zipOut.write(bytes, 0, length);
				}
				fis.close();
			}
			zipOut.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fos.toByteArray();
	}
	
	public static List<File> unZipFiles(MultipartFile zipFile, String savedLocation) {
		byte[] buffer = new byte[1024];
		List<File> fileList = new ArrayList<>();
		File savedLocationFile = new File(savedLocation);
		try{
			ZipInputStream zis = new ZipInputStream(zipFile.getInputStream());
			ZipEntry zipEntry = zis.getNextEntry();
			while (zipEntry != null) {

				File newFile = new File(savedLocationFile, zipEntry.getName());
				fileList.add(newFile);
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				zipEntry = zis.getNextEntry();
			}
			zis.closeEntry();
			zis.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return fileList;
	}
	
	public static Boolean hasJackson2HttpMessageConverter(RestTemplate restTemplate) {
		boolean jacksonConverterFlag = false;
		for(HttpMessageConverter<?> obj : restTemplate.getMessageConverters()) {
			if(obj instanceof MappingJackson2HttpMessageConverter) {
				jacksonConverterFlag = true;
				break;
			}
		}
		return jacksonConverterFlag;
	}

}
