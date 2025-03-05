package net.syscon.s4.inst.legalscreens.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.inst.legals.beans.OffenderSentenceAdjustment;
import net.syscon.s4.inst.legals.beans.SentenceAdjustment;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalSettings;

public interface OidcustadRepository {
	
	Integer saveBookings(List<OffenderSentenceAdjustment> savesentences);
	Integer updateBookings(List<OffenderSentenceAdjustment> updatesentences);
	Integer deleteBookings(List<OffenderSentenceAdjustment> deletesentences);
	List<OffenderSentenceAdjustment> fetchBookingDetails(Long offenderBookId);
	List<SentenceAdjustment> getBookingCodes();
	String  getDebitorCreditCode(String code);
	List<SentenceAdjustment> getSentenceCodes();
	List<OffenderSentenceAdjustment> getSentenceData(String objectType,Long offenderBookId);
	String getusagecode(String code);
	List<LegalSettings> getRemissionEligibility();
	OffenderExternalMovements getIntakeDetails(Long offenderBookId);
}
