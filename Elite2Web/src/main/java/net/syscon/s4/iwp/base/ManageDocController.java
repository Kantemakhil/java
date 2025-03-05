package net.syscon.s4.iwp.base;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
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
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.http.HttpRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageProperties;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aspose.pdf.DocSaveOptions;
import com.aspose.pdf.DocSaveOptions.RecognitionMode;
import com.aspose.words.BookmarkCollection;
import com.aspose.words.BookmarkStart;
import com.aspose.words.Field;
import com.aspose.words.FieldFormText;
import com.aspose.words.FieldType;
import com.aspose.words.FindReplaceOptions;
import com.aspose.words.License;
import com.aspose.words.NodeType;
import com.aspose.words.PdfCompliance;
import com.aspose.words.PdfSaveOptions;
import com.aspose.words.SaveFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syncfusion.docio.TextSelection;
import com.syncfusion.docio.WordDocument;
import com.syncfusion.ej2.wordprocessor.FormatType;
import com.syncfusion.ej2.wordprocessor.WordProcessorHelper;
import com.syncfusion.javahelper.system.text.regularExpressions.MatchSupport;

import net.syscon.s4.common.CustomMultipartFile;
import net.syscon.s4.common.DocManageUtilities;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.eoffender.beans.DBDocument;
import net.syscon.s4.eoffender.beans.Document;
import net.syscon.s4.eoffender.beans.DocumentRequestBean;
import net.syscon.s4.eoffender.beans.EoffenderDetails;
import net.syscon.s4.eoffender.beans.EoffenderSqlParameter;
import net.syscon.s4.eoffender.beans.EoffenderTemplate;
import net.syscon.s4.eoffender.beans.FileLimits;
import net.syscon.s4.global.Omss40Service;
import net.syscon.s4.im.beans.IwpDocuments;
import net.syscon.s4.im.beans.IwpDocumentsCommitBean;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogsCommitBean;
import net.syscon.s4.iwp.beans.DocDetails;
import net.syscon.s4.iwp.beans.GeneratedDoc;
import net.syscon.s4.iwp.beans.ManageDocumentRequest;
import net.syscon.s4.iwp.eoffender.DMSService;
import net.syscon.s4.iwp.eoffender.EoffenderDocHelper;
import net.syscon.s4.iwp.eoffender.EoffenderDocService;
import net.syscon.s4.iwp.eoffender.EoffenderService;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@EliteController
public class ManageDocController {


	private static Logger logger = LogManager.getLogger(ManageDocController.class.getName());
	private static final String FILE_UPLOAD_FAILED= "UPLOAD FAILED";
	private static final Integer FILE_NOT_SUPPORTED = 422;

	@Autowired
	ManageDocService manageDocService;

	@Autowired
	private EoffenderDocService eoffenderDocumentService;

	@Autowired
	private DocManageUtilities docManageUtilities;

	@Qualifier("trim")
	@Autowired
	private DMSService dmsService;

	@Autowired
	private Environment env;

	@Autowired
	private EoffenderService eoffenderService;

	@Autowired
	private Omss40Service omss40Service;

	private String saveDir = null;

	@Autowired
	private License license;

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@Autowired
	private EoffenderDocHelper docHelper;
	
	@Autowired
	private ProsmainService prosmainService;

	private void addHttpRequestImpersonationHeader (HttpRequest request, String trimUser) {
		request.setHeader("userToImpersonate",trimUser);
		return;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/eoffender/document/generate", method = RequestMethod.POST)
	public ResponseEntity downloadDocument(@RequestBody ManageDocumentRequest manageDocumentRequest,
										   HttpServletRequest httpServletRequest, @QueryParam("dialogLink") String dialogLink) throws Exception {
		
		HttpStatus httpStatus = HttpStatus.OK;
		ResponseEntity repsonseEntity = null;
		Path destinationPath = null;
		String sfdtText =null;
		Map<String, Object> objectDataMap=null;
		ObjectMapper mapper = new ObjectMapper();
		HttpHeaders headerRes = new HttpHeaders();
		if(manageDocumentRequest.getDocDetails() == null) {
			manageDocumentRequest.setDocDetails(new DocDetails());
		}
		if(manageDocumentRequest.getObjectData()!=null) {
			objectDataMap = mapper.convertValue(manageDocumentRequest.getObjectData(), HashMap.class);
			
		}
		String offIdDisplay = manageDocumentRequest.getDocDetails().getOffenderIdDisplay();
		String offBookingNo = manageDocumentRequest.getDocDetails().getOffenderBookingNo();

		String savedLocation = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/TRIM/");
		manageDocumentRequest.setPath(savedLocation);
		manageDocumentRequest.setTemplateId(manageDocumentRequest.getTemplateId());
		manageDocumentRequest.setUserName(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		GeneratedDoc genratedDoc = manageDocService.generateDocument(manageDocumentRequest,objectDataMap);
		try {
			String templateData = new String(genratedDoc.getTemplateContent(), StandardCharsets.UTF_8);
			if(genratedDoc.getTemplateType()!=null && genratedDoc.getTemplateType().equals("EMAIL")) {
				sfdtText=replaceBookMarkData(templateData,genratedDoc);
			}else {
				sfdtText=replaceBookMarkData(templateData,genratedDoc);
				 WordDocument document=WordProcessorHelper.save(sfdtText);
				 sfdtText = replaceBookMarks(document,genratedDoc.getBookmarValueMap());
			}
		logger.info("Template Sfdt after conversion"+sfdtText);
		destinationPath = Paths.get(genratedDoc.getGeneratedDocLocation());
	   	Path newPath = Paths.get(destinationPath.toString());
			String mimeType = docManageUtilities.getMimeType(destinationPath.toString());
			headerRes = docManageUtilities.getHttpHeadersForFileUpload(mimeType, newPath.getFileName().toString(), false);
			repsonseEntity = new ResponseEntity<>(sfdtText.getBytes("UTF-8"), headerRes, HttpStatus.OK);
			logger.info("======Document is Generated=====");
		} catch (Exception e) {
			logger.error("downloadDocument move operation failed: " + ", offenderBookingNo " + offBookingNo+ ", offenderIdDisplay " + offIdDisplay + "message:" + e.getMessage());
			repsonseEntity = new ResponseEntity<>(null, null, httpStatus);
			return repsonseEntity;
		}
		return repsonseEntity;
	}
	private String replaceBookMarkData(String document, GeneratedDoc genratedDoc) throws Exception {
		logger.info("==================================replaceBookamarks starts here =======================");
		String docText[] = new String[] {String.join("\n",document)};
		if(genratedDoc.getBookmarValueMap()!=null) {
		genratedDoc.getBookmarValueMap().forEach((key, value)->{
			String replacementText = "";
		if((value != null && !value.isEmpty())) {
			if(value.size() == 1) {
				replacementText = value.get(0);
				if(replacementText!=null) {
					replacementText=replacementText.replace("\n", " ").replace("\r", " ");
				}
			} else  {
				replacementText =  String.join(", ", value);
				if(replacementText!=null) {
					replacementText=replacementText.replace("\n", " ").replace("\r", " ");
				}
			}
			try {
				if(docText[0].contains("{{" + key + "}}")) {
				if(replacementText.contains("\"isMetaFile\":false")) {
					docText[0] = docText[0].replace("\"text\":\"{{"+ key +"}}\"", replacementText);
				} else {
					if(genratedDoc.getTemplateType()!=null && genratedDoc.getTemplateType().equals("EMAIL")) {
						docText[0]= docText[0].replace("{{" + key + "}}", replacementText);
					}
				}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}});
		}
		return docText[0];
	}

	
	private String replaceBookMarks(WordDocument document, Map<String, List<String>> bookmarkValueMap) throws Exception {
		logger.info("==================================replaceBookamarks starts here =======================");
		String sfdtText=null;
		List<String> textsToReplace = new ArrayList<>();
		if(bookmarkValueMap!=null) {
		bookmarkValueMap.forEach((key, value)->{String replacementText = "";
		if((value != null && !value.isEmpty())) {
			if(value.size() == 1) {
				replacementText = value.get(0);
				if(replacementText!=null) {
					replacementText=replacementText.replace("\n", " ").replace("\r", " ");
				}
			} else  {
				replacementText =  String.join(", ", value);
				if(replacementText!=null) {
					replacementText=replacementText.replace("\n", " ").replace("\r", " ");
				}
			}
			try {
				TextSelection[] textSelections = document.findAll("{{" + key + "}}",false,true);
				if(textSelections!=null && textSelections.length>0) {
					if(!replacementText.contains("\"isMetaFile\":false")) {
						document.replace("{{" + key + "}}", replacementText, true, true);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}});
		}
		TextSelection[] textSelections = document.findAll(Pattern.compile(MatchSupport.trimPattern("\\{\\{([^}]+)\\}\\}")));
		if(textSelections!=null) {
		for (int i = 0; i < textSelections.length; i++) 
		{
			if(textSelections[i]!=null && textSelections[i].getSelectedText()!=null && !textSelections[i].getSelectedText().equals("")) {
				textsToReplace.add(textSelections[(int) i].getSelectedText());
				//document.replace(textSelections[(int) i].getSelectedText(), "", true, true);
			}
		}
		}
		if(textsToReplace!=null && !textsToReplace.isEmpty()) {
		for(String obj:textsToReplace) {
			document.replace(obj, "", true, true);
		}
		}
		sfdtText = WordProcessorHelper.load(document);
		return sfdtText;
	}
   
	
	
	

	@PostMapping(value = "/iwp/document/generateCmd", produces = MediaType.ALL_VALUE)
	public Map<String, Object> downloadDocumentCmd(@RequestBody String manageDocument,
									  HttpServletRequest httpServletRequest) throws Exception {
		Long result = null;
		Map<String, Object> response = new HashMap<String, Object>();
		ManageDocumentRequest manageDocumentRequest = new ManageDocumentRequest();
		ObjectMapper mapper = new ObjectMapper();
		try {
			manageDocumentRequest = mapper.readValue(manageDocument, ManageDocumentRequest.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String temproaryDocumentId = manageDocService.getTempDocId().toString();
		String dialogLink = temproaryDocumentId + "-" + manageDocumentRequest.getTemplateType();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		VHeaderBlock offenderDetails = manageDocService.getOffenderDetails(manageDocumentRequest.getDocDetails().getOffenderBookId(),userName);
		manageDocumentRequest.getDocDetails().setOffenderBookId(offenderDetails.getOffenderBookId().longValue());
		manageDocumentRequest.getDocDetails().setOffenderIdDisplay(offenderDetails.getOffenderIdDisplay());
		manageDocumentRequest.getDocDetails().setLastName(offenderDetails.getLastName());
		manageDocumentRequest.getDocDetails().setFirstName(offenderDetails.getFirstName());
		manageDocumentRequest.getDocDetails().setBirthDate(offenderDetails.getBirthDate());

		String updatedFileName = manageDocumentRequest.getDocDetails().getOffenderIdDisplay()+manageDocumentRequest.getDocDetails().getLastName()+ ", " +manageDocumentRequest.getDocDetails().getFirstName()+"-"+manageDocumentRequest.getTemplateName().toString()+".docx";

		HttpStatus httpStatus = HttpStatus.OK;
		ResponseEntity repsonseEntity = null;
		HttpHeaders headerRes = new HttpHeaders();
		if(manageDocumentRequest.getDocDetails() == null) {
			manageDocumentRequest.setDocDetails(new DocDetails());
		}
		String offIdDisplay = manageDocumentRequest.getDocDetails().getOffenderIdDisplay();
		String offBookingNo = manageDocumentRequest.getDocDetails().getOffenderBookingNo();

		String savedLocation = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/TRIM/");
		manageDocumentRequest.setPath(savedLocation);
		manageDocumentRequest.setTemplateId(manageDocumentRequest.getTemplateId());
		manageDocumentRequest.setUserName(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		GeneratedDoc genratedDoc = manageDocService.generateDocument(manageDocumentRequest,null);
		File generateFile = new File(genratedDoc.getGeneratedDocLocation());
		String sfdtText = replaceSFDT(generateFile.getAbsolutePath(), genratedDoc.getBookmarValueMap());
		Path destinationPath = null;
		try {
			if(generateFile.getAbsolutePath().contains(".docx") || generateFile.getAbsolutePath().contains(".DOCX")) {
				destinationPath = Paths.get(generateFile.getAbsolutePath());
			} else {
				destinationPath = convertDocumentTypeToDocx(manageDocumentRequest, generateFile, ".DOC", dialogLink, generateFile.getAbsolutePath());
			}

			String mimeType = docManageUtilities.getMimeType(destinationPath.toString());
			Path newPath = Paths.get(destinationPath.toString());
			byte[] documentContent = Files.readAllBytes(newPath);
			headerRes = docManageUtilities.getHttpHeadersForFileUpload(mimeType, newPath.getFileName().toString(), false);
			repsonseEntity = new ResponseEntity<>(documentContent, headerRes, HttpStatus.OK);

			FileInputStream input = new FileInputStream(generateFile);
			MultipartFile multipartFile = new MockMultipartFile("file",
					generateFile.getName(), null, sfdtText.getBytes("UTF-8"));

			result = uploadDocumentCmd(multipartFile, manageDocumentRequest.getDocDetails().getOffenderBookId().toString(), manageDocumentRequest.getTemplateId(), manageDocumentRequest.getTemplateType(), updatedFileName, httpServletRequest);
		} catch (Exception e) {
			logger.error("downloadDocument move operation failed: " + ", offenderBookingNo " + offBookingNo+ ", offenderIdDisplay " + offIdDisplay);
			repsonseEntity = new ResponseEntity<>(null, null, httpStatus);
			response.put("Response", "Document not created");
		}
		if(result != null) {
			response.put("Response", "Document created");
		} else {
			response.put("Response", "Document not created");
		}
		return response;
	}

	public Long uploadDocumentCmd(MultipartFile file, String offenderBookId, Long templateId, String objectType, String updatedFileName,
								  HttpServletRequest httpServletRequest)throws Exception {

		Map<String, String> uploadResult = new ConcurrentHashMap<>();
		Long docId = null;

		List<IwpDocuments> iwpDocuments = new ArrayList<>();
		String savedLocation = "";
		try {
			savedLocation = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/TRIM/");
		} catch(Exception ex) {
			savedLocation = null;
		}

		String fileExtension = docManageUtilities.getFileExtension("".equals(file.getOriginalFilename())?file.getName():file.getOriginalFilename());
		fileExtension = fileExtension.equals("")? ".docx": ".docx";
		updatedFileName = updatedFileName.replace("||", ",");

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if (fileName.contains("..")) {
			uploadResult.put(file.getOriginalFilename(),
					"UPLOAD FAILED :: " + "Filename contains invalid path sequence");
		}
		IwpDocuments iwpDocument = new IwpDocuments();
		iwpDocument.setTemplateId(new BigDecimal(templateId));
		iwpDocument.setDocumentName(updatedFileName);
		String fileDataStr = new String(file.getBytes());
		byte[] fileDataBytes= fileDataStr.getBytes();
		iwpDocument.setDocumentContent(fileDataBytes);
		iwpDocument.setActiveFlag("Y");
		iwpDocument.setDocumentStatus("PUBLIC");
		if( offenderBookId.equals("")) {
			iwpDocument.setOffenderBookId(null);
		}else {
			iwpDocument.setOffenderBookId(new BigDecimal(offenderBookId));
		}
		iwpDocument.setFileExtension(fileExtension);
		iwpDocument.setObjectType(objectType);
		iwpDocuments.add(iwpDocument);
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		int[] success = manageDocService.uploadDocuments(iwpDocuments, savedLocation,userName);
		if(success.length != 0 ) {
			docId = manageDocService.getDocId();
		}
		return docId;
	}

	private String replaceSFDT(String documentPath, Map<String, List<String>> bookmarkValueMap) throws Exception {
		logger.info("==================================replaceSFDT starts here =======================");

		com.aspose.words.Document asposeDocument = new com.aspose.words.Document(documentPath);
		String docText[] = new String[] {String.join("\n", Files.readAllLines(Paths.get(documentPath)))};
		bookmarkValueMap.forEach((key, value)->{
			String replacementText = "";
			if(docText[0].contains( "{{" + key + "}}" ) && (value != null && !value.isEmpty())) {
				if(value.size() == 1) {
					replacementText = value.get(0);
					if(replacementText!=null) {
						replacementText=replacementText.replace("\n", " ").replace("\r", " ");
					}
				} else  {
					replacementText =  String.join(", ", value);
					if(replacementText!=null) {
						replacementText=replacementText.replace("\n", " ").replace("\r", " ");
					}
					replacementText =  replacementText + "\\n";
					/*for(String text : value) {
						replacementText =  replacementText + text + "\\n";
					}*/
				}
				try {
					// asposeDocument.getRange().replace( "{{" + key + "}}" , replacementText, new FindReplaceOptions());
					if(replacementText.contains("\"isMetaFile\":false")) {
						docText[0] = docText[0].replace( "\"text\":\"{{"+ key +"}}\"" , replacementText);						
					} else {
						docText[0] = docText[0].replace( "{{" + key + "}}" , replacementText);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		docText[0] = docText[0].replaceAll("\\{\\{[A-Za-z_.0-9]+\\}\\}", " ");
		asposeDocument.save(documentPath);
		return docText[0];
	}

	private void replace(String documentPath, Map<String, List<String>> bookmarkValueMap) throws Exception {
		logger.info("==================================BookMark starts here =======================");

		bookmarkValueMap.forEach((key, value)->logger.info("Key - "+key+" value -"+value));
		com.aspose.words.Document asposeDocument = new com.aspose.words.Document(documentPath);
		for (Field field : asposeDocument.getRange().getFields()) {
			if (field.getType() == FieldType.FIELD_FORM_TEXT_INPUT) {
				FieldFormText fieldFormText = (FieldFormText) field;
				BookmarkStart bookmarkStart = null;
				if (fieldFormText.getStart().getNextSibling().getNodeType() == NodeType.BOOKMARK_START) {
					bookmarkStart = (BookmarkStart) fieldFormText.getStart().getNextSibling();
				}
				if (bookmarkStart != null) {
					fieldFormText.getStart().getParentNode().insertBefore(bookmarkStart, fieldFormText.getStart());
				}
			}
		}
		BookmarkCollection bookMarkCollection = asposeDocument.getRange().getBookmarks();
		bookMarkCollection.forEach(bookMark -> {

			logger.info(bookMark.getName());

			if (bookmarkValueMap.containsKey(bookMark.getName())) {
				List<String> bookmarkValueList = bookmarkValueMap.get(bookMark.getName());
				String replacementText = "";
				if (bookmarkValueList !=null ) {
					if(bookmarkValueList.size()==1) {
						replacementText = bookmarkValueList.get(0);
					} else  {
						for(String value:bookmarkValueList) {
							replacementText =  replacementText + value + "\n";
						}
					}
				}
				try {
					bookMark.setText(replacementText);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		asposeDocument.save(documentPath);
	}

	@GetMapping("/iwp/getTemplates/{moduleName}/{userId}")
	public List<EoffenderTemplate> getTemplates(@PathVariable("moduleName") String moduleName, @PathVariable("userId") String userId){

		logger.info("getTemplates templateList: moduleName, " + moduleName+ ", userId " +userId);
		String activeFlag = "Y";
		List<EoffenderTemplate> templateList = new ArrayList<>();
		List<String> roleList = eoffenderDocumentService.getStaffRoles(userId);
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

	@GetMapping("/iwp/getAllTemplates/{moduleName}/{userId}/{trimUser}")
	public List<EoffenderTemplate> getAllTemplates(@PathVariable("moduleName") String moduleName, @PathVariable("userId") String userId,
												   @PathVariable("trimUser") String trimUser){

		logger.info("getTemplates templateList: moduleName, " + moduleName+ ", userId " +userId);
		String activeFlag = "Y&N";
		List<EoffenderTemplate> templateList = new ArrayList<>();
		List<String> roleList = eoffenderDocumentService.getStaffRoles(userId);
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
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/eoffender/getDocumentName", method = RequestMethod.POST)
	public Document generateDocumentName(@RequestBody EoffenderDetails eoffenderDetails, @RequestParam("templateName") String templateName){
		Document document = new Document();
		StringBuilder fileNameBuilder = new StringBuilder();
		if(eoffenderDetails != null) {
			logger.info("generateDocumentName Generating document name :  template " + templateName +  ", offenderBookingNo "
					+  eoffenderDetails.getOffenderBookingNo() +
					", offenderIdDisplay "+eoffenderDetails.getOffenderIdDisplay());
			try {
				if(eoffenderDetails.getOffenderIdDisplay()!=null) {
					fileNameBuilder.append(eoffenderDetails.getOffenderIdDisplay()).append(eoffenderDetails.getLastName()).append(",").
					append(" ").append(this.toProperCase(eoffenderDetails.getFirstName())).append("-").append(templateName);	
				}else {
					fileNameBuilder.append(eoffenderDetails.getObjectId()).append(" ").append(eoffenderDetails.getObjectType()).
				append("-").append(templateName);
				}
			
			} catch (Exception e) {
				logger.error("generateDocumentName Error Generating document name : " + e + " template " + templateName +  ", offenderBookingNo "
						+  eoffenderDetails.getOffenderBookingNo() +
						", offenderIdDisplay "+eoffenderDetails.getOffenderIdDisplay());
			}
		}
		document.setDocumentName(fileNameBuilder.toString());
		document.setTemproaryDocumentId(manageDocService.getTempDocId().toString());
		return document;
	}
	public String  toProperCase(String documentName) {
		return documentName.substring(0, 1).toUpperCase()+documentName.substring(1).toLowerCase();
	}
	@GetMapping(value="/iwp/tempDocId")
	public BigDecimal getTempDocId() {
		return manageDocService.getTempDocId();
	}

	@PostMapping(value = "/iwp/upload")
	public int uploadDocument(
			@RequestParam("file") List<MultipartFile> fileList,
			@RequestParam("offenderBookId") String offenderBookId,
			@RequestParam("templateId") List<String> templateList,
			@RequestParam("documentUriList") List<String> documentUriList,
			@RequestParam("templateUriList") List<String> templateUriList,
			@RequestParam("objectId") String oimsObjectId,
			@RequestParam("objectType") String objectType,
			@RequestParam("fileName") List<String> updatedFileNames,
			HttpServletRequest httpServletRequest,
			String path)
			throws Exception {

		Map<String, String> uploadResult = new ConcurrentHashMap<>();
		List<FileLimits> fileSizelimits = new ArrayList<>();
		fileSizelimits = getEoffenderProfileValues();
		List<String> extensionList = Arrays.asList(".docx", ".doc", ".pdf", ".jpg", ".png", ".gif", ".bmp",".mp4", ".jpeg", ".tiff", ".tif", ".rtf", ".txt",".msg",".xls",".xlsx",".html",".svg");
		/*String fileLimitCheck = DocManageUtilities.fileLimitCheck(fileSizelimits,fileList);	
		if(fileLimitCheck.equals("File size exceeds")){
			uploadResult.put(FILE_UPLOAD_FAILED,"Upload Size Limit is Exceeded");
			return uploadResult;
		}
		if(fileLimitCheck.equals("File count exceeds")){
			uploadResult.put(FILE_UPLOAD_FAILED,"Upload Count Limit is Exceeded");
			return uploadResult;
		}*/

		List<IwpDocuments> iwpDocuments = new ArrayList<>();
		String savedLocation = "";
		try {
			savedLocation = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/TRIM/");
		} catch(Exception ex) {
			savedLocation = path;
		}

		int fileCount = fileList.size();
		for (int i = 0; i < fileCount; i++) {
			String sfdtFile=null;
			Path destinationPath = null;
			CustomMultipartFile customMultipartFile;
			MultipartFile file = fileList.get(i);
			ManageDocumentRequest manageDocumentRequest=new ManageDocumentRequest();
			String fileExtension = docManageUtilities.getFileExtension("".equals(file.getOriginalFilename())?file.getName():file.getOriginalFilename());
			if(fileExtension!=null && !extensionList.contains(fileExtension.toLowerCase())) {
				return FILE_NOT_SUPPORTED;
			}
			
			fileExtension = fileExtension.equals("")? ".docx": fileExtension;
			String templateId = templateList.get(i);
			String documentUri = documentUriList.get(i);
			String templateUri = templateUriList.get(i);
			String updatedFileName = updatedFileNames.get(i).replace("||", ",");
			
			String fileNameExtension = docManageUtilities.getFileExtension(updatedFileName);
			if(fileNameExtension!=null && (!extensionList.contains(fileNameExtension.toLowerCase()) || !fileNameExtension.equalsIgnoreCase(fileExtension))) {
				return FILE_NOT_SUPPORTED;
			}

			//verify difference between file.getoriginalFilenname and file getname 
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				uploadResult.put(file.getOriginalFilename(),
						"UPLOAD FAILED :: " + "Filename contains invalid path sequence");
			}
			IwpDocuments iwpDocument = new IwpDocuments();
			iwpDocument.setTemplateId(new BigDecimal(templateUri));
			iwpDocument.setDocumentName(updatedFileName);
			
			if(fileExtension.equals(".docx") || fileExtension.equals(".doc")) {
				if(fileExtension.equals(".doc")) {
					 savedLocation = getFileSavedLocation(httpServletRequest, offenderBookId, ".docx");
							File trimFile = new File(savedLocation);
							Files.write(Paths.get(trimFile.getAbsolutePath()), file.getBytes());
							 destinationPath = convertDocumentTypeToDocx(manageDocumentRequest, trimFile, ".DOC", null,
									trimFile.getAbsolutePath());
							 Path newPath = Paths.get(destinationPath.toString());
								byte[] documentContent = Files.readAllBytes(newPath);
								 customMultipartFile = new CustomMultipartFile(documentContent);
				}else {
					 customMultipartFile = new CustomMultipartFile(file.getBytes());
				}
				fileExtension=".docx";
				sfdtFile = WordProcessorHelper.load(customMultipartFile.getInputStream(), FormatType.Docx,false);
			}
			
			if(sfdtFile!=null) {
				iwpDocument.setDocumentContent(sfdtFile.getBytes());
			}else {
				iwpDocument.setDocumentContent(file.getBytes());
			}
			
			iwpDocument.setActiveFlag("Y");
			iwpDocument.setDocumentId(!documentUri.equalsIgnoreCase("NEW_FILE")?new BigDecimal(documentUri):null);
			iwpDocument.setDocumentStatus("PUBLIC");
			if( offenderBookId.equals("")) {
				iwpDocument.setOffenderBookId(null);
			}else {
				iwpDocument.setOffenderBookId(new BigDecimal(offenderBookId));
			}
			iwpDocument.setObjectId(oimsObjectId);
			iwpDocument.setObjectType(objectType);
			iwpDocument.setFileExtension(fileExtension);
			iwpDocuments.add(iwpDocument);
		}
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		int[] success = manageDocService.uploadDocuments(iwpDocuments, savedLocation,userName);
		return success.length;
	}
	
	private String getFileSavedLocation(HttpServletRequest httpServletRequest, String offenderIdDisplay,
			String fileType) {
		Calendar cal = Calendar.getInstance();
		String pathToDirectory = httpServletRequest.getSession().getServletContext()
				.getRealPath("/WEB-INF/classes/TRIM/");

		File directoryPath = new File(pathToDirectory);
		if (!directoryPath.exists()) {
			directoryPath.mkdirs();
		}
		String savedLocation = pathToDirectory.concat(offenderIdDisplay + "_").concat(cal.getTimeInMillis() + "")
				.concat(fileType);
		return savedLocation;
	}

	@PostMapping(value = "/iwp/document/downloadfromTRIM", produces = MediaType.ALL_VALUE)
	public ResponseEntity downloadEditableDocument(HttpServletRequest httpServletRequest,
												   @RequestBody Document documentMetaData) throws Exception {


		String user = httpServletRequest.getUserPrincipal().getName();//getServletContext().get

		ResponseEntity repsonseEntity = null;
		HttpHeaders headerRes = new HttpHeaders();
		String savedLocation = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/TRIM/");

		String documentId = documentMetaData.getDocumentId();
		if(documentId !=null && !documentId.equals("")) {
			IwpDocuments iwpDocuments  = null;
			try {
				iwpDocuments = manageDocService.getEditableDocument(new BigDecimal(documentId), savedLocation, httpServletRequest.getUserPrincipal().getName());
			} catch (Exception ex) {
				logger.error(ex.getMessage());
				ex.printStackTrace();
				headerRes.setPragma("ex.getMessage()  "+ documentId);
				return new ResponseEntity<>(null, headerRes, HttpStatus.BAD_REQUEST);
			}

			byte[] byteArray = iwpDocuments.getDocumentContent();
			String fileName = docManageUtilities.getFileNameFromContentDisposition(iwpDocuments.getDocumentName());
			String fileExt = docManageUtilities.getFileExtension(iwpDocuments.getDocumentName());

			if (DocManageUtilities.isEmpty(fileExt) ) {
				headerRes.setPragma("downloadEditableDocument File extension not found for Document  "+ documentId);
				return new ResponseEntity<>(null, headerRes, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			headerRes = docManageUtilities.getHttpHeadersForFileUpload(fileExt, fileName, true);
			repsonseEntity = new ResponseEntity<>(byteArray, headerRes, HttpStatus.OK);

			return repsonseEntity;
		} else {
			return null;
		}

	}
	
	
	/*@PostMapping(value = "/iwp/document/downloadfromTRIM", produces = MediaType.ALL_VALUE)
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
			String fileExt = docManageUtilities.getExtensionFromContentDisposition(contentDisposition[1]);
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
					documentMetaData.setTemproaryDocumentId(manageDocService.getTempDocId().toString());
					documentMetaData.setFileExt(fileExt);
					eoffenderDocumentService.insertEditedFile(documentMetaData);
				}
				DocManageUtilities.addPropertiesInDocument(trimEditFile, documentMetaData, fileExt, trimEditFileToUpdate);
				if(fileExt.equals(DocManageUtilities.DOCX)){
					trimFileInputStream = new FileInputStream(trimEditFile);
				} else if(fileExt.equals(DocManageUtilities.PDF) || fileExt.equals(DocManageUtilities.DOC)){
					trimFileInputStream = new FileInputStream(trimEditFileToUpdate);
				}
				HttpHeaders headers = docManageUtilities.getHttpHeadersForFileUpload(fileExt, documentMetaData.getDocumentName(), true);
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
	}*/

	private void saveFileInDB(Document document, byte[] byteArray) {
		try {
			eoffenderDocumentService.saveFileToDB(document, byteArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping(value = "/iwp/document/viewfile", produces = MediaType.ALL_VALUE)
	public ResponseEntity viewIwpDocuments(@RequestParam("uri") String documentId, HttpServletRequest httpServletRequest) throws Exception {
		ResponseEntity repsonseEntity = null;
		byte[] byteArray;
		HttpHeaders headerRes = new HttpHeaders();
		String savedLocation = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/TRIM/");
		//int httpStatus = response.getStatusLine().getStatusCode();
		if(documentId !=null && !documentId.equals("")) {
			IwpDocuments iwpDocuments = manageDocService.viewDocument(new BigDecimal(documentId), savedLocation);
			if(iwpDocuments.getSignedDoc() != null) {
				 byteArray = iwpDocuments.getSignedDoc();
			}else {
				 byteArray = iwpDocuments.getDocumentContent();
			}
			
			String fileName = docManageUtilities.getFileNameFromContentDisposition(iwpDocuments.getDocumentName());
			String fileExt = docManageUtilities.getFileExtension(iwpDocuments.getDocumentName());

			if (DocManageUtilities.isEmpty(fileExt) ) {
				headerRes.setPragma("downloadfromTRIM2 No Content Found : URI "+ documentId);
				return new ResponseEntity<>(null, headerRes, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			headerRes = docManageUtilities.getHttpHeaderForuploadFile(fileExt, iwpDocuments.getDocumentName(), true);
			repsonseEntity = new ResponseEntity<>(byteArray, headerRes, HttpStatus.OK);

			return repsonseEntity;
		} else {
			return null;
		}
	}


	@PostMapping(value = "/iwp/document/getPropertiesFromFile")
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


	@PostMapping(value = "/iwp/document/getPropertiesFromFiles")
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
				fileExt = docManageUtilities.getFileExtension(multipartFileWithNameMap.get(multipartFile));
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
	@GetMapping("/iwp/document/cancel")
	public String cancelDocument(@RequestParam("documentUri") String documentUri, @RequestParam("trimUser") String trimUser) {
		logger.info("cancelDocument Check-out of Document with URI " + documentUri,trimUser);
		//Update status as Checked-In
		return dmsService.cancelDocument(documentUri,trimUser);
	}

	//final document
	@GetMapping("/iwp/document/final")
	public String finalDocument(@RequestParam("recordNumber") String recordNumber,@RequestParam("trimUser") String trimUser,@RequestParam("status") String status,@RequestParam("fileName") String fileName) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return manageDocService.updateDocumentStatus(recordNumber,status,fileName,userName);
	}

	@GetMapping("/iwp/document/completeDocument")
	public String completeDocument(@RequestParam("documentId") String documentId,@RequestParam("fileName") String fileName) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return manageDocService.updateDocumentStatus(documentId,"COMPLETED",fileName,userName);
	}
	//list all documents
	@PostMapping("/iwp/document/list")
	public List<Document> listDocuments(@RequestBody DocumentRequestBean documentRequestBean) throws IOException {
		logger.info("listDocuments fetching documents : ", documentRequestBean.getOffenderBookingId() + documentRequestBean.getModuleName());
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		if(documentRequestBean!=null) {
			documentRequestBean.setCreateUserId(userName);
		}
		List<Document> docList = manageDocService.listDocuments(documentRequestBean);
		return docList;

	}

	@GetMapping("/iwp/location")
	public String getLocationUri(@RequestParam("location") String locaton , String TrimUser) {
		logger.info("getLocationUri Fetching location for Author : " + locaton);
		//Update status as Checked-In
		return dmsService.getLocationUri(locaton,TrimUser);
	}

	@GetMapping("/iwp/editedDocCheckInVerification")
	public String verifyEditedDocCheckedIn(@RequestParam("docId") String docId) {
		String response = "";

		logger.info("verifyEditedDocCheckedIn Fetching location for Author : " );
		//Update status as Checked-In
		try {
			response=eoffenderDocumentService.verifyEditedDocCheckedIn(docId);
		} catch (Exception e) {
			logger.error("verifyEditedDocCheckedIn Fetching location for Author : " + e.getMessage() );
		}

		logger.info(" Controller Response is :" + response);
		return response;
	}
	
	/*@GetMapping("/iwp/generatedDocVerification")
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
	}*/



	@RequestMapping(value = "/iwp/getEoffenderProfileValues", method = RequestMethod.GET)
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
	private Path convertDocumentTypeToDocx(ManageDocumentRequest eoffenderDocumentRequest, File generatedFile, String trimFileExt, String dialogLink, String saveDir) throws Exception{
		Path destinationPath = null;
		com.aspose.words.Document asposeDocument = new com.aspose.words.Document(saveDir);
		// Convert file to docx format
		String newFilePath = saveDir.replace(trimFileExt, DocManageUtilities.DOCX);
		asposeDocument.save(newFilePath, SaveFormat.DOCX);
		destinationPath = Paths.get(newFilePath);
		saveDir = newFilePath;
		try (OPCPackage opc = OPCPackage.open(saveDir);) {
			PackageProperties pp = opc.getPackageProperties();
			pp.setCategoryProperty("NEW_FILE" + "-" + eoffenderDocumentRequest.getTemplateId() + "-" + "NEW_RECORD"
					+ "-" + eoffenderDocumentRequest.getTemplateType()+ "-" + dialogLink);
		}
		return destinationPath;
	}

	private Map<String, String> getBookmarkResolvedSqlMap(ManageDocumentRequest eoffenderDocumentRequest,
														  List<String> bookmarkList) {
		Map<String, String> bookmarkResolvedSqlMap = null;
		try {
			CountDownLatch latch = new CountDownLatch(2);

			Callable<Map<String, String>> bookMarkSqlMappingCallable = () -> {
				Map<String, String> bookmarkSqlMap = null;
				try {
					bookmarkSqlMap = eoffenderDocumentService.getBookmarkSqlMapping(bookmarkList);

					logger.info("getBookmarkResolvedSqlMap bookmarkSqlMap: " + bookmarkSqlMap + ", offenderBookingNo "
							+ eoffenderDocumentRequest.getDocDetails().getOffenderBookingNo()
							+ ", offenderIdDisplay "
							+ eoffenderDocumentRequest.getDocDetails().getOffenderIdDisplay());

				} catch (Exception e) {
					logger.error("getBookmarkResolvedSqlMap bookmarkSqlMapping faild: " + e + ", offenderBookingNo "
							+ eoffenderDocumentRequest.getDocDetails().getOffenderBookingNo()
							+ ", offenderIdDisplay "
							+ eoffenderDocumentRequest.getDocDetails().getOffenderIdDisplay());
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
							+ eoffenderDocumentRequest.getDocDetails().getOffenderBookingNo()
							+ ", offenderIdDisplay "
							+ eoffenderDocumentRequest.getDocDetails().getOffenderIdDisplay());
				} catch (Exception e) {
					logger.error("getBookmarkResolvedSqlMap bookmark parameter Mapping faild: " + bookmarkParametersMap
							+ ", offenderBookingNo "
							+ eoffenderDocumentRequest.getDocDetails().getOffenderBookingNo()
							+ ", offenderIdDisplay "
							+ eoffenderDocumentRequest.getDocDetails().getOffenderIdDisplay());
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
						+ ", offenderBookingNo " + eoffenderDocumentRequest.getDocDetails().getOffenderBookingNo()
						+ ", offenderIdDisplay "
						+ eoffenderDocumentRequest.getDocDetails().getOffenderIdDisplay());
			}

			bookmarkResolvedSqlMap = docManageUtilities.getbookmarkResolvedSqlMap(bookmarkSqlMap, bookmarkParametersMap,
					eoffenderDocumentRequest,null);
		} catch (InterruptedException | ExecutionException | SecurityException e) {
			logger.error("getBookmarkResolvedSqlMap bookmark parameter Mapping faild: "
					+ ", offenderBookingNo : "
					+ eoffenderDocumentRequest.getDocDetails().getOffenderBookingNo()
					+ ", offenderIdDisplay : "
					+ eoffenderDocumentRequest.getDocDetails().getOffenderIdDisplay());
		}

		return bookmarkResolvedSqlMap;
	}

	@PostMapping(value = "/iwp/document/uploadFromGenerateDialog")
	public int uploadDocumentFromDialog(HttpServletRequest httpServletRequest,
										@RequestParam("key") String key,
										@RequestParam("objectType") String objectType,
			/*,@RequestParam("objectId") String oimsObjectId, 
			@RequestParam("recordCreator") String recordCreator,*/
										@RequestParam("offenderBookId") String offenderBookId
	) throws Exception {


		System.out.println("=====================Method get called");

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


		int result = uploadDocument(fileList,
				offenderBookId,
				templateList,
				documentUriList,
				templateUriList,
				null,
				objectType,
				updatedFileNames,
				null,
				httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/TRIM/"));

		try {
			fileInputStream.close();
			//fileInputStream1.close();
			Files.deleteIfExists(dbFile.toPath());
			Files.deleteIfExists(dbFile.toPath());
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		//filesOpenTimeMap.remove(dbFile.getName());
		//filesUploadedTimeMap.put(dbFile.getName(), System.currentTimeMillis());

		return result;
	}

	@PostMapping(value = "/iwp/document/uploadFromSchedular", produces = MediaType.ALL_VALUE)
	public int uploadDocumentFromSchedular(HttpServletRequest httpServletRequest, @QueryParam("key") String key) throws Exception {

		DBDocument dbDocument = eoffenderDocumentService.getTrimDocumentAsBlobFromDB(key);
		String docId = dbDocument.getDocumentId();
		String status = dmsService.getStatausOfDocument(docId);

		if(status.equals("Checked Out"))
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

			int result = uploadDocument(fileList, "fix it",templateList, documentUriList, templateUriList,
					/*eoffenderDocumentRequest.getDocDetails().getTrimUser()*/
					/*eoffenderDocumentRequest.getDocDetails().getObjectId().toString()*/ /*"2"*/ null,
					/*eoffenderDocumentRequest.getDocDetails().getObjectType()*/ null,
					/*eoffenderDocumentRequest.getDocDetails().getOsUser()*/ updatedFileNames,null,
					httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/TRIM/"));

			logger.info("uploadDocument result is "+result);

			if (result==1) {
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
			//filesOpenTimeMap.remove(dbFile.getName());
			//filesUploadedTimeMap.put(dbFile.getName(), System.currentTimeMillis());
			return result;
		}

		return -1;
	}

	@PostMapping(value = "/iwp/document/uploadFileIntoDB", produces = MediaType.ALL_VALUE)
	public String uploadFileIntoDB(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile multipartFile, @QueryParam("key") String key,  @QueryParam("fileName") String fileName) throws Exception {
		String s = manageDocService.saveFileFromWatcherToDB(multipartFile.getBytes(), key, fileName);
		return s;
	}

	@GetMapping(value = "/iwp/watcherInfo", produces = MediaType.ALL_VALUE)
	public ProfileCodes getWatcherAvailable() {
		ProfileCodes profileCode = new ProfileCodes();
		List<String> profileCodes = new ArrayList<String>();
		profileCodes.add("IWP");
		List<ProfileCodes> searchProfileCodes = omss40Service.searchProfileCodes(profileCodes);
		for(ProfileCodes prCode:searchProfileCodes) {
			if(prCode.getProfileCode().equalsIgnoreCase("WATCH_ENABLE")) {
				profileCode = prCode;
			}
		}
		return profileCode;
	}


	@PostMapping(value = "/iwp/uploadDoc")
	public BigDecimal uploadDoc(
			@RequestParam("file") List<MultipartFile> fileList,
			@RequestParam("offenderBookId") String offenderBookId,
			@RequestParam("templateId") List<String> templateList,
			@RequestParam("documentUriList") List<String> documentUriList,
			@RequestParam("templateUriList") List<String> templateUriList,
			@RequestParam("objectId") String oimsObjectId, 
			@RequestParam("objectType") String objectType,
			@RequestParam("fileName") List<String> updatedFileNames,
			HttpServletRequest httpServletRequest,
			String path)
			throws Exception {
		BigDecimal documentId;
		Map<String, String> uploadResult = new ConcurrentHashMap<>();
		List<FileLimits> fileSizelimits = new ArrayList<>();
		fileSizelimits = getEoffenderProfileValues();
		List<IwpDocuments> iwpDocuments = new ArrayList<>();
		String savedLocation = "";
		try {
			savedLocation = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/TRIM/");
		} catch(Exception ex) {
			savedLocation = path;
		}
		 
		int fileCount = fileList.size();
		for (int i = 0; i < fileCount; i++) {
			MultipartFile file = fileList.get(i);
			String fileExtension = docManageUtilities.getFileExtension("".equals(file.getOriginalFilename())?file.getName():file.getOriginalFilename());
			fileExtension = fileExtension.equals("")? ".docx": fileExtension;
			String templateId = templateList.get(i);
			String documentUri = documentUriList.get(i);
			String templateUri = templateUriList.get(i);
			String updatedFileName = updatedFileNames.get(i).replace("||", ",");
			
			//verify difference between file.getoriginalFilenname and file getname 
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				uploadResult.put(file.getOriginalFilename(),
						"UPLOAD FAILED :: " + "Filename contains invalid path sequence");
			}
			IwpDocuments iwpDocument = new IwpDocuments();
				iwpDocument.setTemplateId(new BigDecimal(templateUri));
			iwpDocument.setDocumentName(updatedFileName);
			iwpDocument.setDocumentContent(file.getBytes());
			iwpDocument.setActiveFlag("Y");
			iwpDocument.setDocumentId(!documentUri.equalsIgnoreCase("NEW_FILE")?new BigDecimal(documentUri):null);
			iwpDocument.setDocumentStatus("PUBLIC");
			if( offenderBookId.equals("undefined") || offenderBookId.equals("")) {
				iwpDocument.setOffenderBookId(null);
			}else {
				iwpDocument.setOffenderBookId(new BigDecimal(offenderBookId));
			}
			iwpDocument.setObjectId(oimsObjectId);
			iwpDocument.setObjectType(objectType);
			iwpDocument.setFileExtension(fileExtension);
			iwpDocuments.add(iwpDocument);
		}
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		List<IwpDocuments> insertList = iwpDocuments.stream().filter(i -> i.getDocumentId() == null).collect(Collectors.toList());
		documentId = manageDocService.uploadDocument(iwpDocuments, savedLocation,userName);
		if(documentId.compareTo(BigDecimal.ZERO) != 0) {
			IwpDocumentsCommitBean commitBean = new IwpDocumentsCommitBean();
			commitBean.setInsertList(insertList);
			String  authorization = httpServletRequest.getHeader("Authorization");
			prosmainService.enableTriggers(commitBean, authorization, "131");
		}
		return documentId;
	}
	@PostMapping(value = "/iwp/uploadSignedDoc")
	public int uploadSignedDocument(
			@RequestParam("file") MultipartFile file,
			@RequestParam("documentId") BigDecimal documentId,
			@RequestParam("fileName") String fileName,
			HttpServletRequest httpServletRequest,
			String path,
			@RequestHeader HttpHeaders headers)
			throws Exception {
		int liReturn = 0;
		
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			//byte[] out = replacePDFText(file);
			liReturn=manageDocService.insertSignedDocument(file.getBytes(), documentId, userName,fileName);
		} catch (Exception e) {
			logger.error("error in uploadSignedDocument Method: ", e);
		}
		return liReturn;
		
	
	}

	private byte[] replacePDFText(MultipartFile file) throws Exception {
		
		InputStream inputStream =  new BufferedInputStream(file.getInputStream());
		com.aspose.pdf.Document doc = new com.aspose.pdf.Document(inputStream);
		com.aspose.pdf.DocSaveOptions saveOptions = new com.aspose.pdf.DocSaveOptions();
		ByteArrayOutputStream outputStream =  new ByteArrayOutputStream();
		ByteArrayOutputStream outStreamPdf = new ByteArrayOutputStream();
		PdfSaveOptions pso = new PdfSaveOptions();
		
		// convert PDF to DOCX
		saveOptions.setFormat(DocSaveOptions.DocFormat.DocX);
		saveOptions.setMode(RecognitionMode.Flow);
        
		// Replace watermark text
		
        doc.save(outputStream,saveOptions);
        byte[] wordOut = outputStream.toByteArray();
        
        // Convert back to PDF
        com.aspose.words.Document wordDoc = new com.aspose.words.Document(new ByteArrayInputStream(wordOut), null);
        wordDoc.getRange().replace( "Created with a trial version of Syncfusion Essential" , "", new FindReplaceOptions());
        wordDoc.getRange().replace( "Evaluation Only. Created with Aspose.PDF. Copyright 2002-2019 Aspose Pty Ltd." , "", new FindReplaceOptions());
		pso.setCompliance(PdfCompliance.PDF_15);
		wordDoc.save(outStreamPdf, pso);
		byte[] outPdf = outStreamPdf.toByteArray();
        
        return outPdf;
		
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/eoffender/getStaffEliteDocDeleteRoles", method = RequestMethod.GET)
	public List<StaffMemberRoles> getStaffRoles() {
		List<StaffMemberRoles> recordList = new ArrayList<StaffMemberRoles>();
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		try {
			recordList = manageDocService.getStaffEliteDocDeleteRoles(authentication.getName());
		} catch (Exception e) {
			logger.error("In getStaffEliteDocDeleteRoles:"+ e.getMessage());
			return Collections.emptyList();
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/eoffender/deleteEliteDoc", method = RequestMethod.POST)
	public @ResponseBody Integer deleteEliteDoc(@RequestBody final IwpDocuments iwpDocuments) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			iwpDocuments.setModifyUserId(userName);
			liReturn = manageDocService.deleteEliteDoc(iwpDocuments);
		} catch (Exception e) {
			logger.error("In method deleteEliteDoc"+ e.getMessage());
		}
		return liReturn;
	}
}
