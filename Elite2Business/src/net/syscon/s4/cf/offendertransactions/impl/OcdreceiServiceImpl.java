package net.syscon.s4.cf.offendertransactions.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.syscon.s4.cf.deductions.beans.FeeAccountBalanceBean;
import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cf.deductions.beans.OcrorrecReportsBean;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.cf.maintenance.beans.FeeAccounts;
import net.syscon.s4.cf.offendertransactions.OcdadjusService;
import net.syscon.s4.cf.offendertransactions.OcdbreciRepository;
//import net.syscon.s4.cf.offendertransactions.OcdadjusRepository;
import net.syscon.s4.cf.offendertransactions.OcdreceiRepository;
import net.syscon.s4.cf.offendertransactions.OcdreceiService;
import net.syscon.s4.cf.offendertransactions.beans.PrintReceiptsTmp;
import net.syscon.s4.cf.statements.beans.ocdbreciReportsBean;
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.OtddisbuProcessTransactionsBean;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReportBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalconfiguration.OumaglocRepository;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.im.beans.VAgencyAddresses;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inst.casemanagement.OcdiplanRepository;
import net.syscon.s4.inst.casemanagement.beans.StaffMembersV2;
import net.syscon.s4.inst.demographicsbiometrics.OcdaddreRepository;
import net.syscon.s4.iwp.OcdexpowRepository;
import net.syscon.s4.pkgs.deductions.DeductionsRepository;
import net.syscon.s4.pkgs.deductions.DeductionsService;
import net.syscon.s4.pkgs.financial.FinancialService;
import net.syscon.s4.pkgs.trust.TrustService;
import net.syscon.s4.triggers.OffFeeAccountProfileT2Service;
import net.syscon.s4.triggers.OffFeeBillTransactionsT1Service;
import net.syscon.s4.triggers.OffFeeBillTransactionsT2Service;
import net.syscon.s4.triggers.OffFeeBillsT2Service;
import net.syscon.s4.triggers.PrintReceiptsTmpTjnService;

/**
 * Class OcdreceiServiceImpl
 */
@Service
public class OcdreceiServiceImpl extends BaseBusiness implements OcdreceiService {

	private static Logger logger = LogManager.getLogger(OcdreceiServiceImpl.class.getName());

	@Autowired
	private OcdreceiRepository ocdreceiRepository;
	
	@Autowired
	private OcdbreciRepository ocdbreciRepository;

	@Autowired
	private EliteDateService eliteDateService;

	@Autowired
	private OumaglocRepository oumaglocRepo;

	@Autowired
	private OcdaddreRepository ocdaddreDao;

	@Autowired
	private OcdiplanRepository ocdiplanRepository;

	@Autowired
	private OcdexpowRepository ocdexpowRepository;

	@Autowired
	private TrustService trustService;

	@Autowired
	private FinancialService financialService;

	@Autowired
	private OffFeeBillTransactionsT1Service offFeeBillTransactionsT1Service;

	@Autowired
	private OffFeeBillTransactionsT2Service offFeeBillTransactionsT2Service;

	@Autowired
	private PrintReceiptsTmpTjnService printReceiptsTmpTjnService;

	@Autowired
	private DeductionsService deductionsService;
	
	@Autowired
	private OffFeeBillsT2Service offFeeBillsT2Service;
	
	@Autowired
	private OffFeeAccountProfileT2Service offFeeAccountProfileT2Service;

	// @Autowired
	// private OcdadjusRepository ocdbadjusRepository;
	
	@Autowired
	private OcdadjusService ocdadjusService;

	/**
	 * 
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderTransactions> offTxnExecuteQuery(final OffenderTransactions searchRecord) {
		return ocdreceiRepository.offTxnExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_TXN
	 *
	 */
	@Transactional
	public OffenderTransactions offTxnCommit(final OffenderTransactions offenderTransactions) {
		preCommit(offenderTransactions);
		logger.info("preCommit response" + offenderTransactions);
		if (offenderTransactions.getSealFlag() != null) {
			return offenderTransactions;
		}
		keyCommitOne(offenderTransactions);
		logger.info("keyCommitOne response" + offenderTransactions);
		if (offenderTransactions.getSealFlag() != null) {
			return offenderTransactions;
		}
		return offenderTransactions;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<TransactionTypes> cgfkOffTxnTxnTypeRecordGroup(String user) {
		List<TransactionTypes> returnobj = new ArrayList<TransactionTypes>();
		try {
			returnobj = ocdreceiRepository.cgfkOffTxnTxnTypeRecordGroup(user);

		} catch (Exception e) {
			logger.info("cgfkOffTxnTxnTypeRecordGroup response" + e);
		}
		return returnobj;

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> cgfkOffTxnDspInformationNRecordGroup(Long offenderBookId) {
		return ocdreceiRepository.cgfkOffTxnDspInformationNRecordGroup(offenderBookId);
	}

	@Override
	public OffenderTransactions whenValidateItem(final OffenderTransactions searchBean) {
		bouncedCheckAlert(searchBean);
		try {
			final OffenderTransactions offenderTransactions = ocdreceiRepository.getProdFlagDetails(searchBean);
			logger.info("getProdFlagDetails response" + offenderTransactions);
			if (offenderTransactions != null) {
				searchBean.setReceiptProductionFlag(offenderTransactions.getReceiptProductionFlag());
				searchBean.setCheckInd(offenderTransactions.getCheckInd());
			} else {
				searchBean.setSealFlag("1");
				return searchBean;
				// This Transaction Type is not defined in Transaction Operations table or it is
				// not a Receipt transaction type.
			}
			Long pShadowId = (long) 0;
			final String deductionFlag = deductionsService.chkOffenderDeductions(searchBean.getCaseloadId(),
					searchBean.getRootOffenderId(), searchBean.getTxnType(), pShadowId);
			if (deductionFlag != null) {
				searchBean.setDeductionFlag(deductionFlag);
			} else {
				searchBean.setSealFlag("2");
				return searchBean;
				// Other Error in DEDUCTIONS.CHK_OFFENDER_DEDUCTIONS:
			}
			final Long offenderDeductionId = ocdreceiRepository.getOffenderDeductionId(searchBean.getRootOffenderId());
			logger.info("getOffenderDeductionId response" + offenderDeductionId);
			if (offenderDeductionId != 0) {
				final String vAssociatedFlag = ocdreceiRepository.checkOffenderDeductionReceipts(offenderDeductionId,
						searchBean.getTxnType());
				logger.info("checkOffenderDeductionReceipts response" + vAssociatedFlag);
				if (vAssociatedFlag != null && vAssociatedFlag.equals("Y")) {
					searchBean.setSealFlag("3");
					// One or more deduction(s) of this offender''s receipt is suspended.
				} else {
					searchBean.setSealFlag("4");
					return searchBean;
					// Other error in checking receipt types:
				}
			}
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " whenValidateItem error :: ", e);
		}

		return searchBean;
	}

	private OffenderTransactions bouncedCheckAlert(OffenderTransactions searchBean) {
		final Long countValue = ocdreceiRepository.countOffenderIdDet(searchBean.getRootOffenderId());
		logger.info("countOffenderIdDet response" + countValue);
		final String profileValue = ocdreceiRepository.getProfileValue();
		logger.info("getProfileValue response" + profileValue);
		final Long countRefCodes = ocdreceiRepository.getReferenceCodesValid(searchBean.getTxnType());
		logger.info("getReferenceCodesValid response" + countRefCodes);
		if (countValue > 0) {
			searchBean.setChequeProductionFlag("Y");
			if (profileValue.equals("Y") && countRefCodes > 0) {
				searchBean.setSealFlag("4");
				// This offender has previously bounced check(s).
			}
		} else {
			searchBean.setChequeProductionFlag("N");
		}
		return searchBean;
	}

	OffenderTransactions insertToTmpTable(final OffenderTransactions offenderTransactions) throws Exception {
		try {
			final long pSessionId = ocdreceiRepository.getSessionId();
			ocdreceiRepository.deletePrintReceiptsTmp(pSessionId, offenderTransactions.getCreateUserId());
			ocdreceiRepository.insertPrintReceiptsTmp(offenderTransactions.getTxnId(),
					offenderTransactions.getRootOffenderId(), offenderTransactions.getReceiptNumber(), pSessionId,
					offenderTransactions.getCreateUserId());
		} catch (final Exception e) {
			// Error: Cannot insert record in PRINT_RECEIPTS_TMP table
			logger.error(e.getMessage());
			offenderTransactions.setSealFlag("4");
			throw new Exception("PRINT_RECEIPTS_TMP");
			// return offenderTransactions;
		}
		return offenderTransactions;

	}

	OffenderTransactions preInsert(final OffenderTransactions offenderTransactions) throws Exception {
		String postingType = null;
		String subActType = null;
		Integer seqNo = 0;
		try {
			/*
			 * final String accountStatus =
			 * ocdreceiRepository.chkAccountStatus(offenderTransactions.getCaseloadId(),
			 * offenderTransactions.getRootOffenderId());
			 * logger.info("OcdreceiServiceImpl class preInsert  accountStatus = " +
			 * accountStatus);
			 */
			// procedure call

			trustService.chkAccountStatus(offenderTransactions.getCaseloadType(),
					offenderTransactions.getOffenderId() != null
							? BigDecimal.valueOf(offenderTransactions.getOffenderId())
							: null);

			/*
			 * if ("Y".equals(accountStatus)) { offenderTransactions.setSealFlag("2"); //
			 * Offender has closed Trust Account return offenderTransactions; } else if
			 * ("X".equals(accountStatus)) { offenderTransactions.setSealFlag("3"); //
			 * Offender has no active trust account return offenderTransactions; } else if
			 * ("M".equals(accountStatus)) { offenderTransactions.setSealFlag("4"); //
			 * Offender has multiple accounts at Caseload: '||csld_id return
			 * offenderTransactions; }
			 */
		} catch (final Exception e) {
			logger.error("Exception in OcdreceiServiceImpl class preInsert checking  accountStatus", e.getMessage());
			offenderTransactions.setSealFlag("5");
			throw new Exception("TRUST.CHK_ACCOUNT_STATUS");
			// Other Error in TRUST.CHK_ACCOUNT_STATUS:
			// return offenderTransactions;
		}
		seqNo = seqNo + 1;
		offenderTransactions.setTxnEntrySeq(seqNo);
		Map<String, Object> getSubActTypeMap = null;
		try {
			// getSubActTypeMap = ocdreceiRepository.getSubActType("OCDRECEI",
			// offenderTransactions.getTxnType(),
			// offenderTransactions.getCaseloadId());
			OtddisbuProcessTransactionsBean newOtddisbuProcessTransactionsBean = new OtddisbuProcessTransactionsBean();
//			BeanUtils.copyProperties(offenderTransactions, newOtddisbuProcessTransactionsBean);
			newOtddisbuProcessTransactionsBean.setpCaseloadId(offenderTransactions.getCaseloadId());
			newOtddisbuProcessTransactionsBean.setpModuleName("OCDRECEI");
			newOtddisbuProcessTransactionsBean.setpTxnType(offenderTransactions.getTxnType());
			newOtddisbuProcessTransactionsBean.setCaseLoadType(offenderTransactions.getCaseloadType());
			getSubActTypeMap = trustService.getSubActType(newOtddisbuProcessTransactionsBean);
			logger.info("getSubActType response");

		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " preInsert error :: ", e);
			offenderTransactions.setSealFlag("6");
			throw new Exception("TRUST.GET_SUB_ACT_TYPE");
			// Other Error in TRUST.GET_SUB_ACT_TYPE:
			// return offenderTransactions;
		}

		if (getSubActTypeMap != null) {
			if (getSubActTypeMap.get("P_TXN_POST_TYPE") != null) {
				postingType = getSubActTypeMap.get("P_TXN_POST_TYPE").toString();
				offenderTransactions.setTxnPostingType(postingType);
			}
			if (getSubActTypeMap.get("P_SUB_ACT_TYPE") != null && offenderTransactions.getSubAccountType() == null) {
				subActType = getSubActTypeMap.get("P_SUB_ACT_TYPE").toString();
				offenderTransactions.setSubAccountType(subActType);
			}
		}
		final Date transDate = trunc(eliteDateService.getDBTime());
		offenderTransactions.setTxnEntryDate(transDate);
		offenderTransactions.setModifyDate(transDate);
		offenderTransactions.setPreWithholdAmount(offenderTransactions.getTxnEntryAmount());
		offenderTransactions.setSlipPrintedFlag("N");
		offenderTransactions.setReceiptPendingPrintFlag("Y");
		if (offenderTransactions.getReceiptProductionFlag() == null
				|| offenderTransactions.getReceiptProductionFlag().equals("")) {
			final OffenderTransactions getProdFlagDetails = ocdreceiRepository.getProdFlagDetails(offenderTransactions);
			logger.info("getProdFlagDetails response" + getProdFlagDetails);
			if (getProdFlagDetails != null) {
				offenderTransactions.setReceiptProductionFlag(getProdFlagDetails.getReceiptProductionFlag());
				offenderTransactions.setCheckInd(getProdFlagDetails.getCheckInd());
			} else {
				offenderTransactions.setSealFlag("7");
				logger.error("Exception in OcdreceiServiceImpl class preInsert  getProdFlagDetails");
				throw new Exception("GET_PROD_FLAG_DETAILS");
				// This Transaction Type is not defined in Transaction Operations table or it is
				// not a Receipt transaction type
				// return offenderTransactions;
			}
		}

		if (offenderTransactions.getReceiptProductionFlag() != null
				&& offenderTransactions.getReceiptProductionFlag().equals("Y")) {
			final Integer receiptNum = ocdreceiRepository
					.genTrustRcptNmbr("SEQUENCE_" + offenderTransactions.getCaseloadId());
			logger.info("OcdreceiServiceImpl class preInsert  genTrustRcptNmbr = " + receiptNum);
			if (receiptNum != null) {
				String num = receiptNum.toString();
				while (num.length() < 6) {
					num = "0" + num;
				}
				offenderTransactions.setReceiptNumber(offenderTransactions.getCaseloadId() + num);
			}
		}
		return offenderTransactions;
	}

	private List<String> chkMissingPayPlan(final Long offenderId, final String infoNumber) {
		return ocdreceiRepository.chkMissingPayPlan(offenderId, infoNumber);
	}

	private OffenderTransactions keyCommitOne(final OffenderTransactions offenderTransactions) {
		if (offenderTransactions.getTxnEntryDesc() == null) {
			offenderTransactions.setTxnEntryDesc("ERROR");
			if (offenderTransactions.getTxnType().equals("O_CD") && offenderTransactions.getTxnEntryAmount() > 0
					&& (offenderTransactions.getTxnEntryDesc().equals("ERROR")
							|| offenderTransactions.getTxnReferenceNumber() == null)) {
				offenderTransactions.setSealFlag("2");// Error : Card Not Swiped Properly. Can Not Save ...
				return offenderTransactions;
			} else {
				offenderTransactions.setTxnEntryDesc(null);
			}
		}
		validateBeforeCommit(offenderTransactions);
		logger.info("after validateBeforeCommit method call");
		if (offenderTransactions.getSealFlag() != null) {
			return offenderTransactions;
		}
		return offenderTransactions;
	}

	@Transactional
	public OffenderTransactions keyCommitTwo(final OffenderTransactions offenderTransactions) throws Exception {
		offenderTransactions.setTxnId(ocdreceiRepository.txnIdNextValData());
		logger.info("setTxnId response");

		logger.info("OcdreceiServiceImp KeyCommitTwo preInsert success");
		final String systemProfile = ocdreceiRepository.getSystemProfile();
		logger.info("getSystemProfile response" + systemProfile);
		if (systemProfile != null && systemProfile.equals("Y")
				&& offenderTransactions.getReceiptProductionFlag() != null
				&& offenderTransactions.getReceiptProductionFlag().equals("Y")) {
			insertToTmpTable(offenderTransactions);
		}
		if (offenderTransactions.getSealFlag() != null) {
			return offenderTransactions;
		}
		preInsert(offenderTransactions);
		if (offenderTransactions.getSealFlag() != null) {
			return offenderTransactions;
		}
		postInsert(offenderTransactions);
		logger.info("OcdreceiServiceImp KeyCommitTwo postInsert success");
		return offenderTransactions;
	}

	private OffenderTransactions validateBeforeCommit(final OffenderTransactions offenderTransactions) {
		final String lvPaymentPlanFlag = ocdreceiRepository.getProfileValuePaymentPlan();
		logger.info("getProfileValuePaymentPlan response" + lvPaymentPlanFlag);
		if (lvPaymentPlanFlag != null && lvPaymentPlanFlag.equals("Y")) {
			final List<String> chkMissingPayPlan = chkMissingPayPlan(offenderTransactions.getRootOffenderId(),
					offenderTransactions.getInfoNumber());
			if (chkMissingPayPlan != null && chkMissingPayPlan.size() > 0) {
				offenderTransactions.setSealFlag("3");// Missing Payment Plan. Do you wish to continue?
			}
		}
		return offenderTransactions;
	}

	public OffenderTransactions postInsert(final OffenderTransactions offenderTransactions) throws Exception {
		offenderTransactions.setDeductionFlag("N");
		Integer offTxnInsert = ocdreceiRepository.insertIntoOffenderTransaction(offenderTransactions);
		if (offTxnInsert == 1) {
			logger.info("OcdreceiServiceImpl in postInsert after insertIntoOffenderTransaction success");
		} else {
			logger.error("OcdreceiServiceImpl in postInsert after insertIntoOffenderTransaction failed");
		}
		offenderTransactions.setFmSubAccountType(null);
		offenderTransactions.setModuleName("OCDRECEI");
		offenderTransactions.setGlEntrySeq(Long.valueOf(0));
		// ocdreceiRepository.processGlTransNew(offenderTransactions);
		try {
			trustService.processGlTransNew(offenderTransactions.getCaseloadId(), offenderTransactions.getTxnType(),
					null, offenderTransactions.getTxnEntryAmount(), offenderTransactions.getTxnId(),
					offenderTransactions.getTxnEntryDate(), offenderTransactions.getTxnEntryDesc(),
					offenderTransactions.getTxnEntrySeq(), offenderTransactions.getModuleName(),
					offenderTransactions.getRootOffenderId().intValue(),
					(Object) offenderTransactions.getOffenderBookId(),
					(Object) offenderTransactions.getFmSubAccountType(), offenderTransactions.getSubAccountType(), null,
					null, (Object) "", offenderTransactions.getGlEntrySeq().intValue(), null,
					offenderTransactions.getCreateUserId());
		} catch (final Exception e) {
			offenderTransactions.setSealFlag("8");
			logger.error("Error in Class " + this.getClass().getName() + " processGlTransNew error :: ", e);
			throw new Exception("PROCESS_GL_TRANS_NEW");
			// Other error in PROCESS_GL_TRANS_NEW:' || SQLERRM
		}
		logger.info("OcdreceiServiceImpl in postInsert after processGlTransNew");
		if (offenderTransactions.getSealFlag() != null) {
			return offenderTransactions;
		}
		// ocdreceiRepository.updateOffenderBalance(offenderTransactions);
		try {
			OffenderTransactions offenderTransactionsTemp = offenderTransactions;
			offenderTransactionsTemp.setToSubAccountType(offenderTransactions.getSubAccountType());
			trustService.updateOffenderBalance(offenderTransactionsTemp, offenderTransactions.getCreateUserId());
		} catch (final Exception e) {
			offenderTransactions.setSealFlag("9");
			logger.error("Error in Class " + this.getClass().getName() + " updateOffenderBalance error :: ", e);
			// Other Error in TRUST: ' || SQLERRM
			throw new Exception("UPDATE_OFFENDER_BALANCE");
		}

		logger.info("updateOffenderBalance response");
		if (offenderTransactions.getSealFlag() != null) {
			return offenderTransactions;
		}
		// final Integer financialDoDuctionsFinancial = ocdreceiRepository
		// .financialDoDuctionsFinancial(offenderTransactions, "Y");
		Map<String, Object> financialDoDuctionsFinancial = new HashMap<String, Object>();
		financialDoDuctionsFinancial = financialService.doDeductionsFinancial(offenderTransactions.getCaseloadId(),
				offenderTransactions.getRootOffenderId(), offenderTransactions.getOffenderBookId(),
				offenderTransactions.getTxnType(), offenderTransactions.getTxnId().longValue(),
				offenderTransactions.getTxnEntryDate(), offenderTransactions.getSubAccountType(), "Y",
				new BigDecimal(offenderTransactions.getTxnEntryAmount()), null,
				offenderTransactions.getTxnReferenceNumber() != null
						? Long.parseLong(offenderTransactions.getTxnReferenceNumber())
						: null,
				offenderTransactions.getTxnEntrySeq(), offenderTransactions.getCreateUserId());
		logger.info("doDeductionsFinancial response" + financialDoDuctionsFinancial);
		if (financialDoDuctionsFinancial != null) {
			offenderTransactions.setSealFlag("10");
			logger.error("Exception in OcdreceiServiceImpl in postInsert financialDoDuctionsFinancial");
			throw new Exception("DO_DEDUCTIONS_FINANCIAL");
			// Other error in Financial.do_deductions_financial: ' || SQLERRM
		}
		return offenderTransactions;
	}

	public Date trunc(final Date date) {
		if (date != null) {
			final Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			calender.set(Calendar.HOUR, 0);
			calender.set(Calendar.MINUTE, 0);
			calender.set(Calendar.SECOND, 0);
			calender.set(Calendar.MILLISECOND, 0);
			return calender.getTime();
		}
		return null;
	}

	@Override
	public OffenderTransactions preCommit(final OffenderTransactions offenderTransactions) {
		final String accountClosedFlag = ocdreceiRepository.checkAccountClosedFlag(offenderTransactions);
		logger.info("checkAccountClosedFlag response" + accountClosedFlag);
		if (accountClosedFlag == null) {
			offenderTransactions.setSealFlag("1");
			// Error in Pre-Commit when checking offender trust account...
			return offenderTransactions;
		}
		return offenderTransactions;
	}

	@Override
	public OffenderTransactions whenNewBlockInstance(final OffenderTransactions offenderTransactions) {
		// final String accountStatus =
		// ocdreceiRepository.chkAccountStatus(offenderTransactions.getCaseloadId(),
		// offenderTransactions.getRootOffenderId());
		Map<String, Object> accountStatus = new HashMap<String, Object>();
		accountStatus = trustService.chkAccountStatus(offenderTransactions.getCaseloadId(),
				new BigDecimal(offenderTransactions.getRootOffenderId()));
		logger.info("chkAccountStatus response" + accountStatus);
		if (accountStatus.equals("ERROR")) {
			offenderTransactions.setSealFlag("11");
			// OTHER ERROR IN CHK_ACCOUNT_STATUS:
			return offenderTransactions;
		} else if (accountStatus.equals("Y")) {
			ocdreceiRepository.updateOfndrTrustAcc(offenderTransactions.getRootOffenderId(),
					offenderTransactions.getCaseloadId());
			logger.info("updateOfndrTrustAcc response");
		}
		final String oblFlag = ocdreceiRepository.getUniqueObligationFlag(offenderTransactions.getRootOffenderId());
		logger.info("getUniqueObligationFlag response" + oblFlag);
		if (oblFlag != null && oblFlag.equals("Y")) {
			offenderTransactions.setSealFlag("11");
			// This offender has a unique obligation profile. Please go to the Override
			// Obligations window.
			return offenderTransactions;
		}
		return offenderTransactions;
	}

	@Override
	public Long validateDspInfoNumber(final OffenderTransactions searchBean) throws Exception {
		return ocdreceiRepository.validateDspInfoNumber(searchBean);
	}

	@Transactional
	public List<OffenderTransactions> printReport(final OffenderTransactions offTransactions) {
		List<OffenderTransactions> runreport;
		final OffenderTransactions retrunDataReq = new OffenderTransactions();
		offTransactions.setSessionId(ocdreceiRepository.getSessionId());
		retrunDataReq.setCreateUserId(offTransactions.getCreateUserId());
		insertToTmpTableOne(offTransactions);
		retrunDataReq.setCaseloadId(offTransactions.getCaseloadId());
		retrunDataReq.setSessionId(offTransactions.getSessionId());
		retrunDataReq.setModuleName(offTransactions.getModuleName());
		runreport = runreport(retrunDataReq);
		return runreport;
	}

	public byte[] ocrdreceRunReportQuery(final OffenderTransactions bean) {
		byte[] returnReport = null;
		final ocdbreciReportsBean reportBean = new ocdbreciReportsBean();
		final ReportBean rBean = new ReportBean();
		List<ocdbreciReportsBean> fields;
		final SystemProfiles sysPflBean = ocdreceiRepository.getFclientValue();
		logger.info("getFclientValue response" + sysPflBean);
		if (sysPflBean.getDescription() != null) {
			reportBean.setfClient(sysPflBean.getDescription());
		}
		final String fCaseloadDesc = ocdreceiRepository.getfcaseloadDesc(bean.getCaseloadId());
		logger.info("getfcaseloadDesc response" + fCaseloadDesc);
		if (fCaseloadDesc != null) {
			reportBean.setfClDesc(fCaseloadDesc);
		}
		fields = ocdreceiRepository.getReportData(bean);
		logger.info("getReportData response" + fields);
		if (!fields.isEmpty()) {
			fields.forEach(element -> {
				if (sysPflBean.getDescription() != null) {
					element.setfClient(sysPflBean.getDescription().toUpperCase());
				}
				if (fCaseloadDesc != null) {
					element.setfClDesc(fCaseloadDesc);
				}
				if (element.getOffenderId() != null) {
					final String offenderId = ocdreceiRepository.getOffenderIdData(bean.getCaseloadId(),
							element.getOffenderId(), bean.getCreateUserId());
					logger.info("getOffenderIdData response" + offenderId);
					if (offenderId != null) {
						element.setfOffId(offenderId);
					}
					final VHeaderBlock data = ocdreceiRepository.getOffenderNameData(bean.getCaseloadId(),
							element.getOffenderId(), bean.getCreateUserId());
					logger.info("getOffenderNameData response" + data);
					if (data != null) {
						if (data.getFirstName() != null) {
							element.setfOffName(data.getLastName() + ", " + data.getFirstName());
						}
						if (data.getFirstName() != null && data.getMiddleName() != null) {
							element.setfOffName(null);
							element.setfOffName(
									data.getLastName() + ", " + data.getFirstName() + " " + data.getMiddleName());
						}
					}
					final OffenderBeneficiaries amountsData = ocdreceiRepository
							.getTotalAmountandOwingAmount(element.getOffenderId());
					logger.info("getTotalAmountandOwingAmount response" + amountsData);
					if (amountsData != null) {
						element.setfTwo(amountsData.getAmount());
					} else {
						element.setfTwo(BigDecimal.ZERO);
					}
				}
				if (element.getAmt() != null) {
					String amountWords = null;
					String centsWord = null;
					final BigDecimal amount = ocdreceiRepository.getTruncAmount(element.getAmt());
					logger.info("getTruncAmount response" + amount);
					final BigDecimal cents = element.getAmt().subtract(amount);
					final Double amountOne = Double.valueOf(amount.toString());
					final Double centsOne = Double.valueOf(cents.toString());
					if (amountOne > 0) {
						amountWords = ocdreceiRepository.getAmoutninWords(amount);
						logger.info("getAmoutninWords response" + amountWords);
					}
					if (amountOne > 0 && centsOne > 0) {
						amountWords = amountWords + " AND ";
					}
					if (centsOne > 0) {
						centsWord = ocdreceiRepository.getAmoutninWords(cents);
						logger.info("getAmoutninWords response" + centsWord);
						amountWords = amountWords + centsWord + " CENTS";
					}
					amountWords = amountWords + " ONLY";
					element.setfAmtTwo(amountWords);
				}
				final BigDecimal transFees = ocdreceiRepository.getTransfeesAmount(element.getTxnId(),
						element.getTxnSeq(), element.getTxnType());
				logger.info("getTransfeesAmount response" + transFees);
				if (transFees == null) {
					element.setfFour(BigDecimal.ZERO);
				} else {
					element.setfFour(transFees);
				}
				final BigDecimal dedTxnAmount = ocdreceiRepository.getAmountData(element.getTxnId(),
						element.getTxnSeq(), element.getTxnType(), transFees);
				logger.info("getAmountData response" + dedTxnAmount);
				if (dedTxnAmount == null) {
					element.setfThree(BigDecimal.ZERO);
					element.setfFive(BigDecimal.ZERO);
				} else {
					element.setfThree(transFees);
					element.setfFive(transFees);
				}
				final List<OffenderDeductions> deductionsData = ocdreceiRepository
						.getReportDataQuery(element.getOffenderId());
				logger.info("getReportDataQuery response" + deductionsData);
				if (deductionsData.size() > 0) {
					deductionsData.forEach(action -> {
						String dedFlag = "N";
						String existFlag = "N";
						if (action.getDeductionType() != null) {
							dedFlag = ocdreceiRepository.dedFlag(action.getDeductionType());
							logger.info("dedFlag response" + dedFlag);
						}
						if (action.getOffenderDeductionId() != null) {
							existFlag = ocdreceiRepository.existFlag(action.getOffenderDeductionId());
							logger.info("existFlag response" + existFlag);
						}
						if ("Y".equals(dedFlag) && "Y".equals(existFlag)) {
							if (action.getMaxTotalAmount() != null) {
								element.setfOne(null);
								element.setfOne(action.getMaxTotalAmount());
							} else if (action.getMaxMonthlyAmount() != null) {
								element.setfOne(null);
								element.setfOne(action.getMaxMonthlyAmount());
							} else if (action.getMaxRecursiveAmount() != null) {
								element.setfOne(null);
								final BigDecimal monthsBetweenAMount = ocdreceiRepository
										.monthsBetweenAmount(action.getOffenderDeductionId());
								logger.info("monthsBetweenAmount response" + monthsBetweenAMount);
								final BigDecimal amount = action.getMaxRecursiveAmount().multiply(monthsBetweenAMount);
								element.setfOne(amount);
							} else {
								element.setfOne(BigDecimal.ZERO);
							}
						} else {
							element.setfOne(BigDecimal.ZERO);
						}
					});

				} else {
					element.setfOne(BigDecimal.ZERO);
				}
			});
			final Map<String, Object> param = new HashMap<>();
			returnReport = generateReport("OCRRECEI", param, fields);
			rBean.setReport(returnReport);
			return returnReport;
		}
		return returnReport;
	}

	public byte[] generateReport(final String reportName, final Map<String, Object> parameteres, final List<?> fields) {
		byte[] returnReport = null;
		JasperPrint jasperPrint = null;
		try {
			final InputStream reportInStream = this.getClass().getClassLoader()
					.getResourceAsStream("resource/jasperreports/" + reportName + ".jrxml");
			final JasperReport jasperReport = JasperCompileManager.compileReport(reportInStream);
			if ((fields != null && !fields.isEmpty())) {
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameteres,
						new JRBeanCollectionDataSource(fields));
			} else {
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameteres, new JREmptyDataSource());
			}
			returnReport = JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (final Exception e) {

			logger.error("Error in Class " + this.getClass().getName() + " generateReport error :: ", e);
		}
		return returnReport;
	}

	public Integer aftereReport(final String modulename, final Long sessionId, String modifyUserId) {

		final Integer returnCount = 0;
		ocdreceiRepository.updateOffenderTransactionsrpt(modulename, sessionId, modifyUserId);
		ocdreceiRepository.printReceiptsTmpDeletequeryRpt(modulename, sessionId, modifyUserId);
		return returnCount;

	}

	public List<OffenderTransactions> runreport(final OffenderTransactions offenderTransactions) {
		final List<OffenderTransactions> returnData = new ArrayList<>();
		final OffenderTransactions report = new OffenderTransactions();
		byte[] pdfReport = null;
		int liReturn = 0;
		pdfReport = ocrdreceRunReportQuery(offenderTransactions);
		logger.info("ocrdreceRunReportQuery response" + pdfReport);
		liReturn = aftereReport(offenderTransactions.getModuleName(), offenderTransactions.getSessionId(),
				offenderTransactions.getCreateUserId());
		logger.info("aftereReport response" + liReturn);
		if (liReturn == 1) {
			report.setReport(pdfReport);
			report.setSealFlag("1");
			returnData.clear();
			returnData.add(report);
		}
		report.setReport(pdfReport);
		returnData.add(report);
		return returnData;
	}

	public Integer insertToTmpTableOne(final OffenderTransactions offenderTransactions) {
		Integer liReturn = 0;
		ocdreceiRepository.deletePrintReceiptsTmp(offenderTransactions.getSessionId(), offenderTransactions.getCreateUserId());
		ocdreceiRepository.insertPrintReceiptsTmp(offenderTransactions);
		return liReturn;
//		Integer liReturn = 0;
//		
//		PrintReceiptsTmp printReceiptsTmp = new PrintReceiptsTmp();
//		printReceiptsTmp = ocdreceiRepository.getPrintReceiptsBySessionid(offenderTransactions.getSessionId());
//		logger.info("getPrintReceiptsBySessionid response" + printReceiptsTmp);
//		ocdreceiRepository.deletePrintReceiptsTmp(offenderTransactions.getSessionId());
//		logger.info("deletePrintReceiptsTmp response");
//		PrintReceiptsTmp oldRecord = new PrintReceiptsTmp();
//		oldRecord.setModuleName(printReceiptsTmp.getModuleName());
//		oldRecord.setTxnId(printReceiptsTmp.getTxnId());
//		oldRecord.setOffenderId(printReceiptsTmp.getOffenderId());
//		oldRecord.setReceiptNumber(printReceiptsTmp.getReceiptNumber());
//		oldRecord.setSessionId(printReceiptsTmp.getSessionId());
//		oldRecord.setCreateUserId(printReceiptsTmp.getCreateUserId());
//		oldRecord.setModifyUserId(printReceiptsTmp.getModifyUserId());
//		oldRecord.setSealFlag(printReceiptsTmp.getSealFlag());
//		printReceiptsTmpTjnService.printReceiptsTmpTjn(new PrintReceiptsTmp(), oldRecord, "DELETE");
//		logger.info("printReceiptsTmpTjn response");
//		ocdreceiRepository.insertPrintReceiptsTmp(offenderTransactions);
//		logger.info("insertPrintReceiptsTmp response");
//		PrintReceiptsTmp newRecord = new PrintReceiptsTmp();
// 		newRecord.setOffenderId(new BigDecimal(offenderTransactions.getOffenderId()));
//		newRecord.setModuleName(offenderTransactions.getModuleName());
//		newRecord.setTxnId(new BigDecimal(offenderTransactions.getTxnId()));
//		newRecord.setReceiptNumber(offenderTransactions.getReceiptNumber());
//		newRecord.setSessionId(new BigDecimal(offenderTransactions.getSessionId()));
//		newRecord.setSealFlag(offenderTransactions.getSealFlag());
//		newRecord.setCreateUserId(offenderTransactions.getCreateUserId());
//		//Added trigger call of PRINT_RECEIPTS_TMP table
//		printReceiptsTmpTjnService.printReceiptsTmpTjn(newRecord, new PrintReceiptsTmp(), "INSERT");
//		logger.info("printReceiptsTmpTjn response");
//		return liReturn;
	}

	@Override
	public String getSystemProfileValue() {
		return ocdreceiRepository.getSystemProfileValue();
	}

	@Override
	public String getCfPaymentSystemProfileValue() {
		return ocdreceiRepository.getCfPaymentSystemProfileValue();
	}

	@Override
	public List<OffFeeBillTransactions> getOffenederFeeSectionQuery(String offenderIdDisplay, String userName) {
		List<OffFeeBillTransactions> listObj= ocdreceiRepository.getOffenederFeeSectionQuery(offenderIdDisplay, userName);
		return ocdadjusService.postExecuteQuery(listObj);

	}


	@Override
	public Date getLongestSupervisionExpireDate(Long offenderBookId) {
		return ocdreceiRepository.getLongestSupervisionExpireDate(offenderBookId);
	}

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public OffenderTransactions offenderReceiptsCommit(OffenderTransactionsCommitBean commitBean) throws Exception {
		OffenderTransactions offenderTransactions = new OffenderTransactions();
		OffenderTransactions returnObj = new OffenderTransactions();
		// List<OffFeeBillTransactions> updateOffFeeBillsList = new
		// ArrayList<OffFeeBillTransactions>();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OffenderTransactions obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				// obj.setModifyUserId(commitBean.getCreateUserId());
				offenderTransactions = obj;
				keyCommitTwo(offenderTransactions);
				logger.info("after keyCommitTwo");
				returnObj = offenderTransactions;
				// System.out.println(mxnTxnEntrySeq);
			}
		}
		Integer txnEntrySeq = ocdreceiRepository.getMaxTxnEntrySeq(returnObj.getRootOffenderId(), returnObj.getTxnId());
		logger.info("getMaxTxnEntrySeq response" + txnEntrySeq);
		// Integer txnEntrySeq = returnObj.getTxnEntrySeq();
		if (commitBean.getPrepaidAcntInsertList() != null && commitBean.getPrepaidAcntInsertList().size() > 0) {
			for (OffenderTransactions obj : commitBean.getPrepaidAcntInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setModifyUserId(commitBean.getCreateUserId());
				offenderTransactions = new OffenderTransactions();
				offenderTransactions = obj;
				offenderTransactions.setTxnId(returnObj.getTxnId());
				txnEntrySeq = txnEntrySeq + 1;
				offenderTransactions.setTxnEntrySeq(txnEntrySeq);
				offenderTransactions.setTxnReferenceNumber(returnObj.getTxnReferenceNumber());
				prepaidAcntInsert(offenderTransactions);
				logger.info("after prepaidAcntInsert");
				txnEntrySeq = txnEntrySeq + 1;
				if (offenderTransactions.getSealFlag() != null) {
					return offenderTransactions;
				} else {
					logger.info("OcdreceiServiceImpl class offenderReceiptsCommit before updateFeeAccountStatus ");
					BigDecimal currentBalanceOwing = ocdreceiRepository.getOffFeeIdTotalBalanceOwing(
							obj.getOffenderBookId(), obj.getSubAccountType(), obj.getCaseloadId());
					FeeAccountProfiles fap = new FeeAccountProfiles();
					fap.setOffenderBookId(obj.getOffenderBookId());
					fap.setFeeCode(obj.getSubAccountType());
					fap.setCaseloadId(obj.getCaseloadId());
					fap.setCreateUserId(obj.getCreateUserId());
					fap.setOffenderFeeId(null);

					if (currentBalanceOwing == null || currentBalanceOwing == BigDecimal.ZERO) {
						fap.setFeeActStatus("P");
					} else {
						fap.setFeeActStatus("A");
					}
					Integer insertCount = ocdreceiRepository.updateFeeAccountStatus(fap);
					if(insertCount == 1) {
						fap= ocdbreciRepository.getOldDataFeeAccountProfiles(fap);

						offFeeAccountProfileT2Service.offFeeAccountProfileT2(fap);		
					}
					logger.info("OcdreceiServiceImpl class offenderReceiptsCommit after updateFeeAccountStatus "
							+ insertCount);
				}
			}
		}
		if (commitBean.getOffFeeUpdateList() != null && commitBean.getOffFeeUpdateList().size() > 0) {
			Integer glSeq = txnEntrySeq + 1;
			// int index = 0;
			for (OffFeeBillTransactions offFeeBillTxn : commitBean.getOffFeeUpdateList()) {
				offFeeBillTxn.setCreateUserId(commitBean.getCreateUserId());
				offFeeBillTxn.setModifyUserId(commitBean.getCreateUserId());
				txnEntrySeq = txnEntrySeq + 1;
				logger.info("OcdreceiServiceImpl class offenderReceiptsCommit before getBillTranId");
				offFeeBillTxn.setBillTxnNo(ocdreceiRepository.getBillTranId(offFeeBillTxn.getBillId()));
				offFeeBillTxn.setTrustTxnId(returnObj.getTxnId());
				offFeeBillTxn.setOffenderId(returnObj.getOffenderId());
				offFeeBillTxn.setOffenderBookId(returnObj.getOffenderBookId());
				logger.info("OcdreceiServiceImpl class offenderReceiptsCommit before getStaffID");
				offFeeBillTxn.setBillTxnStaffId(ocdreceiRepository.getStaffID(offFeeBillTxn.getCreateUserId()));
				offFeeBillTxn = billAgingARStatusProcess(offFeeBillTxn);
//				Integer stmtId = ocdbadjusRepository.stmtPreInsert();
//				if (commitBean.getStmtInsertList() != null && !commitBean.getStmtInsertList().isEmpty()) {
//					commitBean.getStmtInsertList().get(index).setBillingStatementId(stmtId);
//					commitBean.getStmtInsertList().get(index)
//							.setStatementGenerateStaffId(ocdreceiRepository.getStaffID());
//					Integer insertCount = ocdreceiRepository.offStmtCommit(commitBean.getStmtInsertList().get(index));
//					if (insertCount == 1) {
//						ocdreceiRepository.offFeeBillsUpdate(commitBean.getStmtInsertList().get(index));
//
//					}
//					offFeeBillTxn.setBillingStatementId(stmtId);
//					offFeeBillTxn.setBillAgingStartDate(
//							commitBean.getStmtInsertList().get(index).getBillingCycleStartDate());
//					offFeeBillTxn
//							.setBillAgingEndDate(commitBean.getStmtInsertList().get(index).getBillingCycleEndDate());
//					offFeeBillTxn.setTxnStatementGeneratedFlag("Y");
//				}

//				if ("LD_PP".equalsIgnoreCase(offFeeBillTxn.getBillStatus())) {
//					updateOffFeeBillsList.add(offFeeBillTxn);
//				}
				glSeq = updateOffenderFees(offFeeBillTxn, glSeq, txnEntrySeq, returnObj.getTxnReferenceNumber(),
						returnObj.getInfoNumber(), returnObj.getReceiptNumber(), returnObj.getCaseloadId());
		
				// index += 1;
			}
		}
		// updateOffFeeBillsDate(updateOffFeeBillsList);
		return returnObj;
	}

	public void updateOffFeeBillsDate(List<OffFeeBillTransactions> updateOffFeeBillsList) throws Exception {
		if (updateOffFeeBillsList != null && updateOffFeeBillsList.size() > 0) {
				OffFeeBills oldBeanBills = new OffFeeBills();
				for (OffFeeBillTransactions bean : updateOffFeeBillsList) {
			
			oldBeanBills = ocdreceiRepository.getOldDataOffFeeBills(bean.getBillId());
			offFeeBillsT2Service.offFeeBillsT2(bean, oldBeanBills);
			ocdreceiRepository.updateOffFeeBillsDate(updateOffFeeBillsList);
			
			logger.info("updateOffFeeBillsDate  response");
				}
		}
	}

	@Override
	public Integer updateOffenderFees(OffFeeBillTransactions offFeeBillTxn, Integer glSeq, Integer txnEntrySeq,
			String txnReferenceNumber, String infoNumber, String receiptNumber, String caseLoad) throws Exception {
		logger.info("OcdreceiServiceImpl class starting updateOffenderFees method");
		List<OffFeeBillTransactions> updateOffFeeBillsList = new ArrayList<OffFeeBillTransactions>();
		BigDecimal crDeductAcntCode = ocdreceiRepository.getCrDeductAcntCode(offFeeBillTxn.getOffenderFeeId());
		logger.info(
				"OcdreceiServiceImpl class updateOffenderFees -> getCrDeductAcntCode response = " + crDeductAcntCode);
		Integer drAccountCode = ocdreceiRepository.getDrAccountCode(offFeeBillTxn.getBillTxnType(), caseLoad);
		logger.info("OcdreceiServiceImpl class updateOffenderFees -> getDrAccountCode response = " + drAccountCode);
		OffenderTransactions offTxnObj = new OffenderTransactions();
		if (crDeductAcntCode != null && drAccountCode != null) {
			offTxnObj.setTxnId(offFeeBillTxn.getTrustTxnId());
			offTxnObj.setTxnEntrySeq(txnEntrySeq);
			offTxnObj.setReceiptNumber(receiptNumber);
			offTxnObj.setOffenderId(offFeeBillTxn.getOffenderId());
			offTxnObj.setOffenderBookId(offFeeBillTxn.getOffenderBookId());
			offTxnObj.setTxnEntryDesc(ocdreceiRepository.getSubAccountTypeDesc("DEDC"));
			offTxnObj.setTxnReferenceNumber(txnReferenceNumber);
			offTxnObj.setTxnType("DEDC");
			offTxnObj.setDeductionFlag(null);
			offTxnObj.setSubAccountType("REG");
			offTxnObj.setTxnPostingType("DR");
			offTxnObj.setAccountCode(new BigDecimal(drAccountCode));
			offTxnObj.setCaseloadId(offFeeBillTxn.getCaseloadId());
			final Date transDate = trunc(eliteDateService.getDBTime());
			offTxnObj.setTxnEntryDate(transDate);
			offTxnObj.setModifyDate(transDate);
			offTxnObj.setTxnEntryAmount(offFeeBillTxn.getAmount());
			offTxnObj.setSlipPrintedFlag("N");
			offTxnObj.setReceiptPendingPrintFlag("Y");
			offTxnObj.setRootOffenderId(offTxnObj.getOffenderId());
			offTxnObj.setGlEntrySeq(Long.valueOf(glSeq + 1));
			offTxnObj.setInfoNumber(infoNumber);
			offTxnObj.setCreateUserId(offFeeBillTxn.getCreateUserId());

//			Integer genSequenceOne = ocdreceiRepository.trustInsertGltransNew(offTxnObj);
//			logger.info("trustInsertGltransNew  response" + genSequenceOne);
//			 if (genSequenceOne == 0) {
//			 logger.error("error in updateOffenderFees method trustInsertGltransNew genSequenceOne");
//			 throw new Exception("PROCESS_GL_TRANS_NEW");
//			 }
			Integer genSequenceOne;
			try {
				GlTransactions newGlTransactions1 = new GlTransactions();
				BeanUtils.copyProperties(offTxnObj, newGlTransactions1);
				newGlTransactions1.setTxnId(offTxnObj.getTxnId().longValue());
				newGlTransactions1.setTxnEntrySeq(offTxnObj.getTxnEntrySeq().longValue());
				newGlTransactions1.setTxnPostUsage(offTxnObj.getTxnPostingType());
				newGlTransactions1.setTxnEntryAmount(new BigDecimal(offTxnObj.getTxnEntryAmount()));
				

				
				trustService.insertGlTransNew(newGlTransactions1);
				logger.info("insertGlTransNew  response");
				genSequenceOne = 1;
			} catch (Exception e) {
				logger.error("error in updateOffenderFees method trustInsertGltransNew genSequenceOne");
				genSequenceOne = 0;
				throw new Exception("PROCESS_GL_TRANS_NEW");
			}
			offTxnObj.setGlEntrySeq(Long.valueOf(glSeq + 2));
			offTxnObj.setTxnPostingType("CR");
			offTxnObj.setAccountCode(crDeductAcntCode);
			logger.info("OcdreceiServiceImpl class updateOffenderFees -> before second trustInsertGltransNew ");
//			Integer genSequenceTwo = ocdreceiRepository.trustInsertGltransNew(offTxnObj);
//			if (genSequenceTwo == 0) {
//				logger.error("error in updateOffenderFees method  trustInsertGltransNew genSequenceTwo");
//				throw new Exception("PROCESS_GL_TRANS_NEW");
//			}
			
			Integer genSequenceTwo;
			try {
				GlTransactions newGlTransactions1 = new GlTransactions();
				BeanUtils.copyProperties(offTxnObj, newGlTransactions1);
				newGlTransactions1.setTxnId(offTxnObj.getTxnId().longValue());
				newGlTransactions1.setTxnEntrySeq(offTxnObj.getTxnEntrySeq().longValue());
				newGlTransactions1.setTxnPostUsage(offTxnObj.getTxnPostingType());
				newGlTransactions1.setTxnEntryAmount(new BigDecimal(offTxnObj.getTxnEntryAmount()));
				trustService.insertGlTransNew(newGlTransactions1);
				logger.info("insertGlTransNew  response");
				genSequenceTwo = 1;
			} catch (Exception e) {
				logger.error("error in updateOffenderFees method  trustInsertGltransNew genSequenceTwo");
				genSequenceTwo = 0;
				throw new Exception("PROCESS_GL_TRANS_NEW");
			}
			
			// offTxnObj.setTxnEntrySeq(txnEntrySeq + 1);
			offTxnObj.setTxnPostingType("DR");
			logger.info("OcdreceiServiceImpl class updateOffenderFees -> before insertIntoOffenderTransaction");
			ocdreceiRepository.insertIntoOffenderTransaction(offTxnObj);
			logger.info("OcdreceiServiceImpl class updateOffenderFees -> before updateOffenderBalance");
			//ocdreceiRepository.updateOffenderBalance(offTxnObj);
			trustService.updateOffenderBalance(offTxnObj, offTxnObj.getCreateUserId());

			

		} else {
			logger.error(
					"Exception in OcdreceiServiceImpl class updateOffenderFees crDeductAcntCode and drAccountCode not found");
			throw new Exception("CR_DEDUCT_TO NOT FOUND");
		}
		offFeeBillTxn.setTrustTxnId(offTxnObj.getTxnId());
		offFeeBillTxn.setTrustTxnEntrySeq(offTxnObj.getTxnEntrySeq());
		logger.info("OcdreceiServiceImpl class updateOffenderFees -> before updateOffenderFees repository call");
		Integer feeAccountUpdateResponse = ocdreceiRepository.updateOffenderFees(offFeeBillTxn);
		offFeeBillTransactionsT1Service.offFeeBillTransactionsT1(offFeeBillTxn);
		offFeeBillTransactionsT2Service.offFeeBillTransactionsT2(offFeeBillTxn);
		logger.info(
				"OcdreceiServiceImpl class updateOffenderFees -> after updateOffenderFees repository call response ="
						+ feeAccountUpdateResponse);
		if (offFeeBillTxn.getBillLdppStartDate() != null) {
			updateOffFeeBillsList.add(offFeeBillTxn);
		}
		logger.info("OcdreceiServiceImpl class updateOffenderFees -> before updateOffFeeBillsDate repository call");
		updateOffFeeBillsDate(updateOffFeeBillsList);
		logger.info("OcdreceiServiceImpl class updateOffenderFees -> after updateOffFeeBillsDate repository call");
		BigDecimal prepaidBal = ocdreceiRepository.getPrepaidBalance(offFeeBillTxn.getOffenderId(), caseLoad,
				offFeeBillTxn.getFeeCode());
		if (prepaidBal != null && prepaidBal.compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal totalBal = ocdreceiRepository.getOffFeeIdTotalBalanceOwing(offFeeBillTxn.getOffenderBookId(),
					offFeeBillTxn.getFeeCode(), caseLoad);
			FeeAccountProfiles fap = new FeeAccountProfiles();
			fap.setOffenderBookId(offFeeBillTxn.getOffenderBookId());
			fap.setFeeCode(offFeeBillTxn.getFeeCode());
			fap.setCaseloadId(caseLoad);
			fap.setOffenderFeeId(offFeeBillTxn.getOffenderFeeId());
			fap.setCreateUserId(offFeeBillTxn.getCreateUserId());
			
			if (totalBal == null || totalBal == BigDecimal.ZERO) {
				fap.setFeeActStatus("P");
			} else {
				fap.setFeeActStatus("A");
			}
			Integer insertCount = ocdreceiRepository.updateFeeAccountStatus(fap);
			if(insertCount == 1) {
				fap= ocdbreciRepository.getOldDataFeeAccountProfiles(fap);

				offFeeAccountProfileT2Service.offFeeAccountProfileT2(fap);
				
			}
			
		}
		return offTxnObj.getGlEntrySeq().intValue();
	}

	// Bill_STATUS = "AR"
	public OffFeeBillTransactions billAgingARStatusProcess(OffFeeBillTransactions offFeeBillTxn) {
		if ("AR".equalsIgnoreCase(offFeeBillTxn.getBillStatus())) {
			if (offFeeBillTxn.getBillTxnAmount() != null && (offFeeBillTxn.getBalanceOwingAmount().subtract(offFeeBillTxn.getBillTxnAmount())).equals(BigDecimal.ZERO)) {
				offFeeBillTxn.setBillStatus("PAID");
			}
		} else {
			offFeeBillTxn = billAgingLDPPProcess(offFeeBillTxn);
		}
		return offFeeBillTxn;
	}

	// Bill_STATUS = "L&D PP"
	public OffFeeBillTransactions billAgingLDPPProcess(OffFeeBillTransactions offFeeBillTxn) {
		if ("LD_PP".equalsIgnoreCase(offFeeBillTxn.getBillStatus())) {
			if (offFeeBillTxn.getBillTxnAmount() != null && offFeeBillTxn.getBalanceOwingAmount().subtract(offFeeBillTxn.getBillTxnAmount()).equals(BigDecimal.ZERO)) {
				offFeeBillTxn.setBillStatus("PAID");
			} else {
				offFeeBillTxn.setBillStatus("LD_PP");
				offFeeBillTxn.setBillLdppStartDate(eliteDateService.getDBTime());
				offFeeBillTxn.setBillAgingStartDate(eliteDateService.getDBTime());
				LocalDateTime sysDate = eliteDateService.getDBTime().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDateTime();
				LocalDateTime sdate = sysDate.plusDays(89);
				Date bae = java.util.Date.from(sdate.atZone(ZoneId.systemDefault()).toInstant());
				offFeeBillTxn.setBillAgingEndDate(bae);
				offFeeBillTxn.setBillLdppEndDate(bae);
			}
		} else {
			offFeeBillTxn = billAgingLDEXProcess(offFeeBillTxn);
		}
		return offFeeBillTxn;

	}

	// Bill_STATUS = "L&D EX (I)"
	public OffFeeBillTransactions billAgingLDEXProcess(OffFeeBillTransactions offFeeBillTxn) {
		if ("LD_EXI".equalsIgnoreCase(offFeeBillTxn.getBillStatus())) {
			if (offFeeBillTxn.getBillTxnAmount() != null && offFeeBillTxn.getBalanceOwingAmount().subtract(offFeeBillTxn.getBillTxnAmount()).equals(BigDecimal.ZERO)) {
				offFeeBillTxn.setBillStatus("PAID");
			} else {
				offFeeBillTxn.setBillStatus("LD_PP");
				offFeeBillTxn.setBillLdppStartDate(eliteDateService.getDBTime());
				offFeeBillTxn.setBillAgingStartDate(eliteDateService.getDBTime());
				LocalDateTime sysDate = eliteDateService.getDBTime().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDateTime();
				LocalDateTime sdate = sysDate.plusDays(89);
				Date bae = java.util.Date.from(sdate.atZone(ZoneId.systemDefault()).toInstant());
				offFeeBillTxn.setBillAgingEndDate(bae);
				offFeeBillTxn.setBillLdppEndDate(bae);
			}
		}
		return offFeeBillTxn;

	}

	public OffenderTransactions setTxnDetails(OffenderTransactions offTxn, String txnPostingType, String subAccntType)
			throws Exception {
		offTxn.setTxnPostingType(txnPostingType);
		if("CR".equals(txnPostingType)) {
			offTxn.setToSubAccountType(subAccntType);
		}else {
			offTxn.setSubAccountType(subAccntType);
		}
		final Date transDate = trunc(eliteDateService.getDBTime());
		offTxn.setTxnEntryDate(transDate);
		offTxn.setModifyDate(transDate);
		offTxn.setPreWithholdAmount(offTxn.getTxnEntryAmount());
		offTxn.setSlipPrintedFlag("N");
		offTxn.setReceiptPendingPrintFlag("Y");
		return offTxn;
	}

	@Transactional(rollbackFor = { Exception.class })
	public void prepaidAcntInsert(OffenderTransactions offTxn) throws Exception {
//		String subAccntTypeTemp = offTxn.getSubAccountType();
//		Integer glSeq = offTxn.getTxnEntrySeq();
//		offTxn.setTxnEntryDesc(ocdreceiRepository.getSubAccountTypeDesc("OTC"));
//		offTxn.setDeductionFlag(null);
//		//ocdreceiRepository.prepaidAccountTransfer(setTxnDetails(offTxn, "DR", "REG"));
	//trustService.insertIntoOffenderTrans(setTxnDetails(offTxn, "DR", "REG"));
//		offTxn.setTxnEntrySeq(offTxn.getTxnEntrySeq() + 1);
//		trustService.insertIntoOffenderTrans(setTxnDetails(offTxn, "CR", subAccntTypeTemp));
//   ocdreceiRepository.prepaidAccountTransfer(setTxnDetails(offTxn, "CR", subAccntTypeTemp));
//		offTxn.setFmSubAccountType("REG");
//		offTxn.setModuleName("OCDRECEI");
//		offTxn.setGlEntrySeq(Long.valueOf(glSeq));
//		//ocdreceiRepository.processGlTransNew(offTxn);
//						
//		trustService.processGlTransNew(offTxn.getCaseloadId(), offTxn.getTxnType(),(Object) null, offTxn.getTxnEntryAmount(), offTxn.getTxnId(),
//				offTxn.getTxnEntryDate(),  offTxn.getTxnEntryDesc(), offTxn.getTxnEntrySeq(), offTxn.getModuleName(), offTxn.getRootOffenderId().intValue(), 
//				(Object)offTxn.getOffenderBookId(), (Object)offTxn.getFmSubAccountType(),  offTxn.getSubAccountType(), null, null, "", offTxn.getGlEntrySeq().intValue(),
//				(Object)null, offTxn.getCreateUserId());
//		logger.info("processGlTransNew reponse");
		String subAccntTypeTemp = offTxn.getSubAccountType();
		Integer glSeq = offTxn.getTxnEntrySeq();
		offTxn.setTxnEntryDesc(ocdreceiRepository.getSubAccountTypeDesc("OTC"));
		offTxn.setDeductionFlag(null);
		try {
			trustService.insertIntoOffenderTrans(setTxnDetails(offTxn, "DR", "REG"));

			
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" prepaidAccountTransfer", e);
			throw new Exception("PROCESS_GL_TRANS_NEW");
		}
		
		offTxn.setTxnEntrySeq(offTxn.getTxnEntrySeq() + 1);
		try {
			trustService.insertIntoOffenderTrans(setTxnDetails(offTxn, "CR", subAccntTypeTemp));
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" prepaidAccountTransfer", e);
			throw new Exception("PROCESS_GL_TRANS_NEW");
		}
		
		//ocdbreciRepository.prepaidAccountTransfer(setTxnDetails(offTxn, "CR", subAccntTypeTemp));
		offTxn.setFmSubAccountType("REG");
		offTxn.setModuleName("OCDRECEI");
		offTxn.setGlEntrySeq(Long.valueOf(glSeq));
		//result = ocdbreciRepository.processGlTransNew(offTxn);
		//procedure call
		try {
			trustService.processGlTransNew(offTxn.getCaseloadId(), offTxn.getTxnType(),(Object) null, offTxn.getTxnEntryAmount(), offTxn.getTxnId(),
					offTxn.getTxnEntryDate(),  offTxn.getTxnEntryDesc(), offTxn.getTxnEntrySeq(), offTxn.getModuleName(), offTxn.getRootOffenderId().intValue(), 
					(Object)offTxn.getOffenderBookId(), (Object)offTxn.getFmSubAccountType(),  offTxn.getToSubAccountType(), null, null, "", offTxn.getGlEntrySeq().intValue(),
					(Object)null, offTxn.getCreateUserId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		logger.info("processGlTransNew reponse");

	}

	@Override
	public Integer getPaymentObligationCount(Long offenderId) {
		return ocdreceiRepository.getPaymentObligationCount(offenderId);
	}

	@SuppressWarnings("null")
	@Override
	public List<OffenderTransactions> printReportSupv(OffenderTransactions returnReport) {
		List<OffenderTransactions> runreport = new ArrayList<OffenderTransactions>();
		byte[] pdfReport = null;
		final List<OffenderTransactions> returnData = new ArrayList<>();
		final OffenderTransactions report = new OffenderTransactions();
		final Map<String, Object> param = new HashMap<>();
		List<OcrorrecReportsBean> fields = new ArrayList<OcrorrecReportsBean>();
		OcrorrecReportsBean obj = new OcrorrecReportsBean();
		VAgencyAddresses addrBean = new VAgencyAddresses();
		VAddresses vAddresses = new VAddresses();
		if (returnReport != null) {
			if (returnReport.getNbtModifyUserId() != null) {
				obj.setfUserOne(returnReport.getNbtModifyUserId());
			}
			if (returnReport.getReceiptNumber() != null) {
				obj.setfReceiptNumber(returnReport.getReceiptNumber());
			}
			if (returnReport.getCaseloadId() != null) {
				addrBean.setAgyLocId(returnReport.getCaseloadId());
			}
			if (returnReport.getRootOffenderId() != null) {
				vAddresses.setOwnerId(BigDecimal.valueOf(returnReport.getRootOffenderId()));
			}
		}
		try {
			final String fCaseloadDesc = ocdreceiRepository.getfcaseloadDesc(returnReport.getCaseloadId());
			if (fCaseloadDesc != null) {
				obj.setCaselodHeaderLabelName(fCaseloadDesc);
			}
			addrBean.setAddressType("MAIL");
			List<VAgencyAddresses> returnList = ocdreceiRepository.getAddreesDetails(addrBean);
			if (!returnList.isEmpty() && returnList.get(0).getStreetInformation() != null) {
				obj.setfAddressOne(returnList.get(0).getStreetInformation());
			}
			StringBuilder addressTwo = new StringBuilder();
			String finalStringTwo = null;
			if (!returnList.isEmpty()) {
				if (returnList.get(0).getCityName() != null) {
					addressTwo = addressTwo.append(returnList.get(0).getCityName().concat(","));
				}
				if (returnList.get(0).getProvStateDesc() != null) {
					addressTwo = addressTwo.append(returnList.get(0).getProvStateDesc().concat(","));
				}
				if (returnList.get(0).getCountryCode() != null) {
					addressTwo = addressTwo.append(returnList.get(0).getCountryCode().concat(","));
				}
				if (returnList.get(0).getZipPostalCode() != null) {
					addressTwo = addressTwo.append(returnList.get(0).getZipPostalCode().concat(","));
				}
			}
			if (addressTwo != null) {
				if (addressTwo.toString().endsWith(",")) {
					finalStringTwo = addressTwo.toString().trim().substring(0, addressTwo.length() - 1);
					obj.setfAddressTwo(finalStringTwo);
				} else {
					obj.setfAddressTwo(finalStringTwo);
				}
			}

			List<Phones> phoneNumberData = new ArrayList<Phones>();
			if (!returnList.isEmpty() && returnList.get(0) != null && returnList.get(0).getAddressId() != null) {
				Phones phonesObj = new Phones();
				phonesObj.setOwnerId(returnList.get(0).getAddressId());
				phoneNumberData = oumaglocRepo.phonesExecuteQuery(phonesObj);
				if (!phoneNumberData.isEmpty() && phoneNumberData.get(0).getPhoneNo() != null) {
					obj.setfPhoneNumber(phoneNumberData.get(0).getPhoneNo());
				}
			}
			if (returnReport.getOffenderBookId() != null) {
				String clientName = ocdreceiRepository.getClientName(returnReport.getOffenderBookId(),
						returnReport.getCreateUserId());
				if (clientName != null) {
					obj.setfClientName(clientName);
				}
			}

			List<VAddresses> addressClientList = ocdaddreDao.vAddSearchVAddresses(vAddresses);
			if (!addressClientList.isEmpty()) {
				for (VAddresses vAddresses2 : addressClientList) {
					if ("Y".equals(vAddresses2.getPrimaryFlag())) {
						StringBuilder addressClient = new StringBuilder();
						String finalString = null;
						if (vAddresses2.getHouse() != null) {
							addressClient = addressClient.append(vAddresses2.getHouse().concat(","));
						}

						if (vAddresses2.getCityName() != null) {
							addressClient = addressClient.append(vAddresses2.getCityName().concat(","));
						}
						if (vAddresses2.getProvStateDesc() != null) {
							addressClient = addressClient.append(vAddresses2.getProvStateDesc().concat(","));
						}
						if (vAddresses2.getCountryDesc() != null) {
							addressClient = addressClient.append(vAddresses2.getCountryDesc().concat(","));
						}
						if (vAddresses2.getZipPostalCode() != null) {
							addressClient = addressClient.append(vAddresses2.getZipPostalCode().concat(","));
						}
						if (addressClient != null) {
							if (addressClient.toString().endsWith(",")) {
								finalString = addressClient.toString().trim().substring(0, addressClient.length() - 1);
								obj.setfClientAddress(finalString);
							} else {
								obj.setfClientAddress(addressClient.toString());
							}
						}
					}
				}
			}
			obj.setfSidNumber(returnReport.getRootOffenderId());
			Date longSupvDate = ocdreceiRepository.getLongestSupervisionExpireDate(returnReport.getOffenderBookId());
			if (longSupvDate != null) {
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				obj.setfDateOfBirth(formatter.format(longSupvDate));
			}

			List<CasePlans> casePlanList = new ArrayList<>();
			casePlanList = ocdreceiRepository.getCasePlanDetailsToGetPoName(returnReport.getOffenderBookId());
			if (!casePlanList.isEmpty()) {
				if (casePlanList.get(0).getCalAgyLocId() != null || casePlanList.get(0).getSacStaffId() != null
						|| casePlanList.get(0).getFromDate() != null || casePlanList.get(0).getRole() != null
						|| casePlanList.get(0).getPosition() != null) {
					StaffMembersV2 staffBean = new StaffMembersV2();
					staffBean = ocdiplanRepository.casPlnPostQuerySacStaffId(casePlanList.get(0));
					if (staffBean.getStaffName() != null) {
						obj.setfPoName(staffBean.getStaffName());
					}
				}
			}
			String remitterName = ocdreceiRepository.getRemitterName(returnReport.getTxnId(),
					returnReport.getTxnEntrySeq());
			if (remitterName != null) {
				obj.setfRemitter(remitterName);
			}

			String txnDescription = ocdreceiRepository.getTransActionDescription(returnReport.getTxnType());
			if (txnDescription != null) {
				obj.setfPaymentType(txnDescription);
			}
			if (returnReport.getTxnId() != null) {
				obj.setfPaymentNumber(returnReport.getTxnId().toString());
			}
			List<FeeAccountBalanceBean> feeAcntBeanList = new ArrayList<FeeAccountBalanceBean>();
			BigDecimal beForePaymentTotal = BigDecimal.ZERO;
			BigDecimal paymentAmountTotal = BigDecimal.ZERO;
			BigDecimal afterPaymentTotal = BigDecimal.ZERO;

			BigDecimal beForePaymentTotalVal = BigDecimal.ZERO;
			BigDecimal paymentAmountTotalVal = BigDecimal.ZERO;
			BigDecimal afterPaymentTotalVal = BigDecimal.ZERO;

			List<OffFeeBillTransactions> offFeeData = ocdreceiRepository
					.getFeeBillPrvsCurrentBlnc(returnReport.getTxnId());
			for (OffFeeBillTransactions offFeeBillTransactions : offFeeData) {
				FeeAccountBalanceBean feeAcntBlncBean = new FeeAccountBalanceBean();
				feeAcntBlncBean.setFeeCode(offFeeBillTransactions.getFeeCode());
				feeAcntBlncBean.setCaseloadDescription(
						ocdreceiRepository.getfcaseloadDesc(offFeeBillTransactions.getCaseloadId()));
				beForePaymentTotal = offFeeBillTransactions.getPreviousAmount();
				beForePaymentTotalVal = beForePaymentTotalVal.add(beForePaymentTotal);

				feeAcntBlncBean.setBeforePaymentBalance(beForePaymentTotal);
				afterPaymentTotal = offFeeBillTransactions.getBillTxnAmount();
				afterPaymentTotalVal = afterPaymentTotalVal.add(afterPaymentTotal);

				feeAcntBlncBean.setAfterPaymentBalance(afterPaymentTotal);
				paymentAmountTotal = offFeeBillTransactions.getPreviousAmount()
						.subtract(offFeeBillTransactions.getBillTxnAmount());
				paymentAmountTotalVal = paymentAmountTotalVal.add(paymentAmountTotal);

				feeAcntBlncBean.setPaymentAmount(paymentAmountTotal);
				feeAcntBeanList.add(feeAcntBlncBean);
			}
			if (!feeAcntBeanList.isEmpty()) {
				obj.setFeeAccountBalanceBean(feeAcntBeanList);
			}

			obj.setfBlncBeforePaymentTot(beForePaymentTotalVal);
			obj.setfPaymentAfterAmountTot(afterPaymentTotalVal);
			obj.setfPaymentAmountTot(paymentAmountTotalVal);
			fields.add(obj);
			pdfReport = generateReport("OCRORREC", param, fields);
			report.setReport(pdfReport);
			report.setSealFlag("1");
			returnData.clear();
			returnData.add(report);
			runreport.add(report);
			return runreport;
		} catch (Exception e) {
			logger.error("OcdreceiServiceImpl printReportSupv" + e.getMessage());
			return runreport;
		}
	}

	@Override
	public List<FeeAccounts> getFeeCodeRecordGroup() {
		List<FeeAccounts> returnList = new ArrayList<FeeAccounts>();
		returnList = ocdreceiRepository.getFeeCodeRecordGroup();
		logger.info("getFeeCodeRecordGroup reponse" + returnList);
		for (FeeAccounts obj : returnList) {
			if (obj.getExpiryDatetime() != null) {
				obj.setCanDisplay(false);
			} else {
				obj.setCanDisplay(true);
			}
		}
		return returnList;
	}

	@Override
	public String getbillEndDayPfVal() {
		return ocdreceiRepository.getbillEndDayPfVal();
	}
}