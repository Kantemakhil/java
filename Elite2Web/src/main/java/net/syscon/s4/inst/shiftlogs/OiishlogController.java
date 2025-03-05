package net.syscon.s4.inst.shiftlogs;

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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.shiftlogs.bean.AgencyShiftLogs;
import net.syscon.s4.inst.shiftlogs.bean.AgencyShiftLogsCommitBean;

/**
 * class OiishlogController 
 */
@EliteController
public class OiishlogController {
@Autowired
private OiishlogService oiishlogService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OiishlogController.class.getName());
	/**
	 *getting cgfkAgyShilAgyActivityCod LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiishlog/cgfkAgyShilAgyActivityCodRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> cgfkAgyShilAgyActivityCodRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = oiishlogService.cgfkAgyShilAgyActivityCodRecordGroup();
		} catch(Exception e){
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In cgfkAgyShilAgyActivityCodRecordGroups method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgAgency LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiishlog/rgAgencyRecordGroup",method=RequestMethod.GET)
	public List<AgencyLocations> rgAgencyRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId)  {
		List<AgencyLocations> recordList =new ArrayList<AgencyLocations>();
		try {
			recordList = oiishlogService.rgAgencyRecordGroup(caseloadId);
		} catch(Exception e){
			final AgencyLocations obj = new AgencyLocations();
			logger.error("In rgAgencyRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgLocation LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiishlog/rgLocationRecordGroup",method=RequestMethod.GET)
	public List<AgencyInternalLocations> rgLocationRecordGroup(@RequestParam(value = "agyLocId") final String agyLocId)  {
		List<AgencyInternalLocations> recordList =new ArrayList<AgencyInternalLocations>();
		try {
			recordList = oiishlogService.rgLocationRecordGroup(agyLocId);
		} catch(Exception e){
			final AgencyInternalLocations obj = new AgencyInternalLocations();
			logger.error("In rgLocationRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgStaff LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiishlog/rgStaffRecordGroup",method=RequestMethod.GET)
	public List<StaffMembers> rgStaffRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId)  {
		List<StaffMembers> recordList =new ArrayList<StaffMembers>();
		try {
			recordList = oiishlogService.rgStaffRecordGroup(caseloadId);
		} catch(Exception e){
			final StaffMembers obj = new StaffMembers();
			logger.error("In rgStaffRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiishlog/agyShilExecuteQuery", method=RequestMethod.POST)
	public List<AgencyShiftLogs> agyShilExecuteQuery(@RequestBody final AgencyShiftLogs searchBean) {
		List<AgencyShiftLogs> searchResult = new ArrayList<>();
		try {
			searchResult = oiishlogService.agyShilExecuteQuery(searchBean);
		} catch (Exception e) {
			final AgencyShiftLogs bean = new AgencyShiftLogs();
			logger.error("In agyShilExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 *Performing basic Oracle form functions i.e. insert,delete, update into the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oiishlog/agyShilCommit",method=RequestMethod.POST)
	public @ResponseBody Integer agyShilCommit(@RequestBody final  AgencyShiftLogsCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = oiishlogService.agyShilCommit(commitBean);
		}catch(Exception e){
			logger.error("In agyShilCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiishlog/agyShil1ExecuteQuery", method=RequestMethod.POST)
	public List<AgencyShiftLogs> agyShil1ExecuteQuery(@RequestBody final AgencyShiftLogs searchBean) {
		List<AgencyShiftLogs> searchResult = new ArrayList<>();
		try {
			searchResult = oiishlogService.agyShil1ExecuteQuery(searchBean);
		} catch (Exception e) {
			final AgencyShiftLogs bean = new AgencyShiftLogs();
			logger.error("In agyShil1ExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}
	

}