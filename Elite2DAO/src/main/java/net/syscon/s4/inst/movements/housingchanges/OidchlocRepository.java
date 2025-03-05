package net.syscon.s4.inst.movements.housingchanges;

import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.LivingUnits;

/**
 * Interface OidchlocRepository
 */
public interface OidchlocRepository {
	LivingUnits cgfkchkBedAhBedAhVLiv(LivingUnits paramBean);

	List<Object> offBkgOnCheckDeleteMaster(BedAssignmentHistories paramBean);

	List<BedAssignmentHistories> bedAhExecuteQuery(BedAssignmentHistories obj);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer bedAhInsertBedAssignmentHistories(List<BedAssignmentHistories> list,String iep);

	Integer bedAhPreInsert(BedAssignmentHistories paramBean);

	List<ReferenceCodes> rgAssignmentReasonRecordGroup();

	Integer offBookingUpdate(VHeaderBlock commitBean);

	Integer bedAhUpdateBedAssignmentHistories(List<BedAssignmentHistories> listforUpdate);

	BedAssignmentHistories getMovementDateAndTime(BedAssignmentHistories searchRecord);

	BedAssignmentHistories checkAllConficts(BedAssignmentHistories searchRecord);

	OffenderBookings getOldDataOffenderBookings(Long offdenderBookId);
	
	List<String> getNonAssocationOffenderDetails(Integer offenderId,Integer livingunitId);
	
	String offenderDetailsByOffenderId(Integer offenderId);
	
	Integer getOffenderId (Integer offenderBookId);
	
	Boolean checkMoveAdminRoleForUser(String userId);
	
	BedAssignmentHistories offenderName(Integer offenderBookId);
}
