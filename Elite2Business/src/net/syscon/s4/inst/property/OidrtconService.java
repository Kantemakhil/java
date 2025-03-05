package net.syscon.s4.inst.property;

import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyContainersCommitBean;

/**
 * Interface OidrtconService
 */
public interface OidrtconService {
	List<SysDual> cgwhenNewFormInstance();

	OffenderPptyContainers offConCommit(OffenderPptyContainersCommitBean commitBean);

	List<ReferenceCodes> cgfkchkOffConOffConRef(ReferenceCodes paramBean);

	List<OffenderPptyContainers> offConExecuteQuery(OffenderPptyContainers obj);

	List<ReferenceCodes> cgfklkpOffConOffConRef(ReferenceCodes paramBean);

	Map<String, Object> cgfkchkOffConOffConOff(OffenderBookings paramBean);

	List<AgencyLocations> oidrtconRecievedFromLov();

}
