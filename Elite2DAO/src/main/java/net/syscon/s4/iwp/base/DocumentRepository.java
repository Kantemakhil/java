package net.syscon.s4.iwp.base;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.IwpDocuments;
import net.syscon.s4.im.beans.IwpTemplates;

public interface DocumentRepository {

	List<IwpDocuments> getIWPDocumentList(Long offenderBookId, String moduleName);


	List<IwpDocuments> getIWPObjectDocumentList(String objectId, String moduleName);

	IwpTemplates getTemplateAsBlobFromDB(Long templateId);

	IwpDocuments getIwpDocument (BigDecimal documentId);

	IwpTemplates getIwpTemplate (BigDecimal documentId);

	int[] uploadDocument(List<IwpDocuments> iwpDocuments);

	int[] uploadTemplate(List<IwpTemplates> iwpDocuments);

	String updateDocStatus(BigDecimal id, String status,String fileName,String userName) throws Exception ;

	BigDecimal getTempDocId();

	String insertFileByteArrayFromWatcher(byte[] fileByteArray, String key, String fileName) throws Exception;


	List<IwpDocuments> getIWPDocumentObjectOffBkgList(Long offenderBookingId, String objectId, String moduleName);

	VHeaderBlock getOffenderDetails(Long offenderBookId,String userName);

	Long getDocId();
	
	BigDecimal getIwpDocSeq();
	BigDecimal uploadDoc(List<IwpDocuments> iwpDocuments);


	Integer insertSignedDoc(byte[] fileByteArray, BigDecimal documenId, Date modifyDate, String modifyUser,String fileName);
	
	List<IwpDocuments> getIwpDocumentList(Long offenderBookId);
	
	List<StaffMemberRoles> getStaffEliteDocDeleteRoles(String userId);
	Integer deleteEliteDoc(IwpDocuments iwpDocuments);

}