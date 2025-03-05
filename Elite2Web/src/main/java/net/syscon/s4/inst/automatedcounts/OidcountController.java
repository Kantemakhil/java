package net.syscon.s4.inst.automatedcounts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypesCommitBean;
import net.syscon.s4.inst.automatedcounts.beans.TempOidcount;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OidcountController
 */
@EliteController
public class OidcountController {
	@Autowired
	private OidcountService oidcountService;
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidcountController.class.getName());

	/**
	 * getting cgfkAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcount/cgfkAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkAgyLocIdRecordGroup(@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidcountService.cgfkAgyLocIdRecordGroup(caseLoadId);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting cgfkCountTypes LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcount/cgfkCountTypesRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkCountTypesRecordGroup(@RequestParam(value = "agyLocId") final String agyLocId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidcountService.cgfkCountTypesRecordGroup(agyLocId);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting cgfkScheduledTime LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcount/cgfkScheduledTimeRecordGroup", method = RequestMethod.GET)
	public List<AgencyCountTypes> cgfkScheduledTimeRecordGroup(@RequestParam(value = "agyLocId") final String agyLocId,
			@RequestParam(value = "countTypeId") final String countTypeId) {
		List<AgencyCountTypes> recordList = new ArrayList<AgencyCountTypes>();
		try {
			recordList = oidcountService.cgfkScheduledTimeRecordGroup(agyLocId, countTypeId);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting cgfkScheduledTime LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcount/getGlobalSessionId", method = RequestMethod.GET)
	public Long getGlobalSessionId() {
		Long returnValue = null;
		try {
			returnValue = oidcountService.getGlobalSessionId();
		} catch (Exception e) {
			logger.error(e);
		}
		return returnValue;
	}

	/**
	 * getting cgfkScheduledTime LOV values
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidcount/countLockedMoulesCursor", method = RequestMethod.POST)
	public Map<String, Object> countLockedMoulesCursor(@RequestBody final AgencyCountTypes bean, @RequestHeader HttpHeaders headers) {
		Map<String, Object> returnValue = new HashMap<>();
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if(bean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				bean.setCreateUserId(userName);
			}
			returnValue = oidcountService.countLockedMoulesCursor(bean);
			if(returnValue != null) {
				AgencyCountTypesCommitBean commitBean = new AgencyCountTypesCommitBean();
				List<AgencyCountTypes> AgencyCountTypesList = new ArrayList<AgencyCountTypes>();
				AgencyCountTypesList.add(bean);
				commitBean.setInsertList(AgencyCountTypesList);
				prosmainService.enableTriggers(commitBean, authorization, "26");
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return returnValue;
	}
	/**
	 * getting cgfkScheduledTime LOV values
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidcount/initiateCountSetup", method = RequestMethod.POST)
	public List<TempOidcount> initiateCountSetup(@RequestBody final AgencyCountTypes bean) {
		List<TempOidcount> recordList = new ArrayList<TempOidcount>();
		try {
			if(bean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				bean.setCreateUserId(userName);
			}
			recordList = oidcountService.initiateCountSetup(bean);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}
	/**
	 * getting CountTypeId 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcount/getCountTypeIdFromDb", method = RequestMethod.POST)
	public Integer getCountTypeId(@RequestBody final AgencyCountTypes bean) {
		Integer returnValue = 0;
		try {
			returnValue = oidcountService.getCountTypeIdFromDb(bean);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnValue;
	}
	/**
	 * getting CountTypeId 
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidcount/deleteInitiateRecords", method = RequestMethod.POST)
	public Integer deleteInitiateRecords(@RequestBody final AgencyCountTypes bean) {
		Integer returnList = 0;
		try {
			if(bean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				bean.setCreateUserId(userName);
				bean.setModifyUserId(userName);
			}
			returnList = oidcountService.deleteInitiateRecords(bean);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}
	/**
	 * getting CountTypeId 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcount/cgwhenNewFormInstanceCgPsessionId", method = RequestMethod.GET)
	public List<TempOidcount> cgwhenNewFormInstanceCgPsessionId(@RequestParam(value = "caseloadId") final String caseloadId) {
		List<TempOidcount> returnValue = new ArrayList<TempOidcount>();
		try {
			returnValue = oidcountService.cgwhenNewFormInstanceCgPsessionId(caseloadId);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnValue;
	}
	/**
	 * getting CountTypeId 
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidcount/checkRemoveDeadJobsProcedure", method = RequestMethod.GET)
	public Integer checkRemoveDeadJobsProcedure(@RequestParam(value="sessionId") final Integer sessionId) {
		Integer returnList = 0;
		try {
			returnList = oidcountService.checkRemoveDeadJobsProcedure(sessionId,SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcount/cgwhenNewFormInstanceLockedModules", method = RequestMethod.GET)
	public String cgwhenNewFormInstanceLockedModules(@RequestParam(value="sessionId") final Integer sessionId, @RequestParam(value = "caseLoadId") final String caseLoadId) {
		String returnList =null;
		final String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			returnList = oidcountService.cgwhenNewFormInstanceLockedModules(sessionId,caseLoadId,userId);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcount/checkExistingCountSession", method = RequestMethod.GET)
	public List<TempOidcount> checkExistingCountSession(@RequestParam(value="sessionId") final Integer sessionId,
			 @RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<TempOidcount> recordList = new ArrayList<TempOidcount>();
		try {
			recordList = oidcountService.checkExistingCountSession(sessionId, caseLoadId);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcount/refreshCount", method = RequestMethod.GET)
	public TempOidcount refreshCount(@RequestParam(value="sessionId") final Integer sessionId) {
		TempOidcount recordList = new TempOidcount();
		try {
			recordList = oidcountService.refreshCount(sessionId);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcount/refreshCountOfTempOidCount", method = RequestMethod.GET)
	public List<TempOidcount> refreshCountOfTempOidCount(@RequestParam(value="sessionId") final Integer sessionId) {
		List<TempOidcount> recordList = new ArrayList<>();
		try {
			recordList = oidcountService.refreshCountOfTempOidCount(sessionId);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidcount/submitCountSetTempOidcount", method = RequestMethod.POST)
	public Integer submitCountSetTempOidcount(@RequestBody final AgencyCountTypes paramBean) {
		Integer returnList = 0;
		try {
			if(paramBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				paramBean.setCreateUserId(userName);
				paramBean.setModifyUserId(userName);
			}
			returnList = oidcountService.submitCountSetTempOidcount(paramBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcount/refreshCountUserCancelledCur", method = RequestMethod.GET)
	public String refreshCountUserCancelledCur(@RequestParam(value = "sessionId") final Integer sessionId,
			@RequestParam(value = "userId") final String userId) {
		String returnValue = null;
		try {
			returnValue = oidcountService.refreshCountUserCancelledCur(sessionId,userId);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnValue;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcount/getTimerValue", method = RequestMethod.GET)
	public Integer getTimerValue() {
		Integer timerValue = 0;
		try {
			timerValue = oidcountService.getTimerValue();
		} catch (Exception e) {
			logger.error("getTimerValue", e);
		}
		return timerValue;
	}
}