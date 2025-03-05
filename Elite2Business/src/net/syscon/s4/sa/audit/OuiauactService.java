package net.syscon.s4.sa.audit;
import java.text.ParseException;
import java.util.List;

import net.syscon.s4.common.beans.StaffMembers;
/**
 * Interface OuiauactService 
 */
public interface OuiauactService  {
	List<StaffMembers> rgStfMemberRecordGroup() ;

	Integer getUserDetailCommit(SysTagAuditFormGetUserDetailCommitBean commitBean) ;

	List<SysTagAuditFormGetUserDetail> getUserDetailExecuteQuery(SysTagAuditFormGetUserDetail objSysTagAuditFormGetuserdetail) throws ParseException ;

}
