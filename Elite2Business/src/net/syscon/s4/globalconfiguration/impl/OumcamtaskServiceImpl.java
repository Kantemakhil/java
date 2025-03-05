package net.syscon.s4.globalconfiguration.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.syscon.s4.cm.teamsworkflow.impl.OcdalertTaskDescriptioParser;
import net.syscon.s4.common.beans.TaskUsers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalconfiguration.OumcamtaskService;
import net.syscon.s4.globalconfiguration.OumsysetService;
import net.syscon.s4.globaloffenderrecords.OsiosearRepository;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.workflow.maintenance.OcmteamsRepository;
import net.syscon.s4.sa.recordmaintenance.BpmnProcess;
import net.syscon.s4.sa.recordmaintenance.ProsmainRepository;

@Service
public class OumcamtaskServiceImpl extends BaseBusiness implements OumcamtaskService {

	@Autowired
	private OcmteamsRepository ocmteamsRepository;

	@Autowired
	private ProsmainRepository prosmainRepository;

	@Autowired
	private OumsysetService oumsysetService;
	
	RestTemplate rt = new RestTemplate();
	
	private static Logger logger = LogManager.getLogger(OumcamtaskServiceImpl.class);
	
	@Autowired
	private OsiosearRepository osiosearDao;

	@Override
	public List<TaskUsers> getAssignedTaskList(List<Map<String, Object>> taskData,String userName) {
		List<TaskUsers> taskList = new ArrayList<>();
		List<TaskUsers> retrunList = new ArrayList<>();
		OcdalertTaskDescriptioParser alertParserObj = new OcdalertTaskDescriptioParser();
		retrunList = PrepareTaskData();
		if (taskData != null && !taskData.isEmpty()) {
			for (Map<String, Object> taskMap : taskData) {
				TaskUsers task = new TaskUsers();
				Map<String, Object> parserMap = null;
				Map<String, Object> formVariables = null;
				String formType = null;
				if (taskMap != null) {

					if (taskMap.containsKey("name") && taskMap.get("name") != null) {
						task.setNodeName(taskMap.get("name").toString());
					}

					if (taskMap.containsKey("created") && taskMap.get("created") != null) {
						task.setCreateDate(taskMap.get("created").toString());
					}

					if (taskMap.containsKey("id") && taskMap.get("id") != null) {
						task.setTaskId(taskMap.get("id").toString());
					}

					if (taskMap.containsKey("assignee") && taskMap.get("assignee") != null) {
						task.setAssignee(taskMap.get("assignee").toString());
					}

					if (taskMap.containsKey("processInstanceId") && taskMap.get("processInstanceId") != null) {
						task.setProcessInstanceId(taskMap.get("processInstanceId").toString());
					}
					if (taskMap.containsKey("processDefinitionId") && taskMap.get("processDefinitionId") != null) {
						task.setProcessDefId(taskMap.get("processDefinitionId").toString());
					}

					if (taskMap.containsKey("description") && taskMap.get("description") != null) {
						task.setDescription(taskMap.get("description").toString());
					}
					
					if (taskMap.containsKey("due") && taskMap.get("due") != null) {
						task.setDueDate(taskMap.get("due").toString());
					}
					
					if (taskMap.containsKey("followUp") && taskMap.get("followUp") != null) {
						task.setFollowUpDate(taskMap.get("followUp").toString());
					}

					
						try {
							if (task.getProcessInstanceId() != null) {
								formVariables = getFormType(task.getTaskId());
								getJsonParserData(task, formVariables);
								getDescriptionMap(formVariables, task,userName);
								
							} else {
								
								if (task.getDescription() != null) {
								formType = alertParserObj.getFormType(task.getDescription());
								parserMap = alertParserObj.parseFormData(task.getDescription());
								task.setSourceName(formType);
								if (parserMap != null) {
									task.setDescriptionMapper(getDescriptionMap(parserMap, task,userName));
									getJsonParserData(task, parserMap);
								}
							}
							}
							

						} catch (Exception e) {
							logger.error("Exception in getAssignedTaskList:",e.getMessage());
						}

					}

				

				taskList.add(task);

			}
			retrunList = getSubTask(taskList, retrunList);

		}

		return retrunList;
	}

	private Map<String, Object> getDescriptionMap(Map<String, Object> parsedMap, TaskUsers task,String userName) {
		Map<String, Object> descMap = new LinkedHashMap<>();
		if (parsedMap.containsKey("offenderId") && parsedMap.get("offenderId") != null) {
			descMap.put("Offender ID", parsedMap.get("offenderId"));
		}
		
		if (parsedMap.containsKey("offenderBookId") && parsedMap.get("offenderBookId") != null) {
			List<VHeaderBlock> offender = osiosearDao
					.getOffenderDetials(Integer.parseInt(parsedMap.get("offenderBookId").toString()),userName);
			if (offender != null && !offender.isEmpty()) {
				if (offender.get(0).getLastName() != null && offender.get(0).getFirstName() != null) {
					task.setOffenderName(
							offender.get(0).getLastName().concat(",").concat(offender.get(0).getFirstName()));

				}
				if (offender.get(0).getOffenderId() != null) {
					task.setOffenderId(offender.get(0).getOffenderId());
				}
				if (offender.get(0).getImageThumbnail() != null) {
					task.setImageId(offender.get(0).getImageId());
					task.setImageThumbnail(offender.get(0).getImageThumbnail());
				}
				
				if (offender.get(0).getOffenderIdDisplay() != null) {
					task.setOffenderIdDisplay(offender.get(0).getOffenderIdDisplay());
				}
			}

		}else {
			if (parsedMap.containsKey("formVariableMap") && parsedMap.get("formVariableMap") != null) {
				Map<String,Object> formMap=(Map<String, Object>) parsedMap.get("formVariableMap");
				task.setDescriptionMapper(formMap);
			}
			if (parsedMap.containsKey("agencyIncidentId") && parsedMap.get("agencyIncidentId") != null) {
				task.setAgencyIncidentId(Integer.parseInt(parsedMap.get("agencyIncidentId").toString()));
			}
			
		}

		return descMap;
	}

	private TaskUsers getJsonParserData(TaskUsers task, Map<String, Object> parsedMap) {
		if (parsedMap.containsKey("offenderBookId") && parsedMap.get("offenderBookId") != null) {
			task.setOffenderBookId(Integer.valueOf(parsedMap.get("offenderBookId").toString()));
		}
		if (parsedMap.containsKey("offenderId") && parsedMap.get("offenderId") != null) {
			task.setOffenderId(new BigDecimal(parsedMap.get("offenderId").toString()));
		}
		if (parsedMap.containsKey("notification") && parsedMap.get("notification") != null && !parsedMap.get("notification").equals("''") && !parsedMap.get("notification").equals("")) {
			task.setComment(parsedMap.get("notification").toString());
		}
		if (parsedMap.containsKey("formType") && parsedMap.get("formType") != null) {
			task.setSourceName(parsedMap.get("formType").toString());
		}
		if (parsedMap.containsKey("userTaskId") && parsedMap.get("userTaskId") != null) {
			task.setIsApprovButton(true);
			task.setUserTaskId(parsedMap.get("userTaskId").toString());
		}
		return task;
	}
	

	private List<TaskUsers> getSubTask(List<TaskUsers> taskList, List<TaskUsers> parentTaskList) {
         int i=2;
		for (TaskUsers task : taskList) {
                i=i+1;
			if (task.getAssignee() != null) {
				if(task.getNodeName()!=null) {
					
				boolean exist = parentTaskList.get(0).getSubChild().stream()
						.anyMatch(obj -> obj.getNodeName().equals(task.getNodeName()));
				if (!exist) {
					TaskUsers taskObj = new TaskUsers();
					List<TaskUsers> taskChildData = new ArrayList<>();
					taskObj.setNodeId(i);
					taskObj.setNodeName(task.getNodeName());
					taskObj.setParentNodeId(1);
					taskObj.setCount(1);
					taskObj.setHasChild(false);
					task.setNodeId(i+1);
					taskChildData.add(task);
					taskObj.setChildData(taskChildData);
					parentTaskList.get(0).getSubChild().add(taskObj);
					
				} else {
					for (TaskUsers taskObj : parentTaskList.get(0).getSubChild()) {
						if (taskObj.getNodeName() != null && taskObj.getNodeName().equals(task.getNodeName())) {
							task.setNodeId(i+1);
							taskObj.getChildData().add(task);
							taskObj.setCount(taskObj.getCount() + 1);
						}
					}
				}
				parentTaskList.get(0).setTaskCount(parentTaskList.get(0).getTaskCount()+1);
				i =i+1;
				}
			} else {
				if(task.getNodeName()!=null) {
					
				boolean exist = parentTaskList.get(1).getSubChild().stream()
						.anyMatch(obj -> obj.getNodeName().equals(task.getNodeName()));
				if (!exist) {
					TaskUsers taskObj = new TaskUsers();
					List<TaskUsers> taskChildData = new ArrayList<>();
					taskObj.setNodeName(task.getNodeName());
					taskObj.setNodeId(i);
					taskObj.setParentNodeId(2);
					taskObj.setCount(1);
					taskObj.setHasChild(false);
					task.setNodeId(i+1);
					taskChildData.add(task);
					taskObj.setChildData(taskChildData);
					parentTaskList.get(1).getSubChild().add(taskObj);
				} else {
					for (TaskUsers taskObj : parentTaskList.get(1).getSubChild()) {
						if (taskObj.getNodeName() != null && taskObj.getNodeName().equals(task.getNodeName())) {
							task.setNodeId(i+1);
							taskObj.getChildData().add(task);
							taskObj.setCount(taskObj.getCount() + 1);
						}
					}
				}
				parentTaskList.get(1).setTaskCount(parentTaskList.get(1).getTaskCount()+1);
				i =i+1;
			}
			}
			parentTaskList.get(0).setMaxNodeId(i+2);
			parentTaskList.get(1).setMaxNodeId(i+2);

		}

		return parentTaskList;

	}

	@Override
	public String getUserTeams(String user) {
		String group = null;
		List<Teams> teams = ocmteamsRepository.getTeamsBasedOnUser(user);
		List<String> teamList = new ArrayList<>();
		if (teams != null && !teams.isEmpty()) {
			for (Teams team : teams) {
				teamList.add(team.getTeamCode());
			}
			group = String.join(",", teamList);
		}

		return group;
	}

	private List<TaskUsers> PrepareTaskData() {
		List<TaskUsers> taskList = new ArrayList<>();
		List<TaskUsers> childList = new ArrayList<>();
		List<TaskUsers> childDataList = new ArrayList<>();
		List<TaskUsers> child = new ArrayList<>();
		List<TaskUsers> subChildList = new ArrayList<>();
		TaskUsers assigneTask = new TaskUsers();
		TaskUsers unAssigneTask = new TaskUsers();
		assigneTask.setNodeId(1);
		assigneTask.setNodeName("My Tasks");
		assigneTask.setTaskCount(0);
		assigneTask.setIcon("assignment_ind");
		assigneTask.setSubChild(childList);
		assigneTask.setChildData(childDataList);
		unAssigneTask.setNodeId(2);
		unAssigneTask.setNodeName("Team Tasks");
		unAssigneTask.setTaskCount(0);
		unAssigneTask.setIcon("group");
		unAssigneTask.setSubChild(subChildList);
		unAssigneTask.setChildData(child);
		taskList.add(assigneTask);
		taskList.add(unAssigneTask);

		return taskList;
	}

	@Override
	public Map<String, String> getProcessBpmn(Map<String, Object> processData, String instanceId) {
		Map<String, String> bpmnData = new HashMap<>();
		String bpmnFile = null;
		if (processData != null && processData.containsKey("definitionId") && processData.get("definitionId") != null) {
			try {
				String deployId = getDeploymentId(processData.get("definitionId").toString());
				if (deployId != null) {
					BpmnProcess processDetails = prosmainRepository.getProcessDetails(deployId);
					if (processDetails != null && processDetails.getBpmnFile() != null) {
						byte[] bdata = processDetails.getBpmnFile();
						bpmnFile = new String(bdata);
						bpmnData.put("bpmn", bpmnFile);
						if (bpmnFile != null) {
							bpmnData.put("activityId", getActiveInstanceId(instanceId));
						}

					}
				}
			} catch (Exception e) {
				logger.error("Exception in getProcessBpmn:"+e.getMessage());
			}

		}

		return bpmnData;
	}

	private String getActiveInstanceId(String instanceId) {
		String activityId = null;
		Map<String, Object> taskMap = new HashMap<>();
		List<Map<String, Object>> actvityList = new ArrayList<>();
		String url = this.getAutomationUrl() + "/engine-rest/process-instance/{id}/activity-instances";
		try {
			ResponseEntity<Object> response = rt.getForEntity(url, Object.class, instanceId);
			taskMap = (Map<String, Object>) response.getBody();
			if (taskMap != null && taskMap.containsKey("childActivityInstances")
					&& taskMap.get("childActivityInstances") != null) {
				actvityList = (List<Map<String, Object>>) taskMap.get("childActivityInstances");
				for (Map<String, Object> activeMap : actvityList) {
					if (activeMap.containsKey("activityId") && activeMap.get("activityId") != null) {
						activityId = activeMap.get("activityId").toString();
					}

				}
			}

		} catch (final Exception e) {
			logger.error("Exception in getActiveInstanceId:"+e.getMessage());

		}
		return activityId;

	}

	private String getDeploymentId(String processDefId) {

		String deployId = null;
		Map<String, Object> taskMap = new HashMap<>();
		String url = this.getAutomationUrl() + "/engine-rest/process-definition/{id}";
		try {
			ResponseEntity<Object> response = rt.getForEntity(url, Object.class, processDefId);
			taskMap = (Map<String, Object>) response.getBody();
			if (taskMap != null && taskMap.containsKey("deploymentId") && taskMap.get("deploymentId") != null) {
				deployId = taskMap.get("deploymentId").toString();
			}

		} catch (final Exception e) {
			logger.error("Exception in getDeploymentId:"+e.getMessage());

		}
		return deployId;
	}
	@Override
	public String getAutomationUrl() {
		
		
		return oumsysetService.getConfValue("serverConfig", "PRODUCT", "AUT_SER_URL");
	}

	private Map<String,Object> getFormType(String taskId) {
		Map<String, Object> module;
		Map<String,Object> returnObj=new HashMap<>();
		Map<String, Object> taskMap = new HashMap<>();
		Map<String, Object> formVariableMap = new HashMap<>();
		Map<String, Object> valueMap;
	    
		String url = this.getAutomationUrl() + "/engine-rest/task/{id}/form-variables";
		try {
			ResponseEntity<Object> response = rt.getForEntity(url, Object.class, taskId);
			taskMap = (Map<String, Object>) response.getBody();
			if (taskMap != null && taskMap.containsKey("moduleName") && taskMap.get("moduleName") != null ) {
				module = (Map<String, Object>) taskMap.get("moduleName");
				if (module != null && module.containsKey("value") && module.get("value") != null  && module.get("value")!="") {
					returnObj.put("formType", module.get("value").toString());
				}
			}
			if (taskMap != null && taskMap.containsKey("offenderId") && taskMap.get("offenderId") != null) {
				module = (Map<String, Object>) taskMap.get("offenderId");
				
				if (module != null && module.containsKey("value") && module.get("value") != null && module.get("value")!="") {
					returnObj.put("offenderId", module.get("value").toString());
				}
			}
			if (taskMap != null && taskMap.containsKey("notification") && taskMap.get("notification") != null) {
				module = (Map<String, Object>) taskMap.get("notification");
				if (module != null && module.containsKey("value") && module.get("value") != null  && module.get("value")!="") {
					returnObj.put("notification", module.get("value").toString());
				}
			}
			if (taskMap != null && taskMap.containsKey("offenderBookId") && taskMap.get("offenderBookId") != null) {
				module = (Map<String, Object>) taskMap.get("offenderBookId");
				if (module != null && module.containsKey("value") && module.get("value") != null  && module.get("value")!="") {
					returnObj.put("offenderBookId", module.get("value").toString());
				}
			}
			for (Entry<String, Object> entry : taskMap.entrySet()) {
		        if (entry.getKey().contains("approveBtn")) {
		        	String key=entry.getKey();
		        	if(key!=null) {
		        		String[] keys=key.split("approveBtn");	
		        		if(keys!=null && keys[0]!=null) {
		        			returnObj.put("userTaskId", keys[0]);
		        		}
			        	
		        	}
		        	
		        }
		        valueMap=(Map<String, Object>) entry.getValue();
		        if(valueMap!=null && valueMap.containsKey("value") && valueMap.get("value") != null  && valueMap.get("value")!="" && !entry.getKey().equals("authorization")&& !entry.getKey().equals("url"))
		        formVariableMap.put(entry.getKey(), valueMap.get("value"));
                  if(entry.getKey().equals("agencyIncidentId") ) {
                	  returnObj.put("agencyIncidentId", valueMap.get("value"));
		        }
		    }
			returnObj.put("formVariableMap", formVariableMap);

		} catch (final Exception e) {
			logger.error("Exception in getFormType:"+e.getMessage());
		}
		return returnObj;
	}

	@Override
	public Map<String, Object> getTaskCount(String user) {
		Map<String, Object> taskMap=new HashMap<>();
		Map<String, Object> countMap;
		Integer teamTask=0;
		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
		String url = this.getAutomationUrl()+"/engine-rest/task/count?assignee=" + user;
		try {
			ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
			if(response.getStatusCode().value() == 200) { 
				countMap =  (Map<String, Object>) response.getBody();
				if(countMap!=null && countMap.containsKey("count") && countMap.get("count")!=null) {
					taskMap.put("userTask", Integer.parseInt(countMap.get("count").toString()));
				}
				teamTask=getTeamTaskCount(user);
				taskMap.put("teamTask", teamTask);
			} else {
				// TODO - Need to set blank.
			}
		} catch (final Exception e) {
			logger.error("Exception in getTaskCount:"+e.getMessage());
		}
		return taskMap;
	}
	
	private Integer getTeamTaskCount(String user) {
		Integer assigneTask=0;
		Map<String, Object> countMap;
		String group = getUserTeams(user);
		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
		if(group!=null) {
			String url = this.getAutomationUrl()+"/engine-rest/task/count?candidateGroups=" + group;
			try {
				ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
				if(response.getStatusCode().value() == 200) { 
					countMap =  (Map<String, Object>) response.getBody();
					if(countMap!=null && countMap.containsKey("count") && countMap.get("count")!=null) {
						assigneTask = Integer.parseInt(countMap.get("count").toString());
					}
				}
			} catch (final Exception e) {
				logger.error("Exception in getTeamTaskCount:"+e.getMessage());
			}
		}
		return assigneTask;
	}

	@Override
	public String completeTask(TaskUsers taskDetails) {
		String result = null;
		HttpHeaders headers = new HttpHeaders();
		String url = this.getAutomationUrl()+"/engine-rest/task/{id}/complete";
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject taskJsonObject = new JSONObject();
		JSONObject variable = new JSONObject();
		if(taskDetails.getIsApprovButton()!=null && taskDetails.getIsApprovButton()) {
			if(taskDetails.getActionButton()!=null && taskDetails.getActionButton().trim().equals("A")) {
				JSONObject action = new JSONObject();
				action.put("value", "A");
				if(taskDetails.getUserTaskId()!=null) {
					variable.put(taskDetails.getUserTaskId().concat("actionFlag"), action);
				}
				taskJsonObject.put("variables", variable);
				taskJsonObject.put("withVariablesInReturn", true);
				
				
			}else if(taskDetails.getActionButton()!=null && taskDetails.getActionButton().trim().equals("R")) {
				JSONObject action = new JSONObject();
				action.put("value", "R");
				if(taskDetails.getUserTaskId()!=null) {
					variable.put(taskDetails.getUserTaskId().concat("actionFlag"), action);
				}
				JSONObject reject = new JSONObject();
				reject.put("value", taskDetails.getComment());
				if(taskDetails.getUserTaskId()!=null) {
					variable.put(taskDetails.getUserTaskId().concat("rejectionReason"), reject);
				}
				taskJsonObject.put("variables", variable);
				taskJsonObject.put("withVariablesInReturn", true);
			}
			
		}
		HttpEntity<String> request = new HttpEntity<String>(taskJsonObject.toString(), headers);
		try {
			rt.postForObject(url, request, String.class, taskDetails.getTaskId());
			result = "1";
		} catch (final Exception e) {
			
			logger.error("Exception in Task Completion:"+e.getMessage());

		}
		return result;
	}
	
	// Override timeouts in the request factory
	private HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(20000);
		clientHttpRequestFactory.setReadTimeout(20000);
		return clientHttpRequestFactory;
	}

}
