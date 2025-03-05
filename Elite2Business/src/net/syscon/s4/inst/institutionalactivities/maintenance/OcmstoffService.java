package net.syscon.s4.inst.institutionalactivities.maintenance;

import java.text.ParseException;
import java.util.List;

import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.ProgramServicesProfiles;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.ProgramServicesProfilesCommitBean;

/**
 * Interface OcmstoffService
 */
public interface OcmstoffService {

	List<ReferenceCodes> rgPsNeedsRecordGroup();

	List<ProgramServicesProfiles> prgPrfGdCommit(ProgramServicesProfilesCommitBean commitBean);

	List<ReferenceCodes> rgPsSexRecordGroup();

	List<ReferenceCodes> rgEthnicityRecordGroup();

	List<ReferenceCodes> rgPsAgeRangeRecordGroup();

	List<ReferenceCodes> rgPsOffGrpsRecordGroup();

	List<ProgramServicesProfiles> prgPrfGdExecuteQuery(ProgramServicesProfiles object);

	List<ProgramServicesProfiles> prgPrfXgExecuteQuery(ProgramServicesProfiles searchBean);

	List<ProgramServicesProfiles> prgPrfRcExecuteQuery(ProgramServicesProfiles searchBean);

	List<ProgramServicesProfiles> prgPrfAgExecuteQuery(ProgramServicesProfiles searchBean);

	List<ProgramServicesProfiles> prgPrfFaExecuteQuery(ProgramServicesProfiles searchBean);

	List<ProgramServicesProfiles> prgPrfIgExecuteQuery(ProgramServicesProfiles searchBean);

	Integer getProfileExist(ProgramServicesProfiles searchRecord) throws ParseException;

}
