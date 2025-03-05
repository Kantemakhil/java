package net.syscon.s4.triggers.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.comunity_dbtrg_pkg.ComunityDbtrgPkgService;
import net.syscon.s4.triggers.CaseNotes;
import net.syscon.s4.triggers.SubstanceTests;
import net.syscon.s4.triggers.SubstanceTestsTieqRepository;
import net.syscon.s4.triggers.SubstanceTestsTieqService;

@Service
public class SubstanceTestsTieqServiceImpl implements SubstanceTestsTieqService {
	final Logger logger = LogManager.getLogger(SubstanceTestsTieqServiceImpl.class);
	@Autowired
	SubstanceTestsTieqRepository substanceTestsTieqRepository;
	@Autowired
	ComunityDbtrgPkgService comunityDbtrgPkgService;

	@Override
	public Integer substanceTestsTieqTrigger(final SubstanceTests substanceTests, final CaseNotes CNotes,String userName) {
		Boolean vCasenoteFlag;
		Boolean vEmailFlag;
		String vSubstance = null;
		final DateFormat sdf = new SimpleDateFormat("MM/DD/YYYY");
		final SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
		final CaseNotes caseNotes = new CaseNotes();
		final String vSubCur = substanceTestsTieqRepository.vSubCur(substanceTests.getOffenderBookId(),
				substanceTests.getSampleSeq());
		try {
			if ("P".equals(substanceTests.getResults())) {
				vCasenoteFlag = comunityDbtrgPkgService.getActiveFlag("TEST", "P", "CASENOTE");
				vEmailFlag = comunityDbtrgPkgService.getActiveFlag("TEST", "P", "EMAIL");
				if (vCasenoteFlag == true || vEmailFlag == true) {
					if (Optional.ofNullable(vSubCur).isPresent()) {
						vSubstance = vSubCur;
					} else {
						vSubstance = null;
					}
				}
				if (vCasenoteFlag == true) {
					final Integer vStaffId = comunityDbtrgPkgService.getStaffId(substanceTests.getCreateUserId());
					final Long vNoteSeq = comunityDbtrgPkgService.getNoteSeq(substanceTests.getOffenderBookId());
					final String vText = "Offender tested positive for ".concat(vSubstance).concat("on ")
							.concat(sdf.format(substanceTests.getDateTested())).concat(".");
					if (Optional.ofNullable(vStaffId).isPresent() && Optional.ofNullable(vNoteSeq).isPresent()) {
						caseNotes.setOffenderBookId(substanceTests.getOffenderBookId());
						caseNotes.setNoteSeq(vNoteSeq);
						caseNotes.setStaffId(vStaffId);
						caseNotes.setNoteDate(df.parse(df.format(new Date())));
						caseNotes.setCasNotType("TEST");
						caseNotes.setReason("P");
						caseNotes.setText(vText);
						caseNotes.setContactDate(df.parse(df.format(new Date())));
						caseNotes.setScheduleId(CNotes.getScheduleId());
						comunityDbtrgPkgService.insertCaseNNotes(caseNotes);
					}
					if (vEmailFlag == true) {
						comunityDbtrgPkgService.commEmailParams(substanceTests.getOffenderBookId(),userName);

					}
				}

			}
		} catch (final Exception e) {
			logger.info("substanceTestsTieqTrigger");
			logger.error(" AFTER_INSERT ON SUBSTANCE_TESTS: ", e);
		}
		return null;
	}

}
