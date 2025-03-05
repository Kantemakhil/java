package net.syscon.s4.inst.booking.maintainence;

import java.util.HashMap;
import java.util.List;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.inst.booking.beans.ContactPersonTypes;
import net.syscon.s4.inst.booking.beans.ContactPersonTypesCommitBean;

/**
 * Interface OimctactService
 */
public interface OimctactService {
	Integer contactPersonTypesCommit(ContactPersonTypesCommitBean commitBean);

	List<StaffMembers> contactPersonTypesPostQuery(StaffMembers paramBean);

	List<ContactPersonTypes> contactPersonTypesExecuteQuery(ContactPersonTypes isContPers);

	List<HashMap<String, Object>> rgRelationshipTypeRecordGroup();

	List<HashMap<String, Object>> rgContactTypeRecordGroup();

}
