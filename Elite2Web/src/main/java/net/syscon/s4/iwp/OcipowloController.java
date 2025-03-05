package net.syscon.s4.iwp;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.cm.primaryofficeragentassignment.beans.VAssignedOffenders;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VStaffLocation;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;

@EliteController
public class OcipowloController {
	@Autowired
	private OcipowloService ocipowloService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcipowloController.class.getName());
	
	
	/**
	 * Fetch the lov data from database
	 *@return List<AgencyLocations>
	 * @param caseLoadId
	 *
	 */

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocipowlo/cgfkStaffLr1DspDescriptionRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkStaffLr1DspDescriptionRecordGroup(@RequestParam("caseLoadId") String caseLoadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ocipowloService.cgfkStaffLr1DspDescriptionRecordGroup(caseLoadId);
		} catch (Exception e) {
			logger.error("Exception : Ocipowlo:", e);
		}
		return recordList;
	}

	/**
	 * Fetch the lov data from database
	 *@return  List<ReferenceCodes>
	 * 
	 *
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocipowlo/positionLovRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> positionLovRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocipowloService.positionLovRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Ocipowlo:", e);
		}
		return recordList;
	}
	/**
	 * Fetch the lov data from database
	 *@return  List<ReferenceCodes> 
	 *  
	 *
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocipowlo/roleLovRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> roleLovRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocipowloService.roleLovRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Ocipowlo:", e);
		}
		return recordList;
	}
	/**
	 * Fetch the lov data from database
	 *@return List<ReferenceCodes> 
	 * 
	 *
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocipowlo/scheduleTypeLovRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> scheduleTypeLovRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocipowloService.scheduleTypeLovRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Ocipowlo:", e);
		}
		return recordList;
	}
	/**
	 * Fetch the records from database table
	 *@return List<VStaffLocation>
	 * @param searchBean
	 *
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocipowlo/vOffDetExecuteQuery", method = RequestMethod.POST)
	public List<VStaffLocation> vOffDetExecuteQuery(@RequestBody VStaffLocation searchBean) {
		List<VStaffLocation> searchResult = new ArrayList<>();
		try {
			searchResult = ocipowloService.vOffDetExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
	/**
	 * Fetch the records from database table
	 *@return  List<VAssignedOffenders>
	 * @param searchBean
	 *
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocipowlo/vAssOffExecuteQuery", method = RequestMethod.POST)
	public List<VAssignedOffenders> vAssOffExecuteQuery(@RequestBody VAssignedOffenders searchBean) {
		List<VAssignedOffenders> searchResult = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = ocipowloService.vAssOffExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

}