package net.syscon.s4.globalrbac;

import java.util.List;

import net.syscon.s4.common.beans.StaffMembers;

/**
 * Interface OumpersdRepository
 */
public interface OumpersdRepository {

	List<StaffMembers> staffExecuteQuery(final Integer staffId);

	Integer staffUpdateStaffMembers(List<StaffMembers> lstStaffMembers);

}
