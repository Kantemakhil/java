package net.syscon.s4.inst.workflow.managingworkassignments;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.pkgs.common.CommonService;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffSkills;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffSkillsCommitBean;

/**
 * OumstafcController
 */
@EliteController
public class OumstafcController {
	@Autowired
	private OumstafcService oumstafcService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumstafcController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumstafc/stskExecuteQuery", method = RequestMethod.POST)
	public List<StaffSkills> stskExecuteQuery(@RequestBody final StaffSkills searchBean) {
		List<StaffSkills> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oumstafcService.stskExecuteQuery(searchBean);
		} catch (final Exception e) {
			final StaffSkills bean = new StaffSkills();
			logger.error("stskExecuteQuery :", e);
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
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumstafc/stskCommit", method = RequestMethod.POST)
	public @ResponseBody Integer stskCommit(@RequestBody final StaffSkillsCommitBean commitBean) {
		final String Username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(Username);
		int liReturn = 0;
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			liReturn = oumstafcService.stskCommit(commitBean);
		} catch (final Exception e) {

			logger.error("stskCommit :", e);
		}
		return liReturn;
	}

	/**
	 * getting rgStaffSkill LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumstafc/rgStaffSkillRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgStaffSkillRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oumstafcService.rgStaffSkillRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgStaffSkillRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgProgram LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumstafc/rgProgramRecordGroup", method = RequestMethod.GET)
	public List<ProgramServices> rgProgramRecordGroup() {
		List<ProgramServices> recordList = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oumstafcService.rgProgramRecordGroup();
		} catch (final Exception e) {
			final ProgramServices obj = new ProgramServices();
			logger.error("rgProgramRecordGroup :", e);
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgSubType LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumstafc/rgSubTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSubTypeRecordGroup(@RequestParam("skillType") final String skillType) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oumstafcService.rgSubTypeRecordGroup(skillType);
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgSubTypeRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OUMSTAFC");
	}
}