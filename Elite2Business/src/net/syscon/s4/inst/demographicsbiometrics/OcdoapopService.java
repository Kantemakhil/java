package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.AddressesCommitBean;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;

/**
 * Interface OcdoapopService
 */
public interface OcdoapopService {
	ReferenceCodes addressWhenCreateRecord(ReferenceCodes paramBean);

	List<ReferenceCodes> rgStreetDirRecordGroup();

	List<ReferenceCodes> rgCountryRecordGroup();

	SystemProfiles citySystemProfile(SystemProfiles paramBean);

	List<Addresses> addressExecuteQuery(Addresses objAddresses);

	Object ocdoapopPreInsert();

	List<ReferenceCodes> rgCountyRecordGroup();

	Addresses addressCommit(AddressesCommitBean commitBean);

	List<Phones> addressKeyDelrec(Phones paramBean);

	List<ReferenceCodes> rgProvStateCodeRecordGroup();

	List<ReferenceCodes> rgSpecialNeedsRecordGroup();

	List<ReferenceCodes> rgCityRecordGroup();

	List<ReferenceCodes> rgTypeRecordGroup();

}
