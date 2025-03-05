package net.syscon.s4.inst.property;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.PropertyStorages;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyContainersCommitBean;

/**
 * Interface OiipclocService
 * 
 */
public interface OiipclocService {
	List<Object> cgwhenNewFormInstance();

	List<PropertyStorages> cgfklkpOffConOffConPpty(PropertyStorages paramBean);

	List<AgencyInternalLocations> cgfkchkOffConOffConPpty(AgencyInternalLocations paramBean);

	Integer offConCommit(OffenderPptyContainersCommitBean commitBean);

	List<OffenderPptyContainers> offConExecuteQuery(OffenderPptyContainers objOffenderPptyContainers);

	VHeaderBlock cgfkchkOffConOffConVPhe(VHeaderBlock paramBean);

	List<ReferenceCodes> rgContainerCodeRecordGroup();

	List<AgencyInternalLocations> rgDescriptionRecordGroup(String caseloadId);

}
