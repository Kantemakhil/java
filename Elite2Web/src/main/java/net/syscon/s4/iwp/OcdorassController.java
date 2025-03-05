package net.syscon.s4.iwp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.cm.primaryofficeragentassignment.beans.ExtOwnershipTransfer;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.ExtOwnershipTransferCommitBean;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VOmTeamMembers;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VOmTeamMembersCommitBean;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderBookingsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * class OcdorassController
 * 
 */
@EliteController
public class OcdorassController {
	@Autowired
	private OcdorassService ocdorassService;
	
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdorassController.class.getName());

	/**
	 * getting rgAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdorass/rgAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocIdRecordGroup(final String caseLoadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ocdorassService.rgAgyLocIdRecordGroup(caseLoadId);
		} catch (Exception e) {
			AgencyLocations obj = new AgencyLocations();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgPosition LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdorass/rgPositionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPositionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdorassService.rgPositionRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgRole LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdorass/rgRoleRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgRoleRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdorassService.rgRoleRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgScheduleType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdorass/rgScheduleTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgScheduleTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdorassService.rgScheduleTypeRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgSexCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdorass/rgSexCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSexCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdorassService.rgSexCodeRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgTeam LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdorass/rgTeamRecordGroup", method = RequestMethod.GET)
	public List<Teams> rgTeamRecordGroup(@RequestParam final String sealFlag) {
		List<Teams> recordList = new ArrayList<Teams>();
		try {
			recordList = ocdorassService.rgTeamRecordGroup(sealFlag);
		} catch (Exception e) {
			Teams obj = new Teams();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdorass/offBkg1ExecuteQuery", method = RequestMethod.POST)
	public List<OffenderBookings> offBkg1ExecuteQuery(@RequestBody OffenderBookings searchBean) {
		List<OffenderBookings> searchResult = new ArrayList<>();
		try {
			searchResult = ocdorassService.offBkg1ExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdorass/offBkg1Commit", method = RequestMethod.POST)
	public @ResponseBody Integer offBkg1Commit(@RequestBody OffenderBookingsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (Optional.ofNullable(commitBean).isPresent())
				commitBean.setCreateUserId(
						SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			liReturn = ocdorassService.offBkg1Commit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdorass/extOtExecuteQuery", method = RequestMethod.POST)
	public List<ExtOwnershipTransfer> extOtExecuteQuery(@RequestBody ExtOwnershipTransfer searchBean) {
		List<ExtOwnershipTransfer> searchResult = new ArrayList<>();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = ocdorassService.extOtExecuteQuery(searchBean);
		} catch (Exception e) {
			ExtOwnershipTransfer bean = new ExtOwnershipTransfer();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdorass/extOtCommit", method = RequestMethod.POST)
	public @ResponseBody Integer extOtCommit(@RequestBody ExtOwnershipTransferCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (Optional.ofNullable(commitBean).isPresent())
				commitBean.setCreateUserId(
						SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			liReturn = ocdorassService.extOtCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdorass/vOffDetExecuteQuery", method = RequestMethod.POST)
	public List<VOmTeamMembers> vOffDetExecuteQuery(@RequestBody VOmTeamMembers searchBean) { //
		List<VOmTeamMembers> searchResult = new ArrayList<>();
		try {
			searchResult = ocdorassService.vOffDetExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdorass/vOffDetCommit", method = RequestMethod.POST)
	public @ResponseBody Integer vOffDetCommit(@RequestBody VOmTeamMembersCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (Optional.ofNullable(commitBean).isPresent())
				commitBean.setCreateUserId(
						SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			liReturn = ocdorassService.vOffDetCommit(commitBean);
			if(0 != liReturn ) {
				VOmTeamMembersCommitBean processCommitBean = new VOmTeamMembersCommitBean();
				List<VOmTeamMembers> updateList = new ArrayList<VOmTeamMembers>();
				commitBean.getOffbkg1UpdatetList().forEach( e -> {
					VOmTeamMembers obj = new VOmTeamMembers();
					obj.setStaffId((commitBean.getUpdateList()!= null && commitBean.getUpdateList().get(0) !=null)?commitBean.getUpdateList().get(0).getStaffId():null);
					obj.setOffenderBookId(e.getOffenderBookId());
					updateList.add(obj);
				});
				commitBean.getExtotUpdatetList().forEach( e -> {
					VOmTeamMembers obj = new VOmTeamMembers();
					obj.setStaffId((commitBean.getUpdateList()!= null && commitBean.getUpdateList().get(0) !=null)?commitBean.getUpdateList().get(0).getStaffId():null);
					obj.setOffenderBookId(e.getOffenderBookId());
					updateList.add(obj);
				});
				processCommitBean.setUpdateList(updateList);
				prosmainService.enableTriggers(processCommitBean, authorization, "94");
			}
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdorass/getProfileTrustValueDisabled", method = RequestMethod.GET)
	public Boolean getProfileTrustValueDisabled() {
		Boolean returnVal = false;
		try {
			returnVal = ocdorassService.ocdorassGetOmTeamMand();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnVal;
	}
}