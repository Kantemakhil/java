package net.syscon.s4.inmate.trust.generalledger.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.GlTransactionsCommitBean;
import net.syscon.s4.inmate.trust.generalledger.OcdcashrRepository;
import net.syscon.s4.inmate.trust.generalledger.OcdcashrService;
import net.syscon.s4.pkgs.trust.TrustService;
import net.syscon.s4.triggers.GlTransactionsT1Service;
import net.syscon.s4.triggers.GlTransactionsT2Service;
import net.syscon.s4.triggers.GlTransactionsTjnService;
import net.syscon.s4.triggers.SystemProfilesBkadmTrgService;
import net.syscon.s4.triggers.SystemProfilesT1Service;
import net.syscon.s4.triggers.SystemProfilesTjnService;
import net.syscon.s4.triggers.TrustAudits;

/**
 * Class OcdcashrServiceImpl
 */
@Service
public class OcdcashrServiceImpl extends BaseBusiness implements OcdcashrService {

	@Autowired
	private OcdcashrRepository ocdcashrRepository;

	@Autowired
	private EliteDateService eliteDateService;
	
	@Autowired
	private SystemProfilesTjnService systemProfilesTjnService;
	
	@Autowired
	private SystemProfilesT1Service systemProfilesT1Service;
	
	@Autowired
	private SystemProfilesBkadmTrgService systemProfilesBkadmTrgService;
	
	@Autowired
	private GlTransactionsTjnService glTransactionsTjnService;
	
	@Autowired
	private GlTransactionsT2Service glTransactionsT2Service;
	
	@Autowired
	private GlTransactionsT1Service glTransactionsT1Service;
	
	@Autowired
	private TrustService trustService;
	
	
	

	/**
	 * Creates new OcdcashrServiceImpl class Object
	 */
	public OcdcashrServiceImpl() {
		// OcdcashrServiceImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<GlTransactions> glTxnExecuteQuery(final GlTransactions searchRecord) {
		List<GlTransactions> returnList = ocdcashrRepository.glTxnExecuteQuery(searchRecord);
		for (final GlTransactions glTransactions : returnList) {
			String receiptNumber = ocdcashrRepository.receiptNumber(glTransactions.getTxnId(),
					glTransactions.getTxnEntrySeq());
			if (receiptNumber != null) {
				glTransactions.setReceiptNumber(receiptNumber);
			}
			String offenderIdDisp = ocdcashrRepository.offenderIdDisplayData(glTransactions.getOffenderId());
			if (offenderIdDisp != null) {
				glTransactions.setOffenderIdDisplay(offenderIdDisp);
			}
			if ("DR".equals(glTransactions.getTxnPostUsage())) {
				glTransactions.setAccountCodeOne(glTransactions.getAccountCode());
			} else {
				glTransactions.setAccountCodeTwo(glTransactions.getAccountCode());
			}
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstGL_TXN
	 *
	 * 
	 */
	@Transactional
	public GlTransactions glTxnCommit(final GlTransactionsCommitBean commitBean) {
		GlTransactions liReturn = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (GlTransactions obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				TrustAudits  newRef = new TrustAudits(); 
				try {
					BeanUtils.copyProperties(newRef, obj);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				glTransactionsT1Service.glTransactionsT1Trigger(newRef);
			}
			liReturn = glTxnInsertGlTransactions(commitBean.getInsertList());
			for (GlTransactions obj : commitBean.getInsertList()) {
				Date transDate = trunc(eliteDateService.getDBTime());
				obj.setTxnEntryDate(transDate);
				//glTransactionsTjnService.glTransactionsTjnTrigger(obj, "INSERTING");
				glTransactionsT2Service.glTransactionsT2Trigger(obj);
			}
		}
		return liReturn;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @return Integer
	 * @Param commitBean
	 */
	@Transactional
	public GlTransactions glTxnInsertGlTransactions(final List<GlTransactions> lstOffenderAlerts) {
		GlTransactions liReturnData = new GlTransactions();
		for (GlTransactions glTransactions : lstOffenderAlerts) {
			Double txnAmt = null;
			Double amtEnt = Double.valueOf(glTransactions.getCgnbtPayeeNameTextOne().toString());
			if (glTransactions.getCgnbtPayeeNameTextTwo() != null) {
				txnAmt = Double.valueOf(glTransactions.getCgnbtPayeeNameTextTwo().toString());
				if (glTransactions.getCgnbtPayeeNameTextOne() != null && glTransactions.getAccountCode() != null) {
					BigDecimal txnEntryAmount = ocdcashrRepository.txnEntryAmountData(amtEnt, txnAmt);
					glTransactions.setTxnEntryAmount(txnEntryAmount);
				}
			}
			String modName = "OCDCASHR";
			String varTxnType = null;
			String varTxnDesc = null;
			Date transDate = trunc(eliteDateService.getDBTime());
			try {
				List<TransactionTypes> returnData = ocdcashrRepository.txnTypeDescriptionData(modName,
						glTransactions.getCaseloadId(), glTransactions.getCaseloadType());
				if (returnData.size() > 1) {
					liReturnData.setSealFlag("A");
					return liReturnData;
				} else if (returnData.size() == 1) {
					for (TransactionTypes transactionTypes : returnData) {
						varTxnType = transactionTypes.getTxnType();
						varTxnDesc = transactionTypes.getDescription();
					}
				} else {
					liReturnData.setSealFlag("B");
					return liReturnData;
				}
			} catch (Exception e) {
				liReturnData.setSealFlag("C");
				return liReturnData;
			}
			if ("Y".equals(glTransactions.getReconClearFlag())) {
				glTransactions.setReconClearFlag("Y");
			} else {
				glTransactions.setReconClearFlag("N");
			}
			if (amtEnt != null && txnAmt != null) {
				if (amtEnt.equals(txnAmt)) {
					GlTransactions zeroCashDet = zeroCash(glTransactions.getTxnEntryAmount(), varTxnType, varTxnDesc,
							glTransactions.getCaseloadId(), glTransactions.getAccountCode(), transDate, glTransactions.getCreateUserId());
					if (zeroCashDet != null) {
						liReturnData.setSealFlag(zeroCashDet.getSealFlag());
						liReturnData.setTxnId(zeroCashDet.getTxnId());
						liReturnData.setAccountCodeOne(zeroCashDet.getAccountCodeOne());
						liReturnData.setTxnEntryDesc(zeroCashDet.getTxnEntryDesc());
						return liReturnData;
					}
				} else if (amtEnt > txnAmt) {
					GlTransactions overageDet = overage(glTransactions.getTxnEntryAmount(), varTxnType, varTxnDesc,
							glTransactions.getCaseloadId(), glTransactions.getAccountCode(), transDate,
							glTransactions.getReconClearFlag(), glTransactions.getCreateUserId());
					if (overageDet != null) {
						liReturnData.setSealFlag(overageDet.getSealFlag());
						if ("Y".equals(overageDet.getSealFlag())) {
							liReturnData.setTxnId(overageDet.getTxnId());
						} else {
							String amount = glTransactions.getTxnEntryAmount().toString();
							String txnAmount = txnAmountDataSlashes(amount);
							liReturnData.setTxnPostUsage(txnAmount);
						}
						liReturnData.setAccountCodeOne(overageDet.getAccountCodeOne());
						liReturnData.setTxnEntryDesc(overageDet.getTxnEntryDesc());
						return liReturnData;
					}
				} else if (amtEnt < txnAmt) {
					GlTransactions shortage = shortage(glTransactions.getTxnEntryAmount(), varTxnType, varTxnDesc,
							glTransactions.getCaseloadId(), glTransactions.getAccountCode(), transDate,
							glTransactions.getReconClearFlag(), glTransactions.getCreateUserId());
					if (shortage != null) {
						liReturnData.setSealFlag(shortage.getSealFlag());
						if ("Y".equals(shortage.getSealFlag())) {
							liReturnData.setTxnId(shortage.getTxnId());
						} else {
							String amount = glTransactions.getTxnEntryAmount().toString();
							String txnAmount = txnAmountDataSlashes(amount);
							liReturnData.setTxnPostUsage(txnAmount);
						}
						liReturnData.setAccountCodeOne(shortage.getAccountCodeOne());
						liReturnData.setTxnEntryDesc(shortage.getTxnEntryDesc());
						return liReturnData;
					}
				}
			}
		}
		return liReturnData;
	}

	private GlTransactions zeroCash(BigDecimal txnEntryAmount, String varTxnType, String varTxnDesc, String caseloadId,
			BigDecimal acCode, Date transDate, String userName) {
		GlTransactions glTransactions = new GlTransactions();
		String postType = null;
		String postTypeOne = null;
		BigDecimal nbtAcCode = ocdcashrRepository.bankCrAccountCodeData(acCode, caseloadId);
		if (nbtAcCode != null) {
			glTransactions.setAccountCodeOne(nbtAcCode);
		} else {
			glTransactions.setSealFlag("D");
			return glTransactions;
		}
		AccountCodes accountNameTxnPosting = ocdcashrRepository.accountNameTxnPostingType(nbtAcCode);
		if (accountNameTxnPosting != null) {
			glTransactions.setTxnEntryDesc(accountNameTxnPosting.getAccountName());
			postType = accountNameTxnPosting.getaTxnPostingType();
		} else {
			glTransactions.setSealFlag("D");
			return glTransactions;
		}
		AccountCodes txnPostingType = ocdcashrRepository.txnPostingTypeData(acCode);
		if (txnPostingType != null) {
			postTypeOne = txnPostingType.getTxnPostingType();
		} else {
			glTransactions.setSealFlag("D");
			return glTransactions;
		}
		Integer txnIdNextVal = ocdcashrRepository.txnIdNextValData();
		try {
			
			 /* ocdcashrRepository.insertGlTransNew("DR", acCode, postTypeOne, caseloadId,
			 * varTxnType, txnEntryAmount, txnIdNextVal, transDate, varTxnDesc, 1, 1, null,
			 * null, null);
			 */
			GlTransactions obj = new GlTransactions();
			obj.setTxnPostUsage("DR");
			obj.setAccountCode(acCode);
			obj.setAcntPosting(postTypeOne);
			obj.setCaseloadId(caseloadId);
			obj.setTxnType(varTxnType);
			obj.setTxnEntryAmount(txnEntryAmount);
			obj.setTxnId(txnIdNextVal.longValue());
			obj.setTxnEntryDate(transDate);
			obj.setTxnEntryDesc(varTxnDesc);
			obj.setTxnEntrySeq(1L);
			obj.setGlEntrySeq(1L);
			obj.setOffenderId(null);
			obj.setOffenderBookId(null);
			obj.setTxnReferenceNumber(null);
			obj.setCreateUserId(userName);
			//Procedure call
			trustService.insertGlTransNew(obj);
			/*
			 * ocdcashrRepository.insertGlTransNew("CR", nbtAcCode, postType, caseloadId,
			 * varTxnType, txnEntryAmount, txnIdNextVal, transDate, varTxnDesc, 1, 2, null,
			 * null, null);
			 */
			GlTransactions obj1 = new GlTransactions();
			obj1.setTxnPostUsage("CR");
			obj1.setAccountCode(nbtAcCode);
			obj1.setAcntPosting(postType);
			obj1.setCaseloadId(caseloadId);
			obj1.setTxnType(varTxnType);
			obj1.setTxnEntryAmount(txnEntryAmount);
			obj1.setTxnId(txnIdNextVal.longValue());
			obj1.setTxnEntryDate(transDate);
			obj1.setTxnEntryDesc(varTxnDesc);
			obj1.setTxnEntrySeq(1L);
			obj1.setGlEntrySeq(2L);
			obj1.setOffenderId(null);
			obj1.setOffenderBookId(null);
			obj1.setTxnReferenceNumber(null);
			obj1.setCreateUserId(userName);
			//Procedure call
			trustService.insertGlTransNew(obj1);
			glTransactions.setTxnId(Long.valueOf(txnIdNextVal));
		} catch (Exception e) {
			glTransactions.setSealFlag("E");
			return glTransactions;
		}
		glTransactions.setSealFlag(null);
		return glTransactions;
	}

	private GlTransactions overage(BigDecimal txnEntryAmount, String varTxnType, String varTxnDesc, String caseloadId,
			BigDecimal acCode, Date transDate, String reconClearFlag, String userName) {
		GlTransactions glTransactions = new GlTransactions();
		String postType = null;
		String postTypeOne = null;
		BigDecimal nbtAcCode = ocdcashrRepository.crAccountCodeData(acCode, caseloadId);
		if (nbtAcCode != null) {
			glTransactions.setAccountCodeOne(nbtAcCode);
		} else {
			glTransactions.setSealFlag("D");
		}
		AccountCodes accountNameTxnPosting = ocdcashrRepository.accountNameTxnPostingType(nbtAcCode);
		if (accountNameTxnPosting != null) {
			glTransactions.setTxnEntryDesc(accountNameTxnPosting.getAccountName());
			postType = accountNameTxnPosting.getaTxnPostingType();
		} else {
			glTransactions.setSealFlag("D");
		}
		AccountCodes txnPostingType = ocdcashrRepository.txnPostingTypeData(acCode);
		if (txnPostingType != null) {
			postTypeOne = txnPostingType.getTxnPostingType();
		} else {
			glTransactions.setSealFlag("D");
		}
		if ("Y".equals(reconClearFlag)) {
			Integer txnIdNextVal = ocdcashrRepository.txnIdNextValData();
			try {
				/*
				 * ocdcashrRepository.insertGlTransNew("DR", acCode, postTypeOne, caseloadId,
				 * varTxnType, txnEntryAmount, txnIdNextVal, transDate, varTxnDesc, 1, 1, null,
				 * null, null);
				 */
				GlTransactions obj = new GlTransactions();
				obj.setTxnPostUsage("DR");
				obj.setAccountCode(acCode);
				obj.setAcntPosting(postTypeOne);
				obj.setCaseloadId(caseloadId);
				obj.setTxnType(varTxnType);
				obj.setTxnEntryAmount(txnEntryAmount);
				obj.setTxnId(txnIdNextVal.longValue());
				obj.setTxnEntryDate(transDate);
				obj.setTxnEntryDesc(varTxnDesc);
				obj.setTxnEntrySeq(1L);
				obj.setGlEntrySeq(1L);
				obj.setOffenderId(null);
				obj.setOffenderBookId(null);
				obj.setTxnReferenceNumber(null);
				obj.setCreateUserId(userName);
				//Procedure call
				trustService.insertGlTransNew(obj);
				/*
				 * ocdcashrRepository.insertGlTransNew("CR", nbtAcCode, postType, caseloadId,
				 * varTxnType, txnEntryAmount, txnIdNextVal, transDate, varTxnDesc, 1, 2, null,
				 * null, null);
				 */
				GlTransactions obj1 = new GlTransactions();
				obj1.setTxnPostUsage("CR");
				obj1.setAccountCode(nbtAcCode);
				obj1.setAcntPosting(postType);
				obj1.setCaseloadId(caseloadId);
				obj1.setTxnType(varTxnType);
				obj1.setTxnEntryAmount(txnEntryAmount);
				obj1.setTxnId(txnIdNextVal.longValue());
				obj1.setTxnEntryDate(transDate);
				obj1.setTxnEntryDesc(varTxnDesc);
				obj1.setTxnEntrySeq(1L);
				obj1.setGlEntrySeq(2L);
				obj1.setOffenderId(null);
				obj1.setOffenderBookId(null);
				obj1.setTxnReferenceNumber(null);
				obj1.setCreateUserId(userName);
				//Procedure call
				trustService.insertGlTransNew(obj1);
				glTransactions.setTxnId(Long.valueOf(txnIdNextVal));
				glTransactions.setSealFlag("Y");
			} catch (Exception e) {
				glTransactions.setSealFlag("E");
			}
		} else {
			glTransactions.setSealFlag("X");
		}
		return glTransactions;
	}

	private GlTransactions shortage(BigDecimal txnEntryAmount, String varTxnType, String varTxnDesc, String caseloadId,
			BigDecimal acCode, Date transDate, String reconClearFlag, String userName) {
		GlTransactions glTransactions = new GlTransactions();
		String postType = null;
		String postTypeOne = null;
		BigDecimal nbtAcCode = ocdcashrRepository.drAccountCodeData(acCode, caseloadId);
		if (nbtAcCode != null) {
			glTransactions.setAccountCodeOne(nbtAcCode);
		} else {
			glTransactions.setSealFlag("D");
		}
		AccountCodes accountNameTxnPosting = ocdcashrRepository.accountNameTxnPostingType(nbtAcCode);
		if (accountNameTxnPosting != null) {
			glTransactions.setTxnEntryDesc(accountNameTxnPosting.getAccountName());
			postType = accountNameTxnPosting.getaTxnPostingType();
		} else {
			glTransactions.setSealFlag("D");
		}
		AccountCodes txnPostingType = ocdcashrRepository.txnPostingTypeData(acCode);
		if (txnPostingType != null) {
			postTypeOne = txnPostingType.getTxnPostingType();
		} else {
			glTransactions.setSealFlag("D");
		}
		if ("Y".equals(reconClearFlag)) {
			Integer txnIdNextVal = ocdcashrRepository.txnIdNextValData();
			try {
				/*
				 * ocdcashrRepository.insertGlTransNew("DR", acCode, postTypeOne, caseloadId,
				 * varTxnType, txnEntryAmount, txnIdNextVal, transDate, varTxnDesc, 1, 1, null,
				 * null, null);
				 */
				GlTransactions obj = new GlTransactions();
				obj.setTxnPostUsage("DR");
				obj.setAccountCode(acCode);
				obj.setAcntPosting(postTypeOne);
				obj.setCaseloadId(caseloadId);
				obj.setTxnType(varTxnType);
				obj.setTxnEntryAmount(txnEntryAmount);
				obj.setTxnId(txnIdNextVal.longValue());
				obj.setTxnEntryDate(transDate);
				obj.setTxnEntryDesc(varTxnDesc);
				obj.setTxnEntrySeq(1L);
				obj.setGlEntrySeq(1L);
				obj.setOffenderId(null);
				obj.setOffenderBookId(null);
				obj.setTxnReferenceNumber(null);
				obj.setCreateUserId(userName);
				//Procedure call
				trustService.insertGlTransNew(obj);
				/*
				 * ocdcashrRepository.insertGlTransNew("CR", nbtAcCode, postType, caseloadId,
				 * varTxnType, txnEntryAmount, txnIdNextVal, transDate, varTxnDesc, 1, 2, null,
				 * null, null);
				 */
				GlTransactions obj1 = new GlTransactions();
				obj1.setTxnPostUsage("CR");
				obj1.setAccountCode(nbtAcCode);
				obj1.setAcntPosting(postType);
				obj1.setCaseloadId(caseloadId);
				obj1.setTxnType(varTxnType);
				obj1.setTxnEntryAmount(txnEntryAmount);
				obj1.setTxnId(txnIdNextVal.longValue());
				obj1.setTxnEntryDate(transDate);
				obj1.setTxnEntryDesc(varTxnDesc);
				obj1.setTxnEntrySeq(1L);
				obj1.setGlEntrySeq(2L);
				obj1.setOffenderId(null);
				obj1.setOffenderBookId(null);
				obj1.setTxnReferenceNumber(null);
				obj1.setCreateUserId(userName);
				//Procedure call
				trustService.insertGlTransNew(obj1);
				glTransactions.setTxnId(Long.valueOf(txnIdNextVal));
				glTransactions.setSealFlag("Y");
			} catch (Exception e) {
				glTransactions.setSealFlag("E");
			}
		} else {
			glTransactions.setSealFlag("Z");
		}
		return glTransactions;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstGL_TXN1
	 *
	 * 
	 */
	@Transactional
	public Integer glTxn1Commit(final GlTransactionsCommitBean CommitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return ocdcashrRepository.sysPflExecuteQuery(searchRecord);

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
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (SystemProfiles obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdcashrRepository.sysPflInsertSystemProfiles(commitBean.getInsertList());
			for (SystemProfiles obj : commitBean.getInsertList()) {
				SystemProfiles old =ocdcashrRepository.getOldRecords(obj);
				//systemProfilesTjnService.systemProfilesTjn(old, obj,"INSERTING");
			}
			//systemProfilesBkadmTrgService.
			// TODO this is from audit
			/* SystemProfilesBkadmTrg trigger related to dbms
			 * dbms_scheduler.set_attribute(
		                name => 'BKADM_QUEUE_SCHEDULE'
		               ,attribute => 'repeat_interval'
		               ,value => 'FREQ=SECONDLY; INTERVAL=' || lv_value);
			  */
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (SystemProfiles obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdcashrRepository.sysPflDeleteSystemProfiles(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<AccountCodes> cgfkGlTxnAccountCodeRecordGroup(String caseloadId, String caseloadType) {
		List<AccountCodes> returnList = ocdcashrRepository.cgfkGlTxnAccountCodeRecordGroup(caseloadId, caseloadType);
		for (AccountCodes accountCodes : returnList) {
			accountCodes.setCode(accountCodes.getAccountCode().toString());
		}
		return returnList;

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
	public String accountCodeChangeEvent(final String caseloadId, final String caseloadType,
			final BigDecimal accountCode, final String userName) {
		String returnAmount = null;
		String lvMultiCash = ocdcashrRepository.profileValData();
		String amountDet = ocdcashrRepository.txnAmountData(caseloadId, caseloadType, accountCode, lvMultiCash, userName);
		if (amountDet != null) {
			String amountSlash = txnAmountDataSlashes(amountDet);
			if (amountSlash != null) {
				returnAmount = amountSlash;
			}
		} else {
			returnAmount = "Y";
		}
		return returnAmount;
	}

	@Override
	public String txnAmountDataSlashes(final String txnEntryAmount) {
		return ocdcashrRepository.txnAmountDataSlashes(txnEntryAmount);
	}
}