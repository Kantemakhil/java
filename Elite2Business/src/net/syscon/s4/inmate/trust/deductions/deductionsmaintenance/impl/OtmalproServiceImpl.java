package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.CaseloadDeductionDetailsCommitBean;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfilesCommitBean;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.OtmalproRepository;
import net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.OtmalproService;

/**
 * Class OtmalproServiceImpl
 */
@Service
public class OtmalproServiceImpl implements OtmalproService {

	@Autowired
	private OtmalproRepository otmalproRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<CaseloadDeductionProfiles> csldDpExecuteQuery(CaseloadDeductionProfiles searchRecord) {
		List<CaseloadDeductionProfiles> returnLits = otmalproRepository.csldDpExecuteQuery(searchRecord);
		for (final CaseloadDeductionProfiles obj : returnLits) {
			if (obj.getDeductionType() != null) {
				String fromBalType = otmalproRepository.getfromBalType(obj.getDeductionType());

				if (fromBalType != null) {
					String fromBalTypeDesc = otmalproRepository.getFormBalDsc(fromBalType);
					if (fromBalTypeDesc != null) {
						obj.setNbtModifyUserId(fromBalTypeDesc);
					}
				}
			}
			if (obj.getMaxMonthlyAmount() != null && obj.getMaxTotalAmount() == null) {
				obj.setCoCreditWhenIndigentFlag("N");
			} else if (obj.getMaxMonthlyAmount() == null && obj.getMaxTotalAmount() != null) {
				obj.setCoCreditWhenIndigentFlag("N");
			} else if (obj.getMaxMonthlyAmount() == null && obj.getMaxTotalAmount() != null) {
				obj.setCoCreditWhenIndigentFlag("Y");

			}

		}
		return returnLits;

	}

	public String getfromBalDesc(String deductionType) {
		String fromBal = otmalproRepository.getfromBalDesc(deductionType);
		if (fromBal != null) {
			String fromBalTypeDesc = otmalproRepository.getFormBalDsc(fromBal);
			if (fromBalTypeDesc != null) {
				fromBal = fromBalTypeDesc;

			}
		}
		return fromBal;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCSLD_DP
	 *
	 * @throws SQLException
	 */
	@Transactional
	public String csldDpCommit(CaseloadDeductionProfilesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (CaseloadDeductionProfiles obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = csldDpInsertCaseloadDeductionProfiles(commitBean.getInsertList());
			return String.valueOf(liReturn);
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (CaseloadDeductionProfiles obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otmalproRepository.csldDpUpdateCaseloadDeductionProfiles(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (CaseloadDeductionProfiles obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otmalproRepository.csldDpDeleteCaseloadDeductionProfiles(commitBean.getDeleteList());
		}
		return String.valueOf(liReturn);
	}

	private Integer csldDpInsertCaseloadDeductionProfiles(List<CaseloadDeductionProfiles> insertList) {
		Integer retVal = 0;
		for (CaseloadDeductionProfiles obj : insertList) {
			BigDecimal extPrt = otmalproRepository.getExternalPriority(obj.getCaseloadId());
			obj.setExternalPriorityNo(extPrt);
			obj.setInternalPriorityNo(BigDecimal.valueOf(1));
			List<CaseloadDeductionProfiles> list = new ArrayList<CaseloadDeductionProfiles>();
			list.add(obj);
			retVal = otmalproRepository.csldDpInsertCaseloadDeductionProfiles(list);
		}
		return retVal;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<CaseloadDeductionDetails> csldDdExecuteQuery(CaseloadDeductionDetails searchRecord) {
		List<CaseloadDeductionDetails> deductionDeatilsList = otmalproRepository.csldDdExecuteQuery(searchRecord);

		return deductionDeatilsList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCSLD_DD
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer csldDdCommit(CaseloadDeductionDetailsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (CaseloadDeductionDetails obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = csldDdInsertCaseloadDeductionDetails(commitBean.getInsertList());
			return liReturn;
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (CaseloadDeductionDetails obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = csldDdUpdateCaseloadDeductionDetails(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (CaseloadDeductionDetails obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = csldDdDeleteCaseloadDeductionDetails(commitBean.getDeleteList());
		}
		return liReturn;
	}

	private Integer csldDdDeleteCaseloadDeductionDetails(List<CaseloadDeductionDetails> deleteList) {
		for (CaseloadDeductionDetails obj : deleteList) {
			List<CaseloadDeductionProfiles> percentageExternalList = otmalproRepository
					.gtePercentageandExternalPriority(obj.getCaseloadId(), obj.getDeductionType());
			for (CaseloadDeductionProfiles objPercent : percentageExternalList) {
				if (!"Y".equals(obj.getDialogFlg())) {
					if (obj.getDeductionType() != null && obj.getReceiptTxnType() != null && obj.getFoFlag() != null
							&& "Original Balance".equals(obj.getNbtModifyUserId())) {
						if (objPercent.getExternalPriorityNo().compareTo(BigDecimal.ZERO) != 0
								&& objPercent.getPercentage().compareTo(BigDecimal.ZERO) != 0) {
							return 6;
						}
					}
				}
			}
		}
		return otmalproRepository.csldDdDeleteCaseloadDeductionDetails(deleteList);
	}

	private Integer csldDdUpdateCaseloadDeductionDetails(List<CaseloadDeductionDetails> updateList) {
		for (CaseloadDeductionDetails obj : updateList) {
			List<CaseloadDeductionProfiles> percentageExternalList = otmalproRepository
					.gtePercentageandExternalPriority(obj.getCaseloadId(), obj.getDeductionType());
			for (CaseloadDeductionProfiles objPercent : percentageExternalList) {
				if (!"Y".equals(obj.getDialogFlg())) {
					if (obj.getDeductionType() != null && obj.getReceiptTxnType() != null && obj.getFoFlag() != null
							&& "Original Balance".equals(obj.getNbtModifyUserId())) {
						if (objPercent.getExternalPriorityNo().compareTo(BigDecimal.ZERO) != 0
								&& objPercent.getPercentage().compareTo(BigDecimal.ZERO) != 0) {
							return 6;
						}
					}
				}
			}
		}
		return otmalproRepository.csldDdUpdateCaseloadDeductionDetails(updateList);
	}

	private Integer csldDdInsertCaseloadDeductionDetails(List<CaseloadDeductionDetails> insertList) {
		Integer listReturn=0;
		for(CaseloadDeductionDetails obj :insertList){
		List<CaseloadDeductionProfiles> percentageExternalList =otmalproRepository.gtePercentageandExternalPriority(obj.getCaseloadId(),obj.getDeductionType());
		for(CaseloadDeductionProfiles objPercent: percentageExternalList){
			if(!"Y".equals(obj.getDialogFlg())){
			if(obj.getDeductionType()!=null && obj.getReceiptTxnType()!=null && obj.getFoFlag()!=null
					&& "Original Balance".equals(obj.getNbtModifyUserId())){
				if( objPercent.getExternalPriorityNo().compareTo(BigDecimal.ZERO)!=0 &&
						objPercent.getPercentage().compareTo(BigDecimal.ZERO)!=0 ){
					return 6;
				}	
			}
			}			
		}
		}
		
		listReturn= otmalproRepository.csldDdInsertCaseloadDeductionDetails(insertList);
		
		if(listReturn == 1){
			for(CaseloadDeductionDetails obj:insertList){
				if(obj.getReceiptTxnType()!=null){
					BigDecimal percentage =otmalproRepository.getMaxPercenatge(obj.getCaseloadId(),obj.getDeductionType());
					if(percentage!=null){
						otmalproRepository.updateCaseloadDeductionProfiles(obj.getCaseloadId(),percentage,obj.getDeductionType(),obj.getNbtModifyUserId());
						
					}
					
				} else {
					otmalproRepository.updateCaseloadDeductionProfilesWithouttxnType(obj.getCaseloadId(),obj.getDeductionType(),obj.getNbtModifyUserId());
				}
				
			}
			
		}
		
		return listReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(final String caseloadType) {
		List<AccountCodes> returnList = otmalproRepository.cgfkCsldDpAccountCodeRecordGroup(caseloadType);
		returnList.forEach(result -> {
			result.setCode(result.getAccountCode().toString());
			result.setDescription(result.getAccountName());

		});
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<TransactionTypes> cgfkCsldDdReceiptTxnTypeRecordGroup(final String caseloadType) {
		List<TransactionTypes> returnList = otmalproRepository.cgfkCsldDdReceiptTxnTypeRecordGroup(caseloadType);
		returnList.forEach(result -> {
			result.setCode(result.getReceiptTxnType());
			result.setDescription(result.getDescription());
		});
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(final String caseloadType) {
		List<DeductionTypes> returnList = otmalproRepository.cgfkCsldDpDeductionTypeRecordGroup(caseloadType);
		returnList.forEach(result -> {
			result.setCode(result.getDeductionType());
			result.setDescription(result.getDeductionDesc());
		});
		return returnList;

	}

	public List<CaseloadDeductionProfiles> checkExists(final String caseloadId, final String deductionType) {
		return otmalproRepository.checkExists(caseloadId, deductionType);
	}

	public Integer compareEffectiveDatec(String effectiveDate) {
		return otmalproRepository.compareEffectiveDatec(effectiveDate);
	}

	
	public String getfromBalTypes(String deductionType) {
		String fromBal = otmalproRepository.getfromBalTypes(deductionType);
		return fromBal;
	}
	
	public String allocTypeValidation(final String allocType,String caseloadId) {
		return otmalproRepository.allocTypeValidation(allocType,caseloadId);
	}

}