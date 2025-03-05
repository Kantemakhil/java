package net.syscon.s4.sa.audit;
import java.util.List;

import net.syscon.s4.im.beans.OmsModules;
/**
 * Interface OuiausesRepository
 */
public interface OuiausesRepository {
	List<OmsModules> createFormGlobals(final OmsModules paramBean);

	List<SysTagAuditFormGetsessiondetail> getSessionDetailExecuteQuery(final SysTagAuditFormGetsessiondetail objSearch) ;
	
	List<SysTagAuditFormGetsessiondetail> getDateAndTime(final Long sessionId);
	

}
