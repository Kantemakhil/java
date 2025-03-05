package net.syscon.s4.inst.movementexternal;

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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * class OidtrojuController
 */
@EliteController
public class OidtrojuController {
	@Autowired
	private OidtrojuService oidtrojuService;
	
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidtrojuController.class.getName());

	/**
	 * getting cgfkOffEmToProvStatCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtroju/cgfkOffEmToProvStatCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffEmToProvStatCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidtrojuService.cgfkOffEmToProvStatCodeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method cgfkOffEmToProvStatCodeRecordGroup", e);
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
	@RequestMapping(value = "/oidtroju/offEmExecuteQuery", method = RequestMethod.POST)
	public List<OffenderExternalMovements> offEmExecuteQuery(@RequestBody final OffenderExternalMovements searchBean) {
		List<OffenderExternalMovements> searchResult = new ArrayList<>();
		try {
			searchResult = oidtrojuService.offEmExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderExternalMovements bean = new OffenderExternalMovements();
			logger.error("In method offEmExecuteQuery", e);
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
	@RequestMapping(value = "/oidtroju/offEmCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offEmCommit(@RequestBody final OffenderExternalMovementsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(username);
		try {
			liReturn = oidtrojuService.offEmCommit(commitBean);
			if(liReturn != 0) {
				prosmainService.enableTriggers(commitBean, authorization, "9");
			}
		} catch (Exception e) {
			logger.error("In method offEmCommit", e);
			String error = e.getMessage();
			if (error.contains("off_em_move_rsn_f1")) {
				return liReturn;
			}
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtroju/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oidtrojuService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error("In method sysPflExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtroju/checkMovementDate", method = RequestMethod.POST)
	public Integer checkMovementDate(@RequestBody final OffenderExternalMovements obj) {
		Integer returnVal=null;
		try {
			returnVal = oidtrojuService.checkMovementDate(obj);
		} catch (Exception e) {
			logger.error("In method sysPflExecuteQuery", e);
			
		}
		return returnVal;
	}

}