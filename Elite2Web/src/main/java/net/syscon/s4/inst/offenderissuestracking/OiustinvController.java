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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.inst.offenderissuestracking.beans.OffenderGrievStaffs;
import net.syscon.s4.inst.offenderissuestracking.beans.OffenderGrievStaffsCommitBean;

/**
 * OiustinvController
 */
@EliteController
public class OiustinvController {
	@Autowired
	private OiustinvService oiustinvService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiustinvController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiustinv/offenderGrievStaffsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderGrievStaffs> offenderGrievStaffsExecuteQuery(
			@RequestBody final OffenderGrievStaffs searchBean) {
		List<OffenderGrievStaffs> searchResult = new ArrayList<>();
		try {
			searchResult = oiustinvService.offenderGrievStaffsExecuteQuery(searchBean);
		} catch (final Exception e) {
			final OffenderGrievStaffs bean = new OffenderGrievStaffs();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiustinv/offenderGrievStaffsCommit", method = RequestMethod.POST)
	public @ResponseBody List<OffenderGrievStaffs> offenderGrievStaffsCommit(
			@RequestBody final OffenderGrievStaffsCommitBean commitBean) {
		List<OffenderGrievStaffs> liReturn = new ArrayList<>();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oiustinvService.offenderGrievStaffsCommit(commitBean);
		} catch (final Exception e) {
			final OffenderGrievStaffs error = new OffenderGrievStaffs();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg);
			liReturn.add(error);
		}
		return liReturn;
	}

	/**
	 * getting rgStaff LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiustinv/rgStaffRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgStaffRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oiustinvService.rgStaffRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/oiustinv/offenderGrievStaffsPostQuery", method = RequestMethod.POST)
	public List<StaffMembers> offenderGrievStaffsPostQuery(@RequestBody final StaffMembers searchBean) {
		List<StaffMembers> searchResult = new ArrayList<>();
		try {
			searchResult = oiustinvService.offenderGrievStaffsPostQuery(searchBean);
		} catch (final Exception e) {
			final StaffMembers bean = new StaffMembers();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
}
