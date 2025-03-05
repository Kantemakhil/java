package net.syscon.s4.cf.deductions.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cf.deductions.OcufovdtRepository;
import net.syscon.s4.cf.deductions.OcutrdetRepository;
import net.syscon.s4.cf.deductions.OcutrdetService;
import net.syscon.s4.cf.deductions.beans.FeeAccountBalanceBean;
import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.cf.offendertransactions.OcdadjusRepository;
import net.syscon.s4.cm.intakeclosure.OcdsupstRepository;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderTransactions;

@Service
public class OcutrdetServiceImpl extends BaseBusiness implements OcutrdetService {

	@Autowired
	private OcutrdetRepository ocutrdetRepository;

	@Autowired
	private OcdsupstRepository ocdsupstRepository;

	@Autowired
	private OcdadjusRepository ocdadjusRepository;

	@Autowired
	private EliteDateService eliteDateService;

	@Autowired
	private OcufovdtRepository ocufovdtRepository;

	
	
	@Override
	public List<ReferenceCodes> overrideTypeRecordGroup() {
		return ocutrdetRepository.overrideTypeRecordGroup();
	}

	@Override
	public FeeAccountProfiles feeActExecuteQuery(final FeeAccountProfiles searchBean) {
		final FeeAccountProfiles returnData = new FeeAccountProfiles();
		returnData.setFeeCode(ocutrdetRepository.getDeductionDesc(searchBean.getFeeCode()));
		returnData.setOffenderFeeId(searchBean.getOffenderFeeId());
		returnData.setAmount(searchBean.getAmount());
		returnData.setStartDate(searchBean.getStartDate());
		returnData.setEffectiveDate(searchBean.getEffectiveDate());
		returnData.setCode(ocutrdetRepository.getFrequency(searchBean.getFrequencyCode()));
		returnData.setLocation(ocutrdetRepository.getLocation(searchBean.getCaseloadId()));
		returnData.setFrequency(ocutrdetRepository.getFrequencyType(searchBean.getFrequencyType()));
		returnData.setFeeActStatusDesc(ocutrdetRepository.feeActStatusDescription(searchBean.getFeeActStatus()));
		BigDecimal balanceAmount = calculateCurrentBalance(searchBean);
		returnData.setCurrentBalance(balanceAmount);
		return returnData;
	}

	@Override
	public List<OffFeeBills> billDetailsExecuteQuery(final OffFeeBills searchBean) {
		List<OffFeeBills> returnData = ocutrdetRepository.billDetailsExecuteQuery(searchBean);
		for (OffFeeBills offFeeBills : returnData) {
			OffFeeBillTransactions object = new OffFeeBillTransactions();
			object.setBillId(offFeeBills.getBillId());
			object.setAdjustmentAmountTot(offFeeBills.getBillGenerateAmount());
			if(offFeeBills.getBillGenerateStaffId() != null) {
			offFeeBills.setUserId(ocufovdtRepository.getUserID(offFeeBills.getBillGenerateStaffId().longValue()));
			}else {
				offFeeBills.setUserId(offFeeBills.getBillGenerateUser());
			}
			offFeeBills.setBillGenerateStatus(ocutrdetRepository.getBillStatusForBillId(offFeeBills.getBillId()));
			offFeeBills.setBillTotalAmount(offFeeBills.getBillGenerateAmount().add(offFeeBills.getBillOverrideIncreaseAmount()).subtract(offFeeBills.getBillOverrideDecreaseAmount()));
			String comment =ocutrdetRepository.getBillDetailsCommentValue(offFeeBills.getBillId());
			offFeeBills.setBillTxnComment(comment);
		}
		return returnData;
	}

	@Override
	public List<OffFeeBillTransactions> billTransDetailsExecuteQuery(final OffFeeBillTransactions searchBean) {
		List<OffFeeBillTransactions> returnData = ocutrdetRepository.billTransDetailsExecuteQuery(searchBean);
		for (OffFeeBillTransactions offFeeBillTxn : returnData) {
			if (offFeeBillTxn.getBillTxnStaffId() != null) {
				offFeeBillTxn.setUserId(ocufovdtRepository.getUserID(offFeeBillTxn.getBillTxnStaffId().longValue()));
			}
			if("R".equals(offFeeBillTxn.getTxnUsage())) {			
				OffenderTransactions offenderTransactions = ocutrdetRepository.getOffTransData(
						offFeeBillTxn.getTrustTxnId(), offFeeBillTxn.getTrustTxnEntrySeq());
				if (offenderTransactions != null) {
					if (offenderTransactions.getTxnReferenceNumber() != null) {
						System.out
								.println("Reference NUmber" + offenderTransactions.getTxnReferenceNumber());
					}
					offFeeBillTxn.setBillTxnNo(offenderTransactions.getTxnId());
					offFeeBillTxn.setTxnReferenceNumber(offenderTransactions.getTxnReferenceNumber());
					offFeeBillTxn.setOriginalBillId(offenderTransactions.getReceiptNumber());
				}
			}	
		}
		return returnData;
	}

	@Override
	public List<OffFeeBillTransactions> billTransTotalExecuteQuery(final OffFeeBillTransactions searchBean) {
		OffFeeBills searchBeanBillDet = new OffFeeBills();
		List<OffFeeBillTransactions> finalResult = new ArrayList<>();
		BigDecimal owingamount = BigDecimal.ZERO;
		if (searchBean.getOffenderFeeId() != null) {
			searchBeanBillDet.setOffenderFeeId(searchBean.getOffenderFeeId());
			List<OffFeeBills> feeBillData = billDetailsExecuteQuery(searchBeanBillDet);
			for (OffFeeBills offFeeBills : feeBillData) {
				if (offFeeBills.getBillId() != null) {
					OffFeeBillTransactions billTransDto = new OffFeeBillTransactions();
					billTransDto.setBillId(offFeeBills.getBillId());
					List<OffFeeBillTransactions> returnData = ocutrdetRepository
							.billTransDetailsExecuteQuery(billTransDto);
					
					for (OffFeeBillTransactions offFeeBillTxn : returnData) {
						if("B".equals(offFeeBillTxn.getTxnUsage())) {
							BigDecimal owingAmount = ocdadjusRepository.getPostQuery(offFeeBillTxn.getBillId());
							if(owingAmount!=null) {
								owingamount = owingamount.add(owingAmount);
							}
						}
						finalResult.add(offFeeBillTxn);
					}
				}
			}
		}
		Collections.reverse(finalResult);
		if(owingamount!=null && finalResult.size() > 0) {
			finalResult.get(0).setBalanceOwingAmount(owingamount);
		}
		for (OffFeeBillTransactions offFeeBillTransactions : finalResult) {
			if("R".equals(offFeeBillTransactions.getTxnUsage())) {			
				OffenderTransactions offenderTransactions = ocutrdetRepository.getOffTransData(
						offFeeBillTransactions.getTrustTxnId(), offFeeBillTransactions.getTrustTxnEntrySeq());
				if (offenderTransactions != null) {
					if (offenderTransactions.getTxnReferenceNumber() != null) {
						System.out
								.println("Reference NUmber" + offenderTransactions.getTxnReferenceNumber());
					}
					offFeeBillTransactions.setBillTxnNo(offenderTransactions.getTxnId());
					offFeeBillTransactions.setTxnReferenceNumber(offenderTransactions.getTxnReferenceNumber());
					offFeeBillTransactions.setOriginalBillId(offenderTransactions.getReceiptNumber());
				}
			}		
		}
		return finalResult;
	}

	@Override
	public Integer updateBillTransactionDeails(List<OffFeeBills> bean) {
		List<OffFeeBillTransactions> saveList = new ArrayList<>();
		for (OffFeeBills offFeeBills : bean) {
			OffFeeBillTransactions ofFeeBillTrnBean = new OffFeeBillTransactions();
			List<OffFeeBillTransactions> list = ocutrdetRepository.getBillTransDetails(offFeeBills.getBillId());
			if (!list.isEmpty()) {
				ofFeeBillTrnBean = list.get(0);
			}
			Integer staffId = ocdsupstRepository.getstaffId(offFeeBills.getUserId());
			ofFeeBillTrnBean.setBillTxnStaffId(staffId);
			ofFeeBillTrnBean.setBillTxnUser(offFeeBills.getUserId());
			ofFeeBillTrnBean.setBillStatus(offFeeBills.getBillGenerateStatus());
			ofFeeBillTrnBean.setBillTxnNo(ocdsupstRepository.getBillTranId(offFeeBills.getBillId()));
			ofFeeBillTrnBean.setBillTxnDatetime(eliteDateService.getDBTime());
			saveList.add(ofFeeBillTrnBean);
		}
		return ocutrdetRepository.offFeeBillTransInsertQuery(saveList);
	}

	@Override
	public Integer sysPflBillAdjusExecuteQuery(String userName) {
		SystemProfiles returnedObject = new SystemProfiles();
		List<SystemProfiles> returnList = ocutrdetRepository.sysPflBillAdjusExecuteQuery();
		if (!returnList.isEmpty()) {
			returnedObject = returnList.get(0);		
				List<String> roleIdList = ocufovdtRepository.getRoleIdList(userName);
				for (String string : roleIdList) {
					if (string != null && returnedObject.getProfileValue() != null) {
						if (string.equals(returnedObject.getProfileValue())) {
							return 1;
						} 
				}
			}

		}
		return 0;
	}

	@Override
	public Integer sysPflBillStatusExecuteQuery(String userName) {
		SystemProfiles returnedObject = new SystemProfiles();
		List<SystemProfiles> returnList = ocutrdetRepository.sysPflBillStatusExecuteQuery();
		if (!returnList.isEmpty()) {
			returnedObject = returnList.get(0);
			List<String> roleIdList = ocufovdtRepository.getRoleIdList(userName);
			for (String string : roleIdList) {
				if (string != null && returnedObject.getProfileValue() != null) {
					if (string.equals(returnedObject.getProfileValue())) {
						return 1;
					} 
				}
			}
		}
		return 0;
	}
	
	
	public BigDecimal calculateCurrentBalance(FeeAccountProfiles searchBean) {
		BigDecimal currentBalanceOwning=BigDecimal.ZERO;
		OffFeeBills searchBeanBillDet = new OffFeeBills();
		List<OffFeeBillTransactions> finalResult = new ArrayList<>();
		if (searchBean.getOffenderFeeId() != null) {
			searchBeanBillDet.setOffenderFeeId(searchBean.getOffenderFeeId());
			List<OffFeeBills> feeBillData = billDetailsExecuteQuery(searchBeanBillDet);
			for (OffFeeBills offFeeBills : feeBillData) {
				if (offFeeBills.getBillId() != null) {
					OffFeeBillTransactions billTransDto = new OffFeeBillTransactions();
					billTransDto.setBillId(offFeeBills.getBillId());
					List<OffFeeBillTransactions> returnData = ocutrdetRepository
							.billTransDetailsExecuteQuery(billTransDto);
					BigDecimal previousAmnt = BigDecimal.ZERO;
					BigDecimal prviousAmntTemp = BigDecimal.ZERO;
					for (OffFeeBillTransactions offFeeBillTxn : returnData) {
						previousAmnt = offFeeBillTxn.getBillTxnAmount();
						if ("B".equals(offFeeBillTxn.getTxnUsage())) {
							offFeeBillTxn.setBalanceOwingAmount(offFeeBillTxn.getBillTxnAmount());
							offFeeBillTxn.setAdjustmentAmount(null);
						} else if ("R".equals(offFeeBillTxn.getTxnUsage())) {
							offFeeBillTxn.setPaymentAmount(prviousAmntTemp.subtract(offFeeBillTxn.getBillTxnAmount()));
							offFeeBillTxn.setAdjustmentAmount(null);
							offFeeBillTxn.setBalanceOwingAmount(offFeeBillTxn.getBillTxnAmount());
							offFeeBillTxn.setBillTxnAmount(null);
						} else if ("BI".equals(offFeeBillTxn.getTxnUsage())) {
							offFeeBillTxn.setAdjustmentAmount(null);
							offFeeBillTxn.setPaymentAmount(null);
							offFeeBillTxn.setBalanceOwingAmount(offFeeBillTxn.getBillTxnAmount());
							offFeeBillTxn.setBillTxnAmount(offFeeBillTxn.getBillTxnAmount().subtract(prviousAmntTemp));
						} else if ("BD".equals(offFeeBillTxn.getTxnUsage())) {
							offFeeBillTxn.setAdjustmentAmount(null);
							offFeeBillTxn.setPaymentAmount(null);
							offFeeBillTxn.setBillTxnAmount(prviousAmntTemp.subtract(offFeeBillTxn.getBillTxnAmount()));
							offFeeBillTxn.setBalanceOwingAmount(previousAmnt);
						} else if ("A".equals(offFeeBillTxn.getTxnUsage())) {
							offFeeBillTxn
									.setAdjustmentAmount(prviousAmntTemp.subtract(offFeeBillTxn.getBillTxnAmount()));
							offFeeBillTxn.setPaymentAmount(null);
							offFeeBillTxn.setBalanceOwingAmount(
									prviousAmntTemp.subtract(offFeeBillTxn.getAdjustmentAmount()));
							offFeeBillTxn.setBillTxnAmount(null);
						}

						prviousAmntTemp = previousAmnt;
						finalResult.add(offFeeBillTxn);
					}
					
					Collections.reverse(finalResult);
					currentBalanceOwning = currentBalanceOwning.add(finalResult.get(0).getBalanceOwingAmount());
				}
			}
		}
		return currentBalanceOwning;
		
	}
	
	@Override
	public List<FeeAccountBalanceBean> billTransReportExecuteQuery(final OffFeeBillTransactions searchBean) {
		OffFeeBills searchBeanBillDet = new OffFeeBills();
		List<OffFeeBillTransactions> finalResult = new ArrayList<>();
		List<FeeAccountBalanceBean> feeAccntBlnList = new ArrayList<>();
		if (searchBean.getOffenderFeeId() != null) {
			searchBeanBillDet.setOffenderFeeId(searchBean.getOffenderFeeId());
			List<OffFeeBills> feeBillData = billDetailsExecuteQuery(searchBeanBillDet);
			for (OffFeeBills offFeeBills : feeBillData) {
				if (offFeeBills.getBillId() != null) {
					OffFeeBillTransactions billTransDto = new OffFeeBillTransactions();
					billTransDto.setBillId(offFeeBills.getBillId());
					List<OffFeeBillTransactions> returnData = ocutrdetRepository
							.billTransDetailsExecuteQuery(billTransDto);
					BigDecimal previousAmnt = null;
					BigDecimal prviousAmntTemp = null;
					
					for (OffFeeBillTransactions offFeeBillTxn : returnData) {
						previousAmnt = offFeeBillTxn.getBillTxnAmount();
						if(offFeeBillTxn.getBillTxnStaffId()!=null) {							
							offFeeBillTxn
							.setUserId(ocufovdtRepository.getUserID(offFeeBillTxn.getBillTxnStaffId().longValue()));
						}
						if ("B".equals(offFeeBillTxn.getTxnUsage())) {
							offFeeBillTxn.setBalanceOwingAmount(offFeeBillTxn.getBillTxnAmount());
							offFeeBillTxn.setAdjustmentAmount(null);
						} else if ("R".equals(offFeeBillTxn.getTxnUsage())) {
							offFeeBillTxn.setPaymentAmount(prviousAmntTemp.subtract(offFeeBillTxn.getBillTxnAmount()));
							offFeeBillTxn.setAdjustmentAmount(null);
							offFeeBillTxn.setBalanceOwingAmount(offFeeBillTxn.getBillTxnAmount());
							OffenderTransactions offenderTransactions = ocutrdetRepository.getOffTransData(
									offFeeBillTxn.getTrustTxnId(), offFeeBillTxn.getTrustTxnEntrySeq());
							if (offenderTransactions != null) {
								if (offenderTransactions.getTxnReferenceNumber() != null) {
									System.out
											.println("Reference NUmber" + offenderTransactions.getTxnReferenceNumber());
								}
								offFeeBillTxn.setBillTxnNo(offenderTransactions.getTxnId());
								offFeeBillTxn.setTxnReferenceNumber(offenderTransactions.getTxnReferenceNumber());
								offFeeBillTxn.setOriginalBillId(offenderTransactions.getReceiptNumber());
							}
							offFeeBillTxn.setBillTxnAmount(null);
						} else if ("BI".equals(offFeeBillTxn.getTxnUsage())) {
							offFeeBillTxn.setAdjustmentAmount(null);
							offFeeBillTxn.setPaymentAmount(null);
							offFeeBillTxn.setBalanceOwingAmount(offFeeBillTxn.getBillTxnAmount());
							offFeeBillTxn.setBillTxnAmount(offFeeBillTxn.getBillTxnAmount().subtract(prviousAmntTemp));
						} else if ("BD".equals(offFeeBillTxn.getTxnUsage())) {
							offFeeBillTxn.setAdjustmentAmount(null);
							offFeeBillTxn.setPaymentAmount(null);
							offFeeBillTxn.setBillTxnAmount(prviousAmntTemp.subtract(offFeeBillTxn.getBillTxnAmount()));
							offFeeBillTxn.setBalanceOwingAmount(previousAmnt);
						} else if ("A".equals(offFeeBillTxn.getTxnUsage())) {
							offFeeBillTxn
									.setAdjustmentAmount(prviousAmntTemp.subtract(offFeeBillTxn.getBillTxnAmount()));
							offFeeBillTxn.setPaymentAmount(null);
							offFeeBillTxn.setBalanceOwingAmount(
									prviousAmntTemp.subtract(offFeeBillTxn.getAdjustmentAmount()));
							offFeeBillTxn.setBillTxnAmount(null);
							offFeeBillTxn.setBillTxnNo(offFeeBillTxn.getOffAdjTxnId());
						}

						prviousAmntTemp = previousAmnt;
						finalResult.add(offFeeBillTxn);
					}
					BigDecimal initialAmnt = BigDecimal.ZERO;
					BigDecimal adjAmnt = BigDecimal.ZERO;
					BigDecimal payAmnt = BigDecimal.ZERO;
					BigDecimal remainingAmnt = BigDecimal.ZERO;
					for (OffFeeBillTransactions finalObj : finalResult) {
						if("CBILL".equals(finalObj.getBillTxnType())) {
							initialAmnt = initialAmnt.add(finalObj.getBillTxnAmount());
						}
						if("A".equals(finalObj.getTxnUsage())) {
							adjAmnt = adjAmnt.add(finalObj.getAdjustmentAmount());
						} else if("R".equals(finalObj.getTxnUsage())){
							payAmnt =payAmnt.add(finalObj.getPaymentAmount());
						}
						remainingAmnt = initialAmnt.subtract(payAmnt.add(adjAmnt));
						
					}
					FeeAccountBalanceBean feeAcntBlncBean = new FeeAccountBalanceBean();
					feeAcntBlncBean.setBeginingBalanceAmount(initialAmnt);
					feeAcntBlncBean.setPaymentsCreditsAmount(payAmnt);
					feeAcntBlncBean.setBillingsAmount(adjAmnt);
					feeAcntBlncBean.setEndingBalanceAmount(remainingAmnt);
					feeAccntBlnList.add(feeAcntBlncBean);
				}
			}
		}
		return feeAccntBlnList;
	}
}
