package net.syscon.s4.pkgs.ocdintak;

import java.util.List;

import net.syscon.s4.common.beans.OffenderBookingEvent;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;

public interface OcdintakPkgRepository {
	Integer updateOffBookings(final String toAgyLocId, final OffenderBookings offbkg, final Integer staffId, final String pCommStatus, final String lvBookingType);

	String cGetTrustActFlag(final String pCsldId);

	List<AccountCodes> getCrAcAndRecFlag(final String pCsldId);

	List<AccountCodes> getPTxnPtgTypeAndSubacType(final Double crAc);

	String getPTxnDesc(final String pTxnType);

	Integer checkIfSequenceExists(final String inputStg);

	Integer generateReceiptNoFromSeq(final String trimCsLdId);

	Integer generateReceiptNoFromSeqElBlock(final String trimCsLdId);

	Integer getTxnId();

	Integer updateOffenSubAcc(final OffenderBookingEvent offEve);

	Integer updateOffenderTrustAccounts(final String pCsldId, final Integer pOffId, final String userName);

	Integer insertOffenderTrustAccounts(final Integer pOffId, final String pCsldId, final String userName);

	Integer insertOffenderSubAccounts(final Integer pOffId, final String pCsldId, final Integer pTxnId,
			final String userName);

	List<OffenderDeductions> dedTypeCursor(final Integer pOffId);

	String getCaseLoadCode(final String deductionType);

	Long getGroupId(final String deductionType);

	void updateOffenderDeductions(final Long vGroupId, final String deductionType, final Integer pOffId,
			final String userName);

	Integer insertOffTxn(final OffenderBookingEvent offEve);

	Integer instOffBooking(final OffenderBookingEvent object);

	Integer instOffBookAgyLoc(final OffenderBookingEvent object);

	Integer vOffenderAllSchedules(final OffenderIndSchedules objec);

	Integer getLatestBooking(OffenderBookingEvent obj);

}