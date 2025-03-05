package net.syscon.s4.cm.programsservices;

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
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;

@EliteController
public class OidpwaitController {
	@Autowired
	private OidpwaitService oidpwaitService;

	private static Logger logger = LogManager.getLogger(OidpwaitController.class.getName());

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpwait/rgProgramServicesRecordGroup", method = RequestMethod.GET)
	public List<ProgramServices> rgProgramServicesRecordGroup() {
		List<ProgramServices> recordList = new ArrayList<ProgramServices>();
		try {
			recordList = oidpwaitService.rgProgramServicesRecordGroup();
		} catch (Exception e) {
			logger.error("rgProgramServicesRecordGroup :", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpwait/rgRegionRecordGroup", method = RequestMethod.GET)
	public List<Areas> rgRegionRecordGroup() {
		List<Areas> recordList = new ArrayList<Areas>();
		try {
			recordList = oidpwaitService.rgRegionRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpwait/rgAreasRecordGroup", method = RequestMethod.GET)
	public List<ProgramServices> rgAreasRecordGroup(@RequestParam(value = "areaInputs") final String areaInputs) {
		List<ProgramServices> recordList = new ArrayList<ProgramServices>();
		try {
			recordList = oidpwaitService.rgAreasRecordGroup(areaInputs);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpwait/rgAgyLocsRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocsRecordGroup(
			@RequestParam(value = "facilityInputs") final String facilityInputs) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oidpwaitService.rgAgyLocsRecordGroup(facilityInputs);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgAllTeams LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpwait/rgAllTeamsRecordGroup", method = RequestMethod.GET)
	public List<Areas> rgAllTeamsRecordGroup() {
		List<Areas> recordList = new ArrayList<Areas>();
		try {
			recordList = oidpwaitService.rgAllTeamsRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpwait/rgRestrictTeamsRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgRestrictTeamsRecordGroup(
			@RequestParam(value = "teamInputs") final String teamInputs) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidpwaitService.rgRestrictTeamsRecordGroup(teamInputs);
		} catch (Exception e) {
			logger.error("rgRestrictTeamsRecordGroup :", e);
		}
		return recordList;
	}

	/* ExeCute Query */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpwait/vOffPrgOblExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderPrgObligations> vOffPrgOblExecuteQuery(@RequestBody VOffenderPrgObligations searchBean) {
		List<VOffenderPrgObligations> searchResult = new ArrayList<>();
		try {
			searchResult = oidpwaitService.vOffPrgOblExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("vOffPrgOblExecuteQuery :", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpwait/rgPsPrgAvailRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPsPrgAvailRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidpwaitService.rgPsPrgAvailRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidpwait/vOffPrgOblCommit", method = RequestMethod.POST)
	public @ResponseBody List<VOffenderPrgObligations> vOffPrgOblCommit(
			@RequestBody VOffenderPrgObligationsCommitBean commitBean) {
		List<VOffenderPrgObligations> liReturn = new ArrayList<VOffenderPrgObligations>();
		try {
			commitBean
					.setCreateUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			liReturn = oidpwaitService.vOffPrgOblCommit(commitBean);
		} catch (Exception e) {
			final VOffenderPrgObligations error = new VOffenderPrgObligations();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg);
			liReturn.add(error);
			logger.error("vOffPrgOblCommit :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpwait/clearTempList", method = RequestMethod.GET)
	public Integer clearTempList() {
		int count = 0;
		try {
			count = oidpwaitService.clearTempList(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		} catch (Exception e) {
			logger.error("clearTempList :", e);
		}
		return count;
	}

	/* non_association */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpwait/nonAssociation", method = RequestMethod.POST)
	public Integer nonAssociation(@RequestBody VOffenderPrgObligations searchBean) {
		int count = 0;
		try {
			searchBean
					.setCreateUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			searchBean
					.setModifyUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			count = oidpwaitService.nonAssociation(searchBean);
		} catch (Exception e) {
			logger.error("nonAssociation :", e);
		}
		return count;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpwait/assignServicesToOffenders", method = RequestMethod.POST)
	public Integer assignServicesToOffenders(@RequestBody VOffenderPrgObligations searchBean) {
		int count = 0;
		try {
			searchBean
					.setCreateUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			count = oidpwaitService.assignServicesToOffenders(searchBean);
		} catch (Exception e) {
			logger.error("assignServicesToOffenders :", e);
		}
		return count;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpwait/whenNewFormInstance", method = RequestMethod.GET)
	public List<ReferenceCodes> whenNewFormInstance(final String currentCaseLoad) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidpwaitService.whenNewFormInstance(currentCaseLoad);
		} catch (Exception e) {
			logger.error("whenNewFormInstance :", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpwait/getcommareadefault", method = RequestMethod.GET)
	public List<ReferenceCodes> getcommareadefault(final String currentCaseLoad) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidpwaitService.getcommareadefault(currentCaseLoad);
		} catch (Exception e) {
			logger.error("getcommareadefault :", e);
		}
		return recordList;
	}

}