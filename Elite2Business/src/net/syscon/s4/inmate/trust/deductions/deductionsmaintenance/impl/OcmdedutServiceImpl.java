package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.DeductionTypesCommitBean;
import net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.OcmdedutRepository;
import net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.OcmdedutService;

/**
 * Class OcmdedutServiceImpl
 */
@Service
public class OcmdedutServiceImpl extends BaseBusiness implements OcmdedutService {

	@Autowired
	private OcmdedutRepository ocmdedutRepository;

	/**
	 * Creates new OcmdedutServiceImpl class Object
	 */
	public OcmdedutServiceImpl() {
		// OcmdedutServiceImpl;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public List<ReferenceCodes> cgfkchkDedTypeDedTypeRef(final ReferenceCodes paramBean) {
		return ocmdedutRepository.cgfkchkDedTypeDedTypeRef(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public List<ReferenceCodes> cgfkchkDedTypeDedType(final ReferenceCodes paramBean) {
		return ocmdedutRepository.cgfkchkDedTypeDedType(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public List<ReferenceCodes> cgfkchkDedTypeDedTypededutCat(final ReferenceCodes paramBean) {
		return ocmdedutRepository.cgfkchkDedTypeDedTypededutCat(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public List<TransactionTypes> cgrichkDeductionTypes(final TransactionTypes paramBean) {
		return ocmdedutRepository.cgrichkDeductionTypes(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<DeductionTypes> dedTypeExecuteQuery(final DeductionTypes searchRecord) {
		return ocmdedutRepository.dedTypeExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 */
	@Transactional
	public Integer dedTypeCommit(final DeductionTypesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = ocmdedutRepository.dedTypeInsertDeductionTypes(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = dedTypeUpdateDeductionTypes(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = ocmdedutRepository.dedTypeDeleteDeductionTypes(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Update the records from database table
	 *
	 * @param lstOffenderAlerts
	 */
	@Transactional
	public Integer dedTypeUpdateDeductionTypes(final List<DeductionTypes> lstOffenderAlerts) {
		for (final DeductionTypes obj : lstOffenderAlerts) {
			if (obj.getActiveFlag() != null && obj.getActiveFlag().equals("true")) {
				obj.setActiveFlag("Y");
			} else {
				obj.setActiveFlag("N");
			}
			if (obj.getCaseloadRestrictedFlag() != null && obj.getCaseloadRestrictedFlag().equals("true")) {
				obj.setCaseloadRestrictedFlag("Y");
			} else {
				obj.setCaseloadRestrictedFlag("N");
			}
			if (obj.getUpdateAllowedFlag() != null && obj.getUpdateAllowedFlag().equals("true")) {
				obj.setUpdateAllowedFlag("Y");
			} else {
				obj.setUpdateAllowedFlag("N");
			}
		}
		return ocmdedutRepository.dedTypeUpdateDeductionTypes(lstOffenderAlerts);
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> cgfkDedTypeCaseloadCodeRecordGroup() {
		return ocmdedutRepository.cgfkDedTypeCaseloadCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> cgfkDedTypeDeductionCategoRecordGroup() {
		return ocmdedutRepository.cgfkDedTypeDeductionCategoRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> cgfkDedTypeFromBalanceTypRecordGroup() {
		return ocmdedutRepository.cgfkDedTypeFromBalanceTypRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<DeductionTypes> rgParentDeductionTypeRecordGroup(final String deductionType) {
		List<DeductionTypes> returnList = ocmdedutRepository.rgParentDeductionTypeRecordGroup(deductionType);
		for (DeductionTypes deductionTypes : returnList) {
			deductionTypes.setDeductionDesc(deductionTypes.getDescription());
			deductionTypes.setDescription(deductionTypes.getCode());
		}
		return returnList;
	}

	/**
	 * This method is used to validation
	 */
	public String dedCodeValidation(final String dedCode) {
		return ocmdedutRepository.dedCodeValidation(dedCode);
	}

	/**
	 * This method is used to validation
	 */
	public Integer deleteDedTypeValidation(final String dedCode) {
		Integer returnData = 0;
		Integer delTransactionTypes = ocmdedutRepository.deleteTransactionTypes(dedCode);
		if (delTransactionTypes > 0) {
			return 1;
		}
		Integer commDelTransactionTypes = ocmdedutRepository.commDeleteTransactionTypes(dedCode);
		if (commDelTransactionTypes > 0) {
			return 2;
		}
		Integer delDeductionLimitTypes = ocmdedutRepository.deleteDeductionLimitTypes(dedCode);
		if (delDeductionLimitTypes > 0) {
			return 3;
		}
		Integer delOffenderSentObligations = ocmdedutRepository.deleteOffenderSentObligations(dedCode);
		if (delOffenderSentObligations > 0) {
			return 4;
		}
		Integer delOffenderDeductions = ocmdedutRepository.deleteOffenderDeductions(dedCode);
		if (delOffenderDeductions > 0) {
			return 5;
		}
		Integer csldDedProfiles = ocmdedutRepository.caseloadDeductionProfiles(dedCode);
		if (csldDedProfiles > 0) {
			return 6;
		}
		return returnData;
	}

}