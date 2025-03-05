package net.syscon.s4.sa.admin.mergeoffenders.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.pkgs.merge_transacation_request.MergeTransactionRequestService;
import net.syscon.s4.pkgs.transfer_booking_core.TransferBookingCoreRepository;
import net.syscon.s4.pkgs.transfer_booking_core.TransferBookingCoreService;
import net.syscon.s4.pkgs.transfer_booking_trust.TransferBookingTrustService;
import net.syscon.s4.sa.admin.beans.VMergeTransactionProcesses;
import net.syscon.s4.sa.admin.mergeoffenders.OummerofRepository;
import net.syscon.s4.sa.admin.mergeoffenders.OumtrnbkRepository;
import net.syscon.s4.sa.admin.mergeoffenders.OumtrnbkService;
import net.syscon.s4.sa.recordmaintenance.MergeProcesses;
import net.syscon.s4.sa.recordmaintenance.MergeTransactionBean;
import net.syscon.s4.sa.recordmaintenance.MergeTransactionProcessesCommitBean;

/**
 * Class OumtrnbkServiceImpl
 */
@Service
public class OumtrnbkServiceImpl extends BaseBusiness implements OumtrnbkService {

	@Autowired
	private OumtrnbkRepository oumtrnbkRepository;
	
	@Autowired
	private OummerofRepository oummerofRepository;
	
	@Autowired
	private TransferBookingCoreService transferBookingsCoreService;
	
	@Autowired
	private TransferBookingCoreRepository transferBookingCoreRepository;
	
	@Autowired
	private TransferBookingTrustService transferBookingTrustService;
	
	@Autowired
	private MergeTransactionRequestService mergeTransactionRequestService;
	
	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<MergeProcesses> IsProcessTimeRequired(MergeProcesses paramBean) {
		return oumtrnbkRepository.isProcessTimeRequired(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<MergeProcesses> IsProcessDefaultTransfer(MergeProcesses paramBean) {
		return oumtrnbkRepository.isProcessDefaultTransfer(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	@Transactional(rollbackFor = Exception.class)
	public List<VMergeTransactionProcesses> mrgProcExecuteQuery(MergeTransactionBean bean) {
		VMergeTransactionProcesses searchBean = new VMergeTransactionProcesses();
		Integer trnsBkgReq = mergeTransactionRequestService.createTransferBkgRequest(bean);
		searchBean.setpFromOffBookId(bean.getpFromOffBookId());
		searchBean.setpFromRootOffId(bean.getpFromRootOffId());
		Long prvBookId = transferBookingsCoreService.getPrvBookId(bean.getpFromOffBookId(), bean.getpFromRootOffId().longValue());
		Long nextBookId = transferBookingsCoreService.getNextBookId(bean.getpFromOffBookId(), bean.getpFromRootOffId().longValue());
		List<VMergeTransactionProcesses> returnList = oumtrnbkRepository.mergeProcExecuteQuery(bean.getpMergeTransactionId());
		for (VMergeTransactionProcesses vmtpBean : returnList) {
			if ("Y".equals(vmtpBean.getDefaultOnFlag()) || "Y".equals(vmtpBean.getMandatoryOnFlag())) {
				vmtpBean.setTransferFlag("Y");
				if ("Y".equals(vmtpBean.getTransferFlag()) && "Y".equals(vmtpBean.getTimeframeFlag())) {
					vmtpBean.setBeginDate(transferBookingsCoreService.getBookingStartDate(bean.getpFromOffBookId()));
					vmtpBean.setBeginTime(transferBookingsCoreService.getBookingStartDate(bean.getpFromOffBookId()));
					vmtpBean.setEndDate(transferBookingsCoreService.getBookingEndDate(bean.getpFromOffBookId()));
					vmtpBean.setEndTime(transferBookingsCoreService.getBookingEndDate(bean.getpFromOffBookId()));
					vmtpBean.setPrevBkgEndDate(transferBookingsCoreService.getBookingEndDate(prvBookId));
					vmtpBean.setNextBkgStartDate(transferBookingsCoreService.getBookingStartDate(nextBookId));
				}
			}
		}
		
		
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstMRG_PROC
	 *
	 * @throws SQLException
	 */
	@Transactional(rollbackFor = Exception.class)
	public Integer mrgProcCommit(MergeTransactionProcessesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			commitBean.getInsertList().forEach(mergeTransProcs -> {
				mergeTransProcs.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = oumtrnbkRepository.mrgProcInsertVMergeTransactionProcesses(commitBean.getInsertList());

		}
		
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			liReturn = oumtrnbkRepository.mrgProcDeleteVMergeTransactionProcesses(commitBean.getDeleteList());
		}
		
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			liReturn = oumtrnbkRepository.updateMergeTransProc(commitBean.getUpdateList());
		}
		return liReturn;
	}

     
	public Integer chkOffendersForTransfer(final MergeTransactionBean bean) {
		//Boolean result = true;
		Integer result = 0;
		Boolean validateResult = true;
		Long pFromOffRootId = bean.getpFromRootOffId().longValue();
		Long pToOffRootId = bean.getpToRootOffId().longValue();
		Long pFromOffBookId =  bean.getpFromOffBookId();
		Long pToOffBookId = bean.getpToOffBookId();
		bean.setpMergeTransactionId(oummerofRepository.getMergeTransactionID());
		Long pMergeTransactionId = bean.getpMergeTransactionId().longValue();
		Boolean oneBookingOnly = isOneBookingOnly(pFromOffRootId);
		Boolean bothBookingsActive = isBothBookingsActive(pFromOffBookId, pToOffRootId);
		Boolean inactiveAfterActive = isInactiveAfterActive(pFromOffRootId, pFromOffBookId, pToOffRootId, pToOffBookId);
		
		if(pFromOffRootId.equals(pToOffRootId) ) {
			result = 1;
		}else if(oneBookingOnly) {
			result = 2;
		}else if(bothBookingsActive) {
			result = 3;
		}else if(inactiveAfterActive) {
			result = 4;
		}else if(pMergeTransactionId != null) {
			validateResult = transferBookingTrustService.validateBeforeTransfer(pFromOffRootId,
					pFromOffBookId, pToOffRootId, pToOffBookId, pMergeTransactionId);
			if(!validateResult) {
				result = 5;
			}
		}
		return result;
	}
	
	public Boolean isOneBookingOnly(Long pFromOffRootId) {
		Integer lvCount = null;
		lvCount = oumtrnbkRepository.countOffBookings(pFromOffRootId);
		if(lvCount > 1) {
			return false;
		}else {
			return true;
		}
	}
	
	public Boolean isBothBookingsActive(Long pFromOffBookId, Long pToOffRootId) {
		Integer lvFromActive;
		Integer lvToActive;
		Boolean lvResult;
		lvFromActive = oumtrnbkRepository.getInstBookActive(pFromOffBookId);
		lvToActive = oumtrnbkRepository.getToBookActive(pToOffRootId);
		if(lvFromActive > 0 && lvToActive > 0 ) {
			lvResult = true;
		}else {
			lvResult = false;
		}
		return lvResult;
	} 
	
	
	public Boolean isInactiveAfterActive(Long pFromOffRootId, Long pFromOffBookId, Long pToOffRootId, Long pToOffBookId) {
		Date lvFromOffBookDate = null;
		Date lvToOffBookDate = null;
		Integer lvExistsActive = 0;
		lvFromOffBookDate = transferBookingsCoreService.getBookingStartDate(pFromOffBookId);
		lvToOffBookDate = transferBookingsCoreService.getBookingStartDate(pToOffBookId);
		if (lvFromOffBookDate.compareTo(lvToOffBookDate) > 0) {
			lvExistsActive = oumtrnbkRepository.getActiveBookingCur(pToOffBookId);//S4-24590
		} else if (lvFromOffBookDate.compareTo(lvToOffBookDate) < 0) {
			lvExistsActive = oumtrnbkRepository.getActiveBookingCur(pFromOffBookId);
		}
		if (lvExistsActive > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	

	public String processTransferTransaction(final MergeTransactionBean bean) {
		
		return mergeTransactionRequestService.processTrnBkgRequest(bean);
		
	}

	@Override
	public List<VMergeTransactionProcesses> mrgProcExecuteQueryRet(MergeTransactionBean bean) {
		List<VMergeTransactionProcesses> returnList = oumtrnbkRepository.mergeProcExecuteQuery(bean.getpMergeTransactionId());
		for (VMergeTransactionProcesses vmtpBean : returnList) {
			if("Initialize Transfer Booking".equalsIgnoreCase(vmtpBean.getProcessDescription().trim())) {
				vmtpBean.setTransferFlag("Y");
			}
			if("Transfer OMS".equalsIgnoreCase(vmtpBean.getProcessDescription().trim())) {
				vmtpBean.setTransferFlag("Y");
			}
			if (vmtpBean.getBeginDate() == null && vmtpBean.getBeginTime() == null && 
					("Transfer Medical".equalsIgnoreCase(vmtpBean.getProcessDescription().trim()) || "Transfer Trust".equalsIgnoreCase(vmtpBean.getProcessDescription().trim()))){
				vmtpBean.setTransferFlag("N");
			}else {
				vmtpBean.setTransferFlag("Y");
			}
		}
		return returnList;
	}
}