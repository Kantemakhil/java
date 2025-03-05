package net.syscon.s4.inst.visitsmanagement;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.visitsmanagement.beans.VisitingGroups;
import net.syscon.s4.inst.visitsmanagement.beans.VisitingGroupsCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.VisitingMembers;
import net.syscon.s4.inst.visitsmanagement.beans.VisitingMembersCommitBean;

/**
 * Class OidvtourController
 */
@EliteController
public class OidvtourController {
	@Autowired
	private OidvtourService oidvtourService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidvtourController.class.getName());

	/**
	 * getting rgGroupPurpos LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvtour/rgGroupPurposRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgGroupPurposRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidvtourService.rgGroupPurposRecordGroup();
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting rgIdType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvtour/rgIdTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgIdTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidvtourService.rgIdTypeRecordGroup();
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting rgStaffMembers LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvtour/rgStaffMembersRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgStaffMembersRecordGroup() {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = oidvtourService.rgStaffMembersRecordGroup();
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting rgAgencyLocations LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvtour/rgAgencyLocationsRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgencyLocationsRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "caseloadType") final String caseloadType) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oidvtourService.rgAgencyLocationsRecordGroup(caseloadId, caseloadType);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvtour/visitingGroupsExecuteQuery", method = RequestMethod.POST)
	public List<VisitingGroups> visitingGroupsExecuteQuery(@RequestBody final VisitingGroups searchBean) {
		List<VisitingGroups> searchResult = new ArrayList<>();
		try {
			searchResult = oidvtourService.visitingGroupsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
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
	@RequestMapping(value = "/oidvtour/visitingGroupsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer visitingGroupsCommit(@RequestBody final VisitingGroupsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidvtourService.visitingGroupsCommit(commitBean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvtour/visitingMembersExecuteQuery", method = RequestMethod.POST)
	public List<VisitingMembers> visitingMembersExecuteQuery(@RequestBody final VisitingMembers searchBean) {
		List<VisitingMembers> searchResult = new ArrayList<>();
		try {
			searchResult = oidvtourService.visitingMembersExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
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
	@RequestMapping(value = "/oidvtour/visitingMembersCommit", method = RequestMethod.POST)
	public @ResponseBody Integer visitingMembersCommit(@RequestBody final VisitingMembersCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidvtourService.visitingMembersCommit(commitBean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}

}