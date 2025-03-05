package net.syscon.s4.triggers.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.comunity_dbtrg_pkg.ComunityDbtrgPkgService;
import net.syscon.s4.triggers.CaseNotes;
import net.syscon.s4.triggers.SubstanceTestResults;
import net.syscon.s4.triggers.SubstanceTestResultsTcnRepository;
import net.syscon.s4.triggers.SubstanceTestResultsTcnService;
import net.syscon.s4.triggers.SubstanceTests;

@Service
public class SubstanceTestResultsTcnServiceImpl implements SubstanceTestResultsTcnService {
	private static Logger logger = LogManager.getLogger(SubstanceTestResultsTcnServiceImpl.class);
	@Autowired
	private SubstanceTestResultsTcnRepository SubstanceTestResultsTcnRepository;
	@Autowired
	private ComunityDbtrgPkgService comunityDbtrgPkgService;

	@Override
	public Integer SubstanceTestResultsTcn(final SubstanceTestResults substanceTestResults,
			final CaseNotes caseNotesObj, final Offenders offenders,String userName) {
		final SubstanceTests vSubCur = SubstanceTestResultsTcnRepository
				.vSubCur(substanceTestResults.getOffenderBookId(), substanceTestResults.getSampleSeq());
		boolean vCasenoteFlag;
		boolean vEmailFlag;
		Date lvDateTested;
		String vSubstance;
		CaseNotes caseNotes;
		final DateFormat sdf = new SimpleDateFormat("MM/DD/YYYY");
		if ("P".equals(substanceTestResults.getResultCode())) {
			vCasenoteFlag = comunityDbtrgPkgService.getActiveFlag("TEST", "P", "CASENOTE");
			vEmailFlag = comunityDbtrgPkgService.getActiveFlag("TEST", "P", "EMAIL");
			if (vCasenoteFlag == true || vEmailFlag == true) {
				if (vSubCur == null) {
					lvDateTested = null;
				}
			}
			vSubstance = substanceTestResults.getSubstance();
			if (vCasenoteFlag == true) {
				final Long vNoteSeq = comunityDbtrgPkgService.getNoteSeq(substanceTestResults.getOffenderBookId());
				final Integer vStaffId = comunityDbtrgPkgService.getStaffId(substanceTestResults.getCreateUserId());
				final String vText = new StringBuilder("Offender tested positive for ").append(vSubstance).append("on")
						.append(sdf.format(vSubCur)).toString();
				if (Optional.ofNullable(vNoteSeq).isPresent() && Optional.ofNullable(vStaffId).isPresent()) {
					caseNotes = new CaseNotes();
					dataMapping(substanceTestResults, caseNotes, vNoteSeq, vStaffId, vText);
					caseNotes.setScheduleId(caseNotesObj.getScheduleId());
					comunityDbtrgPkgService.insertCaseNNotes(caseNotes);
				}
			}
			if (vEmailFlag == true) {
				try {
					final VHeaderBlock vHeaderBlock = comunityDbtrgPkgService
							.commEmailParams(substanceTestResults.getOffenderBookId(),userName);
				} catch (final Exception e) {
					logger.error("RAISE_APPLICATION_ERROR(-20001, ' AFTER_INSERT ON SUBSTANCE_TESTS: ' || SQLERRM)", e);
				}
			}

		}

		return null;
	}

	private void dataMapping(final SubstanceTestResults substanceTestResults, final CaseNotes caseNotes,
			final Long vNoteSeq, final Integer vStaffId, final String vText) {
		caseNotes.setOffenderBookId(substanceTestResults.getOffenderBookId());
		caseNotes.setNoteSeq(vNoteSeq);
		caseNotes.setStaffId(vStaffId);
		caseNotes.setNoteDate(new Date());
		caseNotes.setCasNotType("TEST");
		caseNotes.setReason("POSITIVE");
		caseNotes.setText(vText);
		caseNotes.setContactDate(new Date());
	}

}
