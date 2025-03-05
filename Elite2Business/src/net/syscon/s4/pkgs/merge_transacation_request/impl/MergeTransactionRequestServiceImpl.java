package net.syscon.s4.pkgs.merge_transacation_request.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.pkgs.merge_log.MergeLogService;
import net.syscon.s4.pkgs.merge_offender_core.MergeOffenderCoreService;
import net.syscon.s4.pkgs.merge_transacation_request.MergeTransactionRequestRepository;
import net.syscon.s4.pkgs.merge_transacation_request.MergeTransactionRequestService;
import net.syscon.s4.sa.admin.beans.MergeTransactions;
import net.syscon.s4.sa.recordmaintenance.MergeTransactionBean;

@Service
public class MergeTransactionRequestServiceImpl implements MergeTransactionRequestService {
	
	private static Logger logger = LogManager.getLogger(MergeTransactionRequestServiceImpl.class.getName());

	@Autowired
	private MergeTransactionRequestRepository mergetxnreqrepo;
	
	@Autowired
	private MergeTransactionRequestService mergeTransactionRequestService;

	@Autowired
	private MergeOffenderCoreService mergeOffenderCoreService;
	
	@Autowired
	private MergeLogService mergeLogService;
	
	
	@Override
	public Long manualCreateRequest(MergeTransactions bean) {
		Long mergeTransactionId = mergetxnreqrepo.getMergeTransactionsId();
		bean.setMergeTransactionId(mergeTransactionId);
		Integer count = mergetxnreqrepo.insertMergeTransactions(bean);
		setStatusToInprogress(mergeTransactionId, bean.getRequestStatusCode());;
		return null;
	}
	
	@Override
	public void setStatusToInprogress(Long mergeTransactionId, String requestStatusCode) {
		setStatus(mergeTransactionId, requestStatusCode);
	}

	@Override
	public Integer setStatus(Long mergeTransactionId, String requestStatusCode) {
		Integer setStatus = mergetxnreqrepo.setStatus(mergeTransactionId, requestStatusCode);
		return setStatus;
		
	}

	@Override
	public Integer setStatusToCompleted(Long mergeTransactionId, String requestStatusCode) {

		return setStatus(mergeTransactionId, requestStatusCode);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String processTrnBkgRequest(MergeTransactionBean bean) {
		Integer liReturn = 0;
		Boolean result = null;
		List<MergeTransactionBean> saveList = new ArrayList<>();
		bean.setRequestStatusCode("PENDING");
		logger.info(this.getClass().getName() + " getMergeTransactionID");
		bean.setTransactionSource("MANUAL");
		saveList.add(bean);
		mergeTransactionRequestService.setStatusToInprogress(bean.getpMergeTransactionId().longValue(), "INPROGRESS");
		try {
			result = mergeOffenderCoreService.mergeOffenders(bean.getpMergeTransactionId().longValue(),
					bean.getpFromRootOffId().longValue(), bean.getpFromOffBookId(), bean.getpToRootOffId().longValue(),
					bean.getpToOffBookId(), "MANUAL", "TRANSFER", bean.getCreateUserId());
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method mergeOffenders", e);
		}
		if (result == true) {
			liReturn = mergeTransactionRequestService.setStatusToCompleted(bean.getpMergeTransactionId().longValue(), "COMPLETED");
		} else {
			mergeLogService.error("Process transfer booking request: ERROR : Update Transfer Transaction Request",
					bean.getpMergeTransactionId().longValue(), bean.getCreateUserId());
			mergeTransactionRequestService.setStatusToFailed(bean.getpMergeTransactionId().longValue(), "FAILED");
		}
		if (liReturn == 1 && result == true) {
			return "success";
		} else {
			return "fail";
		}
	}

	@Override
	public Integer createTransferBkgRequest(MergeTransactionBean bean) {
		Integer mergeTransactionId = mergetxnreqrepo.getMergeTransactionsId().intValue();
		bean.setpMergeTransactionId(mergeTransactionId);
		Integer insertMrgTransactions = mergetxnreqrepo.insertMrgTransactions(bean);
		return insertMrgTransactions;
	}
	
	@Override
	public void setStatusToFailed(Long mergeTransactionId, String requestStatusCode) {
		setStatus(mergeTransactionId, requestStatusCode);
	}
	
}
