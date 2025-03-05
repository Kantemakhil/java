package net.syscon.s4.inst.booking.maintainence;

import java.util.List;

import net.syscon.s4.common.beans.ProfileCategory;
import net.syscon.s4.im.beans.ProfileCategoriesCommitBean;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.im.beans.ProfileTypesCommitBean;

/**
 * Interface OimprfcaService
 */
public interface OimprfcaService {
	List<ProfileCategory> pflCatCommit(ProfileCategoriesCommitBean commitBean);

	List<ProfileTypes> pflTypeExecuteQuery(ProfileTypes objProfileTypes);

	List<ProfileCategory> pflCatExecuteQuery(ProfileCategory obj);

	List<ProfileTypes> pflTypeCommit(ProfileTypesCommitBean commitBean);
}
