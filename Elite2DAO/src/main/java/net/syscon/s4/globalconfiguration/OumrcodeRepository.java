package net.syscon.s4.globalconfiguration;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReferenceDomains;

public interface OumrcodeRepository {
	ReferenceDomains cgfkchkRefDmnRfeDmnRef(ReferenceDomains paramBean);

	ReferenceDomains cguvchkReferenceDomainsPk(ReferenceDomains paramBean);

	Integer refCodeUpdateReferenceCodes(List<ReferenceCodes> lstReferenceCodes);

	Integer refDmnInsertReferenceDomains(List<ReferenceDomains> lstReferenceDomains);

	Integer refCodeInsertReferenceCodes(List<ReferenceCodes> lstReferenceCodes);

	List<ReferenceDomains> cgrichkReferenceDomains(ReferenceDomains paramBean);

	List<ReferenceCodes> refCodeExecuteQuery(ReferenceCodes objReferenceCodes);

	Integer refDmnUpdateReferenceDomains(List<ReferenceDomains> lstReferenceDomains);

	List<ReferenceDomains> refDmnExecuteQuery(ReferenceDomains objReferenceDomains);

	ReferenceCodes cguvchkReferenceCodesPk(ReferenceCodes paramBean);
	
	Integer refCodeInsertReferenceDomains(List<ReferenceCodes> lstReferenceCodes);
	
	Integer refCodeUpdateReferenceDomains(final List<ReferenceCodes> lstReferenceCodes);

}
