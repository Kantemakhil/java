package net.syscon.s4.inmate.trust.deductions.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.GroupedObligations;
import net.syscon.s4.im.beans.ObligationGroups;
import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.im.beans.OffenderDeductionReceipts;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderBeneficiariesCommitBean;
import net.syscon.s4.inmate.beans.OffenderDeductionReceiptsCommitBean;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderDeductionsCommitBean;
import net.syscon.s4.inmate.trust.deductions.OcdoobliRepository;
import net.syscon.s4.inmate.trust.deductions.OcdoobliService;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.pkgs.oms_date_time.OmsDateTimeService;
import net.syscon.s4.pkgs.oms_utils.OmsUtilsService;
import net.syscon.s4.triggers.OffenderBeneficiariesT1Service;
import net.syscon.s4.triggers.OffenderBeneficiariesT2Service;
import net.syscon.s4.triggers.OffenderDeductionsHty;
import net.syscon.s4.triggers.OffenderDeductionsT2Service;
import net.syscon.s4.triggers.OffenderDeductionsThtyService;
import net.syscon.s4.triggers.OffenderDeductionsTjnService;
import net.syscon.s4.triggers.OffendersVineIntfTrgService;
import net.syscon.s4.triggers.OmtoffsrcService;

/**
 * Class OcdoobliServiceImpl
 */
@Service
public class OcdoobliServiceImpl extends BaseBusiness implements OcdoobliService {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdoobliServiceImpl.class.getName());

	@Autowired
	private OcdoobliRepository ocdoobliRepository;


	@Autowired
	private OmsDateTimeService omsDateTimeService;

	@Autowired
	private OmsUtilsService omsUtilsService;

	@Autowired
	private OffenderDeductionsT2Service offenderDeductionsT2Service;

	@Autowired
	private OffenderDeductionsThtyService offenderDeductionsThtyService;

	@Autowired
	private OffenderDeductionsTjnService offenderDeductionsTjnService;

	@Autowired
	private OffenderBeneficiariesT1Service offenderBeneficiariesT1Service;

	@Autowired
	private OffenderBeneficiariesT2Service offenderBeneficiariesT2Service;

	@Autowired
	private OffendersVineIntfTrgService offendersVineIntfTrgService;
	@Autowired
	private OmtoffsrcService omtoffsrcService;


	/**
	 * Creates new OcdoobliServiceImpl class Object
	 */
	public OcdoobliServiceImpl() {
		// OcdoobliServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OffenderDeductions> offBkgOnCheckDeleteMaster(final OffenderDeductions paramBean) {
		return ocdoobliRepository.offBkgOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OffenderBeneficiaries> offDedOnCheckDeleteMaster(final OffenderBeneficiaries paramBean) {
		return ocdoobliRepository.offDedOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<ReferenceCodes> cgfkchkOffDedOffDedRef() {
		return ocdoobliRepository.cgfkchkOffDedOffDedRef();
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceCodes cgfklkpOffDedOffDedRef(final ReferenceCodes paramBean) {
		return ocdoobliRepository.cgfklkpOffDedOffDedRef(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Corporates cgfkchkOffBncOffBncCorp(final Corporates paramBean) {
		return ocdoobliRepository.cgfkchkOffBncOffBncCorp(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Persons cgfkchkOffBncOffBncPer(final Persons paramBean) {
		return ocdoobliRepository.cgfkchkOffBncOffBncPer(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<TransactionTypes> cgfkchkOffDrOffDrTxnTyp(final TransactionTypes paramBean) {
		return ocdoobliRepository.cgfkchkOffDrOffDrTxnTyp(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceCodes cgfkchkOffDed1OffDedRef(final ReferenceCodes paramBean) {
		return ocdoobliRepository.cgfkchkOffDed1OffDedRef(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public GroupedObligations cgfkchkOffDedGrpId(final GroupedObligations paramBean) {
		return ocdoobliRepository.cgfkchkOffDedGrpId(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Integer cgfklkpOffDedCaseNumber(final Long rootOffenderId, final String informationNumber) {
		return ocdoobliRepository.cgfklkpOffDedCaseNumber(rootOffenderId, informationNumber);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_DED
	 *
	 *
	 */
	@Transactional
	public Integer offDedCommit(final OffenderDeductionsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OffenderDeductions obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = offDedInsertOffenderDeductions(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderDeductions obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				obj.setCreateUserId(commitBean.getCreateUserId());			}
			liReturn = offDedUpdateOffenderDeductions(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (OffenderDeductions obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = offDedDeleteOffenderDeductions(commitBean.getDeleteList(), commitBean.getCreateUserId());
		}
		return liReturn;
	}

	private int offDedDeleteOffenderDeductions(final List<OffenderDeductions> deleteList, final String user) {
		// Single Delete Implementation
		if (deleteList != null && !deleteList.isEmpty()) {
			deleteList.stream().forEach(data -> {
				final List<OffenderBeneficiaries> offBrn = ocdoobliRepository.offBncExecuteQuery(getBeneficiary(data));
				if (offBrn != null && !offBrn.isEmpty()) {
					offBncDeleteOffenderBeneficiaries(offBrn);
				}
				List<OffenderDeductionReceipts> offDedRect = ocdoobliRepository
						.offDrExecuteQuery(getReceipt(data));
				offDedRect.forEach(obj -> {obj.setModifyUserId(user);
				});
				if (offDedRect != null && !offDedRect.isEmpty()) {
					ocdoobliRepository.offDrDeleteOffenderDeductionReceipts(offDedRect);
				}
			});
		}
		String payPlanFlag = null;
		Boolean val = false;
		final String profileVal = ocdoobliRepository.profilePlanFlag();
		ArrayList<OffenderDeductions> oldRecList = new ArrayList<OffenderDeductions>();

		for (OffenderDeductions obj : deleteList) {
			OffenderDeductions oldRec = ocdoobliRepository.offenderDeductionData(obj.getOffenderDeductionId());
			oldRecList.add(oldRec);
		}
		final Integer listReturn = ocdoobliRepository.offDedDeleteOffenderDeductions(deleteList);
		if (listReturn > 0) {
			for (OffenderDeductions obj : deleteList) {
				for (OffenderDeductions oldRec : oldRecList) {
					if (oldRec.getOffenderDeductionId().equals(obj.getOffenderDeductionId())) {
						OffenderDeductionsHty offDedHty = new OffenderDeductionsHty();
						offDedHty.setJnOperation("DEL");
						offDedHty.setJnOracleUser(user);
						offDedHty.setOffenderDeductionId(obj.getOffenderDeductionId());
						offDedHty.setCaseloadId(obj.getCaseloadId());
						offDedHty.setCreditLimit(obj.getCreditLimit());
						offDedHty.setDeductionType(obj.getDeductionType());
						offDedHty.setDeductionStatus(obj.getDeductionStatus());
						offDedHty.setDeductionPriority(
								(obj.getDeductionPriority() != null ? obj.getDeductionPriority().doubleValue() : null));
						offDedHty.setInformationNumber(obj.getInformationNumber());
						offDedHty.setDeductionPercentage(
								(obj.getDeductionPercentage() != null ? obj.getDeductionPercentage().doubleValue()
										: null));
						offDedHty.setProcessPriorityNumber(
								(obj.getProcessPriorityNumb() != null ? obj.getProcessPriorityNumb().doubleValue()
										: null));
						offDedHty.setEffectiveDate(obj.getEffectiveDate());
						offDedHty.setCommentText(obj.getCommentText());
						offDedHty.setFifoFlag(obj.getFifoFlag());
						offDedHty.setPayeePersonId(
								(obj.getPayeePersonId() != null ? obj.getPayeePersonId().longValue() : null));
						offDedHty.setPayeeCorporateId(
								(obj.getPayeeCorporateId() != null ? obj.getPayeeCorporateId().longValue() : null));
						offDedHty.setMaxMonthlyAmount(obj.getMaxMonthlyAmount());
						offDedHty.setMaxTotalAmount(obj.getMaxTotalAmount());
						offDedHty.setDeductionAmount(obj.getDeductionAmount());
						offDedHty.setAdjustmentReasonCode(obj.getAdjustmentReasonCode());
						offDedHty.setAdjustmentAmount(obj.getAdjustmentAmount());
						offDedHty.setAdjustmentUserId(obj.getAdjustmentUserId());
						offDedHty.setAdjustmentTxnId(
								(obj.getAdjustmentTxnId() != null ? obj.getAdjustmentTxnId().longValue() : null));
						offDedHty.setAdjustmentText(obj.getAdjustmentText());
						offDedHty.setModifyDate(obj.getModifyDate());
						offDedHty.setPayDeductionFlag(obj.getPayDeductionFlag());
						offDedHty.setMaxRecursiveAmount(obj.getMaxRecursiveAmount());
						offDedHty.setGroupId((obj.getGroupId() != null ? obj.getGroupId().longValue() : null));
						offDedHty.setCaseId((obj.getCaseId() != null ? obj.getCaseId().longValue() : null));
						offDedHty.setParentDeductionId(
								(obj.getParentDeductionId() != null ? obj.getParentDeductionId().longValue() : null));
						offDedHty.setJsStatus(obj.getJsStatus());
						offDedHty.setCollectAgencyAmount(obj.getCollectAgencyAmount());
						offDedHty.setCollectAgencyFlag(obj.getCollectAgencyFlag());
						offDedHty.setCollectSentDate(obj.getCollectSentDate());
						offDedHty.setOffenderPaymentProfileId((obj.getOffenderPaymentProfileId() != null
								? obj.getOffenderPaymentProfileId().longValue()
										: null));
						offDedHty.setSealFlag(obj.getSealFlag());
						offDedHty.setCreateUserId(obj.getCreateUserId());
						offDedHty.setModifyUserId(obj.getModifyUserId());
						offenderDeductionsThtyService.OffenderDeductionsThtyTrigger(offDedHty, "DELETE");
						//offenderDeductionsTjnService.offenderDeductionsTjn(obj, oldRec, "DELETE");
					}
				}
			}
		}

		for (final OffenderDeductions obj : deleteList) {
			final Integer checkOne = ocdoobliRepository.getCheckOne(obj.getOffenderId(), obj.getInformationNumber(),
					obj.getGroupId());
			if (checkOne != null) {
				val = true;
			} else {
				val = false;
			}
			if ("COMM".equals(obj.getCaseloadType())) {
				payPlanFlag = profileVal;
				if ("Y".equals(payPlanFlag)) {
					if (val) {
						return 111;

					}

				}
			}

		}
		return listReturn;
	}

	private Integer offDedUpdateOffenderDeductions(final List<OffenderDeductions> updateList) {
		BigDecimal vDedAmnt = null;
		String vUnlimDedFlag = null;
		for (final OffenderDeductions checkDedamnt : updateList) {
			if (checkDedamnt.getMaxTotalAmount() != null) {
				vDedAmnt = checkDedamnt.getMaxTotalAmount();
			} else if (checkDedamnt.getMaxMonthlyAmount() != null) {
				vDedAmnt = checkDedamnt.getMaxMonthlyAmount();
			} else if (checkDedamnt.getMaxRecursiveAmount() != null) {
				vDedAmnt = checkDedamnt.getMaxRecursiveAmount();

			}
			if (vDedAmnt == null) {
				vUnlimDedFlag = "Y";
			} else {
				vUnlimDedFlag = "N";
			}
			if ("Y".equals(vUnlimDedFlag)) {

			} else if ("N".equals(vUnlimDedFlag)) {
				if (vDedAmnt == null) {
					vDedAmnt = BigDecimal.valueOf(-1);
				}
				if (vDedAmnt == null) {
					checkDedamnt.setAmount(BigDecimal.valueOf(-1));
				}
				if (checkDedamnt.getAmount() != null) {
					if (vDedAmnt != checkDedamnt.getAmount()) {
						return 555;

					}
				}

			}
		}
		ArrayList<OffenderDeductions> oldRecList = new ArrayList<OffenderDeductions>();
		for (OffenderDeductions obj : updateList) {
			OffenderDeductions oldRec = ocdoobliRepository.offenderDeductionData(obj.getOffenderDeductionId());
			oldRecList.add(oldRec);
		}
		final Integer listReturn = ocdoobliRepository.offDedUpdateOffenderDeductions(updateList);
		OffenderDeductionsHty offDedHty = new OffenderDeductionsHty();
		if (listReturn > 0) {
			for (OffenderDeductions obj : updateList) {
				for (OffenderDeductions oldRec : oldRecList) {
					if (oldRec.getOffenderDeductionId().equals(obj.getOffenderDeductionId())) {
						offenderDeductionsT2Service.offenderDeductionsT2Trigger(obj, oldRec, "UPDATE");
						//offenderDeductionsTjnService.offenderDeductionsTjn(obj, oldRec, "UPDATE");
						offDedHty = new OffenderDeductionsHty();
						offDedHty.setJnOperation("UPD");
						offDedHty.setJnOracleUser(obj.getModifyUserId());
						offDedHty.setOffenderDeductionId(obj.getOffenderDeductionId());
						offDedHty.setCaseloadId(obj.getCaseloadId());
						offDedHty.setCreditLimit(obj.getCreditLimit());
						offDedHty.setDeductionType(obj.getDeductionType());
						offDedHty.setDeductionStatus(obj.getDeductionStatus());
						offDedHty.setDeductionPriority(
								(obj.getDeductionPriority() != null ? obj.getDeductionPriority().doubleValue() : null));
						offDedHty.setInformationNumber(obj.getInformationNumber());
						offDedHty.setDeductionPercentage(
								(obj.getDeductionPercentage() != null ? obj.getDeductionPercentage().doubleValue()
										: null));
						offDedHty.setProcessPriorityNumber(
								(obj.getProcessPriorityNumb() != null ? obj.getProcessPriorityNumb().doubleValue()
										: null));
						offDedHty.setEffectiveDate(obj.getEffectiveDate());
						offDedHty.setCommentText(obj.getCommentText());
						offDedHty.setFifoFlag(obj.getFifoFlag());
						offDedHty.setPayeePersonId(
								(obj.getPayeePersonId() != null ? obj.getPayeePersonId().longValue() : null));
						offDedHty.setPayeeCorporateId(
								(obj.getPayeeCorporateId() != null ? obj.getPayeeCorporateId().longValue() : null));
						offDedHty.setMaxMonthlyAmount(obj.getMaxMonthlyAmount());
						offDedHty.setMaxTotalAmount(obj.getMaxTotalAmount());
						offDedHty.setDeductionAmount(obj.getDeductionAmount());
						offDedHty.setAdjustmentReasonCode(obj.getAdjustmentReasonCode());
						offDedHty.setAdjustmentAmount(obj.getAdjustmentAmount());
						offDedHty.setAdjustmentUserId(obj.getAdjustmentUserId());
						offDedHty.setAdjustmentTxnId(
								(obj.getAdjustmentTxnId() != null ? obj.getAdjustmentTxnId().longValue() : null));
						offDedHty.setAdjustmentText(obj.getAdjustmentText());
						offDedHty.setModifyDate(obj.getModifyDate());
						offDedHty.setPayDeductionFlag(obj.getPayDeductionFlag());
						offDedHty.setMaxRecursiveAmount(obj.getMaxRecursiveAmount());
						offDedHty.setGroupId((obj.getGroupId() != null ? obj.getGroupId().longValue() : null));
						offDedHty.setCaseId((obj.getCaseId() != null ? obj.getCaseId().longValue() : null));
						offDedHty.setParentDeductionId(
								(obj.getParentDeductionId() != null ? obj.getParentDeductionId().longValue() : null));
						offDedHty.setJsStatus(obj.getJsStatus());
						offDedHty.setCollectAgencyAmount(obj.getCollectAgencyAmount());
						offDedHty.setCollectAgencyFlag(obj.getCollectAgencyFlag());
						offDedHty.setCollectSentDate(obj.getCollectSentDate());
						offDedHty.setOffenderPaymentProfileId((obj.getOffenderPaymentProfileId() != null
								? obj.getOffenderPaymentProfileId().longValue()
										: null));
						offDedHty.setSealFlag(obj.getSealFlag());
						offDedHty.setCreateUserId(obj.getCreateUserId());
						offDedHty.setModifyUserId(obj.getModifyUserId());

						offenderDeductionsThtyService.OffenderDeductionsThtyTrigger(offDedHty, "UPDATE");
					}
				}
			}
		}

		if (listReturn == 1) {
			for (final OffenderDeductions offDed : updateList) {
				final List<OffenderDeductions> returnList = ocdoobliRepository
						.getcurChildOffDed(offDed.getOffenderDeductionId());
				String vParentAmountTypeNew = null;
				if (offDed.getMaxTotalAmount() != null) {
					vParentAmountTypeNew = "TOTAL";
				} else if (offDed.getMaxMonthlyAmount() != null) {
					vParentAmountTypeNew = "MONTHLY";
				} else if (offDed.getMaxRecursiveAmount() != null) {
					vParentAmountTypeNew = "RECURSIVE";
				} else {
					vParentAmountTypeNew = "UNLIMITED";
				}
				if (returnList.size() > 0) {
					for (final OffenderDeductions cod : returnList) {
						String vChildAmountType = null;
						if (cod.getMaxTotalAmount() != null) {
							vChildAmountType = "TOTAL";
						} else if (offDed.getMaxMonthlyAmount() != null) {
							vChildAmountType = "MONTHLY";
						} else if (offDed.getMaxRecursiveAmount() != null) {
							vChildAmountType = "RECURSIVE";
						}

						if (cod.getDeductionAmount() == null) {
							cod.setDeductionAmount(BigDecimal.valueOf(0));
						}
						if (cod.getDeductionAmount().compareTo(BigDecimal.valueOf(0)) == 1) {
							if (!vChildAmountType.equals(vParentAmountTypeNew)) {

							} else {
								ocdoobliRepository.updateOffenderDeductions(offDed.getDeductionPriority(),
										offDed.getInformationNumber(), offDed.getCaseId(), offDed.getGroupId(),
										offDed.getDeductionStatus(), offDed.getMaxTotalAmount(),
										cod.getPercentageOfParent(), cod.getDeductionAmount(),
										offDed.getMaxMonthlyAmount(), offDed.getMaxRecursiveAmount(),
										cod.getOffenderDeductionId(), offDed.getModifyUserId());
								try {
								offenderDeductionsT2Service.offenderDeductionsT2Trigger(cod, offDed,ApplicationConstants.UPDATE);
								offenderDeductionsThtyService.OffenderDeductionsThtyTrigger(offDedHty, ApplicationConstants.UPDATE);
								//offenderDeductionsTjnService.offenderDeductionsTjn(cod, offDed, ApplicationConstants.UPDATE);

								OffenderBeneficiaries newRec = new OffenderBeneficiaries();
								OffenderBeneficiaries oldRec = ocdoobliRepository
										.offenderBeneficiariesDatabyDeductionId(cod.getOffenderDeductionId());
								BeanUtils.copyProperties(oldRec, newRec);
								newRec.setMaxTotalAmount(offDed.getMaxTotalAmount());
								newRec.setMaxMontlyAmount(offDed.getMaxMonthlyAmount());
								newRec.setMaxRecursiveAmount(offDed.getMaxRecursiveAmount());
								offenderBeneficiariesT1Service.offenderBeneficiariesT1Trigger(newRec, oldRec);
								offenderBeneficiariesT2Service.offenderBeneficiariesT2Trigger(newRec, ApplicationConstants.UPDATE);
								ocdoobliRepository.updateOffenderOffenderBeneficiaries(offDed.getMaxTotalAmount(),
										offDed.getMaxMonthlyAmount(), offDed.getMaxRecursiveAmount(),
										cod.getPercentageOfParent(), cod.getOffenderDeductionId(),
										offDed.getModifyUserId());
								}catch (Exception e) {
									logger.error(this.getClass().getName() + " error in cgfkOffEmDspDescriptionRecordGroup " + e);
								}
							}

						}
					}
				}

			}
		}
		return listReturn;
	}

	private Integer offDedInsertOffenderDeductions(final List<OffenderDeductions> insertList) {
		for (final OffenderDeductions obj : insertList) {
			if (obj.getGroupId() != null) {
				if (obj.getMaxTotalAmount() != null) {
					final List<OffenderDeductions> manyRowsOne = ocdoobliRepository.checkOneList(obj.getOffenderId(),
							obj.getInformationNumber(), obj.getGroupId());
					if (manyRowsOne.size() > 1) {
						return 5;
					}
				}
			}

			if (obj.getMaxRecursiveAmount() != null) {
				final List<OffenderDeductions> manyRowscheckTwo = ocdoobliRepository.checkOneList(obj.getOffenderId(),
						obj.getInformationNumber(), obj.getGroupId());
				if (manyRowscheckTwo.size() > 1) {
					return 6;
				}
			}
			final Long deductionId = ocdoobliRepository.getNxtVal();
			obj.setOffenderDeductionId(deductionId);
			if (obj.getDeductionType() != null && obj.getCaseId() != null) {
				if ("REST".equals(obj.getDeductionType())) {
					final Integer count = ocdoobliRepository.setJsCondition(obj.getCaseId()!=null?obj.getCaseId().intValue():null);
					if (count > 0) {
						obj.setJsStatus("Y");

					}
				}
			} else {
				obj.setProcessPriorityNumb(BigDecimal.valueOf(99));
				obj.setCollectAgencyFlag("N");
				obj.setJsStatus("N");
			}

			final String profilePlanFlag = ocdoobliRepository.profilePlanFlag();
			obj.setProfilePayplnFlg(profilePlanFlag);
			if ("COMM".equals(obj.getCaseloadType())) {
				if ("Y".equals(obj.getProfilePayplnFlg())) {
					if (obj.getGroupId() != null) {
						ocdoobliRepository.updateOffenderPayments(obj.getOffenderId(), obj.getMaxTotalAmount(),
								obj.getGroupId(), obj.getInformationNumber(), obj.getCreateUserId());

					}

				}

			}
			try {
			if (!"Y".equals(obj.getCgnbtMaxMonthlyAmount())) {
				final List<OffenderDeductions> curChildDeductions = ocdoobliRepository
						.getcurChildDeductions(obj.getDeductionType());
				final List<OffenderDeductions> curChildcsldDed = ocdoobliRepository
						.getcurChildCsldDed(obj.getDeductionType(), obj.getCaseloadId());
				for (final OffenderDeductions cd : curChildDeductions) {
					obj.setDeductionType(cd.getDeductionType());
					obj.setPercentageOfParent(cd.getPercentageOfParent());
					for (final OffenderDeductions cdp : curChildcsldDed) {
						obj.setFifoFlag(cdp.getFifoFlag());
						final Long lvDedId = ocdoobliRepository.getNxtVal();
						obj.setOffenderDeductionId(lvDedId);
						ocdoobliRepository.insertIntoOffenderDeductions(obj);
						OffenderBeneficiaries newRec = new OffenderBeneficiaries();
						BeanUtils.copyProperties(obj, newRec);
						offenderBeneficiariesT2Service.offenderBeneficiariesT2Trigger(newRec, ApplicationConstants.INSERT);
						ocdoobliRepository.insertOffenderBenficiaries(obj);
						ocdoobliRepository
						.insertIntoOffenderDeductionsReciepts(obj);

					}

				}

			}
			}catch (Exception e) {
				logger.error(this.getClass().getName() + " error in offDedInsertOffenderDeductions " + e);
			}

		}
		final Integer listRet = ocdoobliRepository.offDedInsertOffenderDeductions(insertList);
		for (OffenderDeductions obj : insertList) {
			offenderDeductionsT2Service.offenderDeductionsT2Trigger(obj, new OffenderDeductions(), ApplicationConstants.INSERT);
			//offenderDeductionsTjnService.offenderDeductionsTjn(obj, new OffenderDeductions(), ApplicationConstants.INSERT);

			OffenderDeductionsHty offDedHty = new OffenderDeductionsHty();
			offDedHty.setJnOperation("INS");
			offDedHty.setJnOracleUser(obj.getCreateUserId());
			offDedHty.setOffenderDeductionId(obj.getOffenderDeductionId());
			offDedHty.setCaseloadId(obj.getCaseloadId());
			offDedHty.setCreditLimit(obj.getCreditLimit());
			offDedHty.setDeductionType(obj.getDeductionType());
			offDedHty.setDeductionStatus(obj.getDeductionStatus());
			offDedHty.setDeductionPriority(
					(obj.getDeductionPriority() != null ? obj.getDeductionPriority().doubleValue() : null));
			offDedHty.setInformationNumber(obj.getInformationNumber());
			offDedHty.setDeductionPercentage(
					(obj.getDeductionPercentage() != null ? obj.getDeductionPercentage().doubleValue() : null));
			offDedHty.setProcessPriorityNumber(
					(obj.getProcessPriorityNumb() != null ? obj.getProcessPriorityNumb().doubleValue() : null));
			offDedHty.setEffectiveDate(obj.getEffectiveDate());
			offDedHty.setCommentText(obj.getCommentText());
			offDedHty.setFifoFlag(obj.getFifoFlag());
			offDedHty.setPayeePersonId((obj.getPayeePersonId() != null ? obj.getPayeePersonId().longValue() : null));
			offDedHty.setPayeeCorporateId(
					(obj.getPayeeCorporateId() != null ? obj.getPayeeCorporateId().longValue() : null));
			offDedHty.setMaxMonthlyAmount(obj.getMaxMonthlyAmount());
			offDedHty.setMaxTotalAmount(obj.getMaxTotalAmount());
			offDedHty.setDeductionAmount(obj.getDeductionAmount());
			offDedHty.setAdjustmentReasonCode(obj.getAdjustmentReasonCode());
			offDedHty.setAdjustmentAmount(obj.getAdjustmentAmount());
			offDedHty.setAdjustmentUserId(obj.getAdjustmentUserId());
			offDedHty.setAdjustmentTxnId(
					(obj.getAdjustmentTxnId() != null ? obj.getAdjustmentTxnId().longValue() : null));
			offDedHty.setAdjustmentText(obj.getAdjustmentText());
			offDedHty.setModifyDate(obj.getModifyDate());
			offDedHty.setPayDeductionFlag(obj.getPayDeductionFlag());
			offDedHty.setMaxRecursiveAmount(obj.getMaxRecursiveAmount());
			offDedHty.setGroupId((obj.getGroupId() != null ? obj.getGroupId().longValue() : null));
			offDedHty.setCaseId((obj.getCaseId() != null ? obj.getCaseId().longValue() : null));
			offDedHty.setParentDeductionId(
					(obj.getParentDeductionId() != null ? obj.getParentDeductionId().longValue() : null));
			offDedHty.setJsStatus(obj.getJsStatus());
			offDedHty.setCollectAgencyAmount(obj.getCollectAgencyAmount());
			offDedHty.setCollectAgencyFlag(obj.getCollectAgencyFlag());
			offDedHty.setCollectSentDate(obj.getCollectSentDate());
			offDedHty.setOffenderPaymentProfileId(
					(obj.getOffenderPaymentProfileId() != null ? obj.getOffenderPaymentProfileId().longValue() : null));
			offDedHty.setSealFlag(obj.getSealFlag());
			offDedHty.setCreateUserId(obj.getCreateUserId());
			offDedHty.setModifyUserId(obj.getCreateUserId());

			offenderDeductionsThtyService.OffenderDeductionsThtyTrigger(offDedHty,ApplicationConstants.INSERT);

		}

		for (final OffenderDeductions objBeaninsertBean : insertList) {
			if (listRet == 1) {
				if (objBeaninsertBean.getOffenderDeductionId() != null) {
					final Integer vDupRec = ocdoobliRepository
							.checkBenficiaryInserted(objBeaninsertBean.getOffenderDeductionId());
					String vOffBeanExistFlag = null;
					if (vDupRec == 0) {
						vOffBeanExistFlag = "N";
					} else {
						vOffBeanExistFlag = "Y";
					}
					String vUnlimDed = "N";
					if ("N".equals(vOffBeanExistFlag)) {
						final Integer vClientWash = ocdoobliRepository.getvClientWash();

						final Integer count = ocdoobliRepository.getLvCount(objBeaninsertBean.getCaseloadId(),
								objBeaninsertBean.getDeductionType());
						Integer lvFlatRate = ocdoobliRepository.getLvFlatRate(objBeaninsertBean.getCaseloadId(),
								objBeaninsertBean.getDeductionType());
						Integer nonLvFlatRate = ocdoobliRepository.getNonLvFlatRate(objBeaninsertBean.getCaseloadId(),
								objBeaninsertBean.getDeductionType());
						if (lvFlatRate == 0 && nonLvFlatRate == 0) {
							if (vClientWash == 0) {
							}

						}
						if (objBeaninsertBean.getMaxTotalAmount() == null) {
							objBeaninsertBean.setMaxTotalAmount(BigDecimal.valueOf(-1));
						}
						if (objBeaninsertBean.getMaxRecursiveAmount() == null) {
							objBeaninsertBean.setMaxRecursiveAmount(BigDecimal.valueOf(-1));
						}
						if (objBeaninsertBean.getMaxMonthlyAmount() == null) {
							objBeaninsertBean.setMaxMonthlyAmount(BigDecimal.valueOf(-1));
						}

						if (objBeaninsertBean.getMaxTotalAmount().compareTo(BigDecimal.valueOf(0)) == -1
								&& objBeaninsertBean.getMaxRecursiveAmount().compareTo(BigDecimal.valueOf(0)) == -1
								&& objBeaninsertBean.getMaxMonthlyAmount().compareTo(BigDecimal.valueOf(0)) == -1) {
							objBeaninsertBean.setAmount(BigDecimal.valueOf(999999000.99));
							vUnlimDed = "Y";
							objBeaninsertBean.setvUnlimDed(vUnlimDed);

						} else if (objBeaninsertBean.getMaxTotalAmount().compareTo(BigDecimal.valueOf(0)) >= 0
								&& objBeaninsertBean.getMaxRecursiveAmount().compareTo(BigDecimal.valueOf(0)) == -1
								&& objBeaninsertBean.getMaxMonthlyAmount().compareTo(BigDecimal.valueOf(0)) == -1) {
							objBeaninsertBean.setAmount(objBeaninsertBean.getMaxTotalAmount());

						} else if (objBeaninsertBean.getMaxTotalAmount().compareTo(BigDecimal.valueOf(0)) == -1
								&& objBeaninsertBean.getMaxRecursiveAmount().compareTo(BigDecimal.valueOf(0)) >= 0
								&& objBeaninsertBean.getMaxMonthlyAmount().compareTo(BigDecimal.valueOf(0)) == -1) {
							objBeaninsertBean.setAmount(objBeaninsertBean.getMaxRecursiveAmount());

						} else if (objBeaninsertBean.getMaxTotalAmount().compareTo(BigDecimal.valueOf(0)) == -1
								&& objBeaninsertBean.getMaxRecursiveAmount().compareTo(BigDecimal.valueOf(0)) == -1
								&& objBeaninsertBean.getMaxMonthlyAmount().compareTo(BigDecimal.valueOf(0)) >= 0) {
							objBeaninsertBean.setAmount(objBeaninsertBean.getMaxMonthlyAmount());

						} else {
							return 101;
						}
						final List<OffenderBeneficiaries> multiBeanList = objBeaninsertBean.getOffenderBeneficiaries();
						for (final OffenderBeneficiaries offBenf : multiBeanList) {
							offBenf.setOffenderDeductionId(objBeaninsertBean.getOffenderDeductionId());
							offBenf.setOffenderId(BigDecimal.valueOf(objBeaninsertBean.getOffenderId()));
							offBenf.setCreateUserId(objBeaninsertBean.getCreateUserId());
							/*	offBenf.setPersonId(objBeaninsertBean.getOffenderBeneficiaries() != null
										? BigDecimal.valueOf(objBeaninsertBean.getPersonId())
												: null);*/
							if (offBenf.getCorporateId() != null) {
								offBenf.setUnknownBenId(null);
							} else if (offBenf.getPersonId() != null) {
								offBenf.setUnknownBenId(null);
							} else {
								final Integer unknownBenId = ocdoobliRepository.getUnknownIdNextVal();
								offBenf.setUnknownBenId(BigDecimal.valueOf(Long.valueOf(unknownBenId.toString())));
							}

							if (count == 0) {
								offenderBeneficiariesT2Service.offenderBeneficiariesT2Trigger(offBenf, ApplicationConstants.INSERT);
								ocdoobliRepository
								.insertMultipleBeneficiaries(offBenf);
							} else {
								offenderBeneficiariesT2Service.offenderBeneficiariesT2Trigger(offBenf, ApplicationConstants.INSERT);
								if(offBenf.getPersonId() != null) {
									offBenf.setUnknownBenId(offBenf.getPersonId());
								} else if(offBenf.getCorporateId() != null) {
									offBenf.setUnknownBenId(offBenf.getCorporateId());
								}
								else {
									offBenf.setUnknownBenId(ocdoobliRepository.getUnknownIdNextVal()!=null?BigDecimal.valueOf(ocdoobliRepository.getUnknownIdNextVal()):null);
								}
								offBenf.setBeneficiaryId(ocdoobliRepository.getBenfiNextId());
								ocdoobliRepository.insertIntoOffenderBenficiaries(offBenf);
							}
						}

						String vReceiptTypeExistFlg = null;
						final Integer receiptCount = ocdoobliRepository
								.checkReceiptTypeInserted(objBeaninsertBean.getOffenderDeductionId());
						if (receiptCount == 0) {
							vReceiptTypeExistFlg = "N";
						} else {
							vReceiptTypeExistFlg = "Y";
						}
						if (vReceiptTypeExistFlg == "N") {
							final List<OffenderDeductionReceipts> receiptBeanList = objBeaninsertBean
									.getOffenderDeductionReceipts();
							for (final OffenderDeductionReceipts cslodDedBean : receiptBeanList) {
								cslodDedBean.setOffenderDeductionId(objBeaninsertBean.getOffenderDeductionId());
								cslodDedBean.setCreateUserId(objBeaninsertBean.getCreateUserId());
								final String existFlg = ocdoobliRepository.getExistFlg(
										cslodDedBean.getOffenderDeductionId(), cslodDedBean.getReceiptTxnType());
								if ("N".equals(existFlg)) {
									ocdoobliRepository
									.insertIntoOffenderDeductionreceipts(cslodDedBean);
								}

							}

						}

					}
				}
				try {
					for (final OffenderDeductions objBean : insertList) {
						final List<OffenderBeneficiaries> benFList = ocdoobliRepository
								.postInsert(objBean.getOffenderDeductionId());
						ocdoobliRepository.profilePlanFlag();

						for (final OffenderBeneficiaries bean : benFList) {
							final List<OffenderBeneficiaries> benAmntList = ocdoobliRepository
									.benAmnt(objBean.getOffenderDeductionId(), bean.getPriority());
							bean.setOffenderDeductionId(objBean.getOffenderDeductionId());
							for (final OffenderBeneficiaries benAmnt : benAmntList) {
								benAmnt.setOffenderDeductionId(objBean.getOffenderDeductionId());
								if (!bean.getTotalAmount().equals(BigDecimal.ZERO)) {
									OffenderBeneficiaries newRec = new OffenderBeneficiaries();
									BeanUtils.copyProperties(benAmnt, newRec);
									newRec.setTotalAmount(bean.getTotalAmount());
									offenderBeneficiariesT1Service.offenderBeneficiariesT1Trigger(newRec, benAmnt);
									offenderBeneficiariesT2Service.offenderBeneficiariesT2Trigger(newRec, "UPDATE");
									ocdoobliRepository.updateOffenderBeneficiaries(benAmnt.getBeneficiaryId(),
											benAmnt.getAmount(), bean.getTotalAmount(), objBean.getCreateUserId());

								} else {
									OffenderBeneficiaries newRec = new OffenderBeneficiaries();
									BeanUtils.copyProperties(benAmnt, newRec);
									newRec.setTotalAmount(bean.getTotalAmount());
									offenderBeneficiariesT1Service.offenderBeneficiariesT1Trigger(newRec, benAmnt);
									offenderBeneficiariesT2Service.offenderBeneficiariesT2Trigger(newRec, "UPDATE");
									ocdoobliRepository.updateElseOffenderBeneficiaries(benAmnt.getBeneficiaryId(),
											benAmnt.getModifyUserId());
								}
								final Integer totPercent = ocdoobliRepository
										.getPercetanage(objBean.getOffenderDeductionId(), bean.getPriority());
								if (!Integer.valueOf(0).equals(totPercent) && !Integer.valueOf(100).equals(totPercent)) {
									OffenderBeneficiaries oldRec = ocdoobliRepository
											.offenderBeneficiariesData(bean.getBeneficiaryId());
									OffenderBeneficiaries newRec = new OffenderBeneficiaries();
									BeanUtils.copyProperties(oldRec, newRec);
									newRec.setAmount(benAmnt.getAmount());
									newRec.setTotalAmount(bean.getTotalAmount());
									offenderBeneficiariesT1Service.offenderBeneficiariesT1Trigger(newRec, oldRec);
									offenderBeneficiariesT2Service.offenderBeneficiariesT2Trigger(newRec, "UPDATE");
									ocdoobliRepository.updatePercentageInBenficiaries(totPercent, bean.getPriority(),
											objBean.getOffenderDeductionId(), objBean.getModifyUserId());

								}
							}
						}

					}
				}catch (Exception e) {
					logger.error(this.getClass().getName() + " error in offDedInsertOffenderDeductions " + e);
				}

			}
		}

		return listRet;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_BNC
	 *
	 *
	 */
	@Transactional
	public String offBncCommit(final OffenderBeneficiariesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OffenderBeneficiaries obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = offBncInsertOffenderBeneficiaries(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderBeneficiaries obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = offBncUpdateOffenderBeneficiaries(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (OffenderBeneficiaries obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = offBncDeleteOffenderBeneficiaries(commitBean.getDeleteList());
		}
		return String.valueOf(liReturn);
	}

	private Integer offBncDeleteOffenderBeneficiaries(final List<OffenderBeneficiaries> deleteList) {
		BigDecimal vDedAmnt = null;
		String vUnlimDedFlag = null;
		for (final OffenderBeneficiaries checkDedamnt : deleteList) {
			if (vDedAmnt == null) {
				vUnlimDedFlag = "Y";
			} else {
				vUnlimDedFlag = "N";
			}
			if ("Y".equals(vUnlimDedFlag)) {

			} else if ("N".equals(vUnlimDedFlag)) {
				if (vDedAmnt == null) {
					vDedAmnt = BigDecimal.valueOf(-1);
				}
				if (vDedAmnt == null) {
					checkDedamnt.setAmount(BigDecimal.valueOf(-1));
				}
				if (vDedAmnt != checkDedamnt.getAmount()) {
					return 555;

				}

			}
		}
		final Integer listReturn = ocdoobliRepository.offBncDeleteOffenderBeneficiaries(deleteList);
		try {
			for (final OffenderBeneficiaries obj : deleteList) {
				final List<OffenderBeneficiaries> returnList = ocdoobliRepository.postInsert(obj.getOffenderDeductionId());
				if (returnList != null) {
					for (final OffenderBeneficiaries bp : returnList) {
						final List<OffenderBeneficiaries> benAmntList = ocdoobliRepository
								.benAmnt(obj.getOffenderDeductionId(), bp.getPriority());
						for (final OffenderBeneficiaries benAmnt : benAmntList) {
							if (!bp.getTotalAmount().equals(BigDecimal.ZERO)) {
								OffenderBeneficiaries oldRec = ocdoobliRepository
										.offenderBeneficiariesData(bp.getBeneficiaryId());
								OffenderBeneficiaries newRec = new OffenderBeneficiaries();
								BeanUtils.copyProperties(oldRec, newRec);
								newRec.setTotalAmount(bp.getTotalAmount());
								newRec.setModifyUserId(bp.getModifyUserId());
								offenderBeneficiariesT1Service.offenderBeneficiariesT1Trigger(newRec, oldRec);
								offenderBeneficiariesT2Service.offenderBeneficiariesT2Trigger(obj, "UPDATE");
								ocdoobliRepository.updateOffenderBeneficiaries(benAmnt.getBeneficiaryId(),
										benAmnt.getAmount(), bp.getTotalAmount(), bp.getModifyUserId());

							} else {
								OffenderBeneficiaries newRec = new OffenderBeneficiaries();
								ocdoobliRepository
								.offenderBeneficiariesData(bp.getBeneficiaryId());
								BeanUtils.copyProperties(benAmnt, newRec);
								newRec.setAmount(benAmnt.getAmount());
								newRec.setTotalAmount(benAmnt.getTotalAmount());
								offenderBeneficiariesT1Service.offenderBeneficiariesT1Trigger(newRec, benAmnt);
								offenderBeneficiariesT2Service.offenderBeneficiariesT2Trigger(newRec, "UPDATE");
								ocdoobliRepository.updateElseOffenderBeneficiaries(benAmnt.getBeneficiaryId(),
										benAmnt.getModifyUserId());
							}
							final Integer totPercent = ocdoobliRepository.getPercetanage(obj.getOffenderDeductionId(),
									bp.getPriority());
							if (!Integer.valueOf(0).equals(totPercent) && !Integer.valueOf(100).equals(totPercent)) {
								OffenderBeneficiaries oldRec = ocdoobliRepository
										.offenderBeneficiariesData(bp.getBeneficiaryId());
								OffenderBeneficiaries newRec = new OffenderBeneficiaries();
								BeanUtils.copyProperties(oldRec, newRec);
								newRec.setAmount(benAmnt.getAmount());
								newRec.setTotalAmount(bp.getTotalAmount());
								offenderBeneficiariesT1Service.offenderBeneficiariesT1Trigger(newRec, oldRec);
								offenderBeneficiariesT2Service.offenderBeneficiariesT2Trigger(newRec, "UPDATE");
								ocdoobliRepository.updatePercentageInBenficiaries(totPercent, bp.getPriority(),
										obj.getOffenderDeductionId(), obj.getModifyUserId());

							}

						}
					}
				}

			}
		}catch (Exception e) {
			logger.error(this.getClass().getName() + " error in offBncDeleteOffenderBeneficiaries " + e);
		}
		return listReturn;
	}

	private Integer offBncUpdateOffenderBeneficiaries(final List<OffenderBeneficiaries> updateList) {
		ArrayList<OffenderBeneficiaries> oldRecList = new ArrayList<OffenderBeneficiaries>();
		for (final OffenderBeneficiaries obj : updateList) {
			OffenderBeneficiaries oldRec = ocdoobliRepository.offenderBeneficiariesData(obj.getBeneficiaryId());
			oldRecList.add(oldRec);
			offenderBeneficiariesT1Service.offenderBeneficiariesT1Trigger(obj, oldRec);
			offenderBeneficiariesT2Service.offenderBeneficiariesT2Trigger(obj, "UPDATE");
			if ("Y".equals(obj.getUnknownBenId()) || (obj.getPersonId() == null && obj.getCorporateId() == null)) {
				final Integer unknownId = ocdoobliRepository.getUnknownIdNextVal();
				obj.setUnknownBenId(BigDecimal.valueOf(Long.valueOf(unknownId.toString())));
			} else {
				obj.setUnknownBenId(null);
			}
		}

		final Integer listreturn = ocdoobliRepository.offBncUpdateOffenderBeneficiaries(updateList);
		try {
			for (final OffenderBeneficiaries objBean : updateList) {
				final List<OffenderBeneficiaries> returnList = ocdoobliRepository
						.postInsert(objBean.getOffenderDeductionId());
				for (final OffenderBeneficiaries bp : returnList) {
					final List<OffenderBeneficiaries> benAmntList = ocdoobliRepository
							.benAmnt(objBean.getOffenderDeductionId(), bp.getPriority());
					for (final OffenderBeneficiaries benAmnt : benAmntList) {
						if (!bp.getTotalAmount().equals(BigDecimal.ZERO)) {
							OffenderBeneficiaries newRec = new OffenderBeneficiaries();
							BeanUtils.copyProperties(benAmnt, newRec);
							newRec.setTotalAmount(bp.getTotalAmount());
							newRec.setModifyUserId(objBean.getModifyUserId());
							offenderBeneficiariesT1Service.offenderBeneficiariesT1Trigger(newRec, benAmnt);
							offenderBeneficiariesT2Service.offenderBeneficiariesT2Trigger(newRec, "UPDATE");
							ocdoobliRepository.updateOffenderBeneficiaries(benAmnt.getBeneficiaryId(), benAmnt.getAmount(),
									bp.getTotalAmount(), objBean.getModifyUserId());
						} else {
							OffenderBeneficiaries newRec = new OffenderBeneficiaries();
							BeanUtils.copyProperties(benAmnt, newRec);
							newRec.setModifyUserId(objBean.getModifyUserId());
							offenderBeneficiariesT1Service.offenderBeneficiariesT1Trigger(newRec, benAmnt);
							offenderBeneficiariesT2Service.offenderBeneficiariesT2Trigger(newRec, "UPDATE");
							ocdoobliRepository.updateElseOffenderBeneficiaries(benAmnt.getBeneficiaryId(),
									benAmnt.getModifyUserId());
						}
						final Integer totPercent = ocdoobliRepository.getPercetanage(objBean.getOffenderDeductionId(),
								bp.getPriority());
						if (!Integer.valueOf(0).equals(totPercent) && !Integer.valueOf(100).equals(totPercent)) {
							OffenderBeneficiaries newRec = new OffenderBeneficiaries();
							BeanUtils.copyProperties(benAmnt, newRec);
							newRec.setTotalAmount(bp.getTotalAmount());
							newRec.setModifyUserId(objBean.getModifyUserId());
							newRec.setPriority(bp.getPriority());
							offenderBeneficiariesT1Service.offenderBeneficiariesT1Trigger(newRec, benAmnt);
							offenderBeneficiariesT2Service.offenderBeneficiariesT2Trigger(newRec, "UPDATE");
							ocdoobliRepository.updatePercentageInBenficiaries(totPercent, bp.getPriority(),
									objBean.getOffenderDeductionId(), objBean.getModifyUserId());
						}
					}
				}

			}
		}catch (Exception e) {
			logger.error(this.getClass().getName() + " error in offBncUpdateOffenderBeneficiaries " + e);
		}

		return listreturn;
	}

	private Integer offBncInsertOffenderBeneficiaries(final List<OffenderBeneficiaries> insertList) {
		for (final OffenderBeneficiaries obj : insertList) {
			if ("Y".equals(obj.getUnknownBenId()) || (obj.getPersonId() == null && obj.getCorporateId() == null)) {
				final Integer unknownId = ocdoobliRepository.getUnknownIdNextVal();
				obj.setUnknownBenId(BigDecimal.valueOf(Long.valueOf(unknownId.toString())));

			}
			final Long benId = ocdoobliRepository.getBenfiNextId();
			obj.setBeneficiaryId(benId);

			offenderBeneficiariesT2Service.offenderBeneficiariesT2Trigger(obj, "INSERT");
		}
		final Integer listReturn = ocdoobliRepository.offBncInsertOffenderBeneficiaries(insertList);
		return listReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 *
	 */
	public List<OffenderBeneficiaries> offBncExecuteQuery(final OffenderBeneficiaries searchRecord) {
		BigDecimal priorityTot = null;
		final List<OffenderBeneficiaries> returnList = ocdoobliRepository.offBncExecuteQuery(searchRecord);
		BigDecimal lvBenPercent = BigDecimal.valueOf(0);
		for (final OffenderBeneficiaries obj : returnList) {
			if (obj.getPersonId() != null) {
				final OffenderBeneficiaries off = ocdoobliRepository.getFirstlastNamesusflg(obj.getPersonId());
				if (off != null) {
					obj.setLastName(off.getLastName());
					obj.setFirstName(off.getFirstName());
					obj.setCgnbtPercent(off.getSuspendedFlag());
					obj.setCgnbtPersonId(off.getLastName() + "," + off.getFirstName());
				}
			}
			if (obj.getCorporateId() != null) {
				final OffenderBeneficiaries corpNaame = ocdoobliRepository.getCorporateNamesusflg(obj.getCorporateId());
				obj.setCorporateName(corpNaame.getCorporateName());
				obj.setCgnbtPercent(corpNaame.getSuspendedFlag());
			}
			if (obj.getAmount().compareTo(BigDecimal.ZERO) == 0) {
				lvBenPercent = BigDecimal.valueOf(0);
				obj.setDspPercent(lvBenPercent);
			} else {
				if (obj.getOffenderDeductionId() != null && obj.getPriority() != null) {
					priorityTot = ocdoobliRepository.calcBenPercent(obj.getOffenderDeductionId(), obj.getPriority());
				}
				if (obj.getMonthlyAmount() == null) {
					if (obj.getAmount().equals(BigDecimal.valueOf(999999999.99))) {
						obj.setDspPercent(obj.getPercent());
					}
				}
				if (obj.getMonthlyAmount() != null) {
					BigDecimal maxMonthly = ocdoobliRepository
							.getMaxMonthlyAmountfromDeductions(obj.getOffenderDeductionId());
					if (maxMonthly != null && obj.getMonthlyAmount() != null
							&& !(obj.getMonthlyAmount().compareTo(BigDecimal.ZERO) == 0)) {
						final BigDecimal lbPercent = ocdoobliRepository.getPercentFromMonthly(obj.getMonthlyAmount(),
								obj.getOffenderDeductionId(), obj.getPriority());

						obj.setDspPercent(lbPercent);
					}
				} else {

					final BigDecimal lbMaxtotPercent = ocdoobliRepository.getPercentFromMaxTot(obj.getAmount(),
							obj.getOffenderDeductionId(), obj.getPriority());
					obj.setDspPercent(lbMaxtotPercent);

				}

			}

		}

		return returnList;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 *
	 */
	public CaseloadDeductionProfiles cgfkchkOffDedOffDedCsld(final String deductinType, final String user) {
		return ocdoobliRepository.cgfkchkOffDedOffDedCsld(deductinType, user);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 *
	 */
	public List<OffenderDeductionReceipts> offDrExecuteQuery(final OffenderDeductionReceipts searchRecord) {

		final List<OffenderDeductionReceipts> returnList = ocdoobliRepository.offDrExecuteQuery(searchRecord);

		for (final OffenderDeductionReceipts obj : returnList) {
			final String desc = ocdoobliRepository.getRecieptTxntypeDesc(obj.getReceiptTxnType());
			obj.setDspDescription(desc);
			obj.setBell(obj.getReceiptTxnType());

		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_DR
	 *
	 *
	 */
	@Transactional
	public String offDrCommit(final OffenderDeductionReceiptsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OffenderDeductionReceipts obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdoobliRepository.offDrInsertOffenderDeductionReceipts(commitBean.getInsertList());
			return String.valueOf(liReturn);
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderDeductionReceipts obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdoobliRepository.offDrUpdateOffenderDeductionReceipts(commitBean.getUpdateList());
			return String.valueOf(liReturn);
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (OffenderDeductionReceipts obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdoobliRepository.offDrDeleteOffenderDeductionReceipts(commitBean.getDeleteList());
			return String.valueOf(liReturn);
		}
		return String.valueOf(liReturn);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 *
	 */
	public List<OffenderDeductions> offDedExecuteQuery(final OffenderDeductions searchRecord) {
		String unlFlag = null;
		String profileVal = ocdoobliRepository.getProfileVal();
		if ("Y".equals(profileVal)) {
			searchRecord.setProfilePayplnFlg("Y");
		} else {
			searchRecord.setProfilePayplnFlg("N");
		}
		final List<OffenderDeductions> returnList = ocdoobliRepository.offDedExecuteQuery(searchRecord);
		if (searchRecord.getOffenderId() != null) {
			unlFlag = ocdoobliRepository.getUnlFlg(searchRecord.getOffenderId());
		}
		for (final OffenderDeductions obj : returnList) {
			final String desc = ocdoobliRepository.getDesc(obj.getDeductionType());
			obj.setDeductionDesc(desc);
			final String deductionCat = ocdoobliRepository.getDeductionCategory(obj.getDeductionType());
			obj.setDeductionCategory(deductionCat);
			obj.setUniqueobligationprofile(unlFlag);
			if (obj.getMaxRecursiveAmount() == null && obj.getMaxTotalAmount() == null
					&& obj.getMaxMonthlyAmount() == null) {
				obj.setCgnbtMaxMonthlyAmount("Y");
			} else {
				obj.setCgnbtMaxMonthlyAmount("N");
			}
			if (obj.getAdjustmentAmount() == null) {
				obj.setAdjustmentAmount(new BigDecimal(0));
			}
			if (obj.getAdjustmentAmount().compareTo(BigDecimal.ZERO) > 0 && obj.getAdjustmentReasonCode() != null) {
				if (obj.getMaxTotalAmount() == null) {
					obj.setMaxTotalAmount(new BigDecimal(0));
				}
				if (obj.getDeductionAmount() == null) {
					obj.setDeductionAmount(new BigDecimal(0));
				}
				if (obj.getMaxMonthlyAmount() == null) {
					obj.setMaxMonthlyAmount(BigDecimal.valueOf(0));
				}
				final BigDecimal dedandadjAmnt = obj.getDeductionAmount().add(obj.getAdjustmentAmount());
				if (obj.getMaxTotalAmount().compareTo(dedandadjAmnt) == 1) {
					obj.setCgnbtAdjustmentStatus("P");

				} else {
					if (obj.getMaxTotalAmount().compareTo(dedandadjAmnt) == 0) {
						obj.setCgnbtAdjustmentStatus("F");
					}
				}

			}

			List<DeductionTypes> parentDecuctionTypes = cgfkchkOffDedOffDedT(obj.getDeductionType(),
					obj.getCaseloadId());
			if (parentDecuctionTypes != null && !parentDecuctionTypes.isEmpty()) {
				parentDecuctionTypes.stream().forEach(data -> obj.setParentDeductionType(data));
			}

			List<OffenderTransactions> transactionDeatail = checkprevDedTxnAndCheckpreviousBenrcvied(obj);
			if (transactionDeatail != null && !transactionDeatail.isEmpty()) {
				transactionDeatail.stream().forEach(data -> obj.setTransactionDetails(data));
			}

		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_DED1
	 *
	 *
	 */

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 *
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return ocdoobliRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 *
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean commitBean) {
		final int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			// liReturn =
			// ocdoobliRepository.sysPflInsertsysPfl(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			// liReturn =
			// ocdoobliRepository.SysPflUpdatesysPfl(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<DeductionTypes> cgfkOffDedDeductionTypeRecordGroup(String userName) {
		final List<DeductionTypes> resultList = ocdoobliRepository.cgfkOffDedDeductionTypeRecordGroup(userName);
		resultList.forEach(element -> {
			if ("N".equals(element.getActiveFlag())) {
				element.setCanDisplay(false);
			}
		});
		return resultList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ObligationGroups> cgfkGroupOblGroupIdRecordGroup(final String deductionType) {
		return ocdoobliRepository.cgfkGroupOblGroupIdRecordGroup(deductionType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> cgfkOffDedDspDescriptionRecordGroup() {
		return ocdoobliRepository.cgfkOffDedDspDescriptionRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> cgfkOffDed1AdjustmentReasoRecordGroup() {
		return ocdoobliRepository.cgfkOffDed1AdjustmentReasoRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<TransactionTypes> cgfkOffDrReceiptTxnTypeRecordGroup(String user) {
		final List<TransactionTypes> returnList = ocdoobliRepository.cgfkOffDrReceiptTxnTypeRecordGroup(user);
		returnList.forEach(result -> {
			if ("N".equals(result.getSealFlag())) {
				result.setCanDisplay(false);
			}
			result.setCode(result.getCode());
			result.setDescription(result.getDescription());

		});

		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<OffenderCases> cgfkOffDedCaseInfoNumberRecordGroup() {
		return ocdoobliRepository.cgfkOffDedCaseInfoNumberRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<Persons> cgfkOffBncPersonIdRecordGroup() {
		return ocdoobliRepository.cgfkOffBncPersonIdRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<Corporates> cgfkOffBncCorporateIdRecordGroup() {
		return ocdoobliRepository.cgfkOffBncCorporateIdRecordGroup();

	}

	public Integer getTCount(final Long offenderId, final String caseloadId, final String deductionType,
			final String deductionPriority) {
		return ocdoobliRepository.getTCount(offenderId, caseloadId, deductionType, deductionPriority);
	}

	public String checkCrTpe(final String deductionType) {
		return ocdoobliRepository.checkCrTpe(deductionType);
	}

	public Integer setJsCondition(final Integer caseId) {
		return ocdoobliRepository.setJsCondition(caseId);
	}

	public Integer omsUtilcomareDateTime(final String effectiveDate, final String dspEffectiveDate) {
		return omsDateTimeService.compareDateTime(effectiveDate, dspEffectiveDate);
	}

	public String displayErrorMessage() {
		return omsUtilsService.displayUserMessage(44, "OMS", "Effective", "Obligation Code Effective");
	}

	public String profilePlanFlag() {
		return ocdoobliRepository.profilePlanFlag();
	}

	public Integer getCheckOne(final Long offenderId, final String informationNumber, final BigDecimal groupId) {
		return ocdoobliRepository.getCheckOne(offenderId, informationNumber, groupId);
	}

	public OffenderDeductions getvsDamtCur(final Integer deductionId) {
		OffenderDeductions returnBean = new OffenderDeductions();
		final Integer val = ocdoobliRepository.getvsDamtCurVal(deductionId);
		if (val != null) {
			returnBean.setVsDamtCurVal(val);
		}
		returnBean = ocdoobliRepository.getvsDamtCur(deductionId);
		return returnBean;

	}

	public BigDecimal getDeductionAmnt(final Integer deductionId) {
		return ocdoobliRepository.getDeductionAmnt(deductionId);
	}

	public Persons getLastFirstNames(final Long personId) {
		Persons returnBean = null;
		returnBean = ocdoobliRepository.getLastFirstNames(personId);
		if (returnBean != null) {
			final String lastFirstName = ocdoobliRepository.getLastFirstName(returnBean.getLastName(),
					returnBean.getFirstName());
			returnBean.setLastNameKey(lastFirstName);
		}
		return returnBean;
	}

	public Integer getPerExists(final Integer deductionId, final Integer personId) {
		return ocdoobliRepository.getPerExists(deductionId, personId);
	}

	public Integer updateBenficiaryTransactions(final OffenderBeneficiaries updateBean) {
		updateBean.setModifyUserId(updateBean.getCreateUserId());
		return ocdoobliRepository.updateBenficiaryTransactions(updateBean);
	}

	public Integer getCorpExists(final Integer deductionId, final Integer corporateId) {
		return ocdoobliRepository.getCorpExists(deductionId, corporateId);
	}

	public List<OffenderDeductionReceipts> getreciepttxnType(final OffenderDeductionReceipts searchBean) {
		List<OffenderDeductionReceipts> tempListBean = null;
		List<OffenderDeductionReceipts> tempListBeanRcpt = null;
		final List<OffenderDeductionReceipts> tempList = ocdoobliRepository
				.getoffdedRecieptList(searchBean.getOffenderDeductionId());
		if (tempList.size() > 1) {
			tempListBean = ocdoobliRepository.getreciepttxnType(searchBean.getOffenderDeductionId(),
					searchBean.getReceiptTxnType());
		}
		if (tempListBean.size() < 0) {
			if ("INST".equals(searchBean.getCaseloadType())) {
				tempListBeanRcpt = ocdoobliRepository.getRcptPercent(searchBean.getCaseloadId(),
						searchBean.getDeductionType(), searchBean.getReceiptTxnType());
				for (final OffenderDeductionReceipts obj : tempListBeanRcpt) {
					if (obj.getPercentage() != null || obj.getFlatRate() != null) {
						obj.setReceiptPercentage(obj.getPercentage());
						obj.setFlatRate(obj.getFlatRate());
						obj.setGlobalSuccessFlag("Y");

					} else {
						obj.setReceiptPercentage(BigDecimal.valueOf(100));
						obj.setGlobalSuccessFlag("Y");
					}
					tempListBeanRcpt.add(obj);
				}

			}
		}
		if (tempListBean.size() > 1) {
			final String bell = "bell";
			tempListBean.get(0).setGlobalSuccessFlag("N");
			tempListBean.get(0).setBell(bell);
			return tempListBean;
		}
		if (tempList.size() < 0) {
			if ("INST".equals(searchBean.getCaseloadType())) {
				tempListBeanRcpt = ocdoobliRepository.getRcptPercent(searchBean.getCaseloadId(),
						searchBean.getDeductionType(), searchBean.getReceiptTxnType());
				for (final OffenderDeductionReceipts objRec : tempListBeanRcpt) {
					if (objRec.getPercentage() != null || objRec.getFlatRate() != null) {
						objRec.setReceiptPercentage(objRec.getPercentage());
						objRec.setFlatRate(objRec.getFlatRate());
						objRec.setGlobalSuccessFlag("Y");

					} else {
						objRec.setReceiptPercentage(BigDecimal.valueOf(100));
						objRec.setGlobalSuccessFlag("Y");
					}
					tempListBeanRcpt.add(objRec);
				}

			}
		}

		return tempListBeanRcpt;
	}

	public Integer getMonths(final Date vEffDate) {

		return ocdoobliRepository.getMonths(vEffDate);
	}

	public Integer updateOffenders(final String oblFlg, String userName, final Long rootOffenderId) {
		omtoffsrcService.omtoffsrc(null, userName);
		ocdoobliRepository.updateOffenders(oblFlg, userName, rootOffenderId);
		offendersVineIntfTrgService.offendersVineIntfTrg(null, userName);
		return null;
	}

	public String getDescriptionforReciptType(final String code) {
		return ocdoobliRepository.getDescriptionforReciptType(code);
	}

	public List<DeductionTypes> cgfkchkOffDedOffDedT(final String deductionType, final String caseloadId) {
		return ocdoobliRepository.cgfkchkOffDedOffDedT(deductionType, caseloadId);
	}

	public List<OffenderTransactions> checkprevDedTxnAndCheckpreviousBenrcvied(final OffenderDeductions bean) {
		final List<OffenderTransactions> returnList = new ArrayList<OffenderTransactions>();
		final OffenderTransactions beanTran = new OffenderTransactions();
		String profileVal = "N";
		String txnExistFlg = "N";
		String vprevBncFlg = "N";
		txnExistFlg = ocdoobliRepository.getPreviousDedTxn(bean.getOffenderId(), bean.getDeductionType(),
				bean.getInformationNumber());
		vprevBncFlg = ocdoobliRepository.checkprevBenRec(bean.getOffenderDeductionId());
		profileVal = ocdoobliRepository.profilePlanFlag();
		if ("Y".equals(profileVal)) {
			if (bean.getMaxTotalAmount() == null) {
				bean.setMaxTotalAmount(BigDecimal.valueOf(0));

			}
			final Integer count = ocdoobliRepository.updateOffenderpaymentPlans(bean);
			if (count > 0) {
				ocdoobliRepository.deleteoffenderObligationHty(bean.getOffenderDeductionId());

			}
		}
		beanTran.setProfileVal(profileVal);
		beanTran.setTxnExistFlg(txnExistFlg);
		beanTran.setVprevBncFlg(vprevBncFlg);
		returnList.add(beanTran);
		return returnList;
	}

	public Corporates getCorporateName(final Long corporateId) {
		return ocdoobliRepository.getCorporateName(corporateId);
	}

	public Integer checkinformationandDeductionType(final Integer offId, final String dedType, final String info) {
		return ocdoobliRepository.checkinformationandDeductionType(offId, dedType, info);
	}

	private OffenderBeneficiaries getBeneficiary(final OffenderDeductions offDed) {
		final OffenderBeneficiaries benf = new OffenderBeneficiaries();
		benf.setOffenderDeductionId(offDed.getOffenderDeductionId());
		return benf;
	}

	private OffenderDeductionReceipts getReceipt(final OffenderDeductions offDed) {
		final OffenderDeductionReceipts offRe = new OffenderDeductionReceipts();
		offRe.setOffenderDeductionId(offDed.getOffenderDeductionId());
		offRe.setOffenderId(BigDecimal.valueOf(offDed.getOffenderId()));
		return offRe;
	}

	public String getDisabledButton(final BigDecimal parentId) {
		return ocdoobliRepository.getDisabledButton(parentId);
	}

}
