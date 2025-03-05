package net.syscon.s4.sa.audit.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;

/**
 * Interface OumauditRepository
 */
public interface OumauditRepository {

	List<AllAuditPolicies> allAuditPoliciesExecuteQuery(AllAuditPolicies objAllAuditPolicies);

	List<ReferenceCodes> rgDbObjectsRecordGroup();

	int enableOrDisablePolicy(AllAuditPolicies searchBean);

	int createPolicy(AllAuditPolicies searchBean, String statType);

	boolean checkPolicyExists(String objectName);

	int dropPolicy(AllAuditPolicies searchBean);

	int disableAll();

	int enableAll();

	int dropAll();

	int createAll();

	List<ReferenceCodes> getAllTableNames();

}
