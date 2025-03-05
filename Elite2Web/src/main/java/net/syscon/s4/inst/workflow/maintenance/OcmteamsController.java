package net.syscon.s4.inst.workflow.maintenance;

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

import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembersCommitBean;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.workflow.maintenance.beans.TeamFunctions;
import net.syscon.s4.inst.workflow.maintenance.beans.TeamFunctionsCommitBean;
import net.syscon.s4.inst.workflow.maintenance.beans.TeamsCommitBean;

/**
 * class OcmteamsController
 * 
 */
@EliteController
public class OcmteamsController {
	@Autowired
	private OcmteamsService ocmteamsService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmteamsController.class.getName());

	/**
	 * getting rgTeamArea LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteams/rgTeamAreaRecordGroup", method = RequestMethod.GET)
	public List<Areas> rgTeamAreaRecordGroup(@RequestParam("areaType") final String areaType) {
		List<Areas> recordList = new ArrayList<Areas>();
		try {
			recordList = ocmteamsService.rgTeamAreaRecordGroup(areaType);
		} catch (Exception e) {
			final Areas obj = new Areas();
			logger.error("rgTeamAreaRecordGroup : Ocmteams:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgTeamsTeamCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteams/rgTeamsTeamCodeRecordGroup", method = RequestMethod.GET)
	public List<Teams> rgTeamsTeamCodeRecordGroup() {
		List<Teams> recordList = new ArrayList<Teams>();
		try {
			recordList = ocmteamsService.rgTeamsTeamCodeRecordGroup();
		} catch (Exception e) {
			final Teams obj = new Teams();
			logger.error("rgTeamsTeamCodeRecordGroup : Ocmteams:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgTeamsTeamCategory LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteams/rgTeamsTeamCategoryRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTeamsTeamCategoryRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmteamsService.rgTeamsTeamCategoryRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgTeamsTeamCategoryRecordGroup : Ocmteams:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAreaType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteams/rgAreaTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAreaTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmteamsService.rgAreaTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgAreaTypeRecordGroup : Ocmteams:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgFuctionType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteams/rgFuctionTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgFuctionTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmteamsService.rgFuctionTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgFuctionTypeRecordGroup : Ocmteams:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAvailTeamTeamCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteams/rgAvailTeamTeamCodeRecordGroup", method = RequestMethod.GET)
	public List<Teams> rgAvailTeamTeamCodeRecordGroup() {
		List<Teams> recordList = new ArrayList<Teams>();
		try {
			recordList = ocmteamsService.rgAvailTeamTeamCodeRecordGroup();
		} catch (Exception e) {
			final Teams obj = new Teams();
			logger.error("rgAvailTeamTeamCodeRecordGroup : Ocmteams:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgPosition LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteams/rgPositionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPositionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmteamsService.rgPositionRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgPositionRecordGroup : Ocmteams:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgRole LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteams/rgRoleRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgRoleRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmteamsService.rgRoleRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgRoleRecordGroup : Ocmteams:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteams/rgAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocIdRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ocmteamsService.rgAgyLocIdRecordGroup();
		} catch (Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("rgAgyLocIdRecordGroup : Ocmteams:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAdmAgyLoc LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteams/rgAdmAgyLocRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAdmAgyLocRecordGroup(@RequestParam("areaCode") final String areaCode) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ocmteamsService.rgAdmAgyLocRecordGroup(areaCode);
		} catch (Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("rgAdmAgyLocRecordGroup : Ocmteams:", e);
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
	@RequestMapping(value = "/ocmteams/teamsExecuteQuery", method = RequestMethod.POST)
	public List<Teams> teamsExecuteQuery(@RequestBody final Teams searchBean) {
		List<Teams> searchResult = new ArrayList<>();
		try {
			searchResult = ocmteamsService.teamsExecuteQuery(searchBean);
		} catch (Exception e) {
			final Teams bean = new Teams();
			logger.error("teamsExecuteQuery :", e);
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
	@RequestMapping(value = "/ocmteams/teamsCommit", method = RequestMethod.POST)
	public @ResponseBody List<Teams> teamsCommit(@RequestBody final TeamsCommitBean commitBean) {
		List<Teams> liReturn = new ArrayList<>();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmteamsService.teamsCommit(commitBean);
		} catch (Exception e) {
			final Teams bean = new Teams();
			logger.error("Exception : teamsCommit", e);
			bean.setErrorMessage(e.getMessage().toUpperCase());
			liReturn.add(bean);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteams/teamFunctionsExecuteQuery", method = RequestMethod.POST)
	public List<TeamFunctions> teamFunctionsExecuteQuery(@RequestBody final TeamFunctions searchBean) {
		List<TeamFunctions> searchResult = new ArrayList<>();
		try {
			searchResult = ocmteamsService.teamFunctionsExecuteQuery(searchBean);
		} catch (Exception e) {
			final TeamFunctions bean = new TeamFunctions();
			logger.error("Exception : teamFunctionsExecuteQuery", e);
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
	@RequestMapping(value = "/ocmteams/teamFunctionsCommit", method = RequestMethod.POST)
	public @ResponseBody List<TeamFunctions> teamFunctionsCommit(@RequestBody final TeamFunctionsCommitBean commitBean) {
		List<TeamFunctions> liReturn = new ArrayList<>();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmteamsService.teamFunctionsCommit(commitBean);
		} catch (Exception e) {
			String error = "Error : " + e.getMessage();
			final TeamFunctions bean = new TeamFunctions();
			if(error.contains("team_functions_pk")) {
				bean.setErrorMessage(error.toUpperCase());
				liReturn.add(bean);
				return liReturn;
			}
			if (error.contains("constraint")) {
				error = error.substring(error.indexOf("constraint"), error.indexOf("\" on"))
						.replaceFirst("constraint", "").trim();
				final String tableName = ocmteamsService.errorNameValidationTimes(error.substring(1, error.length()));

				bean.setSealFlag(tableName.toUpperCase());
				bean.setServerCode(2292);
				logger.error("agyVisitTimesUpdateAgencyVisitTimes", e);
				liReturn.add(bean);
				return liReturn;
			}
			logger.error("Exception : teamFunctionsCommit", e);
			bean.setErrorMessage(e.getMessage().toUpperCase());
			liReturn.add(bean);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteams/availTeamExecuteQuery", method = RequestMethod.POST)
	public List<Teams> availTeamExecuteQuery(@RequestBody final Teams searchBean) {
		List<Teams> searchResult = new ArrayList<>();
		try {
			searchResult = ocmteamsService.availTeamExecuteQuery(searchBean);
		} catch (Exception e) {
			final Teams bean = new Teams();
			logger.error("Exception : availTeamExecuteQuery", e);
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
	@RequestMapping(value = "/ocmteams/teamMembersExecuteQuery", method = RequestMethod.POST)
	public List<TeamMembers> teamMembersExecuteQuery(@RequestBody final TeamMembers searchBean) {
		List<TeamMembers> searchResult = new ArrayList<>();
		try {
			searchResult = ocmteamsService.teamMembersExecuteQuery(searchBean);
		} catch (Exception e) {
			final TeamMembers bean = new TeamMembers();
			logger.error("Exception : teamMembersExecuteQueryfi", e);
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
	@RequestMapping(value = "/ocmteams/teamMembersCommit", method = RequestMethod.POST)
	public @ResponseBody List<TeamMembers> teamMembersCommit(@RequestBody final TeamMembersCommitBean commitBean) {
		List<TeamMembers> liReturn = new ArrayList<>();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmteamsService.teamMembersCommit(commitBean);
		} catch (Exception e) {
			final TeamMembers bean = new TeamMembers();
			logger.error("Exception : teamMembersCommit", e);
			bean.setErrorMessage(e.getMessage().toUpperCase());
			liReturn.add(bean);

		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteams/availTeamActiveExecuteQuery", method = RequestMethod.POST)
	public List<Teams> availTeamActiveExecuteQuery(@RequestBody final Teams searchBean) {
		List<Teams> searchResult = new ArrayList<>();
		try {
			searchResult = ocmteamsService.availTeamActiveExecuteQuery(searchBean);
		} catch (Exception e) {
			final Teams bean = new Teams();
			logger.error("Exception : availTeamActiveExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteams/validateTeamCode", method = RequestMethod.POST)
	public Integer validateTeamCode(@RequestBody final Teams searchBean) {
		Integer searchResult = 0;
		try {
			searchResult = ocmteamsService.validateTeamCode(searchBean);
		} catch (Exception e) {
			logger.error("Exception : validateTeamCode", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteams/verifyTeamMembersData", method = RequestMethod.POST)
	public Integer verifyTeamMembersData(@RequestBody final TeamMembers searchBean) {
		Integer searchResult = 0;
		try {
			searchResult = ocmteamsService.ocmteamsKeyDelrec(searchBean);
		} catch (Exception e) {
			logger.error("Exception : verifyTeamMembersData", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteams/updateAllowedCheck", method = RequestMethod.GET)
	public Integer getUpdateAllowedCheck(@RequestParam("caseloadId") final String caseloadId,
			@RequestParam("agyLocId") final String agyLocId) {
		Integer recordCount = 0;
		try {
			recordCount = ocmteamsService.getUpdateAllowedCheck(caseloadId, agyLocId);
		} catch (Exception e) {
			logger.error("Exception : getUpdateAllowedCheck:", e);
		}
		return recordCount;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteams/validateFunctionCode", method = RequestMethod.POST)
	public Integer validateFunctionCode(@RequestBody final TeamFunctions searchBean) {
		Integer searchResult = 0;
		try {
			searchResult = ocmteamsService.validateFunctionCode(searchBean);
		} catch (Exception e) {
			logger.error("Exception : validateFunctionCode:", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteams/availTeamOnCheckDeleteMaster", method = RequestMethod.POST)
	public Integer availTeamOnCheckDeleteMaster(@RequestBody final TeamMembers searchBean) {
		Integer searchResult = 0;
		try {
			searchResult = ocmteamsService.availTeamOnCheckDeleteMaster(searchBean);
		} catch (Exception e) {
			logger.error("Exception : availTeamOnCheckDeleteMaster:", e);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteams/getAllTeams", method = RequestMethod.GET)
	public List<Teams> getAllTeams() {
		List<Teams> recordList = new ArrayList<>();
		try {
			recordList = ocmteamsService.getAllTeams();
		} catch (final Exception e) {
			final Teams obj = new Teams();
			logger.error("rgTeamRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

}
