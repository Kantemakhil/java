package net.syscon.s4.inst.incidentsoic;

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
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VAgencyIncidents;
import net.syscon.s4.inst.incidentsoic.impl.OiiinlogServiceImpl;

/**
 * Class OiiinlogController
 */
@EliteController
public class OiiinlogController {
	
	@Autowired
	private OiiinlogServiceImpl oiiinlogservice;
	/**
	 * Logger object used to print the log in the file
	 */	
	private static Logger logger = LogManager.getLogger(OiiinlogController.class.getName());

	/**
	 * Fetch the records from database table
	 * 
	 * @param searchBean
	 *            VAgencyIncidents
	 * @return List<VAgencyIncidents>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiinlog/vAgyIncExecuteQuery", method = RequestMethod.POST)
	public List<VAgencyIncidents> vAgyIncExecuteQuery(@RequestBody final VAgencyIncidents searchBean) {
		List<VAgencyIncidents> searchResult = new ArrayList<>();
		try {
			searchResult = oiiinlogservice.vAgyIncExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("VAgencyExecute Query Error :",e);
		}
		return searchResult;
	}

	/**
	 * getting rgOccurType LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiinlog/rgOccurTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgOccurTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		
		try {
			recordList = oiiinlogservice.rgOccurTypeRecordGroup();
		} catch (Exception e) {
			logger.error("rgOccurTypeRecordGroup",e);
		}
		return recordList;
	}

	/**
	 * getting rgAgyLoc LOV values
	 * 
	 * @param searchBean
	 *            CaseloadAgencyLocations
	 * @return List<CaseloadAgencyLocations>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiinlog/rgAgyLocRecordGroup", method = RequestMethod.GET)
	public List<CaseloadAgencyLocations> rgAgyLocRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId
			, @RequestParam(value = "caseLoadType") final String caseLoadType) {
		List<CaseloadAgencyLocations> recordList = new ArrayList<CaseloadAgencyLocations>();
		
		try {
			recordList = oiiinlogservice.rgAgyLocRecordGroup(caseloadId, caseLoadType);
		} catch (Exception e) {
			logger.error("rgAgyLocRecordGroup",e);
		}
		return recordList;
	}

	/**
	 * getting rgLevelLoc LOV values
	 * 
	 * @param searchBean
	 *            AgencyInternalLocations
	 * @return List<AgencyInternalLocations>
	 * 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiinlog/rgLevelLocRecordGroup", method = RequestMethod.GET)
	public List<AgencyInternalLocations> rgLevelLocRecordGroup(@RequestParam(value = "agyLocId") final String searchBean) {
		List<AgencyInternalLocations> recordList = new ArrayList<AgencyInternalLocations>();
		
		try {
			recordList = oiiinlogservice.rgLevelLocRecordGroup(searchBean);
		} catch (Exception e) {
			logger.error("rgLevelLocRecordGroup",e);
		}
		return recordList;
	}

	/**
	 * getting rgStaff LOV values * @param String caseloadId
	 * 
	 * @return List<StaffMembers>
	 * 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiinlog/rgStaffRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgStaffRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId) {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
	
		try {
			recordList = oiiinlogservice.rgStaffRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error("rgStaffRecordGroup",e);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiinlog/findIncidentTypeDescList", method = RequestMethod.GET)
	public @ResponseBody List<String> findIncidentTypeDescList() {
		List<String> listOfRecords = null;
		try {
			listOfRecords = oiiinlogservice.findIncidentTypeDescList();
		} catch (Exception e) {
			logger.error("findIncidentTypeDescList: ", e);
		}
		return listOfRecords;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiinlog/findIntLocationsList", method = RequestMethod.GET)
	public @ResponseBody List<String> findIntLocationsList() {
		List<String> listOfRecords = null;
		try {
			listOfRecords = oiiinlogservice.findIntLocationsList();
		} catch (Exception e) {
			logger.error("findIntLocationsList: ", e);
		}
		return listOfRecords;
	}
	
	/**
	 * getting Call Module OIDINCDE
	 * 
	 * @return List<String>
	 * 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiinlog/agencyIncidentsCallModuleOidincde", method = RequestMethod.GET)
	public List<String> agencyIncidentsCallModuleOidincde() {
		List<String> recordList = new ArrayList<>();
		try {
			recordList = oiiinlogservice.agencyIncidentsCallModuleOidincde();
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}
	

	/**
	 * getting Call Module OIDINCDE
	 * 
	 * @return List<String>
	 * 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiinlog/agencyIncidentsCallModuleOiuincrp", method = RequestMethod.GET)
	public List<String> agencyIncidentsCallModuleOiuincrp() {
		List<String> recordList = new ArrayList<>();
		try {
			recordList = oiiinlogservice.agencyIncidentsCallModuleOiuincrp();
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}
}