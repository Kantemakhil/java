package net.syscon.s4.pkgs.tag_assessment.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.OffenderBookingDetails;
import net.syscon.s4.pkgs.tag_assessment.TagAssessmentRepository;
import net.syscon.s4.pkgs.tag_assessment.TagAssessmentService;

/*
||    Purpose: This package provides the basic funtions for assessments.
||
||    MODIFICATION HISTORY (Please put version history IN a REVERSE-chronological ORDER below)
||    --------------------
||    Person                     DATE       Version  Comments
||    ---------              ---------    ---------  -----------------------------------
||    Neil                 02-OCT-2006          2.9  D#4643 Added get_offender_desc.
||    Neil                 27-SEP-2006          2.8  Added check_offender_assessment
||    Erin                 22-SEP-2006          2.6  Added FUNCTION chk_scale
||    Neil                 20-SEP-2006          2.5  Added get_assessment_info.
||    Neil                 20-SEP-2006          2.4  Added get_assessment_desc.
||    Ade Adekoya          15-SEP-2006          2.3  Rename get_uk_scale_code_rec to get_child_rec
||    Ade Adekoya          14-SEP-2006          2.2  Add get_assessment_id, chk_integrity, get_uk_scale_code_rec
||    Neil                 24-MAR-2006          2.1  chk_assessment_csa: new table assessment_results.
||    Neil                 22-FEB-2006          2.0  Created the Package.
*/
@Service
public class TagAssessmentServiceImpl extends BaseBusiness implements TagAssessmentService {

	@Autowired
	private TagAssessmentRepository tagAssessmentRepository;

	@Override
	public Long getAssessmentId() {
		return tagAssessmentRepository.getAssessmentId();
	}

	@Override
	public Integer chkBkgCsa(final BigDecimal offenderBookId) {
		return tagAssessmentRepository.chkBkgCsa(offenderBookId);
	}

	@Override
	public List<Offenders> getCsaOccupants(final BigDecimal livingUnitId) {
		return tagAssessmentRepository.getCsaOccupants(livingUnitId);
	}

	@Override
	public String chkAssessmentCsa(final BigDecimal pAssessmentId, final String pAssessmentCode) {
		return tagAssessmentRepository.csaResult(pAssessmentId, pAssessmentCode);
	}

	@Override
	public Integer updateBkgCsa(final Long offenderBookId, final String lvCsa, final String userName) {
		final OffenderBookingDetails inputObj = new OffenderBookingDetails();
		inputObj.setOffenderBookId(offenderBookId);
		inputObj.setCellSharingAlertFlag(lvCsa);
		inputObj.setModifyUserId(userName);
		return tagAssessmentRepository.updateBkgCsa(inputObj);
	}

}