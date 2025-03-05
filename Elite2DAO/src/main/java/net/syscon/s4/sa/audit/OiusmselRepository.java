package net.syscon.s4.sa.audit;

import java.util.List;

import net.syscon.s4.common.beans.StaffMembers;

/**
 * Interface OiusmselRepository
 */
public interface OiusmselRepository {
	List<StaffMembers> staffMembersExecuteQuery(StaffMembers objVStaffMember);

}
