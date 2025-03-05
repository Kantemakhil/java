package net.syscon.s4.programswithoutschedules;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.inst.programswithoutschedules.OsunmemoService;

@EliteController
public class OsunmemoController {
@Autowired
private OsunmemoService osunmemoService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OsunmemoController.class.getName());
	
	
	/**
	 *getting offender fname lname
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/osunmemo/getDisplayAuto",method=RequestMethod.GET)
	public String getDisplayAuto(@RequestParam(value = "offenderBookId") final String offenderBookId) {
		String offendername = null;
		try {
			offendername = osunmemoService.getDisplayAuto(offenderBookId);
		} catch(Exception e){
			
			logger.error("Exception :",e);
			return e.getMessage();
		}
		return offendername;
	}  
	/**
	 *getting rgWorks LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/osunmemo/rgWorksRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgWorksRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = osunmemoService.rgWorksRecordGroup();
		} catch(Exception e){
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :",e);
		    obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	/**
	 *getting rgStaff LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/osunmemo/rgStaffRecordGroup",method=RequestMethod.GET)
	public List<StaffMembers> rgStaffRecordGroup(@RequestParam(value = "workAndObId") final String workAndObId) {
		List<StaffMembers> recordList =new ArrayList<StaffMembers>();
		try {
			recordList = osunmemoService.rgStaffRecordGroup(workAndObId);
		} catch(Exception e){
			final StaffMembers obj = new StaffMembers();
			logger.error("Exception :",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgTeams LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/osunmemo/rgTeamsRecordGroup",method=RequestMethod.GET)
	public List<TeamMembers> rgTeamsRecordGroup(@RequestParam(value = "staffWorkAndObId") final String staffWorkAndObId) {
		List<TeamMembers> recordList =new ArrayList<TeamMembers>();
		try {
			recordList = osunmemoService.rgTeamsRecordGroup(staffWorkAndObId);
		} catch(Exception e){
			final TeamMembers obj = new TeamMembers();
			logger.error("Exception :",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgSeverity LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/osunmemo/rgSeverityRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgSeverityRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = osunmemoService.rgSeverityRecordGroup();
		} catch(Exception e){
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	/**
	 *save data 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/osunmemo/submitAdhocWorkflow",method=RequestMethod.POST)
	public  List<TagWorkflowBrowseQueue> submitAdhocWorkflow(@RequestBody final TagWorkflowBrowseQueue newMemoModel) {
		List<TagWorkflowBrowseQueue> liReturn = new ArrayList<>();
		try {
			liReturn = osunmemoService.submitAdhocWorkflow(newMemoModel);

		} catch (Exception e) {
			final TagWorkflowBrowseQueue error = new TagWorkflowBrowseQueue();
			logger.error("Exception : submitAdhocWorkflow", e);
			error.setErrorMessage(e.getMessage());
			liReturn.add(error);
			
		}

		return liReturn;
	}
	
	
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/osunmemo/getTeamemberId",method=RequestMethod.GET)
	public @ResponseBody Integer  getTeamemberId(@RequestParam(value = "teamMemberId") final String teamMemberId) {
		Integer   liReturn = 0;
		try {
			liReturn = osunmemoService.getTeamemberId(teamMemberId);

		} catch (Exception e) {
			logger.error("Exception : submitAdhocWorkflow", e.getMessage());
		}

		return liReturn;
	}
	
	
	

}