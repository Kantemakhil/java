package net.syscon.s4.sa.admin;

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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.im.beans.StaffMemberRolesCommitBean;

/**
 */
@EliteController
public class OumsmugaController {
	@Autowired
	private OumsmugaService oumsmugaService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumsmugaController.class.getName());

	/**
	 * getting cgfkStaffRoleDspUserId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumsmuga/cgfkStaffRoleDspUserIdRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkStaffRoleDspUserIdRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oumsmugaService.cgfkStaffRoleDspUserIdRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
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
	@RequestMapping(value = "/oumsmuga/omsRoleExecuteQuery", method = RequestMethod.POST)
	public List<OmsRoles> omsRoleExecuteQuery(@RequestBody final OmsRoles searchBean) {
		List<OmsRoles> searchResult = new ArrayList<>();
		try {
			searchResult = oumsmugaService.omsRoleExecuteQuery(searchBean);
		} catch (Exception e) {
			final OmsRoles bean = new OmsRoles();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumsmuga/staffRoleExecuteQuery", method = RequestMethod.POST)
	public List<StaffMemberRoles> staffRoleExecuteQuery(@RequestBody final StaffMemberRoles searchBean) {
		List<StaffMemberRoles> searchResult = new ArrayList<>();
		try {
			searchResult = oumsmugaService.staffRoleExecuteQuery(searchBean);
		} catch (Exception e) {
			final StaffMemberRoles bean = new StaffMemberRoles();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
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
	@RequestMapping(value = "/oumsmuga/staffRoleCommit", method = RequestMethod.POST)
	public @ResponseBody StaffMemberRoles staffRoleCommit(@RequestBody final StaffMemberRolesCommitBean commitBean) {
		StaffMemberRoles staffMemRoles = new StaffMemberRoles();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			staffMemRoles = oumsmugaService.staffRoleCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return staffMemRoles;
	}

}