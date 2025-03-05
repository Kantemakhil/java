package net.syscon.s4.inst.visitsmanagement;
import java.util.List;

import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.TagImages;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictions;

public interface OidviresRepository {
	List<ReferenceCodes> rgAuthVisContactTypeRecordGroup();

	Integer offAuthVisitorsInsertOffenderContactPersons(List<OffenderContactPersons> lstOffenderContactPersons);

	List<Images> imageVisitExecuteQuery(Long imageObjectId, String type, String userName);

	List<ReferenceCodes> rgAuthPriRelationshipTypeRecordGroup(String contactType);

	Integer offAuthVisitorsUpdateOffenderContactPersons(List<OffenderContactPersons> lstOffenderContactPersons);

	List<StaffMembers> rgStaffIdRecordGroup(String agyLocId);

	Integer offVisitRestInsertOffenderRestrictions(List<OffenderRestrictions> lstOffenderRestrictions);

	List<TagImages> offAuthVisitorsOnCheckDeleteMaster(TagImages paramBean);

	List<ReferenceCodes> rgOffRestrictionTypeRecordGroup();

	List<OffenderContactPersons> offAuthVisitorsExecuteQuery(OffenderContactPersons objOffenderContactPersons);
	
	List<OffenderContactPersons> offVisitingExecuteQuery(OffenderContactPersons objOffenderContactPersons);

	Integer imageVisitDeleteImages(List<net.syscon.s4.im.beans.Images> list);

	List<OffenderRestrictions> offBkgOnCheckDeleteMaster(OffenderRestrictions paramBean);

	List<ReferenceCodes> rgAuthVisRelationshipTypeRecordGroup();

	List<Images> offAuthVisitOffOnCheckDeleteMaster(Images paramBean);

	List<OffenderRestrictions> offVisitRestExecuteQuery(OffenderRestrictions objOffenderRestrictions);

	List<OffenderContactPersons> offBkgOnCheckDeleteMaster(OffenderContactPersons paramBean);

	Integer offVisitRestUpdateOffenderRestrictions(List<OffenderRestrictions> lstOffenderRestrictions);

	Integer offAuthVisitorsDeleteOffenderContactPersons(List<OffenderContactPersons> lstOffenderContactPersons);
	
	Integer oidviresOffauthvisitoffInsertOffenderContactPersons(List<OffenderContactPersons> lstOffenderContactPersons);
	
	Integer oidviresOffauthvisitoffUpdateOffenderContactPersons(List<OffenderContactPersons> lstOffenderContactPersons);
	
	Integer oidviresOffauthvisitoffDeleteOffenderContactPersons(List<OffenderContactPersons> lstOffenderContactPersons);
	
	Integer oidviresFindTagVisitsGetStaffId(String username);

	List<String> oidviresIsOffenderBanRestriction(Long offenderBookId);

	List<ReferenceCodes> oidviresIsPersonBanRestriction(Long personId);

	String chkNaBetweenOffenders(Long glbOffBkgId, Long visOffBkgId);
	

}
