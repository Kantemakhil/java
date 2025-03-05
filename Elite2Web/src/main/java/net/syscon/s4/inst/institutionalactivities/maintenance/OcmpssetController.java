package net.syscon.s4.inst.institutionalactivities.maintenance;

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
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.ProgramPaySettingsBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.ProgramPaySettingsCommitBean;

@EliteController
public class OcmpssetController {
	@Autowired
	private OcmpssetService ocmpssetService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmpssetController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmpsset/iepLevelRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> iepLevelRecordGroup() {
		List<ReferenceCodes> searchResult = new ArrayList<>();
		try {
			searchResult = ocmpssetService.iepLevelRecordGroup();
		} catch (final Exception e) {
			logger.error("Exception in iepLevelRecordGroup :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmpsset/acpOutcomeCodesRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> acpOutcomeCodesRecordGroup() {
		List<ReferenceCodes> searchResult = new ArrayList<>();
		try {
			searchResult = ocmpssetService.acpOutcomeCodesRecordGroup();
		} catch (final Exception e) {
			logger.error("Exception in acpOutcomeCodesRecordGroup :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmpsset/progServSettingExecuteQuery", method = RequestMethod.GET)
	public List<ProgramPaySettingsBean> progServSettingExecuteQuery() {
		List<ProgramPaySettingsBean> searchResult = new ArrayList<>();
		try {
			searchResult = ocmpssetService.progServSettingExecuteQuery();
		} catch (final Exception e) {
			logger.error("Exception in progServSettingExecuteQuery :", e);
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
	@RequestMapping(value = "/ocmpsset/prgSrvSettingCommit", method = RequestMethod.POST)
	public @ResponseBody Integer prgSrvSettingCommit(@RequestBody final ProgramPaySettingsCommitBean commitBean) {
		Integer liReturn = null;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmpssetService.prgSrvSettingCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception in prgSrvSettingCommit :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmpsset/getHours", method = RequestMethod.GET)
	public Integer getHours() {
		Integer resultObj = null;
		try {
			resultObj = ocmpssetService.getHours();
		} catch (final Exception e) {
			logger.error("Error occured in getHours :", e);
		}
		return resultObj;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmpsset/getProgramServicePayFlag", method = RequestMethod.GET)
	public String getProgramServicePayFlag() {
		String resultObj = null;
		try {
			resultObj = ocmpssetService.getProgramServicePayFlag();
		} catch (final Exception e) {
			logger.error("Error occured in getProgramServicePayFlag :", e);
		}
		return resultObj;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmpsset/eliteFinancialsRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> eliteFinancialsRecordGroup() {
		List<ReferenceCodes> searchResult = new ArrayList<>();
		try {
			searchResult = ocmpssetService.eliteFinancialsRecordGroup();
		} catch (final Exception e) {
			logger.error("Exception in eliteFinancialsRecordGroup :", e);
		}
		return searchResult;
	}
}
