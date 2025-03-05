package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.OffenderTrustAccounts;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.trust.trustaccounts.OtidtaccRepository;
import net.syscon.s4.inmate.trust.trustaccounts.OtidtaccService;
import net.syscon.s4.pkgs.indigent.IndigentService;

/**
 * Class OtidtaccServiceImpl
 * 
 */
@Service
public class OtidtaccServiceImpl extends BaseBusiness implements OtidtaccService {

	@Autowired
	private OtidtaccRepository otidtaccRepos;
	
	@Autowired
	private IndigentService indigentService;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderTrustAccounts> offTaExecuteQuery(final OffenderTrustAccounts searchRecord) {
		return otidtaccRepos.offTaExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderSubAccounts> offSubaExecuteQuery(final OffenderSubAccounts searchRecord) {
		List<OffenderSubAccounts> returnList = new ArrayList<>();
		Integer calRemDays = 0;
		if ("TRN".equals(searchRecord.getCaseloadId())) {
			searchRecord.setCaseloadId("OUT");
		}
		returnList = otidtaccRepos.offSubaExecuteQuery(searchRecord);
		final String agyLocId = otidtaccRepos.getAgyLocId(searchRecord);
		searchRecord.setDescription(agyLocId);
		for (final OffenderSubAccounts obj : returnList) {
			obj.setDescription(agyLocId);
			final ReferenceCodes refResult = otidtaccRepos.cgfkchkOffSubaOffSubaAc(obj.getTrustAccountCode());
			final Integer daysRemain = otidtaccRepos.getTheDaysRemaining(obj);
			obj.setDaysRemain(daysRemain);
			if (obj.getIndDate() != null) {
				calRemDays = otidtaccRepos.calculateTheRemainDays(obj);
			}
			if (calRemDays < 0) {
				calRemDays = 0;
			}
			obj.setDaysRemain(calRemDays);
			obj.setSubAccountType(refResult.getCode());
			obj.setDescription(refResult.getDescription());

		}
		return returnList;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderDeductions> offDedExecuteQuery(final OffenderDeductions searchRecord) {
		List<OffenderDeductions> returnList = new ArrayList<OffenderDeductions>();
		final DeductionTypes paramObj = new DeductionTypes();
		OffenderDeductions paramBean = new OffenderDeductions();
		Boolean actualFlag = false;
		returnList = otidtaccRepos.offDedExecuteQuery(searchRecord);
		for (final OffenderDeductions obj : returnList) {
			if (obj.getDeductionType() != null) {
				paramObj.setDeductionType(obj.getDeductionType());
				final DeductionTypes returnObj = otidtaccRepos.getTheDescriptionOfDeductionType(paramObj);
				obj.setDeductionDesc(returnObj.getDeductionDesc());

			}
			final String dedType = otidtaccRepos.getThedeductionCategory(obj.getDeductionType());
			if (obj.getMaxMonthlyAmount() == null) {
				actualFlag = true;
				obj.setMaxMonthlyAmount(new BigDecimal(0));
			} else {
				actualFlag = false;
			}
			if ("FXOB".equals(dedType) && obj.getMaxMonthlyAmount().compareTo(new BigDecimal(0)) != 0) {
				obj.setMaxTotalAmount(obj.getMaxMonthlyAmount());
				obj.setAdjustmentAmount(new BigDecimal(0.00));
				final BigDecimal dedAmount = otidtaccRepos.getTheDeductionAmount(obj.getOffenderDeductionId());
				obj.setDeductionAmount(dedAmount);
				obj.setMaxMonthlyAmount(obj.getMaxMonthlyAmount().subtract(dedAmount));
			} else {
				BigDecimal actual = null;
				if (obj.getMaxTotalAmount() != null) {
					actual = obj.getMaxTotalAmount();
				} else {
					if (Boolean.TRUE.equals(actualFlag)) {
						actual = null;
					} else if (Boolean.FALSE.equals(actualFlag)) {
						actual = obj.getMaxMonthlyAmount();
					}

				}
				obj.setMaxTotalAmount(actual);
				if (actual != null) { // && actual.compareTo(new BigDecimal(0))
										// != 0
					if (obj.getAdjustmentAmount() == null) {
						obj.setAdjustmentAmount(new BigDecimal(0));
					}
					final BigDecimal totMax = actual.subtract(obj.getMaxMonthlyAmount());
					
					if(obj.getDeductionAmount().equals(BigDecimal.ZERO)){
						obj.setMaxMonthlyAmount(totMax.subtract(obj.getAdjustmentAmount()));
					} else{
					obj.setMaxMonthlyAmount(totMax.subtract(obj.getDeductionAmount()));
					obj.setMaxMonthlyAmount(obj.getMaxMonthlyAmount().subtract(obj.getAdjustmentAmount()));
					//obj.setMaxMonthlyAmount(totalOwing);
					}
				} else {
					obj.setUnlimited("UNLIMITED");
					obj.setAdjustmentAmount(new BigDecimal(0.00));
				}
			}
			paramBean = new OffenderDeductions();
			paramBean.setOffenderId(searchRecord.getOffenderId());
			paramBean.setOffenderDeductionId(obj.getOffenderDeductionId());
			paramBean.setDeductionType(obj.getDeductionType());
			final OffenderDeductions returnBean = otidtaccRepos.checkFixMthAct(paramBean);
			if (returnBean.getMaxTotalAmount() != null) {
				obj.setFixedFlag("Y");
			} else {
				obj.setFixedFlag("N");
			}
			if (returnBean.getMaxMonthlyAmount() != null) {
				obj.setMthFlag("Y");
			} else {
				obj.setMthFlag("N");
			}
			if ("A".equals(returnBean.getDeductionStatus())) {
				obj.setActFlag("Y");
			} else {
				obj.setActFlag("N");
			}
		}
		return returnList;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderTransactions> offTxnExecuteQuery(final OffenderTransactions searchRecord) {
		List<OffenderTransactions> returnList = new ArrayList<>();
		returnList = otidtaccRepos.offTxnExecuteQuery(searchRecord);
		Double currentBal = 0.0;
		Double amount= 0.0;
		if (searchRecord.getCurrentCaseLoad() != null) {
			currentBal = otidtaccRepos.getTheCurrentBalanceWithCaseLoad(searchRecord.getOffenderId(),
					searchRecord.getCurrentCaseLoad());
		} else {
			currentBal = Double.valueOf(otidtaccRepos.getTheCurrentBalance(searchRecord.getOffenderId()).toString());
		}
		Double holdBal = otidtaccRepos.getHoldBalance(searchRecord.getOffenderId().toString(),
				searchRecord.getCaseloadId());
		for (final OffenderTransactions obj : returnList) {
			if ("DR".equalsIgnoreCase(obj.getTxnPostingType())) {
				obj.setTxnEntryAmount(obj.getTxnEntryAmount() * -1);
			}
			amount = obj.getTxnEntryAmount();
			if (holdBal != null && "HOA".equals(obj.getTxnType()) || "HOR".equals(obj.getTxnType())) {
				obj.setHoldBalance(holdBal);
				holdBal = holdBal + amount;			
				
				
			} else {
				obj.setHoldBalance(holdBal);
			}
			
			if (currentBal != null) {
				obj.setCurrentBalance(currentBal);
				currentBal = currentBal - amount; 
			} 		
			

			final OffenderTransactions chequeClear = otidtaccRepos.getChequeAndClearData(obj.getTxnId());
			if (chequeClear.getPreWithholdAmount() != null) {
				obj.setClosingChequeNumber(chequeClear.getPreWithholdAmount().longValue()+""); // Modified by Yaseen on 24-June-2019.
			}
			if ("Y".equalsIgnoreCase(chequeClear.getReceiptPrintedFlag())) {
				obj.setReceiptPrintedFlag("Y");
			} else {
				obj.setReceiptPrintedFlag("N");
			}
			final Integer payRollId = otidtaccRepos.getThePayRollId(obj);
			obj.setPayRollId(payRollId);
			final String bookingNo = otidtaccRepos.getTheBookingNoOfOffenderBookId(obj.getOffenderBookId());
			obj.setBookingNo(bookingNo);
			if (obj.getRemitterId() != null) {
				final String remitterName = otidtaccRepos.getTheRemitterName(obj.getRemitterId());
				obj.setRemitterName(remitterName);
			}
			final OffenderTransactions payeeNameBean = otidtaccRepos.populatePayeeRemitterName(obj);
			if (payeeNameBean.getTxnId() != null) {
				if (payeeNameBean.getPayeeNameText() != null) {
					if (payeeNameBean.getPayeePersonId() != null) {
						obj.setPayeeId(payeeNameBean.getPayeePersonId());
					} else if (payeeNameBean.getPayeeCorporateId() != null) {
						obj.setPayeeId(payeeNameBean.getPayeeCorporateId());
					} else {
						obj.setPayeeId(0);
					}
					obj.setPayeeName(payeeNameBean.getPayeeNameText());
				} else {
					if (payeeNameBean.getPayeePersonId() != null) {
						final String personName = otidtaccRepos.getPersonCursor(payeeNameBean.getPayeePersonId());
						obj.setPayeeId(payeeNameBean.getPayeePersonId());
						obj.setPayeeName(personName);

					} else if (payeeNameBean.getPayeeCorporateId() != null) {
						final String corporateName = otidtaccRepos
								.getCorporateCursor(payeeNameBean.getPayeeCorporateId());
						obj.setPayeeId(payeeNameBean.getPayeeCorporateId());
						obj.setPayeeName(corporateName);

					} else {
						if (payeeNameBean.getPayeeNameText() != null) {
							obj.setPayeeId(0);
							obj.setPayeeName(payeeNameBean.getPayeeNameText());
						} else {
							obj.setPayeeId(0);
							obj.setPayeeName(null);
						}
					}

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
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return otidtaccRepos.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<OffenderTransactions> calcAccountBalancesRecordGroup() {
		return otidtaccRepos.calcAccountBalancesRecordGroup();

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public OffenderDeductions populateCreditObligation(final OffenderSubAccounts searchBean) {
		OffenderDeductions returnList = new OffenderDeductions();
		returnList = otidtaccRepos.populateCreditObligation(searchBean.getOffenderId());
		final OffenderDeductions returnFixedList = otidtaccRepos
				.populateFixedObligation(searchBean.getOffenderId());
		//Procedure call
		final String indigentFlag = indigentService.indFlagformula(new BigDecimal(searchBean.getOffenderId()), searchBean.getSealFlag(), searchBean.getCaseloadId());
		final String acntClosedFlag = otidtaccRepos.getaccountClosedFlag(searchBean);
		final String nbtAcntFlag = otidtaccRepos.getNbtAccountClosedFlag(searchBean);
		returnList.setAccountClosedFlag(nbtAcntFlag);
		returnList.setIndigentFlag(indigentFlag);
		returnList.setAcntClosedFlag(acntClosedFlag);
		returnList.setMaxMonthlyAmount(returnFixedList.getMaxMonthlyAmount());
		returnList.setMaxRecursiveAmount(returnFixedList.getMaxRecursiveAmount());
		return returnList;

	}
}