package net.syscon.s4.inmate.trust.deductions.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactionsCommitBean;
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderBeneficiariesCommitBean;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.trust.deductions.OcdpayobRepository;
import net.syscon.s4.inmate.trust.deductions.OcdpayobService;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.pkgs.deductions.DeductionsService;
import net.syscon.s4.pkgs.financial.FinancialService;
import net.syscon.s4.pkgs.ocdpayob.OcdpayobPkgService;
import net.syscon.s4.pkgs.trust.TrustService;
import net.syscon.s4.triggers.OffFeeBillTransactionsT1Service;
import net.syscon.s4.triggers.OffFeeBillTransactionsT2Service;
import net.syscon.s4.triggers.OffenderBeneficiariesT1Service;
import net.syscon.s4.triggers.OffenderBeneficiariesT2Service;

/**
 * Class OcdpayobServiceImpl
 */
@Service
public class OcdpayobServiceImpl extends BaseBusiness implements OcdpayobService {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdpayobServiceImpl.class.getName());

	@Autowired
	private OcdpayobRepository ocdpayobRepository;

	@Autowired
	private EliteDateService eliteDateService;


	@Autowired
	private OffenderBeneficiariesT1Service offenderBeneficiariesT1Service;
	@Autowired
	private OffenderBeneficiariesT2Service offenderBeneficiariesT2Service;
	@Autowired
	private OffFeeBillTransactionsT1Service offFeeBillTransactionsT1Service;
	@Autowired
	private OffFeeBillTransactionsT2Service offFeeBillTransactionsT2Service;
	@Autowired
	private OcdpayobPkgService ocdpayobPkgService;
	@Autowired
	private FinancialService financialService;
	@Autowired
	private DeductionsService deductionsService;
	@Autowired
	private TrustService trustService;

	/**
	 * Creates new OcdpayobServiceImpl class Object
	 */
	public OcdpayobServiceImpl() {
		
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public Corporates cgfkchkOffBncOffBncCorp(final OffenderBeneficiaries paramBean) {
		final Corporates obj = ocdpayobRepository.cgfkchkOffBncOffBncCorp(paramBean);
		return obj;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public Persons cgfkchkOffBncOffBncPer(final OffenderBeneficiaries paramBean) {
		final Persons persons = ocdpayobRepository.cgfkchkOffBncOffBncPer(paramBean);
		return persons;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<OffenderTransactions> offTxnExecuteQuery(final OffenderTransactions searchRecord) {
		return ocdpayobRepository.offTxnExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_TXN
	 */
	@Transactional
	public Integer offTxnCommit(final OffenderTransactionsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bean -> bean.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = ocdpayobRepository.offTxnInsertOffenderTransactions(commitBean.getInsertList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<OffenderBeneficiaries> offBncExecuteQuery(final OffenderBeneficiaries searchRecord) {
		searchRecord.setModuleName("OCDPAYOB");
		String deduction=ocdpayobPkgService.setDefaultWhereOffBnc(searchRecord);
		final List<OffenderBeneficiaries> returnList = ocdpayobRepository.offBncExecuteQuery(searchRecord,deduction);
		for (final OffenderBeneficiaries obj : returnList) {
			if (obj.getReceivedAmount() == null) {
				obj.setReceivedAmount(BigDecimal.ZERO);
			}
			if (obj.getPersonId() != null) {
				final Persons returnObj = cgfkchkOffBncOffBncPer(obj);
				obj.setLastName(returnObj.getLastName());
				obj.setFirstName(returnObj.getFirstName());

			}
			if (obj.getCorporateId() != null) {
				final Corporates returnObj = cgfkchkOffBncOffBncCorp(obj);
				obj.setCorporateName(returnObj.getCorporateName());
			}
			if (obj.getOffenderDeductionId() != null) {
				final OffenderDeductions returnObj = ocdpayobRepository.cgfkchkOffBncOffBncOff(obj);
				obj.setDeductionType(returnObj.getDeductionType());
				obj.setInformationNumber(returnObj.getInformationNumber());
				obj.setDeductionDesc(returnObj.getDeductionDesc());
				final OffenderDeductions returnValue = ocdpayobRepository.offBncPostQuery(obj);
				if (returnValue.getMaxTotalAmount() != null) {
					final OffenderDeductions writeOffValue = ocdpayobRepository.offBncWriteOffCur(obj);
					BigDecimal vwrt = new BigDecimal(0);
					if (writeOffValue.getAdjustmentReasonCode() != null) {
						vwrt = writeOffValue.getAdjustmentAmount();
					} else {
						vwrt = BigDecimal.ZERO;
					}
					obj.setDrvAmount((obj.getAmount().subtract(obj.getReceivedAmount()).subtract(vwrt)));
				} else if (returnValue.getMaxMonthlyAmount() != null) {
					if (obj.getPersonId() == null) {
						obj.setPersonId(BigDecimal.ZERO);
					}
					if (obj.getCorporateId() == null) {
						obj.setCorporateId(BigDecimal.ZERO);
					}
					final BigDecimal vMonAmt = ocdpayobRepository.offBncVmonAmt(obj);
					obj.setDrvAmount(obj.getMonthlyAmount().subtract(vMonAmt));
				} else if (returnValue.getMaxRecursiveAmount() != null) {
					final BigDecimal vRecMAx = ocdpayobRepository.offBncVrecMAx(obj);
					obj.setDrvAmount((obj.getAmount().multiply(vRecMAx)).subtract(obj.getReceivedAmount()));
				} else {
					obj.setDrvAmount(new BigDecimal(123456));
				}
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_BNC
	 * @throws Exception 
	 */
	@Transactional(rollbackFor = { Exception.class })
	public OffenderBeneficiaries offBncCommit(final OffenderBeneficiariesCommitBean commitBean) throws Exception {
		OffenderBeneficiaries returnList = new OffenderBeneficiaries();
		List<OffFeeBillTransactions> updateOffFeeBillsList = new ArrayList<OffFeeBillTransactions>();
		int liReturn = 0;
		int offFeeReturn = 0;
		OffenderBeneficiaries oldData=new OffenderBeneficiaries();
		logger.info("OcdpayobServiceImpl offBncCommit before txnIdNextValData");
		Integer txnIdNextVal = ocdpayobRepository.txnIdNextValData();
		logger.info("OcdpayobServiceImpl offBncCommit after txnIdNextValData");
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			Date transDate = trunc(eliteDateService.getDBTime());
			for (final OffenderBeneficiaries objOffTransactions : commitBean.getUpdateList()) {
				objOffTransactions.setCreateUserId(commitBean.getCreateUserId());
				oldData= ocdpayobRepository.getBeneficiariesOldData(objOffTransactions.getBeneficiaryId());
				if (objOffTransactions.getOverrideAmount() != null) {
					offenderBeneficiariesT2Service.offenderBeneficiariesT2Trigger(objOffTransactions, objOffTransactions.getCreateUserId());
					offenderBeneficiariesT1Service.offenderBeneficiariesT1Trigger(objOffTransactions, oldData);
					logger.info("OcdpayobServiceImpl offBncCommit before updateOffenderBeneficiaries");
					liReturn = ocdpayobRepository.updateOffenderBeneficiaries(objOffTransactions);
					logger.info("OcdpayobServiceImpl offBncCommit after updateOffenderBeneficiaries");
				}
				Double amount = Double.valueOf(objOffTransactions.getTxnEntryAmount().toString());
				if (objOffTransactions.getTxnEntryAmount() == null || amount < 0) {
					returnList.setSealFlag("B");
					return returnList;
				} else if (objOffTransactions.getSubAccountType() == null) {
					returnList.setSealFlag("C");
					return returnList;
				} else {
					Integer pDedAmt = 0;
					Integer pTxnEntrySeq = objOffTransactions.getTxnEntrySeq();
					try {
						logger.info("OcdpayobServiceImpl offBncCommit before financialDoDuctionsFinancial");
						financialService.doDeductionsFinancial(objOffTransactions.getCaseloadId(),
							objOffTransactions.getOffenderId()!=null?objOffTransactions.getOffenderId().longValue():null,
									objOffTransactions.getOffenderBookId(),
							objOffTransactions.getTxnType(), txnIdNextVal!=null?txnIdNextVal.longValue():null, transDate,
							objOffTransactions.getSubAccountType(), "Y", objOffTransactions.getTxnEntryAmount(),
							pDedAmt!=null?pDedAmt.longValue():null, null,pTxnEntrySeq,objOffTransactions.getCreateUserId());
						logger.info("OcdpayobServiceImpl offBncCommit after financialDoDuctionsFinancial");
						if (liReturn == 0) {
							returnList.setSealFlag("F");
							return returnList;
						}
					} catch (Exception e) {
						logger.error("financial.Do_Ductions_Financial", e);
					}
					logger.info("OcdpayobServiceImpl offBncCommit before getAcAndSetIndDate");
					deductionsService.getAcAndSetIndDate(objOffTransactions.getOffenderId()!=null?objOffTransactions.getOffenderId().longValue():null,
						objOffTransactions.getCaseloadId(), objOffTransactions.getCreateUserId());
					logger.info("OcdpayobServiceImpl offBncCommit after getAcAndSetIndDate");
					logger.info("OcdpayobServiceImpl offBncCommit before glTxnsCount");
					Integer txnIdCount = ocdpayobRepository.glTxnsCount(txnIdNextVal);
					logger.info("OcdpayobServiceImpl offBncCommit after glTxnsCount");
					if (txnIdCount > 0) {
						Long txnId = Long.valueOf(txnIdNextVal.toString());
						returnList.setOffenderDeductionId(txnId);
						returnList.setSealFlag("E");
					} else {
						if (objOffTransactions.getOverrideAmount() != null) {
							logger.info("OcdpayobServiceImpl offBncCommit before updateOffenderBeneficiariesOverRidden");
							offenderBeneficiariesT2Service.offenderBeneficiariesT2Trigger(objOffTransactions, objOffTransactions.getCreateUserId());
							offenderBeneficiariesT1Service.offenderBeneficiariesT1Trigger(objOffTransactions, oldData);
							liReturn = ocdpayobRepository.updateOffenderBeneficiariesOverRidden(objOffTransactions);
							logger.info("OcdpayobServiceImpl offBncCommit after updateOffenderBeneficiariesOverRidden");
						}
						returnList.setSealFlag("G");
						return returnList;
					}
				}
			}
		}
		if (commitBean.getOffFeeBillupdateList() != null && commitBean.getOffFeeBillupdateList().size() > 0) {
			String feeCode = null;

			Map<String,Double> map = new HashMap<String, Double>();
			Map<String,String> map2 = new HashMap<String, String>();
			for(int i = 0; i < commitBean.getOffFeeBillupdateList().size(); i++) {
				feeCode = commitBean.getOffFeeBillupdateList().get(i).getFeeCode();
				if(map != null && map.containsKey(feeCode)) {
					Double amount = commitBean.getOffFeeBillupdateList().get(i).getAmount();
					Double amount1 = map.get(feeCode);
					map2.put(feeCode, commitBean.getOffFeeBillupdateList().get(i).getCaseloadId());
					map.put(feeCode, amount1+amount);
				}else {
					map.put(feeCode, commitBean.getOffFeeBillupdateList().get(i).getAmount());
					map2.put(feeCode, commitBean.getOffFeeBillupdateList().get(i).getCaseloadId());
				}
			}
			for (Map.Entry<String,Double> entry : map.entrySet()) {
				commitBean.getOffFeeBillupdateList().get(0).setModifyUserId(commitBean.getCreateUserId());
				logger.info("OcdpayobServiceImpl offBncCommit before getMaxTxnEntrySeq");
				Integer txnEntrySeq = ocdpayobRepository.getMaxTxnEntrySeq(commitBean.getOffFeeBillupdateList().get(0).getRootOffenderId().longValue(), txnIdNextVal);
				logger.info("OcdpayobServiceImpl offBncCommit after getMaxTxnEntrySeq");

				logger.info("OcdpayobServiceImpl offBncCommit before getMaxGlEntrySeq");
				Integer glSeq = ocdpayobRepository.getMaxGlEntrySeq(commitBean.getOffFeeBillupdateList().get(0).getRootOffenderId().longValue(), txnIdNextVal);
				logger.info("OcdpayobServiceImpl offBncCommit after getMaxGlEntrySeq");
				Long offenderBookId = commitBean.getOffFeeBillupdateList().get(0).getOffenderBookId();
				Long offenderId = commitBean.getOffFeeBillupdateList().get(0).getOffenderId();
				returnList = updateOffenderFees(txnIdNextVal,txnEntrySeq,glSeq,map2.get(entry.getKey()),entry.getKey(),entry.getValue(),offenderBookId,offenderId,commitBean.getCreateUserId());
				if("H".equals(returnList.getSealFlag())) {
					return returnList;
				}
				for(OffFeeBillTransactions obj : commitBean.getOffFeeBillupdateList()) {
					if(obj.getFeeCode().equals(entry.getKey()) && obj.getAmount()>0) {
						obj.setTrustTxnId(txnIdNextVal);
						obj.setTrustTxnEntrySeq(txnEntrySeq);
						logger.info("OcdpayobServiceImpl offBncCommit before getBillTranId");
						Integer txnNo = ocdpayobRepository.getBillTranId(obj.getBillId());
						logger.info("OcdpayobServiceImpl offBncCommit after getBillTranId");
						obj.setBillTxnNo(txnNo);
						obj.setBalance(obj.getBalance() - obj.getAmount());
						if(obj.getBalance() < 0) {
							throw new Exception("AMT_CANNOT_GREATER_THAN_BALANCE_OWING");
						}
						obj.setBillTxnAmount(new BigDecimal(obj.getBalance()));
						logger.info("OcdpayobServiceImpl offBncCommit before getstaffId");
						obj.setBillTxnStaffId(ocdpayobRepository.getstaffId(obj.getBillTxnUser()));
						logger.info("OcdpayobServiceImpl offBncCommit after getstaffId");

						obj = billAgingARStatusProcess(obj);
						logger.info("OcdpayobServiceImpl offBncCommit before insertOffenderFees");
						ocdpayobRepository.insertOffenderFees(obj);
						offFeeBillTransactionsT1Service.offFeeBillTransactionsT1(obj);
						offFeeBillTransactionsT2Service.offFeeBillTransactionsT2(obj);
						logger.info("OcdpayobServiceImpl offBncCommit after insertOffenderFees");
						if(obj.getBillLdppStartDate() != null) {
							updateOffFeeBillsList.add(obj);
						}
						updateOffFeeBillsDate(updateOffFeeBillsList);
					}
				}
			}
			if (offFeeReturn != 0) {
				returnList.setSealFlag("A");
				return returnList;
			}
		}
		return returnList;
	}

	public void updateOffFeeBillsDate(List<OffFeeBillTransactions> updateOffFeeBillsList) {
		if (updateOffFeeBillsList != null && updateOffFeeBillsList.size() > 0) {
			ocdpayobRepository.updateOffFeeBillsDate(updateOffFeeBillsList);
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return ocdpayobRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean commitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<TransactionOperation> cgfkOffTxnSubAccountTypeRecordGroup(final String caseloadId) {
		return ocdpayobRepository.cgfkOffTxnSubAccountTypeRecordGroup(caseloadId);

	}

	/**
	 * This method is used to execute a record group
	 */
	public BigDecimal getCurrentBalance(final OffenderTransactions searchBean) {
		return ocdpayobRepository.getCurrentBalance(searchBean);
	}

	public String txnTyepOffTxns(final String subAccountType, final String caseloadType, final String caseloadId) {
		return ocdpayobRepository.txnTyepOffTxns(subAccountType, caseloadType, caseloadId);
	}

	public Date trunc(final Date date) {
		if (date != null) {
			final Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			calender.set(Calendar.HOUR, 0);
			calender.set(Calendar.MINUTE, 0);
			calender.set(Calendar.SECOND, 0);
			calender.set(Calendar.MILLISECOND, 0);
			final Date returnDate = calender.getTime();
			return returnDate;
		}
		return null;
	}
	@Override
	public String getOffenderFeesEnableBtn() {
		return ocdpayobRepository.getOffenderFeesEnableBtn();
	}
	@Override
	public List<OffFeeBillTransactions> offFeeExecuteQuery(OffFeeBillTransactions serachBean) {
		return ocdpayobRepository.offFeeExecuteQuery(serachBean);
	}
	@Transactional(rollbackFor = { Exception.class })
	@Override
	public Integer offFeesCommit(OffFeeBillTransactionsCommitBean commitBean) {
		int lireturn = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffFeeBillTransactions offFeesTrns : commitBean.getUpdateList()) {
				offFeesTrns.setBillTxnAmount(new BigDecimal(offFeesTrns.getBalance()));
				Integer txnNo = ocdpayobRepository.getBillTranId(offFeesTrns.getBillId());
				offFeesTrns.setBillTxnNo(txnNo);
			}
		}
		return lireturn;
	}

	@Override
	public OffenderBeneficiaries updateOffenderFees(Integer trustTxnId,Integer txnEntrySeq,Integer glSeq,String caseLoad,String FeeeCode,Double amount,
			Long offenderBookId,Long offenderId, String userName) throws Exception {
		logger.info("OcdpayobServiceImpl class starting updateOffenderFees method");
		OffenderBeneficiaries returnList = new OffenderBeneficiaries();
		BigDecimal crDeductAcntCode = ocdpayobRepository.getCrDeductAcntCode(FeeeCode,caseLoad);
		logger.info("OcdpayobServiceImpl class updateOffenderFees -> getCrDeductAcntCode response = " + crDeductAcntCode);
		Integer drAccountCode = 21010; // ocdpayobRepository.getDrAccountCode("PAYOIB", offFeeBillTxn.getCaseloadId());
		OffenderTransactions offTxnObj = new OffenderTransactions();
		if (crDeductAcntCode != null && drAccountCode != null) {
			offTxnObj.setTxnId(trustTxnId);
			offTxnObj.setTxnEntrySeq(txnEntrySeq);
			offTxnObj.setOffenderBookId(offenderBookId);
			offTxnObj.setOffenderId(offenderId);
			offTxnObj.setTxnEntryDesc(ocdpayobRepository.getSubAccountTypeDesc("DEDC"));
			offTxnObj.setTxnType("DEDC");
			offTxnObj.setDeductionFlag(null);
			offTxnObj.setSubAccountType("REG");
			offTxnObj.setTxnPostingType("DR");
			offTxnObj.setAccountCode(new BigDecimal(drAccountCode));
			offTxnObj.setCaseloadId(caseLoad);
			final Date transDate = trunc(eliteDateService.getDBTime());
			offTxnObj.setTxnEntryDate(transDate);
			offTxnObj.setModifyDate(transDate);
			offTxnObj.setTxnEntryAmount(amount);
			offTxnObj.setSlipPrintedFlag("N");
			offTxnObj.setReceiptPendingPrintFlag("Y");
			offTxnObj.setRootOffenderId(offTxnObj.getOffenderId());
			glSeq = glSeq + 1 ;
			offTxnObj.setGlEntrySeq(Long.valueOf(glSeq));
			logger.info("OcdpayobServiceImpl class updateOffenderFees -> before first trustInsertGltransNew ");
			GlTransactions gl=new GlTransactions();
			BeanUtils.copyProperties(offTxnObj, gl);
			gl.setTxnId(trustTxnId.longValue());
			gl.setTxnEntrySeq(txnEntrySeq.longValue());
			gl.setOffenderBookId(new BigDecimal(offenderBookId));
			gl.setOffenderId(new BigDecimal(offenderId));
			gl.setTxnEntryAmount(new BigDecimal(amount));
			gl.setTxnEntryDesc(ocdpayobRepository.getSubAccountTypeDesc("DEDC"));
			gl.setGlEntrySeq(Long.valueOf(glSeq));
			gl.setTxnPostUsage("DR");
			gl.setCreateUserId(userName);
			try {
				trustService.insertGlTransNew(gl);
			}catch (Exception e) {
					logger.error("error in updateOffenderFees method  trustInsertGltransNew genSequenceOne");
					returnList.setSealFlag("H");
					return returnList;
			}
			glSeq = glSeq + 1 ;
			offTxnObj.setGlEntrySeq(Long.valueOf(glSeq));
			offTxnObj.setTxnPostingType("CR");
			offTxnObj.setAccountCode(crDeductAcntCode);
			gl.setGlEntrySeq(Long.valueOf(glSeq));
			gl.setTxnPostUsage("CR");
			gl.setAccountCode(crDeductAcntCode);
			try {
			trustService.insertGlTransNew(gl);
			}catch(Exception e) {

				logger.error("error in updateOffenderFees method  trustInsertGltransNew genSequenceTwo");
				returnList.setSealFlag("H");
				return returnList;
			}
			offTxnObj.setTxnPostingType("DR");
			offTxnObj.setCreateUserId(userName);
			logger.info("OcdpayobServiceImpl class updateOffenderFees -> before insertIntoOffenderTransaction");
			Integer retVal = ocdpayobRepository.insertIntoOffenderTransaction(offTxnObj);
			logger.info("OcdpayobServiceImpl class updateOffenderFees -> before updateOffenderBalance");
			if(retVal != 0) {
				trustService.updateOffenderBalance(offTxnObj, offTxnObj.getCreateUserId());
				returnList.setSealFlag("A");
				return returnList;
			}
		} else {
			logger.error("Exception in OcdpayobServiceImpl class updateOffenderFees crDeductAcntCode and drAccountCode not found");
			throw new Exception("CR_DEDUCT_TO NOT FOUND");
		}
		return returnList;
	}

	public OffFeeBillTransactions billAgingARStatusProcess(OffFeeBillTransactions offFeeBillTxn) {
		if ("AR".equalsIgnoreCase(offFeeBillTxn.getBillStatus())) {
			if (offFeeBillTxn.getBillTxnAmount() != null && offFeeBillTxn.getBillTxnAmount().equals(BigDecimal.ZERO)) {
				offFeeBillTxn.setBillStatus("PAID");
			}
		} else {
			offFeeBillTxn = billAgingLDPPProcess(offFeeBillTxn);
		}
		return offFeeBillTxn;
	}

	public OffFeeBillTransactions billAgingLDPPProcess(OffFeeBillTransactions offFeeBillTxn) {
		if ("LD_PP".equalsIgnoreCase(offFeeBillTxn.getBillStatus())) {
			if (offFeeBillTxn.getBillTxnAmount() != null && offFeeBillTxn.getBillTxnAmount().equals(BigDecimal.ZERO)) {
				offFeeBillTxn.setBillStatus("PAID");
			} else {
				offFeeBillTxn.setBillLdppStartDate(eliteDateService.getDBTime());
			}
		} else {
			offFeeBillTxn = billAgingLDEXProcess(offFeeBillTxn);
		}
		return offFeeBillTxn;
	}

	public OffFeeBillTransactions billAgingLDEXProcess(OffFeeBillTransactions offFeeBillTxn) {
		if ("LD_EXI".equalsIgnoreCase(offFeeBillTxn.getBillStatus())) {
			if (offFeeBillTxn.getBillTxnAmount() != null && offFeeBillTxn.getBillTxnAmount().equals(BigDecimal.ZERO)) {
				offFeeBillTxn.setBillStatus("PAID");
			} else {
				offFeeBillTxn.setBillStatus("LD_PP");
				offFeeBillTxn.setBillLdppStartDate(eliteDateService.getDBTime());
			}
		}
		return offFeeBillTxn;
	}
}