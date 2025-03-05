package net.syscon.s4.inmate.trust.financialsmaintenance;

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
import net.syscon.s4.im.beans.OffenderLimits;
import net.syscon.s4.im.beans.OffenderLimitsCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;

/**
 * class OtmoflimController
 */

@EliteController
public class OtmoflimController {

	@Autowired
	private OtmoflimService otmoflimService;

	/**
	 * Logger object used to print the log in the file
	 */

	private static Logger logger = LogManager.getLogger(OtmoflimController.class.getName());

	/**
	 * getting cgfkOffLimLimitType LOV values
	 */

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmoflim/cgfkOffLimLimitTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffLimLimitTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = otmoflimService.cgfkOffLimLimitTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table method:offLimExecuteQuery
	 * 
	 * @Param searchRecord
	 * @return List<OffenderLimits>
	 */

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmoflim/offLimExecuteQuery", method = RequestMethod.POST)
	public List<OffenderLimits> offLimExecuteQuery(@RequestBody final OffenderLimits searchBean) {
		List<OffenderLimits> searchResult = new ArrayList<OffenderLimits>();
		try {
			searchResult = otmoflimService.offLimExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception:", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otmoflim/offLimCommit", method = RequestMethod.POST)
	public @ResponseBody String offLimCommit(@RequestBody final OffenderLimitsCommitBean commitBean) {
		String liReturn = "0";
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = otmoflimService.offLimCommit(commitBean);
		} catch (Exception e) {
			String error = "Error : " + e.getMessage().toUpperCase();
			if(error.contains("OFFENDER_LIMITS_PK")) {
				liReturn = "OMS_OWNER.OFFENDER_LIMITS_PK";
			}
			logger.error("Exception :", e);
		}
		return liReturn;
	}

}