package net.syscon.s4.inst.programswithoutschedules.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.programswithoutschedules.OsunmemoRepository;
import net.syscon.s4.inst.programswithoutschedules.OsunmemoService;

/**
 * Class OsunmemoServiceImpl */
@Service
public class OsunmemoServiceImpl extends BaseBusiness implements OsunmemoService{

@Autowired
private OsunmemoRepository osunmemoRepository;
		 

/**
 * This method is execute a Dao class method when trigger event is fired
 *
 * @param params
 *
 * @throws Exception
*/
public String getDisplayAuto(final String offenderBookId)  {
	return osunmemoRepository.getDisplayAuto(offenderBookId);
}


/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<ReferenceCodes> rgWorksRecordGroup()  {
		return osunmemoRepository.rgWorksRecordGroup();

}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<StaffMembers> rgStaffRecordGroup(final String workAndObId)  {
	 if(workAndObId.contains("-")){
			final String[] returnArray = workAndObId.split("-");
			return osunmemoRepository.rgStaffRecordGroup(returnArray[0],returnArray[1]);
	 }
	 else{
		 return  new ArrayList<>();
}

}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<TeamMembers> rgTeamsRecordGroup(final String staffWorkAndObId)  {
	List<TeamMembers> recordList = new ArrayList<TeamMembers>();
	 if(staffWorkAndObId.contains("-")){
			final String[] returnArray = staffWorkAndObId.split("-");
			if(returnArray.length == 2) {
				
				recordList = osunmemoRepository.rgTeamsRecordGroup(returnArray[0],returnArray[1],"");
				recordList.forEach(e ->{
					e.setCanDisplay(true);
				});
				return recordList;
			}else {
				recordList = osunmemoRepository.rgTeamsRecordGroup(returnArray[0],returnArray[1],returnArray[2]);
				recordList.forEach(e ->{
					e.setCanDisplay(true);
				});
				return recordList;
			}
			
	 }
	 else{
		 return  new ArrayList<>();
}

}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<ReferenceCodes> rgSeverityRecordGroup()  {
		return osunmemoRepository.rgSeverityRecordGroup();

}

/**
 * This method is used to execute a submitAdhocWorkflow procedure
 *
 *@throws SQLException
*/

@Override
public List<TagWorkflowBrowseQueue> submitAdhocWorkflow(final TagWorkflowBrowseQueue newMemoModel) {
	
	final List<TagWorkflowBrowseQueue> finalList = new ArrayList<TagWorkflowBrowseQueue>();
	final String finalMsg = getSubString(newMemoModel.getFirstName(),newMemoModel.getMessageText());
	
	newMemoModel.setMessageText(finalMsg);
	final String result = osunmemoRepository.submitAdhocWorkflow(newMemoModel);
	newMemoModel.setFunctionType(result);
	finalList.add(newMemoModel);
	return finalList;
}






private String getSubString(final String offName,final String messageText) {
	
	return osunmemoRepository.getSubString(offName,messageText);
}


@Override
public Integer getTeamemberId(final String teamMemberId) {
	int retData =0;
	
	if(teamMemberId.contains("-")){
		final String[] returnArray = teamMemberId.split("-");
		retData = osunmemoRepository.getTeamemberId(returnArray[0],returnArray[1]);
		
 }
	
	return retData;
}

}