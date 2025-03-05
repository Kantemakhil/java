package net.syscon.s4.sa.audit.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;

/**
 * Interface OumauditService
 */
public interface OumauditService {
	List<AllAuditPolicies> allAuditPoliciesExecuteQuery(AllAuditPolicies objAllAuditPolicies);

	List<ReferenceCodes> rgDbObjectsRecordGroup();

	int enableOrDisablePolicy(AllAuditPolicies searchBean);

	AllAuditPolicies createPolicy(AllAuditPolicies searchBean);

	int dropPolicy(AllAuditPolicies searchBean);

	int disableAll();

	int enableAll();

	int dropAll();

	int createAll();

	List<ReferenceCodes> getAllTableNames();

}
