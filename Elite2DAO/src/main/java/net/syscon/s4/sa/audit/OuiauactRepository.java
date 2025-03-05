package net.syscon.s4.sa.audit;
import java.util.List;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.OmsModules;
/**
 * Interface OuiauactRepository
 */
public interface OuiauactRepository {
	List<OmsModules> createFormGlobals(OmsModules paramBean);

	List<StaffMembers> rgStfMemberRecordGroup() ;

	List<SysTagAuditFormGetUserDetail> getUserDetailExecuteQuery(SysTagAuditFormGetUserDetail objSysTagAuditFormGetuserdetail) ;
	List<SysTagAuditFormGetUserDetail> getuserActivityDetails(SysTagAuditFormGetUserDetail objSysTagAuditFormGetuserdetail) ;

}
