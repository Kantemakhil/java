package net.syscon.s4.workspace;

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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;

/**
 * Class OcdsmemoController
 */
@EliteController
public class OcdsmemoController {
	@Autowired
	private OcdsmemoService ocdsmemoService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdsmemoController.class.getName());

	/**
	 * getting rgWorkType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdsmemo/rgWorkTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgWorkTypeRecordGroup(@RequestParam(value = "caseloadType") final String caseloadType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdsmemoService.rgWorkTypeRecordGroup(caseloadType);
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocdsmemo:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgWorkSubType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdsmemo/rgWorkSubTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgWorkSubTypeRecordGroup(@RequestParam(value = "workType") final String workType,
			@RequestParam(value = "caseloadType") final String caseloadType) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocdsmemoService.rgWorkSubTypeRecordGroup(workType, caseloadType);
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocdsmemo:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgSeverity LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdsmemo/rgSeverityRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSeverityRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdsmemoService.rgSeverityRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocdsmemo:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStaff LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdsmemo/rgStaffRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgStaffRecordGroup() {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = ocdsmemoService.rgStaffRecordGroup();
		} catch (Exception e) {
			StaffMembers obj = new StaffMembers();
			logger.error("Exception : Ocdsmemo:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdsmemo/staffMemoComitt", method = RequestMethod.POST)
	public @ResponseBody String staffMemoComitt(@RequestBody final StaffMembers commitBean) {
		String errorCode;
		try {
			errorCode = ocdsmemoService.staffMemoComitt(commitBean);

		} catch (final Exception e) {
			errorCode = null;
			logger.error("staffMemoComitt :", e);
		}

		return errorCode;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdsmemo/getStaffMessage", method = RequestMethod.POST)
	public @ResponseBody String getStaffMessage(@RequestBody final StaffMembers commitBean) {
		String code;
		try {
			code = ocdsmemoService.getStaffMessage(commitBean);

		} catch (final Exception e) {
			code = null;
			
			logger.error("getStaffMessage :", e);
		}

		return code;
	}

}