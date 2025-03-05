package net.syscon.s4.inst.booking.maintainence;

import java.util.List;

import net.syscon.s4.common.beans.ProfileCategory;
import net.syscon.s4.im.beans.ProfileTypes;

/**
 * Interface OimprfcaRepository
 */
public interface OimprfcaRepository {

	Integer pflCatUpdateProfileCategories(List<ProfileCategory> lstProfCat);

	Integer pflTypeInsertProfileTypes(List<ProfileTypes> lstProfileTypes);

	Integer pflCatInsertProfileCategories(List<ProfileCategory> lstProfCat);

	List<ProfileTypes> pflTypeExecuteQuery(ProfileTypes objProfileTypes);

	List<ProfileCategory> pflCatExecuteQuery(ProfileCategory lstProfCat);

	Integer pflTypeUpdateProfileTypes(List<ProfileTypes> lstProfileTypes);

	Integer pflCatDeleteProfileCategories(List<ProfileCategory> lstProfCat);

}
