package net.syscon.s4.global;

import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.AuditLog;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.EliteViewLog;
import net.syscon.s4.common.beans.EmailUser;
import net.syscon.s4.common.beans.HelpMedia;
import net.syscon.s4.common.beans.MenuSecurities;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.UserRoleInfo;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.WorkFlowFolders;
import net.syscon.s4.common.beans.WorkflowScreens;
import net.syscon.s4.im.beans.ProfileCodes;

/**
 * Interface Omss40Service
 */
public interface Omss40Service {

	List<MenuSecurities> whenNewFormInstanceMenuNameCur(MenuSecurities paramBean);

	WorkflowScreens buildworkFlowmenurgsubWorkflowCur(WorkflowScreens paramBean);

	List<MenuSecurities> mainpopList(MenuSecurities paramBean);

	List<SystemProfiles> preFormPtypeClient(SystemProfiles paramBean);

	List<SystemProfiles> preFormPtypeSys(SystemProfiles paramBean);

	List<WorkFlowFolders> buildworkFlowmenurgmainWorkflowCur(WorkflowScreens paramBean);

	String getCurrentCaseload(String pUser);
	
	String getCurrentCaseloadType(String userId);
		
	String changeWorkingCaseLoad(String lvCaseloadId, String userName);

	List<Caseloads> getCaseloads(final StaffMembers searchBean);
	
	Map<String, List<String>> getCaseLoadAgencies(String userId);
		
	UserRoleInfo getUserRoleInfo(String userId);
	
	List<ProfileCodes> searchProfileCodes(List<String> profileType);
	List<VHeaderBlock> getAssignedOffenderList(String userId,String currentCaseLoadType);
	List<HelpMedia> getHelpMedia(String moduleName);
	List<VHeaderBlock> updateRecentOffenderList(VHeaderBlock paramBean);
	
	List<VHeaderBlock> getRecentOffenderList(String userId, String caseLoadId , Integer count);
	
	String getServerTime();
	
	StaffMembers getCurrentStaffDetail(String userId);
	int iwpOnScreen(String moduleName); 
	
	EmailUser getDecryptPwd(String mailId);
	
	String getUserStatus(String userId);
	
	String getmailId(String userId);

	List<ProfileCodes> specficSystemProfile();

	Map<String, Object> getApplicationStatus();

	String getAutomationUrl();

	String getSentenceUrl();

	Integer auditPage(EliteViewLog auditLog);
	
}
