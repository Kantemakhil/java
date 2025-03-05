package net.syscon.s4.inst.visitsmanagement;
import java.util.List;

import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.ImagesCommitBean;
import net.syscon.s4.im.beans.TagImages;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.booking.beans.OffenderContactPersonsCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictions;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictionsCommitBean;

public interface OidviresService  {
	List<ReferenceCodes> rgAuthVisContactTypeRecordGroup();

	List<Images> imageVisitExecuteQuery(Long imageObjectId, String type, String userName);

	Integer offAuthVisitOffCommit(OffenderContactPersonsCommitBean CommitBean);

	List<TagImages> offAuthVisitorsOnCheckDeleteMaster(TagImages paramBean) ;

	List<Images> offAuthVisitOffOnCheckDeleteMaster(Images paramBean) ;

	List<ReferenceCodes> rgAuthPriRelationshipTypeRecordGroup(String contactType);

	List<StaffMembers> rgStaffIdRecordGroup(String agyLocId);

	Integer imageVisitCommit(ImagesCommitBean commitBean);

	List<ReferenceCodes> rgOffRestrictionTypeRecordGroup();

	List<OffenderContactPersons> offAuthVisitorsExecuteQuery(OffenderContactPersons objOffenderContactPersons);

	Integer imagesOffCommit(ImagesCommitBean commitBean);

	List<ReferenceCodes> rgAuthVisRelationshipTypeRecordGroup();

	Integer offVisitRestCommit(OffenderRestrictionsCommitBean CommitBean);

	List<OffenderRestrictions> offVisitRestExecuteQuery(OffenderRestrictions objOffenderRestrictions);

	Integer offAuthVisitorsCommit(OffenderContactPersonsCommitBean CommitBean);

	List<OffenderRestrictions> offBkgOnCheckDeleteMaster(OffenderRestrictions paramBean) ;
	
	List<OffenderContactPersons> offVisitingExecuteQuery(OffenderContactPersons objOffenderContactPersons);
	
	Integer oidviresFindTagVisitsGetStaffId(String userName);
	
	List<String> oidviresIsOffenderBanRestriction(Long offenderBookId);

	List<ReferenceCodes> oidviresIsPersonBanRestriction(Long personId);

	String chkNaBetweenOffenders(Long glbOffBkgId, Long visOffBkgId);
}
