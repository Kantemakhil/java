package net.syscon.s4.inst.offenderissuestracking;

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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.offenderissuestracking.beans.VGrievanceInquiry;

@EliteController
public class OiigrievController {
	@Autowired
	private OiigrievService oiigrievService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiigrievController.class.getName());

	/**
	 * getting rgAgy LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiigriev/rgAgyRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oiigrievService.rgAgyRecordGroup();
		} catch (Exception e) {
			AgencyLocations obj = new AgencyLocations();
			logger.error("rgAgyRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgGrieType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiigriev/rgGrieTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgGrieTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			recordList = oiigrievService.rgGrieTypeRecordGroup(user);
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgGrieTypeRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStaffAsg LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiigriev/rgStaffAsgRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgStaffAsgRecordGroup() {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = oiigrievService.rgStaffAsgRecordGroup();
		} catch (Exception e) {
			StaffMembers obj = new StaffMembers();
			logger.error("rgStaffAsgRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgLevel LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiigriev/rgLevelRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgLevelRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oiigrievService.rgLevelRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgLevelRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStaffInv LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiigriev/rgStaffInvRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgStaffInvRecordGroup() {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = oiigrievService.rgStaffInvRecordGroup();
		} catch (Exception e) {
			StaffMembers obj = new StaffMembers();
			logger.error("rgStaffInvRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiigriev/grieDetExecuteQuery", method = RequestMethod.POST)
	public List<VGrievanceInquiry> grieDetExecuteQuery(@RequestBody VGrievanceInquiry searchBean) {
		List<VGrievanceInquiry> searchResult = new ArrayList<>();
		try {
			String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userId);
			searchResult = oiigrievService.grieDetExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("grieDetExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiigriev/whenNewRecordInstance", method = RequestMethod.GET)
	public String whenNewRecordInstance() {
		String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		String searchResult = null;
		try {
			searchResult = oiigrievService.whenNewRecordInstance(user);
		} catch (Exception e) {
			logger.error("whenNewRecordInstance", e);
		}
		return searchResult;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiigriev/rgGrieReasonCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgGrieReasonCodeRecordGroup( String grievType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oiigrievService.rgGrieReasonCodeRecordGroup(grievType);
		} catch (Exception e) {
			logger.error("rgGrievReasonRecordGroup", e);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiigriev/rgGrieTransactionTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgGrieTransactionRecordGroup( String grievType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oiigrievService.rgGrieTransactionRecordGroup(grievType);
		} catch (Exception e) {
			logger.error("rgGrievReasonRecordGroup", e);
		}
		return recordList;
	}
}