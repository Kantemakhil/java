package net.syscon.s4.iwp.base.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EmptyFileException;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.itextpdf.text.pdf.PdfReader;
import com.syncfusion.docio.TextSelection;
import com.syncfusion.docio.WordDocument;
import com.syncfusion.ej2.wordprocessor.WordProcessorHelper;
import com.syncfusion.javahelper.system.text.regularExpressions.MatchSupport;

import net.syscon.s4.common.CustomMultipartFile;
import net.syscon.s4.common.DocManageUtilities;
import net.syscon.s4.common.EoffenderUtilities;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.eoffender.beans.Document;
import net.syscon.s4.eoffender.beans.DocumentRequestBean;
import net.syscon.s4.eoffender.beans.EoffenderSqlParameter;
import net.syscon.s4.im.beans.IwpDocuments;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.iwp.base.DocumentRepository;
import net.syscon.s4.iwp.base.ManageDocService;
import net.syscon.s4.iwp.beans.GeneratedDoc;
import net.syscon.s4.iwp.beans.ManageDocumentRequest;
import net.syscon.s4.iwp.eoffender.EoffenderDocService;
import net.syscon.s4.sa.admin.beans.IwpTemplateRoles;
import net.syscon.s4.sa.admin.integratedwordprocessing.OumdtempRepository;


@Service
public class ManageDocServiceImpl implements ManageDocService {
	
	private static Logger logger = LogManager.getLogger(ManageDocServiceImpl.class.getName());
	
	private static final  int BUFFER_SIZE = 2048;
	
	private static final String FINAL_STATUS = "FROZEN";
	private static final String PUBLIC_STATUS = "PUBLIC";
	private static final String PRIVATE_STATUS = "PRIVATE";
	
	@Autowired
	DocumentRepository documentRepository;
	@Autowired
	private EoffenderDocService eoffenderDocumentService;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private DocManageUtilities docManageUtilities;
	
	
	@Autowired
	private OumdtempRepository oumdtempRepository;
	@Override
	public GeneratedDoc generateDocument(ManageDocumentRequest manageDocumentRequest,Map<String, Object> objectDataMap) {
		logger.info("========generateDocument Document starts here =========");
		GeneratedDoc generatedDoc = new GeneratedDoc();
		IwpTemplates iwpTemplates = documentRepository.getTemplateAsBlobFromDB(manageDocumentRequest.getTemplateId());
		String offBookingNo = "";
		String offIdDisplay = "";
		String newFilePath = "";
		List<String> bookmarkList = null;
		try {
			String type = (iwpTemplates.getTemplateType() ==null || "".equals(iwpTemplates.getTemplateType())) ? "docx" : iwpTemplates.getTemplateType().toUpperCase();
			File downloadedFile = saveFileToTempLoc(manageDocumentRequest.getPath(),iwpTemplates.getTemplateContent(),manageDocumentRequest.getTemplateId().toString(),".zip");
			newFilePath = downloadedFile.getAbsolutePath().replace(".zip", "."+type);
			boolean isUncompressed = uncompress(downloadedFile.getAbsolutePath(), newFilePath);
			Path newPath = Paths.get(newFilePath);
			byte[] documentContent = Files.readAllBytes(newPath);
			generatedDoc.setTemplateContent(documentContent);
			if(documentContent!=null) {
				 bookmarkList = getTemplateBookmarks(documentContent);
			}
			
			logger.info("downloadDocument bookmarkList : " + bookmarkList + ", offenderBookingNo " + offBookingNo+ ", offenderIdDisplay " + offIdDisplay);
		if(bookmarkList!=null && !bookmarkList.isEmpty()) {
			Map<String, String> bookmarkResolvedSqlMap = getBookmarkResolvedSqlMap(manageDocumentRequest, bookmarkList,objectDataMap);
			Map<String, List<String>> bookmarValueMap = eoffenderDocumentService.getBookmarkValueMap(bookmarkResolvedSqlMap, manageDocumentRequest.getUserName());
			generatedDoc.setBookmarValueMap(bookmarValueMap);
			}
			generatedDoc.setTemplateType(iwpTemplates.getObjectType());
		} catch (Exception  e) {
			logger.error("Error in generateDocument:: "+ e.getMessage());
		} 
		
		generatedDoc.setGeneratedDocLocation(newFilePath);
		
		return generatedDoc;
	}
	
	private List<String> getTemplateBookmarks(byte[] documentContent) throws Exception{
		Set<String> bookMarkList=new HashSet<>();
		List<String> bookMarks = null;
		String templateData = new String(documentContent, StandardCharsets.UTF_8);
		String docText[] = new String[] {String.join("\n",templateData)};
		if(docText[0]!=null) {
		 WordDocument document=WordProcessorHelper.save(docText[0]);
		 if(document!=null) {
		 TextSelection[] textSelections = document.findAll(Pattern.compile(MatchSupport.trimPattern("\\{\\{([^}]+)\\}\\}")));
			if(textSelections!=null) {
			for (int i = 0; i < textSelections.length; i++) 
			{
				if(textSelections[i]!=null && textSelections[i].getSelectedText()!=null && !textSelections[i].getSelectedText().equals("")) {
					 String bookMark=textSelections[i].getSelectedText().replace("{","").replace("}", "");
					  if(bookMark.contains(".")) {
					 String[] data=bookMark.split("[.]");
					 if(!ArrayUtils.isEmpty(data)){
						  bookMarkList.add(bookMark.split("[.]")[0]);
					  }
					  }else {
						  bookMarkList.add(bookMark);
					  }
				}
			}
			 bookMarks= bookMarkList.stream().collect(Collectors.toList());
			}
			
		 }
		
		
		}
		return bookMarks;
		
	}
	
	@Override
	public IwpDocuments viewDocument(BigDecimal documentId, String path) throws IOException {
		File downloadedFile;
		IwpDocuments iwpDocuments = documentRepository.getIwpDocument(documentId);
		String fileExt = docManageUtilities.getFileExtension(iwpDocuments.getDocumentName());
		if(iwpDocuments.getSignedDoc() == null) {
			 downloadedFile = saveFileToTempLoc(path, iwpDocuments.getDocumentContent(), documentId+"",".zip");
			 String newFilePath = downloadedFile.getAbsolutePath().replace(".zip", fileExt);
				boolean isUncomressed = uncompress(downloadedFile.getAbsolutePath(), newFilePath);
				iwpDocuments.setDocumentContent(Files.readAllBytes(Paths.get(newFilePath)));
				Files.deleteIfExists(Paths.get(newFilePath));	
				 newFilePath = downloadedFile.getAbsolutePath().replace(fileExt,".zip");
				 Files.deleteIfExists(Paths.get(newFilePath));
		}
		
		
		return iwpDocuments;
	}
	
	@Override
	public IwpDocuments getEditableDocument(BigDecimal documentId, String path, String user) throws Exception {
		IwpDocuments iwpDocuments = documentRepository.getIwpDocument(documentId);
		if(iwpDocuments != null) {
			if(FINAL_STATUS.equalsIgnoreCase(iwpDocuments.getDocumentStatus())) {
				// Return Document Can not be edited
				throw new Exception("Document Can not be edited because it is final.");
			} else if(PRIVATE_STATUS.equalsIgnoreCase(iwpDocuments.getDocumentStatus()) && !iwpDocuments.getCreateUserId().equals(user)) {
				// Return Document Can not be edited
				throw new Exception("Document Can not be edited because it is not allowed by creator.");
			} else {
				File downloadedFile = saveFileToTempLoc(path, iwpDocuments.getDocumentContent(), documentId+"",".zip");
				String fileExt = docManageUtilities.getFileExtension(iwpDocuments.getDocumentName());
				String newFilePath = downloadedFile.getAbsolutePath().replace(".zip", fileExt);
				boolean isUncomressed = uncompress(downloadedFile.getAbsolutePath(), newFilePath);
				File fileForAddingProperties = saveFileToTempLoc(path, Files.readAllBytes(Paths.get(newFilePath)), documentId+"-Edited",fileExt);
				Document documentMetaData = new Document();
				documentMetaData.setDocumentId(iwpDocuments.getDocumentId().toString());
				documentMetaData.setTemplateUri(iwpDocuments.getTemplateId().toString());
				DocManageUtilities.addPropertiesInDocument(new File(newFilePath), documentMetaData , fileExt, fileForAddingProperties);
				String returnFilePath  = fileExt.equalsIgnoreCase(DocManageUtilities.DOCX)?newFilePath:fileForAddingProperties.getAbsolutePath();
				iwpDocuments.setDocumentContent(Files.readAllBytes(Paths.get(returnFilePath)));
				Files.deleteIfExists(Paths.get(returnFilePath));
				 newFilePath = downloadedFile.getAbsolutePath().replace(fileExt,".zip");
				 Files.deleteIfExists(Paths.get(newFilePath));
				return iwpDocuments;
			}
		}
		return null;
	}
	
	@Override
	public List<Document> listDocuments(DocumentRequestBean documentRequestBean) {
		List<IwpDocuments> iwpDocumentsList = null;
		List<Document> documentList = new ArrayList<>();
		List<IwpTemplateRoles> templateList = new ArrayList<>();
		try {
			
			if(documentRequestBean.getOffenderBookingId() != null && documentRequestBean.getModuleName()!=null && documentRequestBean.getModuleName().equals("OCIDOCUM"))
			{
				iwpDocumentsList = documentRepository.getIwpDocumentList(documentRequestBean.getOffenderBookingId());
			}
			else if (documentRequestBean.getObjectId() != null && documentRequestBean.getOffenderBookingId() != null
					&& !documentRequestBean.getObjectId().equals("")) {
				iwpDocumentsList = documentRepository.getIWPDocumentObjectOffBkgList(
						documentRequestBean.getOffenderBookingId(), documentRequestBean.getObjectId(),
						documentRequestBean.getModuleName());
			} else if (documentRequestBean.getOffenderBookingId() != null && documentRequestBean.getObjectId() != null
					&& documentRequestBean.getObjectId().equals("")) {
				iwpDocumentsList = documentRepository.getIWPDocumentList(documentRequestBean.getOffenderBookingId(),
						documentRequestBean.getModuleName());
			} else {
				iwpDocumentsList = documentRepository.getIWPObjectDocumentList(documentRequestBean.getObjectId(),
						documentRequestBean.getModuleName());
			}
			templateList=oumdtempRepository.iwpRolesForStaffExecuteQuery(documentRequestBean.getCreateUserId());
			if(iwpDocumentsList!=null && !iwpDocumentsList.isEmpty()) {
				filterDocumentList(templateList,iwpDocumentsList);
			}
			
		for(IwpDocuments iwpDocuments: iwpDocumentsList) {
			Document document = new Document();
			document.setDocumentId(iwpDocuments.getDocumentId()+"");
			document.setDocumentName(iwpDocuments.getDocumentName());
			document.setDocumentAuthor(iwpDocuments.getUserCreated());
			document.setCreatedDateAsDate(iwpDocuments.getDateCreated());
			document.setModifiedDateAsDate(iwpDocuments.getModifyDatetime());
			document.setCreatedDate(String.valueOf(iwpDocuments.getCreateDatetime()));
			document.setModifiedDate(String.valueOf(iwpDocuments.getModifyDatetime()));
			document.setTemplateName(iwpDocuments.getTemplateName());
			document.setDocumentType(iwpDocuments.getTemplateName());
			document.setStatus(iwpDocuments.getDocumentStatus());
			document.setSignatureAccess(iwpDocuments.getSignatureAccess());
			document.setSignedDoc(iwpDocuments.getSignedDoc());
			document.setObjectType(iwpDocuments.getObjectType());
			document.setModuleDescription(iwpDocuments.getModuleDescription());
			document.setCommentText(iwpDocuments.getCommentText());
			documentList.add(document);
			}
		} catch (Exception e) {
			logger.error("Retrieving listDocuments :: "+ e.getMessage());
		}
		return documentList;
	}
	
	
	private void filterDocumentList(List<IwpTemplateRoles> templateList,List<IwpDocuments> documentList) {
		if(templateList!=null && !templateList.isEmpty()) {
			Set<Long> templateIds=templateList.stream().map(data -> data.getTemplateId()).collect(Collectors.toSet());
			documentList.removeIf(person -> !(templateIds.contains(new Long(person.getTemplateId().toString()))));

		}else {
			documentList.clear();
		}
		
	}
	private File saveFileToTempLoc(String pathToDirectory, byte[] templateByteArray,
			String offenderIdDisplay, String fileType) throws IOException {
		
		Calendar cal = Calendar.getInstance();
		File directoryPath = new File(pathToDirectory);
		if (!directoryPath.exists()) {
			directoryPath.mkdirs();
		}
		String saveDir = pathToDirectory.concat(offenderIdDisplay + "_").concat(cal.getTimeInMillis() + "").concat(fileType);
		File trimFile = new File(saveDir);
		CustomMultipartFile customMultipartFile = new CustomMultipartFile(templateByteArray);
		customMultipartFile.transferTo(trimFile);
		return trimFile;
	}

	@Override
	public Document getPropertiesFromFile(String saveDir, String fileExt) throws IOException, InvalidFormatException  {
		Document document = new Document();
		switch (fileExt) {
		case EoffenderUtilities.DOCX:
			document = getPropertiesFromDocxFile(saveDir);
			break;
		case EoffenderUtilities.PDF:
			document = getPropertiesFromPdfFile(saveDir);
			break;
		case EoffenderUtilities.DOC:
			document = getPropertiesFromDocFile(saveDir);
			break;	
		default:
			document = null;
			break;
		}
		return document;
	}
	
	private Document getPropertiesFromDocxFile(String filePath) throws InvalidFormatException, IOException {
		Document document = null;
		try(OPCPackage opc = OPCPackage.open(filePath);) {

			PackageProperties pp = opc.getPackageProperties();
			Optional<String> templateNameUriOptional = pp.getCategoryProperty();
			document = new Document();
			if (templateNameUriOptional.isPresent()) {
				String[] templateNameUri = templateNameUriOptional.get().split("-");

				if(templateNameUri.length>=4){
					document.setUri(templateNameUri[0]);
					document.setTemplateUri(templateNameUri[1]);
					if (!EoffenderUtilities.isEmpty(templateNameUri[2])) {
						if (templateNameUri[2].equals("NEW_RECORD"))
							document.setDocumentId("");
						else
							document.setDocumentId(templateNameUri[2]);
					}
					document.setTemplateName(templateNameUri[3]);
					document.setDocumentType(templateNameUri[3]);
				}

			}
		}catch(EmptyFileException ex){
			logger.error("getPropertiesFromDocxFile :: The supplied file was empty (zero bytes long)"+ex.getMessage());
			document = new Document();
			
		}
		return document;
	}
	
	private Document getPropertiesFromPdfFile(String saveDir) throws IOException {
//		XmpWriter xmp = null;
		PdfReader reader = null;
    	Document document = new Document();
    	try(ByteArrayOutputStream baos = new ByteArrayOutputStream();) {

    		reader = new PdfReader(saveDir);
			HashMap<String, String> info = reader.getInfo();
			if (!info.isEmpty() && info != null) {

				if (!EoffenderUtilities.isEmpty(info.get("Keywords"))) {

					String[] properties = info.get("Keywords").split("-");

					document.setUri(properties[0]);
					if (!EoffenderUtilities.isEmpty(properties[1])) {
						document.setTemplateUri(properties[1]);
					}
					// document.setTemplateId(properties[1]);
					document.setDocumentId(properties[2]);
					document.setTemplateName(properties[3]);
					document.setDocumentType(properties[3]);
					//xmp = new XmpWriter(baos, info);
				}
			}

		} catch (IOException e) {
			logger.error("Unable to Retrieve properties from PDF file",e.getMessage());
		} finally {

			if(reader!=null){
				reader.close();
			}
		}
		return document;
	}
	
	private Document getPropertiesFromDocFile(String saveDir) throws FileNotFoundException, IOException{
		Document document  = null;
		HWPFDocument hwpfDocument = null;
		FileInputStream fis = null;
		try {
			document = new Document();
			fis = new FileInputStream(saveDir);
			hwpfDocument = new HWPFDocument(fis);
			if(!EoffenderUtilities.isEmpty(hwpfDocument.getDocumentSummaryInformation().getCategory())){
				String[] templateNameUri = hwpfDocument.getDocumentSummaryInformation().getCategory().split("-");

				if(templateNameUri.length>=4){
					document.setUri(templateNameUri[0]);
					document.setTemplateUri(templateNameUri[1]);
					if (!EoffenderUtilities.isEmpty(templateNameUri[2])) {
						if (templateNameUri[2].equals("NEW_RECORD"))
							document.setDocumentId("");
						else
							document.setDocumentId(templateNameUri[2]);
					}
					document.setTemplateName(templateNameUri[3]);
					document.setDocumentType(templateNameUri[3]);
				}
				hwpfDocument.close();
			}
		} catch (EmptyFileException ex) {
			logger.error("getPropertiesFromDocFile :: The supplied file was empty (zero bytes long)"+ex.getMessage());
			fis.close();
		} catch (Exception e){
			logger.error("getPropertiesFromDocFile :: The supplied file was empty (zero bytes long)"+e.getMessage());
		}
		return document;
	}
	
	@Override
	public int[] uploadDocuments(List<IwpDocuments> iwpDocuments, String pathToDirectory, String user) {
		int count=0;
		for(IwpDocuments iwpDocument: iwpDocuments) {
			File downloadedFile;
			try {
				downloadedFile = saveFileToTempLoc(pathToDirectory, iwpDocument.getDocumentContent(), iwpDocument.getOffenderBookId()+"", iwpDocument.getFileExtension());
				String newFilePath = downloadedFile.getAbsolutePath().replace(iwpDocument.getFileExtension(), ".zip");
				byte[] copressedContent = compress(downloadedFile.getAbsolutePath(), newFilePath);
				iwpDocument.setDocumentContent(copressedContent);
				iwpDocument.setCreateUserId(user);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int[] iwpDocumentsList = documentRepository.uploadDocument(iwpDocuments);
		//TODO Delete both files downloadFile and newFilePath
		return iwpDocumentsList;
	}
	
	@Override
	public int[] uploadTemplates(List<IwpTemplates> iwpDocuments, String pathToDirectory) {
		int count=0;
		for(IwpTemplates iwpDocument: iwpDocuments) {
			File downloadedFile;
			try {
				downloadedFile = saveFileToTempLoc(pathToDirectory, iwpDocument.getTemplateContent(), iwpDocument.getTemplateId()+"", iwpDocument.getFileExtension());
				String newFilePath = downloadedFile.getAbsolutePath().replace(iwpDocument.getFileExtension(), ".zip");
				byte[] copressedContent = compress(downloadedFile.getAbsolutePath(), newFilePath);
				iwpDocument.setTemplateContent(copressedContent);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int[] iwpDocumentsList = documentRepository.uploadTemplate(iwpDocuments);
		//TODO Delete both files downloadFile and newFilePath
		return iwpDocumentsList;
	}
	
	
	
	
	private byte[] compress(String inFile, String outFile){
	    FileInputStream fin = null;
	    GZIPOutputStream out = null;
	    byte[] documentContent = null;
	    try{
	       // open file
	       fin = new FileInputStream(inFile);
	       // prepare output
	       out = new GZIPOutputStream(new FileOutputStream(outFile));
	       // transfer bytes
	       int len = 0;
	       byte[] buf = new byte[BUFFER_SIZE];
	       while((len = fin.read(buf)) > 0){
	         out.write(buf, 0, len);
	       }
	       fin.close();
	       // complete
	       out.finish();
	       out.close();    
	       documentContent = Files.readAllBytes(Paths.get(outFile));
	    } catch(Exception e) {
	       try{
	         if (fin != null) fin.close();
	         if (out != null) out.close();
	       }catch(Exception ex){
	    	   
	       }
	    } finally {
	    	//delete created files
	    }
	    return documentContent;
	  }
	
	private  boolean uncompress(String inFile, String outFile){
	    GZIPInputStream fin = null;
	    OutputStream out = null;
	    try{
	       // open output file
	       out = new FileOutputStream(outFile);
	       fin = new GZIPInputStream(new FileInputStream(inFile));
	       //transfer bytes
	       byte[] buf = new byte[BUFFER_SIZE];
	       int len = 0;
	       while((len = fin.read(buf)) > 0){
	         out.write(buf, 0, len);
	       }
	       // close
	       fin.close();
	       out.close();
	       return true;
	    }catch(Exception e){
	       try{
	         if (fin != null) fin.close();
	         if (out != null) out.close();
	       }catch(Exception ex){}
	       return false;
	    }
	  }
	
	private Map<String, String> getBookmarkResolvedSqlMap(ManageDocumentRequest manageDocumentRequest,
			List<String> bookmarkList,Map<String,Object> objectDataMap) {
		Map<String, String> bookmarkResolvedSqlMap = null;
		try {
			
			CountDownLatch latch = new CountDownLatch(2);
			Callable<Map<String, String>> bookMarkSqlMappingCallable = () -> {
				Map<String, String> bookmarkSqlMap = null;
				try {
					bookmarkSqlMap = eoffenderDocumentService.getBookmarkSqlMapping(bookmarkList);

					/*logger.info("getBookmarkResolvedSqlMap bookmarkSqlMap: " + bookmarkSqlMap + ", offenderBookingNo "
							+ eoffenderDocumentRequest.getDocDetails().getOffenderBookingNo()
							+ ", offenderIdDisplay "
							+ eoffenderDocumentRequest.getDocDetails().getOffenderIdDisplay());*/

				} catch (Exception e) {
					logger.error("getBookmarkResolvedSqlMap bookmarkSqlMapping faild: " + e + ", offenderBookingNo "
							+ manageDocumentRequest.getDocDetails().getOffenderBookingNo()
							+ ", offenderIdDisplay "
							+ manageDocumentRequest.getDocDetails().getOffenderIdDisplay());
				}

				latch.countDown();
				return bookmarkSqlMap;
			};
			
			Future<Map<String, String>> bookmarkSqlMapFuture = taskExecutor.submit(bookMarkSqlMappingCallable);

			Callable<Map<String, List<EoffenderSqlParameter>>> bookMarkParamterCallable = () -> {
				Map<String, List<EoffenderSqlParameter>> bookmarkParametersMap = null;
				try {
					bookmarkParametersMap = eoffenderDocumentService.getBookmarkParameterMapping(bookmarkList);
					/*logger.info("getBookmarkResolvedSqlMap bookmark parameter: " + bookmarkParametersMap + ", offenderBookingNo "
							+ eoffenderDocumentRequest.getDocDetails().getOffenderBookingNo()
							+ ", offenderIdDisplay "
							+ eoffenderDocumentRequest.getDocDetails().getOffenderIdDisplay());*/
				} catch (Exception e) {
					logger.error("getBookmarkResolvedSqlMap bookmark parameter Mapping faild: " + bookmarkParametersMap
							+ ", offenderBookingNo "
							+ manageDocumentRequest.getDocDetails().getOffenderBookingNo()
							+ ", offenderIdDisplay "
							+ manageDocumentRequest.getDocDetails().getOffenderIdDisplay());
				}

				latch.countDown();
				return bookmarkParametersMap;
			};
			
			Future<Map<String, List<EoffenderSqlParameter>>> bookmarkParametersMapFuture = taskExecutor.submit(bookMarkParamterCallable);
			Map<String, String> bookmarkSqlMap = bookmarkSqlMapFuture.get();
			Map<String, List<EoffenderSqlParameter>> bookmarkParametersMap = bookmarkParametersMapFuture.get();
			
			
			try {
				latch.await();
			} catch (InterruptedException e) {
				logger.error("getBookmarkResolvedSqlMap bookmarkParameterMap/ bookmarkSqlMap  Mapping faild: " + e
						+ ", offenderBookingNo " + manageDocumentRequest.getDocDetails().getOffenderBookingNo()
						+ ", offenderIdDisplay "
						+ manageDocumentRequest.getDocDetails().getOffenderIdDisplay());
			}

			bookmarkResolvedSqlMap =new  DocManageUtilities().getbookmarkResolvedSqlMap(bookmarkSqlMap, bookmarkParametersMap,
					manageDocumentRequest,objectDataMap);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			logger.error("getBookmarkResolvedSqlMap bookmark parameter Mapping faild: "
					+ ", offenderBookingNo : "
					+ manageDocumentRequest.getDocDetails().getOffenderBookingNo()
					+ ", offenderIdDisplay : "
					+ manageDocumentRequest.getDocDetails().getOffenderIdDisplay());
		}

		return bookmarkResolvedSqlMap;
	}
	
	public String updateDocumentStatus(String documentID,String status,String fileName,String user) {
		String result = "";
		try {
			result = documentRepository.updateDocStatus(new BigDecimal(documentID), status,fileName,user);
		} catch (Exception e) {
			logger.error("error in updateDocumentStatus"+e.getMessage());
		}
		return result;
	}
	
	@Override
	public String saveFileFromWatcherToDB(byte[] fileByteArray, String key, String fileName) throws Exception {
		return documentRepository.insertFileByteArrayFromWatcher(fileByteArray, key, fileName);
	}

	@Override
	public BigDecimal getTempDocId() {
		return documentRepository.getTempDocId();
	}
	
	@Override
	public VHeaderBlock getOffenderDetails(Long offenderBookId,String userName) {
		return documentRepository.getOffenderDetails(offenderBookId,userName);
	}
	@Override
	public Long getDocId() {
		return documentRepository.getDocId();
	}
	@Override
	public BigDecimal uploadDocument(List<IwpDocuments> iwpDocuments, String pathToDirectory, String userName) {
		for (IwpDocuments iwpDocument : iwpDocuments) {
			File downloadedFile;
			try {
				downloadedFile = saveFileToTempLoc(pathToDirectory, iwpDocument.getDocumentContent(), iwpDocument.getOffenderBookId()+"", iwpDocument.getFileExtension());
				String newFilePath = downloadedFile.getAbsolutePath().replace(iwpDocument.getFileExtension(), ".zip");
				byte[] copressedContent = compress(downloadedFile.getAbsolutePath(), newFilePath);
				iwpDocument.setDocumentContent(copressedContent);
				if (iwpDocument.getDocumentId() == null) {
					iwpDocument.setCreateUserId(userName);
				} else {
					iwpDocument.setModifyUserId(userName);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return documentRepository.uploadDoc(iwpDocuments);
	}
	@Override
	public int insertSignedDocument(byte[] fileByteArray, BigDecimal documenId, String modifyUser,String fileName) {
		Date date = new Date(System.currentTimeMillis());
		return documentRepository.insertSignedDoc(fileByteArray, documenId, date, modifyUser,fileName);
	}

	@Override
	public List<StaffMemberRoles> getStaffEliteDocDeleteRoles(String userId) {
		return documentRepository.getStaffEliteDocDeleteRoles(userId);
	}

	@Override
	public Integer deleteEliteDoc(IwpDocuments iwpDocuments) {
		return documentRepository.deleteEliteDoc(iwpDocuments);
	}

}
