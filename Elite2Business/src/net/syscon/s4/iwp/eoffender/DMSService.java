package net.syscon.s4.iwp.eoffender;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import net.syscon.s4.common.beans.trim.RecordAccessControl;
import net.syscon.s4.eoffender.beans.Document;
import net.syscon.s4.eoffender.beans.DocumentRequestBean;
import net.syscon.s4.eoffender.beans.EoffenderTemplate;

/**
 * Document Management Service
 * @author ankur.gupta
 *
 */
public interface DMSService {

	List<Document> listDocuments(DocumentRequestBean documentRequestBean);

	String cancelDocument(String documentUri,String trimUser);

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

	String getStatausOfDocument(String documentId);
	
	Integer getLanguageId();

}
