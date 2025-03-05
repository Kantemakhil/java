package net.syscon.s4.pkgs.tag_termination.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.legalscreens.bean.UpdateCase;
import net.syscon.s4.pkgs.tag_termination.TagTerminationRepository;
import net.syscon.s4.pkgs.tag_termination.TagTerminationService;
import net.syscon.s4.pkgs.tag_workflow.TagWorkflowService;
/*
||    Purpose: This package provides procedures for Terminations.
||    MODIFICATION HISTORY (Please put version history IN a REVERSE-chronological ORDER below)
||    ----------------------------------------------------------------------------------------
||    Person                   DATE       Version  Comments
||    ---------            ---------    ---------  -----------------------------------
||    Claus                23-May-2007      2.22   D# 6558. Peer-review fix
||    Claus                22-May-2007      2.21   D# 6558. Modified chk_active_obligations.
      Surya                18-Apr-2007      2.20   TD6389:Corrected the locking message.
      Surya				   17-Apr-2007	    2.19   TD6389:Modified terminate_all_sent procedure for ENQUE TX locks fix.
	                                               Removed the version history from spec as it is redundant.
||    Claus                21-Feb-2007      2.18   D# 5948. Peer-review fix: Modified chk_active_licences.
||    Claus                20-Feb-2007      2.17   D# 5948. Modified chk_active_licences. Check if associated licence is active.
||    Claus                09-Feb-2007      2.16   D# 5948. Added chk_active_licence_for_booking.
||    Claus                23-Jan-2007      2.15   D# 5948. Added populate_licences, insert_licences,
                                                   ins_off_licence_sentences and flush_licences_tab to prevent mutating table.
                                                   Modified chk_active_licences.
||    GJC                  14-Oct-2006      2.14   SHOW_VERSION changed from procedure to function
||    Claus                09-OCT-2006      2.13   D# 5008. Modified chk_active_obligations
||    Claus                05-OCT-2006      2.11   D# 5008. Added chk_active_obligations and chk_active_prg_profiles.
||    Claus                04-OCT-2006      2.10   D# 5008. Modified chk_active_schedules_for_cond.
||    Claus                04-OCT-2006      2.9    D# 5008. Added chk_active_schedules_for_cond and modified chk_active_sentence_schedules
||    Rajshree             25-JUL-2006      2.8    Modified terminate_all_sent.Defect ID 3556
||    Claus                07-JUL-2006      2.7    D# 3173. Added function release_type_auto to determine if movement reason
||                                                 is of auto-release type as defined in TAG\Legals\NOMS_D_Legals Summer 2006.doc
||                                                 and procedure terminate_all_sent to terminate all active sentences.
||    D Rice               25-JUN-2006      2.6    Defect: 2488 - Modified case statement so that both CONDITION and LICENCE_COND exist.
||    D Rice               23-JUN-2006      2.5    Defect: 2488 - Changed case statment in procedure: update_status
||                                                 so that CONDITION is now LICENCE_COND
||    Claus                07-JUN-2006      2.4    Removed ampersand from comments in v2.2
||    Claus                07-JUN-2006      2.3    D# 2327. Modified update_status (include 'LICENCE').
||    Johnson              30-MAY-2006      2.2    D# 1881. Added Comments and function chk_active_licences
||    NDB                  28-APR-2006      2.1    Defect 1576.
||    NDB                  20-APR-2006      2.0    Initial UK Version
*/
@Service
public class TagTerminationServiceImpl extends BaseBusiness implements TagTerminationService {
	@Autowired
	private TagTerminationRepository tagTerminationRepository;
	@Autowired
	private TagWorkflowService tagWorkflowService;
	private static Logger logger = LogManager.getLogger(TagTerminationServiceImpl.class.getName());

	private static String Y = "Y";
	private static String N = "N";
	
	// Prevention of Termination of bookings if offender has active TASKS and MEMO's
	@Override
	public String chkTasks(final Long offenderBookId) {
		String lvDummy = N;
		Boolean flag = null;
		try {
			flag = tagWorkflowService.checkOutstandingTask(offenderBookId);
			if (flag) {
				lvDummy = Y;
			}
		} catch (Exception e) {
			logger.error("chkTasks :" + e);
			lvDummy = null;
		}
		return lvDummy;
	}

	@Override
	public String chkActiveLicences(final UpdateCase updateCase) {
		String lvDummy = N;
		lvDummy = tagTerminationRepository.chkActiveLicences(updateCase);
		return lvDummy;
	}

	@Override
	public String chkActiveSentences(final UpdateCase updateCase) {
		String returnString = null;
		try {
			final Long lvCaseId = getCaseId(updateCase.getRowId());
			returnString = chkActiveSentences(lvCaseId);
		} catch (Exception e) {
			logger.error("chkActiveSentences :" + e);
			returnString = null;
		}
		return returnString;
	}

	@Override
	public Long getCaseId(final String pRowid) {
		return tagTerminationRepository.getCaseId(pRowid);
	}

	@Override
	public String chkActiveSentences(final Long pCaseId) {
		String lvDummy = N;
		lvDummy = tagTerminationRepository.chkActiveSentences(pCaseId);
		return lvDummy;
	}

	@Override
	public String chkActiveConditions(final UpdateCase updateCase) {
		String returnString = null;
		try {
			final Long lvSentSeq = getSentenceSeq(updateCase.getRowId());
			returnString = chkActiveConditions(updateCase.getOffenderBookId(), lvSentSeq);
		} catch (Exception e) {
			logger.error("chkActiveConditions :" + e);
			returnString = null;
		}
		return returnString;
	}

	@Override
	public Long getSentenceSeq(final String pRowid) {
		return tagTerminationRepository.getSentenceSeq(pRowid);
	}

	@Override
	public String chkActiveConditions(final Long pOffenderBookId, final Long pSentenceSeq) {
		String lvDummy = N;
		lvDummy = tagTerminationRepository.chkActiveConditions(pOffenderBookId, pSentenceSeq);
		return lvDummy;
	}
     // Prevention of Termination if active cases still exists
	@Override
	public String chkActiveCases(final OffenderExternalMovements searchdao) {
		return tagTerminationRepository.chkActiveCases(searchdao);
	}

}