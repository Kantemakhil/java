package net.syscon.s4.inst.booking.maintainence;

import java.util.ArrayList;
import java.util.HashMap;
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
import net.syscon.s4.inst.booking.beans.ContactPersonTypes;
import net.syscon.s4.inst.booking.beans.ContactPersonTypesCommitBean;

@EliteController
public class OimctactController {
	@Autowired
	private OimctactService oimctactService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimctactController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimctact/contactPersonTypesExecuteQuery", method = RequestMethod.POST)
	public List<ContactPersonTypes> contactPersonTypesExecuteQuery(@RequestBody final ContactPersonTypes searchBean) {
		List<ContactPersonTypes> searchResult = new ArrayList<>();
		try {
			searchResult = oimctactService.contactPersonTypesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimctact/contactPersonTypesCommit", method = RequestMethod.POST)
	public ContactPersonTypes contactPersonTypesCommit(@RequestBody final ContactPersonTypesCommitBean commitBean) {
		int liReturn = 0;
		final ContactPersonTypes liReturnData = new ContactPersonTypes();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oimctactService.contactPersonTypesCommit(commitBean);
		} catch (Exception e) {
			final String errorMsg = "Error : " + e.getMessage();
			liReturnData.setErrorMessage(errorMsg.toUpperCase());
			logger.error("contactPersonTypesCommit :", e);
		}
		if (liReturn == 1) {
			liReturnData.setSealFlag("1");
		} else {
			liReturnData.setSealFlag("0");
		}
		return liReturnData;
	}

	/**
	 * getting rgRelationshipType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimctact/rgRelationshipTypeRecordGroup", method = RequestMethod.GET)
	public List<HashMap<String, Object>> rgRelationshipTypeRecordGroup() {
		List<HashMap<String, Object>> recordList = new ArrayList<>();
		try {
			recordList = oimctactService.rgRelationshipTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgContactType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimctact/rgContactTypeRecordGroup", method = RequestMethod.GET)
	public List<HashMap<String, Object>> rgContactTypeRecordGroup() {
		List<HashMap<String, Object>> recordList = new ArrayList<>();
		try {
			recordList = oimctactService.rgContactTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

}