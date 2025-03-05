package net.syscon.s4.sa;

import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.StaffAccessibleCaseloads;

/**
 * Interface OumcpassRepository
 */
public interface OumcpassRepository {
	List<StaffAccessibleCaseloads> staffAcExecuteQuery(StaffAccessibleCaseloads object);

	List<StaffMembers> staffExecuteQuery(StaffMembers object);

	Integer staffUpdateStaffMembers(String userId);

	Integer changePassword(String userId, String oldPassword, String newPassword);

	Map<String, Object> validatePassword(String newPassword);

	boolean authenticate(String username, String password);

}
