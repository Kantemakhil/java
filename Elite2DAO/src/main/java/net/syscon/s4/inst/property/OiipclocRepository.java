package net.syscon.s4.inst.property;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.PropertyStorages;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;

/**
 * Interface OiipclocRepository
 */
public interface OiipclocRepository {
	List<PropertyStorages> cgfklkpOffConOffConPpty(PropertyStorages paramBean);

	List<Object> cgwhenNewFormInstance(SysDual paramBean);

	VHeaderBlock cgfkchkOffConOffConVPhe(VHeaderBlock paramBean);

	List<OffenderPptyContainers> offConExecuteQuery(OffenderPptyContainers objOffenderPptyContainers);

	List<ReferenceCodes> rgContainerCodeRecordGroup();

	List<AgencyInternalLocations> rgDescriptionRecordGroup(String caseloadId);

	List<AgencyInternalLocations> cgfkchkOffConOffConPpty(AgencyInternalLocations paramBean);

}
