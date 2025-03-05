package net.syscon.s4.iwp.base;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import net.syscon.s4.eoffender.beans.DBDocument;
import net.syscon.s4.eoffender.beans.Document;
import net.syscon.s4.eoffender.beans.EoffenderSqlParameter;
import net.syscon.s4.eoffender.beans.EoffenderTemplate;
import net.syscon.s4.im.beans.IwpDocuments;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.iwp.beans.ManageDocumentRequest;

@Repository
public interface EoffenderDocumentRepository {
	
    List<String> getBookmarkList(ManageDocumentRequest eoffenderDocumentRequest);
	
	Map<String, String> getBookmarkSqlMapping(List<String> bookmarkList);
	
	Map<String, List<EoffenderSqlParameter>> getBookmarkParameterMapping(List<String> bookmarkList);
	
	Map<String, List<String>> getBookmarkValueMap(Map<String, String> bookmarkResolvedSqlMap, String userName);
	
	List<EoffenderTemplate> getTemplates(String moduleName, List<String> staffRoleList, String activeFlag);
	
	List<String> getStaffRoles(String staffId);
	
	List<String> getStaffRoleCodes(String staffId);
	
	Optional<String> generateDocumentName();
	
	void saveFileAsBlobInDB(Document document, byte[] bytes) throws IOException;
	
	DBDocument getTrimDocumentAsBlobFromDB(String documentId);

	boolean insertRecordforEditDocument(Document document);
	
	String verifyEditedDocCheckedIn(String docId);
	
	String updateDocStatus(BigDecimal id, String status) throws Exception; 
	String verifyGeneratedDocCommited(String docId);

	List<IwpTemplates> getIWPTemplatesByType(String templateType);
	
	Integer updateDocumentName(List<IwpDocuments> documentList);
	
	List<ModulePrivileges>  getUserModuleAccess(String moduleName, String userId);
}
