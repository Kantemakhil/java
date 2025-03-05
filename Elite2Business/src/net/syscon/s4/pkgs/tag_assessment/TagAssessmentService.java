package net.syscon.s4.pkgs.tag_assessment;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.im.beans.Offenders;

public interface TagAssessmentService {

	Long getAssessmentId();

	Integer chkBkgCsa(final BigDecimal offenderBookId);

	List<Offenders> getCsaOccupants(final BigDecimal livingUnitId);
	
	String chkAssessmentCsa(BigDecimal pAssessmentId , String pAssessmentCode);

	Integer updateBkgCsa(Long offenderBookId, String lvCsa,String userName);
}