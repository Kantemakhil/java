package net.syscon.s4.inst.programswithoutschedules.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalconfiguration.OumsysetService;
import net.syscon.s4.globalconfiguration.impl.OumsypflServiceImpl;
import net.syscon.s4.inst.programswithoutschedules.OsuntaskRepository;
import net.syscon.s4.inst.programswithoutschedules.OsuntaskService;

/**
 * Class OsuntaskServiceImpl
 */
@Service
public class OsuntaskServiceImpl extends BaseBusiness implements OsuntaskService {

	@Autowired
	private OsuntaskRepository osuntaskRepo;
	
	@Autowired
	private OumsysetService oumsysetService;
	

	RestTemplate rt = new RestTemplate();
	

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	@Override
	public List<ReferenceCodes> rgWorksRecordGroup(final String caseloadId) {
		return osuntaskRepo.rgWorksRecordGroup(caseloadId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	@Override
	public List<StaffMembers> rgStaffRecordGroup(final String workAndObId) {
		if (workAndObId.contains("-")) {
			final String[] returnArray = workAndObId.split("-");
			final String workId = returnArray[0];
			final String offenderBookId = returnArray[1];
			return osuntaskRepo.rgStaffRecordGroup(workId, offenderBookId);
		} else {
			return new ArrayList<>();
		}

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	@Override
	public List<TeamMembers> rgTeamsRecordGroup(final String staffWorkAndObId) {
		List<TeamMembers> recordList = new ArrayList<TeamMembers>();
		if (staffWorkAndObId.contains("-")) {
			final String[] returnArray = staffWorkAndObId.split("-");
			final String workId = returnArray[0];
			final String offenderBookId = returnArray[1];
			String staffId = "";
			if (returnArray.length == 3) {
				staffId = returnArray[2];
			}
			recordList = osuntaskRepo.rgTeamsRecordGroup(workId, offenderBookId, staffId);
			recordList.forEach(e -> {
				e.setCanDisplay(true);
			});
			return recordList;

		} else {
			return new ArrayList<>();
		}

	}

	@Override
	public Integer getTeamemberId(final String teamMemberId) {
		int retData = 0;

		if (teamMemberId.contains("-")) {
			final String[] returnArray = teamMemberId.split("-");
			final String staffId = returnArray[0];
			final String teamId = returnArray[1];
			retData = osuntaskRepo.getTeamemberId(teamId, staffId);

		}

		return retData;
	}

	@Override
	public List<TagWorkflowBrowseQueue> submitAdhocWorkflow(final TagWorkflowBrowseQueue newTaskModel) {

		String returnValue = null;
		String finalMsg = null;
		final List<TagWorkflowBrowseQueue> finalList = new ArrayList<TagWorkflowBrowseQueue>();
		String description = newTaskModel.getMessageText();
		if (newTaskModel.getFirstName() != null) {
			finalMsg = getSubString(newTaskModel.getFirstName(), newTaskModel.getMessageText());
		} else {
			finalMsg = newTaskModel.getMessageText();
		}
		if (newTaskModel.getTeamMemberId() == null && newTaskModel.getTeamId() != null
				&& newTaskModel.getStaffId() != null) {
			newTaskModel
					.setTeamMemberId(osuntaskRepo.getTeamemberId(newTaskModel.getTeamId(), newTaskModel.getStaffId()));
		}
		newTaskModel.setMessageText(finalMsg);
		newTaskModel.setWorkflowType("TASK");
		newTaskModel.setTaskId(String.valueOf(osuntaskRepo.getTaskId()));
		newTaskModel.setMessageText(description);
		Integer createTask = saveTaskInCamunda(newTaskModel);
		if (createTask == 1) {
			returnValue = setGroupForTask(newTaskModel);
		}

		newTaskModel.setFunctionType(returnValue);
		finalList.add(newTaskModel);
		return finalList;
	}

	private Integer saveTaskInCamunda(TagWorkflowBrowseQueue newTaskModel) {
		Integer returnValue = null;
		String userId = null;
		HttpHeaders headers = new HttpHeaders();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		if (newTaskModel.getStaffId() != null) {
			userId = osuntaskRepo.getUserId(newTaskModel.getStaffId());
		}
		String url = this.getAutomationUrl() + "/engine-rest/task/create";
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject personJsonObject = new JSONObject();
		JSONObject taskData = prepareJsonObject(newTaskModel);
		personJsonObject.put("id", newTaskModel.getTaskId());
		personJsonObject.put("name", newTaskModel.getWorkType());
		personJsonObject.put("assignee", userId);
		personJsonObject.put("description", taskData.toString());
		if (newTaskModel.getDueDate() != null) {
			personJsonObject.put("due", sdf.format(newTaskModel.getDueDate()));
		}

		personJsonObject.put("owner", userId);
		personJsonObject.put("delegationState", "PENDING");
		HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
		try {
			rt.postForObject(url, request, String.class);
			returnValue = 1;
		} catch (final Exception e) {
			returnValue = 2;

		}

		return returnValue;

	}

	private JSONObject prepareJsonObject(TagWorkflowBrowseQueue newTaskModel) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(date);
		JSONObject taskData = new JSONObject();
		JSONObject uniqueIds = new JSONObject();
		JSONObject formData = new JSONObject();
		uniqueIds.put("offenderId", newTaskModel.getOffenderId());
		uniqueIds.put("offenderBookId", newTaskModel.getOffenderBookId());
		taskData.put("uniqueId", uniqueIds);
		
		formData.put("id", newTaskModel.getWorkId());
		formData.put("type", newTaskModel.getWorkType());
		formData.put("subType", newTaskModel.getWorkSubType());
		//formData.put("workflowType", newTaskModel.getWorkflowType());
		formData.put("dueDate", newTaskModel.getDueDate());
		formData.put("eventDate", strDate);
		formData.put("comment", newTaskModel.getMessageText());
		formData.put("functionType", newTaskModel.getFunctionType());
		taskData.put("formData", formData);
		
		taskData.put("formType", newTaskModel.getSourceName());
		return taskData;

	}

	private String getAutomationUrl() {
		return oumsysetService.getConfValue("serverConfig", "PRODUCT", "AUT_SER_URL");
	}

	private String setGroupForTask(TagWorkflowBrowseQueue newTaskModel) {
		String returnValue = null;
		String teamCode = null;
		HttpHeaders headers = new HttpHeaders();
		String url = this.getAutomationUrl() + "/engine-rest/task/{id}/identity-links";
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject personJsonObject = new JSONObject();
		personJsonObject.put("groupId", osuntaskRepo.getTeamCode(newTaskModel.getTeamId()));
		personJsonObject.put("type", "candidate");
		HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
		try {
			rt.postForObject(url, request, String.class, newTaskModel.getTaskId());
			returnValue = "1";
		} catch (final Exception e) {
			returnValue = "2";

		}

		return returnValue;
	}

	private String getSubString(final String offName, final String messageText) {

		return osuntaskRepo.getSubString(offName, messageText);
	}

}