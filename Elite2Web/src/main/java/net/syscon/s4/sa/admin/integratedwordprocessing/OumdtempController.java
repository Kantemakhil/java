package net.syscon.s4.sa.admin.integratedwordprocessing;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aspose.words.BookmarkCollection;
import com.aspose.words.Field;
import com.aspose.words.FieldHyperlink;
import com.aspose.words.FieldType;
import com.aspose.words.SaveFormat;
import com.syncfusion.docio.WordDocument;
import com.syncfusion.ej2.wordprocessor.FormatType;
import com.syncfusion.ej2.wordprocessor.WordProcessorHelper;

import net.syscon.s4.common.CustomMultipartFile;
import net.syscon.s4.common.DocManageUtilities;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.eoffender.beans.FileLimits;
import net.syscon.s4.im.beans.IwpDocuments;
import net.syscon.s4.im.beans.IwpTemplateModules;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.inst.legalscreens.maintenance.OimcrtorService;
import net.syscon.s4.iwp.beans.DocDetails;
import net.syscon.s4.iwp.beans.ManageDocumentRequest;
import net.syscon.s4.sa.admin.beans.IwpBookmarks;
import net.syscon.s4.sa.admin.beans.IwpParameterMappings;
import net.syscon.s4.sa.admin.beans.IwpParameterMappingsCommitBean;
import net.syscon.s4.sa.admin.beans.IwpTemplateModulesCommitBean;
import net.syscon.s4.sa.admin.beans.IwpTemplateRoles;
import net.syscon.s4.sa.admin.beans.IwpTemplateRolesCommitBean;
import net.syscon.s4.sa.admin.beans.IwpTemplatesCommitBean;

/**
 * Class OumdtempController
 */
@EliteController
public class OumdtempController {
@Autowired
private OumdtempService oumdtempService;

@Autowired
private OimcrtorService oimcrtorService;

@Autowired
private DocManageUtilities docManageUtilities;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OumdtempController.class.getName());
	
	private static final Integer FILE_NOT_SUPPORTED = 422;

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumdtemp/aIwpTemplatesExecuteQuery", method=RequestMethod.POST)
	public List<IwpTemplates> aIwpTemplatesExecuteQuery(@RequestBody IwpTemplates searchBean) {
		List<IwpTemplates> searchResult = new ArrayList<>();
		try {
			searchResult = oumdtempService.aIwpTemplatesExecuteQuery(searchBean);
		} catch (Exception e) {
			IwpTemplates bean = new IwpTemplates();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oumdtemp/aIwpTemplatesCommit",method=RequestMethod.POST)
	public @ResponseBody Integer aIwpTemplatesCommit(@RequestBody IwpTemplatesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oumdtempService.aIwpTemplatesCommit(commitBean);
		}catch(Exception e){

			logger.error("Exception : Oumdtemp",e);
		}
		return liReturn;
	}

	/**
	 *getting rgRoles LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumdtemp/rgRolesRecordGroup",method=RequestMethod.GET)
	public List<OmsRoles> rgRolesRecordGroup() {
		List<OmsRoles> recordList =new ArrayList<>();
		try {
			recordList = oumdtempService.rgRolesRecordGroup();
		} catch(Exception e){
			OmsRoles obj = new OmsRoles();
			logger.error("Exception : Oumdtemp:",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgBmList LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumdtemp/rgBmListRecordGroup",method=RequestMethod.GET)
	public List<IwpBookmarks> rgBmListRecordGroup() {
		List<IwpBookmarks> recordList =new ArrayList<IwpBookmarks>();
		try {
			recordList = oumdtempService.rgBmListRecordGroup();
		} catch(Exception e){
			IwpBookmarks obj = new IwpBookmarks();
			logger.error("Exception : Oumdtemp:",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgOmsModule LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumdtemp/rgOmsModuleRecordGroup",method=RequestMethod.GET)
	public List<OmsModules> rgOmsModuleRecordGroup() {
		List<OmsModules> recordList =new ArrayList<OmsModules>();
		try {
			recordList = oumdtempService.rgOmsModuleRecordGroup();
		} catch(Exception e){
			OmsModules obj = new OmsModules();
			logger.error("Exception : Oumdtemp:",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgReportName LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumdtemp/rgReportNameRecordGroup",method=RequestMethod.GET)
	public List<OmsModules> rgReportNameRecordGroup() {
		List<OmsModules> recordList =new ArrayList<>();
		try {
			recordList = oumdtempService.rgReportNameRecordGroup();
		} catch(Exception e){
			OmsModules obj = new OmsModules();
			logger.error("Exception : Oumdtemp:",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgStaff LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumdtemp/rgStaffRecordGroup",method=RequestMethod.GET)
	public List<StaffMembers> rgStaffRecordGroup() {
		List<StaffMembers> recordList =new ArrayList<>();
		try {
			recordList = oumdtempService.rgStaffRecordGroup();
		} catch(Exception e){
			StaffMembers obj = new StaffMembers();
			logger.error("Exception : Oumdtemp:",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgObjectType LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumdtemp/rgObjectTypeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgObjectTypeRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = oumdtempService.rgObjectTypeRecordGroup();
		} catch(Exception e){
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oumdtemp:",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumdtemp/aIwpTagRelationsExecuteQuery", method=RequestMethod.POST)
	public List<IwpTemplateModules> aIwpTagRelationsExecuteQuery(@RequestBody IwpTemplateModules searchBean) {
		List<IwpTemplateModules> searchResult = new ArrayList<>();
		try {
			searchResult = oumdtempService.aIwpTagRelationsExecuteQuery(searchBean);
		} catch (Exception e) {
			IwpTemplateModules bean = new IwpTemplateModules();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oumdtemp/aIwpTagRelationsCommit",method=RequestMethod.POST)
	public @ResponseBody Integer aIwpTagRelationsCommit(@RequestBody IwpTemplateModulesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oumdtempService.aIwpTagRelationsCommit(commitBean);
		}catch(Exception e){

			logger.error("Exception : Oumdtemp",e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumdtemp/iwpParameterMappingsExecuteQuery", method=RequestMethod.POST)
	public List<IwpParameterMappings> iwpParameterMappingsExecuteQuery(@RequestBody IwpParameterMappings searchBean) {
		List<IwpParameterMappings> searchResult = new ArrayList<>();
		try {
			searchResult = oumdtempService.iwpParameterMappingsExecuteQuery(searchBean);
		} catch (Exception e) {
			IwpParameterMappings bean = new IwpParameterMappings();
			logger.error("Exception :",e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oumdtemp/iwpParameterMappingsCommit",method=RequestMethod.POST)
	public @ResponseBody Integer iwpParameterMappingsCommit(@RequestBody IwpParameterMappingsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oumdtempService.iwpParameterMappingsCommit(commitBean);
		}catch(Exception e){

			logger.error("Exception : Oumdtemp",e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumdtemp/templRolesExecuteQuery", method=RequestMethod.POST)
	public List<IwpTemplateRoles> templRolesExecuteQuery(@RequestBody IwpTemplates searchBean) {
		List<IwpTemplateRoles> searchResult = new ArrayList<>();
		try {
			searchResult = oumdtempService.iwpRolesExecuteQuery(searchBean);
		} catch (Exception e) {
			IwpTemplateRoles bean = new IwpTemplateRoles();
			logger.error("Exception :",e);
			searchResult.add(bean);
		}
		return searchResult;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumdtemp/whenActChangedEvent", method=RequestMethod.POST)
	public String whenActChangedEvent(@RequestBody IwpTemplates searchBean) {
		String returnValue = null;
		try {
			returnValue = oumdtempService.whenActChangedEvent(searchBean);
		} catch (Exception e) {
			logger.error("Exception :",e);
		}
		return returnValue;
	}
	
	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oumdtemp/templRolesCommit",method=RequestMethod.POST)
	public @ResponseBody Integer templRolesCommit(@RequestBody IwpTemplateRolesCommitBean commitBean) {
		Integer liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oumdtempService.templRolesCommit(commitBean);
		}catch(Exception e){

			logger.error("Exception : Oumdtemp",e);
		}
		return liReturn;
	}
	
	
	
	@PostMapping(value = "/iwp/templateUpload")
	public int uploadTemplate(
			@RequestParam("file") List<MultipartFile> fileList,
			@RequestParam("offenderBookId") String offenderBookId,
			@RequestParam("templateId") List<String> templateList,
			@RequestParam("objectId") String oimsObjectId, 
			@RequestParam("objectType") String objectType,
			@RequestParam("fileName") List<String> updatedFileNames,
			@RequestParam("docConvertion") String docConvertion, 
			@RequestParam("docEditor") String docEditor,
			HttpServletRequest httpServletRequest,
			String path)
			throws Exception {
		
		Map<String, String> uploadResult = new ConcurrentHashMap<>();
		List<FileLimits> fileSizelimits = new ArrayList<>();
		File downloadFile;
		List<String> extensionList = Arrays.asList(".docx", ".doc", ".dotx", ".dot");
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		List<IwpTemplates> iwpDocuments = new ArrayList<>();
		String savedLocation = "";
		try {
			savedLocation = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/TRIM/");
		} catch(Exception ex) {
			savedLocation = path;
		}
		 
		int fileCount = fileList.size();
		for (int i = 0; i < fileCount; i++) {
			MultipartFile file = fileList.get(i);
			
			String extension = docManageUtilities.getFileExtension("".equals(file.getOriginalFilename())?file.getName():file.getOriginalFilename());
			if((docEditor == null || "N".equals(docEditor)) && extension!=null && !extensionList.contains(extension.toLowerCase())) {
				return FILE_NOT_SUPPORTED;
			}
			
			String fileExtension = ".docx";//docManageUtilities.getFileExtension("".equals(file.getOriginalFilename())?file.getName():file.getOriginalFilename());
			String templateId = templateList.get(i);
//			String documentUri = documentUriList.get(i);
//			String templateUri = templateUriList.get(i);
			String updatedFileName = updatedFileNames.get(i).replace("||", ",");
			
			//verify difference between file.getoriginalFilenname and file getname 
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			if (fileName.contains("..")) {
				uploadResult.put(file.getOriginalFilename(),
						"UPLOAD FAILED :: " + "Filename contains invalid path sequence");
			}
			IwpTemplates iwpDocument = new IwpTemplates();
			iwpDocument.setTemplateId(new BigDecimal(templateList.get(i)));
			iwpDocument.setTemplateName(fileName);
			iwpDocument.setActiveFlag("Y");
			iwpDocument.setObjectType(objectType);
			iwpDocument.setModifyUserId(userName);
			iwpDocument.setFileExtension(fileExtension);
			if(docConvertion!=null && "Y".equals(docConvertion)) {
				downloadFile=saveFileToTempLoc(savedLocation, file.getBytes(), iwpDocument.getTemplateId()+"", iwpDocument.getFileExtension());
				ManageDocumentRequest manageDocumentRequest = new ManageDocumentRequest();
				manageDocumentRequest.setDocDetails(new DocDetails());
				Files.write(Paths.get(downloadFile.getAbsolutePath()), file.getBytes());
				
				Path destinationPath = convertDocumentTypeToDocx(manageDocumentRequest, downloadFile, iwpDocument.getFileExtension(), null,
						downloadFile.getAbsolutePath());
				Path newPath = Paths.get(destinationPath.toString());
				replaceMergeCodes(destinationPath.toString());
				byte[] documentContent = Files.readAllBytes(newPath);
				CustomMultipartFile customMultipartFile = new CustomMultipartFile(documentContent);
				customMultipartFile.transferTo(downloadFile);
				fileList.add(0, customMultipartFile);
				String sfdtFile = WordProcessorHelper.load(customMultipartFile.getInputStream(), FormatType.Docx,false);
				
				iwpDocument.setTemplateContent(sfdtFile.getBytes("Utf-8"));
			}else {
				iwpDocument.setTemplateContent(file.getBytes());
			}
			iwpDocuments.add(iwpDocument);
		}
		int[] success = oumdtempService.uploadTemplate(iwpDocuments, savedLocation);
		return success.length;
	}
	
	
	private void replaceMergeCodes(String documentPath) throws Exception {
		logger.info("==================================replaceMergeCodes =======================");
		com.aspose.words.Document asposeDocument = new com.aspose.words.Document(documentPath);
		BookmarkCollection bookMarkCollection = asposeDocument.getRange().getBookmarks();
		for (Field field : asposeDocument.getRange().getFields()) {
		    if (field.getType() == FieldType.FIELD_HYPERLINK) {
		        FieldHyperlink hyperlink = (FieldHyperlink) field;
		        hyperlink.unlink();
		    }
		}
		bookMarkCollection.forEach(bookMark -> {
			try {
				if(bookMark.getText()!=null && bookMark.getText().contains("<<")) {
					bookMark.setText("{{" + bookMark.getName() + "}}");
				}
				bookMark.remove();
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		});
		
		asposeDocument.save(documentPath);
	}
	private Path convertDocumentTypeToDocx(ManageDocumentRequest eoffenderDocumentRequest, File generatedFile,
			String trimFileExt, String dialogLink, String saveDir) throws Exception {
		Path destinationPath = null;
		com.aspose.words.Document asposeDocument = new com.aspose.words.Document(saveDir);
		String newFilePath = saveDir.replace(trimFileExt, DocManageUtilities.DOCX);
		asposeDocument.save(newFilePath, SaveFormat.DOCX);
		destinationPath = Paths.get(newFilePath);
		saveDir = newFilePath;
		try (OPCPackage opc = OPCPackage.open(saveDir);) {
			PackageProperties pp = opc.getPackageProperties();
			pp.setCategoryProperty("NEW_FILE" + "-" + eoffenderDocumentRequest.getTemplateId() + "-" + "NEW_RECORD"
					+ "-" + eoffenderDocumentRequest.getTemplateType() + "-" + dialogLink);
		}
		return destinationPath;
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
		return trimFile;
	}

	@GetMapping(value = "/iwp/document/viewTemplatefile", produces = MediaType.ALL_VALUE)
	public ResponseEntity viewIwpDocuments(@RequestParam("uri") String documentId, HttpServletRequest httpServletRequest) throws Exception {
		ResponseEntity repsonseEntity = null;
		HttpHeaders headerRes = new HttpHeaders();
		String savedLocation = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/TRIM/");
		try {
		if(documentId !=null && !documentId.equals("")) {
			IwpTemplates iwpDocuments = oumdtempService.viewTemplate(new BigDecimal(documentId), savedLocation);
			byte[] byteArray = iwpDocuments.getTemplateContent();
			String fileName = docManageUtilities.getFileNameFromContentDisposition(iwpDocuments.getTemplateName());
			String fileExt =".docx";
			if (DocManageUtilities.isEmpty(fileExt) ) {
				headerRes.setPragma("downloadfromTRIM2 No Content Found : URI "+ documentId);
				return new ResponseEntity<>(null, headerRes, HttpStatus.OK);
			}
			headerRes = docManageUtilities.getHttpHeadersForFileUpload(fileExt, fileName, true);
			repsonseEntity = new ResponseEntity<>(byteArray, headerRes, HttpStatus.OK);
			
			return repsonseEntity;
		} else {
			return null;
		}
		} catch (Exception e) {
			return new ResponseEntity<>(null, headerRes, HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumdtemp/getIwpDocCount", method = RequestMethod.GET)
	public Integer getIwpDocCount(@RequestParam final BigDecimal templateId) {
		Integer count = 0;
		try {
			count = oimcrtorService.iwpTempOnCheckDeleteMaster(templateId);
		} catch (final Exception e) {
			logger.error("Exception in getIwpDocCount:"+ e.getMessage());
		}
		return count;
	}


}