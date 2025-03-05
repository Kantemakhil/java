package net.syscon.s4.inmate.trust.deductions.beneficiaryinquiry.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.CorporateAddressV;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderBeneficiariesCommitBean;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.trust.deductions.beneficiaryinquiry.OcicbeneRepository;
import net.syscon.s4.inmate.trust.deductions.beneficiaryinquiry.OcicbeneService;
import net.syscon.s4.pkgs.trust.TrustService;

/**
 * Class OcicbeneServiceImpl
 */
@Service
public class OcicbeneServiceImpl extends BaseBusiness implements OcicbeneService {

	@Autowired
	private OcicbeneRepository ocicbeneRepository;
	
	@Autowired
	private TrustService trustService;

	/**
	 * Creates new OcicbeneServiceImpl class Object
	 */
	public OcicbeneServiceImpl() {
		// OcicbeneServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public OffenderDeductions cgfkchkOffBncOffBncOff(final OffenderDeductions paramBean) {
		final OffenderDeductions offenderDeductions = ocicbeneRepository.cgfkchkOffBncOffBncOff(paramBean);
		return offenderDeductions;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> cgwhenNewFormInstance() {
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<CorporateAddressV> vCorpExecuteQuery(final CorporateAddressV searchRecord) {
		List<CorporateAddressV> returnList = null;
		String caseloadFlag = ocicbeneRepository.caseloadFlagData("CLIENT", "BEN_INQ_GLOB");
		if ("Y".equals(caseloadFlag)) {
			searchRecord.setPrimaryFlag(caseloadFlag);
		}
		returnList = ocicbeneRepository.vCorpExecuteQuery(searchRecord);
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_CORP
	 *
	 * @
	 */
	@Transactional
	public Integer vCorpCommit(final Corporates beanObj) {
		Integer liReturn = 0;
		if (beanObj != null) {
			liReturn = ocicbeneRepository.corpUpdateSuspendDate(beanObj);
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OffenderBeneficiaries> offBncExecuteQuery(final OffenderBeneficiaries searchRecord) {
		final List<OffenderBeneficiaries> returnList = ocicbeneRepository.offBncExecuteQuery(searchRecord);
		final BigDecimal chequeSum = ocicbeneRepository.getChequeSum(searchRecord.getCorporateId());
		final BigDecimal sumCfpp = ocicbeneRepository.getPaymentAmount(searchRecord.getCorporateId());
		final BigDecimal totalPaidcheck = chequeSum.add(sumCfpp);
		//final BigDecimal totalTemp = ocicbeneRepository.calculateBeneficiariesTotal(searchRecord);
		//Procedure call
		final BigDecimal totalTemp = trustService.calculateBeneficiariesTotal(searchRecord.getCorporateId());
		for (final OffenderBeneficiaries obj : returnList) {
			if (returnList.indexOf(obj) == 0) {
				obj.setDrvAmount(totalPaidcheck);
				obj.setPercent(totalTemp.subtract(totalPaidcheck));
			}
			final String totAmt = String.valueOf(obj.getAmount());
			final String totRec = String.valueOf(obj.getReceivedAmount());
			if ("null".equals(totAmt)) {
				obj.setTotalDescription("0");
			}
			if ("null".equals(totRec)) {
				obj.setReceivedAmount(BigDecimal.valueOf(0));
			}
			if (obj.getOffenderId() != null) {
				final OffenderBeneficiaries returnObj = ocicbeneRepository.offBncPostQuery(obj);
				obj.setOffenderIdDisplay(returnObj.getOffenderIdDisplay());
				obj.setLastName(returnObj.getLastName());
			}
			if (obj.getOffenderDeductionId() != null) {
				String deductionCategory = null;
				final OffenderDeductions returnObj = ocicbeneRepository.offBncPopulateWriteOff(obj);
				if (returnObj.getDeductionType() != null) {
					deductionCategory = ocicbeneRepository.offBncCheckDedCat(returnObj.getDeductionType());
				} else {
					deductionCategory = "PP";
				}
				if ("CROB".equals(deductionCategory)) {
					if (returnObj.getAdjustmentReasonCode() != null) {
						obj.setWriteOfAmount(returnObj.getAdjustmentAmount());
					} else {
						obj.setWriteOfAmount(BigDecimal.ZERO);
					}
				} else {
					obj.setWriteOfAmount(BigDecimal.ZERO);
				}
			}
			if (obj.getAmount() == null && obj.getMonthlyAmount() == null) {
				obj.setTotalDescription("Unlimited");
				obj.setTotalOwing("Unlimited");
				obj.setTotalCollected(String.valueOf(obj.getReceivedAmount()));

			} else if ((!"999999999.99".equals(totAmt) && !"99999999.99".equals(totAmt))
					&& obj.getMonthlyAmount() == null) {
				obj.setTotalDescription(String.valueOf(obj.getAmount()));
				obj.setTotalCollected(String.valueOf(obj.getReceivedAmount()));
				BigDecimal amountvalue = (obj.getAmount().subtract(obj.getReceivedAmount()))
						.subtract(obj.getWriteOfAmount());
				obj.setTotalOwing(String.valueOf(amountvalue));
			} else {
				if (obj.getMonthlyAmount() != null && obj.getMonthlyAmount().compareTo(BigDecimal.ZERO) > 0) {
					obj.setTotalDescription("Monthly");
					obj.setTotalOwing("Monthly");
					final Integer returnValue = ocicbeneRepository.offBncGetMonAmt(obj);
					obj.setTotalCollected(returnValue.toString());
				} else if (obj.getMonthlyAmount() != null && obj.getMonthlyAmount().compareTo(BigDecimal.ZERO) > 0) {
					final BigDecimal returnValue = ocicbeneRepository.offBncRecMonth(obj);
					obj.setTotalCollected(String.valueOf(obj.getReceivedAmount()));
					if (totAmt == null) {
						obj.setTotalDescription("0");
					}
					if (totRec == null) {
						obj.setReceivedAmount(BigDecimal.valueOf(0));
					}
					obj.setTotalDescription(String.valueOf(obj.getAmount().multiply(returnValue)));
					obj.setTotalOwing(
							String.valueOf((obj.getAmount().multiply(returnValue)).subtract(obj.getReceivedAmount())));
				} else if (("999999999.99".equals(totAmt) || "99999999.99".equals(totAmt))
						&& obj.getMonthlyAmount() == null) {
					obj.setTotalDescription("Unlimited");
					obj.setTotalOwing("Unlimited");
					obj.setTotalCollected(String.valueOf(obj.getReceivedAmount()));
				}
			}

		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_BNC
	 *
	 * @
	 */
	@Transactional
	public Integer offBncCommit(final OffenderBeneficiariesCommitBean commitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return ocicbeneRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 * @
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean CommitBean) {
		final int liReturn = 0;
		return liReturn;
	}

}