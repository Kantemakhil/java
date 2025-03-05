package net.syscon.s4.global;

import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.HelpMedia;
import net.syscon.s4.common.beans.MenuNode;
import net.syscon.s4.common.beans.MenuSecurities;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.WorkFlowFolders;
import net.syscon.s4.common.beans.WorkflowScreens;
import net.syscon.s4.common.beans.*;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ReferenceCodes;

/**
 * Interface Omss40Repository
 */
public interface Omss40Repository {

	List<MenuSecurities> mainpopList(MenuSecurities paramBean);

	List<WorkFlowFolders> buildworkFlowmenurgmainWorkflowCur(WorkflowScreens paramBean);

	WorkflowScreens buildworkFlowmenurgsubWorkflowCur(WorkflowScreens paramBean);

	List<MenuSecurities> whenNewFormInstanceMenuNameCur(MenuSecurities paramBean);

	String getCurrentCaseload(String pUser);

	List<SystemProfiles> preFormPtypeClient(SystemProfiles paramBean);

	List<SystemProfiles> preFormPtypeSys(SystemProfiles paramBean);

	List<Caseloads> getCaseloads(final StaffMembers searchBean);
	
	List<VHeaderBlock> getCaseLoadAgencies(String userId);

	String changeWorkingCaseLoad(String lvCaseloadId);

	List<ReferenceCodes> cgfkStaffDspDescriptionRecordGroup();
	
	List<MenuNode> getMenus(String userId);
	
	List<ModulePrivileges> getUserRolesInfo(String userId);
	
	List<ProfileCodes> searchProfileCodes(List<String> profileType);
	
	List<VHeaderBlock> getAssignedOffenderList(String userId,String currentCaseLoadType);
	
	List<VHeaderBlock> updateRecentOffenderList(VHeaderBlock paramBean);
	
	List<VHeaderBlock> getRecentOffenderList(String userId, String caseLoadId, Integer count);
	
	String getServerTime();
	
	String getCurrentCaseloadType(String userId);
	
	StaffMembers getCurrentStaffDetail(String userId);
	
	List<HelpMedia> getHelpMedia(String moduleName); 
	int iwpOnScreen(String moduleName) ;
	String getEncryPassword(String userName);
	
	String getUserId(String mailId);
	
	String getEncryptPwd(String userId);
	
	String getStatus(String userId);
	
	String getmailId(String userId);

	List<ProfileCodes> specficSystemProfile();
}
