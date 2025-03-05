package net.syscon.s4.inst.visitsmanagement;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.booking.beans.OffenderContactPersonsCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.VOffContactPersons;

/**
 * Interface OcuavisnService
 */
public interface OcuavisnService {
	Integer vOffAuthVisCommit(OffenderContactPersonsCommitBean commitBean);

	List<VOffContactPersons> vOffAuthVisExecuteQuery(VOffContactPersons objVOffContactPersons);

	List<ReferenceCodes> rgRelationshipTypeRecordGroup(String contactType);

	List<ReferenceCodes> rgContactTypeRecordGroup();

	VOffContactPersons getGlobalRestriction(Integer personId, Integer offenderBookId, Long visitDate);
	List<ReferenceCodes> rgRelationshipTypeTotalRecordGroup();

}
