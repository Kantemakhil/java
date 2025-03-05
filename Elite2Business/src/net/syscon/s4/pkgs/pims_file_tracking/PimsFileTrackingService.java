package net.syscon.s4.pkgs.pims_file_tracking;

import net.syscon.s4.legalorders.OffenderFileTransactions;

public interface PimsFileTrackingService {

	Integer cancelTransfer(final OffenderFileTransactions offFileTrans, final String userName);

	Integer insertTrans(final OffenderFileTransactions bean, final String userName);

	void transferFile(final Integer pOffenderFileSeq, final Long pOffenderId, final String pFileTransComment,
			final String pAgyLocIdFrom, final String pAgyLocIdTo, final Long pStaffIdFrom, final Long pStaffIdTo,
			final String pNonOfficerFrom, final String pNonOfficerTo, final String userName);

	Boolean inTransit(final Integer pOffenderFileSeq, final Long pOffenderId);

	Boolean transferable(final String pNonOfficerStatus);
}