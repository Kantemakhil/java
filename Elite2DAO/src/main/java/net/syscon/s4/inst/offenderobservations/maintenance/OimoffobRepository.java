package net.syscon.s4.inst.offenderobservations.maintenance;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsCharacteristics;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsZoneDetails;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationTypes;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationZones;

public interface OimoffobRepository {

	List<OffenderObservationTypes> observationTypesExecuteQuery();

	Integer offenderObservationInsertData(List<OffenderObservationTypes> insertList);

	Integer offenderObservationUpdateData(List<OffenderObservationTypes> updateList);

	Integer offenderObservationDeleteData(List<OffenderObservationTypes> deleteList);

	
	Integer offenderObservationInsertZoneData(List<OffenderObservationZones> insertList);

	Integer offenderObservationUpdateZoneData(List<OffenderObservationZones> updateList);

	Integer offenderObservationDeleteZoneData(List<OffenderObservationZones> deleteList);

	List<OffenderObservationZones> getZoneDetailsExecuteQuery(OffenderObservationZones searchBean);

	List<OffObsZoneDetails> getZoneDetailsHousingExecuteQuery(OffObsZoneDetails searchBean);

	Integer offenderObservationInsertZoneHousingData(List<OffObsZoneDetails> insertList);

	Integer offenderObservationUpdateZoneHousingData(List<OffObsZoneDetails> updateList);

	Integer offenderObservationDeleteZoneHousingData(List<OffObsZoneDetails> deleteList);

	Integer offObsCharacteristicsInsertCharctData(List<OffObsCharacteristics> insertList);

	Integer offObsCharacteristicsUpdateCharctData(List<OffObsCharacteristics> updateList);

	Integer offObsCharacteristicsDeleteCharctData(List<OffObsCharacteristics> deleteList);

	Integer offObsDeleteCharctData(String observationType, String characteristicsType);

	List<OffObsCharacteristics> observationCharectersticExecuteQuery(OffObsCharacteristics searchBean);
	
	List<AgencyLocations> rgAgyLocRecordGroup(String userName);
	
	List<OffObsZoneDetails> getHousingLocDescription(BigDecimal internalLocationId);

	Integer updateCharecterFlagsData(OffenderObservationTypes observationCheckDetailTypeBean);

	Integer getObservationPeriodDeleteCount(OffenderObservationTypes obj);

	
	BigDecimal getObsTypeVersionIdSeq();

	Integer offenderObservationCommonInsertData(List<OffenderObservationTypes> insertList);

	Integer offObsDetailsCommonInsertCharctData(List<OffObsCharacteristics> detailTypeCodeList);

	Integer updateActiveFlag(List<OffenderObservationTypes> updateList);

	List<OffenderObservationTypes> getActiveObservationTypeList(OffenderObservationTypes obj);

}
