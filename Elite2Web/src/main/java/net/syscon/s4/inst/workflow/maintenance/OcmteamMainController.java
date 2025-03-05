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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembersCommitBean;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.globalconfiguration.impl.OumrcodeServiceImpl;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.workflow.maintenance.beans.TeamsCommitBean;




/**
 * class OcmteamsController
 * 
 */
@EliteController
public class OcmteamMainController {
	@Autowired
	private OcmteamMainService ocmteamMainService;
	@Autowired
	private OcmteamsService ocmteamsService;
	@Autowired
	private OumrcodeServiceImpl oumrcodeservice;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmteamMainController.class.getName());

	/**
	 * getting rgTeamArea LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteammain/getCaseLoads", method = RequestMethod.GET)
	public List<ReferenceCodes> getCaseLoadList() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmteamMainService.getCaseLoadList();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgTeamAreaRecordGroup : Ocmteams:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteammain/teamsExecuteQuery", method = RequestMethod.GET)
	public List<Teams> teamsExecuteQuery() {
		List<Teams> searchResult = new ArrayList<>();
		try {
			searchResult = ocmteamMainService.teamsExecuteQuery();
		} catch (Exception e) {
			final Teams bean = new Teams();
			logger.error("teamsExecuteQuery :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocmteammain/teamsCommit", method = RequestMethod.POST)
	public @ResponseBody List<Teams> teamsCommit(@RequestBody final TeamsCommitBean commitBean) {
		List<Teams> liReturn = new ArrayList<>();
		if (commitBean != null) {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
		}
		try {
			liReturn = ocmteamMainService.teamsCommit(commitBean);
		} catch (Exception e) {
			final Teams bean = new Teams();
			logger.error("Exception : teamsCommit", e);
			bean.setErrorMessage(e.getMessage());
			liReturn.add(bean);
		}
		return liReturn;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteammain/getAgyLocRecords", method = RequestMethod.GET)
	public List<AgencyLocations> getAgyLocRecord() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ocmteamMainService.getAgyLocRecords();
		} catch (Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("getAgyLocRecord : Ocmteams:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocmteammain/teamDataCommit", method = RequestMethod.POST)
	public @ResponseBody List<Teams>  teamDataCommit(@RequestBody final TeamsCommitBean commitBean) {
		List<Teams>  liReturn = new ArrayList<>();
		if (commitBean != null) {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
		}
		try {
			liReturn = ocmteamMainService.teamDataCommit(commitBean);
		} catch (Exception e) {
			final Teams bean = new Teams();
			logger.error("Exception : teamsCommit", e);
			bean.setErrorMessage(e.getMessage());
			liReturn.add(bean);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteammain/getStaffDetails", method = RequestMethod.GET)
	public List<StaffMembers> getStaffDetails() {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = ocmteamMainService.getStaffDetails();
		} catch (Exception e) {
			final StaffMembers obj = new StaffMembers();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteammain/teamMembersExecuteQuery", method = RequestMethod.POST)
	public List<TeamMembers> teamMembersExecuteQuery(@RequestBody final TeamMembers searchBean) {
		List<TeamMembers> searchResult = new ArrayList<>();
		try {
			searchResult = ocmteamMainService.teamMembersExecuteQuery(searchBean);
		} catch (Exception e) {
			final TeamMembers bean = new TeamMembers();
			logger.error("Exception : teamMembersExecuteQueryfi", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocmteammain/teamMembersCommit", method = RequestMethod.POST)
	public @ResponseBody List<TeamMembers> teamMembersCommit(@RequestBody final TeamMembersCommitBean commitBean) {
		List<TeamMembers> liReturn = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocmteamMainService.teamMembersCommit(commitBean);
		} catch (Exception e) {
			final TeamMembers bean = new TeamMembers();
			logger.error("Exception : teamMembersCommit", e);
			bean.setErrorMessage(e.getMessage());
			liReturn.add(bean);

		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteammain/rgFuctionTypeRecordGroup", method = RequestMethod.GET)
	public List<net.syscon.s4.im.beans.ReferenceCodes> rgFuctionTypeRecordGroup() {
		List<net.syscon.s4.im.beans.ReferenceCodes> recordList = new ArrayList<net.syscon.s4.im.beans.ReferenceCodes>();
		try {
			recordList = ocmteamsService.rgFuctionTypeRecordGroup();
		} catch (Exception e) {
			recordList = null;
		}
		return recordList;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmteammain/getAllTeams", method = RequestMethod.GET)
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
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocmteammain/refCodeExecuteQuery", method=RequestMethod.POST)
	public List<ReferenceCodes> refCodeExecuteQuery(@RequestBody ReferenceCodes searchBean) {
		List<ReferenceCodes> searchResult = new ArrayList<>();
		try {
			searchResult = oumrcodeservice.refCodeExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("refCodeExecuteQuery",e);
		}
		return searchResult;
	}

}