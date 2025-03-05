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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.workflow.maintenance.beans.AgyLocTeamFunctions;
import net.syscon.s4.inst.workflow.maintenance.beans.AgyLocTeamFunctionsCommitBean;

/**
 * class OcmdeftmController
 */
@EliteController
public class OcmdeftmController {
	@Autowired
	private OcmdeftmService ocmdeftmService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmdeftmController.class.getName());

	/**
	 * getting rgAgyLoc LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmdeftm/rgAgyLocRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocRecordGroup(
			@RequestParam("agencyLocationType") final String agencyLocationType,
			@RequestParam("caseloadId") final String caseloadId) {
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = ocmdeftmService.rgAgyLocRecordGroup(agencyLocationType, caseloadId);
		} catch (final Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("Exception : Ocmdeftm:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAgyLocType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmdeftm/rgAgyLocTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAgyLocTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			recordList = ocmdeftmService.rgAgyLocTypeRecordGroup(userId);
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocmdeftm:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgFunction LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmdeftm/getWrittenFlagCodes", method = RequestMethod.GET)
	public List<ReferenceCodes> getWrittenFlagCodes() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmdeftmService.getWrittenFlagCodes();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocmdeftm:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgFunction LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmdeftm/rgFunctionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgFunctionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmdeftmService.rgFunctionRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocmdeftm:", e);
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
	@RequestMapping(value = "/ocmdeftm/agyTmFnExecuteQuery", method = RequestMethod.POST)
	public List<AgyLocTeamFunctions> agyTmFnExecuteQuery(@RequestBody final AgencyLocations searchBean) {
		List<AgyLocTeamFunctions> searchResult = new ArrayList<>();
		try {
			searchResult = ocmdeftmService.agyTmFnExecuteQuery(searchBean);
		} catch (final Exception e) {
			final AgyLocTeamFunctions bean = new AgyLocTeamFunctions();
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
	@RequestMapping(value = "/ocmdeftm/agyTmFnCommit", method = RequestMethod.POST)
	public @ResponseBody Integer agyTmFnCommit(@RequestBody final AgyLocTeamFunctionsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmdeftmService.agyTmFnCommit(commitBean);
		} catch (final Exception e) {

			logger.error("Exception : Ocmdeftm", e);
		}
		return liReturn;
	}

}