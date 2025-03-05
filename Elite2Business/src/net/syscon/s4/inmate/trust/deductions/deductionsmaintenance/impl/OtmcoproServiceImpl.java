package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.CaseloadDeductionDetailsCommitBean;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfilesCommitBean;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.OtmcoproRepository;
import net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.OtmcoproService;
import net.syscon.s4.inst.booking.beans.Persons;

@Service
public class OtmcoproServiceImpl implements OtmcoproService {

	/**
	 * Creates new OtmcoproServiceImpl class Object
	 */
	public OtmcoproServiceImpl() {
		// OtmcoproServiceImpl
	}

	@Autowired
	private OtmcoproRepository otmcoproRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<CaseloadDeductionProfiles> csldDpExecuteQuery(final CaseloadDeductionProfiles searchRecord) {
		List<CaseloadDeductionProfiles> resultList = otmcoproRepository.csldDpExecuteQuery(searchRecord);
		resultList.forEach(data -> {
			final String nbtModifyUserId = getCalculateOnVal(data.getDeductionType());
			data.setNbtModifyUserId(nbtModifyUserId);
			try {
				CaseloadDeductionProfiles getLimitAndPeriodType = otmcoproRepository
						.getLimitAndPeriodType(data.getCaseloadId(), data.getDeductionType());
				if(getLimitAndPeriodType!=null) {
				data.setCoLimitAmount(getLimitAndPeriodType.getCoLimitAmount());
				data.setNbtDeductionType(getLimitAndPeriodType.getNbtDeductionType());
				}
			} catch (Exception e) {
				data.setCoLimitAmount(null);
				data.setNbtDeductionType(null);
			}
			if (data.getCoLimitAmount() != null) {
				data.setNbtAccountCode("N");
			} else {
				data.setNbtAccountCode("Y");
			}

			Corporates corporates = cgfkchkCsldDbenCsldDben(data.getPayeeCorporateId());
			if (corporates != null && corporates.getErrorMessage() == null) {
				data.setCorporateName(corporates.getCorporateName());
			}

		});
		return resultList;

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
		//commitBean = preInsert(commitBean);
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (CaseloadDeductionProfiles obj : commitBean.getInsertList()) {
				obj = preInsert(obj);
				obj.setCreateUserId(commitBean.getCreateUserId());	
				List<CaseloadDeductionProfiles> list = new ArrayList<CaseloadDeductionProfiles>();
				list.add(obj);
				liReturn = otmcoproRepository.csldDpInsertCaseloadDeductionProfiles(list);
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (CaseloadDeductionProfiles obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());	
			}
			liReturn = otmcoproRepository.csldDpUpdateCaseloadDeductionProfiles(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (CaseloadDeductionProfiles obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());	
			}
			liReturn = otmcoproRepository.csldDpDeleteCaseloadDeductionProfiles(commitBean.getDeleteList());
		}

		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(data -> {
				postFormsCommit(data.getCaseloadId(), data.getDeductionType(),data.getCreateUserId());
			});
		}

		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(data -> {
				postFormsCommit(data.getCaseloadId(), data.getDeductionType(),data.getModifyUserId());
			});
		}
		return String.valueOf(liReturn);
	}

	private CaseloadDeductionProfiles preInsert(final CaseloadDeductionProfiles commitBean) {
		BigDecimal extPrt = BigDecimal.ONE;
		BigDecimal intPrt = BigDecimal.ONE;
		Map<String, BigDecimal> getNextExpInp = getNextExpInp(commitBean.getCaseloadId());
		if (getNextExpInp != null) {
			if (getNextExpInp.get("extPrt") != null) {
				extPrt = getNextExpInp.get("extPrt");
			}
			if (getNextExpInp.get("intPrt") != null) {
				intPrt = getNextExpInp.get("intPrt");
			}
		}
		commitBean.setFifoFlag("Y");
		commitBean.setPercentage(BigDecimal.ZERO);
		commitBean.setExternalPriorityNo(extPrt);
		commitBean.setInternalPriorityNo(intPrt);
		commitBean.setFoAlAllOffenderFlag("N");
		return commitBean;

	}

	private Map<String, BigDecimal> getNextExpInp(final String caseloadId) {
		Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
		BigDecimal lvTmpExt = BigDecimal.ONE;
		BigDecimal lvTmpInt = BigDecimal.ONE;
		lvTmpExt = otmcoproRepository.getMaxExternalPriorityNo(caseloadId);
		if (lvTmpExt.compareTo(BigDecimal.valueOf(99)) > 0) {
			lvTmpExt = BigDecimal.ONE;
			boolean isLoop = true;
			while (isLoop) {
				lvTmpInt = otmcoproRepository.getMaxInternalPriorityNo(caseloadId, lvTmpExt);

				if (lvTmpInt.compareTo(BigDecimal.valueOf(99)) > 0) {
					if (BigDecimal.valueOf(99).equals(lvTmpExt)) {
						throw new RuntimeException("otmfopro.nomreexpcomb");
					} else {
						lvTmpExt = lvTmpExt.add(BigDecimal.ONE);
					}
				} else {
					isLoop = false;
				}
			}
		}

		result.put("extPrt", lvTmpExt);
		result.put("intPrt", lvTmpInt);

		return result;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<CaseloadDeductionDetails> csldDdExecuteQuery(final CaseloadDeductionDetails searchRecord) {
		return otmcoproRepository.csldDdExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCSLD_DD
	 *
	 * @throws SQLException
	 */
	@Transactional
	public String csldDdCommit(final CaseloadDeductionDetailsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = otmcoproRepository.csldDdInsertCaseloadDeductionDetails(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = otmcoproRepository.csldDdUpdateCaseloadDeductionDetails(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = otmcoproRepository.csldDdDeleteCaseloadDeductionDetails(commitBean.getDeleteList());
		}

		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(data -> {
				postFormsCommit(data.getCaseloadId(), data.getDeductionType(),data.getCreateUserId());
			});
		}

		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(data -> {
				postFormsCommit(data.getCaseloadId(), data.getDeductionType(),data.getModifyUserId());
			});
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(data -> {
				postFormsCommit(data.getCaseloadId(), data.getDeductionType(),data.getModifyUserId());
			});
		}
		return String.valueOf(liReturn);
	}

	private Integer postFormsCommit(final String caseloadId, final String deductionType,  final String userId) {
		String vCsldId = caseloadId;
		try {
			BigDecimal receiptPercentage = null;
			String dedType = deductionType;
			receiptPercentage = otmcoproRepository.getReceiptPercentage(dedType, vCsldId);
			otmcoproRepository.updateCaseloadDeductionProfilesPercentage(receiptPercentage, vCsldId, dedType,userId);
		} catch (Exception e) {
			throw new RuntimeException("Unable to find deduction profile record and receipt type attachment");
		}
		return 1;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<Corporates> cgfkCsldDpPayeeCorporateIRecordGroup() {
		return otmcoproRepository.cgfkCsldDpPayeeCorporateIRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<TransactionTypes> cgfkCsldDdReceiptTxnTypeRecordGroup(final String caseloadType) {
		return otmcoproRepository.cgfkCsldDdReceiptTxnTypeRecordGroup(caseloadType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(final String caseloadType) {
		List<DeductionTypes> returnList = otmcoproRepository.cgfkCsldDpDeductionTypeRecordGroup(caseloadType);
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(final String caseloadType) {
		List<AccountCodes> returnList = otmcoproRepository.cgfkCsldDpAccountCodeRecordGroup(caseloadType);
		for (final AccountCodes accountCodes : returnList) {
			accountCodes.setCode(accountCodes.getAccountCode().toString());
		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<Persons> cgfkCsldDpPayeePersonIdRecordGroup() {
		return otmcoproRepository.cgfkCsldDpPayeePersonIdRecordGroup();

	}

	@Override
	public Map<String, Object> preCommit(final String caseloadId, final String deductionType) {
		return otmcoproRepository.getPercentAndEnternalPriority(caseloadId, deductionType);
	}

	@Override
	public String getCalculateOnVal(final String deductionType) {
		return otmcoproRepository.getCalculateOnVal(deductionType);
	}

	@Override
	public String chkDuplicate(final String caseloadId, final String deductionType) {
		return otmcoproRepository.chkDuplicate(caseloadId, deductionType);
	}

	@Override
	public Corporates cgfkchkCsldDbenCsldDben(final BigDecimal corporateId) {
		return otmcoproRepository.cgfkchkCsldDbenCsldDben(corporateId);
	}

}