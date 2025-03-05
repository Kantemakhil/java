package net.syscon.s4.inst.systemsearch;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.beans.FormAccessibleFormsCommitBean;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.VHeaderBlock2;
import net.syscon.s4.im.beans.OmsModules;

@EliteController
public class OsihrsumController {
	@Autowired
	private OsihrsumService osihrsumService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OsihrsumController.class.getName());

	/**
	 * getting cgfkFafDestinationForm LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osihrsum/cgfkFafDestinationFormRecordGroup", method = RequestMethod.GET)
	public List<OmsModules> cgfkFafDestinationFormRecordGroup() {
		List<OmsModules> recordList = new ArrayList<OmsModules>();
		try {
			recordList = osihrsumService.cgfkFafDestinationFormRecordGroup();
		} catch (final Exception e) {
			logger.error("cgfkFafDestinationFormRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osihrsum/vHisBooExecuteQuery", method = RequestMethod.GET)
	public List<VHistoricalBookings> vHisBooExecuteQuery(@RequestParam final String rootOffenderId) {
		List<VHistoricalBookings> searchResult = new ArrayList<>();
		try {
			searchResult = osihrsumService.vHisBooExecuteQuery(rootOffenderId);
		} catch (final Exception e) {
			logger.error("vHisBooExecuteQuery :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osihrsum/fafExecuteQuery", method = RequestMethod.POST)
	public List<FormAccessibleForms> fafExecuteQuery(@RequestBody final VHeaderBlock2 searchBean) {
		List<FormAccessibleForms> searchResult = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = osihrsumService.fafExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("fafExecuteQuery :", e);
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
	@RequestMapping(value = "/osihrsum/fafCommit", method = RequestMethod.POST)
	public @ResponseBody FormAccessibleForms fafCommit(@RequestBody final FormAccessibleFormsCommitBean commitBean) {
		FormAccessibleForms returnObj = new FormAccessibleForms();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			returnObj = osihrsumService.fafCommit(commitBean);
		} catch (final Exception e) {
			logger.error("fafCommit ", e);
		}
		return returnObj;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osihrsum/getImageData", method = RequestMethod.POST)
	public Images getImageData(@RequestBody final VHistoricalBookings searchBean) {
		Images searchResult = null;
		try {
			searchResult = osihrsumService.getImageData(searchBean);
		} catch (final Exception e) {
			logger.error("getImageData", e);
		}
		return searchResult;
	}

}