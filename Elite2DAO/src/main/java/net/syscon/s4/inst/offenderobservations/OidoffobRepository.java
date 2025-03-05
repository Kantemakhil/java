package net.syscon.s4.inst.offenderobservations;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.inst.careinplacement.beans.OffObsPeriodChecks;
import net.syscon.s4.inst.offenderobservations.beans.OffenderObservationPeriods;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsCharacteristics;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationTypes;

public interface OidoffobRepository {

	List<OffenderObservationTypes> getObservatioTypeData(OffenderObservationTypes searchBean);

	List<OffenderObservationPeriods> getOffenderPeriodExecuteQuery(OffenderObservationPeriods searchBean);
	
	Integer offenderObservationPeriodInsertData(List<OffenderObservationPeriods> insertList);

	Integer offenderObservationPeriodUpdateData(List<OffenderObservationPeriods> updateList);

	Integer offenderObservationPeriodDeleteData(List<OffenderObservationPeriods> deleteList);
	
	Integer offenderObservationPeriodCheckInsertData(List<OffObsPeriodChecks> insertList);
	
	Long getSequence();

	List<OffObsPeriodChecks> getOffenderPeriodCheckExecuteQuery(OffObsPeriodChecks searchBean);

	Integer offenderObservationCheckUpdateData(List<OffObsPeriodChecks> insertList);

	List<OffObsCharacteristics> getAdditionalCherctersticsExecuteQuery(OffObsCharacteristics searchBean);

	Integer offObsDeleteAdditionalCharctData(long checkId,String modifyUserId);

	Integer offObsCharacteristicsInsertCharctData(List<OffObsCharacteristics> insertList);

	Integer saveOffenderObservationCheckComment(List<OffObsPeriodChecks> updateList);

	List<OffObsPeriodChecks> getCommentExecuteQuery(OffObsPeriodChecks searchBean);

	List<ReferenceCodes> cellCondiLinkDomainRecordGroup(String observationType);
	
	List<ReferenceCodes> activityLinkDomainRecordGroup(String observationType);
	
	List<ReferenceCodes> commDetailLinkDomainRecordGroup(String observationType); 
	
	List<ReferenceCodes> notInLinkDomainRecordGroup(String observationType);

	Integer getOffenderLivningUnitIdCount(BigDecimal offenderBookId);

	List<OffenderObservationTypes> getObservationTypeRecordGroup();

	Integer offObsDeleteAddCharData(List<OffObsCharacteristics> deleteList);
	
	BigDecimal getMaximumObsPeriodSeqBookId(BigDecimal offenderBookId);
	
	Integer getOffenderLivningUnitIdCountNotInLocation(BigDecimal offenderBookId, String username, String agyLocId);

	Integer offenderObservationCheckUpdateStartDate(List<OffObsPeriodChecks> checkDetailsList);

	Integer getCurrentStaffId(String userName);

	List<SystemProfiles> sysPflExecuteQuery();

}
