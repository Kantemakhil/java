package net.syscon.s4.inst.incidentsoic.maintenance;

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
import net.syscon.s4.common.beans.SystemProfiles;

/**
 * @author Arkin Software Technologies
 * @version 1.0
 */
@EliteController
public class OimoicmpController {
	@Autowired
	private OimoicmpService oimoicmpService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimoicmpController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoicmp/oicSlExecuteQuery", method = RequestMethod.POST)
	public List<OicSanctionLimits> oicSlExecuteQuery(@RequestBody OicSanctionLimits searchBean) {
		List<OicSanctionLimits> searchResult = new ArrayList<>();
		try {
			searchResult = oimoicmpService.oicSlExecuteQuery(searchBean);
		} catch (Exception e) {
			OicSanctionLimits bean = new OicSanctionLimits();
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
	@RequestMapping(value = "/oimoicmp/oicSlCommit", method = RequestMethod.POST)
	public @ResponseBody List<OicSanctionLimits> oicSlCommit(@RequestBody OicSanctionLimitsCommitBean commitBean) {
		List<OicSanctionLimits> liReturn = new ArrayList<>();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oimoicmpService.oicSlCommit(commitBean);
		} catch (Exception e) {
			final OicSanctionLimits error = new OicSanctionLimits();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkOicSlOicSanctionCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoicmp/cgfkOicSlOicSanctionCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOicSlOicSanctionCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimoicmpService.cgfkOicSlOicSanctionCodeRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oimoicmp:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOicSlOicHearingType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoicmp/cgfkOicSlOicHearingTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOicSlOicHearingTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimoicmpService.cgfkOicSlOicHearingTypeRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oimoicmp:", e);
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
	@RequestMapping(value = "/oimoicmp/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oimoicmpService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			SystemProfiles bean = new SystemProfiles();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}