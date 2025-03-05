package net.syscon.s4.globalconfiguration;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReferenceCodesCommitBean;
import net.syscon.s4.common.beans.ReferenceDomains;
import net.syscon.s4.common.beans.ReferenceDomainsCommitBean;

public interface OumrcodeService  {
	ReferenceDomains cguvchkReferenceDomainsPk(ReferenceDomains paramBean);

	ReferenceCodes cguvchkReferenceCodesPk(ReferenceCodes paramBean);

	Integer refCodeCommit(ReferenceCodesCommitBean CommitBean);

	Integer refDmnCommit(ReferenceDomainsCommitBean commitBean) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

	List<ReferenceCodes> refCodeExecuteQuery(ReferenceCodes objReferenceCodes);

	List<ReferenceDomains> cgrichkReferenceDomains(ReferenceDomains paramBean) ;

	List<ReferenceDomains> refDmnExecuteQuery(ReferenceDomains objReferenceDomains);

}
