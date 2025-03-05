package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.CaseloadDedBeneficiaries;
import net.syscon.s4.common.beans.CaseloadDedBeneficiariesCommitBean;
import net.syscon.s4.common.beans.OffenderSentObligations;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.CaseloadDeductionDetailsCommitBean;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfilesCommitBean;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.OtmfoproRepository;
import net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.OtmfoproService;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.casemanagement.beans.CommunityConditions;

/**
 * Class OtmfoproServiceImpl@Service
 * 
 */
@Service
public class OtmfoproServiceImpl extends BaseBusiness implements OtmfoproService {

	@Autowired
	private OtmfoproRepository otmfoproRepository;

	/**
	 * Creates new OtmfoproServiceImpl class Object
	 */
	public OtmfoproServiceImpl() {
		// OtmfoproServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public void csldDpPreDelete() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<CommunityConditions> csldDpPostQuery(final CommunityConditions paramBean) {
		List<CommunityConditions> communityConditionsList = otmfoproRepository.csldDpPostQuery(paramBean);
		return communityConditionsList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public Object csldDbenPreInsert() {
		return new Object();
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<OmsModules> CreateFormGlobals(final OmsModules paramBean) {
		List<OmsModules> omsModulesList = otmfoproRepository.createFormGlobalsCreateFormGlobals(paramBean);
		return omsModulesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<AccountCodes> CgfkchkCsldDpDedprofAcCo(final AccountCodes paramBean) {
		List<AccountCodes> accountCodesList = otmfoproRepository.cgfkchkCsldDpDedprofAcCo(paramBean);
		return accountCodesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<DeductionTypes> CgfkchkCsldDpDedprofDedty(final DeductionTypes paramBean) {
		List<DeductionTypes> deductionTypesList = otmfoproRepository.cgfkchkCsldDpDedprofDedty(paramBean);
		return deductionTypesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public Persons CgfkchkCsldDbenCsldDben(final Long personId) {
		Persons persons = otmfoproRepository.cgfkchkCsldDbenCsldDben(personId);
		return persons;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public Corporates CgfkchkCsldDbenCsldDben(final BigDecimal corporateId) {
		Corporates corporates = otmfoproRepository.cgfkchkCsldDbenCsldDben(corporateId);
		return corporates;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<Object> CgwhenNewFormInstance() {
		return Collections.emptyList();
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<TransactionTypes> CgfkchkCsldDdCsldDdTxnty(final TransactionTypes paramBean) {
		List<TransactionTypes> transactionTypesList = otmfoproRepository.cgfkchkCsldDdCsldDdTxnty(paramBean);
		return transactionTypesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public Object CgrichkCaseloadDeductionPr(final CaseloadDeductionDetails paramBean) {
		return new Object();
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<OffenderSentObligations> CgrichkCaseloadDeductionDe(final OffenderSentObligations paramBean) {
		List<OffenderSentObligations> offenderSentObligationsList = otmfoproRepository
				.cgrichkCaseloadDeductionDe(paramBean);
		return offenderSentObligationsList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<CaseloadDeductionProfiles> csldDpExecuteQuery(final CaseloadDeductionProfiles searchRecord) {
		List<CaseloadDeductionProfiles> resultList = otmfoproRepository.csldDpExecuteQuery(searchRecord);
		resultList.forEach(data -> {
			final String nbtModifyUserId = calculateOn(data.getDeductionType());
			data.setNbtModifyUserId(nbtModifyUserId);
		});
		return resultList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCSLD_DP
	 *
	 * 
	 */
	@Transactional
	public Integer csldDpCommit(final CaseloadDeductionProfilesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (CaseloadDeductionProfiles bean : commitBean.getInsertList()) {
				bean = preInsert(bean);
				bean.setCreateUserId(commitBean.getCreateUserId());
				List<CaseloadDeductionProfiles> list = new ArrayList<CaseloadDeductionProfiles>();
				list.add(bean);
				liReturn = otmfoproRepository.csldDpInsertCaseloadDeductionProfiles(list);
			}
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean->{
				bean.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = otmfoproRepository.csldDpUpdateCaseloadDeductionProfiles(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bean->{
				bean.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = otmfoproRepository.csldDpDeleteCaseloadDeductionProfiles(commitBean.getDeleteList());
		}

		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<CaseloadDedBeneficiaries> csldDbenExecuteQuery(final CaseloadDedBeneficiaries searchRecord) {
		List<CaseloadDedBeneficiaries> resultList = otmfoproRepository.csldDbenExecuteQuery(searchRecord);
		resultList.forEach(data -> {
			if (data.getPersonId() != null) {
				Persons person = otmfoproRepository.cgfkchkCsldDbenCsldDben(data.getPersonId().longValue());
				if (person != null) {
					data.setDspLastName(person.getLastName());
					data.setDspFirstName(person.getFirstName());
				}
			}

			if (data.getCorporateId() != null) {
				Corporates corporates = otmfoproRepository.cgfkchkCsldDbenCsldDben(data.getCorporateId());
				if (corporates != null) {
					data.setDspCorporateName(corporates.getCorporateName());
				}
			}
			final BigDecimal percentage = populatePercentage(data.getCaseloadId(), data.getDeductionType(),
					data.getPriority(), data.getAmount());
			data.setPercent(percentage);
		});
		return resultList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCSLD_DBEN
	 *
	 * 
	 */
	@Transactional
	public Integer csldDbenCommit(final CaseloadDedBeneficiariesCommitBean commitBean) {
		int liReturn = 0;
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bean -> {
				bean.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = otmfoproRepository.csldDbenDeleteCaseloadDedBeneficiaries(commitBean.getDeleteList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean -> {
				bean.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = otmfoproRepository.csldDbenUpdateCaseloadDedBeneficiaries(commitBean.getUpdateList());
		}
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bean -> {
				bean.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = otmfoproRepository.csldDbenInsertCaseloadDedBeneficiaries(commitBean.getInsertList());
			commitBean.getInsertList().forEach(data -> {
				chk2percentBeneficiary(data.getCaseloadId(), data.getDeductionType());
			});
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<CaseloadDeductionDetails> csldDdExecuteQuery(final CaseloadDeductionDetails searchRecord) {
		return otmfoproRepository.csldDdExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCSLD_DD
	 *
	 * 
	 */
	@Transactional
	public String csldDdCommit(final CaseloadDeductionDetailsCommitBean commitBean) {
		int liReturn = 0;
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(data -> {
				data.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = otmfoproRepository.csldDdDeleteCaseloadDeductionDetails(commitBean.getDeleteList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(data -> {
				data.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = otmfoproRepository.csldDdUpdateCaseloadDeductionDetails(commitBean.getUpdateList());
		}

		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bean -> {
				bean.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = otmfoproRepository.csldDdInsertCaseloadDeductionDetails(commitBean.getInsertList());

		}
		return String.valueOf(liReturn);
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<TransactionTypes> cgfkCsldDdReceiptTxnTypeRecordGroup(final String caseloadType) {
		return otmfoproRepository.cgfkCsldDdReceiptTxnTypeRecordGroup(caseloadType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<CommunityConditions> rgConditionRecordGroup() {
		List<CommunityConditions> returnList = otmfoproRepository.rgConditionRecordGroup();
		returnList.forEach(data -> {
			data.setDescriptionType(data.getDescription());
			data.setDescription(data.getCommConditionType());
			data.setCode(data.getCommConditionType());
		});
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(final String caseloadType) {
		return otmfoproRepository.cgfkCsldDpAccountCodeRecordGroup(caseloadType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<Corporates> rgCorpRecordGroup() {
		return otmfoproRepository.rgCorpRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(final String caseloadType) {
		return otmfoproRepository.cgfkCsldDpDeductionTypeRecordGroup(caseloadType);

	}

	BigDecimal populatePercentage(final String caseloadId, final String deductionType, final BigDecimal priority,
			final BigDecimal amount) {
		try {
			final String vDedType = deductionType;
			final BigDecimal vPriority = priority;
			final BigDecimal vAmount = amount;
			final BigDecimal vPrAmount = getPriorityAmount(caseloadId, vDedType, vPriority, vAmount);
			BigDecimal vPercent = null;
			if (BigDecimal.ZERO.equals(vPrAmount)) {
				vPercent = BigDecimal.ZERO;
			} else if (vPrAmount.compareTo(BigDecimal.ZERO) > 0) {
				vPercent = vAmount.divide(vPrAmount, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
//				vPercent = vPercent.multiply(BigDecimal.valueOf(100));
			}
			return vPercent;
		} catch (Exception e) {
			throw new RuntimeException("otmfopro.errpopulatepercetage");
		}
	}

	@Override
	public BigDecimal getPriorityAmount(final String caseloadId, final String deductionType, final BigDecimal priority,
			final BigDecimal amount) {
		BigDecimal priorityAmount = BigDecimal.ZERO;
		try {
			priorityAmount = otmfoproRepository.getPriorityAmount(caseloadId, deductionType, priority);
			if (priorityAmount.compareTo(BigDecimal.ZERO) > 0) {
				return priorityAmount;
			} else {
				return amount;
			}
		} catch (Exception e) {
			throw new RuntimeException("otmfopro.errgtngpritorytot");
		}
	}

	@Override
	public String calculateOn(final String deductionType) {
		return otmfoproRepository.calculateOn(deductionType);
	}

	@Override
	public Map<String, Object> calcBenTotal(final String caseloadId, final String deductionType) {
		return otmfoproRepository.calcBenTotal(caseloadId, deductionType);
	}

	@Override
	public Boolean checkMaxAmount(final CaseloadDeductionProfiles bean) {
		try {
			BigDecimal vBenCount = null;
			BigDecimal vBenTotal = null;
			BigDecimal vMaxMonth = bean.getMaxMonthlyAmount();
			BigDecimal vMaxTotal = bean.getMaxTotalAmount();
			BigDecimal vMaxRecur = bean.getMaxRecursiveAmount();

			if (vMaxMonth == null) {
				vMaxMonth = BigDecimal.ZERO;
			}

			if (vMaxTotal == null) {
				vMaxTotal = BigDecimal.ZERO;
			}

			if (vMaxRecur == null) {
				vMaxRecur = BigDecimal.ZERO;
			}
			if (!"Y".equals(bean.getCoCreditWhenIndigentFlag())) {
				Map<String, Object> calcBenTotal = calcBenTotal(bean.getCaseloadId(), bean.getDeductionType());
				if (calcBenTotal != null) {
					if (calcBenTotal.get("P_BEN_COUNT") != null) {
						vBenCount = new BigDecimal(calcBenTotal.get("P_BEN_COUNT").toString());
					}
					if (calcBenTotal.get("P_BEN_TOTAL") != null) {
						vBenTotal = new BigDecimal(calcBenTotal.get("P_BEN_TOTAL").toString());
					}
				}
				if (vBenCount.compareTo(BigDecimal.ZERO) > 0) {
					if (vMaxMonth.add(vMaxTotal).add(vMaxRecur).compareTo(vBenTotal) != 0) {
						throw new RuntimeException("otmfopro.benetotisnteqal");
					}
				}

			}
		} catch (Exception e) {
			if ("otmfopro.benetotisnteqal".equalsIgnoreCase(e.getMessage())) {
				throw new RuntimeException(e.getMessage());
			} else {
				throw new RuntimeException("otmfopro.errchkmaxamt");
			}
		}
		return true;
	}

	@Override
	public Boolean percentage(final CaseloadDeductionProfiles bean) {
		BigDecimal myPercentage = BigDecimal.ZERO;
		CaseloadDedBeneficiaries caseloadDedBeneficiaries = new CaseloadDedBeneficiaries();
		caseloadDedBeneficiaries.setCaseloadId(bean.getCaseloadId());
		caseloadDedBeneficiaries.setDeductionType(bean.getDeductionType());
		caseloadDedBeneficiaries.setModifyUserId(bean.getCreateUserId());
		try {
			if ("Y".equals(bean.getCoCreditWhenIndigentFlag())) {
				otmfoproRepository.updateCaseloadDedBeneficiariesAmount(caseloadDedBeneficiaries);
			}
		} catch (Exception e) {
			throw new RuntimeException("otmfopro.otrerrincatingprcn");
		}
		try {
			List<CaseloadDedBeneficiaries> dedPriorities = otmfoproRepository.dedPriorities(caseloadDedBeneficiaries);
			if (dedPriorities != null) {
				for (CaseloadDedBeneficiaries data : dedPriorities) {
					if (BigDecimal.ZERO.equals(data.getAmount())) {
						myPercentage = BigDecimal.ZERO;
					} else {
						BigDecimal priorityAmt = getPriorityAmount(data.getCaseloadId(), data.getDeductionType(),
								data.getPriority(), data.getAmount());
						myPercentage = data.getAmount().multiply(BigDecimal.valueOf(100)).divide(priorityAmt);
						if (myPercentage != null) {
							MathContext mcontext = new MathContext(2);
							myPercentage = myPercentage.round(mcontext);
						}
					}

					otmfoproRepository.updateCaseloadDedBeneficiariesPercentage(myPercentage,
							data.getCaseloadDedBeneficiaryId(),data.getModifyUserId());
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("otmfopro.otrerrupdtingunltamt");
		}

		return true;
	}

	@Override
	public BigDecimal countMinBalLogic(final String caseloadId, final String deductionType) {
		return otmfoproRepository.countMinBalLogic(caseloadId, deductionType);
	}

	@Transactional(rollbackFor = Exception.class)
	public String singleCommit(CaseloadDeductionProfilesCommitBean commitBean) {
//		commitBean = preInsert(commitBean);
		commitBean.getInsertList().forEach(bean -> {
			bean.setCreateUserId(commitBean.getCreateUserId());
		});
		commitBean.getUpdateList().forEach(bean -> {
			bean.setModifyUserId(commitBean.getCreateUserId());
		});
		csldDpCommit(commitBean);
//		for (final CaseloadDeductionProfiles csldDben : commitBean.getUpdateList()) {
//			csldDbenCommit(csldDben.getCaseloadDedBeneficiariesCommitBean());
//		}
//		for (final CaseloadDeductionProfiles csldDben : commitBean.getInsertList()) {
//			csldDbenCommit(csldDben.getCaseloadDedBeneficiariesCommitBean());
//		}
//		for (final CaseloadDeductionProfiles csldDd : commitBean.getUpdateList()) {
//			csldDdCommit(csldDd.getCaseloadDeductionDetailsCommitBean());
//		}
//		for (final CaseloadDeductionProfiles csldDd : commitBean.getInsertList()) {
//			csldDdCommit(csldDd.getCaseloadDeductionDetailsCommitBean());
//		}
		
		try {
			commitBean.getInsertList().forEach(data -> {
				checkMaxAmount(data);
			});
			commitBean.getUpdateList().forEach(data -> {
				checkMaxAmount(data);
			});
			commitBean.getDeleteList().forEach(data -> {
				checkMaxAmount(data);
			});
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		try {
			commitBean.getInsertList().forEach(data -> {
				percentage(data);
			});
			commitBean.getUpdateList().forEach(data -> {
				percentage(data);
			});
			commitBean.getDeleteList().forEach(data -> {
				percentage(data);
			});
		} catch (Exception e) {
			throw new RuntimeException("otmfopro.othrerrinprce");
		}
		commitBean.getInsertList().forEach(bean -> {
			postFormsCommit(bean);
		});
		commitBean.getUpdateList().forEach(bean -> {
			postFormsCommit(bean);
		});
		return String.valueOf(1);
	}

	private CaseloadDeductionProfiles preInsert(final CaseloadDeductionProfiles commitBean) {

		if (commitBean != null) {
			
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
		
		}

		return commitBean;

	}

	private Map<String, BigDecimal> getNextExpInp(final String caseloadId) {
		Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
		BigDecimal lvTmpExt = BigDecimal.ONE;
		BigDecimal lvTmpInt = BigDecimal.ONE;
		lvTmpExt = otmfoproRepository.getMaxExternalPriorityNo(caseloadId);
		if (lvTmpExt.compareTo(BigDecimal.valueOf(99)) > 0) {
			lvTmpExt = BigDecimal.ONE;
			boolean isLoop = true;
			while (isLoop) {
				lvTmpInt = otmfoproRepository.getMaxInternalPriorityNo(caseloadId, lvTmpExt);

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

	public String chk2percentBeneficiary(final String caselodadId, final String deductionType) {
		String vDedType = deductionType;
		String vCsldId = caselodadId;
		BigDecimal vParentExists = BigDecimal.ZERO;
		BigDecimal vBenCount = BigDecimal.ZERO;
		vParentExists = otmfoproRepository.vParentExists(vDedType);
		vBenCount = otmfoproRepository.vBenCount(vDedType, vCsldId);
		if (vParentExists.compareTo(BigDecimal.ZERO) > 0) {
			if (vBenCount.compareTo(BigDecimal.ONE) > 0) {
				throw new RuntimeException("otmfopro.mltplebefilogisntsup");
			}
		}

		return "Y";
	}

	private Integer postFormsCommit(final CaseloadDeductionProfiles bean) {
		String vCsldId = bean.getCaseloadId();
		try {
			BigDecimal receiptPercentage = null;
			String dedType = bean.getDeductionType();
			receiptPercentage = otmfoproRepository.getReceiptPercentage(dedType, vCsldId);
			otmfoproRepository.updateCaseloadDeductionProfilesPercentage(receiptPercentage, vCsldId, dedType,bean.getModifyUserId());
		} catch (Exception e) {
			throw new RuntimeException("otmfopro.ublefnddedprofle");
		}
		return 1;

	}

	@Override
	public Map<String, Object> preCommit(final String caseloadId, final String deductionType) {
		return otmfoproRepository.getPercentAndEnternalPriority(caseloadId, deductionType);
	}

}