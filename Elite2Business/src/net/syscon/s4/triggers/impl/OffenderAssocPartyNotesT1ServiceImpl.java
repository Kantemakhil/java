package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.triggers.OffenderAssocPEventNotifs;
import net.syscon.s4.triggers.OffenderAssocPartyNotes;
import net.syscon.s4.triggers.OffenderAssocPartyNotesT1Repository;
import net.syscon.s4.triggers.OffenderAssocPartyNotesT1Service;

@Service
public class OffenderAssocPartyNotesT1ServiceImpl implements OffenderAssocPartyNotesT1Service {
	private static Logger logger = LogManager.getLogger(OffenderAssocPartyNotesT1ServiceImpl.class);
	@Autowired
	OffenderAssocPartyNotesT1Repository OffenderAssocPartyNotesT1Repository;

	@Override
	public Integer OffenderAssocPartyNotesT1(final OffenderAssocPartyNotes offenderAssocPartyNotes) throws CustomException {
		final OffenderAssocPEventNotifs offeAssPEveNot = new OffenderAssocPEventNotifs();
		offeAssPEveNot.setTrgEventId(OffenderAssocPartyNotesT1Repository.trgEventIdSeq());
		offeAssPEveNot.setEventDate(offenderAssocPartyNotes.getNoteDatetime());
		offeAssPEveNot.setOffenderBookId(
				OffenderAssocPartyNotesT1Repository.offenderBookId(offenderAssocPartyNotes.getAssociatedPartyId()));
		offeAssPEveNot.setCreateUserId(offenderAssocPartyNotes.getCreateUserId());
		Integer result = null;
		try {
			if ("RES_CON".equals(offenderAssocPartyNotes.getNoteType())) {
				if ("RC_RSN_APP".equals(offenderAssocPartyNotes.getNoteSubtype())) {
					offeAssPEveNot.setNotificationCode("RC_APP");
					result = OffenderAssocPartyNotesT1Repository.saveOffenderAssocPEventNotifs(offeAssPEveNot);
				} else if ("RC_RSN_APL".equals(offenderAssocPartyNotes.getNoteSubtype())) {
					offeAssPEveNot.setNotificationCode("RC_APL");
					result = OffenderAssocPartyNotesT1Repository.saveOffenderAssocPEventNotifs(offeAssPEveNot);
				} else if ("RC_RSN_DEN".equals(offenderAssocPartyNotes.getNoteSubtype())) {
					offeAssPEveNot.setNotificationCode("RC_DEN");
					result = OffenderAssocPartyNotesT1Repository.saveOffenderAssocPEventNotifs(offeAssPEveNot);
				}
			} else if ("DRU_COU".equals(offenderAssocPartyNotes.getNoteType())) {
				if ("DC_RSN_APP".equals(offenderAssocPartyNotes.getNoteSubtype())) {
					offeAssPEveNot.setNotificationCode("DC_APP");
					result = OffenderAssocPartyNotesT1Repository.saveOffenderAssocPEventNotifs(offeAssPEveNot);
				} else if ("DC_RSN_APL".equals(offenderAssocPartyNotes.getNoteSubtype())) {
					offeAssPEveNot.setNotificationCode("DC_APL");
					result = OffenderAssocPartyNotesT1Repository.saveOffenderAssocPEventNotifs(offeAssPEveNot);
				} else if ("DC_RSN_DEN".equals(offenderAssocPartyNotes.getNoteSubtype())) {
					offeAssPEveNot.setNotificationCode("DC_DEN");
					result = OffenderAssocPartyNotesT1Repository.saveOffenderAssocPEventNotifs(offeAssPEveNot);
				}
			} else if ("DUI".equals(offenderAssocPartyNotes.getNoteType())) {
				if ("DUI_RSN_APP".equals(offenderAssocPartyNotes.getNoteSubtype())) {
					offeAssPEveNot.setNotificationCode("DUI_APP");
					result = OffenderAssocPartyNotesT1Repository.saveOffenderAssocPEventNotifs(offeAssPEveNot);
				} else if ("DUI_RSN_APL".equals(offenderAssocPartyNotes.getNoteSubtype())) {
					offeAssPEveNot.setNotificationCode("DUI_APL");
					result = OffenderAssocPartyNotesT1Repository.saveOffenderAssocPEventNotifs(offeAssPEveNot);
				} else if ("DUI_RSN_DEN".equals(offenderAssocPartyNotes.getNoteSubtype())) {
					offeAssPEveNot.setNotificationCode("DUI_DEN");
					result = OffenderAssocPartyNotesT1Repository.saveOffenderAssocPEventNotifs(offeAssPEveNot);
				}

			} else if ("INT_TRN".equals(offenderAssocPartyNotes.getNoteType())) {
				if ("IT_RSN_APP".equals(offenderAssocPartyNotes.getNoteSubtype())) {
					offeAssPEveNot.setNotificationCode("IT_APP");
					result = OffenderAssocPartyNotesT1Repository.saveOffenderAssocPEventNotifs(offeAssPEveNot);
				} else if ("IT_RSN_APL".equals(offenderAssocPartyNotes.getNoteSubtype())) {
					offeAssPEveNot.setNotificationCode("IT_RSN_DEN");
					result = OffenderAssocPartyNotesT1Repository.saveOffenderAssocPEventNotifs(offeAssPEveNot);
				} else if ("DUI_RSN_DEN".equals(offenderAssocPartyNotes.getNoteSubtype())) {
					offeAssPEveNot.setNotificationCode("IT_DEN");
					result = OffenderAssocPartyNotesT1Repository.saveOffenderAssocPEventNotifs(offeAssPEveNot);
				}
			}
		} catch (final Exception e) {
			logger.error("OffenderAssocPartyNotesT1", e);
			throw new CustomException("An error occurred in OFFENDER_ASSOC_PARTY_NOTES_T1:");
		}
		return result;
	}

}
