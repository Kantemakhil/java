package net.syscon.s4.sa.audit.maintenance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.sa.audit.maintenance.AllAuditPolicies;
import net.syscon.s4.sa.audit.maintenance.OumauditRepository;
import net.syscon.s4.sa.audit.maintenance.OumauditService;

/**
 * Class OumauditServiceImpl
 */
@Service
public class OumauditServiceImpl extends BaseBusiness implements OumauditService {

	@Autowired
	private OumauditRepository oumauditRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<AllAuditPolicies> allAuditPoliciesExecuteQuery(final AllAuditPolicies searchRecord) {
		return oumauditRepository.allAuditPoliciesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstALL_AUDIT_POLICIES
	 *
	 */
	/*
	 * @Transactional public Integer
	 * allAuditPoliciesCommit(<AllAuditPoliciesCommitBean commitBean) { int liReturn
	 * = 0; }
	 */
	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgDbObjectsRecordGroup() {
		return oumauditRepository.rgDbObjectsRecordGroup();

	}

	@Override
	public int enableOrDisablePolicy(final AllAuditPolicies searchBean) {
		return oumauditRepository.enableOrDisablePolicy(searchBean);
	}

	@Override
	public AllAuditPolicies createPolicy(final AllAuditPolicies searchBean) {
		String statType = "";
		if (searchBean.getSel().equals("Y")) {
			statType = statType.concat("SELECT,");
		}
		if (searchBean.getIns().equals("Y")) {
			statType = statType.concat("INSERT,");
		}
		if (searchBean.getUpd().equals("Y")) {
			statType = statType.concat("UPDATE,");
		}
		if (searchBean.getDel().equals("Y")) {
			statType = statType.concat("DELETE");
		}
		if (statType.endsWith("'")) {
			statType = statType.substring(0, statType.length() - 1);
		}
		final boolean policyExistFlag = oumauditRepository.checkPolicyExists(searchBean.getObjectName());
		if (policyExistFlag) {
			searchBean.setSealFlag("1");
			// Audit policy already exists for this table.
		} else {
			oumauditRepository.createPolicy(searchBean, statType);
			searchBean.setSealFlag("Success");
		}
		return searchBean;
	}

	@Override
	public int dropPolicy(final AllAuditPolicies searchBean) {
		return oumauditRepository.dropPolicy(searchBean);
	}

	@Override
	public int disableAll() {
		return oumauditRepository.disableAll();
	}

	@Override
	public int enableAll() {
		return oumauditRepository.enableAll();
	}

	@Override
	public int dropAll() {
		return oumauditRepository.dropAll();
	}

	@Override
	public int createAll() {
		return oumauditRepository.createAll();
	}

	@Override
	public List<ReferenceCodes> getAllTableNames() {
		return oumauditRepository.getAllTableNames();
	}

}