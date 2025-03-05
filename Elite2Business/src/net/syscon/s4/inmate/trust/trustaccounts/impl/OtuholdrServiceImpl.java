package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.trust.trustaccounts.OtuholdrRepository;
import net.syscon.s4.inmate.trust.trustaccounts.OtuholdrService;
import net.syscon.s4.pkgs.deductions.DeductionsService;
import net.syscon.s4.pkgs.financial.FinancialService;
import net.syscon.s4.pkgs.trust.TrustService;

@Service
public class OtuholdrServiceImpl extends BaseBusiness implements OtuholdrService {
	private static Logger logger = LogManager.getLogger(OtuholdrServiceImpl.class.getName());

@Autowired
private OtuholdrRepository otuholdrRepository;

@Autowired
private EliteDateService eliteDateService;

@Autowired
private TrustService trustService;

@Autowired
private DeductionsService deductionsService;

@Autowired
private FinancialService financialService;

/**
 *Creates new OtuholdrServiceImpl class Object 
 */
public OtuholdrServiceImpl(){
//	OtuholdrServiceImpl
}		 


/**
 * This method is execute a Dao class method when trigger event is fired
 *
 * @param params
 *
 * @throws Exception
*/
	public List<Object> CgwhenNewFormInstance()  {
		return null;
}

/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * @throws SQLException
 */
public List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions searchRecord)  {
		return otuholdrRepository.offTxnExecuteQuery(searchRecord);

}

/**Insert the records from database table
 *
 * @param lstOFF_TXN
 *
 * @throws SQLException
 */
@Transactional
public Integer offTxnCommit(OffenderTransactionsCommitBean commitBean)  {
		int liReturn = 0;
			//insertRecords
			if (commitBean.getInsertList() != null	&& commitBean.getInsertList().size() > 0) {
				liReturn = otuholdrRepository.offTxnInsertOffenderTransactions(commitBean.getInsertList());

			}
			//updateRecords
			if (commitBean.getUpdateList() != null	&& commitBean.getUpdateList().size() > 0) {
				liReturn = otuholdrRepository.offTxnUpdateOffenderTransactions(commitBean.getUpdateList());
			}
			//deleteRecords
			if (commitBean.getDeleteList() != null	&& commitBean.getDeleteList().size() > 0) {
				commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
				liReturn = otuholdrRepository.offTxnDeleteOffenderTransactions(commitBean.getDeleteList());
			}
			return liReturn;
}


@Override
public String getVHoldClearFlag(final OffenderTransactions paramBean) {
	return otuholdrRepository.getVHoldClearFlag(paramBean);
}

private Integer finalInsertOffTxn(final OffenderTransactions paramBean) {
	Integer pTxnNum = null;
	Long pOffId = null;
	Long pOffBookId = null;
	String pCsldId = null;
	Integer pTxnEntrySeq = null;
	String pSubAccountType = null;
	Double pTotAmt = null;
	Integer pGlSeq = null;
	String pTxnDesc = null;
	Integer pHoldNumber = null;
	String pTxnPostingType = null;
	String pTxnType = null;
	String pTxnAdjustedFlag = null;
	String pHoldClearFlag = null;
	Date pTxnDate = null;
	String pModifyUserId = null;
	
	Date sysDate = eliteDateService.getDBTime();
	try {
		pTxnDate = sysDate;
		pOffId = paramBean.getOffenderId();
		pCsldId = paramBean.getCaseloadId();
		pTxnEntrySeq = 0;
		pTotAmt = paramBean.getTxnEntryAmount();
		pTxnDesc = paramBean.getTxnEntryDesc();
		pSubAccountType = paramBean.getSubAccountType();
		pTxnType = "HOR";
		pTxnAdjustedFlag = "N";
		pHoldClearFlag = "Y";
		pTxnPostingType = "CR";
		pTxnNum = otuholdrRepository.genTrustTrans("TXN_ID");
		pHoldNumber = paramBean.getHoldNumber();
		pModifyUserId = paramBean.getCreateUserId();
		try {
			pOffBookId = otuholdrRepository.getMaxBookId(pOffId);
		}catch (Exception e) {
			if (e.getMessage().startsWith("otuholdr")) {
				throw new RuntimeException(e.getMessage());
			}
			logger.error("Error in FINAL_INSERT_OFF_TXN, getting MAX Offender_Book_ID.", e);
			throw new RuntimeException("otuholdr.errfinalbookid");
		}
		pTxnEntrySeq = 1;
		
		try {
			subInsertOffTxn(pTxnNum, pTxnEntrySeq, pCsldId, pOffId, pOffBookId, pTxnPostingType,
					pTxnType, pTxnDesc, pTotAmt, pTxnDate, pSubAccountType, sysDate,
					paramBean.getCreateUserId(), "N", "N", "Y", pHoldNumber);
		} catch (Exception e) {
			if (e.getMessage().startsWith("otuholdr")) {
				throw new RuntimeException(e.getMessage());
			}
			logger.error("Error(final_insert_off_txn.sub_insert_off_txn", e);
			throw new RuntimeException("otuholdr.errfinalinsrtofftxn");
		}
		pTxnEntrySeq = 1;
		
		try {			
			trustService.processGlTransNew(pCsldId, pTxnType, null, pTotAmt, pTxnNum, sysDate, pTxnDesc,
					pTxnEntrySeq, "OTDHIREM",pOffId.intValue(),pOffBookId, null, pSubAccountType, null,
					null, null, pGlSeq,  null,paramBean.getCreateUserId());
		} catch (Exception e) {
			if (e.getMessage().startsWith("otuholdr")) {
				throw new RuntimeException(e.getMessage());
			}
			logger.error("Error(final_insert_off_txn.Trust.process_gl_trans_new)", e);
			throw new RuntimeException("otuholdr.errfinalgltxn");
		}
		
		otuholdrRepository.updateOffenderTrustAccounts(pTotAmt, pOffId, pCsldId,pModifyUserId);
		otuholdrRepository.updateOffenderSubAccount(pTotAmt, pOffId, pCsldId, pSubAccountType,pModifyUserId);
		
		
	}catch (Exception e) {
		if (e.getMessage().startsWith("otuholdr")) {
			throw new RuntimeException(e.getMessage());
		}
		logger.error("Error(final_insert_off_txn)", e);
		throw new RuntimeException("otuholdr.errfinalinsert");
	}
	
	return pTxnNum;
}

	private void subInsertOffTxn(Integer pTxnNum, Integer pTxnEntrySeq, String pCsldId, Long pOffId, 
			Long pOffBookId, String pTxnPtgType, String pTxnType, String pTxnDesc, Double pTxnAmount, 
			Date pTxnDate, String pSubAcType, Date pModfyDate, String pModfyUserId, String pSlipPrFlag, 
			String pTxnAdjFlag, String pHoldClFlag, Integer pHoldNumber) {
		
		try {
			try {
				otuholdrRepository.subInsertOffTxnInsert(pTxnNum, pTxnEntrySeq, pCsldId, pOffId, pOffBookId, pTxnPtgType, pTxnType, pTxnDesc, pTxnAmount, pTxnDate, pSubAcType, pModfyDate, pModfyUserId, pSlipPrFlag, pTxnAdjFlag, pHoldClFlag, pHoldNumber,pModfyUserId);
			} catch (Exception e) {
				if (e.getMessage().startsWith("otuholdr")) {
					throw new RuntimeException(e.getMessage());
				}
				logger.error("Error: Cannot insert record into Offender Transaction.", e);
				throw new RuntimeException("otuholdr.errinsrtofftxn");
			}
			try {
				OffenderTransactions trans=new OffenderTransactions();
				trans.setCaseloadId(pCsldId);
				trans.setOffenderId(pOffId);
				trans.setTxnPostingType(pTxnPtgType);
				trans.setTxnEntryDate(pTxnDate);
				trans.setTxnReferenceNumber(pTxnNum.toString());
				trans.setTxnType(pTxnType);
				trans.setTxnEntryAmount(pTxnAmount);
				trans.setSubAccountType(pSubAcType);
				
			trustService.updateOffenderBalance(trans, trans.getCreateUserId());
			} catch (Exception e) {
				if (e.getMessage().startsWith("otuholdr")) {
					throw new RuntimeException(e.getMessage());
				}
				logger.error("Error(sub_insert_off_txn.Trust.update_offender_balance)", e);
				throw new RuntimeException("otuholdr.errupdateoffbal");
			}
		} catch (Exception e) {
			if (e.getMessage().startsWith("otuholdr")) {
				throw new RuntimeException(e.getMessage());
			}
			logger.error("Error(sub_insert_off_txn)", e);
			throw new RuntimeException("otuholdr.errsubinsrtofftxn");
		}
		
	}
	
	private void updateHoldRecord(final OffenderTransactions paramBean) {
		final Integer lastTxn = paramBean.getTxnId();
		final Integer lHoldNumber = paramBean.getHoldNumber();
		final Long lOffenderId = paramBean.getOffenderId();
		final String lModifyUserId = paramBean.getCreateUserId();
		try {
		otuholdrRepository.updateHoldRecord(lastTxn, lHoldNumber, lOffenderId, lModifyUserId);
		} catch (Exception e) {
			if (e.getMessage().startsWith("otuholdr")) {
				throw new RuntimeException(e.getMessage());
			}
			logger.error("Error in Update_Hold_Record, updating Hold_Clear_Flag.", e);
			throw new RuntimeException("otuholdr.errinholdupdating");
		}
	}
	@Transactional(rollbackFor=Exception.class)
	public String onInsert(final OffenderTransactions paramBean) {
		Integer lTxnId = 0;
		Integer lTxnEntrySeq = paramBean.getTxnEntrySeq();
		Long lOffenderId = paramBean.getOffenderId();
		Double lTxnEntryAmount = null;
		String lOrgTxnType = null;
		Integer lHoldTxnId = paramBean.getTxnId();
		final Date sysdate = eliteDateService.getDBTime();
		
		try {
			lTxnId = finalInsertOffTxn(paramBean);
			updateHoldRecord(paramBean);
			try {
				deductionsService.getAcAndSetIndDate(paramBean.getOffenderId(), paramBean.getCaseloadId(), paramBean.getCreateUserId());
			try {
				lOrgTxnType = otuholdrRepository.getOrgTxnType(lHoldTxnId, lTxnEntrySeq, lOffenderId);
				if (lOrgTxnType != null) {
				financialService.doDeductionsFinancial(paramBean.getCaseloadId(), paramBean.getOffenderId(), paramBean.getOffenderBookId(), lOrgTxnType, lTxnId!=null?lTxnId.longValue():null, sysdate, paramBean.getSubAccountType(), "Y", paramBean.getTxnEntryAmount()!=null ? BigDecimal.valueOf(paramBean.getTxnEntryAmount()): null ,null, lTxnEntryAmount!=null?lTxnEntryAmount.longValue():null, lTxnEntrySeq,  paramBean.getCreateUserId());
				}
			} catch (Exception e) {
				if (e.getMessage().startsWith("otuholdr")) {
					throw new RuntimeException(e.getMessage());
				}
				logger.error("Other error in Financial.do_deductions_financial", e);
				throw new RuntimeException("otuholdr.otrfinaldodedeciton");
			}
			} catch (Exception e) {
				if (e.getMessage().startsWith("otuholdr")) {
					throw new RuntimeException(e.getMessage());
				}
				logger.error("Error(on_insert.Deductions.get_ac_and_set_ind_date)", e);
				throw new RuntimeException("otuholdr.errsetgetdate");
			}
			
			
		} catch (Exception e) {
			if (e.getMessage().startsWith("otuholdr")) {
				throw new RuntimeException(e.getMessage());
			}
			logger.error("Error(On_Insert", e);
			throw new RuntimeException("otuholdr.oninserterror");
		}
		
		return String.valueOf(lTxnId);
	}

}