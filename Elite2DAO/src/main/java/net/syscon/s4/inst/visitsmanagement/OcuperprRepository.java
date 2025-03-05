package net.syscon.s4.inst.visitsmanagement;

import java.util.List;

import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.PersonProfiles;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;

/**
 * Interface OcuperprRepository
 */
public interface OcuperprRepository {
	List<ProfileCodes> rgProfileCodeRecordGroup(String profileType);

	Integer profilesUpdatePersonProfiles(List<PersonProfiles> lstPersonProfiles);

	List<PersonProfiles> profilesExecuteQuery(PersonProfiles objPersonProfiles);

	Object profilesPreInsert(Dual paramBean);

	ProfileTypes getProfileTypeDesc(PersonProfiles personProfiles);

	ProfileCodes getProfileCodeDesc(PersonProfiles personProfiles);

	List<PersonProfiles> insertprofilesTypes(Integer personId);

}
