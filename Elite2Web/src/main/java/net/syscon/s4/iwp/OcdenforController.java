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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.legals.beans.CommonLov;
import net.syscon.s4.inst.legals.beans.OffenderProceedings;
import net.syscon.s4.inst.legals.beans.OffenderProceedingsCommitBean;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.inst.legals.beans.OffenderSentConditionsCommitBean;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * @author Arkin Software Technologies
 * @version 1.0
 */
@EliteController
public class OcdenforController {
	@Autowired
	private OcdenforService ocdenforService;
	
	@Autowired
	private ProsmainService prosmainService;
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdenforController.class.getName());
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdenfor/rgAgyLocsRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocsRecordGroup(@RequestParam String proceedingType) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ocdenforService.rgAgyLocsRecordGroup(proceedingType);
		} catch (Exception e) {
			AgencyLocations obj = new AgencyLocations();
			logger.error(this.getClass().getName() + " rgAgyLocsRecordGroup and Exception is : {}", e.getMessage());
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdenfor/rgTeamResponsibleRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTeamResponsibleRecordGroup(@RequestParam String agylocId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			recordList = ocdenforService.rgTeamResponsibleRecordGroup(userId, agylocId);
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error(this.getClass().getName() + " rgTeamResponsibleRecordGroup and Exception is : {}", e.getMessage());
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdenfor/rgStaffResponsibleRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgStaffResponsibleRecordGroup(@RequestParam String caseloadId, @RequestParam String teamResponsible) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdenforService.rgStaffResponsibleRecordGroup(caseloadId, teamResponsible);
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error(this.getClass().getName() + " rgStaffResponsibleRecordGroup and Exception is : {}", e.getMessage());
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdenfor/actionsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderProceedings> actionsExecuteQuery(@RequestBody OffenderProceedings searchBean) {
		List<OffenderProceedings> searchResult = new ArrayList<>();
		try {
			searchResult = ocdenforService.actionsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " actionsExecuteQuery and Exception is : {}", e.getMessage());
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
	@RequestMapping(value = "/ocdenfor/actionsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer actionsCommit(@RequestBody OffenderProceedingsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdenforService.actionsCommit(commitBean);
			if(liReturn == 1) {
				prosmainService.enableTriggers(commitBean, authorization, "80");
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " actionsCommit and Exception is : {}", e.getMessage());
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdenfor/scheduleConflict", method = RequestMethod.POST)
	public Long scheduleConflict(@RequestBody CourtEvents  searchBean) {
		Long count = null;
		try {
			count = ocdenforService.scheduleConflict(searchBean);
		} catch (final Exception e) {
			logger.error("Exception : Oimcrtor:", e);
		}
		return count;
	}
	
	@RequestMapping(value = "/ocdenfor/getConditionTypeGridData", method = RequestMethod.POST)
	public List<OffenderSentConditions> getConditionTypeGridData(@RequestBody OffenderSentConditions condition) {
		List<OffenderSentConditions> result = new ArrayList<>();
		try {
			result = ocdenforService.getConditionTypeGridData(condition);
		} catch (Exception e) {
			logger.error("getConditionTypeGridData", e);
		}
		return result;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdenfor/conditionProceedSave", method = RequestMethod.POST)
	public @ResponseBody Integer conditionProceedSave(@RequestBody OffenderSentConditionsCommitBean commitBean) {
		int liReturn = 0;
		
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdenforService.conditionProceedSave(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " conditionProceedSave and Exception is : {}", e.getMessage());
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdenfor/getPersutHideShowValue", method = RequestMethod.GET)
	public String getPersutHideShowValue(@RequestParam("code") final String code) {
		String returnValue=null;
		try {
			returnValue = ocdenforService.getPersutHideShowValue(code);
		} catch (Exception e) {
			final AgencyInternalLocations obj = new AgencyInternalLocations();
			logger.error("Exception : getPersutHideShowValue:", e);
			
		}
		return returnValue;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdenfor/getProgram", method = RequestMethod.GET)
	public List<CommonLov> getProgram() {
		List<CommonLov> result = new ArrayList<>();
		try {
			result = ocdenforService.getProgram();
		} catch (Exception e) {
			logger.error("getProgram", e);
		}
		return result;
	}

}