package net.syscon.s4.pkgs.pims_file_tracking.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.legalorders.OffenderFileTransactions;
import net.syscon.s4.pkgs.pims_file_tracking.PimsFileTrackingRepository;
import net.syscon.s4.pkgs.pims_file_tracking.PimsFileTrackingService;

@Service
public class PimsFileTrackingServiceImpl implements PimsFileTrackingService {

	@Autowired
	private PimsFileTrackingRepository pimsTransferRepositoryRepo;

	private static final String MERGED = "MERGED";
	private static final String DESTROYED = " DESTROYED";
	private static final String SUPERSEDED = " SUPERSEDED";
	private static final String N = "N";
	private static final String Y = "Y";
	private static final String INT = "INT";
	private static final String TRAN = "TRAN";
	private static final String CANC = "CANC";

	@Override
	public Integer cancelTransfer(final OffenderFileTransactions offFileTrans, final String userName) {
		OffenderFileTransactions offFileTran = pimsTransferRepositoryRepo.tranCur(offFileTrans.getOffenderFileSeq(),
				offFileTrans.getOffenderId());
		if (offFileTrans.getFileTransType().equals(TRAN)) {
			offFileTran.setTransferDate(new Date());
		} else {
			offFileTran.setTransferDate(null);
		}
		if (offFileTrans.getFileTransType() == null) {
			offFileTrans.setFileTransType(CANC);
		}
		offFileTrans.setConfirmed(Y);
		offFileTrans.setCreateUserId(userName);
		return pimsTransferRepositoryRepo.insertTrans(offFileTrans);
	}

	@Override
	public Integer insertTrans(final OffenderFileTransactions bean, final String userName) {
		bean.setCreateUserId(userName);
		return pimsTransferRepositoryRepo.insertTrans(bean);
	}
    
	//This method can  be used for  transferring data 
	@Override
	public void transferFile(final Integer pOffenderFileSeq, final Long pOffenderId, final String pFileTransComment,
			final String pAgyLocIdFrom, final String pAgyLocIdTo, final Long pStaffIdFrom, final Long pStaffIdTo,
			final String pNonOfficerFrom, final String pNonOfficerTo, final String userName) {
		String lvConfirmed = null;
		final Boolean intransir = inTransit(pOffenderFileSeq, pOffenderId);
		final Boolean transf = transferable(pNonOfficerFrom);
		if (!intransir) {
			if (transf) {
				if (pStaffIdFrom != null && pStaffIdTo != null) {
					if (pStaffIdFrom != pStaffIdTo) {
						pimsTransferRepositoryRepo.transferFileInsertOffenderFileTransactionsOne(pOffenderFileSeq,
								pOffenderId, pAgyLocIdFrom, pAgyLocIdTo, pStaffIdFrom, pStaffIdTo, pFileTransComment,
								userName);
					}
				} else if (pNonOfficerFrom == null && pStaffIdTo == null && pNonOfficerTo != null) {
					if (INT.equals(pNonOfficerTo))
						lvConfirmed = N;
					else
						lvConfirmed = Y;
					pimsTransferRepositoryRepo.transferFileInsertOffenderFileTransactionsTwo(pOffenderFileSeq,
							pOffenderId, lvConfirmed, pAgyLocIdFrom, pAgyLocIdTo, pStaffIdFrom, pNonOfficerTo,
							pFileTransComment, userName);
				} else if (pNonOfficerTo == null && pStaffIdFrom == null && pStaffIdTo != null) {
					pimsTransferRepositoryRepo.transferFileInsertOffenderFileTransactionsThree(pOffenderFileSeq,
							pOffenderId, pAgyLocIdFrom, pAgyLocIdTo, pStaffIdTo, pNonOfficerFrom, pFileTransComment,
							userName);
				} else if (pNonOfficerFrom != null && pNonOfficerTo != null) {
					if (pNonOfficerFrom != pNonOfficerTo) {
						if (INT.equals(pNonOfficerTo)) {
							lvConfirmed = N;
						} else {
							lvConfirmed = Y;
						}
						pimsTransferRepositoryRepo.transferFileInsertOffenderFileTransactionsFour(pOffenderFileSeq,
								pOffenderId, lvConfirmed, pAgyLocIdFrom, pAgyLocIdTo, pNonOfficerFrom, pNonOfficerTo,
								pFileTransComment, userName);
					}
				} // elseif
			} // second if
		} // first if
	}// method end

	@Override
	public Boolean inTransit(final Integer pOffenderFileSeq, final Long pOffenderId) {
		Integer parentOffenderFileSeq = null;
		String existsResult = null;
		Boolean returnValue = false;
		parentOffenderFileSeq = pimsTransferRepositoryRepo.getMaxOffenderFileSeq(pOffenderFileSeq, pOffenderId);

		if (parentOffenderFileSeq == null) {
			existsResult = pimsTransferRepositoryRepo.getTranCur(pOffenderId, pOffenderFileSeq);
		} else {
			existsResult = pimsTransferRepositoryRepo.getTranCur(pOffenderId, parentOffenderFileSeq);
		}

		if (existsResult != null) {
			returnValue = true;
		}
		return returnValue;
	}

	// -- returns true if the file can be transfered D
	@Override
	public Boolean transferable(final String pNonOfficerStatus) {

		if (MERGED.equals(pNonOfficerStatus) || DESTROYED.equals(pNonOfficerStatus)
				|| SUPERSEDED.equals(pNonOfficerStatus)) {
			return false;
		} else {
			return true;
		}
	}

}