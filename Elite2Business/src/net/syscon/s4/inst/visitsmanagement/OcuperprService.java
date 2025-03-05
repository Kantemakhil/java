package net.syscon.s4.inst.visitsmanagement;

import java.util.List;

import net.syscon.s4.im.beans.PersonProfiles;
import net.syscon.s4.im.beans.PersonProfilesCommitBean;
import net.syscon.s4.im.beans.ProfileCodes;

/**
 * Interface OcuperprService
 */
public interface OcuperprService {
	List<ProfileCodes> rgProfileCodeRecordGroup(String profileType);

	Integer profilesCommit(PersonProfilesCommitBean commitBean);

	List<PersonProfiles> profilesExecuteQuery(PersonProfiles objPersonProfiles);

	List<PersonProfiles> insertProfilesTypes(Integer personId, String userName);

}
