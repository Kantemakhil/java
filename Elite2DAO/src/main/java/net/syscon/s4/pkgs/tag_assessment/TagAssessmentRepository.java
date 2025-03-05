package net.syscon.s4.pkgs.tag_assessment;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.OffenderBookingDetails;

public interface TagAssessmentRepository {

	Long getAssessmentId();

	Integer chkBkgCsa(final BigDecimal offenderBookId);

	List<Offenders> getCsaOccupants(final BigDecimal livingUnitId);

	String csaResult(BigDecimal pAssessmentId, String pAssessmentCode);

	Integer updateBkgCsa(OffenderBookingDetails inputObj);

}