package net.syscon.s4.inst.visitsmanagement;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.visitsmanagement.beans.VOffContactPersons;

/**
 * Interface OcuavisnRepository
 */
public interface OcuavisnRepository {
	Integer vOffAuthVisInsertVOffContactPersons(List<OffenderContactPersons> lstVOffContactPersons);

	List<VOffContactPersons> vOffAuthVisExecuteQuery(VOffContactPersons objVOffContactPersons);

	Integer vOffAuthVisUpdateVOffContactPersons(List<OffenderContactPersons> lstVOffContactPersons);

	List<ReferenceCodes> rgRelationshipTypeRecordGroup(String contactType);

	List<ReferenceCodes> rgContactTypeRecordGroup();

	VOffContactPersons populateVisitorDetails(Integer offenderBookId, Integer personId, Integer offenderContactPersonId,
			Date visitDate);

	Integer getContactPersonId();
	 List<ReferenceCodes> rgRelationshipTypeTotalRecordGroup();
	 String getConatctTypeDescription(String contactType);
	 String getRelationTypeDescription(String relationType);
}
