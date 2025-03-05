package net.syscon.s4.inst.movements.housingchanges;

import java.util.List;

import net.syscon.s4.common.beans.BedAssignmentHistoriesCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.LivingUnits;

/**
 * Interface OidchlocService
 */
public interface OidchlocService {
	List<Object> offBkgOnCheckDeleteMaster(BedAssignmentHistories paramBean);

	List<BedAssignmentHistories> bedAhExecuteQuery(BedAssignmentHistories obj);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	Integer bedAhPreInsert(BedAssignmentHistories paramBean);

	Integer bedAhCommit(BedAssignmentHistoriesCommitBean commitBean);

	List<ReferenceCodes> rgAssignmentReasonRecordGroup();

	LivingUnits cgfkchkBedAhBedAhVLiv(LivingUnits paramBean);

	Integer offBookingUpdate(VHeaderBlock commitBean);

	BedAssignmentHistories getMovementDateAndTime(BedAssignmentHistories searchBean);

	BedAssignmentHistories checkAllConficts(BedAssignmentHistories searchBean);

	List<BedAssignmentHistories> checkNonIndGangConficts(List<BedAssignmentHistories> searchList);
}
