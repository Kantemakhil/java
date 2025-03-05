package net.syscon.s4.inst.incidentsoic;

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
import net.syscon.s4.common.beans.AgencyIncidentAssoTostg;
import net.syscon.s4.common.beans.AgencyIncidentAssoTostgCommitBean;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.inst.incidentsoic.impl.OiustgasServiceImpl;

@EliteController
public class OiustgasController {

	@Autowired
	private OiustgasServiceImpl oiustgasService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiustgasController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiustgas/agencyIncidentAssoTostgExecuteQuery", method = RequestMethod.POST)
	public List<AgencyIncidentAssoTostg> agencyIncidentAssoTostgExecuteQuery(
			@RequestBody final AgencyIncidentAssoTostg searchBean) {
		List<AgencyIncidentAssoTostg> searchResult = new ArrayList<>();
		try {
			searchResult = oiustgasService.agencyIncidentAssoTostgExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("agencyIncidentAssoTostgExecuteQuery", e);
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
	@RequestMapping(value = "/oiustgas/agencyIncidentAssoTostgCommit", method = RequestMethod.POST)
	public @ResponseBody Integer agencyIncidentAssoTostgCommit(
			@RequestBody final AgencyIncidentAssoTostgCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oiustgasService.agencyIncidentAssoTostgCommit(commitBean);
		} catch (Exception e) {
			logger.error("agencyIncidentAssoTostgCommit", e);
		}
		return liReturn;
	}

	/**
	 * getting rgStg2 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiustgas/rgStgRecordGroup", method = RequestMethod.GET)
	public List<SecurityThreatGroups> rgStgRecordGroup() {
		List<SecurityThreatGroups> recordList = new ArrayList<>();
		try {
			recordList = oiustgasService.rgStgRecordGroup();
		} catch (Exception e) {
			logger.error("rgStgRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgStg1 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiustgas/rgStgORecordGroup", method = RequestMethod.GET)
	public List<SecurityThreatGroups> rgStgORecordGroup() {
		List<SecurityThreatGroups> recordList = new ArrayList<>();
		try {
			recordList = oiustgasService.rgStgORecordGroup();
		} catch (Exception e) {
			logger.error("rgStgORecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgStg3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiustgas/rgStgLRecordGroup", method = RequestMethod.GET)
	public List<SecurityThreatGroups> rgStgLRecordGroup() {
		List<SecurityThreatGroups> recordList = new ArrayList<>();
		try {
			recordList = oiustgasService.rgStgLRecordGroup();
		} catch (Exception e) {
			logger.error("rgStgLRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting stgGrp LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiustgas/stgGrpRecordGroup", method = RequestMethod.GET)
	public List<SecurityThreatGroups> stgGrpRecordGroup() {
		List<SecurityThreatGroups> recordList = new ArrayList<>();
		try {
			recordList = oiustgasService.stgGrpRecordGroup();
		} catch (Exception e) {
			logger.error("stgGrpRecordGroup", e);
		}
		return recordList;
	}

}