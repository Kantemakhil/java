package net.syscon.s4.inmate.trust.financialsmaintenance.subaccounts.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.InstitutionMiniBalances;
import net.syscon.s4.common.beans.InstitutionMiniBalancesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.trust.financialsmaintenance.subaccounts.OtmisambRepository;
import net.syscon.s4.inmate.trust.financialsmaintenance.subaccounts.OtmisambService;

/**
 * Creates new OtmisambServiceImpl class Object
 */
@Service
public class OtmisambServiceImpl extends BaseBusiness implements OtmisambService {

	@Autowired
	private OtmisambRepository otmisambRepository;
	
	public OtmisambServiceImpl() {
//		OtmisambServiceImpl
	}

	/**
	 * Fetch the records from database table method:instMnbalExecuteQuery
	 * 
	 * @param searchRecord
	 * @return List<InstitutionMiniBalances>
	 *
	 * 
	 */
	public List<InstitutionMiniBalances> instMnbalExecuteQuery(final InstitutionMiniBalances searchRecord) {
		return otmisambRepository.instMnbalExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table method:instMnbalCommit
	 * 
	 * @param lstINST_MNBAL
	 * @return Integer
	 * 
	 */
	@Transactional(rollbackFor = Exception.class)
	public String instMnbalCommit(final InstitutionMiniBalancesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (InstitutionMiniBalances obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());	
			}

			liReturn = otmisambRepository.instMnbalInsertInstitutionMiniBalances(commitBean.getInsertList());

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (InstitutionMiniBalances obj : commitBean.getUpdateList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());	
			}
			liReturn = otmisambRepository.instMnbalUpdateInstitutionMiniBalances(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (InstitutionMiniBalances obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());	
			}
			liReturn = otmisambRepository.instMnbalDeleteInstitutionMiniBalances(commitBean.getDeleteList());
		}
		return String.valueOf(liReturn);
	}

	/**
	 * This method is used to execute a record group
	 * method:cgfkInstMnbalCaseloadIdRecordGroup
	 * 
	 * @return List<Caseloads>
	 * 
	 */
	public List<Caseloads> cgfkInstMnbalCaseloadIdRecordGroup() {
		List<Caseloads> results = otmisambRepository.cgfkInstMnbalCaseloadIdRecordGroup();
		if (results != null) {
			results.forEach(data -> {
				data.setCaseloadType(data.getDescription());
				data.setDescription(data.getCode());
			});
		}
		return results;

	}

	/**
	 * This method is used to execute a record group
	 * method:cgfkInstMnbalAccountCodeRecordGroup
	 * 
	 * @return List<AccountCodes>
	 */
	public List<AccountCodes> cgfkInstMnbalAccountCodeRecordGroup() {
		List<AccountCodes> results = otmisambRepository.cgfkInstMnbalAccountCodeRecordGroup();
		if (results != null) {
			results.forEach(data -> {
				data.setDescription(data.getAccountName());
			});
		}
		return results;
	}

	/**
	 * This method is used to execute a record group
	 * method:cgfkInstMnbalAgyLocIdRecordGroup
	 * 
	 * @return List<CaseloadAgencyLocations>
	 */
	public List<CaseloadAgencyLocations> cgfkInstMnbalAgyLocIdRecordGroup(final String caseloadId) {
		List<CaseloadAgencyLocations> result = otmisambRepository.cgfkInstMnbalAgyLocIdRecordGroup(caseloadId);
		result.forEach(data -> {
			data.setAgyLocId(data.getDescription());
			data.setDescription(data.getCode());
		});
		return result;

	}

}