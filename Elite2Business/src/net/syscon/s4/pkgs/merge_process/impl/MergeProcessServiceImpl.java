package net.syscon.s4.pkgs.merge_process.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.MergeProcessRules;
import net.syscon.s4.pkgs.merge_context.MergeContextService;
import net.syscon.s4.pkgs.merge_log.MergeLogService;
import net.syscon.s4.pkgs.merge_offender_oms.MergeOffenderOmsService;
import net.syscon.s4.pkgs.merge_offender_trust.MergeOffenderTrustRepository;
import net.syscon.s4.pkgs.merge_offender_trust.MergeOffenderTrustService;
import net.syscon.s4.pkgs.merge_process.MergeProcessRepository;
import net.syscon.s4.pkgs.merge_process.MergeProcessService;
import net.syscon.s4.pkgs.merge_transacation_request.MergeTransactionRequestRepository;
import net.syscon.s4.pkgs.transfer_booking_oms.TransferBookingOmsService;
import net.syscon.s4.sa.admin.beans.MergeTransactions;
import net.syscon.s4.sa.recordmaintenance.MergeProcesses;

@Service
public class MergeProcessServiceImpl implements MergeProcessService {

	private static Logger logger = LogManager.getLogger(MergeProcessServiceImpl.class.getName());

	@Autowired
	private MergeProcessRepository mergeProcessRepository;

	@Autowired
	private MergeTransactionRequestRepository mergeTransactionRequestRepository;

	@Autowired
	private MergeContextService mergeContextService;
	
	@Autowired
	private MergeOffenderOmsService mergeOffenderOmsService;
	
	@Autowired
	private MergeOffenderTrustService mergeOffenderTrustService;
	
	@Autowired
	private TransferBookingOmsService transferBookingOmsService;
	
	@Autowired
	private MergeLogService mergeLogService;
	
	
	
//	@Transactional(rollbackFor = Exception.class)
	@Override
	public Boolean initProc(Offenders fromOffenders,Offenders toOffenders,Long pMergeTxnId, String pTxnType, String user) {
		MergeTransactions MergeTransactions = new MergeTransactions();
		pMergeTxnId = pMergeTxnId;
		String cFunc = "initProc";
		boolean lResult;
		boolean lDummyRes = false;
		boolean lvTurnedOn;
		Long processId = null;
		Long lProcessId = getStartProcessId(pTxnType);
		MergeProcesses lMpRec = getMergeProcessesInfo(lProcessId, pMergeTxnId, user);
		processId = new Long(lMpRec.getProcessId());
		while (lMpRec != null && processId != null) {
			if (lMpRec.getActiveFlag() != null && lMpRec.getActiveFlag().equals("Y")) {
				lvTurnedOn = checkTransferRuleTurnedOn(pMergeTxnId, lProcessId);
				if (lvTurnedOn || lMpRec.getTransactionType().equals("MERGE") || lMpRec.getTransactionType().equals("TRANSFER")) {
					checkTooManyProcExecs(processId);
					if (lMpRec.getInitProc() != null) {
						lDummyRes = executeProc("PROCEDURE", lMpRec.getInitProc(),pMergeTxnId, fromOffenders,toOffenders, user);
					}
					if (lMpRec.getFirstRuleId() != null) {
						initProcRules(lMpRec.getFirstRuleId().longValue(),pMergeTxnId, fromOffenders,toOffenders, user);
					}
					if (lMpRec.getNextCheckProc() != null) {
						executeProc(lMpRec.getNextCheckProcType(), lMpRec.getNextCheckProc(), pMergeTxnId, fromOffenders,toOffenders, user);
					} else {
						lResult = true;
					}

					if (lMpRec.getTermProc() != null) {
						lDummyRes = executeProc("PROCEDURE", lMpRec.getTermProc(), pMergeTxnId, fromOffenders,toOffenders, user);
					}
				} else if (lMpRec.getFirstRuleIdOff() != null) {
					initProcRules(lMpRec.getFirstRuleIdOff().longValue(),pMergeTxnId,  fromOffenders,toOffenders, user);
				}
				lResult = true;
			} else {
				lResult = true;
			}

			if (lMpRec.getEndingProcessFlag() != null && lMpRec.getEndingProcessFlag().equals("Y")) {
				mergeLogService.debug(cFunc.concat("Ending merge process set as Ending_Process_Flag = 'Y'"), pMergeTxnId, user);
			} else if (lMpRec.getResultTrueNextId() == null && lMpRec.getResultFalseNextId() == null) {
				mergeLogService.debug(cFunc.concat("Ending merge process set as Result_True/False_Next_Id''s are NULL.'"), pMergeTxnId, user);
			}
			if (("Y".equalsIgnoreCase(lMpRec.getEndingProcessFlag()) 
					|| lMpRec.getResultTrueNextId() == null) && lMpRec.getResultFalseNextId() == null) {
				break;
			}

			if (lResult == true) {
				lMpRec = getMergeProcessesInfo(lMpRec.getResultTrueNextId().longValue(), pMergeTxnId, user);
			} else {
				lMpRec = getMergeProcessesInfo(lMpRec.getResultFalseNextId().longValue(), pMergeTxnId, user);
			}
		}
		return lDummyRes;
	}

	@Override
	public Long getStartProcessId(String transactionType) {
		Long startProcessId = null;
		try {
			startProcessId = mergeProcessRepository.getStartProcessId(transactionType);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getStartProcessId", e);
		}
		return startProcessId;
	}

	@Override
	public MergeProcesses getMergeProcessesInfo(Long processId, Long pMergeTxnId,String user) {
		String cFunc = "merge_offenders_table";
		MergeProcesses mergeProcessesInfo = new MergeProcesses();
		try {
			mergeProcessesInfo = mergeProcessRepository.getMergeProcessesInfo(processId);
			String cFuncS = cFunc.concat(": ProcessId =").concat(String.valueOf(mergeProcessesInfo.getProcessId()))
					.concat(", FirstRuleId =").concat(String.valueOf(mergeProcessesInfo.getFirstRuleId()))
					.concat(", ResultTrueNextId =").concat(String.valueOf(mergeProcessesInfo.getResultTrueNextId()))
					.concat(", ResultFalseNextId =").concat(String.valueOf(mergeProcessesInfo.getResultFalseNextId()))
					.concat(", ProcessName =").concat(String.valueOf(mergeProcessesInfo.getProcessName()));
			mergeLogService.debug(cFuncS, pMergeTxnId, user);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getMergeProcessesInfo", e);
		}
		return mergeProcessesInfo;
	}

	@Override
	public Boolean checkTransferRuleTurnedOn(Long mergeTransactionId, Long processId) {

		boolean lvTurnedOn = false;
		Long lvNumber = null;
		try {
			lvNumber = mergeProcessRepository.checkTransferFlag(mergeTransactionId, processId);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method checkTransferFlag", e);
		}
		if (lvNumber > 0) {
			lvTurnedOn = true;
		} else {
			lvTurnedOn = false;
		}
		return lvTurnedOn;
	}

	@Override
	public void checkTooManyProcExecs(Long processId) {

		try {
			checkTooManyExecs(null, processId);
			;
		} catch (Exception e) {
			logger.error("en_too_many_execs 'Too many Proc executions. Id: ' || p_process_id", e);
		}

	}

	@Override
	public void checkTooManyExecs(String tNumExecsTab, Long pId) {

	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean executeProc(String pProcType, String pProc,Long pMergeTxnId, Offenders fromOffenders, Offenders toOffenders, String user) {
		Boolean result = null;
		Long fromRootOffenderId = fromOffenders.getRootOffenderId().longValue();
		if (pProcType != null && pProcType.equals("PROCEDURE") && pProc.equals("merge_process_request.set_from_offender_to_off1;") ) {
			Long mergeTransactionId = pMergeTxnId;
			
			Long fromOffenderBookId = fromOffenders.getOffenderBookId();
			
			Long fromOffenderId = fromOffenders.getOffenderId();
			String fromOffenderIdDisplay = fromOffenders.getOffenderIdDisplay();
			String fromLastName = fromOffenders.getLastName();
			String fromfirstName = fromOffenders.getFirstName();
			Long toOffenderBookId = toOffenders.getOffenderBookId();
			Long toRootOffenderId = toOffenders.getRootOffenderId().longValue();
			Long toOffenderId = toOffenders.getOffenderId();
			String toOffenderIdDisplay = toOffenders.getOffenderIdDisplay();
			String toLastName = toOffenders.getLastName();
			String tofirstName = toOffenders.getFirstName();
			
			result = true;
		}
		
		if (pProcType != null && pProcType.equals("PROCEDURE") && pProc.equals("merge_transaction_request.update_merge_trn_request;") ) {

			Long mergeTransactionId = pMergeTxnId;
			Long fromOffenderBookId = fromOffenders.getOffenderBookId();
			Long fromOffenderId = fromOffenders.getOffenderId();
			String fromOffenderIdDisplay = fromOffenders.getOffenderIdDisplay();
			String fromLastName = fromOffenders.getLastName();
			String fromfirstName = fromOffenders.getFirstName();
			Long toOffenderBookId = toOffenders.getOffenderBookId();
			Long toRootOffenderId = toOffenders.getRootOffenderId().longValue();
			Long toOffenderId = toOffenders.getOffenderId();
			String toOffenderIdDisplay = toOffenders.getOffenderIdDisplay();
			String toLastName = toOffenders.getLastName();
			String tofirstName = toOffenders.getFirstName();
			
			Integer updateTransaction;
			
			updateTransaction = mergeTransactionRequestRepository.updateTransaction(mergeTransactionId,
					fromOffenderBookId, fromRootOffenderId, fromOffenderId, fromOffenderIdDisplay, fromLastName,
					fromfirstName, toOffenderBookId, toRootOffenderId, toOffenderId, toOffenderIdDisplay, toLastName,
					tofirstName);
			if(updateTransaction == 1) {
				result = true;
			}else {
				result  =false;
			}
		}
		
		if (pProcType != null && pProcType.equals("PROCEDURE") && pProc.equals("merge_offender_oms.merge_offender_records;") ) {
			
			try {
				mergeOffenderOmsService.mergeOffenderRecords(fromOffenders, toOffenders, user, pMergeTxnId );
				result = true;
			}catch(Exception e) {
				logger.error("Exception occured in " + this.getClass().getName() + " in method mergeOffenderRecords", e);
				result = false;
			}
		} 
		
		if(pProcType != null && pProcType.equals("PROCEDURE") && pProc.equals("transfer_booking_oms.transfer_booking_records;")) {
			try {
			transferBookingOmsService.transferBookingRecords(pMergeTxnId, fromOffenders, toOffenders, user);
			result = true;
			}catch(Exception e) {
				logger.error("Exception occured in " + this.getClass().getName() + " in method transferBookingRecords", e);
				result = false;
			}
			
			
		}else if (pProcType != null && pProcType.equals("FUNCTION") && pProc.equals("merge_offender_trust.merge_from_has_trust_account;")) {
			
			try {
				 result = mergeOffenderTrustService.mergeFromHasTrustAccount(fromRootOffenderId);
			} catch (Exception e) {
				logger.error("Exception occured in " + this.getClass().getName() + " in method mergeFromHasTrustAccount", e);
			}
	
		}
		return result; 
		
	}

	@Override
	public void initProcRules(Long firstRuleId,Long pMergeTxnId, Offenders fromOffenders, Offenders toOffenders, String user) {
		MergeProcessRules mergeProcessRules = new MergeProcessRules();
		Long lRuleId = mergeProcessRules.getRuleId();
		String cFunc = "initProcRules";
		boolean lResult;
		boolean lDummyRes;

		MergeProcessRules lMprRec = getMergeProcessRulesInfo(firstRuleId);

		while (lMprRec != null && lMprRec.getRuleId() != null) {
			if (lMprRec.getActiveFlag() != null && lMprRec.getActiveFlag().equals("Y")) {
				checkTooManyProcExecs(lMprRec.getRuleId());
				if (lMprRec.getInitProc() != null) {
					lDummyRes = executeProc("PROCEDURE", lMprRec.getInitProc(),pMergeTxnId, fromOffenders, toOffenders, user);
				}
				if (lMprRec.getRuleProc() != null) {
					lResult = executeProc(lMprRec.getRuleProcType(), lMprRec.getRuleProc(),pMergeTxnId, fromOffenders,  toOffenders, user);
				} else {
					lResult = true;
				}
				if (lMprRec.getTermProc() != null) {
					lDummyRes = executeProc("PROCEDURE", lMprRec.getTermProc(),pMergeTxnId, fromOffenders,  toOffenders, user);
				}
			} else {
				lResult = true;
			}

			if (lMprRec.getEndingRuleFlag() != null && lMprRec.getEndingRuleFlag().equals("Y")) {
				mergeLogService.debug(cFunc.concat(": Ending merge proces rule set as Ending_Rule_Flag = 'Y'"), pMergeTxnId, user);

			} else if (lMprRec.getResultTrueNextId() == null && lMprRec.getResultFalseNextId() == null) {
				mergeLogService.debug(cFunc.concat(": Ending merge process rule set as Result_True/False_Next_Id''s are NULL."), pMergeTxnId, user);
			}
			if ( ("Y".equalsIgnoreCase(lMprRec.getEndingRuleFlag())
					|| lMprRec.getResultTrueNextId() == null) && (lMprRec.getResultFalseNextId() == null)) {
				break;
			}
			if (lResult == true) {
				lMprRec = getMergeProcessRulesInfo(lMprRec.getResultTrueNextId());
			} else {
				lMprRec = getMergeProcessRulesInfo(lMprRec.getResultFalseNextId());
			}
		}
	}

	@Override
	public MergeProcessRules getMergeProcessRulesInfo(Long ruleId) {
		MergeProcessRules lMergeProcessRulesRow = new MergeProcessRules();
		try {
			lMergeProcessRulesRow = mergeProcessRepository.getMergeProcessRulesInfo(ruleId);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getMergeProcessRulesInfo",
					e);
		}

		return lMergeProcessRulesRow;
	}

}
