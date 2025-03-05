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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.visitsmanagement.beans.VOffContactPersons;
import net.syscon.s4.pkgs.common.CommonService;
import net.syscon.s4.inst.booking.beans.OffenderContactPersonsCommitBean;

/**
 * Class OcuavisnController
 */
@EliteController
public class OcuavisnController {
	@Autowired
	private OcuavisnService ocuavisnService;
	
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuavisnController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuavisn/vOffAuthVisExecuteQuery", method = RequestMethod.POST)
	public List<VOffContactPersons> vOffAuthVisExecuteQuery(@RequestBody final VOffContactPersons searchBean) {
		List<VOffContactPersons> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = ocuavisnService.vOffAuthVisExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
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
	@RequestMapping(value = "/ocuavisn/vOffAuthVisCommit", method = RequestMethod.POST)
	public @ResponseBody Integer vOffAuthVisCommit(@RequestBody final OffenderContactPersonsCommitBean commitBean) {
		int liReturn = 0;
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocuavisnService.vOffAuthVisCommit(commitBean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}

	/**
	 * getting rgContactType LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuavisn/rgContactTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgContactTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = ocuavisnService.rgContactTypeRecordGroup();
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting rgRelationshipType LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuavisn/rgRelationshipTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgRelationshipTypeRecordGroup(
			@RequestParam(value = "contactType") final String contactType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = ocuavisnService.rgRelationshipTypeRecordGroup(contactType);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}
	
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuavisn/getGlobalRestriction", method = RequestMethod.GET)
	public VOffContactPersons getGlobalRestriction(@RequestParam(value="personId") final Integer personId,
			@RequestParam(value="offenderBookId") final Integer offenderBookId,@RequestParam(value="visitDate") final Long visitDate) {
		VOffContactPersons searchResult = new VOffContactPersons();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = ocuavisnService.getGlobalRestriction(personId,offenderBookId,visitDate);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuavisn/rgRelationshipTypeTotalRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgRelationshipTypeTotalRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = ocuavisnService.rgRelationshipTypeTotalRecordGroup();
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCUAVISN");
	}

}