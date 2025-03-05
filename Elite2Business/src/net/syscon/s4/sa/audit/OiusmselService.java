package net.syscon.s4.sa.audit;

import java.util.List;

import net.syscon.s4.common.beans.StaffMembers;

/**
 * Interface OiusmselService
 */
public interface OiusmselService {
	List<StaffMembers> staffMembersExecuteQuery(StaffMembers objVStaffMember);

}
