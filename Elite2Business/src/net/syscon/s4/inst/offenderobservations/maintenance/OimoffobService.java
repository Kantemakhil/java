package net.syscon.s4.inst.offenderobservations.maintenance;

import java.util.List;

import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsCharacteristics;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsCharacteristicsCommitBean;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsZoneDetails;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsZoneDetailsCommitBean;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationTypes;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationTypesCommitBean;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationTypesSaveCommitBean;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationZones;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationZonesCommitBean;

public interface OimoffobService {

	List<OffenderObservationTypes> observationTypesExecuteQuery();

	List<OffenderObservationTypes> obserVationTypeCommit(OffenderObservationTypesCommitBean commitBean);

	List<OffenderObservationZones> zoneDataSaveForm(OffenderObservationZonesCommitBean commitBean);

	List<OffenderObservationZones> getZoneDetailsExecuteQuery(OffenderObservationZones searchBean);

	List<OffObsZoneDetails> getZoneDetailsHousingExecuteQuery(OffObsZoneDetails searchBean);

	List<OffObsZoneDetails> zoneHousingDataCommitForm(OffObsZoneDetailsCommitBean commitBean);

	List<OffObsCharacteristics> saveCharecterDetails(OffObsCharacteristicsCommitBean commitBean);

	List<OffObsCharacteristics> observationCharectersticExecuteQuery(OffObsCharacteristics searchBean);
	
	 List<AgencyLocations> rgAgyLocRecordGroup(String userName);

	List<OffenderObservationTypes> saveCommonDetails(OffenderObservationTypesSaveCommitBean commitBean);

}
