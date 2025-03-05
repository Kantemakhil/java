package net.syscon.s4.iwp.base;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.eoffender.beans.Document;
import net.syscon.s4.eoffender.beans.DocumentRequestBean;
import net.syscon.s4.im.beans.IwpDocuments;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.iwp.beans.GeneratedDoc;
import net.syscon.s4.iwp.beans.ManageDocumentRequest;

/**
 * Document Management Service
 * @author ankur.gupta
 *
 */
public interface ManageDocService {

	GeneratedDoc generateDocument(ManageDocumentRequest manageDocumentRequest,Map<String, Object> objectDataMap);

	List<Document> listDocuments(DocumentRequestBean documentRequestBean);

	IwpDocuments viewDocument(BigDecimal documentId, String path) throws IOException;

	Document getPropertiesFromFile(String saveDir, String fileExt) throws IOException, InvalidFormatException;

	int[] uploadDocuments(List<IwpDocuments> iwpDocuments, String pathToDirectory,String user);

	int[] uploadTemplates(List<IwpTemplates> iwpDocuments, String pathToDirectory);

	String updateDocumentStatus(String recordNumber,String status,String fileName,String user);

	BigDecimal getTempDocId();

	String saveFileFromWatcherToDB(byte[] fileByteArray, String key, String fileName) throws Exception;

	public IwpDocuments getEditableDocument(BigDecimal documentId, String path, String user) throws Exception;

	VHeaderBlock getOffenderDetails(Long offenderBookId,String userName);

	Long getDocId();
	
	BigDecimal uploadDocument(List<IwpDocuments> iwpDocuments, String pathToDirectory,String userName);

	/*String cancelDocument(String documentUri,String trimUser);

	String getURIOffenderManagmentFile(String min, String trimUser, String offenderName, String dob);

	String getClassficationFromTemlateId(String templateId,String trimUser);

	Map<String, String> isSubfolderExist(String containerURI, String objectId, boolean isTemplate, String trimUser);

	String createSubFolder(String containerRecordNumber, String title, String classification,String offenedrBookingNo, String offenderIdDisplay, String trimUser);

	String getRecordNumberForURI(String uri, String trimUser);

	String updateDocument(String recordType, String containerRecordNumber, String title, String classification,
			 String filePath, String documentUri, String offenderIdDisplay, String offenderBookingNo, String trimUser) throws IOException;

	String createDocument(String recordType, String containerRecordNumber, String title, String classification,
			 String filePath, String templateName, String objectId, String objectType, String recordCreator, String offenderIdDisplay, String offenderBookingNo,boolean isFolder,String trimUser) throws IOException;

	String checkedOutDocument(String documentUri, String trimUser);

	boolean isCheckedOutStatus(String documentUri,String trimUser);

	String finalisedDocument(String documentUri,String trimUser);

	RecordAccessControl getSecurityAccessControlData(String containerURI, String objectId, boolean isFolder);

	String getLocationUri(String locaton, String trinUser);

	Map<String, String> getTemplateUriIdMap(List<EoffenderTemplate> templateList, String trimUser);
	
	public Map<String, String> partitionedTemplateUriMap(List<EoffenderTemplate> templateList, String trimUser);

	Map<String, String> getClassficationUriForFolder(String folderNo,String trimUser);
	
	org.apache.http.HttpResponse viewTRIMFile(String documentUri, String trimUser) throws ClientProtocolException, IOException;

	String getStatausOfDocument(String documentId);*/
	
	int insertSignedDocument(byte[] fileByteArray, BigDecimal documenId, String modifyUser,String fileName);
	
	List<StaffMemberRoles> getStaffEliteDocDeleteRoles(String userId);
	
	Integer deleteEliteDoc(IwpDocuments iwpDocuments);

}
