package net.syscon.s4.inst.visitsmanagement;

import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.VisitorRestrictions;
import net.syscon.s4.im.beans.VisitorRestrictionsCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictions;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictionsCommitBean;

/**
 * Interface OcuvwarnService
 */
public interface OcuvwarnService {
	List<VisitorRestrictions> visitorRestrictionsExecuteQuery(VisitorRestrictions objTagVisitsGetVisitorRestrictions);

	Integer visitorRestrictionsCommit(VisitorRestrictionsCommitBean commitBean);

	Integer offenderRestrictionCommit(OffenderRestrictionsCommitBean commitBean);

	List<OffenderRestrictions> offenderRestrictionExecuteQuery(
			OffenderRestrictions objTagVisitsGetOffenderRestrictions);

	List<VisitorRestrictions> populateVisitorDetailsExecuteQuery(OffenderRestrictions searchBean);

	Persons getPersonNames(OffenderRestrictions searchBean);

	SystemProfiles getProfileValues(String profileType, String profileCode);

	Offenders getOffenderNames(Long offenderId);

}
