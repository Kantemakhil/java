package net.syscon.s4.inst.institutionalactivities.maintenance;

import java.text.ParseException;
import java.util.List;

import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.ProgramServicesProfiles;

/**
 * Interface OcmstoffRepository
 */
public interface OcmstoffRepository {
	List<ReferenceCodes> rgPsNeedsRecordGroup();

	ProgramServicesProfiles prgPrfGdDeleteProgramServicesProfiles(List<ProgramServicesProfiles> list);

	Integer prgPrfGdUpdateProgramServicesProfiles(List<ProgramServicesProfiles> list);

	List<ReferenceCodes> rgPsSexRecordGroup();

	ProgramServicesProfiles prgPrfGdInsertProgramServicesProfiles(List<ProgramServicesProfiles> list);

	List<ReferenceCodes> rgEthnicityRecordGroup();

	List<ReferenceCodes> rgPsAgeRangeRecordGroup();

	List<ReferenceCodes> rgPsOffGrpsRecordGroup();

	List<ProgramServicesProfiles> prgPrfGdExecuteQuery(ProgramServicesProfiles object);

	List<ProgramServicesProfiles> prgPrfIgExecuteQuery(ProgramServicesProfiles searchRecord);

	List<ProgramServicesProfiles> prgPrfXgExecuteQuery(ProgramServicesProfiles searchRecord);

	List<ProgramServicesProfiles> prgPrfFaExecuteQuery(ProgramServicesProfiles searchRecord);

	List<ProgramServicesProfiles> prgPrfRcExecuteQuery(ProgramServicesProfiles searchRecord);

	List<ProgramServicesProfiles> prgPrfAgExecuteQuery(ProgramServicesProfiles searchRecord);

	Integer getProfileExist(ProgramServicesProfiles object) throws ParseException;

}
