package net.syscon.s4.sa.audit;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.StaffMembers;

/**
 * class OuiauactController
 */
@EliteController
public class OuiauactController {
	@Autowired
	private OuiauactService ouiauactService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OuiauactController.class.getName());

	/**
	 * getting rgStfMember LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ouiauact/rgStfMemberRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgStfMemberRecordGroup() {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = ouiauactService.rgStfMemberRecordGroup();
		} catch (final Exception e) {
			final StaffMembers obj = new StaffMembers();
			logger.error("Exception : Ouiauact:", e);
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
	@RequestMapping(value = "/ouiauact/getUserDetailExecuteQuery", method = RequestMethod.POST)
	public List<SysTagAuditFormGetUserDetail> getUserDetailExecuteQuery(
			@RequestBody final SysTagAuditFormGetUserDetail searchBean) {
		List<SysTagAuditFormGetUserDetail> searchResult = new ArrayList<>();
		try {
			searchResult = ouiauactService.getUserDetailExecuteQuery(searchBean);
		} catch (final Exception e) {
			final SysTagAuditFormGetUserDetail bean = new SysTagAuditFormGetUserDetail();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}