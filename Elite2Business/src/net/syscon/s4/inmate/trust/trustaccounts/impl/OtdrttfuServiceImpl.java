package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderTrustTransfers;
import net.syscon.s4.inmate.beans.OffenderTrustTransfersCommitBean;
import net.syscon.s4.inmate.trust.trustaccounts.OtdrttfuRepository;
import net.syscon.s4.inmate.trust.trustaccounts.OtdrttfuService;
import net.syscon.s4.pkgs.deductions.DeductionsService;
import net.syscon.s4.pkgs.financial.FinancialService;
import net.syscon.s4.pkgs.trust.TrustService;

/**
 * Class OtdrttfuServiceImpl
 */
@Service
public class OtdrttfuServiceImpl extends BaseBusiness implements OtdrttfuService {

	@Autowired
	private OtdrttfuRepository otdrttfuRepository;

	@Autowired
	private EliteDateService eliteDateService;

	@Autowired
	private TrustService trustService;

	@Autowired
	private FinancialService financialService;

	@Autowired
	private DeductionsService deductionsService;

	/**
	 * Creates new OtdrttfuServiceImpl class Object
	 */
	public OtdrttfuServiceImpl() {
		// OtdrttfuServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<Caseloads> cgfkchkOffTtOffTtCsld(final Caseloads paramBean) {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<Object> cgwhenNewFormInstance() {
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderTrustTransfers> offTtExecuteQuery(final OffenderTrustTransfers searchRecord) {
		String desc = null;
		Integer checkNo;
		final List<OffenderTrustTransfers> returnList = otdrttfuRepository.offTtExecuteQuery(searchRecord);
		if (returnList.size() > 0) {
			for (final OffenderTrustTransfers obj : returnList) {
				desc = otdrttfuRepository.getfromCaseloadDesc(obj.getFromCaseload());
				obj.setDspDescription(desc);

				checkNo = otdrttfuRepository.getTheCheckNo(obj.getFromCaseload(), obj.getTxnId());
				if (checkNo == 0) {
					obj.setCheckNo(checkNo);
				} else {
					obj.setCheckNo(checkNo);
				}

			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_TT
	 *
	 */
	@Transactional
	public List<OffenderTrustTransfers> offTtCommit(final OffenderTrustTransfersCommitBean commitBean) {
		List<OffenderTrustTransfers> returnList = new ArrayList<OffenderTrustTransfers>();
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OffenderTrustTransfers obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			returnList = offTtInsertOffenderTrustTransfers(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderTrustTransfers obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			returnList = offTtInsertOffenderTrustTransfers(commitBean.getUpdateList());
		}
		return returnList;
	}

	private List<OffenderTrustTransfers> offTtInsertOffenderTrustTransfers(
			final List<OffenderTrustTransfers> insertList) {
		List<OffenderTrustTransfers> returnList = new ArrayList<OffenderTrustTransfers>();
		Integer returnval = 0;
		Integer txnseq = 0;
		BigDecimal txnId = BigDecimal.ZERO;
		for (OffenderTrustTransfers obj : insertList) {
			final Integer val = otdrttfuRepository.updateOffenderTrustTransfers(obj);
			if (obj.getTxnId() == BigDecimal.ZERO) {
				txnId = otdrttfuRepository.getNextVal();
				obj.setTxnId(txnId);
			}

			final List<OffenderTransactions> oftxns = otdrttfuRepository.getSubAcntOffIdtxnAmtbkgId(obj);
			txnId = otdrttfuRepository.getNextVal();
			obj.setTxnId(txnId);
			for (final OffenderTransactions oftxn : oftxns) {
				obj.setSubAcntType(oftxn.getSubAccountType());
				obj.setOffenderId(oftxn.getOffenderId());
				obj.setOffenderBookId(oftxn.getOffenderBookId());
				obj.setTxnEntryAmnt(oftxn.getTxnEntryAmount());
				if (oftxn.getSubAccountType() != null) {
					final OffenderTransactions data = otdrttfuRepository.getTxnTypeDescTxnUsageRecProdFlag(obj);
					if (data == null) {
						returnval = 2;
					}
					if (data.getTxnUsage() != null) {
						obj.setTxnType(data.getTxnType());
						obj.setTxndesc(data.getTxnEntryDesc());

						obj.setTxnUsage(data.getTxnUsage());
					}

				}

				final String acStatus = otdrttfuRepository.getAcStatus(obj.getToCaseload(), obj.getOffenderId());
				if (acStatus == null) {
					obj.setAcntClosedFlag("NONE");
					obj.setAcntClosedFlag("X");
				} else {
					obj.setAcntClosedFlag(acStatus);
				}
				if ("CLOSED".equals(obj.getAcntClosedFlag())) {
					otdrttfuRepository.updateOffenderTrustTransfersWithClosedFlagN(obj);
				} else if ("X".equals(obj.getAcntClosedFlag())) {
					if ("X".equals(obj.getAcntClosedFlag())) {
						Integer insertTrustacnt = otdrttfuRepository.insertOffenderTrustAcounts(obj);
						Integer insertSubacnt = otdrttfuRepository.insertOffenderSubAccounts(obj);
						//Date vdate = otdrttfuRepository.createDefaultDeductions(obj);
						Date vdate = deductionsService.createDefaultDeductions(obj.getCaseloadId(), obj.getOffenderId().intValue(), obj.getCreateUserId());
						if (vdate != null) {
							obj.setTxnEntryDate(vdate);
						}
					}

				}
				if (obj.getTxnEntrySeq() == null) {
					obj.setTxnEntrySeq(0);
				}
				obj.setTxnEntrySeq(obj.getTxnEntrySeq() + 1);

				if ("OPEN".equals(obj.getAcntClosedFlag())) {
					obj.setAcntClosedFlag("Y");
				}
				if (oftxn.getTxnEntryAmount() > 0 && "R".equals(obj.getTxnUsage())
						&& "Y".equals(obj.getAcntClosedFlag())) {
					if ("R".equals(obj.getTxnUsage())) {
						Integer reciept = otdrttfuRepository.genTrustRcptNmbr("SEQUENCE_" + obj.getCaseloadId());
						if (reciept != null) {
							String recieptNum = reciept.toString();
							while (recieptNum.length() < 6) {
								recieptNum = "0" + recieptNum;
							}
							obj.setReceiptNumber(obj.getCaseloadId() + recieptNum);
						}

					} else {
						if ("D".equals(obj.getTxnUsage())) {
							Integer reciept = otdrttfuRepository
									.genTrustRcptNmbr("SEQUENCE_" + obj.getCaseloadId() + "_D");
							if (reciept != null) {
								String recieptNum = reciept.toString();
								while (recieptNum.length() < 6) {
									recieptNum = "0" + recieptNum;
								}
								obj.setReceiptNumber(obj.getCaseloadId() + recieptNum);
							}

						}
					}
				}
				returnval = insertintoOffenderTrans(obj);

			}
		}

		if (returnval != 1) {
			final OffenderTrustTransfers offTransError = new OffenderTrustTransfers();
			offTransError.setErrorMessage("Error");
			final List<OffenderTrustTransfers> offTransErrorList = new ArrayList<OffenderTrustTransfers>();
			offTransErrorList.add(offTransError);
			return offTransErrorList;
		}

		return insertList;
	}

	private Integer insertintoOffenderTrans(final OffenderTrustTransfers obj) {
		final String desc = " from ";
		final String txnDesc = obj.getTxndesc() + desc + obj.getToCaseload();
		obj.setTxndesc(txnDesc);
		final Integer transcmt = otdrttfuRepository.insertintoOffenderTrans(obj);
		if (transcmt == 1) {
			obj.setTxnEntryDate(trunc(eliteDateService.getDBTime()));
			// otdrttfuRepository.updateOffenderBalance(obj);
			OffenderTransactions offenderTransactions = new OffenderTransactions();
			offenderTransactions.setCaseloadId(obj.getCaseloadId());
			offenderTransactions.setOffenderId(obj.getOffenderId());
			offenderTransactions.setTxnPostingType("CR");
			offenderTransactions.setTxnEntryDate(obj.getTxnEntryDate());
			offenderTransactions.setTxnId(obj.getTxnId().intValue());
			offenderTransactions.setTxnType(obj.getTxnType());
			offenderTransactions.setTxnEntryAmount(obj.getTxnEntryAmnt());
			offenderTransactions.setToSubAccountType(obj.getSubAcntType());
			// Procedure call
			trustService.updateOffenderBalance(offenderTransactions, obj.getCreateUserId());
		} else {
			return 3;// Unable to insert record into table OFFENDER_TRANSACTIONS
		}
		// final Integer glNew=otdrttfuRepository.processGlTransNew(obj);
		// Procedure call
		final Integer glNew = trustService.processGlTransNew(obj.getCaseloadId(), obj.getTxnType(), null,
				obj.getTxnEntryAmnt(), obj.getTxnId().intValue(), obj.getTxnEntryDate(), obj.getTxndesc(),
				obj.getTxnEntrySeq(), "OTDRTTFU", obj.getOffenderId().intValue(), obj.getOffenderBookId(), null,
				obj.getSubAcntType(), null, null, null, 0, null, obj.getCreateUserId());
//		if(glNew ==1){
//			return 1;
//		}
		String profileVal = otdrttfuRepository.getProfileVal();

		if (obj.getTxnEntryAmnt() > 0 && "REG".equals(obj.getSubAcntType()) && "N".equals(profileVal)) {
			// Integer dofinacount= otdrttfuRepository.doDeductionsFinancial(obj);
			// Procedure call
			Map<String, Object> dofinacount = new HashMap<String, Object>();
			dofinacount = financialService.doDeductionsFinancial(obj.getToCaseload(), obj.getOffenderId(),
					obj.getOffenderBookId(), obj.getTxnType(), obj.getTxnId().longValue(), obj.getTxnEntryDate(),
					obj.getSubAcntType(), "Y", new BigDecimal(obj.getTxnEntryAmnt()), null, null, obj.getTxnEntrySeq(),
					obj.getCreateUserId());
		}
		// otdrttfuRepository.deductionGetAcAndSetIndDate(obj.getOffenderId(),
		// obj.getCaseloadId());
		// Procedure call
		deductionsService.getAcAndSetIndDate(obj.getOffenderId(), obj.getCaseloadId(), obj.getCreateUserId());

		return glNew;
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

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderTransactions> offTxnExecuteQuery(final OffenderTransactions searchRecord) {
		final List<OffenderTransactions> returnList = otdrttfuRepository.offTxnExecuteQuery(searchRecord);
		if (returnList != null) {
			for (final OffenderTransactions obj : returnList) {
				// final Long obkgId=
				// otdrttfuRepository.getBookId(obj.getOffenderId());
				final OffenderTransactions beandata = otdrttfuRepository.getLastNameFirstNames(obj.getOffenderId(),
						searchRecord.getCaseloadType());
				if (beandata != null) {
					obj.setFirstName(beandata.getFirstName());
					obj.setLastName(beandata.getLastName());
					obj.setOffenderIdDisplay(beandata.getOffenderIdDisplay());
					String activeFlag = otdrttfuRepository.getActiveFlg(beandata.getOffenderBookId());
					if (activeFlag != null) {
						obj.setActiveFlag(activeFlag);
					}
				}
				String acStatus = otdrttfuRepository.getAcStatus(searchRecord.getToCaseloadId(), obj.getOffenderId());
				if (acStatus == null) {
					obj.setAccountClosedFlag("NONE");
				} else {
					obj.setAccountClosedFlag(acStatus);
				}
			}
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_TXN
	 *
	 */
	@Transactional
	public Integer offTxnCommit(final OffenderTransactionsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = otdrttfuRepository.offTxnInsertOffenderTransactions(commitBean.getInsertList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = otdrttfuRepository.offTxnDeleteOffenderTransactions(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return otdrttfuRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean commitBean) {
		return 0;
	}

}