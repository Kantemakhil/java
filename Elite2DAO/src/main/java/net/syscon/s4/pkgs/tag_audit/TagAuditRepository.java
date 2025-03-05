package net.syscon.s4.pkgs.tag_audit;

import java.util.List;

import net.syscon.s4.sa.audit.maintenance.AllAuditPolicies;

public interface TagAuditRepository {

	List<AllAuditPolicies> allAuditPoliciesSelectOperation(final String pPolicyName);

	List<AllAuditPolicies> allAuditPolicies(final String pPolicyName);

	List<AllAuditPolicies> dropAllSelectOperation(final String pPolicyName);

	List<AllAuditPolicies> getObjNames(final String pOwner);
}