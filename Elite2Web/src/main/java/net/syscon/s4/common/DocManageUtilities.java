package net.syscon.s4.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EmptyFileException;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.HPSFPropertiesOnlyDocument;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageProperties;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.xml.xmp.XmpWriter;

import net.syscon.s4.eoffender.beans.Document;
import net.syscon.s4.eoffender.beans.EoffenderDocumentRequest;
import net.syscon.s4.eoffender.beans.EoffenderSqlParameter;
import net.syscon.s4.eoffender.beans.FileLimits;
import net.syscon.s4.iwp.beans.ManageDocumentRequest;


@Component
public class DocManageUtilities {


	private final String OFFENDER_BOOK_ID = "OFFENDER_BOOK_ID";
	private final String OFFENDER_ID_DISPLAY = "OFFENDER_ID_DISPLAY";
	private final String OFFENDER_PROCEEDING_ID = "OFFENDER_PROCEEDING_ID";
	private final String offender_proceeding_id = "offender_proceeding_id";
	private final String EVENT_ID = "EVENT_ID";
	private final String USER_ID = "USER_ID";
	public static final String TEMPLATE_NOT_FOUND = "Not Found";
	public static final String TEMPLATE_NOT_SUPPORTED = "Template not Supported";
	public static final String UNAUTHORIZED_ACCESS = "Unauthorized Access";
	public static final String DOCX = ".docx";
	public static final String PDF = ".pdf";
	public static final String DOC = ".doc";
	public static final String DOTX = ".dotx";
	public static final String DOT = ".dot";
	public static final String RTF = ".rtf";
	public static final String TXT = ".txt";
	public static final String XLS = ".xls";
	public static final String XLSX = ".xlsx";
	public static final Integer STATUS_SUCCESS = 200;
	public static int totalSizeLimit;
	public static double byteSize=0.00000095367432;
	public static int filecountLimit;
	public static final String FILE_SIZE_EXCEEDS ="File size exceeds";
	public static final String FILE_COUNT_EXCEEDS ="File count exceeds";
	public static final String SUCCESS ="success";
	public Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

	public static final Map<String, String> documentMimeTypeMap;
	public static final Map<String, String> templateMimeTypeMap;
	
	
    static {
    	documentMimeTypeMap = new HashMap<>();
		documentMimeTypeMap.put("Office Open XML Document", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		documentMimeTypeMap.put("DOTX File", "application/vnd.openxmlformats-officedocument.wordprocessingml.template");
		documentMimeTypeMap.put("Adobe Acrobat Document", "application/pdf");
		documentMimeTypeMap.put("DOC File", "application/msword");
		documentMimeTypeMap.put("application/vnd.openxmlformats-officedocument.wordprocessingml.template", ".dotx");
		documentMimeTypeMap.put("application/vnd.openxmlformats-officedocument.wordprocessingml.document", ".docx");
		documentMimeTypeMap.put("application/pdf", ".pdf");
		documentMimeTypeMap.put("application/msword", ".dot");
		documentMimeTypeMap.put(".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		documentMimeTypeMap.put(".pdf", "application/pdf");
		documentMimeTypeMap.put(".doc", "application/msword");
		documentMimeTypeMap.put(".png", "image/png");
		documentMimeTypeMap.put(".jpg", "image/jpeg");
		documentMimeTypeMap.put(".jpeg", "image/jpeg");
		documentMimeTypeMap.put(".bmp", "image/bmp");
		documentMimeTypeMap.put(".gif", "image/gif");
		documentMimeTypeMap.put(".tif", "image/tiff");
		documentMimeTypeMap.put(".tiff", "image/tiff");
		documentMimeTypeMap.put(".excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		documentMimeTypeMap.put(".html", "text/html");
		documentMimeTypeMap.put(".rtf", "application/rtf");
		documentMimeTypeMap.put(".rtf", "application/rtf");
		documentMimeTypeMap.put(".txt", "text/plain");
		documentMimeTypeMap.put(".xlsx", "application/vnd.ms-excel");
		//documentMimeTypeMap.put(".xls", "application/vnd.ms-excel");
		documentMimeTypeMap.put(".zip", "application/zip");
		documentMimeTypeMap.put(".csv", "text/csv");
		
		
		
		
	}
    
    static {
    	templateMimeTypeMap = new HashMap<>();
    	templateMimeTypeMap.put("application/vnd.openxmlformats-officedocument.wordprocessingml.template", DocManageUtilities.DOTX);
    	templateMimeTypeMap.put("application/msword", DocManageUtilities.DOT);
		
	}
    
    private static Logger logger = LogManager.getLogger(DocManageUtilities.class.getName());
    
	public Map<String, String> getbookmarkResolvedSqlMap(Map<String, String> bookmarkSqlMap, Map<String, List<EoffenderSqlParameter>> bookmarkParamsMap, ManageDocumentRequest eoffenderDocumentRequest,Map<String,Object> objectDataMap){
		for (String bookmark : bookmarkSqlMap.keySet()) {
			String resolvedSql = bookmarkSqlMap.get(bookmark);
			if (bookmarkParamsMap.get(bookmark) != null) {
				resolvedSql=resolvedSql.replaceAll("\\s*=\\s*", "=");
					for (EoffenderSqlParameter eoffenderSqlParameter : bookmarkParamsMap.get(bookmark)) {
						if(eoffenderSqlParameter.getParameterName()!=null){
							eoffenderSqlParameter.setParameterName(eoffenderSqlParameter.getParameterName().trim());
						}
						if (eoffenderSqlParameter.getParameterName().equalsIgnoreCase(OFFENDER_BOOK_ID)) {
							
							resolvedSql = replaceParameterData(eoffenderSqlParameter,resolvedSql,eoffenderDocumentRequest.getDocDetails().getOffenderBookId(),null,objectDataMap);
						
						} else if (eoffenderSqlParameter.getParameterName().equalsIgnoreCase(OFFENDER_ID_DISPLAY)){
							
							resolvedSql = replaceParameterData(eoffenderSqlParameter,resolvedSql,null,eoffenderDocumentRequest.getDocDetails().getOffenderIdDisplay(),objectDataMap);
						
						}else if (eoffenderSqlParameter.getParameterName().equalsIgnoreCase(EVENT_ID)){
							resolvedSql = replaceParameterData(eoffenderSqlParameter,resolvedSql,eoffenderDocumentRequest.getDocDetails().getEventId(),null,objectDataMap);
							
						
						} else if (eoffenderSqlParameter.getParameterName().equalsIgnoreCase(OFFENDER_PROCEEDING_ID)){
							resolvedSql = replaceParameterData(eoffenderSqlParameter,resolvedSql,eoffenderDocumentRequest.getDocDetails().getObjectId(),null,objectDataMap);
						
						} else {
						
							resolvedSql = replaceParameterData(eoffenderSqlParameter,resolvedSql,eoffenderDocumentRequest.getDocDetails().getObjectId(),null,objectDataMap);
						}

						// Resolving OFFENDER_PROCEEDING_ID as it is not in bookmark params table

						if(resolvedSql.contains("OFFENDER_PROCEEDING_ID")){
							resolvedSql = resolvedSql.replaceAll(OFFENDER_PROCEEDING_ID + "=\\?",
									OFFENDER_PROCEEDING_ID + "="
											 + eoffenderDocumentRequest.getDocDetails().getObjectId());
						}else if(resolvedSql.contains(offender_proceeding_id)){
							resolvedSql = resolvedSql.replaceAll(offender_proceeding_id + "=\\?",
									offender_proceeding_id + "="
											 + eoffenderDocumentRequest.getDocDetails().getObjectId());
						}

						if(resolvedSql.contains(":USER_ID")){
							resolvedSql = resolvedSql.replaceAll(":USER_ID",
									"'"+eoffenderDocumentRequest.getUserName()+"'");
						}else if(resolvedSql.contains(":user_id")){
							resolvedSql = resolvedSql.replaceAll(":user_id",
									eoffenderDocumentRequest.getUserName());
						}else if(resolvedSql.contains("=USER")){
							resolvedSql = resolvedSql.replaceAll("=USER",
									 "="+ "'" + eoffenderDocumentRequest.getUserName() + "'");
						}
						
						
					}
					bookmarkSqlMap.put(bookmark, resolvedSql);

			} else {
				resolvedSql=resolvedSql.replaceAll("\\s*=\\s*", "=");
				resolvedSql=resolvedSql.toUpperCase();
				/*if(resolvedSql.contains("SM.USER_ID=USER")){
					resolvedSql = resolvedSql.replaceAll("SM.USER_ID=USER",
							USER_ID + "="
									+ "'" + eoffenderDocumentRequest.getUserName() + "'");
				}else */
					if(resolvedSql.contains("=USER")){
					resolvedSql = resolvedSql.replaceAll("=USER",
							 "="+ "'" + eoffenderDocumentRequest.getUserName() + "'");
				}
				
				bookmarkSqlMap.put(bookmark, resolvedSql);
				
			}
		}

		return bookmarkSqlMap;
	}

	
	private String  replaceParameterData(EoffenderSqlParameter eoffenderSqlParameter,String sqlQuery,Long objectId,String offenderDisplayId, Map<String,Object> objectDataMap) {
		if(eoffenderSqlParameter.getField()!=null && objectDataMap!=null) {
			String replaceFieldString=null;
			Long replaceFieldNumber=null;
			if(eoffenderSqlParameter.getParameterDataType().equals("T")){
				if(objectDataMap!=null && objectDataMap.containsKey(eoffenderSqlParameter.getField()) && objectDataMap.get(eoffenderSqlParameter.getField())!=null){
				replaceFieldString=objectDataMap.get(eoffenderSqlParameter.getField()).toString();
				sqlQuery = sqlQuery.replaceAll(eoffenderSqlParameter.getParameterName().toLowerCase() + "=\\?",
				eoffenderSqlParameter.getParameterName() + "="
						+  "'"+replaceFieldString+"'").replaceAll(eoffenderSqlParameter.getParameterName().toUpperCase() + "=\\?",
								eoffenderSqlParameter.getParameterName() + "="
										+ "'"+replaceFieldString+"'");
				}
			}else if(eoffenderSqlParameter.getParameterDataType().equals("N")) {
				if(objectDataMap!=null && objectDataMap.containsKey(eoffenderSqlParameter.getField()) && objectDataMap.get(eoffenderSqlParameter.getField())!=null){
				if(isNumeric(objectDataMap.get(eoffenderSqlParameter.getField()).toString())) {
				replaceFieldNumber=Long.parseLong((objectDataMap.get(eoffenderSqlParameter.getField()).toString()));
				sqlQuery = sqlQuery.replaceAll(eoffenderSqlParameter.getParameterName().toLowerCase() + "=\\?",
				eoffenderSqlParameter.getParameterName() + "="
						+ replaceFieldNumber).replaceAll(eoffenderSqlParameter.getParameterName().toUpperCase() + "=\\?",
								eoffenderSqlParameter.getParameterName() + "="
										+ replaceFieldNumber);
				}
				}
			}
		}else {
			if(offenderDisplayId!=null) {
				sqlQuery = sqlQuery.replaceAll(eoffenderSqlParameter.getParameterName().toLowerCase() + "=\\?",
						eoffenderSqlParameter.getParameterName() + "="
								+ "'"+offenderDisplayId.toString()+"'").replaceAll(eoffenderSqlParameter.getParameterName().toUpperCase() + "=\\?",
										eoffenderSqlParameter.getParameterName() + "="
												+ offenderDisplayId.toString());
			}else {
				sqlQuery = sqlQuery.replaceAll(eoffenderSqlParameter.getParameterName().toLowerCase() + "=\\?",
						eoffenderSqlParameter.getParameterName() + "="
								+ objectId).replaceAll(eoffenderSqlParameter.getParameterName().toUpperCase() + "=\\?",
										eoffenderSqlParameter.getParameterName() + "="
												+ objectId);
			}
			
		}
		
		return sqlQuery;
	}
	
	
	
	public String getMimeType(String newDocumentPath){
		String mimeType = "application/octet-stream";
		if (newDocumentPath.toLowerCase().endsWith(".dot")) {
			mimeType = "application/msword";
		}else if (newDocumentPath.toLowerCase().endsWith(".docx")) {
			mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		} else if (newDocumentPath.toLowerCase().endsWith(".doc")) {
			mimeType = "application/msword";
		} else if (newDocumentPath.toLowerCase().endsWith(".dotx")) {
			mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.template";
		}

		return mimeType;
	}

	public static String getFileExtension(String newDocumentPath){      
		String fileExt="";
		String fileExtension = FilenameUtils.getExtension(newDocumentPath);
		logger.info("file extension in getFileExtension method::"+fileExtension);
		if(fileExtension!=null&& !fileExtension.equals("")) {
			fileExt=".".concat(fileExtension);
		}
		return fileExt;
		
		
	}

	public String getExtensionFromContentDisposition(String fileName){
		   String fileExt="";
        if(!DocManageUtilities.isEmpty(fileName)){
        	fileName = fileName.toLowerCase();
		String fileExtension = FilenameUtils.getExtension(fileName);
		if(fileExtension!=null&& !fileExtension.equals("")) {
			fileExt=".".concat(fileExtension);
		}
        }
		return fileExt;
       
		
	}

	public static String getFileNameFromContentDisposition(String contentDisposition){
		Pattern p = Pattern.compile("\"([^\"]*)\"");
		Matcher m = p.matcher(contentDisposition);

		String fileName2 = "";
		while (m.find()) {
			fileName2 = m.group(1);
		}
		if("".equals(fileName2)) {
			fileName2 = contentDisposition;
		}
		fileName2 = FilenameUtils.removeExtension(fileName2);
		fileName2 = fileName2.replaceAll(" ", "_");
		return fileName2;
	}
	
	
	public static boolean isEmpty(String s) {
		if (s == null || s.trim().length() == 0)
			return true;
		return false;
	}


	public HttpHeaders getHttpHeadersForFileUpload(String fileExt, String documentName, boolean isExtension){	
		HttpHeaders headers = new HttpHeaders();
		
		if(isExtension)
		    headers.setContentType(MediaType.parseMediaType(documentMimeTypeMap.get(fileExt)));
		else
			headers.setContentType(MediaType.parseMediaType(fileExt));
		
		//headers.set("Content-Disposition", "attachment; filename=" + documentName);
        headers.setAccessControlExposeHeaders(Collections.singletonList("Content-Disposition"));
        headers.setAccessControlAllowHeaders(Collections.singletonList("Content-Disposition"));
        return headers;
	}
	
	public HttpHeaders getHttpHeaderForuploadFile(String fileExt, String documentName, boolean isExtension) throws IOException{	
		HttpHeaders headers = new HttpHeaders();
		logger.info("getHttpHeaderForuploadFile method  started");
		logger.info("file extension ::"+fileExt);
		logger.info("file name :: "+documentName);
		if(isExtension) {
			if(documentName!=null) {
				File file = new File(documentName);
	            MimetypesFileTypeMap mimeType = new MimetypesFileTypeMap();
	            logger.info("mime type for file ::"+mimeType.getContentType(file));
			    headers.setContentType(MediaType.parseMediaType(mimeType.getContentType(file)));
			}else {
				headers.setContentType(MediaType.parseMediaType(documentMimeTypeMap.get(fileExt)));
			}
		}
		else {
			headers.setContentType(MediaType.parseMediaType(fileExt));
		}
			
		
		//headers.set("Content-Disposition", "attachment; filename=" + documentName);
        headers.setAccessControlExposeHeaders(Collections.singletonList("Content-Disposition"));
        headers.setAccessControlAllowHeaders(Collections.singletonList("Content-Disposition"));
        return headers;
	}

	public static void addPropertiesInDocument(File oldfile, Document documentMetaData, String fileExt , File newFile) throws IOException, DocumentException, InvalidFormatException,EmptyFileException {

		switch (fileExt) {
		case DOCX:
			addPropertiesInDocx(oldfile, documentMetaData);
			break;
		case PDF:
			addPropertiesInPdf(oldfile, documentMetaData, newFile);
			break;
		case DOC:
			addPropertiesInDoc(oldfile, documentMetaData, newFile);
			break;
		default:
			break;
		}
		
	}

    private static void addPropertiesInDocx(File file, Document documentMetaData) throws InvalidFormatException, IOException, EmptyFileException{

		try(OPCPackage opc = OPCPackage.open(file.getAbsolutePath());) {

			PackageProperties pp = opc.getPackageProperties();
			Optional<String> templateNameUriOptional = pp.getCategoryProperty();

			String uri;
			String temlplateUri;
			String recordNo;
			String templateName;
			
			if(!templateNameUriOptional.isPresent()){
				
				uri = documentMetaData.getUri();
				temlplateUri = documentMetaData.getTemplateUri();
				recordNo = "NEW_RECORD";
				templateName = documentMetaData.getDocumentType();
				
			} else {
				String[] metaData = templateNameUriOptional.get().split("-");

				uri = documentMetaData.getUri();
				temlplateUri = metaData[1] == null ? documentMetaData.getTemplateUri() : metaData[1];
				recordNo = metaData[2];
				templateName = documentMetaData.getDocumentType();
				
				if(isEmpty(recordNo) || "null".equalsIgnoreCase(recordNo) || "NEW_RECORD".equals(recordNo)){
					recordNo = documentMetaData.getDocumentId();
				}
			}
			String category  = uri + "-" + temlplateUri + "-" + recordNo + "-" + templateName;
			if(documentMetaData.getTemproaryDocumentId() != null) {
				category = category +"-"+documentMetaData.getTemproaryDocumentId();
			}
			pp.setCategoryProperty(category);
		}

	}

    private static void addPropertiesInPdf(File oldFile, Document documentMetaData, File newFile) throws IOException, DocumentException{
    	XmpWriter xmp = null;
    	PdfStamper stamper = null;
    	PdfReader reader = null;
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

			

			try (FileOutputStream os = new FileOutputStream(newFile.getAbsolutePath())) {
				reader = new PdfReader(oldFile.getAbsolutePath());
				stamper = new PdfStamper(reader, os);
				HashMap<String, String> info = reader.getInfo();
				String uri;
				String temlplateUri;
				String recordNo;
				String templateName;

				if (!info.isEmpty() && info != null) {

					if (!DocManageUtilities.isEmpty(info.get("Keywords"))) {

						String[] metaData = info.get("Keywords").split("-");

						uri = documentMetaData.getUri();
						temlplateUri = metaData[1];
						templateName = metaData[3];
						
						if (isEmpty(temlplateUri) || "null".equalsIgnoreCase(temlplateUri)) {
							temlplateUri = documentMetaData.getTemplateUri();
						}

						recordNo = metaData[2];

						if (isEmpty(recordNo) || "null".equalsIgnoreCase(recordNo) || "NEW_RECORD".equals(recordNo)) {
							recordNo = documentMetaData.getDocumentId();
						}

					} else {

						uri = documentMetaData.getUri();
						temlplateUri = documentMetaData.getTemplateUri();
						recordNo = "NEW_RECORD";
						templateName = documentMetaData.getDocumentType();
					}

					info.put("Keywords", uri + "-" + temlplateUri + "-" + recordNo + "-" + templateName);

					stamper.setMoreInfo(info);
					xmp = new XmpWriter(baos, info);
					stamper.setXmpMetadata(baos.toByteArray());
                    
					stamper.close();
				}
			}finally {
				if(stamper != null)
					stamper.close();
			}
		} catch (IOException | DocumentException e) {

			e.printStackTrace();

			throw e;
		} finally {
			if(reader !=null){
				reader.close();
			}
			if(xmp!=null)
			   xmp.close();
		}
	}
    
    private static void addPropertiesInDoc(File oldFile, Document documentMetaData, File newFile) throws FileNotFoundException, IOException, EmptyFileException {
		String uri;
		String temlplateUri;
		String recordNo;
		String templateName;
		
		POIFSFileSystem oldFs = new POIFSFileSystem(oldFile);
		
		try (HPSFPropertiesOnlyDocument documentPropertiesOld = new HPSFPropertiesOnlyDocument(oldFs);
		    // HPSFPropertiesOnlyDocument documentPropertiesNew = new HPSFPropertiesOnlyDocument(newFs);
				){
				
			DocumentSummaryInformation si = documentPropertiesOld.getDocumentSummaryInformation();
			if (si == null){
				documentPropertiesOld.createInformationProperties();
			}
				
			String categoryMetaData = si.getCategory();
 
			if (isEmpty(categoryMetaData)) {

				uri = documentMetaData.getUri();
				temlplateUri = documentMetaData.getTemplateUri();
				recordNo = "NEW_RECORD";
				templateName = documentMetaData.getDocumentType();

			} else {
				String[] metaData = categoryMetaData.split("-");

				uri = documentMetaData.getUri();
				temlplateUri = metaData[1] == null ? documentMetaData.getTemplateUri() : metaData[1];
				recordNo = metaData[2];
				templateName = documentMetaData.getDocumentType();

				if (isEmpty(recordNo) || "null".equalsIgnoreCase(recordNo) || "NEW_RECORD".equals(recordNo)) {
					recordNo = documentMetaData.getDocumentId();
				}
			}
			String category = uri + "-" + temlplateUri + "-" + recordNo + "-" + templateName;
			if(documentMetaData.getTemproaryDocumentId() != null) {
				category = category +"-" + documentMetaData.getTemproaryDocumentId();
			}
			si.setCategory(category);
			documentPropertiesOld.write(newFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
    
    public static void addPropertiesInDocGeneration(File oldFile, String properties, File newFile) throws FileNotFoundException, IOException {

    	POIFSFileSystem oldFs = new POIFSFileSystem(oldFile);
		try (HPSFPropertiesOnlyDocument documentPropertiesOld = new HPSFPropertiesOnlyDocument(oldFs);){
				
			DocumentSummaryInformation si = documentPropertiesOld.getDocumentSummaryInformation();
			if (si == null){
				documentPropertiesOld.createInformationProperties();
			}
				
			si.setCategory(properties);
			documentPropertiesOld.write(newFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
    
    public static boolean isSupportedTemplateType(String mimeType){
    	return templateMimeTypeMap.containsKey(mimeType);
    }
    
    
    public static String fileLimitCheck(List<FileLimits> fileSizelimits, List<MultipartFile> fileList ){
    	if(!fileSizelimits.isEmpty() && fileSizelimits.get(1).getProfileCode().equals("EOF_SIZE_LIM")){
			totalSizeLimit = Integer.parseInt(fileSizelimits.get(1).getProfileValue());
    	}	
	    if(fileSizelimits.get(0).getProfileCode().equals("EOF_NO_LIM")){
			filecountLimit = Integer.parseInt(fileSizelimits.get(0).getProfileValue());
		}
		double fileUploadSizeInMb=0;
		
		for (int i = 0; i < fileList.size(); i++) {			
			MultipartFile file = fileList.get(i);
			double size = file.getSize()*byteSize;
			fileUploadSizeInMb = fileUploadSizeInMb + size;
		} 
		if(fileUploadSizeInMb > totalSizeLimit){
			return FILE_SIZE_EXCEEDS;
		}
			
		if( fileList.size()>filecountLimit){
			
			return FILE_COUNT_EXCEEDS;
		}
    	
    	return SUCCESS;
    	
    }
    
public static Runnable sleep(long timeInMilliseconds){
        Runnable sleepingTask = () -> {
			try {
				Thread.sleep(timeInMilliseconds);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		return sleepingTask;
    }
    
    public static String cleanFileName(String fileName){
    	if(fileName.startsWith("EDITED")){
    		return fileName.split("_")[2];
    	}
    	
    	return fileName;
    }
    
    public static String generateRandomString() {
		int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 7;
	    Random random = new Random();
	 
	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	 
	    System.out.println(generatedString);
	    
	    return generatedString;
	}
    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false; 
        }
        return pattern.matcher(strNum).matches();
    }
}
