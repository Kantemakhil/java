package net.syscon.s4.globalrbac;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.UserCreation;
import net.syscon.s4.common.beans.dao.RecordGroup;
import net.syscon.s4.globalconfiguration.OumucreatService;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CaseloadAgencyLocationsCommitBean;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.im.beans.StaffAccessibleCaseloads;
import net.syscon.s4.im.beans.StaffAccessibleCaseloadsCommitBean;
import net.syscon.s4.im.beans.StaffMemberRolesCommitBean;
import net.syscon.s4.im.beans.StaffMembersCommitBean;
import net.syscon.s4.pkgs.common.CommonService;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * class OumusersController
 */
@EliteController
public class OumusersController {

	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OumusersController.class.getName());
	
	@Autowired
	private OumusersService oumusersService;
	@Autowired
	private ProsmainService prosmainService;
	@Autowired
	private OumucreatService oumucreatService;
	@Autowired
	private CommonService commonService;
	/**
	 *Fetching the record from database table
	 *@Param searchBean
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumusers/staffExecuteQuery", method=RequestMethod.POST)
	public List<StaffMembers> staffExecuteQuery(@RequestBody final StaffMembers searchBean) {
		List<StaffMembers> searchResult = new ArrayList<>();
		final StaffMembers bean = new StaffMembers();
		try {
			searchResult = oumusersService.staffExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In staffExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 *Performing basic Oracle form functions i.e. insert,delete, update into the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oumusers/staffCommit",method=RequestMethod.POST)
	public @ResponseBody String staffCommit(@RequestBody final StaffMembersCommitBean commitBean) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		String liReturn = null;
		try {
			liReturn = oumusersService.staffCommit(commitBean);
		}catch(Exception e){
			logger.error("In staffCommit method : ", e);
		}
		return liReturn;
	}
	
	/**
	 * 
	 * @param userDeatils
	 * @return
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumusers/createInsightsUser", method = RequestMethod.POST)
	public Integer createInsightsUser(@RequestBody UserCreation userDeatils) {
		Integer result = null;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			userDeatils.setCreateUserId(userName);
			result=oumucreatService.createInsightsUser(userDeatils);
		} catch (Exception e) {
			logger.error("Create Insight User Failled - ", e);
			result = 0;
		}
		return result;
	}

	/**
	 *getting rgStaffAssignedCaseload LOV values 
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/oumusers/rgStaffAssignedCaseloadRecordGroup",method=RequestMethod.GET)
	public List<RecordGroup> rgStaffAssignedCaseloadRecordGroup() {
		List<RecordGroup> recordList =new ArrayList<RecordGroup>();
		final RecordGroup bean = new RecordGroup();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oumusersService.rgStaffAssignedCaseloadRecordGroup();
		} catch(Exception e){
			logger.error("In rgStaffAssignedCaseloadRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgStaffMemberRolesRole LOV values 
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/oumusers/rgStaffMemberRolesRoleRecordGroup",method=RequestMethod.GET)
	public List<OmsRoles> rgStaffMemberRolesRoleRecordGroup() {
		List<OmsRoles> recordList =new ArrayList<OmsRoles>();
		final OmsRoles bean = new OmsRoles();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oumusersService.rgStaffMemberRolesRoleRecordGroup();
		} catch(Exception e){
			logger.error("In rgStaffMemberRolesRoleRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgStaffAcCaseloadId LOV values 
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/oumusers/rgStaffAcCaseloadIdRecordGroup",method=RequestMethod.GET)
	public List<Caseloads> rgStaffAcCaseloadIdRecordGroup() {
		List<Caseloads> recordList =new ArrayList<Caseloads>();
		final Caseloads bean = new Caseloads();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oumusersService.rgStaffAcCaseloadIdRecordGroup();
		} catch(Exception e){
			logger.error("In rgStaffAcCaseloadIdRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchBean
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumusers/staffAcExecuteQuery", method=RequestMethod.POST)
	public List<StaffAccessibleCaseloads> staffAcExecuteQuery(@RequestBody final StaffAccessibleCaseloads searchBean) {
		List<StaffAccessibleCaseloads> searchResult = new ArrayList<>();
		final StaffAccessibleCaseloads bean = new StaffAccessibleCaseloads();
		try {
			searchResult = oumusersService.staffAcExecuteQuery(searchBean);
		} catch (Exception e) {
			
			logger.error("In staffAcExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 *Performing basic Oracle form functions i.e. insert,delete, update into the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oumusers/staffAcCommit",method=RequestMethod.POST)
	public @ResponseBody Integer staffAcCommit(@RequestBody final StaffAccessibleCaseloadsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumusersService.staffAcCommit(commitBean);
		}catch(Exception e){
			logger.error("In staffAcCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchBean
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumusers/staffMemberRolesExecuteQuery", method=RequestMethod.POST)
	public List<StaffMemberRoles> staffMemberRolesExecuteQuery(@RequestBody final StaffMemberRoles searchBean) {
		List<StaffMemberRoles> searchResult = new ArrayList<>();
		final StaffMemberRoles bean = new StaffMemberRoles();
		try {
			searchResult = oumusersService.staffMemberRolesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In staffMemberRolesExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 *Performing basic Oracle form functions i.e. insert,delete, update into the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oumusers/staffMemberRolesCommit",method=RequestMethod.POST)
	public @ResponseBody Integer staffMemberRolesCommit(@RequestBody final StaffMemberRolesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumusersService.staffMemberRolesCommit(commitBean);
		}catch(Exception e){
			logger.error("In staffMemberRolesCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumusers/calExecuteQuery", method=RequestMethod.POST)
	public List<CaseloadAgencyLocations> calExecuteQuery(@RequestBody final CaseloadAgencyLocations searchBean) {
		List<CaseloadAgencyLocations> searchResult = new ArrayList<>();
		final CaseloadAgencyLocations bean = new CaseloadAgencyLocations();
		try {
			searchResult = oumusersService.calExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In calExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 *Performing basic Oracle form functions i.e. insert,delete, update into the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oumusers/calCommit",method=RequestMethod.POST)
	public @ResponseBody Integer calCommit(@RequestBody final CaseloadAgencyLocationsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumusersService.calCommit(commitBean);
		}catch(Exception e){
			logger.error("In calCommit method : ", e);
		}
		return liReturn;
	}
	/**
	 *Fetching the record from database table
	 *@Param searchBean
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumusers/cgfkchkCalCsldAlAgyLoc", method=RequestMethod.POST)
	public List<AgencyLocations> cgfkchkCalCsldAlAgyLoc(@RequestBody final AgencyLocations searchBean) {
		List<AgencyLocations> searchResult = new ArrayList<>();
		final AgencyLocations bean = new AgencyLocations();
		try {
			searchResult = oumusersService.cgfkchkCalCsldAlAgyLoc(searchBean);
		} catch (Exception e) {
			logger.error("In cgfkchkCalCsldAlAgyLoc method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}
	/**
	 *Fetching the record from database table
	 *@Param searchBean
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumusers/imageExecuteQuery", method=RequestMethod.POST)
	public List<Images> imageExecuteQuery(@RequestBody final StaffMembers searchBean) {
		List<Images> bean = new ArrayList<>();
		try {
			bean = oumusersService.imageExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In imageExecuteQuery method : ", e);
		}
		return bean;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumusers/insightsExecuteQuery", method=RequestMethod.POST)
	public List<UserCreation> insightsExecuteQuery(@RequestBody final StaffMembers searchBean) {
		List<UserCreation> searchResult = new ArrayList<>();
		final UserCreation bean = new UserCreation();
		try {
			searchResult = oumusersService.insightsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In insightsExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oumusers/resetPassword", method=RequestMethod.POST)
	public  StaffMembers resetPassword(@RequestBody StaffMembers staffMember, @RequestHeader HttpHeaders headers) {
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		final StaffMembers bean = new StaffMembers();
		try {
			if(staffMember!=null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				staffMember.setModifyUserId(userName);
				staffMember = oumusersService.resetPassword(staffMember);
				StaffMembersCommitBean commitBean = new StaffMembersCommitBean();
				List<StaffMembers> staffList = new ArrayList<StaffMembers>();
				staffList.add(staffMember);
				commitBean.setUpdateList(staffList);
				if(staffMember.getPasswordReturnVal() == 1 && staffMember.getMailId() != null && staffMember.getMailId() != "") {
					prosmainService.enableTriggers(commitBean, authorization, "84");
				}
			}
		} catch (Exception e) {
			logger.error("In ResetPassword method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return staffMember;
	}
	
	@RequestMapping(value = "/oumusers/updateUsersInsGroups", method = RequestMethod.POST)
	public Integer updateUsersInsGroups(@RequestBody UserCreation userDeatils) {
		Integer result = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			userDeatils.setCreateUserId(userName);
			int userId = oumucreatService.getInsightsUserId(userDeatils.getMailId().toLowerCase());
			List<String> groupIdList = oumusersService.getGroupIdList(userDeatils.getMailId().toLowerCase());
			int deleteListResp = oumusersService.deleteUserFromGroups(groupIdList, userId);
			if(deleteListResp == 1) {
				result=oumucreatService.addUserToGroup(userDeatils.getInsightsGropId(), userId);
			}
		} catch (Exception e) {
			logger.error("createUser", e);
			result = 0;
		}
		return result;
	}
	
	@RequestMapping(value = "/oumusers/removeInsightUser", method = RequestMethod.POST)
	public Integer removeInsightUser(@RequestBody UserCreation userDeatils) {
		Integer result = null;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			userDeatils.setCreateUserId(userName);
			result=oumusersService.removeInsightUser(userDeatils);
		} catch (Exception e) {
			logger.error("createUser", e);
			result = 0;
		}
		return result;
	}
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OUMUSERS");
	}
}