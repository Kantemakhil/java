package net.syscon.s4.pkgs.pims_file_tracking;

import net.syscon.s4.legalorders.OffenderFileTransactions;

public interface PimsFileTrackingRepository {

	OffenderFileTransactions tranCur(final Long offenderFileSeq, final Long offenderId);

	Integer updateOffenderFileTransaction(final Long transactionId, final String userName);

	Integer insertTrans(final OffenderFileTransactions bean);

	Integer getMaxOffenderFileSeq(final Integer pOffenderFileSeq, final Long pOffenderId);

	String getTranCur(final Long pOffenderId, final Integer pOffenderFileSeq);

	Integer transferFileInsertOffenderFileTransactionsOne(final Integer pOffenderFileSeq, final Long pOffenderId,
			final String pAgyLocIdFrom, final String pAgyLocIdTo, final Long pStaffIdFrom, final Long pStaffIdTo,
			final String pFileTransComment, final String userName);

	Integer transferFileInsertOffenderFileTransactionsTwo(final Integer pOffenderFileSeq, final Long pOffenderId,
			final String lvConfirmed, final String pAgyLocIdFrom, final String pAgyLocIdTo, final Long pStaffIdFrom,
			final String pNonOfficerTo, final String pFileTransComment, final String userName);

	Integer transferFileInsertOffenderFileTransactionsThree(final Integer pOffenderFileSeq, final Long pOffenderId,
			final String pAgyLocIdFrom, final String pAgyLocIdTo, final Long pStaffIdTo, final String pNonOfficerFrom,
			final String pFileTransComment, final String userName);

	Integer transferFileInsertOffenderFileTransactionsFour(final Integer pOffenderFileSeq, final Long pOffenderId,
			final String lvConfirmed, final String pAgyLocIdFrom, final String pAgyLocIdTo,
			final String pNonOfficerFrom, final String pNonOfficerTo, final String pFileTransComment,
			final String userName);

}