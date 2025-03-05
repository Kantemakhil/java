package net.syscon.s4.inst.offenderobservations;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.inst.careinplacement.beans.OffObsPeriodChecks;
import net.syscon.s4.inst.careinplacement.beans.OffObsPeriodCheckscommitBean;
import net.syscon.s4.inst.offenderobservations.beans.OffenderObservationPeriods;
import net.syscon.s4.inst.offenderobservations.beans.OffenderObservationPeriodsCommitBean;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsCharacteristics;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsCharacteristicsCommitBean;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationTypes;

public interface OidoffobService {

	List<OffenderObservationTypes> getObservatioTypeData(OffenderObservationTypes searchBean);

	List<OffenderObservationPeriods> getOffenderPeriodExecuteQuery(OffenderObservationPeriods searchBean);
	
	List<OffenderObservationPeriods> offenderObservationPeriodDataCommit(OffenderObservationPeriodsCommitBean commitBean);

	List<OffObsPeriodChecks> getOffenderPeriodCheckExecuteQuery(OffObsPeriodChecks searchBean);

	List<OffObsPeriodChecks> offenderObservationCheckDataCommit(OffObsPeriodCheckscommitBean commitBean);

	List<OffObsCharacteristics> additionalCheckCharxecuteQuery(OffObsCharacteristics searchBean);

	List<OffObsCharacteristics> saveAdditionalCharecterData(OffObsCharacteristicsCommitBean commitBean);

	Integer saveOffenderObservationCheckComment(OffObsPeriodCheckscommitBean commitBean);

	List<OffObsPeriodChecks> getCommentExecuteQuery(OffObsPeriodChecks searchBean);

	List<ReferenceCodes> cellCondiLinkDomainRecordGroup(String observationType);
	
    List<ReferenceCodes> activityLinkDomainRecordGroup(String observationType);
	
	List<ReferenceCodes> commDetailLinkDomainRecordGroup(String observationType); 
	
	List<ReferenceCodes> notInLinkDomainRecordGroup(String observationType);

	Integer getOffenderLivningUnitIdCount(BigDecimal offenderBookId);

	List<OffenderObservationTypes> getObservationTypeRecordGroup();
	
	Integer getOffenderLivningUnitIdCountNotInLocation(BigDecimal offenderBookId, String userId, String agyLocId);

	Integer getCurrentStaffId(String userName);

	List<SystemProfiles> sysPflExecuteQuery();

}
