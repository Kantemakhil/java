package net.syscon.s4.inst.visitsmanagement;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.VisitorRestrictions;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictions;
/**
 * Interface OcuvwarnRepository
 */
public interface OcuvwarnRepository {
	List<VisitorRestrictions> visitorRestrictionsExecuteQuery(VisitorRestrictions objTagVisitsGetVisitorRestrictions) ;

	Integer offenderRestrictionInsertTagVisitsGetOffenderRestrictions(List<OffenderRestrictions> lstTagVisitsGetOffenderRestrictions) ;

	List<OffenderRestrictions> offenderRestrictionExecuteQuery(OffenderRestrictions objTagVisitsGetOffenderRestrictions) ;

	Integer visitorRestrictionsInsertTagVisitsGetVisitorRestrictions(List<VisitorRestrictions> lstTagVisitsGetVisitorRestrictions) ;

	List<VisitorRestrictions> populateVisitorDetailsExecuteQuery(OffenderRestrictions searchBean);

	Persons getPersonNames(OffenderRestrictions searchBean);

	SystemProfiles getProfileValues(String profileType, String profileCode);

	Offenders getOffenderNames(Long offenderId);

	String getDescCode(String string, String restrictionType);

}
