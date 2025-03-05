package net.syscon.s4.globalconfiguration;

import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.TaskUsers;

public interface OumcamtaskService {

	List<TaskUsers> getAssignedTaskList(List<Map<String,Object>> taskData,String userName);
	
	String getUserTeams(String user);
	
	Map<String,String> getProcessBpmn (Map<String, Object> processData,String instanceId);
	
	Map<String,Object> getTaskCount(String user);
	
	String completeTask(TaskUsers taskDetails);
	
	String getAutomationUrl();
	
}
