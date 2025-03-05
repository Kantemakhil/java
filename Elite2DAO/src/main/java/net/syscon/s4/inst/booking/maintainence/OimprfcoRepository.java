package net.syscon.s4.inst.booking.maintainence;

import java.util.List;

import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;

/**
 * Interface OimprfcoRepository
 */
public interface OimprfcoRepository {

	List<ProfileTypes> pflTypeExecuteQuery(ProfileTypes objProfileTypes);

	List<ProfileCodes> pflCodeExecuteQuery(ProfileCodes objProfileCodes);

	Integer pflCodeInsertProfileCodes(List<ProfileCodes> lstProfileCodes);

	Integer pflCodeUpdateProfileCodes(List<ProfileCodes> lstProfileCodes);

	Integer pflCodeDeleteProfileCodes(List<ProfileCodes> lstProfileCodes);

	int checkProfileCodes(String profileCode);

	String getUserId(String createUserId);

}
