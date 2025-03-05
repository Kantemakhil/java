package net.syscon.s4.globalconfiguration;

import java.util.List;

import net.syscon.s4.common.beans.ADUser;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.sa.usersystemsecurity.beans.OmsUsersList;

public interface OumucreatRepository {

	boolean createUser(String userName, String passWord, String defaultTableSpace, String tempTableSpace);

	Integer getUserDeatils(String userName);

	boolean grantPermToUser(String userName);

	boolean connectPermToUser(String userName, String grantUser);

	boolean tagUserToUser(String userName);

	Integer saveEncryptPwd(String userName, String passWord, String createUserId);

	Integer insertOmsUsersList(OmsUsersList inputBean);

	Integer verifyEmailId(String emailId);

	List<StaffMembers> verifyEmailIdbyStaffId(String emailId, Integer staffId);

	String getpwd(String mailId);

	List<ADUser> getADUserDeatils();

	Integer UpdateOmsUser(OmsUsersList inputBean);
	
	Integer ResetADUser(List<OmsUsersList> userList);

}
