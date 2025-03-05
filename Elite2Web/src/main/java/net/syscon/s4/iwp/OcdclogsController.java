package net.syscon.s4.iwp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderCaseNotesCommitBean;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;
import net.syscon.s4.inst.workflow.maintenance.beans.StaffLocationRoles;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@EliteController
public class OcdclogsController {

	@Autowired
	private OcdclogsService ocdclogsService;
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdclogsController.class.getName());

	/**
	 * getting rgCasenoteType LOV values 1
	 */
	@RequestMapping(value = "/ocdclogs/rgCasenoteTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCasenoteTypeRecordGroup(
			@RequestParam(value = "caseloadType") final String caseloadType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			recordList = ocdclogsService.rgCasenoteTypeRecordGroup(caseloadType,userName);
		} catch (Exception e) {
			logger.error("rgCasenoteTypeRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * getting rgCasenoteSubtype LOV values 2
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclogs/rgCasenoteSubtypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCasenoteSubtypeRecordGroup(@RequestParam(value = "caseloadType") final String caseloadType,
			@RequestParam(value = "caseNoteType") final String caseNoteType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			recordList = ocdclogsService.rgCasenoteSubtypeRecordGroup(caseNoteType,userName,caseloadType);
		} catch (Exception e) {
			logger.error("rgCasenoteSubtypeRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * getting rgCasenoteSubtype LOV values 2
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclogs/rgCasenotestaffNameRecordGroup", method = RequestMethod.GET)
	public List<OffenderCaseNotes> rgCasenotestaffNameRecordGroup(@RequestParam(value = "tip") final String tip) {
		List<OffenderCaseNotes> recordList = new ArrayList<OffenderCaseNotes>();
		try {
			recordList = ocdclogsService.rgCasenotestaffNameRecordGroup(tip);
		} catch (Exception e) {
			logger.error("rgCasenotestaffNameRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table 3
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclogs/offNotesExecuteQuery", method = RequestMethod.POST)
	public List<OffenderCaseNotes> offNotesExecuteQuery(@RequestBody final OffenderCaseNotes searchBean) {
		List<OffenderCaseNotes> searchResult = new ArrayList<OffenderCaseNotes>();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = ocdclogsService.offNotesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offNotesExecuteQuery :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclogs/offSchExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderAllSchedules> offSchExecuteQuery(@RequestBody final VOffenderAllSchedules searchBean) {
		List<VOffenderAllSchedules> searchResult = new ArrayList<>();
		try {
			searchResult = ocdclogsService.offSchExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offSchExecuteQuery :", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclogs/schExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderAllSchedules> schExecuteQuery(@RequestBody final BigDecimal offenderBookId) {
		List<VOffenderAllSchedules> searchResult = new ArrayList<>();
		try {
			searchResult = ocdclogsService.schExecuteQuery(offenderBookId);
		} catch (Exception e) {
			logger.error("schExecuteQuery :", e);
		}
		return searchResult;
	}

	/**
	 * getting rgOutcome LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclogs/lvLoginUserStaffId", method = RequestMethod.GET)
	public StaffMembers rgOutcomeRecordGroup() {
		StaffMembers staffName = new StaffMembers();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			staffName = ocdclogsService.lvLoginUserStaffName(userName);
		} catch (Exception e) {
			logger.error("lvLoginUserStaffId :", e);
		}
		return staffName;
	}

	/**
	 * getting rgEventOutcome LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclogs/rgEventOutcomeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgEventOutcomeRecordGroup(@RequestParam(value = "threeip") final String threeip) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdclogsService.rgEventOutcomeRecordGroup(threeip);
		} catch (Exception e) {
			logger.error("rgEventOutcomeRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * getting rgLocation LOV values
	 */
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclogs/rgLocationRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgLocationRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ocdclogsService.rgLocationRecordGroup();
		} catch (Exception e) {
			logger.error("rgLocationRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * getting rgScheduleType LOV values
	 */
	//@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdclogs/rgScheduleTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgScheduleTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdclogsService.rgScheduleTypeRecordGroup();
		} catch (Exception e) {
			logger.error("rgScheduleTypeRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * getting rgScheduleSubType LOV values
	 */
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclogs/rgScheduleSubTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgScheduleSubTypeRecordGroup(
			@RequestParam(value = "eventType") final String eventType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdclogsService.rgScheduleSubTypeRecordGroup(eventType);
		} catch (Exception e) {
			logger.error("rgScheduleSubTypeRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * getting rgnoteSource LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclogs/rgnoteSourceRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgnoteSourceRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdclogsService.rgnoteSourceRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgStaffname LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclogs/rgStaffnameRecordGroup", method = RequestMethod.GET)
	public List<StaffLocationRoles> rgStaffnameRecordGroup() {
		List<StaffLocationRoles> recordList = new ArrayList<StaffLocationRoles>();
		try {
			recordList = ocdclogsService.rgStaffnameRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);

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
	@RequestMapping(value = "/ocdclogs/offNotesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offNotesCommit(@RequestBody final OffenderCaseNotesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdclogsService.offNotesCommit(commitBean);
			if(1==liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "20");
			}
		} catch (Exception e) {
			logger.error("offNotesCommit :", e);
		}
		return liReturn;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdclogs/offSchCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offSchCommit(@RequestBody final VOffenderAllSchedulesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdclogsService.offSchCommit(commitBean);
			if(1==liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "21");
			}
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclogs/validateNoteTypeSubType", method = RequestMethod.POST)
	public Integer validateNoteTypeSubType(@RequestBody final OffenderCaseNotes searchBean) {
		int count = 0;
		try {
			count = ocdclogsService.validateNoteTypeSubType(searchBean);
		} catch (Exception e) {
			logger.error("validateNoteTypeSubType :", e);
		}
		return count;
	}

	/* Getting Module Name */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclogs/getModuleName", method = RequestMethod.POST)
	public String getModuleName(@RequestBody final OffenderCaseNotes searchBean) {
		String mName = "";
		try {
			mName = ocdclogsService.getModuleName(searchBean);
		} catch (Exception e) {
			logger.error("getModuleName :", e);
		}
		return mName;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclogs/rglovOutComeRecordGroup", method = RequestMethod.GET)
	public List<Areas> rglovOutComeRecordGroup() {
		List<Areas> recordList = new ArrayList<Areas>();
		try {
			recordList = ocdclogsService.rglovOutComeRecordGroup();
		} catch (Exception e) {
			logger.error("rglovOutComeRecordGroup :", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdclogs/caseNoteTextData", method = RequestMethod.POST)
	public String caseNoteTextData(@RequestBody final OffenderCaseNotes caseNotesObj) {
		String staffId = null;
		try {
			staffId = ocdclogsService.getcaseNoteTextData(caseNotesObj);
		} catch (Exception e) {
			logger.error("getStaffId :", e);
			 staffId=null;
		}
		return staffId;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclogs/checkNonAssociations", method = RequestMethod.POST)
	public String checkNonAssociations(@RequestBody final VOffenderAllSchedulesCommitBean commitBean) {
		String resultString = null;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			resultString = ocdclogsService.checkNonAssociations(commitBean);
		} catch (final Exception e) {
			logger.error("Error occured in checkNonAssociations :", e);
		}
		return resultString;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclogs/getEmailSmsFlag", method = RequestMethod.POST)
	public List<EventMeasures> getEmailSmsFlag(@RequestBody final VOffenderAllSchedules beanOne) {
		List<EventMeasures> returnList=new ArrayList<>();
		try {
			returnList = ocdclogsService.getEmailSmsFlag(beanOne);
		} catch (Exception e) {
			logger.error("getEmailSmsFlag :", e);
			return returnList;
		}
		return returnList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdclogs/getCancelFlag", method = RequestMethod.POST)
	public String getCancelFlag(@RequestBody final VOffenderAllSchedules beanOne) {
		String returnList=null;
		try {
			returnList = ocdclogsService.getCancelFlag(beanOne);
		} catch (Exception e) {
			logger.error("getCancelFlag :", e);
			return returnList;
		}
		return returnList;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdclogs/checkCasenoteSubType", method = RequestMethod.GET)
	public @ResponseBody String checkCasenoteSubType(@RequestParam(value = "caseNoteType") final String caseNoteType, 
			@RequestParam(value = "caseNoteSubType") final String caseNoteCode) {
		String subTypeFlag = null;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			subTypeFlag = ocdclogsService.checkCasenoteSubType(caseNoteType, caseNoteCode, userName);
		} catch (Exception e) {

			logger.error("offNotesCommit", e);
		}
		return subTypeFlag;
	}
}