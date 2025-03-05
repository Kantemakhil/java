package net.syscon.s4.inst.careinplacement;

import java.util.List;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.careinplacement.beans.OffenderCipDetails;
import net.syscon.s4.inst.careinplacement.beans.OffenderCipDetailsCommitBean;
import net.syscon.s4.inst.careinplacement.beans.PlacementDurations;

/**
 * Interface OidciponService
 */
public interface OidciponService {
	Integer offCipDetailsPreInsert(OffenderCipDetails paramBean);

	List<Object> defaultAgyLocId(CaseloadAgencyLocations paramBean);

	List<PlacementDurations> defaultDurationType(PlacementDurations paramBean);

	Integer checkDate(VHeaderBlock paramBean);

	OffenderCipDetails offBkgOnCheckDeleteMaster(OffenderCipDetails paramBean);

	List<ReferenceCodes> rgPlacementReasonRecordGroup(String placementType);

	List<OffenderCipDetails> checkForActiveCpRec(OffenderCipDetails paramBean);

	Integer offCipDetailsCommit(OffenderCipDetailsCommitBean commitBean);

	List<ReferenceCodes> rgAuthorizedByRecordGroup();

	List<OffenderCipDetails> offCipDetailsExecuteQuery(OffenderCipDetails objOffCipDetails);

	AgencyLocations offCipDetailsPostQuery(AgencyLocations paramBean);

	List<AgencyLocations> rgAgyLocsRecordGroup(String caseloadId);

	List<ReferenceCodes> rgReleasedByRecordGroup();

	String dtValidationForInactiveOff(OffenderExternalMovements paramBean);

	List<ReferenceCodes> rgRequestedByRecordGroup();

	OmsModules createFormGlobals(OmsModules paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	List<ReferenceCodes> rgPlacementTypeRecordGroup();

	List<PlacementDurations> rgDurationTypeRecordGroup(String placementType);

}
