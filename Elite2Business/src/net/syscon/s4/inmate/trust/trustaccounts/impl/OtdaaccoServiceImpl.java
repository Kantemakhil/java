package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.GlTransactionsCommitBean;
import net.syscon.s4.inmate.trust.trustaccounts.OtdaaccoRepository;
import net.syscon.s4.inmate.trust.trustaccounts.OtdaaccoService;
import net.syscon.s4.pkgs.deductions.DeductionsService;
import net.syscon.s4.pkgs.financial.FinancialService;
import net.syscon.s4.pkgs.trust.TrustService;

/**
 * Class OtdaaccoServiceImpl
 * 
 */
@Service
public class OtdaaccoServiceImpl extends BaseBusiness implements OtdaaccoService {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdaaccoServiceImpl.class.getName());

	@Autowired
	private OtdaaccoRepository otdaaccoRepository;

	@Autowired
	private EliteDateService eliteDateService;
	
	@Autowired
	private TrustService trustService;

	
	@Autowired
	private FinancialService financialService;
	
	@Autowired
	private DeductionsService deductionsService;
	
	/**
	 * Creates new OtdaaccoServiceImpl class Object
	 */
	public OtdaaccoServiceImpl() {
		// OtdaaccoServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public AccountCodes cgfkchkGlTxn1GlTxnAcCod(final AccountCodes paramBean) {
		return otdaaccoRepository.cgfkchkGlTxn1GlTxnAcCod(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public AccountCodes cgfkchkGlTxnGlTxnAcCode(final AccountCodes paramBean) {
		return otdaaccoRepository.cgfkchkGlTxnGlTxnAcCode(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<GlTransactions> cgrichkGlTransactions(final GlTransactions paramBean) {
		return otdaaccoRepository.cgrichkGlTransactions(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<GlTransactions> glTxn1ExecuteQuery(final GlTransactions searchRecord) {
		return otdaaccoRepository.glTxn1ExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstGL_TXN1
	 *
	 */
	@Transactional(rollbackFor = Exception.class)
	public GlTransactions glTxn1Commit(final GlTransactionsCommitBean commitBean) {
		GlTransactions liReturn = new GlTransactions();
		int retrunData = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			String failFlag = "N";
			String pDrSubAccountType = null;
			String pDrAccntPosting = null;
			String pCrSubAccountType = null;
			String pCrAccntPosting = null;
			BigDecimal drOffBookId = null;
			BigDecimal crOffBookId = null;
			Integer pTxnEntrySeq = null;
			Integer pGlSeq = null;
			Integer pTxnNum = null;
			String adjustDesc = null;
			Date transDate = trunc(eliteDateService.getDBTime());
			try {
				
				for (final GlTransactions objOffTransactions : commitBean.getInsertList()) {
					if (objOffTransactions.getOffenderId() != null) {
						GlTransactions overDraft = validateOvrdraft(objOffTransactions.getOffenderId(),
								objOffTransactions.getCaseloadId(), objOffTransactions.getTxnEntryAmount(),
								objOffTransactions.getAccountCodeOne(), objOffTransactions.getReconClearFlag(),
								objOffTransactions.getTxnType(),commitBean.getCreateUserId());
						if (overDraft != null) {
							if ("A".equals(overDraft.getSealFlag())) {
								liReturn.setSealFlag("A");
								failFlag = "Y";
								return liReturn;
							} else if ("B".equals(overDraft.getSealFlag())) {
								liReturn.setSealFlag("B");
								liReturn.setAccountCode(overDraft.getAccountCode());
								failFlag = "Y";
								return liReturn;
							} else if ("C".equals(overDraft.getSealFlag())) {
								liReturn.setSealFlag("C");
								return liReturn;
							}
						}
					}
					if ((objOffTransactions.getOffenderId() != null)
							&& (objOffTransactions.getNbtOffenderId() == null)) {
						if (objOffTransactions.getTxnEntryDesc() != null) {
							adjustDesc = objOffTransactions.getTxnEntryDesc();
						} else {
							adjustDesc = "ADJUST ACCOUNT";
						}
						
						AccountCodes drAccountCodes = otdaaccoRepository
								.drAccountCodesData(objOffTransactions.getAccountCodeOne());
						if (drAccountCodes != null) {
							pDrSubAccountType = drAccountCodes.getSubAccountType();
							pDrAccntPosting = drAccountCodes.getTxnPostingType();
						}
						AccountCodes crAccountCodes = otdaaccoRepository
								.crAccountCodesData(objOffTransactions.getAccountCodeTwo());
						if (crAccountCodes != null) {
							pCrSubAccountType = crAccountCodes.getSubAccountType();
							pCrAccntPosting = crAccountCodes.getTxnPostingType();
						}
						BigDecimal offBookIdTemp = otdaaccoRepository.maxOffenderBookId(
								objOffTransactions.getCaseloadId(), objOffTransactions.getOffenderId());
						if (offBookIdTemp != null) {
							drOffBookId = offBookIdTemp;
						}
						pTxnNum = otdaaccoRepository.txnIdNextValData();
						pTxnEntrySeq = 1;
						pGlSeq = 1;
						
						//Procedure call
						OffenderTransactions obj = new OffenderTransactions();
						obj.setTxnId(pTxnNum);
						obj.setTxnEntrySeq(pTxnEntrySeq);
						obj.setCaseloadId(objOffTransactions.getCaseloadId());
					    obj.setOffenderId(objOffTransactions.getOffenderId().longValue());	
						obj.setOffenderBookId(drOffBookId.longValue());
						obj.setTxnPostingType("DR");
						obj.setTxnType(objOffTransactions.getTxnType());
						obj.setTxnEntryDesc(adjustDesc);
						obj.setTxnEntryAmount(objOffTransactions.getTxnEntryAmount().doubleValue());
						obj.setTxnEntryDate(transDate);
						obj.setSubAccountType(pCrSubAccountType);
						obj.setSlipPrintedFlag("N");
						obj.setPreWithholdAmount(null);
						obj.setDeductionFlag(null);
						obj.setPayeeCorporateId(null);
						obj.setPayeePersonId(null);
						obj.setDeductionType(null);
						obj.setInfoNumber(null);
						obj.setCreateUserId(objOffTransactions.getCreateUserId());
						retrunData=trustService.insertIntoOffenderTrans(obj);
						
						if (retrunData == 0) {
							liReturn.setSealFlag("E");
							return liReturn;
						}
						
						//Procedure call
						GlTransactions objOffTransactions1=new GlTransactions();
						objOffTransactions1.setTxnPostUsage("DR");
						objOffTransactions1.setAccountCodeOne(objOffTransactions.getAccountCodeOne());
						objOffTransactions1.setAcntPosting(pCrAccntPosting);
						objOffTransactions1.setCaseloadId(objOffTransactions.getCaseloadId());
						objOffTransactions1.setTxnType(objOffTransactions.getTxnType());
						objOffTransactions1.setTxnEntryAmount(objOffTransactions.getTxnEntryAmount());
						objOffTransactions1.setTxnId(pTxnNum.longValue());
						objOffTransactions1.setTxnEntryDate(transDate);
						objOffTransactions1.setTxnEntryDesc(adjustDesc);
						objOffTransactions1.setTxnEntrySeq(pTxnEntrySeq.longValue());
						objOffTransactions1.setGlEntrySeq(pGlSeq.longValue());
						objOffTransactions1.setOffenderId( objOffTransactions.getOffenderId());
						objOffTransactions1.setOffenderBookId(drOffBookId);
						objOffTransactions1.setTxnReferenceNumber(objOffTransactions.getTxnReferenceNumber());
						trustService.insertGlTransNew(objOffTransactions1);
						
						if (retrunData == 0) {
							liReturn.setSealFlag("F");
							return liReturn;
						}
						pGlSeq = pGlSeq + 1;
						
						//Procedure call
						GlTransactions objOffTransactions2=new GlTransactions();
						
						objOffTransactions2.setTxnPostUsage("CR");
						objOffTransactions2.setAccountCode(objOffTransactions.getAccountCodeTwo());
						objOffTransactions2.setAcntPosting(pCrAccntPosting);
						objOffTransactions2.setCaseloadId(objOffTransactions.getCaseloadId());
						objOffTransactions2.setTxnType(objOffTransactions.getTxnType());
						objOffTransactions2.setTxnEntryAmount(objOffTransactions.getTxnEntryAmount());
						objOffTransactions2.setTxnId(pTxnNum.longValue());
						objOffTransactions2.setTxnEntryDate(transDate);
						objOffTransactions2.setTxnEntryDesc(adjustDesc);
						objOffTransactions2.setTxnEntrySeq(pTxnEntrySeq.longValue());
						objOffTransactions2.setGlEntrySeq(pGlSeq.longValue());
						objOffTransactions2.setOffenderId( objOffTransactions.getOffenderId());
						objOffTransactions2.setOffenderBookId(drOffBookId);
						objOffTransactions2.setInfoNumber(null);
						objOffTransactions2.setPayeePersonId(null);
						objOffTransactions2.setPayeeCorporateId(null);
						objOffTransactions2.setPayeeNameText(null);
						objOffTransactions2.setReversedTxnId(null);
						objOffTransactions2.setReversedGlEntrySeq(null);
						objOffTransactions2.setReversedTxnEntrySeq(null);
						objOffTransactions2.setTxnReferenceNumber(objOffTransactions.getTxnReferenceNumber());
						objOffTransactions2.setOffDeductionId(null);					
						objOffTransactions2.setCreateUserId(commitBean.getCreateUserId());
						trustService.insertGlTransNew(objOffTransactions2);
								
						if (retrunData == 0) {
							liReturn.setSealFlag("G");
							return liReturn;
						}
						liReturn.setSealFlag("Z");
						liReturn.setTxnId(Long.valueOf(pTxnNum.toString()));
					}
					if ((objOffTransactions.getOffenderId() == null)
							&& (objOffTransactions.getNbtOffenderId() != null)) {
						if (objOffTransactions.getTxnEntryDesc() != null) {
							adjustDesc = objOffTransactions.getTxnEntryDesc();
						} else {
							adjustDesc = "ADJUST ACCOUNT";
						}
						AccountCodes drAccountCodes = otdaaccoRepository
								.drAccountCodesData(objOffTransactions.getAccountCodeOne());
						if (drAccountCodes != null) {
							pDrSubAccountType = drAccountCodes.getSubAccountType();
							pDrAccntPosting = drAccountCodes.getTxnPostingType();
						}
						AccountCodes crAccountCodes = otdaaccoRepository
								.crAccountCodesData(objOffTransactions.getAccountCodeTwo());
						if (crAccountCodes != null) {
							pCrSubAccountType = crAccountCodes.getSubAccountType();
							pCrAccntPosting = crAccountCodes.getTxnPostingType();
						}
						BigDecimal offBookIdTempCR = otdaaccoRepository.maxOffenderBookId(
								objOffTransactions.getCaseloadId(), objOffTransactions.getNbtOffenderId());
						if (offBookIdTempCR != null) {
							crOffBookId = offBookIdTempCR;
						}
						pTxnNum = otdaaccoRepository.txnIdNextValData();
						pTxnEntrySeq = 1;
						pGlSeq = 1;
						//Procedure call
						
						OffenderTransactions offenderObj=new OffenderTransactions();
						offenderObj.setTxnId(pTxnNum);
						offenderObj.setTxnEntrySeq(pTxnEntrySeq);
						offenderObj.setCaseloadId(objOffTransactions.getCaseloadId());
						offenderObj.setOffenderId(objOffTransactions.getNbtOffenderId().longValue());	
						offenderObj.setOffenderBookId(crOffBookId.longValue());
						offenderObj.setTxnPostingType("CR");
						offenderObj.setTxnType(objOffTransactions.getTxnType());
						offenderObj.setTxnEntryDesc(adjustDesc);
						offenderObj.setTxnEntryAmount(objOffTransactions.getTxnEntryAmount().doubleValue());
						offenderObj.setTxnEntryDate(transDate);
						offenderObj.setSubAccountType(pCrSubAccountType);
						offenderObj.setToSubAccountType(pCrSubAccountType);
						offenderObj.setSlipPrintedFlag("N");
						offenderObj.setPreWithholdAmount(null);
						offenderObj.setDeductionFlag(null);
						offenderObj.setPayeeCorporateId(null);
						offenderObj.setPayeePersonId(null);
						offenderObj.setDeductionType(null);
						offenderObj.setInfoNumber(null);
						offenderObj.setCreateUserId(objOffTransactions.getCreateUserId());				
						retrunData=trustService.insertIntoOffenderTrans(offenderObj);					
						
						if (retrunData == 0) {
							liReturn.setSealFlag("H");
							return liReturn;
						}
						//Procedure call
                    GlTransactions objOffTransactions3=new GlTransactions();
                    
                    objOffTransactions3.setTxnPostUsage("CR");
                    objOffTransactions3.setAccountCode(objOffTransactions.getAccountCodeTwo());
					objOffTransactions3.setAcntPosting(pCrAccntPosting);
					objOffTransactions3.setCaseloadId(objOffTransactions.getCaseloadId());
					objOffTransactions3.setTxnType(objOffTransactions.getTxnType());
					objOffTransactions3.setTxnEntryAmount(objOffTransactions.getTxnEntryAmount());
					objOffTransactions3.setTxnId(pTxnNum.longValue());
					objOffTransactions3.setTxnEntryDate(transDate);
					objOffTransactions3.setTxnEntryDesc(adjustDesc);
					objOffTransactions3.setTxnEntrySeq(pTxnEntrySeq.longValue());
					objOffTransactions3.setGlEntrySeq(pGlSeq.longValue());
					objOffTransactions3.setOffenderId( objOffTransactions.getNbtOffenderId());
					objOffTransactions3.setOffenderBookId(crOffBookId);
					objOffTransactions3.setInfoNumber(null);
					objOffTransactions3.setPayeePersonId(null);
					objOffTransactions3.setPayeeCorporateId(null);
					objOffTransactions3.setPayeeNameText(null);
					objOffTransactions3.setReversedTxnId(null);
					objOffTransactions3.setReversedGlEntrySeq(null);
					objOffTransactions3.setReversedTxnEntrySeq(null);
					objOffTransactions3.setTxnReferenceNumber(objOffTransactions.getTxnReferenceNumber());
					objOffTransactions3.setOffDeductionId(null);					
					objOffTransactions3.setCreateUserId(commitBean.getCreateUserId());
					trustService.insertGlTransNew(objOffTransactions3);
       
						
						if (retrunData == 0) {
							liReturn.setSealFlag("F");
							return liReturn;
						}
						pGlSeq = pGlSeq + 1;
						//Procedure call
						
						GlTransactions objOffTransactions4=new GlTransactions();
						
						objOffTransactions4.setTxnPostUsage("DR");
						objOffTransactions4.setAccountCode(objOffTransactions.getAccountCodeOne());
						objOffTransactions4.setAcntPosting(pDrAccntPosting);
						objOffTransactions4.setCaseloadId(objOffTransactions.getCaseloadId());
						objOffTransactions4.setTxnType(objOffTransactions.getTxnType());
						objOffTransactions4.setTxnEntryAmount(objOffTransactions.getTxnEntryAmount());
						objOffTransactions4.setTxnId(pTxnNum.longValue());
						objOffTransactions4.setTxnEntryDate(transDate);
						objOffTransactions4.setTxnEntryDesc(adjustDesc);
						objOffTransactions4.setTxnEntrySeq(pTxnEntrySeq.longValue());
						objOffTransactions4.setGlEntrySeq(pGlSeq.longValue());
						objOffTransactions4.setOffenderId( objOffTransactions.getNbtOffenderId());
						objOffTransactions4.setOffenderBookId(crOffBookId);
						objOffTransactions4.setInfoNumber(null);
						objOffTransactions4.setPayeePersonId(null);
						objOffTransactions4.setPayeeCorporateId(null);
						objOffTransactions4.setPayeeNameText(null);
						objOffTransactions4.setReversedTxnId(null);
						objOffTransactions4.setReversedGlEntrySeq(null);
						objOffTransactions4.setReversedTxnEntrySeq(null);
						objOffTransactions4.setTxnReferenceNumber(objOffTransactions.getTxnReferenceNumber());
						objOffTransactions4.setOffDeductionId(null);					
						objOffTransactions4.setCreateUserId(commitBean.getCreateUserId());
						trustService.insertGlTransNew(objOffTransactions4);
			
						
						if (retrunData == 0) {
							liReturn.setSealFlag("F");
							return liReturn;
						}
						try {
							//Procedure call
							Map<String, Object> financialDoDuctionsFinancialObj = new HashMap<String, Object>();
							//Procedure call
							financialDoDuctionsFinancialObj = financialService.doDeductionsFinancial(objOffTransactions.getCaseloadId(), objOffTransactions.getNbtOffenderId().longValue(), 
									crOffBookId.longValue(), objOffTransactions.getTxnType(), pTxnNum.longValue(),
									transDate, pCrSubAccountType, "Y", objOffTransactions.getTxnEntryAmount(), null, null,pTxnEntrySeq , commitBean.getCreateUserId());
													
							
							
						} catch (Exception e) {
							logger.error("financial.Do_Ductions_Financial", e);
						}
						liReturn.setSealFlag("Z");
						liReturn.setTxnId(Long.valueOf(pTxnNum.toString()));
					}
					if ((objOffTransactions.getOffenderId() != null)
							&& (objOffTransactions.getNbtOffenderId() != null)) {
						if (objOffTransactions.getTxnEntryDesc() != null) {
							adjustDesc = objOffTransactions.getTxnEntryDesc();
						} else {
							adjustDesc = "ADJUST ACCOUNT";
						}
						AccountCodes drAccountCodes = otdaaccoRepository
								.drAccountCodesData(objOffTransactions.getAccountCodeOne());
						if (drAccountCodes != null) {
							pDrSubAccountType = drAccountCodes.getSubAccountType();
							pDrAccntPosting = drAccountCodes.getTxnPostingType();
						}
						AccountCodes crAccountCodes = otdaaccoRepository
								.crAccountCodesData(objOffTransactions.getAccountCodeTwo());
						if (crAccountCodes != null) {
							pCrSubAccountType = crAccountCodes.getSubAccountType();
							pCrAccntPosting = crAccountCodes.getTxnPostingType();
						}
						BigDecimal offBookIdTemp = otdaaccoRepository.maxOffenderBookId(
								objOffTransactions.getCaseloadId(), objOffTransactions.getOffenderId());
						if (offBookIdTemp != null) {
							drOffBookId = offBookIdTemp;
						} else {
							liReturn.setSealFlag("D");
							return liReturn;
						}
						BigDecimal offBookIdTempCR = otdaaccoRepository.maxOffenderBookId(
								objOffTransactions.getCaseloadId(), objOffTransactions.getNbtOffenderId());
						if (offBookIdTempCR != null) {
							crOffBookId = offBookIdTempCR;
						} else {
							liReturn.setSealFlag("D");
							return liReturn;
						}
						pTxnNum = otdaaccoRepository.txnIdNextValData();
						pTxnEntrySeq = 1;
						pGlSeq = 1;
						/*	retrunData = otdaaccoRepository.insertIntoOffenderTransaction(pTxnNum, pTxnEntrySeq,
									objOffTransactions.getCaseloadId(), objOffTransactions.getOffenderId(), drOffBookId,
									"DR", objOffTransactions.getTxnType(), adjustDesc,
									objOffTransactions.getTxnEntryAmount(), transDate, pDrSubAccountType,
									objOffTransactions.getReconClearFlag());*/
						OffenderTransactions offObj=new OffenderTransactions();
			
						offObj.setTxnId(pTxnNum);
						offObj.setTxnEntrySeq(pTxnEntrySeq);
						offObj.setCaseloadId(objOffTransactions.getCaseloadId());
						offObj.setOffenderId(objOffTransactions.getOffenderId().longValue());	
					    offObj.setOffenderBookId(drOffBookId.longValue());
					    offObj.setTxnPostingType("DR");
						offObj.setTxnType(objOffTransactions.getTxnType());
						offObj.setTxnEntryDesc(adjustDesc);
						offObj.setTxnEntryAmount(objOffTransactions.getTxnEntryAmount().doubleValue());
						offObj.setTxnEntryDate(transDate);
						offObj.setSubAccountType(pCrSubAccountType);
						offObj.setSlipPrintedFlag("N");
						offObj.setPreWithholdAmount(null);
						offObj.setDeductionFlag(null);
						offObj.setPayeeCorporateId(null);
						offObj.setPayeePersonId(null);
						offObj.setDeductionType(null);
						offObj.setInfoNumber(null);
						offObj.setCreateUserId(commitBean.getCreateUserId());
						
						retrunData=trustService.insertIntoOffenderTrans(offObj);
						if (retrunData == 0) {
							liReturn.setSealFlag("I");
							return liReturn;
						}
						pTxnEntrySeq = pTxnEntrySeq + 1;
						//Procedure call	
						OffenderTransactions offenderObj=new OffenderTransactions();
						offenderObj.setTxnId(pTxnNum);
						offenderObj.setTxnEntrySeq(pTxnEntrySeq);
						offenderObj.setCaseloadId(objOffTransactions.getCaseloadId());
						offenderObj.setOffenderId(objOffTransactions.getNbtOffenderId().longValue());	
						offenderObj.setOffenderBookId(crOffBookId.longValue());
						offenderObj.setTxnPostingType("CR");
						offenderObj.setTxnType(objOffTransactions.getTxnType());
						offenderObj.setTxnEntryDesc(adjustDesc);
						offenderObj.setTxnEntryAmount(objOffTransactions.getTxnEntryAmount().doubleValue());
						offenderObj.setTxnEntryDate(transDate);
						offenderObj.setSubAccountType(pCrSubAccountType);
						offenderObj.setToSubAccountType(pCrSubAccountType);
						offenderObj.setSlipPrintedFlag("N");
						offenderObj.setPreWithholdAmount(null);
						offenderObj.setDeductionFlag(null);
						offenderObj.setPayeeCorporateId(null);
						offenderObj.setPayeePersonId(null);
						offenderObj.setDeductionType(null);
						offenderObj.setInfoNumber(null);
						offenderObj.setCreateUserId(commitBean.getCreateUserId());				
						retrunData=trustService.insertIntoOffenderTrans(offenderObj);					
						
						
						if (retrunData == 0) {
							liReturn.setSealFlag("J");
							return liReturn;
						}
						pTxnEntrySeq = 1;
						//Procedure call
						GlTransactions objOffTransactions5=new GlTransactions();
						/*objOffTransactions5.setDspTxnPostingType("DR");*/
						objOffTransactions5.setTxnPostUsage("DR");
						objOffTransactions5.setAccountCode(objOffTransactions.getAccountCodeOne());
						objOffTransactions5.setAcntPosting(pDrAccntPosting);
						objOffTransactions5.setCaseloadId(objOffTransactions.getCaseloadId());
						objOffTransactions5.setTxnType(objOffTransactions.getTxnType());
						objOffTransactions5.setTxnEntryAmount(objOffTransactions.getTxnEntryAmount());
						objOffTransactions5.setTxnId(pTxnNum.longValue());
						objOffTransactions5.setTxnEntryDate(transDate);
						objOffTransactions5.setTxnEntryDesc(adjustDesc);
						objOffTransactions5.setTxnEntrySeq(pTxnEntrySeq.longValue());
						objOffTransactions5.setGlEntrySeq(pGlSeq.longValue());
						objOffTransactions5.setOffenderId( objOffTransactions.getOffenderId());
						objOffTransactions5.setOffenderBookId(drOffBookId);
						objOffTransactions5.setInfoNumber(null);
						objOffTransactions5.setPayeePersonId(null);
						objOffTransactions5.setPayeeCorporateId(null);
						objOffTransactions5.setPayeeNameText(null);
						objOffTransactions5.setReversedTxnId(null);
						objOffTransactions5.setReversedGlEntrySeq(null);
						objOffTransactions5.setReversedTxnEntrySeq(null);
						objOffTransactions5.setTxnReferenceNumber(objOffTransactions.getTxnReferenceNumber());
						objOffTransactions5.setOffDeductionId(null);					
						objOffTransactions5.setCreateUserId(commitBean.getCreateUserId());
						trustService.insertGlTransNew(objOffTransactions5);
						
						if (retrunData == 0) {
							liReturn.setSealFlag("F");
							return liReturn;
						}
						pGlSeq = pGlSeq + 1;
						//Procedure call
						GlTransactions objOffTransactions6=new GlTransactions();
						
						objOffTransactions6.setTxnPostUsage("CR");
						objOffTransactions6.setAccountCode(objOffTransactions.getAccountCodeTwo());
						objOffTransactions6.setAcntPosting(pCrAccntPosting);
						objOffTransactions6.setCaseloadId(objOffTransactions.getCaseloadId());
						objOffTransactions6.setTxnType(objOffTransactions.getTxnType());
						objOffTransactions6.setTxnEntryAmount(objOffTransactions.getTxnEntryAmount());
						objOffTransactions6.setTxnId(pTxnNum.longValue());
						objOffTransactions6.setTxnEntryDate(transDate);
						objOffTransactions6.setTxnEntryDesc(adjustDesc);
						objOffTransactions6.setTxnEntrySeq(pTxnEntrySeq.longValue());
						objOffTransactions6.setGlEntrySeq(pGlSeq.longValue());
						objOffTransactions6.setOffenderId( objOffTransactions.getNbtOffenderId());
						objOffTransactions6.setOffenderBookId(crOffBookId);
						objOffTransactions6.setInfoNumber(null);
						objOffTransactions6.setPayeePersonId(null);
						objOffTransactions6.setPayeeCorporateId(null);
						objOffTransactions6.setPayeeNameText(null);
						objOffTransactions6.setReversedTxnId(null);
						objOffTransactions6.setReversedGlEntrySeq(null);
						objOffTransactions6.setReversedTxnEntrySeq(null);
						objOffTransactions6.setTxnReferenceNumber(objOffTransactions.getTxnReferenceNumber());
						objOffTransactions6.setOffDeductionId(null);					
						objOffTransactions6.setCreateUserId(commitBean.getCreateUserId());
						trustService.insertGlTransNew(objOffTransactions6);
						
						if (retrunData == 0) {
							liReturn.setSealFlag("F");
							return liReturn;
						}
						pTxnEntrySeq = pTxnEntrySeq + 1;
						try {
							//Procedure call
							Map<String, Object> financialDoDuctionsFinancialObj = new HashMap<String, Object>();
							
							financialDoDuctionsFinancialObj = financialService.doDeductionsFinancial(objOffTransactions.getCaseloadId(), objOffTransactions.getNbtOffenderId().longValue(), 
									crOffBookId.longValue(), objOffTransactions.getTxnType(), pTxnNum.longValue(),
									transDate, pCrSubAccountType, "Y", objOffTransactions.getTxnEntryAmount(), null, null,pTxnEntrySeq , commitBean.getCreateUserId());
													
						} catch (Exception e) {
							logger.error("financial.Do_Ductions_Financial", e);
						}
						liReturn.setSealFlag("Z");
						liReturn.setTxnId(Long.valueOf(pTxnNum.toString()));
					}
					if (objOffTransactions.getOffenderId() != null) {
						//Procedure call
						String setIndDate = deductionsService.getAcAndSetIndDate(objOffTransactions.getOffenderId().longValue(), objOffTransactions.getCaseloadId(), commitBean.getCreateUserId());
						if (setIndDate!=null && !"GETINDDATE".equals(setIndDate)) {
							liReturn.setSealFlag("K");
							return liReturn;
						}
					}
					if (objOffTransactions.getNbtOffenderId() != null) {
						//Procedure call
						String setIndDate =deductionsService.getAcAndSetIndDate(objOffTransactions.getNbtOffenderId().longValue(), objOffTransactions.getCaseloadId(), commitBean.getCreateUserId());
						if (setIndDate!=null && !"GETINDDATE".equals(setIndDate)) {
							liReturn.setSealFlag("K");
							return liReturn;
						}
					}
				}
			} catch (Exception e) {
				logger.error("glTxn1Commit : ", e);
			}
		}
		liReturn.setGlEntrySeq(1L);
		return liReturn;
	}

	private GlTransactions validateOvrdraft(final BigDecimal offenderId, final String caseloadId,
			final BigDecimal txnEntryAmount, final BigDecimal accountCodeOne, final String reconClearFlag,
			String txnType,String userName) {
		GlTransactions returnData = new GlTransactions();
		
		String creditOblFlag = null;
		String subAccountType = null;
		Integer txnEntrySeq = 1;
		String checkInd = "Y";
		Integer txnFee = 0;
		Integer txnId = null;
		BigDecimal offenderBookId = null;
			
		BigDecimal overDraft = otdaaccoRepository.overDraftAmount(caseloadId, offenderId, accountCodeOne,
				txnEntryAmount);
		String overDraftTotalAmount = otdaaccoRepository.overDraftAmountTotal(caseloadId, offenderId, accountCodeOne);
		
		Double overDraftDouble = Double.valueOf(overDraft.toString());
		returnData.setAccountCode(overDraft);
		if (overDraftDouble != null ) {
			if (overDraftDouble < 0.0) {
				String creditObligation = otdaaccoRepository.transactionTypesY(txnType, caseloadId);
				
				if (creditObligation != null) {
					creditOblFlag = creditObligation;
				}
				String subAcntType = otdaaccoRepository.subAccountType(accountCodeOne);
				if (subAcntType != null) {
					subAccountType = subAcntType;
				}
				BigDecimal maxOffenderBookId = otdaaccoRepository.maxOffenderBookId(caseloadId, offenderId);
				if (maxOffenderBookId != null) {
					offenderBookId = maxOffenderBookId;
				}
				if ("Y".equals(creditOblFlag)) {
					txnId = otdaaccoRepository.txnIdNextValData();
					Map<String, Object> chkOverDrawn =trustService.chkOverdrawn(caseloadId, offenderId, subAccountType, 
							txnEntryAmount, txnType, txnEntrySeq.longValue(), checkInd, "OTDAACCO", BigDecimal.valueOf(txnId),
							offenderBookId, txnFee,null,userName);
					
					if (chkOverDrawn != null) {
						returnData.setSealFlag("A");
						returnData.setCaseloadId("Y");
						return returnData;
					}
				} else {
					returnData.setSealFlag("B");
					returnData.setCaseloadId("Y");
					return returnData;
				}
			}
		}
		 else {
			returnData.setSealFlag("C");
			return returnData;
		}

		return returnData;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<SystemProfiles> systemProfilesExecuteQuery(final SystemProfiles searchRecord) {
		return otdaaccoRepository.systemProfilesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYSTEM_PROFILES
	 *
	 */
	@Transactional
	public Integer systemProfilesCommit(final SystemProfilesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = otdaaccoRepository.systemProfilesInsertSystemProfiles(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = otdaaccoRepository.systemProfilesUpdateSystemProfiles(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkGlTxn1TxnTypeRecordGroup(final String caseLoadId, final String caseLoadType) {
		return otdaaccoRepository.cgfkGlTxn1TxnTypeRecordGroup(caseLoadId, caseLoadType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkGlTxnAccountCodeRecordGroup(final String txnType, final String caseLoadId,
			final String caseLoadType) {
		return otdaaccoRepository.cgfkGlTxnAccountCodeRecordGroup(txnType, caseLoadId, caseLoadType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkGlTxn1AccountCodeRecordGroup() {
		return otdaaccoRepository.cgfkGlTxn1AccountCodeRecordGroup();

	}

	public String checkNavigation(final BigDecimal accountCode) {
		return otdaaccoRepository.checkNavigation(accountCode);
	}

	public String chkOffAcnt(final BigDecimal accountCode) {
		return otdaaccoRepository.chkOffAcnt(accountCode);
	}

	public Integer whenValidateItem(final GlTransactions bean) {
		Integer returnData = null;
		if ("A".equals(bean.getSealFlag())) {
			if (bean.getOffenderId() != null) {
				String currentCaseload = otdaaccoRepository.currentCaseloadValidation(bean.getOffenderId(),
						bean.getCaseloadId(), bean.getAccountCodeOne());
				if (currentCaseload == null) {
					returnData = 1;
					return returnData;
				}
				String closedAccount = otdaaccoRepository.closedAccountValidation(bean.getOffenderId(),
						bean.getCaseloadId());
				if ("Y".equals(closedAccount)) {
					returnData = 2;
					return returnData;
				}
			}
		}
		if (bean.getNbtOffenderId() != null) {
			String closedAccount = otdaaccoRepository.closedAccountValidation(bean.getNbtOffenderId(),
					bean.getCaseloadId());
			if ("Y".equals(closedAccount)) {
				returnData = 2;
				return returnData;
			}
			if (bean.getOffenderId().equals(bean.getNbtOffenderId())) {
				returnData = 3;
				return returnData;
			}
			String currentCaseload = otdaaccoRepository.currentCaseloadValidation(bean.getNbtOffenderId(),
					bean.getCaseloadId(), bean.getAccountCodeTwo());
			if (currentCaseload == null) {
				returnData = 4;
				return returnData;
			}
		}
		if (bean.getAccountCodeOne() != null) {
			String chkNavigation = otdaaccoRepository.checkNavigation(bean.getAccountCodeOne());
			if ("Y".equals(chkNavigation)) {
				if (bean.getOffenderId() == null) {
					returnData = 5;
					return returnData;
				}
			}
		}
		if (bean.getAccountCodeTwo() != null) {
			String chkNavigation = otdaaccoRepository.checkNavigation(bean.getAccountCodeTwo());
			if ("Y".equals(chkNavigation)) {
				if (bean.getNbtOffenderId() == null) {
					returnData = 5;
					return returnData;
				}
			}
		}
		return returnData;
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
	public Integer chkInvalidAccounts(final BigDecimal accountCodeOne, final BigDecimal accountCodeTwo,
			final String caseloadId) {
		Integer liReturn = null;
		if (accountCodeOne != null && accountCodeTwo != null) {
			String drAccountCodeFlag = otdaaccoRepository.drAccountCode(accountCodeOne);
			Integer drAcntCaseloadFlag = otdaaccoRepository.drAccountCodeCaseloadId(caseloadId, accountCodeTwo);
			if ((drAcntCaseloadFlag == 1) && "Y".equals(drAccountCodeFlag)) {
				liReturn = 1;
				return liReturn;
			} else if (drAcntCaseloadFlag > 1) {
				liReturn = 1;
				return liReturn;
			} else {

			}
			String crAccountCodeFlag = otdaaccoRepository.drAccountCode(accountCodeTwo);
			Integer crAcntCaseloadFlag = otdaaccoRepository.drAccountCodeCaseloadId(caseloadId, accountCodeOne);
			if ("Y".equals(crAccountCodeFlag) && (crAcntCaseloadFlag == 1)) {
				liReturn = 2;
				return liReturn;
			} else if (crAcntCaseloadFlag > 1) {
				liReturn = 2;
				return liReturn;
			}
			String crAccountCodeTempFlag = otdaaccoRepository.crAccountCode(accountCodeTwo);
			if ("Y".equals(drAccountCodeFlag) && "Y".equals(crAccountCodeTempFlag)) {
				liReturn = 3;
				return liReturn;
			}
			String drAccountCodeTempFlag = otdaaccoRepository.crAccountCode(accountCodeOne);
			if ("Y".equals(crAccountCodeFlag) && "Y".equals(drAccountCodeTempFlag)) {
				liReturn = 3;
				return liReturn;
			}
		}
		return liReturn;
	}

	@Override
	public String whenCheckBoxChecked(String caseloadId, BigDecimal offfenderId, String txnType) {
		return otdaaccoRepository.whenCheckBoxChecked(caseloadId, offfenderId, txnType);
	}

}