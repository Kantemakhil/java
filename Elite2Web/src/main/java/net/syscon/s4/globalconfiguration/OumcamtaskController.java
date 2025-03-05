package net.syscon.s4.globalconfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.TaskUsers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.VPropertyHeaderBlock;
import net.syscon.s4.globaloffenderrecords.OsiosearService;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.inst.movementexternal.beans.VHeaderBlockCommitBean;

@EliteController
public class OumcamtaskController {

	@Autowired
	private OumcamtaskService oumcamtaskService;
	
	@Autowired
	private OsiosearService osiosearService;
	
	@Autowired
	private OumsysetService oumsysetService;
	private static Logger logger = LogManager.getLogger(OumcamtaskController.class.getName());

	RestTemplate rt = new RestTemplate();

	@RequestMapping(value = "/oumcamtask/getTask", method = RequestMethod.GET)
	public List<TaskUsers> getTaskList(@RequestParam String assignee) {
		
		List<Map<String, Object>> taskMap = new ArrayList<>();
		List<Map<String, Object>> groupTasks = new ArrayList<>();
		List<TaskUsers> taskList =new ArrayList<>();
		String url = this.getAutomationUrl()+"/engine-rest/task?assignee=" + assignee + "&sortBy=priority&sortOrder=asc";
		try {
					ResponseEntity<Object> response = rt.getForEntity(url, Object.class);
			taskMap = (List<Map<String, Object>>) response.getBody();
			groupTasks = getTaskAssignedToGroup(assignee);
			if (groupTasks != null && !groupTasks.isEmpty()) {
				taskMap.addAll(groupTasks);
			}
			if(taskMap!=null && !taskMap.isEmpty()) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				taskList = oumcamtaskService.getAssignedTaskList(taskMap,userName);
			}
			

		} catch (final Exception e) {
			return taskList;
		}
		return taskList;
	}
	
	
	private String getAutomationUrl() {
		 return oumsysetService.getConfValue("serverConfig", "PRODUCT", "AUT_SER_URL");
	}
	
	@RequestMapping(value = "/oumcamtask/getTaskBasedOnProcess", method = RequestMethod.GET)
	public List<TaskUsers> getTaskListBasedOnInstance(@RequestParam String instanceId) {

		List<Map<String, Object>> taskMap = new ArrayList<>();
		List<Map<String, Object>> groupTasks = new ArrayList<>();
		List<TaskUsers> taskList = null;
		String url = this.getAutomationUrl()+"/engine-rest/task?processInstanceId=" + instanceId;

		try {
			ResponseEntity<Object> response = rt.getForEntity(url, Object.class);
			taskMap = (List<Map<String, Object>>) response.getBody();
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			taskList = oumcamtaskService.getAssignedTaskList(taskMap,userName);

		} catch (final Exception e) {
			logger.error("Exception in getTaskListBasedOnInstance:"+e.getMessage());
		}
		return taskList;
	}
	
	@RequestMapping(value = "/oumcamtask/getDiagramForInstance", method = RequestMethod.GET)
	public Map<String, String> getDiagramForInstance(@RequestParam String instanceId) {
		Map<String, String> bpmnData = null;
		Map<String, Object> taskMap = new HashMap<>();
		String url = this.getAutomationUrl()+"/engine-rest/process-instance/{id}";

		try {
			ResponseEntity<Object> response = rt.getForEntity(url, Object.class,instanceId);
			taskMap =( Map<String, Object>) response.getBody();
			bpmnData=oumcamtaskService.getProcessBpmn(taskMap,instanceId);

		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return bpmnData;
	}
	
	

	@RequestMapping(value = "/oumcamtask/setAssignee", method = RequestMethod.POST)
	public String setAssignee(@RequestBody TaskUsers taskDetails) {
		String result = null;
		HttpHeaders headers = new HttpHeaders();
		String url = this.getAutomationUrl()+"/engine-rest/task/{id}/claim";
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject personJsonObject = new JSONObject();
		personJsonObject.put("userId", taskDetails.getAssignee());
		HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
		try {
			rt.postForObject(url, request, String.class, taskDetails.getTaskId());
			result = "1";
		} catch (final Exception e) {
			logger.error("Exception in setAssignee:"+e.getMessage());

		}
		return result;
	}
	
	@RequestMapping(value = "/oumcamtask/getXml", method = RequestMethod.GET)
	public String  getProcessXml() {
		String result = null;
		HttpHeaders headers = new HttpHeaders();
		String url = this.getAutomationUrl()+"/engine-rest/process-definition/key/{key}/xml";
		headers.setContentType(MediaType.APPLICATION_JSON);
		String id="LoanProcess";
		JSONObject personJsonObject = new JSONObject();
		HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
		try {
			Map<String,Object> response=  (Map<String, Object>) rt.getForObject(url,Object.class ,id);
			if(response!=null && response.containsKey("bpmn20Xml")) {
				result=response.get("bpmn20Xml").toString();
				result=result.substring(result.indexOf('\n')+1);
			}
		} catch (final Exception e) {
			logger.error("Exception :", e);

		}
		return result;
    	
    }



	@RequestMapping(value = "/oumcamtask/unclaim", method = RequestMethod.POST)
	public String unClaimUser(@RequestBody TaskUsers taskDetails) {
		String result = null;
		HttpHeaders headers = new HttpHeaders();
		
		String url = this.getAutomationUrl()+"/engine-rest/task/{id}/unclaim";
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject personJsonObject = new JSONObject();
		HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
		try {
			rt.postForObject(url, request, String.class, taskDetails.getTaskId());
			result = "1";
		} catch (final Exception e) {
			logger.error("Exception in unClaimUser:"+e.getMessage());

		}
		return result;
	}
	
	@RequestMapping(value = "/oumcamtask/completeTask", method = RequestMethod.POST)
	public String completeTask(@RequestBody TaskUsers taskDetails) {
		String result = null;
		try {
			result = oumcamtaskService.completeTask(taskDetails);
		} catch (final Exception e) {
			logger.error("Exception in completeTask:"+e.getMessage());
		}
		return result;
	}

	private List<Map<String, Object>> getUnAssignedTask() {
		List<Map<String, Object>> taskMap = new ArrayList<>();
		String url = "http://localhost:8091/engine-rest/task?unassigned=true";
		try {
			ResponseEntity<Object> response = rt.getForEntity(url, Object.class);
			taskMap = (List<Map<String, Object>>) response.getBody();
		} catch (final Exception e) {
			logger.error("Exception in getUnAssignedTask:"+e.getMessage());
		}

		return taskMap;

	}


	private List<Map<String, Object>> getTaskAssignedToGroup(String assignee) {
		String group;
		String url = null;
		List<Map<String, Object>> taskMap = null;
		group = oumcamtaskService.getUserTeams(assignee);
		if (group != null) {
			 url = this.getAutomationUrl()+"/engine-rest/task?candidateGroups=" + group + "&sortBy=priority&sortOrder=asc";
			try {
				ResponseEntity<Object> response = rt.getForEntity(url, Object.class);
				taskMap = (List<Map<String, Object>>) response.getBody();
			} catch (final Exception e) {
				logger.error("Exception in getTaskAssignedToGroup:"+e.getMessage());
			}

		}
		return taskMap;
	}
	
	
	@RequestMapping(value = "/oumcamtask/getTaskCount", method = RequestMethod.GET)
	public Map<String,Object> getTaskCount() {
		Map<String,Object> taskMap = null;
		try {
			String assignee = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			taskMap=oumcamtaskService.getTaskCount(assignee);

		} catch (final Exception e) {
			logger.error("Exception in getTaskCount:"+e.getMessage());
		}
		return taskMap;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumcamtask/offbkgGlobalQuery", method = RequestMethod.POST)
	public List<VHeaderBlock> offbkgGlobalQuery(@Valid @RequestBody final VHeaderBlock searchBean, @RequestHeader HttpHeaders headers) {
		List<VHeaderBlock> searchResult = new ArrayList<>();
		final VHeaderBlock bean = new VHeaderBlock();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			searchBean.setCreateUserId(userName);
			searchResult = osiosearService.offbkgGlobalQuery(searchBean);
			if(searchResult == null){
				searchResult = new ArrayList<>();
				bean.setErrorMessage("Agency Location Type  Not Provided");
				searchResult.add(bean);
				return searchResult;
			} else {
				for(VHeaderBlock vHeaderBlock : searchResult) {
					vHeaderBlock.setCreateuserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
				}
			}
		} catch (Exception e) {
			logger.error("error in oumcamtask offbkgGlobalQuery"+ e.getMessage());
			searchResult = new ArrayList<>();
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumcamtask/imageExecuteQuery", method = RequestMethod.POST)
	public List<Images> imageExecuteQuery(@RequestBody final Images searchBean) {
		List<Images> searchResult = new ArrayList<Images>();
		try {
			searchResult = osiosearService.imageExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("error in oumcamtask imageExecuteQuery"+ e.getMessage());
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumcamtask/offbkgVPHeadGlobalQuery", method = RequestMethod.POST)
	public List<VPropertyHeaderBlock> offbkgVPHeadGlobalQuery(@Valid @RequestBody final VPropertyHeaderBlock searchBean) {
		List<VPropertyHeaderBlock> searchResult = new ArrayList<>();
		final VPropertyHeaderBlock bean = new VPropertyHeaderBlock();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			searchBean.setCreateUserId(userName);
			searchResult = osiosearService.offbkgVPHeadGlobalQuery(searchBean);
		} catch (Exception e) {
			logger.error("", e);
			searchResult = new ArrayList<>();
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
}
