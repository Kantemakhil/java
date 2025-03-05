package net.syscon.s4.iwp.eoffender;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageProperties;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import com.itextpdf.text.DocumentException;

import net.syscon.s4.common.CustomMultipartFile;
import net.syscon.s4.common.DocManageUtilities;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.eoffender.beans.DBDocument;
import net.syscon.s4.eoffender.beans.Document;
import net.syscon.s4.eoffender.beans.DocumentRequestBean;
import net.syscon.s4.eoffender.beans.DownloadedFile;
import net.syscon.s4.eoffender.beans.EoffenderDetails;
import net.syscon.s4.eoffender.beans.EoffenderDocumentRequest;
import net.syscon.s4.eoffender.beans.EoffenderSqlParameter;
import net.syscon.s4.eoffender.beans.EoffenderTemplate;
import net.syscon.s4.eoffender.beans.FileLimits;
import net.syscon.s4.im.beans.IwpDocumentsCommitBean;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.iwp.base.ManageDocService;
import net.syscon.s4.iwp.base.TemplateRepository;
import net.syscon.s4.iwp.beans.WordContentReplacer;

@EliteController
public class EoffenderDocController  {

	private static Logger logger = LogManager.getLogger(EoffenderDocController.class.getName());
	private static final String DOCUMENT_RECORD_TYPE = "2017";
	private static final String FILE_UPLOAD_FAILED= "UPLOAD FAILED";
	
	private Map<String, Long> filesUploadedTimeMap = new HashMap<String, Long>();
	private Map<String, Long> filesOpenTimeMap = new HashMap<String, Long>();
	private Map<String, Long> filesViewTimeMap = new HashMap<String, Long>(); 

	@Autowired
	private EoffenderDocService eoffenderDocumentService;

	
	@Autowired
	private ManageDocService manageDocumentService;

	@Autowired
	private DocManageUtilities eoffenderUtilities;

	@Qualifier("trim")
	@Autowired
	private DMSService dmsService;

	@Autowired
	private TemplateRepository templateRepository;

	@Autowired
	private Environment env;
	
	@Autowired
	private EoffenderService eoffenderService;

	private String saveDir = null;
    
//	@Autowired
	private License license;
	
//	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	private EoffenderDocHelper docHelper;
	
	private void addHttpRequestImpersonationHeader (HttpRequest request, String trimUser) {
		request.setHeader("userToImpersonate",trimUser);
		return;
	}

	//private List<EoffenderTemplate> docTemplateList;

	@PostMapping(value = "/eoffender/document/download", produces = MediaType.ALL_VALUE)
	public ResponseEntity downloadDocument(@RequestBody EoffenderDocumentRequest eoffenderDocumentRequest,
			HttpServletRequest httpServletRequest, @QueryParam("dialogLink") String dialogLink) throws Exception {
		HttpStatus httpStatus = HttpStatus.OK;
		ResponseEntity repsonseEntity = null;
		HttpHeaders headerRes = new HttpHeaders();
		String offIdDisplay = eoffenderDocumentRequest.getEoffenderDetails().getOffenderIdDisplay();
		String offBookingNo = eoffenderDocumentRequest.getEoffenderDetails().getOffenderBookingNo();
		// DOWNLOAD FILE TO RESOURCES FOLDER FROM TRIM
		logger.info("downloadDocument templateName : " + eoffenderDocumentRequest.getTemplateName()
				+ ", offenderBookingNo " + offBookingNo + ", offenderIdDisplay " + offIdDisplay);

		try {
			if (eoffenderDocumentRequest.getTemplateUri() == null
					|| DocManageUtilities.isEmpty(eoffenderDocumentRequest.getTemplateUri())) {
				httpStatus = HttpStatus.NO_CONTENT;
				headerRes.setPragma("Template not found");
				repsonseEntity = new ResponseEntity<>(null, headerRes, httpStatus);
				logger.error("downloadDocument TemplateUri is null : offenderBookingNo " + offBookingNo
						+ ", offenderIdDisplay " + offIdDisplay);
				return repsonseEntity;

			}

			DownloadedFile downloadedFile = eoffenderDocumentService.downLoadFileFromTRIM(
					eoffenderDocumentRequest.getTemplateUri(),
					eoffenderDocumentRequest.getEoffenderDetails().getTrimUser());

			if (DocManageUtilities.TEMPLATE_NOT_FOUND.equals(downloadedFile.getStatus())
					|| DocManageUtilities.TEMPLATE_NOT_SUPPORTED.equals(downloadedFile.getStatus())) {
				httpStatus = HttpStatus.NO_CONTENT;
				// HttpHeaders headerRes = new HttpHeaders();
				headerRes.setPragma("Template not found");
				repsonseEntity = new ResponseEntity<>(null, headerRes, httpStatus);
				logger.error("downloadDocument Error : " + downloadedFile.getStatus() + ", offenderBookingNo "
						+ offBookingNo + ", offenderIdDisplay " + offIdDisplay);
				return repsonseEntity;
			}
			String trimTemplateExt = DocManageUtilities.documentMimeTypeMap.get(downloadedFile.getFileType());

			if (DocManageUtilities.DOT.equalsIgnoreCase(trimTemplateExt) && !EoffenderDocHelper.isAsposeLicenseValid(license)) {
				logger.error("downloadDocument Invalid Licence  :: " + downloadedFile.getStatus()
						+ ", offenderBookingNo " + offBookingNo + ", offenderIdDisplay " + offIdDisplay);
				return repsonseEntity;
			}

			File trimFile = saveFileToTempLoc(httpServletRequest, downloadedFile.getFileByteArray(),
					eoffenderDocumentRequest.getEoffenderDetails().getOffenderBookId().toString(), trimTemplateExt);

			// RESOLVE BOOKMARKS
			//List<String> bookmarkList = eoffenderDocumentService.getBookmarkList(eoffenderDocumentRequest);
			List<String> bookmarkList = null;
			logger.info("downloadDocument bookmarkList : " + bookmarkList + ", offenderBookingNo " + offBookingNo
					+ ", offenderIdDisplay " + offIdDisplay);

			if (bookmarkList == null || bookmarkList.isEmpty()) {
				logger.error("downloadDocument bookmarkList is Empty : " + bookmarkList + ", offenderBookingNo "
						+ offBookingNo + ", offenderIdDisplay " + offIdDisplay);
				httpStatus = HttpStatus.OK;
				repsonseEntity = new ResponseEntity<>(null, null, httpStatus);
				return repsonseEntity;
			}

			Path destinationPath = null;

			try {
				destinationPath = convertDocumentTypeToDocx(eoffenderDocumentRequest, trimFile, trimTemplateExt, dialogLink); 
			} catch (Exception e) {
				logger.error("downloadDocument move operation failed: " + ", offenderBookingNo " + offBookingNo
						+ ", offenderIdDisplay " + offIdDisplay);
				repsonseEntity = new ResponseEntity<>(null, null, httpStatus);
				return repsonseEntity;
			}

			Map<String, String> bookmarkResolvedSqlMap = getBookmarkResolvedSqlMap(eoffenderDocumentRequest,
					bookmarkList);

			Map<String, List<String>> bookmarValueMap = eoffenderDocumentService.getBookmarkValueMap(bookmarkResolvedSqlMap, SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

			WordContentReplacer replacer = new WordContentReplacer();

			try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
					XWPFDocument document = replacer.Replace(saveDir, bookmarValueMap);) {

				if (document == null) {
					logger.error("downloadDocument could not resolve bookmarks : " + ", offenderBookingNo "
							+ offBookingNo + ", offenderIdDisplay " + offIdDisplay);
					repsonseEntity = new ResponseEntity<>(null, null, httpStatus);
				}

				document.write(baos);
				document.close();

				String mimeType = eoffenderUtilities.getMimeType(saveDir);

				Path newPath = Paths.get(saveDir);
				byte[] documentContent = baos.toByteArray();
				headerRes = eoffenderUtilities.getHttpHeadersForFileUpload(mimeType, newPath.getFileName().toString(),
						false);
				repsonseEntity = new ResponseEntity<>(documentContent, headerRes, HttpStatus.OK);
			}

			Files.deleteIfExists(destinationPath);
			Files.deleteIfExists(trimFile.toPath());
		} catch (Exception e) {
			headerRes.setPragma(e.getMessage());
			repsonseEntity = new ResponseEntity<>(null, headerRes, HttpStatus.INTERNAL_SERVER_ERROR);
			logger.error("downloadDocument faild: " + e + ", offenderBookingNo " + offBookingNo + ", offenderIdDisplay "
					+ offIdDisplay);

		}

		return repsonseEntity;
	}

	@GetMapping("/eoffender/getTemplates/{moduleName}")
	public List<EoffenderTemplate> getTemplates(@PathVariable("moduleName") String moduleName){
		String activeFlag = "Y";
		List<EoffenderTemplate> templateList = new ArrayList<>();
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();
		logger.info("getTemplates templateList: moduleName, " + moduleName+ ", userId " +userId);
		List<String> roleList = eoffenderDocumentService.getStaffRoleCodes(userId);
		try {
			templateList = eoffenderDocumentService.getTemplates(moduleName, roleList, activeFlag);
			//Map<String, String> templateUriMap = this.getTemplateIdUriMap(templateList,trimUser);
			
			/*for(EoffenderTemplate eoffenderTemplate: templateList) {
				eoffenderTemplate.setUri(templateUriMap.get(eoffenderTemplate.getTemplateId()));
			}*/
			logger.info("getTemplates templateList: moduleName, " + moduleName+ ", userId " +userId + ", "+ templateList);
			//docTemplateList = templateList;
		} catch (Exception e) {
			logger.error("getTemplates Cannot fetch templates : moduleName. " + moduleName+ ", userId " +userId);
		}

		return templateList;
	}
	
	@GetMapping("/eoffender/getAllTemplates/{moduleName}/{userId}/{trimUser}")
	public List<EoffenderTemplate> getAllTemplates(@PathVariable("moduleName") String moduleName, @PathVariable("userId") String userId,
			@PathVariable("trimUser") String trimUser){
		
		logger.info("getTemplates templateList: moduleName, " + moduleName+ ", userId " +userId);
		String activeFlag = "Y&N";
		List<EoffenderTemplate> templateList = new ArrayList<>();
		List<String> roleList = eoffenderDocumentService.getStaffRoleCodes(userId);
		try {
			templateList = eoffenderDocumentService.getTemplates(moduleName, roleList, activeFlag);
			Map<String, String> templateUriMap = this.getTemplateIdUriMap(templateList,trimUser);
			
			for(EoffenderTemplate eoffenderTemplate: templateList) {
				eoffenderTemplate.setUri(templateUriMap.get(eoffenderTemplate.getTemplateId()));
			}
			logger.info("getTemplates templateList: moduleName, " + moduleName+ ", userId " +userId + ", "+ templateList);
			//docTemplateList = templateList;
		} catch (Exception e) {
			logger.error("getTemplates Cannot fetch templates : moduleName. " + moduleName+ ", userId " +userId);
		}

		return templateList;
	}
	
	private Map<String, String> getTemplateIdUriMap(List<EoffenderTemplate> docTemplateList, String trimUser){		
		return dmsService.partitionedTemplateUriMap(docTemplateList, trimUser);		 
	}

	@PostMapping("/iwp/getDocumentName")
	public Document generateDocumentName(@RequestBody EoffenderDetails eoffenderDetails, @RequestParam("templateName") String templateName){
		logger.info("generateDocumentName Generating document name :  template " + templateName +  ", offenderBookingNo " 
				+  eoffenderDetails.getOffenderBookingNo() + 
				", offenderIdDisplay "+eoffenderDetails.getOffenderIdDisplay());
		
		Document document = new Document();
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		
		StringBuilder fileNameBuilder = new StringBuilder(); 
		try {
			manageDocumentService.getTempDocId();
			if(eoffenderDetails.getMiddleName()!=null)
			{
			fileNameBuilder.append(eoffenderDetails.getOffenderIdDisplay()).append(" ").append("-").append(" ").append(eoffenderDetails.getLastName()).append(",").
			append(" ").append(this.toProperCase(eoffenderDetails.getFirstName())).append(" ").append(this.toProperCase(eoffenderDetails.getMiddleName()))
			                   .append(" ").append("-").append(" ").append(dateformat.format(eoffenderDetails.getBirthDate())).append(" ").append("-").append(" ").append(templateName);
			}
			else {
				fileNameBuilder.append(eoffenderDetails.getOffenderIdDisplay()).append(" ").append("-").append(" ").append(eoffenderDetails.getLastName()).append(",").
				append(" ").append(this.toProperCase(eoffenderDetails.getFirstName())).append(" ").append("-").append(" ")
                .append(dateformat.format(eoffenderDetails.getBirthDate())).append(" ").append("-").append(" ").append(templateName);
			}
		} catch (Exception e) {
			logger.error("generateDocumentName Error Generating document name : " + e + " template " + templateName +  ", offenderBookingNo " 
					+  eoffenderDetails.getOffenderBookingNo() + 
					", offenderIdDisplay "+eoffenderDetails.getOffenderIdDisplay());
		}
		document.setDocumentName(fileNameBuilder.toString());
		document.setTemproaryDocumentId(manageDocumentService.getTempDocId().toString()); 
		return document;
	}
	public String  toProperCase(String documentName) {
	    return documentName.substring(0, 1).toUpperCase()+documentName.substring(1).toLowerCase();
	}
	@GetMapping(value="/tempDocId")
	public BigDecimal getTempDocId() {
		return manageDocumentService.getTempDocId();
	}

	@PostMapping(value = "/eoffender/upload")
	public Map<String, String> uploadDocument(@RequestParam("file") List<MultipartFile> fileList,
			@RequestParam("templateId") List<String> templateList,
			@RequestParam("documentUriList") List<String> documentUriList,
			@RequestParam("templateUriList") List<String> templateUriList,
			@RequestParam("trimUser") String trimUser,
			@RequestParam("offenderName") String offenderName,
			@RequestParam("dob") String dob,
			@RequestParam("bookingNo") String offenderBookingNo,
			@RequestParam("offenderIdDisplay") String offenderIdDisplay,
			@RequestParam("objectId") String oimsObjectId, 
			@RequestParam("objectType") String objectType,
			@RequestParam("recordCreator") String recordCreator,
			HttpServletRequest httpServletRequest,
			@RequestParam("fileName") List<String> updatedFileNames,
			String path)
			throws Exception {
		Map<String, String> uploadResult = new ConcurrentHashMap<>();
		
		logger.info("uploadDocument  documentUriList ::"+documentUriList+", templateUriList ::"+templateUriList+", templateList ::"+templateList+
				", trimUser ::"+trimUser+", offenderBookingNo ::"+offenderBookingNo+", offenderIdDisplay ::"+offenderIdDisplay+", oimsObjectId ::"+oimsObjectId+
				", objectType ::"+objectType+"recordCreator ::"+recordCreator+"updateFileName ::"+updatedFileNames);
		
		
		//Fetch LocationUri using logged-in user
		String locationUri = getLocationUri(recordCreator,trimUser);
		if(DocManageUtilities.isEmpty(locationUri)){
			uploadResult.put("All Files", "Cannot upload files with logged-in user");
			return uploadResult;
		}
		
		List<FileLimits> fileSizelimits = new ArrayList<>();
		fileSizelimits = getEoffenderProfileValues();
		
		String fileLimitCheck = DocManageUtilities.fileLimitCheck(fileSizelimits,fileList);	
		if(fileLimitCheck.equals("File size exceeds")){
			uploadResult.put(FILE_UPLOAD_FAILED,"Upload Size Limit is Exceeded");
			return uploadResult;
		}
		if(fileLimitCheck.equals("File count exceeds")){
			uploadResult.put(FILE_UPLOAD_FAILED,"Upload Count Limit is Exceeded");
			return uploadResult;
		}
		String offenderManagmentFileUri = dmsService.getURIOffenderManagmentFile(offenderIdDisplay,trimUser,offenderName,dob);
		
		logger.info("uploadDocument offenderManagmentFileUri : "+ offenderManagmentFileUri + ", offenderBookingNo "+ offenderBookingNo + ", offenderIdDisplay "+offenderIdDisplay);

		if (org.apache.commons.lang3.StringUtils.isBlank(offenderManagmentFileUri)) {
			logger.error("uploadDocument Unable to Retrive/Create offender Managment File Uri , offenderBookingNo "+ offenderBookingNo + ", offenderIdDisplay "+offenderIdDisplay);
			uploadResult.put(FILE_UPLOAD_FAILED,"Unable to Retrive/Create offender Managment File Uri");
			return uploadResult;
			//throw new Exception("Unable to Retrive/Create offender Managment File Uri");
			
		}

		
		int fileCount = fileList.size();
		//ExecutorService executor = Executors.newFixedThreadPool(fileCount);
		CountDownLatch latch = new CountDownLatch(fileCount);
		try {
			
			for (int i = 0; i < fileCount; i++) {
				MultipartFile file = fileList.get(i);
				String templateId = templateList.get(i);
				String fileExtension = eoffenderUtilities.getFileExtension(file.getOriginalFilename());
				String documentUri = documentUriList.get(i);
				String templateUri = templateUriList.get(i);
				String updatedFileName = updatedFileNames.get(i).replace("||", ",");
				Runnable runnableTask = () -> {
					try {
					//verify difference betwwen file.getoriginalFilenname and file getname 
						String fileName = StringUtils.cleanPath(file.getOriginalFilename());
						// Check if the file's name contains invalid characters
						if (fileName.contains("..")) {
							uploadResult.put(file.getOriginalFilename(),
									"UPLOAD FAILED :: " + "Filename contains invalid path sequence");
							logger.error("uploadDocument UPLOAD FAILED :: Filename contains invalid path sequence"
									+ ", offenderBookingNo "+ offenderBookingNo + ", offenderIdDisplay "+offenderIdDisplay);
						}
						// read template info from DB
						IwpTemplates iwpTemplate = templateRepository.getIWPTemplateByName(templateId);
						logger.info("uploadDocument iwpTemplate: " + iwpTemplate+ ", offenderBookingNo "+ offenderBookingNo + ", offenderIdDisplay "+offenderIdDisplay);
						String subFolderRecordNumber = "";
						String classificationUri = "";
						// Check if template id FOLDER
						 boolean isFolder = false;
						
						if ("FOLDER".equals(iwpTemplate.getObjectType())) {
							isFolder= true;
							Map<String, String> classificationUriMap = dmsService.getClassficationUriForFolder(iwpTemplate.getEdrmsFolder(),trimUser);
							
							//TODO  - Correct message and if block
							if(classificationUriMap.isEmpty()) {
								uploadResult.put(file.getOriginalFilename(),
										"Cant get Classification From TRIM for Given Folder " + iwpTemplate.getTemplateName());
								logger.info("uploadDocument Cant get Classification From TRIM for Given Folder:  ," + offenderBookingNo + ", offenderIdDisplay "+offenderIdDisplay);
							} else {
								classificationUri = classificationUriMap.entrySet().iterator().next().getKey();
							}
							// IN CASE OF FOLDER RETURN OBJECT CONTAIN
							// FILDER_RECORD_NUMBER/URI and CLASSIFICATION
							

						} else {
							
							// get classification from templateId
							String templateIdClassificationUri = dmsService
									.getClassficationFromTemlateId(iwpTemplate.getEdrmsRecordNo(),trimUser);
							classificationUri = templateIdClassificationUri;

							logger.info("uploadDocument template classification: " + classificationUri + ", offenderBookingNo "+ offenderBookingNo + ", offenderIdDisplay "+offenderIdDisplay);
							if (DocManageUtilities.isEmpty(classificationUri)) {

								// Get subfolderData map of Sub-Folder based on
								// Template classification
								uploadResult.put(file.getOriginalFilename(),
										"Cant get Classification From TRIM for Given DocumentType " + templateId);
								logger.info("uploadDocument Cant get Classification From TRIM for Given DocumentType: " + templateId + ", offenderBookingNo "+ offenderBookingNo + ", offenderIdDisplay "+offenderIdDisplay);

							} 
						}
							
							Map<String, String> subfolderData = dmsService
									.isSubfolderExist(offenderManagmentFileUri, classificationUri, false, trimUser);
							logger.info("uploadDocument subfolderData: " + subfolderData + ", offenderBookingNo "+ offenderBookingNo + ", offenderIdDisplay "+offenderIdDisplay);
							
							if (subfolderData.isEmpty()) {

								// IF Record Number of Sub-Folder based on
								// Template classification is not available
								// then create a new SUB-FOLDER based on
								// classification

								String offenderManagmentFileRecordNumber = dmsService
										.getRecordNumberForURI(offenderManagmentFileUri,trimUser);
								logger.info("uploadDocument offenderManagmentFileRecordNumber: " + offenderManagmentFileRecordNumber + ", offenderBookingNo "+ offenderBookingNo + ", offenderIdDisplay "+offenderIdDisplay);
								
								
								if(!DocManageUtilities.isEmpty(offenderManagmentFileRecordNumber)){
									
									String subFolderUri = dmsService.createSubFolder(offenderManagmentFileRecordNumber,
											offenderIdDisplay+"-"+offenderName+"-"+dob, classificationUri, offenderBookingNo ,offenderIdDisplay,trimUser);
									logger.info("uploadDocument subFolderUri: " + subFolderUri + ", offenderBookingNo "+ offenderBookingNo + ", offenderIdDisplay "+offenderIdDisplay);
									subFolderRecordNumber = dmsService.getRecordNumberForURI(subFolderUri,trimUser);
								}else{
									uploadResult.put(file.getOriginalFilename(),
											"Cant get offenderManagmentFileRecordNumber From TRIM for Given DocumentType "
													+ templateId);
								}
							} else {

								// IF Record Number of Sub-Folder based on
								// Template classification is not available
								// then create a new SUB-FOLDER based on
								// classification

								subFolderRecordNumber = subfolderData.get("subFolderRecordNumber");
							}

							logger.info("subFolderRecordNumber ==>" + subFolderRecordNumber);

						

						// Upload File to Trim when subFolderRecordNumber &
						// classificationUri both have valid value

						if (!DocManageUtilities.isEmpty(subFolderRecordNumber) && !DocManageUtilities.isEmpty(classificationUri)) {
							// File trimTempFile;
							File trimUpdatedFile = null;
							try {
								byte[] fileByteArray = IOUtils.toByteArray(file.getInputStream());

								// Save properties in docx and PDF file
								if (fileExtension.equals(DocManageUtilities.PDF) || fileExtension.equals(DocManageUtilities.DOC)){
									trimUpdatedFile = saveFileToTempLoc2(httpServletRequest!=null?httpServletRequest.getSession().getServletContext()
											.getRealPath("/WEB-INF/classes/TRIM/"):path, fileByteArray, "pdf",
											fileExtension);
								}				

								File trimTempFile = saveFileToTempLoc2(httpServletRequest!=null?httpServletRequest.getSession().getServletContext()
										.getRealPath("/WEB-INF/classes/TRIM/"):path, fileByteArray, offenderIdDisplay,
										fileExtension);

								Document document = new Document();
								document.setDocumentType(templateId);
								document.setUri(documentUri);
								document.setTemplateUri(templateUri);
								
								// Check if file is Empty
                                if(trimTempFile.length() == 0 && !DocManageUtilities.PDF.equals(fileExtension)){  
                                	// Add text in document using Aspose Words
                                	EoffenderDocHelper.insertTextInBlankFile(trimTempFile);
                                }
								
							    DocManageUtilities.addPropertiesInDocument(trimTempFile, document, fileExtension,
											trimUpdatedFile);
								
								String result;
								logger.info("uploadDocument documentUri: " + documentUri+ ", offenderBookingNo "+ offenderBookingNo + ", offenderIdDisplay "+offenderIdDisplay);

								File uploadFile = trimTempFile;

								if (fileExtension.equals(DocManageUtilities.PDF) ||  fileExtension.equals(DocManageUtilities.DOC)) {
									uploadFile = trimUpdatedFile;
								}

								// Save File to TRIM
								if (DocManageUtilities.isEmpty(documentUri) || "NEW_FILE".equals(documentUri)) {

									// Create new Document.
									result = dmsService.createDocument(DOCUMENT_RECORD_TYPE, subFolderRecordNumber,
											updatedFileName, classificationUri, uploadFile.getAbsolutePath(),
											templateUri, oimsObjectId, objectType, locationUri, offenderIdDisplay, offenderBookingNo,isFolder,trimUser);
									logger.info("uploadDocument createDocument: " + result+ ", offenderBookingNo "+ offenderBookingNo + ", offenderIdDisplay "+offenderIdDisplay);
								} else {
									// UPDATE EXISTING DOCUMENT

									// CHECK IF DOCUMENT IS CHECK-OUT STATUS

									if (dmsService.isCheckedOutStatus(documentUri,trimUser)) {

										result = dmsService.updateDocument(DOCUMENT_RECORD_TYPE, subFolderRecordNumber,
												fileName, classificationUri, uploadFile.getAbsolutePath(), documentUri, offenderIdDisplay, offenderBookingNo,trimUser
												 );
										logger.info("uploadDocument updateDocument: " + result+ ", offenderBookingNo "+ offenderBookingNo + ", offenderIdDisplay "+offenderIdDisplay);
										
									} else {
										result = "Can't Update Document ::  is not is Check-Out Status";
										logger.error("uploadDocument Can't Update Document ::  is not is Check-Out Status , offenderBookingNo "+ offenderBookingNo + ", offenderIdDisplay "+offenderIdDisplay);
									}

								}

								uploadResult.put(file.getOriginalFilename(), result);
							    Files.deleteIfExists(trimTempFile.toPath());
							    if(trimUpdatedFile != null){
							    	Files.deleteIfExists(trimUpdatedFile.toPath());	
							    }
							    

							} catch (IllegalStateException | DocumentException | InvalidFormatException e) {
								uploadResult.put(file.getOriginalFilename(),
										"Cant Create/update File :: " + e.getMessage());
								logger.error("uploadDocument Cant Create/update File :: " + e.getMessage()+ ", offenderBookingNo "+ offenderBookingNo + ", offenderIdDisplay "+offenderIdDisplay);
							}
						} else {

							if (uploadResult.get(file.getOriginalFilename()) != null) {
								uploadResult.put(file.getOriginalFilename(),
										uploadResult.get(file.getOriginalFilename())
												+ " , Cant get subFolderRecordNumber From TRIM for Given DocumentType "
												+ templateId);
								logger.error("uploadDocument Cant get subFolderRecordNumber From TRIM for Given DocumentType: " + file.getOriginalFilename() + ", offenderBookingNo "+ offenderBookingNo + ", offenderIdDisplay "+offenderIdDisplay);

							} else {
								uploadResult.put(file.getOriginalFilename(),
										"Cant get subFolderRecordNumber From TRIM for Given DocumentType "
												+ templateId);
								logger.error("uploadDocument Cant get subFolderRecordNumber From TRIM for Given DocumentType: " + file.getOriginalFilename()+ ", offenderBookingNo "+ offenderBookingNo + ", offenderIdDisplay "+offenderIdDisplay);
							}
                            
						}

					} catch (IOException e) {
						e.printStackTrace();

						uploadResult.put(file.getOriginalFilename(), "UPLOAD FAILED :: " + e.getMessage());
						logger.error("uploadDocument UPLOAD FAILED: " + e.getMessage() + ", offenderBookingNo "+ offenderBookingNo + ", offenderIdDisplay "+offenderIdDisplay);
					}

					latch.countDown();
				};
				taskExecutor.execute(runnableTask);
                
			}
            
			try {
				latch.await();
			} catch (InterruptedException e) {

			}
			
		} finally {
			//executor.shutdown();
		}
		return uploadResult;
	}

	@PostMapping(value = "/eoffender/document/downloadfromTRIM", produces = MediaType.ALL_VALUE)
	public ResponseEntity downloadDocumentFromTrim(HttpServletRequest httpServletRequest,
			@RequestBody Document documentMetaData) throws Exception {
		logger.info("downloadDocumentFromTrim Downloading document For Editing :  template " + documentMetaData.getTemplateId() +" offenderBookId "+ documentMetaData.getOffenderBookId());
		String fileDownloadUrl = env.getProperty("trim.url")
				+ "/Record/".concat(documentMetaData.getDocumentId()).concat("/File/document");
		HttpClientBuilder clientHelper = HttpClientBuilder.create();
		
		CredentialsProvider creds = new BasicCredentialsProvider();
	    creds.setCredentials(AuthScope.ANY, new NTCredentials(env.getProperty("trim.apiUser"), env.getProperty("trim.apiPwd"), "", env.getProperty("trim.dev")));
	    clientHelper.setDefaultCredentialsProvider(creds);
	    
	    RequestConfig config = RequestConfig.custom().setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM)).build();
	    HttpClient httpClient = clientHelper.setDefaultRequestConfig(config).build();
              
        HttpGet get = new HttpGet(fileDownloadUrl);

        get.setHeader("Content-type", "application/x-www-form-urlencoded");
        get.setHeader("Cache-Control", "no-cache");
        this.addHttpRequestImpersonationHeader(get, documentMetaData.getTrimUser());
		org.apache.http.HttpResponse response = httpClient.execute(get);
		int httpStatus = response.getStatusLine().getStatusCode();
		
		logger.info("downloadDocumentFromTrim response  : "+response +", offenderBookId "+ documentMetaData.getOffenderBookId());
		logger.info("downloadDocumentFromTrim response status : "+httpStatus +", offenderBookId "+ documentMetaData.getOffenderBookId());
		
		if(httpStatus == DocManageUtilities.STATUS_SUCCESS){
			HttpEntity entity = response.getEntity();
			
			InputStream inputStream = entity.getContent();
			byte[] byteArray = IOUtils.toByteArray(inputStream);
			
			if(inputStream!=null){
				inputStream.close();
			}
			String[] contentDisposition = response.getFirstHeader("content-disposition").getValue().split(";");
			String fileExt = eoffenderUtilities.getExtensionFromContentDisposition(contentDisposition[1]);
			logger.info("downloadfromTRIM file extension: "+ fileExt+" offenderBookId "+ documentMetaData.getOffenderBookId());
		
			if (DocManageUtilities.isEmpty(fileExt) || DocManageUtilities.DOTX.equals(fileExt)) {
				 HttpHeaders headerRes = new HttpHeaders();
				 headerRes.setPragma("No Content Found");
				return new ResponseEntity<>(null, headerRes , HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
			File trimEditFile = saveFileToTempLoc(httpServletRequest, byteArray, "", fileExt);
			FileInputStream trimFileInputStream = null;
			File trimEditFileToUpdate = null;
			try {			
				trimEditFileToUpdate = saveFileToTempLoc(httpServletRequest, byteArray, "", fileExt);
				//save it in db.
				if(!documentMetaData.isCheckOut()) {
					documentMetaData.setTemproaryDocumentId(manageDocumentService.getTempDocId().toString());
					documentMetaData.setFileExt(fileExt);
					eoffenderDocumentService.insertEditedFile(documentMetaData);
				}
				DocManageUtilities.addPropertiesInDocument(trimEditFile, documentMetaData, fileExt, trimEditFileToUpdate);
				if(fileExt.equals(DocManageUtilities.DOCX)){
					trimFileInputStream = new FileInputStream(trimEditFile);
				} else if(fileExt.equals(DocManageUtilities.PDF) || fileExt.equals(DocManageUtilities.DOC)){
					trimFileInputStream = new FileInputStream(trimEditFileToUpdate);
				}
				HttpHeaders headers = eoffenderUtilities.getHttpHeadersForFileUpload(fileExt, documentMetaData.getDocumentName(), true);
				ResponseEntity repsonseEntity = new ResponseEntity<>(IOUtils.toByteArray(trimFileInputStream), headers, HttpStatus.OK);
				
				// Change status as 'Checked-Out' in TRIM for Document
				String checkedOutDocumentMsg = dmsService.checkedOutDocument(documentMetaData.getUri(),documentMetaData.getTrimUser());

				logger.info("downloadfromTRIM status checkedOutDocument: " + checkedOutDocumentMsg + " offenderBookId "+ documentMetaData.getOffenderBookId());

				if (!"CHECK-OUT SUCCESS".equals(checkedOutDocumentMsg)) {
					 HttpHeaders headerRes = new HttpHeaders();
					 headerRes.setPragma(checkedOutDocumentMsg);
					repsonseEntity = new ResponseEntity<>(null,headerRes, HttpStatus.INTERNAL_SERVER_ERROR);
				}
				return repsonseEntity;

			} catch (Exception e) {
				logger.error("downloadfromTRIM Unable to download file from TRIM : " + e + " offenderBookId "
						+ documentMetaData.getOffenderBookId());
				 HttpHeaders headerRes = new HttpHeaders();
				 headerRes.setPragma(e.getMessage());
				return new ResponseEntity<>(null, headerRes, HttpStatus.INTERNAL_SERVER_ERROR);
			} finally {
				if(trimFileInputStream!=null){
		            	trimFileInputStream.close();
		        }
				Files.deleteIfExists(trimEditFileToUpdate.toPath());
				Files.deleteIfExists(trimEditFile.toPath());         	
			}
		}else{
			 HttpHeaders headerRes = new HttpHeaders();
			 headerRes.setPragma("Unable to download document from trim");
			 logger.error("downloadfromTRIM Unable to download file from TRIM : " +" offenderBookId "
						+ documentMetaData.getOffenderBookId());
			return new ResponseEntity<>(null, headerRes, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void saveFileInDB(Document document, byte[] byteArray) {
		try {
			eoffenderDocumentService.saveFileToDB(document, byteArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping(value = "/eoffender/document/viewfile", produces = MediaType.ALL_VALUE)
	public ResponseEntity viewTRIMFile(@RequestParam("uri") String documentUri, @RequestParam("trimUser") String trimUser) throws Exception {
		ResponseEntity repsonseEntity = null;
		HttpHeaders headerRes = new HttpHeaders();
		logger.info("viewTRIMFile from documentLink: " +documentUri +" trimUser :: "+trimUser);
		org.apache.http.HttpResponse response = dmsService.viewTRIMFile(documentUri, trimUser);
		int httpStatus = response.getStatusLine().getStatusCode();
		
		logger.info("viewTRIMFile response  : "+response +", URI "+ documentUri);
		logger.info("viewTRIMFile response status : "+httpStatus +", URI "+ documentUri);
		
		if(httpStatus == DocManageUtilities.STATUS_SUCCESS){
			HttpEntity entity = response.getEntity();
			InputStream inputStream = entity.getContent();
			byte[] byteArray = IOUtils.toByteArray(inputStream);
			
			String[] contentDisposition = response.getFirstHeader("content-disposition").getValue().split(";");
			String fileName = DocManageUtilities.getFileNameFromContentDisposition(contentDisposition[1]);
			String fileExt = eoffenderUtilities.getExtensionFromContentDisposition(contentDisposition[1]);
			if (DocManageUtilities.isEmpty(fileExt) ) {
				logger.error("viewTRIMFile No Content Found : URI "+ documentUri );
				headerRes.setPragma("downloadfromTRIM2 No Content Found : URI "+ documentUri);
				return new ResponseEntity<>(null, headerRes, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			headerRes = eoffenderUtilities.getHttpHeadersForFileUpload(fileExt,
					fileName, true);
			repsonseEntity = new ResponseEntity<>(byteArray, headerRes, HttpStatus.OK);

			return repsonseEntity;
		}else{
			headerRes.setPragma("Unable to download document from trim");
			repsonseEntity = new ResponseEntity<>(null, headerRes, HttpStatus.INTERNAL_SERVER_ERROR);
			return repsonseEntity;
		}
	}


	@PostMapping(value = "/eoffender/document/getPropertiesFromFile")
	public Document getPropertiesFromFile(@RequestParam("file") MultipartFile multipartFile,
			@RequestParam("fileType") String fileType, HttpServletRequest httpServletRequest) throws Exception {
		logger.info("getPropertiesFromFile Fetching Properties from document: " +fileType);
		
		Document document = null;

		String fileExt;

		if (fileType.contains(DocManageUtilities.DOCX)) {
			fileExt = DocManageUtilities.DOCX;

		} else if (fileType.contains(DocManageUtilities.PDF)) {
			fileExt = DocManageUtilities.PDF;

		} else {
			fileExt = DocManageUtilities.documentMimeTypeMap.get(fileType);
		}

		byte[] fileByteArray = IOUtils.toByteArray(multipartFile.getInputStream());

		File trimTempFile = saveFileToTempLoc(httpServletRequest, fileByteArray, "", fileExt);
		document = eoffenderDocumentService.getPropertiesFromFile(trimTempFile.getAbsolutePath(), fileExt);
		Files.deleteIfExists(trimTempFile.toPath());

		return document;
	}


	@PostMapping(value = "/eoffender/document/getPropertiesFromFiles")
	public List<Document> getPropertiesFromMultipleFile(@RequestParam("file") List<MultipartFile> multipartFileList,
			@RequestParam("fileType") String[] fileType, HttpServletRequest httpServletRequest) throws Exception {
		
		logger.info("getPropertiesFromFiles Fetching Properties from document: " +fileType.toString());
		List<Document> documentListWithProperties = new ArrayList<>();
		ExecutorService executor = Executors.newFixedThreadPool(multipartFileList.size());
		CountDownLatch latch = new CountDownLatch(multipartFileList.size());

		Map<MultipartFile,String> multipartFileWithNameMap = new LinkedHashMap<>();

		for(int i =0 ; i< multipartFileList.size(); i++){
			multipartFileWithNameMap.put(multipartFileList.get(i), fileType[i]);
		}	
		List<Future<Document>> futureList = new ArrayList<>();
		List<Callable<Document>> taskList = new ArrayList<>();
		for(MultipartFile multipartFile : multipartFileWithNameMap.keySet()){

			Callable<Document> getpropertiesFromFileCallable = () -> {
				String fileExt;
				Document document = null;
				logger.info("getpropertiesFromFileCallable execution started ");
				fileExt = eoffenderUtilities.getFileExtension(multipartFileWithNameMap.get(multipartFile));
				logger.info("getpropertiesFromFileCallable execution started 1 "+fileExt);
				byte[] fileByteArray = IOUtils.toByteArray(multipartFile.getInputStream());
				logger.info("getpropertiesFromFileCallable execution started 2 "+fileByteArray);
				File trimTempFile = saveFileToTempLoc(httpServletRequest, fileByteArray, "", fileExt);
				logger.info("getpropertiesFromFileCallable execution started 3 "+trimTempFile);
				document = eoffenderDocumentService.getPropertiesFromFile(trimTempFile.getAbsolutePath(), fileExt);
				
				logger.info("getpropertiesFromFileCallable execution started 4 "+document);
				Files.deleteIfExists(trimTempFile.toPath());
				latch.countDown();
				return document;
			};
			
			
			taskList.add(getpropertiesFromFileCallable);
			//taskExecutor.submit(getpropertiesFromFileCallable);
			futureList.add(taskExecutor.submit(getpropertiesFromFileCallable));
			//futureList.add(documentFuture);
			//Document document = documentFuture.get();
			//documentListWithProperties.add(document);

		}
		
		//futureList = executor.invokeAll(taskList);
		executor.shutdown();
		
		for (int i = 0; i < futureList.size(); i++) {
            Future<Document> future = futureList.get(i);
            try {
            	logger.info("Future get started ================");
            	Document document = future.get();
            	logger.info("Future get End ================"+document);
            	documentListWithProperties.add(document);
            } catch (InterruptedException | ExecutionException e) {
                
            }
        }

		try {
			latch.await();
		} catch (InterruptedException e) {
				logger.error(e.getLocalizedMessage());
		}

		return documentListWithProperties;
	}
public List<Document> getPropertiesFromMultipleFile2(@RequestParam("file") List<MultipartFile> multipartFileList,
			@RequestParam("fileType") String[] fileType, String path) throws Exception {
		logger.info("getPropertiesFromFiles Fetching Properties from document: " +fileType.toString());
		List<Document> documentListWithProperties = new ArrayList<>();
		

		Map<MultipartFile,String> multipartFileWithNameMap = new LinkedHashMap<>();

		for(int i =0 ; i< multipartFileList.size(); i++){
			multipartFileWithNameMap.put(multipartFileList.get(i), fileType[i]);
		}	
		
		for (MultipartFile multipartFile : multipartFileWithNameMap.keySet()) {

			String fileExt;
			Document document = null;

			fileExt = eoffenderUtilities.getFileExtension(multipartFileWithNameMap.get(multipartFile));
			byte[] fileByteArray = IOUtils.toByteArray(multipartFile.getInputStream());

			File trimTempFile = saveFileToTempLoc2(path, fileByteArray, "", fileExt);

			document = eoffenderDocumentService.getPropertiesFromFile(trimTempFile.getAbsolutePath(), fileExt);
			Files.deleteIfExists(trimTempFile.toPath());

			documentListWithProperties.add(document);

		}
	return documentListWithProperties;
	}
public Document getPropertiesFromFile(MultipartFile multipartFile,
			String fileType, String path) throws Exception {
		
		logger.info("getPropertiesFromFiles Fetching Properties from document: " +fileType.toString());
		Document document = new Document();
		
		String fileExt = fileType;
		byte[] fileByteArray = IOUtils.toByteArray(multipartFile.getInputStream());

		File trimTempFile = saveFileToTempLoc2(path, fileByteArray, "", fileExt);

		document = eoffenderDocumentService.getPropertiesFromFile(trimTempFile.getAbsolutePath(), fileExt);
		Files.deleteIfExists(trimTempFile.toPath());

		return document;
	}

	//cancel document
	@GetMapping("/eoffender/document/cancel")
	public String cancelDocument(@RequestParam("documentUri") String documentUri, @RequestParam("trimUser") String trimUser) {
		logger.info("cancelDocument Check-out of Document with URI " + documentUri,trimUser);
		//Update status as Checked-In
		return dmsService.cancelDocument(documentUri,trimUser);
	}

	//final document
	@GetMapping("/eoffender/document/final")
	public String finalDocument(@RequestParam("recordNumber") String recordNumber,@RequestParam("trimUser") String trimUser) {
		return dmsService.finalisedDocument(recordNumber,trimUser);
	}


	//list all documents
	@PostMapping("/eoffender/document/list")
	public List<Document> listDocuments(@RequestBody DocumentRequestBean documentRequestBean) throws IOException {
		logger.info("listDocuments fetching documents : ", documentRequestBean);
		
		List<Document> docList = dmsService.listDocuments(documentRequestBean);

		return docList;

	}
	
	@GetMapping("/eoffender/location")
	public String getLocationUri(@RequestParam("location") String locaton , String TrimUser) {
		logger.info("getLocationUri Fetching location for Author : " + locaton);
		//Update status as Checked-In
		return dmsService.getLocationUri(locaton,TrimUser);
	}
	
	@GetMapping("/eoffender/editedDocCheckInVerification")
	public String verifyEditedDocCheckedIn(@RequestParam("docId") String docId) {
		String response = "";
				
		logger.info("chekEditedDocCheckedIn Fetching location for Author : " );
		//Update status as Checked-In
		try {
			response=eoffenderDocumentService.verifyEditedDocCheckedIn(docId);
		} catch (Exception e) {
			logger.error("chekEditedDocCheckedIn Fetching location for Author : " + e.getMessage() );
		}
		
		logger.info(" Controller Response is :" + response);
		return response;
	}
	
	@GetMapping("/iwp/generatedDocVerification")
	public String generatedDocVerification(@RequestParam("docId") String docId) {
		String response = "";
				
		logger.info("chekEditedDocCheckedIn Fetching location for Author : " );
		//Update status as Checked-In
		try {
			response=eoffenderDocumentService.verifyGeneratedDocVerification(docId);
		} catch (Exception e) {
			logger.error("chekEditedDocCheckedIn Fetching location for Author : " + e.getMessage() );
		}
		
		logger.info(" Controller Response is :" + response);
		return response;
	}
	
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/eoffender/getEoffenderProfileValues", method = RequestMethod.GET)
	public  List<FileLimits> getEoffenderProfileValues() {
		List<FileLimits> returnObj = null ;
		try {
			returnObj = eoffenderService.getEoffenderProfileValues();
		} catch (Exception e) {
			logger.error("getPreInsertAgyLocation", e);
		}
		return returnObj;
	}
	
	private File saveFileToTempLoc(HttpServletRequest httpServletRequest, byte[] templateByteArray,
			String offenderIdDisplay, String fileType) throws IllegalStateException, IOException {
		Calendar cal = Calendar.getInstance();

		String pathToDirectory = httpServletRequest.getSession().getServletContext()
				.getRealPath("/WEB-INF/classes/TRIM/");

		logger.info(pathToDirectory);
		File directoryPath = new File(pathToDirectory);
		if (!directoryPath.exists())
			directoryPath.mkdirs();

		saveDir = pathToDirectory.concat(offenderIdDisplay + "_").concat(cal.getTimeInMillis() + "").concat(fileType);

		File trimFile = new File(saveDir);

		CustomMultipartFile customMultipartFile = new CustomMultipartFile(templateByteArray);
		customMultipartFile.transferTo(trimFile);

		return trimFile;
	}
	
	private File saveFileToTempLoc2(String path, byte[] templateByteArray,
			String offenderIdDisplay, String fileType) throws IllegalStateException, IOException {
		Calendar cal = Calendar.getInstance();
		
		String pathToDirectory = path;

		File directoryPath = new File(pathToDirectory);
		if (!directoryPath.exists())
			directoryPath.mkdirs();

		saveDir = pathToDirectory.concat(offenderIdDisplay + "_").concat(cal.getTimeInMillis() + "").concat(fileType);

		File trimFile = new File(saveDir);

		CustomMultipartFile customMultipartFile = new CustomMultipartFile(templateByteArray);
		customMultipartFile.transferTo(trimFile);

		return trimFile;
	}
	private Path convertDocumentTypeToDocx(EoffenderDocumentRequest eoffenderDocumentRequest, File trimFile, String trimFileExt, String dialogLink) throws Exception{
		Path destinationPath = null;
        
		com.aspose.words.Document asposeDocument = new com.aspose.words.Document(saveDir);
		// Convert file to docx format
		String newFilePath = saveDir.replace(trimFileExt, DocManageUtilities.DOCX);
		asposeDocument.save(newFilePath, SaveFormat.DOCX);
		destinationPath = Paths.get(newFilePath);
		saveDir = newFilePath;

		try (OPCPackage opc = OPCPackage.open(saveDir);) {
			PackageProperties pp = opc.getPackageProperties();
			pp.setCategoryProperty("NEW_FILE" + "-" + eoffenderDocumentRequest.getTemplateUri() + "-" + "NEW_RECORD"
					+ "-" + eoffenderDocumentRequest.getTemplateType()+ "-" + dialogLink);
		}
		
		return destinationPath;
	}
	
	private Map<String, String> getBookmarkResolvedSqlMap(EoffenderDocumentRequest eoffenderDocumentRequest,
			List<String> bookmarkList) {
		Map<String, String> bookmarkResolvedSqlMap = null;
		try {
			CountDownLatch latch = new CountDownLatch(2);

			Callable<Map<String, String>> bookMarkSqlMappingCallable = () -> {
				Map<String, String> bookmarkSqlMap = null;
				try {
					bookmarkSqlMap = eoffenderDocumentService.getBookmarkSqlMapping(bookmarkList);

					logger.info("getBookmarkResolvedSqlMap bookmarkSqlMap: " + bookmarkSqlMap + ", offenderBookingNo "
							+ eoffenderDocumentRequest.getEoffenderDetails().getOffenderBookingNo()
							+ ", offenderIdDisplay "
							+ eoffenderDocumentRequest.getEoffenderDetails().getOffenderIdDisplay());

				} catch (Exception e) {
					logger.error("getBookmarkResolvedSqlMap bookmarkSqlMapping faild: " + e + ", offenderBookingNo "
							+ eoffenderDocumentRequest.getEoffenderDetails().getOffenderBookingNo()
							+ ", offenderIdDisplay "
							+ eoffenderDocumentRequest.getEoffenderDetails().getOffenderIdDisplay());
				}

				latch.countDown();
				return bookmarkSqlMap;
			};
			
			Future<Map<String, String>> bookmarkSqlMapFuture = taskExecutor.submit(bookMarkSqlMappingCallable);

			Callable<Map<String, List<EoffenderSqlParameter>>> bookMarkParamterCallable = () -> {
				Map<String, List<EoffenderSqlParameter>> bookmarkParametersMap = null;
				try {
					bookmarkParametersMap = eoffenderDocumentService.getBookmarkParameterMapping(bookmarkList);
					logger.info("getBookmarkResolvedSqlMap bookmark parameter: " + bookmarkParametersMap + ", offenderBookingNo "
							+ eoffenderDocumentRequest.getEoffenderDetails().getOffenderBookingNo()
							+ ", offenderIdDisplay "
							+ eoffenderDocumentRequest.getEoffenderDetails().getOffenderIdDisplay());
				} catch (Exception e) {
					logger.error("getBookmarkResolvedSqlMap bookmark parameter Mapping faild: " + bookmarkParametersMap
							+ ", offenderBookingNo "
							+ eoffenderDocumentRequest.getEoffenderDetails().getOffenderBookingNo()
							+ ", offenderIdDisplay "
							+ eoffenderDocumentRequest.getEoffenderDetails().getOffenderIdDisplay());
				}

				latch.countDown();
				return bookmarkParametersMap;
			};
			
			Future<Map<String, List<EoffenderSqlParameter>>> bookmarkParametersMapFuture = taskExecutor
					.submit(bookMarkParamterCallable);
			Map<String, String> bookmarkSqlMap = bookmarkSqlMapFuture.get();
			Map<String, List<EoffenderSqlParameter>> bookmarkParametersMap = bookmarkParametersMapFuture.get();
			try {
				latch.await();
			} catch (InterruptedException e) {
				logger.error("getBookmarkResolvedSqlMap bookmarkParameterMap/ bookmarkSqlMap  Mapping faild: " + e
						+ ", offenderBookingNo " + eoffenderDocumentRequest.getEoffenderDetails().getOffenderBookingNo()
						+ ", offenderIdDisplay "
						+ eoffenderDocumentRequest.getEoffenderDetails().getOffenderIdDisplay());
			}

			/*bookmarkResolvedSqlMap = eoffenderUtilities.getbookmarkResolvedSqlMap(bookmarkSqlMap, bookmarkParametersMap,
					eoffenderDocumentRequest);*/
		} catch (InterruptedException | ExecutionException e) {
			logger.error("getBookmarkResolvedSqlMap bookmark parameter Mapping faild: "
					+ ", offenderBookingNo : "
					+ eoffenderDocumentRequest.getEoffenderDetails().getOffenderBookingNo()
					+ ", offenderIdDisplay : "
					+ eoffenderDocumentRequest.getEoffenderDetails().getOffenderIdDisplay());
		}

		return bookmarkResolvedSqlMap;
	}

	
	@PostMapping(value = "/eoffender/document/uploadFromDialog", produces = MediaType.ALL_VALUE)
	public Map<String, String> uploadDocumentFromDialog(HttpServletRequest httpServletRequest, @RequestBody String key/*, @RequestBody EoffenderDocumentRequest eoffenderDocumentRequest*/) throws Exception {
        DBDocument dbDocument = eoffenderDocumentService.getTrimDocumentAsBlobFromDB(key);
        
        File dbFile = saveFileToTempLoc(httpServletRequest, dbDocument.getFileData(), "", DocManageUtilities.DOCX);
        
        FileInputStream fileInputStream = new FileInputStream(dbFile);
        
		MultipartFile multipartFile = new MockMultipartFile(dbDocument.getDocumentName(), fileInputStream);
		List<MultipartFile> fileList = new ArrayList<>();
		fileList.add(multipartFile);

		// POST getProperties API
		Document document = getPropertiesFromFile(fileList.get(0), DocManageUtilities.DOCX , httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/TRIM/"));

		List<String> templateList = new ArrayList<>();
		templateList.add(document.getDocumentType());

		List<String> documentUriList = new ArrayList<>();
		documentUriList.add(document.getUri());

		List<String> templateUriList = new ArrayList<>();
		templateUriList.add(document.getTemplateUri());
		
		List<String> updatedFileNames = new ArrayList<>();
		updatedFileNames.add(dbDocument.getDocumentName());

		
		Map<String, String> result = uploadDocument(fileList, templateList, documentUriList, templateUriList,
				/*eoffenderDocumentRequest.getEoffenderDetails().getTrimUser()*/ "7410537",
				/*eoffenderDocumentRequest.getEoffenderDetails().getLastName() */ "BLD,LEY" , "Watcher_Service",
				/*eoffenderDocumentRequest.getEoffenderDetails().getOffenderBookingNo()*/ /*"2013220035"*/ "2017347560",
				/*eoffenderDocumentRequest.getEoffenderDetails().getOffenderIdDisplay()*/ /*"0000530748"*/ "0000592793",
				/*eoffenderDocumentRequest.getEoffenderDetails().getObjectId().toString()*/ /*"2"*/ "47557777",
				/*eoffenderDocumentRequest.getEoffenderDetails().getObjectType()*/ "WARRANT",
				/*eoffenderDocumentRequest.getEoffenderDetails().getOsUser()*/ "anurag.sachan",null, updatedFileNames,
				httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/TRIM/"));
		
		System.out.println(result);
		try {
			fileInputStream.close();
			//fileInputStream1.close();
			Files.deleteIfExists(dbFile.toPath());	
			Files.deleteIfExists(dbFile.toPath());
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}	
		filesOpenTimeMap.remove(dbFile.getName());
		filesUploadedTimeMap.put(dbFile.getName(), System.currentTimeMillis());
		
		return result;
	}

	
	@PostMapping(value = "/eoffender/document/uploadFromGenerateDialog")
	public Map<String, String> uploadDocumentFromDialog(HttpServletRequest httpServletRequest, 
			@RequestParam("key") String key, 
			@RequestParam("trimUser") String trimUser,
			@RequestParam("offenderName") String offenderName,
			@RequestParam("dob") String dob,
			@RequestParam("bookingNo") String offenderBookingNo,
			@RequestParam("offenderIdDisplay") String offenderIdDisplay,
			@RequestParam("objectId") String oimsObjectId, 
			@RequestParam("objectType") String objectType,
			@RequestParam("recordCreator") String recordCreator
			) throws Exception {
        DBDocument dbDocument = eoffenderDocumentService.getTrimDocumentAsBlobFromDB(key);
        
        File dbFile = saveFileToTempLoc(httpServletRequest, dbDocument.getFileData(), "", DocManageUtilities.DOCX);
        
        FileInputStream fileInputStream = new FileInputStream(dbFile);
        
		MultipartFile multipartFile = new MockMultipartFile(dbDocument.getDocumentName(), fileInputStream);
		List<MultipartFile> fileList = new ArrayList<>();
		fileList.add(multipartFile);

		// POST getProperties API
		Document document = getPropertiesFromFile(fileList.get(0), DocManageUtilities.DOCX , httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/TRIM/"));

		List<String> templateList = new ArrayList<>();
		templateList.add(document.getDocumentType());

		List<String> documentUriList = new ArrayList<>();
		documentUriList.add(document.getUri());

		List<String> templateUriList = new ArrayList<>();
		templateUriList.add(document.getTemplateUri());
		
		List<String> updatedFileNames = new ArrayList<>();
		updatedFileNames.add(dbDocument.getDocumentName());

		
		Map<String, String> result = uploadDocument(fileList, templateList, documentUriList, templateUriList,
				trimUser ,
				offenderName ,
				dob,
			    offenderBookingNo ,
				offenderIdDisplay ,
				oimsObjectId,
				objectType,
				recordCreator, null, updatedFileNames,
				httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/TRIM/"));
		
		try {
			fileInputStream.close();
			//fileInputStream1.close();
			Files.deleteIfExists(dbFile.toPath());	
			Files.deleteIfExists(dbFile.toPath());
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}	
		filesOpenTimeMap.remove(dbFile.getName());
		filesUploadedTimeMap.put(dbFile.getName(), System.currentTimeMillis());
		
		return result;
	}
	
	public Map<String, String> uploadDocumenDirectly(HttpServletRequest httpServletRequest,
			byte[] fileByteArray, String fileName, EoffenderDocumentRequest eoffenderDocumentRequest) throws Exception {

		File dbFile = saveFileToTempLoc(httpServletRequest, fileByteArray, "", DocManageUtilities.DOCX);

		FileInputStream fileInputStream = new FileInputStream(dbFile);

		MultipartFile multipartFile = new MockMultipartFile(fileName.concat(DocManageUtilities.DOCX) , fileInputStream);
		List<MultipartFile> fileList = new ArrayList<>();
		fileList.add(multipartFile);

		// POST getProperties API
		Document document = getPropertiesFromFile(fileList.get(0), DocManageUtilities.DOCX,
				httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/TRIM/"));

		List<String> templateList = new ArrayList<>();
		templateList.add(document.getDocumentType());

		List<String> documentUriList = new ArrayList<>();
		documentUriList.add(document.getUri());

		List<String> templateUriList = new ArrayList<>();
		templateUriList.add(document.getTemplateUri());
		
		List<String> updatedFileNames = new ArrayList<>();
		updatedFileNames.add(fileName);

		Map<String, String> result = uploadDocument(fileList, templateList, documentUriList, templateUriList,
				eoffenderDocumentRequest.getEoffenderDetails().getTrimUser() ,
				eoffenderDocumentRequest.getEoffenderDetails().getLastName() ,
				"DATE_OF_BIRTH",
			    eoffenderDocumentRequest.getEoffenderDetails().getOffenderBookingNo(),
				eoffenderDocumentRequest.getEoffenderDetails().getOffenderIdDisplay() ,
				eoffenderDocumentRequest.getEoffenderDetails().getObjectId().toString() ,
				eoffenderDocumentRequest.getEoffenderDetails().getObjectType(),
				eoffenderDocumentRequest.getEoffenderDetails().getOsUser(), null, updatedFileNames,
				httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/TRIM/"));
		try {
			fileInputStream.close();
			Files.deleteIfExists(dbFile.toPath());
			Files.deleteIfExists(dbFile.toPath());
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		filesOpenTimeMap.remove(dbFile.getName());
		filesUploadedTimeMap.put(dbFile.getName(), System.currentTimeMillis());

		return result;
	}
	
	@PostMapping(value = "/eoffender/document/uploadFromSchedular", produces = MediaType.ALL_VALUE)
	public Map<String, String> uploadDocumentFromSchedular(HttpServletRequest httpServletRequest, @QueryParam("key") String key) throws Exception {
        
		DBDocument dbDocument = eoffenderDocumentService.getTrimDocumentAsBlobFromDB(key);
		String docId=dbDocument.getDocumentId();
		String Status=dmsService.getStatausOfDocument(docId);
		
		if(Status.equals("Checked Out"))
		{
		byte[] byteArray = dbDocument.getFileData();
        
        File dbFile = saveFileToTempLoc(httpServletRequest, byteArray, "", dbDocument.getFileExt());
        
        FileInputStream fileInputStream = new FileInputStream(dbFile);
        
        String fileName = DocManageUtilities.cleanFileName(dbDocument.getDocumentName());
        
		MultipartFile multipartFile = new MockMultipartFile(fileName, fileInputStream);
		List<MultipartFile> fileList = new ArrayList<>();
		fileList.add(multipartFile);

		// POST getProperties API
		Document document = getPropertiesFromFile(fileList.get(0), dbDocument.getFileExt(), httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/TRIM/"));

		List<String> templateList = new ArrayList<>();
		templateList.add(document.getDocumentType());

		List<String> documentUriList = new ArrayList<>();
		documentUriList.add(document.getUri());

		List<String> templateUriList = new ArrayList<>();
		templateUriList.add(document.getTemplateUri());
		
		List<String> updatedFileNames = new ArrayList<>();
		updatedFileNames.add(fileName);

		Map<String, String> result = uploadDocument(fileList, templateList, documentUriList, templateUriList,
				/*eoffenderDocumentRequest.getEoffenderDetails().getTrimUser()*/ dbDocument.getTrimUser(),
				/*eoffenderDocumentRequest.getEoffenderDetails().getLastName() */ dbDocument.getOffednerName() , dbDocument.getOffDOB(),
				/*eoffenderDocumentRequest.getEoffenderDetails().getOffenderBookingNo()*/ /*"2013220035"*/ dbDocument.getOffenderBookingNo(),
				/*eoffenderDocumentRequest.getEoffenderDetails().getOffenderIdDisplay()*/ /*"0000530748"*/ dbDocument.getOffenderDisplayId(),
				/*eoffenderDocumentRequest.getEoffenderDetails().getObjectId().toString()*/ /*"2"*/ null,
				/*eoffenderDocumentRequest.getEoffenderDetails().getObjectType()*/ null,
				/*eoffenderDocumentRequest.getEoffenderDetails().getOsUser()*/ dbDocument.getDocumentAuthor(), null,updatedFileNames,
				httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/TRIM/"));
		
		logger.info("uploadDocument result is "+result);
		
		if ((result!=null ) && (result.get("").equals("SUCCESS") || (result.get(fileName)!=null && result.get(fileName).equals("SUCCESS")))) {
			BigDecimal id = new BigDecimal(key);
			eoffenderDocumentService.updateDocStatus(id, "C");
		}
		
		try {
			fileInputStream.close();
			Files.deleteIfExists(dbFile.toPath());	
		} catch (Exception e) {
			logger.error("Uploadfrom scheduler has error "+e.getMessage());
			e.printStackTrace();
		}	
		filesOpenTimeMap.remove(dbFile.getName());
		filesUploadedTimeMap.put(dbFile.getName(), System.currentTimeMillis());
		return result;
		}
		
		return null;
	}
	
	@PostMapping(value = "/eoffender/document/uploadFileIntoDB", produces = MediaType.ALL_VALUE)
	public String uploadFileIntoDB(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile multipartFile, @QueryParam("key") String key,  @QueryParam("fileName") String fileName) throws Exception {
        
		manageDocumentService.saveFileFromWatcherToDB(multipartFile.getBytes(), key, fileName);
		
		return null;
	}
	
	@GetMapping(value = "/eoffender/document/getIWPTemplatesByType")
	public List<IwpTemplates> getIWPTemplatesByType(HttpServletRequest httpServletRequest, @QueryParam("templateType") String templateType ) {
		return eoffenderDocumentService.getIWPTemplatesByType(templateType);
	}
	
	@GetMapping(value = "/eoffender/document/getEmailTemplates")
	public List<IwpTemplates> getEmailTemplates(HttpServletRequest httpServletRequest) {
		return eoffenderDocumentService.getIWPTemplatesByType("EMAIL");
	}
	@RequestMapping(value="/eoffender/documentDataUpdate",method=RequestMethod.POST)
	public @ResponseBody Integer documentDataUpdate(@RequestBody IwpDocumentsCommitBean commitBean) {
		Integer liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = eoffenderDocumentService.documentsDataUpdate(commitBean);
		}catch(Exception e){

			logger.error("Exception in documentDataUpdate"+e.getMessage());
		}
		return liReturn;
	} 
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/eoffender/getUserModuleAccess", method = RequestMethod.GET)
	public String getUserModuleAccess(@RequestParam("moduleName") String moduleName) {
		String  access=null;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			access=eoffenderDocumentService.getModuleAccess(moduleName, userName);
		}catch(Exception e) {
			logger.error("error in getUserModuleAccess"+e.getMessage());
		}
		return access;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/eoffender/getLanguageId", method = RequestMethod.GET)
	public Integer getLanguageId() {
		Integer languageId=null;
		try {
			languageId = dmsService.getLanguageId();
		} catch (Exception e) {
			logger.error("error in getLanguageId: "+e.getMessage());
		}
		return languageId;
	}

}
