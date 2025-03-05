package net.syscon.s4.globalrbac;

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
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.im.beans.ModulePrivilegesCommitBean;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.OmsRoles;

/**
 * Class OumassmuService
 */
@EliteController
public class OumassmuController {
	@Autowired
	private OumassmuService oumassmuService;
	
	@Autowired
	private OumrolesService oumrolesService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumassmuController.class.getName());

	/**
	 * getting rgStaffMemberRolesRole LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumassmu/rgStaffMemberRolesRoleRecordGroup", method = RequestMethod.GET)
	public List<OmsRoles> rgStaffMemberRolesRoleRecordGroup() {
		List<OmsRoles> recordList = new ArrayList<OmsRoles>();
		try {
			recordList = oumassmuService.rgStaffMemberRolesRoleRecordGroup();
		} catch (Exception e) {
			final OmsRoles obj = new OmsRoles();
			logger.error("In method rgStaffMemberRolesRoleRecordGroup"+e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkModPrivAccessPrivilege LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumassmu/cgfkModPrivAccessPrivilegeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkModPrivAccessPrivilegeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oumassmuService.cgfkModPrivAccessPrivilegeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method cgfkModPrivAccessPrivilegeRecordGroup"+e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkModPrivModuleName LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumassmu/cgfkModPrivModuleNameRecordGroup", method = RequestMethod.GET)
	public List<OmsModules> cgfkModPrivModuleNameRecordGroup() {
		List<OmsModules> recordList = new ArrayList<OmsModules>();
		try {
			recordList = oumassmuService.cgfkModPrivModuleNameRecordGroup();
		} catch (Exception e) {
			final OmsModules obj = new OmsModules();
			logger.error("In method cgfkModPrivModuleNameRecordGroup"+e);
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
	@RequestMapping(value = "/oumassmu/omsRoleExecuteQuery", method = RequestMethod.POST)
	public List<OmsRoles> omsRoleExecuteQuery(final @RequestBody OmsRoles searchBean) {
		List<OmsRoles> searchResult = new ArrayList<>();
		try {
			searchResult = oumassmuService.omsRoleExecuteQuery(searchBean);
		} catch (Exception e) {
			final OmsRoles bean = new OmsRoles();
			logger.error("In method omsRoleExecuteQuery"+e);
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
	@RequestMapping(value = "/oumassmu/modPrivExecuteQuery", method = RequestMethod.POST)
	public List<ModulePrivileges> modPrivExecuteQuery(final @RequestBody ModulePrivileges searchBean) {
		List<ModulePrivileges> searchResult = new ArrayList<>();
		try {
			searchResult = oumassmuService.modPrivExecuteQuery(searchBean);
		} catch (Exception e) {
			final ModulePrivileges bean = new ModulePrivileges();
			logger.error("In method modPrivExecuteQuery"+e);
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
	@RequestMapping(value = "/oumassmu/modPrivCommit", method = RequestMethod.POST)
	public @ResponseBody Integer modPrivCommit(final @RequestBody ModulePrivilegesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumassmuService.modPrivCommit(commitBean);
		} catch (Exception e) {
			if (e.getMessage().contains("mod_priv_oms_mod_f1")) {
				liReturn = 2;
			}
			logger.error("In method modPrivCommit"+e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumassmu/omsRoleExecuteQry", method = RequestMethod.POST)
	public List<OmsRoles> omsRoleExecuteQry(@RequestBody OmsRoles searchBean) {
		List<OmsRoles> searchResult = new ArrayList<>();
		try {
			searchResult = oumrolesService.omsRoleExecuteQuery(searchBean);
		} catch (Exception e) {
			OmsRoles bean = new OmsRoles();
			logger.error("In this method omsRoleExecuteQuery :" + e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
}


