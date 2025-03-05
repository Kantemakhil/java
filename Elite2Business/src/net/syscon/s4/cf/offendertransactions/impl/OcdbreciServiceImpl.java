package net.syscon.s4.cf.offendertransactions.impl;

import java.io.InputStream;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import net.syscon.s4.cf.deductions.OcutrdetService;
import net.syscon.s4.cf.deductions.beans.FeeAccountBalanceBean;
import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.offendertransactions.OcdadjusService;
import net.syscon.s4.cf.offendertransactions.OcdbreciRepository;
import net.syscon.s4.cf.offendertransactions.OcdbreciService;
import net.syscon.s4.cf.offendertransactions.OcdreceiRepository;
import net.syscon.s4.cf.offendertransactions.OcdreceiService;
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
import net.syscon.s4.im.beans.TransStatementBean;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.im.beans.VAgencyAddresses;
import net.syscon.s4.im.beans.VTrustHeader;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.trust.trustaccounts.OtdsubatRepository;
import net.syscon.s4.inst.casemanagement.OcdiplanRepository;
import net.syscon.s4.inst.casemanagement.beans.StaffMembersV2;
import net.syscon.s4.inst.demographicsbiometrics.OcdaddreRepository;
import net.syscon.s4.pkgs.deductions.DeductionsService;
import net.syscon.s4.pkgs.financial.FinancialService;
import net.syscon.s4.pkgs.trust.TrustService;
import net.syscon.s4.triggers.OffFeeAccountProfileT2Service;
import net.syscon.s4.triggers.OffFeeBillTransactionsT1Service;
import net.syscon.s4.triggers.OffFeeBillTransactionsT2Service;

/**
 * Class OcdbreciServiceImpl
 */
@Service
public class OcdbreciServiceImpl extends BaseBusiness implements OcdbreciService {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdbreciServiceImpl.class.getName());

	@Autowired
	private OcdbreciRepository ocdbreciRepository;

	@Autowired
	private EliteDateService eliteDateService;

	List<OffenderTransactions> runreportTemp;

	@Autowired
	private OcdreceiRepository ocdreceiRepository;

	@Autowired
	private OcdiplanRepository ocdiplanRepository;

	@Autowired
	private OumaglocRepository oumaglocRepo;

	@Autowired
	private OcdaddreRepository ocdaddreDao;

	@Autowired
	private OcdadjusService ocdadjusService;
	
	@Autowired
	private OtdsubatRepository otdsubatRepository;
	
	@Autowired
	private OcdreceiService ocdreceiService;
	
	@Autowired
	private OcutrdetService ocutrdetService;
	
	@Autowired
	private FinancialService financialService;
	
	
	@Autowired
	private TrustService trustService;
	
	@Autowired
	private DeductionsService deductionsService;
	
	@Autowired
	private OffFeeBillTransactionsT1Service offFeeBillTransactionsT1Service;
	@Autowired
	private OffFeeBillTransactionsT2Service offFeeBillTransactionsT2Service;
	
	@Autowired
	private OffFeeAccountProfileT2Service offFeeAccountProfileT2Service;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public List<TransactionTypes> cgfkchkOffTxn1OffTxnTxn(final TransactionTypes paramBean) {
		return ocdbreciRepository.cgfkchkOffTxn1OffTxnTxn(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public Object cgfkchkOffTxnOffTxnOff(final OffenderDeductions paramBean) {
		return ocdbreciRepository.cgfkchkOffTxnOffTxnOff(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public Object cgfklkpOffTxnOffTxnOff(final OffenderDeductions paramBean) {
		return ocdbreciRepository.cgfklkpOffTxnOffTxnOff(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<OffenderTransactions> offTxn1ExecuteQuery(final OffenderTransactions searchRecord) {
		return ocdbreciRepository.offTxn1ExecuteQuery(searchRecord);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<OffenderTransactions> offTxnExecuteQuery(final OffenderTransactions searchRecord) {
		return ocdbreciRepository.offTxn1ExecuteQuery(searchRecord);

	}

	@Transactional(rollbackFor = { Exception.class })
	public List<OffenderTransactions> offTxnCommitTemp(final OffenderTransactionsCommitBean commitBean)throws Exception {
		return offTxnCommit(commitBean);
	}

	@Transactional
	public Integer updateOffenderFees(OffFeeBillTransactions offFeeBillTxn, Integer txnEntrySeq) throws Exception {
		List<OffFeeBillTransactions> list=new ArrayList<OffFeeBillTransactions>();
		BigDecimal crDeductAcntCode = ocdbreciRepository.getCrDeductAcntCode(offFeeBillTxn.getOffenderFeeId());
		Integer drAccountCode = ocdbreciRepository.getDrAccountCode(offFeeBillTxn.getBillTxnType(),
				offFeeBillTxn.getCaseloadId());
		Integer txnEntrySeqOne = ocdbreciRepository.getTxnEntrySeq(offFeeBillTxn.getTrustTxnId(),
				offFeeBillTxn.getOffenderId());
		OffenderTransactions offTxnObj = new OffenderTransactions();
		if (crDeductAcntCode != null && drAccountCode != null) {
			offTxnObj.setInfoNumber(offFeeBillTxn.getInfoNumber());
			offTxnObj.setTxnReferenceNumber(offFeeBillTxn.getTxnReferenceNumber());
			offTxnObj.setTxnId(offFeeBillTxn.getTrustTxnId());
			offTxnObj.setTxnEntrySeq(txnEntrySeqOne);
			offTxnObj.setOffenderId(offFeeBillTxn.getOffenderId());
			offTxnObj.setOffenderBookId(offFeeBillTxn.getOffenderBookId());
			offTxnObj.setTxnEntryDesc(ocdbreciRepository.getSubAccountTypeDesc("DEDC"));
			// offTxnObj.setTxnReferenceNumber(txnReferenceNumber);
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
			offTxnObj.setGlEntrySeq(
					ocdbreciRepository.getGlEntrySeqTxn(offTxnObj.getTxnId(), offTxnObj.getOffenderId()).longValue()
							+ 1);
			offTxnObj.setReceiptNumber(offFeeBillTxn.getReceiptNumber());
			//Integer genSequence = ocdbreciRepository.trustInsertGltransNew(offTxnObj);
//			trustService.insertGlTransNew(null);
//			if (genSequence == 0) {
//				throw new Exception("error in trustInsertGltransNew");
//			}
			Integer genSequence;
			try {
				GlTransactions newGlTransactions1 = new GlTransactions();
				BeanUtils.copyProperties(offTxnObj, newGlTransactions1);
				newGlTransactions1.setTxnId(offTxnObj.getTxnId().longValue());
				newGlTransactions1.setTxnEntrySeq(offTxnObj.getTxnEntrySeq().longValue());
				newGlTransactions1.setTxnPostUsage(offTxnObj.getTxnPostingType());
				newGlTransactions1.setTxnEntryAmount(new BigDecimal(offTxnObj.getTxnEntryAmount()));
				newGlTransactions1.setCreateUserId(offFeeBillTxn.getCreateUserId());							
				trustService.insertGlTransNew(newGlTransactions1);
				logger.info("insertGlTransNew  response");
				genSequence = 1;
			} catch (Exception e) {
				logger.error("error in updateOffenderFees method trustInsertGltransNew genSequenceOne");
				genSequence = 0;
				throw new Exception("PROCESS_GL_TRANS_NEW");
			}
			offTxnObj.setGlEntrySeq(
					ocdbreciRepository.getGlEntrySeqTxn(offTxnObj.getTxnId(), offTxnObj.getOffenderId()).longValue()
							+ 1);
			offTxnObj.setTxnPostingType("CR");
			offTxnObj.setAccountCode(crDeductAcntCode);
			offTxnObj.setTxnEntrySeq(
					ocdbreciRepository.getTxnEntrySeq(offFeeBillTxn.getTrustTxnId(), offFeeBillTxn.getOffenderId()));
			//Integer genSequenceTwo = ocdbreciRepository.trustInsertGltransNew(offTxnObj);
			Integer genSequenceTwo;
			try {
				GlTransactions newGlTransactions1 = new GlTransactions();
				BeanUtils.copyProperties(offTxnObj, newGlTransactions1);
				newGlTransactions1.setTxnId(offTxnObj.getTxnId().longValue());
				newGlTransactions1.setTxnEntrySeq(offTxnObj.getTxnEntrySeq().longValue());
				newGlTransactions1.setTxnPostUsage(offTxnObj.getTxnPostingType());
				newGlTransactions1.setTxnEntryAmount(new BigDecimal(offTxnObj.getTxnEntryAmount()));
				newGlTransactions1.setCreateUserId(offFeeBillTxn.getCreateUserId());							

				trustService.insertGlTransNew(newGlTransactions1);
				logger.info("insertGlTransNew  response");
				genSequenceTwo = 1;
			} catch (Exception e) {
				logger.error("error in updateOffenderFees method  trustInsertGltransNew genSequenceTwo");
				genSequenceTwo = 0;
				throw new Exception("PROCESS_GL_TRANS_NEW");
			}
			if (genSequenceTwo == 0) {
				throw new Exception("error in trustInsertGltransNew genSequenceTwo");
			}
			offTxnObj.setTxnPostingType("DR");
			offTxnObj.setCreateUserId(offFeeBillTxn.getCreateUserId());							

			ocdbreciRepository.insertIntoOffenderTransaction(offTxnObj);
			//ocdbreciRepository.updateOffenderBalance(offTxnObj);
			trustService.updateOffenderBalance(offTxnObj, offTxnObj.getCreateUserId());


		} else {
			logger.error(this.getClass().getName()+" updateOffenderFees ");
			throw new Exception("CR_DEDUCT_TO NOT FOUND");
		}
		offFeeBillTxn.setTrustTxnId(offTxnObj.getTxnId());
		offFeeBillTxn.setTrustTxnEntrySeq(offTxnObj.getTxnEntrySeq());
		if (offFeeBillTxn.getBillLdppStartDate() != null) {
			list.add(offFeeBillTxn);
		}
		//fee bill date updation
		if(list.size()> 0 ) {
			ocdreceiService.updateOffFeeBillsDate(list);
			list.clear();
			
		}
		logger.info(this.getClass().getName() + " insertUpdateOffenderFees ");
		int result = insertUpdateOffenderFees(offFeeBillTxn);
//		offFeeBillTransactionsT1Service.offFeeBillTransactionsT1(offFeeBillTxn);
//		offFeeBillTransactionsT2Service.offFeeBillTransactionsT2(offFeeBillTxn);
		// Prepaid Account Status
		BigDecimal prepaidBal = ocdreceiRepository.getPrepaidBalance(offFeeBillTxn.getOffenderId(),
				offFeeBillTxn.getCaseloadId(), offFeeBillTxn.getFeeCode());
		logger.info(this.getClass().getName() + " getPrepaidBalance ");
		if (prepaidBal != null && prepaidBal.compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal totalBal = ocdreceiRepository.getOffFeeIdTotalBalanceOwing(offFeeBillTxn.getOffenderBookId(),
					offFeeBillTxn.getFeeCode(), offFeeBillTxn.getCaseloadId());
			logger.info(this.getClass().getName() + " getOffFeeIdTotalBalanceOwing ");
			FeeAccountProfiles fap = new FeeAccountProfiles();
			fap.setOffenderBookId(offFeeBillTxn.getOffenderBookId());
			fap.setFeeCode(offFeeBillTxn.getFeeCode());
			fap.setCaseloadId(offFeeBillTxn.getCaseloadId());
			fap.setOffenderFeeId(offFeeBillTxn.getOffenderFeeId());
			if (totalBal == null || totalBal == BigDecimal.ZERO) {
				fap.setFeeActStatus("P");
			} else {
				fap.setFeeActStatus("A");
			}
			Integer insertCount = ocdreceiRepository.updateFeeAccountStatus(fap);
			if(insertCount >= 1) {
				fap= ocdbreciRepository.getOldDataFeeAccountProfiles(fap);
				offFeeAccountProfileT2Service.offFeeAccountProfileT2(fap);	
			}

			logger.info(this.getClass().getName() + " updateFeeAccountStatus ");
		}
		return result;
	}

	@Transactional
	private Integer insertUpdateOffenderFees(OffFeeBillTransactions offFeeBillTxn) {
		offFeeBillTxn.setOriginalBillId(null);
		offFeeBillTxn.setOriginalBillTxnNo(null);
		return ocdbreciRepository.updateOffenderFees(offFeeBillTxn);
	}

	// insert Prepaid Acct balance
	@Transactional
	public Integer insertPrepaidAccount(List<OffenderTransactions> prePaidList) throws Exception {
		Integer result = null;
		if (prePaidList.size() > 0 && prePaidList != null) {
			try {
				for (int i = 0; i < prePaidList.size(); i++) {
					result = prepaidAcntInsert(prePaidList.get(i));
				}
			} catch (Exception e) {
				logger.error(this.getClass().getName()+" insertPrepaidAccount :", e);
				throw new Exception("PROCESS_GL_TRANS_NEW");
			}
		}
		return result;
	}

	public OffenderTransactions setTxnDetails(OffenderTransactions offTxn, String txnPostingType, String subAccntType)
			throws Exception {
		//offTxn.setSubAccountType(subAccntType);
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
	public Integer prepaidAcntInsert(OffenderTransactions offTxn) throws Exception {
		Integer result = null;
		String subAccntTypeTemp = offTxn.getSubAccountType();
		offTxn.setTxnEntryDesc(ocdbreciRepository.getSubAccountTypeDesc("OTC"));
		offTxn.setDeductionFlag(null);
		// Integer
		// txnEntrySeq=ocdbreciRepository.getTxnEntrySeq(offTxn.getTxnId(),offTxn.getOffenderId());
		offTxn.setTxnEntrySeq(ocdbreciRepository.getTxnEntrySeq(offTxn.getTxnId(), offTxn.getOffenderId()));
		//ocdbreciRepository.prepaidAccountTransfer(setTxnDetails(offTxn, "DR", "REG"));
		try {
			trustService.insertIntoOffenderTrans(setTxnDetails(offTxn, "DR", "REG"));

			
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" prepaidAccountTransfer", e);
			throw new Exception("PROCESS_GL_TRANS_NEW");
		}
				
		offTxn.setTxnEntrySeq(ocdbreciRepository.getTxnEntrySeq(offTxn.getTxnId(), offTxn.getOffenderId()));
//		ocdbreciRepository.prepaidAccountTransfer(setTxnDetails(offTxn, "CR", subAccntTypeTemp));
		try {
			trustService.insertIntoOffenderTrans(setTxnDetails(offTxn, "CR", subAccntTypeTemp));
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" prepaidAccountTransfer", e);
			throw new Exception("PROCESS_GL_TRANS_NEW");
		}
		offTxn.setFmSubAccountType("REG");
		offTxn.setModuleName("OCDBRECI");
		offTxn.setGlEntrySeq(
				ocdbreciRepository.getGlEntrySeqTxn(offTxn.getTxnId(), offTxn.getOffenderId()).longValue());
		//sult = ocdbreciRepository.processGlTransNew(offTxn);
		try {
			trustService.processGlTransNew(offTxn.getCaseloadId(), offTxn.getTxnType(),(Object) null, offTxn.getTxnEntryAmount(), offTxn.getTxnId(),
					offTxn.getTxnEntryDate(),  offTxn.getTxnEntryDesc(), offTxn.getTxnEntrySeq(), offTxn.getModuleName(), offTxn.getRootOffenderId().intValue(), 
					(Object)offTxn.getOffenderBookId(), (Object)offTxn.getFmSubAccountType(),  offTxn.getToSubAccountType(), null, null, "", offTxn.getGlEntrySeq().intValue(),
					(Object)null, offTxn.getCreateUserId());
			result = 2;
		} catch (Exception e) {
			result = 0;
			logger.error(this.getClass().getName()+" processGlTransNew", e);
			throw new Exception("PROCESS_GL_TRANS_NEW");
			// TODO: handle exception
		}
		logger.info("processGlTransNew reponse");

		return result;
	
	}

	// offender fee update method
	public Integer offFeeUpdate(OffenderTransactionsCommitBean commitBean) throws Exception {
		if (commitBean.getOffFeeUpdateList() != null && commitBean.getOffFeeUpdateList().size() > 0) {
			offFeeUpdate(commitBean.getOffFeeUpdateList());
		}
		return 1;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_TXN
	 * @throws Exception 
	 */
	@Transactional
	public List<OffenderTransactions> offTxnCommit(final OffenderTransactionsCommitBean commitBean) throws Exception {
		int liReturn = 0;
		List<OffenderTransactions> runreport = new ArrayList<>();
		OffenderTransactions objTrans = null;
		Integer lvNumCommited = 0;
		Integer txnId = ocdbreciRepository.txnIdNextValData();
		if (commitBean.getInsertList().size() > 0 && !commitBean.getInsertList().isEmpty()) {
			commitBean.getInsertList().forEach(bo -> {
				bo.setCreateUserId(commitBean.getCreateUserId());
				bo.setModifyUserId(commitBean.getCreateUserId());
				bo.setTxnId(txnId);
			});
		}
		OffenderTransactions retrunData = new OffenderTransactions();
		OffenderTransactions retrunDataReq = new OffenderTransactions();
		Long sessionId = ocdbreciRepository.runReportroleCur();
		if (commitBean.getOmsReqBean() != null && "OCDBRECI".equals(commitBean.getOmsReqBean().getModuleName())) {
			for (final OffenderTransactions offTransactions : commitBean.getInsertList()) {
				if (offTransactions.getReceiptNumber() != null) {
					offTransactions.setCreateUserId(commitBean.getCreateUserId());
					offTransactions.setModuleName(commitBean.getOmsReqBean().getModuleName());
					offTransactions.setSessionId(sessionId);
					liReturn = insertToTmpTable(offTransactions);
					retrunDataReq.setCaseloadId(offTransactions.getCaseloadId());
					retrunDataReq.setSessionId(offTransactions.getSessionId());
					retrunDataReq.setModuleName(offTransactions.getModuleName());
					retrunData = runreport(retrunDataReq);
					runreport.add(retrunData);
				}
			}

			return runreport;

		} else {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				objTrans = new OffenderTransactions();
				try {
					for (final OffenderTransactions offTransactions : commitBean.getInsertList()) {
						offTransactions.setOffenderId(Long.parseLong(offTransactions.getOffenderIdDisplay()));
						String createCaseload = null;
						String errmsg = "Offender: ";
						Integer noErr = 0;
						BigDecimal controltot = BigDecimal.ZERO;
						Double tempTotal = Double.valueOf(0);
						String moduleName = "OCDBRECI";
						String postingType = null;
						String subActType = null;
						Integer seqNo = 0;
						logger.info(this.getClass().getName()+" checkAccountClosedFlag method call ");
						offTransactions.setCreateUserId(commitBean.getCreateUserId());
						String accountClosedFlag = ocdbreciRepository.checkAccountClosedFlag(offTransactions);
						if (accountClosedFlag == null) {
							retrunData.setSealFlag("2");
							runreport.add(retrunData);
							return runreport;
						}
						// offTransactions.setTxnId(ocdbreciRepository.txnIdNextValData());
						final Date transDate = trunc(eliteDateService.getDBTime());
						offTransactions.setTxnEntryDate(transDate);
						offTransactions.setModifyDate(transDate);
						String failFlag = "N";
						String temp = "Processing record ";
						try {
					Map<String, Object> trustAccStatusMap = trustService.chkAccountStatus(offTransactions.getCaseloadId(),new BigDecimal(offTransactions.getOffenderId()));
							String accountStatus = (String) trustAccStatusMap.get("P_OPEN_AN_ACCOUNT");

							if ("Y".equals(accountStatus)) {
								retrunData.setSealFlag("3");
								runreport.add(retrunData);
								return runreport;
							} else if ("X".equals(accountStatus)) {
								retrunData.setSealFlag("4");
								runreport.add(retrunData);
								return runreport;
							} else if ("M".equals(accountStatus)) {
								retrunData.setSealFlag("5");
								runreport.add(retrunData);
								return runreport;
							}
							logger.info(this.getClass().getName()+" checkReceiptProductionFlag method call ");
							String recProdFlag = ocdbreciRepository.checkReceiptProductionFlag(offTransactions);
							if ("Y".equals(recProdFlag)) {
								if (offTransactions.getReceiptNumber() == null) {
									logger.info(this.getClass().getName()+" genTrustRcptNmbr method call ");
									Integer receiptNum = ocdbreciRepository
											.genTrustRcptNmbr("SEQUENCE_" + offTransactions.getCaseloadId());
									if (receiptNum != null) {
										String num = receiptNum.toString();
										while (num.length() < 6) {
											num = "0" + num;
										}
										offTransactions.setReceiptNumber(offTransactions.getCaseloadId() + num);
									}
								}
							}
							Map<String, Object> getSubActTypeMap = new HashMap<String, Object>();
							logger.info(this.getClass().getName()+" getSubActType method call ");
							OtddisbuProcessTransactionsBean newOtddisbuProcessTransactionsBean = new OtddisbuProcessTransactionsBean();
							newOtddisbuProcessTransactionsBean.setpCaseloadId(offTransactions.getCaseloadId());
							newOtddisbuProcessTransactionsBean.setpModuleName("OCDBRECI");
							newOtddisbuProcessTransactionsBean.setpTxnType(offTransactions.getTxnType());
							newOtddisbuProcessTransactionsBean.setCaseLoadType(offTransactions.getCaseloadType());
							getSubActTypeMap = trustService.getSubActType(newOtddisbuProcessTransactionsBean);
							logger.info("getSubActType response");
//							getSubActTypeMap = trustService.getSubActType(moduleName,
//									offTransactions.getTxnType(), offTransactions.getCaseloadId());
							if (getSubActTypeMap != null) {
								if (getSubActTypeMap.get("P_TXN_POST_TYPE") != null) {
									postingType = getSubActTypeMap.get("P_TXN_POST_TYPE").toString();
									offTransactions.setTxnPostingType(postingType);
								}
								if (getSubActTypeMap.get("P_SUB_ACT_TYPE") != null
										&& offTransactions.getSubAccountType() == null) {
									subActType = getSubActTypeMap.get("P_SUB_ACT_TYPE").toString();
									offTransactions.setSubAccountType(subActType);
								}
							}
							logger.info(this.getClass().getName()+" checkDescriptionTxnType method call ");
							String txnEntryDesc = ocdbreciRepository.checkDescriptionTxnType(offTransactions);
							if (txnEntryDesc != null) {
								offTransactions.setTxnEntryDesc(txnEntryDesc);
							} else {
								retrunData.setSealFlag("6");
								runreport.add(retrunData);
								return runreport;
							}
							offTransactions.setSlipPrintedFlag("N");
							offTransactions.setReceiptPendingPrintFlag("Y");
							logger.info(this.getClass().getName()+" getTxnEntrySeq method call ");
							offTransactions.setTxnEntrySeq(
									ocdbreciRepository.getTxnEntrySeq(txnId, offTransactions.getOffenderId()));
							try {
								logger.info(this.getClass().getName()+" insertIntoOffenderTransaction method call ");
								Integer returnList = ocdbreciRepository.insertIntoOffenderTransaction(offTransactions);
								if (returnList == 0) {
									retrunData.setSealFlag("7");
									runreport.add(retrunData);
									return runreport;
								}
								offTransactions.setGlEntrySeq(ocdbreciRepository
										.getGlEntrySeqTxn(txnId, offTransactions.getOffenderId()).longValue());
								logger.info(this.getClass().getName()+" getGlEntrySeqTxn method call ");
								//Integer processGlData = ocdbreciRepository.processGlTransNew(offTransactions);
								//procedure call 
								offTransactions.setFmSubAccountType(null);
								offTransactions.setModuleName("OCDBRECI");
								//offTxn.setGlEntrySeq(Long.valueOf(glSeq));
								try {
									
									Integer processGlData = trustService.processGlTransNew(offTransactions.getCaseloadId(), offTransactions.getTxnType(),(Object) null, offTransactions.getTxnEntryAmount(), offTransactions.getTxnId(),
											offTransactions.getTxnEntryDate(),  offTransactions.getTxnEntryDesc(), offTransactions.getTxnEntrySeq(), offTransactions.getModuleName(), offTransactions.getRootOffenderId().intValue(), 
											(Object)offTransactions.getOffenderBookId(), (Object)offTransactions.getFmSubAccountType(),  offTransactions.getSubAccountType(), null, null, "", offTransactions.getGlEntrySeq().intValue(),
											(Object)null, offTransactions.getCreateUserId());
								} catch (Exception e) {
									retrunData.setSealFlag("8");
									runreport.add(retrunData);
									return runreport;
//									logger.error("Error in Class " + this.getClass().getName() + " processGlTransNew error :: ", e);
//									throw new Exception("PROCESS_GL_TRANS_NEW");
								}

								logger.info(this.getClass().getName()+" processGlTransNew method call ");
//								if (processGlData != 2) {
//									retrunData.setSealFlag("8");
//									runreport.add(retrunData);
//									return runreport;
//								}
								logger.info(this.getClass().getName()+" updateOffenderBalance method call ");
								//ocdbreciRepository.updateOffenderBalance(offTransactions);
								OffenderTransactions offenderTransactionsTemp = offTransactions;
								offenderTransactionsTemp.setToSubAccountType(offTransactions.getSubAccountType());
								trustService.updateOffenderBalance(offenderTransactionsTemp, offTransactions.getModifyUserId());
								logger.info(this.getClass().getName()+" financialDoDuctionsFinancial method call ");
								Map<String, Object> financialDoDuctionsFinancialObj = new HashMap<String, Object>();
                                         financialDoDuctionsFinancialObj = financialService.doDeductionsFinancial(
										offTransactions.getCaseloadId(), offTransactions.getOffenderId(),
										offTransactions.getOffenderBookId(), offTransactions.getTxnType(),
										offTransactions.getTxnId().longValue(), offTransactions.getTxnEntryDate(),
										offTransactions.getSubAccountType(), "Y", new BigDecimal(offTransactions.getTxnEntryAmount()), 
										offTransactions.getTxnEntryAmountOne() != null?offTransactions.getTxnEntryAmountOne().longValue() : null, 
										offTransactions.getTxnEntrySeq()!= null ?offTransactions.getTxnEntrySeq().longValue():null,
										offTransactions.getInfoNumber()!= null ?Integer.parseInt(offTransactions.getInfoNumber()):null,"OCDBRECI");
								
								logger.info(this.getClass().getName()+" getAcAndSetIndDate method call ");
								String getIndDate = deductionsService.getAcAndSetIndDate(offTransactions.getOffenderId(), offTransactions.getCaseloadId(),offTransactions.getCreateUserId());
								if (getIndDate != null && !"GETINDDATE".equals(getIndDate)) {
									retrunData.setSealFlag("9");
									runreport.add(retrunData);
									return runreport;
								}
							} catch (Exception e) {
								throw new RuntimeException("10");
							}
						} catch (Exception e) {
							noErr = noErr + 1;
						}
						lvNumCommited = lvNumCommited + 1;
						offTransactions.setSealFlag("1");
						// retrunData.setTxnId(offTransactions.getTxnId());
						// retrunData.setReceiptNumber(offTransactions.getReceiptNumber());
						offTransactions.setAdjustTxnEntryId(lvNumCommited);

						// Prepaid Amount Flow
						List<OffenderTransactions> prePaidList = new ArrayList<OffenderTransactions>();
						Integer count = 0;
						if (offTransactions.getFeeCode() != null) {
							offTransactions.setTxnId(offTransactions.getTxnId());
							offTransactions.setTxnEntrySeq(offTransactions.getTxnEntrySeq() + 1);
							offTransactions.setInfoNumber(offTransactions.getInfoNumber());
							offTransactions.setSubAccountType(offTransactions.getFeeCode());
							offTransactions.setTxnEntryAmount(offTransactions.getAmount().doubleValue());
							offTransactions.setTxnType("OTC");
							offTransactions.setCaseloadId(offTransactions.getCaseloadId());
							prePaidList.add(offTransactions);
						}
						if (prePaidList.size() > 0 && prePaidList != null) {
							logger.info(this.getClass().getName()+" insertPrepaidAccount method call ");
							count = insertPrepaidAccount(prePaidList);
							//Prepaid Account Status
							logger.info(this.getClass().getName()+" getOffFeeIdTotalBalanceOwing before method call ");
							BigDecimal currentBalanceOwing = ocdreceiRepository.getOffFeeIdTotalBalanceOwing(offTransactions.getOffenderBookId(), offTransactions.getFeeCode(), offTransactions.getCaseloadId());
							FeeAccountProfiles fap = new FeeAccountProfiles();
							fap.setOffenderBookId(offTransactions.getOffenderBookId());
							fap.setFeeCode(offTransactions.getFeeCode());
							fap.setCaseloadId(offTransactions.getCaseloadId());
							if(currentBalanceOwing == null || currentBalanceOwing == BigDecimal.ZERO) {
								fap.setFeeActStatus("P");//Prepaid
							}else {
								fap.setFeeActStatus("A");//Active
							}
							Integer insertCount = ocdreceiRepository.updateFeeAccountStatus(fap);		
							if(insertCount == 1) {
							fap= ocdbreciRepository.getOldDataFeeAccountProfiles(fap);
								offFeeAccountProfileT2Service.offFeeAccountProfileT2(fap);		
							}
							logger.info(this.getClass().getName()+" offenderReceiptsCommit after updateFeeAccountStatus " + insertCount);
						}
						if (count == 8) {
							offTransactions.setSealFlag("8");
						}

						// offender fees updation
						List<OffFeeBillTransactions> offFeeBillTxn = new ArrayList<OffFeeBillTransactions>();
						if (offTransactions.getOffFeeBillList() != null
								&& offTransactions.getOffFeeBillList().size() > 0) {
							for (OffFeeBillTransactions feeBill : offTransactions.getOffFeeBillList()) {
								if (feeBill.getAmount() != null && feeBill.getAmount() > 0) {
									feeBill.setTrustTxnId(offTransactions.getTxnId());
									feeBill.setTrustTxnEntrySeq(offTransactions.getTxnEntrySeq() + 1);
									feeBill.setBillTxnType(offTransactions.getOrgTxnType());
									feeBill.setOffenderId(offTransactions.getOffenderId());
									feeBill.setOffenderBookId(offTransactions.getOffenderBookId());
									feeBill.setTxnReferenceNumber(offTransactions.getTxnReferenceNumber());
									feeBill.setInfoNumber(offTransactions.getInfoNumber());
									feeBill.setReceiptNumber(offTransactions.getReceiptNumber());
									feeBill.setBillTxnAmount(new BigDecimal(feeBill.getAmount()));
									feeBill.setCreateUserId(offTransactions.getCreateUserId());
									//Bill Aging Status Process
									logger.info(this.getClass().getName()+" billAgingARStatusProcess method call ");
									feeBill=ocdreceiService.billAgingARStatusProcess(feeBill);
									offFeeBillTxn.add(feeBill);
								}
							}
						}
						if (offFeeBillTxn.size() > 0 && !offFeeBillTxn.isEmpty()) {
							logger.info(this.getClass().getName()+" offFeeUpdate method call ");
							count = offFeeUpdate(offFeeBillTxn);
							if (count == 8) {
								for (OffenderTransactions obj : runreport) {
									obj.setSealFlag("8");
								}
							}
						}
						runreport.add(offTransactions);
					}
				} catch (Exception e) {
					logger.error(this.getClass().getName()+" offTxnCommit :", e);
					throw new Exception("PROCESS_GL_TRANS_NEW");
				}
			}
		}



		return runreport;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return ocdbreciRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<TransactionTypes> cgfkOffTxn1TxnTypeRecordGroup(String user) {
		return ocdbreciRepository.cgfkOffTxn1TxnTypeRecordGroup(user);

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> cgfkOffTxnDspInformationNRecordGroup(final Long offenderId, final String caseloadId,
			final String txnId) {
		return ocdbreciRepository.cgfkOffTxnDspInformationNRecordGroup(offenderId, caseloadId, txnId);
	}

	@Override
	public OffenderTransactions whenValidateItem(final OffenderTransactions searchBean) {
		OffenderTransactions returnData = new OffenderTransactions();
		VTrustHeader data = ocdbreciRepository.getOffenderIdDetails(searchBean);
		if (data.getLastName() != null) {
			returnData.setRootOffenderId(data.getRootOffenderId().longValue());
			returnData.setLastName(data.getLastName());
			returnData.setFirstName(data.getFirstName());
			returnData.setOffenderBookId(data.getOffenderBookId().longValue());
		} else {
			returnData.setSealFlag("1");
			return returnData;
		}
		String deductionFlag = bouncedCheckAlert(returnData.getRootOffenderId(), searchBean.getTxnType());
		if ("Z".equals(deductionFlag)) {
			returnData.setSealFlag("2");
			// return returnData;
		} else {
			returnData.setDeductionFlag(deductionFlag);
			return returnData;
		}
//		String accountStatus = trustService.chkAccountStatus(searchBean.getCaseloadId(),
//				returnData.getRootOffenderId());
		Map<String, Object> trustAccStatusMap = trustService.chkAccountStatus(searchBean.getCaseloadId(),new BigDecimal(searchBean.getOffenderId()));
		String accountStatus = (String) trustAccStatusMap.get("P_OPEN_AN_ACCOUNT");
		if ("Y".equals(accountStatus)) {
			returnData.setSealFlag("3");
			return returnData;
		} else if ("X".equals(accountStatus)) {
			returnData.setSealFlag("4");
			return returnData;
		} else if ("M".equals(accountStatus)) {
			returnData.setSealFlag("5");
			return returnData;
		}
		String chkDeductions = ocdbreciRepository.chkOffenderDeductions(searchBean.getCaseloadId(),
				returnData.getRootOffenderId(), searchBean.getTxnType());
		if (chkDeductions != null) {
			returnData.setDeductionFlag(chkDeductions);
		} else {
			returnData.setSealFlag("6");
			return returnData;
		}
		List<OffenderDeductions> chkRootOffId = ocdbreciRepository
				.checkOffenderDeductionId(returnData.getRootOffenderId());
		Boolean defFlag = false;
		String flag = null;
		for (final OffenderDeductions offenderDed : chkRootOffId) {
			if (offenderDed.getOffenderDeductionId() != null) {
				flag = ocdbreciRepository.checkOffenderDeductionReceipts(offenderDed.getOffenderDeductionId(),
						searchBean.getTxnType());
				if ("N".equals(flag) && !defFlag) {
				} else {
					flag = "Y";
					defFlag = true;
				}
			}
		}
		if ("Y".equals(flag)) {
			returnData.setSealFlag("7");
			return returnData;
		}
		/*OffenderTransactions bean=ocdreceiRepository.getProdFlagDetails(searchBean);
		if(bean==null) {
			returnData.setSealFlag("8");
		}*/
		return returnData;
	}

	private String bouncedCheckAlert(final Long rootOffenderId, final String txnType) {
		String returnVal = null;
		Long countValue = ocdbreciRepository.countOffenderIdDet(rootOffenderId);
		if (countValue > 0) {
			returnVal = "Y";
			String profileValue = ocdbreciRepository.getProfileValue();
			if ("Y".equals(profileValue)) {
				Long countRefCodes = ocdbreciRepository.getReferenceCodesValid(txnType);
				if (countRefCodes != null) {
					// returnVal = "Z";
				}
			}
		} else {
			returnVal = "N";
		}
		return returnVal;
	}

	@Override
	public OffenderTransactions whenValidateItemAmountInfonumber(final OffenderTransactions searchBean) {
		OffenderTransactions returnData = new OffenderTransactions();
		String chkUniqueId = ocdbreciRepository.chkUniqueObligationFlag(searchBean.getOffenderId());
		if ("Y".equals(chkUniqueId)) {
			returnData.setSealFlag("8");
			return returnData;
		}
		if (searchBean.getTxnEntryAmount() != null && searchBean.getInfoNumber() != null) {
			String chkProfileValue = ocdbreciRepository.getProfileValuePaymentPlan();
			if ("Y".equals(chkProfileValue)) {
				String chkMissingPayPlan = chkMissingPayPlan(searchBean.getOffenderId(), searchBean.getInfoNumber());
				if ("Y".equals(chkMissingPayPlan)) {
					returnData.setSealFlag("9");
					return returnData;
				}
			}
		} else if (searchBean.getTxnEntryAmount() != null && searchBean.getInfoNumber() == null) {
			String chkProfileValue = ocdbreciRepository.getProfileValuePaymentPlan();
			if ("Y".equals(chkProfileValue)) {
				String chkMissingPayPlan = chkMissingPayPlan(searchBean.getOffenderId(), searchBean.getInfoNumber());
				if ("Y".equals(chkMissingPayPlan)) {
					returnData.setSealFlag("10");
					return returnData;
				}
			}
		}
		return returnData;
	}

	private String chkMissingPayPlan(Long offenderId, String infoNumber) {
		return ocdbreciRepository.chkMissingPayPlan(offenderId, infoNumber);
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

	public OffenderTransactions runreport(OffenderTransactions offenderTransactions) {
		// List<OffenderTransactions> returnData = new ArrayList<>();
		final OffenderTransactions report = new OffenderTransactions();
		byte[] pdfReport = null;
		int liReturn = 0;
		pdfReport = ocrdreceRunReportQuery(offenderTransactions);
		liReturn = aftereReport(offenderTransactions.getModuleName(), offenderTransactions.getSessionId(), offenderTransactions.getCreateUserId() );
		if (liReturn == 1) {
			report.setReport(pdfReport);
			report.setSealFlag("1");
			// returnData.clear();
			// returnData.add(report);
			return report;
		}
		report.setReport(pdfReport);
		// returnData.add(report);
		return report;
	}

	public Integer insertToTmpTable(final OffenderTransactions insertList) {
		Integer liReturn = 0;
		liReturn = ocdbreciRepository.printReceiptsTmpDeletequery(insertList.getSessionId(), insertList.getCreateUserId());
		List<OffenderTransactions> bean = new ArrayList<>();
		bean.add(insertList);
		liReturn = ocdbreciRepository.printReceitsInsertQuery(bean);
		return liReturn;
	}

	public byte[] ocrdreceRunReportQuery(OffenderTransactions bean) {
		byte[] returnReport = null;
		ocdbreciReportsBean reportBean = new ocdbreciReportsBean();
		final ReportBean rBean = new ReportBean();
		List<ocdbreciReportsBean> fields = new ArrayList<>();
		SystemProfiles sysPflBean = ocdbreciRepository.getFclientValue();
		if (sysPflBean.getDescription() != null) {
			reportBean.setfClient(sysPflBean.getDescription());
		}
		String fCaseloadDesc = ocdbreciRepository.getfcaseloadDesc(bean.getCaseloadId());
		if (fCaseloadDesc != null) {
			reportBean.setfClDesc(fCaseloadDesc);
		}
		fields = ocdbreciRepository.getReportData(bean);
		if (fields.size() > 0) {
			fields.forEach(element -> {
				if (sysPflBean.getDescription() != null) {
					element.setfClient(sysPflBean.getDescription().toUpperCase());
				}
				if (fCaseloadDesc != null) {
					element.setfClDesc(fCaseloadDesc);
				}
				if (element.getOffenderId() != null) {
					String offenderId = ocdbreciRepository.getOffenderIdData(bean.getCaseloadId(),
							element.getOffenderId(),bean.getCreateUserId());
					if (offenderId != null) {
						element.setfOffId(offenderId);
					}
					VHeaderBlock data = ocdbreciRepository.getOffenderNameData(bean.getCaseloadId(),
							element.getOffenderId(),bean.getCreateUserId());
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
					OffenderBeneficiaries amountsData = ocdbreciRepository
							.getTotalAmountandOwingAmount(element.getOffenderId());
					if (amountsData != null) {
						element.setfTwo(amountsData.getAmount());
					} else {
						element.setfTwo(BigDecimal.ZERO);
					}
				}
				if (element.getAmt() != null) {
					String amountWords = null;
					String centsWord = null;
					BigDecimal amount = ocdbreciRepository.getTruncAmount(element.getAmt());
					BigDecimal cents = element.getAmt().subtract(amount);
					Double amountOne = Double.valueOf(amount.toString());
					Double centsOne = Double.valueOf(cents.toString());
					if (amountOne > 0) {
						amountWords = ocdbreciRepository.getAmoutninWords(amount);
					}
					if (amountOne > 0 && centsOne > 0) {
						amountWords = amountWords + " AND ";
					}
					if (centsOne > 0) {
						centsWord = ocdbreciRepository.getAmoutninWords(cents);
						amountWords = amountWords + centsWord + " CENTS";
					}
					amountWords = amountWords + " ONLY";
					element.setfAmtTwo(amountWords);
				}
				BigDecimal transFees = ocdbreciRepository.getTransfeesAmount(element.getTxnId(), element.getTxnSeq(),
						element.getTxnType());
				if (transFees == null) {
					element.setfFour(BigDecimal.ZERO);
				} else {
					element.setfFour(transFees);
				}
				BigDecimal dedTxnAmount = ocdbreciRepository.getAmountData(element.getTxnId(), element.getTxnSeq(),
						element.getTxnType(), transFees);
				if (dedTxnAmount == null) {
					element.setfThree(BigDecimal.ZERO);
					element.setfFive(BigDecimal.ZERO);
				} else {
					element.setfThree(transFees);
					element.setfFive(transFees);
				}
				List<OffenderDeductions> deductionsData = ocdbreciRepository
						.getReportDataQuery(element.getOffenderId());
				if (deductionsData.size() > 0) {
					deductionsData.forEach(action -> {
						String dedFlag = "N";
						String existFlag = "N";
						if (action.getDeductionType() != null) {
							dedFlag = ocdbreciRepository.dedFlag(action.getDeductionType());
						}
						if (action.getOffenderDeductionId() != null) {
							existFlag = ocdbreciRepository.existFlag(action.getOffenderDeductionId());
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
								BigDecimal monthsBetweenAMount = ocdbreciRepository
										.monthsBetweenAmount(action.getOffenderDeductionId());
								BigDecimal amount = action.getMaxRecursiveAmount().multiply(monthsBetweenAMount);
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
			Map<String, Object> param = new HashMap<>();
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
			logger.error(this.getClass().getName()+" generateReport", e);
		}
		return returnReport;
	}

	public Integer aftereReport(String modulename, Long sessionId , String modifyUserId) {
		Integer returnCount = 0;
		returnCount = ocdbreciRepository.updateOffenderTransactionsrpt(modulename, sessionId , modifyUserId);
		returnCount = ocdbreciRepository.printReceiptsTmpDeletequeryRpt(modulename, sessionId, modifyUserId);
		return returnCount;

	}

	@Override
	public List<ReferenceCodes> cgfkOffTxnDspInformationNRecordGroupOne(final OffenderTransactions searchBean) {
		return ocdbreciRepository.cgfkOffTxnDspInformationNRecordGroupOne(searchBean);
	}

	@Override
	public String getProfileValueDisableBtn() {
		return ocdbreciRepository.getProfileValueDisableBtn();
	}

	@Override
	public List<OffFeeBillTransactions> offFeeExecuteQuery(OffFeeBillTransactions serachBean) {
		VTrustHeader bean = new VTrustHeader();
		bean.setOffenderBookId(new BigDecimal(serachBean.getOffenderBookId()));
		Integer casePlanId = ocdadjusService.getCasePlanId(bean);
		List<OffFeeBillTransactions> returList = ocdbreciRepository.offFeeExecuteQuery(serachBean);
		if (returList != null && returList.size() > 0) {
			returList.forEach(bo -> {
				bo.setCasePlanId(casePlanId);
			});
		}
		return ocdadjusService.postExecuteQuery(returList);
	}

	@Override
	public Date longestSuperVisionDate(Long offenderBookId) {
		return ocdbreciRepository.longestSuperVisionDate(offenderBookId);
	}

	@Transactional(rollbackFor = { Exception.class })
	public Integer offFeeUpdate(List<OffFeeBillTransactions> offFeeBillTxn) throws Exception {

		if (offFeeBillTxn != null && offFeeBillTxn.size() > 0) {
			for (OffFeeBillTransactions bo : offFeeBillTxn) {
				bo.setBillTxnAmount(new BigDecimal(bo.getBalance()));
				if (bo.getBalance() < 0 && bo.getAmount() >= 0) {
					logger.error(this.getClass().getName()+" offFeeUpdate ");
					throw new Exception("AMT_CANNOT_GREATER_THAN_BALANCE_OWING");
				}
				logger.error(this.getClass().getName()+" getBillTranId method call");
				Integer txnNo = ocdbreciRepository.getBillTranId(bo.getBillId());
				bo.setBillTxnNo(txnNo);
				try {
					logger.error(this.getClass().getName()+" updateOffenderFeesBillAging method call");
					updateOffenderFeesBillAging(bo, bo.getTrustTxnEntrySeq());
				} catch (Exception e) {
					logger.error(this.getClass().getName()+" offFeeUpdate", e);
					throw new Exception("PROCESS_GL_TRANS_NEW");
				}
			}
		}
		return 1;
	}

	public void updateOffenderFeesBillAging(OffFeeBillTransactions offFeeBillTxn, Integer txnEntrySeq)
			throws Exception {
		updateOffenderFees(offFeeBillTxn, txnEntrySeq);
	}

	public List<ReferenceCodes> docketRecordGroup(final Long offenderBookId) {
		return ocdbreciRepository.docketRecordGroup(offenderBookId);
	}

	public Integer cgfkOffTxnDspInformationNRecordGroupCount(final Long offenderBookId) {
		return ocdbreciRepository.cgfkOffTxnDspInformationNRecordGroupCount(offenderBookId);
	}

	public List<OffenderTransactions> offTxnCommitRecipt(final OffenderTransactionsCommitBean commitBean) throws Exception {
		List<OffenderTransactions> runreport = offTxnCommit(commitBean);
		return runreport;
	}

	@SuppressWarnings("null")
	@Override
	public List<OffenderTransactions> printReportSupv(OffenderTransactionsCommitBean returnReportList) {
		List<OffenderTransactions> runreport = new ArrayList<OffenderTransactions>();

		OffenderTransactions returnData = new OffenderTransactions();
		final OffenderTransactions report = new OffenderTransactions();
		final Map<String, Object> param = new HashMap<>();
		List<OffenderTransactions> returnReportGetList = returnReportList.getInsertList();
		List<TransStatementBean> fields = new ArrayList<TransStatementBean>();
		byte[] pdfReport = null;
		try {
		for (OffenderTransactions returnReport : returnReportGetList) {
			TransStatementBean obj = new TransStatementBean();
			VAgencyAddresses addrBean = new VAgencyAddresses();
			if(returnReport.getNbtModifyUserId()!=null) {				
				obj.setfUserOne(returnReport.getNbtModifyUserId());
			}
			if(returnReport.getReceiptNumber()!=null) {				
				obj.setfReceiptNumber(returnReport.getReceiptNumber());
			}
			if(returnReport.getCaseloadId()!=null) {
				addrBean.setAgyLocId(returnReport.getCaseloadId());
			}
			final String fCaseloadDesc = ocdreceiRepository.getfcaseloadDesc(returnReport.getCaseloadId());
			if (fCaseloadDesc != null) {
				obj.setCaselodHeaderLabelName(fCaseloadDesc);
			}			
			addrBean.setAddressType("MAIL");
			List<VAgencyAddresses> returnList = ocdreceiRepository.getAddreesDetails(addrBean);
			if (!returnList.isEmpty() && returnList.get(0).getStreetInformation() != null) {
				obj.setfAddressOne(returnList.get(0).getStreetInformation());
			}
			Phones phonesObj = new Phones();
			StringBuilder addressTwo = new StringBuilder();
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
				if (returnList.get(0).getAddressId() != null) {
					phonesObj.setOwnerId(returnList.get(0).getAddressId());
				}
			}
			if (addressTwo != null) {
				obj.setfAddressTwo(addressTwo.toString());
			}

			List<Phones> phoneNumberData = new ArrayList<Phones>();
			phoneNumberData = oumaglocRepo.phonesExecuteQuery(phonesObj);
			if (!phoneNumberData.isEmpty() && phoneNumberData.get(0).getPhoneNo() != null) {
				obj.setfPhoneNumber(phoneNumberData.get(0).getPhoneNo());
			}
			String clientName = ocdreceiRepository.getClientName(returnReport.getOffenderBookId(),returnReport.getCreateUserId());
			if (clientName != null) {
				obj.setfClientName(clientName);
			}
			VAddresses vAddresses = new VAddresses();
			vAddresses.setOwnerId(BigDecimal.valueOf(returnReport.getRootOffenderId()));
			List<VAddresses> addressClientList = ocdaddreDao.vAddSearchVAddresses(vAddresses);
			if (!addressClientList.isEmpty()) {
				for (VAddresses vAddresses2 : addressClientList) {
					if ("Y".equals(vAddresses2.getPrimaryFlag())) {
						StringBuilder addressClient = new StringBuilder();
						if (vAddresses2.getCityName() != null) {
							addressClient = addressClient.append(vAddresses2.getCityName().concat(","));
						}
						if (vAddresses2.getProvStateDesc() != null) {
							addressClient = addressClient.append(vAddresses2.getProvStateDesc().concat(","));
						}
						if (vAddresses2.getCountryCode() != null) {
							addressClient = addressClient.append(vAddresses2.getCountryCode().concat(","));
						}
						if (vAddresses2.getZipPostalCode() != null) {
							addressClient = addressClient.append(vAddresses2.getZipPostalCode().concat(","));
						}
						if (addressClient != null) {
							obj.setfClientAddress(addressClient.toString());
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

			List<OffFeeBillTransactions> offFeeData = ocdbreciRepository
					.getFeeBillPrvsCurrentBlnc(returnReport.getTxnId(),returnReport.getOffenderBookId());
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

		}

		pdfReport = generateReport("OCROBREC", param, fields);
		report.setReport(pdfReport);
		returnData = report;
		runreport.add(returnData);		
		return runreport;
		} catch(Exception e){
		logger.error("OcdbreciServiceImpl printReportSupv" + e.getMessage());
		return runreport;
		}
	}



	@Override
	public OffenderTransactions getProdFlagDetails(OffenderTransactions searchBean) {
		return ocdbreciRepository.getProdFlagDetails(searchBean);
	}
}
