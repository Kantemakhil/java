package net.syscon.s4.cm.programsservices.maintenance;

import java.util.ArrayList;
import java.util.Arrays;
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
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderCaseNotesCommitBean;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.im.beans.OffenderPrgObligationsCommitBean;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OffenderProgramProfilesCommitBean;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEventsCommitBean;

@EliteController
public class OidowrelController {

	@Autowired
	private OidowrelService oidowrelService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidowrelController.class.getName());

	/**
	 * getting rgProgram LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidowrel/rgProgramRecordGroup", method = RequestMethod.GET)
	public List<ProgramServices> rgProgramRecordGroup() {
		List<ProgramServices> recordList = new ArrayList<ProgramServices>();
		try {
			recordList = oidowrelService.rgProgramRecordGroup();
		} catch (Exception e) {
			ProgramServices obj = new ProgramServices();
			logger.error(this.getClass().getName()+" rgProgramRecordGroup error :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgPriority LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidowrel/rgPriorityRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPriorityRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidowrelService.rgPriorityRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error(this.getClass().getName()+" rgPriorityRecordGroup error :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgEndReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidowrel/rgEndReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgEndReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidowrelService.rgEndReasonRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
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
	@RequestMapping(value = "/oidowrel/offPrgObligationsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPrgObligations> offPrgObligationsExecuteQuery(@RequestBody OffenderPrgObligations searchBean) {
		List<OffenderPrgObligations> searchResult = new ArrayList<>();
		try {
			searchResult = oidowrelService.offPrgObligationsExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderPrgObligations bean = new OffenderPrgObligations();
			logger.error(this.getClass().getName() + "offPrgObligationsExecuteQuery :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * getting rgCancelReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidowrel/rgCancelReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCancelReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidowrelService.rgCancelReasonRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error(this.getClass().getName()+" rgCancelReasonRecordGroup error :", e);
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
	@RequestMapping(value = "/oidowrel/offPrgObligationsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offPrgObligationsCommit(@RequestBody OffenderPrgObligationsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidowrelService.offPrgObligationsCommit(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " offPrgObligationsCommit :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidowrel/offProgramProfilesExecuteQuery", method = RequestMethod.POST)
	public List<OffenderProgramProfiles> offProgramProfilesExecuteQuery(
			@RequestBody OffenderProgramProfiles searchBean) {
		List<OffenderProgramProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oidowrelService.offProgramProfilesExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderProgramProfiles bean = new OffenderProgramProfiles();
			logger.error(this.getClass().getName() + "offProgramProfilesExecuteQuery :", e);
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
	@RequestMapping(value = "/oidowrel/offProgramProfilesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offProgramProfilesCommit(@RequestBody OffenderProgramProfilesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidowrelService.offProgramProfilesCommit(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "offProgramProfilesCommit :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidowrel/vOffenderCourseEventsExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderCourseEvents> vOffenderCourseEventsExecuteQuery(
			@RequestBody VOffenderCourseEvents searchBean) {
		List<VOffenderCourseEvents> searchResult = new ArrayList<>();
		try {
			searchResult = oidowrelService.vOffenderCourseEventsExecuteQuery(searchBean);
		} catch (Exception e) {
			VOffenderCourseEvents bean = new VOffenderCourseEvents();
			logger.error(this.getClass().getName() + "vOffenderCourseEventsExecuteQuery :", e);
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
	@RequestMapping(value = "/oidowrel/vOffenderCourseEventsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer vOffenderCourseEventsCommit(@RequestBody VOffenderCourseEventsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidowrelService.vOffenderCourseEventsCommit(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" vOffenderCourseEventsCommit error :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidowrel/offenderCaseNotesExecuteQuery", method = RequestMethod.POST)
	public List<OffenderCaseNotes> offenderCaseNotesExecuteQuery(@RequestBody OffenderCaseNotes searchBean) {
		List<OffenderCaseNotes> searchResult = new ArrayList<>();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = oidowrelService.offenderCaseNotesExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderCaseNotes bean = new OffenderCaseNotes();
			logger.error(this.getClass().getName()+" offenderCaseNotesExecuteQuery error :", e);
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
	@RequestMapping(value = "/oidowrel/offenderCaseNotesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offenderCaseNotesCommit(@RequestBody OffenderCaseNotesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidowrelService.offenderCaseNotesCommit(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "offenderCaseNotesCommit :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidowrel/viewLink", method = RequestMethod.GET)
	public List<ReferenceCodes> dspDescriptionRecordGroup() {
		ReferenceCodes listOne = new ReferenceCodes();
		ReferenceCodes listTwo = new ReferenceCodes();
		listOne.setCode("1");
		listOne.setDescription("Confirmed");
		listTwo.setCode("2");
		listTwo.setDescription("Pending");
		List<ReferenceCodes> viewList = Arrays.asList(listOne, listTwo);
		return viewList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidowrel/staffNameExcecuteQuery", method = RequestMethod.GET)
	public String staffNameExcecuteQuery() {
		String staffName = null;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			staffName = oidowrelService.getStaffName(userName);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "staffNameExcecuteQuery :", e);
		}
		return staffName;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidowrel/getModuleName", method = RequestMethod.POST)
	public OffenderCaseNotes getModuleName(@RequestBody final OffenderCaseNotes searchBean) {
		OffenderCaseNotes searchResult = new OffenderCaseNotes();
		try {
			searchResult = oidowrelService.getModuleName(searchBean);
		} catch (final Exception e) {
			logger.error(this.getClass().getName()+" getModuleName error :", e);
			searchResult.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidowrel/checkNonAssociations", method = RequestMethod.POST)
	public String checkNonAssociations(@RequestBody final OffenderProgramProfilesCommitBean commitBean) {
		String resultString = null;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			resultString = oidowrelService.checkNonAssociations(commitBean);
		} catch (final Exception e) {
			logger.error("Error occured in checkNonAssociations :", e);
		}
		return resultString;
	}

}