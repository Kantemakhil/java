package net.syscon.s4.pkgs.transfer_booking_trust.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.pkgs.transfer_booking_trust.TransferBookingTrustRepository;
import net.syscon.s4.pkgs.transfer_booking_trust.TransferBookingTrustService;

@Service
public class TransferBookingTrustServiceImpl implements TransferBookingTrustService{
	@Autowired
	private TransferBookingTrustRepository transferBookingTrustRepository;

	@Override
	public Boolean validateBeforeTransfer(Long pFromOffRootId, Long pFromOffBookId, Long pToOffRootId, Long pToOffBookId,
			Long pMergeTransactionId) {
		
		Date lvTrustBeginDate;
		Date lvTrustEndDate;
		Boolean result = true; 
		String pProcessName = "TRANSFER TRUST";
		lvTrustBeginDate = getBeginDate(pMergeTransactionId, pProcessName);
		lvTrustEndDate = getEndDate(pMergeTransactionId, pProcessName);
		
		if(lvTrustBeginDate != null && lvTrustEndDate != null) {
			Boolean accountInSync = isAccountInSync(pFromOffRootId);
			Boolean accountInSync2 = isAccountInSync(pToOffRootId);
			
			Integer checkAlienDedTxn = checkAlienDedTxn(pFromOffRootId, lvTrustBeginDate, lvTrustEndDate);
			Integer checkOwnDedTxn = checkOwnDedTxn(pFromOffRootId, lvTrustBeginDate, lvTrustEndDate);
			
			if(!accountInSync) {
				//EX_ACCOUNT_NOT_IN_SYNC //return false;
				result = false;
			}else if(!accountInSync2) {
				//EX_ACCOUNT_NOT_IN_SYNC
				result = false;
			}else if(checkAlienDedTxn > 0) {
				//EX_ALIEN_DED_IN_TIME_FRAME
				result = false;
			}else if(checkOwnDedTxn > 0) {
				//EX_DED_EXISTS_OUT_TIME_FRAME
				result = false;
			}
		}
		return result;
	}

	@Override
	public Date getBeginDate(Long pMergeTransactionId, String pProcessName) {
		Date lvDate;
		lvDate = transferBookingTrustRepository.getBeginDate(pMergeTransactionId, pProcessName);
		return lvDate;
	}

	@Override
	public Date getEndDate(Long pMergeTransactionId, String pProcessName) {
		Date lvDate;
		lvDate = transferBookingTrustRepository.getEndDate(pMergeTransactionId, pProcessName);
		return lvDate;
	}

	@Override
	public Boolean isAccountInSync(Long pRootOffenderID) {
		BigDecimal lvTxnSum;
		List<OffenderSubAccounts> currSubAccountBal = transferBookingTrustRepository.getCurrSubAccountBal(pRootOffenderID);
		for (OffenderSubAccounts eachAccount : currSubAccountBal) {
			BigDecimal getBalance = new BigDecimal(eachAccount.getBalance());
			lvTxnSum = transferBookingTrustRepository.getPostedOffTxn(pRootOffenderID, eachAccount.getCaseloadId(),eachAccount.getTrustAccountCode());
			if(lvTxnSum != getBalance ) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Integer checkAlienDedTxn(Long pRootOffenderID, Date leftDateTime, Date rightDateTime) {
		 Integer lvCount;
		 Boolean lvResult = false;
		 lvCount = transferBookingTrustRepository.deductionTxnWithinTime(pRootOffenderID, leftDateTime, rightDateTime);
		 if(lvCount > 0) {
			 lvResult = false;
		 }else {
			 lvResult = true;
		 }
		return lvCount;
	}

	@Override
	public Integer checkOwnDedTxn(Long pRootOffenderID, Date leftDateTime, Date rightDateTime) {
		Integer lvCount;
		 Boolean lvResult = false;
		 lvCount = transferBookingTrustRepository.deductionTxnOutofTime(pRootOffenderID, leftDateTime, rightDateTime);
		 if(lvCount > 0) {
			 lvResult = false;
		 }else {
			 lvResult = true;
		 }
		return lvCount;
	}

}
