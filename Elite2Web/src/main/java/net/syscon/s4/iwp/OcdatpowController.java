package net.syscon.s4.iwp;

import java.util.ArrayList;
import java.util.List;

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

import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderBookingsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@EliteController
public class OcdatpowController {
	@Autowired
	private OcdatpowService ocdatpowService;
	@Autowired
	private ProsmainService prosmainService;

	private static Logger logger = LogManager.getLogger(OcdatpowController.class.getName());

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdatpow/rgPositionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPositionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdatpowService.rgPositionRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Ocdatpow:", e);
		}
		return recordList;
	}
	


	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdatpow/rgRoleRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgRoleRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdatpowService.rgRoleRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Ocdatpow:", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdatpow/rgSexCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSexCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdatpowService.rgSexCodeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Ocdatpow:", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdatpow/rgScheduleTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgScheduleTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdatpowService.rgScheduleTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Ocdatpow:", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdatpow/rgTeamRecordGroup", method = RequestMethod.GET)
	public List<Teams> rgTeamRecordGroup(@RequestParam final String sealFlag) {
		List<Teams> recordList = new ArrayList<Teams>();
		try {
			recordList = ocdatpowService.rgTeamRecordGroup(sealFlag);
		} catch (Exception e) {
			logger.error("Exception : Ocdatpow:", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdatpow/cgfkstaffLrDspDescriptionRecordGroup", method = RequestMethod.GET)
	public List<TeamMembers> cgfkStaffLrDspDescriptionRecordGroup(@RequestParam final String caseLoadType) {
		List<TeamMembers> recordList = new ArrayList<TeamMembers>();
		try {
			recordList = ocdatpowService.cgfkStaffLrDspDescriptionRecordGroup(caseLoadType);
		} catch (Exception e) {
			logger.error("Exception : Ocdatpow:", e);
		}
		return recordList;
	}
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdatpow/omMandatoryGrid", method = RequestMethod.GET)
	public Boolean omMandatoryGrid() {
		Boolean omteam=false;
		try {
			omteam=ocdatpowService.omMandatoryGrid();
		} catch (Exception e) {
			logger.error("Exception : Ocdatpow:", e);
		}
		return omteam;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdatpow/cgfkStaffLrDspLastNameRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> cgfkStaffLrDspLastNameRecordGroup() {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = ocdatpowService.cgfkStaffLrDspLastNameRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Ocdatpow:", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdatpow/offBkg1ExecuteQuery", method = RequestMethod.POST)
	public List<OffenderBookings> offBkg1ExecuteQuery(@RequestBody final OffenderBookings searchBean) {
		List<OffenderBookings> searchResult = new ArrayList<>();
		try {
			searchResult = ocdatpowService.offBkg1ExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :Ocdatpow", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdatpow/offBkg1Commit", method = RequestMethod.POST)
	public @ResponseBody Integer offBkg1Commit(@RequestBody final OffenderBookingsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdatpowService.offBkg1Commit(commitBean);
			if(1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "12");
			}
		} catch (Exception e) {
			logger.error("Exception : Ocdatpow", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdatpow/vOffDetExecuteQuery", method = RequestMethod.POST)
	public List<TeamMembers> vOffDetExecuteQuery(@RequestBody final TeamMembers searchBean) {
		List<TeamMembers> searchResult = new ArrayList<>();
		try {
			searchResult = ocdatpowService.vOffDetExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :Ocdatpow", e);
		}
		return searchResult;
	}

}
