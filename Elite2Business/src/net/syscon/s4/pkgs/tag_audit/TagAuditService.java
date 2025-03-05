package net.syscon.s4.pkgs.tag_audit;

import java.util.List;
import java.util.Map;

import net.syscon.s4.sa.audit.SysTagAuditFormGetUserDetail;
import net.syscon.s4.sa.audit.SysTagAuditFormGetsessiondetail;
import net.syscon.s4.sa.audit.maintenance.AllAuditPolicies;

public interface TagAuditService {

	Integer disableAll(final String pPolicyName);

	Integer createPolicy(final AllAuditPolicies searchBean, final String statType);

	Integer dropPolicy(final AllAuditPolicies searchBean);

	Integer enableAll(final String pPolicyName);

	Integer dropAll(final String pPolicyName);

	Integer createAll(final String pOwner);

	Map<String, List<SysTagAuditFormGetsessiondetail>> formGetsessiondetail(final Long psessionid);

	List<SysTagAuditFormGetsessiondetail> getsessiondetails(final Long psessionid);

	List<SysTagAuditFormGetUserDetail> formGetuserdetail(SysTagAuditFormGetUserDetail bean);
	
	Object getUserDetails(String userId);
	
	Boolean checkArray(Object myArray, Integer pIndex);
}