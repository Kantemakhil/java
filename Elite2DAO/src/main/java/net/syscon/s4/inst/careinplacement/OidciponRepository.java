package net.syscon.s4.inst.careinplacement;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.careinplacement.beans.OffenderCipDetails;
import net.syscon.s4.inst.careinplacement.beans.PlacementDurations;

/**
 * Interface OidciponRepository
 */
public interface OidciponRepository {
	Integer offCipDetailsUpdateOffenderCipDetails(List<OffenderCipDetails> lstOffCipDets);

	PlacementDurations offCipDetailsPostQuery(PlacementDurations paramBean);

	AgencyLocations offCipDetailsPostQuery(AgencyLocations paramBean);

	List<OffenderCipDetails> checkDate(VHeaderBlock paramBean);

	List<ReferenceCodes> rgPlacementReasonRecordGroup(String placementType);

	List<ReferenceCodes> rgAuthorizedByRecordGroup();

	List<OffenderCipDetails> offCipDetailsExecuteQuery(OffenderCipDetails objOffCipDets);

	List<AgencyLocations> rgAgyLocsRecordGroup(String caseloadId);

	Integer offCipDetailsInsertOffenderCipDetails(List<OffenderCipDetails> lstOffCipDets);

	List<ReferenceCodes> rgReleasedByRecordGroup();

	Integer offCipDetailsPreInsert(OffenderCipDetails paramBean);

	ReferenceCodes offCipDetailsPostQuery(ReferenceCodes paramBean);

	Integer offCipDetailsDeleteOffenderCipDetails(List<OffenderCipDetails> lstOffCipDets);

	List<ReferenceCodes> rgRequestedByRecordGroup();

	List<Object> cgwhenNewFormInstance(SysDual paramBean);

	List<OffenderCipDetails> checkForActiveCpRec(OffenderCipDetails paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	List<Object> defaultAgyLocId(CaseloadAgencyLocations paramBean);

	List<ReferenceCodes> rgPlacementTypeRecordGroup();

	OmsModules createFormGlobals(OmsModules paramBean);

	List<OffenderExternalMovements> dtValidationForInactiveOff(OffenderExternalMovements paramBean);

	List<PlacementDurations> defaultDurationType(PlacementDurations paramBean);

	OffenderCipDetails offBkgOnCheckDeleteMaster(OffenderCipDetails paramBean);

	List<PlacementDurations> rgDurationTypeRecordGroup(String placementType);

	Integer checkDateCount(VHeaderBlock paramBean);

	Double computeForHours(Date effectiveTime, Date releaseTime);

	Double computeForTime(Double lvTtInHours);

	String computeForHoursFormat(Double lvRelTime);

	Integer computeForFloorHours(Double lvTtInHours);

}
