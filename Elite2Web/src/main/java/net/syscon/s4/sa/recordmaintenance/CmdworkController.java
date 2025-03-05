package net.syscon.s4.sa.recordmaintenance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@EliteController
public class CmdworkController {
	@Autowired
	private CmdworkService cmdworkService;
	
	private static Logger logger = LogManager.getLogger(OumbadmiController.class.getName());
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/cmdwork/rgWorkTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgWorkTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = cmdworkService.rgWorkTypeRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in rgWorkTypeRecordGroup: Cmdwork:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/cmdwork/rgModulesRecordGroup", method = RequestMethod.GET)
	public List<CmdModules> rgModulesRecordGroup() {
		List<CmdModules> recordList = new ArrayList<>();
		try {
			recordList = cmdworkService.rgModulesRecordGroup("USER_TASK");
		} catch (Exception e) {
			CmdModules obj = new CmdModules();
			logger.error("Exception in rgModulesRecordGroup: Cmdwork:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/cmdwork/rgModuleTriggersRecordGroup", method = RequestMethod.GET)
	public List<ModuleTriggers> rgModuleTriggersRecordGroup() {
		List<ModuleTriggers> recordList = new ArrayList<>();
		try {
			recordList = cmdworkService.rgModuleTriggersRecordGroup();
		} catch (Exception e) {
			ModuleTriggers obj = new ModuleTriggers();
			logger.error("Exception in rgModuleTriggersRecordGroup: Cmdwork:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/cmdwork/rgProcessRecordGroup", method = RequestMethod.GET)
	public List<BpmnProcess> rgProcessRecordGroup() {
		List<BpmnProcess> recordList = new ArrayList<>();
		try {
			recordList = cmdworkService.rgProcessRecordGroup();
		} catch (Exception e) {
			BpmnProcess obj = new BpmnProcess();
			logger.error("Exception in rgWorkTypeRecordGroup: Cmdwork:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/cmdwork/workItemsExecuteQuery", method = RequestMethod.GET)
	public List<WorkItems> workItemsExecuteQuery() {
		List<WorkItems> searchResult = new ArrayList<>();
		try {
			searchResult = cmdworkService.workItemsExecuteQuery();
		} catch (Exception e) {
			WorkItems bean = new WorkItems();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/cmdwork/commitWorkItems", method = RequestMethod.POST)
	public @ResponseBody Integer commitWorkItems(@RequestBody WorkItemsCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = cmdworkService.commitWorkItems(commitBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/cmdwork/deleteTrigger", method = RequestMethod.POST)
	public @ResponseBody Integer deleteTrigger(@RequestBody BpmnProcess bpmnProcess) {
		int liReturn = 0;
		final String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		bpmnProcess.setModifyUserId(user);
		try {
			liReturn = cmdworkService.deleteTrigger(bpmnProcess);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/cmdwork/validateDeploy", method = RequestMethod.POST)
	public @ResponseBody List<BpmnProcess> validateDeploy(@RequestBody BpmnProcess bpmnProcess) {
		List<BpmnProcess> searchResult = new ArrayList<>();
		try {
			searchResult = cmdworkService.validateDeploy(bpmnProcess);
		} catch (Exception e) {
			BpmnProcess bean = new BpmnProcess();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/cmdwork/getModuleTriggerData", method = RequestMethod.GET)
	public List<ReferenceCodes> getModuleTriggerData(@RequestParam(value = "triggerId") final String triggerId) {
		List<ReferenceCodes> fieldList = new ArrayList<>();
		try {
			fieldList = cmdworkService.getModuleTriggerData(triggerId);
		} catch (Exception e) {
			logger.error("Exception in getModuleTriggerData:"+e.getMessage());
		}
		return fieldList;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/cmdwork/deleteBulkTrigger", method = RequestMethod.POST)
	public @ResponseBody Integer deleteBulkTrigger(@RequestBody List<BpmnProcess> bpmnProcessList) {
		int liReturn = 0;
		final String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		
		try {
			for(BpmnProcess delObj : bpmnProcessList) {
				delObj.setModifyUserId(user);
				liReturn = cmdworkService.deleteTrigger(delObj);
			}
		} catch (Exception e) {
			logger.error("Exception in deleteBulkTrigger {} :", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/cmdwork/validateBulkDeploy", method = RequestMethod.POST)
	public @ResponseBody List<Map<String,Object>> validateBulkDeploy(@RequestBody List<BpmnProcess> bpmnProcessList) {
		List<Map<String,Object>> returnList = new ArrayList<Map<String,Object>>();
		List<BpmnProcess> searchResult = new ArrayList<>();
		try {
			Map<String,Object> triggerObj = new HashMap<String, Object>();
			for( BpmnProcess obj  : bpmnProcessList) {
				searchResult = new ArrayList<BpmnProcess>();
				triggerObj = new HashMap<String, Object>();
				searchResult = cmdworkService.validateDeploy(obj);
				if(searchResult != null && !searchResult.isEmpty()) {
					triggerObj.put("processList", searchResult);
					triggerObj.put("triggerId", obj.getTriggerId());
					triggerObj.put("description", cmdworkService.getTriggerName(obj.getTriggerId()));
					triggerObj.put("latestProcess",obj.getProcessDesc());
					returnList.add(triggerObj);
				}
			}
		} catch (Exception e) {
			BpmnProcess bean = new BpmnProcess();
			logger.error("Exception in validateBulkDeploy {} :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return returnList;
	}
	
}
