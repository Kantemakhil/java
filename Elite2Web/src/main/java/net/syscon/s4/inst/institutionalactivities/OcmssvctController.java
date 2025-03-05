package net.syscon.s4.inst.institutionalactivities;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.CourseActivityParties;
import net.syscon.s4.im.beans.CourseActivityPartiesCommitBean;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * OcmssvctController
 */
@EliteController
public class OcmssvctController {
	@Autowired
	private OcmssvctService ocmssvctService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmssvctController.class.getName());

	/**
	 * getting rgStaffNameInst LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmssvct/rgStaffNameInstRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgStaffNameInstRecordGroup(@RequestParam("caseloadType") final String caseloadType,
			@RequestParam("providerPartyCode") final String providerPartyCode) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmssvctService.rgStaffNameInstRecordGroup(caseloadType, providerPartyCode);
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocmssvct:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStaffNameComm LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmssvct/rgStaffNameCommRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgStaffNameCommRecordGroup(@RequestParam("providerId") final Long providerId) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmssvctService.rgStaffNameCommRecordGroup(providerId);
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocmssvct:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgTeamMembers LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmssvct/rgTeamMembersRecordGroup", method = RequestMethod.GET)
	public List<TeamMembers> rgTeamMembersRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<TeamMembers> recordList = new ArrayList<TeamMembers>();
		try {
			recordList = ocmssvctService.rgTeamMembersRecordGroup();
		} catch (Exception e) {
			final TeamMembers obj = new TeamMembers();
			logger.error("Exception : Ocmssvct:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStaffNameInstProg LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmssvct/rgStaffNameInstProgRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgStaffNameInstProgRecordGroup(
			@RequestParam("providerPartyCode") final String providerPartyCode,
			@RequestParam("programId") final Long programId) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmssvctService.rgStaffNameInstProgRecordGroup(providerPartyCode, programId);
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocmssvct:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStaffNameCommProg LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmssvct/rgStaffNameCommProgRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgStaffNameCommProgRecordGroup(@RequestParam("providerPartyId") final Long providerPartyId,
			@RequestParam("programId") final Long programId) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmssvctService.rgStaffNameCommProgRecordGroup(providerPartyId, providerPartyId);
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocmssvct:", e);
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
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmssvct/crsActPtyExecuteQuery", method = RequestMethod.POST)
	public List<CourseActivityParties> crsActPtyExecuteQuery(@RequestBody final CourseActivityParties searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<CourseActivityParties> searchResult = new ArrayList<>();
		try {
			searchResult = ocmssvctService.crsActPtyExecuteQuery(searchBean);
		} catch (Exception e) {
			final CourseActivityParties bean = new CourseActivityParties();
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
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmssvct/crsActPtyCommit", method = RequestMethod.POST)
	public @ResponseBody List<CourseActivityParties> crsActPtyCommit(
			@RequestBody final CourseActivityPartiesCommitBean commitBean) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<CourseActivityParties> liReturn = new ArrayList<>();
		try { 
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			 liReturn = ocmssvctService.crsActPtyCommit(commitBean);
		} catch (Exception e) {
			final CourseActivityParties bean = new CourseActivityParties();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage().toUpperCase());
			liReturn.add(bean);
			logger.error("Exception : Ocmssvct", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmssvct/extConExecuteQuery", method = RequestMethod.POST)
	public List<CourseActivityParties> extConExecuteQuery(@RequestBody final CourseActivityParties searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<CourseActivityParties> searchResult = new ArrayList<>();
		try {
			searchResult = ocmssvctService.extConExecuteQuery(searchBean);
		} catch (Exception e) {
			final CourseActivityParties bean = new CourseActivityParties();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCMSSVCT");
	}
}