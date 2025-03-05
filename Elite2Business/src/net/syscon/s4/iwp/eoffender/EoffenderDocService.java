package net.syscon.s4.iwp.eoffender;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.exceptions.UnirestException;

import net.syscon.s4.eoffender.beans.DBDocument;
import net.syscon.s4.eoffender.beans.Document;
import net.syscon.s4.eoffender.beans.DownloadedFile;
import net.syscon.s4.eoffender.beans.EoffenderSqlParameter;
import net.syscon.s4.eoffender.beans.EoffenderTemplate;
import net.syscon.s4.im.beans.IwpDocumentsCommitBean;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.iwp.beans.ManageDocumentRequest;

@Service
public interface EoffenderDocService {

	List<String> getBookmarkList(ManageDocumentRequest eoffenderDocumentRequest);

	Map<String, String> getBookmarkSqlMapping(List<String> bookmarkList);

	Map<String, List<EoffenderSqlParameter>> getBookmarkParameterMapping(List<String> bookmarkList);

	Map<String, List<String>> getBookmarkValueMap(Map<String, String> bookmarkResolvedSqlMap, String userName);

	List<EoffenderTemplate> getTemplates(String Module, List<String> staffRoleList, String activeFlag);

	List<String> getStaffRoles(String staffId);
	
	List<String> getStaffRoleCodes(String staffId);

	Optional<String> generateDocumentName();

	DownloadedFile downLoadFileFromTRIM(String templateUri, String trimUser) throws UnirestException, IOException;
	
	Document getPropertiesFromFile(String saveDir, String fileExt) throws IOException, InvalidFormatException;
	
	void saveFileToDB(Document document, byte[] byteArray) throws IOException;
	
	DBDocument getTrimDocumentAsBlobFromDB(String documentId);
	
	boolean insertEditedFile(Document document);
		
	String verifyEditedDocCheckedIn(String docId);
	
	String updateDocStatus(BigDecimal id, String status);
	
	String verifyGeneratedDocVerification(String docId);

	List<IwpTemplates> getIWPTemplatesByType(String templateType); 
	
	Integer documentsDataUpdate(IwpDocumentsCommitBean documentCommitBean);

	String getModuleAccess(String moduleName, String userId);
}
