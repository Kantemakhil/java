package net.syscon.s4.pkgs.comunity_dbtrg_pkg.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.pkgs.comunity_dbtrg_pkg.ComunityDbtrgPkgRepository;
import net.syscon.s4.pkgs.comunity_dbtrg_pkg.ComunityDbtrgPkgService;
import net.syscon.s4.triggers.CaseNotes;
/*
 * Below comments are copied from COMUNITY_DBTRG_PKG procedure.
 * Purpose: This is to store all Procedures/functions related to Community Development
||          and associated data base trigger procedure commonly used
||
|| MODIFICATION HISTORY (Please put version history in a reverse-chronological order below)
||
|| -----------------------------------------------------------------------------------
|| Person        Date          Version    Comments
|| ------------  -----------   ---------  -----------------------------------
|| A.Adekoya     28 Nov 2006   2.4        D# 5689: Add backslash in body
|| A.Adekoya     21 Nov 2006   2.3        D# 5689: Modify Package Body add END;
|| GJC           14 Oct 2006   2.2        SHOW_VERSION changed from procedure to function
|| Vipul         22-MAR-2002   6.1.0.0    Added new Function GET_STAFF_INFO and GET_MISC_VALUES
||                                        to be used for Case Notes Triggers.
|| Snezana M.    27-FEB-2001              Added Procedure Process_Email_Queue_PBOARD
|| Snezana M.    27-FEB-2001              Added Procedure GET_EMAIL_PBOARD
|| Srinivas ALN  14-AUG-2000              Modified the Insert_case_notes procedure
|| Vipul Mishra  03-FEB-2000              Added Procedure Process_Email_Queue
|| Vipul Mishra  03-FEB-2000              Added Procedure Comm_email_param.
|| Srinivas ALN  18-JAN-2000              Common Procdures and Functions in Community Development
|| ====================================================================================
 * 
 * */
@Service
public class ComunityDbtrgPkgServiceImpl implements ComunityDbtrgPkgService {
	private static Logger logger = LogManager.getLogger(ComunityDbtrgPkgServiceImpl.class);
	@Autowired
	private ComunityDbtrgPkgRepository comunityDbtrgPkgRepository;
	private static String cUser;

	@Override
	public String getEmailadres(final Long pOffenderBookId) {
		final StaffMembers casePlans = comunityDbtrgPkgRepository.vsEmailcur(pOffenderBookId);
		if (casePlans == null) {
			return null;
		}
		if (Optional.ofNullable(casePlans).isPresent() && casePlans.getUserId().equals(cUser)) {
			return null;
		} else {
			return casePlans.getInternetAddress();
		}
	}
    //procedure to get active flag
	@Override
	public Boolean getActiveFlag(final String casNotType, final String reason, final String triggerEvent) {
		final String vsCaseNoteCur = comunityDbtrgPkgRepository.vsCaseNoteCur(casNotType, reason, triggerEvent);
		if (vsCaseNoteCur == null) {
			return false;
		}
		if ("Y".equals(vsCaseNoteCur)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Long getNoteSeq(final Long offenderBookId) {
		try {
			return comunityDbtrgPkgRepository.vsNseqCur(offenderBookId);
		} catch (final Exception e) {
			return null;
		}
	}

	@Override
	public Integer getStaffId(final String userId) {
		final StaffMembers sm = comunityDbtrgPkgRepository.vsStaffCur(userId);
		if (Optional.ofNullable(sm).isPresent()) {
			return sm.getStaffId();
		}
		return null;
	}

	@Transactional
	@Override
	public Integer insertCaseNNotes(final CaseNotes caseNotes) {
		return comunityDbtrgPkgRepository.insertCaseNotes(caseNotes);
	}

	/*
	 * This procedure is used to get the Email Parameters used for constructing the
	 * Email Data.
	 * 
	 */
	@Override
	public VHeaderBlock commEmailParams(final Long offenderBookId,String userId) {
		try {
			final VHeaderBlock vHeaderBlock = comunityDbtrgPkgRepository.vsOffInfoCur(offenderBookId,userId);
			if (Optional.ofNullable(vHeaderBlock).isPresent()) {
				return vHeaderBlock;
			} else {
				return null;
			}
		} catch (final Exception e) {
			logger.error("Error : Getting the Offender Info", e);
			return null;
		}
	}

	@Override
	public String getEmailPboard() {
		return comunityDbtrgPkgRepository.curEmailPboard();
	}

	/*
	 * This procedure will check if the reason,case note type exists with an active
	 * flag for that given trigger event ( overloaded function )
	 * 
	 */
	@Override
	public Boolean getActiveFlag(final String caseType, final String reason) {
		final String vsCaseNotesCur = comunityDbtrgPkgRepository.vsCaseNotesCur(caseType, reason);
		if (vsCaseNotesCur == null || "".equals(vsCaseNotesCur)) {
			return false;
		}
		if ("Y".equals(vsCaseNotesCur)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getStaffInfo(final Integer staffId) {
		return comunityDbtrgPkgRepository.staffCur(staffId);
	}

	@Override
	public String getMiscValues(final Integer type, final String pAgyLocId, final String pEventType,
			final String pEventSubType) {
		String lvReturnValue = null;
		try {
			if (type == 1) {
				lvReturnValue = comunityDbtrgPkgRepository.agyLocCur(pAgyLocId);
			} else if (type == 2) {
				lvReturnValue = comunityDbtrgPkgRepository.eventDspCur(pEventType);

			} else if (type == 3) {
				lvReturnValue = comunityDbtrgPkgRepository.evtSubDspCur(pEventSubType, pEventType);
			}
		} catch (final Exception e) {
			logger.error(e);
			return null;
		}
		return lvReturnValue;
	}

}
