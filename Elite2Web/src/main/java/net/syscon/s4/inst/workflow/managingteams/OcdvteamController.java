package net.syscon.s4.inst.workflow.managingteams;

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
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.workflow.managingteams.beans.OffenderTeamAssignments;
import net.syscon.s4.inst.workflow.managingteams.beans.OffenderTeamAssignmentsCommitBean;

/**
 * OcdvteamController
 */
@EliteController
public class OcdvteamController {
	@Autowired
	private OcdvteamService ocdvteamService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdvteamController.class.getName());

	/**
	 * getting rgFunction LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdvteam/rgFunctionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgFunctionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdvteamService.rgFunctionRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocdvteam:", e);
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
	@RequestMapping(value = "/ocdvteam/offTeamAssignExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTeamAssignments> offTeamAssignExecuteQuery(@RequestBody final OffenderTeamAssignments searchBean) {
		List<OffenderTeamAssignments> searchResult = new ArrayList<>();
		try {
			searchResult = ocdvteamService.offTeamAssignExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderTeamAssignments bean = new OffenderTeamAssignments();
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
	@RequestMapping(value = "/ocdvteam/offTeamAssignCommit", method = RequestMethod.POST)
	public @ResponseBody List<OffenderTeamAssignments> offTeamAssignCommit(
			@RequestBody final OffenderTeamAssignmentsCommitBean commitBean) {
		List<OffenderTeamAssignments> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdvteamService.offTeamAssignCommit(commitBean);
		} catch (Exception e) {
			final OffenderTeamAssignments error = new OffenderTeamAssignments();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg);
			liReturn.add(error);
			logger.error("Exception :", e);
			logger.error("Exception : Ocdvteam", e);
		}
		return liReturn;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdvteam/getTeamDetails", method = RequestMethod.POST)
	public OffenderTeamAssignments getTeamDetails(@RequestBody final OffenderTeamAssignments searchBean) {
		 OffenderTeamAssignments searchResult = new OffenderTeamAssignments();
		try {
			searchResult = ocdvteamService.getTeamDetails(searchBean);
		} catch (Exception e) {
			final OffenderTeamAssignments bean = new OffenderTeamAssignments();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}
	
}