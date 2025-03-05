package net.syscon.s4.sa;

import java.util.List;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.StaffAccessibleCaseloads;
import net.syscon.s4.im.beans.StaffAccessibleCaseloadsCommitBean;

/**
 * Interface OumcpassService
 */
public interface OumcpassService {
	Integer staffCommit(final String userId);

	List<StaffAccessibleCaseloads> staffAcExecuteQuery(StaffAccessibleCaseloads object);

	Integer staffAcCommit(StaffAccessibleCaseloadsCommitBean object);

	List<StaffMembers> staffExecuteQuery(StaffMembers object);

	boolean authenticate(String username, String password);

	Integer changePassword(String userName, String oldPassword, String newPassword, String loggedUserName);

	String validatePassword(String newPassword);

}
