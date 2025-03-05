package net.syscon.s4.inst.booking.maintainence;

import java.util.List;

import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileCodesCommitBean;
import net.syscon.s4.im.beans.ProfileTypes;

/**
 * Interface OimprfcoService
 */
public interface OimprfcoService {
	List<ProfileTypes> pflTypeExecuteQuery(ProfileTypes objProfileTypes);

	List<ProfileCodes> pflCodeExecuteQuery(ProfileCodes objProfileCodes);

	List<ProfileCodes> pflCodeCommit(ProfileCodesCommitBean commitBean);

	int checkProfileCodes(String profileCodes);

}
