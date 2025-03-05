package net.syscon.s4.cm.teamsworkflow.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import net.syscon.s4.cm.teamsworkflow.OcdaworkRepository;
import net.syscon.s4.cm.teamsworkflow.OcdaworkService;
import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueueCommitBean;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembersCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemSettingsBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalconfiguration.OumsysetService;
import net.syscon.s4.globalconfiguration.impl.OumsypflServiceImpl;
import net.syscon.s4.im.beans.Teams;

/**
 * Class OcdaworkServiceImpl
 */
@Service
public class OcdaworkServiceImpl extends BaseBusiness implements OcdaworkService {

	@Autowired
	private OcdaworkRepository ocdaworkRepository;
	
	@Autowired
	private OumsysetService oumsysetService;
	
	RestTemplate rt = new RestTemplate();

	private static Logger logger = LogManager.getLogger(OcdaworkServiceImpl.class.getName());

	/**
	 * Creates new OcdaworkServiceImpl class Object
	 */
	public OcdaworkServiceImpl() {
		// OcdaworkServiceImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<TagWorkflowBrowseQueue> teamQueueExecuteQuery(final TagWorkflowBrowseQueue searchRecord) {
		List<TagWorkflowBrowseQueue> returnList = new ArrayList<>();
		List<Map<String, Object>> taskData = new ArrayList<>();
		if (searchRecord.getUserId() != null) {
			taskData = getTaskAssignedToUser(searchRecord.getUserId());
		} else if (searchRecord.getTeamId() != null) {
			taskData = getTaskAssignedToGroup(searchRecord.getTeamCode());

		}
		returnList = getAssignedTaskList(taskData);

		return returnList;

	}

	private List<TagWorkflowBrowseQueue> getAssignedTaskList(List<Map<String, Object>> taskData) {
		List<TagWorkflowBrowseQueue> taskList = new ArrayList<>();
		
		OcdalertTaskDescriptioParser alertParserObj = new OcdalertTaskDescriptioParser();
		if (taskData != null && !taskData.isEmpty()) {
			for (Map<String, Object> taskMap : taskData) {
				Map<String, Object> parserMap = null;
				TagWorkflowBrowseQueue task = new TagWorkflowBrowseQueue();
				if (taskMap != null) {
					if (taskMap.containsKey("id") && taskMap.get("id") != null) {
						task.setTaskId(taskMap.get("id").toString());
					}
					if (taskMap.containsKey("description") && taskMap.get("description") != null) {
						task.setDescription(taskMap.get("description").toString());
					}
					if (taskMap.containsKey("assignee") && taskMap.get("assignee") != null) {
						task.setAssignee(taskMap.get("assignee").toString());
					}
					if (taskMap.containsKey("name") && taskMap.get("name") != null) {
						task.setWorkType(taskMap.get("name").toString());
					}
					if (task.getDescription() != null) {
						parserMap = alertParserObj.parseFormData(task.getDescription());
						if (parserMap != null) {
							getJsonParserData(task, parserMap);
						}

					}

					if (task.getOffenderBookId() != null) {
						final TagWorkflowBrowseQueue returnObj = ocdaworkRepository
								.getOffenderDetails(task.getOffenderBookId());
						task.setLastName(returnObj.getLastName());
						task.setFirstName(returnObj.getFirstName());
						task.setOffenderIdDisplay(returnObj.getOffenderIdDisplay());
					}

					taskList.add(task);

				}

			}

		}

		return taskList;
	}

	public TagWorkflowBrowseQueue getJsonParserData(TagWorkflowBrowseQueue task, Map<String, Object> parsedMap) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if (parsedMap.containsKey("offenderBookId") && parsedMap.get("offenderBookId") != null) {
				task.setOffenderBookId(Integer.valueOf(parsedMap.get("offenderBookId").toString()));
			}
			if (parsedMap.containsKey("offenderId") && parsedMap.get("offenderId") != null) {
				task.setOffenderId(new BigDecimal(parsedMap.get("offenderId").toString()));
			}
			if (parsedMap.containsKey("id") && parsedMap.get("id") != null) {
				task.setWorkId(Integer.valueOf(parsedMap.get("id").toString()));
			}
			if (parsedMap.containsKey("eventDate") && parsedMap.get("eventDate") != null) {
				task.setEventDate(formatter.parse(parsedMap.get("eventDate").toString()));
			}

			if (parsedMap.containsKey("comment") && parsedMap.get("comment") != null) {
				task.setMessageText(parsedMap.get("comment").toString());
			}
			if (parsedMap.containsKey("dueDate") && parsedMap.get("dueDate") != null) {
				task.setDueDate(formatter.parse(parsedMap.get("dueDate").toString()));
			}

		} catch (ParseException e) {

		}

		return task;

	}

	private List<Map<String, Object>> getTaskAssignedToUser(String userId) {
		List<Map<String, Object>> taskMap = null;
		String url;
		if (userId != null) {
			url = this.getAutomationUrl() + "/engine-rest/task?assignee=" + userId;
			try {
				ResponseEntity<Object> response = rt.getForEntity(url, Object.class);
				taskMap = (List<Map<String, Object>>) response.getBody();
			} catch (final Exception e) {
				logger.error("Exception :", e);
			}

		}
		return taskMap;
	}

	private List<Map<String, Object>> getTaskAssignedToGroup(String group) {
		List<Map<String, Object>> taskMap = null;
		String url;
		if (group != null) {
			url = this.getAutomationUrl() + "/engine-rest/task?candidateGroups=" + group;
			try {
				ResponseEntity<Object> response = rt.getForEntity(url, Object.class);
				taskMap = (List<Map<String, Object>>) response.getBody();
			} catch (final Exception e) {
				logger.error("Exception :", e);
			}

		}
		return taskMap;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstTEAM_QUEUE
	 *
	 * @
	 */
	@Transactional
	public Integer teamQueueCommit(final TagWorkflowBrowseQueueCommitBean commitBean) {
		int liReturn = 0;
		int deleteTaskGroup = 0;
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final TagWorkflowBrowseQueue obj : commitBean.getUpdateList()) {
				if (obj.getAssignedFlag() != null && obj.getAssignedFlag() && obj.getAssignOtherTeam() != null
						&& obj.getAssignOtherTeam() && obj.getAssignedTeamId() != null) {
					obj.setEnableOrDisable(1);
					deleteTaskGroup = deleteTeamsAssigned(obj);
					if (deleteTaskGroup == 1) {
						obj.setTeamId(obj.getAssignedTeamId());
						liReturn = setGroupForTask(obj);
					}

				} else if (obj.getAssignedFlag() != null && obj.getAssignedFlag()
						&& obj.getAssignFromUserToTeam() != null && obj.getAssignFromUserToTeam()
						&& obj.getAssignedTeamId() != null) {
					int unAssignTask = unClaimUser(obj);
					if (unAssignTask == 1) {
						deleteTaskGroup = deleteTeamsAssigned(obj);
						if (deleteTaskGroup == 1) {
							liReturn = setGroupForTask(obj);
						}

					}

				} else if (obj.getAssignedFlag() != null && obj.getAssignedFlag() && obj.getAssignToOtherUser() != null
						&& obj.getAssignToOtherUser()) {
					int unAssignTask = unClaimUser(obj);
					// deleteTaskGroup = deleteTeamsAssigned(obj);
					if (unAssignTask == 1) {
						obj.setAssignee(obj.getAssigneeOtherUser());
						int assigneUser = assigneTaskUserInCamunda(obj);
						if (assigneUser == 1) {
							liReturn = setGroupForTask(obj);
						}
					}
				} else if (obj.getAssignedFlag() != null && obj.getAssignedFlag() && obj.getAssignToUser() != null
						&& obj.getAssignToUser()) {
					obj.setEnableOrDisable(0);
					// deleteTaskGroup = deleteTeamsAssigned(obj);
					int assigneUser = assigneTaskUserInCamunda(obj);
					if (assigneUser == 1) {
						liReturn = setGroupForTask(obj);
					}
				} else if (obj.getCompleteFlag() != null && obj.getCompleteFlag()) {
					if ("TASK".equals(obj.getWorkflowType())) {
						liReturn = completeTask(obj);
					} else if ("MEMO".equals(obj.getWorkflowType())) {
						liReturn = ocdaworkRepository.getRemoveFromQueue(obj);
					}

				}
			}
		}
		return liReturn;
	}

	private Integer deleteTeamsAssigned(TagWorkflowBrowseQueue object) {
		Integer returnValue = 0;
		List<Map<String, Object>> groupList = getGroupAssignedToTask(object.getTaskId());
		if (groupList != null && !groupList.isEmpty()) {
			for (Map<String, Object> group : groupList) {
				if (group.get("groupId") != null) {
					object.setTeamCode(group.get("groupId").toString());
					returnValue = deleteTeamAssignedToTask(object);
				}
			}
		} else {
			returnValue = 1;
		}

		return returnValue;
	}

	private String getAutomationUrl() {
		return oumsysetService.getConfValue("serverConfig", "PRODUCT", "AUT_SER_URL");
	}

	private Integer unClaimUser(TagWorkflowBrowseQueue object) {
		Integer result = null;
		HttpHeaders headers = new HttpHeaders();
		String url = this.getAutomationUrl() + "/engine-rest/task/{id}/unclaim";
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject personJsonObject = new JSONObject();
		HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
		try {
			rt.postForObject(url, request, String.class, object.getTaskId());
			result = 1;
		} catch (final Exception e) {
			result = 0;
			logger.error("Exception :", e);

		}
		return result;
	}

	private Integer deleteTeamAssignedToTask(TagWorkflowBrowseQueue object) {
		Integer result = null;
		HttpHeaders headers = new HttpHeaders();
		String url = this.getAutomationUrl() + "/engine-rest/task/{id}/identity-links/delete";
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject personJsonObject = new JSONObject();
		personJsonObject.put("groupId", object.getTeamCode());
		personJsonObject.put("type", "candidate");
		HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
		try {
			rt.postForObject(url, request, String.class, object.getTaskId());
			result = 1;
		} catch (final Exception e) {

			result = 0;
			logger.error(e.getMessage());

		}
		return result;

	}

	private Integer setGroupForTask(TagWorkflowBrowseQueue newTaskModel) {
		Integer returnValue = null;
		HttpHeaders headers = new HttpHeaders();
		String url = this.getAutomationUrl() + "/engine-rest/task/{id}/identity-links";
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject personJsonObject = new JSONObject();
		personJsonObject.put("groupId", newTaskModel.getAssignedTeamCode());
		personJsonObject.put("type", "candidate");
		HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
		try {
			rt.postForObject(url, request, String.class, newTaskModel.getTaskId());
			returnValue = 1;
		} catch (final Exception e) {
			returnValue = 0;

		}

		return returnValue;
	}

	private List<Map<String, Object>> getGroupAssignedToTask(String taskId) {
		String url = null;
		List<Map<String, Object>> taskMap = null;
		if (taskId != null) {
			url = this.getAutomationUrl() + "/engine-rest/task/{id}/identity-links";
			try {
				ResponseEntity<Object> response = rt.getForEntity(url, Object.class, taskId);
				taskMap = (List<Map<String, Object>>) response.getBody();
			} catch (final Exception e) {
				logger.error("Exception :", e);
			}

		}
		return taskMap;
	}

	private Integer completeTask(TagWorkflowBrowseQueue newTaskModel) {
		Integer result = null;
		HttpHeaders headers = new HttpHeaders();
		String url = this.getAutomationUrl() + "/engine-rest/task/{id}/complete";
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject personJsonObject = new JSONObject();
		HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
		try {
			rt.postForObject(url, request, String.class, newTaskModel.getTaskId());
			result = 1;
		} catch (final Exception e) {
			logger.error("Exception :", e);
			result = 0;

		}
		return result;

	}

	private Integer assigneTaskUserInCamunda(TagWorkflowBrowseQueue object) {
		Integer result = null;
		String userId = null;
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		String url = this.getAutomationUrl() + "/engine-rest/task/{id}/claim";
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject personJsonObject = new JSONObject();
		personJsonObject.put("userId", object.getAssignee());
		HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
		try {
			rt.postForObject(url, request, String.class, object.getTaskId());
			result = 1;
		} catch (final Exception e) {
			logger.error(e.getMessage());
			result = 0;
		}
		return result;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<TeamMembers> teamMembersExecuteQuery(TeamMembers searchRecord) {
		return ocdaworkRepository.teamMembersExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstTEAM_MEMBERS
	 *
	 * @
	 */
	@Transactional
	public Integer teamMembersCommit(final TeamMembersCommitBean commitBean) {
		int liReturn = 0;
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = ocdaworkRepository.teamMembersUpdateQuery(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgReasonRecordGroup() {
		return ocdaworkRepository.rgReasonRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgSexRecordGroup() {
		return ocdaworkRepository.rgSexRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgWorkTypeRecordGroup() {
		return ocdaworkRepository.rgWorkTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgWorkSubTypeRecordGroup() {
		return ocdaworkRepository.rgWorkSubTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgWorkflowTypeRecordGroup() {
		return ocdaworkRepository.rgWorkflowTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgPositionRecordGroup() {
		return ocdaworkRepository.rgPositionRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgRoleRecordGroup() {
		return ocdaworkRepository.rgRoleRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<Teams> rgTeamStaffRecordGroup(final String agylocId) {
		return ocdaworkRepository.rgTeamStaffRecordGroup(agylocId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<StaffMembers> rgTeamMembersRecordGroup() {
		return ocdaworkRepository.rgTeamMembersRecordGroup();

	}

	public List<StaffMembers> rgStaffSearchRecordGroup(final Integer teamId) {
		final List<StaffMembers> listStaff = ocdaworkRepository.rgStaffSearchRecordGroup(teamId);
		for (final StaffMembers staffMembers : listStaff) {
			staffMembers.setCode(staffMembers.getStaffId());
			staffMembers.setDescription(staffMembers.getStaffName());
		}
		return listStaff;
	}

	@Transactional
	public Integer getCompleteTask(final TagWorkflowBrowseQueue object) {

		return completeTask(object);
	}

}