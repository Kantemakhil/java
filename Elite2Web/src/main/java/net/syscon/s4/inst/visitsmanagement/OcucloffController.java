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
import net.syscon.s4.im.beans.VDistinctLinkedOffenders;
import net.syscon.s4.pkgs.common.CommonService;

@EliteController
public class OcucloffController {
	@Autowired
	private OcucloffService ocucloffService;
	
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcucloffController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocucloff/contactsExecuteQuery", method = RequestMethod.POST)
	public List<VDistinctLinkedOffenders> contactsExecuteQuery(@RequestBody final VDistinctLinkedOffenders searchBean) {
		List<VDistinctLinkedOffenders> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = ocucloffService.contactsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("contactsExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * getting rgRelationshipType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucloff/rgRelationshipTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgRelationshipTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocucloffService.rgRelationshipTypeRecordGroup();
		} catch (Exception e) {
			logger.error("rgRelationshipTypeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgContactType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucloff/rgContactTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgContactTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocucloffService.rgContactTypeRecordGroup();
		} catch (Exception e) {
			logger.error("rgContactTypeRecordGroup", e);
		}
		return recordList;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCUCLOFF");
	}

}