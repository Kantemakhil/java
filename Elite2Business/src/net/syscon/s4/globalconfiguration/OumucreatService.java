package net.syscon.s4.globalconfiguration;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.UserCreation;

/**
 * Interface OumacaseService
 */
public interface OumucreatService {

	Integer createUser(UserCreation userDeatils);
	
	Integer getUserDetails(String userName);

	Integer verifyEmailId(String emailId);
	
	List<StaffMembers> verifyEmailIdbyStaffId(String emailId, Integer staffId);
	
	Integer createInsightsUser(UserCreation userDeatils);
	
	String getAuthToken();

	void migrateADUser(String userName);
	
	List<ReferenceCodes> getInsightsUserGroups();
	
	Integer getInsightsUserId(String mailId);
	
	Integer addUserToGroup(List<String> groupIdList, int userId);
	
	int resetADUser(String loggedInUser);
}
