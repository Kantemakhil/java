package net.syscon.s4.inst.visitsmanagement;

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
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderRestrictions;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * Class OcuprestController
 */
@EliteController
public class OcuprestController {
	@Autowired
	private OcuprestService ocuprestService;
	
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuprestController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuprest/offExecuteQuery", method = RequestMethod.POST)
	public List<VHeaderBlock> offExecuteQuery(@RequestBody final VHeaderBlock searchBean) {
		List<VHeaderBlock> searchResult = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		if(!checkOffenderModulesAccess(userName,"read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			//String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = ocuprestService.offExecuteQuery(searchBean);
		} catch (Exception e) {
			final VHeaderBlock bean = new VHeaderBlock();
			logger.error("In method offExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * getting rgAuthorisedBy LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuprest/rgAuthorisedByRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgAuthorisedByRecordGroup() {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = ocuprestService.rgAuthorisedByRecordGroup();
		} catch (Exception e) {
			final StaffMembers obj = new StaffMembers();
			logger.error("In method rgAuthorisedByRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgRestrictionType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuprest/rgRestrictionTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgRestrictionTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocuprestService.rgRestrictionTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgRestrictionTypeRecordGroup", e);
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
	@RequestMapping(value = "/ocuprest/vOffRestExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderRestrictions> vOffRestExecuteQuery(@RequestBody final VOffenderRestrictions searchBean) {
		List<VOffenderRestrictions> searchResult = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		if(!checkOffenderModulesAccess(userName,"read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = ocuprestService.vOffRestExecuteQuery(searchBean);
		} catch (Exception e) {
			final VOffenderRestrictions bean = new VOffenderRestrictions();
			logger.error("In method vOffRestExecuteQuery", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	private Boolean checkOffenderModulesAccess(String userId, String role) {
		return commonService.checkOffenderSpecificScreenAccess(userId, role);
	}
}