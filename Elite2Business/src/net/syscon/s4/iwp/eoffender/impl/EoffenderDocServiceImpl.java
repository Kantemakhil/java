package net.syscon.s4.iwp.eoffender.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.compress.utils.IOUtils;
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
import org.apache.poi.EmptyFileException;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.itextpdf.text.pdf.PdfReader;
import com.mashape.unirest.http.exceptions.UnirestException;

import net.syscon.s4.common.EoffenderStatusCodes;
import net.syscon.s4.common.EoffenderUtilities;
import net.syscon.s4.eoffender.beans.DBDocument;
import net.syscon.s4.eoffender.beans.Document;
import net.syscon.s4.eoffender.beans.DownloadedFile;
import net.syscon.s4.eoffender.beans.EoffenderSqlParameter;
import net.syscon.s4.eoffender.beans.EoffenderTemplate;
import net.syscon.s4.im.beans.IwpDocuments;
import net.syscon.s4.im.beans.IwpDocumentsCommitBean;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.iwp.base.impl.EoffenderDocumentRepositoryImpl;
import net.syscon.s4.iwp.base.impl.TempletRepositoryImpl;
import net.syscon.s4.iwp.beans.ManageDocumentRequest;
import net.syscon.s4.iwp.eoffender.EoffenderDocService;



@Service
public class EoffenderDocServiceImpl implements EoffenderDocService{
	
	private static Logger logger = LogManager.getLogger(EoffenderDocServiceImpl.class.getName());
	
	@Value("${trim.url}")
	private String trimUrl;

	@Autowired
	private EoffenderDocumentRepositoryImpl eOffenderDocumentRepository;
	
	@Autowired
	private TempletRepositoryImpl templateRepository;

	@Autowired
	private Environment env;

	@Override
	public List<String> getBookmarkList(ManageDocumentRequest manageDocumentRequest) {
		// TODO Auto-generated method stub
		return eOffenderDocumentRepository.getBookmarkList(manageDocumentRequest);
	}

	@Override
	public Map<String, String> getBookmarkSqlMapping(List<String> bookmarkList) {
		// TODO Auto-generated method stub
		return eOffenderDocumentRepository.getBookmarkSqlMapping(bookmarkList);
	}

	@Override
	public Map<String, List<EoffenderSqlParameter>> getBookmarkParameterMapping(List<String> bookmarkList) {
		// TODO Auto-generated method stub
		return eOffenderDocumentRepository.getBookmarkParameterMapping(bookmarkList);
	}

	@Override
	public Map<String, List<String>> getBookmarkValueMap(Map<String, String> bookmarkResolvedSqlMap, String userName) {
		return eOffenderDocumentRepository.getBookmarkValueMap(bookmarkResolvedSqlMap, userName);
	}

	@Override
	public List<EoffenderTemplate> getTemplates(String moduleName,  List<String> staffRoleList, String activeFlag) {
		return eOffenderDocumentRepository.getTemplates(moduleName, staffRoleList, activeFlag);
	}

	@Override
	public List<String> getStaffRoles(String staffId) {
		return eOffenderDocumentRepository.getStaffRoles(staffId);
	}

	@Override
	public Optional<String> generateDocumentName() {
		return eOffenderDocumentRepository.generateDocumentName();
	}

	@Override
	public DownloadedFile downLoadFileFromTRIM(String templateUri, String trimUser) throws UnirestException, IOException {
		return downLoadFileFromTRIM2(templateUri,trimUser);
	}
	
	
	private DownloadedFile downLoadFileFromTRIM2(String templateUri, String trimUser) throws UnirestException, IOException {
		DownloadedFile trimDownloadedFile = new DownloadedFile();
		logger.info("downloadDocument Downloading document ", templateUri);
		
		HttpClientBuilder clientHelper = HttpClientBuilder.create();
		
		CredentialsProvider creds = new BasicCredentialsProvider();
	    creds.setCredentials(AuthScope.ANY, new NTCredentials(env.getProperty("trim.apiUser"), env.getProperty("trim.apiPwd"), "", env.getProperty("trim.dev")));
	    clientHelper.setDefaultCredentialsProvider(creds);
	    
	    RequestConfig config = RequestConfig.custom().setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM)).build();
	    
	    
	    HttpClient httpClient = clientHelper.setDefaultRequestConfig(config).build();
              
        HttpGet get = new HttpGet(this.trimUrl+ "/Record/" + templateUri + "/File/document");

        get.setHeader("Content-type", "application/x-www-form-urlencoded");
        
        this.addHttpRequestImpersonationHeader(get, trimUser);
		org.apache.http.HttpResponse response = httpClient.execute(get);

		if (response.getStatusLine().getStatusCode() == EoffenderStatusCodes.NOT_FOUND_CODE) {
			logger.error("downloadDocument Template Not Found : "  + response.getStatusLine().getStatusCode() + ", Template "+ templateUri);
			trimDownloadedFile.setStatus(EoffenderUtilities.TEMPLATE_NOT_FOUND);
			return trimDownloadedFile;
		}
		
		if (response.getStatusLine().getStatusCode() == EoffenderStatusCodes.UNAUTHORIZED_ACCESS_CODE) {
			trimDownloadedFile.setStatus(EoffenderUtilities.UNAUTHORIZED_ACCESS);
			logger.error("downloadDocument Unauthorized Acccess : "  + response.getStatusLine().getStatusCode() + ", Template "+ templateUri);
			return trimDownloadedFile;
		}

		// ADD exception for NULL response *************************		

		if (response.getFirstHeader("content-type") != null &&  !EoffenderUtilities.isSupportedTemplateType(response.getFirstHeader("content-type").getValue())) {
			logger.error("downloadDocument Template Type Not Supported ", templateUri);
			trimDownloadedFile.setStatus(EoffenderUtilities.TEMPLATE_NOT_SUPPORTED);
			return trimDownloadedFile;
		}
		
		trimDownloadedFile.setFileByteArray(IOUtils.toByteArray(response.getEntity().getContent()));
		trimDownloadedFile.setFileType(response.getFirstHeader("content-type").getValue());
        
		return trimDownloadedFile;
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
	
	/*@Override
	public BigDecimal getTempDocId() {
		return eOffenderDocumentRepository.getTempDocId();
	}*/
	
	@Override
	public boolean insertEditedFile(Document document) {
		return eOffenderDocumentRepository.insertRecordforEditDocument(document);
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
	
	@Override
	public String updateDocStatus(BigDecimal id, String status)  {
		try {
			return eOffenderDocumentRepository.updateDocStatus(id, status);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
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
	
	private void addHttpRequestImpersonationHeader (HttpRequest request, String trimUser) {
		request.setHeader("userToImpersonate",trimUser);
		return;
	}

	@Override
	public void saveFileToDB(Document document, byte[] bytes) throws IOException {
		eOffenderDocumentRepository.saveFileAsBlobInDB(document, bytes);		
	}
	
	//@Override
	public DBDocument getTemplteDocumentAsBlobFromDB(String documentId) {
		
		return eOffenderDocumentRepository.getTrimDocumentAsBlobFromDB(documentId);
	}

	@Override
	public DBDocument getTrimDocumentAsBlobFromDB(String documentId) {
		
		return eOffenderDocumentRepository.getTrimDocumentAsBlobFromDB(documentId);
	}

	
	
	@Override
	public String verifyEditedDocCheckedIn(String docId) {
		return eOffenderDocumentRepository.verifyEditedDocCheckedIn(docId);
	}
	
	@Override
	public String verifyGeneratedDocVerification(String docId) {
		return eOffenderDocumentRepository.verifyGeneratedDocCommited(docId);
	}

	@Override
	public List<IwpTemplates> getIWPTemplatesByType(String templateType) {
		return eOffenderDocumentRepository.getIWPTemplatesByType(templateType);
	}

	@Override
	public Integer documentsDataUpdate(IwpDocumentsCommitBean commitBean) {
		Integer liReturn=0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (IwpDocuments bean : commitBean.getUpdateList()) {				
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = eOffenderDocumentRepository.updateDocumentName(commitBean.getUpdateList());
			
		}
		return liReturn;
	}

	@Override
	public List<String> getStaffRoleCodes(String staffId) {
		return eOffenderDocumentRepository.getStaffRoleCodes(staffId);
		}
	
	@Override
	public String getModuleAccess(String moduleName, String userId) {
		List<ModulePrivileges>  modulePrivileges;
		modulePrivileges=eOffenderDocumentRepository.getUserModuleAccess(moduleName, userId);
		String access="Q";
		for (ModulePrivileges mode : modulePrivileges) {
			if(mode.getAccessPrivilege()!=null && "A".equals(mode.getAccessPrivilege()) ){
				access=mode.getAccessPrivilege();
				break;
			}
			
		}
		
		return access;
	}
	
}
