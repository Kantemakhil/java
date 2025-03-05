package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;

/**
 * Interface OcdoapopRepository
 * 
 */
public interface OcdoapopRepository {
	SystemProfiles citySystemProfile(SystemProfiles paramBean);

	List<ReferenceCodes> rgStreetDirRecordGroup();

	List<ReferenceCodes> rgCountryRecordGroup();

	Integer addressInsertAddresses(List<Addresses> lstAddresses);

	Object ocdoapopPreInsert();

	ReferenceCodes validateCityInfoCode(ReferenceCodes paramBean);

	ReferenceCodes validateCityInfoDescription(ReferenceCodes paramBean);

	List<Phones> addressKeyDelrec(Phones paramBean);

	List<Addresses> addressExecuteQuery(Addresses objAddresses);

	Integer addressDeleteAddresses(List<Addresses> lstAddresses);

	List<ReferenceCodes> rgCountyRecordGroup();

	ReferenceCodes addressWhenCreateRecord(ReferenceCodes paramBean);

	List<ReferenceCodes> rgProvStateCodeRecordGroup();

	List<ReferenceCodes> rgSpecialNeedsRecordGroup();

	List<ReferenceCodes> rgCityRecordGroup();

	List<ReferenceCodes> rgTypeRecordGroup();

	Integer addressUpdateAddresses(List<Addresses> lstAddresses);

}
