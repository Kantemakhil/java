package net.syscon.s4.sa.audit;
import java.util.List;
/**
 * Interface OuiausesService 
 */
public interface OuiausesService  {
	//Integer getSessionDetailCommit(SysTagAuditFormGetsessiondetailcommitBean CommitBean) ;

	List<SysTagAuditFormGetsessiondetail> getSessionDetailExecuteQuery(final SysTagAuditFormGetsessiondetail objSearch) ;

}
